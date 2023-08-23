<h1>S3 Backlog</H1>

<!-- TOC -->

- [1. Encryption](#1-encryption)
- [2. Workshops](#2-workshops)
- [3. Pre-Signed URLs](#3-pre-signed-urls)
- [4. Uploading](#4-uploading)
- [5. Access Control](#5-access-control)
- [6. Replication](#6-replication)
- [7. Cross Account Access](#7-cross-account-access)
- [8. Java examples](#8-java-examples)
- [9. Access tiers](#9-access-tiers)
- [S3 endpoints](#s3-endpoints)
- [11. Workshops](#11-workshops)

<!-- /TOC -->

1. [S3 URI Parsing is now available in AWS SDK for Java 2.x by David Ho](https://aws.amazon.com/blogs/devops/s3-uri-parsing-is-now-available-in-aws-sdk-for-java-2-x/)

# 1. Encryption

1. [workshop - Encryption On AWS - Tutorial](https://catalog.us-east-1.prod.workshops.aws/workshops/aad9ff1e-b607-45bc-893f-121ea5224f24/en-US/s3/serverside/sses3)
2. [[MY NEXT] Difference between AWS S3 Bucket Encryption SSE-C , SSE-S3, SSE-KMS](https://awstip.com/5-minutes-to-aws-s3-bucket-encryption-sse-c-sse-s3-sse-kms-e2fb07b05cb3)
3. [Secure AWS S3 with KMS Encryption By Neil Davis](https://www.youtube.com/watch?v=uqyf66kgB94)
4. [Amazon S3 Client-Side Authenticated Encryption by Hanson Char](https://aws.amazon.com/blogs/developer/amazon-s3-client-side-authenticated-encryption/)
5. [Taming client-side key rotation with the Amazon S3 encryption client by Hanson Char ](https://aws.amazon.com/blogs/developer/taming-client-side-key-rotation-with-the-amazon-s3-encryption-client/)
6. [Amazon S3 Client-side Key Migration to AWS Key Management Service by Hanson Char](https://aws.amazon.com/blogs/developer/amazon-s3-client-side-key-migration-to-aws-key-management-service/)
7. [Why am I getting an HTTP 403 Forbidden error when I try to upload files using the Amazon S3 console?](https://www.youtube.com/watch?v=rn4qLXhMesg)

# 2. Workshops

1. [START HERE](https://catalog.us-east-1.prod.workshops.aws/workshops/aad9ff1e-b607-45bc-893f-121ea5224f24/en-US/s3)
3. https://trendmicro.awsworkshop.io/ee/60_finding_and_remediating/6001_s3_bucket.html

# 3. Pre-Signed URLs
1. [Generating Amazon S3 Pre-signed URLs with SSE (Part 1) by Hanson Char ](https://aws.amazon.com/blogs/developer/generating-amazon-s3-pre-signed-urls-with-sse-part-1/)
2. [Generating Amazon S3 Pre-signed URLs with SSE-KMS (Part 2) by Hanson Char](https://aws.amazon.com/blogs/developer/generating-amazon-s3-pre-signed-urls-with-sse-kms-part-2/)
3. [Generating Amazon S3 Pre-signed URLs with SSE-C (Part 4) by Hanson Char](https://aws.amazon.com/blogs/developer/generating-amazon-s3-pre-signed-urls-with-sse-c-part-4/)
4. [Generating Amazon S3 Pre-signed URLs with SSE-S3 (Part 3) by Hanson Char ](https://aws.amazon.com/blogs/developer/generating-amazon-s3-pre-signed-urls-with-sse-s3-part-3/)
5. [Generating Amazon S3 Pre-signed URLs with SSE-C (Part 5 Finale) by Hanson Char ](https://aws.amazon.com/blogs/developer/generating-amazon-s3-pre-signed-urls-with-sse-c-part-5-finale/)

# 4. Uploading

1. [Patterns for building an API to upload files to Amazon S3 by Thomas Moore,](https://aws.amazon.com/blogs/compute/patterns-for-building-an-api-to-upload-files-to-amazon-s3/)

# 5. Access Control
1. [Amazon S3 Access Control - IAM Policies, Bucket Policies and ACLs](https://www.youtube.com/watch?v=xFzJw6wJ8eY&t=16s)

# 6. Replication
1. [Configuring Amazon S3 Cross-Region Replication (CRR) and Same-Region Replication (SRR) By Neil Davis](https://www.youtube.com/watch?v=trmicgGpmd4&t=6s)

# 7. Cross Account Access
1. [Cross-Account Access to Amazon S3 | AWS IAM By Neil Davis](https://www.youtube.com/watch?v=HP8XSRWrFQc)

# 8. Java examples

1. [Amazon S3 Developer Guide](https://github.com/alexdebrie/amazon-s3-developer-guide/tree/master/code_examples/java_examples/S3Examples)

# 9. Access tiers

1. [Amazon S3 Intelligent Tiering](https://blog.awsfundamentals.com/amazon-s3-intelligent-tiering)

# S3 endpoints

1. [[MY NEXT] Best practices for using Amazon S3 endpoints with AWS CloudFormation by Tony Bulding](https://aws.amazon.com/blogs/infrastructure-and-automation/best-practices-for-using-amazon-s3-endpoints-in-aws-cloudformation-templates/)
- difference between path-style and virtual-hosted-style endpoints
- code: /Volumes/Lexar/git-repos/aws-repo/aws-samples/s3/s3-endpoints-and-cfn

<img src="./images/s3-endpoints-1.png" title="s3-endpoints-1.png" width="900"/>

# 11. Workshops

"Encryption On AWS - Tutorial for S3
- Server-side encryption - SSE-S3
- Server-side encryption  - SSE-KMS
- Server-side encryption - SSE-C
- Client-side encryption"
https://catalog.us-east-1.prod.workshops.aws/workshops/aad9ff1e-b607-45bc-893f-121ea5224f24/en-US/s3

Amazon S3: Data Encryption Options (use this video along with s3 encryption workshop)	https://www.youtube.com/watch?v=U1USUvvhuCY

Amazon S3 Multi-Region Access Points	https://catalog.workshops.aws/s3multiregionaccesspoints/en-US

Applying Attribute Based Access Control in AWS - Workshop	https://catalog.workshops.aws/applying-abac/en-US

Amazon S3 Select - Querying data without servers or databases	https://catalog.us-east-1.prod.workshops.aws/workshops/c85a4d91-2b80-4155-948d-bbdc200567a6/en-US

Secure Hybrid Access to S3 using VPC Endpoints	https://catalog.us-east-1.prod.workshops.aws/workshops/3a8d4ddf-66c5-4d26-ae6f-6292a517f46c/en-US

Getting started using the Amazon S3 Glacier storage classes	https://aws.amazon.com/getting-started/hands-on/getting-started-using-amazon-s3-glacier-storage-classes/?trk=gs_card