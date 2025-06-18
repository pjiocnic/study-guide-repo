## 📚 Java Lambda Logging to CloudWatch – Cookbook

---

### ✅ 1. **Logging from Your Lambda Function**

#### ✔️ Use `System.out.println` or SLF4J (preferred for structured apps)

##### Example 1: Plain Logging (Simple)

```java
public class MyHandler implements RequestHandler<Map<String, Object>, String> {
    @Override
    public String handleRequest(Map<String, Object> input, Context context) {
        System.out.println("This log goes to CloudWatch");
        return "Done";
    }
}
```

##### Example 2: Using SLF4J with Logback (Recommended for complex apps)

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyHandler implements RequestHandler<Map<String, Object>, String> {
    private static final Logger logger = LoggerFactory.getLogger(MyHandler.class);

    @Override
    public String handleRequest(Map<String, Object> input, Context context) {
        logger.info("Handling input: {}", input);
        return "Success";
    }
}
```

> ✅ Lambda automatically sends logs (stdout/stderr) to CloudWatch under:
>
> ```
> /aws/lambda/<function-name>
> ```

---

### 📦 2. **Maven Dependencies for SLF4J**

```xml
<!-- SLF4J API -->
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-api</artifactId>
  <version>2.0.13</version>
</dependency>

<!-- Logback for SLF4J implementation -->
<dependency>
  <groupId>ch.qos.logback</groupId>
  <artifactId>logback-classic</artifactId>
  <version>1.4.14</version>
</dependency>
```

> Optional: Put a `logback.xml` in your `/resources` to configure logging levels/format.

---

## 🔍 3. **Reading Logs from CloudWatch**

### A. Using AWS Console

1. Go to **CloudWatch > Log groups**
2. Open `/aws/lambda/<function-name>`
3. Explore log streams by timestamp
4. Download or filter via **Insights**

---

### B. Using AWS CLI

#### List Log Groups:

```bash
aws logs describe-log-groups
```

#### List Log Streams (sorted by latest):

```bash
aws logs describe-log-streams \
  --log-group-name "/aws/lambda/my-function" \
  --order-by "LastEventTime" \
  --descending
```

#### Get Log Events:

```bash
aws logs get-log-events \
  --log-group-name "/aws/lambda/my-function" \
  --log-stream-name "<stream-name>"
```

---

### C. Filter Logs by Keyword:

```bash
aws logs filter-log-events \
  --log-group-name "/aws/lambda/my-function" \
  --filter-pattern "ERROR"
```

---

### D. CloudWatch Logs Insights Query (Advanced)

```bash
aws logs start-query \
  --log-group-names "/aws/lambda/my-function" \
  --start-time $(($(date +%s) - 3600)) \
  --end-time $(date +%s) \
  --query-string 'fields @timestamp, @message | sort @timestamp desc | limit 20'
```

> Then fetch results:

```bash
aws logs get-query-results --query-id <query-id>
```

---

### 🛑 4. Common Pitfalls

| Issue                 | Fix                                                                           |
| --------------------- | ----------------------------------------------------------------------------- |
| No logs visible       | Ensure Lambda has **execution role with CloudWatchLogs permissions**          |
| Logs delayed          | May take a few seconds to show up                                             |
| Log group not created | Lambda auto-creates `/aws/lambda/<function-name>` only after first invocation |

---

## 🧪 5. Sample IAM Policy for Lambda Logging

```json
{
  "Effect": "Allow",
  "Action": [
    "logs:CreateLogGroup",
    "logs:CreateLogStream",
    "logs:PutLogEvents"
  ],
  "Resource": "*"
}
```

Attach this policy to the Lambda execution role.

---

## ✅ Summary

| Task        | Tool                                |
| ----------- | ----------------------------------- |
| Write logs  | `System.out`, SLF4J                 |
| View logs   | Console, AWS CLI, Insights          |
| Search logs | `filter-log-events`, Insights query |
| Format logs | Use SLF4J + Logback                 |

---

Would you like:

* A ready-made **Java Lambda + SLF4J sample project**?
* A **CloudFormation/Terraform script** to create the log group and IAM role?
* A **log parser/aggregator script** to extract errors?
