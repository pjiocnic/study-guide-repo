
<!-- TOC -->

- [1. Python](#1-python)
- [2. Elastic Load Balancer](#2-elastic-load-balancer)
- [3. DynamoDB](#3-dynamodb)
- [4. Account setup](#4-account-setup)
- [5. Cloudformation](#5-cloudformation)
- [6. Athena](#6-athena)
- [7. Lambda](#7-lambda)
  - [7.1. Using Java](#71-using-java)
- [8. Kinesis](#8-kinesis)
  - [8.1. Kinesis Firehose](#81-kinesis-firehose)
- [9. Serverless](#9-serverless)
  - [9.1. Performance tuning](#91-performance-tuning)
- [10. Microservices](#10-microservices)
- [11. Curate](#11-curate)
- [12. Cloudwatch](#12-cloudwatch)
- [13. IAM](#13-iam)

<!-- /TOC -->

# 1. Python

1. [Learn Python On AWS Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/3d705026-9edc-40e8-b353-bdabb116c89c/en-US)

# 2. Elastic Load Balancer

1. [AWS Elastic Load Balancer Demos](https://exampleloadbalancer.com/)

# 3. DynamoDB

1. [Amazon DynamoDB Labs](https://amazon-dynamodb-labs.com/)
2. [Amazon DynamoDB Labs FastTrack](https://catalog.us-east-1.prod.workshops.aws/workshops/3319b690-3a41-4921-9af8-f31c7bef4cdb/en-US)
3. [Amazon DynamoDB Labs](https://eventbox.dev/published/lesson/dynamodbs-security-101/)
- DynamoDB Data Denormalization
- DynamoDB Access Control
- DynamoDB Processing Options

3. [Build a Serverless Web Application with AWS Lambda, Amazon API Gateway, AWS Amplify, Amazon DynamoDB, and Amazon Cognito](https://aws.amazon.com/getting-started/hands-on/build-serverless-web-app-lambda-apigateway-s3-dynamodb-cognito/)

# 4. Account setup

1. [Workshop AWS Account Setup](https://workshop-aws-account-setup.fstehle.com/)
- [Shared Services account](https://docs.aws.amazon.com/managedservices/latest/userguide/shared-services-account.html)

# 5. Cloudformation

1. [[My NEXT] Highly Available Web Application Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/3de93ad5-ebbe-4258-b977-b45cdfe661f1/en-US)
- Lab 1: Configure the network
- Lab 2: Set up your RDS database using cfn
- Lab 3: Set up Elasticache for Memcached using cfn
- Lab 4: Create the shared filesystem using cfn
- Lab 5: Create the load balancer using cfn
- Lab 6: Create a launch configuration using cfn
- Lab 7: Create the app server using cfn
- Lab 8: Configure caching using cfn
- Lab 9:: Add a Content Delivery Network using cfn
- Lab 10: Chaos testing with AWS Fault Injection Simulator
2. [AWS CloudFormation Workshop](https://catalog.workshops.aws/cfn101/en-US)

# 6. Athena

1. [[My NEXT] Amazon Athena Workshop :: Hands on Labs](https://catalog.us-east-1.prod.workshops.aws/workshops/9981f1a1-abdc-49b5-8387-cb01d238bb78/en-US)

# 7. Lambda

## 7.1. Using Java

1. [Java on AWS Lambda](https://catalog.workshops.aws/java-on-aws-lambda/en-US)

# 8. Kinesis

1. [Real Time Streaming with Amazon Kinesis](https://catalog.us-east-1.prod.workshops.aws/workshops/2300137e-f2ac-4eb9-a4ac-3d25026b235f/en-US)
- Produce data to Kinesis Data Streams
- Write Data to a Kinesis Data Stream using Kinesis Data Analytics Studio Notebook
- Lambda with Kinesis Data Firehose
- Clean, Aggregate, and Enrich Events with Kinesis Data Analytics
- Lambda Consumer for Kinesis Data Stream
- Consuming with Amazon KCL

## 8.1. Kinesis Firehose

1. [Amazon Kinesis Data Firehose Immersion Day](https://catalog.us-east-1.prod.workshops.aws/workshops/32e6bc9a-5c03-416d-be7c-4d29f40e55c4/en-US)

# 9. Serverless

1. [AWS Serverless Ecommerce Platform](https://github.com/aws-samples/aws-serverless-ecommerce-platform)
    - [Scheduling AWS Lambda Provisioned Concurrency for recurring peak usage by Chris Munns](https://aws.amazon.com/blogs/compute/scheduling-aws-lambda-provisioned-concurrency-for-recurring-peak-usage/)
2. [Welcome to the Serverlesspresso workshop! ](https://workshop.serverlesscoffee.com/)
3. [[MY NEXT] AWS re:Invent 2022 - Building Serverlesspresso: Creating event-driven architectures (SVS312) BY James Beswick](https://www.youtube.com/watch?v=qs0U0LdNkV0)
3. [Welcome to Innovator Island! ](https://www.eventbox.dev/published/lesson/innovator-island/)
4. [Wild Rydes workshop](https://webapp.serverlessworkshops.io/)
5. [Serverless Data Processing on AWS](https://catalog.us-east-1.prod.workshops.aws/workshops/76d4b4eb-bff7-40c6-a925-7f101ad3bd43/en-US)
- Real-time Streaming Data
- Stream Processing
- Streaming Aggregation
- Data Lake
6. [AWS Serverless Observability Workshop ](https://serverless-observability.workshop.aws/en/010_introduction.html)
- Metrics and Dashboards
- Synthetic Canaries
- Centralized Logging
- Distributed Tracing
- Performance Bottlenecks

## 9.1. Performance tuning

1. [Startup optimization: Tuning application performance for maximum efficiency](https://catalog.workshops.aws/performance-tuning/en-US)
- Module 1: Serverless Performance Tuning
- Module 2: Container Performance Tuning
- Module 3: AIOps for Relational Databases

# 10. Microservices

1. [Decoupled Microservices with Wild Rydes service](https://catalog.us-east-1.prod.workshops.aws/workshops/e8738cf6-6eb0-4d1d-9e98-ae240d229535/en-US)
- Lab 1 - Fan-out & message filtering
- Lab 2 - Topic-queue chaining & load balancing
- Lab 3 - Scatter-gather
- Lab 4 - Orchestration and coordination

# 11. Curate

1. [Enterprise Cloud Native Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/0466c70e-4216-4352-98d9-5a8af59c86b2/en-)
2. [Designing Cloud Native Microservices on AWS (via DDD/EventStormingWorkshop)](https://github.com/aws-samples/designing-cloud-native-microservices-on-aws)
3. [Amazon VPC Lattice Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/9e543f60-e409-43d4-b37f-78ff3e1a07f5/en-US)
4. [AWS Gateway API Controller for VPC Lattice](https://github.com/aws/aws-application-networking-k8s)

# 12. Cloudwatch

1. [[MY NEXT] AWS CloudWatch and Systems Manager Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/a8e9c6a6-0ba9-48a7-a90d-378a440ab8ba/en-US)

# 13. IAM

1. [IAM policy evaluation workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/6dc3124a-6bd4-46eb-b5c4-be438a82ba3d/en-US)
2. [IAM Hands-On Lab](https://catalog.us-east-1.prod.workshops.aws/workshops/8efd4edb-2b91-49fd-b1b8-3e3b5e71aa03/en-US/iam)
3. [Builder Sessions ](https://awssecworkshops.com/builder-sessions/)
4. [Security Workshops](https://awssecworkshops.com/workshops/)