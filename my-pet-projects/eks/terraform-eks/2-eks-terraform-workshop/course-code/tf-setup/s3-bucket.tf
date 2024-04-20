
# S3 Bucket
resource "aws_s3_bucket" "terraform_state" {

  bucket = format("tf-state-workshop-%s", random_id.id1.hex)

  // This is only here so we can destroy the bucket as part of automated tests.
  // You should not copy this for production usage
  force_destroy = true

  lifecycle {
    ignore_changes = [bucket]
  }

}

# S3 Server-Side Encryption Configuration
resource "aws_s3_bucket_server_side_encryption_configuration" "terraform_state" {
  bucket = aws_s3_bucket.terraform_state.id

  // Defines a server-side encryption rule for the S3 bucket
  rule {
    bucket_key_enabled = false #  it means that the S3 bucket does not require objects to have server-side encryption provided by the AWS Key Management Service (KMS).

    apply_server_side_encryption_by_default {
      sse_algorithm     = "aws:kms"
      kms_master_key_id = aws_kms_key.ekskey.key_id
    }
  }
}

# S3 Bucket Versioning
resource "aws_s3_bucket_versioning" "terraform_state" {
  # Enable versioning so we can see the full revision history
  # of our state files
  bucket = aws_s3_bucket.terraform_state.id
  versioning_configuration {
    status = "Enabled"
  }
}

# S3 Bucket Public Access Block
resource "aws_s3_bucket_public_access_block" "pub_block_state" {
  bucket = aws_s3_bucket.terraform_state.id

  restrict_public_buckets = true
  block_public_acls       = true  # blocks new public Access Control Lists (ACLs) for objects in the bucket.
  block_public_policy     = true  # blocks public bucket policies
  ignore_public_acls      = true  # ignores public ACLs
}

