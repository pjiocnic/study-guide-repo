
<!-- TOC -->

- [1. Must See](#1-must-see)
- [2. How encryption works in AWS](#2-how-encryption-works-in-aws)
- [3. Encrytion for SNS](#3-encrytion-for-sns)
- [4. Encrytion for S3](#4-encrytion-for-s3)
- [5. single-Region or multi-Region KMS key](#5-single-region-or-multi-region-kms-key)
- [6. Customer managed keys vs AWS managed keys vs AWS owned keys](#6-customer-managed-keys-vs-aws-managed-keys-vs-aws-owned-keys)
- [7. Sample Applications](#7-sample-applications)
  - [7.1. Customer-managed AWS KMS Key usage](#71-customer-managed-aws-kms-key-usage)
- [8. Envelop Encryption / Symmetric encryption](#8-envelop-encryption--symmetric-encryption)
- [9. Asymmetric keys](#9-asymmetric-keys)
- [11. Encryption Java SDK](#11-encryption-java-sdk)
- [12. How to choose policies for KMS keys](#12-how-to-choose-policies-for-kms-keys)
- [13. Bucket Keys](#13-bucket-keys)
- [14. Samples to curate (categorize)](#14-samples-to-curate-categorize)
- [15. Digital Signing](#15-digital-signing)
- [16. Key Material (BYOK)](#16-key-material-byok)

<!-- /TOC -->

# 1. Must See

1. [AWS KMS Workshop](https://github.com/aws-samples/aws-kms-workshop/tree/master)

# 2. How encryption works in AWS

1. [The importance of encryption and how AWS can help by Ken Beer](https://aws.amazon.com/blogs/security/importance-of-encryption-and-how-aws-can-help/)

# 3. Encrytion for SNS

[Encrypting messages published to Amazon SNS with AWS KMS by Michelle Mercier and Otavio Ferreira](https://aws.amazon.com/blogs/compute/encrypting-messages-published-to-amazon-sns-with-aws-kms/)

# 4. Encrytion for S3

1. [Encrypting existing Amazon S3 objects with the AWS CLI by Andrew Guthrie](https://aws.amazon.com/blogs/storage/encrypting-existing-amazon-s3-objects-with-the-aws-cli/)
1. [Changing your Amazon S3 encryption from S3-Managed to AWS KMS by Charlie Llewellyn](https://aws.amazon.com/blogs/storage/changing-your-amazon-s3-encryption-from-s3-managed-encryption-sse-s3-to-aws-key-management-service-sse-kms/)

# 5. single-Region or multi-Region KMS key

1. [Choose the right type of AWS KMS key to encrypt Amazon RDS and Aurora Global Database by Siva Subramaniam and Robert Daly](https://aws.amazon.com/blogs/database/choose-the-right-type-of-aws-kms-key-to-encrypt-amazon-rds-and-aurora-global-database/)

# 6. Customer managed keys vs AWS managed keys vs AWS owned keys

1. [Choose the right type of AWS KMS key to encrypt Amazon RDS and Aurora Global Database by Siva Subramaniam and Robert Daly](https://aws.amazon.com/blogs/database/choose-the-right-type-of-aws-kms-key-to-encrypt-amazon-rds-and-aurora-global-database/)
2. [Customer managed keys](https://docs.aws.amazon.com/kms/latest/developerguide/concepts.html#customer-cmk)
3. [AWS managed keys](https://docs.aws.amazon.com/kms/latest/developerguide/concepts.html#aws-managed-cmk)
4. [AWS owned keys](https://docs.aws.amazon.com/kms/latest/developerguide/concepts.html#aws-owned-cmk)

# 7. Sample Applications

## 7.1. Customer-managed AWS KMS Key usage

1. [Strengthening data security in AWS Step Functions with a customer-managed AWS KMS key by Dhiraj Mahapatro](https://aws.amazon.com/blogs/compute/strengthening-data-security-in-aws-step-functions-with-a-customer-managed-aws-kms-key/)
2. [Sample stock trading application to showcase customer-managed AWS KMS Key usage in Step Functions](https://github.com/aws-samples/aws-stepfunctions-examples/tree/main/sam/app-sfn-kms-integration)

# 8. Envelop Encryption / Symmetric encryption

Using DEKs (Data encryption keys)

1. [The importance of encryption and how AWS can help by Ken Beer](https://aws.amazon.com/blogs/security/importance-of-encryption-and-how-aws-can-help/)
1. [AWS KMS and Envelope Encryption](https://lobster1234.github.io/2017/09/29/aws-kms-envelope-encryption/)
1. [[JAVA][SDK] How to Use the New AWS Encryption SDK to Simplify Data Encryption and Improve Application Availability by Greg Rubin](https://aws.amazon.com/blogs/security/how-to-use-the-new-aws-encryption-sdk-to-simplify-data-encryption-and-improve-application-availability/)
1. [[SAMPLE] Encryption with AWS KMS](https://github.com/aws-samples/aws-kms-workshop/blob/master/Section-2-Encryption-with-AWS-KMS.md)

# 9. Asymmetric keys

1. [How to use AWS KMS RSA keys for offline encryption by Patrick Palmer](https://aws.amazon.com/blogs/security/how-to-use-aws-kms-rsa-keys-for-offline-encryption/)

# 11. Encryption Java SDK

1. [Encryption SDK - Data Key Caching](https://learn.cantrill.io/courses/1723753/lectures/39253241)

1. [[JAVA] How to Use the New AWS Encryption SDK to Simplify Data Encryption and Improve Application Availability by Greg Rubin](https://aws.amazon.com/blogs/security/how-to-use-the-new-aws-encryption-sdk-to-simplify-data-encryption-and-improve-application-availability/)

# 12. How to choose policies for KMS keys

# 13. Bucket Keys

How to use buckets to reduce the cost of using `SSE-KMS`

Before

<img src="./images/aws-kms-1.png" title="aws-kms-1.png" width="900"/>

After

<img src="./images/aws-kms-2.png" title="aws-kms-2.png" width="900"/>

1. [AWS Certified Security - Specialty > Bucket Keys](https://learn.cantrill.io/courses/1723753/lectures/39253233)
1. [Reducing AWS Key Management Service costs by up to 99% with Amazon S3 Bucket Keys by Will Cavin](https://aws.amazon.com/blogs/storage/reducing-aws-key-management-service-costs-by-up-to-99-with-s3-bucket-keys/)

# 14. Samples to curate (categorize)

1. /Volumes/Lexar/git-repos/aws-repo/my-github/study-guide-repo/my-pet-projects/kms/enable-bucket-key-example-main

# 15. Digital Signing

1. [[BASICS] AWS Certified Security - Specialty > Digital Signing using KMS](https://learn.cantrill.io/courses/1723753/lectures/39253239)
1. [Digital signing with the new asymmetric keys feature of AWS KMS by Raj Copparapu](https://aws.amazon.com/blogs/security/digital-signing-asymmetric-keys-aws-kms/)
1. [How to verify AWS KMS asymmetric key signatures locally with OpenSSL by J.D. Bean ](https://aws.amazon.com/blogs/security/how-to-verify-aws-kms-asymmetric-key-signatures-locally-with-openssl/)

# 16. Key Material (BYOK)

1. [Demystifying KMS keys operations, bring your own key (BYOK), custom key store, and ciphertext portability by Arthur Mnev](https://aws.amazon.com/blogs/security/demystifying-kms-keys-operations-bring-your-own-key-byok-custom-key-store-and-ciphertext-portability/)