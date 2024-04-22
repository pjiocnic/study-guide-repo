
<!-- TOC -->

- [1. Networking Concepts](#1-networking-concepts)
- [2. Hub and Spoke](#2-hub-and-spoke)
- [3. PrivateLinks](#3-privatelinks)
  - [3.1. SQS](#31-sqs)
  - [3.2. S3](#32-s3)
- [4. VPC Lattice](#4-vpc-lattice)
- [5. Twitch Shows](#5-twitch-shows)
- [6. CDK scripts](#6-cdk-scripts)
- [7. Workshops](#7-workshops)

<!-- /TOC -->

# 1. Networking Concepts

1. [Role of TCP vs UDP on LoadBalancers By Lee Harding](https://medium.com/circuitpeople/a-case-for-very-simple-load-balancing-f66f1aca86f9)

# 2. Hub and Spoke

1. [How to centralize DNS management in a multi-account environment by Mahmoud Matouk ](https://aws.amazon.com/blogs/security/how-to-centralize-dns-management-in-a-multi-account-environment/)
1. [[**MUST TRY**] Centralize access using VPC interface endpoints to access AWS services across multiple VPCs by Chetan Agrawal](https://aws.amazon.com/blogs/networking-and-content-delivery/centralize-access-using-vpc-interface-endpoints/)
1. [[FARGATE,VPC-ENDPOINT] Providing controlled internet access through centralised proxy servers using AWS Fargate and PrivateLink by Sanjay Dandeker and Saurabh Kothari](https://aws.amazon.com/blogs/networking-and-content-delivery/providing-controlled-internet-access-through-centralised-proxy-servers-using-aws-fargate-and-privatelink/)

# 3. PrivateLinks

1. [VPC Endpoint Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/25daa7f1-11a5-4c96-8923-9b0e333acc59/en-US)

## 3.1. SQS

1. [/Volumes/Lexar/git-repos/aws-repo/my-aws-samples/networking/privatelink/[TODO] sqs-endpoint/SQS-VPCE-Tutorial-CloudFormation.yaml](/Volumes/Lexar/git-repos/aws-repo/my-aws-samples/networking/privatelink/[TODO] sqs-endpoint/SQS-VPCE-Tutorial-CloudFormation.yaml)
- https://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/sqs-sending-messages-from-vpc.html

## 3.2. S3

1. [Amazon S3 Multi-Region Access Points](https://catalog.workshops.aws/s3multiregionaccesspoints/en-US)
2. [[WORKSHOP - MUST TRY] Secure Hybrid Access to S3 using VPC Endpoints](https://catalog.us-east-1.prod.workshops.aws/workshops/3a8d4ddf-66c5-4d26-ae6f-6292a517f46c/en-US)

# 4. VPC Lattice

1. [Introducing VPC Lattice – Simplify Networking for Service-to-Service Communication (Preview) by Danilo Poccia ](https://aws.amazon.com/blogs/aws/introducing-vpc-lattice-simplify-networking-for-service-to-service-communication-preview/)
2. [Amazon VPC Lattice Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/9e543f60-e409-43d4-b37f-78ff3e1a07f5/en-US)
1. [Simplify Modernization of your monolithic application using Amazon VPC Lattice by Sanket Nasre and Hemant Ahire](https://aws.amazon.com/blogs/mt/simplify-modernization-of-your-monolithic-application-using-amazon-vpc-lattice/)


# 5. Twitch Shows

1. [The Routing Loop](https://www.theroutingloop.net/)

 if NLB is deployed to multiple AZs, then how should we deploy Lambdas? particularly when Lambda is deployed to VPC should we somehow tell it to deploy into same AZs as the NLBs?

# 6. CDK scripts

1. [Amazon VPC Routing Enhancements Allow You to Inspect Traffic Between Subnets In a VPC by Sébastien Stormacq](https://aws.amazon.com/blogs/aws/inspect-subnet-to-subnet-traffic-with-amazon-vpc-more-specific-routing/)

# 7. Workshops

1. [Networking Immersion Day](https://catalog.workshops.aws/networking/en-US)