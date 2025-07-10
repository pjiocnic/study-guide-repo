
package com.example.patientcard.controller;

import com.example.patientcard.service.PatientCardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generateCard")
public class PatientCardController {
    private final PatientCardService service;

    public PatientCardController(PatientCardService service) {
        this.service = service;
    }

    @PostMapping("/{patientId}")
    public String generate(@PathVariable Long patientId) {
        try {
            service.generateCard(patientId);
            return "Card generated successfully for patient " + patientId;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
