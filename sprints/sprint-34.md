<h1>Sprint 34 - Aug 22, 2023- Aug 29, 2023</h1>

<!-- TOC -->

- [1. Incomplete](#1-incomplete)
- [2. Cull](#2-cull)
- [3. SNS](#3-sns)
- [4. SQS](#4-sqs)
- [5. Lambda](#5-lambda)
- [6. Curate](#6-curate)
- [7. Route53 Resolver](#7-route53-resolver)
- [8. Questions](#8-questions)

<!-- /TOC -->

# 1. Incomplete

1. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 3 by Pascal Vogel](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-3/)
2. [Implementing AWS Lambda error handling patterns by Julian Wood, Jeff Chen, and Jeff Li](https://aws.amazon.com/blogs/compute/implementing-aws-lambda-error-handling-patterns/)
3. Make Notes - https://www.youtube.com/watch?v=xQeGrgQJJDc
4. Make Notes - https://medium.com/event-driven-utopia/aws-sqs-visibility-timeout-explained-c13d8a728ab5
5. Complete - ./notes-under-construction/4-best-practices-lambda.md that has blogs from Jerome Van Der Linden
6. Draw a diagram on how STS works
7. When would you need a role to assume itself? - https://docs.aws.amazon.com/lambda/latest/dg/lambda-intro-execution-role.html
8. Make notes on ECS - https://www.youtube.com/watch?v=5uJUmGWjRZY&list=PLhr1KZpdzukeNrcZo5aAgVPkm4UnugRX3&index=3
9. Crossplane - https://www.youtube.com/watch?v=8CdyxX7eGkA
- https://github.com/awslabs/crossplane-on-eks/tree/main/examples
10. [Data Modeling with MongoDB (Webinar)](https://www.youtube.com/watch?v=zjDkBgyGdwQ)

# 2. Cull
1. https://serverlessland.com/search?search=sqs+to+lambda
2. https://repost.aws/knowledge-center/lambda-subscribe-sns-topic-same-account
3. https://aws.amazon.com/tw/blogs/mobile/invoking-aws-lambda-functions-via-amazon-sns/
4. [Author: Jeff Barr](https://aws.amazon.com/blogs/aws/author/jbarr/)

# 3. SNS

Check out tutorials listed in [lambda-tutorials.md](../my-tracks/lambda-tutorials.md)

# 4. SQS

Check out tutorials listed in [lambda-tutorials.md](../my-tracks/lambda-tutorials.md)

# 5. Lambda

1. [Lambda resource access permissions](https://docs.aws.amazon.com/en_us/lambda/latest/dg/lambda-permissions.html)
2. [[TRY] Using Amazon SQS dead-letter queues to replay messages By Alexandre Pinhel](https://aws.amazon.com/blogs/compute/using-amazon-sqs-dead-letter-queues-to-replay-messages)

# 6. Curate

1. [Integration & Automation](https://aws.amazon.com/blogs/infrastructure-and-automation/)

# 7. Route53 Resolver

1. [Automating DNS infrastructure using Route 53 Resolver endpoints by Shiva Vaidyanathan and Akhil Nayabu](https://aws.amazon.com/blogs/networking-and-content-delivery/automating-dns-infrastructure-using-route-53-resolver-endpoints/)

# 8. Questions

1. How do you use connection pooling inside a Lambda?
2. Any cloudformation templates to create MongoDB in AWS
Links to CDk and Cloudformation templates please

https://www.mongodb.com/developer/code-examples/bash/get-started-atlas-aws-cloudformation/
https://www.mongodb.com/docs/atlas/manage-connections-aws-lambda/

https://www.mongodb.com/blog/post/atlas-integrations-aws-cloud-formation-cdk-now-generally-available
https://github.com/mongodb/awscdk-resources-mongodbatlas/tree/main/examples/mern-cdk-ci-cd
https://github.com/mongodb/awscdk-resources-mongodbatlas
https://github.com/mongodb/awscdk-resources-mongodbatlas/tree/main/examples/mern-cdk-ci-cd

https://www.mongodb.com/cloud/atlas/register

i want to use schema validation with polymorphic documents. Any examples how to go about?
A: Yes, setting up schema validators for schema governance is definitely recommended even for polymorphic documents. Here is some information about schema validators: https://www.mongodb.com/docs/manual/core/schema-validation/

Soheyl Rafi and Bobby

Q. jumping ahead - i'm coming from oracle and was wondering if its possible do vpd at the document and fields. I want to secure Personal Information (PII)
A: Atlas clusters are deployed in VPCs in the cloud. You can then use VPC peering to connect your application to Atlas clusters. There are many ways to secure PII, including BYOK, client side field level encryption, RBAC, and more.

Q: trying to secure at rest. So, User B should not be able to SEE User A's data
A: For this, we have granular, collection-level role based access controls within Atlas.
A: RBAC: https://www.mongodb.com/docs/manual/core/authorization/#:~:text=MongoDB%20employs%20Role%2DBased%20Access,no%20access%20to%20the%20system.

Q: is the vpc peering automatically setup when integrating from aws. Any docs that walk thru?
A: https://www.mongodb.com/docs/atlas/security-vpc-peering/