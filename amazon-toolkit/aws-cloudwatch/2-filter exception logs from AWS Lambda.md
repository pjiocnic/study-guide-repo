To **filter exception logs from AWS Lambda**, you can extract them from CloudWatch logs using:

---

## ✅ 1. Filter via AWS CLI

To find logs containing exceptions:

```bash
aws logs filter-log-events \
  --log-group-name "/aws/lambda/<your-function-name>" \
  --filter-pattern '"Exception"' \
  --limit 100
```

You can also search for common exception patterns:

```bash
--filter-pattern '"Exception" || "at " || "Caused by:"'
```

This will catch:

* `java.lang.NullPointerException`
* `at com.example.MyClass.method(MyClass.java:42)`
* `Caused by: java.io.IOException`

---

## ✅ 2. CloudWatch Logs Insights Query (Recommended)

Go to **CloudWatch > Logs > Insights**, select your Lambda log group:

### 🔍 Query: Find Java Exception Traces

```sql
fields @timestamp, @message
| filter @message like /Exception/
| sort @timestamp desc
| limit 50
```

### 🔍 Query: Capture stack traces (look for "at ")

```sql
fields @timestamp, @message
| filter @message like /at /
| sort @timestamp desc
| limit 100
```

### 🔍 Query: Full Java trace block

```sql
fields @timestamp, @message
| filter @message like /Exception/ or @message like /at / or @message like /Caused by/
| sort @timestamp desc
| limit 100
```

> You can group by requestId or time window to reconstruct full stack traces.

---

## ✅ 3. Python Script to Extract Exception Logs

```python
import boto3
import re

log_group = "/aws/lambda/your-lambda-name"

client = boto3.client("logs")

def filter_exceptions():
    response = client.filter_log_events(
        logGroupName=log_group,
        filterPattern='"Exception" || "Caused by" || "at "'
    )

    for event in response.get("events", []):
        print(event["message"])

filter_exceptions()
```

---

## ✅ 4. Tips for Better Exception Logging

### 🛠 Add structured tags to your log messages:

```java
try {
    doSomething();
} catch (Exception e) {
    logger.error("[EXCEPTION] {}", e.toString(), e);
}
```

Then search with:

```sql
filter @message like /[EXCEPTION]/
```

---

## ✅ Summary

| Tool                     | Use                            |
| ------------------------ | ------------------------------ |
| `filter-log-events`      | CLI-based one-time grep        |
| CloudWatch Logs Insights | Fast querying & filtering      |
| Python script            | Automate & extract to local    |
| SLF4J structured logs    | Easier pattern-based filtering |

---

Would you like a ready-to-run notebook or script to download all exception logs for a given day and email or store them?
