# Oracle → Mongo → JSON/Parquet (chunked) → S3 (Spring Boot 3, Java 17)

Now **configurable output format**:
- `jsonl` → uploads `*.jsonl.gz` (Content-Encoding: gzip)
- `jsonarray` → uploads `*.json.gz` (gzip-compressed JSON array)
- `parquet` → uploads `*.parquet` (Snappy compression via Parquet)

## Configure
In your external `application.properties`:
```properties
app.output.rows-per-file=1000
app.output.format=parquet      # jsonl | jsonarray | parquet | csv
app.output.s3-key-prefix=exports/example/
app.output.file-name-pattern=export_%s_part%05d.%s
```
> For `parquet`, complex Mongo values are serialized to JSON strings by default for schema stability.

## Build & Run
```bash
mvn -q -DskipTests package
java -jar target/oracle-mongo-s3-uploader-0.0.2-SNAPSHOT.jar   --spring.config.location=./application.properties
```

## Notes
- Parquet schema is inferred from the first row of each chunk with **nullable types**. Subsequent rows are coerced (numbers → numeric, booleans → boolean, everything else → string).
- If you need nested columns as true Parquet structs/arrays, swap the default merger to preserve nested structures and extend `ParquetSink` to build nested Avro schemas accordingly.