package com.example.producer.service;

import com.example.producer.domain.FileCheckpoint;
import com.example.producer.repo.FileCheckpointRepository;
import com.example.producer.util.ByteCountingReader;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger; import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Pattern;

@Service
public class FileProducerService {
  private static final Logger log = LoggerFactory.getLogger(FileProducerService.class);

  private final Path inputDir;
  private final Pattern includePattern;
  private final int threads;
  private final int batchSize;
  private final int checkpointEvery;
  private final int rateLimitPerSec;
  private final SqsSendService sqs;
  private final FileCheckpointRepository repo;
  private final FileLeaseService leaseService;

  public FileProducerService(
      @Value("${producer.inputDir}") String inputDir,
      @Value("${producer.includePattern}") String includePattern,
      @Value("${producer.threads}") int threads,
      @Value("${producer.batchSize}") int batchSize,
      @Value("${producer.checkpointEvery}") int checkpointEvery,
      @Value("${producer.rateLimitPerSec}") int rateLimitPerSec,
      SqsSendService sqs,
      FileCheckpointRepository repo,
      FileLeaseService leaseService) {

    this.inputDir = Paths.get(inputDir);
    this.includePattern = Pattern.compile(includePattern);
    this.threads = threads;
    this.batchSize = Math.min(10, Math.max(1, batchSize));
    this.checkpointEvery = Math.max(1, checkpointEvery);
    this.rateLimitPerSec = rateLimitPerSec;
    this.sqs = sqs;
    this.repo = repo;
    this.leaseService = leaseService;
  }

  @PostConstruct
  public void start() throws Exception {
    List<Path> files = scanFiles();
    if (files.isEmpty()) {
      log.warn("No files matched in {}", inputDir.toAbsolutePath());
      return;
    }

    ExecutorService pool = Executors.newFixedThreadPool(threads);
    List<Future<?>> futures = new ArrayList<>();

    for (Path p : files) {
      futures.add(pool.submit(() -> processOneFile(p)));
    }

    pool.shutdown();
    for (Future<?> f : futures) f.get();
    log.info("All files processed.");
  }

  private List<Path> scanFiles() throws Exception {
    List<Path> paths = new ArrayList<>();
    try (DirectoryStream<Path> ds = Files.newDirectoryStream(inputDir)) {
      for (Path p : ds) {
        if (Files.isRegularFile(p) && includePattern.matcher(p.getFileName().toString()).matches()) {
          paths.add(p);
        }
      }
    }
    return paths;
  }

  private void processOneFile(Path file) {
    FileLock lock = null;
    try {
      lock = leaseService.tryAcquireLock(file);
      if (lock == null) {
        log.info("Skip {} (another instance holds the lock)", file);
        return;
      }

      File f = file.toFile();
      long size = f.length();

      FileCheckpoint cp = repo.findByFilePath(f.getAbsolutePath())
          .orElseGet(() -> FileCheckpoint.builder()
              .filePath(f.getAbsolutePath())
              .nextLineNumber(0)
              .nextByteOffset(0)
              .fileSizeBytes(size)
              .status("PENDING")
              .updatedAt(Instant.now())
              .build());

      if (cp.getFileSizeBytes() != size) {
        cp.setNextLineNumber(0);
        cp.setNextByteOffset(0);
        cp.setFileSizeBytes(size);
      }

      cp.setStatus("IN_PROGRESS"); repo.save(cp);

      try (ByteCountingReader reader = new ByteCountingReader(f, StandardCharsets.UTF_8, cp.getNextByteOffset())) {
        long sentSinceCheckpoint = 0;
        List<String> batch = new ArrayList<>(batchSize);

        long currentLine = cp.getNextLineNumber();
        String line;
        while ((line = reader.readLine()) != null) {
          currentLine++;

          if (rateLimitPerSec > 0) {
            Thread.sleep(1000L / rateLimitPerSec);
          }

          batch.add(line);

          if (batch.size() == batchSize) {
            sqs.sendBatch(batch);
            batch.clear();
            sentSinceCheckpoint += batchSize;

            if (sentSinceCheckpoint >= checkpointEvery) {
              updateCheckpoint(cp, currentLine, reader.getByteOffset());
              sentSinceCheckpoint = 0;
            }
          }
        }

        if (!batch.isEmpty()) {
          sqs.sendBatch(batch);
          batch.clear();
        }

        updateCheckpoint(cp, currentLine, reader.getByteOffset());
        cp.setStatus("DONE"); repo.save(cp);
        log.info("Completed {}", f.getName());
      }

    } catch (Exception e) {
      log.error("Error processing file {}: {}", file, e.toString(), e);
    } finally {
      try { leaseService.releaseLock(lock); } catch (Exception ignored) {}
    }
  }

  private void updateCheckpoint(FileCheckpoint cp, long nextLineNumber, long nextByteOffset) {
    cp.setNextLineNumber(nextLineNumber);
    cp.setNextByteOffset(nextByteOffset);
    cp.setUpdatedAt(Instant.now());
    repo.save(cp);
  }
}
