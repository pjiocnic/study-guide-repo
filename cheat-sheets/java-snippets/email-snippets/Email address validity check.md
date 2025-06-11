# 📘 **Java Email Validation Cookbook (JDK 1.8 Compatible)**

---

## ✅ **1. Simple Email Format Check (Basic Regex)**

A lightweight, readable regex for everyday use.

```java
import java.util.regex.Pattern;

public class SimpleEmailValidator {
    private static final Pattern EMAIL_PATTERN =
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public static boolean isValid(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}
```

✅ **Use when:** You need quick syntax checking (e.g. form input).

---

## ✅ **2. RFC 5322 Compliant Check (Strict Regex)**

A comprehensive regex that matches most valid email formats under RFC 5322.

```java
import java.util.regex.Pattern;

public class StrictEmailValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+" +
        "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|" +
        "\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f!#-[\\]-~]|" +
        "\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@" +
        "(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+" +
        "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[" +
        "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
        "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|" +
        "[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f" +
        "!#-[\\]-~]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
        Pattern.CASE_INSENSITIVE
    );

    public static boolean isValid(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}
```

✅ **Use when:** You want near-complete compliance with RFC 5322, supporting edge cases like quotes, IPs, and symbols.

---

## ✅ **3. Using Apache Commons Validator**

**Dependency (Maven):**
```xml
<dependency>
  <groupId>commons-validator</groupId>
  <artifactId>commons-validator</artifactId>
  <version>1.7</version>
</dependency>
```

**Code:**
```java
import org.apache.commons.validator.routines.EmailValidator;

public class CommonsEmailValidator {
    public static boolean isValid(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public static boolean isValidStrict(String email) {
        return EmailValidator.getInstance(true).isValid(email); // strict TLD check
    }
}
```

✅ **Use when:** You want a mature, well-tested, and reusable validator.

---

## ✅ **4. Using JavaMail (`InternetAddress`)**

**Dependency (Maven):**
```xml
<dependency>
  <groupId>com.sun.mail</groupId>
  <artifactId>javax.mail</artifactId>
  <version>1.6.2</version>
</dependency>
```

**Code:**
```java
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;

public class JavaMailEmailValidator {
    public static boolean isValid(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            return true;
        } catch (AddressException ex) {
            return false;
        }
    }
}
```

✅ **Use when:** You want to validate email syntax using the same logic used by the JavaMail system.

---

## ✅ **5. Libraries & Tools for Domain / DNS / MX Validation**

Basic syntax validation **does not** verify whether the domain **accepts email**. For that, consider:

### 🔍 **DNS/MX Record Lookup (Manual)**

```java
import javax.naming.directory.*;
import javax.naming.*;

public class DNSLookup {
    public static boolean hasMXRecord(String domain) {
        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
            DirContext ctx = new InitialDirContext(env);
            Attributes attrs = ctx.getAttributes(domain, new String[]{"MX"});
            return attrs.get("MX") != null;
        } catch (NamingException e) {
            return false;
        }
    }
}
```

### 📦 **3rd-party Libraries**
| Library          | Purpose                        |
|------------------|--------------------------------|
| **dnsjava**      | MX, TXT, A record lookups      |
| **ZeroBounce**   | Email verification API         |
| **Mailgun API**  | Email verification + spam risk |
| **NeverBounce**  | Bulk verification               |

✅ **Use when:** You need to check if the domain **can receive email**.

---

## 🧪 Test All Together

Here’s how you can combine everything for layered validation:

```java
public class FullEmailValidation {
    public static boolean isValidEmail(String email) {
        return SimpleEmailValidator.isValid(email) &&
               CommonsEmailValidator.isValid(email) &&
               JavaMailEmailValidator.isValid(email);
    }
}
```

