package com.example.perf.mq;

import com.example.perf.service.PerformanceService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MqEventListener {

    @Autowired
    private PerformanceService service;

    @Autowired
    private MeterRegistry meterRegistry;

    @JmsListener(destination = "MY.QUEUE")
    public void onMessage(String message) {
        Timer.Sample sample = Timer.start(meterRegistry);
        try {
            service.simulateProcessing();
        } finally {
            sample.stop(Timer.builder("app.total.processing.time")
                             .description("End-to-end time from MQ event")
                             .tags("source", "mq")
                             .register(meterRegistry));
        }
    }
}