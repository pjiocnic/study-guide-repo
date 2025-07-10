
package com.example.patientcard.scheduler;

import com.example.patientcard.service.PatientCardService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PatientCardScheduler {

    private final PatientCardService service;

    @Value("${card.schedule.interval.seconds}")
    private int intervalSeconds;

    public PatientCardScheduler(PatientCardService service) {
        this.service = service;
    }

    @Scheduled(fixedDelayString = "#{${card.schedule.interval.seconds} * 1000}")
    public void generateCardsBatch() {
        try {
            service.generateCards();
        } catch (Exception e) {
            System.err.println("Error in scheduled card generation: " + e.getMessage());
        }
    }
}
