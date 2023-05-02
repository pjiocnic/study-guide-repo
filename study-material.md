<h1> Table of Contents </h1>

<!-- TOC -->

- [1. DocumentDB](#1-documentdb)
- [2. MongoDB](#2-mongodb)
- [3. IAM](#3-iam)
- [4. Encryption](#4-encryption)
- [5. ElasticCache](#5-elasticcache)
- [6. Subscription filters](#6-subscription-filters)
- [7. Design Patterns](#7-design-patterns)
- [8. Centralized logging](#8-centralized-logging)
- [9. Step functions](#9-step-functions)
- [10. Spring Boot Transactions](#10-spring-boot-transactions)
- [11.DynamoDB vs DocumentDB](#11dynamodb-vs-documentdb)

<!-- /TOC -->

# 1. DocumentDB

1. [Creating a REST API for Amazon DocumentDB (with MongoDB compatibility) with Amazon API Gateway and AWS Lambda by Brian Hess](https://aws.amazon.com/blogs/database/creating-a-rest-api-for-amazon-documentdb-with-mongodb-compatibility-with-amazon-api-gateway-and-aws-labda/)
2. [Amazon DocumentDB (with MongoDB compatibility) read autoscaling by Randy DeFauw](https://aws.amazon.com/blogs/database/amazon-documentdb-with-mongodb-compatibility-read-autoscaling/)
3. https://github.com/aws-samples/amazon-documentdb-integration-with-application-autoscaler
4. [Run queries with Amazon Athena on Amazon DocumentDB](https://catalog.us-east-1.prod.workshops.aws/workshops/464d6c17-9faa-4fef-ac9f-dd49610174d3/en-US/change-streams/querydocdbwithathena)


# 2. MongoDB

1. [Schema Design Anti-Patterns Series MongoDB](https://www.youtube.com/watch?v=8CZs-0it9r4)
2. [Data Modeling with MongoDB](https://www.youtube.com/watch?v=3GHZd0zv170)
3. [Advanced Data Modeling](https://www.youtube.com/watch?v=qGzJRsaOOO0)
4. [NoSQL Data Modeling for the RDBMS Developer](https://www.youtube.com/watch?v=Y9WGjiSQkt8)
5. [MongoDB Schema Design Best Practices](https://www.youtube.com/watch?v=QAqK-R9HUhc)
6. [Sample DataModels using Hackolade](https://github.com/hackolade/sample-data-models/blob/main/MongoDB/Northwind%20Oracle%20normalized.hck.json)
7. [Schema Patterns - MongoDB - Part 1](https://shanu95.medium.com/schema-patterns-mongodb-part-1-16564f1198dc)
8. [Schema Patterns - MongoDB - Part 2](https://shanu95.medium.com/schema-patterns-mongodb-part-2-73bfabf86c9)
9. [MongoDB schema design](https://medium.com/hackernoon/mongodb-schema-design-86327d8fae83)
10. [Dive into 6 Common MongoDB Patterns With Example](https://medium.com/geekculture/dive-into-6-common-mongodb-patterns-with-examples-3fa7ece34b55)
11. [Data Model Examples and Patterns](https://www.mongodb.com/docs/manual/applications/data-models/)
12. [MongoDB Data Modeling Patterns](https://medium.com/@italoservio/mongodb-data-modeling-patterns-ae2e7a4ff155)
13. [M320: MongoDB Data Modeling](https://learn.mongodb.com/courses/m320-mongodb-data-modeling)
14. [JOINs and Aggregations Using Real-Time Indexing on MongoDB Atlas](https://www.youtube.com/watch?v=6be6aEOHk3w)
15. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 1 of 3) – Introduction](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-1-of-3-introduction)
16. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 2 of 3) – Worked Examples](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-2-of-3-worked-examples)
17. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 3 of 3) – Adding Some Code Glue and Geolocation](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-3-of-3-adding-some-code-glue-and-geolocation)
18. [Versioning using mongoose-vermongo](https://medium.com/versioning-in-database-mongodb-versioning-vermongo/database-versioning-7cf59a729bb3)
19. [JOINs and Aggregations Using Real-Time Indexing on MongoDB Atlas By Bhat](https://www.youtube.com/watch?v=6be6aEOHk3w)
20. [Schema Design Anti-Patterns - Part 1](https://www.youtube.com/watch?v=8CZs-0it9r4)
21. [Schema Design Anti-Patterns - Part 2](https://www.youtube.com/watch?v=mHeP5IbozDU)
22. [Optimistic locking in Mongo](https://medium.com/@andris.briedis/optimistic-locking-in-mongo-69fa693864ec)
23. [DocumentDB Insider Hour | Episode 14 | Data Modeling By Karthik Vijayraghavan](https://www.twitch.tv/videos/1235477994)
24. [Change Streams & Triggers with Node.js Tutorial](https://www.mongodb.com/developer/languages/javascript/nodejs-change-streams-triggers/)

# 3. IAM
1. [Example IAM identity-based policies](https://docs.aws.amazon.com/IAM/latest/UserGuide/access_policies_examples.html)
2. [How and when to use different IAM policy types](https://catalog.workshops.aws/iam-policy-types/en-US)
3. [IAM policy evaluation workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/6dc3124a-6bd4-46eb-b5c4-be438a82ba3d/en-US)
4. [Effective IAM for Amazon Web Services](https://www.effectiveiam.com/)

# 4. Encryption
1. [Difference between AWS S3 Bucket Encryption SSE-C , SSE-S3, SSE-KMS](https://awstip.com/5-minutes-to-aws-s3-bucket-encryption-sse-c-sse-s3-sse-kms-e2fb07b05cb3)
2. [START HERE](https://catalog.us-east-1.prod.workshops.aws/workshops/aad9ff1e-b607-45bc-893f-121ea5224f24/en-US/s3)
3. https://trendmicro.awsworkshop.io/ee/60_finding_and_remediating/6001_s3_bucket.html

# 5. ElasticCache

1. [Using the Heimdall Proxy to Split Reads and Writes for Amazon Aurora and Amazon RDS by Jatin Singh and Erik Brandsberg](https://aws.amazon.com/blogs/apn/using-the-heimdall-proxy-to-split-reads-and-writes-for-amazon-aurora-and-amazon-rds/)
2. [Database Caching Strategies Using Redis](https://docs.aws.amazon.com/whitepapers/latest/database-caching-strategies-using-redis/caching-patterns.html)
3. [Highly Available Web Application Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/3de93ad5-ebbe-4258-b977-b45cdfe661f1/en-US)
4. [Automated query caching into Amazon ElastiCache for Amazon RDS, Amazon Aurora and Amazon Redshift by Darin Briskman](https://aws.amazon.com/blogs/database/automating-sql-caching-for-amazon-elasticache-and-amazon-rds)

# 6. Subscription filters

1. [AWS: Create a subscription filter on Cloudwatch Log groups using Kinesis](https://awstip.com/aws-create-a-subscription-filter-on-cloudwatch-log-groups-using-kinesis-44b80e365bc9)
2. [How To Use AWS CloudWatch Subscription Filters By Kanika Modi](https://aws.plainenglish.io/how-to-use-aws-cloudwatch-subscription-filters-2f33f3e450c9)
3. [Sending events to CloudWatch Logs](https://docs.aws.amazon.com/awscloudtrail/latest/userguide/send-cloudtrail-events-to-cloudwatch-logs.html)
4. [Using CloudWatch Logs subscription filters](https://docs.aws.amazon.com/AmazonCloudWatch/latest/logs/SubscriptionFilters.html#LambdaFunctionExample)

# 7. Design Patterns

1. [Scalable Data Processing with AWS Serverless Scatter-Gather Pattern Implementation](https://aws.plainenglish.io/scalable-data-processing-with-aws-serverless-scatter-gather-pattern-implementation-63d25d6f6d23)

# 8. Centralized logging
1. [Stream Amazon CloudWatch Logs to a Centralized Account for Audit and Analysis by David Bailey](https://aws.amazon.com/blogs/architecture/stream-amazon-cloudwatch-logs-to-a-centralized-account-for-audit-and-analysis/)
2. [Using CloudWatch Logs subscription filters](https://docs.aws.amazon.com/AmazonCloudWatch/latest/logs/SubscriptionFilters.html#LambdaFunctionExample)
3. [AWS: Create a subscription filter on Cloudwatch Log groups using Kinesis](https://awstip.com/aws-create-a-subscription-filter-on-cloudwatch-log-groups-using-kinesis-44b80e365bc9)

# 9. Step functions
1. [Serverless Loan Broker @AWS, Part 1: Step Functions By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions.html)
2. [Serverless Loan Broker @AWS, Part 2: Recipient List By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions_recipient_list.html)
3. [Serverless Loan Broker @AWS, Part 3: Publish-Subscribe with SNS By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions_pubsub.html)
4. [Serverless Loan Broker @ AWS, Part 4: Automation By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_automation.html)
5. [Serverless Loan Broker @ AWS, Part 5: Integration Patterns with CDK By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_cdk.html)

# 10. Spring Boot Transactions
1. [Best Practices for Using @Transactional in Spring Boot](https://medium.com/@omernaci/best-practices-for-using-transactional-in-spring-boot-ff416146a0e7)

# 11.DynamoDB vs DocumentDB
1. [Build with DynamoDB | When Should I use Amazon DynamoDB or Amazon DocumentDB](https://www.twitch.tv/aws/video/484071022)