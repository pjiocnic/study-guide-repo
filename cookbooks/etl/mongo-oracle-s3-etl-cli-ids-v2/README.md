# mongo-oracle-s3-etl-cli-ids

Pipeline:
1. Read IDs from a file (`app.input.ids-file`)
2. For each ID, fetch **raw JSON** from MongoDB (by `_id` by default, or set `app.mongo.id-field`)
3. Enrich with Oracle via the parameterized SQL (`app.oracle.by-id-sql`)
4. Build composite JSON `{ id, mongo, oracle }`
5. Upload to S3 (one object per ID)

## Configure
Edit `src/main/resources/application.yml`.

## Build & Run
```bash
mvn clean package
java -jar target/mongo-oracle-s3-etl-cli-ids-0.0.1-SNAPSHOT.jar
```
`ids.txt` can contain comma- or newline-separated IDs.

## Notes
- Mongo Atlas URIs are supported in `spring.data.mongodb.uri`.
- If your Mongo `_id` is an `ObjectId`, the reader tries ObjectId first, then falls back to string `_id`.
- Set `app.mongo.id-field` to look up by a different field (e.g. `empId`).
