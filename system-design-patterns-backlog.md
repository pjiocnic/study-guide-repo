
<!-- TOC -->

- [1. Consistent hashing](#1-consistent-hashing)
- [2. System Design](#2-system-design)
- [3. WebHook](#3-webhook)
- [4. Websockets](#4-websockets)
- [5. Design Patterns](#5-design-patterns)
  - [5.1. Storage-first patterns](#51-storage-first-patterns)
  - [5.2. Sequence Generator](#52-sequence-generator)
  - [5.3. Priority Queues](#53-priority-queues)
  - [5.4. Webhooks](#54-webhooks)
  - [5.5. Scatter Gather](#55-scatter-gather)
  - [5.6. Rate Limiter](#56-rate-limiter)

<!-- /TOC -->

# 1. Consistent hashing

1. [Consistent hashing explained](https://ably.com/blog/implementing-efficient-consistent-hashing)
2. [Consistent Hashing Algorithm](http://highscalability.com/blog/2023/2/22/consistent-hashing-algorithm.html)

# 2. System Design

1. [10 System Design Algorithms, Protocols, and Distributed Data Structure to solve large-scales Problems](https://medium.com/javarevisited/10-system-design-algorithms-protocols-and-distributed-data-structure-to-solve-large-scales-40bd24d9a57f)

# 3. WebHook

1. [Mastering AWS Lambda Yohan Wadia, Udita Gupta#Invoking Lambda using an external application](https://learning.oreilly.com/library/view/mastering-aws-lambda/9781786467690/25d22e80-8526-4310-994d-9b7863f938c5.xhtml)
2. AWS Lambda in Action Danilo Poccia
    - Chapter 16. Calling external services
    - Chapter 17. Receiving events from other services

# 4. Websockets

1. [See APGW backlog.md#WebSockets][./apgw-backlog.md]

# 5. Design Patterns

1. [Scalable Data Processing with AWS Serverless Scatter-Gather Pattern Implementation](https://aws.plainenglish.io/scalable-data-processing-with-aws-serverless-scatter-gather-pattern-implementation-63d25d6f6d23)
2. [Serverless-Architecture-Patterns-in-AWS](https://blog.richiebartlett.com/Work_%E4%BB%95%E4%BA%8B/Serverless-Architecture-Patterns-in-AWS/)

## 5.1. Storage-first patterns

1. [Building storage-first serverless applications with HTTP APIs service integrations by Eric Johnson](https://aws.amazon.com/blogs/compute/building-storage-first-applications-with-http-apis-service-integrations/)
2. [Build Better Serverless APIs By Going Storage First By Allen Helton](https://www.readysetcloud.io/blog/allen.helton/built-better-serverless-apis-by-going-storage-first/)
3. [Storage-First pattern in AWS with API Gateway, Part 1: using S3](https://dev.to/aws-builders/storage-first-pattern-in-aws-with-api-gateway-part-1-using-s3-1l5p)
4. [The Storage First Pattern By Jeremy Daly](https://www.jeremydaly.com/the-storage-first-pattern/)
5. [Storage-First Serverless Solutions By Serverless Advocate](https://blog.serverlessadvocate.com/storage-first-serverless-solutions-c0fc182243b9)

## 5.2. Sequence Generator

1. [Generating Sequence Numbers in Serverless via API Gateway By Sheen Brisals](https://medium.com/lego-engineering/sequence-numbering-in-serverless-via-api-gateway-40e5f6c83e93)
2. [Reliable Auto-Incrementing Integers in DynamoDB By Bite-Sized Serverless](https://bitesizedserverless.com/bite/reliable-auto-increments-in-dynamodb/)

## 5.3. Priority Queues

1. [Implement the Priority Queue Pattern with SQS and Lambda](https://bitesizedserverless.com/bite/implement-the-priority-queue-pattern-with-sqs-and-lambda/)

## 5.4. Webhooks

1. [Building a Webhooks System By Derek Comartin](https://www.youtube.com/watch?v=NuHC5uwbFAc&list=PLThyvG1mlMzm2FyVpKDiU2c7VtrB2Zezg&index=33)
2. [Building a Webhooks System with Event Driven Architecture](https://codeopinion.com/building-a-webhooks-system-with-event-driven-architecture/)
3. [How an AWS Lambda Function Can Be Integrated with Box Webhooks by Gaurav Verma ](https://aws.amazon.com/blogs/apn/how-an-aws-lambda-function-can-be-integrated-with-box-webhooks/)
4. [Getting started with serverless for developers: Part 1 by Benjamin Smith ](https://aws.amazon.com/blogs/compute/getting-started-with-serverless-for-developers-part-1/)

## 5.5. Scatter Gather

1. [[MY NEXT] Serverless Loan Broker @AWS, Part 1: Step Functions By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions.html)
2. [Serverless Loan Broker @AWS, Part 2: Recipient List By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions_recipient_list.html)
3. [Serverless Loan Broker @AWS, Part 3: Publish-Subscribe with SNS By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions_pubsub.html)
4. [Serverless Loan Broker @ AWS, Part 4: Automation By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_automation.html)
5. [Serverless Loan Broker @ AWS, Part 5: Integration Patterns with CDK By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_cdk.html)

## 5.6. Rate Limiter

1. [Prevent API overload with rate limiting in AWS](https://dev.to/aws-builders/prevent-api-overload-with-rate-limiting-in-aws-1dgb)
2. [[RATED] System Design Basics: Rate Limiter By Abhinav Singh](https://builtin.com/software-engineering-perspectives/rate-limiter)
3. [4 Rate Limit Algorithms Every Developer Should Know By Jason Ngan](https://betterprogramming.pub/4-rate-limit-algorithms-every-developer-should-know-7472cb482f48)
4. [Building well-architected serverless applications: Regulating inbound request rates – part 1](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-regulating-inbound-request-rates-part-1/)
5. [Building well-architected serverless applications: Regulating inbound request rates – part 2	](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-regulating-inbound-request-rates-part-2/)
6. [Rate Limiting Strategies for Serverless Applications by Sharon Li, Akhil Aendapally, and Ashish Lagwankar](https://aws.amazon.com/blogs/architecture/rate-limiting-strategies-for-serverless-applications/)
7. [System Design — Design A Rate Limiter](https://medium.com/geekculture/system-design-design-a-rate-limiter-81d200c9d392)
8. [COURSE - Design Patterns for Distributed Systems By Priyank Gupta](https://learning.oreilly.com/live-events/design-patterns-for-distributed-systems/0636920061982/0636920072964/)