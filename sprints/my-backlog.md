
<!-- TOC -->

- [1. Start Here](#1-start-here)
- [2. CDK Examples](#2-cdk-examples)
- [3. S3 Encryption](#3-s3-encryption)
- [4. Step Functions](#4-step-functions)
- [5. DocumentDB](#5-documentdb)
- [6. Networking](#6-networking)
  - [6.1. Hub and Spoke](#61-hub-and-spoke)
  - [6.2. Networking sessions](#62-networking-sessions)
- [7. Cloud Watch](#7-cloud-watch)
- [8. DynamoDB](#8-dynamodb)
- [9. IAM](#9-iam)
- [10. System Design](#10-system-design)
- [11. Private Endpoints](#11-private-endpoints)
- [12. Organizations](#12-organizations)
- [13. Lambda](#13-lambda)
- [14. Books](#14-books)
- [15. Event Driven](#15-event-driven)
- [16. Patterns](#16-patterns)
- [17. Fargate](#17-fargate)
- [18. Java SDK examples](#18-java-sdk-examples)
- [19. ECS](#19-ecs)
- [20. RDS Proxy](#20-rds-proxy)

<!-- /TOC -->

# 1. Start Here

1. [[BOOK] SpringBoot XA Transaction Heuristics]
2. [[WORKSHOP] - AWS CloudFormation Workshop](https://catalog.workshops.aws/cfn101/en-US)
- Nested/ Layer Stacks
3. [Automation in AWS with CloudFormation, CLI, and SDKs By Richard A. Jones](https://learning.oreilly.com/videos/automation-in-aws/9780134818313/)
- Good place to brush up
4. [[READ THIS BOOK] AWS System Administration Mike Ryan, Federico Lucifredi](https://learning.oreilly.com/library/view/aws-system-administration/9781449342562/foreword01.html)

# 2. CDK Examples

1. [CDK Patterns](https://github.com/cdk-patterns/serverless/tree/main)
2. [AWS CDK Advanced Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/d93fec4c-fb0f-4813-ac90-758cb5527f2f/en-US)

# 3. S3 Encryption

1. [Difference between AWS S3 Bucket Encryption SSE-C , SSE-S3, SSE-KMS](https://awstip.com/5-minutes-to-aws-s3-bucket-encryption-sse-c-sse-s3-sse-kms-e2fb07b05cb3)

# 4. Step Functions

1. [[MY NEXT] Building Serverless Land: Part 1 – Automating content aggregation by Benjamin Smith ](https://aws.amazon.com/blogs/compute/building-serverless-land-part-1-automating-content-aggregation/)

# 5. DocumentDB

1. [[MY NEXT]Amazon DocumentDB (with MongoDB compatibility) read autoscaling by Randy DeFauw](https://aws.amazon.com/blogs/database/amazon-documentdb-with-mongodb-compatibility-read-autoscaling/)
- Study thePY scripts

# 6. Networking

## 6.1. Hub and Spoke

1. [[MY NEXT] Centralize access using VPC interface endpoints to access AWS services across multiple VPCs by Chetan Agrawal](https://aws.amazon.com/blogs/networking-and-content-delivery/centralize-access-using-vpc-interface-endpoints/)
2. [/Volumes/Lexar/git-repos/aws-repo/my-aws-samples/networking/privatelink/[TODO] sqs-endpoint/SQS-VPCE-Tutorial-CloudFormation.yaml](/Volumes/Lexar/git-repos/aws-repo/my-aws-samples/networking/privatelink/[TODO] sqs-endpoint/SQS-VPCE-Tutorial-CloudFormation.yaml)
- https://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/sqs-sending-messages-from-vpc.html

## 6.2. Networking sessions

1. [AWS re:Invent 2022 - Application networking foundations (NET204)](https://www.youtube.com/watch?v=WcZwWuq6FTk&t=10s)

# 7. Cloud Watch

1. [[MY NEXT] AWS CloudWatch and Systems Manager Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/a8e9c6a6-0ba9-48a7-a90d-378a440ab8ba/en-US)

# 8. DynamoDB

1. [DynamoDB Book](DynamoDB Book By Alex)
2. [ Amazon DynamoDB Labs ](https://amazon-dynamodb-labs.com/hands-on-labs.html)
3. [[MY NEXT] DynamoDB: Its purpose, main features, and key concepts | Jason Hunter | AWS Events](https://www.youtube.com/watch?v=ummOosOW4lE)
4. [[MY NEXT] DynamoDB: Under the hood, managing throughput, advanced design patterns | Jason Hunter | AWS Events](https://www.youtube.com/watch?v=0iGR8GnIItQ)
5. [[MY NEXT] Implementing priority queueing with Amazon DynamoDB by Zoran Ivanovic](https://aws.amazon.com/blogs/database/implementing-priority-queueing-with-amazon-dynamodb/)
6. [[MY NEXT] Use parallelism to optimize querying large amounts of data in Amazon DynamoDB by Zoran Ivanovic](https://aws.amazon.com/blogs/database/use-parallelism-to-optimize-querying-large-amounts-of-data-in-amazon-dynamodb/)
7. [Choosing the Right DynamoDB Partition Key by Gowri Balasubramanian and Sean Shriver ](https://aws.amazon.com/blogs/database/choosing-the-right-dynamodb-partition-key/)
8. [[MY NEXT][MUST SEE] A set of DynamoDB demo scripts and sample data that illustrate the read and write cost of various data access patterns By Rob McCauley](https://github.com/robm26/efficiencydemos)
- [Data Modeling with Amazon DynamoDB- AWS Database in 15 By Rob McCauley](https://www.youtube.com/watch?v=kQ-DSjtCb90)
- [DynamoDB Office Hours - Designing for Cost with Rob McCauley](https://www.youtube.com/watch?v=S02CRffcoX8)
- [[START HERE] Data Modeling with DynamoDB Workshop - AWS Virtual Workshop with Rob McCauley and Chad Tindel](https://www.youtube.com/watch?v=p8phVh6HRkQ)

# 9. IAM

1. [IAM policy evaluation workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/6dc3124a-6bd4-46eb-b5c4-be438a82ba3d/en-US)

# 10. System Design

1. Plan to read `/Volumes/Lexar/git-repos/aws-repo/books/system-design/System Design Interview An Insider’s Guide by Alex Xu (z-lib.org).pdf`
2. `/Volumes/Lexar/git-repos/aws-repo/books/system-design/ByteByteGo_LinkedIn_PDF.pdf`

# 11. Private Endpoints

1. [Tutorial: Sending a message to an Amazon SQS queue from Amazon Virtual Private Cloud](/Volumes/Lexar/git-repos/aws-repo/my-aws-samples/networking/privatelink/sqs-endpoint/my-readme.md)

# 12. Organizations

1. [Workshop AWS Account Setup](/Volumes/Lexar/git-repos/aws-repo/my-aws-workshops/iam/organizations/1-AWSAccountSetup/my-readme.md)

# 13. Lambda

1. [SpringBoot on Lambda By James Eastham](https://github.com/jeastham1993/java-spring-on-lambda)
- https://www.youtube.com/watch?v=eierYzOAyg8
2. [AWS Lambda series by Dan Vega](https://www.youtube.com/watch?v=bxK4GscuVgs&list=PLZV0a2jwt22vS1QAp0XJsFACeLNL5oN1g)

# 14. Books

1. [Stratospheric - From Zero to Production with Spring Boot and AWS](file:////Volumes/Lexar/books/aws/stratospheric/stratospheric-2023-04-21.pdf)

# 15. Event Driven

1. [Building a modern, event-driven application for insurance claims processing – Part 1 by Emily Shea and Dhiraj Mahapatro](https://aws.amazon.com/blogs/industries/building-a-modern-event-driven-application-for-insurance-claims-processing-part-1/)
2. [Extending a serverless, event-driven architecture to existing container workloads by Dhiraj Mahapatro](https://aws.amazon.com/blogs/compute/extending-a-serverless-event-driven-architecture-to-existing-container-workloads/)

# 16. Patterns

2. [Build a near real-time data aggregation pipeline using a serverless, event-driven architecture by Lucas Rettenmeier and Kirill Bogdanov](https://aws.amazon.com/blogs/database/build-a-near-real-time-data-aggregation-pipeline-using-a-serverless-event-driven-architecture/)
1. [Build a fault-tolerant, serverless data aggregation pipeline with exactly-once processing by Lucas Rettenmeier and Kirill Bogdanov](https://aws.amazon.com/blogs/database/build-a-fault-tolerant-serverless-data-aggregation-pipeline-with-exactly-once-processing/)

# 17. Fargate

1. [[MUST SEE] Run message-driven workloads at scale by using AWS Fargate](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/run-message-driven-workloads-at-scale-by-using-aws-fargate.html)
2. [[MUST SEE] Run event-driven and scheduled workloads at scale with AWS Fargate](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/run-event-driven-and-scheduled-workloads-at-scale-with-aws-fargate.html)

# 18. Java SDK examples

1. [[MY NEXT] AWS SDK for Java (v2) code examples](https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2))

# 19. ECS

1. [[TRY EXAMPLE] Deploying Java Microservices on Amazon Elastic Container Service by Nathan Taber](https://aws.amazon.com/blogs/compute/deploying-java-microservices-on-amazon-ec2-container-service/)
- Study the CFN template and PY script

# 20. RDS Proxy

1. [[MUST SEE] Amazon API Gateway HTTP API to AWS Lambda to RDS Proxy By Pankaj Agarwal](https://serverlessland.com/patterns/apigw-http-api-lambda-rds-proxy)
-  https://github.com/aws-samples/serverless-patterns/tree/main/apigw-http-api-lambda-rds-proxy
2. [Pankaj Agarwal shows with and without proxy](https://github.com/aws-samples/serverless-rds-proxy-demo)
- /Volumes/Lexar/git-repos/aws-repo/my-aws-samples/lambda/pooling/serverless-rds-proxy-demo/my-readme.md
3. [8.4: Use RDS Proxy to minimize failover disruptions](https://catalog.us-east-1.prod.workshops.aws/workshops/098605dc-8eee-4e84-85e9-c5c6c9e43de2/en-US/lab8-fault-tolerance/proxy)