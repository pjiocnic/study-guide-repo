Great 👍 — let’s walk through a **concrete example run** with the template project.

---

## Example Run Setup

**application.properties**

```properties
# --- Oracle ---
app.job.oracle-sql=SELECT ID, NAME, KEY_FIELD FROM MY_TABLE
app.keys.oracle-key-column=KEY_FIELD
app.keys.mongo-collection=my_collection
app.keys.mongo-key-field=_id

# --- S3 ---
app.s3.bucket=my-demo-bucket
app.s3.region=us-east-1

# --- Output ---
app.output.rows-per-file=500
app.output.format=jsonl
app.output.s3-key-prefix=exports/example/
app.output.file-name-pattern=export_%s_part%05d.%s.gz
```

**Oracle query result size:** 2,500 rows
**Rows per file:** 500

---

## What Happens

1. **ExportJob starts**

   * Opens the first gzip file (`part 00001`)
   * Streams rows from Oracle, does Mongo lookups, merges into JSON, writes to gzip

2. **Chunking**

   * Every 500 rows → current gzip file is closed & uploaded to S3
   * Then a new gzip file is started

3. **End of job**

   * Total rows = 2,500
   * That divides neatly into 5 chunks

---

## Files Created and Uploaded to S3

Suppose the run started at **2025-09-01 19:45:30**.
Timestamp placeholder `%s` resolves to `20250901_194530`.

Here’s what S3 will contain:

```
s3://my-demo-bucket/exports/example/export_20250901_194530_part00001.jsonl.gz
s3://my-demo-bucket/exports/example/export_20250901_194530_part00002.jsonl.gz
s3://my-demo-bucket/exports/example/export_20250901_194530_part00003.jsonl.gz
s3://my-demo-bucket/exports/example/export_20250901_194530_part00004.jsonl.gz
s3://my-demo-bucket/exports/example/export_20250901_194530_part00005.jsonl.gz
```

* **Each file** contains **500 rows** (as JSONL, one JSON per line)
* All are **gzip-compressed**
* Each has `Content-Encoding: gzip` and `Content-Type: application/json` set

---

✅ **Summary:**
For 2,500 Oracle rows with `rows-per-file=500`, the job creates **5 gzip files** and uploads them all to your bucket under the configured prefix.

---

Would you like me to also show how this looks if the total row count is *not divisible* by the chunk size (e.g. 2,650 rows with 500 per file)? That way you’ll see how the final smaller file is handled.
