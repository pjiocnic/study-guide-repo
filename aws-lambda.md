<H1>Lambda BackLog</h1>

<!-- TOC -->

- [1. Best Practices](#1-best-practices)
- [2. AWS Lambda Web Adapter](#2-aws-lambda-web-adapter)
- [3. Architecture](#3-architecture)
- [4. Autoscaling](#4-autoscaling)
- [5. Batch Processing](#5-batch-processing)
  - [5.1. Why is it cost effective?](#51-why-is-it-cost-effective)
  - [5.2. Checkpointing](#52-checkpointing)
- [6. Costs](#6-costs)
- [7. Cookbooks](#7-cookbooks)
  - [7.1. Cookbook by Ran Isenberg](#71-cookbook-by-ran-isenberg)
- [8. CI/CD](#8-cicd)
- [9. Cross-account access](#9-cross-account-access)
  - [9.1. Blue Green Deployments](#91-blue-green-deployments)
  - [9.2. Canary Deployments](#92-canary-deployments)
- [10. Code Signing](#10-code-signing)
- [11. Core Topics](#11-core-topics)
  - [11.1. Throttling](#111-throttling)
  - [11.2. Error Handling](#112-error-handling)
    - [11.2.1. Exponential backoff and jitter algorithm](#1121-exponential-backoff-and-jitter-algorithm)
- [12. Cloudwatch Insights](#12-cloudwatch-insights)
- [13. Comparison between REST and GraphQL API architectures](#13-comparison-between-rest-and-graphql-api-architectures)
- [14. Destinations](#14-destinations)
- [15. Devops](#15-devops)
- [16. Development](#16-development)
  - [16.1. Compilation techniques](#161-compilation-techniques)
  - [16.2. Designing](#162-designing)
  - [16.3. Java](#163-java)
  - [16.4. SAM](#164-sam)
  - [16.5. Snapstart](#165-snapstart)
  - [16.6. Code organization](#166-code-organization)
- [17. DLQs](#17-dlqs)
- [18. Extensions](#18-extensions)
- [19. Event Source Mapping](#19-event-source-mapping)
  - [19.1. Core](#191-core)
  - [19.2. Filtering](#192-filtering)
  - [19.3. FIFO SQS as Event Source](#193-fifo-sqs-as-event-source)
  - [19.4. Kinesis](#194-kinesis)
- [20. Function URLs](#20-function-urls)
- [21. Integration with AWS Services](#21-integration-with-aws-services)
  - [21.1. ECS](#211-ecs)
  - [21.2. CloudFront](#212-cloudfront)
  - [21.3. Kafka](#213-kafka)
  - [21.4. Parameter store](#214-parameter-store)
  - [21.5. S3](#215-s3)
  - [21.6. Secrets-Manager](#216-secrets-manager)
  - [21.7. SNS](#217-sns)
  - [21.8. SQS](#218-sqs)
- [22. Invocation Models](#22-invocation-models)
- [23. Kinesis](#23-kinesis)
- [24. Layers](#24-layers)
- [25. Monitoring](#25-monitoring)
- [26. Networking](#26-networking)
  - [26.1. VPC-Enabled Lambda](#261-vpc-enabled-lambda)
    - [26.1.1. Deploying to a VPC](#2611-deploying-to-a-vpc)
- [27. Observability](#27-observability)
- [28. Pricing](#28-pricing)
- [29. Powertools](#29-powertools)
  - [29.1. Java](#291-java)
  - [29.2. Typescript](#292-typescript)
- [30. Skillbuilder Courses](#30-skillbuilder-courses)
- [31. Scaling and concurrency in Lambda](#31-scaling-and-concurrency-in-lambda)
  - [31.1. Core Concepts](#311-core-concepts)
  - [31.2. Provisioned Concurrency](#312-provisioned-concurrency)
  - [31.3. Devops](#313-devops)
  - [31.4. Concurrency Controls and Quotas](#314-concurrency-controls-and-quotas)
  - [31.5. Max Concurrency](#315-max-concurrency)
  - [31.6. Throttling](#316-throttling)
- [32. RDSProxy](#32-rdsproxy)
- [33. Security](#33-security)
  - [33.1. Lambda Security Using Resource Policy / ExecutionRole](#331-lambda-security-using-resource-policy--executionrole)
  - [33.2. Lambda Authorizers](#332-lambda-authorizers)
  - [33.3. Using Cognito to secure Lambda's function URL](#333-using-cognito-to-secure-lambdas-function-url)
- [34. Stream Event Sources](#34-stream-event-sources)
- [35. Testing](#35-testing)
- [36. Spring](#36-spring)
- [37. Tooling](#37-tooling)
- [38. Troubleshooting](#38-troubleshooting)
- [39. Tracing](#39-tracing)
- [40. User Stories](#40-user-stories)
- [41. Use cases](#41-use-cases)
  - [41.1. Aggregating Data](#411-aggregating-data)
- [42. Videos](#42-videos)
- [43. Workshops](#43-workshops)
- [44. Well Architected](#44-well-architected)
  - [44.1. Series TOC](#441-series-toc)
  - [44.2. Controlling serverless API access](#442-controlling-serverless-api-access)
  - [44.3. Security](#443-security)
  - [44.4. Service Quotas / Scaling / Concurrency](#444-service-quotas--scaling--concurrency)
  - [44.5. Event Driven](#445-event-driven)
  - [44.6. How to secure workloads with public endpoints](#446-how-to-secure-workloads-with-public-endpoints)
  - [44.7. Serverless Lens](#447-serverless-lens)
- [45. Staging](#45-staging)

<!-- /TOC -->

# 1. Best Practices

1. [[MY NEXT] AWS LAMBDA BEST PRACTICES](https://dev.to/aws-builders/aws-lambda-best-practices-4chn)
2. [Best practices for organizing larger serverless applications by James Beswick ](https://aws.amazon.com/blogs/compute/best-practices-for-organizing-larger-serverless-applications/)
3. [aws api gateway & lambda: multiple endpoint/functions vs single endpoint](https://stackoverflow.com/questions/41425511/aws-api-gateway-lambda-multiple-endpoint-functions-vs-single-endpoint)
4. [Best Practices for Integrating Amazon SQS with AWS Lambda](https://levelup.gitconnected.com/best-practices-for-integrating-amazon-sqs-with-aws-lambda-443db4b959e0)

# 2. AWS Lambda Web Adapter

1. [Using response streaming with AWS Lambda Web Adapter to optimize performance by Harold Sun](https://aws.amazon.com/blogs/compute/using-response-streaming-with-aws-lambda-web-adapter-to-optimize-performance/)
1. [A tool to run web applications on AWS Lambda](https://github.com/awslabs/aws-lambda-web-adapter)
1. [Developing portable AWS Lambda functions By Uri Segev,]()
1. [AWS Lambda Web Adapter](https://github.com/awslabs/aws-lambda-web-adapter)

# 3. Architecture

1. [AWS re:Invent 2022 - A closer look at AWS Lambda (SVS404-R)](https://www.youtube.com/watch?v=0_jfH6qijVY&t=1308s)

# 4. Autoscaling

See [autoscaling-backlog.md](./autoscaling-backlog.md)

# 5. Batch Processing

## 5.1. Why is it cost effective?

1. [Why you should avoid setting batch size = 1 for the SQS/Lambda event pattern By Capital One Tech](https://medium.com/capital-one-tech/best-practices-configuring-aws-lambda-sqs-batch-size-27ebc8a5d5ce)

## 5.2. Checkpointing

1. [Optimizing batch processing with custom checkpoints in AWS Lambda by James Beswick |](https://aws.amazon.com/blogs/compute/optimizing-batch-processing-with-custom-checkpoints-in-aws-lambda/)

# 6. Costs

1. [Understanding techniques to reduce AWS Lambda costs in serverless applications by James Beswick](https://aws.amazon.com/blogs/compute/understanding-techniques-to-reduce-aws-lambda-costs-in-serverless-applications/)
2. [Serverless Automated Cost Controls, Part1 by Shankar Ramachandran ](https://aws.amazon.com/blogs/compute/serverless-automated-cost-controls-part1/)
    - [cfn_budget_lambda_blog_post.yaml](./templates/cfn_budget_lambda_blog_post.yaml)

# 7. Cookbooks

1. [aws/serverless-application-model](https://github.com/aws/serverless-application-model/tree/master/examples)
- Found this link from https://aws.amazon.com/blogs/architecture/top-resources-for-api-architects-and-developers/
2. [AWS Lambda Handler Cookbook - A Serverless Service Template By Ran the builder](https://ran-isenberg.github.io/aws-lambda-handler-cookbook/)
- [Lambda cookbook with Python](https://www.youtube.com/watch?v=yoLAuHaIRs8)

## 7.1. Cookbook by Ran Isenberg

1. [AWS Lambda Cookbook — Part 1 — Logging Best Practices By Ran Isenberg](https://www.ranthebuilder.cloud/post/aws-lambda-cookbook-elevate-your-handler-s-code-part-1-logging)
2. [AWS Lambda Cookbook — Part 2 — Observability Best Practices By Ran Isenberg](https://www.ranthebuilder.cloud/post/aws-lambda-cookbook-elevate-your-handler-s-code-part-2-observability)
3. [AWS Lambda Cookbook — Part 3 —Business Domain Observability Best Practices By Ran Isenberg](https://www.ranthebuilder.cloud/post/aws-lambda-cookbook-elevate-your-handler-s-code-part-3-business-domain-observability)
4. [AWS Lambda Cookbook  - Part 4 - Environment Variables Best Practices By Ran Isenberg](https://www.ranthebuilder.cloud/post/aws-lambda-cookbook-environment-variables)
5. [AWS Lambda Cookbook  - Part 5 - Input Validation Best Practices By Ran Isenberg](https://www.ranthebuilder.cloud/post/aws-lambda-cookbook-elevate-your-handler-s-code-part-5-input-validation)
6. [AWS Lambda Cookbook  - Part 6 -  Configuration & Feature Flags Best Practices By Ran Isenberg](https://www.ranthebuilder.cloud/post/aws-lambda-cookbook-part-6-feature-flags-configuration-best-practices)
7. [Start Your AWS Serverless Service With Two Clicks By Ran Isenberg](https://www.ranthebuilder.cloud/post/aws-lambda-cookbook-part-7-how-to-use-the-aws-lambda-cookbook-github-template-project)
8. [AWS CDK - Best Practices From The Trenches By Ran Isenberg](https://www.ranthebuilder.cloud/post/aws-cdk-best-practices-from-the-trenches)

# 8. CI/CD

1. [Using AWS Lambda SnapStart with infrastructure as code and CI/CD pipelines by James Beswick](https://aws.amazon.com/blogs/compute/using-aws-lambda-snapstart-with-infrastructure-as-code-and-ci-cd-pipelines/)
2. [Implementing cross-account CI/CD with AWS SAM for container-based Lambda functions by Eric Johnson ](https://aws.amazon.com/blogs/compute/implementing-cross-account-cicd-with-aws-sam-for-container-based-lambda/)

# 9. Cross-account access

1. [Introducing cross-account Amazon ECR access for AWS Lambda by Brian Zambrano](https://aws.amazon.com/blogs/compute/introducing-cross-account-amazon-ecr-access-for-aws-lambda/)
    - Shows how to centralize docker images to a single account. This way Lambda function can reside in a different AWS account (in Account A) from ECR repository (in Account B) that owned the container image

## 9.1. Blue Green Deployments

1. [Blue/Green Deployment of API Using AWS Lambda and API Gateway BY Enlear Academy](https://enlear.academy/blue-green-deployment-of-api-using-aws-lambda-and-api-gateway-6668fe73c5fe)
1. [The challenges of blue/green deployment with AWS Lambda and CloudFormation By Ben Kehoe](https://ben11kehoe.medium.com/some-quick-thoughts-on-blue-green-deployment-for-lambda-with-cloudformation-ac66797984f)

## 9.2. Canary Deployments

1. [Implementing Canary Deployments of AWS Lambda Functions with Alias Traffic Shifting](https://aws.amazon.com/blogs/compute/implementing-canary-deployments-of-aws-lambda-functions-with-alias-traffic-shifting/)
1. [AWS Lambda Canary Deployment By Yan Cui](https://lumigo.io/aws-lambda-deployment/canary-deployment-for-aws-lambda/)

# 10. Code Signing

1. [Best practices and advanced patterns for Lambda code signing by Cassia Martin By Ryan Green](https://aws.amazon.com/blogs/security/best-practices-and-advanced-patterns-for-lambda-code-signing/)

# 11. Core Topics

## 11.1. Throttling

1. [Understanding AWS Lambda’s invoke throttling limits](https://aws.amazon.com/blogs/compute/understanding-aws-lambdas-invoke-throttle-limits/)

## 11.2. Error Handling

1. [Implementing AWS Lambda error handling patterns by Julian Wood, Jeff Chen, and Jeff Li](https://aws.amazon.com/blogs/compute/implementing-aws-lambda-error-handling-patterns/)
    - [Github code](https://github.com/aws-samples/aws-lambda-error-handling-pattern)
2. [Implementing error handling for AWS Lambda asynchronous invocations By Poornima Chand](https://aws.amazon.com/blogs/compute/implementing-error-handling-for-aws-lambda-asynchronous-invocations/)

### 11.2.1. Exponential backoff and jitter algorithm

1. [Using Amazon SQS dead-letter queues to replay messages By Alexandre Pinhel](https://aws.amazon.com/blogs/compute/using-amazon-sqs-dead-letter-queues-to-replay-messages)

# 12. Cloudwatch Insights

1. [Understanding AWS Lambda behavior using Amazon CloudWatch Logs Insights by Greg Eppel](https://aws.amazon.com/blogs/mt/understanding-aws-lambda-behavior-using-amazon-cloudwatch-logs-insights/)
1. [Operating Lambda: Using CloudWatch Logs Insights by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-using-cloudwatch-logs-insights/)
1. [Operating Lambda: Logging and custom metrics by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-logging-and-custom-metrics/)

# 13. Comparison between REST and GraphQL API architectures

1. [[MY NEXT] How to Architect APIs for Scale and Security by George Mao](https://aws.amazon.com/blogs/architecture/how-to-architect-apis-for-scale-and-security/)

# 14. Destinations

1. [Introducing AWS Lambda Destinations by Julian Wood](https://aws.amazon.com/blogs/compute/introducing-aws-lambda-destinations/)
> 1. [[**MY-NEXT**] Lambda Destinations: What We Learned the Hard Way By Trek10](https://www.trek10.com/blog/lambda-destinations-what-we-learned-the-hard-way)

# 15. Devops

1. [Automatically Detect Operational Issues in Lambda Functions with Amazon DevOps Guru for Serverless by Marcia Villalba](https://aws.amazon.com/blogs/aws/automatically-detect-operational-issues-in-lambda-functions-with-amazon-devops-guru-for-serverless/)

# 16. Development

## 16.1. Compilation techniques

1. [[CDK] Increasing performance of Java AWS Lambda functions using tiered compilation By Mark Sailes](https://aws.amazon.com/blogs/compute/increasing-performance-of-java-aws-lambda-functions-using-tiered-compilation/)
- https://github.com/aws-samples/aws-lambda-java-tiered-compilation-example/tree/main

## 16.2. Designing

1. [Developing portable AWS Lambda functions by Pascal Vogel](https://aws.amazon.com/blogs/compute/developing-portable-aws-lambda-functions/)
2. [Developing evolutionary architecture with AWS Lambda by Luca Mezzalira](https://aws.amazon.com/blogs/compute/developing-evolutionary-architecture-with-aws-lambda/)
3.  [Issues to Avoid When Implementing Serverless Architecture with AWS Lambda by Andrei Maksimov](https://aws.amazon.com/blogs/architecture/mistakes-to-avoid-when-implementing-serverless-architecture-with-lambda/)

## 16.3. Java

1. [Bootstrapping a Java Lambda application with minimal AWS Java SDK startup time using Maven by Zoe Wang ](https://aws.amazon.com/blogs/developer/bootstrapping-a-java-lambda-application-with-minimal-aws-java-sdk-startup-time-using-maven/)
    - shows how to create maven archetypes

## 16.4. SAM

1. [[MY NEXT] Building serverless Java applications with the AWS SAM CLI by James Beswick](https://aws.amazon.com/blogs/compute/building-serverless-java-applications-with-the-aws-sam-cli/)
    - [Building Java apps with SAM | Serverless Office Hours](https://www.youtube.com/watch?v=c7_gGalsIyE)

## 16.5. Snapstart

1. [Reducing Java cold starts on AWS Lambda functions with SnapStart by Mark Sailes](https://aws.amazon.com/blogs/compute/reducing-java-cold-starts-on-aws-lambda-functions-with-snapstart/)
    - https://serverlessland.com/patterns/apigw-lambda-snapstart

## 16.6. Code organization

1. [Best practices for organizing larger serverless applications by James Beswick](https://aws.amazon.com/blogs/compute/best-practices-for-organizing-larger-serverless-applications/)

# 17. DLQs

1. [Durable Serverless Architectures: Working with Dead-Letter Queues API309 By Otavio Ferreira](https://d1.awsstatic.com/events/reinvent/2019/Durable_serverless_architecture_Working_with_dead-letter_queues_API309.pdf)
2. [Asynchronous invocation](https://docs.aws.amazon.com/lambda/latest/dg/invocation-async.html)
3. [Configuring an Amazon SNS dead-letter queue for a subscription](https://docs.aws.amazon.com/sns/latest/dg/sns-configure-dead-letter-queue.html)
4. [Designing durable serverless apps with DLQs for Amazon SNS, Amazon SQS, AWS Lambda By Otavio Ferreira](https://aws.amazon.com/blogs/compute/designing-durable-serverless-apps-with-dlqs-for-amazon-sns-amazon-sqs-aws-lambda/)
5. [Introducing payload-based message filtering for Amazon SNS](https://aws.amazon.com/blogs/compute/introducing-payload-based-message-filtering-for-amazon-sns/)
6. [**[MY NEXT]** Implementing AWS Lambda error handling patterns by Julian Wood, Jeff Chen, and Jeff Li ](https://aws.amazon.com/blogs/compute/implementing-aws-lambda-error-handling-patterns/)
7. [**[MY NEXT]** Using Amazon SQS dead-letter queues to replay messages By Alexandre Pinhel](https://aws.amazon.com/blogs/compute/using-amazon-sqs-dead-letter-queues-to-replay-messages/?ref=serverlessland)
8. [How do dead-letter queues work](https://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/sqs-dead-letter-queues.html)

# 18. Extensions

1. [Introducing AWS Lambda Extensions by Julian Wood](https://aws.amazon.com/blogs/compute/introducing-aws-lambda-extensions-in-preview/)
1. [Creating AWS Lambda environment variables from AWS Secrets Manager by Andy Hall](https://aws.amazon.com/blogs/compute/creating-aws-lambda-environmental-variables-from-aws-secrets-manager/)
<img src="./images/extensions-1.png" title="extensions-1.png" width="900"/>

# 19. Event Source Mapping

## 19.1. Core

1. [AWS Lambda Permissions: Execution Role and Resource-based Policies By Mehmet Ozkaya](https://medium.com/aws-lambda-serverless-developer-guide-with-hands/aws-lambda-permissions-execution-role-and-resource-based-policies-be2e325998fc)

## 19.2. Filtering

1. [Understanding Amazon SQS and AWS Lambda Event Source Mapping for Efficient Message Processing by Tushar Sharma and Shaun Wang](https://aws.amazon.com/blogs/apn/understanding-amazon-sqs-and-aws-lambda-event-source-mapping-for-efficient-message-processing/)
2. [Filtering event sources for AWS Lambda functions by Benjamin Smith](https://aws.amazon.com/blogs/compute/filtering-event-sources-for-aws-lambda-functions/)
    - Show how to filter messages before the invocation of a Lambda function
3. [[Workshop] Serverless Optimization Workshop (Performance and Cost) > Event Filtering](https://catalog.us-east-1.prod.workshops.aws/workshops/2d960419-7d15-44e7-b540-fd3ebeb7ce2e/en-US/event-filtering)

## 19.3. FIFO SQS as Event Source

1. [New for AWS Lambda – SQS FIFO as an event source by James Beswick ](https://aws.amazon.com/blogs/compute/new-for-aws-lambda-sqs-fifo-as-an-event-source/)

## 19.4. Kinesis

1. [Filtering event sources for AWS Lambda functions](https://aws.amazon.com/blogs/compute/filtering-event-sources-for-aws-lambda-functions/)

# 20. Function URLs

1. [Announcing AWS Lambda Function URLs: Built-in HTTPS Endpoints for Single-Function Microservices by Alex Casalboni ](https://aws.amazon.com/blogs/aws/announcing-aws-lambda-function-urls-built-in-https-endpoints-for-single-function-microservices/)
1. [Protecting an AWS Lambda function URL with Amazon CloudFront and Lambda@Edge](https://aws.amazon.com/blogs/compute/protecting-an-aws-lambda-function-url-with-amazon-cloudfront-and-lambdaedge/)

# 21. Integration with AWS Services

## 21.1. ECS

1. [[MUST SEE] Run message-driven workloads at scale by using AWS Fargate](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/run-message-driven-workloads-at-scale-by-using-aws-fargate.html)
2. [[MUST SEE] Run event-driven and scheduled workloads at scale with AWS Fargate](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/run-event-driven-and-scheduled-workloads-at-scale-with-aws-fargate.html)

## 21.2. CloudFront

1. [Using Amazon CloudFront with AWS Lambda as origin to accelerate your web applications by Jaiganesh Girinathan and Samrat Karak](https://aws.amazon.com/blogs/networking-and-content-delivery/using-amazon-cloudfront-with-aws-lambda-as-origin-to-accelerate-your-web-applications/)

## 21.3. Kafka

1. [Using Aws Lambda To Process Apache Kafka Streams](https://serverlessland.com/content/guides/lambda-kafka/introduction)

## 21.4. Parameter store

1. [Using the AWS Parameter and Secrets Lambda extension to cache parameters and secrets By Pal Patel and Saud ul Khalid](https://aws.amazon.com/blogs/compute/using-the-aws-parameter-and-secrets-lambda-extension-to-cache-parameters-and-secrets/)

<img src="./images/secrets-1.png" title="secrets-1.png" width="900"/>

## 21.5. S3

1. [S3-to-Lambda patterns: Avoiding recursive invocation](https://github.com/aws-samples/s3-to-lambda-invocation-patterns)
2. [Manage Amazon S3 Event Notifications using a Lambda function by Philip Chen](https://aws.amazon.com/blogs/infrastructure-and-automation/manage-amazon-s3-event-notifications-using-lambda-function/)
3. [[MY NEXT] Manage Amazon S3 Event Notifications using a Lambda function by Philip Chen ](https://aws.amazon.com/blogs/infrastructure-and-automation/manage-amazon-s3-event-notifications-using-lambda-function/)
<img src="./images/lambd-s3-notifications-2.jpg" title="lambd-s3-notifications-2.jpg" width="900"/>

## 21.6. Secrets-Manager

1. [Lambda function had to reside in the same AWS account as the ECR repository that owned the container image by Andy Hall](https://aws.amazon.com/blogs/compute/creating-aws-lambda-environmental-variables-from-aws-secrets-manager/)
1. [Cache secrets using AWS Lambda extensions](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/cache-secrets-using-aws-lambda-extensions.html)
1. [How to securely provide database credentials to Lambda functions by using AWS Secrets Manager by Ramesh Adabala, Anand Komandooru, and Noorul Hasan](https://aws.amazon.com/blogs/security/how-to-securely-provide-database-credentials-to-lambda-functions-by-using-aws-secrets-manager/)
> 1. [[**MY-NEXT**] Retrieving parameters and secrets with Powertools for AWS Lambda (TypeScript) by Pascal Vogel](https://aws.amazon.com/blogs/compute/retrieving-parameters-and-secrets-with-powertools-for-aws-lambda-typescript/)
1. [Using the AWS Parameters and Secrets Lambda Extension with Python: A Practical Example](https://community.aws/posts/parameters-and-secrets-lambda-extension-with-python)
1. [Using the AWS Parameter and Secrets Lambda extension to cache parameters and secrets By Pal Patel and Saud ul Khalid](https://aws.amazon.com/blogs/compute/using-the-aws-parameter-and-secrets-lambda-extension-to-cache-parameters-and-secrets/)

<img src="./images/secrets-1.png" title="secrets-1.png" width="900"/>

## 21.7. SNS

1. [Invoking AWS Lambda functions via Amazon SNS by Arjun Cholkar](https://aws.amazon.com/tw/blogs/mobile/invoking-aws-lambda-functions-via-amazon-sns/)
2. [How do I subscribe a Lambda function to an Amazon SNS topic in the same account?](https://repost.aws/knowledge-center/lambda-subscribe-sns-topic-same-account)
3. [Tutorial: Using AWS Lambda with Amazon Simple Notification Service](https://docs.aws.amazon.com/en_us/lambda/latest/dg/with-sns-example.html)

## 21.8. SQS

1. [Handle SQS message failure in batch with partial batch response feature](https://medium.com/srcecde/handle-sqs-message-failure-in-batch-with-partial-batch-response-b858ad212573)
2. [Lambda + SQS Users Should Know About This](https://www.youtube.com/watch?v=0707Py8Jyf0)
3. [Why isn't my Lambda function with an Amazon SQS event source scaling optimally?](https://repost.aws/knowledge-center/lambda-sqs-scaling)
> 4. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 1 by Pascal Vogel ](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-1/)
> 5. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 2 by Pascal Vogel ](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-2/)
> 6. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 3 by Pascal Vogel](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-3/)

# 22. Invocation Models

1. [How to Design Your Serverless Apps for Massive Scale by George Mao](https://aws.amazon.com/blogs/architecture/how-to-design-your-serverless-apps-for-massive-scale/)
2. [[MY NEXT] Understanding the Different Ways to Invoke Lambda Functions by George Mao](https://aws.amazon.com/blogs/architecture/understanding-the-different-ways-to-invoke-lambda-functions/)
3. [Best Practices for Developing on AWS Lambda by George Mao](https://aws.amazon.com/blogs/architecture/best-practices-for-developing-on-aws-lambda/)
4. [AWS Lambda Fundamentals - AWS Lambda invocation models](https://www.youtube.com/watch?v=8K3_w3QyN6M)

# 23. Kinesis

1. [Building serverless applications with streaming data: Part 1 by James Beswick ](https://aws.amazon.com/blogs/compute/building-serverless-applications-with-streaming-data-part-1/)
2. [Building serverless applications with streaming data: Part 2 by James Beswick ](https://aws.amazon.com/blogs/compute/building-serverless-applications-with-streaming-data-part-2/)
3. [Building serverless applications with streaming data: Part 3 by James Beswick ](https://aws.amazon.com/blogs/compute/building-serverless-applications-with-streaming-data-part-3/)
4. [Building leaderboard functionality with serverless data analytics by James Beswick](https://aws.amazon.com/blogs/compute/building-serverless-applications-with-streaming-data-part-4/)
5. [Monitoring and troubleshooting serverless data analytics applications by James Beswick](https://aws.amazon.com/blogs/compute/monitoring-and-troubleshooting-serverless-data-analytics-applications/)
6. [Alleycat - Building serverless applications with streaming data](https://github.com/aws-samples/serverless-streaming-data-application)

# 24. Layers

1. [Using Lambda layers to simplify your development process by James Beswick](https://aws.amazon.com/blogs/compute/using-lambda-layers-to-simplify-your-development-process/)
1. [AWS Lambda layer in Java By Asaf Adar](https://medium.com/analytics-vidhya/aws-lambda-layer-in-java-ad67ce5d94b4)

# 25. Monitoring

1. [Lambda Monitoring Overview](https://www.youtube.com/watch?v=idkluLIwous)

# 26. Networking

1. [Building private serverless APIs with AWS Lambda and Amazon VPC Lattice by James Beswick ](https://aws.amazon.com/blogs/compute/building-private-serverless-apis-with-aws-lambda-and-amazon-vpc-lattice/)
2. [AWS Lambda Fundamentals - AWS Lambda networking](https://www.youtube.com/watch?v=W1YZs1YEdKM)

## 26.1. VPC-Enabled Lambda

1. [Steps to secure AWS Serverless — Lambda (Part 1)](https://medium.com/orchestrated/steps-to-secure-aws-serverless-lambda-part-1-a6e5d1b05f45)
2. [Three ways to use AWS services from a Lambda in a VPC By Alex Debrie](https://www.alexdebrie.com/posts/aws-lambda-vpc/)
3. [Things You Must Know When Configuring Lambda With VPC Resources Access By Aruna Silva](https://medium.com/@arunasilva86/how-to-use-vpc-networking-effectively-for-lambda-445abefdf8cf)
> 4. [[**MY-NEXT**]AWS re:Invent 2020: AWS Lambda networking best practices By Uri Segev](https://www.youtube.com/watch?v=Ax6cnBEDnsM)

### 26.1.1. Deploying to a VPC

1. [Why Use AWS Lambda in a Custom VPC? by Akash Jain](https://aws.amazon.com/blogs/apn/why-use-aws-lambda-in-a-custom-vpc/)
1. [lambda inside VPC with internet access By Asaf Adar](https://medium.com/@asafadar55/vpc-lambda-internet-access-f70a55dc7a39)

# 27. Observability

1. [AWS Lambda based Serverless Observability](https://aws-observability.github.io/observability-best-practices/guides/serverless/aws-native/lambda-based-observability/)
1. [Operating Lambda: Using CloudWatch Logs Insights by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-using-cloudwatch-logs-insights/)
1. [Operating Lambda: Logging and custom metrics by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-logging-and-custom-metrics/)
1. [Operating Lambda: Isolating and resolving issues by James Beswick ](https://aws.amazon.com/blogs/compute/operating-lambda-isolating-and-resolving-issues/)

# 28. Pricing

1. [Estimating cost for Amazon SQS message processing using AWS Lambda By Sabha Parameswaran](https://aws.amazon.com/blogs/compute/estimating-cost-for-amazon-sqs-message-processing-using-aws-lambda/)

# 29. Powertools

## 29.1. Java

1. [Powertools for AWS Lambda (Java)](https://awslabs.github.io/aws-lambda-powertools-java/)
2. [Simplifying serverless best practices with AWS Lambda Powertools Java by Pankaj Agrawal](https://aws.amazon.com/blogs/opensource/simplifying-serverless-best-practices-with-aws-lambda-powertools-java/)
3. [[MY NEXT] SVS 307 Workshop - Easily Add Observability with AWS Lambda Powertools for Java](https://catalog.us-east-1.prod.workshops.aws/workshops/a7011c82-e4af-4a52-80fa-fcd61f1dacd9/en-US/introduction)

## 29.2. Typescript

1. [AWS Lambda Powertools for TypeScript Workshop](https://github.com/aws-samples/aws-lambda-powertools-typescript-workshop)

# 30. Skillbuilder Courses

1. [Troubleshooting: AWS Lambda](https://explore.skillbuilder.aws/learn/course/10771/play/41089/troubleshooting-aws-lambda)

# 31. Scaling and concurrency in Lambda

## 31.1. Core Concepts

1. [Scaling and concurrency in Lambda](https://aws.amazon.com/blogs/compute/operating-lambda-application-design-scaling-and-concurrency-part-2/)
- On-demand scaling example
- Provisioned Concurrency scaling example
- Reserved concurrency
2. [Managing AWS Lambda Function Concurrency by Chris Munns](https://aws.amazon.com/blogs/compute/managing-aws-lambda-function-concurrency/)
3. [[MY NEXT] Understanding AWS Lambda scaling and throughput by Julian Wood](https://aws.amazon.com/blogs/compute/understanding-aws-lambda-scaling-and-throughput/)

## 31.2. Provisioned Concurrency

1. [Scheduling AWS Lambda Provisioned Concurrency for recurring peak usage by Chris Munns](https://aws.amazon.com/blogs/compute/scheduling-aws-lambda-provisioned-concurrency-for-recurring-peak-usage/)

## 31.3. Devops

1. [Investigating spikes in AWS Lambda function concurrency by Chris Munns](https://aws.amazon.com/blogs/compute/investigating-spikes-in-aws-lambda-function-concurrency/)

## 31.4. Concurrency Controls and Quotas

1. [AWS Lambda Fundamentals - AWS Lambda concurrency controls and quotas](https://www.youtube.com/watch?v=5XRBZNPy6Ng)

## 31.5. Max Concurrency

1. [Does Maximum Concurrency Solve the Lambda+SQS Issue?](https://zaccharles.medium.com/does-maximum-concurrency-solve-the-lambda-sqs-issue-3c19701e6e75)
2. [Introducing maximum concurrency of AWS Lambda functions when using Amazon SQS as an event source by Julian Wood](https://aws.amazon.com/blogs/compute/introducing-maximum-concurrency-of-aws-lambda-functions-when-using-amazon-sqs-as-an-event-source/)

## 31.6. Throttling

1. [Understanding AWS Lambda’s invoke throttling limits by Archana Srikanta](https://aws.amazon.com/blogs/compute/understanding-aws-lambdas-invoke-throttle-limits/)

# 32. RDSProxy

1. [Using Amazon RDS Proxy with AWS Lambda by George Mao](https://aws.amazon.com/blogs/compute/using-amazon-rds-proxy-with-aws-lambda/)
1. [How To: Manage RDS Connections from AWS Lambda Serverless Functions By Jeremy Daly](https://www.jeremydaly.com/manage-rds-connections-aws-lambda/)

# 33. Security

## 33.1. Lambda Security Using Resource Policy / ExecutionRole

1. [Building AWS Lambda governance and guardrails by Julian Wood](https://aws.amazon.com/blogs/compute/building-aws-lambda-governance-and-guardrails/)
2. [AWS Lambda Fundamentals - AWS Lambda function permissions](https://www.youtube.com/watch?v=6oG9O44U9x0)
3. https://aws.amazon.com/blogs/compute/building-aws-lambda-governance-and-guardrails/
4. [Operating Lambda: Building a solid security foundation – Part 1 by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-building-a-solid-security-foundation-part-1/)
5. [Operating Lambda: Building a solid security foundation – Part 2 by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-building-a-solid-security-foundation-part-2/)
5. https://docs.aws.amazon.com/lambda/latest/dg/lambda-permissions.html
6. https://docs.aws.amazon.com/lambda/latest/dg/access-control-resource-based.html
7. [[MUST SEE] AWS LAMBDA OPERATOR GUIDE Security](https://serverlessland.com/content/service/lambda/guides/aws-lambda-operator-guide/security-ops)
8. [Building well-architected serverless applications: Controlling serverless API access – part 1 by Julian Wood](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-controlling-serverless-api-access-part-1/)
9. [Building well-architected serverless applications: Controlling serverless API access – part 2 by Julian Wood](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-controlling-serverless-api-access-part-2/)
10. [Building well-architected serverless applications: Controlling serverless API access – part 3 by Julian Wood](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-controlling-serverless-api-access-part-3/)

## 33.2. Lambda Authorizers

1. [Use AWS Lambda authorizers with a third-party identity provider to secure Amazon API Gateway REST APIs by Bryant Bost ](https://aws.amazon.com/blogs/security/use-aws-lambda-authorizers-with-a-third-party-identity-provider-to-secure-amazon-api-gateway-rest-apis/)
2. [How to secure API Gateway HTTP endpoints with JWT authorizer by Siva Rajamani, Rajat Mathur, and Sudhanshu Malhotra](https://aws.amazon.com/blogs/security/how-to-secure-api-gateway-http-endpoints-with-jwt-authorizer/)

## 33.3. Using Cognito to secure Lambda's function URL

1. [Securing Lambda Function URLs using Amazon Cognito, Amazon CloudFront and AWS WAF by Marcia Villalba](https://aws.amazon.com/blogs/compute/securing-lambda-function-urls-using-amazon-cognito-amazon-cloudfront-and-aws-waf/)

# 34. Stream Event Sources

1. [SVS323-R – Mastering AWS Lambda streaming event sources](https://d1.awsstatic.com/events/reinvent/2019/REPEAT_1_Mastering_AWS_Lambda_streaming_event_sources_SVS323-R1.pdf)

# 35. Testing

1. [Review of "Testing Serverless Architectures" by Yan Cui](https://www.trek10.com/blog/review-of-testing-serverless-architectures-by-yan-cui)
2. [Testing Serverless Applications By Dan Fox](https://serverlessland.com/testing)
3. [Testing Serverless Applications By Dan Fox | Serverless Office Hours](https://www.youtube.com/watch?v=8AxsqNmwAFw)

# 36. Spring

1. [AWS Lambda series by Dan Vega](https://www.youtube.com/watch?v=bxK4GscuVgs&list=PLZV0a2jwt22vS1QAp0XJsFACeLNL5oN1g)
2. [SpringBoot on Lambda By James Eastham](https://github.com/jeastham1993/java-spring-on-lambda)
- https://www.youtube.com/watch?v=eierYzOAyg8

# 37. Tooling

1. [A tool to run web apps as lambda or docker containers](https://github.com/awslabs/aws-lambda-web-adapter)

# 38. Troubleshooting

1. [AWS Lambda - Troubleshooting](https://explore.skillbuilder.aws/learn/course/internal/view/elearning/10771/aws-lambda-troubleshooting)
2. [How to Speed Up Your AWS Lambda Functions](https://explore.skillbuilder.aws/learn/course/internal/view/elearning/14738/how-to-speed-up-your-aws-lambda-functions)
3. [AWS Lambda Fundamentals - AWS Lambda observability and troubleshooting](https://www.youtube.com/watch?v=K2aebIhjWj8)
4. [Introducing new asynchronous invocation metrics for AWS Lambda By Dhiraj Mahapatro](https://aws.amazon.com/blogs/compute/introducing-new-asynchronous-invocation-metrics-for-aws-lambda/)

# 39. Tracing

1. [AWS Lambda Fundamentals - AWS Lambda tracing](https://www.youtube.com/watch?v=FSE_ngsBbTE)

# 40. User Stories

1. [Building Scalable and Responsive Big Data Interfaces with AWS Lambda by FireEye](https://aws.amazon.com/blogs/big-data/building-scalable-and-responsive-big-data-interfaces-with-aws-lambda/)

# 41. Use cases

## 41.1. Aggregating Data

1. [Build a fault-tolerant, serverless data aggregation pipeline with exactly-once processing by Lucas Rettenmeier and Kirill Bogdanov](https://aws.amazon.com/blogs/database/build-a-fault-tolerant-serverless-data-aggregation-pipeline-with-exactly-once-processing/)

# 42. Videos

1. [AWS re:Invent 2022 - Serverless Compute](https://www.youtube.com/watch?v=SbL3a9YOW7s&list=PL2yQDdvlhXf8Erryfslfo3E42QtcX-aiD)

# 43. Workshops

1. [Java on AWS Lambda](https://catalog.workshops.aws/java-on-aws-lambda/en-US)
  - From Serverful to Serverless Java with AWS Lambda
  <img src="./images/unicorn-store-overview.png" title="Using Java" width="900"/>
  - Accelerate Serverless Java with GraalVM
  <img src="./images/unicorn-location-api-overview.png" title="GraalVM" width="900"/>
  - Starting up faster with AWS Lambda SnapStart
  <img src="./images/unicorn-location-api-overview.png" title="SnapStart" width="900"/>
2. [Build a Serverless Web Application with AWS Lambda, Amazon API Gateway, AWS Amplify, Amazon DynamoDB, and Amazon Cognito](https://aws.amazon.com/getting-started/hands-on/build-serverless-web-app-lambda-apigateway-s3-dynamodb-cognito/)

# 44. Well Architected

## 44.1. Series TOC

1. [Building well-architected serverless applications: Introduction by Julian Wood](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-introduction/)

## 44.2. Controlling serverless API access

1. [Building well-architected serverless applications: Controlling serverless API access – part 1 by Julian Wood ](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-controlling-serverless-api-access-part-1/)
2. [Building well-architected serverless applications: Controlling serverless API access – part 2 by Julian Wood](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-controlling-serverless-api-access-part-2/)
3. [Building well-architected serverless applications: Controlling serverless API access – part 3 by Julian Wood ](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-controlling-serverless-api-access-part-3/)

## 44.3. Security

4. [Building well-architected serverless applications: Managing application security boundaries – part 1 by Julian Wood](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-managing-application-security-boundaries-part-1/)
5. [Building well-architected serverless applications: Managing application security boundaries – part 2 by Julian Wood ](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-managing-application-security-boundaries-part-2/)

## 44.4. Service Quotas / Scaling / Concurrency

1. [Operating Lambda: Application design and Service Quotas – Part 1 by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-application-design-and-service-quotas-part-1/)
2. [Operating Lambda: Application design – Scaling and concurrency: Part 2 by James Beswick ](https://aws.amazon.com/blogs/compute/operating-lambda-application-design-scaling-and-concurrency-part-2/)
3. [Operating Lambda: Application design – Part 3 by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-application-design-part-3/)

## 44.5. Event Driven

1. [Operating Lambda: Understanding event-driven architecture – Part 1 by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-understanding-event-driven-architecture-part-1/)
2. [Operating Lambda: Design principles in event-driven architectures – Part 2 by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-design-principles-in-event-driven-architectures-part-2/)
3. [Operating Lambda: Anti-patterns in event-driven architectures – Part 3 by James Beswick ](https://aws.amazon.com/blogs/compute/operating-lambda-anti-patterns-in-event-driven-architectures-part-3/)

## 44.6. How to secure workloads with public endpoints

1. [Operating Lambda: Building a solid security foundation – Part 1 by James Beswick ](https://aws.amazon.com/blogs/compute/operating-lambda-building-a-solid-security-foundation-part-1/)
2. [Operating Lambda: Building a solid security foundation – Part 2 by James Beswick ](https://aws.amazon.com/blogs/compute/operating-lambda-building-a-solid-security-foundation-part-2/)

## 44.7. Serverless Lens

1. https://d1.awsstatic.com/whitepapers/architecture/AWS-Serverless-Applications-Lens.pdf

# 45. Staging

1. [Extract time series from satellite weather data with AWS Lambda by Lior Perez](https://aws.amazon.com/blogs/big-data/extract-time-series-from-satellite-weather-data-with-aws-lambda/)
2. [Enrich VPC Flow Logs with resource tags and deliver data to Amazon S3 using Amazon Kinesis Data Firehose by Chaitanya Shah and Vaibhav Katkade](https://aws.amazon.com/blogs/big-data/enrich-vpc-flow-logs-with-resource-tags-and-deliver-data-to-amazon-s3-using-amazon-kinesis-data-firehose/)
3. [AWS Lambda resources](https://aws.amazon.com/lambda/resources/?aws-lambda-resources-blog.sort-by=item.additionalFields.createdDate&aws-lambda-resources-blog.sort-order=desc)
