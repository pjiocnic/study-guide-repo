package com.example.demo.controller;

import com.example.demo.service.BatchJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchJobController {

    private final BatchJobService batchJobService;

    @Autowired
    public BatchJobController(BatchJobService batchJobService) {
        this.batchJobService = batchJobService;
    }

    @GetMapping("/trigger-jobs")
    public String triggerJobs() {
        batchJobService.processPendingJobs();
        return "Batch jobs triggered!";
    }
}
