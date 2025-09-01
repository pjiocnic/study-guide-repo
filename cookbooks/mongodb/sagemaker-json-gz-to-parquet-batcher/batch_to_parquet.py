import argparse
import io
import json
import gzip
from typing import List, Optional

import boto3
import pandas as pd

# pandas options for better memory visibility
pd.set_option("display.width", 120)
pd.set_option("display.max_columns", 200)


def list_s3_keys(s3, bucket: str, prefix: str, suffix: str = ".gz", max_keys: Optional[int] = None) -> List[str]:
    """List keys under prefix ending with suffix, sorted ascending."""
    token = None
    out = []
    while True:
        kwargs = {"Bucket": bucket, "Prefix": prefix, "MaxKeys": 1000}
        if token:
            kwargs["ContinuationToken"] = token
        page = s3.list_objects_v2(**kwargs)
        for obj in page.get("Contents", []):
            key = obj["Key"]
            if not suffix or key.endswith(suffix):
                out.append(key)
                if max_keys and len(out) >= max_keys:
                    return sorted(out)
        if page.get("IsTruncated"):
            token = page.get("NextContinuationToken")
        else:
            break
    return sorted(out)


def open_s3_gzip_stream(s3, bucket: str, key: str):
    """Return a file-like handle that yields decompressed bytes for a gz object in S3."""
    obj = s3.get_object(Bucket=bucket, Key=key)
    return gzip.GzipFile(fileobj=io.BytesIO(obj["Body"].read()))


def read_jsonl_gz_to_df(s3, bucket: str, key: str, dtype: Optional[dict] = None) -> pd.DataFrame:
    with open_s3_gzip_stream(s3, bucket, key) as fh:
        return pd.read_json(fh, lines=True, dtype=dtype)


def read_json_array_gz_to_df(s3, bucket: str, key: str, dtype: Optional[dict] = None) -> pd.DataFrame:
    with open_s3_gzip_stream(s3, bucket, key) as fh:
        data = json.load(fh)  # list[dict]
    return pd.DataFrame(data, dtype=dtype)


def load_one(s3, bucket: str, key: str, dtype: Optional[dict] = None, assume_jsonl: bool = True) -> pd.DataFrame:
    low = key.lower()
    if assume_jsonl:
        return read_jsonl_gz_to_df(s3, bucket, key, dtype=dtype)
    # Fast path by extension
    if low.endswith("jsonl.gz"):
        return read_jsonl_gz_to_df(s3, bucket, key, dtype=dtype)
    if low.endswith("json.gz"):
        return read_json_array_gz_to_df(s3, bucket, key, dtype=dtype)
    # Fallback by first non-space char
    with open_s3_gzip_stream(s3, bucket, key) as fh:
        b = fh.read(1)
        while b and b in b" \t\r\n":
            b = fh.read(1)
        if b == b"[":
            fh.seek(0)
            data = json.load(fh)
            return pd.DataFrame(data, dtype=dtype)
        fh.seek(0)
        return pd.read_json(fh, lines=True, dtype=dtype)


def load_dtype_mapping(s3, path: Optional[str]) -> Optional[dict]:
    """Load a JSON dict of column dtypes. Supports s3:// and local paths."""
    if not path:
        return None
    if path.startswith("s3://"):
        _, _, rest = path.partition("s3://")
        bucket, _, key = rest.partition("/")
        obj = s3.get_object(Bucket=bucket, Key=key)
        return json.loads(obj["Body"].read())
    with open(path, "r") as f:
        return json.load(f)


def yield_batches(s3, bucket: str, keys: List[str], batch_size: int, dtype: Optional[dict], assume_jsonl: bool):
    batch = []
    for key in keys:
        df = load_one(s3, bucket, key, dtype=dtype, assume_jsonl=assume_jsonl)
        batch.append(df)
        if len(batch) >= batch_size:
            yield pd.concat(batch, ignore_index=True)
            batch = []
    if batch:
        yield pd.concat(batch, ignore_index=True)


def write_parquet_batch(df: pd.DataFrame, bucket: str, out_prefix: str, part_no: int,
                        repartition: int = 0):
    """
    Write DataFrame as one or more Parquet files on S3.
    Returns list of written s3:// urls.
    """
    written = []
    if repartition and repartition > 1:
        # Split roughly equal parts by row count
        splits = []
        step = max(1, len(df) // repartition)
        for i in range(0, len(df), step):
            splits.append(df.iloc[i:i+step])
        for i, sub in enumerate(splits, 1):
            key = f"{out_prefix.rstrip('/')}/part-{part_no:05d}-{i:02d}.snappy.parquet"
            url = f"s3://{bucket}/{key}"
            sub.to_parquet(url, index=False, compression="snappy", storage_options={"anon": False})
            written.append(url)
    else:
        key = f"{out_prefix.rstrip('/')}/part-{part_no:05d}.snappy.parquet"
        url = f"s3://{bucket}/{key}"
        df.to_parquet(url, index=False, compression="snappy", storage_options={"anon": False})
        written.append(url)
    return written


def main():
    ap = argparse.ArgumentParser(description="Batch-convert S3 jsonl.gz/json.gz to Parquet on S3.")
    ap.add_argument("--bucket", required=True, help="S3 bucket name")
    ap.add_argument("--prefix", required=True, help="S3 prefix containing gz JSON files")
    ap.add_argument("--out-prefix", required=True, help="Destination S3 prefix for Parquet (inside same bucket)")
    ap.add_argument("--max-files", type=int, default=0, help="Limit number of source files (0 = no limit)")
    ap.add_argument("--batch-size", type=int, default=25, help="Files per DataFrame batch (25 is safe on ml.m4.xlarge)")
    ap.add_argument("--assume-jsonl", type=str, default="true", help="true|false - assume jsonl for speed")
    ap.add_argument("--dtype-json", type=str, default="", help="Optional JSON mapping file for column dtypes (s3:// or local path)")
    ap.add_argument("--repartition", type=int, default=0, help="If >0, split each batch into N parquet files")
    args = ap.parse_args()

    s3 = boto3.client("s3")

    assume_jsonl = str(args.assume_jsonl).strip().lower() in ("1", "true", "yes")
    dtype_map = load_dtype_mapping(s3, args.dtype_json) if args.dtype_json else None

    keys = list_s3_keys(s3, args.bucket, args.prefix, suffix=".gz", max_keys=args.max_files or None)
    print(f"Discovered {len(keys)} gz files under s3://{args.bucket}/{args.prefix}")

    part = 1
    for df in yield_batches(s3, args.bucket, keys, args.batch_size, dtype=dtype_map, assume_jsonl=assume_jsonl):
        written = write_parquet_batch(df, args.bucket, args.out_prefix, part_no=part, repartition=args.repartition)
        print(f"Wrote: {written}")
        part += 1

    print("Done.")


if __name__ == "__main__":
    main()
