
<!-- TOC -->

- [1. CDK Dashboard (Start here)](#1-cdk-dashboard-start-here)
- [2. My Next](#2-my-next)
- [3. Currated Lists](#3-currated-lists)
- [4. Constructs](#4-constructs)
- [5. Construct Libraries](#5-construct-libraries)
- [6. Projen](#6-projen)
- [7. CDK Tutorials](#7-cdk-tutorials)
- [8. Projects](#8-projects)
  - [8.1. Microservices](#81-microservices)
- [9. CDK + AWS Services](#9-cdk--aws-services)
  - [9.1. RDS](#91-rds)
    - [9.1.1. Cloudwatch Alarms for RDS](#911-cloudwatch-alarms-for-rds)
  - [9.2. AWS PrivateLink](#92-aws-privatelink)
  - [9.3. Cloudwatch Dashboard](#93-cloudwatch-dashboard)
  - [9.4. Hub and Spoke](#94-hub-and-spoke)
  - [9.5. ECS](#95-ecs)
    - [9.5.1. Spring + ECS](#951-spring--ecs)
  - [9.6. Lambda](#96-lambda)
    - [9.6.1. Using docker + Lamba](#961-using-docker--lamba)
  - [9.7. SQS](#97-sqs)
  - [9.8. Grafana](#98-grafana)
  - [9.9. DynamoDB](#99-dynamodb)
  - [9.10. S3](#910-s3)
  - [9.11. Kinesis](#911-kinesis)
  - [9.12. Firehose](#912-firehose)
  - [9.13. Elasticache](#913-elasticache)
  - [9.14. MongoDB](#914-mongodb)
- [10. By Language](#10-by-language)
  - [10.1. Typescript](#101-typescript)
  - [10.2. Python](#102-python)
  - [10.3. Java](#103-java)
- [11. Curate](#11-curate)
- [12. Resources](#12-resources)
- [13. Workshops](#13-workshops)
- [14. Patterns](#14-patterns)
- [15. CI/CD](#15-cicd)
- [16. Testing](#16-testing)
- [17. SAM + CDK](#17-sam--cdk)
- [18. Building Extensions](#18-building-extensions)
- [19. Curate](#19-curate)
- [React](#react)
- [Networking](#networking)
- [EKS](#eks)
- [20. Courses](#20-courses)
- [21. Examples](#21-examples)
- [22. RDS](#22-rds)
- [23. SSM](#23-ssm)
- [24. Centralized Logging](#24-centralized-logging)
- [25. ElasticCache](#25-elasticcache)
- [26. Videos to watch](#26-videos-to-watch)
- [27. Blogs](#27-blogs)
- [28. Documentation](#28-documentation)
- [CloudFormation](#cloudformation)
- [Event Driven Architecture + Lambda](#event-driven-architecture--lambda)
- [kafka](#kafka)

<!-- /TOC -->

# 1. CDK Dashboard (Start here)

1. [Awesome CDK By RAJKUMAR KALAISELVAM](https://github.com/kalaiser/awesome-cdk)

# 2. My Next

1. [Centralize Amazon CloudWatch Logs using AWS CDK by Naveen Balaraman ](https://aws.amazon.com/blogs/developer/build-infrastructure-for-centralized-logging-using-aws-cdk/)

# 3. Currated Lists

1. [Blog Posts Collection by kaliser](https://github.com/kalaiser/awesome-cdk#blog-posts--talks)
2. [Curated list for AWS CDK](https://project-awesome.org/kolomied/awesome-cdk)
3. [AWS Open Source Blog - Category: AWS Cloud Development Kit](https://aws.amazon.com/blogs/opensource/category/developer-tools/aws-cloud-development-kit/)

# 4. Constructs

1. [[START HERE] Writing a HitCounter Construct](https://cdkworkshop.com/20-typescript/40-hit-counter.html)
2. [Improve collaboration between teams by using AWS CDK constructs by Joerg Woehrle and Mohamed Othman ](https://aws.amazon.com/blogs/devops/improve-collaboration-between-teams-by-using-aws-cdk-constructs/)
3. [How to create an AWS CDK Construct By Danny Steenman](https://towardsthecloud.com/aws-cdk-construct)
4. [Writing your own AWS CDK constructs by Niranjan Manjunath](https://fourco.nl/blogs/writing-your-own-aws-cdk-constructs/)
5. [Understand Constructs in AWS CDK and learn how to build your first L3 Constructs for reusing your infrastructure as code — AWS CDK](https://medium.com/devops-techable/understand-constructs-in-aws-cdk-and-learn-how-to-build-your-first-l3-constructs-for-reusing-your-62e60b9a8da8)
6. [What Are AWS CDK Constructs, Stacks and How To Use Them](https://dev.to/faizanraza_interweave/what-are-aws-cdk-constructs-stacks-and-how-to-use-them-169c)
7. [[START HERE] What are Constructs in AWS CDK - Complete Guide By Borislav Hadzhiev](https://bobbyhadz.com/blog/cdk-constructs-tutorial)
8. [Getting Started with AWS Solutions Constructs](https://docs.aws.amazon.com/solutions/latest/constructs/getting-started-with-aws-solutions-constructs.html)

# 5. Construct Libraries

1. [How to use cdk-dynamo-table-viewer typescript library](https://cdkworkshop.com/20-typescript/50-table-viewer.html)
2. [How to use cdk-dynamo-table-viewer java library](https://cdkworkshop.com/50-java/50-table-viewer.html)

# 6. Projen

1. [A Beginner's Guide to Create AWS CDK Construct Library with projen](https://dev.to/aws-builders/a-beginner-s-guide-to-create-aws-cdk-construct-library-with-projen-5eh4)

# 7. CDK Tutorials

1. [AWS CDK Part 1: How to create a custom VPC](https://blog.codecentric.de/en/2019/09/aws-cdk-create-custom-vpc/)
2. [AWS CDK Part 2: How to create an S3 Bucket](https://blog.codecentric.de/en/2019/10/aws-cdk-part-2-s3-bucket/)
3. [AWS CDK Part 3: How to create an RDS instance](https://blog.codecentric.de/en/2019/11/aws-cdk-part-3-how-to-create-an-rds-instance/)
4. [AWS CDK Part 4: How to create Lambdas](https://blog.codecentric.de/en/2019/11/aws-cdk-part-4-create-lambdas/)
5. [AWS CDK Part 5: How to create a step function](https://blog.codecentric.de/en/2019/11/aws-cdk-part-5-create-step-functions/)
6. [AWS CDK Part 6: Lessons learned	AWS CDK Part 6: Lessons learned](https://www.codecentric.de/wissens-hub/blog/aws-cdk-part-6-lessons-learned)
7. [Loan Broker with AWS CDK](https://github.com/aws-samples/aws-cdk-loan-broker)
- This project is an AWS Cloud Development Kit (CDK) implementation of [Gregor Hohpe's Loan Broker example](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions.html)

# 8. Projects

1. [How to use Multiple load balancer Target Group Support for Amazon ECS to access internal and external service endpoint using the same DNS name by Vijay Menon](https://aws.amazon.com/blogs/containers/how-to-use-multiple-load-balancer-target-group-support-for-amazon-ecs-to-access-internal-and-external-service-endpoint-using-the-same-dns-name/)
 - Code [serverless-container-constructs](https://github.com/aws-samples/serverless-container-constructs)

## 8.1. Microservices

# 9. CDK + AWS Services

## 9.1. RDS

1. [Use AWS CDK to initialize Amazon RDS instances](https://aws.amazon.com/blogs/infrastructure-and-automation/use-aws-cdk-to-initialize-amazon-rds-instances/)
2. [AWS CDK Java setup for Aurora RDS and Client VPN](https://slawomirstec.com/blog/2021/04/cdk-rds-vpn)
3. [RDS Example in AWS CDK - Complete Guide By Borislav Hadzhiev](https://bobbyhadz.com/blog/aws-cdk-rds-example)
4. [Cross-account RDS access using AWS Privatelink demo](https://github.com/aws-samples/amazon-rds-over-privatelink-cdk-demo)
5. [Run an Active-Passive, multi region API using Aurora RDS Global Cluster by Siva Ramani and Subramanian Thangavelu ](https://aws.amazon.com/blogs/developer/10322-2/)

### 9.1.1. Cloudwatch Alarms for RDS

1. [Understanding AWS RDS CPU Utilization via CloudWatch Alarms with CDK](https://thecodinginterface.com/blog/aws-aurora-rds-cloudwatch-alarms-and-cdk/)
2. [[MUST SEE] Aurora PostgreSQL Slow Query Logging and CloudWatch Alarms via AWS CDK](https://thecodinginterface.com/blog/aurora-postgresql-slow-query-logging/)

## 9.2. AWS PrivateLink

1. [Exploring AWS VPC Endpoints by Examples with AWS CDK](https://thecodinginterface.com/blog/aws-vpc-endpoints-with-cdk/)
2. [Cross-account RDS access using AWS Privatelink demo](https://github.com/aws-samples/amazon-rds-over-privatelink-cdk-demo)
3. [Private cross-account APIs with Amazon API Gateway and AWS PrivateLink [Python]](https://github.com/aws-samples/apigateway-vpcendpoints)

## 9.3. Cloudwatch Dashboard

1. [CloudWatch Dashboards as Code (the Right Way) Using AWS CDK by Simon-Pierre Gingras](https://medium.com/poka-techblog/cloudwatch-dashboards-as-code-the-right-way-using-aws-cdk-1453309c5481)
2. [The CloudWatch Dashboard using CDK](https://github.com/cdk-patterns/serverless/blob/main/the-cloudwatch-dashboard/)
3. [CloudWatch Lambda Dashboards using CDK @ aws-samples](https://github.com/aws-samples/aws-cdk-lambda-cloudwatch-dashboard)

## 9.4. Hub and Spoke

1. [[MY NEXT] Centralize access using VPC interface endpoints to access AWS services across multiple VPCs by Chetan Agrawal](https://aws.amazon.com/blogs/networking-and-content-delivery/centralize-access-using-vpc-interface-endpoints/)
2. [AWS Hub and Spoke Architecture with an Inspection VPC @ aws-samples](https://github.com/aws-samples/hub-and-spoke-with-inspection-vpc-cdk)

## 9.5. ECS

1. [Getting started with the AWS Cloud Development Kit for Amazon ECS by Nathan Peck ](https://aws.amazon.com/blogs/compute/getting-started-with-the-aws-cloud-development-kit-for-amazon-ecs/)
2. [Developing microservices using container image support for AWS Lambda and AWS CDK](https://aws.amazon.com/blogs/opensource/developing-microservices-using-container-image-support-for-aws-lambda-and-aws-cdk/)
3. [Deploying a Spring boot app to Fargate with the AWS CDK](https://www.profit4cloud.nl/blog/deploying-a-spring-boot-app-to-fargate-with-the-aws-cdk/)
4. [Getting Started with ECS](https://github.com/aws-samples/aws-cdk-microservices-workshop)

### 9.5.1. Spring + ECS

1. [Deploying a Spring boot app to Fargate with the AWS CDK](https://www.profit4cloud.nl/blog/deploying-a-spring-boot-app-to-fargate-with-the-aws-cdk/)
2. [How to build microsservices with Spring Boot and AWS with Fargate and DOCKER - Part 1](https://dev.to/pedrospiet/how-to-build-microsservices-with-spring-boot-and-aws-with-fargate-and-docker-part-1-4chc)
3. [How to build microsservices with Spring Boot and AWS with Fargate and DOCKER - Part 2](https://dev.to/pedrospiet/how-to-build-microsservices-with-spring-boot-and-aws-with-fargate-and-docker-part-2-687)
4. [How to build microsservices with Spring Boot and AWS with Fargate and DOCKER - Part 3](https://dev.to/pedrospiet/how-to-build-microsservices-with-spring-boot-and-aws-with-fargate-and-docker-part-3-17je)
5. [How to build microsservices with Spring Boot and AWS with Fargate and DOCKER - Part 4](https://dev.to/pedrospiet/how-to-build-microsservices-with-spring-boot-and-aws-with-fargate-and-docker-part-4-4ldp)
6. [How to build microsservices with Spring Boot and AWS with Fargate and DOCKER - Part 5](https://dev.to/pedrospiet/how-to-build-microsservices-with-spring-boot-and-aws-with-fargate-and-docker-part-5-dln)
7. [How to build microsservices with Spring Boot and AWS with Fargate and DOCKER - Part 6](https://dev.to/pedrospiet/how-to-build-microsservices-with-spring-boot-and-aws-with-fargate-and-docker-part-6-4c42)
8. [How to build microsservices with Spring Boot and AWS with Fargate and DOCKER - Part 7](https://dev.to/pedrospiet/how-to-build-microsservices-with-spring-boot-and-aws-with-fargate-and-docker-part-7-1m07)

## 9.6. Lambda

1. [Deploy a Java Lambda Function and API Gateway with AWS CDK](https://blog.tericcabrel.com/aws-lambda-java-cdk/)
2. [How to package multiple Java Lambdas](https://github.com/aws-samples/cdk-lambda-packaging-java)
3. [Implementing, bundling and deploying multi-language serverless applications using AWS CDK](https://github.com/aws-samples/cdk-lambda-bundling)

### 9.6.1. Using docker + Lamba

1. [How to deploy multiple Java AWS Lambdas with Docker and CDK?](https://stackoverflow.com/questions/70030472/how-to-deploy-aws-lambdas-with-docker-and-cdk)
2. [Package and deploy a Lambda function as a Docker container with AWS CDK	](https://itnext.io/package-and-deploy-a-lambda-function-as-a-docker-container-with-aws-cdk-fd0df5e37de7)

## 9.7. SQS

1. [Implementing Well-Architected Best Practices for Amazon SQS with CDK](https://github.com/aws-samples/amazon-sqs-best-practices-cdk)

## 9.8. Grafana

1. [CDK Grafana](https://github.com/aws-samples/aws-cdk-grafana)

## 9.9. DynamoDB

1. [Beginner's Guide to DynamoDB with AWS CDK: Step-by-Step Tutorial for provisioning NoSQL Databases](https://www.youtube.com/watch?v=4o1KzB2AAk4)

## 9.10. S3

1. [S3 Content Distribution via CloudFront Signed Urls Provisioned with AWS CDK](https://thecodinginterface.com/blog/signed-urls-cloudfront-s3/)
2. [Manage Amazon S3 Event Notifications using a Lambda function by Philip Chen](https://aws.amazon.com/blogs/infrastructure-and-automation/manage-amazon-s3-event-notifications-using-lambda-function/)

## 9.11. Kinesis

1. [Deliver DynamoDB records to Amazon S3 using Kinesis Data Streams and Kinesis Data Firehose with AWS CDK](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/deliver-dynamodb-records-to-amazon-s3-using-kinesis-data-streams-and-kinesis-data-firehose-with-aws-cdk.html)

## 9.12. Firehose

1. [aws-cdk-centralize-logs-typescript](https://github.com/aws-samples/aws-cdk-centralize-logs-typescript)
- This is a CDK project that set up centralized logging to an S3 bucket via a Kinesis Firehose.

## 9.13. Elasticache

1. [Deploy Amazon ElastiCache for Redis using AWS CDK](https://github.com/aws-samples/amazon-elasticache-demo-using-aws-cdk)

## 9.14. MongoDB

1. [How to Deploy MongoDB Atlas with AWS CDK in TypeScript](https://www.youtube.com/watch?v=p7Fru0fbmxY)

# 10. By Language

## 10.1. Typescript

1. [AWS Infrastructure with TypeScript: Getting Started by David Tucker](https://app.pluralsight.com/library/courses/aws-infrastructure-typescript-getting-started/table-of-contents)
2. [Udemty Course - AWS & Typescript Masterclass - CDK V2, Serverless, React](https://www.udemy.com/course/aws-typescript-cdk-serverless-react/)
3. [The TypeScript Workshop for CDK](https://cdkworkshop.com/20-typescript.html)
4. [TYPESCRIPT PROJECT TEMPLATE](https://github.com/aws-samples/configurable-dynamic-cdk-resource-provisioning-typescript)
5. [Using strong typing practices to declare a large number of resources with AWS CDK by Ishan Bhanuka ](https://aws.amazon.com/blogs/opensource/using-strong-typing-practices-to-declare-a-large-number-of-resources-with-aws-cdk/)
- https://github.com/aws-samples/strong-typing-practices-with-cdk

## 10.2. Python

1. [The Python Workshop for CDK](https://cdkworkshop.com/15-prerequisites/600-python.html)
2. [[PYTHON PROJECT TEMPLATE] Recommended AWS CDK project structure for Python applications by Alex Pulver](https://aws.amazon.com/blogs/developer/recommended-aws-cdk-project-structure-for-python-applications/)

## 10.3. Java

1. [The Java Workshop for CDK](https://cdkworkshop.com/50-java.html)
2. [Writing your CDK in Java By Melina Schweizer](https://medium.com/i-love-my-local-farmer-engineering-blog/writing-your-cdk-in-java-685a380d8e4e)
3. [Packaging and deploying AWS Lambda functions written in Java with AWS Cloud Development Kit	By Pankaj Agarwal](https://aws.amazon.com/blogs/opensource/packaging-and-deploying-aws-lambda-functions-written-in-java-with-aws-cloud-development-kit/)
4. [cdk-lambda-packaging-java @ aws-samples](https://github.com/aws-samples/cdk-lambda-packaging-java/tree/main)
5. [AWS CDK constructs for Java @ aws-sample](https://github.com/aws-samples/aws-cdk-constructs-for-java)
6. [[CDK] Increasing performance of Java AWS Lambda functions using tiered compilation By Mark Sailes](https://aws.amazon.com/blogs/compute/increasing-performance-of-java-aws-lambda-functions-using-tiered-compilation/)
- https://github.com/aws-samples/aws-lambda-java-tiered-compilation-example/tree/main

# 11. Curate

1. [AWS CDK Workshop - From Zero to Hero Part I By Cloudvisor](https://www.youtube.com/watch?v=ubhnzRI_FMs)
2. [AWS CDK Serverless Cookbook By Miguel A. Calles](https://medium.com/chapter-by-chapter/aws-cdk-serverless-cookbook-ebook-1d4d4e0488c)
3. [AWS CDK — A Beginner’s Guide with Examples](https://enlear.academy/aws-cdk-a-beginners-guide-with-examples-424c600ac409)
4. [[MUST SEE] Centralize Amazon CloudWatch Logs using AWS CDK by Naveen Balaraman ](https://aws.amazon.com/blogs/developer/build-infrastructure-for-centralized-logging-using-aws-cdk/)
5. [Working with the AWS Cloud Development Kit and AWS Construct Library by Lee Packham](https://aws.amazon.com/blogs/developer/working-with-the-aws-cloud-development-kit-and-aws-construct-library/)
6. [Testing infrastructure with the AWS Cloud Development Kit (CDK) by Rico Huijbers](https://aws.amazon.com/blogs/developer/testing-infrastructure-with-the-aws-cloud-development-kit-cdk/)
7. [Getting started with the AWS Cloud Development Kit and Python by Mitch Garnaat ](https://aws.amazon.com/blogs/developer/getting-started-with-the-aws-cloud-development-kit-and-python/)
8. [Packaging and deploying AWS Lambda functions written in Java with AWS Cloud Development Kit](https://aws.amazon.com/blogs/opensource/packaging-and-deploying-aws-lambda-functions-written-in-java-with-aws-cloud-development-kit/)
9. [Best practices for developing cloud applications with AWS CDK by Eric Z. Beard and Rico Huijbers](https://aws.amazon.com/blogs/devops/best-practices-for-developing-cloud-applications-with-aws-cdk/)
10. [Deploying a serverless application using AWS CDK by Georges Leschener and Luis Lopez Soria](https://aws.amazon.com/blogs/devops/deploying-a-serverless-application-using-aws-cdk/)

# 12. Resources

1. [[START HERE] CDK Resources](https://cdk.dev/resources)
2. [CDK Patterns](https://cdkpatterns.com/)
- https://github.com/cdk-patterns/serverless
3. [GitHub - CDK Patterns](https://github.com/cdk-patterns/serverless/tree/main)
4. [Deconstructing AWS CDK Patterns](https://www.youtube.com/channel/UCuR3jnWEnxx1G2axUMVaogg)
5. [Construct Hub](https://constructs.dev/)
6. [CDK Patterns for Typescript](https://serverlessland.com/patterns?framework=CDK&language=TypeScript)
7. [CDK Patterns for Java](https://serverlessland.com/patterns?framework=CDK&language=Java)
8. [CDK Patterns for Python](https://serverlessland.com/patterns?framework=CDK&language=Python)
9. [CDK Labs at AWS](https://github.com/cdklabs)
10. [AWS CDK Examples](https://github.com/aws-samples/aws-cdk-examples/tree/master)
11. [[START HERE] Awesome CDK By RAJKUMAR KALAISELVAM](https://github.com/kalaiser/awesome-cdk)
- Training Materials and Sample Code
- Blog Posts & Talks
12. [[MUST SEE] CDK Samples from AWS-SAMPLES](https://github.com/orgs/aws-samples/repositories?language=&page=5&q=cdk&sort=&type=all)
13. [AWS-CDK-Examples By GreenChapel](https://github.com/johngreen-dev/AWS-CDK-Examples)
14. [Hey CDK, how can I migrate my existing CloudFormation templates? by Philipp Garbe](https://garbe.io/blog/2019/09/11/hey-cdk-how-to-migrate/)
15. [Developer Guide from AWS](https://docs.aws.amazon.com/cdk/v2/guide/home.html)

# 13. Workshops

1. [CDK Workshop](https://cdkworkshop.com/)
2. [AWS CDK Advanced Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/d93fec4c-fb0f-4813-ac90-758cb5527f2f/en-US)
3. [Build Your Infrastructure with AWS CloudFormation and the AWS CDK ](https://build-infra-cfn-cdk.workshop.aws/en/)
4. [Extended CDK Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/071bbc60-6c1f-47b6-8c66-e84f5dc96b3f/en-US)
- [Extended CDK Workshop - Coffee Listing App](https://github.com/aws-samples/extended-cdk-workshop-coffee-listing-app)
5. [Workshop - Deploying Containers with AWS CDK v2 and React with AWS Amplify](https://containers-cdk-react-amplify.ws.kabits.com/)
6. [Introduction to the Cloud Development Kit (CDK)](https://catalog.us-east-1.prod.workshops.aws/workshops/5962a836-b214-4fbf-9462-fedba7edcc9b/en-US)
- Shows how to build VPC
7. [Manage your EKS cluster in Full-stack with CDK](https://catalog.us-east-1.prod.workshops.aws/workshops/c15012ac-d05d-46b1-8a4a-205e7c9d93c9/en-US/10-intro)

# 14. Patterns

1. [CDK Patterns at 20 By Matt Coulter](https://dev.to/nideveloper/cdk-patterns-at-20-let-s-walk-through-all-20-serverless-patterns-for-aws-d1n)
2. [Developing enterprise application patterns with the AWS CDK by Krishnakumar Rengarajan and Usman Umar ](https://aws.amazon.com/blogs/devops/developing-application-patterns-cdk/)
- https://github.com/aws-samples/aws-cdk-developing-application-patterns-blog

# 15. CI/CD

1. [AWS Nordics Office Hours - Continuous delivery for AWS CDK applications with CDK Pipelines](https://www.youtube.com/watch?v=5BpKoVJb7QI)
2. [CDK Pipelines: Continuous delivery for AWS CDK applications by Rico Huijbers](https://aws.amazon.com/blogs/developer/cdk-pipelines-continuous-delivery-for-aws-cdk-applications/)
3. [CDK Pipelines from CDK Workshop](https://cdkworkshop.com/20-typescript/70-advanced-topics/200-pipelines.html)
4. [How to build a serverless web application with multiple pipelines](https://github.com/aws-samples/multi-pipeline-serverless-web-application-with-cdk)
5. [CICD on Serverless Applications using AWS CodeArtifact by Anand Krishna ](https://aws.amazon.com/blogs/devops/cicd-on-serverless-applications-using-aws-codeartifact/)
- a simple DevOps pipeline for a sample JAVA application (JAR file) to be built with Maven.

# 16. Testing

1. [Automated, shared testing pipeline for AWS Lambda codefiles and AWS CDK constructs](https://github.com/aws-samples/aws-cdk-lambda-automated-testing)
2. [How to write and execute integration tests for AWS CDK applications by Svenja Raether, Ahmed Bakry, Iris Kraja, and Philip Chen ](https://aws.amazon.com/blogs/devops/how-to-write-and-execute-integration-tests-for-aws-cdk-applications/)

# 17. SAM + CDK

1. [Better together: AWS SAM and AWS CDK by Eric Johnson](https://aws.amazon.com/blogs/compute/better-together-aws-sam-and-aws-cdk/)

# 18. Building Extensions

1. [Extending CloudFormation and CDK with Third-Party Extensions by Lucas Chen, Anuj Sharma, and Rahul Sharma ](https://aws.amazon.com/blogs/devops/extending-cloudformation-and-cdk-with-third-party-extensions/)

# 19. Curate

1. [Rapid and flexible Infrastructure as Code using the AWS CDK with AWS Solutions Constructs by Biff Gaut ](https://aws.amazon.com/blogs/devops/rapid-flexible-infrastructure-with-solutions-constructs-cdk/)
2. [AWS re:Invent 2022 - How to reuse patterns when developing infrastructure as code (DOP302)](https://www.youtube.com/watch?v=ndd9XwQZbyM)
3. [The AWS CDK - The Bad Parts By Matthew Bonig](https://www.youtube.com/watch?v=AVMkjtJ21Co)
4. [Working with the AWS Cloud Development Kit and AWS Construct Library by Lee Packham](https://aws.amazon.com/blogs/developer/working-with-the-aws-cloud-development-kit-and-aws-construct-library/)

# React

1. [Building server-side rendering for React in AWS Lambda by James Beswick ](https://aws.amazon.com/blogs/compute/building-server-side-rendering-for-react-in-aws-lambda/)
2. [Deploying Sample UI Forms using React, Formik, and AWS CDK by Kevin Rivera, Mark Carlson, Shruti Arora, and Britney Tong](https://aws.amazon.com/blogs/architecture/deploying-sample-ui-forms-using-react-formik-and-aws-cdk/)
3. [Deploy a NextJS 13 application to Amplify with the AWS CDK by Michael Liendo ](https://aws.amazon.com/blogs/mobile/deploy-a-nextjs-13-application-to-amplify-with-the-aws-cdk/)

# Networking

1. [Deploy an SPA with personalized subdomains using AWS CDK by Paul Kukiel](https://aws.amazon.com/blogs/infrastructure-and-automation/deploy-spa-with-personalized-subdomains-using-aws-cdk/)
2. [Building an egress VPC with AWS Transit Gateway and the AWS CDK by Ivan Zaytsev ](https://aws.amazon.com/blogs/networking-and-content-delivery/building-an-egress-vpc-with-aws-transit-gateway-and-the-aws-cdk/)
3. [Using the AWS CDK and AWS Transit Gateway Inter-Region peering to build a global network by Joel Desaulniers and Maish Saidel-Keesing](https://aws.amazon.com/blogs/networking-and-content-delivery/using-the-aws-cdk-and-aws-transit-gateway-inter-region-peering-to-build-a-global-network/)
4. [Deploy bastion hosts into private subnets with AWS CDK by Ramy Nasreldin, Rolando Santamaria Maso, and Prasanna Tuladhar](https://aws.amazon.com/blogs/infrastructure-and-automation/deploy-bastion-hosts-into-private-subnets-with-aws-cdk/)
5. [Introducing AWS Site-to-Site VPN Private IP VPNs by Pablo Sánchez Carmona and Andy Taylor](https://aws.amazon.com/blogs/networking-and-content-delivery/introducing-aws-site-to-site-vpn-private-ip-vpns/)
6. [Improve web application availability with CloudFront and Route53 hybrid origin failover by Chakib Sahraoui and Abhinav Bannerjee](https://aws.amazon.com/blogs/networking-and-content-delivery/improve-web-application-availability-with-cloudfront-and-route53-hybrid-origin-failover/)
7. [Centralize access using VPC interface endpoints to access AWS services across multiple VPCs by Chetan Agrawal ](https://aws.amazon.com/blogs/networking-and-content-delivery/centralize-access-using-vpc-interface-endpoints/)

# EKS

1. [Continuous Delivery of Amazon EKS Clusters Using AWS CDK and CDK Pipelines by Jairo da Silva Junior and Davi Garcia](https://aws.amazon.com/blogs/containers/continuous-delivery-of-amazon-eks-clusters-using-aws-cdk-and-cdk-pipelines/)


# 20. Courses

1. [AWS & Typescript Masterclass - CDK V2, Serverless, React](https://www.udemy.com/course/aws-typescript-cdk-serverless-react/)
2. [Get Started with AWS CDK](https://aws.amazon.com/getting-started/guides/setup-cdk/)

# 21. Examples

1. [aws-cdk-changelogs-demo](https://github.com/aws-samples/aws-cdk-changelogs-demo)
2. [AWS Solutions Constructs - Restaurant Management System Example](https://github.com/awslabs/aws-solutions-constructs/tree/main/source/use_cases/aws-restaurant-management-demo)
2. [This use case implements an example using the Kinesis Data Streams Glue Job construct.](https://github.com/awslabs/aws-solutions-constructs/tree/main/source/use_cases/aws-custom-glue-etl)
3. [Creating an AWS Fargate service using the AWS CDK](https://docs.aws.amazon.com/cdk/v2/guide/ecs_example.html)
4. [Creating a serverless application using the AWS CDK](https://docs.aws.amazon.com/cdk/v2/guide/serverless_example.html)

# 22. RDS

1. [Run an Active-Passive, multi region API using Aurora RDS Global Cluster by Siva Ramani and Subramanian Thangavelu ](https://aws.amazon.com/blogs/developer/10322-2/)

# 23. SSM

1. [Using SSM Parameters in AWS CDK - Complete Guide](https://bobbyhadz.com/blog/aws-cdk-ssm-parameters)

# 24. Centralized Logging

1. [Centralize Amazon CloudWatch Logs using AWS CDK by Naveen Balaraman](https://aws.amazon.com/blogs/developer/build-infrastructure-for-centralized-logging-using-aws-cdk/)

# 25. ElasticCache

1. [Deploy Amazon ElastiCache for Redis using AWS CDK by Hantzley Tauckoor and Calvin Ngo](https://aws.amazon.com/blogs/database/deploy-amazon-elasticache-for-redis-using-aws-cdk/)

# 26. Videos to watch

1. [AWS re:Invent 2022 - How to reuse patterns when developing infrastructure as code (DOP302)](https://www.youtube.com/watch?v=ndd9XwQZbyM)

# 27. Blogs

1. [CDK Articles by Borislav Hadzhiev](https://bobbyhadz.com/)

# 28. Documentation

1. [class Vpc (construct)](https://docs.aws.amazon.com/cdk/api/v2/docs/aws-cdk-lib.aws_ec2.Vpc.html)

# CloudFormation

1. [Read parameters across AWS Regions with AWS CloudFormation custom resources by Jagdeep Singh Soni and Michael Fraedrich](https://aws.amazon.com/blogs/infrastructure-and-automation/read-parameters-across-aws-regions-with-aws-cloudformation-custom-resources/)

# Event Driven Architecture + Lambda

1. [Extending a serverless, event-driven architecture to existing container workloads by Dhiraj Mahapatro](https://aws.amazon.com/blogs/compute/extending-a-serverless-event-driven-architecture-to-existing-container-workloads/)

# kafka

1. [Building an Apache Kafka data processing Java application using the AWS CDK by Piotr Chotkowski](https://aws.amazon.com/blogs/developer/building-an-apache-kafka-data-processing-java-application-using-the-aws-cdk/)