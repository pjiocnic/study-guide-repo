package com.example.emailbatch;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import freemarker.template.Configuration;

import jakarta.mail.internet.MimeMessage;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private RecipientRepository repository;

    @Autowired
    private Configuration freemarkerConfig;

    public void sendBatchEmails(String tableName, int startId, int endId) {
        String runId = java.util.UUID.randomUUID().toString();
        System.out.println("Run ID: " + runId);
        System.out.println("Processing batch: " + startId + " to " + endId);
        try {
            String templatePath = System.getProperty("template.path");
            Template template = new Template("email", new FileReader(templatePath), freemarkerConfig);

            List<Recipient> recipients = repository.findBatchInRange(tableName, startId, endId);

            for (Recipient r : recipients) {
                if (!EmailUtils.isValid(r.getEmail())) {
                    System.err.println("Invalid email: " + r.getEmail());
                    repository.markAsError(tableName, r.getId());
                    continue;
                }

                Map<String, Object> model = new HashMap<>();
                model.put("name", r.getName());

                StringWriter writer = new StringWriter();
                template.process(model, writer);

                sendEmail(r.getEmail(), writer.toString());
                repository.markAsSent(tableName, r.getId());
            }

        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    private void sendEmail(String to, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject("Welcome!");
            helper.setText(body, true);
            mailSender.send(message);
            System.out.println("Email sent to " + to);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
