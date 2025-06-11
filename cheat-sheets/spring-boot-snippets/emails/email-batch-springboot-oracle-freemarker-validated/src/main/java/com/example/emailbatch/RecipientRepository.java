package com.example.emailbatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RecipientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Recipient> findAllPending() {
        return jdbcTemplate.query("SELECT id, name, email FROM recipients WHERE status = 'PENDING'",
            (rs, rowNum) -> new Recipient(rs.getLong("id"), rs.getString("name"), rs.getString("email")));
    }

    public void markAsSent(Long id) {
        jdbcTemplate.update("UPDATE recipients SET status = 'SENT' WHERE id = ?", id);
    }

    public void markAsError(Long id) {
        jdbcTemplate.update("UPDATE recipients SET status = 'E' WHERE id = ?", id);
    }
}
