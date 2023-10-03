https://stackoverflow.com/questions/40105958/mongodb-conditional-validation-on-arrays-and-embedded-documents

<!-- TOC -->

- [1. AWS](#1-aws)
- [2. Aggregations](#2-aggregations)
- [3. Books](#3-books)
- [4. Courses](#4-courses)
- [5. Comparisons - DocumentDB vs MongoDB](#5-comparisons---documentdb-vs-mongodb)
- [6. Docker](#6-docker)
- [7. IAM](#7-iam)
- [8. Patterns](#8-patterns)
  - [8.1. Bucket Pattern](#81-bucket-pattern)
  - [8.2. Polymorphism](#82-polymorphism)
  - [8.3. Anti-Patterns](#83-anti-patterns)
- [9. Connection Pooling](#9-connection-pooling)
- [10. Change Data Streams](#10-change-data-streams)
- [11. Data Modeling](#11-data-modeling)
- [12. Java](#12-java)
  - [12.1. Docker](#121-docker)
  - [12.2. Spring Data](#122-spring-data)
  - [12.3. 3rd Party](#123-3rd-party)
  - [12.4. Implementing Polymorphism](#124-implementing-polymorphism)
- [13. JOINs and Aggregations](#13-joins-and-aggregations)
- [14. Lambda](#14-lambda)
- [15. Optimistic Locking](#15-optimistic-locking)
- [16. Transactions](#16-transactions)
- [17. Versioning](#17-versioning)
- [18. Schema Validation](#18-schema-validation)
- [20. Sample Data](#20-sample-data)
- [19. Sequence Generation](#19-sequence-generation)
- [Sharding](#sharding)
- [OpsManager](#opsmanager)
- [SQS](#sqs)
- [21. Streams](#21-streams)
- [22. Tools](#22-tools)
- [23. VSCode](#23-vscode)
- [24. Schema Validation](#24-schema-validation)
  - [24.1. Validation using Java](#241-validation-using-java)
  - [24.2. Schema validation for polymorphic schemas](#242-schema-validation-for-polymorphic-schemas)
  - [24.3. Curate](#243-curate)
- [25. Staging](#25-staging)
- [26. Transactions](#26-transactions)
- [27. MongoDB Shows](#27-mongodb-shows)
  - [27.1. [Migrating Stored Procedures to MongoDB](https://www.youtube.com/watch?v=Bog0aIGHG0A) By Vittal Pai @B'lore](#271-migrating-stored-procedures-to-mongodbhttpswwwyoutubecomwatchvbog0aighg0a-by-vittal-pai-blore)
- [28. Curate](#28-curate)
- [29. Sample Data Sets](#29-sample-data-sets)
- [30. Authorization](#30-authorization)

<!-- /TOC -->

# 1. AWS

1. [AWS Marketplace: Best Practices of Running MongoDB Atlas on AWS By cloudAdvisor](https://www.youtube.com/watch?v=Gi7yX2xh_e0)
1. [Relational to NoSQL at Enterprise Scale: Lessons from Amazon](https://www.mongodb.com/blog/post/relational-nosql-enterprise-scale-lessons-amazon)
1. [Using AWS IAM Authentication with MongoDB 4.4 in Atlas to Build Modern Secure Applications](https://www.youtube.com/watch?v=99iV9lCctrU)

# 2. Aggregations

1. [Use a View to Join Two Collections](https://www.mongodb.com/docs/manual/core/views/join-collections-with-view/)

# 3. Books

1. [Practical MongoDB Aggregations Book](https://www.practical-mongodb-aggregations.com/front-cover.html)

# 4. Courses

1. [The MongoDB Aggregation Framework (M121)](https://university.mongodb.com/courses/M121/about)
2. [M100: MongoDB for SQL Pros](https://learn.mongodb.com/courses/m100-mongodb-for-sql-pros)
3. [MongoDB CRUD Operations in Java](https://learn.mongodb.com/learn/course/mongodb-crud-operations-in-java/lesson-1-working-with-mongodb-documents-in-java/learn?client=customer)
4. [Using MongoDB with Java](https://learn.mongodb.com/learn/learning-path/using-mongodb-with-java)

# 5. Comparisons - DocumentDB vs MongoDB

1. [Amazon DocumentDB Architectural Comparisons](https://www.mongodb.com/compare/documentdb-vs-mongodb/architecture)

# 6. Docker

1. [MongoDB in Docker](https://medium.com/@zzpzaf.se/mongodb-in-docker-bfa77346b389)

# 7. IAM

1. [Using AWS IAM Authentication with MongoDB 4.4 in Atlas to Build Modern Secure Applications](https://www.youtube.com/watch?v=99iV9lCctrU)

# 8. Patterns

1. [Dive into 6 Common MongoDB Patterns With Example](https://medium.com/geekculture/dive-into-6-common-mongodb-patterns-with-examples-3fa7ece34b55)
2. [Data Model Examples and Patterns](https://www.mongodb.com/docs/manual/applications/data-models/)
3. [MongoDB Data Modeling Patterns](https://medium.com/@italoservio/mongodb-data-modeling-patterns-ae2e7a4ff155)

## 8.1. Bucket Pattern

1. [Building with Patterns: The Bucket Pattern](https://www.mongodb.com/blog/post/building-with-patterns-the-bucket-pattern)

## 8.2. Polymorphism

1. [Building with Patterns: The Polymorphic Pattern Ken W. Alger, Daniel Coupal](https://www.mongodb.com/developer/products/mongodb/polymorphic-pattern/)
> 1. [[**START-HERE**] MongoDB Java Driver for Polymorphism](https://zx77.medium.com/mongodb-java-driver-for-polymorphism-8d8a9e28ec24)
1. [M320: MongoDB Data Modeling](https://learn.mongodb.com/courses/m320-mongodb-data-modeling)
- Polymorphism
1. [All Schema Tutorials from MongoDB](https://www.mongodb.com/developer/products/mongodb/schema/tutorials/)
1. [Building with Patterns: A Summary](https://www.mongodb.com/blog/post/building-with-patterns-a-summary)
> 1. [[**START-HERE**] Document Validation for Polymorphic Collections By Andrew Morgan](https://www.mongodb.com/developer/products/mongodb/polymorphic-document-validation/)

## 8.3. Anti-Patterns

1. [Schema Design Anti-Patterns Series MongoDB](https://www.youtube.com/watch?v=8CZs-0it9r4)
2. [Schema Design Anti-Patterns - Part 1](https://www.youtube.com/watch?v=8CZs-0it9r4)
3. [Schema Design Anti-Patterns - Part 2](https://www.youtube.com/watch?v=mHeP5IbozDU)

# 9. Connection Pooling

1. [How to Use MongoDB Connection Pooling on AWS Lambda](https://scalegrid.io/blog/how-to-use-mongodb-connection-pooling-on-aws-lambda/)
2. [Manage Connections with AWS Lambda](https://www.mongodb.com/docs/atlas/manage-connections-aws-lambda/)
3. [Use MongoDB Connection Pool on AWS Lambda By Mohaiminul Islam](https://mithulix.medium.com/use-mongodb-connection-pooling-on-aws-lambda-be91038dda92)
4. [How to Use MongoDB Connection Pooling on AWS Lambda](https://devpress.csdn.net/mongodb/62f2030ac6770329307f5cb7.html)

# 10. Change Data Streams

1. [Change Streams & Triggers with Node.js Tutorial](https://www.mongodb.com/developer/languages/javascript/nodejs-change-streams-triggers/)

# 11. Data Modeling

1. [Data Modeling with MongoDB](https://www.youtube.com/watch?v=3GHZd0zv170)
1. [Advanced Data Modeling](https://www.youtube.com/watch?v=qGzJRsaOOO0)
1. [NoSQL Data Modeling for the RDBMS Developer](https://www.youtube.com/watch?v=Y9WGjiSQkt8)
1. [MongoDB Schema Design Best Practices](https://www.youtube.com/watch?v=QAqK-R9HUhc)
1. [Sample DataModels using Hackolade](https://github.com/hackolade/sample-data-models/blob/main/MongoDB/Northwind%20Oracle%20normalized.hck.json)
1. [Schema Patterns - MongoDB - Part 1](https://shanu95.medium.com/schema-patterns-mongodb-part-1-16564f1198dc)
1. [Schema Patterns - MongoDB - Part 2](https://shanu95.medium.com/schema-patterns-mongodb-part-2-73bfabf86c9)
1. [MongoDB schema design](https://medium.com/hackernoon/mongodb-schema-design-86327d8fae83)
1. [DocumentDB Insider Hour | Episode 14 | Data Modeling By Karthik Vijayraghavan](https://www.twitch.tv/videos/1235477994)
1. [DocumentDB Insider Hour | Episode 21 | Document Modeling](https://www.youtube.com/watch?v=Lqjq36GC8Os)
1. [Document modeling with Amazon DocumentDB and Hackolade by Karthik Vijayraghavan and Pascal Desmarets ](https://aws.amazon.com/blogs/database/document-modeling-with-amazon-documentdb-and-hackolade/)
1. [<span style="color:red">[MY NEXT]</span> A Complete Methodology of Data Modeling for MongoDB](https://www.youtube.com/watch?v=DUCvYbcgGsQ)
1. [Data Modeling Introduction By MongoDB Docs](https://www.mongodb.com/docs/manual/core/data-modeling-introduction/)
> 1. [[**MY-NEXT**] Modeling Data for a Car Reservation System By Rick Houlihan](https://www.youtube.com/watch?v=41ZIyAKNtdk)

# 12. Java

## 12.1. Docker

1. [Spring Boot Application with MongoDB in Docker Container](https://www.appsdeveloperblog.com/deploying-spring-boot-mongodb-application-with-docker/)

## 12.2. Spring Data

1. [Introduction to Spring Data MongoDB](https://www.baeldung.com/spring-data-mongodb-tutorial)
1. [Chap 17 - Working with Spring Data MongoDB @ Java Persistence with Spring Data and Hibernate By Catalin Tudose](https://learning.oreilly.com/library/view/java-persistence-with/9781617299186/OEBPS/Text/17.htm#heading_id_3)

## 12.3. 3rd Party
1. [Using Javers](https://javers.org/documentation/spring-boot-integration/)

## 12.4. Implementing Polymorphism

1. [MongoDB Java Driver for Polymorphism](https://zx77.medium.com/mongodb-java-driver-for-polymorphism-8d8a9e28ec24)

# 13. JOINs and Aggregations

1. [JOINs and Aggregations Using Real-Time Indexing on MongoDB Atlas](https://www.youtube.com/watch?v=6be6aEOHk3w)
2. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 1 of 3) – Introduction](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-1-of-3-introduction)
3. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 2 of 3) – Worked Examples](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-2-of-3-worked-examples)
4. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 3 of 3) – Adding Some Code Glue and Geolocation](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-3-of-3-adding-some-code-glue-and-geolocation)
5. [JOINs and Aggregations Using Real-Time Indexing on MongoDB Atlas By Bhat](https://www.youtube.com/watch?v=6be6aEOHk3w)

# 14. Lambda

1. [How to Use MongoDB Connection Pooling on AWS Lambda](https://scalegrid.io/blog/how-to-use-mongodb-connection-pooling-on-aws-lambda/)
2. [Manage Connections with AWS Lambda](https://www.mongodb.com/docs/atlas/manage-connections-aws-lambda/)
3. [AWS Lambda Import JSON messages to MongoDB](https://github.com/udoheld/aws-lambda-import-json-to-mongodb/tree/master)

# 15. Optimistic Locking

1. [Optimistic locking in Mongo](https://medium.com/@andris.briedis/optimistic-locking-in-mongo-69fa693864ec)
1. [Snapshot isolation](https://en.wikipedia.org/wiki/Snapshot_isolation)

# 16. Transactions

1. [Spring Data MongoDB Transactions](https://www.baeldung.com/spring-data-mongodb-transactions)
1. [Spring Data MongoDB - Transaction sample](https://github.com/spring-projects/spring-data-examples/blob/main/mongodb/transactions/README.md)

# 17. Versioning

1. [Versioning using mongoose-vermongo](https://medium.com/versioning-in-database-mongodb-versioning-vermongo/database-versioning-7cf59a729bb3)
1. [Implement Document Versioning Pattern with MongoDB Atlas](https://www.youtube.com/watch?v=BLILsO060gY)
1. [[**MY-NEXT**] How to create document versioning in MongoDB using Spring Boot, REST API. #TechieTaught](https://www.youtube.com/watch?v=wSVsMFGkbOs)
1. [[**MY-NEXT**] Versioning code](https://github.com/rabrath/techietaught/tree/master)

# 18. Schema Validation

1. [MongoDB Schema Validation Rules By Panos Zafeiropoulos](https://betterprogramming.pub/mongodb-schema-validation-rules-8a1afc6ea67b)
2. [JSON Schema Validation - Locking down your model the smart way](https://www.mongodb.com/blog/post/json-schema-validation--locking-down-your-model-the-smart-way)
3. [JSON Schema Validation: A Vocabulary for Structural Validation of JSON](http://json-schema.org/draft/2020-12/json-schema-validation.html#name-json-pointers)
4. [Generate JSON Schema from JSON](https://www.jsonschema.net/)
5. [Schema validation from MongoDB docs](https://www.mongodb.com/docs/manual/core/schema-validation/)
6. [Add a choice, conditional, or pattern field from hackolade docs](https://hackolade.com/help/Addachoiceconditionalorpatternfi.html)

# 20. Sample Data

1. [Sample Data from MongoDB](https://www.mongodb.com/docs/atlas/sample-data/)

# 19. Sequence Generation

1. [MongoDB Auto-Increment](https://www.mongodb.com/basics/mongodb-auto-increment)
1. [Generating Globally Unique Identifiers for Use with MongoDB](https://www.mongodb.com/blog/post/generating-globally-unique-identifiers-for-use-with-mongodb)
1. [Spring Boot and MongoDB Sequence ID Generator](https://examples.javacodegeeks.com/spring-boot-and-mongodb-sequence-id-generator/)
1. https://www.baeldung.com/spring-boot-mongodb-auto-generated-field
1. https://www.slmanju.com/2021/07/design-unique-sequence-with-mongodb.html
1. https://data-flair.training/blogs/mongodb-auto-increment-sequence/
1. https://sujalmandal.medium.com/creating-sequence-id-in-with-mongo-spring-data-4fc3fd20d685

# Sharding

1. [Sharding Introduction](https://www.mongodb.com/docs/v3.0/core/sharding-introduction/)
1. [MongoDB Sharding: Concepts, Examples & Tutorials](https://www.bmc.com/blogs/mongodb-sharding-explained/)
1. [[ MongoDB 7 ] Set up Sharding in MongoDB using Docker containers](https://www.youtube.com/watch?v=7Lp6R4CmuKE)
1. [[ MongoDB 8 ] Adding a shard to sharded cluster](https://www.youtube.com/watch?v=LGERGvEaPW0)
1. [[ MongoDB 9 ] Sharding a MongoDB Collection](https://www.youtube.com/watch?v=Rwg26U0Zs1o)

# OpsManager

1. [[ MongoDB 10 ] MongoDB OpsManager Installation](https://www.youtube.com/watch?v=AHBDcvcbQ9k)
1. [[ MongoDB 11 ] Deploying a Replicaset using OpsManager](https://www.youtube.com/watch?v=Zzbvnpk-OXQ)

# SQS

1. [Improving Our MongoDB Write Throughput with SQS](https://hackernoon.com/improving-our-mongodb-write-throughput-with-sqs)

# 21. Streams

1. [Robust Architecture to populate Data from MongoDB in Real-Time Using Mongo Streams, Event Bridge, SQS Queue and Lambdas](https://www.linkedin.com/pulse/robust-architecture-populate-data-from-mongodb-real-time-soumil-shah/)

# 22. Tools

1. [Generate JSON Schema from JSON](https://www.jsonschema.net/)

# 23. VSCode

1. [MongoDB for VS Code - MongoDB Developer Tools](https://www.youtube.com/watch?v=MLWlWrRAb4w)

# 24. Schema Validation

## 24.1. Validation using Java

1. [Introduction to JSON Schema in Java](https://www.baeldung.com/introduction-to-json-schema-in-java)
1. [JSON schema validation in Postman using external JSON files](https://medium.com/skillhive/json-schema-validation-in-postman-using-external-json-files-2f3f0741800f)

## 24.2. Schema validation for polymorphic schemas

2. [Document Validation for Polymorphic Collections By Andrew Morgan](https://www.mongodb.com/developer/products/mongodb/polymorphic-document-validation/)

## 24.3. Curate

1. https://www.percona.com/blog/mongodb-how-to-use-json-schema-validator/
1. https://hackolade.com/help/Addachoiceconditionalorpatternfi.html
1. /Volumes/Lexar/git-repos/aws-repo/my-aws-samples/mongodb/mongodb-staging.md
1. https://json-schema.org/understanding-json-schema/reference/conditionals.html
1. https://www.mongodb.com/docs/manual/core/schema-validation/
1. https://github.com/everit-org/json-schema
1. https://json-schema.org/implementations.html
1. https://github.com/victools/jsonschema-generator
1. https://github.com/PacktPublishing/MongoDB---The-Complete-Developer-s-Guide/tree/master

# 25. Staging

1. [Relational to NoSQL at Enterprise Scale: Lessons from Amazon By Rick Houlihan](https://www.mongodb.com/blog/post/relational-nosql-enterprise-scale-lessons-amazon)

# 26. Transactions

1. [Transactions course from MongoDB Institute](https://learn.mongodb.com/learn/course/mongodb-transactions/lesson-3-using-transactions-in-mongodb/learn?page=2)
1. [MongoDB 4 Update: Multi-Document ACID Transactions](https://www.mongodb.com/blog/post/mongodb-multi-document-acid-transactions-general-availability)

# 27. MongoDB Shows

## 27.1. [Migrating Stored Procedures to MongoDB](https://www.youtube.com/watch?v=Bog0aIGHG0A) By Vittal Pai @B'lore

1. [Technical Guide: Migrating Stored Procedures to MongoDB](https://www.mongodb.com/collateral/technical-guide-migrating-stored-procedures-to-mongodb)
1. https://github.com/vittalpai/Stored-Procedure-Migration
1. https://landing.mdb.link/migrating-stored-procedures-mongodb

# 28. Curate

1. https://www.mongodb.com/blog/post/generating-globally-unique-identifiers-for-use-with-mongodb
1. https://www.mongodb.com/developer/products/mongodb/polymorphic-document-validation/

# 29. Sample Data Sets

1. https://github.com/neelabalan/mongodb-sample-dataset

# 30. Authorization

1. [Using AWS IAM Authentication with MongoDB 4.4 in Atlas to Build Modern Secure Applications](https://www.youtube.com/watch?v=99iV9lCctrU)

