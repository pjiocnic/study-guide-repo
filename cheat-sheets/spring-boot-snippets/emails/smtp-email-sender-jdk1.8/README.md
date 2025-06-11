# SMTP Email Sender with Rate Limiting

This project demonstrates how to:

1. Send email **synchronously** via SMTP and wait for acknowledgement.
2. Use **rate limiting** (using Bucket4j) to restrict the number of SMTP requests.

---

## ✅ What’s Included

| Component         | Description                                   |
|------------------|-----------------------------------------------|
| `EmailService`    | Sends email using JavaMailSender              |
| `RateLimiter`     | Controls how many emails can be sent per sec  |
| `EmailController` | REST endpoint to trigger email send           |

---

## 📦 Tech Stack

- Java 8
- Spring Boot 2.7.x
- Spring Mail
- Bucket4j (token bucket rate limiting)

---

## 🚀 Run & Test

1. Add your SMTP settings in `application.properties`
2. Run the app and use this cURL to send an email:

```bash
curl -X POST http://localhost:8080/api/send-email \
     -H "Content-Type: application/json" \
     -d '{"to": "test@example.com", "subject": "Hello", "body": "Test"}'
```
