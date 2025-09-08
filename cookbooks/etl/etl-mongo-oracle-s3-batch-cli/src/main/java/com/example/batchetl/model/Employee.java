package com.example.batchetl.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("employees")
public class Employee {
    @Id
    private String id;
    private Long empId;
    private String firstName;
    private String lastName;
    private String email;
    private String oracleDept;
    private String oracleTitle;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Long getEmpId() { return empId; }
    public void setEmpId(Long empId) { this.empId = empId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getOracleDept() { return oracleDept; }
    public void setOracleDept(String oracleDept) { this.oracleDept = oracleDept; }
    public String getOracleTitle() { return oracleTitle; }
    public void setOracleTitle(String oracleTitle) { this.oracleTitle = oracleTitle; }
}
