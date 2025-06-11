package com.example.batchrunner;

public class BatchWorker implements Runnable {

    private final int start;
    private final int end;
    private final int threadId;

    public BatchWorker(int start, int end, int threadId) {
        this.start = start;
        this.end = end;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            System.out.printf("Thread-%d processing record %d%n", threadId, i);
            // Simulate work
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {}
        }
    }
}
