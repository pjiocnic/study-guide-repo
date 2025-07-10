
package com.example.patientcard.repository;

import com.example.patientcard.model.Patient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class PatientRepository {
    private final JdbcTemplate jdbcTemplate;

    public PatientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Patient findPatientById(Long id) {
        return jdbcTemplate.queryForObject(
            "SELECT patient_id, name, address, dob, photo FROM PATIENTS WHERE patient_id = ?",
            new Object[]{id},
            (rs, rowNum) -> mapPatient(rs));
    }

    public void markAsProcessed(Long id) {
        jdbcTemplate.update("UPDATE PATIENTS SET processed = 'Y' WHERE patient_id = ?", id);
    }

    private Patient mapPatient(ResultSet rs) throws SQLException {
        Patient p = new Patient();
        p.setId(rs.getLong("patient_id"));
        p.setName(rs.getString("name"));
        p.setAddress(rs.getString("address"));
        p.setDob(rs.getDate("dob").toString());
        InputStream photoStream = rs.getBinaryStream("photo");
        if (photoStream != null) {
            try {
                p.setPhoto(photoStream.readAllBytes());
            } catch (Exception ex) {
                p.setPhoto(null);
            }
        }
        return p;
    }
}
