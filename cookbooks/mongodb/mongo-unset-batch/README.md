# mongo-unset-batch

Spring Boot batch application that reads a file of **keys** and unsets a list of fields for all documents in a MongoDB collection whose `keyField` matches any of those keys.

## Features
- Reads keys from a file (one per line; blank lines and lines starting with `#` are ignored).
- Unsets multiple fields using a single `$unset` operation per batch.
- Processes keys in chunks to avoid overly large `$in` arrays.
- Dry-run mode to preview what would be updated.
- Works with either an entity class or a raw collection name (we use collection name here).

## Requirements
- JDK 17+
- Maven 3.9+
- A reachable MongoDB instance

## Configure
Edit `src/main/resources/application.properties`:

```properties
# Connection string, e.g. mongodb://user:pass@host:27017/dbname
spring.data.mongodb.uri=mongodb://localhost:27017/test

# Default collection if not passed via --collection
app.collection=sample
# Default key field if not passed via --keyField
app.key-field=status
# Chunk size for $in batches
app.chunk-size=1000
```

## Input file
Create a file like `keys.txt`:
```
# Any line starting with # is a comment
inactive
trial-expired
suspended

```

## Build
```bash
mvn -q -DskipTests package
```

## Run

Minimal required flags:
```bash
java -jar target/mongo-unset-batch-1.0.0.jar \          --collection=myCollection \          --keyField=status \          --fields=field1,field2,field3 \          --keysFile=./keys.txt
```

With dry-run (no writes, only counts):
```bash
java -jar target/mongo-unset-batch-1.0.0.jar \          --collection=myCollection \          --keyField=status \          --fields=field1,field2,field3 \          --keysFile=./keys.txt \          --dryRun=true
```

Override Mongo URI without editing properties:
```bash
java -jar target/mongo-unset-batch-1.0.0.jar \          --spring.data.mongodb.uri="mongodb://user:pass@host:27017/db" \          --collection=myCollection \          --keyField=status \          --fields=field1,field2,field3 \          --keysFile=./keys.txt
```

Control chunk size:
```bash
java -jar target/mongo-unset-batch-1.0.0.jar --chunkSize=2000 ...
```

## Notes
- Keys are treated as **strings**; if your `keyField` is numeric or ObjectId, adapt parsing in `KeyFileReader`.
- The app logs how many docs match and how many were modified per chunk.
- If you need to match on **multiple key fields** or compound queries, extend `BatchUnsetRunner.buildQuery(...)` accordingly.
