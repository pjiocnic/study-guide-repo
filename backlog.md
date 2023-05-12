<h1> Table of Contents </h1>

<!-- TOC -->

- [1. MongoDB](#1-mongodb)
  - [1.1. Patterns](#11-patterns)
  - [1.2. Anti-Patterns](#12-anti-patterns)
  - [1.3. Data Modeling](#13-data-modeling)
  - [1.4. Change Data Streams](#14-change-data-streams)
  - [1.5. JOINs and Aggregations](#15-joins-and-aggregations)
  - [1.6. Versioning](#16-versioning)
  - [1.7. Optimistic Locking](#17-optimistic-locking)
  - [1.8. DocumentDB vs MongoDB](#18-documentdb-vs-mongodb)
  - [1.9. Schema Validation](#19-schema-validation)
  - [1.10. Aggregations](#110-aggregations)
  - [1.11. Tools](#111-tools)
  - [1.12. Sample Data](#112-sample-data)
  - [1.13. Books](#113-books)
  - [1.14. Courses](#114-courses)
  - [1.15. Docker](#115-docker)
- [2. IAM](#2-iam)
- [3. Organizations](#3-organizations)
- [4. Encryption](#4-encryption)
- [5. ElasticCache](#5-elasticcache)
- [7. Step functions](#7-step-functions)
- [8. Spring Boot Transactions](#8-spring-boot-transactions)
- [9. DynamoDB vs DocumentDB](#9-dynamodb-vs-documentdb)
- [10. RDS](#10-rds)
  - [10.1. Scaling](#101-scaling)
  - [10.2. Deployment - Single-AZ instance, Multi-AZ instance, or Multi-AZ](#102-deployment---single-az-instance-multi-az-instance-or-multi-az)
  - [10.3. Replication](#103-replication)
- [13. DNS](#13-dns)
- [15. Testing](#15-testing)
- [16. Case Studies](#16-case-studies)
- [17. Solutions](#17-solutions)
- [18. Workshops](#18-workshops)
- [19. FireHose](#19-firehose)
- [20. Aethna](#20-aethna)

<!-- /TOC -->


# 1. MongoDB

## 1.1. Patterns

1. [Building with Patterns: The Polymorphic Pattern Ken W. Alger, Daniel Coupal](https://www.mongodb.com/developer/products/mongodb/polymorphic-pattern/)
2. [Dive into 6 Common MongoDB Patterns With Example](https://medium.com/geekculture/dive-into-6-common-mongodb-patterns-with-examples-3fa7ece34b55)
3. [Data Model Examples and Patterns](https://www.mongodb.com/docs/manual/applications/data-models/)
4. [MongoDB Data Modeling Patterns](https://medium.com/@italoservio/mongodb-data-modeling-patterns-ae2e7a4ff155)
5. [M320: MongoDB Data Modeling](https://learn.mongodb.com/courses/m320-mongodb-data-modeling)
- Polymorphism
6. [Building with Patterns: The Bucket Pattern](https://www.mongodb.com/blog/post/building-with-patterns-the-bucket-pattern)
7. [All Schema Tutorials from MongoDB](https://www.mongodb.com/developer/products/mongodb/schema/tutorials/)
8. [Building with Patterns: A Summary](https://www.mongodb.com/blog/post/building-with-patterns-a-summary)

## 1.2. Anti-Patterns

1. [Schema Design Anti-Patterns Series MongoDB](https://www.youtube.com/watch?v=8CZs-0it9r4)
2. [Schema Design Anti-Patterns - Part 1](https://www.youtube.com/watch?v=8CZs-0it9r4)
3. [Schema Design Anti-Patterns - Part 2](https://www.youtube.com/watch?v=mHeP5IbozDU)

## 1.3. Data Modeling

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

## 1.4. Change Data Streams

1. [Change Streams & Triggers with Node.js Tutorial](https://www.mongodb.com/developer/languages/javascript/nodejs-change-streams-triggers/)

## 1.5. JOINs and Aggregations

1. [JOINs and Aggregations Using Real-Time Indexing on MongoDB Atlas](https://www.youtube.com/watch?v=6be6aEOHk3w)
2. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 1 of 3) – Introduction](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-1-of-3-introduction)
3. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 2 of 3) – Worked Examples](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-2-of-3-worked-examples)
4. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 3 of 3) – Adding Some Code Glue and Geolocation](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-3-of-3-adding-some-code-glue-and-geolocation)
5. [JOINs and Aggregations Using Real-Time Indexing on MongoDB Atlas By Bhat](https://www.youtube.com/watch?v=6be6aEOHk3w)

## 1.6. Versioning

1. [Versioning using mongoose-vermongo](https://medium.com/versioning-in-database-mongodb-versioning-vermongo/database-versioning-7cf59a729bb3)

## 1.7. Optimistic Locking

1. [Optimistic locking in Mongo](https://medium.com/@andris.briedis/optimistic-locking-in-mongo-69fa693864ec)

## 1.8. DocumentDB vs MongoDB

1. [Amazon DocumentDB Architectural Comparisons](https://www.mongodb.com/compare/documentdb-vs-mongodb/architecture)

## 1.9. Schema Validation

1. [MongoDB Schema Validation Rules By Panos Zafeiropoulos](https://betterprogramming.pub/mongodb-schema-validation-rules-8a1afc6ea67b)
2. [JSON Schema Validation - Locking down your model the smart way](https://www.mongodb.com/blog/post/json-schema-validation--locking-down-your-model-the-smart-way)
3. [JSON Schema Validation: A Vocabulary for Structural Validation of JSON](http://json-schema.org/draft/2020-12/json-schema-validation.html#name-json-pointers)
4. [Generate JSON Schema from JSON](https://www.jsonschema.net/)
5. [Schema validation from MongoDB docs](https://www.mongodb.com/docs/manual/core/schema-validation/)
6. [Add a choice, conditional, or pattern field from hackolade docs](https://hackolade.com/help/Addachoiceconditionalorpatternfi.html)

## 1.10. Aggregations

1. [Use a View to Join Two Collections](https://www.mongodb.com/docs/manual/core/views/join-collections-with-view/)

## 1.11. Tools
1. [Generate JSON Schema from JSON](https://www.jsonschema.net/)

## 1.12. Sample Data

1. [Sample Data from MongoDB](https://www.mongodb.com/docs/atlas/sample-data/)

## 1.13. Books

1. [Practical MongoDB Aggregations Book](https://www.practical-mongodb-aggregations.com/front-cover.html)

## 1.14. Courses

1. [The MongoDB Aggregation Framework (M121)](https://university.mongodb.com/courses/M121/about)
2. [M100: MongoDB for SQL Pros](https://learn.mongodb.com/courses/m100-mongodb-for-sql-pros)
3. [MongoDB CRUD Operations in Java](https://learn.mongodb.com/learn/course/mongodb-crud-operations-in-java/lesson-1-working-with-mongodb-documents-in-java/learn?client=customer)
4. [Using MongoDB with Java](https://learn.mongodb.com/learn/learning-path/using-mongodb-with-java)

## 1.15. Docker

1. [MongoDB in Docker](https://medium.com/@zzpzaf.se/mongodb-in-docker-bfa77346b389)

# 2. IAM

1. [Example IAM identity-based policies](https://docs.aws.amazon.com/IAM/latest/UserGuide/access_policies_examples.html)
2. [How and when to use different IAM policy types](https://catalog.workshops.aws/iam-policy-types/en-US)
3. [IAM policy evaluation workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/6dc3124a-6bd4-46eb-b5c4-be438a82ba3d/en-US)
4. [Effective IAM for Amazon Web Services](https://www.effectiveiam.com/)

# 3. Organizations

1. [How to Use Service Control Policies in AWS Organizations by Frank Phillis](https://aws.amazon.com/blogs/security/how-to-use-service-control-policies-in-aws-organizations/)
2. [Best Practices for Organizational Units with AWS Organizations by Andrew Blackham and Sam Elmalak ](https://aws.amazon.com/blogs/mt/best-practices-for-organizational-units-with-aws-organizations/)

# 4. Encryption

1. [Difference between AWS S3 Bucket Encryption SSE-C , SSE-S3, SSE-KMS](https://awstip.com/5-minutes-to-aws-s3-bucket-encryption-sse-c-sse-s3-sse-kms-e2fb07b05cb3)
2. [START HERE](https://catalog.us-east-1.prod.workshops.aws/workshops/aad9ff1e-b607-45bc-893f-121ea5224f24/en-US/s3)
3. https://trendmicro.awsworkshop.io/ee/60_finding_and_remediating/6001_s3_bucket.html

# 5. ElasticCache

1. [Using the Heimdall Proxy to Split Reads and Writes for Amazon Aurora and Amazon RDS by Jatin Singh and Erik Brandsberg](https://aws.amazon.com/blogs/apn/using-the-heimdall-proxy-to-split-reads-and-writes-for-amazon-aurora-and-amazon-rds/)
2. [Database Caching Strategies Using Redis](https://docs.aws.amazon.com/whitepapers/latest/database-caching-strategies-using-redis/caching-patterns.html)
3. [Highly Available Web Application Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/3de93ad5-ebbe-4258-b977-b45cdfe661f1/en-US)
4. [Automated query caching into Amazon ElastiCache for Amazon RDS, Amazon Aurora and Amazon Redshift by Darin Briskman](https://aws.amazon.com/blogs/database/automating-sql-caching-for-amazon-elasticache-and-amazon-rds)

# 7. Step functions

# 8. Spring Boot Transactions

1. [Best Practices for Using @Transactional in Spring Boot](https://medium.com/@omernaci/best-practices-for-using-transactional-in-spring-boot-ff416146a0e7)

# 9. DynamoDB vs DocumentDB

1. [Build with DynamoDB | When Should I use Amazon DynamoDB or Amazon DocumentDB](https://www.twitch.tv/aws/video/484071022)

# 10. RDS

## 10.1. Scaling
1. [Scaling Your Amazon RDS Instance Vertically and Horizontally by Marie Yap and Neha Gupta](https://aws.amazon.com/blogs/database/scaling-your-amazon-rds-instance-vertically-and-horizontally/)
2. [How can I distribute read requests across multiple Amazon RDS read replicas](https://aws.amazon.com/premiumsupport/knowledge-center/requests-rds-read-replicas/)

## 10.2. Deployment - Single-AZ instance, Multi-AZ instance, or Multi-AZ
1. [[MY NEXT] Choose the right Amazon RDS deployment option: Single-AZ instance, Multi-AZ instance, or Multi-AZ database cluster by Ankush Agarwal and Pranshu Mishra](https://aws.amazon.com/blogs/database/choose-the-right-amazon-rds-deployment-option-single-az-instance-multi-az-instance-or-multi-az-database-cluster/)

## 10.3. Replication

1. [Best practices for Amazon RDS PostgreSQL replication by Vivek Singh ](https://aws.amazon.com/blogs/database/best-practices-for-amazon-rds-postgresql-replication/)

# 13. DNS

1. [How to centralize DNS management in a multi-account environment by Mahmoud Matouk ](https://aws.amazon.com/blogs/security/how-to-centralize-dns-management-in-a-multi-account-environment/)
2. [[MY NEXT] Centralize access using VPC interface endpoints to access AWS services across multiple VPCs by Chetan Agrawal](https://aws.amazon.com/blogs/networking-and-content-delivery/centralize-access-using-vpc-interface-endpoints/)

# 15. Testing

1. [Testing Serverless Applications](https://serverlessland.com/testing)

# 16. Case Studies

1. [Prime Video Switched from Serverless to EC2 and ECS to Save Costs](https://www.infoq.com/news/2023/05/prime-ec2-ecs-saves-costs/)

# 17. Solutions

1. [How can I prevent my public GitHub repository from showing up on search results?](https://www.quora.com/How-can-I-prevent-my-public-GitHub-repository-from-showing-up-on-search-results)

# 18. Workshops

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
3. [Amazon Athena Workshop :: Hands on Labs](https://catalog.us-east-1.prod.workshops.aws/workshops/9981f1a1-abdc-49b5-8387-cb01d238bb78/en-US)

# 19. FireHose

1. [Amazon Kinesis Firehose Data Transformation with AWS Lambda by Bryan Liston](https://aws.amazon.com/blogs/compute/amazon-kinesis-firehose-data-transformation-with-aws-lambda/)

# 20. Aethna

1. [Loading Data from S3 to AWS Athena](https://levelup.gitconnected.com/loading-data-from-s3-to-aws-athena-7c56c63efccc)
2. [Extracting and joining data from multiple data sources with Athena Federated Query by Saurabh Bhutyani and Amir Basirat](https://aws.amazon.com/blogs/big-data/extracting-and-joining-data-from-multiple-data-sources-with-athena-federated-query/)
- CFN available
3. [Query any data source with Amazon Athena’s new federated query by Janak Agarwal](https://aws.amazon.com/blogs/big-data/query-any-data-source-with-amazon-athenas-new-federated-query/)
4. [Optimize Federated Query Performance using EXPLAIN and EXPLAIN ANALYZE in Amazon Athena by Nishchai JM and Varad Ram ](https://aws.amazon.com/blogs/big-data/optimize-federated-query-performance-using-explain-and-explain-analyze-in-amazon-athena/)


