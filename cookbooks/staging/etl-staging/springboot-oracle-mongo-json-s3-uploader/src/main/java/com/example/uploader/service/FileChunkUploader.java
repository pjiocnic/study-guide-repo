package com.example.uploader.service;

import com.example.uploader.config.AppProperties;
import com.example.uploader.service.JsonSinks.JsonArrayGzipSink;
import com.example.uploader.service.JsonSinks.JsonSink;
import com.example.uploader.service.JsonSinks.JsonlGzipSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@Component
public class FileChunkUploader {

    private static final Logger log = LoggerFactory.getLogger(FileChunkUploader.class);

    private final AppProperties properties;
    private final S3Client s3Client;

    public FileChunkUploader(AppProperties properties, S3Client s3Client) {
        this.properties = properties;
        this.s3Client = s3Client;
    }

    public JsonSink newSink(int partNo) throws IOException {
        String ext = properties.getOutput().getFormat().equalsIgnoreCase("jsonarray") ? "json" : "jsonl";
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = String.format(properties.getOutput().getFileNamePattern(), ts, partNo, ext);
        File temp = Files.createTempFile("chunk_", "_" + filename).toFile();
        log.info("Starting new chunk file: {}", temp.getAbsolutePath());

        if (properties.getOutput().getFormat().equalsIgnoreCase("jsonarray")) {
            return new JsonArrayGzipSink(temp);
        } else {
            return new JsonlGzipSink(temp);
        }
    }

    public String upload(JsonSink sink, int partNo) throws IOException {
        sink.close();
        File file = sink.getFile();
        String ext = properties.getOutput().getFormat().equalsIgnoreCase("jsonarray") ? "json" : "jsonl";
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = String.format(properties.getOutput().getFileNamePattern(), ts, partNo, ext);

        String key = properties.getOutput().getS3KeyPrefix() + filename;
        log.info("Uploading {} to s3://{}/{}", file.getName(), properties.getS3().getBucket(), key);

        PutObjectRequest req = PutObjectRequest.builder()
                .bucket(properties.getS3().getBucket())
                .key(key)
                .contentType("application/json")
                .contentEncoding("gzip")
                .build();

        s3Client.putObject(req, RequestBody.fromFile(file.toPath()));
        Files.deleteIfExists(file.toPath());
        return key;
    }
}