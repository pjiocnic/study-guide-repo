package com.example.etl;

import com.example.etl.service.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class MongoOracleS3EtlCliIdsApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MongoOracleS3EtlCliIdsApplication.class);

    private final IdsLoader idsLoader;
    private final MongoJsonReader mongoReader;
    private final OracleEnricher oracleEnricher;
    private final S3ExportService s3;

    @Value("${app.input.ids-file}")
    private String idsFile;

    @Value("${app.s3.bucket}")
    private String bucket;

    @Value("${app.s3.prefix}")
    private String prefix;

    public MongoOracleS3EtlCliIdsApplication(IdsLoader idsLoader,
                                             MongoJsonReader mongoReader,
                                             OracleEnricher oracleEnricher,
                                             S3ExportService s3) {
        this.idsLoader = idsLoader;
        this.mongoReader = mongoReader;
        this.oracleEnricher = oracleEnricher;
        this.s3 = s3;
    }

    public static void main(String[] args) {
        SpringApplication.run(MongoOracleS3EtlCliIdsApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        return om;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("=== Workflow: IDs file -> Mongo JSON -> Oracle enrich -> JSON -> S3 ===");
        List<String> ids = idsLoader.loadIds(Path.of(idsFile));
        log.info("Loaded {} IDs from {}", ids.size(), idsFile);

        AtomicInteger ok = new AtomicInteger();
        AtomicInteger missMongo = new AtomicInteger();
        AtomicInteger missOracle = new AtomicInteger();
        AtomicInteger uploaded = new AtomicInteger();

        for (String id : ids) {
            try {
                var mongoJsonOpt = mongoReader.fetchJsonById(id);
                if (mongoJsonOpt.isEmpty()) {
                    log.warn("Mongo doc not found for id={}", id);
                    missMongo.incrementAndGet();
                    continue;
                }
                var mongoJson = mongoJsonOpt.get();

                var oracle = oracleEnricher.loadById(id);
                if (oracle == null) {
                    log.warn("Oracle row not found for id={}", id);
                    missOracle.incrementAndGet();
                }

                Map<String, Object> composite = new HashMap<>();
                composite.put("id", id);
                composite.put("mongo", mongoJson);
                composite.put("oracle", oracle);

                String key = s3.uploadJson(bucket, prefix, "record-" + id, composite);
                uploaded.incrementAndGet();
                log.info("Uploaded s3://{}/{}", bucket, key);
                ok.incrementAndGet();

            } catch (Exception e) {
                log.error("Error processing id={}: {}", id, e.toString());
            }
        }

        log.info("=== Summary ===");
        log.info("Total IDs: {}", ids.size());
        log.info("Uploaded: {}", uploaded.get());
        log.info("Mongo not found: {}", missMongo.get());
        log.info("Oracle not found: {}", missOracle.get());
    }
}
