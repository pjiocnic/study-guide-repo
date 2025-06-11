package com.example.batchrunner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class BatchProcessor {

    @Value("${batch.start}")
    private int batchStart;

    @Value("${batch.end}")
    private int batchEnd;

    @Value("${spring.profiles.active:default}")
    private String profile;

    @PostConstruct
    public void runBatch() {
        System.out.printf(">>> [%s] Running batch from %d to %d%n", profile, batchStart, batchEnd);
    }
}
