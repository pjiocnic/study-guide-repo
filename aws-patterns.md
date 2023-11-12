
<!-- TOC -->

- [1. Pattern Collection](#1-pattern-collection)
- [2. Batch Processing](#2-batch-processing)
- [3. Cataloging Patterns for the Org](#3-cataloging-patterns-for-the-org)
- [4. Consistent hashing](#4-consistent-hashing)
- [5. Circuit Breaker](#5-circuit-breaker)
- [6. CQRS](#6-cqrs)
- [7. Enterprise Integration patterns (EIP)](#7-enterprise-integration-patterns-eip)
- [8. Event Aggregation in real-time](#8-event-aggregation-in-real-time)
- [9. Event Sourcing](#9-event-sourcing)
- [10. High Availability Patterns](#10-high-availability-patterns)
- [11. Orchestration and choreography patterns](#11-orchestration-and-choreography-patterns)
- [12. Parallel Processing](#12-parallel-processing)
- [13. Priority Queues](#13-priority-queues)
- [14. Routing Patterns](#14-routing-patterns)
- [15. Rate Limiter](#15-rate-limiter)
- [16. Regulating Request Rates Using Token Buckets](#16-regulating-request-rates-using-token-buckets)
- [17. Retry with Backoffs](#17-retry-with-backoffs)
- [18. Scatter Gather](#18-scatter-gather)
- [19. Sequence Generator](#19-sequence-generator)
- [20. Storage-first patterns](#20-storage-first-patterns)
- [21. Saga Pattern](#21-saga-pattern)
- [22. Strangler fig pattern](#22-strangler-fig-pattern)
- [23. Task Scheduling](#23-task-scheduling)
- [24. Transactional Outbox Pattern](#24-transactional-outbox-pattern)
- [25. WebHook](#25-webhook)
- [26. Websockets](#26-websockets)
- [27. Webhooks](#27-webhooks)

<!-- /TOC -->

# 1. Pattern Collection

1. [Cloud design patterns, architectures, and implementations](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/introduction.html)
2. [Serverless-Architecture-Patterns-in-AWS](https://blog.richiebartlett.com/Work_%E4%BB%95%E4%BA%8B/Serverless-Architecture-Patterns-in-AWS/)
> 1. [[**MUST-SEE**] Cloud Design Patterns](https://en.clouddesignpattern.org/index.php/Main_Page.html)

# 2. Batch Processing

1. [Creating AWS Serverless batch processing architectures by James Beswick](https://aws.amazon.com/blogs/compute/creating-aws-serverless-batch-processing-architectures/)
2. [Running batch workloads on Amazon EKS with AWS Batch](https://catalog.workshops.aws/running-batch-on-eks/en-US)

# 3. Cataloging Patterns for the Org

1. [Maintain visibility over the use of cloud architecture patterns by Rostislav Markov and Ibrahim Bakayoko](https://aws.amazon.com/blogs/architecture/maintain-visibility-over-the-use-of-cloud-architecture-patterns/)

# 4. Consistent hashing

1. [Consistent hashing explained](https://ably.com/blog/implementing-efficient-consistent-hashing)
2. [Consistent Hashing Algorithm](http://highscalability.com/blog/2023/2/22/consistent-hashing-algorithm.html)

# 5. Circuit Breaker

1. [[CDK] Using the circuit breaker pattern with AWS Step Functions and Amazon DynamoDB by Anitha Deenadayalan](https://aws.amazon.com/blogs/compute/using-the-circuit-breaker-pattern-with-aws-step-functions-and-amazon-dynamodb/)
1. [Circuit breaker pattern](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/circuit-breaker.html)
1. [The EventBridge Circuit Breaker By Matt Coulter](https://github.com/cdk-patterns/serverless/tree/main/the-eventbridge-circuit-breaker)

# 6. CQRS

1. [How does CQRS (command query responsibility segregation) work?](https://docs.aws.amazon.com/prescriptive-guidance/latest/modernization-data-persistence/cqrs-pattern.html)
2. [Build a CQRS event store with Amazon DynamoDB by Luke Popplewell ](https://aws.amazon.com/blogs/database/build-a-cqrs-event-store-with-amazon-dynamodb/)
3. [CQRS and Websockets](https://youtu.be/qs0U0LdNkV0?list=PLJo-rJlep0ECijHdz01OZXo3bqhbW_Hb2&t=2384)

# 7. Enterprise Integration patterns (EIP)

1. [publish-subscribe channels by Christian Mueller](https://aws.amazon.com/blogs/compute/implementing-enterprise-integration-patterns-with-aws-messaging-services-publish-subscribe-channels/)
1. [point-to-point channels by Christian Mueller](https://aws.amazon.com/blogs/compute/implementing-enterprise-integration-patterns-with-aws-messaging-services-point-to-point-channels/)
1. [Scatter Gather By Dirk Fröhner](https://aws.amazon.com/blogs/compute/application-integration-patterns-running-distributed-rfqs/)
1. [Fan-out strategies By Dirk Fröhner](https://aws.amazon.com/blogs/compute/application-integration-patterns-for-microservices-fan-out-strategies/)
1. [Orchestration and coordination by Christian Mueller](https://aws.amazon.com/blogs/compute/application-integration-patterns-for-microservices-orchestration-and-coordination/)

# 8. Event Aggregation in real-time

1. [workshop - aggregating messages](https://amazon-dynamodb-labs.com/event-driven-architecture/ex1overview/step1.html)
2. [Build a near real-time data aggregation pipeline using a serverless, event-driven architecture by Lucas Rettenmeier and Kirill Bogdanov](https://aws.amazon.com/blogs/database/build-a-near-real-time-data-aggregation-pipeline-using-a-serverless-event-driven-architecture/)
3. [Build a fault-tolerant, serverless data aggregation pipeline with exactly-once processing by Lucas Rettenmeier and Kirill Bogdanov](https://aws.amazon.com/blogs/database/build-a-fault-tolerant-serverless-data-aggregation-pipeline-with-exactly-once-processing/)
4. [Moving to event-driven architectures with serverless event aggregators by Semih Duru and Marco Sommella ](https://aws.amazon.com/blogs/mt/moving-to-event-driven-architectures-with-serverless-event-aggregators/)
5. [Orchestrating dependent file uploads with AWS Step Functions](https://aws.amazon.com/blogs/compute/orchestrating-dependent-file-uploads-with-aws-step-functions/)

# 9. Event Sourcing

1. [How does EventSourcing pattern work](https://docs.aws.amazon.com/prescriptive-guidance/latest/modernization-data-persistence/service-per-team.html)
2. [Decompose monoliths into microservices by using CQRS and event sourcing](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/decompose-monoliths-into-microservices-by-using-cqrs-and-event-sourcing.html)

# 10. High Availability Patterns

1. [Three advanced design patterns for high available applications using Amazon CloudFront by Ben Lee and Achraf Souk](https://aws.amazon.com/blogs/networking-and-content-delivery/three-advanced-design-patterns-for-high-available-applications-using-amazon-cloudfront/)

# 11. Orchestration and choreography patterns

1. [Orchestration and choreography patterns](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/orchestration-choreography.html)
1. [Choreography vs Orchestration in the land of serverless By Yan Cui](https://theburningmonk.com/2020/08/choreography-vs-orchestration-in-the-land-of-serverless/)

# 12. Parallel Processing

1. [Design Pattern for Highly Parallel Compute: Recursive Scaling with Amazon SQS by Koushik Biswas and Al Mair](https://aws.amazon.com/blogs/architecture/design-pattern-for-highly-parallel-compute-recursive-scaling-with-amazon-sqs/)

# 13. Priority Queues

1. [Implement the Priority Queue Pattern with SQS and Lambda](https://bitesizedserverless.com/bite/implement-the-priority-queue-pattern-with-sqs-and-lambda/)
2. [[MY NEXT] Implementing priority queueing with Amazon DynamoDB by Zoran Ivanovic](https://aws.amazon.com/blogs/database/implementing-priority-queueing-with-amazon-dynamodb/)

# 14. Routing Patterns

1. [API routing patterns](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/api-routing.html)
1. [Hostname routing](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/api-routing-hostname.html)
1. [Path routing](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/api-routing-path.html)
1. [HTTP header routing](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/api-routing-http.html)

# 15. Rate Limiter

1. [Prevent API overload with rate limiting in AWS](https://dev.to/aws-builders/prevent-api-overload-with-rate-limiting-in-aws-1dgb)
2. [[RATED] System Design Basics: Rate Limiter By Abhinav Singh](https://builtin.com/software-engineering-perspectives/rate-limiter)
3. [4 Rate Limit Algorithms Every Developer Should Know By Jason Ngan](https://betterprogramming.pub/4-rate-limit-algorithms-every-developer-should-know-7472cb482f48)
4. [Building well-architected serverless applications: Regulating inbound request rates – part 1](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-regulating-inbound-request-rates-part-1/)
5. [Building well-architected serverless applications: Regulating inbound request rates – part 2](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-regulating-inbound-request-rates-part-2/)
6. [Rate Limiting Strategies for Serverless Applications by Sharon Li, Akhil Aendapally, and Ashish Lagwankar](https://aws.amazon.com/blogs/architecture/rate-limiting-strategies-for-serverless-applications/)
7. [System Design — Design A Rate Limiter](https://medium.com/geekculture/system-design-design-a-rate-limiter-81d200c9d392)
8. [COURSE - Design Patterns for Distributed Systems By Priyank Gupta](https://learning.oreilly.com/live-events/design-patterns-for-distributed-systems/0636920061982/0636920072964/)

# 16. Regulating Request Rates Using Token Buckets

1. [Building well-architected serverless applications: Regulating inbound request rates – part 1 by Julian Wood](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-regulating-inbound-request-rates-part-1/)
2. [Building well-architected serverless applications: Regulating inbound request rates – part 2 by Julian Wood ](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-regulating-inbound-request-rates-part-2/)
3. [Distributed API Rate Limiter](https://systemsdesign.cloud/SystemDesign/RateLimiter)
4. [See System Design Interview: An Insider’s Guide @ CHAPTER 4: DESIGN A RATE LIMITER]

# 17. Retry with Backoffs

1. [retry-with-backoff](https://github.com/aws-samples/retry-with-backoff)
1. [Retry with backoff pattern](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/retry-backoff.html)
1. [Timeouts, retries, and backoff with jitter By Marc Brooker](https://aws.amazon.com/builders-library/timeouts-retries-and-backoff-with-jitter/)
1. [[Example for Jitter] Using Amazon SQS dead-letter queues to replay messages By Alexandre Pinhel](https://aws.amazon.com/blogs/compute/using-amazon-sqs-dead-letter-queues-to-replay-messages/)

# 18. Scatter Gather

1. [[MY NEXT] Serverless Loan Broker @AWS, Part 1: Step Functions By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions.html)
2. [Serverless Loan Broker @AWS, Part 2: Recipient List By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions_recipient_list.html)
3. [Serverless Loan Broker @AWS, Part 3: Publish-Subscribe with SNS By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions_pubsub.html)
4. [Serverless Loan Broker @ AWS, Part 4: Automation By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_automation.html)
5. [Serverless Loan Broker @ AWS, Part 5: Integration Patterns with CDK By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_cdk.html)
6. [Loan Broker with AWS CDK](https://github.com/aws-samples/aws-cdk-loan-broker)
- This project is an AWS Cloud Development Kit (CDK) implementation of [Gregor Hohpe's Loan Broker example](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions.html).
7. [Scalable Data Processing with AWS Serverless Scatter-Gather Pattern Implementation](https://aws.plainenglish.io/scalable-data-processing-with-aws-serverless-scatter-gather-pattern-implementation-63d25d6f6d23)
8. [See step-fns-backlogs.md/Content aggregation-Part 2](./step-fns-backlogs.md)
9. [Application integration patterns for microservices: Running distributed RFQs By Dirk Fröhner](https://aws.amazon.com/blogs/compute/application-integration-patterns-running-distributed-rfqs/)
- This technique is well explained and implemented by By Gregor Hohpe's Loan Broker example

# 19. Sequence Generator

1. [Generating Sequence Numbers in Serverless via API Gateway By Sheen Brisals](https://medium.com/lego-engineering/sequence-numbering-in-serverless-via-api-gateway-40e5f6c83e93)
2. [Reliable Auto-Incrementing Integers in DynamoDB By Bite-Sized Serverless](https://bitesizedserverless.com/bite/reliable-auto-increments-in-dynamodb/)
3. [Atomic counters](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/WorkingWithItems.html#WorkingWithItems.AtomicCounters)
4. [Implement resource counters with Amazon DynamoDB by Jason Hunter and Chris Gillespie](https://aws.amazon.com/blogs/database/implement-resource-counters-with-amazon-dynamodb/)

# 20. Storage-first patterns

1. [Building storage-first serverless applications with HTTP APIs service integrations by Eric Johnson](https://aws.amazon.com/blogs/compute/building-storage-first-applications-with-http-apis-service-integrations/)
1. [serverlessDays Nashville 2020 - Thinking Asynchronously by Eric Johnson](https://www.youtube.com/watch?v=eyjxZK-YHIk)
1. [Build Better Serverless APIs By Going Storage First By Allen Helton](https://www.readysetcloud.io/blog/allen.helton/built-better-serverless-apis-by-going-storage-first/)
1. [Storage-First pattern in AWS with API Gateway, Part 1: using S3](https://dev.to/aws-builders/storage-first-pattern-in-aws-with-api-gateway-part-1-using-s3-1l5p)
1. [The Storage First Pattern By Jeremy Daly](https://www.jeremydaly.com/the-storage-first-pattern/)
1. [Storage-First Serverless Solutions By Serverless Advocate](https://blog.serverlessadvocate.com/storage-first-serverless-solutions-c0fc182243b9)
1. [Build Asynchronous API's using the Storage First Pattern & the AWS CDK By James Eastham](https://www.youtube.com/watch?v=E7M2WKM4O8Q&t=13s)
- [Use ChaptGPT to convert to Java](https://github.com/jeastham1993/sustainable-architecture-patterns)

# 21. Saga Pattern

1. [Saga orchestration](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/saga.html)

# 22. Strangler fig pattern

1. [Strangler fig pattern](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/strangler-fig.html)

# 23. Task Scheduling

1. [Serverless Scheduling with Amazon EventBridge, AWS Lambda, and Amazon DynamoDB by Peter Grman](https://aws.amazon.com/blogs/architecture/serverless-scheduling-with-amazon-eventbridge-aws-lambda-and-amazon-dynamodb/)

# 24. Transactional Outbox Pattern

1. [Transactional outbox pattern](https://docs.aws.amazon.com/prescriptive-guidance/latest/cloud-design-patterns/transactional-outbox.html)
1. [Implementing the transactional outbox pattern with Amazon EventBridge Pipes By Sayan Moitra](https://aws.amazon.com/blogs/compute/implementing-the-transactional-outbox-pattern-with-amazon-eventbridge-pipes/)

# 25. WebHook

1. [Mastering AWS Lambda Yohan Wadia, Udita Gupta#Invoking Lambda using an external application](https://learning.oreilly.com/library/view/mastering-aws-lambda/9781786467690/25d22e80-8526-4310-994d-9b7863f938c5.xhtml)
2. AWS Lambda in Action Danilo Poccia
    - Chapter 16. Calling external services
    - Chapter 17. Receiving events from other services
3. [Manage webhooks at scale with AWS Serverless](https://dev.to/aws-builders/manage-webhooks-at-scale-with-aws-serverless-fof)
4. [REFERENCE DEPLOYMENT - Git Webhooks on AWS](https://aws.amazon.com/solutions/implementations/git-to-s3-using-webhooks/)
- HTTPS endpoint and code for linking your Git repository to AWS
5. [Amazon Aurora & Amazon EventBridge Webhooks Sample](https://github.com/aws-samples/amazon-aurora-eventbridge-webhooks)

# 26. Websockets

1. [See APGW backlog.md#WebSockets][./apgw-backlog.md]

# 27. Webhooks

1. [Building a Webhooks System By Derek Comartin](https://www.youtube.com/watch?v=NuHC5uwbFAc&list=PLThyvG1mlMzm2FyVpKDiU2c7VtrB2Zezg&index=33)
2. [Building a Webhooks System with Event Driven Architecture](https://codeopinion.com/building-a-webhooks-system-with-event-driven-architecture/)
3. [How an AWS Lambda Function Can Be Integrated with Box Webhooks by Gaurav Verma ](https://aws.amazon.com/blogs/apn/how-an-aws-lambda-function-can-be-integrated-with-box-webhooks/)
4. [Getting started with serverless for developers: Part 1 by Benjamin Smith ](https://aws.amazon.com/blogs/compute/getting-started-with-serverless-for-developers-part-1/)
5. [Handling Twitter Webhooks with AWS Lambda and Step Functions](https://www.linkedin.com/pulse/handling-twitter-webhooks-aws-lambda-step-functions-frederik-willaert/)
6.  [[MY NEXT] Generic webhook to EventBridge event bus](https://serverlessrepo.aws.amazon.com/applications/arn:aws:serverlessrepo:us-east-1:721177882564:applications~generic-webhook-to-eventbridge)
-  https://github.com/vacationtracker/generic-webhook-to-eventbridge
7. [Aggregating Webhooks with DynamoDB and SQS Delay Queues Dave North](https://rewind.com/blog/aggregating-webhooks-with-dynamodb-and-sqs-delay-queues/)
8. [Handle Shopify Webhooks Without a Server Mike Potter](https://rewind.com/blog/handle-shopify-webhooks-without-a-server/)

