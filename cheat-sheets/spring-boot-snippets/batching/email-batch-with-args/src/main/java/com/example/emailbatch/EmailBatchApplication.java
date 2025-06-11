package com.example.emailbatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:${config.path}")
public class EmailBatchApplication implements CommandLineRunner {

    private final EmailService emailService;

    public EmailBatchApplication(EmailService emailService) {
        this.emailService = emailService;
    }

    public static void main(String[] args) {
        SpringApplication.run(EmailBatchApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (args.length < 3) {
            System.err.println("Usage: java -Dconfig.path=config.properties -Dtemplate.path=email.ftl -jar app.jar <tableName> <batchStart> <batchEnd>");
            System.exit(1);
        }

        String tableName = args[0];
        int batchStart = Integer.parseInt(args[1]);
        int batchEnd = Integer.parseInt(args[2]);

        emailService.sendBatchEmails(tableName, batchStart, batchEnd);
    }
}
