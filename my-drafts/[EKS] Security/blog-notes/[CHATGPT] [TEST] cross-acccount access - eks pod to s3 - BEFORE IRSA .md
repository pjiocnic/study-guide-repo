End-to-End Example Before IRSA

explain follwing excerpt from [Cross account IAM roles for Kubernetes service accounts by Amit Borulkar](https://aws.amazon.com/blogs/containers/cross-account-iam-roles-for-kubernetes-service-accounts/)

Prior to IRSA, to access the pics bucket in shared_content account, we perform the following steps:

1. Create a S3_Pics role in shared_content account, which creates trust relationship between shared_content and developer account.
2. Attach a policy to S3_Pics role, which allows ReadOnlyAccess only access to the pics bucket.
3. Attach an Amazon EC2 trust relationship policy to the Amazon EKS (EKS) worker node role in developer account.
4. Attach a policy to the EKS worker node role, which allows the EKS worker nodes to perform a sts:AssumeRole operation.

# Answer

**Step 1: Create a Role in the shared_content Account**

1. Create the S3_Pics role in the shared_content account.

This role should have a trust policy allowing the developer account to assume it.

Trust Policy for S3_Pics Role:

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "AWS": "arn:aws:iam::DEVELOPER_ACCOUNT_ID:root"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
```

2. Attach a policy to the S3_Pics role to allow read-only access to the pics bucket.

**Policy for S3_Pics Role:**

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": "s3:GetObject",
      "Resource": "arn:aws:s3:::pics-bucket/*"
    }
  ]
}
```

**Step 2: Attach an Amazon EC2 Trust Relationship Policy to the EKS Worker Node Role in the developer Account**

1. Update the trust relationship of the EKS worker node role (let's assume the role is named eks-node-role).

**Trust Policy for eks-node-role:**

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "Service": "ec2.amazonaws.com"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
```

**Step 3: Attach a Policy to the EKS Worker Node Role to Allow sts:AssumeRole**

1. Attach a policy to the eks-node-role role in the developer account to allow the EKS worker nodes to assume the S3_Pics role in the shared_content account.

**Policy for eks-node-role:**

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": "sts:AssumeRole",
      "Resource": "arn:aws:iam::SHARED_CONTENT_ACCOUNT_ID:role/S3_Pics"
    }
  ]
}
```

**Step 4: Using the Role from a Pod in the EKS Cluster**

1. Launch a pod with the necessary permissions to read from the S3 bucket. The pod will use the instance profile of the EKS worker node, which allows it to assume the S3_Pics role.

**Pod Specification:**

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: s3-reader-pod
spec:
  containers:
  - name: s3-reader
    image: amazonlinux
    command: ["/bin/sh", "-c", "yum install -y aws-cli && aws sts assume-role --role-arn arn:aws:iam::SHARED_CONTENT_ACCOUNT_ID:role/S3_Pics --role-session-name s3access --query 'Credentials.[AccessKeyId,SecretAccessKey,SessionToken]' --output text > /tmp/creds && source /tmp/creds && aws s3 ls s3://pics-bucket"]
```

**Explanation:**

* The pod will run the aws sts assume-role command to assume the S3_Pics role.
* The aws s3 ls command lists the objects in the pics-bucket using the assumed role credentials.

2. Apply the pod configuration:

```bash
kubectl apply -f s3-reader-pod.yaml
```

**Summary**
This example shows how to set up access to an S3 bucket in a shared_content account from an EKS worker node in a developer account without using IRSA. The steps involve creating an IAM role in the shared_content account, setting up the necessary trust policies, and configuring the EKS worker node role in the developer account to assume the S3_Pics role. Finally, a pod in the EKS cluster assumes the role and accesses the S3 bucket using AWS CLI commands.