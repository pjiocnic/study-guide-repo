# SQS Producer (Spring Boot 3, JDK 17)
High-throughput, crash-resumable producer that reads messages from local files and posts to Amazon SQS.

## Features
- Reads **millions** of messages (one message per line)
- **Parallel** file processing with per-file OS locks (multi-instance safe)
- **Batch sending** (up to 10 per request)
- **Retries with backoff**
- **Crash-safe resume** via local H2 checkpoints (byte offset + line number)
- Optional **rate limiting**
- **FIFO** support with configurable dedup strategy

## Quick Start (Windows / macOS / Linux)
1. Install Java 17 and Maven.
2. Configure AWS credentials (`aws configure`) or set env vars.
3. Update `src/main/resources/application.yml` (region and `queueUrl`).
4. Put messages in `./sample-data` (one line = one message).
5. Build & run:
   ```bash
   mvn -q -DskipTests package
   java -jar target/sqs-producer-0.1.0.jar
   ```

## Configuration
Edit `src/main/resources/application.yml`:
```yaml
producer:
  inputDir: "./sample-data"
  includePattern: ".*\.txt"
  threads: 8
  batchSize: 10
  checkpointEvery: 1000
  rateLimitPerSec: 0
  fifo: false
  messageGroupId: "group-1"
  dedupStrategy: "hash"
aws:
  region: "us-east-1"
  queueUrl: "https://sqs.us-east-1.amazonaws.com/123456789012/my-queue"
```

## Multiple instances
Start multiple processes (or on multiple laptops). Each file is guarded by a `*.lock` OS file lock to avoid double-processing. On crash/kill, the lock is released and the next run resumes using the checkpoint DB.

## Sample data generator
See `tools/generate-sample-file.ps1` and `tools/generate-sample-file.py` to create large test files.

## Notes
- SQS max batch size is 10 messages.
- For FIFO queues, set `producer.fifo=true` and choose a `dedupStrategy`.
- Checkpoints are stored in `./checkpoint-db/` (H2 file). Safe to commit to disk; do not delete if you want to resume.
