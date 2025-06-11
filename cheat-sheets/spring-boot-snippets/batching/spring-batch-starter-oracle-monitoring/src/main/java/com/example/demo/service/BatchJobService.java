package com.example.demo.service;

import com.example.demo.entity.BatchJob;
import com.example.demo.repository.BatchJobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class BatchJobService {

    private static final Logger logger = LoggerFactory.getLogger(BatchJobService.class);
    private final BatchJobRepository batchJobRepository;
    private final RedisLockManager redisLockManager;
    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Autowired
    public BatchJobService(BatchJobRepository batchJobRepository, RedisLockManager redisLockManager) {
        this.batchJobRepository = batchJobRepository;
        this.redisLockManager = redisLockManager;
    }

    @Scheduled(fixedRate = 60000)
    public void scheduleJobProcessor() {
        String lockKey = "batch_job_lock";
        String lockValue = UUID.randomUUID().toString();
        if (redisLockManager.acquireLock(lockKey, lockValue, Duration.ofMinutes(2))) {
            try {
                processPendingJobs();
            } finally {
                redisLockManager.releaseLock(lockKey, lockValue);
            }
        } else {
            logger.info("Another instance is already processing batch jobs.");
        }
    }

    public void processPendingJobs() {
        List<BatchJob> pendingJobs = batchJobRepository.findByStatus("PENDING");
        for (BatchJob job : pendingJobs) {
            job.setStatus("PROCESSING");
            batchJobRepository.save(job);

            executorService.submit(() -> processJob(job));
        }
    }

    private void processJob(BatchJob job) {
        try {
            logger.info("Processing job: {}", job.getName());
            Thread.sleep(5000);
            job.setStatus("COMPLETED");
        } catch (InterruptedException e) {
            logger.error("Job failed: {}", job.getName(), e);
            job.setStatus("FAILED");
            Thread.currentThread().interrupt();
        } finally {
            batchJobRepository.save(job);
        }
    }
}
