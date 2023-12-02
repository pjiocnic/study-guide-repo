
<!-- TOC -->

- [Networking Concepts](#networking-concepts)
- [1. Hub and Spoke](#1-hub-and-spoke)
- [2. PrivateLinks](#2-privatelinks)
  - [2.1. SQS](#21-sqs)
  - [2.2. S3](#22-s3)
- [3. VPC Lattice](#3-vpc-lattice)
- [4. DNS](#4-dns)
- [5. Twitch Shows](#5-twitch-shows)

<!-- /TOC -->

# Networking Concepts

1. [Role of TCP vs UDP on LoadBalancers By Lee Harding](https://medium.com/circuitpeople/a-case-for-very-simple-load-balancing-f66f1aca86f9)

# 1. Hub and Spoke

1. [How to centralize DNS management in a multi-account environment by Mahmoud Matouk ](https://aws.amazon.com/blogs/security/how-to-centralize-dns-management-in-a-multi-account-environment/)
2. [[**MUST TRY**] Centralize access using VPC interface endpoints to access AWS services across multiple VPCs by Chetan Agrawal](https://aws.amazon.com/blogs/networking-and-content-delivery/centralize-access-using-vpc-interface-endpoints/)

# 2. PrivateLinks

1. [VPC Endpoint Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/25daa7f1-11a5-4c96-8923-9b0e333acc59/en-US)

## 2.1. SQS

1. [/Volumes/Lexar/git-repos/aws-repo/my-aws-samples/networking/privatelink/[TODO] sqs-endpoint/SQS-VPCE-Tutorial-CloudFormation.yaml](/Volumes/Lexar/git-repos/aws-repo/my-aws-samples/networking/privatelink/[TODO] sqs-endpoint/SQS-VPCE-Tutorial-CloudFormation.yaml)
- https://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/sqs-sending-messages-from-vpc.html

## 2.2. S3

1. [Amazon S3 Multi-Region Access Points](https://catalog.workshops.aws/s3multiregionaccesspoints/en-US)
2. [[WORKSHOP - MUST TRY] Secure Hybrid Access to S3 using VPC Endpoints](https://catalog.us-east-1.prod.workshops.aws/workshops/3a8d4ddf-66c5-4d26-ae6f-6292a517f46c/en-US)

# 3. VPC Lattice

1. [Introducing VPC Lattice – Simplify Networking for Service-to-Service Communication (Preview) by Danilo Poccia ](https://aws.amazon.com/blogs/aws/introducing-vpc-lattice-simplify-networking-for-service-to-service-communication-preview/)
2. [Amazon VPC Lattice Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/9e543f60-e409-43d4-b37f-78ff3e1a07f5/en-US)

# 4. DNS

1. [Powering Secondary DNS in a VPC using AWS Lambda and Amazon Route 53 Private Hosted Zones by Bryan Liston](https://aws.amazon.com/blogs/compute/powering-secondary-dns-in-a-vpc-using-aws-lambda-and-amazon-route-53-private-hosted-zones/)
2. [Building a Dynamic DNS for Route 53 using CloudWatch Events and Lambda by Bryan Liston ](https://aws.amazon.com/blogs/compute/building-a-dynamic-dns-for-route-53-using-cloudwatch-events-and-lambda/)

# 5. Twitch Shows

1. [The Routing Loop](https://www.theroutingloop.net/)

 if NLB is deployed to multiple AZs, then how should we deploy Lambdas? particularly when Lambda is deployed to VPC should we somehow tell it to deploy into same AZs as the NLBs?
