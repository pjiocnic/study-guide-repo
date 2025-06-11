package com.example.batchemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.*;

@Service
public class EmailBatchService {

    @Autowired
    private EmailRepository repository;

    @Autowired
    private JavaMailSender mailSender;

    private final ExecutorService executor = Executors.newFixedThreadPool(5);
    private final Map<String, Integer> batchSizeByTable = new HashMap<>();

    @PostConstruct
    public void init() {
        batchSizeByTable.put("table1", 10);
        batchSizeByTable.put("table2", 20);
    }

    @Scheduled(fixedRate = 30000)
    public void scheduleAllTables() {
        for (String table : batchSizeByTable.keySet()) {
            int batchSize = batchSizeByTable.get(table);
            executor.submit(() -> processBatch(table, batchSize));
        }
    }

    public void processBatch(String table, int batchSize) {
        List<Email> batch = repository.findTopNUnsent(batchSize);
        for (Email email : batch) {
            executor.submit(() -> sendWithRetry(email, 3));
        }
    }

    private void sendWithRetry(Email email, int maxRetries) {
        int attempt = 0;
        long delay = 1000;

        while (attempt < maxRetries) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(email.getRecipient());
                message.setSubject(email.getSubject());
                message.setText(email.getBody());

                mailSender.send(message);
                email.setSent(true);
                repository.save(email);
                return;
            } catch (Exception e) {
                attempt++;
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ignored) {}
                delay *= 2;
            }
        }
        System.err.println("Failed after retries: " + email.getRecipient());
    }
}
