# Batch Email Sender (Scalable Design)

This Spring Boot 2.7 + Java 8 project demonstrates a scalable approach to sending lots of emails stored in **multiple tables**.

---

## ✅ Features

- Reads from multiple email tables using Spring Data JPA
- Sends emails **asynchronously** using `@Async`
- Uses a **controller** to trigger processing by table
- Scalable pattern: add more tables + services without duplicating logic

---

## 🧪 Sample Trigger

```bash
curl -X POST http://localhost:8080/api/send-batch/table1
curl -X POST http://localhost:8080/api/send-batch/table2
```

---

## ⚙️ Technologies Used

- Java 8
- Spring Boot 2.7
- Spring Mail + Data JPA
- H2 in-memory DB (easy to swap with Oracle/MySQL/Postgres)

