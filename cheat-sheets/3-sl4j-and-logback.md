
1. pom.xml

```xml
<!-- SLF4J API -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.32</version> <!-- Use the latest version available -->
</dependency>

<!-- Logback Core and Classic -->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-core</artifactId>
    <version>1.2.6</version> <!-- Use the latest version available -->
</dependency>
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.6</version> <!-- Use the latest version available -->
</dependency>

```

2. logback.xml

```
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="STDOUT"/>
    </root>
</configuration>

```

3. java

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleApp {
    private static final Logger logger = LoggerFactory.getLogger(SampleApp.class);

    public static void main(String[] args) {
        logger.info("This is an info message.");
        logger.error("This is an error message.");
    }
}

```