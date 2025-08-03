import boto3
s3 = boto3.client("s3")
bucket = "your-bucket-name"
prefix = "xgboost-demo"
s3.upload_file("train_script/dummy_train.csv", bucket, f"{prefix}/train.csv")
print("Uploaded to:", f"s3://{bucket}/{prefix}/train.csv")