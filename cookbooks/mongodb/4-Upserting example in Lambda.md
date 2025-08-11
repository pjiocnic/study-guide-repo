Got it—here’s a clean, production-ready pattern for your Lambda (Java 17) that:

* reads SQS messages shaped like `Id1,Value1`
* `GET` #1 with **Id1** → deserialize into your **custom class** (no changes needed)
* extract **Id2** from that POJO
* `GET` #2 with **Id2** → parse as `JsonNode`
* inject new field with **Value1**
* **upsert** into MongoDB

I’m using JDK 17’s built-in `HttpClient`, Jackson’s tree model for the 2nd JSON, and the official MongoDB Java driver. Everything is Lambda/SQS friendly (connection reuse, batch handling, defensive error handling).

---

# Minimal Maven deps

```xml
<dependencies>
  <!-- AWS Lambda -->
  <dependency>
    <groupId>com.amazonaws</groupId>
    <artifactId>aws-lambda-java-core</artifactId>
    <version>1.2.3</version>
  </dependency>
  <dependency>
    <groupId>com.amazonaws</groupId>
    <artifactId>aws-lambda-java-events</artifactId>
    <version>3.11.6</version>
  </dependency>

  <!-- Jackson -->
  <dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.17.2</version>
  </dependency>

  <!-- MongoDB -->
  <dependency>
    <groupId>org.mongodb</groupId>
    <artifactId>mongodb-driver-sync</artifactId>
    <version>5.1.0</version>
  </dependency>

  <!-- (Optional) SLF4J API + your runtime binding -->
  <dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>2.0.13</version>
  </dependency>
</dependencies>
```

---

# Environment variables (configure in Lambda)

* `URL1_TEMPLATE` e.g. `https://api.example.com/v1/orders?id={id1}`
* `URL2_TEMPLATE` e.g. `https://api.example.com/v1/details/{id2}`
* `MONGODB_URI` e.g. `mongodb+srv://user:pass@cluster.mongodb.net/?retryWrites=true&w=majority`
* `MONGO_DB` e.g. `ingestdb`
* `MONGO_COLLECTION` e.g. `orders`
* `UPSERT_KEY_FIELD` (the unique selector for upsert, likely `"id2"` or a field found in the #2 JSON)
* `INJECT_FIELD_NAME` (default to `injectedField` if not set)

> Make sure you’ve created a **unique index** on `UPSERT_KEY_FIELD` in that collection if it’s not `_id`.

---

# Lambda handler (SQS → HTTP → Jackson → Mongo upsert)

```java
package com.example.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.UpdateOptions;

public class SqsJsonUpsertHandler implements RequestHandler<SQSEvent, Void> {

    // Reuse expensive resources across invocations (Lambda container reuse)
    private static final HttpClient HTTP = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String URL1_TEMPLATE      = getenvOrThrow("URL1_TEMPLATE");
    private static final String URL2_TEMPLATE      = getenvOrThrow("URL2_TEMPLATE");
    private static final String MONGODB_URI        = getenvOrThrow("MONGODB_URI");
    private static final String MONGO_DB           = getenvOrThrow("MONGO_DB");
    private static final String MONGO_COLLECTION   = getenvOrThrow("MONGO_COLLECTION");
    private static final String UPSERT_KEY_FIELD   = getenvOrDefault("UPSERT_KEY_FIELD", "id2");
    private static final String INJECT_FIELD_NAME  = getenvOrDefault("INJECT_FIELD_NAME", "injectedField");

    // Mongo client & collection cached
    private static final MongoClient MONGO = MongoClients.create(MONGODB_URI);
    private static final MongoDatabase DB = MONGO.getDatabase(MONGO_DB);
    private static final MongoCollection<Document> COLL = DB.getCollection(MONGO_COLLECTION);

    // Replace with your real custom POJO type used for the first response
    // Ensure it has a getter to extract Id2 (e.g., getId2())
    public static class FirstApiPojo {
        // fields match your JSON from URL1
        private String id2;  // or nested path; adapt as needed

        public String getId2() { return id2; }
        public void setId2(String id2) { this.id2 = id2; }
    }

    @Override
    public Void handleRequest(SQSEvent event, Context context) {
        // Each SQS record: "Id1,Value1"
        for (SQSEvent.SQSMessage msg : event.getRecords()) {
            try {
                final String body = msg.getBody().trim();
                final String[] parts = body.split(",", 2);
                if (parts.length < 2) {
                    log(context, "Bad message (expected 'Id1,Value1'): " + body);
                    // Let it fail to trigger DLQ/retry, or swallow based on your policy:
                    continue;
                }
                final String id1 = parts[0].trim();
                final String value1 = parts[1].trim();

                // 1) GET #1 using Id1
                final String url1 = URL1_TEMPLATE.replace("{id1}", urlEncode(id1));
                final String resp1 = httpGet(url1);

                // 2) Deserialize into custom class (no change to your classes)
                final FirstApiPojo pojo = MAPPER.readValue(resp1, FirstApiPojo.class);

                // 3) Extract Id2
                final String id2 = pojo.getId2();
                if (id2 == null || id2.isBlank()) {
                    throw new IllegalStateException("Id2 missing/blank for Id1=" + id1);
                }

                // 4) GET #2 using Id2
                final String url2 = URL2_TEMPLATE.replace("{id2}", urlEncode(id2));
                final String resp2 = httpGet(url2);

                // 5) Inject new field with Value1
                JsonNode root = MAPPER.readTree(resp2);
                if (!(root instanceof ObjectNode)) {
                    throw new IllegalStateException("Second response root is not an object for Id2=" + id2);
                }
                ObjectNode obj = (ObjectNode) root;
                obj.put(INJECT_FIELD_NAME, value1);

                // (Optionally ensure the upsert key is present in the final doc)
                // If UPSERT_KEY_FIELD is not already present, set it from id2:
                if (!obj.has(UPSERT_KEY_FIELD)) {
                    obj.put(UPSERT_KEY_FIELD, id2);
                }

                // 6) Upsert into Mongo
                Document toUpsert = Document.parse(MAPPER.writeValueAsString(obj));
                COLL.updateOne(
                        eq(UPSERT_KEY_FIELD, obj.get(UPSERT_KEY_FIELD).asText()),
                        new Document("$set", toUpsert),
                        new UpdateOptions().upsert(true)
                );

                log(context, "Upsert OK for Id1=" + id1 + ", Id2=" + id2);
            } catch (Exception e) {
                // Let Lambda/SQS retry semantics handle transient failures
                log(context, "ERROR processing messageId=" + msg.getMessageId() + " : " + e.getMessage());
                throw new RuntimeException(e); // surface error to trigger retry/DLQ
            }
        }
        return null;
    }

    private static String httpGet(String url) throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<String> res = HTTP.send(req, HttpResponse.BodyHandlers.ofString());
        int code = res.statusCode();
        if (code < 200 || code >= 300) {
            throw new IllegalStateException("HTTP " + code + " for " + url + " body=" + safeSnippet(res.body()));
        }
        return res.body();
    }

    private static String urlEncode(String s) {
        return java.net.URLEncoder.encode(s, java.nio.charset.StandardCharsets.UTF_8);
    }

    private static String safeSnippet(String s) {
        if (s == null) return "";
        return s.length() <= 300 ? s : s.substring(0, 300) + "...";
    }

    private static void log(Context ctx, String msg) {
        if (ctx != null && ctx.getLogger() != null) {
            ctx.getLogger().log(msg + "\n");
        } else {
            System.out.println(msg);
        }
    }

    private static String getenvOrThrow(String k) {
        String v = System.getenv(k);
        if (v == null || v.isBlank()) throw new IllegalStateException("Missing env var: " + k);
        return v;
    }

    private static String getenvOrDefault(String k, String def) {
        String v = System.getenv(k);
        return (v == null || v.isBlank()) ? def : v;
    }
}
```

---

## Notes & good practices

* **Idempotency:** Upsert by a **stable unique key** (e.g., `id2`). Ensure a unique index exists on `UPSERT_KEY_FIELD`.
* **POJO for step #1:** Replace `FirstApiPojo` with your real custom class; adjust `getId2()` (e.g., if it’s nested, either add Jackson annotations or map the path into this wrapper POJO).
* **Timeouts/retries:** SQS + Lambda will retry on thrown exceptions. Keep HTTP timeouts tight, and fail fast for transient issues.
* **Cold start & connections:** The static `MongoClient` and `HttpClient` are reused across invocations, minimizing latency.
* **Security:** Store secrets (Mongo URI) in **Secrets Manager** + environment variables, or use an IAM role with AWS PrivateLink/peering where applicable.

---

If you want, I can also:

* switch the Mongo upsert to use `_id = id2` (simplifies indexing),
* add **structured logging** and a **“dry run”** flag,
* provide a **SAM/Serverless** template and example **SQS event JSON** for local testing.
