# jdbc-oracle-fillb-cookbook

A **cookbook template** Spring Boot app (Java 8, Boot 2.7.x, JdbcTemplate) that:

1. Selects IDs from Oracle **table `A`, column `A`** where `B` is `NULL` (batched).
2. Calls an HTTP REST API per ID.
3. Extracts an ID from the JSON response (configurable JSONPath).
4. Updates **column `B`** in the same row.

## Quick start

```bash
# Java 8 + Maven required
mvn -v
java -version

# Build & run
mvn clean package
java -jar target/jdbc-oracle-fillb-cookbook-1.0.0.jar
```

Configure database and API in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@//HOST:PORT/SERVICE
spring.datasource.username=YOUR_USER
spring.datasource.password=YOUR_PASSWORD

app.remote.url=https://api.example.com/resource/{id}
app.remote.jsonPath=$.data.id
app.batch-size=100
app.use-skip-locked=true
```

### Schema (example)
See [`sql/example_schema.sql`](sql/example_schema.sql). If your table already exists, just ensure the column names align.

### Triggering a run
- The app runs **one batch on startup** via `CommandLineRunner`.
- Optionally POST to `http://localhost:8080/batch/run` to trigger on demand (returns a JSON array of results).

### Parallel workers
Leave `app.use-skip-locked=true` and start multiple instances; each instance will lock a different slice:

```sql
SELECT A FROM A WHERE B IS NULL AND ROWNUM <= ? FOR UPDATE SKIP LOCKED;
```

### Change HTTP method / headers
Edit `FillBService.fetch(...)` to use `RestTemplate.exchange(...)` with headers and/or a POST body.

### Java 8 & Boot 2.7
This template targets **JDK 1.8** for legacy environments. Upgradeable to newer LTS versions if desired.

## Project layout

```
src/
  main/java/cookbook/oracle/fillb/
    OracleFillBApplication.java
    config/AppConfig.java
    run/BatchRunner.java
    service/FillBService.java
    web/BatchController.java
  main/resources/application.properties
sql/
  example_schema.sql
pom.xml
```

## License
MIT (adapt as you like).
