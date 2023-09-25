<h1>S3 Backlog</H1>

<!-- TOC -->

- [1. Access tiers](#1-access-tiers)
- [2. Access Control](#2-access-control)
- [3. Cross Account Access](#3-cross-account-access)
- [4. Encryption](#4-encryption)
- [5. Query S3](#5-query-s3)
- [6. Pre-Signed URLs](#6-pre-signed-urls)
- [7. Replication](#7-replication)
- [8. Java examples](#8-java-examples)
- [9. S3 endpoints](#9-s3-endpoints)
- [10. Static WebSites](#10-static-websites)
- [11. Storage Classes](#11-storage-classes)
- [Tools](#tools)
- [12. Uploading](#12-uploading)
- [13. Workshops](#13-workshops)

<!-- /TOC -->

# 1. Access tiers

1. [Amazon S3 Intelligent Tiering](https://blog.awsfundamentals.com/amazon-s3-intelligent-tiering)

# 2. Access Control
1. [Amazon S3 Access Control - IAM Policies, Bucket Policies and ACLs](https://www.youtube.com/watch?v=xFzJw6wJ8eY&t=16s)

# 3. Cross Account Access
1. [Cross-Account Access to Amazon S3 | AWS IAM By Neil Davis](https://www.youtube.com/watch?v=HP8XSRWrFQc)

# 4. Encryption
1. [workshop - Encryption On AWS - Tutorial](https://catalog.us-east-1.prod.workshops.aws/workshops/aad9ff1e-b607-45bc-893f-121ea5224f24/en-US/s3/serverside/sses3)
2. [[MY NEXT] Difference between AWS S3 Bucket Encryption SSE-C , SSE-S3, SSE-KMS](https://awstip.com/5-minutes-to-aws-s3-bucket-encryption-sse-c-sse-s3-sse-kms-e2fb07b05cb3)
3. [Secure AWS S3 with KMS Encryption By Neil Davis](https://www.youtube.com/watch?v=uqyf66kgB94)
4. [Amazon S3 Client-Side Authenticated Encryption by Hanson Char](https://aws.amazon.com/blogs/developer/amazon-s3-client-side-authenticated-encryption/)
5. [Taming client-side key rotation with the Amazon S3 encryption client by Hanson Char ](https://aws.amazon.com/blogs/developer/taming-client-side-key-rotation-with-the-amazon-s3-encryption-client/)
6. [Amazon S3 Client-side Key Migration to AWS Key Management Service by Hanson Char](https://aws.amazon.com/blogs/developer/amazon-s3-client-side-key-migration-to-aws-key-management-service/)
7. [Why am I getting an HTTP 403 Forbidden error when I try to upload files using the Amazon S3 console?](https://www.youtube.com/watch?v=rn4qLXhMesg)

# 5. Query S3

1. [Amazon S3 Select - Querying data without servers or databases](https://catalog.us-east-1.prod.workshops.aws/workshops/c85a4d91-2b80-4155-948d-bbdc200567a6/en-US)

# 6. Pre-Signed URLs
1. [Generating Amazon S3 Pre-signed URLs with SSE (Part 1) by Hanson Char ](https://aws.amazon.com/blogs/developer/generating-amazon-s3-pre-signed-urls-with-sse-part-1/)
1. [Generating Amazon S3 Pre-signed URLs with SSE-KMS (Part 2) by Hanson Char](https://aws.amazon.com/blogs/developer/generating-amazon-s3-pre-signed-urls-with-sse-kms-part-2/)
1. [Generating Amazon S3 Pre-signed URLs with SSE-C (Part 4) by Hanson Char](https://aws.amazon.com/blogs/developer/generating-amazon-s3-pre-signed-urls-with-sse-c-part-4/)
1. [Generating Amazon S3 Pre-signed URLs with SSE-S3 (Part 3) by Hanson Char ](https://aws.amazon.com/blogs/developer/generating-amazon-s3-pre-signed-urls-with-sse-s3-part-3/)
1. [Generating Amazon S3 Pre-signed URLs with SSE-C (Part 5 Finale) by Hanson Char ](https://aws.amazon.com/blogs/developer/generating-amazon-s3-pre-signed-urls-with-sse-c-part-5-finale/)
1. [S3 URI Parsing is now available in AWS SDK for Java 2.x by David Ho](https://aws.amazon.com/blogs/devops/s3-uri-parsing-is-now-available-in-aws-sdk-for-java-2-x/)

# 7. Replication
1. [Configuring Amazon S3 Cross-Region Replication (CRR) and Same-Region Replication (SRR) By Neil Davis](https://www.youtube.com/watch?v=trmicgGpmd4&t=6s)

# 8. Java examples

1. [Amazon S3 Developer Guide](https://github.com/alexdebrie/amazon-s3-developer-guide/tree/master/code_examples/java_examples/S3Examples)

# 9. S3 endpoints

1. [See aws-privatelink.md](./aws-privatelink.md)
1. [Secure Hybrid Access to S3 using VPC Endpoints](https://catalog.us-east-1.prod.workshops.aws/workshops/3a8d4ddf-66c5-4d26-ae6f-6292a517f46c/en-US)

# 10. Static WebSites

1. [Hosting Internal HTTPS Static Websites with ALB, S3, and PrivateLink by Schuyler Jager](https://aws.amazon.com/blogs/networking-and-content-delivery/hosting-internal-https-static-websites-with-alb-s3-and-privatelink/)

<img src="./images/Networking_NetCDNBlog-430-v2.jpg" title="Networking_NetCDNBlog-430-v2.jpg" width="900"/>

# 11. Storage Classes

1. [Amazon S3 Storage Classes | AWS S3](https://www.youtube.com/watch?v=EqqtzKqewaA)
1. [Getting started using the Amazon S3 Glacier storage classes](https://aws.amazon.com/getting-started/hands-on/getting-started-using-amazon-s3-glacier-storage-classes/)

# Tools

1. [Amazon S3 Bundler](https://github.com/jstrunk/s3bundler)
- "Before moving Amazon DocumentDB events to S3 Glacier, you should consider bundling several events into larger objects" by using above tool

# 12. Uploading

1. [Patterns for building an API to upload files to Amazon S3 by Thomas Moore,](https://aws.amazon.com/blogs/compute/patterns-for-building-an-api-to-upload-files-to-amazon-s3/)

# 13. Workshops

1. [START HERE](https://catalog.us-east-1.prod.workshops.aws/workshops/aad9ff1e-b607-45bc-893f-121ea5224f24/en-US/s3)
1. [Encryption On AWS - Tutorial for S3](https://catalog.us-east-1.prod.workshops.aws/workshops/aad9ff1e-b607-45bc-893f-121ea5224f24/en-US/s3)
    - Server-side encryption - SSE-S3
    - Server-side encryption  - SSE-KMS
    - Server-side encryption - SSE-C
    - Client-side encryption
1. [Amazon S3: Data Encryption Options (use this video along with s3 encryption workshop)](https://www.youtube.com/watch?v=U1USUvvhuCY)
1. [Amazon S3 Multi-Region Access Points](https://catalog.workshops.aws/s3multiregionaccesspoints/en-US)
1. [Applying Attribute Based Access Control in AWS - Workshop](https://catalog.workshops.aws/applying-abac/en-US)
1. [Finding And Remediating Misconfigurations in S3](https://trendmicro.awsworkshop.io/ee/60_finding_and_remediating/6001_s3_bucket.html)


