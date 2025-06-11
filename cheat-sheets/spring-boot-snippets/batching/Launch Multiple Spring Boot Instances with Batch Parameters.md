Great question! If you want to **run multiple instances of your Spring Boot application**, each processing a **different batch range**, you can easily do this by:

1. **Passing parameters via the command line**
2. **Reading them in your Spring Boot code (e.g., using `@Value`, `Environment`, or `ApplicationArguments`)**

---

## ✅ Step-by-Step: Launch Multiple Spring Boot Instances with Batch Parameters

---

### ✅ 1. Pass Parameters via Command Line

Use the `--` syntax to pass Spring Boot-style properties:

```bash
java -jar your-app.jar --batch.start=100 --batch.end=200
```

Launch another with:

```bash
java -jar your-app.jar --batch.start=201 --batch.end=300
```

Each instance will get a different batch range.

---

### ✅ 2. Accept Parameters in Code

#### Option A: Use `@Value` injection (most convenient)

```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BatchProcessor {

    @Value("${batch.start}")
    private int batchStart;

    @Value("${batch.end}")
    private int batchEnd;

    public void run() {
        System.out.printf("Processing records from %d to %d%n", batchStart, batchEnd);
        // your logic here
    }
}
```

Then call `batchProcessor.run()` in a `@PostConstruct` or from `CommandLineRunner`.

---

#### Option B: Use `ApplicationArguments`

```java
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class BatchRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        int start = Integer.parseInt(args.getOptionValues("batch.start").get(0));
        int end = Integer.parseInt(args.getOptionValues("batch.end").get(0));
        System.out.printf("Running batch from %d to %d%n", start, end);
    }
}
```

---

## 🔧 Optional: Provide Defaults via `application.properties`

If you want fallback values:

```properties
batch.start=0
batch.end=100
```

Then your `@Value`-based code still works even if command-line values aren't passed.

---

## 🚀 Launch Script Example (Linux)

```bash
#!/bin/bash

java -jar your-app.jar --batch.start=0 --batch.end=100 &
java -jar your-app.jar --batch.start=101 --batch.end=200 &
java -jar your-app.jar --batch.start=201 --batch.end=300 &
```

You can even pass these from `cron` or a shell script loop.

---

Let me know if you want:
- A template Spring Boot app that demonstrates this
- Integration with Docker using `CMD` args
- Using profiles to isolate configs per batch instance