https://www.epochconverter.com/weeks/2023

<!-- TOC -->

- [1. Topics to visit](#1-topics-to-visit)
- [2. Examples to work on](#2-examples-to-work-on)
  - [2.1. Custom Autoscaling](#21-custom-autoscaling)
  - [2.2 Scaling provisioned Concurrency](#22-scaling-provisioned-concurrency)
  - [2.2. Centralized Logging](#22-centralized-logging)
  - [2.3. IAM Authentication for RDS](#23-iam-authentication-for-rds)
  - [2.4. Hub and Spoke](#24-hub-and-spoke)
  - [2.5. Serverless](#25-serverless)
  - [2.6. Review SDK Examples](#26-review-sdk-examples)
  - [2.7. Priority Queues](#27-priority-queues)
  - [2.8. FIFO Queues](#28-fifo-queues)
  - [2.9 Near-Real-Time Event Processing](#29-near-real-time-event-processing)

<!-- /TOC -->

# 1. Topics to visit

1. FIFO queue
2. Priority Queues
3. AWS PowerTools
4. Step fns
5. elasticcache: Whitepaper; hiemdall proxy
6. documentdb: scaling the read replicas, indexes
7. centralized logging
8. kinesis: using lambda as consumer
9. eks workshop
10. AWS Cookbook: Mornings?
11. multi-service demos	"Need a way to record all example URLs
- https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2"
12. documentdb dev guide
13. Event broker example by mohapathra
14. Webhooks: From Lambda in action
15. Hands-on with AWS S3 Safari course By Rick

# 2. Examples to work on

## 2.1. Custom Autoscaling
1. [Amazon DocumentDB (with MongoDB compatibility) read autoscaling by Randy DeFauw](https://aws.amazon.com/blogs/database/amazon-documentdb-with-mongodb-compatibility-read-autoscaling/)

## 2.2 Scaling provisioned Concurrency

See Lambda-backlog.md

## 2.2. Centralized Logging
2. [Stream Amazon CloudWatch Logs to a Centralized Account for Audit and Analysis by David Bailey](https://aws.amazon.com/blogs/architecture/stream-amazon-cloudwatch-logs-to-a-centralized-account-for-audit-and-analysis/)

## 2.3. IAM Authentication for RDS
3. [How to securely provide database credentials to Lambda functions by using AWS Secrets Manager by Ramesh Adabala, Anand Komandooru, and Noorul Hasan](https://aws.amazon.com/blogs/security/how-to-securely-provide-database-credentials-to-lambda-functions-by-using-aws-secrets-manager/)

## 2.4. Hub and Spoke
[[MY NEXT] Centralize access using VPC interface endpoints to access AWS services across multiple VPCs by Chetan Agrawal](https://aws.amazon.com/blogs/networking-and-content-delivery/centralize-access-using-vpc-interface-endpoints/)

## 2.5. Serverless
1. [AWS Serverless Ecommerce Platform](https://github.com/aws-samples/aws-serverless-ecommerce-platform)
    - [Scheduling AWS Lambda Provisioned Concurrency for recurring peak usage by Chris Munns](https://aws.amazon.com/blogs/compute/scheduling-aws-lambda-provisioned-concurrency-for-recurring-peak-usage/)

## 2.6. Review SDK Examples
1. [https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2](https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2)

## 2.7. Priority Queues

1. [[MY NEXT] Implementing priority queueing with Amazon DynamoDB by Zoran Ivanovic](https://aws.amazon.com/blogs/database/implementing-priority-queueing-with-amazon-dynamodb/)

## 2.8. FIFO Queues

1. [Implement serverless FIFO queues with filtering capabilities using Amazon DynamoDB transactions by Nikhil Penmetsa and Randy DeFauw](https://aws.amazon.com/blogs/database/serverless-fifo-queues-filtering-dynamodb-transactions/)

## 2.9 Near-Real-Time Event Processing

1. [How to perform ordered data replication between applications by using Amazon DynamoDB Streams by Aravind Kodandaramaiah](https://aws.amazon.com/blogs/database/how-to-perform-ordered-data-replication-between-applications-by-using-amazon-dynamodb-streams/)