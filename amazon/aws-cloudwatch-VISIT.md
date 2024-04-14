<h1>AWS Cloudwatch </h1>

<!-- TOC -->

- [1. Curate](#1-curate)
- [2. Alarms](#2-alarms)
- [3. Concepts](#3-concepts)
- [4. Customize Alarms](#4-customize-alarms)
- [5. Examples](#5-examples)
- [6. Insights](#6-insights)
- [7. Custom Metrics](#7-custom-metrics)
- [8. Building Dashboards using CDK](#8-building-dashboards-using-cdk)
  - [8.1. Building Dashboards for DocumentDB](#81-building-dashboards-for-documentdb)
- [9. Pricing](#9-pricing)
- [10. Videos](#10-videos)
- [11. Tutorials](#11-tutorials)
- [12. Workshops](#12-workshops)
- [13. Subscription filters](#13-subscription-filters)

<!-- /TOC -->

# 1. Curate

1. [How to centralize CloudWatch Alarms with Amazon EventBridge and AWS CloudFormation by Chaitanya Gummadi ](https://aws.amazon.com/blogs/mt/how-to-centralize-cloudwatch-alarms-with-amazon-eventbridge-and-aws-cloudformation/)

# 2. Alarms

1. [Alarms, incident management, and remediation in the cloud with Amazon CloudWatch by Eric Scholz ](https://aws.amazon.com/blogs/mt/alarms-incident-management-and-remediation-in-the-cloud-with-amazon-cloudwatch/)

# 3. Concepts

1. [Understanding CloudWatch: A Comprehensive Guide to AWS Monitoring Service By Sandro Volpicella](https://blog.awsfundamentals.com/aws-cloudwatch-monitoring)
1. [[**TUTORIALSDOJO**] Amazon CloudWatch Cheat Sheet](https://tutorialsdojo.com/amazon-cloudwatch/)

# 4. Customize Alarms

1. [Customize Amazon CloudWatch alarm notifications to your local time zone – Part 1 by Ahmed Magdy Wahdan](https://aws.amazon.com/blogs/mt/customize-amazon-cloudwatch-alarm-notifications-to-your-local-time-zone-part-1/)
- [Customize-CloudWatch-alarm-Eventrule.yaml](./templates/Customize-CloudWatch-alarm-Eventrule.yaml)
2. [Customize Amazon CloudWatch alarm notifications to your local time zone – Part 2 by Ahmed Magdy Wahdan](https://aws.amazon.com/blogs/mt/customize-amazon-cloudwatch-alarm-notifications-to-your-local-time-zone-part-2/)
- [Customize-CloudWatch-alarm-SNSSubscription.yaml](./templates/Customize-CloudWatch-alarm-SNSSubscription.yaml)

# 5. Examples

1. [Code examples for CloudWatch using AWS SDKs](https://docs.aws.amazon.com/AmazonCloudWatch/latest/monitoring/service_code_examples.html)

# 6. Insights

1. [The Serverless Guide to AWS CloudWatch Logs Insights](https://baselime.io/blog/cloudwatch-insights-guide)
1. [Operating Lambda: Using CloudWatch Logs Insights by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-using-cloudwatch-logs-insights/)
1. [Operating Lambda: Logging and custom metrics by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-logging-and-custom-metrics/)
1. [New – Amazon CloudWatch Logs Insights – Fast, Interactive Log Analytics   by Jeff Barr](https://aws.amazon.com/blogs/aws/new-amazon-cloudwatch-logs-insights-fast-interactive-log-analytics/)

# 7. Custom Metrics

1. [Writing Custom Metrics to Amazon CloudWatch Using the AWS SDK for Java by Sascha Moellering](https://aws.amazon.com/blogs/developer/writing-custom-metrics-to-amazon-cloudwatch-using-the-aws-sdk-for-java/)
1. [Optimize Your Application Monitoring with CloudWatch Custom Metrics By Sandro Volpicella](https://blog.awsfundamentals.com/optimize-your-application-monitoring-with-cloudwatch-custom-metrics)

# 8. Building Dashboards using CDK

1. [The CloudWatch Dashboard using CDK](https://github.com/cdk-patterns/serverless/blob/main/the-cloudwatch-dashboard/)
2. [Deploying an automated Amazon CloudWatch dashboard for AWS Outposts using AWS CDK by Sheila Busser ](https://aws.amazon.com/blogs/compute/deploying-an-automated-amazon-cloudwatch-dashboard-for-aws-outposts-using-aws-cdk/)
3. [AWS Dashboards with AWS CDK in TypeScript](https://levelup.gitconnected.com/aws-dashboards-with-aws-cdk-in-typescript-12d97bf0958)
> 4. [[**MY-NEXT**] Building Amazon CloudWatch dashboards with AWS CDK By FooBar](https://www.youtube.com/watch?v=0VNKHIcQ5wk)
- https://github.com/mavi888/cdk-migrated-app-infra
3. [[cdk-patterns/serverless] The CloudWatch Dashboard](https://github.com/cdk-patterns/serverless/blob/main/the-cloudwatch-dashboard/README.md)

## 8.1. Building Dashboards for DocumentDB

7. [Dashboard automation for DocumentDB](https://catalog.us-east-1.prod.workshops.aws/workshops/464d6c17-9faa-4fef-ac9f-dd49610174d3/en-US/monitoring/deploy)

# 9. Pricing

1. [Understanding AWS CloudWatch Pricing: A Comprehensive Guide By Sandro Volpicella](https://blog.awsfundamentals.com/understanding-aws-cloudwatch-pricing-a-comprehensive-guide)

# 10. Videos

1. [Monitor AWS Resources Using Amazon CloudWatch Dashboards](https://www.youtube.com/watch?v=I7EFLChc07M)
1. [Monitoring Resources on AWS: CloudWatch Metrics and Dashboards](https://www.youtube.com/watch?v=9qKryBb7t6s)
1. [How To Create a CloudWatch Dashboard | Step by Step Walkthrough By Be A Better Dev](https://www.youtube.com/watch?v=5QK3FB1EsV0)
> 1. [[**MY-NEXT**] Building Amazon CloudWatch dashboards with AWS CDK By FooBar Serverless](https://www.youtube.com/watch?v=0VNKHIcQ5wk)
> 1. [CloudWatch Embedded Metric Format by cloudonaut](https://www.youtube.com/watch?v=HdopVzW6pX0)

# 11. Tutorials

1. [Mastering AWS CloudWatch: A Step-by-Step Tutorial for Beginners](https://cto.ai/blog/aws-cloudwatch/)

# 12. Workshops

1. [AWS CloudWatch and Systems Manager Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/a8e9c6a6-0ba9-48a7-a90d-378a440ab8ba/en-US)
2. [AWS Management and Governance Tools Workshop > Amazon CloudWatch](https://mng.workshop.aws/cloudwatch.html)
3. [AWS for Linux Workloads Immersion Day > CloudWatch](https://catalog.us-east-1.prod.workshops.aws/workshops/a8e9c6a6-0ba9-48a7-a90d-378a440ab8ba/en-US/300-cloudwatch)

# 13. Subscription filters

> 1. [[**MY-NEXT**] How to automatically subscribe to Amazon CloudWatch Logs groups by Rohit Kumar Singh and Ennio Pastore](https://aws.amazon.com/blogs/infrastructure-and-automation/how-to-automatically-subscribe-to-amazon-cloudwatch-logs-groups/)
  <img src="./images/enniop-architecture-diagram.jpg" title="enniop-architecture-diagram.jpg" width="900"/>
1. [CloudWatch Lambda Subscription Filter (CDK-Demo) by Duleendra Shashimal](https://towardsaws.com/cloudwatch-lamba-subscription-filter-cdk-demo-f0eb571547c6)

1. [[WORKSHOP] AWS CloudTrail Log Monitoring > Subscriptions Filter](https://catalog.us-east-1.prod.workshops.aws/workshops/2e48b9fc-f721-4417-b811-962b7f31b61c/en-US/subscriptionsfilter/creating-filter)

1. [Example 1: Subscription filters with Kinesis Data Streams](https://docs.aws.amazon.com/AmazonCloudWatch/latest/logs/SubscriptionFilters.html#DestinationKinesisExample)
1. [Example 2: Subscription filters with AWS Lambda](https://docs.aws.amazon.com/AmazonCloudWatch/latest/logs/SubscriptionFilters.html#LambdaFunctionExample)
1. [Example 3: Subscription filters with Amazon Kinesis Data Firehose](https://docs.aws.amazon.com/AmazonCloudWatch/latest/logs/SubscriptionFilters.html#FirehoseExample)

1. [Analyzing AWS CloudTrail in Amazon CloudWatch by Avinash Gogineni and Imaya Kumar Jagannathan ](https://aws.amazon.com/blogs/mt/analyzing-cloudtrail-in-cloudwatch/)


