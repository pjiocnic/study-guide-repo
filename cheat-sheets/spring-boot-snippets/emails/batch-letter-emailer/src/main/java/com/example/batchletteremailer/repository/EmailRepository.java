package com.example.batchletteremailer.repository;
import com.example.batchletteremailer.model.EmailRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EmailRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<EmailRecord> fetchPendingEmails() {
        return jdbcTemplate.query(
            "SELECT id, email, name FROM email_queue WHERE status = 'PENDING'",
            (rs, rowNum) -> {
                EmailRecord record = new EmailRecord();
                record.setId(rs.getLong("id"));
                record.setEmail(rs.getString("email"));
                record.setName(rs.getString("name"));
                return record;
            }
        );
    }

    public void markCompleted(Long id) {
        jdbcTemplate.update("UPDATE email_queue SET status = 'SENT' WHERE id = ?", id);
    }
}