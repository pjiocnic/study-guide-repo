package com.example.uploader.service;

import com.example.uploader.config.AppProperties;
import com.example.uploader.service.JsonSinks.CsvSink;
import com.example.uploader.service.JsonSinks.JsonArrayGzipSink;
import com.example.uploader.service.JsonSinks.JsonlGzipSink;
import com.example.uploader.service.JsonSinks.ParquetSink;
import com.example.uploader.service.JsonSinks.Sink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class FileChunkUploader {

    private static final Logger log = LoggerFactory.getLogger(FileChunkUploader.class);

    private final AppProperties properties;
    private final S3Client s3Client;

    public FileChunkUploader(AppProperties properties, S3Client s3Client) {
        this.properties = properties;
        this.s3Client = s3Client;
    }

    public Sink newSink(int partNo) throws IOException {
        String format = properties.getOutput().getFormat().toLowerCase();
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename;
        File temp;

        switch (format) {
            case "parquet" -> {
                filename = String.format(properties.getOutput().getFileNamePattern(), ts, partNo, "parquet");
                temp = Files.createTempFile("chunk_", "_" + filename).toFile();
                log.info("Starting new Parquet chunk file: {}", temp.getAbsolutePath());
                return new ParquetSink(temp);
            }
            case "jsonarray" -> {
                filename = String.format(properties.getOutput().getFileNamePattern(), ts, partNo, "json.gz");
                temp = Files.createTempFile("chunk_", "_" + filename).toFile();
                log.info("Starting new JSON array gzip chunk: {}", temp.getAbsolutePath());
                return new JsonArrayGzipSink(temp);
            }
            case "csv" -> {
                filename = String.format(properties.getOutput().getFileNamePattern(), ts, partNo, "csv");
                temp = Files.createTempFile("chunk_", "_" + filename).toFile();
                log.info("Starting new CSV chunk file: {}", temp.getAbsolutePath());
                return new CsvSink(temp);
            }
            default -> {
                filename = String.format(properties.getOutput().getFileNamePattern(), ts, partNo, "jsonl.gz");
                temp = Files.createTempFile("chunk_", "_" + filename).toFile();
                log.info("Starting new JSONL gzip chunk: {}", temp.getAbsolutePath());
                return new JsonlGzipSink(temp);
            }
        }
    }

    public String upload(Sink sink, int partNo) throws IOException {
        sink.close();
        File file = sink.getFile();
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String ext = sink.getExt();
        String filename = String.format(properties.getOutput().getFileNamePattern(), ts, partNo, ext);

        String key = properties.getOutput().getS3KeyPrefix() + filename;
        log.info("Uploading {} to s3://{}/{}", file.getName(), properties.getS3().getBucket(), key);

        PutObjectRequest.Builder b = PutObjectRequest.builder()
                .bucket(properties.getS3().getBucket())
                .key(key)
                .contentType(sink.getContentType());

        if (sink.getContentEncoding() != null) {
            b.contentEncoding(sink.getContentEncoding());
        }

        s3Client.putObject(b.build(), RequestBody.fromFile(file.toPath()));
        Files.deleteIfExists(file.toPath());
        return key;
    }
}