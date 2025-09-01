Awesome—here’s a SageMaker-style, copy-pasteable set of cells you can drop into your notebook to **discover**, **stream-load**, and **batch-concatenate** your gzipped JSON files from S3 into Pandas for feature engineering. It works for both `jsonl.gz` (one object per line) and `json.gz` (a JSON array).

---

# 1) Setup (SageMaker session, boto3, and config)

```python
import boto3
import sagemaker
from sagemaker import Session

sess = sagemaker.Session()
region = sess.boto_region_name
s3 = boto3.client("s3", region_name=region)

# === EDIT THESE ===
BUCKET = "your-bucket-name"
PREFIX = "exports/example/"  # folder/prefix where the .gz files were written
FILE_EXT = ".gz"             # keep as .gz (we’ll detect jsonl vs json-array automatically)
```

---

# 2) List your exported files in S3 (with pagination)

```python
def list_s3_keys(bucket: str, prefix: str, suffix: str = ""):
    """List S3 object keys under a prefix (handles pagination)."""
    token = None
    keys = []
    while True:
        kwargs = {"Bucket": bucket, "Prefix": prefix, "MaxKeys": 1000}
        if token:
            kwargs["ContinuationToken"] = token
        page = s3.list_objects_v2(**kwargs)
        for obj in page.get("Contents", []):
            key = obj["Key"]
            if key.endswith(suffix):
                keys.append(key)
        if page.get("IsTruncated"):
            token = page.get("NextContinuationToken")
        else:
            break
    return sorted(keys)

keys = list_s3_keys(BUCKET, PREFIX, suffix=FILE_EXT)
print(f"Found {len(keys)} files under s3://{BUCKET}/{PREFIX}")
keys[:5]
```

---

# 3) Streaming readers for `jsonl.gz` and `json-array.gz` from S3

These do **not** download to disk; they stream from S3.

```python
import io, gzip, json
import pandas as pd

def _open_s3_gzip_stream(bucket: str, key: str):
    """Return a file-like handle that yields decompressed bytes from s3://bucket/key."""
    obj = s3.get_object(Bucket=bucket, Key=key)
    return gzip.GzipFile(fileobj=io.BytesIO(obj["Body"].read()))

def read_jsonl_gz_to_df(bucket: str, key: str, dtype=None):
    """
    Efficient reader for JSONL (one JSON per line) gzipped.
    Uses pandas.read_json(..., lines=True).
    """
    # pandas can read from a file-like object directly
    with _open_s3_gzip_stream(bucket, key) as fh:
        return pd.read_json(fh, lines=True, dtype=dtype)

def read_json_array_gz_to_df(bucket: str, key: str, dtype=None):
    """
    Reader for a gzipped JSON array (e.g., [ {...}, {...}, ... ]).
    """
    with _open_s3_gzip_stream(bucket, key) as fh:
        data = json.load(fh)     # list[dict]
    return pd.DataFrame(data, dtype=dtype)
```

---

# 4) Auto-detect `jsonl` vs `json-array` (by peeking at filename or first byte)

Your template names files like `...jsonl.gz` (JSONL) or `...json.gz` (JSON array). We’ll use the filename for detection (fast), with a safe fallback.

```python
def load_one_gz_to_df(bucket: str, key: str, dtype=None):
    # Heuristic #1: filename
    low = key.lower()
    if low.endswith("jsonl.gz"):
        return read_jsonl_gz_to_df(bucket, key, dtype=dtype)
    if low.endswith("json.gz"):
        return read_json_array_gz_to_df(bucket, key, dtype=dtype)
    # Heuristic #2: first non-whitespace char ('[' means array; otherwise assume jsonl)
    with _open_s3_gzip_stream(bucket, key) as fh:
        first_bytes = fh.read(1)
        while first_bytes and first_bytes in b" \t\r\n":
            first_bytes = fh.read(1)
        if first_bytes == b"[":
            # It’s a JSON array
            fh.seek(0)
            data = json.load(fh)
            return pd.DataFrame(data, dtype=dtype)
        else:
            fh.seek(0)
            return pd.read_json(fh, lines=True, dtype=dtype)
```

---

# 5) Batch loader (choose how many files to concatenate at a time)

This keeps memory in check: you decide the **batch size** (number of files), we concatenate that batch into a DataFrame, and you can either use it directly or write it out (e.g., to Parquet) before moving to the next.

```python
from typing import Iterable, List, Optional

def load_in_batches(
    bucket: str,
    keys: List[str],
    batch_size: int = 10,
    dtype: Optional[dict] = None,
) -> Iterable[pd.DataFrame]:
    """
    Yield DataFrames, each formed by concatenating `batch_size` files.
    """
    batch = []
    for i, key in enumerate(keys, 1):
        df = load_one_gz_to_df(bucket, key, dtype=dtype)
        batch.append(df)
        if len(batch) >= batch_size:
            yield pd.concat(batch, ignore_index=True)
            batch = []
    if batch:
        yield pd.concat(batch, ignore_index=True)
```

---

# 6) Example: load the **first 20 files** in 5-file batches

```python
# Tweak these based on your memory and file sizes
MAX_FILES = 20
BATCH_SIZE = 5

subset = keys[:MAX_FILES]
print(f"Loading {len(subset)} files in batches of {BATCH_SIZE}...")

dfs = []
for j, df_batch in enumerate(load_in_batches(BUCKET, subset, batch_size=BATCH_SIZE), 1):
    print(f"Batch {j}: shape={df_batch.shape}")
    # EXAMPLE: do feature engineering here or write to Parquet
    # df_batch = your_feature_engineering(df_batch)
    dfs.append(df_batch)

full_df = pd.concat(dfs, ignore_index=True) if dfs else pd.DataFrame()
full_df.shape, full_df.head()
```

---

## How many files should I load into Pandas at once?

Short answer: **aim for 1–4 GB of uncompressed data per batch** (roughly **30–60% of your instance RAM**), then iterate batch-by-batch.

Why this works:

* Pandas expands data in memory (Python objects, dtype alignment, temporary copies).
* A conservative rule-of-thumb is to **stay under \~60% of available RAM** per working batch to leave headroom for transforms and temporary objects.

### Practical heuristic

1. **Measure one file**:

   * Uncompress a single file locally once and check its size or load it and note `df.memory_usage(deep=True).sum()`.
   * Suppose one file → \~150 MB in-memory.
2. **Pick a batch**:

   * If notebook instance has \~16 GB RAM → budget \~5–8 GB per batch.
   * 5 GB / 150 MB ≈ **\~30–35 files per batch**.
3. **Start conservative**:

   * Try **10–15 files per batch**; if RAM headroom looks good, increase.

### Tips to scale cleanly

* Prefer **JSONL** (already line-delimited) and **convert to Parquet** for subsequent iterations:

  ```python
  # example inside the batch loop
  df_batch.to_parquet(f"s3://{BUCKET}/stage/parquet/part-{j:05d}.snappy.parquet", index=False)
  ```
* Downcast dtypes where possible (e.g., `int64`→`int32`, categorical strings→`category`).
* If you plan heavy FE, consider **Dask** or **Spark** on SageMaker for distributed processing, then bring a sampled slice back to Pandas for fast iteration.

---

## Quick “just load it all” variant (only if you’re sure RAM is sufficient)

```python
# ⚠️ Use only when your total data volume comfortably fits in memory.
df_all = pd.concat([load_one_gz_to_df(BUCKET, k) for k in keys], ignore_index=True)
df_all.shape
```

---

If you share a rough **average file size** and your **instance RAM**, I can suggest a concrete batch size (e.g., “use 12 files per batch, expect \~3.2 GB per batch”).
