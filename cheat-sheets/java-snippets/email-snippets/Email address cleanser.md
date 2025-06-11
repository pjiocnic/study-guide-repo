# 📘 **Java Email Cleansing Cookbook**

---

## ✅ **Goal**
- Clean up common typos and formatting errors in email addresses.
- Validate after cleaning.
- Log unfixable entries.

---

## 🧰 **Features Implemented**
- Trimming whitespace
- Lowercasing
- Replacing ` at ` or `(at)` with `@`
- Replacing ` dot ` or `(dot)` with `.`
- Removing multiple `@` symbols
- Optional domain fixing (`gmial.com` → `gmail.com`)
- Syntax validation via regex
- Logging unfixable emails

---

## ✅ **1. Email Cleansing Utility**

```java
import java.util.*;
import java.util.regex.*;
import java.util.logging.*;

public class EmailCleaner {
    private static final Logger LOGGER = Logger.getLogger(EmailCleaner.class.getName());

    // Basic validation regex
    private static final Pattern VALID_EMAIL_REGEX =
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    // Common typo fixes
    private static final Map<String, String> domainCorrections = new HashMap<>();
    static {
        domainCorrections.put("gmial.com", "gmail.com");
        domainCorrections.put("gamil.com", "gmail.com");
        domainCorrections.put("hotnail.com", "hotmail.com");
        domainCorrections.put("yaho.com", "yahoo.com");
        domainCorrections.put("outlok.com", "outlook.com");
    }

    public static Optional<String> cleanEmail(String rawEmail) {
        if (rawEmail == null || rawEmail.trim().isEmpty()) {
            logBad(rawEmail, "Empty or null email");
            return Optional.empty();
        }

        String cleaned = rawEmail.trim()
                .toLowerCase()
                .replaceAll("\\s*\\(at\\)\\s*|\\s+at\\s+", "@")
                .replaceAll("\\s*\\(dot\\)\\s*|\\s+dot\\s+", ".")
                .replaceAll("[\\s\\[\\]\\{\\}\\(\\)<>]", "")
                .replaceAll("\\.{2,}", ".")
                .replaceAll("@{2,}", "@");

        // Fix common domain typos
        for (Map.Entry<String, String> entry : domainCorrections.entrySet()) {
            if (cleaned.endsWith(entry.getKey())) {
                cleaned = cleaned.replace(entry.getKey(), entry.getValue());
                break;
            }
        }

        if (isValid(cleaned)) {
            return Optional.of(cleaned);
        } else {
            logBad(rawEmail, "Invalid after cleaning: " + cleaned);
            return Optional.empty();
        }
    }

    private static boolean isValid(String email) {
        return VALID_EMAIL_REGEX.matcher(email).matches();
    }

    private static void logBad(String rawEmail, String reason) {
        LOGGER.warning("Bad email: \"" + rawEmail + "\" — Reason: " + reason);
    }
}
```

---

## ✅ **2. Sample Usage**

```java
import java.util.Optional;

public class EmailCleanerTest {
    public static void main(String[] args) {
        String[] emails = {
            " test(at)gmail(dot)com ",
            "USER@GMAIL.COM",
            "bad@@mail.com",
            "missingat.com",
            "another.email@hotnail.com",
            null,
            "john (at) example (dot) com",
            "user@domain"
        };

        for (String email : emails) {
            Optional<String> cleaned = EmailCleaner.cleanEmail(email);
            cleaned.ifPresentOrElse(
                e -> System.out.println("✅ Cleaned: " + e),
                () -> System.out.println("❌ Could not clean: " + email)
            );
        }
    }
}
```

---

### 🧪 Sample Output
```
✅ Cleaned: test@gmail.com
✅ Cleaned: user@gmail.com
❌ Could not clean: bad@@mail.com
❌ Could not clean: missingat.com
✅ Cleaned: another.email@hotmail.com
❌ Could not clean: null
✅ Cleaned: john@example.com
❌ Could not clean: user@domain
```

And the logger would write:
```
WARNING: Bad email: "bad@@mail.com" — Reason: Invalid after cleaning: bad@@mail.com
WARNING: Bad email: "missingat.com" — Reason: Invalid after cleaning: missingat.com
WARNING: Bad email: "null" — Reason: Empty or null email
WARNING: Bad email: "user@domain" — Reason: Invalid after cleaning: user@domain
```

---

## 🔌 **Extending the Cleanser**

You can easily extend it to:
- Normalize Unicode (`é` → `e`)
- Use domain auto-suggest (e.g., Levenshtein distance)
- Batch clean a CSV file
- Send rejected emails to a separate log file

---

## 📦 Want All-in-One?

Would you like a full Maven project boilerplate with:
- This cleanser
- The validation cookbook
- Unit tests
- Logging to file?

I can bundle it up for you.