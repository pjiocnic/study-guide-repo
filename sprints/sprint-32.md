<h1>Sprint 32 - Aug 7, 2023- Aug 13, 2023</h1>

<!-- TOC -->

- [1. Incomplete Tasks](#1-incomplete-tasks)
- [2. Daily](#2-daily)
- [3. This week's backlog](#3-this-weeks-backlog)
  - [3.1. IAM](#31-iam)
  - [3.2. Ran Isenberg](#32-ran-isenberg)
  - [3.3. CDK](#33-cdk)
  - [3.4. SQS](#34-sqs)
    - [3.4.1. Invocation Models](#341-invocation-models)
  - [3.5. CDK packaging](#35-cdk-packaging)
- [4. CDK Constructs](#4-cdk-constructs)
- [5. Monitoring](#5-monitoring)
- [6. DLQs](#6-dlqs)
- [7. Best practices for Lambda](#7-best-practices-for-lambda)

<!-- /TOC -->
# 1. Incomplete Tasks
1. Cloudformation workshop
2. Highly Available Web App workshop - https://catalog.us-east-1.prod.workshops.aws/workshops/5ceb632a-c07f-44a5-a3bd-b8f616a631c0/en-US/application/lab5
3. Stratospheric book
4. Typescript chapter from Udemy course
5. Lambda architecture - [AWS re:Invent 2022 - A closer look at AWS Lambda (SVS404-R)](https://www.youtube.com/watch?v=0_jfH6qijVY&t=1308s)
step function workshop
loan processor
documentdb


# 2. Daily

1. [Serverless Applications Lens - AWS Well-Architected Framework](https://docs.aws.amazon.com/wellarchitected/latest/serverless-applications-lens/welcome.html)
2. [Serverless Applications Lens AWS Well-Architected Framework By AGPIAL](https://www.youtube.com/watch?v=g1WpzZHQ4Gc)
3. [AWS Serverless Multi-Tier Architectures. AWS Whitepaper AGPIAL Audio](https://www.youtube.com/watch?v=lEf9XjbHs0U)
4. [Security Overview of AWS Lambda AWS Whitepaper. AGPIAL Audiobook](https://www.youtube.com/watch?v=QjVU-WiKLo4)
5. [Maximizing Value with AWS. AGPIAL Audiobook](https://www.youtube.com/watch?v=roT6LOINf30)
3. [Reliability Pillar AWS Well-Architected Framework. AGPIAL Audiobook](https://www.youtube.com/watch?v=avp7Hb-JybU)
5. [Performance Eﬃciency Pillar AWS Well-Architected Framework. AGPIAL Audiobook](https://www.youtube.com/watch?v=jze2oaojNSI)
6. [Operational Excellence Pillar AWS Well-Architected Framework. AGPIAL Audiobook](https://www.youtube.com/watch?v=Vhk6a5MUhE8)
7. [Cost Optimization Pillar AWS Well-Architected Framework AGPIAL Audiobook](https://www.youtube.com/watch?v=zeLyoWund1I)
8. [Implementing Microservices on AWS August 2019 | microservices-on-aws](https://www.youtube.com/watch?v=g3I2Ff9aR5Y&list=PL6eq_rAwpz95oGznLjxxwkWOhznyNf2G_)
9. [Designing durable serverless apps with DLQs for Amazon SNS, Amazon SQS, AWS Lambda](https://aws.amazon.com/blogs/compute/designing-durable-serverless-apps-with-dlqs-for-amazon-sns-amazon-sqs-aws-lambda/)

# 3. This week's backlog

## 3.1. IAM

1. [Viewing permission issues with service-linked roles by Rich McDonough ](https://aws.amazon.com/blogs/mt/viewing-permission-issues-with-service-linked-roles/)
2. [Get to Grips with AWS IAM Roles: Terms, Concepts, and Examples](https://blog.awsfundamentals.com/aws-iam-roles-terms-concepts-and-examples)

## 3.2. Ran Isenberg

1. [AWS Lambda Handler Cookbook - A Serverless Service Template](https://ran-isenberg.github.io/aws-lambda-handler-cookbook/#serverless-service-the-order-service)

## 3.3. CDK

1. [AWS CDK - Best Practices From The Trenches By Ran Isenberg](https://www.ranthebuilder.cloud/post/aws-cdk-best-practices-from-the-trenches)

## 3.4. SQS

1. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 1 by Pascal Vogel ](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-1/)
2. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 2 by Pascal Vogel ](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-2/)
3. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 3 by Pascal Vogel](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-3/)

### 3.4.1. Invocation Models

1. [Asynchronous invocation](https://docs.aws.amazon.com/lambda/latest/dg/invocation-async.html)
2. [[MY NEXT] Understanding the Different Ways to Invoke Lambda Functions by George Mao](https://aws.amazon.com/blogs/architecture/understanding-the-different-ways-to-invoke-lambda-functions/)

## 3.5. CDK packaging

1. [Packaging and deploying AWS Lambda functions written in Java with AWS Cloud Development Kit	By Pankaj Agarwal](https://aws.amazon.com/blogs/opensource/packaging-and-deploying-aws-lambda-functions-written-in-java-with-aws-cloud-development-kit/)
2. [cdk-lambda-packaging-java @ aws-samples](https://github.com/aws-samples/)

# 4. CDK Constructs

1. https://bobbyhadz.com/blog/cdk-constructs-tutorial
/Volumes/Lexar/git-repos/aws-repo/my-aws-cookbook/cdk/development/1-What are 3 Levels of constructs in cdk.md

# 5. Monitoring

1. [[START HERE] Lambda Monitoring Overview](https://www.youtube.com/watch?v=idkluLIwous)

# 6. DLQs

1. [Implementing AWS Lambda error handling patterns by Julian Wood, Jeff Chen, and Jeff Li ](https://aws.amazon.com/blogs/compute/implementing-aws-lambda-error-handling-patterns/)
- /Volumes/Lexar/git-repos/aws-repo/my-aws-samples/lambda/dlq/aws-lambda-error-handling-pattern
2. Document the retry mechanism for each data source
- https://aws.amazon.com/blogs/architecture/understanding-the-different-ways-to-invoke-lambda-functions/

# 7. Best practices for Lambda

1. [4-best-practices-lambda.md](my-github/study-guide-repo/prepare-notes/4-best-practices-lambda.md)