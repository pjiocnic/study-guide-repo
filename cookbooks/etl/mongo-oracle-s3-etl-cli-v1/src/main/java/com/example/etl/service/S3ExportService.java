package com.example.etl.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.charset.StandardCharsets;
import java.time.Instant;

@Service
public class S3ExportService {

    private final S3Client s3;
    private final ObjectMapper mapper;

    public S3ExportService(ObjectMapper mapper) {
        this.mapper = mapper;
        this.s3 = S3Client.builder()
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    public String uploadJson(String bucket, String keyPrefix, String baseName, Object payload) throws Exception {
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);
        String key = String.format("%s/%s-%d.json",
                (keyPrefix == null || keyPrefix.isBlank()) ? "exports" : keyPrefix,
                baseName, Instant.now().toEpochMilli());

        PutObjectRequest put = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType("application/json")
                .build();

        s3.putObject(put, RequestBody.fromString(json, StandardCharsets.UTF_8));
        return key;
    }
}
