<h1>S3 Encryption</h1>

# S3 does SSE encryption by default

S3 no longer supports unencrypted objects

1. [Amazon S3 Encrypts New Objects By Default by Sébastien Stormacq](https://aws.amazon.com/blogs/aws/amazon-s3-encrypts-new-objects-by-default/)

# Different types of encryption in S3

SSE-S3 / SSE-KMS / SSE-C / Client-side encryption

1. [Protecting data with encryption](https://docs.aws.amazon.com/AmazonS3/latest/userguide/UsingEncryption.html)
2. [[**START_HERE**][OLD] How to Prevent Uploads of Unencrypted Objects to Amazon S3 by Michael St. Onge](https://aws.amazon.com/blogs/security/how-to-prevent-uploads-of-unencrypted-objects-to-amazon-s3/)
3. [[**START_HERE**][OLD] All you need to know about encrypting Amazon S3 buckets](https://blog.jineshkumar.com/all-you-need-to-know-about-encrypting-amazon-s3-buckets)
4. [[**MY_NEXT**] Difference between AWS S3 Bucket Encryption SSE-C , SSE-S3, SSE-KMS](https://awstip.com/5-minutes-to-aws-s3-bucket-encryption-sse-c-sse-s3-sse-kms-e2fb07b05cb3)

# SSE

1. [ncryption On AWS - Tutorial > S3 > Server-side encryption > SSE-S3](https://catalog.us-east-1.prod.workshops.aws/workshops/aad9ff1e-b607-45bc-893f-121ea5224f24/en-US/s3/serverside/sses3)

# SSE-KMS

1. [Encryption On AWS - Tutorial > S3 > Server-side encryption > SSE-KMS](https://catalog.us-east-1.prod.workshops.aws/workshops/aad9ff1e-b607-45bc-893f-121ea5224f24/en-US/s3/serverside/ssekms)

# SSE-C

1. [Encryption On AWS - Tutorial > S3 > Server-side encryption > SSE-C](https://catalog.us-east-1.prod.workshops.aws/workshops/aad9ff1e-b607-45bc-893f-121ea5224f24/en-US/s3/serverside/ssec)

# Client Side encryption

1. https://github.com/aws/amazon-s3-encryption-client-java
2. [Protecting data using client-side encryption](https://github.com/awsdocs/amazon-s3-developer-guide/blob/master/doc_source/UsingClientSideEncryption.md)
2. [Client-Side Data Encryption with the AWS SDK for Java and Amazon S3](https://aws.amazon.com/articles/client-side-data-encryption-with-the-aws-sdk-for-java-and-amazon-s3/)

## explore

- "use a master key that you store within your application" mentioned here `https://catalog.us-east-1.prod.workshops.aws/workshops/aad9ff1e-b607-45bc-893f-121ea5224f24/en-US/s3/clientside`

# Curate

1. [Secure AWS S3 with KMS Encryption By Neil Davis](https://www.youtube.com/watch?v=uqyf66kgB94)
1. [Amazon S3 Client-Side Authenticated Encryption by Hanson Char](https://aws.amazon.com/blogs/developer/amazon-s3-client-side-authenticated-encryption/)
1. [Taming client-side key rotation with the Amazon S3 encryption client by Hanson Char ](https://aws.amazon.com/blogs/developer/taming-client-side-key-rotation-with-the-amazon-s3-encryption-client/)
1. [Amazon S3 Client-side Key Migration to AWS Key Management Service by Hanson Char](https://aws.amazon.com/blogs/developer/amazon-s3-client-side-key-migration-to-aws-key-management-service/)
1. [Why am I getting an HTTP 403 Forbidden error when I try to upload files using the Amazon S3 console?](https://www.youtube.com/watch?v=rn4qLXhMesg)
1. [Encryption On AWS - Tutorial for S3](https://catalog.us-east-1.prod.workshops.aws/workshops/aad9ff1e-b607-45bc-893f-121ea5224f24/en-US/s3)
    - Server-side encryption - SSE-S3
    - Server-side encryption  - SSE-KMS
    - Server-side encryption - SSE-C
    - Client-side encryption