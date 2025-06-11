package com.example.batchletteremailer.service;
import com.example.batchletteremailer.model.EmailRecord;
import com.example.batchletteremailer.repository.EmailRepository;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;

@Service
public class EmailSenderService {
    private static final Logger logger = LoggerFactory.getLogger(EmailSenderService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailRepository emailRepo;

    public void processAndSendEmails() {
        List<EmailRecord> emails = emailRepo.fetchPendingEmails();

        for (EmailRecord record : emails) {
            try {
                sendEmail(record);
                emailRepo.markCompleted(record.getId());
                logger.info("Email sent to {}", record.getEmail());
            } catch (Exception e) {
                logger.error("Failed to send email to {}: {}", record.getEmail(), e.getMessage());
            }
        }
    }

    private void sendEmail(EmailRecord record) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        helper.setTo(record.getEmail());
        helper.setSubject("Personalized Letter");

        String html = Files.readString(new ClassPathResource("email-template.html").getFile().toPath(), StandardCharsets.UTF_8)
                           .replace("[Recipient]", record.getName())
                           .replace("[Insert Date Here]", LocalDate.now().toString())
                           .replace("[Your Name]", "Your Company");

        helper.setText(html, true);
        helper.addInline("letter_logo", new ClassPathResource("logo.png"));
        mailSender.send(message);
    }
}