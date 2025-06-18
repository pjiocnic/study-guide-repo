Perfect! Here's a fully functional solution:

---

# 📘 **Lambda-Based CloudWatch Exception Extractor + Self-Emailer (via SES)**

> 🕐 Automatically runs on schedule
> 📥 Fetches exception logs from another Lambda
> 📧 Sends them as email attachment via AWS SES

---

## ✅ Use Case

* You want a **daily job** that:

  * Scans logs of a Lambda function
  * Filters exceptions
  * Emails the results

---

## 🧱 Architecture Overview

```
┌───────────────────────────┐
│ CloudWatch Logs (Source)  │
│  /aws/lambda/my-target-fn │
└────────────┬──────────────┘
             │
             ▼
┌────────────────────────────┐
│ Scheduled Lambda (Fetcher) │
│  - cron (e.g. 6 AM daily)  │
│  - fetch exceptions        │
│  - email via SES           │
└────────────────────────────┘
             │
             ▼
         📧 SES Email
```

---

## 🧰 Setup Steps

### ✅ 1. Create IAM Role for Lambda

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": [
        "logs:FilterLogEvents",
        "ses:SendRawEmail"
      ],
      "Effect": "Allow",
      "Resource": "*"
    }
  ]
}
```

---

### ✅ 2. Lambda Code (Python 3.12)

#### 📁 `lambda_function.py`

```python
import boto3
import datetime
import time
import os
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.mime.application import MIMEApplication

# ==== CONFIGURATION ====
LOG_GROUP = "/aws/lambda/my-target-function"
FROM_EMAIL = "your-verified-sender@example.com"
TO_EMAIL = "your-verified-recipient@example.com"

# ==== AWS Clients ====
logs_client = boto3.client("logs")
ses_client = boto3.client("ses")

def lambda_handler(event, context):
    today = datetime.date.today()
    date_str = today.strftime("%Y-%m-%d")
    filename = f"exceptions-{date_str}.log"

    # Time range: today (00:00 to 23:59)
    start = int(time.mktime(datetime.datetime(today.year, today.month, today.day, 0, 0).timetuple()) * 1000)
    end = start + 86400000  # +24h in ms

    # Pull exception logs
    paginator = logs_client.get_paginator("filter_log_events")
    log_lines = []

    for page in paginator.paginate(
        logGroupName=LOG_GROUP,
        startTime=start,
        endTime=end,
        filterPattern='"Exception" || "Caused by" || "at "',
        PaginationConfig={"PageSize": 1000}
    ):
        for event in page.get("events", []):
            log_lines.append(event["message"])

    if not log_lines:
        log_lines.append("No exceptions logged.")

    # Save to temporary file
    tmp_path = f"/tmp/{filename}"
    with open(tmp_path, "w") as f:
        for line in log_lines:
            f.write(line + "\n")

    # Email the file
    msg = MIMEMultipart()
    msg["Subject"] = f"Lambda Exception Logs - {date_str}"
    msg["From"] = FROM_EMAIL
    msg["To"] = TO_EMAIL
    msg.attach(MIMEText(f"Attached are the exception logs from {LOG_GROUP} for {date_str}", "plain"))

    with open(tmp_path, "rb") as f:
        part = MIMEApplication(f.read())
        part.add_header("Content-Disposition", "attachment", filename=filename)
        msg.attach(part)

    response = ses_client.send_raw_email(
        Source=FROM_EMAIL,
        Destinations=[TO_EMAIL],
        RawMessage={"Data": msg.as_string()}
    )

    return {"status": "success", "messageId": response["MessageId"]}
```

---

### ✅ 3. Deploy Lambda

* Runtime: Python 3.12
* Handler: `lambda_function.lambda_handler`
* Timeout: 30 seconds
* Memory: 256 MB (or higher)
* Role: use IAM role from Step 1

---

### ✅ 4. Schedule with EventBridge Rule

Go to EventBridge > **Create Rule**

| Field     | Value                   |
| --------- | ----------------------- |
| Rule name | `DailyExceptionEmailer` |
| Schedule  | `cron(0 6 * * ? *)`     |
| Target    | Your Lambda function    |

This runs every day at **6 AM UTC**.

---

### ✅ 5. Verify SES Emails

* Both **FROM** and **TO** emails must be **verified** in AWS SES if you're in the **SES sandbox**.

---

## ✅ Summary

| Component       | Purpose                              |
| --------------- | ------------------------------------ |
| Lambda Function | Fetches + emails exception logs      |
| `/tmp/` File    | Temporary .log file for attachment   |
| SES             | Sends structured email with log file |
| EventBridge     | Triggers the Lambda on schedule      |

---

Would you like:

* A ZIP file with `lambda_function.py` and `requirements.txt`?
* Terraform or AWS SAM/CloudFormation template to automate deployment?
* The same system to push logs to S3 as a backup instead of email?
