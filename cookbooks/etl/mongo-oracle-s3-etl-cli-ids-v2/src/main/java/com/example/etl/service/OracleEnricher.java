package com.example.etl.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OracleEnricher {

    private final JdbcTemplate jdbc;

    @Value("${app.oracle.by-id-sql}")
    private String byIdSql;

    public OracleEnricher(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Map<String, Object> loadById(String id) {
        try {
            return jdbc.query(byIdSql, rs -> {
                if (rs.next()) {
                    Map<String, Object> m = new HashMap<>();
                    m.put("EMP_ID", rs.getObject("EMP_ID"));
                    m.put("FIRST_NAME", rs.getString("FIRST_NAME"));
                    m.put("LAST_NAME", rs.getString("LAST_NAME"));
                    m.put("EMAIL", rs.getString("EMAIL"));
                    return m;
                }
                return null;
            }, id);
        } catch (Exception e) {
            try {
                long numeric = Long.parseLong(id);
                return jdbc.query(byIdSql, rs -> {
                    if (rs.next()) {
                        Map<String, Object> m = new HashMap<>();
                        m.put("EMP_ID", rs.getObject("EMP_ID"));
                        m.put("FIRST_NAME", rs.getString("FIRST_NAME"));
                        m.put("LAST_NAME", rs.getString("LAST_NAME"));
                        m.put("EMAIL", rs.getString("EMAIL"));
                        return m;
                    }
                    return null;
                }, numeric);
            } catch (NumberFormatException nfe) {
                throw e;
            }
        }
    }
}
