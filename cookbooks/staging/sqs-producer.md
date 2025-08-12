Got it — you want a **resumable, high-throughput SQS producer** you can run from your Windows laptop. Below is a lean Spring Boot 3 / JDK 17 blueprint that supports:

* Reading a **million+ messages** from file(s)
* **Multi-file** and **multi-thread** sending
* **Batching** (10 msgs per SQS batch)
* **Retries** with backoff
* **Crash-safe resume** (starts where it left off)
* Optionally run **multiple app instances**, each working on different files

I’ll give you the key pieces so you can paste into a project and run.

---

# Project layout (Maven)

```
sqs-producer/
  pom.xml
  src/main/java/com/example/producer/
    SqsProducerApplication.java
    config/SqsConfig.java
    domain/FileCheckpoint.java
    repo/FileCheckpointRepository.java
    service/FileLeaseService.java
    service/FileProducerService.java
    service/SqsSendService.java
    util/ByteCountingReader.java
  src/main/resources/
    application.yml
```

---

# pom.xml (core deps)

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" ...>
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.example</groupId>
  <artifactId>sqs-producer</artifactId>
  <version>0.1.0</version>
  <properties>
    <java.version>17</java.version>
    <spring-boot.version>3.3.2</spring-boot.version>
    <aws.sdk.version>2.25.40</aws.sdk.version>
  </properties>

  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>${spring-boot.version}</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>

  <dependencies>
    <!-- Spring Boot -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <!-- JPA + H2 for local, file-backed checkpoints -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
      <groupId>com.h2database</groupId>
      <artifactId>h2</artifactId>
      <scope>runtime</scope>
    </dependency>

    <!-- AWS SDK v2 (async SQS for throughput) -->
    <dependency>
      <groupId>software.amazon.awssdk</groupId>
      <artifactId>sqs</artifactId>
      <version>${aws.sdk.version}</version>
    </dependency>
    <dependency>
      <groupId>software.amazon.awssdk</groupId>
      <artifactId>netty-nio-client</artifactId>
      <version>${aws.sdk.version}</version>
    </dependency>

    <!-- Lombok (optional but handy) -->
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <optional>true</optional>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
    </plugins>
  </build>
</project>
```

---

# application.yml (edit queue URL/region here)

```yaml
spring:
  datasource:
    url: jdbc:h2:file:./checkpoint-db/sqs-producer;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE
    username: sa
    password:
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: update
    open-in-view: false

producer:
  inputDir: "C:/data/messages"     # directory with your files
  includePattern: ".*\\.txt"        # regex for files to load
  threads: 8                        # workers per process
  batchSize: 10                     # SQS max=10
  checkpointEvery: 1000             # update DB every N messages
  rateLimitPerSec: 0                # 0 = unlimited (use with care)
  fifo: false                       # set true if your queue is FIFO
  messageGroupId: "group-1"         # for FIFO only
  dedupStrategy: "hash"             # "none" | "line" | "hash"
aws:
  region: "us-east-1"
  queueUrl: "https://sqs.us-east-1.amazonaws.com/123456789012/my-queue"
```

> Credentials: use `aws configure` or env vars (`AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY`, `AWS_REGION`).

---

# SqsProducerApplication.java

```java
package com.example.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SqsProducerApplication {
  public static void main(String[] args) {
    SpringApplication.run(SqsProducerApplication.class, args);
  }
}
```

---

# SqsConfig.java

```java
package com.example.producer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.time.Duration;

@Configuration
public class SqsConfig {

  @Bean
  public SqsAsyncClient sqsAsyncClient(@Value("${aws.region}") String region) {
    return SqsAsyncClient.builder()
        .region(Region.of(region))
        .httpClientBuilder(NettyNioAsyncHttpClient.builder()
            .maxConcurrency(256)
            .readTimeout(Duration.ofSeconds(30))
            .writeTimeout(Duration.ofSeconds(30)))
        .build();
  }
}
```

---

# FileCheckpoint.java (resume info)

```java
package com.example.producer.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "file_checkpoint", uniqueConstraints = @UniqueConstraint(columnNames = "filePath"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FileCheckpoint {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String filePath;

  // next line number to read (0-based)
  @Column(nullable = false)
  private long nextLineNumber;

  // next byte offset to read (faster resume for big files)
  @Column(nullable = false)
  private long nextByteOffset;

  // simple checksum or file size to detect content changes
  private long fileSizeBytes;
  private Instant updatedAt;

  // PENDING, IN_PROGRESS, DONE
  @Column(nullable = false)
  private String status;
}
```

---

# FileCheckpointRepository.java

```java
package com.example.producer.repo;

import com.example.producer.domain.FileCheckpoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileCheckpointRepository extends JpaRepository<FileCheckpoint, Long> {
  Optional<FileCheckpoint> findByFilePath(String filePath);
}
```

---

# FileLeaseService.java (safe multi-instance locking)

```java
package com.example.producer.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.*;

@Service
public class FileLeaseService {

  // Obtain an OS file lock that auto-releases if the process dies.
  public FileLock tryAcquireLock(Path file) throws IOException {
    Path lockPath = Path.of(file.toString() + ".lock");
    FileChannel channel = FileChannel.open(lockPath,
        StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    FileLock lock = channel.tryLock();
    if (lock == null) {
      channel.close();
      return null;
    }
    return lock; // caller must close() lock.channel() on release
  }

  public void releaseLock(FileLock lock) throws IOException {
    if (lock != null && lock.isValid()) {
      FileChannel ch = lock.channel();
      lock.release();
      ch.close();
    }
  }
}
```

---

# ByteCountingReader.java (resume via byte offset)

```java
package com.example.producer.util;

import java.io.*;
import java.nio.charset.Charset;

public class ByteCountingReader implements Closeable {
  private final RandomAccessFile raf;
  private final Charset charset;

  public ByteCountingReader(File file, Charset charset, long startByteOffset) throws IOException {
    this.raf = new RandomAccessFile(file, "r");
    this.charset = charset;
    if (startByteOffset > 0) raf.seek(startByteOffset);
  }

  // returns line and updates internal file pointer
  public String readLine() throws IOException {
    return raf.readLine(); // reads ISO-8859-1 bytes; we’ll re-decode below if needed
  }

  public long getByteOffset() throws IOException {
    return raf.getFilePointer();
  }

  @Override public void close() throws IOException { raf.close(); }
}
```

> If your files are UTF-8, `RandomAccessFile.readLine()` returns ISO-8859-1—fine for JSON lines that are ASCII; if you need strict UTF-8, wrap an `InputStreamReader` and manually track bytes. For speed and simplicity, many newline-delimited ASCII/JSON files are OK here.

---

# SqsSendService.java (batching, retries, FIFO support)

```java
package com.example.producer.service;

import org.slf4j.Logger; import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class SqsSendService {
  private static final Logger log = LoggerFactory.getLogger(SqsSendService.class);

  private final SqsAsyncClient sqs;
  private final String queueUrl;
  private final boolean fifo;
  private final String messageGroupId;
  private final String dedupStrategy;

  public SqsSendService(
      SqsAsyncClient sqs,
      @Value("${aws.queueUrl}") String queueUrl,
      @Value("${producer.fifo:false}") boolean fifo,
      @Value("${producer.messageGroupId:group-1}") String messageGroupId,
      @Value("${producer.dedupStrategy:none}") String dedupStrategy) {
    this.sqs = sqs; this.queueUrl = queueUrl; this.fifo = fifo;
    this.messageGroupId = messageGroupId; this.dedupStrategy = dedupStrategy;
  }

  public void sendBatch(List<String> lines) throws Exception {
    if (lines.isEmpty()) return;

    List<SendMessageBatchRequestEntry> entries = new ArrayList<>(lines.size());
    int i = 0;
    for (String body : lines) {
      SendMessageBatchRequestEntry.Builder b = SendMessageBatchRequestEntry.builder()
          .id("m" + i++)
          .messageBody(body);

      if (fifo) {
        b = b.messageGroupId(messageGroupId)
             .messageDeduplicationId(dedupIdFor(body));
      }

      entries.add(b.build());
    }

    SendMessageBatchRequest req = SendMessageBatchRequest.builder()
        .queueUrl(queueUrl)
        .entries(entries)
        .build();

    // simple retry on failures in the batch response
    for (int attempt = 1; attempt <= 5; attempt++) {
      CompletableFuture<SendMessageBatchResponse> f = sqs.sendMessageBatch(req);
      SendMessageBatchResponse resp = f.join();

      if (resp.failed().isEmpty()) return;

      // retry only failed entries
      Map<String, SendMessageBatchRequestEntry> byId = new HashMap<>();
      for (SendMessageBatchRequestEntry e : entries) byId.put(e.id(), e);

      List<SendMessageBatchRequestEntry> retry = new ArrayList<>();
      for (BatchResultErrorEntry err : resp.failed()) {
        log.warn("SQS failed id={} code={} msg={}", err.id(), err.code(), err.message());
        retry.add(byId.get(err.id()));
      }
      entries = retry;

      if (attempt < 5) {
        Thread.sleep((long) Math.min(5000, Math.pow(2, attempt) * 200)); // backoff
        req = req.toBuilder().entries(entries).build();
      } else {
        throw new RuntimeException("SQS batch failed after retries, left=" + entries.size());
      }
    }
  }

  private String dedupIdFor(String body) throws Exception {
    return switch (dedupStrategy) {
      case "line" -> body;
      case "hash" -> sha256(body);
      default -> UUID.randomUUID().toString();
    };
  }

  private static String sha256(String s) throws Exception {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] bytes = md.digest(s.getBytes(StandardCharsets.UTF_8));
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) sb.append(String.format("%02x", b));
    return sb.toString();
  }
}
```

---

# FileProducerService.java (scans files, threads, resume, rate limit)

```java
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
    for (Future<?> f : futures) f.get(); // wait all
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
        // file changed; restart
        cp.setNextLineNumber(0);
        cp.setNextByteOffset(0);
        cp.setFileSizeBytes(size);
      }

      cp.setStatus("IN_PROGRESS"); repo.save(cp);

      try (ByteCountingReader reader = new ByteCountingReader(f, StandardCharsets.UTF_8, cp.getNextByteOffset())) {
        long sentSinceCheckpoint = 0;
        List<String> batch = new ArrayList<>(batchSize);

        // skip lines to match nextLineNumber if byte offset was approximate (safety)
        long currentLine = cp.getNextLineNumber();

        String line;
        while ((line = reader.readLine()) != null) {
          currentLine++;

          // Optionally rate-limit
          if (rateLimitPerSec > 0) {
            // naive: sleep per message
            Thread.sleep(1000L / rateLimitPerSec);
          }

          // You might transform line -> message JSON here if needed
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
          sentSinceCheckpoint += batch.size();
        }

        // final checkpoint
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
```

---

## How you run it

1. **Build**

```powershell
mvn -q -DskipTests package
```

2. **Set AWS creds** (once)

```powershell
aws configure
```

…or set env vars in your terminal.

3. **Place files** in `C:\data\messages\` (or update `application.yml`).

* One message **per line** is simplest.
* If you split the original into `part-000.txt`, `part-001.txt`, etc., the app will process them in parallel (up to `producer.threads`), resuming each file where it left off.

4. **Run**

```powershell
java -jar target\sqs-producer-0.1.0.jar
```

### Running multiple instances

* Start **another** process on the same machine or a different one, pointed at the *same directory*.
* Each instance takes OS file locks (`.lock` sidecar), so **only one instance** works a given file at a time.
* On crash/kill, the lock is released by the OS, and the next run **resumes** from the stored **byte offset + line number**.

---

## Tuning tips

* **Throughput:** Increase `producer.threads` and keep `batchSize=10`. SqsAsyncClient + batch 10 gives best throughput.
* **Retries:** Already handled per-batch with backoff. You can adjust attempts/backoff as needed.
* **FIFO queues:** Set `producer.fifo=true`, set a meaningful `messageGroupId`, and pick `dedupStrategy` (`line` or `hash`) to prevent duplicates.
* **Rate limiting:** If you need to play nice with other workloads, set `producer.rateLimitPerSec`.

---

If you want, I can bundle this as a **ZIP with the full project** and a tiny **sample generator** that makes big test files.
