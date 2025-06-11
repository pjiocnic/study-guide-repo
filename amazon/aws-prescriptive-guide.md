<h1>AWS Prescriptive Guide</h1>

<!-- TOC -->

- [CI/CD](#cicd)
- [1. Developer Guides](#1-developer-guides)
- [2. DR](#2-dr)
- [3. EKS](#3-eks)
- [4. Fargate](#4-fargate)
- [5. Lambda](#5-lambda)
- [6. Networking](#6-networking)
  - [6.1. Hub and Spoke](#61-hub-and-spoke)
- [7. Patterns](#7-patterns)
- [8. SAM](#8-sam)
- [9. Step Functions](#9-step-functions)
- [10. S3](#10-s3)
- [11. OU](#11-ou)
- [12. Examples to work on](#12-examples-to-work-on)
- [13. Custom Autoscaling](#13-custom-autoscaling)
- [14. Scaling provisioned Concurrency](#14-scaling-provisioned-concurrency)
- [15. Centralized Logging](#15-centralized-logging)
- [16. IAM Authentication for RDS](#16-iam-authentication-for-rds)
- [17. Hub and Spoke](#17-hub-and-spoke)
- [18. Serverless](#18-serverless)
- [19. Review SDK Examples](#19-review-sdk-examples)
- [20. Priority Queues](#20-priority-queues)
- [21. FIFO Queues](#21-fifo-queues)
- [22. Near-Real-Time Event Processing](#22-near-real-time-event-processing)

<!-- /TOC -->

# CI/CD

1. [Automatically detect changes and initiate different CodePipeline pipelines for a monorepo in CodeCommit](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/automatically-detect-changes-and-initiate-different-codepipeline-pipelines-for-a-monorepo-in-codecommit.html)

# 1. Developer Guides

1. [Amazon Simple Storage Service User Guide](https://docs.aws.amazon.com/s3/index.html)
2. [AWS Lambda Developer Guide](https://docs.aws.amazon.com/lambda/latest/dg/welcome.html)
3. [AWS DynamoDB Developer Guide](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/monitoring-cloudwatch.html)

# 2. DR

1. [AWS Prescriptive Guidance - Disaster recovery strategy for databases on AWS](https://docs.aws.amazon.com/prescriptive-guidance/latest/strategy-database-disaster-recovery/welcome.html)

# 3. EKS

1. [Deploy and debug Amazon EKS clusters](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/deploy-and-debug-amazon-eks-clusters.html)
2. [Automatically build and deploy a Java application to Amazon EKS using a CI/CD pipeline](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/automatically-build-and-deploy-a-java-application-to-amazon-eks-using-a-ci-cd-pipeline.html)
3. [Deploy a sample Java microservice on Amazon EKS and expose the microservice using an Application Load Balancer](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/deploy-a-sample-java-microservice-on-amazon-eks-and-expose-the-microservice-using-an-application-load-balancer.html)

# 4. Fargate

1. [[MUST SEE] Run message-driven workloads at scale by using AWS Fargate](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/run-message-driven-workloads-at-scale-by-using-aws-fargate.html)
2. [[MUST SEE] Run event-driven and scheduled workloads at scale with AWS Fargate](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/run-event-driven-and-scheduled-workloads-at-scale-with-aws-fargate.html)

# 5. Lambda

1. [Generate a static outbound IP address using a Lambda function, Amazon VPC, and a serverless architecture](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/generate-a-static-outbound-ip-address-using-a-lambda-function-amazon-vpc-and-a-serverless-architecture.html)
2. [Cache secrets using AWS Lambda extensions](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/cache-secrets-using-aws-lambda-extensions.html)
3. [Testing serverless applications on AWS](https://docs.aws.amazon.com/prescriptive-guidance/latest/serverless-application-testing/introduction.html)
4. [Automate deployment of nested applications using AWS SAM](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/automate-deployment-of-nested-applications-using-aws-sam.html)

# 6. Networking

## 6.1. Hub and Spoke

1. [Privately access a central AWS service endpoint from multiple VPCs](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/privately-access-a-central-aws-service-endpoint-from-multiple-vpcs.html)

# 7. Patterns

1. [Decompose monoliths into microservices by using CQRS and event sourcing](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/decompose-monoliths-into-microservices-by-using-cqrs-and-event-sourcing.html)

# 8. SAM

1. [Deploy multiple-stack applications using AWS CDK with TypeScript](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/deploy-multiple-stack-applications-using-aws-cdk-with-typescript.html)
2. [Automate deployment of nested applications using AWS SAM](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/automate-deployment-of-nested-applications-using-aws-sam.html)

# 9. Step Functions

1. [Implement the serverless saga pattern by using AWS Step Functions](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/implement-the-serverless-saga-pattern-by-using-aws-step-functions.html)

# 10. S3

1. [Copy data from an S3 bucket to another account and Region by using the AWS CLI](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/copy-data-from-an-s3-bucket-to-another-account-and-region-by-using-the-aws-cli.html)

# 11. OU

1. [OU structure in regulated AWS landing zones: an example from the pharmaceutical industry](https://docs.aws.amazon.com/prescriptive-guidance/latest/ou-structure-landing-zone/introduction.html)

# 12. Examples to work on

# 13. Custom Autoscaling

1. [Amazon DocumentDB (with MongoDB compatibility) read autoscaling by Randy DeFauw](https://aws.amazon.com/blogs/database/amazon-documentdb-with-mongodb-compatibility-read-autoscaling/)

# 14. Scaling provisioned Concurrency

See Lambda-backlog.md

# 15. Centralized Logging

1. [Stream Amazon CloudWatch Logs to a Centralized Account for Audit and Analysis by David Bailey](https://aws.amazon.com/blogs/architecture/stream-amazon-cloudwatch-logs-to-a-centralized-account-for-audit-and-analysis/)

# 16. IAM Authentication for RDS

1. [How to securely provide database credentials to Lambda functions by using AWS Secrets Manager by Ramesh Adabala, Anand Komandooru, and Noorul Hasan](https://aws.amazon.com/blogs/security/how-to-securely-provide-database-credentials-to-lambda-functions-by-using-aws-secrets-manager/)

# 17. Hub and Spoke

1. [[MY NEXT] Centralize access using VPC interface endpoints to access AWS services across multiple VPCs by Chetan Agrawal](https://aws.amazon.com/blogs/networking-and-content-delivery/centralize-access-using-vpc-interface-endpoints/)

# 18. Serverless

1. [AWS Serverless Ecommerce Platform](https://github.com/aws-samples/aws-serverless-ecommerce-platform)
- [Scheduling AWS Lambda Provisioned Concurrency for recurring peak usage by Chris Munns](https://aws.amazon.com/blogs/compute/scheduling-aws-lambda-provisioned-concurrency-for-recurring-peak-usage/)
2. [[MUST SEE] More patterns](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/serverless-more-patterns-pattern-list.html)
* [Access, query, and join Amazon DynamoDB tables using Athena](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/access-query-and-join-amazon-dynamodb-tables-using-athena.html)
* [Automate deletion of AWS resources by using aws-nuke](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/automate-deletion-of-aws-resources-by-using-aws-nuke.html)
* [Automate deployment of nested applications using AWS SAM](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/automate-deployment-of-nested-applications-using-aws-sam.html)
* [Automatically archive items to Amazon S3 using DynamoDB TTL](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/automatically-archive-items-to-amazon-s3-using-dynamodb-ttl.html)
* [Build a loosely coupled architecture with microservices using DevOps practices and AWS Cloud9](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/build-a-loosely-coupled-architecture-with-microservices-using-devops-practices-and-aws-cloud9.html)
* [Build a multi-tenant serverless architecture in Amazon OpenSearch Service](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/build-a-multi-tenant-serverless-architecture-in-amazon-opensearch-service.html)
* [Cache secrets using AWS Lambda extensions Services and tools](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/cache-secrets-using-aws-lambda-extensions.html)
* [Calculate value at risk (VaR) by using AWS services]()
* [Copy AWS Service Catalog products across different AWS accounts and AWS Regions](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/copy-aws-service-catalog-products-across-different-aws-accounts-and-aws-regions.html)
* [Create dynamic CI pipelines for Java and Python projects automatically](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/create-dynamic-ci-pipelines-for-java-and-python-projects-automatically.html)
* [Decompose monoliths into microservices by using CQRS and event sourcing](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/decompose-monoliths-into-microservices-by-using-cqrs-and-event-sourcing.html)
* [Deploy a React-based single-page application to Amazon S3 and CloudFront](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/deploy-a-react-based-single-page-application-to-amazon-s3-and-cloudfront.html)
* [Deploy an Amazon API Gateway API on an internal website using private endpoints and an Application Load Balancer](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/deploy-an-amazon-api-gateway-api-on-an-internal-website-using-private-endpoints-and-an-application-load-balancer.html)
* [Deploy and debug Amazon EKS clusters](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/deploy-and-debug-amazon-eks-clusters.html)
* [Deploy and manage a serverless data lake on the AWS Cloud by using infrastructure as code](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/deploy-and-manage-a-serverless-data-lake-on-the-aws-cloud-by-using-infrastructure-as-code.html)
* [Deploy Lambda functions with container images Services and tools](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/deploy-lambda-functions-with-container-images.html)
* [Dynamically generate an IAM policy with IAM Access Analyzer by using Step Functions](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/dynamically-generate-an-iam-policy-with-iam-access-analyzer-by-using-step-functions.html)
* Ensure Amazon EMR logging to Amazon S3 is enabled at launch
* Estimate the cost of a DynamoDB table for on-demand capacity
* Generate personalized and re-ranked recommendations using Amazon Personalize
* Generate test data using an AWS Glue job and Python
* [Implement the serverless saga pattern by using AWS Step Functions](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/implement-the-serverless-saga-pattern-by-using-aws-step-functions.html)
* Improve operational performance by enabling Amazon DevOps Guru across multiple AWS Regions, accounts, and OUs with the AWS CDK
* Launch a CodeBuild project across AWS accounts using Step Functions and a Lambda proxy function
* Migrate Apache Cassandra workloads to Amazon Keyspaces using AWS Glue
* Orchestrate an ETL pipeline with validation, transformation, and partitioning using AWS Step Functions
* [Run event-driven and scheduled workloads at scale with AWS Fargate](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/run-event-driven-and-scheduled-workloads-at-scale-with-aws-fargate.html)
* Serve static content in an Amazon S3 bucket through a VPC by using Amazon CloudFront
* Structure a Python project in hexagonal architecture using AWS Lambda
* Turn off security standard controls across all Security Hub member accounts in a multi-account environment

# 19. Review SDK Examples
1. [https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2](https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2)

# 20. Priority Queues

1. [[MY NEXT] Implementing priority queueing with Amazon DynamoDB by Zoran Ivanovic](https://aws.amazon.com/blogs/database/implementing-priority-queueing-with-amazon-dynamodb/)

# 21. FIFO Queues

1. [Implement serverless FIFO queues with filtering capabilities using Amazon DynamoDB transactions by Nikhil Penmetsa and Randy DeFauw](https://aws.amazon.com/blogs/database/serverless-fifo-queues-filtering-dynamodb-transactions/)

# 22. Near-Real-Time Event Processing

1. [How to perform ordered data replication between applications by using Amazon DynamoDB Streams by Aravind Kodandaramaiah](https://aws.amazon.com/blogs/database/how-to-perform-ordered-data-replication-between-applications-by-using-amazon-dynamodb-streams/)