
<H1> DocumentDB Backlog</H1>

<!-- TOC -->

- [1.1. Athena integration](#11-athena-integration)
- [1.2. Scaling](#12-scaling)
- [1.3. Backups](#13-backups)
- [1.4. Lambda](#14-lambda)
- [1.5. Change Streams](#15-change-streams)
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
  - [1.20.1. IDE](#1201-ide)
  - [1.20.2. Connecting from outside VPC](#1202-connecting-from-outside-vpc)
- [1.21. Elastic Clusters](#121-elastic-clusters)
- [1.22. Integrations](#122-integrations)
  - [1.22.1. Opensearch Integration using Change Streams](#1221-opensearch-integration-using-change-streams)
  - [1.22.2. MSK Integration using Change Streams](#1222-msk-integration-using-change-streams)

<!-- /TOC -->

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

## 1.5. Change Streams

1. [Capture changes from Amazon DocumentDB via AWS Lambda and publish them to Amazon MSK by Murat Balkan](https://aws.amazon.com/blogs/database/capture-changes-from-amazon-documentdb-via-aws-lambda-and-publish-them-to-amazon-msk/)
2. [Archiving Data with Amazon DocumentDB Change Streams](https://catalog.us-east-1.prod.workshops.aws/workshops/464d6c17-9faa-4fef-ac9f-dd49610174d3/en-US/change-streams)

## 1.6. Indexing

1. [How to index on Amazon DocumentDB (with MongoDB compatibility) by Cody Allen ](https://aws.amazon.com/blogs/database/how-to-index-on-amazon-documentdb-with-mongodb-compatibility/)

## 1.7. Development

1. [Building resilient applications with Amazon DocumentDB (with MongoDB compatibility), Part 1: Client configuration by Karthik Vijayraghavan](https://aws.amazon.com/blogs/database/building-resilient-applications-with-amazon-documentdb-with-mongodb-compatibility-part-1-client-configuration/)
2. [Building resilient applications with Amazon DocumentDB (with MongoDB compatibility), Part 2: Exception handling by Karthik Vijayraghavan](https://aws.amazon.com/blogs/database/building-resilient-applications-with-amazon-documentdb-with-mongodb-compatibility-part-2-exception-handling/)
3. [Get started with the Amazon DocumentDB JDBC driver by Douglas Bonser ](https://aws.amazon.com/blogs/database/get-started-with-the-amazon-documentdb-jdbc-driver/)

## 1.8. JDBC

1. [Get started with the Amazon DocumentDB JDBC driver by Douglas Bonser](https://aws.amazon.com/blogs/database/get-started-with-the-amazon-documentdb-jdbc-driver/)

## 1.9. IAM

1. [Introducing Amazon DocumentDB (with MongoDB compatibility) user-defined roles for access control by Tim Callaghan](https://aws.amazon.com/blogs/database/introducing-amazon-documentdb-with-mongodb-compatibility-user-defined-roles-for-access-control/)

## 1.10. AWS Config

1. [[MY NEXT] Evaluate Amazon DocumentDB (with MongoDB compatibility) configurations using AWS Config by Jesus Bernal and Karthik Vijayraghavan](https://aws.amazon.com/blogs/database/evaluate-amazon-documentdb-with-mongodb-compatibility-configurations-using-aws-config/)

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
3. [[MY NEXT] Using Transactions with Amazon DocumentDB (with MongoDB compatibility) 4.0 - AWS Online Tech Talks](https://www.youtube.com/watch?v=5m_jRQvOo_I)

## 1.18. Troubleshooting

1. [Profiling slow-running queries in Amazon DocumentDB (with MongoDB compatibility) by Meet Bhagdev](https://aws.amazon.com/blogs/database/profiling-slow-running-queries-in-amazon-documentdb-with-mongodb-compatibility/ )
2. [Monitoring metrics and setting up alarms on your Amazon DocumentDB (with MongoDB compatibility) clusters by Ryan Thurston](https://aws.amazon.com/blogs/database/monitoring-metrics-and-setting-up-alarms-on-your-amazon-documentdb-with-mongodb-compatibility-clusters/)

## 1.19. Cost

1. [Using cost allocation tags with Amazon DocumentDB (with MongoDB compatibility) by Joseph Idziorek ](https://aws.amazon.com/blogs/database/using-cost-allocation-tags-with-amazon-documentdb-with-mongodb-compatibility/)
2. [Optimizing for cost with Amazon DocumentDB (with MongoDB compatibility) by Joseph Idziorek](https://aws.amazon.com/blogs/database/optimizing-for-cost-with-amazon-documentdb-with-mongodb-compatibility/)

## 1.20. Tools

### 1.20.1. IDE

1. [Studio 3T](https://docs.aws.amazon.com/documentdb/latest/developerguide/studio3t.html)

### 1.20.2. Connecting from outside VPC

1. [Connecting to an Amazon DocumentDB Cluster from Outside an Amazon VPC](https://docs.aws.amazon.com/documentdb/latest/developerguide/connect-from-outside-a-vpc.html)

## 1.21. Elastic Clusters

1. [Announcing Amazon DocumentDB Elastic Clusters by Veliswa Boya](https://aws.amazon.com/blogs/aws/announcing-amazon-documentdb-elastic-clusters/)
2. [Amazon DocumentDB elastic clusters: how it works](https://docs.aws.amazon.com/documentdb/latest/developerguide/elastic-how-it-works.html)
3. [Getting Started with Amazon DocumentDB Elastic Clusters- AWS Database in 15 By Vin Yu](https://www.youtube.com/watch?v=6uVb35pd-mA)
4. [Choose shard keys to optimize Amazon DocumentDB Elastic Clusters by Jason Dalba ](https://aws.amazon.com/blogs/database/choose-shard-keys-to-optimize-amazon-documentdb-elastic-clusters/)
5. [Elastic Scaling wtith Amazon DocumentDB- AWS Virtual Workshop By Vin Yu and Pratik Das](https://www.youtube.com/watch?v=I5yIZKx1ctM)

## 1.22. Integrations

### 1.22.1. Opensearch Integration using Change Streams

1. [Run full text search queries on Amazon DocumentDB (with MongoDB compatibility) data with Amazon OpenSearch Service by Meet Bhagdev, Herbert Gomez, and Vijay Injam](https://aws.amazon.com/blogs/database/run-full-text-search-queries-on-amazon-documentdb-data-with-amazon-elasticsearch-service/)
- CFN that launches: Amazon DocumentDB cluster, Amazon ES domain, AWS Secrets Manager secret, Amazon SNS trigger, EventBridge rule, Enable change streams (CS) for DocDB, Lambda replicates from CS to ES

### 1.22.2. MSK Integration using Change Streams

1. [Capture changes from Amazon DocumentDB via AWS Lambda and publish them to Amazon MSK by Murat Balkan](https://aws.amazon.com/blogs/database/capture-changes-from-amazon-documentdb-via-aws-lambda-and-publish-them-to-amazon-msk/)
- Includes CFN
2. [Building Dynamic Data Pipes Using AWS DocumentDB, MSK and Lambda](https://medium.com/cisco-fpie/taming-of-the-queue-f320f855a09)