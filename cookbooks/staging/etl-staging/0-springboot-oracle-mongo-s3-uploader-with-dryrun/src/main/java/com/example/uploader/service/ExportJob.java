package com.example.uploader.service;

import com.example.uploader.config.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ExportJob implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ExportJob.class);

    private final AppProperties props;
    private final OracleRepository oracleRepository;
    private final MongoLookupService mongoLookupService;
    private final BusinessLogicService businessLogicService;
    private final FileChunkUploader fileChunkUploader;

    public ExportJob(AppProperties props,
                     OracleRepository oracleRepository,
                     MongoLookupService mongoLookupService,
                     BusinessLogicService businessLogicService,
                     FileChunkUploader fileChunkUploader) {
        this.props = props;
        this.oracleRepository = oracleRepository;
        this.mongoLookupService = mongoLookupService;
        this.businessLogicService = businessLogicService;
        this.fileChunkUploader = fileChunkUploader;
    }

    @Override
    public void run(String... args) throws Exception {
        final int rowsPerFile = props.getOutput().getRowsPerFile();
        log.info("Starting job with rowsPerFile={}", rowsPerFile);

        final AtomicInteger partNo = new AtomicInteger(1);
        final AtomicInteger inChunk = new AtomicInteger(0);
        final AtomicInteger totalRows = new AtomicInteger(0);

        final JsonSinks.JsonSink[] currentSink = new JsonSinks.JsonSink[1];

        try {
            currentSink[0] = fileChunkUploader.newSink(partNo.get());

            oracleRepository.streamRows(props.getJob().getOracleSql(), row -> {
                try {
                    Object keyVal = row.get(props.getKeys().getOracleKeyColumn());
                    Map<String, Object> mongoDoc = mongoLookupService.findByKey(
                            props.getKeys().getMongoCollection(),
                            props.getKeys().getMongoKeyField(),
                            keyVal
                    );

                    Map<String, Object> merged = businessLogicService.merge(row, mongoDoc);
                    currentSink[0].write(merged);

                    totalRows.incrementAndGet();
                    int count = inChunk.incrementAndGet();

                    if (count >= rowsPerFile) {
                        String uploadedKey = fileChunkUploader.upload(currentSink[0], partNo.get());
                        log.info("Uploaded part {} to key {}", partNo.get(), uploadedKey);
                        partNo.incrementAndGet();
                        inChunk.set(0);
                        currentSink[0] = fileChunkUploader.newSink(partNo.get());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            // Flush remaining
            if (inChunk.get() > 0 && currentSink[0] != null) {
                String uploadedKey = fileChunkUploader.upload(currentSink[0], partNo.get());
                log.info("Uploaded final part {} to key {}", partNo.get(), uploadedKey);
            } else if (currentSink[0] != null) {
                currentSink[0].close();
            }

        } finally {
            // Ensure closed on errors too
            if (currentSink[0] != null) {
                try { currentSink[0].close(); } catch (Exception ignored) {}
            }
        }

        log.info("Job complete. Total rows processed: {}", totalRows.get());
    }
}