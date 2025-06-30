
package com.example.batch;

import com.example.batch.service.BatchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchApplication implements CommandLineRunner
{
    private final BatchService batchService;

    @Value("${batch.param1}")
    private String param1;

    public BatchApplication(BatchService batchService) {
        this.batchService = batchService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running from CLI with param1=" + param1);
        batchService.process(param1);
    }
}
