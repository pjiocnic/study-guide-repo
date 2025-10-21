package com.example.uploader.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.function.Consumer;

@Repository
public class OracleRepository {

    private static final Logger log = LoggerFactory.getLogger(OracleRepository.class);
    private final JdbcTemplate jdbcTemplate;

    public OracleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Streams all rows of the given SQL to the consumer.
     * Uses a RowCallbackHandler to avoid loading everything in memory.
     */
    public void streamRows(String sql, Consumer<Map<String, Object>> rowConsumer) {
        log.info("Executing Oracle SQL for streaming...");
        jdbcTemplate.setFetchSize(500);
        jdbcTemplate.query(sql, rs -> {
            Map<String, Object> row = new ColumnMapRowMapper().mapRow(rs, rs.getRow());
            rowConsumer.accept(row);
        });
    }
}