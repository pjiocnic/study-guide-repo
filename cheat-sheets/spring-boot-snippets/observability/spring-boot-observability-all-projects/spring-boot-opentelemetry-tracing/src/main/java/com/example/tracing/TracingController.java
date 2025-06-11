package com.example.tracing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TracingController {

    @GetMapping("/trace")
    public String traceExample() {
        return "Tracing endpoint hit!";
    }
}
