
<!-- TOC -->

- [1. Pattern Collection](#1-pattern-collection)
- [2. Batch Processing](#2-batch-processing)
- [3. Consistent hashing](#3-consistent-hashing)
- [4. Circuit Breaker](#4-circuit-breaker)
- [5. CQRS](#5-cqrs)
- [6. Event Aggregation in real-time](#6-event-aggregation-in-real-time)
- [7. Event Sourcing](#7-event-sourcing)
- [8. Priority Queues](#8-priority-queues)
- [9. Rate Limiter](#9-rate-limiter)
- [10. Regulating Request Rates Using Token Buckets](#10-regulating-request-rates-using-token-buckets)
- [11. Retry with Backoffs](#11-retry-with-backoffs)
- [12. Scatter Gather](#12-scatter-gather)
- [13. Sequence Generator](#13-sequence-generator)
- [14. Storage-first patterns](#14-storage-first-patterns)
- [15. Task Scheduling](#15-task-scheduling)
- [16. WebHook](#16-webhook)
- [17. Websockets](#17-websockets)
- [18. Webhooks](#18-webhooks)

<!-- /TOC -->

# 1. Pattern Collection

1. [Cloud design patterns, architectures, and implementations](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/introduction.html)
2. [Serverless-Architecture-Patterns-in-AWS](https://blog.richiebartlett.com/Work_%E4%BB%95%E4%BA%8B/Serverless-Architecture-Patterns-in-AWS/)

# 2. Batch Processing

1. [Creating AWS Serverless batch processing architectures by James Beswick](https://aws.amazon.com/blogs/compute/creating-aws-serverless-batch-processing-architectures/)
2. [Running batch workloads on Amazon EKS with AWS Batch](https://catalog.workshops.aws/running-batch-on-eks/en-US)

# 3. Consistent hashing

1. [Consistent hashing explained](https://ably.com/blog/implementing-efficient-consistent-hashing)
2. [Consistent Hashing Algorithm](http://highscalability.com/blog/2023/2/22/consistent-hashing-algorithm.html)

# 4. Circuit Breaker

1. [[CDK] Using the circuit breaker pattern with AWS Step Functions and Amazon DynamoDB by Eric Johnson](https://aws.amazon.com/blogs/compute/using-the-circuit-breaker-pattern-with-aws-step-functions-and-amazon-dynamodb/)

# 5. CQRS

1. [How does CQRS (command query responsibility segregation) work?](https://docs.aws.amazon.com/prescriptive-guidance/latest/modernization-data-persistence/cqrs-pattern.html)
2. [Build a CQRS event store with Amazon DynamoDB by Luke Popplewell ](https://aws.amazon.com/blogs/database/build-a-cqrs-event-store-with-amazon-dynamodb/)
3. [CQRS and Websockets](https://youtu.be/qs0U0LdNkV0?list=PLJo-rJlep0ECijHdz01OZXo3bqhbW_Hb2&t=2384)

# 6. Event Aggregation in real-time

1. [workshop - aggregating messages](https://amazon-dynamodb-labs.com/event-driven-architecture/ex1overview/step1.html)
2. [Build a near real-time data aggregation pipeline using a serverless, event-driven architecture by Lucas Rettenmeier and Kirill Bogdanov](https://aws.amazon.com/blogs/database/build-a-near-real-time-data-aggregation-pipeline-using-a-serverless-event-driven-architecture/)
3. [Build a fault-tolerant, serverless data aggregation pipeline with exactly-once processing by Lucas Rettenmeier and Kirill Bogdanov](https://aws.amazon.com/blogs/database/build-a-fault-tolerant-serverless-data-aggregation-pipeline-with-exactly-once-processing/)
4. [Moving to event-driven architectures with serverless event aggregators by Semih Duru and Marco Sommella ](https://aws.amazon.com/blogs/mt/moving-to-event-driven-architectures-with-serverless-event-aggregators/)

# 7. Event Sourcing

1. [How does EventSourcing pattern work](https://docs.aws.amazon.com/prescriptive-guidance/latest/modernization-data-persistence/service-per-team.html)
2. [Decompose monoliths into microservices by using CQRS and event sourcing](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/decompose-monoliths-into-microservices-by-using-cqrs-and-event-sourcing.html)

# 8. Priority Queues

1. [Implement the Priority Queue Pattern with SQS and Lambda](https://bitesizedserverless.com/bite/implement-the-priority-queue-pattern-with-sqs-and-lambda/)
2. [[MY NEXT] Implementing priority queueing with Amazon DynamoDB by Zoran Ivanovic](https://aws.amazon.com/blogs/database/implementing-priority-queueing-with-amazon-dynamodb/)

# 9. Rate Limiter

1. [Prevent API overload with rate limiting in AWS](https://dev.to/aws-builders/prevent-api-overload-with-rate-limiting-in-aws-1dgb)
2. [[RATED] System Design Basics: Rate Limiter By Abhinav Singh](https://builtin.com/software-engineering-perspectives/rate-limiter)
3. [4 Rate Limit Algorithms Every Developer Should Know By Jason Ngan](https://betterprogramming.pub/4-rate-limit-algorithms-every-developer-should-know-7472cb482f48)
4. [Building well-architected serverless applications: Regulating inbound request rates – part 1](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-regulating-inbound-request-rates-part-1/)
5. [Building well-architected serverless applications: Regulating inbound request rates – part 2](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-regulating-inbound-request-rates-part-2/)
6. [Rate Limiting Strategies for Serverless Applications by Sharon Li, Akhil Aendapally, and Ashish Lagwankar](https://aws.amazon.com/blogs/architecture/rate-limiting-strategies-for-serverless-applications/)
7. [System Design — Design A Rate Limiter](https://medium.com/geekculture/system-design-design-a-rate-limiter-81d200c9d392)
8. [COURSE - Design Patterns for Distributed Systems By Priyank Gupta](https://learning.oreilly.com/live-events/design-patterns-for-distributed-systems/0636920061982/0636920072964/)

# 10. Regulating Request Rates Using Token Buckets

1. [Building well-architected serverless applications: Regulating inbound request rates – part 1 by Julian Wood](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-regulating-inbound-request-rates-part-1/)
2. [Building well-architected serverless applications: Regulating inbound request rates – part 2 by Julian Wood ](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-regulating-inbound-request-rates-part-2/)
3. [Distributed API Rate Limiter](https://systemsdesign.cloud/SystemDesign/RateLimiter)
4. [See System Design Interview: An Insider’s Guide @ CHAPTER 4: DESIGN A RATE LIMITER]

# 11. Retry with Backoffs

1. [retry-with-backoff](https://github.com/aws-samples/retry-with-backoff)
2. [Retry with backoff pattern](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/retry-backoff.html)
3. [Timeouts, retries, and backoff with jitter By Marc Brooker](https://aws.amazon.com/builders-library/timeouts-retries-and-backoff-with-jitter/)

# 12. Scatter Gather

1. [[MY NEXT] Serverless Loan Broker @AWS, Part 1: Step Functions By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions.html)
2. [Serverless Loan Broker @AWS, Part 2: Recipient List By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions_recipient_list.html)
3. [Serverless Loan Broker @AWS, Part 3: Publish-Subscribe with SNS By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions_pubsub.html)
4. [Serverless Loan Broker @ AWS, Part 4: Automation By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_automation.html)
5. [Serverless Loan Broker @ AWS, Part 5: Integration Patterns with CDK By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_cdk.html)
6. [Loan Broker with AWS CDK](https://github.com/aws-samples/aws-cdk-loan-broker)
- This project is an AWS Cloud Development Kit (CDK) implementation of [Gregor Hohpe's Loan Broker example](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions.html).
7. [Scalable Data Processing with AWS Serverless Scatter-Gather Pattern Implementation](https://aws.plainenglish.io/scalable-data-processing-with-aws-serverless-scatter-gather-pattern-implementation-63d25d6f6d23)
8. [See step-fns-backlogs.md/Content aggregation-Part 2](./step-fns-backlogs.md)

# 13. Sequence Generator

1. [Generating Sequence Numbers in Serverless via API Gateway By Sheen Brisals](https://medium.com/lego-engineering/sequence-numbering-in-serverless-via-api-gateway-40e5f6c83e93)
2. [Reliable Auto-Incrementing Integers in DynamoDB By Bite-Sized Serverless](https://bitesizedserverless.com/bite/reliable-auto-increments-in-dynamodb/)
3. [Atomic counters](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/WorkingWithItems.html#WorkingWithItems.AtomicCounters)
4. [Implement resource counters with Amazon DynamoDB by Jason Hunter and Chris Gillespie](https://aws.amazon.com/blogs/database/implement-resource-counters-with-amazon-dynamodb/)

# 14. Storage-first patterns

1. [Building storage-first serverless applications with HTTP APIs service integrations by Eric Johnson](https://aws.amazon.com/blogs/compute/building-storage-first-applications-with-http-apis-service-integrations/)
2. [Build Better Serverless APIs By Going Storage First By Allen Helton](https://www.readysetcloud.io/blog/allen.helton/built-better-serverless-apis-by-going-storage-first/)
3. [Storage-First pattern in AWS with API Gateway, Part 1: using S3](https://dev.to/aws-builders/storage-first-pattern-in-aws-with-api-gateway-part-1-using-s3-1l5p)
4. [The Storage First Pattern By Jeremy Daly](https://www.jeremydaly.com/the-storage-first-pattern/)
5. [Storage-First Serverless Solutions By Serverless Advocate](https://blog.serverlessadvocate.com/storage-first-serverless-solutions-c0fc182243b9)
6. [Build Asynchronous API's using the Storage First Pattern & the AWS CDK By James Eastham](https://www.youtube.com/watch?v=E7M2WKM4O8Q&t=13s)
- [Use ChaptGPT to convert to Java](https://github.com/jeastham1993/sustainable-architecture-patterns)

# 15. Task Scheduling

1. [Serverless Scheduling with Amazon EventBridge, AWS Lambda, and Amazon DynamoDB by Peter Grman](https://aws.amazon.com/blogs/architecture/serverless-scheduling-with-amazon-eventbridge-aws-lambda-and-amazon-dynamodb/)

# 16. WebHook

1. [Mastering AWS Lambda Yohan Wadia, Udita Gupta#Invoking Lambda using an external application](https://learning.oreilly.com/library/view/mastering-aws-lambda/9781786467690/25d22e80-8526-4310-994d-9b7863f938c5.xhtml)
2. AWS Lambda in Action Danilo Poccia
    - Chapter 16. Calling external services
    - Chapter 17. Receiving events from other services
3. [Manage webhooks at scale with AWS Serverless](https://dev.to/aws-builders/manage-webhooks-at-scale-with-aws-serverless-fof)
4. [REFERENCE DEPLOYMENT - Git Webhooks on AWS](https://aws.amazon.com/solutions/implementations/git-to-s3-using-webhooks/)
- HTTPS endpoint and code for linking your Git repository to AWS
5. [Amazon Aurora & Amazon EventBridge Webhooks Sample](https://github.com/aws-samples/amazon-aurora-eventbridge-webhooks)

# 17. Websockets

1. [See APGW backlog.md#WebSockets][./apgw-backlog.md]

# 18. Webhooks

1. [Building a Webhooks System By Derek Comartin](https://www.youtube.com/watch?v=NuHC5uwbFAc&list=PLThyvG1mlMzm2FyVpKDiU2c7VtrB2Zezg&index=33)
2. [Building a Webhooks System with Event Driven Architecture](https://codeopinion.com/building-a-webhooks-system-with-event-driven-architecture/)
3. [How an AWS Lambda Function Can Be Integrated with Box Webhooks by Gaurav Verma ](https://aws.amazon.com/blogs/apn/how-an-aws-lambda-function-can-be-integrated-with-box-webhooks/)
4. [Getting started with serverless for developers: Part 1 by Benjamin Smith ](https://aws.amazon.com/blogs/compute/getting-started-with-serverless-for-developers-part-1/)
5. [Handling Twitter Webhooks with AWS Lambda and Step Functions](https://www.linkedin.com/pulse/handling-twitter-webhooks-aws-lambda-step-functions-frederik-willaert/)
6.  [[MY NEXT] Generic webhook to EventBridge event bus](https://serverlessrepo.aws.amazon.com/applications/arn:aws:serverlessrepo:us-east-1:721177882564:applications~generic-webhook-to-eventbridge)
-  https://github.com/vacationtracker/generic-webhook-to-eventbridge
7. [Aggregating Webhooks with DynamoDB and SQS Delay Queues Dave North](https://rewind.com/blog/aggregating-webhooks-with-dynamodb-and-sqs-delay-queues/)
8. [Handle Shopify Webhooks Without a Server Mike Potter](https://rewind.com/blog/handle-shopify-webhooks-without-a-server/)

