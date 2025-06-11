https://aws.amazon.com/blogs/security/how-to-set-up-least-privilege-access-to-your-encrypted-amazon-sqs-queue/

1. When is a resource policy a better choice?

* Granting **cross-account access** to your AWS resource.
* Granting an AWS service access to your resource when the AWS service uses an AWS service principal. For example, when using AWS CloudTrail, you must explicitly grant the CloudTrail service principal access to write files to an Amazon S3 bucket.
* Applying broad access guardrails to your AWS resources. You can see some examples in the blog post IAM makes it easier for you to manage permissions for AWS services accessing your resources.
* Applying an additional layer of protection for resources that store sensitive data, such as AWS Secrets Manager secrets or an S3 bucket with sensitive data. You can use a resource-based policy to deny access to IAM principals that shouldn’t have access to sensitive data, even if granted access by an identity-based policy. An explicit deny in an IAM policy always overrides an allow.

# References

1. [IAM policy types: How and when to use them by Matt Luttrell and Josh Joy ](https://aws.amazon.com/blogs/security/iam-policy-types-how-and-when-to-use-them/)
