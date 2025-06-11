
1. For an application in the same account as the bucket, access can be granted by either an identity-based policy or a resource-based policy

2. Cross account access with Bucket Policy

- Account A 111111111111 (Trusted Account)

```json
// Identity Policy
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "s3:GetObject"
      ],
      "Resource": "arn:aws:s3:::DOC-EXAMPLE-BUCKET2/*"
    }
  ]
}
```

- Account B 222222222222 Hosting the bucket (trusting account)

```json
// Resource Policy
{
    "Version": "2012-10-17",
    "Statement": [{
        "Principal": {
            "AWS": "arn:aws:iam::111111111111:role/application-roles/ApplicationRole"
        },
        "Effect": "Allow",
        "Action": [
            "s3:GetObject"
        ],
        "Resource": "arn:aws:s3:::DOC-EXAMPLE-BUCKET2/*"
    }]
}
```