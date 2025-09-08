
# 0) setup & helpful flags

```bash
aws configure                                # set creds & default region
# Handy flags you can add to any command:
#   --profile <name>   use a specific profile
#   --region <region>  override region
#   --output table     nicer printing (json|text|table)
#   --endpoint-url ... for S3-compatible storage (minio, etc.)
```

# 1) create a bucket

```bash
# us-east-1 special-case (no LocationConstraint):
aws s3api create-bucket --bucket <bucket>

# all other regions:
aws s3api create-bucket \
  --bucket <bucket> \
  --create-bucket-configuration LocationConstraint=<region>

# useful hardening right after creation:
aws s3api put-public-access-block --bucket <bucket> \
  --public-access-block-configuration BlockPublicAcls=true,IgnorePublicAcls=true,BlockPublicPolicy=true,RestrictPublicBuckets=true

# enable versioning (highly recommended)
aws s3api put-bucket-versioning --bucket <bucket> --versioning-configuration Status=Enabled

# (optional) default encryption (SSE-S3)
aws s3api put-bucket-encryption --bucket <bucket> --server-side-encryption-configuration '{
  "Rules":[{"ApplyServerSideEncryptionByDefault":{"SSEAlgorithm":"AES256"}}]}'
```

# 2) create a “prefix” (aka a folder)

There aren’t real folders—just keys with `/` in them. Two common ways:

```bash
# (A) create an empty "folder marker" object:
aws s3api put-object --bucket <bucket> --key path/to/prefix/

# (B) implicitly create by uploading into it (no pre-step needed):
aws s3 cp local.txt s3://<bucket>/path/to/prefix/local.txt
```

# 3) upload

```bash
# single file
aws s3 cp ./file.json s3://<bucket>/path/to/prefix/

# whole directory (recursive)
aws s3 cp ./data/ s3://<bucket>/path/to/prefix/ --recursive

# include/exclude patterns
aws s3 cp ./data/ s3://<bucket>/path/to/prefix/ --recursive \
  --exclude "*" --include "*.parquet"

# set metadata / content-type
aws s3 cp report.csv s3://<bucket>/reports/ \
  --content-type text/csv --metadata project=alpha

# server-side encryption choices (pick one)
aws s3 cp file.gz s3://<bucket>/secure/ --sse AES256
aws s3 cp file.gz s3://<bucket>/secure/ --sse aws:kms --sse-kms-key-id <kms-key-arn>

# sync a local dir to S3 (one-way)
aws s3 sync ./out/ s3://<bucket>/exports/        # upload new/changed
aws s3 sync s3://<bucket>/exports/ ./out/        # download new/changed
```

# 4) list (ls), find, and inventory-like tricks

```bash
# buckets
aws s3 ls

# top-level of a bucket
aws s3 ls s3://<bucket>/

# under a prefix
aws s3 ls s3://<bucket>/path/to/prefix/

# recursive listing with sizes
aws s3 ls s3://<bucket>/path/to/ --recursive --human-readable --summarize

# low-level list-objects-v2 (JSON output; supports continuation)
aws s3api list-objects-v2 --bucket <bucket> --prefix path/to/prefix/ --max-keys 1000

# list object versions (if versioning enabled)
aws s3api list-object-versions --bucket <bucket> --prefix path/to/prefix/
```

# 5) delete

```bash
# single object
aws s3 rm s3://<bucket>/path/to/file.txt

# recursive remove (CAREFUL!)
aws s3 rm s3://<bucket>/path/to/prefix/ --recursive

# empty & delete a bucket (CAREFUL!)
aws s3 rb s3://<bucket> --force

# versioned buckets: delete a specific VersionId
aws s3api delete-object --bucket <bucket> --key path/to/file.txt --version-id <id>

# remove all versions under a prefix (very careful; example via --query piping)
aws s3api list-object-versions --bucket <bucket> --prefix path/ \
  --query='{Objects: Versions[].{Key:Key,VersionId:VersionId}, Quiet: true}' > /tmp/todelete.json
aws s3api delete-objects --bucket <bucket> --delete file:///tmp/todelete.json
```

# 6) download

```bash
# single file
aws s3 cp s3://<bucket>/path/to/file.parquet ./file.parquet

# recursive
aws s3 cp s3://<bucket>/dataset/ ./dataset/ --recursive

# selective download by pattern
aws s3 cp s3://<bucket>/logs/ ./logs/ --recursive --exclude "*" --include "2025-09-*.gz"
```

# 7) move / rename (copy then delete)

```bash
# move a single object
aws s3 mv s3://<bucket>/old/name.json s3://<bucket>/new/name.json

# move a whole tree
aws s3 mv s3://<bucket>/old-prefix/ s3://<bucket>/new-prefix/ --recursive
```

# 8) presign (temporary download/upload URL)

```bash
# default 3600s (1 hour); change with --expires-in
aws s3 presign s3://<bucket>/path/to/file.zip --expires-in 900
```

# 9) storage class, metadata & ACL (be cautious with ACLs)

```bash
# set storage class on upload
aws s3 cp big.tar s3://<bucket>/archive/ --storage-class STANDARD_IA

# copy in-place to change storage class or metadata (requires reupload)
aws s3 cp s3://<bucket>/x.bin s3://<bucket>/x.bin --metadata-directive REPLACE \
  --storage-class GLACIER_IR --content-type application/octet-stream

# avoid ACLs unless you must (prefer bucket policies + IAM). Example (not recommended by default):
aws s3api put-object-acl --bucket <bucket> --key path/to/file.txt --acl bucket-owner-full-control
```

# 10) lifecycle rules (auto-expire, transition)

```bash
# put a simple lifecycle to expire temp/ after 7 days
aws s3api put-bucket-lifecycle-configuration --bucket <bucket> --lifecycle-configuration '{
  "Rules": [{
    "ID": "expire-temp",
    "Status": "Enabled",
    "Filter": {"Prefix": "temp/"},
    "Expiration": {"Days": 7}
  }]
}'
```

# 11) bucket policy (example: allow only TLS, deny unencrypted puts)

```bash
aws s3api put-bucket-policy --bucket <bucket> --policy '{
  "Version": "2012-10-17",
  "Statement": [
    { "Sid":"DenyInsecureTransport","Effect":"Deny","Principal":"*",
      "Action":"s3:*","Resource":["arn:aws:s3:::<bucket>","arn:aws:s3:::<bucket>/*"],
      "Condition":{"Bool":{"aws:SecureTransport":"false"}}},
    { "Sid":"DenyUnencryptedObjectUploads","Effect":"Deny","Principal":"*",
      "Action":"s3:PutObject","Resource":"arn:aws:s3:::<bucket>/*",
      "Condition":{"StringNotEquals":{"s3:x-amz-server-side-encryption":"AES256"}}}
  ]}'
```

# 12) cross-region copy / replication (one-off copy)

```bash
# copy an object between buckets/regions
aws s3 cp s3://<src-bucket>/data/file.parquet s3://<dst-bucket>/data/file.parquet \
  --source-region <src-region> --region <dst-region>
```

# 13) multipart tuning (large files)

```bash
# Set multipart thresholds (env vars) for faster uploads of huge files
export AWS_MAX_ATTEMPTS=10
export AWS_RETRY_MODE=adaptive
# `aws s3 cp` auto-multipart >8MB; you can adjust chunk sizes with config in ~/.aws/config if needed
```

# 14) quick troubleshooting & tips

* **“NoSuchBucket” / region mismatch**: ensure `--region` matches the bucket’s region.
* **Windows quoting**: use double quotes; JSON parameters often easier via files.
* **Performance**: prefer `sync` for trees; consider parallelizing with GNU parallel or multiple prefixes.
* **Cost/safety**: enable versioning + lifecycle; block public access; use default SSE.
* **Filtering**: `--exclude` is evaluated before `--include`. End your paths with `/` for directory semantics.

