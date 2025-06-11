package com.example.emailbatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RecipientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Recipient> findBatchInRange(String tableName, int start, int end) {
        String sql = String.format("SELECT id, name, email FROM %s WHERE id BETWEEN ? AND ? AND status = 'PENDING'", tableName);
        return jdbcTemplate.query(sql, new Object[]{start, end},
            (rs, rowNum) -> new Recipient(rs.getLong("id"), rs.getString("name"), rs.getString("email")));
    }

    public void markAsSent(String tableName, Long id) {
        String sql = String.format("UPDATE %s SET status = 'SENT' WHERE id = ?", tableName);
        jdbcTemplate.update(sql, id);
    }

    public void markAsError(String tableName, Long id) {
        String sql = String.format("UPDATE %s SET status = 'E' WHERE id = ?", tableName);
        jdbcTemplate.update(sql, id);
    }
}
