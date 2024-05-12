

```bash
eksctl create cluster --name fabulous-painting-1715210268
```

```bash
eksctl utils associate-iam-oidc-provider --cluster fabulous-painting-1715210268 --approve
```

1. Create an IAM role and annotating the service account the pod will be using

```bash
# Create an IAM role and annotating the service account the pod will be using
eksctl create iamserviceaccount \
    --name s3-echoer \
    --cluster fabulous-painting-1715210268 \
    --attach-policy-arn arn:aws:iam::aws:policy/AmazonS3FullAccess
```

```bash
TARGET_BUCKET=irp-test-20240508

# aws s3api create-bucket \
#   --bucket $TARGET_BUCKET \
#   --create-bucket-configuration LocationConstraint=$(aws configure get region) \
#   --region $(aws configure get region)

aws s3api create-bucket \
  --bucket $TARGET_BUCKET

sed -e "s/TARGET_BUCKET/${TARGET_BUCKET}/g" s3-echoer-job.yaml.template > s3-echoer-job.yaml

kubectl get job --all-namespaces

kubectl apply -f s3-echoer-job.yaml

aws s3api list-objects \
  --bucket $TARGET_BUCKET \
  --query 'Contents[].{Key: Key, Size: Size}'
```

# Clean up

eksctl delete cluster --name fabulous-painting-1715210268