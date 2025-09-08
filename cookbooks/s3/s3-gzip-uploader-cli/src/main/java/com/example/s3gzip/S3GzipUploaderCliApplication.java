package com.example.s3gzip;

import com.example.s3gzip.service.S3GzipExportService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class S3GzipUploaderCliApplication implements CommandLineRunner {

    private final S3GzipExportService exporter;

    @Value("${app.s3.bucket}")
    private String bucket;

    @Value("${app.s3.prefix:gzip-uploads}")
    private String prefix;

    public S3GzipUploaderCliApplication(S3GzipExportService exporter) {
        this.exporter = exporter;
    }

    public static void main(String[] args) {
        SpringApplication.run(S3GzipUploaderCliApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Sample JSON strings
        List<String> jsons = List.of(
                "{\"id\":1,\"name\":\"alpha\",\"active\":true}",
                "{\"id\":2,\"name\":\"beta\",\"active\":false}",
                "{\"id\":3,\"name\":\"gamma\",\"active\":true}"
        );

        // 1) One gzip object per JSON (keys like item-<n>.json.gz)
        exporter.uploadEachJsonAsGzip(bucket, prefix, "item", jsons);

        // 2) Single gzip that contains a JSON array of all items (key ends with .json.gz)
        exporter.uploadBatchAsArrayGzip(bucket, prefix, "all-items", jsons);

        // 3) Single gzip that contains NDJSON (one JSON per line)
        exporter.uploadBatchAsNdjsonGzip(bucket, prefix, "all-items-ndjson", jsons);
    }
}
