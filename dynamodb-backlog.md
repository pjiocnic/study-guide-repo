
<!-- TOC -->

- [1. Frequent GoTos](#1-frequent-gotos)
- [2. Multiple topics](#2-multiple-topics)
- [3. Indexes](#3-indexes)
  - [3.1. GSI](#31-gsi)
  - [3.2. LSI](#32-lsi)
- [4. Partition Key](#4-partition-key)
- [5. Data Modeling](#5-data-modeling)
  - [5.1. Handling keys with Low cardinality](#51-handling-keys-with-low-cardinality)
- [6. Workshop](#6-workshop)
- [7. UseCases](#7-usecases)
  - [7.1. Usescases from AlexDebrie's Book](#71-usescases-from-alexdebries-book)
  - [7.2. Real world use cases](#72-real-world-use-cases)
  - [7.3. Priority Queues](#73-priority-queues)
  - [7.4. FIFO Queues](#74-fifo-queues)
  - [7.5. Sequence Generator](#75-sequence-generator)
  - [7.6. URL Shortner](#76-url-shortner)
  - [7.7. Near-Real-Time Event Processing](#77-near-real-time-event-processing)
- [8. Streams](#8-streams)
- [9. DynamoDB Videos](#9-dynamodb-videos)
- [10. Development](#10-development)
  - [10.1. Java](#101-java)
  - [10.2. GSI](#102-gsi)
  - [10.3. DynamoDBMapper](#103-dynamodbmapper)
  - [10.4. Enhanced DynamoDB client](#104-enhanced-dynamodb-client)
  - [10.5. HTTP Client](#105-http-client)
- [11. Design Patterns](#11-design-patterns)
- [12. Lambda Integration](#12-lambda-integration)
- [13. Migration](#13-migration)
- [14. Blogs](#14-blogs)

<!-- /TOC -->

# 1. Frequent GoTos

1. [Links currated by Alex Debrie](https://github.com/alexdebrie/awesome-dynamodb/blob/master/README.md)

# 2. Multiple topics

1. [DynamoDB: An Inside Look Into NoSQL – Part 1](https://cloudacademy.com/blog/dynamodb-an-inside-look-into-nosql-part-1/)
1. [DynamoDB: An Inside Look Into NoSQL – Part 2](https://cloudacademy.com/blog/dynamodb-an-inside-look-into-nosql-part-2/)
1. [DynamoDB: An Inside Look Into NoSQL – Part 3](https://cloudacademy.com/blog/dynamodb-an-inside-look-into-nosql-part-3/)
1. [DynamoDB: Replication and Partitioning – Part 4](https://cloudacademy.com/blog/dynamodb-replication-and-partitioning-part-4/)
1. [Data Versioning With DynamoDB – NoSQL, Part 5](https://cloudacademy.com/blog/data-versioning-with-dynamodb-an-inside-look-into-nosql-part-5/)
1. [How to Handle Failures in DynamoDB – An Inside Look Into NoSQL, Part 6](https://cloudacademy.com/blog/how-to-handle-failures-in-dynamodb-an-inside-look-into-nosql-part-6/)
1. [Membership and Failure Detection in DynamoDB: An Inside Look Into NoSQL, Part 7](https://cloudacademy.com/blog/membership-and-failure-detection-in-dynamodb-an-inside-look-into-nosql-part-7/)
1. [Monitoring DynamoDB with CloudWatch](https://cloudacademy.com/blog/cloudwatch-monitoring-dynamodb/)

# 3. Indexes

## 3.1. GSI

1. [How to use DynamoDB global secondary indexes to improve query performance and reduce costs by Shubham Sethi ](https://aws.amazon.com/blogs/database/how-to-use-dynamodb-global-secondary-indexes-to-improve-query-performance-and-reduce-costs/)
2. [How to design Amazon DynamoDB global secondary indexes by Shubham Sethi ](https://aws.amazon.com/blogs/database/how-to-design-amazon-dynamodb-global-secondary-indexes/)
3. [Now Available – Global Secondary Indexes for Amazon DynamoDB by Jeff Barr ](https://aws.amazon.com/blogs/aws/now-available-global-secondary-indexes-for-amazon-dynamodb/)
4. [Scaling Writes on Amazon DynamoDB Tables with Global Secondary Indexes by Ian Meyers](https://aws.amazon.com/blogs/database/serverless-fifo-queues-filtering-dynamodb-transactions/)
5. [Create, Update, and Delete Global Secondary Indexes Using the Amazon DynamoDB Document API by Manikandan Subramanian](https://aws.amazon.com/blogs/developer/create-update-and-delete-global-secondary-indexes-using-the-amazon-dynamodb-document-api/)

## 3.2. LSI

1. [Local Secondary Indexes for Amazon DynamoDB by Jeff Barr ](https://aws.amazon.com/blogs/aws/local-secondary-indexes-for-amazon-dynamodb/)

# 4. Partition Key

1. [Choosing the Right DynamoDB Partition Key by Gowri Balasubramanian and Sean Shriver ](https://aws.amazon.com/blogs/database/choosing-the-right-dynamodb-partition-key/)
2. [Using write sharding to distribute workloads evenly](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/bp-partition-key-sharding.html)

4. [Everything you need to know about DynamoDB Partitions](https://www.alexdebrie.com/posts/dynamodb-partitions/)
5. [See section on Optimize for provisioned throughput use @ How to design Amazon DynamoDB global secondary indexes](https://aws.amazon.com/blogs/database/how-to-design-amazon-dynamodb-global-secondary-indexes/)
6. [Best practices for designing and using partition keys effectively](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/bp-partition-key-design.html)


# 5. Data Modeling

1. [Data modeling with Amazon DynamoDB Alex DeBrie](https://d1.awsstatic.com/events/reinvent/2019/Data_modeling_with_Amazon_DynamoDB_CMY304.pdf)
- [AWS re:Invent 2019: Data modeling with Amazon DynamoDB (CMY304)](https://www.youtube.com/watch?v=DIQVJqiSUkE)
2. [7 Common DynamoDB Patterns for Modeling and Building an App with Alex De Brie](https://www.youtube.com/watch?v=Q6-qWdsa8a4&t=186s)
- Code: https://github.com/alexdebrie/dynamodb-instagram
3. [Alex de Brie: DynamoDB Misconceptions | Serverless Office Hours](https://www.youtube.com/watch?v=F8TYmz9fj2Y&)
4. [[MY NEXT] From relational DB to single DynamoDB table: a step-by-step exploration](https://www.trek10.com/blog/dynamodb-single-table-relational-modeling)
5. [[Video] Modeling a Wordpress data structures in Amazon DynamoDB with Rick Houlihan](https://www.youtube.com/watch?v=em860yYs7uw)
6. [Fundamentals of Amazon DynamoDB Single Table Design with Rick Houlihan](https://www.youtube.com/watch?v=KYy8X8t4MB8)
7. [AWS re:Invent 2018: Amazon DynamoDB Deep Dive: Advanced Design Patterns for DynamoDB (DAT401) By Rick Houlihan](https://www.youtube.com/watch?v=HaEPXoXVf2k)
-  https://d1.awsstatic.com/events/reinvent/2019/REPEAT_1_Amazon_DynamoDB_deep_dive_Advanced_design_patterns_DAT403-R1.pdf

## 5.1. Handling keys with Low cardinality


# 6. Workshop

See [workshops-backlog.md](./workshops-backlog.md)

# 7. UseCases

## 7.1. Usescases from AlexDebrie's Book

1. [See Alex Debrie's DynamoDB book for use cases]()

## 7.2. Real world use cases

1. [Real-world use cases for Amazon DynamoDB By Sean Shriver and Ankur Kasliwal](https://d1.awsstatic.com/events/reinvent/2019/REPEAT_1_Real-world_use_cases_for_Amazon_DynamoDB_DAT305-R1.pdf)

## 7.3. Priority Queues

1. [[MY NEXT] Implementing priority queueing with Amazon DynamoDB by Zoran Ivanovic](https://aws.amazon.com/blogs/database/implementing-priority-queueing-with-amazon-dynamodb/)

## 7.4. FIFO Queues

1. [Implement serverless FIFO queues with filtering capabilities using Amazon DynamoDB transactions by Nikhil Penmetsa and Randy DeFauw](https://aws.amazon.com/blogs/database/serverless-fifo-queues-filtering-dynamodb-transactions/)

## 7.5. Sequence Generator

1. [See Sequence Generator @ system-design-patterns-backlog.md](./system-design-patterns-backlog.md)

## 7.6. URL Shortner

1. See [apgw-backlog.md](./apgw-backlog.md)

## 7.7. Near-Real-Time Event Processing

1. [How to perform ordered data replication between applications by using Amazon DynamoDB Streams by Aravind Kodandaramaiah](https://aws.amazon.com/blogs/database/how-to-perform-ordered-data-replication-between-applications-by-using-amazon-dynamodb-streams/)

# 8. Streams

1. [DynamoDB Streams Use Cases and Design Patterns by Gowri Balasubramanian](https://aws.amazon.com/blogs/database/dynamodb-streams-use-cases-and-design-patterns/)

1. [Use Amazon DynamoDB global tables in DynamoDB Shell by Amrith Kumar](https://aws.amazon.com/blogs/database/use-amazon-dynamodb-global-tables-in-dynamodb-shell/)
2. [SQL, NoSQL, and Scale: How DynamoDB scales where relational databases don't By Alex Debrie](https://www.alexdebrie.com/posts/dynamodb-no-bad-queries/)

# 9. DynamoDB Videos

1. [AWS re:Invent 2019: [REPEAT 1] Amazon DynamoDB deep dive: Advanced design patterns (DAT403-R1) By Rick H](https://www.youtube.com/watch?v=6yqfmXiZTlM)
2. [DynamoDB under the hood - How does it works? What is the architecture of the database service?](https://www.youtube.com/watch?v=ZhXqNcbR4n0&t=909s)
3. [7 Common DynamoDB Patterns for Modeling and Building an App with Alex De Brie](https://www.youtube.com/watch?v=Q6-qWdsa8a4&t=200s)
4. [Designing a DynamoDB Table in 4 Steps: From Entities to Access Patterns](https://www.youtube.com/watch?v=JLZOI8patlw)
5. [{Video] Modeling a Wordpress data structures in Amazon DynamoDB with Rick Houlihan](https://www.youtube.com/watch?v=em860yYs7uw)

# 10. Development

1. [Working with JSON data in Amazon DynamoDB by Amrith Kumar](https://aws.amazon.com/blogs/database/working-with-json-data-in-amazon-dynamodb/)

## 10.1. Java

1. [Setting up DynamoDB local (downloadable version)](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html)
2. [Load sample data](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/SampleData.html)
3. [How to setup eclipse for dynamoDB](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/CodeSamples.Java.html)
4. [Working with Local Secondary Indexes: Java](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/LSIJavaDocumentAPI.html)
5. [Working with Global Secondary Indexes: Java](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/GSIJavaDocumentAPI.html)
6. [Sample code from AWS](https://github.com/aws/aws-sdk-java/tree/master/src/samples/AmazonDynamoDBDocumentAPI/quick-start/com/amazonaws/services/dynamodbv2/document/quickstart)

## 10.2. GSI

1. [Create, Update, and Delete Global Secondary Indexes Using the Amazon DynamoDB Document API by Manikandan Subramanian](https://aws.amazon.com/blogs/developer/create-update-and-delete-global-secondary-indexes-using-the-amazon-dynamodb-document-api/)

## 10.3. DynamoDBMapper

1. [[MY NEXT] Amazon DynamoDB single-table design using DynamoDBMapper and Spring Boot by Arjan Schaaf ](https://aws.amazon.com/blogs/database/amazon-dynamodb-single-table-design-using-dynamodbmapper-and-spring-boot/)
2. [The DynamoDBMapper, Local Secondary Indexes, and You! by Wade Matveyenko](https://aws.amazon.com/blogs/developer/the-dynamodbmapper-local-secondary-indexes-and-you/)
3. [Storing Java objects in Amazon DynamoDB tables by zachmu](https://aws.amazon.com/blogs/developer/storing-java-objects-in-amazon-dynamodb-tables/)
4. [Understanding Auto-Paginated Scan with DynamoDBMapper by zachmu](https://aws.amazon.com/blogs/developer/understanding-auto-paginated-scan-with-dynamodbmapper/)
5. [Using S3Link with Amazon DynamoDB by Jason Fulghum ](https://aws.amazon.com/blogs/developer/using-s3link-with-amazon-dynamodb/)
6. [Storing JSON documents in Amazon DynamoDB tables by Manikandan Subramanian](https://aws.amazon.com/blogs/developer/storing-json-documents-in-amazon-dynamodb-tables/)
7. [Specifying Conditional Constraints with Amazon DynamoDB Mapper by Jason Fulghum](https://aws.amazon.com/blogs/developer/specifying-conditional-constraints-with-amazon-dynamodb-mapper/)
8. [Client-side Encryption for Amazon DynamoDB by Hanson Char](https://aws.amazon.com/blogs/developer/client-side-encryption-for-amazon-dynamodb/)
9. [Using Custom Marshallers to Store Complex Objects in Amazon DynamoDB by zachmu](https://aws.amazon.com/blogs/developer/using-custom-marshallers-to-store-complex-objects-in-amazon-dynamodb/)

## 10.4. Enhanced DynamoDB client

1. [Introducing enhanced DynamoDB client in the AWS SDK for Java v2 by Ben Maizels](https://aws.amazon.com/blogs/developer/introducing-enhanced-dynamodb-client-in-the-aws-sdk-for-java-v2/)

## 10.5. HTTP Client

1. [Tuning AWS Java SDK HTTP request settings for latency-aware Amazon DynamoDB applications by Joarder Kamal and Sean Shriver](https://aws.amazon.com/blogs/database/tuning-aws-java-sdk-http-request-settings-for-latency-aware-amazon-dynamodb-applications/)

# 11. Design Patterns

1. [Amazon DynamoDB Design Patterns](https://github.com/aws-samples/amazon-dynamodb-design-patterns/tree/master)

# 12. Lambda Integration

1. [Building enterprise applications using Amazon DynamoDB, AWS Lambda, and Go by Geoffroy Rollat ](https://aws.amazon.com/blogs/database/building-enterprise-applications-using-amazon-dynamodb-aws-lambda-and-golang/)
2. [Serverless Architectures with Java 8, AWS Lambda, and Amazon DynamoDB — Part 1](https://aws.amazon.com/blogs/startups/serverless-architectures-with-java-8-aws-lambda-and-amazon-dynamodb-part-1/)
2. [Serverless Architectures with Java 8, AWS Lambda, and Amazon DynamoDB — Part 2 by Brent Rabowsky](https://aws.amazon.com/blogs/startups/serverless-architectures-with-java-8-aws-lambda-and-amazon-dynamodb-part-2/)
3. [Building a serverless developer authentication API in Java using AWS Lambda, Amazon DynamoDB, and Amazon Cognito – Part 1 by Dhruv Thukral](https://aws.amazon.com/blogs/developer/building-a-serverless-developer-authentication-api-in-java-using-aws-lambda-amazon-dynamodb-and-amazon-cognito-part-1/)
3. [Building a serverless developer authentication API in Java using AWS Lambda, Amazon DynamoDB, and Amazon Cognito – Part 2 by Dhruv Thukral](https://aws.amazon.com/blogs/developer/building-a-serverless-developer-authentication-api-in-java-using-aws-lambda-amazon-dynamodb-and-amazon-cognito-part-2/)
4. [Building a serverless developer authentication API in Java using AWS Lambda, Amazon DynamoDB, and Amazon Cognito – Part 3 by Dhruv Thukral](https://aws.amazon.com/blogs/developer/building-a-serverless-developer-authentication-api-in-java-using-aws-lambda-amazon-dynamodb-and-amazon-cognito-part-3/)

# 13. Migration

1. [Near Zero Downtime Migration from MySQL to DynamoDB by YongSeong Lee ](https://aws.amazon.com/blogs/big-data/near-zero-downtime-migration-from-mysql-to-dynamodb/)

# 14. Blogs

[See Dynamo Blogs @ blogs-2-visit.md](./blogs-2-visit.md)