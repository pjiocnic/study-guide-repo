package com.example.etl;

import com.example.etl.model.Employee;
import com.example.etl.service.EtlService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MongoOracleS3EtlCliApplication implements CommandLineRunner {

    private final EtlService etl;

    @Value("${app.s3.bucket}")
    private String bucket;

    @Value("${app.s3.prefix:etl/exports}")
    private String prefix;

    public MongoOracleS3EtlCliApplication(EtlService etl) {
        this.etl = etl;
    }

    public static void main(String[] args) {
        SpringApplication.run(MongoOracleS3EtlCliApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        return om;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== ETL: Oracle -> MongoDB -> S3 (JSON) ===");

        List<Employee> rows = etl.readFromOracle();
        System.out.println("Read from Oracle: " + rows.size() + " rows");

        etl.writeToMongo(rows);
        System.out.println("Wrote to Mongo collection");

        List<Employee> fromMongo = etl.readFromMongo();
        System.out.println("Read back from Mongo: " + fromMongo.size() + " docs");

        String key = etl.exportToS3(bucket, prefix, fromMongo);
        System.out.println("Uploaded to s3://" + bucket + "/" + key);
    }
}
