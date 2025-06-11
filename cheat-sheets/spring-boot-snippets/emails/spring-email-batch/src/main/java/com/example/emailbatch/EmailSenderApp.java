package com.example.emailbatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailSenderApp implements CommandLineRunner {

    @Autowired
    private EmailBatchProcessor processor;

    public static void main(String[] args) {
        SpringApplication.run(EmailSenderApp.class, args);
    }

    @Override
    public void run(String... args) {
        processor.processPendingEmails();
    }
}
