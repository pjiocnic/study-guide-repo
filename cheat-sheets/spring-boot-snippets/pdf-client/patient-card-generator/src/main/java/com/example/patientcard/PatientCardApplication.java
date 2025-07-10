
package com.example.patientcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PatientCardApplication {
    public static void main(String[] args) {
        SpringApplication.run(PatientCardApplication.class, args);
    }
}
