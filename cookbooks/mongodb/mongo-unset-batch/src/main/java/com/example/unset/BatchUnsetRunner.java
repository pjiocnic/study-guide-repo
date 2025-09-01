package com.example.unset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BatchUnsetRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(BatchUnsetRunner.class);

    private final MongoTemplate mongoTemplate;

    @Value("${app.collection:#{null}}")
    private String defaultCollection;

    @Value("${app.key-field:#{null}}")
    private String defaultKeyField;

    @Value("${app.chunk-size:1000}")
    private int defaultChunkSize;

    public BatchUnsetRunner(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        CliArgs cli = CliArgs.parse(args);

        final String collection = firstNonBlank(cli.collection, defaultCollection, "collection");
        final String keyField   = firstNonBlank(cli.keyField, defaultKeyField, "keyField");
        final int chunkSize     = cli.chunkSize != null ? cli.chunkSize : defaultChunkSize;

        if (cli.fieldsCsv == null || cli.fieldsCsv.isBlank()) {
            throw new IllegalArgumentException("--fields is required (comma-separated list)");
        }
        List<String> fields = Arrays.stream(cli.fieldsCsv.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .distinct()
                .collect(Collectors.toList());

        if (cli.keysFile == null) {
            throw new IllegalArgumentException("--keysFile is required");
        }
        List<String> keys = KeyFileReader.readKeys(Path.of(cli.keysFile));
        if (keys.isEmpty()) {
            log.warn("No keys found in file: {}", cli.keysFile);
            return;
        }

        log.info("Starting unset batch");
        log.info("Collection: {}", collection);
        log.info("Key field: {}", keyField);
        log.info("Fields to unset: {}", fields);
        log.info("Total keys loaded: {}", keys.size());
        log.info("Chunk size: {}", chunkSize);
        log.info("Dry run: {}", cli.dryRun);

        int totalMatched = 0;
        int totalModified = 0;

        for (int start = 0; start < keys.size(); start += chunkSize) {
            int end = Math.min(start + chunkSize, keys.size());
            List<String> window = keys.subList(start, end);

            Query query = buildQuery(keyField, window);
            Update update = buildUnsetUpdate(fields);

            long matched = mongoTemplate.count(query, collection);
            totalMatched += matched;

            if (cli.dryRun) {
                log.info("[dry-run] chunk {}..{} keys={}, would match {} docs", start, end, window.size(), matched);
                continue;
            }

            var result = mongoTemplate.updateMulti(query, update, collection);
            long modified = result.getModifiedCount();
            totalModified += modified;
            log.info("chunk {}..{} keys={}, matched={}, modified={}", start, end, window.size(), matched, modified);
        }

        log.info("Done. totalMatched={}, totalModified={}", totalMatched, totalModified);
    }

    private static Query buildQuery(String keyField, List<String> keys) {
        return new Query(Criteria.where(keyField).in(keys));
    }

    private static Update buildUnsetUpdate(List<String> fields) {
        Update u = new Update();
        for (String f : fields) {
            u.unset(f);
        }
        return u;
    }

    private static String firstNonBlank(String a, String b, String name) {
        String v = (a != null && !a.isBlank()) ? a : b;
        if (v == null || v.isBlank()) {
            throw new IllegalArgumentException("--" + name + " is required (and not set by default property)");
        }
        return v;
    }

    static class CliArgs {
        String collection;
        String keyField;
        String fieldsCsv;
        String keysFile;
        Boolean dryRun = false;
        Integer chunkSize;

        static CliArgs parse(String[] args) {
            CliArgs c = new CliArgs();
            for (String arg : args) {
                if (!arg.startsWith("--")) continue;
                String[] kv = arg.substring(2).split("=", 2);
                String k = kv[0];
                String v = kv.length > 1 ? kv[1] : "true";
                switch (k) {
                    case "collection" -> c.collection = v;
                    case "keyField" -> c.keyField = v;
                    case "fields" -> c.fieldsCsv = v;
                    case "keysFile" -> c.keysFile = v;
                    case "dryRun" -> c.dryRun = Boolean.valueOf(v);
                    case "chunkSize" -> c.chunkSize = Integer.valueOf(v);
                    default -> { /* ignore unknowns; Spring will parse its own */ }
                }
            }
            return c;
        }
    }
}
