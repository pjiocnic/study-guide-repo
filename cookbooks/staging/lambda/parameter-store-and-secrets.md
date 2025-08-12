Here’s a clean, production-ready way to fetch from **SSM Parameter Store** and **Secrets Manager** in a Java AWS Lambda using the **AWS SDK v2**. It includes minimal caching to avoid hammering the services on warm invocations.

---

# pom.xml (dependencies + shade plugin)

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.example</groupId>
  <artifactId>lambda-config</artifactId>
  <version>0.0.1</version>
  <properties>
    <maven.compiler.release>17</maven.compiler.release>
    <aws.sdk.version>2.25.40</aws.sdk.version>
  </properties>

  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>software.amazon.awssdk</groupId>
        <artifactId>bom</artifactId>
        <version>${aws.sdk.version}</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>

  <dependencies>
    <!-- Lambda runtime -->
    <dependency>
      <groupId>com.amazonaws</groupId>
      <artifactId>aws-lambda-java-core</artifactId>
      <version>1.2.3</version>
    </dependency>
    <dependency>
      <groupId>com.amazonaws</groupId>
      <artifactId>aws-lambda-java-events</artifactId>
      <version>3.11.5</version>
      <optional>true</optional>
    </dependency>

    <!-- AWS SDK v2 clients -->
    <dependency>
      <groupId>software.amazon.awssdk</groupId>
      <artifactId>ssm</artifactId>
    </dependency>
    <dependency>
      <groupId>software.amazon.awssdk</groupId>
      <artifactId>secretsmanager</artifactId>
    </dependency>

    <!-- (Optional) lightweight logging to stdout -->
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>2.0.13</version>
    </dependency>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-simple</artifactId>
      <version>2.0.13</version>
      <scope>runtime</scope>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <!-- Build a single uber-jar for Lambda -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>3.5.0</version>
        <executions>
          <execution>
            <phase>package</phase>
            <goals><goal>shade</goal></goals>
            <configuration>
              <createDependencyReducedPom>false</createDependencyReducedPom>
              <filters>
                <filter>
                  <artifact>*:*</artifact>
                  <excludes>
                    <exclude>META-INF/*.SF</exclude>
                    <exclude>META-INF/*.DSA</exclude>
                    <exclude>META-INF/*.RSA</exclude>
                  </excludes>
                </filter>
              </filters>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
</project>
```

---

# Parameter Store service (SDK v2, with decryption + TTL cache)

```java
package com.example.lambda.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.*;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ParameterStoreService {
  private static final Logger log = LoggerFactory.getLogger(ParameterStoreService.class);

  private final SsmClient ssm;
  private final long ttlMillis;
  private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();

  public ParameterStoreService(Region region, Duration ttl) {
    this.ssm = SsmClient.builder().region(region).build();
    this.ttlMillis = ttl.toMillis();
  }

  public String getParameter(String name, boolean decrypt) {
    CacheEntry ce = cache.get(name);
    if (ce != null && !ce.isExpired()) return ce.value;

    GetParameterResponse resp = ssm.getParameter(GetParameterRequest.builder()
        .name(name).withDecryption(decrypt).build());
    String val = resp.parameter().value();
    cache.put(name, CacheEntry.of(val, ttlMillis));
    return val;
  }

  /** Fetch all parameters under a path (optionally recursive). Returns name->value. */
  public Map<String, String> getParametersByPath(String path, boolean recursive, boolean decrypt) {
    Map<String, String> out = new LinkedHashMap<>();
    String token = null;
    do {
      GetParametersByPathResponse resp = ssm.getParametersByPath(GetParametersByPathRequest.builder()
          .path(path)
          .recursive(recursive)
          .withDecryption(decrypt)
          .nextToken(token)
          .build());
      for (Parameter p : resp.parameters()) {
        out.put(p.name(), p.value());
        cache.put(p.name(), CacheEntry.of(p.value(), ttlMillis));
      }
      token = resp.nextToken();
    } while (token != null && !token.isEmpty());
    return out;
  }

  private record CacheEntry(String value, long expiresAt) {
    static CacheEntry of(String value, long ttlMillis) {
      return new CacheEntry(value, System.currentTimeMillis() + ttlMillis);
    }
    boolean isExpired() { return System.currentTimeMillis() > expiresAt; }
  }
}
```

---

# Secrets Manager service (SDK v2, with TTL cache + JSON helper)

```java
package com.example.lambda.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/** Simple string/JSON secret fetcher with in-memory TTL cache. */
public class SecretsManagerService {
  private static final Logger log = LoggerFactory.getLogger(SecretsManagerService.class);

  private final SecretsManagerClient sm;
  private final long ttlMillis;
  private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();

  public SecretsManagerService(Region region, Duration ttl) {
    this.sm = SecretsManagerClient.builder().region(region).build();
    this.ttlMillis = ttl.toMillis();
  }

  /** Returns the secret string as-is (typical for JSON payloads). */
  public String getSecretString(String secretId) {
    CacheEntry ce = cache.get(secretId);
    if (ce != null && !ce.isExpired()) return ce.value();

    GetSecretValueResponse resp = sm.getSecretValue(GetSecretValueRequest.builder()
        .secretId(secretId).build());
    String val = resp.secretString();
    cache.put(secretId, CacheEntry.of(val, ttlMillis));
    return val;
  }

  /** Convenience: parse a flat JSON secret into a Map<String,String>. */
  public Map<String, String> getSecretJson(String secretId) {
    String json = getSecretString(secretId);
    // Very small dependency footprint: naive JSON parse if you don’t want a full JSON lib.
    // For real projects, prefer Jackson/Gson to handle nested structures safely.
    return JsonMini.parseFlatObject(json);
  }

  private record CacheEntry(String value, long expiresAt) {
    static CacheEntry of(String value, long ttlMillis) {
      return new CacheEntry(value, System.currentTimeMillis() + ttlMillis);
    }
    boolean isExpired() { return System.currentTimeMillis() > expiresAt; }
  }
}
```

A tiny helper for parsing simple flat JSON (optional—swap in Jackson if you already use it):

```java
package com.example.lambda.config;

import java.util.HashMap;
import java.util.Map;

/** Minimal JSON parser for {"k":"v","x":"y"} (no nesting/escapes). Use Jackson for complex secrets. */
class JsonMini {
  static Map<String,String> parseFlatObject(String json) {
    Map<String,String> out = new HashMap<>();
    if (json == null) return out;
    String s = json.trim();
    if (s.startsWith("{")) s = s.substring(1);
    if (s.endsWith("}")) s = s.substring(0, s.length()-1);
    if (s.isEmpty()) return out;
    for (String part : s.split(",")) {
      String[] kv = part.split(":", 2);
      if (kv.length == 2) {
        String k = strip(kv[0]);
        String v = strip(kv[1]);
        out.put(k, v);
      }
    }
    return out;
  }
  private static String strip(String s) {
    s = s.trim();
    if (s.startsWith("\"") && s.endsWith("\"") && s.length() >= 2) {
      s = s.substring(1, s.length()-1);
    }
    return s;
  }
}
```

---

# Lambda handler example (uses cold-start singletons + TTL caching)

```java
package com.example.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.lambda.config.ParameterStoreService;
import com.example.lambda.config.SecretsManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;

import java.time.Duration;
import java.util.Map;

/** Example handler: reads SSM param + a JSON secret, returns a small summary. */
public class Handler implements RequestHandler<Map<String,Object>, Map<String,String>> {
  private static final Logger log = LoggerFactory.getLogger(Handler.class);

  // Cold-start singletons (auto-pick region from AWS_REGION)
  private static final Region REGION = Region.of(System.getenv("AWS_REGION"));
  private static final ParameterStoreService PARAMS = new ParameterStoreService(REGION, Duration.ofMinutes(5));
  private static final SecretsManagerService SECRETS = new SecretsManagerService(REGION, Duration.ofMinutes(5));

  // Configure names via Lambda environment variables
  private static final String PARAM_DB_URL = getenvOr("PARAM_DB_URL", "/myapp/prod/db/url");
  private static final String SECRET_DB = getenvOr("SECRET_DB", "myapp/prod/dbCredentials");

  @Override
  public Map<String, String> handleRequest(Map<String, Object> input, Context context) {
    // SSM: SecureString -> withDecryption(true)
    String dbUrl = PARAMS.getParameter(PARAM_DB_URL, true);

    // Secrets Manager: typically a JSON like {"username":"...","password":"..."}
    Map<String, String> creds = SECRETS.getSecretJson(SECRET_DB);

    log.info("Fetched config. URL len={}, user={}", dbUrl.length(), creds.get("username"));
    return Map.of(
        "dbUrl", dbUrl,
        "dbUser", creds.getOrDefault("username", "unknown"),
        "dbPassLen", String.valueOf(creds.getOrDefault("password", "").length())
    );
  }

  private static String getenvOr(String k, String def) {
    String v = System.getenv(k);
    return (v == null || v.isBlank()) ? def : v;
  }
}
```

**How to deploy:** Set handler to `com.example.lambda.Handler::handleRequest`. Package with `mvn -q -DskipTests package` and upload the shaded JAR.

---

## Minimum IAM permissions for the Lambda role

Add these to the Lambda execution role (scope ARNs to your actual names):

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "ReadParameterStore",
      "Effect": "Allow",
      "Action": [
        "ssm:GetParameter",
        "ssm:GetParameters",
        "ssm:GetParametersByPath"
      ],
      "Resource": [
        "arn:aws:ssm:us-east-1:123456789012:parameter/myapp/prod/*"
      ]
    },
    {
      "Sid": "ReadSecrets",
      "Effect": "Allow",
      "Action": [
        "secretsmanager:GetSecretValue"
      ],
      "Resource": [
        "arn:aws:secretsmanager:us-east-1:123456789012:secret:myapp/prod/*"
      ]
    }
  ]
}
```

> If your **SSM SecureString** uses a **customer KMS key**, include `kms:Decrypt` on that key.
> If your Lambda runs **in a VPC without internet**, add VPC endpoints for `com.amazonaws.<region>.ssm` and `com.amazonaws.<region>.secretsmanager` (and KMS if needed).

---

### Usage notes

* Put stable, low-churn config in **SSM Parameter Store** (use `SecureString` for sensitive values).
* Put **credentials/rotating secrets** in **Secrets Manager**.
* The provided TTL caches (5 minutes by default) reduce latency and costs on warm invocations. Tune TTL as you like.

Want a ZIP skeleton for this Lambda (pom + classes + sample SAM/TF)? I can package it up for you.
