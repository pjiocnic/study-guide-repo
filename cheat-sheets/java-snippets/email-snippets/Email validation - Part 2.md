## Added on 04/15/2025

In Java, there are several popular libraries that can help you **validate email formats**, ranging from basic regex checks to RFC-compliant validations and domain verification. Here's a rundown of the most widely used options:

---

### ✅ 1. **JavaMail API** (Now Jakarta Mail)
- **Class**: `javax.mail.internet.InternetAddress`
- **How it works**: Parses and optionally validates email format.
- **Limitations**: Doesn't validate the domain (e.g., if the domain actually exists).
- **Example**:
```java
import javax.mail.internet.InternetAddress;

public static boolean isValidEmail(String email) {
    try {
        InternetAddress emailAddr = new InternetAddress(email);
        emailAddr.validate();  // throws exception if invalid
        return true;
    } catch (Exception ex) {
        return false;
    }
}
```

---

### ✅ 2. **Apache Commons Validator**
- **Class**: `org.apache.commons.validator.routines.EmailValidator`
- **RFC compliance**: Optional flag for strict RFC 5322 mode
- **Dependency** (Maven):
```xml
<dependency>
    <groupId>commons-validator</groupId>
    <artifactId>commons-validator</artifactId>
    <version>1.7</version>
</dependency>
```
- **Example**:
```java
import org.apache.commons.validator.routines.EmailValidator;

public static boolean isValidEmail(String email) {
    EmailValidator validator = EmailValidator.getInstance(true); // true for RFC strict
    return validator.isValid(email);
}
```

---

### ✅ 3. **Hibernate Validator (JSR-380 / Jakarta Bean Validation)**
- **Annotation-based** (can be used with `@Email`)
- **Ideal for**: Java beans (DTOs/entities) with annotations
- **Example**:
```java
import javax.validation.constraints.Email;

public class User {
    @Email
    private String email;
}
```

- Add Hibernate Validator to your project:
```xml
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>6.2.5.Final</version>
</dependency>
```

---

### ✅ 4. **Spring Framework (Spring Boot / Spring MVC)**
- Uses Hibernate Validator behind the scenes
- If you're already using Spring, just add `@Email` on fields in DTOs or form objects
- **Example**:
```java
public class ContactForm {
    @Email(message = "Please enter a valid email")
    private String email;
}
```

---

### ✅ 5. **Regular Expression (Manual approach)**
- Not ideal for complex validations (can become error-prone)
- Still useful for basic filters
- **Example**:
```java
public static boolean isValidEmail(String email) {
    return email != null && email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
}
```

---

### 🛠 Comparison Summary

| Library                     | RFC Compliance | Domain Check | Ease of Use | Annotation Support |
|----------------------------|----------------|--------------|--------------|--------------------|
| JavaMail (`InternetAddress`) | Medium         | ❌           | Simple       | ❌                 |
| Apache Commons Validator   | High (opt-in)  | ❌           | Very Easy    | ❌                 |
| Hibernate Validator        | High           | ❌           | Easy         | ✅                 |
| Spring Framework           | High           | ❌           | Very Easy    | ✅                 |
| Manual Regex               | Low–Medium     | ❌           | Simple       | ❌                 |

---

Would you like me to include **domain validation** (e.g. DNS MX record check) as well?

---

Great! Let's expand the study to include **domain validation**, such as checking whether an email domain **actually exists** (e.g., DNS MX record check). This goes beyond format validation and is often used in production apps to reduce bounce rates.

---

## 📡 Part 2: Email **Domain Validation** (DNS / MX Records)

These checks verify if the domain portion of the email (e.g., `gmail.com` in `user@gmail.com`) is:
1. **Resolvable via DNS**
2. **Capable of receiving emails (i.e., has MX records)**

---

### ✅ 1. **Java DNS Lookup using `InetAddress`**
- Basic check: See if domain resolves (not necessarily an email server).
```java
import java.net.InetAddress;

public static boolean isResolvableDomain(String domain) {
    try {
        InetAddress address = InetAddress.getByName(domain);
        return address != null;
    } catch (Exception ex) {
        return false;
    }
}
```

---

### ✅ 2. **MX Record Lookup using `DirContext` (JNDI)**
- This is the correct way to validate if a domain accepts email.

```java
import javax.naming.directory.*;
import javax.naming.NamingException;
import java.util.Hashtable;

public static boolean hasMXRecord(String domain) {
    try {
        Hashtable<String, String> env = new Hashtable<>();
        env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
        DirContext ictx = new InitialDirContext(env);
        Attributes attrs = ictx.getAttributes(domain, new String[]{"MX"});
        Attribute attr = attrs.get("MX");
        return (attr != null);
    } catch (NamingException ex) {
        return false;
    }
}
```

---

### 🧪 Combined Usage Example
```java
public static boolean isEmailDomainValid(String email) {
    try {
        String domain = email.substring(email.indexOf('@') + 1);
        return hasMXRecord(domain);
    } catch (Exception e) {
        return false;
    }
}
```

---

### ✅ 3. **Third-Party Services (Optional for Production Use)**
For enterprise-grade checks (SMTP ping, mailbox detection, blacklists), consider APIs:

| Service               | Features                                      | Free Tier |
|-----------------------|-----------------------------------------------|-----------|
| ZeroBounce            | SMTP ping, spam trap detection, etc.          | Yes       |
| NeverBounce           | Real-time verification + bulk validation      | Yes       |
| Hunter.io             | MX + SMTP check + domain scoring              | Yes       |

---

## ✅ Full Email Validation Pipeline (Format + Domain)

| Stage             | Method                                               | Library/Approach          |
|------------------|------------------------------------------------------|---------------------------|
| Format Validity  | Regex / `InternetAddress` / `EmailValidator`         | JavaMail, Commons Validator |
| RFC Compliance   | `EmailValidator.getInstance(true)`                   | Apache Commons Validator  |
| Domain Check     | DNS / MX record lookup                               | JNDI `DirContext`         |
| Mailbox Check    | (optional) SMTP verification (via 3rd-party API)     | ZeroBounce, Hunter, etc.  |

---

Would you like a ready-to-use **Java utility class** that wraps all of these together?

---

Perfect! Here's a **ready-to-use Java utility class** named `EmailValidatorUtil` that combines:

- ✅ RFC-compliant format validation (via **Apache Commons Validator**)
- ✅ DNS MX record check (via **JNDI lookup**)
- ✅ Optional fallback to **JavaMail** validation

---

### 📦 Maven Dependencies (Add to `pom.xml`)

```xml
<!-- Apache Commons Validator -->
<dependency>
    <groupId>commons-validator</groupId>
    <artifactId>commons-validator</artifactId>
    <version>1.7</version>
</dependency>

<!-- JavaMail (Jakarta Mail) for optional fallback validation -->
<dependency>
    <groupId>com.sun.mail</groupId>
    <artifactId>jakarta.mail</artifactId>
    <version>2.0.1</version>
</dependency>
```

---

### ✅ `EmailValidatorUtil.java`

```java
import org.apache.commons.validator.routines.EmailValidator;

import javax.mail.internet.InternetAddress;
import javax.naming.directory.*;
import javax.naming.NamingException;
import java.net.InetAddress;
import java.util.Hashtable;

public class EmailValidatorUtil {

    /**
     * Validates email using Apache Commons Validator (RFC 5322 strict mode)
     */
    public static boolean isFormatValid(String email) {
        return EmailValidator.getInstance(true).isValid(email);
    }

    /**
     * Fallback format validation using JavaMail's InternetAddress
     */
    public static boolean isFormatValidFallback(String email) {
        try {
            InternetAddress addr = new InternetAddress(email);
            addr.validate(); // throws AddressException if invalid
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Checks if domain resolves to any IP
     */
    public static boolean isDomainResolvable(String domain) {
        try {
            InetAddress.getByName(domain);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if the domain has MX records (can receive email)
     */
    public static boolean hasMXRecord(String domain) {
        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
            DirContext dirContext = new InitialDirContext(env);
            Attributes attrs = dirContext.getAttributes(domain, new String[]{"MX"});
            Attribute attr = attrs.get("MX");
            return attr != null && attr.size() > 0;
        } catch (NamingException ex) {
            return false;
        }
    }

    /**
     * Extracts domain from email address
     */
    public static String extractDomain(String email) {
        int atIndex = email.lastIndexOf("@");
        return (atIndex > 0) ? email.substring(atIndex + 1) : "";
    }

    /**
     * Composite check: format + resolvable domain + MX check
     */
    public static boolean isEmailValid(String email) {
        if (!isFormatValid(email)) return false;

        String domain = extractDomain(email);
        if (domain.isEmpty()) return false;

        return isDomainResolvable(domain) && hasMXRecord(domain);
    }
}
```

---

### 🚀 Usage Example

```java
public class Main {
    public static void main(String[] args) {
        String email = "example@gmail.com";

        if (EmailValidatorUtil.isEmailValid(email)) {
            System.out.println(email + " is valid!");
        } else {
            System.out.println(email + " is invalid!");
        }
    }
}
```

---

### ✅ What This Utility Does:

| Check | Method Used | Result |
|-------|-------------|--------|
| Format (RFC) | Apache Commons Validator | ✔️ |
| Domain Extraction | `@` split | ✔️ |
| DNS Resolution | `InetAddress.getByName()` | ✔️ |
| MX Record Lookup | `DirContext.getAttributes()` | ✔️ |
| Optional fallback | JavaMail `InternetAddress` | ✔️ |

---

Would you like a version that also **logs invalid emails** and **writes them to a file or DB** for cleansing workflows?

---

Absolutely! Here’s how you can integrate and use `EmailValidatorUtil` in a **Spring Boot 3.x** application — especially helpful if you're validating user input from forms, REST APIs, or batch jobs.

---

## 🧩 1. **Spring Boot 3.x Project Structure**

```
src/
└── main/
    ├── java/
    │   └── com/example/emailvalidator/
    │       ├── EmailValidatorUtil.java     ✅ Utility class
    │       ├── EmailValidationService.java ✅ Service to encapsulate logic
    │       ├── EmailRequest.java           ✅ DTO with validation
    │       └── EmailController.java        ✅ REST controller
    └── resources/
        └── application.properties
```

---

## 📦 2. **Add Dependencies in `pom.xml`**

```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Apache Commons Validator -->
    <dependency>
        <groupId>commons-validator</groupId>
        <artifactId>commons-validator</artifactId>
        <version>1.7</version>
    </dependency>

    <!-- JavaMail / Jakarta Mail for fallback -->
    <dependency>
        <groupId>com.sun.mail</groupId>
        <artifactId>jakarta.mail</artifactId>
        <version>2.0.1</version>
    </dependency>
</dependencies>
```

---

## ✅ 3. `EmailValidatorUtil.java`

Use the exact utility class we discussed earlier [📍 See previous message].

Place it inside `com.example.emailvalidator`.

---

## 🎯 4. `EmailRequest.java` – DTO for API input

```java
package com.example.emailvalidator;

public class EmailRequest {
    private String email;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
```

---

## 🧠 5. `EmailValidationService.java` – Service Layer

```java
package com.example.emailvalidator;

import org.springframework.stereotype.Service;

@Service
public class EmailValidationService {

    public boolean validate(String email) {
        return EmailValidatorUtil.isEmailValid(email);
    }
}
```

---

## 🌐 6. `EmailController.java` – REST API Endpoint

```java
package com.example.emailvalidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailValidationService emailValidationService;

    @PostMapping("/validate-email")
    public String validateEmail(@RequestBody EmailRequest request) {
        boolean valid = emailValidationService.validate(request.getEmail());
        return valid ? "✅ Valid email" : "❌ Invalid email";
    }
}
```

---

## 🚀 7. Run the Application

Start the Spring Boot app and test with:

```bash
curl -X POST http://localhost:8080/api/validate-email \
     -H "Content-Type: application/json" \
     -d '{"email": "example@gmail.com"}'
```

Expected Output:
```
✅ Valid email
```

---

## 📂 8. Bonus: Enable Logging for Invalid Emails (Optional)

You can modify `EmailValidationService` to log and persist invalid emails if needed:

```java
if (!EmailValidatorUtil.isEmailValid(email)) {
    logger.warn("Invalid email: " + email);
    // Optionally save to DB or write to file
}
```

---

Would you like me to package this as a ZIP project template with Maven `pom.xml` + folders for you to download and run?