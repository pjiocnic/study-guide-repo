
package com.example.batch.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BatchService
{
    private final JdbcTemplate jdbcTemplate;

    public BatchService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void process(String param1) {
        jdbcTemplate.update("UPDATE USERS SET name = ? WHERE id = ?", param1, 1);
        System.out.println("Updated USER name to " + param1);
    }
}
