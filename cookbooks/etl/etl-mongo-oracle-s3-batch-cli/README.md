# etl-mongo-oracle-s3-batch-cli

**ETL flow (batched):**
1. Batch read IDs from a file (`app.input.ids-file`), batch size configurable
2. For each ID, read Employee from Mongo via: `mongo.find(query, Employee.class, mongoCollection)`
3. Enrich Employee with Oracle attributes
4. Serialize a JSON **array** of employees
5. Gzip the array (Pandas-compatible JSON records)
6. Upload to S3

## Configure
See `src/main/resources/application.yml` for Mongo, Oracle, input, and S3 settings.

## Build & run
```bash
mvn clean package
java -jar target/etl-mongo-oracle-s3-batch-cli-0.0.1-SNAPSHOT.jar
```

The upload key per batch is `s3://<bucket>/<prefix>/<base-name>-<batchNo>-<epoch>.json.gz`.


## Dry-run mode (write locally, skip S3)
Set in `application.yml`:
```yaml
app:
  s3:
    dry-run: true
    local-dir: ./out  # where .json.gz files will be written
```
When `dry-run: true`, each batch writes a gzip file to `local-dir` instead of uploading to S3, and the app logs the local path.

### Run (with dry-run)
```bash
# Edit application.yml -> app.s3.dry-run: true
mvn clean package
java -jar target/etl-mongo-oracle-s3-batch-cli-0.0.1-SNAPSHOT.jar
# Outputs written to ./out/*.json.gz
```

### Run (normal S3 upload)
```bash
# Edit application.yml -> app.s3.dry-run: false
# Ensure AWS credentials/region are configured (SDK v2 default chain)
mvn clean package
java -jar target/etl-mongo-oracle-s3-batch-cli-0.0.1-SNAPSHOT.jar
```
