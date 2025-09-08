# mongo-oracle-s3-etl-cli

**Spring Boot 3** CommandLineRunner example that pulls rows from **Oracle** (JdbcTemplate), writes them to **MongoDB** (MongoTemplate), and uploads a JSON export to **Amazon S3** (AWS SDK v2).

## Configure
Edit `src/main/resources/application.yml`:
- `spring.datasource.*` → Oracle JDBC URL/creds
- `spring.data.mongodb.uri` / `database` → your Mongo connection (including `mongodb+srv://.../mydatabase` if using Atlas)
- `app.s3.bucket`/`prefix` → your destination bucket/prefix
- `app.etl.oracle-query` → adjust the SQL and column names
- `app.etl.mongo-collection` → target Mongo collection

## Build & Run
```bash
mvn clean package
java -jar target/mongo-oracle-s3-etl-cli-0.0.1-SNAPSHOT.jar
```

## Local testing helpers
- Mongo:
  ```bash
  docker run -d --name mongo -p 27017:27017 mongo:7
  ```
- Oracle Free (example only):
  ```bash
  docker run -d --name free-db -p 1521:1521 -e ORACLE_PWD=oracle gvenzl/oracle-free:23.5-slim
  # url: jdbc:oracle:thin:@//localhost:1521/FREEPDB1 , username: system , password: oracle
  ```

## AWS Credentials
Relies on the AWS SDK default chain (env vars, AWS profile, or IAM role). Example:
```bash
export AWS_PROFILE=default
export AWS_REGION=us-east-1
```

## How it works
1. `JdbcTemplate` executes configured SQL and maps rows to `Employee`
2. `MongoTemplate` writes objects to the configured collection
3. `S3Client.putObject` uploads a pretty-printed JSON snapshot
