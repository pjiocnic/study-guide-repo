
<!-- TOC -->

- [1. API Gateway + PrivateLinks](#1-api-gateway--privatelinks)
- [2. Mongo](#2-mongo)
- [3. ECS](#3-ecs)
- [4. Webhooks](#4-webhooks)
- [5. SAGA](#5-saga)
- [6. Integrations](#6-integrations)
  - [6.1. EventBridge + ECS Fargate](#61-eventbridge--ecs-fargate)
- [7. Websockets](#7-websockets)
- [8. Event Bridge](#8-event-bridge)
  - [8.1. Event Routing](#81-event-routing)
  - [8.2. Schema Registry](#82-schema-registry)
  - [8.3. Private Endpoints as Targets](#83-private-endpoints-as-targets)
  - [8.4. ECS as Targets](#84-ecs-as-targets)
- [9. Lambda](#9-lambda)
  - [9.1. Secrets](#91-secrets)
  - [9.2. Error Handling](#92-error-handling)

<!-- /TOC -->

# 1. API Gateway + PrivateLinks

1. [Understanding VPC links in Amazon API Gateway private integrations by Jose Eduardo Montilla Lugo](https://aws.amazon.com/blogs/compute/understanding-vpc-links-in-amazon-api-gateway-private-integrations/)
1. [[**MAKE_NOTES**] Understanding VPC links in Amazon API Gateway private integrations by Jose Eduardo Montilla Lugo](https://aws.amazon.com/blogs/compute/understanding-vpc-links-in-amazon-api-gateway-private-integrations/)
1. [[**ACROSS_ACCOUNTS**] Building private cross-account APIs using Amazon API Gateway and AWS PrivateLink by Brian Zambrano](https://aws.amazon.com/blogs/compute/building-private-cross-account-apis-using-amazon-api-gateway-and-aws-privatelink/)
  <img src="./images/agtwy-vpc-link-1.png" title="agtwy-vpc-link-1.png" width="900"/>
1. [[**ACROSS_REGIONS**] Access Private applications on AWS Fargate using Amazon API Gateway PrivateLink by Mani Chandrasekaran](https://aws.amazon.com/blogs/compute/access-private-applications-on-aws-fargate-using-amazon-api-gateway-privatelink/)
  <img src="./images/agtwy-vpc-link-2.png" title="agtwy-vpc-link-2.png" width="900"/>

# 2. Mongo

1. [Recent Additions to Query and Aggregation You May Have Missed](https://www.youtube.com/watch?v=FprmF6nmkWY&list=PL4RCxklHWZ9v3eIqQeKWcoNPSiLuVPyac)

# 3. ECS

1. [Building HTTP API-based services using Amazon API Gateway, AWS PrivateLink and AWS Fargate by Irshad Buchh](https://aws.amazon.com/blogs/containers/building-http-api-based-services-using-aws-fargate/)
1. [Access Private applications on AWS Fargate using Amazon API Gateway PrivateLink by Mani Chandrasekaran ](https://aws.amazon.com/blogs/compute/access-private-applications-on-aws-fargate-using-amazon-api-gateway-privatelink/)
1. [How to securely publish Internet applications at scale using Application Load Balancer and AWS PrivateLink by Tom Adamsk](https://aws.amazon.com/blogs/networking-and-content-delivery/how-to-securely-publish-internet-applications-at-scale-using-application-load-balancer-and-aws-privatelink/)
1. [[JAVA] Building dynamic Amazon SNS subscriptions for auto scaling container workloads By Mithun Mallick](https://aws.amazon.com/blogs/compute/building-dynamic-amazon-sns-subscriptions-for-auto-scaling-container-workloads/)

# 4. Webhooks

1. [Deconstructing "The Scalable Webhook" AWS Serverless Architecture Pattern By Deconstructing AWS CDK Patterns ](https://www.youtube.com/watch?v=kRI7QJfGBI8&t=38s)
1. [Deconstructing "The EventBridge ETL" AWS Serverless Architecture Pattern by Deconstructing AWS CDK Patterns](https://www.youtube.com/watch?v=8kg5bYsdem4)

# 5. SAGA

1. [Lab 4 - Orchestration and coordination](https://catalog.us-east-1.prod.workshops.aws/workshops/e8738cf6-6eb0-4d1d-9e98-ae240d229535/en-US/orchestration-and-coordination)

# 6. Integrations

## 6.1. EventBridge + ECS Fargate

1. [Extending a serverless, event-driven architecture to existing container workloads](https://aws.amazon.com/blogs/compute/extending-a-serverless-event-driven-architecture-to-existing-container-workloads/)

# 7. Websockets

1. [Managing sessions of anonymous users in WebSocket API-based applications](https://aws.amazon.com/blogs/compute/managing-sessions-of-anonymous-users-in-websocket-api-based-applications/)

# 8. Event Bridge

## 8.1. Event Routing

1. [Implementing architectural patterns with Amazon EventBridge Pipes by David Boyne, Dominik Richter](https://aws.amazon.com/blogs/compute/implementing-architectural-patterns-with-amazon-eventbridge-pipes/)
- https://github.com/aws-samples/amazon-eventbridge-pipes-architectural-patterns
1. [Building a modern, event-driven application for insurance claims processing – Part 1 by Emily Shea and Dhiraj Mahapatro](https://aws.amazon.com/blogs/industries/building-a-modern-event-driven-application-for-insurance-claims-processing-part-1/)
1. [Building a modern, event-driven application for insurance claims processing – Part 2 by Emily Shea, Vaibhav Jain, and Dhiraj Mahapatro](https://aws.amazon.com/blogs/industries/building-a-modern-event-driven-application-for-insurance-claims-processing-part-2/)
1. [Extending a serverless, event-driven architecture to existing container workloads](https://aws.amazon.com/blogs/compute/extending-a-serverless-event-driven-architecture-to-existing-container-workloads/)

## 8.2. Schema Registry

1. [Working with events and the Amazon EventBridge schema registry](https://aws.amazon.com/blogs/compute/working-with-events-and-amazon-eventbridge-schema-registry/)

## 8.3. Private Endpoints as Targets

1. [Sending Amazon EventBridge events to private endpoints in a VPC](https://aws.amazon.com/blogs/compute/sending-amazon-eventbridge-events-to-private-endpoints-in-a-vpc/)

## 8.4. ECS as Targets

1. [Extending a serverless, event-driven architecture to existing container workloads by Dhiraj Mahapatro and Sascha Moellering](https://aws.amazon.com/blogs/compute/extending-a-serverless-event-driven-architecture-to-existing-container-workloads/)

# 9. Lambda

## 9.1. Secrets

1. [Choosing the right solution for AWS Lambda external parameters](https://aws.amazon.com/blogs/compute/choosing-the-right-solution-for-aws-lambda-external-parameters/)

## 9.2. Error Handling

1. [Implementing AWS Lambda error handling patterns](https://aws.amazon.com/blogs/compute/implementing-aws-lambda-error-handling-patterns/)


