package com.example.perf.controller;

import com.example.perf.service.PerformanceService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProcessController {

    @Autowired
    private PerformanceService service;

    @Autowired
    private MeterRegistry meterRegistry;

    @GetMapping("/process")
    public ResponseEntity<String> process() {
        Timer.Sample sample = Timer.start(meterRegistry);
        try {
            service.simulateProcessing();
            return ResponseEntity.ok("Processing complete");
        } finally {
            sample.stop(Timer.builder("app.total.processing.time")
                    .description("Time to process API request")
                    .tags("source", "rest")
                    .register(meterRegistry));
        }
    }
}