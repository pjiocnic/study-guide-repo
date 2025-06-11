package com.example.batchletteremailer;
import com.example.batchletteremailer.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchLetterEmailerApplication implements CommandLineRunner {

    @Autowired
    private EmailSenderService emailSenderService;

    public static void main(String[] args) {
        SpringApplication.run(BatchLetterEmailerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        emailSenderService.processAndSendEmails();
    }
}