package com.example.emailbatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmailBatchProcessor {

    private static final int BATCH_SIZE = 10;

    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private JavaMailSender mailSender;

    public void processPendingEmails() {
        List<EmailRecord> batch;
        do {
            batch = fetchPendingEmails(BATCH_SIZE);
            for (EmailRecord record : batch) {
                try {
                    sendEmail(record);
                    markAsSent(record.getId());
                } catch (Exception e) {
                    System.err.println("Failed to send email to " + record.getEmailAddress() + ": " + e.getMessage());
                    markAsFailed(record.getId());
                }
            }
        } while (!batch.isEmpty());
    }

    private List<EmailRecord> fetchPendingEmails(int limit) {
        return jdbcTemplate.query(
            "SELECT * FROM email_queue WHERE status = 'PENDING' ORDER BY id FETCH FIRST ? ROWS ONLY",
            new Object[]{limit},
            (rs, rowNum) -> new EmailRecord(
                rs.getLong("id"),
                rs.getString("email_address"),
                rs.getString("subject"),
                rs.getString("body"))
        );
    }

    private void sendEmail(EmailRecord email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getEmailAddress());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        mailSender.send(message);
    }

    private void markAsSent(Long id) {
        jdbcTemplate.update("UPDATE email_queue SET status = 'SENT', updated_at = SYSTIMESTAMP WHERE id = ?", id);
    }

    private void markAsFailed(Long id) {
        jdbcTemplate.update("UPDATE email_queue SET status = 'FAILED', updated_at = SYSTIMESTAMP WHERE id = ?", id);
    }
}
