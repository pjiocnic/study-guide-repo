
1. pom.xml

```xml
<!-- HttpClient5 -->
<dependency>
    <groupId>org.apache.httpcomponents.client5</groupId>
    <artifactId>httpclient5</artifactId>
    <version>5.3</version>
</dependency>

<!-- SLF4J API -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.36</version>
</dependency>

<!-- Your logging backend, e.g., Logback -->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.11</version>
</dependency>

<!-- Bridge Apache Commons Logging to SLF4J -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>jcl-over-slf4j</artifactId>
    <version>1.7.36</version>
</dependency>

<dependency>
    <groupId>org.apache.httpcomponents.client5</groupId>
    <artifactId>httpclient5</artifactId>
    <version>5.3</version>
    <exclusions>
        <exclusion>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

2. logback.xml

```xml
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <logger name="org.apache.hc.client5" level="DEBUG" />
    <logger name="org.apache.hc.core5" level="DEBUG" />

    <root level="INFO">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```


```java
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpWrapper {

    public void callRemoteApi(Logger logger, HttpUriRequestBase request) {
        logRequest(logger, request);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            ClassicHttpResponse response = client.executeOpen(null, request, HttpClientContext.create());
            logger.info("Response code: {}", response.getCode());

            if (response.getEntity() != null) {
                String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                logger.info("Response body: {}", responseBody);
            }
        } catch (IOException e) {
            logger.error("API call failed", e);
        }
    }

    private void logRequest(Logger logger, HttpUriRequestBase request) {
        logger.info("HTTP Method: {}", request.getMethod());
        logger.info("Request URI: {}", request.getUri());

        if (request instanceof HttpEntityEnclosingRequestBase) {
            HttpEntity entity = ((HttpEntityEnclosingRequestBase) request).getEntity();
            if (entity != null) {
                try {
                    // Ensure it's repeatable (use ByteArrayEntity or StringEntity)
                    if (entity.isRepeatable()) {
                        String body = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                        logger.info("Request payload: {}", body);
                    } else {
                        logger.warn("Request payload is not repeatable, cannot log body.");
                    }
                } catch (IOException e) {
                    logger.warn("Failed to read request payload for logging", e);
                }
            }
        }
    }
}
```
