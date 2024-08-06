
# Must See

1. [AWS KMS Workshop](https://github.com/aws-samples/aws-kms-workshop/tree/master)

# 1. How encryption works in AWS

1. [The importance of encryption and how AWS can help by Ken Beer](https://aws.amazon.com/blogs/security/importance-of-encryption-and-how-aws-can-help/)

# Encrytion for SNS

[Encrypting messages published to Amazon SNS with AWS KMS by Michelle Mercier and Otavio Ferreira](https://aws.amazon.com/blogs/compute/encrypting-messages-published-to-amazon-sns-with-aws-kms/)

# Encrytion for S3

1. [Encrypting existing Amazon S3 objects with the AWS CLI by Andrew Guthrie](https://aws.amazon.com/blogs/storage/encrypting-existing-amazon-s3-objects-with-the-aws-cli/)
1. [Changing your Amazon S3 encryption from S3-Managed to AWS KMS by Charlie Llewellyn](https://aws.amazon.com/blogs/storage/changing-your-amazon-s3-encryption-from-s3-managed-encryption-sse-s3-to-aws-key-management-service-sse-kms/)

# 2. single-Region or multi-Region KMS key

1. [Choose the right type of AWS KMS key to encrypt Amazon RDS and Aurora Global Database by Siva Subramaniam and Robert Daly](https://aws.amazon.com/blogs/database/choose-the-right-type-of-aws-kms-key-to-encrypt-amazon-rds-and-aurora-global-database/)

# 3. Customer managed keys vs AWS managed keys vs AWS owned keys

1. [Choose the right type of AWS KMS key to encrypt Amazon RDS and Aurora Global Database by Siva Subramaniam and Robert Daly](https://aws.amazon.com/blogs/database/choose-the-right-type-of-aws-kms-key-to-encrypt-amazon-rds-and-aurora-global-database/)
2. [Customer managed keys](https://docs.aws.amazon.com/kms/latest/developerguide/concepts.html#customer-cmk)
3. [AWS managed keys](https://docs.aws.amazon.com/kms/latest/developerguide/concepts.html#aws-managed-cmk)
4. [AWS owned keys](https://docs.aws.amazon.com/kms/latest/developerguide/concepts.html#aws-owned-cmk)

# 4. Sample Applications

## 4.1. Customer-managed AWS KMS Key usage

1. [Strengthening data security in AWS Step Functions with a customer-managed AWS KMS key by Dhiraj Mahapatro](https://aws.amazon.com/blogs/compute/strengthening-data-security-in-aws-step-functions-with-a-customer-managed-aws-kms-key/)
2. [Sample stock trading application to showcase customer-managed AWS KMS Key usage in Step Functions](https://github.com/aws-samples/aws-stepfunctions-examples/tree/main/sam/app-sfn-kms-integration)

# Envelop Encryption / Symmetric encryption

Using DEKs (Data encryption keys)

1. [The importance of encryption and how AWS can help by Ken Beer](https://aws.amazon.com/blogs/security/importance-of-encryption-and-how-aws-can-help/)
1. [AWS KMS and Envelope Encryption](https://lobster1234.github.io/2017/09/29/aws-kms-envelope-encryption/)
1. [[JAVA][SDK] How to Use the New AWS Encryption SDK to Simplify Data Encryption and Improve Application Availability by Greg Rubin](https://aws.amazon.com/blogs/security/how-to-use-the-new-aws-encryption-sdk-to-simplify-data-encryption-and-improve-application-availability/)
1. [[SAMPLE] Encryption with AWS KMS](https://github.com/aws-samples/aws-kms-workshop/blob/master/Section-2-Encryption-with-AWS-KMS.md)

# Asymmetric keys

1. [How to use AWS KMS RSA keys for offline encryption by Patrick Palmer ](https://aws.amazon.com/blogs/security/how-to-use-aws-kms-rsa-keys-for-offline-encryption/)

# Generating Signatures

1. [How to verify AWS KMS asymmetric key signatures locally with OpenSSL by J.D. Bean ](https://aws.amazon.com/blogs/security/how-to-verify-aws-kms-asymmetric-key-signatures-locally-with-openssl/)
2. [Digital signing with the new asymmetric keys feature of AWS KMS by Raj Copparapu](https://aws.amazon.com/blogs/security/digital-signing-asymmetric-keys-aws-kms/)