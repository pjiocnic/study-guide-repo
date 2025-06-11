explain follwing excerpt from [Cross account IAM roles for Kubernetes service accounts by Amit Borulkar](https://aws.amazon.com/blogs/containers/cross-account-iam-roles-for-kubernetes-service-accounts/)

Prior to IRSA, to access the pics bucket in shared_content account, we perform the following steps:

1. Create a S3_Pics role in shared_content account, which creates trust relationship between shared_content and developer account.
2. Attach a policy to S3_Pics role, which allows ReadOnlyAccess only access to the pics bucket.
3. Attach an Amazon EC2 trust relationship policy to the Amazon EKS (EKS) worker node role in developer account.
4. Attach a policy to the EKS worker node role, which allows the EKS worker nodes to perform a sts:AssumeRole operation.

**Answer:**

Here is an end-to-end example demonstrating how to set up IAM roles and policies to allow an Amazon EKS worker node in a developer account to access an S3 bucket in a shared content account using IAM Roles for Service Accounts (IRSA).

**Step-by-Step Implementation**

**Prerequisites**
* Two AWS accounts: shared_content account and developer account.
* An S3 bucket named pics-bucket in the shared_content account.
* An Amazon EKS cluster in the developer account.

**Step 1: Create a S3_Pics Role in the shared_content Account**

1. Create the IAM role S3_Pics in the shared_content account.

Trust Policy: This policy allows the developer account to assume the role.

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

2. Attach a ReadOnlyAccess policy to the S3_Pics role.

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

**Step 2: Attach an Amazon EC2 Trust Relationship Policy to the EKS Worker Node Role in the Developer Account**

1. Edit the trust relationship of the EKS worker node role (usually named something like eks-node-role).

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

**Step 3: Attach a Policy to the EKS Worker Node Role to Allow sts**

Attach a policy to the eks-node-role role in the developer account.

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

**Step 4: Configure IRSA for Your EKS Cluster**

1. Create an OIDC provider for your EKS cluster.

```bash
eksctl utils associate-iam-oidc-provider --region YOUR_REGION --cluster YOUR_CLUSTER_NAME --approve
```

2. Create an IAM policy for the service account.

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": "sts:AssumeRoleWithWebIdentity",
      "Resource": "arn:aws:iam::SHARED_CONTENT_ACCOUNT_ID:role/S3_Pics"
    }
  ]
}
```

Save this policy JSON to a file, e.g., assume-role-policy.json, and create the policy in AWS.

```bash
aws iam create-policy --policy-name AssumeRolePolicy --policy-document file://assume-role-policy.json
```

3. Create a Kubernetes service account and annotate it with the IAM role.

```yaml
Copy code
apiVersion: v1
kind: ServiceAccount
metadata:
  name: s3-access-sa
  namespace default
  annotations:
    eks.amazonaws.com/role-arn: arn:aws:iam::DEVELOPER_ACCOUNT_ID:policy/AssumeRolePolicy
```

Apply the service account to your cluster:

```bash
kubectl apply -f service-account.yaml
```

**Step 5: Use the Service Account in Your Pod**
Deploy a pod that uses the service account.

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: s3-reader-pod
spec:
  serviceAccountName: s3-access-sa
  containers:
  - name: s3-reader
    image: amazonlinux
    command: ["/bin/sh", "-c", "yum install -y aws-cli && aws s3 ls s3://pics-bucket"]
```

Apply the pod configuration:

```bash
kubectl apply -f s3-reader-pod.yaml
```

**Summary**

By following these steps, you've set up the infrastructure to allow an EKS worker node in a developer account to access an S3 bucket in a shared_content account using IRSA. The key steps involved creating roles and policies in both AWS accounts and configuring Kubernetes to use these roles through IRSA. This setup enhances security by ensuring that the EKS pods assume roles dynamically using web identity tokens, following best practices for AWS and Kubernetes integration.