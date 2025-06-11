package com.example.batchemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmailBatchService {

    @Autowired
    private EmailRepository repository;

    @Autowired
    private JavaMailSender mailSender;

    @Async
    @Transactional
    public void sendPendingEmails() {
        List<Email> pending = repository.findBySentFalse();

        for (Email email : pending) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getRecipient());
            message.setSubject(email.getSubject());
            message.setText(email.getBody());

            try {
                mailSender.send(message);
                email.setSent(true);
            } catch (Exception e) {
                e.printStackTrace(); // or log
            }
        }

        repository.saveAll(pending);
    }
}
