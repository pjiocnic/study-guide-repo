In the Spring Boot sample project I built for you, the **default upload format is `jsonl.gz`** (JSON Lines compressed with gzip).

That comes directly from this bit in `application.properties`:

```properties
# --- Output ---
app.output.format=jsonl   # <-- default value
```

* **`jsonl.gz` (default)**
  Each line in the file is a complete JSON object. Very efficient for streaming and downstream ETL/ML, since you can process line by line.
* **`json-array.gz` (optional)**
  If you change

  ```properties
  app.output.format=jsonarray
  ```

  then the files will instead be uploaded as one big JSON array per file:

  ```json
  [ { ... }, { ... }, { ... } ]
  ```

So in the **example run with 2,500 rows / 500 rows per file**, the uploader would create **five files** like:

```
s3://my-demo-bucket/exports/example/export_20250901_194530_part00001.jsonl.gz
...
s3://my-demo-bucket/exports/example/export_20250901_194530_part00005.jsonl.gz
```

All **`jsonl.gz`**, unless you override the property.

---

Would you like me to adjust the Python SageMaker loader snippet so it defaults to expecting **`jsonl.gz`** (instead of doing auto-detection)? That would simplify the pipeline since your Spring Boot job is always emitting JSONL unless you deliberately change it.
