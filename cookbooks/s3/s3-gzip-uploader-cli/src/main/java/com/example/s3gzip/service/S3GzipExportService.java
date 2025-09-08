package com.example.s3gzip.service;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;
import java.util.zip.GZIPOutputStream;

@Service
public class S3GzipExportService {

    private final S3Client s3;
    private final ObjectMapper mapper;

    public S3GzipExportService(ObjectMapper mapper) {
        this.mapper = mapper;
        this.s3 = S3Client.builder()
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    private byte[] gzip(byte[] input) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (GZIPOutputStream gos = new GZIPOutputStream(baos)) {
            gos.write(input);
        }
        return baos.toByteArray();
    }

    public String uploadJsonStringAsGzip(String bucket, String prefix, String baseName, String json) throws Exception {
        byte[] gz = gzip(json.getBytes(StandardCharsets.UTF_8));
        String key = String.format("%s/%s-%d.json.gz",
                (prefix == null || prefix.isBlank()) ? "gzip" : prefix,
                baseName, Instant.now().toEpochMilli());

        PutObjectRequest put = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType("application/json")
                .contentEncoding("gzip")
                .build();

        s3.putObject(put, RequestBody.fromBytes(gz));
        return key;
    }

    public void uploadEachJsonAsGzip(String bucket, String prefix, String baseName, List<String> jsonStrings) throws Exception {
        int i = 1;
        for (String json : jsonStrings) {
            uploadJsonStringAsGzip(bucket, prefix, baseName + "-" + i, json);
            i++;
        }
    }

    public String uploadBatchAsArrayGzip(String bucket, String prefix, String baseName, List<String> jsonStrings) throws Exception {
        var nodes = jsonStrings.stream().map(s -> {
            try { return mapper.readTree(s); } catch (Exception e) { throw new RuntimeException(e); }
        }).toList();

        DefaultPrettyPrinter pp = new DefaultPrettyPrinter();
        pp.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);

        byte[] bytes = mapper.writer(pp).writeValueAsBytes(nodes);
        byte[] gz = gzip(bytes);

        String key = String.format("%s/%s-%d.json.gz",
                (prefix == null || prefix.isBlank()) ? "gzip" : prefix,
                baseName, Instant.now().toEpochMilli());

        PutObjectRequest put = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType("application/json")
                .contentEncoding("gzip")
                .build();

        s3.putObject(put, RequestBody.fromBytes(gz));
        return key;
    }

    public String uploadBatchAsNdjsonGzip(String bucket, String prefix, String baseName, List<String> jsonStrings) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (String s : jsonStrings) {
            var node = mapper.readTree(s);
            sb.append(mapper.writeValueAsString(node)).append('\n');
        }
        byte[] gz = gzip(sb.toString().getBytes(StandardCharsets.UTF_8));

        String key = String.format("%s/%s-%d.ndjson.gz",
                (prefix == null || prefix.isBlank()) ? "gzip" : prefix,
                baseName, Instant.now().toEpochMilli());

        PutObjectRequest put = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType("application/x-ndjson")
                .contentEncoding("gzip")
                .build();

        s3.putObject(put, RequestBody.fromBytes(gz));
        return key;
    }
}
