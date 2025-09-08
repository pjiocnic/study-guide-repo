package com.example.batchetl;

import com.example.batchetl.service.BatchProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchEtlApplication implements CommandLineRunner {

    private final BatchProcessor processor;

    public BatchEtlApplication(BatchProcessor processor) {
        this.processor = processor;
    }

    public static void main(String[] args) {
        SpringApplication.run(BatchEtlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        processor.runBatches();
    }
}
