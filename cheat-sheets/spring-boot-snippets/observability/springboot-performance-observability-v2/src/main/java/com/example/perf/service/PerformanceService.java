package com.example.perf.service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PerformanceService {

    @Autowired
    private MeterRegistry meterRegistry;

    private final RestTemplate restTemplate = new RestTemplate();

    public void simulateProcessing() {
        simulateDbQuery();
        simulateRemoteApiCall();
    }

    public void simulateDbQuery() {
        Timer.Sample sample = Timer.start(meterRegistry);
        try {
            Thread.sleep(300); // Simulated DB latency
        } catch (InterruptedException ignored) {
        } finally {
            sample.stop(Timer.builder("db.query.duration")
                    .description("Simulated Oracle query duration")
                    .tags("query", "simulateDbQuery")
                    .register(meterRegistry));
        }
    }

    public void simulateRemoteApiCall() {
        Timer.Sample sample = Timer.start(meterRegistry);
        try {
            Thread.sleep(200); // Simulated API call
        } catch (InterruptedException ignored) {
        } finally {
            sample.stop(Timer.builder("api.remote.response.duration")
                    .description("Simulated remote API call")
                    .tags("endpoint", "simulateRemoteApi")
                    .register(meterRegistry));
        }
    }
}