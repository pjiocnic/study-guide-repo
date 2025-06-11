package com.example.observability;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObservabilityController {

    @GetMapping("/observe")
    public String observe() {
        return "Metrics and Tracing working!";
    }
}
