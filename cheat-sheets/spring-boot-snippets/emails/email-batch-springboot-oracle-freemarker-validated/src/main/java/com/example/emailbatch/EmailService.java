package com.example.emailbatch;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
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

    public void sendBatchEmails() {
        try {
            String templatePath = System.getProperty("template.path");
            Template template = new Template("email", new FileReader(templatePath), freemarkerConfig);

            List<Recipient> recipients = repository.findAllPending();

            for (Recipient r : recipients) {
                if (!EmailUtils.isValid(r.getEmail())) {
                    System.err.println("Invalid email: " + r.getEmail());
                    repository.markAsError(r.getId());
                    continue;
                }

                Map<String, Object> model = new HashMap<>();
                model.put("name", r.getName());

                StringWriter writer = new StringWriter();
                template.process(model, writer);

                sendEmail(r.getEmail(), writer.toString());
                repository.markAsSent(r.getId());
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
