Here’s a **complete and clean documentation** on how to set up **SLF4J logging in a Spring Boot project using Logback**, including:

✅ Logging to console
✅ Logging to rotating file
✅ Logging errors to a separate file
✅ Rotation by size
✅ Where to place the config file in your Spring Boot project
✅ Bonus: Log pattern, directory, and per-package log levels

---

# ✅ SLF4J + Logback Logging Setup for Spring Boot

---

## 📁 File to Create

Create the following file in your Spring Boot project:

```
src/main/resources/logback-spring.xml
```

> ☝️ **Note**: Spring Boot will automatically detect and use `logback-spring.xml` during startup if it's placed under `src/main/resources`.

---

## 📄 `logback-spring.xml` — Full Configuration

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

    <!-- Folder for all logs -->
    <property name="LOG_PATH" value="logs" />

    <!-- Standard log format -->
    <property name="LOG_PATTERN" value="%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n" />

    <!-- Console logger -->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>${LOG_PATTERN}</pattern>
        </encoder>
    </appender>

    <!-- Rolling file appender for all logs -->
    <appender name="ROLLING_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${LOG_PATH}/app.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeBasedRollingPolicy">
            <maxFileSize>10MB</maxFileSize>
            <fileNamePattern>${LOG_PATH}/app.%i.log</fileNamePattern>
            <maxHistory>5</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>${LOG_PATTERN}</pattern>
        </encoder>
    </appender>

    <!-- Error log file (captures only ERROR logs) -->
    <appender name="ERROR_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${LOG_PATH}/error.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeBasedRollingPolicy">
            <maxFileSize>5MB</maxFileSize>
            <fileNamePattern>${LOG_PATH}/error.%i.log</fileNamePattern>
            <maxHistory>3</maxHistory>
        </rollingPolicy>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <encoder>
            <pattern>${LOG_PATTERN}</pattern>
        </encoder>
    </appender>

    <!-- Root logger -->
    <root level="INFO">
        <appender-ref ref="CONSOLE" />
        <appender-ref ref="ROLLING_FILE" />
        <appender-ref ref="ERROR_FILE" />
    </root>

    <!-- Optional: Fine-tune logging for packages -->
    <logger name="com.example.myapp.dao" level="ERROR"/>
    <logger name="com.example.myapp.service" level="DEBUG"/>

</configuration>
```

---

## 📂 Folder Structure Overview

```
your-springboot-project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/yourapp/
│   │   │       └── MyService.java
│   │   └── resources/
│   │       └── logback-spring.xml  <-- 🔥 place it here
│   │       └── application.properties
└── pom.xml
```

---

## 📌 What This Setup Gives You

| Feature                            | Behavior                                 |
|------------------------------------|------------------------------------------|
| **Console logging**                | All logs to terminal                     |
| **Rolling log file (`app.log`)**   | 10MB max size, keep 5 backups            |
| **Error log file (`error.log`)**   | 5MB max size, keep 3 backups             |
| **Custom patterns**                | With timestamp, thread, log level, etc.  |
| **Per-package log levels**         | Fine-grained control                     |
| **Auto-detection by Spring Boot**  | No extra config needed in `application.properties` |

---

## 🧪 Test It

In any service or controller:

```java
private static final Logger logger = LoggerFactory.getLogger(MyService.class);

public void testLogs() {
    logger.info("This is an info message");
    logger.error("This is an error message");
}
```

Then run your app and check the `logs/` folder.

---

Let me know if you'd like to:
- Separate logs per day (`TimeBasedRollingPolicy`)
- Output logs in JSON format (e.g., for ELK stack)
- Add a separate thread/request ID to logs for tracing