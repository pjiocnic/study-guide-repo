package com.example.batchemail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BatchEmailSenderApplication {
    public static void main(String[] args) {
        SpringApplication.run(BatchEmailSenderApplication.class, args);
    }
}
