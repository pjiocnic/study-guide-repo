<h1> Table of Contents </h1>

<!-- TOC -->

- [1. DocumentDB](#1-documentdb)
  - [1.1. Athena integration](#11-athena-integration)
  - [1.2. Scaling](#12-scaling)
  - [1.3. Backups](#13-backups)
  - [1.4. Lambda](#14-lambda)
  - [1.5. Change Capture](#15-change-capture)
  - [1.6. Indexing](#16-indexing)
  - [1.7. Development](#17-development)
  - [1.8. JDBC](#18-jdbc)
  - [1.9. IAM](#19-iam)
  - [1.10. AWS Config](#110-aws-config)
  - [1.11. Archiving](#111-archiving)
  - [1.12. Spring Boot](#112-spring-boot)
  - [1.13. Data Modeling](#113-data-modeling)
  - [1.14. Keys](#114-keys)
  - [1.15. Caching](#115-caching)
  - [1.16. ECS](#116-ecs)
  - [1.17. Transactions](#117-transactions)
  - [1.18. Troubleshooting](#118-troubleshooting)
  - [1.19. Cost](#119-cost)
  - [1.20. Tools](#120-tools)
- [2. MongoDB](#2-mongodb)
  - [2.1. Patterns](#21-patterns)
  - [2.2. Anti-Patterns](#22-anti-patterns)
  - [2.3. Data Modeling](#23-data-modeling)
  - [2.4. Change Data Streams](#24-change-data-streams)
  - [2.5. JOINs and Aggregations](#25-joins-and-aggregations)
  - [2.6. Versioning](#26-versioning)
  - [2.7. Optimistic Locking](#27-optimistic-locking)
  - [2.8. DocumentDB vs MongoDB](#28-documentdb-vs-mongodb)
  - [2.9. Schema Validation](#29-schema-validation)
  - [2.10. Aggregations](#210-aggregations)
  - [2.11. Tools](#211-tools)
  - [2.12. Sample Data](#212-sample-data)
  - [2.13. Books](#213-books)
  - [2.14. Courses](#214-courses)
- [3. IAM](#3-iam)
- [4. Organizations](#4-organizations)
- [5. Encryption](#5-encryption)
- [6. ElasticCache](#6-elasticcache)
- [7. Design Patterns](#7-design-patterns)
  - [7.1. Storage-first patterns](#71-storage-first-patterns)
  - [7.2. Sequence Generator](#72-sequence-generator)
  - [7.3. Priority Queues](#73-priority-queues)
  - [7.4. Webhooks](#74-webhooks)
  - [7.5. Scatter Gather](#75-scatter-gather)
  - [Rate Limiter](#rate-limiter)
- [8. Step functions](#8-step-functions)
- [9. Spring Boot Transactions](#9-spring-boot-transactions)
- [10. DynamoDB vs DocumentDB](#10-dynamodb-vs-documentdb)
- [11. RDS](#11-rds)
- [12. DynamoDB](#12-dynamodb)
  - [12.1. DynamoDB workshop](#121-dynamodb-workshop)
  - [12.2. DynamoDB Videos](#122-dynamodb-videos)
  - [12.3. Development](#123-development)
  - [12.4. Lambda](#124-lambda)
- [13. Lambda](#13-lambda)
  - [13.1. S3 Integration](#131-s3-integration)
  - [13.2. Costs](#132-costs)
  - [13.3. CI/CD](#133-cicd)
  - [13.4. Development](#134-development)
  - [13.5. SAM](#135-sam)
  - [13.6. Event Source Mapping](#136-event-source-mapping)
  - [13.7. Error Handling](#137-error-handling)
- [14. DNS](#14-dns)
- [15. S3](#15-s3)
  - [15.1. Encryption](#151-encryption)
  - [15.2. Uploading](#152-uploading)
- [16. Testing](#16-testing)
- [17. Case Studies](#17-case-studies)
- [18. Solutions](#18-solutions)
- [19. Workshops](#19-workshops)
- [20. FireHose](#20-firehose)
- [21. Aethna](#21-aethna)

<!-- /TOC -->

# 1. DocumentDB

## 1.1. Athena integration

1. [Run queries with Amazon Athena on Amazon DocumentDB](https://catalog.us-east-1.prod.workshops.aws/workshops/464d6c17-9faa-4fef-ac9f-dd49610174d3/en-US/change-streams/querydocdbwithathena)

## 1.2. Scaling

1. [Scaling Amazon DocumentDB (with MongoDB compatibility), Part 1: Scaling reads by Leonid Koren and Jeff Duffy](https://aws.amazon.com/blogs/database/scaling-amazon-documentdb-with-mongodb-compatibility-part-1-scaling-reads/)
2. [Amazon DocumentDB (with MongoDB compatibility) read autoscaling by Randy DeFauw](https://aws.amazon.com/blogs/database/amazon-documentdb-with-mongodb-compatibility-read-autoscaling/)
3. https://github.com/aws-samples/amazon-documentdb-integration-with-application-autoscaler
4. https://docs.aws.amazon.com/documentdb/latest/developerguide/db-cluster-manage-performance.html#db-cluster-manage-scaling-instance
5. [New Amazon DocumentDB features for aggregations, arrays, and indexing by Joseph Idziorek](https://aws.amazon.com/blogs/database/new-amazon-documentdb-features-for-aggregations-arrays-and-indexing/)

## 1.3. Backups

1. [Backing up data with Amazon DocumentDB (with MongoDB compatibility) by Joseph Idziorek](https://aws.amazon.com/blogs/database/backing-up-data-with-amazon-documentdb-with-mongodb-compatibility/)

## 1.4. Lambda

1. [Running AWS Lambda-based applications with Amazon DocumentDB by Raj Chilakapati and Gowri Balasubramanian](https://aws.amazon.com/blogs/database/running-aws-lambda-based-applications-with-amazon-documentdb/)
2. [Creating a REST API for Amazon DocumentDB (with MongoDB compatibility) with Amazon API Gateway and AWS Lambda by Brian Hess](https://aws.amazon.com/blogs/database/creating-a-rest-api-for-amazon-documentdb-with-mongodb-compatibility-with-amazon-api-gateway-and-aws-labda/)
3. [Using AWS Lambda with Amazon DocumentDB](https://docs.aws.amazon.com/lambda/latest/dg/with-documentdb.html)

## 1.5. Change Capture
1. [Capture changes from Amazon DocumentDB via AWS Lambda and publish them to Amazon MSK by Murat Balkan](https://aws.amazon.com/blogs/database/capture-changes-from-amazon-documentdb-via-aws-lambda-and-publish-them-to-amazon-msk/)

## 1.6. Indexing
1. [How to index on Amazon DocumentDB (with MongoDB compatibility) by Cody Allen ](https://aws.amazon.com/blogs/database/how-to-index-on-amazon-documentdb-with-mongodb-compatibility/)

## 1.7. Development
1. [Building resilient applications with Amazon DocumentDB (with MongoDB compatibility), Part 1: Client configuration by Karthik Vijayraghavan](https://aws.amazon.com/blogs/database/building-resilient-applications-with-amazon-documentdb-with-mongodb-compatibility-part-1-client-configuration/)
1. [Building resilient applications with Amazon DocumentDB (with MongoDB compatibility), Part 2: Exception handling by Karthik Vijayraghavan](https://aws.amazon.com/blogs/database/building-resilient-applications-with-amazon-documentdb-with-mongodb-compatibility-part-2-exception-handling/)

## 1.8. JDBC
1. [Get started with the Amazon DocumentDB JDBC driver by Douglas Bonser](https://aws.amazon.com/blogs/database/get-started-with-the-amazon-documentdb-jdbc-driver/)

## 1.9. IAM
1. [Introducing Amazon DocumentDB (with MongoDB compatibility) user-defined roles for access control by Tim Callaghan](https://aws.amazon.com/blogs/database/introducing-amazon-documentdb-with-mongodb-compatibility-user-defined-roles-for-access-control/)

## 1.10. AWS Config

1. [<span style="color:red">[MY NEXT]</span> Evaluate Amazon DocumentDB (with MongoDB compatibility) configurations using AWS Config by Jesus Bernal and Karthik Vijayraghavan](https://aws.amazon.com/blogs/database/evaluate-amazon-documentdb-with-mongodb-compatibility-configurations-using-aws-config/)

## 1.11. Archiving

1. [Archive data from Amazon DocumentDB (with MongoDB compatibility) to Amazon S3 by Mark Mulligan and Karthik Vijayraghavan](https://aws.amazon.com/blogs/database/archive-data-from-amazon-documentdb-with-mongodb-compatibility-to-amazon-s3/)

## 1.12. Spring Boot

1. [Integrate your Spring Boot application with Amazon DocumentDB (with MongoDB compatibility) by Gururaj S Bayari ](https://aws.amazon.com/blogs/database/integrate-your-spring-boot-application-with-amazon-documentdb/)

## 1.13. Data Modeling

1. [Introduction to data modeling with Amazon DocumentDB (with MongoDB compatibility) for relational database users by Sameer Kumar](https://aws.amazon.com/blogs/database/introduction-to-data-modeling-with-amazon-documentdb-with-mongodb-compatibility-for-relational-database-users/)
2. [Document modeling with Amazon DocumentDB and Hackolade by Karthik Vijayraghavan and Pascal Desmarets](https://aws.amazon.com/blogs/database/document-modeling-with-amazon-documentdb-and-hackolade/)
3. [<span style="color:red">[MY NEXT]</span> Migrating relational databases to Amazon DocumentDB (with MongoDB compatibility) by Ganesh Sawhney](https://aws.amazon.com/blogs/database/migrating-relational-databases-to-amazon-documentdb-with-mongodb-compatibility/)
4. [<span style="color:red">[MY NEXT]</span> DocumentDB Insider Hour | Episode 21 | Document Modeling](https://www.youtube.com/watch?v=Lqjq36GC8Os)
5. [Data Modeling Methodology for Amazon DocumentDB- AWS Virtual Workshop By Mihai Aldoiu and Jason Plank](https://www.youtube.com/watch?v=Y87DVChZfwk&t=3s&pp=ygUpYXdzIGRvY3VtZW50ZGIgbWloYWkgYWxkb2x1IGRhdGEgbW9kZWxpbmc%3D)

## 1.14. Keys

1. [Choose shard keys to optimize Amazon DocumentDB Elastic Clusters by Jason Dalba](https://aws.amazon.com/blogs/database/choose-shard-keys-to-optimize-amazon-documentdb-elastic-clusters/)

## 1.15. Caching

1. [Caching for performance with Amazon DocumentDB and Amazon ElastiCache by Georges Leschener](https://aws.amazon.com/blogs/database/caching-for-performance-with-amazon-documentdb-and-amazon-elasticache/)

## 1.16. ECS

1. [Deploy a containerized application with Amazon ECS and connect to Amazon DocumentDB (with MongoDB compatibility) securely by Hidenori Koizumi](https://aws.amazon.com/blogs/database/deploy-a-containerized-application-with-amazon-ecs-and-connect-to-amazon-documentdb-securely/)

## 1.17. Transactions

1. [Introducing transactions in Amazon DocumentDB (with MongoDB compatibility) by Joseph Idziorek](https://aws.amazon.com/blogs/database/introducing-transactions-in-amazon-documentdb-with-mongodb-compatibility/)
2. [Introducing MongoDB 4.0 compatibility and Transactions in Amazon DocumentDB by Joseph Idziorek ](https://aws.amazon.com/blogs/database/introducing-amazon-documentdb-with-mongodb-compatibility-4-0/)
3. [[MY NEXT]Using Transactions with Amazon DocumentDB (with MongoDB compatibility) 4.0 - AWS Online Tech Talks](https://www.youtube.com/watch?v=5m_jRQvOo_I)

## 1.18. Troubleshooting

1. [Profiling slow-running queries in Amazon DocumentDB (with MongoDB compatibility) by Meet Bhagdev](https://aws.amazon.com/blogs/database/profiling-slow-running-queries-in-amazon-documentdb-with-mongodb-compatibility/ )
2. [Monitoring metrics and setting up alarms on your Amazon DocumentDB (with MongoDB compatibility) clusters by Ryan Thurston](https://aws.amazon.com/blogs/database/monitoring-metrics-and-setting-up-alarms-on-your-amazon-documentdb-with-mongodb-compatibility-clusters/)

## 1.19. Cost

1. [Using cost allocation tags with Amazon DocumentDB (with MongoDB compatibility) by Joseph Idziorek ](https://aws.amazon.com/blogs/database/using-cost-allocation-tags-with-amazon-documentdb-with-mongodb-compatibility/)
2. [Optimizing for cost with Amazon DocumentDB (with MongoDB compatibility) by Joseph Idziorek](https://aws.amazon.com/blogs/database/optimizing-for-cost-with-amazon-documentdb-with-mongodb-compatibility/)

## 1.20. Tools

1. [Studio 3T](https://docs.aws.amazon.com/documentdb/latest/developerguide/studio3t.html)

# 2. MongoDB

## 2.1. Patterns

1. [Building with Patterns: The Polymorphic Pattern Ken W. Alger, Daniel Coupal](https://www.mongodb.com/developer/products/mongodb/polymorphic-pattern/)
2. [Dive into 6 Common MongoDB Patterns With Example](https://medium.com/geekculture/dive-into-6-common-mongodb-patterns-with-examples-3fa7ece34b55)
3. [Data Model Examples and Patterns](https://www.mongodb.com/docs/manual/applications/data-models/)
4. [MongoDB Data Modeling Patterns](https://medium.com/@italoservio/mongodb-data-modeling-patterns-ae2e7a4ff155)
5. [M320: MongoDB Data Modeling](https://learn.mongodb.com/courses/m320-mongodb-data-modeling)
6. [Building with Patterns: The Bucket Pattern](https://www.mongodb.com/blog/post/building-with-patterns-the-bucket-pattern)
7. [All Schema Tutorials from MongoDB](https://www.mongodb.com/developer/products/mongodb/schema/tutorials/)
8. [Building with Patterns: A Summary](https://www.mongodb.com/blog/post/building-with-patterns-a-summary)

## 2.2. Anti-Patterns

1. [Schema Design Anti-Patterns Series MongoDB](https://www.youtube.com/watch?v=8CZs-0it9r4)
2. [Schema Design Anti-Patterns - Part 1](https://www.youtube.com/watch?v=8CZs-0it9r4)
3. [Schema Design Anti-Patterns - Part 2](https://www.youtube.com/watch?v=mHeP5IbozDU)

## 2.3. Data Modeling

1. [Data Modeling with MongoDB](https://www.youtube.com/watch?v=3GHZd0zv170)
2. [Advanced Data Modeling](https://www.youtube.com/watch?v=qGzJRsaOOO0)
3. [NoSQL Data Modeling for the RDBMS Developer](https://www.youtube.com/watch?v=Y9WGjiSQkt8)
4. [MongoDB Schema Design Best Practices](https://www.youtube.com/watch?v=QAqK-R9HUhc)
5. [Sample DataModels using Hackolade](https://github.com/hackolade/sample-data-models/blob/main/MongoDB/Northwind%20Oracle%20normalized.hck.json)
6. [Schema Patterns - MongoDB - Part 1](https://shanu95.medium.com/schema-patterns-mongodb-part-1-16564f1198dc)
7. [Schema Patterns - MongoDB - Part 2](https://shanu95.medium.com/schema-patterns-mongodb-part-2-73bfabf86c9)
8. [MongoDB schema design](https://medium.com/hackernoon/mongodb-schema-design-86327d8fae83)
9. [DocumentDB Insider Hour | Episode 14 | Data Modeling By Karthik Vijayraghavan](https://www.twitch.tv/videos/1235477994)
10. [DocumentDB Insider Hour | Episode 21 | Document Modeling](https://www.youtube.com/watch?v=Lqjq36GC8Os)
11. [Document modeling with Amazon DocumentDB and Hackolade by Karthik Vijayraghavan and Pascal Desmarets ](https://aws.amazon.com/blogs/database/document-modeling-with-amazon-documentdb-and-hackolade/)
12. [<span style="color:red">[MY NEXT]</span> A Complete Methodology of Data Modeling for MongoDB](https://www.youtube.com/watch?v=DUCvYbcgGsQ)
13. [Data Modeling Introduction By MongoDB Docs](https://www.mongodb.com/docs/manual/core/data-modeling-introduction/)

## 2.4. Change Data Streams

1. [Change Streams & Triggers with Node.js Tutorial](https://www.mongodb.com/developer/languages/javascript/nodejs-change-streams-triggers/)

## 2.5. JOINs and Aggregations

1. [JOINs and Aggregations Using Real-Time Indexing on MongoDB Atlas](https://www.youtube.com/watch?v=6be6aEOHk3w)
2. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 1 of 3) – Introduction](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-1-of-3-introduction)
3. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 2 of 3) – Worked Examples](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-2-of-3-worked-examples)
4. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 3 of 3) – Adding Some Code Glue and Geolocation](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-3-of-3-adding-some-code-glue-and-geolocation)
5. [JOINs and Aggregations Using Real-Time Indexing on MongoDB Atlas By Bhat](https://www.youtube.com/watch?v=6be6aEOHk3w)

## 2.6. Versioning

1. [Versioning using mongoose-vermongo](https://medium.com/versioning-in-database-mongodb-versioning-vermongo/database-versioning-7cf59a729bb3)

## 2.7. Optimistic Locking

1. [Optimistic locking in Mongo](https://medium.com/@andris.briedis/optimistic-locking-in-mongo-69fa693864ec)

## 2.8. DocumentDB vs MongoDB

1. [Amazon DocumentDB Architectural Comparisons](https://www.mongodb.com/compare/documentdb-vs-mongodb/architecture)

## 2.9. Schema Validation

1. [MongoDB Schema Validation Rules By Panos Zafeiropoulos](https://betterprogramming.pub/mongodb-schema-validation-rules-8a1afc6ea67b)
2. [JSON Schema Validation - Locking down your model the smart way](https://www.mongodb.com/blog/post/json-schema-validation--locking-down-your-model-the-smart-way)
3. [JSON Schema Validation: A Vocabulary for Structural Validation of JSON](http://json-schema.org/draft/2020-12/json-schema-validation.html#name-json-pointers)
4. [Generate JSON Schema from JSON](https://www.jsonschema.net/)
5. [Schema validation from MongoDB docs](https://www.mongodb.com/docs/manual/core/schema-validation/)
6. [Add a choice, conditional, or pattern field from hackolade docs](https://hackolade.com/help/Addachoiceconditionalorpatternfi.html)

## 2.10. Aggregations

1. [Use a View to Join Two Collections](https://www.mongodb.com/docs/manual/core/views/join-collections-with-view/)

## 2.11. Tools
1. [Generate JSON Schema from JSON](https://www.jsonschema.net/)

## 2.12. Sample Data

1. [Sample Data from MongoDB](https://www.mongodb.com/docs/atlas/sample-data/)

## 2.13. Books

1. [Practical MongoDB Aggregations Book](https://www.practical-mongodb-aggregations.com/front-cover.html)

## 2.14. Courses

1. [The MongoDB Aggregation Framework (M121)](https://university.mongodb.com/courses/M121/about)
2. [M100: MongoDB for SQL Pros](https://learn.mongodb.com/courses/m100-mongodb-for-sql-pros)
3. [MongoDB CRUD Operations in Java](https://learn.mongodb.com/learn/course/mongodb-crud-operations-in-java/lesson-1-working-with-mongodb-documents-in-java/learn?client=customer)
4. [Using MongoDB with Java](https://learn.mongodb.com/learn/learning-path/using-mongodb-with-java)

# 3. IAM

1. [Example IAM identity-based policies](https://docs.aws.amazon.com/IAM/latest/UserGuide/access_policies_examples.html)
2. [How and when to use different IAM policy types](https://catalog.workshops.aws/iam-policy-types/en-US)
3. [IAM policy evaluation workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/6dc3124a-6bd4-46eb-b5c4-be438a82ba3d/en-US)
4. [Effective IAM for Amazon Web Services](https://www.effectiveiam.com/)

# 4. Organizations

1. [How to Use Service Control Policies in AWS Organizations by Frank Phillis](https://aws.amazon.com/blogs/security/how-to-use-service-control-policies-in-aws-organizations/)
2. [Best Practices for Organizational Units with AWS Organizations by Andrew Blackham and Sam Elmalak ](https://aws.amazon.com/blogs/mt/best-practices-for-organizational-units-with-aws-organizations/)

# 5. Encryption

1. [Difference between AWS S3 Bucket Encryption SSE-C , SSE-S3, SSE-KMS](https://awstip.com/5-minutes-to-aws-s3-bucket-encryption-sse-c-sse-s3-sse-kms-e2fb07b05cb3)
2. [START HERE](https://catalog.us-east-1.prod.workshops.aws/workshops/aad9ff1e-b607-45bc-893f-121ea5224f24/en-US/s3)
3. https://trendmicro.awsworkshop.io/ee/60_finding_and_remediating/6001_s3_bucket.html

# 6. ElasticCache

1. [Using the Heimdall Proxy to Split Reads and Writes for Amazon Aurora and Amazon RDS by Jatin Singh and Erik Brandsberg](https://aws.amazon.com/blogs/apn/using-the-heimdall-proxy-to-split-reads-and-writes-for-amazon-aurora-and-amazon-rds/)
2. [Database Caching Strategies Using Redis](https://docs.aws.amazon.com/whitepapers/latest/database-caching-strategies-using-redis/caching-patterns.html)
3. [Highly Available Web Application Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/3de93ad5-ebbe-4258-b977-b45cdfe661f1/en-US)
4. [Automated query caching into Amazon ElastiCache for Amazon RDS, Amazon Aurora and Amazon Redshift by Darin Briskman](https://aws.amazon.com/blogs/database/automating-sql-caching-for-amazon-elasticache-and-amazon-rds)

# 7. Design Patterns

1. [Scalable Data Processing with AWS Serverless Scatter-Gather Pattern Implementation](https://aws.plainenglish.io/scalable-data-processing-with-aws-serverless-scatter-gather-pattern-implementation-63d25d6f6d23)
2. [Serverless-Architecture-Patterns-in-AWS](https://blog.richiebartlett.com/Work_%E4%BB%95%E4%BA%8B/Serverless-Architecture-Patterns-in-AWS/)

## 7.1. Storage-first patterns

1. [Building storage-first serverless applications with HTTP APIs service integrations by Eric Johnson](https://aws.amazon.com/blogs/compute/building-storage-first-applications-with-http-apis-service-integrations/)
2. [Build Better Serverless APIs By Going Storage First By Allen Helton](https://www.readysetcloud.io/blog/allen.helton/built-better-serverless-apis-by-going-storage-first/)
3. [Storage-First pattern in AWS with API Gateway, Part 1: using S3](https://dev.to/aws-builders/storage-first-pattern-in-aws-with-api-gateway-part-1-using-s3-1l5p)
4. [The Storage First Pattern By Jeremy Daly](https://www.jeremydaly.com/the-storage-first-pattern/)
5. [Storage-First Serverless Solutions By Serverless Advocate](https://blog.serverlessadvocate.com/storage-first-serverless-solutions-c0fc182243b9)

## 7.2. Sequence Generator

1. [Generating Sequence Numbers in Serverless via API Gateway By Sheen Brisals](https://medium.com/lego-engineering/sequence-numbering-in-serverless-via-api-gateway-40e5f6c83e93)
2. [Reliable Auto-Incrementing Integers in DynamoDB By Bite-Sized Serverless](https://bitesizedserverless.com/bite/reliable-auto-increments-in-dynamodb/)

## 7.3. Priority Queues

1. [Implement the Priority Queue Pattern with SQS and Lambda](https://bitesizedserverless.com/bite/implement-the-priority-queue-pattern-with-sqs-and-lambda/)

## 7.4. Webhooks

1. [Building a Webhooks System By Derek Comartin](https://www.youtube.com/watch?v=NuHC5uwbFAc&list=PLThyvG1mlMzm2FyVpKDiU2c7VtrB2Zezg&index=33)
2. [Building a Webhooks System with Event Driven Architecture](https://codeopinion.com/building-a-webhooks-system-with-event-driven-architecture/)
3. [How an AWS Lambda Function Can Be Integrated with Box Webhooks by Gaurav Verma ](https://aws.amazon.com/blogs/apn/how-an-aws-lambda-function-can-be-integrated-with-box-webhooks/)
4. [Getting started with serverless for developers: Part 1 by Benjamin Smith ](https://aws.amazon.com/blogs/compute/getting-started-with-serverless-for-developers-part-1/)

## 7.5. Scatter Gather

1. [[MY NEXT] Serverless Loan Broker @AWS, Part 1: Step Functions By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions.html)
2. [Serverless Loan Broker @AWS, Part 2: Recipient List By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions_recipient_list.html)
3. [Serverless Loan Broker @AWS, Part 3: Publish-Subscribe with SNS By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_stepfunctions_pubsub.html)
4. [Serverless Loan Broker @ AWS, Part 4: Automation By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_automation.html)
5. [Serverless Loan Broker @ AWS, Part 5: Integration Patterns with CDK By Gregor Hohpe](https://www.enterpriseintegrationpatterns.com/ramblings/loanbroker_cdk.html)

## Rate Limiter

1. [Prevent API overload with rate limiting in AWS](https://dev.to/aws-builders/prevent-api-overload-with-rate-limiting-in-aws-1dgb)
2. [[RATED] System Design Basics: Rate Limiter By Abhinav Singh](https://builtin.com/software-engineering-perspectives/rate-limiter)
3. [4 Rate Limit Algorithms Every Developer Should Know By Jason Ngan](https://betterprogramming.pub/4-rate-limit-algorithms-every-developer-should-know-7472cb482f48)
4. [Building well-architected serverless applications: Regulating inbound request rates – part 1](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-regulating-inbound-request-rates-part-1/)
5. [Building well-architected serverless applications: Regulating inbound request rates – part 2	](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-regulating-inbound-request-rates-part-2/)
6. [Rate Limiting Strategies for Serverless Applications by Sharon Li, Akhil Aendapally, and Ashish Lagwankar](https://aws.amazon.com/blogs/architecture/rate-limiting-strategies-for-serverless-applications/)
7. [System Design — Design A Rate Limiter](https://medium.com/geekculture/system-design-design-a-rate-limiter-81d200c9d392)
8. [COURSE - Design Patterns for Distributed Systems By Priyank Gupta](https://learning.oreilly.com/live-events/design-patterns-for-distributed-systems/0636920061982/0636920072964/)

# 8. Step functions

# 9. Spring Boot Transactions

1. [Best Practices for Using @Transactional in Spring Boot](https://medium.com/@omernaci/best-practices-for-using-transactional-in-spring-boot-ff416146a0e7)

# 10. DynamoDB vs DocumentDB

1. [Build with DynamoDB | When Should I use Amazon DynamoDB or Amazon DocumentDB](https://www.twitch.tv/aws/video/484071022)

# 11. RDS

1. [Scaling Your Amazon RDS Instance Vertically and Horizontally by Marie Yap and Neha Gupta](https://aws.amazon.com/blogs/database/scaling-your-amazon-rds-instance-vertically-and-horizontally/)
2. [How can I distribute read requests across multiple Amazon RDS read replicas](https://aws.amazon.com/premiumsupport/knowledge-center/requests-rds-read-replicas/)
3. [[MY NEXT] Choose the right Amazon RDS deployment option: Single-AZ instance, Multi-AZ instance, or Multi-AZ database cluster by Ankush Agarwal and Pranshu Mishra](https://aws.amazon.com/blogs/database/choose-the-right-amazon-rds-deployment-option-single-az-instance-multi-az-instance-or-multi-az-database-cluster/)

# 12. DynamoDB

1. [Use Amazon DynamoDB global tables in DynamoDB Shell by Amrith Kumar](https://aws.amazon.com/blogs/database/use-amazon-dynamodb-global-tables-in-dynamodb-shell/)
2. [SQL, NoSQL, and Scale: How DynamoDB scales where relational databases don't By Alex Debrie](https://www.alexdebrie.com/posts/dynamodb-no-bad-queries/)

## 12.1. DynamoDB workshop

1. [Hands-on Labs for Amazon DynamoDB](https://amazon-dynamodb-labs.com/event-driven-architecture.html)

## 12.2. DynamoDB Videos

1. [AWS re:Invent 2019: [REPEAT 1] Amazon DynamoDB deep dive: Advanced design patterns (DAT403-R1) By Rick H](https://www.youtube.com/watch?v=6yqfmXiZTlM)
2. [DynamoDB under the hood - How does it works? What is the architecture of the database service?](https://www.youtube.com/watch?v=ZhXqNcbR4n0&t=909s)
3. [7 Common DynamoDB Patterns for Modeling and Building an App with Alex De Brie](https://www.youtube.com/watch?v=Q6-qWdsa8a4&t=200s)
4. [Designing a DynamoDB Table in 4 Steps: From Entities to Access Patterns](https://www.youtube.com/watch?v=JLZOI8patlw)

## 12.3. Development

1. [Working with JSON data in Amazon DynamoDB by Amrith Kumar](https://aws.amazon.com/blogs/database/working-with-json-data-in-amazon-dynamodb/)

## 12.4. Lambda

1. [Building enterprise applications using Amazon DynamoDB, AWS Lambda, and Go by Geoffroy Rollat ](https://aws.amazon.com/blogs/database/building-enterprise-applications-using-amazon-dynamodb-aws-lambda-and-golang/)

# 13. Lambda

## 13.1. S3 Integration

1. [S3-to-Lambda patterns: Avoiding recursive invocation](https://github.com/aws-samples/s3-to-lambda-invocation-patterns)

## 13.2. Costs
1. [Understanding techniques to reduce AWS Lambda costs in serverless applications by James Beswick](https://aws.amazon.com/blogs/compute/understanding-techniques-to-reduce-aws-lambda-costs-in-serverless-applications/)

## 13.3. CI/CD

3. [Using AWS Lambda SnapStart with infrastructure as code and CI/CD pipelines by James Beswick](https://aws.amazon.com/blogs/compute/using-aws-lambda-snapstart-with-infrastructure-as-code-and-ci-cd-pipelines/)

## 13.4. Development

1. [Developing evolutionary architecture with AWS Lambda by Luca Mezzalira](https://aws.amazon.com/blogs/compute/developing-evolutionary-architecture-with-aws-lambda/)
2.  [Issues to Avoid When Implementing Serverless Architecture with AWS Lambda by Andrei Maksimov](https://aws.amazon.com/blogs/architecture/mistakes-to-avoid-when-implementing-serverless-architecture-with-lambda/)

## 13.5. SAM

1. [[MY NEXT] Building serverless Java applications with the AWS SAM CLI by James Beswick](https://aws.amazon.com/blogs/compute/building-serverless-java-applications-with-the-aws-sam-cli/)
    - [Building Java apps with SAM | Serverless Office Hours](https://www.youtube.com/watch?v=c7_gGalsIyE)

## 13.6. Event Source Mapping

1. [Understanding Amazon SQS and AWS Lambda Event Source Mapping for Efficient Message Processing by Tushar Sharma and Shaun Wang](https://aws.amazon.com/blogs/apn/understanding-amazon-sqs-and-aws-lambda-event-source-mapping-for-efficient-message-processing/)

## 13.7. Error Handling

1. [Implementing error handling for AWS Lambda asynchronous invocations By Poornima Chand](https://aws.amazon.com/blogs/compute/implementing-error-handling-for-aws-lambda-asynchronous-invocations/)

# 14. DNS

1. [How to centralize DNS management in a multi-account environment by Mahmoud Matouk ](https://aws.amazon.com/blogs/security/how-to-centralize-dns-management-in-a-multi-account-environment/)
2. [[MY NEXT] Centralize access using VPC interface endpoints to access AWS services across multiple VPCs by Chetan Agrawal](https://aws.amazon.com/blogs/networking-and-content-delivery/centralize-access-using-vpc-interface-endpoints/)

# 15. S3

## 15.1. Encryption

1. [workshop - Encryption On AWS - Tutorial](https://catalog.us-east-1.prod.workshops.aws/workshops/aad9ff1e-b607-45bc-893f-121ea5224f24/en-US/s3/serverside/sses3)
2. [5 minutes to AWS S3 Bucket Encryption SSE-C , SSE-S3, SSE-KMS](https://awstip.com/5-minutes-to-aws-s3-bucket-encryption-sse-c-sse-s3-sse-kms-e2fb07b05cb3)

## 15.2. Uploading

1. [Patterns for building an API to upload files to Amazon S3 by Thomas Moore,](https://aws.amazon.com/blogs/compute/patterns-for-building-an-api-to-upload-files-to-amazon-s3/)

# 16. Testing

1. [Testing Serverless Applications](https://serverlessland.com/testing)

# 17. Case Studies

1. [Prime Video Switched from Serverless to EC2 and ECS to Save Costs](https://www.infoq.com/news/2023/05/prime-ec2-ecs-saves-costs/)

# 18. Solutions

1. [How can I prevent my public GitHub repository from showing up on search results?](https://www.quora.com/How-can-I-prevent-my-public-GitHub-repository-from-showing-up-on-search-results)

# 19. Workshops

1. [Workshop AWS Account Setup](https://workshop-aws-account-setup.fstehle.com/)
- [Shared Services account](https://docs.aws.amazon.com/managedservices/latest/userguide/shared-services-account.html)
2. [Highly Available Web Application Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/3de93ad5-ebbe-4258-b977-b45cdfe661f1/en-US)
- Lab 1: Configure the network
- Lab 2: Set up your RDS database using cfn
- Lab 3: Set up Elasticache for Memcached using cfn
- Lab 4: Create the shared filesystem using cfn
- Lab 5: Create the load balancer using cfn
- Lab 6: Create a launch configuration using cfn
- Lab 7: Create the app server using cfn
- Lab 8: Configure caching using cfn
- Lab 9:: Add a Content Delivery Network using cfn
- Lab 10: Chaos testing with AWS Fault Injection Simulator

# 20. FireHose

1. [Amazon Kinesis Firehose Data Transformation with AWS Lambda by Bryan Liston](https://aws.amazon.com/blogs/compute/amazon-kinesis-firehose-data-transformation-with-aws-lambda/)

# 21. Aethna

1. [Loading Data from S3 to AWS Athena](https://levelup.gitconnected.com/loading-data-from-s3-to-aws-athena-7c56c63efccc)


