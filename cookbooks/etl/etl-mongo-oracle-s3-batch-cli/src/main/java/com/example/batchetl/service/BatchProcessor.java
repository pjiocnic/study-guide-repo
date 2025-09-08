package com.example.batchetl.service;

import com.example.batchetl.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class BatchProcessor {
    private final IdsLoader loader;
    private final MongoEmployeeReader mongoReader;
    private final OracleEnricher enricher;
    private final S3GzipExportService s3;

    @Value("${app.input.ids-file}") private String idsFile;
    @Value("${app.input.batch-size:100}") private int batchSize;
    @Value("${app.s3.bucket}") private String bucket;
    @Value("${app.s3.prefix}") private String prefix;
    @Value("${app.s3.base-name:employees-batch}") private String baseName;

    public BatchProcessor(IdsLoader loader, MongoEmployeeReader mongoReader, OracleEnricher enricher, S3GzipExportService s3) {
        this.loader = loader;
        this.mongoReader = mongoReader;
        this.enricher = enricher;
        this.s3 = s3;
    }

    public void runBatches() throws Exception {
        List<String> ids = loader.loadIds(Path.of(idsFile));
        if (ids.isEmpty()) return;

        int total = ids.size();
        int from = 0;
        int batchNo = 1;

        while (from < total) {
            int to = Math.min(from + batchSize, total);
            List<String> sub = ids.subList(from, to);

            List<Employee> out = new ArrayList<>();
            for (String id : sub) {
                mongoReader.findById(id).ifPresent(e -> {
                    enricher.enrich(e);
                    out.add(e);
                });
            }

            if (!out.isEmpty()) {
                String key = s3.writeOrUploadJsonArrayGzip(bucket, prefix, baseName + "-" + batchNo, out);
                System.out.println("Uploaded batch " + batchNo + " -> s3://" + bucket + "/" + key + " (records=" + out.size() + ")");
            } else {
                System.out.println("Batch " + batchNo + " had no matching employees.");
            }

            batchNo++;
            from = to;
        }
    }
}
