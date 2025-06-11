
---

## 🕒 Crontab Wrapper

You can schedule the batch run using the included shell script:

### `run-email-batch.sh`
```bash
./run-email-batch.sh <tableName> <batchStart> <batchEnd>
```

Example crontab:
```
0 * * * * /opt/email-batch/run-email-batch.sh recipients 1 100
```

### Output Log:
Logs go to:
```
app_YYYYMMDD_HHMMSS.log
```

Each run includes:
- ✅ Printed batch range: `Processing batch: 1 to 100`
- ✅ Correlation ID: `Run ID: <UUID>` (appears at top of logs)
