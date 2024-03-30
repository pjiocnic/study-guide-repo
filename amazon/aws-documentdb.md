
<H1> DocumentDB Backlog</H1>

<!-- TOC -->

- [1. Curate](#1-curate)
- [2. Archiving](#2-archiving)
- [3. Athena integration](#3-athena-integration)
- [4. AWS Config](#4-aws-config)
- [5. Backups](#5-backups)
- [6. Caching](#6-caching)
- [7. Change Streams](#7-change-streams)
- [8. Connection Pooling](#8-connection-pooling)
- [9. Cost](#9-cost)
- [10. Data Modeling](#10-data-modeling)
- [11. Development](#11-development)
- [12. ECS](#12-ecs)
- [13. Elastic Clusters](#13-elastic-clusters)
- [14. Lambda](#14-lambda)
- [15. IAM](#15-iam)
- [16. Indexing](#16-indexing)
- [17. JDBC](#17-jdbc)
- [18. Java](#18-java)
- [19. Scaling](#19-scaling)
- [20. Spring Boot](#20-spring-boot)
- [21. Sharding](#21-sharding)
- [22. Transactions](#22-transactions)
- [23. Troubleshooting](#23-troubleshooting)
- [24. Tools](#24-tools)
  - [24.1. IDE](#241-ide)
  - [24.2. Connecting from outside VPC](#242-connecting-from-outside-vpc)
  - [24.3. Choosing Shard keys](#243-choosing-shard-keys)
- [25. Integrations](#25-integrations)
  - [25.1. Opensearch Integration using Change Streams](#251-opensearch-integration-using-change-streams)
  - [25.2. MSK Integration using Change Streams](#252-msk-integration-using-change-streams)
  - [25.3. S3](#253-s3)
  - [25.4. ECS](#254-ecs)
- [26. Videos](#26-videos)

<!-- /TOC -->

# 1. Curate

1. [Introducing DML auditing for Amazon DocumentDB (with MongoDB compatibility) by Kaarthiik Thota and Dipti Ranjan Sahoo](https://aws.amazon.com/blogs/database/introducing-dml-auditing-for-amazon-documentdb-with-mongodb-compatibility/)
2. [Archive data from Amazon DocumentDB (with MongoDB compatibility) to Amazon S3 by Mark Mulligan and Karthik Vijayraghavan](https://aws.amazon.com/blogs/database/archive-data-from-amazon-documentdb-with-mongodb-compatibility-to-amazon-s3/)
3. [Deploy a containerized application with Amazon ECS and connect to Amazon DocumentDB (with MongoDB compatibility) securely by Hidenori Koizumi ](https://aws.amazon.com/blogs/database/deploy-a-containerized-application-with-amazon-ecs-and-connect-to-amazon-documentdb-securely/)
4. [Creating a REST API for Amazon DocumentDB (with MongoDB compatibility) with Amazon API Gateway and AWS Lambda](https://aws.amazon.com/blogs/database/creating-a-rest-api-for-amazon-documentdb-with-mongodb-compatibility-with-amazon-api-gateway-and-aws-lambda/)
5. [Monitoring metrics and setting up alarms on your Amazon DocumentDB (with MongoDB compatibility) clusters](https://aws.amazon.com/blogs/database/monitoring-metrics-and-setting-up-alarms-on-your-amazon-documentdb-with-mongodb-compatibility-clusters/)
6. [Profiling slow-running queries in Amazon DocumentDB (with MongoDB compatibility)](https://aws.amazon.com/blogs/database/profiling-slow-running-queries-in-amazon-documentdb-with-mongodb-compatibility/)

# 2. Archiving

1. [Archive data from Amazon DocumentDB (with MongoDB compatibility) to Amazon S3 by Mark Mulligan and Karthik Vijayraghavan](https://aws.amazon.com/blogs/database/archive-data-from-amazon-documentdb-with-mongodb-compatibility-to-amazon-s3/)

# 3. Athena integration

1. [Run queries with Amazon Athena on Amazon DocumentDB](https://catalog.us-east-1.prod.workshops.aws/workshops/464d6c17-9faa-4fef-ac9f-dd49610174d3/en-US/change-streams/querydocdbwithathena)

# 4. AWS Config

1. [[MY NEXT] Evaluate Amazon DocumentDB (with MongoDB compatibility) configurations using AWS Config by Jesus Bernal and Karthik Vijayraghavan](https://aws.amazon.com/blogs/database/evaluate-amazon-documentdb-with-mongodb-compatibility-configurations-using-aws-config/)

# 5. Backups

1. [Backing up data with Amazon DocumentDB (with MongoDB compatibility) by Joseph Idziorek](https://aws.amazon.com/blogs/database/backing-up-data-with-amazon-documentdb-with-mongodb-compatibility/)

# 6. Caching

1. [Caching for performance with Amazon DocumentDB and Amazon ElastiCache by Georges Leschener](https://aws.amazon.com/blogs/database/caching-for-performance-with-amazon-documentdb-and-amazon-elasticache/)

# 7. Change Streams

1. [Capture changes from Amazon DocumentDB via AWS Lambda and publish them to Amazon MSK by Murat Balkan](https://aws.amazon.com/blogs/database/capture-changes-from-amazon-documentdb-via-aws-lambda-and-publish-them-to-amazon-msk/)
2. [Archiving Data with Amazon DocumentDB Change Streams](https://catalog.us-east-1.prod.workshops.aws/workshops/464d6c17-9faa-4fef-ac9f-dd49610174d3/en-US/change-streams)
3. [Stream data from Amazon DocumentDB to Amazon Kinesis Data Firehose using AWS Lambda by Anshu Vajpayee and Sourav Biswas](https://aws.amazon.com/blogs/database/stream-data-from-amazon-documentdb-to-amazon-kinesis-data-firehose-using-aws-lambda/)
4. [DocumentDB Insider Hour | Episode 27 | Lambda Event Source Mapping](https://www.youtube.com/watch?v=U8z0To80crY)

# 8. Connection Pooling

1. [[MY NEXT] Serverless DocumentDB Connection Caching Service — Part 1 By Serverless Advocate](https://towardsaws.com/serverless-documentdb-connection-caching-service-part-1-23db3a3df6dc)
5. [[**MUST-SEE**] GitHub Examples by Mihai Aldolu talks about pooling](https://www.twitch.tv/videos/1936506159)
<img src="./images/docdb-lambda.png" title="Connect Lambda to DocumentDB" width="900"/>
- https://github.com/aws-samples/amazon-documentdb-samples/tree/master/lambda-samples

# 9. Cost

1. [Using cost allocation tags with Amazon DocumentDB (with MongoDB compatibility) by Joseph Idziorek ](https://aws.amazon.com/blogs/database/using-cost-allocation-tags-with-amazon-documentdb-with-mongodb-compatibility/)
2. [Optimizing for cost with Amazon DocumentDB (with MongoDB compatibility) by Joseph Idziorek](https://aws.amazon.com/blogs/database/optimizing-for-cost-with-amazon-documentdb-with-mongodb-compatibility/)
3. [Five Ways to Optimize for Cost with Amazon DocumentDB](https://aws.amazon.com/documentdb/resources/video-five-ways-optimize-cost/)
4. [Optimize AWS DocumentDB Cost and Performance with AWS Graviton2](https://awsmadeeasy.com/blog/aws-documentdb-to-aws-graviton2/)
5. [Amazon DocumentDB (with MongoDB compatibility) pricing](https://aws.amazon.com/documentdb/pricing/)

# 10. Data Modeling

1. [Introduction to data modeling with Amazon DocumentDB (with MongoDB compatibility) for relational database users by Sameer Kumar](https://aws.amazon.com/blogs/database/introduction-to-data-modeling-with-amazon-documentdb-with-mongodb-compatibility-for-relational-database-users/)
2. [Document modeling with Amazon DocumentDB and Hackolade by Karthik Vijayraghavan and Pascal Desmarets](https://aws.amazon.com/blogs/database/document-modeling-with-amazon-documentdb-and-hackolade/)
3. [[MY NEXT] Migrating relational databases to Amazon DocumentDB (with MongoDB compatibility) by Ganesh Sawhney](https://aws.amazon.com/blogs/database/migrating-relational-databases-to-amazon-documentdb-with-mongodb-compatibility/)
4. [[MY NEXT] DocumentDB Insider Hour | Episode 21 | Document Modeling](https://www.youtube.com/watch?v=Lqjq36GC8Os)
5. [Data Modeling Methodology for Amazon DocumentDB- AWS Virtual Workshop By Mihai Aldoiu and Jason Plank](https://www.youtube.com/watch?v=Y87DVChZfwk&t=3s&pp=ygUpYXdzIGRvY3VtZW50ZGIgbWloYWkgYWxkb2x1IGRhdGEgbW9kZWxpbmc%3D)

# 11. Development

1. [Building resilient applications with Amazon DocumentDB (with MongoDB compatibility), Part 1: Client configuration by Karthik Vijayraghavan](https://aws.amazon.com/blogs/database/building-resilient-applications-with-amazon-documentdb-with-mongodb-compatibility-part-1-client-configuration/)
2. [Building resilient applications with Amazon DocumentDB (with MongoDB compatibility), Part 2: Exception handling by Karthik Vijayraghavan](https://aws.amazon.com/blogs/database/building-resilient-applications-with-amazon-documentdb-with-mongodb-compatibility-part-2-exception-handling/)
3. [Get started with the Amazon DocumentDB JDBC driver by Douglas Bonser ](https://aws.amazon.com/blogs/database/get-started-with-the-amazon-documentdb-jdbc-driver/)
4. [Connecting to an Amazon DocumentDB Cluster from Outside an Amazon VPC](https://docs.aws.amazon.com/documentdb/latest/developerguide/connect-from-outside-a-vpc.html)

# 12. ECS

1. [Deploy a containerized application with Amazon ECS and connect to Amazon DocumentDB (with MongoDB compatibility) securely by Hidenori Koizumi](https://aws.amazon.com/blogs/database/deploy-a-containerized-application-with-amazon-ecs-and-connect-to-amazon-documentdb-securely/)

# 13. Elastic Clusters

1. [Announcing Amazon DocumentDB Elastic Clusters by Veliswa Boya](https://aws.amazon.com/blogs/aws/announcing-amazon-documentdb-elastic-clusters/)
2. [Amazon DocumentDB elastic clusters: how it works](https://docs.aws.amazon.com/documentdb/latest/developerguide/elastic-how-it-works.html)
3. [Getting Started with Amazon DocumentDB Elastic Clusters- AWS Database in 15 By Vin Yu](https://www.youtube.com/watch?v=6uVb35pd-mA)
5. [Elastic Scaling wtith Amazon DocumentDB- AWS Virtual Workshop By Vin Yu and Pratik Das](https://www.youtube.com/watch?v=I5yIZKx1ctM)

# 14. Lambda

1. [Running AWS Lambda-based applications with Amazon DocumentDB by Raj Chilakapati and Gowri Balasubramanian](https://aws.amazon.com/blogs/database/running-aws-lambda-based-applications-with-amazon-documentdb/)
2. [Creating a REST API for Amazon DocumentDB (with MongoDB compatibility) with Amazon API Gateway and AWS Lambda by Brian Hess](https://aws.amazon.com/blogs/database/creating-a-rest-api-for-amazon-documentdb-with-mongodb-compatibility-with-amazon-api-gateway-and-aws-labda/)
3. [Using AWS Lambda with Amazon DocumentDB](https://docs.aws.amazon.com/lambda/latest/dg/with-documentdb.html)
> 4. [[**MUST-SEE**] Tutorial: Using AWS Lambda with Amazon DocumentDB Streams](https://docs.aws.amazon.com/lambda/latest/dg/with-documentdb-tutorial.html#docdb-create-interface-vpc-endpoints)
5. [[**MUST-SEE**] DocumentDB Insider Hour | Episode 30 | Using AWS Lambda with Amazon DocumentDB By Mihai Aldolu](https://www.twitch.tv/videos/1936506159)
<img src="./images/docdb-lambda.png" title="Connect Lambda to DocumentDB" width="900"/>
- https://github.com/aws-samples/amazon-documentdb-samples/tree/master/lambda-samples

# 15. IAM

1. [Introducing Amazon DocumentDB (with MongoDB compatibility) user-defined roles for access control by Tim Callaghan](https://aws.amazon.com/blogs/database/introducing-amazon-documentdb-with-mongodb-compatibility-user-defined-roles-for-access-control/)

# 16. Indexing

1. [How to index on Amazon DocumentDB (with MongoDB compatibility) by Cody Allen ](https://aws.amazon.com/blogs/database/how-to-index-on-amazon-documentdb-with-mongodb-compatibility/)

# 17. JDBC

1. [[MY NEXT] Get started with the Amazon DocumentDB JDBC driver by Douglas Bonser](https://aws.amazon.com/blogs/database/get-started-with-the-amazon-documentdb-jdbc-driver/)
- https://github.com/aws/amazon-documentdb-jdbc-driver/tree/develop

# 18. Java

1. [Samples to show amazon-documentdb-jdbc-driver usage](https://github.com/aws/amazon-documentdb-jdbc-driver/blob/develop/src/main/java/software/amazon/documentdb/jdbc/persist/DocumentDbSchemaReader.Java)

# 19. Scaling

1. [Scaling Amazon DocumentDB (with MongoDB compatibility), Part 1: Scaling reads by Leonid Koren and Jeff Duffy](https://aws.amazon.com/blogs/database/scaling-amazon-documentdb-with-mongodb-compatibility-part-1-scaling-reads/)
2. [Amazon DocumentDB (with MongoDB compatibility) read autoscaling by Randy DeFauw](https://aws.amazon.com/blogs/database/amazon-documentdb-with-mongodb-compatibility-read-autoscaling/)
3. https://github.com/aws-samples/amazon-documentdb-integration-with-application-autoscaler
4. https://docs.aws.amazon.com/documentdb/latest/developerguide/db-cluster-manage-performance.html#db-cluster-manage-scaling-instance
5. [New Amazon DocumentDB features for aggregations, arrays, and indexing by Joseph Idziorek](https://aws.amazon.com/blogs/database/new-amazon-documentdb-features-for-aggregations-arrays-and-indexing/)

# 20. Spring Boot

1. [Integrate your Spring Boot application with Amazon DocumentDB (with MongoDB compatibility) by Gururaj S Bayari ](https://aws.amazon.com/blogs/database/integrate-your-spring-boot-application-with-amazon-documentdb/)

# 21. Sharding

1. [Choose shard keys to optimize Amazon DocumentDB Elastic Clusters by Jason Dalba](https://aws.amazon.com/blogs/database/choose-shard-keys-to-optimize-amazon-documentdb-elastic-clusters/)
2. [Perform a live migration from a sharded document database cluster to Amazon DocumentDB by Gururaj S Bayari and Anshu Vajpayee](https://aws.amazon.com/blogs/database/perform-a-live-migration-from-a-sharded-document-database-cluster-to-amazon-documentdb/)

# 22. Transactions

1. [Introducing transactions in Amazon DocumentDB (with MongoDB compatibility) by Joseph Idziorek](https://aws.amazon.com/blogs/database/introducing-transactions-in-amazon-documentdb-with-mongodb-compatibility/)
2. [Introducing MongoDB 4.0 compatibility and Transactions in Amazon DocumentDB by Joseph Idziorek ](https://aws.amazon.com/blogs/database/introducing-amazon-documentdb-with-mongodb-compatibility-4-0/)
3. [[MY NEXT] Using Transactions with Amazon DocumentDB (with MongoDB compatibility) 4.0 - AWS Online Tech Talks](https://www.youtube.com/watch?v=5m_jRQvOo_I)

# 23. Troubleshooting

1. [Profiling slow-running queries in Amazon DocumentDB (with MongoDB compatibility) by Meet Bhagdev](https://aws.amazon.com/blogs/database/profiling-slow-running-queries-in-amazon-documentdb-with-mongodb-compatibility/ )
2. [Monitoring metrics and setting up alarms on your Amazon DocumentDB (with MongoDB compatibility) clusters by Ryan Thurston](https://aws.amazon.com/blogs/database/monitoring-metrics-and-setting-up-alarms-on-your-amazon-documentdb-with-mongodb-compatibility-clusters/)

# 24. Tools

## 24.1. IDE

1. [Studio 3T](https://docs.aws.amazon.com/documentdb/latest/developerguide/studio3t.html)
2. [DbVisualizer](https://www.dbvis.com/download/)
- see https://aws.amazon.com/blogs/database/get-started-with-the-amazon-documentdb-jdbc-driver/

## 24.2. Connecting from outside VPC

1. [Connecting to an Amazon DocumentDB Cluster from Outside an Amazon VPC](https://docs.aws.amazon.com/documentdb/latest/developerguide/connect-from-outside-a-vpc.html)

## 24.3. Choosing Shard keys

1. [Choose shard keys to optimize Amazon DocumentDB Elastic Clusters by Jason Dalba](https://aws.amazon.com/blogs/database/choose-shard-keys-to-optimize-amazon-documentdb-elastic-clusters/)

# 25. Integrations

## 25.1. Opensearch Integration using Change Streams

1. [Run full text search queries on Amazon DocumentDB (with MongoDB compatibility) data with Amazon OpenSearch Service by Meet Bhagdev, Herbert Gomez, and Vijay Injam](https://aws.amazon.com/blogs/database/run-full-text-search-queries-on-amazon-documentdb-data-with-amazon-elasticsearch-service/)
- CFN that launches: Amazon DocumentDB cluster, Amazon ES domain, AWS Secrets Manager secret, Amazon SNS trigger, EventBridge rule, Enable change streams (CS) for DocDB, Lambda replicates from CS to ES

## 25.2. MSK Integration using Change Streams

1. [Capture changes from Amazon DocumentDB via AWS Lambda and publish them to Amazon MSK by Murat Balkan](https://aws.amazon.com/blogs/database/capture-changes-from-amazon-documentdb-via-aws-lambda-and-publish-them-to-amazon-msk/)
- [amazon-documentdb-streams-msk.yaml](./templates/documentdb/amazon-documentdb-streams-msk.yaml)
2. [Building Dynamic Data Pipes Using AWS DocumentDB, MSK and Lambda](https://medium.com/cisco-fpie/taming-of-the-queue-f320f855a09)

## 25.3. S3

1. [Stream data from Amazon DocumentDB to Amazon Kinesis Data Firehose using AWS Lambda by Anshu Vajpayee and Sourav Biswas](https://aws.amazon.com/blogs/database/stream-data-from-amazon-documentdb-to-amazon-kinesis-data-firehose-using-aws-lambda/)

## 25.4. ECS

1. [Deploy a containerized application with Amazon ECS and connect to Amazon DocumentDB (with MongoDB compatibility) securely by Hidenori Koizumi)](https://aws.amazon.com/blogs/database/deploy-a-containerized-application-with-amazon-ecs-and-connect-to-amazon-documentdb-securely/)


# 26. Videos

1. [The Big Dev Theory S3 | Ep. 3| Why it Matters? Modeling Documents with Mongo DB](https://www.twitch.tv/videos/1899430446)