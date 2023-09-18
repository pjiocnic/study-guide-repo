<h1>My Tasks Put on hold</h1>

1. Review and Close notes - `/Volumes/Lexar/git-repos/aws-repo/my-github/study-guide-repo/faqs/my-faqs-for-sqs.md` and find a permanent home
1. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 3 by Pascal Vogel](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-3/)
1. [Implementing AWS Lambda error handling patterns by Julian Wood, Jeff Chen, and Jeff Li](https://aws.amazon.com/blogs/compute/implementing-aws-lambda-error-handling-patterns/)
1. Make Notes - [AWS Auto Scaling Deep Dive By Digital Cloud Training](https://www.youtube.com/watch?v=xQeGrgQJJDc)
1. Make Notes - [AWS SQS Visibility Timeout Explained By Dunith Danushka](https://medium.com/event-driven-utopia/aws-sqs-visibility-timeout-explained-c13d8a728ab5)
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
1. [Complete notes on how to write Jitter/exponential backoff code](../aws-lambda.md#721-exponential-backoff-and-jitter-algorithm)
1. [You need this FREE IAM Management Tool By Be A Better Dev](https://www.youtube.com/watch?v=ryEseI_-12o)
1. Complete notes from workshop - [AWS Management and Governance Tools Workshop > AWS Config](https://mng.workshop.aws/config.html)
1. Complete notes on AWS Config Workshop/Config Rule With Lambda
1. Complete notes on AWS Config Workshop/Config Rule With remediation

1. [Stream Amazon CloudWatch Logs to a Centralized Account for Audit and Analysis by David Bailey](https://aws.amazon.com/blogs/architecture/stream-amazon-cloudwatch-logs-to-a-centralized-account-for-audit-and-analysis/)
1. Workshop - [Amazon DocumentDB (with MongoDB compatibility) Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/464d6c17-9faa-4fef-ac9f-dd49610174d3/en-US)
1. [Amazon DocumentDB (with MongoDB compatibility) read autoscaling by Randy DeFauw](https://aws.amazon.com/blogs/database/amazon-documentdb-with-mongodb-compatibility-read-autoscaling/)
1. [How to monitor Amazon DynamoDB](https://aws.amazon.com/blogs/database/how-to-use-amazon-cloudwatch-to-monitor-amazon-dynamodb-table-size-and-item-count-metrics/)

1. Complete making notes on `/Volumes/Lexar/git-repos/aws-repo/my-aws-workshops/serverless/[TODO]svs-307-powertools`. Check out the TODO section in my-readme.md
1. Complete /Volumes/Lexar/git-repos/aws-repo/my-aws-samples/cdk/sample-app/my-readme.md


# 1. ECS

1. [[**MUST TRY**] Spring Pet Clinic - Deploying Java Microservices on Amazon Elastic Container Service by Huy Huynh and Magnus Bjorkman](https://aws.amazon.com/blogs/compute/deploying-java-microservices-on-amazon-ec2-container-service/)
1. [[**MUST TRY**] Building dynamic Amazon SNS subscriptions for auto scaling container workloads By Mithun Mallick](https://aws.amazon.com/blogs/compute/building-dynamic-amazon-sns-subscriptions-for-auto-scaling-container-workloads/)
    - Architecture: SNS -> SQS -> ECS (Java Polls SQS) -> EventBridge -> Lambda/Dynamodb

# 2. EventBridge

1. [{Orchestrator] Insurance Claims Processing using Serverless and Event-Driven Architecture](https://github.com/aws-samples/serverless-eda-insurance-claims-processing)

# 3. Lambda

1. DLQs - why Lambda DLQs don't suport SQS? https://www.youtube.com/watch?v=hOetf6YN3zo&lc=Ugy1J5lsqtlB5mUBFVt4AaABAg.9unsQBRi0TN9unvMmRmQml
1. Destinations - Why for SQS we dont have destinations?
1. Read thru' AWS fundamentals

# 4. JSON Handling

1. [Spring Boot & JsonNode: How to use it and when to turn to creating Custom Deserializers](https://www.youtube.com/watch?v=cw0TfpcUkao)
1. [Spring Boot & JSON: A Practical Guide to reading JSON and Persisting it to a database](https://www.youtube.com/watch?v=EumLbf8WjnY)

# 5. WAF

1. [Serverless Applications Lens - AWS Well-Architected Framework](https://docs.aws.amazon.com/wellarchitected/latest/serverless-applications-lens/welcome.html)
2. [Serverless Applications Lens AWS Well-Architected Framework By AGPIAL](https://www.youtube.com/watch?v=g1WpzZHQ4Gc)
3. [AWS Serverless Multi-Tier Architectures. AWS Whitepaper AGPIAL Audio](https://www.youtube.com/watch?v=lEf9XjbHs0U)
4. [Security Overview of AWS Lambda AWS Whitepaper. AGPIAL Audiobook](https://www.youtube.com/watch?v=QjVU-WiKLo4)
5. [Maximizing Value with AWS. AGPIAL Audiobook](https://www.youtube.com/watch?v=roT6LOINf30)
3. [Reliability Pillar AWS Well-Architected Framework. AGPIAL Audiobook](https://www.youtube.com/watch?v=avp7Hb-JybU)
5. [Performance Eﬃciency Pillar AWS Well-Architected Framework. AGPIAL Audiobook](https://www.youtube.com/watch?v=jze2oaojNSI)
6. [Operational Excellence Pillar AWS Well-Architected Framework. AGPIAL Audiobook](https://www.youtube.com/watch?v=Vhk6a5MUhE8)
7. [Cost Optimization Pillar AWS Well-Architected Framework AGPIAL Audiobook](https://www.youtube.com/watch?v=zeLyoWund1I)

# 6. Workshop

1. CloudTrail workshop (prep - understand the cdk code in oneobservability workshop before continuing with this workshop)
1. Workshop - [AWS Serverless Developer Experience](https://catalog.workshops.aws/serverless-developer-experience/en-US/introduction/project-init)
1. AWS Control Tower workshop: Will get back to this workshop after few months.  The cost to do this workshop = $50
1. [Learn Python On AWS Workshop > Inputs > Lab 5 - Inputs > Step 1 - User input from the console](https://catalog.us-east-1.prod.workshops.aws/workshops/3d705026-9edc-40e8-b353-bdabb116c89c/en-US/inputs/lab-5/step-1)
1. [Extended CDK Workshop > JSII](https://catalog.us-east-1.prod.workshops.aws/workshops/071bbc60-6c1f-47b6-8c66-e84f5dc96b3f/en-US/40-jsii)
1. For AWS Config Workshop/Config Rule With Lambda, Update diagram for ConfigSSmLab.yml ie cloudwatch triggering lambda
1. Complete HA Web Workshop (https://catalog.us-east-1.prod.workshops.aws/workshops/5ceb632a-c07f-44a5-a3bd-b8f616a631c0/en-US)
1. Continue working thru workshop here - https://workshop-aws-account-setup.fstehle.com/
1. Workshop - [Amazon DynamoDB Labs](https://amazon-dynamodb-labs.com/)