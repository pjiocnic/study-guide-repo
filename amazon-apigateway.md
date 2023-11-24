<H1> API Gateway Backlog<H1>

<!-- TOC -->

- [1. Dashboard](#1-dashboard)
- [2. APIs](#2-apis)
  - [2.1. Open API](#21-open-api)
  - [2.2. HTTP API vs REST API](#22-http-api-vs-rest-api)
- [3. Authorization](#3-authorization)
  - [3.1. JWT Authorizers](#31-jwt-authorizers)
  - [3.2. Cognito](#32-cognito)
  - [3.3. Custom Authorizers](#33-custom-authorizers)
- [4. Best Practices](#4-best-practices)
- [5. Cognito Integration](#5-cognito-integration)
- [6. Custom Domains](#6-custom-domains)
- [7. Features](#7-features)
- [8. Load Testing](#8-load-testing)
- [9. API Gateway + Private Endpoints](#9-api-gateway--private-endpoints)
- [10. Throttling](#10-throttling)
- [11. Websockets](#11-websockets)
- [12. Usage Plans](#12-usage-plans)
- [13. Use Cases](#13-use-cases)
  - [13.1. URL Shortner](#131-url-shortner)
- [14. Curate](#14-curate)
- [15. Workshops and Hands-On](#15-workshops-and-hands-on)

<!-- /TOC -->

# 1. Dashboard

1. [find examples for features](https://docs.aws.amazon.com/apigateway/latest/developerguide/http-api-vs-rest.html)
1. [A Detailed Overview of AWS API Gateway By Alex Debrie](https://www.alexdebrie.com/posts/api-gateway-elements/)
1. [The Missing Guide to AWS API Gateway Access Logs](https://www.alexdebrie.com/posts/page/2/)
1. [Private Endpoints](#9-private-endpoints)

# 2. APIs

## 2.1. Open API

1. [Create RESTful APIs on AWS with OpenAPI Specification (With No Coding) by Samuel Baruffi, Radhika Gupta, and Anthony Watson](https://aws.amazon.com/blogs/opensource/create-restful-apis-on-aws-with-openapi-specification-with-no-coding/)
1. [Building storage-first serverless applications with HTTP APIs service integrations by Eric Johnson](https://aws.amazon.com/blogs/compute/building-storage-first-applications-with-http-apis-service-integrations/)
    - https://github.com/aws-samples/sessions-with-aws-sam/tree/master/http-api-integrations-blog-example
    - https://www.youtube.com/watch?v=qa3lkaz7pnI

## 2.2. HTTP API vs REST API

1. [What’s the Difference Between AWS REST API & HTTP API? by Jonathan Davies](https://www.youtube.com/watch?v=O8RKpHQt6l4)

# 3. Authorization

## 3.1. JWT Authorizers

1. [How to secure API Gateway HTTP endpoints with JWT authorizer by Siva Rajamani, Rajat Mathur, and Sudhanshu Malhotra](https://aws.amazon.com/blogs/security/how-to-secure-api-gateway-http-endpoints-with-jwt-authorizer/)

## 3.2. Cognito

1. [Building fine-grained authorization using Amazon Cognito, API Gateway, and IAM by Artem Lovan](https://aws.amazon.com/blogs/security/building-fine-grained-authorization-using-amazon-cognito-api-gateway-and-iam/)

## 3.3. Custom Authorizers

1. [The Complete Guide to Custom Authorizers with AWS Lambda and API Gateway BY Alex DeBrie](https://www.alexdebrie.com/posts/lambda-custom-authorizers/)
1. [Introducing IAM and Lambda authorizers for Amazon API Gateway **HTTP APIs** by Julian Wood](https://aws.amazon.com/blogs/compute/introducing-iam-and-lambda-authorizers-for-amazon-api-gateway-http-apis/)
1. [Introducing custom authorizers in Amazon API Gateway by Stefano Buliani](https://aws.amazon.com/blogs/compute/introducing-custom-authorizers-in-amazon-api-gateway/)
1. [Use AWS Lambda authorizers with a third-party identity provider to secure Amazon API Gateway REST APIs by Bryant Bost](https://aws.amazon.com/blogs/security/use-aws-lambda-authorizers-with-a-third-party-identity-provider-to-secure-amazon-api-gateway-rest-apis/)
1. [Restricting access on HTTP API Gateway Endpoint with Lambda Authorizer by Pratik Jain and Shirin Bano](https://aws.amazon.com/blogs/networking-and-content-delivery/restricting-access-http-api-gateway-lambda-authorizer/)
1. [[SERVERLESS-LAND] Amazon API Gateway REST API with AWS Lambda token authorizer](https://serverlessland.com/patterns/apigw-lambda-authorizer-custom-header)
1. [[SERVERLESS-LAND] API Gateway REST API with Lambda authorizer](https://serverlessland.com/patterns/apigw-lambda-authorizer-sam-nodejs)

# 4. Best Practices

1. [Things to Consider When You Build REST APIs with Amazon API Gateway by George Mao](https://aws.amazon.com/blogs/architecture/things-to-consider-when-you-build-rest-apis-with-amazon-api-gateway/)

# 5. Cognito Integration

1. [ALB, Amazon Cognito and Lambda integration](https://serverlessland.com/patterns/alb-cognito-lambda)
1. [Building fine-grained authorization using Amazon Cognito, API Gateway, and IAM by Artem Lovan](https://aws.amazon.com/blogs/security/building-fine-grained-authorization-using-amazon-cognito-api-gateway-and-iam/)

# 6. Custom Domains

1. [Custom Domain Name with AWS API Gateway | Step by Step Tutorial By Be A Better Dev](https://www.youtube.com/watch?v=ESei6XQ7dMg)

# 7. Features

1. [Building faster, lower cost, better APIs – HTTP APIs now generally available by Eric Johnson](https://aws.amazon.com/blogs/compute/building-better-apis-http-apis-now-generally-available/)

# 8. Load Testing

1. [Using serverless to load test Amazon API Gateway with authorization by Ashish Mehra](https://aws.amazon.com/blogs/compute/using-serverless-to-load-test-amazon-api-gateway-with-authorization/)
1. [Building well-architected serverless applications: Regulating inbound request rates – part 1 by Julian Wood](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-regulating-inbound-request-rates-part-1/)

# 9. API Gateway + Private Endpoints

1. [[**_MAKE_NOTES_**] Understanding VPC links in Amazon API Gateway private integrations by Jose Eduardo Montilla Lugo](https://aws.amazon.com/blogs/compute/understanding-vpc-links-in-amazon-api-gateway-private-integrations/)
1. [[**_ACROSS_ACCOUNTS_**] Building private cross-account APIs using Amazon API Gateway and AWS PrivateLink by Brian Zambrano](https://aws.amazon.com/blogs/compute/building-private-cross-account-apis-using-amazon-api-gateway-and-aws-privatelink/)
  <img src="./images/agtwy-vpc-link-1.png" title="agtwy-vpc-link-1.png" width="900"/>
1. [[**_ACROSS_REGIONS_**] Access Private applications on AWS Fargate using Amazon API Gateway PrivateLink by Mani Chandrasekaran](https://aws.amazon.com/blogs/compute/access-private-applications-on-aws-fargate-using-amazon-api-gateway-privatelink/)
  <img src="./images/agtwy-vpc-link-2.png" title="agtwy-vpc-link-2.png" width="900"/>
1. [Serverless Private APIs — Part 1 By Serverless Advocate](https://levelup.gitconnected.com/serverless-private-apis-60749934b161)

# 10. Throttling

1. [AWS re:Invent 2019: [REPEAT 2] I didn’t know Amazon API Gateway did that (SVS212-R2)](https://www.youtube.com/watch?v=yfJZc3sJZ8E)
2. [How To: Use SNS and SQS to Distribute and Throttle Events](https://www.jeremydaly.com/how-to-use-sns-and-sqs-to-distribute-and-throttle-events/)

# 11. Websockets

1. [Announcing WebSocket APIs in Amazon API Gateway by Chris Munns](https://aws.amazon.com/blogs/compute/announcing-websocket-apis-in-amazon-api-gateway/)
4. [Building Real-Time Applications using WebSocket APIs Supported by Amazon API Gateway webinar](https://aws.amazon.com/blogs/compute/announcing-websocket-apis-in-amazon-api-gateway/)
3. [Building Real Time Applications using WebSocket APIs Supported by Amazon API Gateway By George Mao](https://pages.awscloud.com/Building-Real-Time-Applications-using-WebSocket-APIs-Supported-by-Amazon-API-Gateway_1211-SRV_OD.html)

# 12. Usage Plans

1. [Creating Usage Plans in console](https://aws.amazon.com/blogs/aws/new-usage-plans-for-amazon-api-gateway/)
2. [Visualizing Amazon API Gateway usage plans using Amazon QuickSight by Roberto Iturralde](https://aws.amazon.com/blogs/compute/visualizing-amazon-api-gateway-usage-plans-using-amazon-quicksight/)
    - [./templates/api-gateway-access-logs-visualization-core.yaml](api-gateway-access-logs-visualization-core.template)

# 13. Use Cases

## 13.1. URL Shortner

1. [Building Serverless URL Shortener Service on AWS Using API Gateway and Dynamodb](https://dev.to/aws-builders/building-serverless-url-shortener-service-on-aws-1895)

# 14. Curate

1. https://aws.amazon.com/blogs/compute/using-api-gateway-mapping-templates-to-handle-changes-in-your-back-end-apis/
1. https://www.alexdebrie.com/posts/api-gateway-elements/

# 15. Workshops and Hands-On

1. [Amazon API Gateway tutorials and workshops](https://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-tutorials.html)