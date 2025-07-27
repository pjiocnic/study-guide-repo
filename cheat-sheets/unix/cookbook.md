Here’s your **fully production-ready shell script** with all the best practices integrated:

---

## ✅ `run_task.sh` – Final Version (Prod-Hardened)

```bash
#!/bin/bash

###############################################################################
# run_task.sh
#
# Description:
#   Run a Java JAR task with a timestamped log file.
#   Includes safety checks, retry, pruning, alerts, and symlink to latest log.
#
# Usage:
#   ./run_task.sh <task_name>
#
# Dependencies:
#   - bash
#   - gzip (for optional log compression)
#   - mail (optional: for alerting)
###############################################################################

set -euo pipefail

# ===== CONFIGURATION =====
JAR_FILE="myapp.jar"                     # Your Spring Boot JAR
JAVA_BIN="${JAVA_HOME:-}/bin/java"      # Use JAVA_HOME if set
LOG_DIR="./logs"                        # Directory for logs
DATE_FORMAT="%Y%m%d_%H%M%S"             # Timestamp format
RETAIN_DAYS=7                           # Log retention period
MAX_RETRIES=3                           # Retries on failure
RETRY_DELAY=10                          # Seconds between retries
MAIL_ALERTS_ENABLED=false               # Set true to enable alerts
ALERT_EMAIL="you@example.com"           # Email for failure notifications
COMPRESS_OLD_LOGS=true                  # Gzip old logs

# ===== ARGUMENT CHECK =====
if [[ $# -ne 1 ]]; then
  echo "❌ Usage: $0 <task_name>"
  exit 1
fi

TASK_NAME="$1"

# ===== CHECK JAR =====
if [[ ! -f "$JAR_FILE" ]]; then
  echo "❌ JAR file not found: $JAR_FILE"
  exit 1
fi

# ===== PREPARE LOG FILE =====
TIMESTAMP=$(date +"$DATE_FORMAT")
mkdir -p "$LOG_DIR"
LOG_FILE="${LOG_DIR}/${TASK_NAME}_${TIMESTAMP}.log"
LATEST_LINK="${LOG_DIR}/${TASK_NAME}_latest.log"

# ===== LOG START INFO =====
{
  echo "=== 🚀 TASK STARTED: $TASK_NAME ==="
  echo "Time: $(date)"
  echo "Host: $(hostname)"
  echo "User: $(whoami)"
  echo "Log File: $LOG_FILE"
} >> "$LOG_FILE"

# ===== EXECUTION WITH RETRIES =====
attempt=1
success=false

while [[ $attempt -le $MAX_RETRIES ]]; do
  {
    echo "--- Attempt #$attempt at $(date) ---"
    "$JAVA_BIN" $JAVA_OPTS -jar "$JAR_FILE"
  } >> "$LOG_FILE" 2>&1 && {
    success=true
    break
  }

  echo "⚠️  Attempt #$attempt failed. Retrying in $RETRY_DELAY seconds..." >> "$LOG_FILE"
  sleep "$RETRY_DELAY"
  attempt=$((attempt + 1))
done

# ===== POST-EXECUTION =====
if [[ "$success" == true ]]; then
  echo "✅ Task completed successfully at $(date)" >> "$LOG_FILE"
else
  echo "❌ All $MAX_RETRIES attempts failed at $(date)" >> "$LOG_FILE"

  if [[ "$MAIL_ALERTS_ENABLED" == true ]]; then
    mail -s "🚨 Task $TASK_NAME FAILED on $(hostname)" "$ALERT_EMAIL" < "$LOG_FILE"
  fi

  exit 1
fi

# ===== UPDATE SYMLINK TO LATEST =====
ln -sf "$(basename "$LOG_FILE")" "$LATEST_LINK"

# ===== CLEANUP OLD LOGS =====
find "$LOG_DIR" -type f -name "${TASK_NAME}_*.log" -mtime +$RETAIN_DAYS -exec rm -f {} \;

# ===== COMPRESS OLD LOGS (EXCEPT LATEST) =====
if [[ "$COMPRESS_OLD_LOGS" == true ]]; then
  find "$LOG_DIR" -type f -name "${TASK_NAME}_*.log" ! -name "$(basename "$LOG_FILE")" -mtime +1 -exec gzip -f {} \;
fi

echo "📄 Log saved to: $LOG_FILE"
echo "🔗 Latest symlink: $LATEST_LINK"
```

---

## ✅ Features Recap

| Feature                            | Included |
| ---------------------------------- | -------- |
| `set -euo pipefail`                | ✅        |
| JAR existence check                | ✅        |
| Retry logic                        | ✅        |
| Timestamped log files              | ✅        |
| Latest log symlink                 | ✅        |
| Log retention policy (7 days)      | ✅        |
| Optional log compression           | ✅        |
| Failure email alert (configurable) | ✅        |
| Full user and host metadata        | ✅        |

---

## 🔧 How to Use

1. ✅ Make it executable:

   ```bash
   chmod +x run_task.sh
   ```

2. ✅ Run:

   ```bash
   ./run_task.sh generate_invoice
   ```

3. ✅ Enable email alerts (optional):

   * Set `MAIL_ALERTS_ENABLED=true` and update `ALERT_EMAIL`

4. ✅ Tune `JAVA_OPTS`:

   ```bash
   export JAVA_OPTS="-Xmx512m -Dspring.profiles.active=prod"
   ```

---

Let me know if you'd like this packaged as a `.zip` with a README and sample app.
