Great! Here's the full **exception log extractor + email delivery via AWS SES**, titled and structured for production use.

---

# 📘 **CloudWatch Exception Log Extractor with Email via AWS SES**

> ✅ Filters exception logs from Lambda
> ✅ Saves them locally
> ✅ Emails the logs as a file attachment using AWS SES

---

## 🧰 Requirements

### ✅ AWS Setup

1. ✅ Lambda function sending logs to CloudWatch
2. ✅ AWS SES verified sender and recipient email
3. ✅ IAM user with `ses:SendEmail` permission

### ✅ Python Dependencies

```bash
pip install boto3
```

---

## 🐍 Full Script: `exception_log_emailer.py`

```python
import boto3
import datetime
import time
import os
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.mime.application import MIMEApplication

# ==== CONFIGURATION ====
LOG_GROUP = "/aws/lambda/your-lambda-function-name"
DATE = "2025-06-17"  # YYYY-MM-DD
OUTPUT_FILE = f"exceptions-{DATE}.log"
FROM_EMAIL = "your-verified-sender@example.com"
TO_EMAIL = "your-verified-recipient@example.com"
SUBJECT = f"Lambda Exception Logs - {DATE}"

# ==== TIME RANGE ====
start_time = int(time.mktime(datetime.datetime.strptime(DATE, "%Y-%m-%d").timetuple()) * 1000)
end_time = start_time + 86400000  # +24 hours

# ==== FILTER PATTERN ====
FILTER_PATTERN = '"Exception" || "Caused by" || "at "'

# ==== BOTO3 CLIENTS ====
logs_client = boto3.client("logs")
ses_client = boto3.client("ses")

def fetch_exception_logs():
    print(f"[INFO] Fetching logs from {LOG_GROUP} for {DATE}...")
    paginator = logs_client.get_paginator("filter_log_events")

    with open(OUTPUT_FILE, "w") as f:
        for page in paginator.paginate(
            logGroupName=LOG_GROUP,
            startTime=start_time,
            endTime=end_time,
            filterPattern=FILTER_PATTERN,
            PaginationConfig={"PageSize": 1000},
        ):
            for event in page.get("events", []):
                f.write(event["message"] + "\n")

    print(f"[INFO] Log file saved to {OUTPUT_FILE}")

def send_email_with_attachment():
    print(f"[INFO] Sending log file to {TO_EMAIL}...")
    msg = MIMEMultipart()
    msg["Subject"] = SUBJECT
    msg["From"] = FROM_EMAIL
    msg["To"] = TO_EMAIL

    body = MIMEText("Attached are the Lambda exception logs for " + DATE, "plain")
    msg.attach(body)

    # Attach log file
    with open(OUTPUT_FILE, "rb") as f:
        part = MIMEApplication(f.read())
        part.add_header("Content-Disposition", "attachment", filename=OUTPUT_FILE)
        msg.attach(part)

    response = ses_client.send_raw_email(
        Source=FROM_EMAIL,
        Destinations=[TO_EMAIL],
        RawMessage={"Data": msg.as_string()}
    )

    print(f"[INFO] Email sent! SES MessageId: {response['MessageId']}")

def main():
    fetch_exception_logs()
    send_email_with_attachment()
    # Optional: os.remove(OUTPUT_FILE)

if __name__ == "__main__":
    main()
```

---

## ✅ IAM Policy Example for SES + CloudWatch Logs

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": [
        "logs:FilterLogEvents",
        "logs:GetLogEvents",
        "ses:SendRawEmail"
      ],
      "Effect": "Allow",
      "Resource": "*"
    }
  ]
}
```

---

## 🧪 Test It

```bash
python exception_log_emailer.py
```

📧 Check your inbox for `exceptions-YYYY-MM-DD.log`

---

## ✅ Summary

| Feature            | Details                                 |
| ------------------ | --------------------------------------- |
| Extracts logs from | `/aws/lambda/your-lambda-function-name` |
| Filters by         | `"Exception"`, `"Caused by"`, `"at "`   |
| Output             | `exceptions-YYYY-MM-DD.log`             |
| Emails via         | AWS SES (raw email + attachment)        |

---

Would you like:

* An S3 upload version instead of email?
* A cronable shell wrapper for automated daily extraction?
* A Lambda version of this extractor that self-emails on schedule?
