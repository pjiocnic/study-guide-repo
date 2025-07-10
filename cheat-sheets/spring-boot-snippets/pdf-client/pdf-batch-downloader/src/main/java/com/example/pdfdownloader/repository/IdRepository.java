
package com.example.pdfdownloader.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IdRepository {

    private final JdbcTemplate jdbcTemplate;

    public IdRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Long> findBatchIds(int batchSize) {
        return jdbcTemplate.queryForList(
            "SELECT id FROM MY_ID_TABLE WHERE PROCESSED = 'N' AND ROWNUM <= ?", Long.class, batchSize);
    }

    public void markAsProcessed(Long id) {
        jdbcTemplate.update("UPDATE MY_ID_TABLE SET PROCESSED = 'Y' WHERE id = ?", id);
    }
}
