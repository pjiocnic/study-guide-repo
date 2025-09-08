# S3 JSON(.gz) → Parquet (Batch Converter) for SageMaker

This lightweight project loads **gzipped JSONL or JSON-array** exports from S3 in **batches** and writes them back to **S3 as Parquet** (Snappy), ready for fast, repeatable feature engineering — aligned with the workflow style of the *SageMaker Customer Churn* example.

## Features
- Streams `jsonl.gz` and `json.gz` directly from S3 (no temp files needed).
- Concatenates **N files per batch** to stay under memory limits (good for `ml.m4.xlarge`).
- Writes **partitioned Parquet** to `s3://.../parquet/` (one file per batch), Snappy-compressed.
- Simple CLI for notebooks or SageMaker Processing jobs.

## Requirements
- Python 3.10+
- `pip install -r requirements.txt`
- AWS credentials configured (env vars, profile, or role)

## Quick Start (Notebook or Local)
```bash
pip install -r requirements.txt
python batch_to_parquet.py       --bucket your-bucket       --prefix exports/example/       --out-prefix stage/parquet/       --max-files 200       --batch-size 25       --assume-jsonl true
```

### Arguments
- `--bucket`: S3 bucket name (source and destination).
- `--prefix`: S3 prefix containing the `.gz` files produced by your Spring Boot job.
- `--out-prefix`: Destination prefix for Parquet (e.g. `stage/parquet/`). Created if missing.
- `--max-files`: Limit number of source files to process (omit to process all).
- `--batch-size`: Number of input files per Parquet output (aim for 25–50 on `ml.m4.xlarge`).
- `--assume-jsonl`: If `true`, treat files as JSONL; if `false`, auto-detect (fallback safe but slower).
- `--repartition`: If set > 0, will split each batch DataFrame into this many roughly equal Parquet parts.
- `--dtype-json`: Optional S3 URI or local path to a JSON dict of column dtypes to enforce (e.g., strings as categories).

## Recommended Settings for `ml.m4.xlarge` (16 GiB)
- Start with `--batch-size 25` (≈ 1–3 GB in-memory, depending on schema).
- Increase to 40–60 if you see plenty of headroom.
- Convert strings with limited cardinality to `category` to shrink memory.

## Output Layout
Writes files like:
```
s3://your-bucket/stage/parquet/part-00001.snappy.parquet
s3://your-bucket/stage/parquet/part-00002.snappy.parquet
...
```
Use AWS Glue Crawler or Spark to create a table from the resulting prefix.

## Using in a SageMaker Processing Job (Optional Sketch)
- Upload this repo to S3 or attach as a CodeCommit/Git repo.
- Use a SKLearn or BYO processing image with `pandas`, `pyarrow`, `s3fs`, `boto3` installed.
- Pass arguments to the script via `ProcessingInput`/`ProcessingOutput` or environment.

## License
MIT