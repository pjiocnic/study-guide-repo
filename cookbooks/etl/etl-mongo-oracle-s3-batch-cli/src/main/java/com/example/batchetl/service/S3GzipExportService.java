package com.example.batchetl.service;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.List;
import java.util.zip.GZIPOutputStream;

@Service
public class S3GzipExportService {
    private final S3Client s3;
    private final ObjectMapper mapper;

    @Value("${app.s3.dry-run:false}")
    private boolean dryRun;
    @Value("${app.s3.local-dir:./out}")
    private String localDir;

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

    /**
     * Uploads a JSON array (records) as .json.gz so Pandas read_json can parse it.
     * If dry-run is true, writes the gzip locally and returns the local file path.
     * Otherwise, uploads to S3 and returns the S3 key.
     */
    public String writeOrUploadJsonArrayGzip(String bucket, String prefix, String baseName, List<?> records) throws Exception {
        DefaultPrettyPrinter pp = new DefaultPrettyPrinter();
        pp.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);

        byte[] json = mapper.writer(pp).writeValueAsBytes(records);
        byte[] gz = gzip(json);

        String filename = baseName + "-" + Instant.now().toEpochMilli() + ".json.gz";

        if (dryRun) {
            Path dir = Path.of(localDir);
            Files.createDirectories(dir);
            Path out = dir.resolve(filename);
            Files.write(out, gz, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            return out.toAbsolutePath().toString();
        } else {
            String key = String.format("%s/%s", (prefix == null || prefix.isBlank()) ? "etl" : prefix, filename);
            PutObjectRequest put = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .contentType("application/json")
                    .contentEncoding("gzip")
                    .build();
            s3.putObject(put, RequestBody.fromBytes(gz));
            return key;
        }
    }
}
