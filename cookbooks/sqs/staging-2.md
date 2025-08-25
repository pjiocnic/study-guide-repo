Absolutely—here’s a compact Spring Boot (Java 8) CLI app that reads a text file and posts each line to an SQS queue (batching up to 10 at a time). It also shows **three ways to pass AWS access keys**: env vars (recommended), `~/.aws/credentials` profile, or explicit CLI flags.

---

# What you get

* **Java 8 / Spring Boot 2.7** (so it runs on JDK 1.8).
* **AWS SDK v1 (1.12.x)** for SQS.
* **Batch sending (<=10 per request)** with FIFO support.
* **Multiple credential options** (env, profile, CLI flags).
* **Clean logs + basic throughput metrics**.

---

## `pom.xml`

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>demo</groupId>
  <artifactId>sqs-file-publisher</artifactId>
  <version>0.0.1</version>
  <properties>
    <java.version>1.8</java.version>
    <spring-boot.version>2.7.18</spring-boot.version>
    <aws.sdk.version>1.12.742</aws.sdk.version>
  </properties>

  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>${spring-boot.version}</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>

  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
    </dependency>
    <dependency>
      <groupId>com.amazonaws</groupId>
      <artifactId>aws-java-sdk-sqs</artifactId>
      <version>${aws.sdk.version}</version>
    </dependency>
    <dependency>
      <groupId>commons-codec</groupId>
      <artifactId>commons-codec</artifactId>
      <version>1.16.0</version>
    </dependency>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.11.0</version>
        <configuration>
          <source>${java.version}</source>
          <target>${java.version}</target>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```

---

## `src/main/resources/application.properties`

```properties
# Defaults; override via CLI flags, env, or profiles
region=us-east-1
batchSize=10
fifo=false
contentBasedDedup=true
groupId=default-group

# logging
logging.level.root=INFO
logging.file.name=logs/sqs-file-publisher.log
logging.file.max-size=10MB
logging.file.total-size-cap=100MB
```

---

## `src/main/java/demo/SqsFilePublisherApplication.java`

```java
package demo;

import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.*;
import com.amazonaws.services.sqs.model.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class SqsFilePublisherApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(SqsFilePublisherApplication.class);

    @Value("${queueUrl:}")
    private String queueUrl;

    @Value("${file:messages.txt}")
    private String filePath;

    @Value("${region:us-east-1}")
    private String region;

    @Value("${batchSize:10}")
    private int batchSize;

    @Value("${fifo:false}")
    private boolean fifo;

    @Value("${groupId:default-group}")
    private String groupId;

    @Value("${contentBasedDedup:true}")
    private boolean contentBasedDedup;

    // Optional explicit credentials via CLI flags (NOT recommended; prefer env/profile)
    @Value("${aws.accessKeyId:}")
    private String accessKeyId;
    @Value("${aws.secretAccessKey:}")
    private String secretAccessKey;
    @Value("${aws.sessionToken:}")
    private String sessionToken;

    private AmazonSQS sqs;

    public static void main(String[] args) {
        SpringApplication.run(SqsFilePublisherApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        validateInputs();
        this.sqs = buildClient();

        Path path = Paths.get(filePath);
        long total = 0L;
        Instant start = Instant.now();

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            List<String> buffer = new ArrayList<>(batchSize);
            String line;

            while ((line = br.readLine()) != null) {
                // skip empty lines
                if (line.trim().isEmpty()) continue;

                buffer.add(line);
                if (buffer.size() == batchSize) {
                    total += sendBatch(buffer);
                    buffer.clear();
                }
            }
            if (!buffer.isEmpty()) {
                total += sendBatch(buffer);
            }
        }

        Duration d = Duration.between(start, Instant.now());
        double secs = Math.max(1.0, d.toMillis() / 1000.0);
        log.info("Completed: sent {} messages in {} ms (~{}/sec)", total, d.toMillis(), Math.round(total / secs));

        // Exit cleanly so containers/cron see success
        System.exit(0);
    }

    private void validateInputs() {
        if (queueUrl == null || queueUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("queueUrl is required. Pass --queueUrl=https://sqs.<region>.amazonaws.com/<acct>/<queue>");
        }
        if (!Files.exists(Paths.get(filePath))) {
            throw new IllegalArgumentException("Input file not found: " + filePath);
        }
        if (batchSize < 1 || batchSize > 10) {
            throw new IllegalArgumentException("batchSize must be between 1 and 10 (SQS limit).");
        }
        if (fifo && !queueUrl.endsWith(".fifo")) {
            log.warn("You set fifo=true but the queue URL does not end with .fifo");
        }
    }

    private AmazonSQS buildClient() {
        AmazonSQSClientBuilder b = AmazonSQSClientBuilder.standard()
                .withRegion(Regions.fromName(region));

        // If CLI flags for keys are provided, use them; else fall back to DefaultAWSCredentialsProviderChain
        AWSCredentialsProvider provider = resolveCredentials();
        if (provider != null) {
            b.setCredentials(provider);
        }
        AmazonSQS client = b.build();

        log.info("SQS client ready. region={}, fifo={}, contentBasedDedup={}", region, fifo, contentBasedDedup);
        return client;
    }

    private AWSCredentialsProvider resolveCredentials() {
        if (notEmpty(accessKeyId) && notEmpty(secretAccessKey)) {
            if (notEmpty(sessionToken)) {
                log.info("Using BasicSessionCredentials from CLI flags");
                return new AWSStaticCredentialsProvider(new BasicSessionCredentials(accessKeyId, secretAccessKey, sessionToken));
            }
            log.info("Using BasicAWSCredentials from CLI flags");
            return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKeyId, secretAccessKey));
        }
        // Default chain: env vars -> system props -> profile (~/.aws/credentials) -> ECS/EKS/EC2 role
        log.info("Using DefaultAWSCredentialsProviderChain");
        return DefaultAWSCredentialsProviderChain.getInstance();
    }

    private boolean notEmpty(String s) { return s != null && !s.trim().isEmpty(); }

    private long sendBatch(List<String> lines) {
        List<SendMessageBatchRequestEntry> entries = new ArrayList<>(lines.size());
        int idx = 0;

        for (String body : lines) {
            idx++;
            SendMessageBatchRequestEntry e = new SendMessageBatchRequestEntry()
                    .withId("m" + System.nanoTime()) // must be unique per batch
                    .withMessageBody(body);

            if (fifo) {
                e.setMessageGroupId(groupId);

                if (!contentBasedDedup) {
                    // If content-based dedup is OFF, you must provide a dedup ID per message.
                    // Use a stable hash of the body; duplicates in 5-min dedup window will be discarded (intended).
                    String dedupId = DigestUtils.sha256Hex(body);
                    e.setMessageDeduplicationId(dedupId);
                }
            }
            entries.add(e);
        }

        SendMessageBatchRequest req = new SendMessageBatchRequest()
                .withQueueUrl(queueUrl)
                .withEntries(entries);

        SendMessageBatchResult result = sqs.sendMessageBatch(req);

        if (!result.getFailed().isEmpty()) {
            String failed = result.getFailed().stream()
                    .map(f -> f.getId() + ":" + f.getMessage())
                    .collect(Collectors.joining(", "));
            log.error("Some messages failed: {}", failed);
            throw new RuntimeException("Batch send failed: " + failed);
        }

        log.debug("Sent {} messages", result.getSuccessful().size());
        return result.getSuccessful().size();
    }
}
```

---

## How to run

### 1) Build

```bash
mvn -q -DskipTests package
```

### 2) Prepare input

Create `messages.txt` (one message per line).

### 3) Provide AWS credentials (pick ONE of the following)

**A. Environment variables (recommended)**

```bash
export AWS_ACCESS_KEY_ID=AKIA...
export AWS_SECRET_ACCESS_KEY=...
# If using temporary creds:
export AWS_SESSION_TOKEN=...
```

**B. Shared profile (`~/.aws/credentials`)**

```ini
[myprofile]
aws_access_key_id=AKIA...
aws_secret_access_key=...
```

Then run with `-Daws.profile=myprofile` (the SDK picks up `AWS_PROFILE` or `aws.profile` automatically), or simply `export AWS_PROFILE=myprofile`.

**C. CLI flags (works, but not recommended because it hits shell history)**

```bash
java -jar target/sqs-file-publisher-0.0.1.jar \
  --aws.accessKeyId=AKIA... \
  --aws.secretAccessKey=... \
  --aws.sessionToken=...   # optional
  ...
```

> Best practice: prefer **A** or **B**. Avoid committing keys; consider tools like aws-vault for secure local usage.

### 4) Send to a **standard** queue

```bash
java -jar target/sqs-file-publisher-0.0.1.jar \
  --queueUrl=https://sqs.us-east-1.amazonaws.com/123456789012/my-queue \
  --file=messages.txt \
  --region=us-east-1 \
  --batchSize=10
```

### 5) Send to a **FIFO** queue

**If content-based deduplication is enabled on the queue:**

```bash
java -jar target/sqs-file-publisher-0.0.1.jar \
  --queueUrl=https://sqs.us-east-1.amazonaws.com/123456789012/my-queue.fifo \
  --file=messages.txt \
  --region=us-east-1 \
  --fifo=true \
  --groupId=my-group-1 \
  --contentBasedDedup=true
```

**If content-based deduplication is NOT enabled (we provide dedup IDs):**

```bash
java -jar target/sqs-file-publisher-0.0.1.jar \
  --queueUrl=https://sqs.us-east-1.amazonaws.com/123456789012/my-queue.fifo \
  --file=messages.txt \
  --region=us-east-1 \
  --fifo=true \
  --groupId=my-group-1 \
  --contentBasedDedup=false
```

---

## Notes & Tips

* **Limits:** message body ≤ 256 KB; batch ≤ 10 messages.
* **Throughput:** for \~millions of lines, split the file and run multiple instances in parallel (e.g., `split -n l/8 messages.txt part_`).
* **Retries:** if you want automatic retries with backoff, we can wrap `sendMessageBatch` in a retry template.
* **Attributes:** add `withMessageAttributes(...)` to entries if you need typed attributes.

If you want, I can add:

* a **Dockerfile**,
* **parallel multi-worker** mode with safe checkpointing (resume after crash),
* or a **REST endpoint** to post messages from a service.
