
# Email Batch Sender (Spring Boot + Oracle + FreeMarker)

## ✅ What's Included in This Spring Boot Project

| Component | Description |
|----------|-------------|
| ✅ Oracle DB | Fetch recipients from `recipients` table and update statuses |
| ✅ External Config | SMTP settings passed using `-Dconfig.path=/etc/email/config.properties` |
| ✅ FreeMarker Templates | Use `-Dtemplate.path=/etc/email/email.ftl` for HTML body |
| ✅ JavaMailSender | Sends personalized HTML emails |
| ✅ Email Validation | Uses Apache Commons Validator to skip invalid addresses |
| ✅ Mark Error | Invalid emails are marked `status = 'E'` in DB |
| ✅ Java 8 Compatible | Runs with `JDK 1.8` and `Spring Boot 2.7.x` |
| ✅ Simple Execution | CLI-based batch job using `CommandLineRunner` |

## 🚀 Run Command

```bash
java -Dconfig.path=/etc/email/config.properties -Dtemplate.path=/etc/email/email.ftl -jar target/email-batch.jar
```

## 📂 Table Structure (Sample)

```sql
CREATE TABLE recipients (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(255),
    email VARCHAR2(255),
    status VARCHAR2(10)
);
```

## 📨 SMTP Config Example (config.properties)

```properties
spring.mail.host=smtp.example.com
spring.mail.port=587
spring.mail.username=your_email@example.com
spring.mail.password=your_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=your_user
spring.datasource.password=your_pass
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
```


## 📝 Logging

Log files are written to the `logs/` directory with SLF4J + Logback:
- `email-batch.log` (rotates after 10MB)
- Keeps up to 5 rotated logs (`email-batch.1.log`, etc.)


## 🖥 Console + File Logging

Logs are sent to both the console and file.  
File logs are saved in `logs/email-batch.log` and rotate after 10MB (5 retained).

### Per-package Log Levels:
- `com.example.emailbatch.RecipientRepository` ➝ **ERROR**
- `com.example.emailbatch.EmailService` ➝ **INFO**


---

## 🐳 Docker Support

### Dockerfile Included

To build the image:

```bash
mvn clean package
docker build -t email-batch .
```

### Run with mounted volumes:

```bash
docker run -v /path/to/config:/config \
           -v /path/to/templates:/templates \
           -v /path/to/logs:/logs \
           email-batch
```

This mounts your external SMTP config, email template, and log directory.
