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

# 6. Lambda

1. [Lambda resource access permissions](https://docs.aws.amazon.com/en_us/lambda/latest/dg/lambda-permissions.html)
2. [[TRY] Using Amazon SQS dead-letter queues to replay messages By Alexandre Pinhel](https://aws.amazon.com/blogs/compute/using-amazon-sqs-dead-letter-queues-to-replay-messages)
3. [Build a fault-tolerant, serverless data aggregation pipeline with exactly-once processing by Lucas Rettenmeier and Kirill Bogdanov](https://aws.amazon.com/blogs/database/build-a-fault-tolerant-serverless-data-aggregation-pipeline-with-exactly-once-processing/)
- Categorize this.  Under samples to try?

# 7. Operating Lambda series

## 7.1. Insights

1. [Operating Lambda: Using CloudWatch Logs Insights by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-using-cloudwatch-logs-insights/)
1. [Operating Lambda: Logging and custom metrics by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-logging-and-custom-metrics/)

## 7.2. Issue management

1. [Operating Lambda: Isolating and resolving issues by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-isolating-and-resolving-issues/)

## 7.3. Debugging
1. [Operating Lambda: Debugging code – Part 1 by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-debugging-code-part-1/)
1. [Operating Lambda: Debugging configurations – Part 2 by James Beswick ](https://aws.amazon.com/blogs/compute/operating-lambda-debugging-configurations-part-2/)
1. [Operating Lambda: Debugging configurations – Part 3 by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-debugging-integrations-part-3/)

## 7.4. Security
1. [Operating Lambda: Building a solid security foundation – Part 1 by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-building-a-solid-security-foundation-part-1/)
1. [Operating Lambda: Building a solid security foundation – Part 2 by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-building-a-solid-security-foundation-part-2/)

## 7.5. EDA

1. [Operating Lambda: Understanding event-driven architecture – Part 1 by James Beswick |](https://aws.amazon.com/blogs/compute/operating-lambda-understanding-event-driven-architecture-part-1/)
1. [Operating Lambda: Design principles in event-driven architectures – Part 2 by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-design-principles-in-event-driven-architectures-part-2/)
1. [Operating Lambda: Anti-patterns in event-driven architectures – Part 3 by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-anti-patterns-in-event-driven-architectures-part-3/)

## 7.6. Governance

1. [Operating serverless at scale: Implementing governance – Part 1 by Jerome Van Der Linden](https://aws.amazon.com/blogs/compute/operating-serverless-at-scale-implementing-governance-part-1/)
1. [Operating serverless at scale: Improving consistency – Part 2 by Jerome Van Der Linden](https://aws.amazon.com/blogs/compute/operating-serverless-at-scale-improving-consistency-part-2/)
1. [Operating serverless at scale: Keeping control of resources – Part 3 by Jerome Van Der Linden](https://aws.amazon.com/blogs/compute/operating-serverless-at-scale-keeping-control-of-resources-part-3/)

## 7.7. Application design considerations

1. [[MAKE-NOTES] Operating Lambda: Application design and Service Quotas – Part 1 by James Beswick |](https://aws.amazon.com/blogs/compute/operating-lambda-application-design-and-service-quotas-part-1/)
1. [[MAKE-NOTES] Operating Lambda: Application design – Scaling and concurrency: Part 2 by James Beswick ](https://aws.amazon.com/blogs/compute/operating-lambda-application-design-scaling-and-concurrency-part-2/)
1. [[MAKE-NOTES] Operating Lambda: Application design – Part 3 by James Beswick ](https://aws.amazon.com/blogs/compute/operating-lambda-application-design-part-3/)

## 7.8. Performance

1. [Operating Lambda: Performance optimization – Part 1 by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-performance-optimization-part-1/)
1. [Operating Lambda: Performance optimization – Part 2 by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-performance-optimization-part-2/)
1. [Operating Lambda: Performance optimization – Part 3 by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-performance-optimization-part-3/)

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