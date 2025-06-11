package com.example.observability.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MetricsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveMetric(String name, double value) {
        jdbcTemplate.update("INSERT INTO metrics_demo (name, value) VALUES (?, ?)", name, value);
    }
}