package com.example.batchetl.service;

import com.example.batchetl.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OracleEnricher {
    private final JdbcTemplate jdbc;
    @Value("${app.oracle.enrich-by-id-sql}") private String enrichSql;

    public OracleEnricher(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public void enrich(Employee e) {
        if (e == null) return;
        Object param = e.getEmpId() != null ? e.getEmpId() : e.getId();
        try {
            jdbc.query(enrichSql, rs -> {
                if (rs.next()) {
                    e.setOracleDept(rs.getString("ORACLE_DEPT"));
                    e.setOracleTitle(rs.getString("ORACLE_TITLE"));
                }
                return null;
            }, param);
        } catch (Exception ex) {
            try {
                long numeric = Long.parseLong(String.valueOf(param));
                jdbc.query(enrichSql, rs -> {
                    if (rs.next()) {
                        e.setOracleDept(rs.getString("ORACLE_DEPT"));
                        e.setOracleTitle(rs.getString("ORACLE_TITLE"));
                    }
                    return null;
                }, numeric);
            } catch (NumberFormatException ignored) {}
        }
    }
}
