
package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MyRepository
{
    private final JdbcTemplate jdbcTemplate;

    public MyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    public String readUsers() {
        return jdbcTemplate.queryForObject(
                "SELECT name FROM USERS WHERE id = 1",
                String.class
        );
    }

    @Transactional
    public void updateUser() {
        jdbcTemplate.update(
                "UPDATE USERS SET name = ? WHERE id = ?",
                "NewName", 1
        );
    }
}
