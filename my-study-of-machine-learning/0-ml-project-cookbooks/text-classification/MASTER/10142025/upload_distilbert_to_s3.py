#!/usr/bin/env python3
"""Upload a local Hugging Face model directory to S3.

Usage:
  python upload_distilbert_to_s3.py \
      --local_dir /path/to/distilbert-base-uncased \
      --bucket your-bucket-name \
      --prefix models/distilbert-base-uncased/ \
      [--region us-east-1]

The script recursively uploads all files under --local_dir to s3://<bucket>/<prefix>.
You can then set MODEL_MODE='offline' and MODEL_S3_PREFIX to this prefix in the SageMaker notebook.
"""

import os
import argparse
import boto3

def upload_dir(local_dir: str, bucket: str, prefix: str, region: str):
    s3 = boto3.client("s3", region_name=region)
    local_dir = os.path.abspath(local_dir)
    uploaded = []
    for root, _, files in os.walk(local_dir):
        for f in files:
            lp = os.path.join(root, f)
            rel = os.path.relpath(lp, local_dir).replace('\\','/')
            key = prefix.rstrip('/') + '/' + rel
            s3.upload_file(lp, bucket, key)
            uploaded.append(f"s3://{bucket}/{key}")
    return uploaded

def main():
    p = argparse.ArgumentParser()
    p.add_argument("--local_dir", required=True, help="Path to local HF model directory")
    p.add_argument("--bucket", required=True, help="S3 bucket name")
    p.add_argument("--prefix", required=True, help="S3 key prefix (folder) to upload into")
    p.add_argument("--region", default=os.environ.get("AWS_DEFAULT_REGION", "us-east-1"))
    args = p.parse_args()

    if not os.path.isdir(args.local_dir):
        raise SystemExit(f"Local model dir not found: {args.local_dir}")

    print(f"Uploading from {args.local_dir} to s3://{args.bucket}/{args.prefix} (region: {args.region})")
    uploaded = upload_dir(args.local_dir, args.bucket, args.prefix, args.region)
    print(f"Uploaded {len(uploaded)} files.")
    if uploaded:
        print("Sample:")
        for u in uploaded[:10]:
            print("  ", u)

if __name__ == "__main__":
    main()
