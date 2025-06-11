1. You typically don't use Resource based policy for workloads that DONT span across accounts.

**Explanation:**
In AWS, resource-based policies are policies that are attached directly to AWS resources like S3 buckets, IAM roles, or AWS Lambda functions. These policies specify who (which principals) can access the resource and what actions they can perform on it. Resource-based policies are particularly useful for granting access to resources across different AWS accounts.

**Workloads within a Single Account:**
When dealing with workloads that operate within the same AWS account, identity-based policies are typically used. Identity-based policies are attached to IAM identities (users, groups, or roles) and specify what actions those identities can perform on which resources.

**Example Scenarios:**

**1. Single-Account Workload:**

**IAM User or Role Policies:**
* Users or roles within the same account use identity-based policies to get permissions.
* Example: An IAM role AppRole within the same account has an identity-based policy allowing it to access DynamoDB tables.

**2. Multi-Account Workload:**

**Resource-Based Policies:**

* When you need to grant access to resources across different AWS accounts, you use resource-based policies.
* Example: An S3 bucket in account A has a bucket policy allowing access to a role from account `B`.

**Key Points:**

**Identity-Based Policies:** Used within the same account. These policies are attached to IAM identities and control what those identities can do.
**Resource-Based Policies:** Useful for cross-account access. These policies are attached directly to the resource and control who can access the resource.

**Example:**

**Single-Account Setup:**
**IAM Role Policy:**

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": "dynamodb:Query",
      "Resource": "arn:aws:dynamodb:us-west-2:123456789012:table/MyTable"
    }
  ]
}
```

In this case, an IAM role within the same account has an identity-based policy allowing it to query a DynamoDB table.

**Cross-Account Setup:**

**S3 Bucket Policy (Resource-Based):**

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "AWS": "arn:aws:iam::210987654321:role/ExternalRole"
      },
      "Action": "s3:GetObject",
      "Resource": "arn:aws:s3:::example-bucket/*"
    }
  ]
}
```

In this case, an S3 bucket policy allows a role from another AWS account to get objects from the bucket.

**Conclusion:**

For workloads that do not span across accounts, you typically rely on identity-based policies attached to users, groups, or roles within the same account. Resource-based policies are more commonly used when you need to grant access to resources across different AWS accounts.