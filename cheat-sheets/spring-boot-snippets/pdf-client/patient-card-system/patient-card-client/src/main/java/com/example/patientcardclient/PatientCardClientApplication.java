
package com.example.patientcardclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PatientCardClientApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PatientCardClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        RestTemplate rt = new RestTemplate();
        Long patientId = 1L; // test id
        String response = rt.postForObject("http://localhost:8080/generateCard/" + patientId, null, String.class);
        System.out.println("Server responded: " + response);
    }
}
