# Email Validator Spring Boot Project

This Spring Boot 3.x project validates email addresses with:

- ✅ RFC-compliant email format validation (Apache Commons Validator)
- ✅ DNS MX record validation (JNDI DirContext)
- ✅ JavaMail fallback parser (InternetAddress)
- ✅ Logs invalid emails to a file `invalid-emails.log`

---

## 📦 What’s Included in This Spring Boot Project

| Component                | Description                                                  |
|--------------------------|--------------------------------------------------------------|
| `EmailValidatorUtil`     | Performs full email validation (format + DNS + MX)           |
| `EmailValidationService` | Validates and logs invalid emails to file                    |
| `EmailController`        | REST endpoint `/api/validate-email`                          |
| `EmailRequest`           | DTO used to accept input from JSON requests                  |

---

## 🚀 Running the Application

1. Ensure you have **Java 17+** and **Maven** installed.
2. Build and run the app:

```bash
./mvnw spring-boot:run
```

---

## 🧪 Sample Test with cURL

Try validating an email:

```bash
curl -X POST http://localhost:8080/api/validate-email \
     -H "Content-Type: application/json" \
     -d '{"email": "invalid@@bad-domain"}'
```

Expected Response:
```
❌ Invalid email (logged)
```

---

## 📁 How to Check That `invalid-emails.log` Was Written

Look in the root folder where the application runs:

```bash
cat invalid-emails.log
```

Each invalid email will be appended with a timestamp:
```
[2025-04-15T08:10:00] Invalid email: invalid@@bad-domain
```

---

## ✅ Tech Stack

- Java 17
- Spring Boot 3.1.x
- Apache Commons Validator 1.7
- Jakarta Mail (JavaMail) 2.0.1
