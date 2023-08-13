<h1>my-tracks</h1>

<!-- TOC -->

- [1. Cloudformation](#1-cloudformation)
- [2. Scatter Gather Pattern By Gregor Hohpe](#2-scatter-gather-pattern-by-gregor-hohpe)
- [3. ECS + Java](#3-ecs--java)
- [4. Lambda Cookbook](#4-lambda-cookbook)
- [6. SQS Best Practices](#6-sqs-best-practices)
- [7. AWS Organizations](#7-aws-organizations)
- [8. Lambda architecture](#8-lambda-architecture)
- [9. Lambda + SQS](#9-lambda--sqs)
- [10. Lambda Troubleshooting](#10-lambda-troubleshooting)
- [11. Well Architected](#11-well-architected)
- [12. CDK Deployment using Java](#12-cdk-deployment-using-java)
- [13. DLQs](#13-dlqs)
- [14. Rate limiting with ECS](#14-rate-limiting-with-ecs)
- [15. Target Groups](#15-target-groups)
- [16. NLB fronting ALBs](#16-nlb-fronting-albs)
- [17. Auto Scaling](#17-auto-scaling)
- [18. Reference architecture to practice - CURATE](#18-reference-architecture-to-practice---curate)
- [19. Samples to practice - CURATE](#19-samples-to-practice---curate)
- [20. Devops](#20-devops)

<!-- /TOC -->

# 1. Cloudformation

1. [AWS CloudFormation Workshop > Layered stacks](https://catalog.workshops.aws/cfn101/en-US/intermediate/templates/layered-stacks)
2. [AWS CloudFormation Workshop > Package and deploy](https://catalog.workshops.aws/cfn101/en-US/intermediate/templates/package-and-deploy)
3. [AWS CloudFormation Workshop > Language extensions](https://catalog.workshops.aws/cfn101/en-US/intermediate/templates/language-extensions)
4. [AWS CloudFormation Workshop > Architecting your templates](https://catalog.workshops.aws/cfn101/en-US/intermediate/templates/architecting-templates)
5. [AWS CloudFormation Workshop > Policy-as-code with Guard](https://catalog.workshops.aws/cfn101/en-US/intermediate/templates/policy-as-code-with-guard)
6. [AWS CloudFormation Workshop > Update behaviors of stack resources](https://catalog.workshops.aws/cfn101/en-US/intermediate/operations/update-behaviors-of-stack-resources)
7. [AWS CloudFormation Workshop > Understanding change sets](https://catalog.workshops.aws/cfn101/en-US/intermediate/operations/understanding-changesets)
8. [AWS CloudFormation Workshop > Stack policy and prevention controls](https://catalog.workshops.aws/cfn101/en-US/intermediate/operations/stack-policy-and-prevention-controls)
9. [AWS CloudFormation Workshop > Stack policy and prevention controls](https://catalog.workshops.aws/cfn101/en-US/intermediate/operations/stack-policy-and-prevention-controls)
10. [AWS CloudFormation Workshop > Resource Importing](https://catalog.workshops.aws/cfn101/en-US/intermediate/operations/resource-importing)
11. [AWS CloudFormation Workshop > Drift Detection](https://catalog.workshops.aws/cfn101/en-US/intermediate/operations/drift-detection)
12. [AWS CloudFormation Workshop > Orchestrating with StackSets](https://catalog.workshops.aws/cfn101/en-US/intermediate/operations/stacksets)
13. [AWS CloudFormation Workshop > Advanced > Modules](https://catalog.workshops.aws/cfn101/en-US/advanced/modules)
14. [AWS CloudFormation Workshop > Advanced > Resource Types](https://catalog.workshops.aws/cfn101/en-US/advanced/resource-types)

15. [Hands-on AWS CloudFormation - Part 1. It All Starts Here By Samira Yusifova](https://dev.to/tiamatt/hands-on-aws-cloudformation-part-1-it-all-starts-here-5153)
15. [AWS CloudFormation best practices](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/best-practices.html)
16. [AWS CloudFormation Sample Templates](https://github.com/awslabs/aws-cloudformation-templates)

# 2. Scatter Gather Pattern By Gregor Hohpe

1. [Loan Broker with AWS CDK](https://github.com/aws-samples/aws-cdk-loan-broker)
2. [Loan Broker Implementation with AWS Step Functions - Part 1](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions.html)
3. [Loan Broker with Recipient List  - Part 2](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions_recipient_list.html)
4. [Publish-Subscribe with SNS  - Part 3](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions_pubsub.html)
5. [Automation - Part 4](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_automation.html)
6. [Integration Patterns with CDK - Part 6](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_cdk.html)

# 3. ECS + Java

1. [Chapter 6. Containers @ AWS Cookbook](https://learning.oreilly.com/library/view/aws-cookbook/9781492092599/ch06.html#introduction-id00000102)
2. [Java Microservices Deployed on EC2 Container Service](https://github.com/aws-samples/amazon-ecs-java-microservices/blob/master/README.MD)
3. [Building dynamic Amazon SNS subscriptions for auto scaling container workloads by Mithun Mallick](https://aws.amazon.com/blogs/compute/building-dynamic-amazon-sns-subscriptions-for-auto-scaling-container-workloads/)

# 4. Lambda Cookbook

1. [AWS Lambda Cookbook — Part 1 — Logging Best Practices By Ran Isenberg](https://www.ranthebuilder.cloud/post/aws-lambda-cookbook-elevate-your-handler-s-code-part-1-logging)
2. [AWS Lambda Cookbook — Part 2 — Observability Best Practices By Ran Isenberg](https://www.ranthebuilder.cloud/post/aws-lambda-cookbook-elevate-your-handler-s-code-part-2-observability)
3. [AWS Lambda Cookbook — Part 3 —Business Domain Observability Best Practices By Ran Isenberg](https://www.ranthebuilder.cloud/post/aws-lambda-cookbook-elevate-your-handler-s-code-part-3-business-domain-observability)
4. [AWS Lambda Cookbook  - Part 4 - Environment Variables Best Practices By Ran Isenberg](https://www.ranthebuilder.cloud/post/aws-lambda-cookbook-environment-variables)
5. [AWS Lambda Cookbook  - Part 5 - Input Validation Best Practices By Ran Isenberg](https://www.ranthebuilder.cloud/post/aws-lambda-cookbook-elevate-your-handler-s-code-part-5-input-validation)
6. [AWS Lambda Cookbook  - Part 6 -  Configuration & Feature Flags Best Practices By Ran Isenberg](https://www.ranthebuilder.cloud/post/aws-lambda-cookbook-part-6-feature-flags-configuration-best-practices)
7. [Start Your AWS Serverless Service With Two Clicks By Ran Isenberg](https://www.ranthebuilder.cloud/post/aws-lambda-cookbook-part-7-how-to-use-the-aws-lambda-cookbook-github-template-project)
8. [AWS CDK - Best Practices From The Trenches By Ran Isenberg](https://www.ranthebuilder.cloud/post/aws-cdk-best-practices-from-the-trenches)


# 6. SQS Best Practices

1. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 1 by Pascal Vogel ](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-1/)
2. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 2 by Pascal Vogel ](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-2/)
3. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 3 by Pascal Vogel](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-3/)
4. [Implementing Well-Architected Best Practices for Amazon SQS with CDK](https://github.com/aws-samples/amazon-sqs-best-practices-cdk)

# 7. AWS Organizations

1. [Workshop AWS Account Setup](https://workshop-aws-account-setup.fstehle.com/)
    - [Shared Services account](https://docs.aws.amazon.com/managedservices/latest/userguide/shared-services-account.html)
2. [Deploy AWS Organizations resources by using CloudFormation by Matt Luttrell and Swara Gandhi](https://aws.amazon.com/blogs/security/deploy-aws-organizations-resources-by-using-cloudformation/)
3. [Foundational Organizational Unit Structure and Accounts](https://github.com/cloud-foundations-on-aws/cloud-foundations-templates/tree/main/organizations/foundational-organizational-unit-structure)
4. [AWS Control Tower Guide](https://catalog.workshops.aws/control-tower/en-US)

# 8. Lambda architecture

1. [AWS re:Invent 2022 - A closer look at AWS Lambda (SVS404-R) By Julian Wood](https://www.youtube.com/watch?v=0_jfH6qijVY&t=1308s)
2. [AWS re:Invent 2022 - Architecting secure serverless applications (SVS302-R) By Josh Kahn](https://www.youtube.com/watch?v=A8iHQjHv8nY)
3. [AWS re:Invent 2020: Scalable serverless event-driven architectures with SNS, SQS & Lambda By Justin Pirtle](https://www.youtube.com/watch?v=8zysQqxgj0I)

# 9. Lambda + SQS

1. [Lambda Concurrency Limits and SQS Triggers Don’t Mix Well (Sometimes)](https://zaccharles.medium.com/lambda-concurrency-limits-and-sqs-triggers-dont-mix-well-sometimes-eb23d90122e0)
2. [AWS re:Invent 2022 - A closer look at AWS Lambda (SVS404-R) By Julian Wood](https://www.youtube.com/watch?v=0_jfH6qijVY)
3. [Understanding AWS Lambda’s invoke throttling limits](https://aws.amazon.com/blogs/compute/understanding-aws-lambdas-invoke-throttle-limits/)
4. [Implementing error handling for AWS Lambda asynchronous invocations By Poornima Chand,](https://aws.amazon.com/blogs/compute/implementing-error-handling-for-aws-lambda-asynchronous-invocations/)
5. [Implementing AWS Lambda error handling patterns by Julian Wood, Jeff Chen, and Jeff Li](https://aws.amazon.com/blogs/compute/implementing-aws-lambda-error-handling-patterns/)
    - [Github code](https://github.com/aws-samples/aws-lambda-error-handling-pattern)
6. [Designing durable serverless apps with DLQs for Amazon SNS, Amazon SQS, AWS Lambda by Rachel Richardson and Otavio Ferreira](https://aws.amazon.com/blogs/compute/designing-durable-serverless-apps-with-dlqs-for-amazon-sns-amazon-sqs-aws-lambda/)
- https://d1.awsstatic.com/events/reinvent/2019/Durable_serverless_architecture_Working_with_dead-letter_queues_API309.pdf
7. [Introducing new asynchronous invocation metrics for AWS Lambda](https://aws.amazon.com/blogs/compute/introducing-new-asynchronous-invocation-metrics-for-aws-lambda/)
8. [Asynchronous invocation](https://docs.aws.amazon.com/lambda/latest/dg/invocation-async.html)

# 10. Lambda Troubleshooting

1. [AWS Lambda - Troubleshooting](https://explore.skillbuilder.aws/learn/course/internal/view/elearning/10771/aws-lambda-troubleshooting)
2. [How to Speed Up Your AWS Lambda Functions](https://explore.skillbuilder.aws/learn/course/internal/view/elearning/14738/how-to-speed-up-your-aws-lambda-functions)
3. [AWS Lambda Fundamentals - AWS Lambda observability and troubleshooting](https://www.youtube.com/watch?v=K2aebIhjWj8)
4. [Introducing new asynchronous invocation metrics for AWS Lambda By Dhiraj Mahapatro](https://aws.amazon.com/blogs/compute/introducing-new-asynchronous-invocation-metrics-for-aws-lambda/)

# 11. Well Architected

1. [Building well-architected serverless applications: Introduction by Julian Wood ](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-introduction/)
2. [AWS Well-Architected Labs](https://github.com/awslabs/aws-well-architected-labs)
3. [Operational Excellence Pillar AWS Well-Architected Framework. AGPIAL Audiobook](https://www.youtube.com/watch?v=Vhk6a5MUhE8)
4. [Reliability Pillar AWS Well-Architected Framework. AGPIAL Audiobook](https://www.youtube.com/watch?v=avp7Hb-JybU)
5. [Performance Eﬃciency Pillar AWS Well-Architected Framework. AGPIAL Audiobook](https://www.youtube.com/watch?v=jze2oaojNSI)
6. [Cost Optimization Pillar AWS Well-Architected Framework AGPIAL Audiobook](https://www.youtube.com/watch?v=zeLyoWund1I)

# 12. CDK Deployment using Java

1. [Packaging and deploying AWS Lambda functions written in Java with AWS Cloud Development Kit by Pankaj Agrawal](https://aws.amazon.com/blogs/opensource/packaging-and-deploying-aws-lambda-functions-written-in-java-with-aws-cloud-development-kit/)
2. [Writing your CDK in Java By Melina Schweizer](https://medium.com/i-love-my-local-farmer-engineering-blog/writing-your-cdk-in-java-685a380d8e4e)
3. [Packaging and deploying AWS Lambda functions written in Java with AWS Cloud Development Kit	By Pankaj Agarwal](https://aws.amazon.com/blogs/opensource/packaging-and-deploying-aws-lambda-functions-written-in-java-with-aws-cloud-development-kit/)
4. [Building, bundling, and deploying applications with the AWS CDK by Cory Hall](https://aws.amazon.com/blogs/devops/building-apps-with-aws-cdk/)
5. [cdk-lambda-packaging-java @ aws-samples](https://github.com/aws-samples/cdk-lambda-packaging-java/tree/main)
6. [AWS CDK constructs for Java @ aws-sample](https://github.com/aws-samples/aws-cdk-constructs-for-java)
7. [Packaging sample by Ajay Wadhara](https://github.com/ajaywadhara/LambdaCDKExample)

# 13. DLQs

[See DLQ](./dlq.md)

# 14. Rate limiting with ECS

[See Rate Limiting](./rate-limiting.md)

# 15. Target Groups

[See Target Group](./target-groups.md)

# 16. NLB fronting ALBs

[See NLB fronting ALBs](./nlb-fronting-alb.md)

# 17. Auto Scaling

[See Auto Scaling](./auto-scaling.md)

# 18. Reference architecture to practice - CURATE

1. [Hosting Moodle on AWS](https://github.com/aws-samples/aws-refarch-moodle)
2. [Deploying Microservices with Amazon ECS, AWS CloudFormation, and an Application Load Balancer](https://github.com/aws-samples/ecs-refarch-cloudformation)
3. [Hosting WordPress on AWS](https://github.com/aws-samples/aws-refarch-wordpress)
4. [Amazon ECS Reference Architecture: Batch Processing](https://github.com/aws-samples/ecs-refarch-batch-processing)
5. [Serverless Reference Architecture: Web Application](https://github.com/aws-samples/lambda-refarch-webapp)

# 19. Samples to practice - CURATE

1. [AWS Serverless Developer Experience workshop reference architecture (Typescript)](https://github.com/aws-samples/aws-serverless-developer-experience-workshop-typescript)
2. [AWS Serverless Developer Experience](https://catalog.workshops.aws/serverless-developer-experience/en-US)
3. [AWS Serverless Developer Experience workshop reference architecture (Java)](https://github.com/aws-samples/aws-serverless-developer-experience-workshop-java)
4. [Java Microservices Deployed on EC2 Container Service](https://github.com/aws-samples/amazon-ecs-java-microservices)
5. [Serverless Reference Architecture: Real-time Stream Processing](https://github.com/aws-samples/lambda-refarch-streamprocessing)
6. [Serverless Reference Architecture: Real-time File Processing](https://github.com/aws-samples/lambda-refarch-fileprocessing)
7. [Serverless Samples](https://github.com/aws-samples/serverless-samples)
8. [Refactoring to Serverless](https://github.com/aws-samples/aws-refactoring-to-serverless)
9. [AWS Lambda Powertools for TypeScript Workshop](https://github.com/aws-samples/aws-lambda-powertools-typescript-workshop)

# 20. Devops

1. [Keeping up with your dependencies: building a feedback loop for shared libraries by Joerg Woehrle ](https://aws.amazon.com/blogs/devops/keeping-up-with-your-dependencies-building-a-feedback-loop-for-shared-libraries/)
2. [Building Modular AWS Lambda Deployment Pipelines with Github Actions Fernando Nieuwveldt](https://towardsaws.com/building-modular-aws-lambda-deployment-pipelines-with-github-actions-9264fa59d7ba)
3. [Operating serverless at scale: Implementing governance – Part 1 by Jerome Van Der Linden](https://aws.amazon.com/blogs/compute/operating-serverless-at-scale-implementing-governance-part-1/)
4. [Operating serverless at scale: Improving consistency – Part 2 by Jerome Van Der Linden](https://aws.amazon.com/blogs/compute/operating-serverless-at-scale-improving-consistency-part-2/)
4. [Operating serverless at scale: Keeping control of resources – Part 3 by Jerome Van Der Linden](https://aws.amazon.com/blogs/compute/operating-serverless-at-scale-keeping-control-of-resources-part-3/)