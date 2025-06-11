# Scalable Batch Email Sender (Advanced)

This version includes:

1. ✅ Retry with exponential backoff
2. ✅ Sharding across threads using ExecutorService
3. ✅ Custom scheduler with dynamic batch sizes per table

---

## 📦 Technologies

- Spring Boot 2.7
- Java 8
- Spring Data JPA + H2 DB
- Spring Mail
- No Spring Batch — custom lightweight logic

---

## 🔁 Scheduler Triggered Automatically

No need to manually invoke API — just run the app!

You can still hit this endpoint to trigger manually:

```bash
curl -X POST http://localhost:8080/api/send-batch/table1
```
