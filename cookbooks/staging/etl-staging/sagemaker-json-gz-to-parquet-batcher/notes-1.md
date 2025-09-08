Exactly ✅

# The workflow

1. **Spring Boot job** → writes chunked **`jsonl.gz`** files to S3

   * Example keys:

     ```
     s3://my-raw-bucket/exports/example/export_20250901_194530_part00001.jsonl.gz
     ...
     ```
   * Controlled by:

     ```properties
     app.output.rows-per-file=1000
     app.output.format=jsonl
     app.output.s3-key-prefix=exports/example/
     ```

2. **SageMaker (or local) batch converter** → reads those `.gz` files and writes **Parquet** to a **different S3 prefix** (often the same bucket, different “folder”), e.g.:

   ```
   s3://my-raw-bucket/stage/parquet/part-00001.snappy.parquet
   ...
   ```

# One-liner you’ll run for the conversion

From the unzipped `sagemaker-json-gz-to-parquet-batcher` project:

```bash
python batch_to_parquet.py \
  --bucket my-raw-bucket \
  --prefix exports/example/ \
  --out-prefix stage/parquet/ \
  --batch-size 25 \
  --assume-jsonl true
```

# Notes for your `ml.m4.xlarge` (16 GiB)

* Start with `--batch-size 25` (safe), increase if memory looks comfortable.
* Keep `--assume-jsonl true` (your exporter emits JSONL by default).
* You can use `--repartition N` to split each batch’s DataFrame into N smaller Parquet parts.

# Optional: call from a SageMaker notebook cell

```python
!python batch_to_parquet.py \
  --bucket my-raw-bucket \
  --prefix exports/example/ \
  --out-prefix stage/parquet/ \
  --batch-size 25 \
  --assume-jsonl true
```

After this, you’ll have fast-loading Parquet files under `stage/parquet/`. You can point Glue/Athena/Spark to that prefix or load in Pandas with:

```python
import pandas as pd
df = pd.read_parquet("s3://my-raw-bucket/stage/parquet/part-00001.snappy.parquet")
```

If you want, I can also add a tiny **SageMaker Processing** script (and example estimator call) so you can run this as a managed step after each export.
