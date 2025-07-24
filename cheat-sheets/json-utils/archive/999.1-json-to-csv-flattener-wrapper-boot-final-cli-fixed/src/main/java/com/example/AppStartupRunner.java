package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "mode", havingValue = "cli", matchIfMissing = true)
public class AppStartupRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        FlattenBatchJob.main(args);
    }
}
