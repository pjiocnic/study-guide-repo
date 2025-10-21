# Oracle → Mongo → JSON (gzip) → S3 (Spring Boot 3, Java 17)

A production-ready template that:
1. **SELECTs from Oracle**
2. **Queries MongoDB** using a key from each Oracle row
3. **Creates JSON** via pluggable business logic
4. **GZIPs** the output
5. **Uploads** chunked files to **Amazon S3**

All behavior is controlled by **external `application.properties`** — including **rows per file** (100–5000), output format (JSONL vs JSON array), S3 bucket/prefix, SQL, and key fields.

---

## Quick Start

### 0) Requirements
- Java 17+
- Maven 3.9+
- Network access to Oracle, MongoDB, and S3
- AWS credentials (via environment, ~/.aws profile, or explicit keys in properties)

### 1) Build
```bash
mvn -q -DskipTests package
```

### 2) Configure (external file recommended)
Copy the sample from `src/main/resources/application.properties` and edit values.
To keep it outside the JAR:
```bash
cp src/main/resources/application.properties ./application.properties
# edit ./application.properties
```

### 3) Run
```bash
java -jar target/oracle-mongo-s3-uploader-0.0.1-SNAPSHOT.jar \
  --spring.config.location=./application.properties
```

---

## Configuration Cheat Sheet (`application.properties`)

### Oracle (JDBC)
```properties
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.datasource.url=jdbc:oracle:thin:@//HOST:PORT/SERVICE
spring.datasource.username=ORACLE_USER
spring.datasource.password=ORACLE_PASSWORD
```

### MongoDB
```properties
spring.data.mongodb.uri=mongodb://USER:PASS@HOST:27017/DBNAME?authSource=admin
```

### AWS / S3
```properties
app.s3.region=us-east-1
app.s3.bucket=your-bucket-name
# OPTIONAL: if not using env/instance/IAM:
# app.s3.access-key-id=...
# app.s3.secret-access-key=...
```

### Job Query + Keys
```properties
# Your SELECT must include the key column named below
app.job.oracle-sql=SELECT ID, NAME, UPDATED_AT, KEY_FIELD FROM MY_TABLE
app.keys.oracle-key-column=KEY_FIELD
app.keys.mongo-collection=my_collection
app.keys.mongo-key-field=_id
```

### Output Controls
```properties
# 100–5000
app.output.rows-per-file=1000
# jsonl or jsonarray
app.output.format=jsonl
# S3 key prefix (folder path inside the bucket)
app.output.s3-key-prefix=exports/example/
# Filename pattern: %s = timestamp, %d = part no, %s = ext (jsonl/json)
app.output.file-name-pattern=export_%s_part%05d.%s.gz
```

---

## How It Works

- `OracleRepository.streamRows(...)` streams JDBC rows (no huge memory spikes).
- For each Oracle row:
  - `MongoLookupService.findByKey(...)` queries Mongo by your configured key.
  - `BusinessLogicService.merge(...)` merges both records into a single `Map`.
- `FileChunkUploader` writes to **GZIP** in either:
  - **JSONL** (`.jsonl.gz`): one JSON object per line, or
  - **JSON array** (`.json.gz`)
- After **N** rows (configurable), the chunk is **uploaded to S3** with the proper `Content-Encoding: gzip`.

---

## Custom Business Logic

Replace `BusinessLogicService.merge(...)` with your logic. By default, it:
- Copies all Oracle columns
- Adds Mongo fields with a `mongo_` prefix
- Adds `_merged_at` timestamp

---

## Tips

- Prefer **environment credentials** or **IAM roles** for S3. Only use static keys when necessary.
- For huge tables, consider adding filters or splitting by time/id ranges and running parallelized jobs.
- If your Mongo key is an ObjectId, ensure Oracle's column is converted (string vs ObjectId) accordingly.

---

## License

This template includes Oracle JDBC via Maven Central. Ensure you comply with your organization’s licensing and security policies.

#### Fixed CSV column order (optional)
- Use `app.output.csv-columns` to set a comma-separated header list.
- If omitted, the header is inferred from the first record and sorted alphabetically.
