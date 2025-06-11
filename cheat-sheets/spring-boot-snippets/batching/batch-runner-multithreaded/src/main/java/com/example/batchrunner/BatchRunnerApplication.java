package com.example.batchrunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchRunnerApplication implements CommandLineRunner {

    @Override
    public void run(String... args) {
        int totalRecords = 1000;
        int threads = 4;
        int recordsPerThread = totalRecords / threads;

        for (int i = 0; i < threads; i++) {
            int start = i * recordsPerThread;
            int end = (i == threads - 1) ? totalRecords - 1 : (start + recordsPerThread - 1);
            Thread t = new Thread(new BatchWorker(start, end, i + 1));
            t.start();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(BatchRunnerApplication.class, args);
    }
}
