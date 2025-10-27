
```bash
#!/usr/bin/env bash
set -euo pipefail

# --- CONFIG ---
FILE="distilbert-base-uncased.tgz"
BUCKET="<your-bucket>"
KEY="<prefix>/models/distilbert-base-uncased.tgz"
REGION="<your-region>"                # e.g., us-east-1
KMS_KEY_ID="arn:aws:kms:<region>:<acct>:key/<KEY_ID>"   # or the alias arn

# Part size (>= 5MB; only last part can be smaller). 128MB is a good default.
PART_SIZE_MB=128

# --- PREP ---
echo "Splitting $FILE into ${PART_SIZE_MB}MB chunks..."
# Creates files like distilbert-base-uncased.tgz.part.0000, 0001, ...
split -b ${PART_SIZE_MB}M -d -a 4 "$FILE" "${FILE}.part."

# --- START MULTIPART UPLOAD ---
echo "Creating multipart upload..."
UPLOAD_ID=$(aws s3api create-multipart-upload \
  --bucket "$BUCKET" \
  --key "$KEY" \
  --region "$REGION" \
  --server-side-encryption aws:kms \
  --ssekms-key-id "$KMS_KEY_ID" \
  --query UploadId --output text)

echo "UploadId: $UPLOAD_ID"

# --- UPLOAD PARTS ---
PARTS_JSON='{"Parts":['
i=1
for part_file in $(ls ${FILE}.part.* | sort); do
  echo "Uploading part #$i from $part_file ..."
  # Note: --body must be a file; CLI returns an ETag like "\"<md5/etag>\""
  ETAG=$(aws s3api upload-part \
    --bucket "$BUCKET" \
    --key "$KEY" \
    --part-number $i \
    --upload-id "$UPLOAD_ID" \
    --body "$part_file" \
    --query ETag --output text)

  # Append to JSON (ETAG already includes quotes)
  if [ $i -gt 1 ]; then PARTS_JSON+=", "; fi
  PARTS_JSON+="{\"ETag\": $ETAG, \"PartNumber\": $i}"
  i=$((i+1))
done
PARTS_JSON+=']}'

# --- COMPLETE MULTIPART UPLOAD ---
echo "Completing multipart upload..."
aws s3api complete-multipart-upload \
  --bucket "$BUCKET" \
  --key "$KEY" \
  --upload-id "$UPLOAD_ID" \
  --multipart-upload "$PARTS_JSON" > /dev/null

echo "Upload complete: s3://$BUCKET/$KEY"

# --- CLEANUP ---
echo "Cleaning up local part files..."
rm -f ${FILE}.part.*

echo "Done."
```

2. If something goes wrong

```bash
aws s3api abort-multipart-upload --bucket "$BUCKET" --key "$KEY" --upload-id "$UPLOAD_ID"
```

3. If the bucket has a default KMS key, you can omit `--ssekms-key-id`, but you must still have key permissions.