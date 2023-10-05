
<!-- TOC -->

- [1. Dashboard](#1-dashboard)
- [2. Frequent GoTos](#2-frequent-gotos)
- [3. Core Concepts](#3-core-concepts)
  - [3.1. Dynamodb TTL](#31-dynamodb-ttl)
- [4. Curate Topics](#4-curate-topics)
- [5. Data Modeling](#5-data-modeling)
  - [5.1. One-to-Many](#51-one-to-many)
  - [5.2. Many-to-Many](#52-many-to-many)
  - [5.3. RDMS to DynamoDB Modeling](#53-rdms-to-dynamodb-modeling)
  - [5.4. Modeling Examples](#54-modeling-examples)
  - [5.5. Modeling Hierarchical Data Structures](#55-modeling-hierarchical-data-structures)
  - [5.6. Single Table Vs Multi-Tables](#56-single-table-vs-multi-tables)
  - [5.7. DynamoDB schema design using SQL knowledge](#57-dynamodb-schema-design-using-sql-knowledge)
- [6. NoSQL Workbench](#6-nosql-workbench)
- [7. Indexes](#7-indexes)
  - [7.1. GSI](#71-gsi)
  - [7.2. LSI](#72-lsi)
- [9. Why does Throttling occur?](#9-why-does-throttling-occur)
- [10. How to design a Partition Key](#10-how-to-design-a-partition-key)
- [11. Partitions](#11-partitions)
  - [11.1. How do partitions work?](#111-how-do-partitions-work)
  - [11.2. Vertical Partitioning - to go beyond 400 KB Item size limit](#112-vertical-partitioning---to-go-beyond-400-kb-item-size-limit)
- [12. Querying](#12-querying)
  - [12.1. Pagination](#121-pagination)
  - [12.2. Parallel queries](#122-parallel-queries)
  - [12.3. Multiple attributes](#123-multiple-attributes)
- [8. Locking](#8-locking)
- [13. Sharding](#13-sharding)
- [19. Transactions](#19-transactions)
- [14. Workshop](#14-workshop)
- [15. UseCases](#15-usecases)
  - [15.1. Usecases from AlexDebrie's Book](#151-usecases-from-alexdebries-book)
  - [15.2. Real world use cases](#152-real-world-use-cases)
  - [15.3. Priority Queues](#153-priority-queues)
  - [15.4. FIFO Queues](#154-fifo-queues)
  - [15.5. Sequence Generator](#155-sequence-generator)
  - [15.6. URL Shortner](#156-url-shortner)
  - [15.7. Near-Real-Time Event Processing](#157-near-real-time-event-processing)
  - [15.8. Handling Large Objects](#158-handling-large-objects)
  - [15.9. Building Queryable index](#159-building-queryable-index)
  - [15.10. Counters](#1510-counters)
  - [15.11. Event filtering](#1511-event-filtering)
- [16. Global Tables](#16-global-tables)
- [17. DynamoDB Shell](#17-dynamodb-shell)
- [18. DynamoDB Streams](#18-dynamodb-streams)
  - [18.1. Deplayed processing](#181-deplayed-processing)
  - [18.2. Tutorials](#182-tutorials)
  - [18.3. Replication](#183-replication)
  - [18.4. Process DynamoDB streams using AWS Lambda](#184-process-dynamodb-streams-using-aws-lambda)
  - [18.5. Triggers](#185-triggers)
  - [18.6. Patterns](#186-patterns)
    - [18.6.1. curate](#1861-curate)
    - [18.6.2. Data Stream Patterns](#1862-data-stream-patterns)
    - [18.6.3. How to create Fan-out Patterns](#1863-how-to-create-fan-out-patterns)
  - [18.7. Event Aggregation](#187-event-aggregation)
- [20. Patterns](#20-patterns)
- [21. Performance Tuning](#21-performance-tuning)
- [22. Java](#22-java)
- [23. DynamoDB Videos](#23-dynamodb-videos)
  - [23.1. Videos with Rick Houlihan](#231-videos-with-rick-houlihan)
  - [23.2. Videos with Alex Debrie](#232-videos-with-alex-debrie)
- [24. Development](#24-development)
  - [24.1. Storing Attributes in a JSON (Fastest way to query)](#241-storing-attributes-in-a-json-fastest-way-to-query)
  - [24.2. Using Document API](#242-using-document-api)
  - [24.3. Writing Queries](#243-writing-queries)
  - [24.4. Java](#244-java)
  - [24.5. GSI](#245-gsi)
  - [24.6. DynamoDBMapper](#246-dynamodbmapper)
  - [24.7. Enhanced DynamoDB client](#247-enhanced-dynamodb-client)
- [25. Design Patterns](#25-design-patterns)
  - [25.1. Rate Limiter](#251-rate-limiter)
  - [25.2. Sequence Generators](#252-sequence-generators)
- [26. Lambda Integration](#26-lambda-integration)
- [27. Enterprise Grade Example](#27-enterprise-grade-example)
- [28. Migration](#28-migration)
- [29. Autoscaler](#29-autoscaler)
- [30. Blogs](#30-blogs)
- [31. SQL Style Interface](#31-sql-style-interface)
- [32. IAM](#32-iam)
- [33. How to Backup DynamoDB Tables](#33-how-to-backup-dynamodb-tables)
  - [33.1. Backup using AWS Backup](#331-backup-using-aws-backup)
- [34. Bulk work on DynamoDB Tables](#34-bulk-work-on-dynamodb-tables)
  - [34.1. How to do Bulk Import data into DynamoDB Tables](#341-how-to-do-bulk-import-data-into-dynamodb-tables)
  - [34.2. How to do Bulk updates on DynamoDB Tables](#342-how-to-do-bulk-updates-on-dynamodb-tables)
- [35. Under The Hood](#35-under-the-hood)
- [36. Monitoring Using Cloud Watch Metrics](#36-monitoring-using-cloud-watch-metrics)
- [37. TroubleShooting](#37-troubleshooting)
- [38. How to calculate Cost?](#38-how-to-calculate-cost)
- [39. CLI examples](#39-cli-examples)
- [40. Dynamodb User Stories](#40-dynamodb-user-stories)
- [41. Integrations](#41-integrations)
  - [41.1. Athena](#411-athena)
  - [41.2. Glue](#412-glue)
  - [41.3. FireHose](#413-firehose)
  - [41.4. S3](#414-s3)
  - [41.5. OpenSearch](#415-opensearch)
  - [41.6. Kinesis](#416-kinesis)
- [42. CLI](#42-cli)
- [43. Make Notes](#43-make-notes)
- [44. Skillbuilder courses](#44-skillbuilder-courses)

<!-- /TOC -->

# 1. Dashboard

1. Do separate projects for DynamoDbClient vs DynamoDbEnhancedClient vs DynamoDbAsyncClient
- See /Volumes/Lexar/git-repos/aws-repo/my-aws-snippets/dynamodb/dynamodb-samples.md
- https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/asynchronous.html
- https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/dynamodb-enhanced-client.html

1. [[**MY-NEXT**] Scaling DynamoDB: How partitions, hot keys, and split for heat impact performance (Part 3: Summary and best practices) by Jason Hunter and Vivek Natarajan |](https://aws.amazon.com/blogs/database/part-3-scaling-dynamodb-how-partitions-hot-keys-and-split-for-heat-impact-performance/)
1. [[**MY-NEXT**][**MUST-SEE**] A set of DynamoDB demo scripts and sample data that illustrate the read and write cost of various data access patterns By Rob McCauley](https://github.com/robm26/efficiencydemos)
1. [[**MY-NEXT**] From relational DB to single DynamoDB table: a step-by-step exploration](https://www.trek10.com/blog/dynamodb-single-table-relational-modeling)
1. [[**MY-NEXT**][**MUST-SEE**] A set of DynamoDB demo scripts and sample data that illustrate the read and write cost of various data access patterns.](https://github.com/robm26/efficiencydemos)
1. [[**MY-NEXT**] AWS re:Invent 2018: Amazon DynamoDB Deep Dive: Advanced Design Patterns for DynamoDB (DAT401) with Rick Houlihan](https://www.youtube.com/watch?v=HaEPXoXVf2k)
- [Notes by kevinhakanson.com](https://kevinhakanson.com/2018-12-04-amazon-dynamodb-deep-dive-advanced-design-patterns-for-dynamodb-dat401/)
1. [[**MY-NEXT**] Amazon DynamoDB single-table design using DynamoDBMapper and Spring Boot by Arjan Schaaf](https://aws.amazon.com/blogs/database/amazon-dynamodb-single-table-design-using-dynamodbmapper-and-spring-boot/)
1. [[**MY-NEXT**] DynamoDB: Its purpose, main features, and key concepts | Jason Hunter | AWS Events](https://www.youtube.com/watch?v=ummOosOW4lE)
1. [[**MY-NEXT**] DynamoDB: Under the hood, managing throughput, advanced design patterns | Jason Hunter | AWS Events](https://www.youtube.com/watch?v=0iGR8GnIItQ)
1. [[**MY-NEXT**] Delayed processing of DynamoDB stream with EventBridge Scheduler](https://serverlessland.com/patterns/apigw-dynamodb-lambda-scheduler-sqs-cdk)
1. [[**MY-NEXT**] Deep Dive into DynamoDB streams and the Lambda integration by Maurice Borgmeier](https://www.tecracer.com/blog/2022/03/deep-dive-into-dynamodb-streams-and-the-lambda-integration.html)
1. [[**MY-NEXT**] Modelling a product catalog in DynamoDB by Maurice Borgmeier](https://www.tecracer.com/blog/2021/03/modelling-a-product-catalog-in-dynamodb.html)
1. [[START HERE] Data Modeling with DynamoDB Workshop By Rob McCauley and Chad Tindel](https://www.youtube.com/watch?v=Uie7yip1yn8)
    - /Volumes/Lexar/git-repos/aws-repo/my-aws-workshops/dynamodb/dynamodb-modeling-workshop/my-readme.md
    - https://github.com/robm26/modeling
1. [[**MY-NEXT**] Amazon DynamoDB schema from the prism of SQL by Deep Dey](https://aws.amazon.com/blogs/database/amazon-dynamodb-schema-from-the-prism-of-sql)
1. [[**MY-NEXT**] Implementing priority queueing with Amazon DynamoDB by Zoran Ivanovic](https://aws.amazon.com/blogs/database/implementing-priority-queueing-with-amazon-dynamodb/)
1. [[**MY-NEXT**] Implement serverless FIFO queues with filtering capabilities using Amazon DynamoDB transactions by Nikhil Penmetsa and Randy DeFauw](https://aws.amazon.com/blogs/database/serverless-fifo-queues-filtering-dynamodb-transactions/)
1. [[**MY-NEXT**] How to use Amazon CloudWatch to monitor Amazon DynamoDB table size and item count metrics by Jason Hunter and Vivek Natarajan](https://aws.amazon.com/blogs/database/how-to-use-amazon-cloudwatch-to-monitor-amazon-dynamodb-table-size-and-item-count-metrics/)
1. [[**MY-NEXT**] Monitoring AWS DynamoDB performance and latency](https://lumigo.io/blog/monitoring-aws-dynamodb-performance-and-latency/)

# 2. Frequent GoTos

1. [Links currated by Alex Debrie](https://github.com/alexdebrie/awesome-dynamodb/blob/master/README.md)
2. [Best practices for designing and architecting with DynamoDB](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/best-practices.html)

# 3. Core Concepts

## 3.1. Dynamodb TTL

1. [Automatically Archive Items to S3 Using DynamoDB Time to Live (TTL) with AWS Lambda and Amazon Kinesis Firehose by Adam Wagner](https://aws.amazon.com/blogs/database/automatically-archive-items-to-s3-using-dynamodb-time-to-live-with-aws-lambda-and-amazon-kinesis-firehose/)

# 4. Curate Topics

1. [DynamoDB: An Inside Look Into NoSQL – Part 1](https://cloudacademy.com/blog/dynamodb-an-inside-look-into-nosql-part-1/)
2. [DynamoDB: An Inside Look Into NoSQL – Part 2](https://cloudacademy.com/blog/dynamodb-an-inside-look-into-nosql-part-2/)
3. [DynamoDB: An Inside Look Into NoSQL – Part 3](https://cloudacademy.com/blog/dynamodb-an-inside-look-into-nosql-part-3/)
4. [DynamoDB: Replication and Partitioning – Part 4](https://cloudacademy.com/blog/dynamodb-replication-and-partitioning-part-4/)
5. [Data Versioning With DynamoDB – NoSQL, Part 5](https://cloudacademy.com/blog/data-versioning-with-dynamodb-an-inside-look-into-nosql-part-5/)
6. [How to Handle Failures in DynamoDB – An Inside Look Into NoSQL, Part 6](https://cloudacademy.com/blog/how-to-handle-failures-in-dynamodb-an-inside-look-into-nosql-part-6/)
7. [Membership and Failure Detection in DynamoDB: An Inside Look Into NoSQL, Part 7](https://cloudacademy.com/blog/membership-and-failure-detection-in-dynamodb-an-inside-look-into-nosql-part-7/)
8. [Monitoring DynamoDB with CloudWatch](https://cloudacademy.com/blog/cloudwatch-monitoring-dynamodb/)
9. [Deep Dive on Partition Sharding and GSI Replication on Amazon DynamoDB with Rick Houlihan](https://www.youtube.com/watch?v=QhU_jozk-4o&t=9s)
10. [Using the NoSQL Workbench to build a purchase order application on DynamoDB with Rick Houlihan](https://www.youtube.com/watch?v=Xn12QSNa4RE)
11. [AWS re:Invent 2021 - DynamoDB deep dive: Advanced design patterns with Rick Houlihan](https://www.youtube.com/watch?v=xfxBhvGpoa0)
11. [DynamoDB Office Hours - Data Modeling with Dynobase](https://www.youtube.com/watch?v=h5wl6uEAVuc)
12. [Scaling DynamoDB: How partitions, hot keys, and split for heat impact performance (Part 1: Loading)](https://aws.amazon.com/blogs/database/part-1-scaling-dynamodb-how-partitions-hot-keys-and-split-for-heat-impact-performance/)
13. [Scaling DynamoDB: How partitions, hot keys, and split for heat impact performance (Part 2: Querying) by Jason Hunter and Vivek Natarajan ](https://aws.amazon.com/blogs/database/part-2-scaling-dynamodb-how-partitions-hot-keys-and-split-for-heat-impact-performance/)
14. [Scaling DynamoDB: How partitions, hot keys, and split for heat impact performance (Part 3: Summary and best practices) by Jason Hunter and Vivek Natarajan |](https://aws.amazon.com/blogs/database/part-3-scaling-dynamodb-how-partitions-hot-keys-and-split-for-heat-impact-performance/)
> 15. [[**MUST-SEE**] Advanced Design Patterns for Amazon DynamoDB By National Australia Bank - Part 1](https://medium.com/@nabtechblog/advanced-design-patterns-for-amazon-dynamodb-c31d65d2e3de)
> 16. [[**MUST-SEE**] Advanced Design Patterns for Amazon DynamoDB By National Australia Bank - Part 2](https://medium.com/@nabtechblog/advanced-design-patterns-for-amazon-dynamodb-354f97c96c2)

# 5. Data Modeling

1. [Data modeling with Amazon DynamoDB Alex DeBrie](https://d1.awsstatic.com/events/reinvent/2019/Data_modeling_with_Amazon_DynamoDB_CMY304.pdf)
- [AWS re:Invent 2019: Data modeling with Amazon DynamoDB (CMY304)](https://www.youtube.com/watch?v=DIQVJqiSUkE)
2. [7 Common DynamoDB Patterns for Modeling and Building an App with Alex De Brie](https://www.youtube.com/watch?v=Q6-qWdsa8a4&t=186s)
- Code: https://github.com/alexdebrie/dynamodb-instagram
3. [Alex de Brie: DynamoDB Misconceptions | Serverless Office Hours](https://www.youtube.com/watch?v=F8TYmz9fj2Y&)
4. [Fundamentals of Amazon DynamoDB Single Table Design with Rick Houlihan](https://www.youtube.com/watch?v=KYy8X8t4MB8)
5. [AWS re:Invent 2018: Amazon DynamoDB Deep Dive: Advanced Design Patterns for DynamoDB (DAT401) By Rick Houlihan](https://www.youtube.com/watch?v=HaEPXoXVf2k)
    -  https://d1.awsstatic.com/events/reinvent/2019/REPEAT_1_Amazon_DynamoDB_deep_dive_Advanced_design_patterns_DAT403-R1.pdf
> 6. [[**MY-NEXT**][**MUST-SEE**] A set of DynamoDB demo scripts and sample data that illustrate the read and write cost of various data access patterns By Rob McCauley](https://github.com/robm26/efficiencydemos)
7. [Data Modeling with Amazon DynamoDB- AWS Database in 15 By Rob McCauley](https://www.youtube.com/watch?v=kQ-DSjtCb90)
8. [DynamoDB Office Hours - Designing for Cost with Rob McCauley](https://www.youtube.com/watch?v=S02CRffcoX8)
9. [How to Model Any Relational Data in DynamoDB to Maximize Performance](https://edward-huang.com/best-practice/database/2021/04/13/how-to-model-any-relational-data-in-dynamodb-to-maximize-performance/)

## 5.1. One-to-Many

1. [How to model one-to-many relationships in DynamoDB By Alex Debrie](https://www.alexdebrie.com/posts/dynamodb-one-to-many/)

## 5.2. Many-to-Many

1. [Best practices for managing many-to-many relationships](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/bp-adjacency-graphs.html)

## 5.3. RDMS to DynamoDB Modeling

> 1. [[**MY-NEXT**] From relational DB to single DynamoDB table: a step-by-step exploration](https://www.trek10.com/blog/dynamodb-single-table-relational-modeling)

## 5.4. Modeling Examples

1. [DynamoDB Single-Table Design Examples Library by dynobase](https://dynobase.dev/dynamodb-single-table-design-examples/)
> 2. [[**MY-NEXT**] Modelling a product catalog in DynamoDB by Maurice Borgmeier](https://www.tecracer.com/blog/2021/03/modelling-a-product-catalog-in-dynamodb.html)
3. [Build with DynamoDB | Implementing an Inventory and Orders Management Data Model (Part 1)](https://www.twitch.tv/aws/video/470816184)
4. [[Video] Modeling a Wordpress data structures in Amazon DynamoDB with Rick Houlihan](https://www.youtube.com/watch?v=em860yYs7uw)
5. [Modeling a movie database](https://www.youtube.com/watch?v=nhUtZ7suZWI)
6. [[START HERE] Data Modeling with DynamoDB Workshop By Rob McCauley and Chad Tindel](https://www.youtube.com/watch?v=Uie7yip1yn8)
    - /Volumes/Lexar/git-repos/aws-repo/my-aws-workshops/dynamodb/dynamodb-modeling-workshop/my-readme.md
    - https://github.com/robm26/modeling

## 5.5. Modeling Hierarchical Data Structures

https://www.dynamodbguide.com/hierarchical-data/
https://www.youtube.com/watch?v=jzeKPKpucS0&t=2165s

## 5.6. Single Table Vs Multi-Tables

1. [Single-table vs. multi-table design in Amazon DynamoDB by Alex DeBrie](https://aws.amazon.com/blogs/database/single-table-vs-multi-table-design-in-amazon-dynamodb/)
2. [Creating a single-table design with Amazon DynamoDB by James Beswick](https://aws.amazon.com/blogs/compute/creating-a-single-table-design-with-amazon-dynamodb/)
3. [Single-table vs multi-table DynamoDB design patterns with GraphQL APIs and AWS AppSync by Ed Lima ](https://aws.amazon.com/blogs/mobile/single-table-vs-multi-table-dynamodb-appsync/)
4. [How to design an Amazon DynamoDB data model for a GraphQL API](https://aws.amazon.com/graphql/graphql-dynamodb-data-modeling/)
5. [The What, Why, and When of Single-Table Design with DynamoDB By Alex Debrie](https://www.alexdebrie.com/posts/dynamodb-single-table/)
6. [How to use single-table DynamoDB design with GraphQL By Alex Debrie | Amazon Web Services](https://www.youtube.com/watch?v=rlypcHUlsao&t=3s)
7. [How to use multiple DynamoDB tables with GraphQL By Alex Debrie | Amazon Web Services](https://www.youtube.com/watch?v=HSDKN43Vx7U&t=1s)

## 5.7. DynamoDB schema design using SQL knowledge

> 1. [[**MY-NEXT**] Amazon DynamoDB schema from the prism of SQL by Deep Dey](https://aws.amazon.com/blogs/database/amazon-dynamodb-schema-from-the-prism-of-sql)

# 6. NoSQL Workbench

1. [Using the NoSQL Workbench to build a purchase order application on DynamoDB with Rick Houlihan](https://www.youtube.com/watch?v=Xn12QSNa4RE)
2. [Data modeling with NoSQL Workbench for Amazon DynamoDB by Danilo Poccia ](https://aws.amazon.com/blogs/database/data-modeling-with-nosql-workbench-for-amazon-dynamodb/)

# 7. Indexes

## 7.1. GSI

1. [How to use DynamoDB global secondary indexes to improve query performance and reduce costs by Shubham Sethi ](https://aws.amazon.com/blogs/database/how-to-use-dynamodb-global-secondary-indexes-to-improve-query-performance-and-reduce-costs/)
2. [[MAKE NOTES] How to design Amazon DynamoDB global secondary indexes by Shubham Sethi ](https://aws.amazon.com/blogs/database/how-to-design-amazon-dynamodb-global-secondary-indexes/)
3. [Now Available – Global Secondary Indexes for Amazon DynamoDB by Jeff Barr ](https://aws.amazon.com/blogs/aws/now-available-global-secondary-indexes-for-amazon-dynamodb/)
4. [Scaling Writes on Amazon DynamoDB Tables with Global Secondary Indexes by Ian Meyers](https://aws.amazon.com/blogs/database/serverless-fifo-queues-filtering-dynamodb-transactions/)
5. [Create, Update, and Delete Global Secondary Indexes Using the Amazon DynamoDB Document API by Manikandan Subramanian](https://aws.amazon.com/blogs/developer/create-update-and-delete-global-secondary-indexes-using-the-amazon-dynamodb-document-api/)
6. [DynamoDB GSI Overloading - Quick Demo](https://www.youtube.com/watch?v=50OFFo7AmZo)

## 7.2. LSI

1. [Local Secondary Indexes for Amazon DynamoDB by Jeff Barr ](https://aws.amazon.com/blogs/aws/local-secondary-indexes-for-amazon-dynamodb/)



# 9. Why does Throttling occur?

1. [Five Ways to Deal With AWS DynamoDB GSI Throttling](https://medium.com/shelf-io-engineering/five-ways-to-deal-with-aws-dynamodb-gsi-throttling-1a489803a981)
2. [Why is my Amazon DynamoDB table being throttled?](https://repost.aws/knowledge-center/dynamodb-table-throttled)
3. [A cookbook to deal with throttling issues in Amazon DynamoDB](https://levelup.gitconnected.com/a-cookbook-to-deal-with-throttling-issues-in-amazon-dynamodb-f953c4ea4785)
4. [Tuning AWS Java SDK HTTP request settings for latency-aware Amazon DynamoDB applications by Joarder Kamal and Sean Shriver](https://aws.amazon.com/blogs/database/tuning-aws-java-sdk-http-request-settings-for-latency-aware-amazon-dynamodb-applications/)

# 10. How to design a Partition Key

1. [[READ AGAIN] Choosing the Right DynamoDB Partition Key by Gowri Balasubramanian and Sean Shriver ](https://aws.amazon.com/blogs/database/choosing-the-right-dynamodb-partition-key/)
2. [Using write sharding to distribute workloads evenly](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/bp-partition-key-sharding.html)
3. [See section on Optimize for provisioned throughput use @ How to design Amazon DynamoDB global secondary indexes](https://aws.amazon.com/blogs/database/how-to-design-amazon-dynamodb-global-secondary-indexes/)
4. [Best practices for designing and using partition keys effectively](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/bp-partition-key-design.html)

# 11. Partitions

## 11.1. How do partitions work?

1. [Partitioning Behavior of DynamoDB by Parth Modi](https://www.cloudbees.com/blog/partitioning-behavior-of-dynamodb)
2. [Everything you need to know about DynamoDB Partitions By Alex Debrie](https://www.alexdebrie.com/posts/dynamodb-partitions/)
3. [Scaling DynamoDB: How partitions, hot keys, and split for heat impact performance (Part 1: Loading)](https://aws.amazon.com/blogs/database/part-1-scaling-dynamodb-how-partitions-hot-keys-and-split-for-heat-impact-performance/)
4. [Scaling DynamoDB: How partitions, hot keys, and split for heat impact performance (Part 2: Querying) by Jason Hunter and Vivek Natarajan ](https://aws.amazon.com/blogs/database/part-2-scaling-dynamodb-how-partitions-hot-keys-and-split-for-heat-impact-performance/)
> 5. [[**MY-NEXT**] Scaling DynamoDB: How partitions, hot keys, and split for heat impact performance (Part 3: Summary and best practices) by Jason Hunter and Vivek Natarajan |](https://aws.amazon.com/blogs/database/part-3-scaling-dynamodb-how-partitions-hot-keys-and-split-for-heat-impact-performance/)

## 11.2. Vertical Partitioning - to go beyond 400 KB Item size limit

1. [Use vertical partitioning to scale data efficiently in Amazon DynamoDB by Mike MacKay and Samaneh Utter |](https://aws.amazon.com/blogs/database/use-vertical-partitioning-to-scale-data-efficiently-in-amazon-dynamodb/)
- Uses NoSQL Workbench for modeling
2. [Implement vertical partitioning in Amazon DynamoDB using AWS Glue by Juhi Patil and Mohammedfahim Pathan ](https://aws.amazon.com/blogs/database/implement-vertical-partitioning-in-amazon-dynamodb-using-aws-glue/)

# 12. Querying

## 12.1. Pagination

1. [Querying and Pagination with DynamoDB by Parth Modi](https://www.cloudbees.com/blog/querying-and-pagination-with-dynamodb)

## 12.2. Parallel queries

> 1. [[**MY-NEXT**] Use parallelism to optimize querying large amounts of data in Amazon DynamoDB by Zoran Ivanovic](https://aws.amazon.com/blogs/database/use-parallelism-to-optimize-querying-large-amounts-of-data-in-amazon-dynamodb/)

## 12.3. Multiple attributes

1. [Querying on Multiple Attributes in Amazon DynamoDB by Scott Todd](https://aws.amazon.com/blogs/database/querying-on-multiple-attributes-in-amazon-dynamodb/)

# 8. Locking

1. [Building Distributed Locks with the DynamoDB Lock Client by Alexander Patrikalakis and Sasha Slutsker](https://aws.amazon.com/blogs/database/building-distributed-locks-with-the-dynamodb-lock-client/)
1. [Locking an Item for Exclusive Processing in DynamoDB by Arseny Yankovski](https://medium.com/@arsenyyankovski/locking-an-item-for-exclusive-processing-in-dynamodb-be43779a12cc)
1. [Handle conditional write errors in high concurrency scenarios with Amazon DynamoDB by Esteban Serna Parra, Lee Hannigan, and Kevin Willis](https://aws.amazon.com/blogs/database/handle-conditional-write-errors-in-high-concurrency-scenarios-with-amazon-dynamodb/)

# 13. Sharding

1. [Choosing the right number of shards for your large-scale Amazon DynamoDB table by Anuj Dewangan and Sean Shriver](https://aws.amazon.com/blogs/database/choosing-the-right-number-of-shards-for-your-large-scale-amazon-dynamodb-table)
2. [Using write sharding to distribute workloads evenly](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/bp-partition-key-sharding.html)
3. [Leaderboard & Write Sharding By Alex Debrie](https://www.dynamodbguide.com/leaderboard-write-sharding/)

# 19. Transactions

1. [A framework for Amazon DynamoDB Transactions by Jeff Chen and Esteban Serna Parra ](https://aws.amazon.com/blogs/database/a-framework-for-amazon-dynamodb-transactions/)
1. [Making coordinated changes to multiple items with Amazon DynamoDB transactions by Baris Yasin and Serdar Nevruzoglu](https://aws.amazon.com/blogs/database/making-coordinated-changes-to-multiple-items-with-amazon-dynamodb-transactions/)
1. [DynamoDB transactions example](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/transaction-example.html)

# 14. Workshop

See [workshops-backlog.md](./workshops-backlog.md)

# 15. UseCases

## 15.1. Usecases from AlexDebrie's Book

1. [See Alex Debrie's DynamoDB book for use cases]()

## 15.2. Real world use cases

1. [Real-world use cases for Amazon DynamoDB By Sean Shriver and Ankur Kasliwal](https://d1.awsstatic.com/events/reinvent/2019/REPEAT_1_Real-world_use_cases_for_Amazon_DynamoDB_DAT305-R1.pdf)

## 15.3. Priority Queues

> 1. [[**MY-NEXT**] Implementing priority queueing with Amazon DynamoDB by Zoran Ivanovic](https://aws.amazon.com/blogs/database/implementing-priority-queueing-with-amazon-dynamodb/)

## 15.4. FIFO Queues

> 1. [[**MY-NEXT**] Implement serverless FIFO queues with filtering capabilities using Amazon DynamoDB transactions by Nikhil Penmetsa and Randy DeFauw](https://aws.amazon.com/blogs/database/serverless-fifo-queues-filtering-dynamodb-transactions/)

## 15.5. Sequence Generator

1. [See Sequence Generator @ system-design-patterns-backlog.md](./system-design-patterns-backlog.md)

## 15.6. URL Shortner

1. See [apgw-backlog.md](./apgw-backlog.md)

## 15.7. Near-Real-Time Event Processing

1. [How to perform ordered data replication between applications by using Amazon DynamoDB Streams by Aravind Kodandaramaiah](https://aws.amazon.com/blogs/database/how-to-perform-ordered-data-replication-between-applications-by-using-amazon-dynamodb-streams/)

## 15.8. Handling Large Objects

1. [Large object storage strategies for Amazon DynamoDB by Josh Hart](https://aws.amazon.com/blogs/database/large-object-storage-strategies-for-amazon-dynamodb/)

## 15.9. Building Queryable index

1. [Building and Maintaining an Amazon S3 Metadata Index without Servers by Mike Deck](https://aws.amazon.com/blogs/big-data/building-and-maintaining-an-amazon-s3-metadata-index-without-servers/)

## 15.10. Counters

1. [Implementing accurate counters in DynamoDB using Python Written by Maurice Borgmeier](https://www.tecracer.com/blog/2022/04/implementing-accurate-counters-in-dynamodb-using-python.html1)

## 15.11. Event filtering

1. [Friend microservices using Amazon DynamoDB and event filtering by Takahiro Ishi](https://aws.amazon.com/blogs/database/friend-microservices-using-amazon-dynamodb-and-event-filtering/)

# 16. Global Tables

1. [Use Amazon DynamoDB global tables in DynamoDB Shell by Amrith Kumar](https://aws.amazon.com/blogs/database/use-amazon-dynamodb-global-tables-in-dynamodb-shell/)

# 17. DynamoDB Shell

1. [Use Amazon DynamoDB global tables in DynamoDB Shell by Amrith Kumar](https://aws.amazon.com/blogs/database/use-amazon-dynamodb-global-tables-in-dynamodb-shell/)

# 18. DynamoDB Streams

> 1. [[**MY-NEXT**] DynamoDB Streams Use Cases and Design Patterns by Gowri Balasubramanian](https://aws.amazon.com/blogs/database/dynamodb-streams-use-cases-and-design-patterns/)
3. [SQL, NoSQL, and Scale: How DynamoDB scales where relational databases don't By Alex Debrie](https://www.alexdebrie.com/posts/dynamodb-no-bad-queries/)
4. [Automatically Archive Items to S3 Using DynamoDB Time to Live (TTL) with AWS Lambda and Amazon Kinesis Firehose by Adam Wagner](https://aws.amazon.com/blogs/database/automatically-archive-items-to-s3-using-dynamodb-time-to-live-with-aws-lambda-and-amazon-kinesis-firehose/)
> 5. [[**MY-NEXT**] Delayed processing of DynamoDB stream with EventBridge Scheduler](https://serverlessland.com/patterns/apigw-dynamodb-lambda-scheduler-sqs-cdk)
6. [How I solved Dynamic Task Scheduling using AWS DynamoDB TTL, Stream and Lambda Mohammad Anik Islam](https://medium.com/monstar-lab-bangladesh-engineering/how-i-solved-dynamic-task-scheduling-using-aws-dynamodb-ttl-stream-and-lambda-c0da5ebd6597)
7. [What is a DynamoDB Stream? (And why you should be using it!) By Be Better Dev](https://www.youtube.com/watch?v=OjppS4RWWt8)
8. [Processing Amazon DynamoDB Streams Using the Amazon Kinesis Client Library by Mital Awachat ](https://aws.amazon.com/blogs/big-data/processing-amazon-dynamodb-streams-using-the-amazon-kinesis-client-library/)
9. [Analyze a Time Series in Real Time with AWS Lambda, Amazon Kinesis and Amazon DynamoDB Streams by JustGiving, Saurabh Shrivastava, and Dhiraj Thakur](https://aws.amazon.com/blogs/big-data/analyze-a-time-series-in-real-time-with-aws-lambda-amazon-kinesis-and-amazon-dynamodb-streams/)
10. [Monitor Your Application for Processing DynamoDB Streams by Mital Awachat](https://aws.amazon.com/blogs/big-data/monitor-your-application-for-processing-dynamodb-streams/)

## 18.1. Deplayed processing

1. [How I solved Dynamic Task Scheduling using AWS DynamoDB TTL, Stream and Lambda By Monstar Lab Bangladesh Engineering](https://medium.com/monstar-lab-bangladesh-engineering/how-i-solved-dynamic-task-scheduling-using-aws-dynamodb-ttl-stream-and-lambda-c0da5ebd6597)

## 18.2. Tutorials

1. [Tutorial #1: Using filters to process all events with Amazon DynamoDB and AWS Lambda using the AWS CLI](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Streams.Lambda.Tutorial.html)
2. [Tutorial #2: Using filters to process some events with DynamoDB and Lambda](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Streams.Lambda.Tutorial2.html)
3. [DynamoDB Streams and Time to Live](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/time-to-live-ttl-streams.html)
4. [Using the DynamoDB Streams Kinesis adapter to process stream records](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Streams.KCLAdapter.html)

## 18.3. Replication

> 1. [[**MY-NEXT**] How to perform ordered data replication between applications by using Amazon DynamoDB Streams by Aravind Kodandaramaiah](https://aws.amazon.com/blogs/database/how-to-perform-ordered-data-replication-between-applications-by-using-amazon-dynamodb-streams)

## 18.4. Process DynamoDB streams using AWS Lambda

1. [How to perform ordered data replication between applications by using Amazon DynamoDB Streams by Aravind Kodandaramaiah](https://aws.amazon.com/blogs/database/how-to-perform-ordered-data-replication-between-applications-by-using-amazon-dynamodb-streams)
> 2. [[**MY-NEXT**] Deep Dive into DynamoDB streams and the Lambda integration by Maurice Borgmeier](https://www.tecracer.com/blog/2022/03/deep-dive-into-dynamodb-streams-and-the-lambda-integration.html)
3. [Automatically Archive Items to S3 Using DynamoDB Time to Live (TTL) with AWS Lambda and Amazon Kinesis Firehose by Adam Wagner](https://aws.amazon.com/blogs/database/automatically-archive-items-to-s3-using-dynamodb-time-to-live-with-aws-lambda-and-amazon-kinesis-firehose/)

## 18.5. Triggers

1. [Building NoSQL Database Triggers with Amazon DynamoDB and AWS Lambda by Tim Wagner](https://aws.amazon.com/blogs/compute/619/)

## 18.6. Patterns

### 18.6.1. curate

1. [Exploring serverless patterns for Amazon DynamoDB by Talia Nassi ](https://aws.amazon.com/blogs/compute/exploring-serverless-patterns-for-amazon-dynamodb/)

### 18.6.2. Data Stream Patterns

1. [Data Streaming Patterns By FooBar](https://www.youtube.com/watch?v=1M3znGyXOIw)

### 18.6.3. How to create Fan-out Patterns

1. [How to perform ordered data replication between applications by using Amazon DynamoDB Streams by Aravind Kodandaramaiah](https://aws.amazon.com/blogs/database/how-to-perform-ordered-data-replication-between-applications-by-using-amazon-dynamodb-streams)

## 18.7. Event Aggregation

1. [Moving to event-driven architectures with serverless event aggregators by Semih Duru and Marco Sommella](https://aws.amazon.com/blogs/mt/moving-to-event-driven-architectures-with-serverless-event-aggregators/)


# 20. Patterns

1. [Exploring serverless patterns for Amazon DynamoDB by Talia Nassi |](https://aws.amazon.com/blogs/compute/exploring-serverless-patterns-for-amazon-dynamodb/)

# 21. Performance Tuning

1. [Understanding Amazon DynamoDB latency by Sandip Gangdhar ](https://aws.amazon.com/blogs/database/understanding-amazon-dynamodb-latency/)

# 22. Java

1. [Tuning the AWS SDK for Java to Improve Resiliency by Andrew Shore ](https://aws.amazon.com/cn/blogs/developer/tuning-the-aws-sdk-for-java-to-improve-resiliency/)
1. [[SAMPLES] AWS Lambda Serverless CRUD API With Java](https://dev.to/aws-builders/aws-lambda-serverless-crud-api-with-java-189n)
1. [[SAMPLES] Creating a Spring Boot application that queries Amazon DynamoDB data](https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2/usecases/creating_dynamodb_web_app)
1. [Using atomic counters in the Enhanced DynamoDB AWS SDK for Java 2.x client by Anna-Karin Salander](https://aws.amazon.com/blogs/developer/using-atomic-counters-in-the-enhanced-dynamodb-aws-sdk-for-java-2-x-client/)
1. [Querying on Multiple Attributes in Amazon DynamoDB by Scott Todd](https://aws.amazon.com/blogs/database/querying-on-multiple-attributes-in-amazon-dynamodb/)
1. [Mid-level DynamoDB mapper/abstraction for Java using the v2 AWS SDK.](https://github.com/aws/aws-sdk-java-v2/tree/master/services-custom/dynamodb-enhanced)
1. [[Advanced Java + DDB Example] Reference for Multi-Region Resiliency for Trade and Settlement Application](https://github.com/aws-samples/multi-region-resiliency-reference-implementation.git)
1. [DynamoDB examples using SDK for Java 2.x](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/java_dynamodb_code_examples.html)


# 23. DynamoDB Videos

1. [DynamoDB under the hood - How does it work? What is the architecture of the database service?](https://www.youtube.com/watch?v=ZhXqNcbR4n0&t=909s)
2. [Designing a DyamoDB Table in 4 Steps: From Entities to Access Patterns](https://www.youtube.com/watch?v=JLZOI8patlw)
3. [Deep Dive on Multi-Region Architectures with Amazon DynamoDB - AWS Online Tech Talks](https://www.youtube.com/watch?v=fqxL3WQ53GM&t=72s)
https://www.youtube.com/watch?v=Mw8wCj0gkRc&list=PLJo-rJlep0EDNtcDeHDMqsXJcuKMcrC5F&index=1
> 4. [[**MUST-SEE**] Getting started with Amazon DynamoDB](https://serverlessland.com/content/service/dynamodb/guides/knowledge-portal/getting-started)

## 23.1. Videos with Rick Houlihan

> 1. [[**MY-NEXT**] AWS re:Invent 2017: [REPEAT] Advanced Design Patterns for Amazon DynamoDB (DAT403-R)](https://www.youtube.com/watch?v=jzeKPKpucS0)
2. [[MAKE NOTES] Modeling a Wordpress data structures in Amazon DynamoDB with Rick Houlihan](https://www.youtube.com/watch?v=em860yYs7uw)
3. [[MAKE NOTES] AWS re:Invent 2019: [REPEAT 1] Amazon DynamoDB deep dive: Advanced design patterns (DAT403-R1)](https://www.youtube.com/watch?v=6yqfmXiZTlM)
4. [[MAKE NOTES] Amazon DynamoDB deep dive: Advanced design patterns](https://d1.awsstatic.com/events/reinvent/2019/REPEAT_1_Amazon_DynamoDB_deep_dive_Advanced_design_patterns_DAT403-R1.pdf)
5. [AWS re:Invent 2021 - DynamoDB deep dive: Advanced design patterns BY RICK HOULIHAN](https://www.youtube.com/watch?v=xfxBhvGpoa0&t=1919s)
> 6. [[**MY-NEXT**] AWS re:Invent 2018: Amazon DynamoDB Deep Dive: Advanced Design Patterns for DynamoDB (DAT401) with Rick Houlihan](https://www.youtube.com/watch?v=HaEPXoXVf2k)
- [Notes by kevinhakanson.com](https://kevinhakanson.com/2018-12-04-amazon-dynamodb-deep-dive-advanced-design-patterns-for-dynamodb-dat401/)
7. [AWS re:Invent 2021 - DynamoDB deep dive: Advanced design Patterns](https://www.youtube.com/watch?v=xfxBhvGpoa0)

## 23.2. Videos with Alex Debrie

1. [7 Common DynamoDB Patterns for Modeling and Building an App with Alex De Brie](https://www.youtube.com/watch?v=Q6-qWdsa8a4&t=200s)

# 24. Development

1. [Amazon DynamoDB Update – JSON, Expanded Free Tier, Flexible Scaling, Larger Items by Jeff Barr ](https://aws.amazon.com/blogs/aws/dynamodb-update-json-and-more/)
2. [AWS Samples](https://github.com/aws-samples/aws-dynamodb-examples/tree/master)

## 24.1. Storing Attributes in a JSON (Fastest way to query)

1. [Working with JSON data in Amazon DynamoDB by Amrith Kumar](https://aws.amazon.com/blogs/database/working-with-json-data-in-amazon-dynamodb/)

## 24.2. Using Document API

1. [Introducing DynamoDB Document API (Part 1) by Hanson Char](https://aws.amazon.com/blogs/developer/introducing-dynamodb-document-api-part-1/)
2. [Introducing DynamoDB Document API (Part 2) by Hanson Char ](https://aws.amazon.com/blogs/developer/introducing-dynamodb-document-api-part-2/)
3. [A-Z Document API quick-start folder](https://github.com/aws/aws-sdk-java/tree/master/src/samples/AmazonDynamoDBDocumentAPI/quick-start/com/amazonaws/services/dynamodbv2/document/quickstart)

## 24.3. Writing Queries

1. [Using Improved Conditional Writes in DynamoDB by David Yanacek](https://aws.amazon.com/blogs/developer/using-improved-conditional-writes-in-dynamodb/)
2. [Improved Query Filtering and Conditional Updates for DynamoDB by Jeff Barr ](https://aws.amazon.com/blogs/aws/improved-queries-and-updates-for-dynamodb/)

## 24.4. Java

1. [Setting up DynamoDB local (downloadable version)](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html)
2. [Load sample data](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/SampleData.html)
3. [How to setup eclipse for dynamoDB](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/CodeSamples.Java.html)
4. [Working with Local Secondary Indexes: Java](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/LSIJavaDocumentAPI.html)
5. [Working with Global Secondary Indexes: Java](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/GSIJavaDocumentAPI.html)
6. [Sample code from AWS](https://github.com/aws/aws-sdk-java/tree/master/src/samples/AmazonDynamoDBDocumentAPI/quick-start/com/amazonaws/services/dynamodbv2/document/quickstart)
7. [Writing less code when using the AWS SDK for Java by Jason Fulghum](https://aws.amazon.com/blogs/developer/writing-less-code-when-using-the-aws-sdk-for-java/)

## 24.5. GSI

1. [Create, Update, and Delete Global Secondary Indexes Using the Amazon DynamoDB Document API by Manikandan Subramanian](https://aws.amazon.com/blogs/developer/create-update-and-delete-global-secondary-indexes-using-the-amazon-dynamodb-document-api/)

## 24.6. DynamoDBMapper

> 1. [[**MY-NEXT**] Amazon DynamoDB single-table design using DynamoDBMapper and Spring Boot by Arjan Schaaf](https://aws.amazon.com/blogs/database/amazon-dynamodb-single-table-design-using-dynamodbmapper-and-spring-boot/)
2. [The DynamoDBMapper, Local Secondary Indexes, and You! by Wade Matveyenko](https://aws.amazon.com/blogs/developer/the-dynamodbmapper-local-secondary-indexes-and-you/)
3. [Storing Java objects in Amazon DynamoDB tables by zachmu](https://aws.amazon.com/blogs/developer/storing-java-objects-in-amazon-dynamodb-tables/)
4. [Understanding Auto-Paginated Scan with DynamoDBMapper by zachmu](https://aws.amazon.com/blogs/developer/understanding-auto-paginated-scan-with-dynamodbmapper/)
5. [Using S3Link with Amazon DynamoDB by Jason Fulghum ](https://aws.amazon.com/blogs/developer/using-s3link-with-amazon-dynamodb/)
6. [Storing JSON documents in Amazon DynamoDB tables by Manikandan Subramanian](https://aws.amazon.com/blogs/developer/storing-json-documents-in-amazon-dynamodb-tables/)
7. [Specifying Conditional Constraints with Amazon DynamoDB Mapper by Jason Fulghum](https://aws.amazon.com/blogs/developer/specifying-conditional-constraints-with-amazon-dynamodb-mapper/)
8. [Client-side Encryption for Amazon DynamoDB by Hanson Char](https://aws.amazon.com/blogs/developer/client-side-encryption-for-amazon-dynamodb/)
9. [Using Custom Marshallers to Store Complex Objects in Amazon DynamoDB by zachmu](https://aws.amazon.com/blogs/developer/using-custom-marshallers-to-store-complex-objects-in-amazon-dynamodb/)

## 24.7. Enhanced DynamoDB client

1. [Introducing enhanced DynamoDB client in the AWS SDK for Java v2 by Ben Maizels](https://aws.amazon.com/blogs/developer/introducing-enhanced-dynamodb-client-in-the-aws-sdk-for-java-v2/)

# 25. Design Patterns

1. [Amazon DynamoDB Design Patterns](https://github.com/aws-samples/amazon-dynamodb-design-patterns/tree/master)

## 25.1. Rate Limiter

1. [Rate-Limited Scans in Amazon DynamoDB by Jason Fulghum](https://aws.amazon.com/blogs/developer/rate-limited-scans-in-amazon-dynamodb/)

## 25.2. Sequence Generators

1. [Atomic counters](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/WorkingWithItems.html#WorkingWithItems.AtomicCounters)

# 26. Lambda Integration

1. [How to Create an AWS IAM Policy to Grant AWS Lambda Access to an Amazon DynamoDB Table by Andrew Robinson](https://aws.amazon.com/blogs/security/how-to-create-an-aws-iam-policy-to-grant-aws-lambda-access-to-an-amazon-dynamodb-table/)
1. [Using AWS Lambda with Amazon DynamoDB](https://docs.aws.amazon.com/lambda/latest/dg/with-ddb.html)
    - Polling and batching streams
    - Simultaneous readers of a shard in DynamoDB Streams
    - Event source mapping APIs
    - Error handling
    - Time windows
    - Amazon CloudWatch metrics
    - Reporting batch item failures
    - AWS SAM template for a DynamoDB application
    - Example DDBEventProcessor.java
1. [Building enterprise applications using Amazon DynamoDB, AWS Lambda, and Go by Geoffroy Rollat ](https://aws.amazon.com/blogs/database/building-enterprise-applications-using-amazon-dynamodb-aws-lambda-and-golang/)
1. [Serverless Architectures with Java 8, AWS Lambda, and Amazon DynamoDB — Part 1](https://aws.amazon.com/blogs/startups/serverless-architectures-with-java-8-aws-lambda-and-amazon-dynamodb-part-1/)
1. [Serverless Architectures with Java 8, AWS Lambda, and Amazon DynamoDB — Part 2 by Brent Rabowsky](https://aws.amazon.com/blogs/startups/serverless-architectures-with-java-8-aws-lambda-and-amazon-dynamodb-part-2/)
1. [Building a serverless developer authentication API in Java using AWS Lambda, Amazon DynamoDB, and Amazon Cognito – Part 1 by Dhruv Thukral](https://aws.amazon.com/blogs/developer/building-a-serverless-developer-authentication-api-in-java-using-aws-lambda-amazon-dynamodb-and-amazon-cognito-part-1/)
1. [Building a serverless developer authentication API in Java using AWS Lambda, Amazon DynamoDB, and Amazon Cognito – Part 2 by Dhruv Thukral](https://aws.amazon.com/blogs/developer/building-a-serverless-developer-authentication-api-in-java-using-aws-lambda-amazon-dynamodb-and-amazon-cognito-part-2/)
1. [Building a serverless developer authentication API in Java using AWS Lambda, Amazon DynamoDB, and Amazon Cognito – Part 3 by Dhruv Thukral](https://aws.amazon.com/blogs/developer/building-a-serverless-developer-authentication-api-in-java-using-aws-lambda-amazon-dynamodb-and-amazon-cognito-part-3/)
1. [Serverless architectures working with DynamoDB and Lambda - AWS Virtual Workshop](https://www.youtube.com/watch?v=SKmHrhUR9oY)
1. [How to Build Dynamic Dashboards Using Lambda and DynamoDB Streams: Part 1 by AWS Admin ](https://aws.amazon.com/blogs/startups/building-dynamic-dashboards-using-lambda-and-dynamodb-streams-part-1/)
1. [How to Build Dynamic Dashboards Using AWS Lambda and Amazon DynamoDB Streams: Part II by AWS Editorial Team ](https://aws.amazon.com/blogs/startups/building-dynamic-dashboards-using-aws-lambda-and-amazon-dynamodb-streams-part-ii/)
1. [Creating a scalable serverless import process for Amazon DynamoDB by James Beswick ](https://aws.amazon.com/blogs/compute/creating-a-scalable-serverless-import-process-for-amazon-dynamodb/)


# 27. Enterprise Grade Example

1. [Building enterprise applications using Amazon DynamoDB, AWS Lambda, and Go by Geoffroy Rollat ](https://aws.amazon.com/blogs/database/building-enterprise-applications-using-amazon-dynamodb-aws-lambda-and-golang/)

# 28. Migration

1. [Near Zero Downtime Migration from MySQL to DynamoDB by YongSeong Lee ](https://aws.amazon.com/blogs/big-data/near-zero-downtime-migration-from-mysql-to-dynamodb/)

# 29. Autoscaler

See [autoscaling-backlog.md](./autoscaling-backlog.md)

# 30. Blogs

[See Dynamo Blogs @ blogs-2-visit.md](./blogs-2-visit.md)

# 31. SQL Style Interface

1. [Build faster with Amazon DynamoDB and PartiQL: SQL-compatible operations by Pete Naylor and Akshat Vig](https://aws.amazon.com/blogs/database/build-faster-with-amazon-dynamodb-and-partiql-sql-compatible-operations/)

# 32. IAM

1. [Fine-Grained Access Control for Amazon DynamoDB by Jeff Barr](https://aws.amazon.com/blogs/aws/fine-grained-access-control-for-amazon-dynamodb/)
2. [Getting Started with Fine-Grained Access Control for DynamoDB](https://www.youtube.com/watch?v=uAUYphLWL5w)
2. [DynamoDB Item and Column Level Access Controls | Part 1 of 2](https://www.youtube.com/watch?v=gHjrj_Efk20)
3. [Setup DynamoDB Column and Row Level Permissions | Part 2 of 2](https://www.youtube.com/watch?v=vluovhSOzxA&t=19s)

# 33. How to Backup DynamoDB Tables

1. [Archive data from Amazon DynamoDB to Amazon S3 using TTL and Amazon Kinesis integration by Bhupesh Sharma and Veerendra Nayak ](https://aws.amazon.com/blogs/database/archive-data-from-amazon-dynamodb-to-amazon-s3-using-ttl-and-amazon-kinesis-integration/)

## 33.1. Backup using AWS Backup

1. [Set up scheduled backups for Amazon DynamoDB using AWS Backup by Dhiraj Thakur](https://aws.amazon.com/blogs/database/set-up-scheduled-backups-for-amazon-dynamodb-using-aws-backup/)
- [DDB.yaml](my-github/study-guide-repo/templates/DDB.yaml)

# 34. Bulk work on DynamoDB Tables

## 34.1. How to do Bulk Import data into DynamoDB Tables

1. [Amazon DynamoDB can now import Amazon S3 data into a new table by Robert McCauley and Aman Dhingra](https://aws.amazon.com/blogs/database/amazon-dynamodb-can-now-import-amazon-s3-data-into-a-new-table/)

## 34.2. How to do Bulk updates on DynamoDB Tables

2. [Cost-effective bulk processing with Amazon DynamoDB by Jason Hunter](https://aws.amazon.com/blogs/database/cost-effective-bulk-processing-with-amazon-dynamodb)

# 35. Under The Hood

> 1. [[**MY-NEXT**] DynamoDB: Its purpose, main features, and key concepts | Jason Hunter | AWS Events](https://www.youtube.com/watch?v=ummOosOW4lE)
> 2. [[**MY-NEXT**] DynamoDB: Under the hood, managing throughput, advanced design patterns | Jason Hunter | AWS Events](https://www.youtube.com/watch?v=0iGR8GnIItQ)
3. [DynamoDB under the hood - How does it works? What is the architecture of the database service? by FooBar](https://www.youtube.com/watch?v=ZhXqNcbR4n0&list=PLGyRwGktEFqdpYgi2eSADjQyLAsOQ4a02&index=16&t=910s)

# 36. Monitoring Using Cloud Watch Metrics

> 1. [[**MY-NEXT**] How to use Amazon CloudWatch to monitor Amazon DynamoDB table size and item count metrics by Jason Hunter and Vivek Natarajan](https://aws.amazon.com/blogs/database/how-to-use-amazon-cloudwatch-to-monitor-amazon-dynamodb-table-size-and-item-count-metrics/)
2. [Top metrics to consider while monitoring DynamoDB performance](https://blogs.manageengine.com/application-performance-2/appmanager/2018/11/16/top-metrics-consider-monitoring-dynamodb-performance.html)
> 3. [[**MY-NEXT**] Monitoring AWS DynamoDB performance and latency](https://lumigo.io/blog/monitoring-aws-dynamodb-performance-and-latency/)
4. [Monitor Your DynamoDB Access Patterns with AWS Contributor Insights By Be Better Dev](https://www.youtube.com/watch?v=yEkePcvI5nA)
5. [Monitoring Amazon DynamoDB for operational awareness by Chad Tindel, Pratik Agarwal, Ankur Kasliwal, and Pete Naylor](https://aws.amazon.com/blogs/database/monitoring-amazon-dynamodb-for-operational-awareness/)
> 6. [[**MY-NEXT**] Top DynamoDB performance metrics](https://www.datadoghq.com/blog/top-dynamodb-performance-metrics/)
7. [How Medium Detects Hotspots in DynamoDB using ElasticSearch, Logstash and Kibana](https://medium.engineering/how-medium-detects-hotspots-in-dynamodb-using-elasticsearch-logstash-and-kibana-aaa3d6632cfd)
8. [A serverless solution to monitor the storage of your Amazon DynamoDB tables by Masudur Rahaman Sayem](https://aws.amazon.com/blogs/database/a-serverless-solution-to-monitor-the-storage-of-your-amazon-dynamodb-tables/)

# 37. TroubleShooting

> 1. [[**MY-NEXT**] Troubleshooting: Amazon DynamoDB](https://explore.skillbuilder.aws/learn/course/12617/play/49630/troubleshooting-amazon-dynamodb)
2. [Amazon DynamoDB Capacity Sizing](https://explore.skillbuilder.aws/learn/course/internal/view/elearning/7673/amazon-dynamodb-capacity-sizing)
3. [Amazon DynamoDB - Monitoring](https://explore.skillbuilder.aws/learn/course/internal/view/elearning/1142/amazon-dynamodb-monitoring)

# 38. How to calculate Cost?

1. [How you should think about DynamoDB costs By Alex Debrie](https://www.alexdebrie.com/posts/dynamodb-costs/)
2. [Dynamodb for DBAs – Calculating RCUs and WCUs](https://klouddb.io/dynamodb-for-dbas-calculating-rcus-and-wcus/)
3. [Calculating the Required Read and Write Capacity Unit for your DynamoDB Table By Tutorial Dojo](https://tutorialsdojo.com/calculating-the-required-read-and-write-capacity-unit-for-your-dynamodb-table/)
4. [Calculate Amazon DynamoDB reserved capacity recommendations to optimize costs by Sanjna Srivatsa and Sahil Thapar ](https://aws.amazon.com/blogs/database/calculate-amazon-dynamodb-reserved-capacity-recommendations-to-optimize-costs/)
5. [An Intro to Amazon DynamoDB Pricing: Challenges and Best Practices | Finout](https://www.finout.io/blog/an-intro-to-dynamodb-pricing-challenges-and-best-practices)
> 6. [[**MY-NEXT**][**MUST-SEE**] A set of DynamoDB demo scripts and sample data that illustrate the read and write cost of various data access patterns.](https://github.com/robm26/efficiencydemos)
7. [ddb viz-A webapp to visualize your DynamoDB tables and table data, with a focus on cost optimization](https://github.com/robm26/ddbviz)
8. [Amazon DynamoDB Cost Modeling Workshop](https://github.com/robm26/cost)

# 39. CLI examples

1. [DynamoDB CLI Commands & Query Examples Cheat Sheet](https://dynobase.dev/dynamodb-cli-query-examples)

# 40. Dynamodb User Stories

1. [What I’ve Learned From Using AWS DynamoDB in Production for More Than 3 Years](https://awstip.com/what-ive-learned-from-using-aws-dynamodb-in-production-for-more-than-3-years-49a077886b5c)
- Has tips suitable for exam preparation
2. [How Twilio modernized its Messaging Postflight service data store with Amazon DynamoDB by ND Ngoka, Vijay Bhat, Greg Krumm, and Nikolai Kolesnikov](https://aws.amazon.com/blogs/database/how-twilio-modernized-its-messaging-postflight-service-data-store-with-amazon-dynamodb/)

# 41. Integrations

## 41.1. Athena

1. [How to perform advanced analytics and build visualizations of your Amazon DynamoDB data by using Amazon Athena by Roger Dahlstrom and Ilya Epshteyn](https://aws.amazon.com/blogs/database/how-to-perform-advanced-analytics-and-build-visualizations-of-your-amazon-dynamodb-data-by-using-amazon-athena/)

## 41.2. Glue

1. [Simplify Amazon DynamoDB data extraction and analysis by using AWS Glue and Amazon Athena by Laith Al-Saadoon and Ghiyath Alazzah](https://aws.amazon.com/blogs/database/simplify-amazon-dynamodb-data-extraction-and-analysis-by-using-aws-glue-and-amazon-athena/)
2. [Accelerate Amazon DynamoDB data access in AWS Glue jobs using the new AWS Glue DynamoDB Export connector by Noritaka Sekiyama, Andrew Kim, Neil Gupta, and Savio Dsouza](https://aws.amazon.com/blogs/big-data/accelerate-amazon-dynamodb-data-access-in-aws-glue-jobs-using-the-new-aws-glue-dynamodb-elt-connector/)

## 41.3. FireHose

1. [Automatically Archive Items to S3 Using DynamoDB Time to Live (TTL) with AWS Lambda and Amazon Kinesis Firehose by Adam Wagner](https://aws.amazon.com/blogs/database/automatically-archive-items-to-s3-using-dynamodb-time-to-live-with-aws-lambda-and-amazon-kinesis-firehose/)
2. [Build seamless data streaming pipelines with Amazon Kinesis Data Streams and Amazon Kinesis Data Firehose for Amazon DynamoDB tables by Ashutosh Pateriya, Elie Gharios, and Saurabh Shrivastava](https://aws.amazon.com/blogs/big-data/build-seamless-data-streaming-pipelines-with-amazon-kinesis-data-streams-and-amazon-kinesis-data-firehose-for-amazon-dynamodb-tables/)

## 41.4. S3

1. [Automatically Archive Items to S3 Using DynamoDB Time to Live (TTL) with AWS Lambda and Amazon Kinesis Firehose by Adam Wagner](https://aws.amazon.com/blogs/database/automatically-archive-items-to-s3-using-dynamodb-time-to-live-with-aws-lambda-and-amazon-kinesis-firehose/)

## 41.5. OpenSearch

1. [Indexing Amazon DynamoDB Content with Amazon Elasticsearch Service Using AWS Lambda by Bryan Liston ](https://aws.amazon.com/blogs/compute/indexing-amazon-dynamodb-content-with-amazon-elasticsearch-service-using-aws-lambda/)

## 41.6. Kinesis

1. [Store and stream sports data feeds using Amazon DynamoDB and Amazon Kinesis Data Streams by Nihilson Gnanadason and Pallavi Jain](https://aws.amazon.com/blogs/database/store-and-stream-sports-data-feeds-using-amazon-dynamodb-and-amazon-kinesis-data-streams/)
2. [Deliver DynamoDB records to Amazon S3 using Kinesis Data Streams and Kinesis Data Firehose with AWS CDK](https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/deliver-dynamodb-records-to-amazon-s3-using-kinesis-data-streams-and-kinesis-data-firehose-with-aws-cdk.html)

# 42. CLI

1. [DynamoDB CLI Commands & Query Examples Cheat Sheet By Dynobase](https://dynobase.dev/dynamodb-cli-query-examples/)

# 43. Make Notes

1. [DynamoDB: Its purpose, main features, and key concepts | Jason Hunter | AWS Events](https://www.youtube.com/watch?v=ummOosOW4lE)
 - DynamoDB: Under the hood, managing throughput, advanced design patterns | Jason Hunter | AWS Events](https://www.youtube.com/watch?v=0iGR8GnIItQ)
1. [[Tuning] Scaling DynamoDB: How partitions, hot keys, and split for heat impact performance (Part 3: Summary and best practices) by Jason Hunter and Vivek Natarajan |](https://aws.amazon.com/blogs/database/part-3-scaling-dynamodb-how-partitions-hot-keys-and-split-for-heat-impact-performance/)
- Deciding ideal RCU/WCUs?

# 44. Skillbuilder courses

1. [Amazon DynamoDB - Troubleshooting](https://explore.skillbuilder.aws/learn/course/12617/amazon-dynamodb-troubleshooting)