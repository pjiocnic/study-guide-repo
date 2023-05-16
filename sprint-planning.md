https://www.epochconverter.com/weeks/2023

<!-- TOC -->

- [1. Topics to visit](#1-topics-to-visit)
- [2. In Progress](#2-in-progress)
- [3. AWS Guides](#3-aws-guides)
  - [3.1. Developer Guides](#31-developer-guides)
  - [3.2. DR](#32-dr)
  - [3.3. EKS](#33-eks)
  - [3.4. Fargate](#34-fargate)
  - [3.5. Lambda](#35-lambda)
  - [3.6. Networking](#36-networking)
    - [3.6.1. Hub and Spoke](#361-hub-and-spoke)
  - [3.7. Patterns](#37-patterns)
  - [3.8. SAM](#38-sam)
  - [3.9. Step Functions](#39-step-functions)
  - [3.10. S3](#310-s3)
  - [3.11. OU](#311-ou)
- [4. Examples to work on](#4-examples-to-work-on)
  - [4.1. Custom Autoscaling](#41-custom-autoscaling)
  - [4.2. Scaling provisioned Concurrency](#42-scaling-provisioned-concurrency)
  - [4.3. Centralized Logging](#43-centralized-logging)
  - [4.4. IAM Authentication for RDS](#44-iam-authentication-for-rds)
  - [4.5. Hub and Spoke](#45-hub-and-spoke)
  - [4.6. Serverless](#46-serverless)
  - [4.7. Review SDK Examples](#47-review-sdk-examples)
  - [4.8. Priority Queues](#48-priority-queues)
  - [4.9. FIFO Queues](#49-fifo-queues)
  - [4.10. Near-Real-Time Event Processing](#410-near-real-time-event-processing)

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
16. Review Lambda examples (Integration with other services) from
- https://docs.aws.amazon.com/lambda/latest/dg/lambda-services.html
- https://docs.aws.amazon.com/lambda/latest/dg/with-sqs.html

# 2. In Progress

1. [Amazon DocumentDB (with MongoDB compatibility) Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/464d6c17-9faa-4fef-ac9f-dd49610174d3/en-US/prerequisites/cloud9)
2. [Workshop AWS Account Setup](https://workshop-aws-account-setup.fstehle.com/)

# 3. AWS Guides

## 3.1. Developer Guides

1. [Amazon Simple Storage Service User Guide](https://docs.aws.amazon.com/s3/index.html)
2. [AWS Lambda Developer Guide](https://docs.aws.amazon.com/lambda/latest/dg/welcome.html)

## 3.2. DR

1. [AWS Prescriptive Guidance - Disaster recovery strategy for databases on AWS](https://docs.aws.amazon.com/prescriptive-guidance/latest/strategy-database-disaster-recovery/welcome.html)

## 3.3. EKS

1. [Deploy and debug Amazon EKS clusters](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/deploy-and-debug-amazon-eks-clusters.html)
2. [Automatically build and deploy a Java application to Amazon EKS using a CI/CD pipeline](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/automatically-build-and-deploy-a-java-application-to-amazon-eks-using-a-ci-cd-pipeline.html)
3. [Deploy a sample Java microservice on Amazon EKS and expose the microservice using an Application Load Balancer](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/deploy-a-sample-java-microservice-on-amazon-eks-and-expose-the-microservice-using-an-application-load-balancer.html)

## 3.4. Fargate

1. [[MUST SEE] Run message-driven workloads at scale by using AWS Fargate](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/run-message-driven-workloads-at-scale-by-using-aws-fargate.html)
2. [[MUST SEE] Run event-driven and scheduled workloads at scale with AWS Fargate](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/run-event-driven-and-scheduled-workloads-at-scale-with-aws-fargate.html)

## 3.5. Lambda

1. [Generate a static outbound IP address using a Lambda function, Amazon VPC, and a serverless architecture](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/generate-a-static-outbound-ip-address-using-a-lambda-function-amazon-vpc-and-a-serverless-architecture.html)
2. [Cache secrets using AWS Lambda extensions](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/cache-secrets-using-aws-lambda-extensions.html)
3. [Testing serverless applications on AWS](https://docs.aws.amazon.com/prescriptive-guidance/latest/serverless-application-testing/introduction.html)
4. [Automate deployment of nested applications using AWS SAM](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/automate-deployment-of-nested-applications-using-aws-sam.html)

## 3.6. Networking

### 3.6.1. Hub and Spoke

1. [Privately access a central AWS service endpoint from multiple VPCs](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/privately-access-a-central-aws-service-endpoint-from-multiple-vpcs.html)

## 3.7. Patterns

1. [Decompose monoliths into microservices by using CQRS and event sourcing](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/decompose-monoliths-into-microservices-by-using-cqrs-and-event-sourcing.html)

## 3.8. SAM

1. [Deploy multiple-stack applications using AWS CDK with TypeScript](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/deploy-multiple-stack-applications-using-aws-cdk-with-typescript.html)
2. [Automate deployment of nested applications using AWS SAM](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/automate-deployment-of-nested-applications-using-aws-sam.html)

## 3.9. Step Functions
1. [Implement the serverless saga pattern by using AWS Step Functions](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/implement-the-serverless-saga-pattern-by-using-aws-step-functions.html)

## 3.10. S3
1. [Copy data from an S3 bucket to another account and Region by using the AWS CLI](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/copy-data-from-an-s3-bucket-to-another-account-and-region-by-using-the-aws-cli.html)

## 3.11. OU

1. [OU structure in regulated AWS landing zones: an example from the pharmaceutical industry](https://docs.aws.amazon.com/prescriptive-guidance/latest/ou-structure-landing-zone/introduction.html)

# 4. Examples to work on

## 4.1. Custom Autoscaling
1. [Amazon DocumentDB (with MongoDB compatibility) read autoscaling by Randy DeFauw](https://aws.amazon.com/blogs/database/amazon-documentdb-with-mongodb-compatibility-read-autoscaling/)

## 4.2. Scaling provisioned Concurrency

See Lambda-backlog.md

## 4.3. Centralized Logging
2. [Stream Amazon CloudWatch Logs to a Centralized Account for Audit and Analysis by David Bailey](https://aws.amazon.com/blogs/architecture/stream-amazon-cloudwatch-logs-to-a-centralized-account-for-audit-and-analysis/)

## 4.4. IAM Authentication for RDS
3. [How to securely provide database credentials to Lambda functions by using AWS Secrets Manager by Ramesh Adabala, Anand Komandooru, and Noorul Hasan](https://aws.amazon.com/blogs/security/how-to-securely-provide-database-credentials-to-lambda-functions-by-using-aws-secrets-manager/)

## 4.5. Hub and Spoke
[[MY NEXT] Centralize access using VPC interface endpoints to access AWS services across multiple VPCs by Chetan Agrawal](https://aws.amazon.com/blogs/networking-and-content-delivery/centralize-access-using-vpc-interface-endpoints/)

## 4.6. Serverless
1. [AWS Serverless Ecommerce Platform](https://github.com/aws-samples/aws-serverless-ecommerce-platform)
    - [Scheduling AWS Lambda Provisioned Concurrency for recurring peak usage by Chris Munns](https://aws.amazon.com/blogs/compute/scheduling-aws-lambda-provisioned-concurrency-for-recurring-peak-usage/)

## 4.7. Review SDK Examples
1. [https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2](https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2)

## 4.8. Priority Queues

1. [[MY NEXT] Implementing priority queueing with Amazon DynamoDB by Zoran Ivanovic](https://aws.amazon.com/blogs/database/implementing-priority-queueing-with-amazon-dynamodb/)

## 4.9. FIFO Queues

1. [Implement serverless FIFO queues with filtering capabilities using Amazon DynamoDB transactions by Nikhil Penmetsa and Randy DeFauw](https://aws.amazon.com/blogs/database/serverless-fifo-queues-filtering-dynamodb-transactions/)

## 4.10. Near-Real-Time Event Processing

1. [How to perform ordered data replication between applications by using Amazon DynamoDB Streams by Aravind Kodandaramaiah](https://aws.amazon.com/blogs/database/how-to-perform-ordered-data-replication-between-applications-by-using-amazon-dynamodb-streams/)