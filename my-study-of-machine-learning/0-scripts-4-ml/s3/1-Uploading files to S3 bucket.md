
## 1. Uploading using CLI

```bash
#!/bin/bash

# ---------- STEP 1: Configure AWS CLI with your credentials ----------

# Set AWS credentials (you can hardcode here temporarily or set via environment)
AWS_ACCESS_KEY_ID="your-access-key-id"
AWS_SECRET_ACCESS_KEY="your-secret-access-key"
AWS_DEFAULT_REGION="us-east-1"  # Change if needed
S3_BUCKET_NAME="my-sagemaker-bucket"  # Change to your actual bucket

# Export credentials to environment
export AWS_ACCESS_KEY_ID
export AWS_SECRET_ACCESS_KEY
export AWS_DEFAULT_REGION

# Optional: Save credentials for future CLI use (persistent config)
aws configure set aws_access_key_id "$AWS_ACCESS_KEY_ID"
aws configure set aws_secret_access_key "$AWS_SECRET_ACCESS_KEY"
aws configure set region "$AWS_DEFAULT_REGION"

# ---------- STEP 2: Confirm credentials and bucket exist ----------

echo "✅ Verifying credentials..."
aws sts get-caller-identity || { echo "❌ Invalid credentials"; exit 1; }

echo "✅ Checking if S3 bucket exists..."
aws s3 ls "s3://$S3_BUCKET_NAME" || { echo "❌ Bucket does not exist or permission denied"; exit 1; }

# ---------- STEP 3: Upload CSV files to S3 ----------

# Local path containing CSVs
LOCAL_FOLDER=~/datasets

# Upload all .csv files from the folder to the root of the S3 bucket
echo "📤 Uploading CSV files from $LOCAL_FOLDER to s3://$S3_BUCKET_NAME/"
aws s3 cp "$LOCAL_FOLDER" "s3://$S3_BUCKET_NAME/" --recursive --exclude "*" --include "*.csv"

echo "✅ Upload completed."

```

## Uploading using python script

```py
import boto3
import os
from pathlib import Path

# ---- CONFIGURATION SECTION ----
AWS_ACCESS_KEY_ID = "your-access-key-id"
AWS_SECRET_ACCESS_KEY = "your-secret-access-key"
AWS_REGION = "us-east-1"
S3_BUCKET = "my-sagemaker-bucket"
S3_PREFIX = "datasets/"  # Optional: Use "" to upload to root

# Local folder with CSV files
LOCAL_FOLDER = Path.home() / "datasets"
# --------------------------------

# Create S3 client
s3 = boto3.client(
    "s3",
    aws_access_key_id=AWS_ACCESS_KEY_ID,
    aws_secret_access_key=AWS_SECRET_ACCESS_KEY,
    region_name=AWS_REGION
)

def upload_csv_files():
    if not LOCAL_FOLDER.exists():
        print(f"❌ Local folder {LOCAL_FOLDER} does not exist.")
        return

    csv_files = list(LOCAL_FOLDER.glob("*.csv"))
    if not csv_files:
        print("⚠️ No CSV files found to upload.")
        return

    for file_path in csv_files:
        key = f"{S3_PREFIX}{file_path.name}"
        print(f"📤 Uploading {file_path} ➜ s3://{S3_BUCKET}/{key}")
        s3.upload_file(str(file_path), S3_BUCKET, key)

    print("✅ All CSV files uploaded.")

if __name__ == "__main__":
    upload_csv_files()

```