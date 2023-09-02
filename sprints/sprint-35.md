<h1>Sprint 35 - Aug 28, 2023- Sep 3, 2023</h1>

# 1. Incomplete

1. Review and Close notes - `/Volumes/Lexar/git-repos/aws-repo/my-github/study-guide-repo/faqs/my-faqs-for-sqs.md` and find a permanent home
1. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 3 by Pascal Vogel](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-3/)
1. [Implementing AWS Lambda error handling patterns by Julian Wood, Jeff Chen, and Jeff Li](https://aws.amazon.com/blogs/compute/implementing-aws-lambda-error-handling-patterns/)
1. Make Notes - https://www.youtube.com/watch?v=xQeGrgQJJDc
1. Make Notes - https://medium.com/event-driven-utopia/aws-sqs-visibility-timeout-explained-c13d8a728ab5
1. Complete - ./notes-under-construction/4-best-practices-lambda.md that has blogs from Jerome Van Der Linden
1. Draw a diagram on how STS works
1. When would you need a role to assume itself? - https://docs.aws.amazon.com/lambda/latest/dg/lambda-intro-execution-role.html
1. Make notes on ECS - https://www.youtube.com/watch?v=5uJUmGWjRZY&list=PLhr1KZpdzukeNrcZo5aAgVPkm4UnugRX3&index=3
1. Crossplane - https://www.youtube.com/watch?v=8CdyxX7eGkA
- https://github.com/awslabs/crossplane-on-eks/tree/main/examples
1. [Data Modeling with MongoDB (Webinar)](https://www.youtube.com/watch?v=zjDkBgyGdwQ)
1. What are permission boundaries. Look for a demo. See Tamas Salli's IAM book.
1. https://docs.aws.amazon.com/IAM/latest/UserGuide/reference_policies_elements_principal.html
1. Review and Close notes - AssumeRole notes
1. Is this about role chaining? https://docs.aws.amazon.com/IAM/latest/UserGuide/tutorial_cross-account-with-roles.html
1. Complete Notes on DLQ handling
1. Complete notes on how to write Jitter/exponential backoff code
1. [You need this FREE IAM Management Tool By Be A Better Dev](https://www.youtube.com/watch?v=ryEseI_-12o)
1. Complete notes on AWS Config Workshop/Config Rule With Lambda
1. Complete notes on AWS Config Workshop/Config Rule With remediation
1. For AWS Config Workshop/Config Rule With Lambda, Update diagram for ConfigSSmLab.yml - cloudwatch triggering lambda
1. HA Web Workshop (https://catalog.us-east-1.prod.workshops.aws/workshops/5ceb632a-c07f-44a5-a3bd-b8f616a631c0/en-US)
1. Different types of account workshop (https://workshop-aws-account-setup.fstehle.com/)

# Work in Progress

1. [Resource Details And Cloudwatch](https://mng.workshop.aws/config/resource-details-and-cloudwatch.html)

# What Next?
1. CloudTrail workshop
1. Complete AWS Service Catalog Workshop
1. AWS Control Tower workshop
1. Prep for certification

# 2. Cull
1. https://serverlessland.com/search?search=sqs+to+lambda
2. https://repost.aws/knowledge-center/lambda-subscribe-sns-topic-same-account
3. https://aws.amazon.com/tw/blogs/mobile/invoking-aws-lambda-functions-via-amazon-sns/
4. [Author: Jeff Barr](https://aws.amazon.com/blogs/aws/author/jbarr/)

# 3. SNS

Check out tutorials listed in [lambda-tutorials.md](../my-tracks/lambda-tutorials.md)

# 4. SQS

Check out tutorials listed in [lambda-tutorials.md](../my-tracks/lambda-tutorials.md)

# 5. Config

1. [[AWS-SAMPLES] How to use AWS Config and CloudTrail to find who made changes to a resource by Mohamed Attalla, Man Man Chau, Eduardo Ortiz Pineda, and Dan Urbano](https://aws.amazon.com/blogs/mt/how-to-use-aws-config-and-cloudtrail-to-find-who-made-changes-to-a-resource/)

<img src="./images/config-1.png" title="config-1.png" width="900"/>

[see aws-config-and-cloudtrail.yaml](../templates/config/aws-config-and-cloudtrail.yaml)

# 6. Lambda

1. [Lambda resource access permissions](https://docs.aws.amazon.com/en_us/lambda/latest/dg/lambda-permissions.html)
2. [[TRY] Using Amazon SQS dead-letter queues to replay messages By Alexandre Pinhel](https://aws.amazon.com/blogs/compute/using-amazon-sqs-dead-letter-queues-to-replay-messages)
3. [Build a fault-tolerant, serverless data aggregation pipeline with exactly-once processing by Lucas Rettenmeier and Kirill Bogdanov](https://aws.amazon.com/blogs/database/build-a-fault-tolerant-serverless-data-aggregation-pipeline-with-exactly-once-processing/)
- Categorize this.  Under samples to try?

# 7. Operating Lambda series

[See Operating Lambda](../notes-under-construction/operating-lambda-series.md)

# 8. Lambda Operator Guide

1. [The AWS Lambda Operator's Guide | Serverless Office Hours](https://www.youtube.com/watch?v=Xofyyqq_Dvs)
1. [AWS Lambda Operator Guide](https://serverlessland.com/content/service/lambda/guides/aws-lambda-operator-guide/intro)
1. [AWS Lambda Operator Guide](https://docs.aws.amazon.com/lambda/latest/operatorguide/intro.html)
1. [Operating and troubleshooting Lambda-based applications](https://github.com/aws-samples/aws-lambda-operators-guide)

# 9. Testing Lambda

1. [Testing and debugging](https://serverlessland.com/event-driven-architecture/testing-and-debugging)

# 10. Curate

1. [Integration & Automation](https://aws.amazon.com/blogs/infrastructure-and-automation/)

# 11. Route53 Resolver

1. [Automating DNS infrastructure using Route 53 Resolver endpoints by Shiva Vaidyanathan and Akhil Nayabu](https://aws.amazon.com/blogs/networking-and-content-delivery/automating-dns-infrastructure-using-route-53-resolver-endpoints/)

# 12. AWS Samples to try this week

1. [Build a fault-tolerant, serverless data aggregation pipeline with exactly-once processing by Lucas Rettenmeier and Kirill Bogdanov](https://aws.amazon.com/blogs/database/build-a-fault-tolerant-serverless-data-aggregation-pipeline-with-exactly-once-processing/)
2.  https://github.com/aws-samples/serverless-eda-insurance-claims-processing
3. Cloudformation for organizations
4. ECS microservices
5. Centralized Logging

aws-cloudtrail-log-monitoring