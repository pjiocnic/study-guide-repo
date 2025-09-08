package com.example.etl.service;

import com.example.etl.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class EtlService {

    private final JdbcTemplate jdbc;
    private final MongoTemplate mongo;
    private final S3ExportService s3;

    @Value("${app.etl.oracle-query}")
    private String oracleQuery;

    @Value("${app.etl.mongo-collection}")
    private String mongoCollection;

    public EtlService(JdbcTemplate jdbc, MongoTemplate mongo, S3ExportService s3) {
        this.jdbc = jdbc;
        this.mongo = mongo;
        this.s3 = s3;
    }

    public List<Employee> readFromOracle() {
        RowMapper<Employee> mapper = new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Employee(
                        rs.getLong("EMP_ID"),
                        rs.getString("FIRST_NAME"),
                        rs.getString("LAST_NAME"),
                        rs.getString("EMAIL")
                );
            }
        };
        return jdbc.query(oracleQuery, mapper);
    }

    public void writeToMongo(List<Employee> employees) {
        employees.forEach(emp -> mongo.save(emp, mongoCollection));
    }

    public List<Employee> readFromMongo() {
        return mongo.find(new Query(), Employee.class, mongoCollection);
    }

    public String exportToS3(String bucket, String prefix, List<Employee> employees) throws Exception {
        return s3.uploadJson(bucket, prefix, "employees", employees);
    }
}
