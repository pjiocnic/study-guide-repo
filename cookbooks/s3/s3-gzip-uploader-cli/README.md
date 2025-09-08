# s3-gzip-uploader-cli

Spring Boot 3 CommandLineRunner that gzips JSON and uploads to Amazon S3 (AWS SDK v2).

## Configure
`src/main/resources/application.yml`:
```yaml
app:
  s3:
    bucket: your-bucket-name
    prefix: gzip-uploads
```
Set AWS credentials/region using env/profile/role (SDK v2 default chain).

## Build & run
```bash
mvn clean package
java -jar target/s3-gzip-uploader-cli-0.0.1-SNAPSHOT.jar
```

## Service methods
- `uploadEachJsonAsGzip(bucket, prefix, baseName, List<String> jsonStrings)`
- `uploadBatchAsArrayGzip(bucket, prefix, baseName, List<String> jsonStrings)`
- `uploadBatchAsNdjsonGzip(bucket, prefix, baseName, List<String> jsonStrings)`
