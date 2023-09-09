https://stackoverflow.com/questions/40105958/mongodb-conditional-validation-on-arrays-and-embedded-documents

<!-- TOC -->

- [1. AWS](#1-aws)
- [2. Patterns](#2-patterns)
  - [2.1. Bucket Pattern](#21-bucket-pattern)
  - [2.2. Polymorphism](#22-polymorphism)
- [3. Anti-Patterns](#3-anti-patterns)
- [4. Data Modeling](#4-data-modeling)
- [5. Change Data Streams](#5-change-data-streams)
- [6. Connection Pooling](#6-connection-pooling)
- [7. Lambda](#7-lambda)
- [8. Spring Data](#8-spring-data)
- [9. Spring boot and docker](#9-spring-boot-and-docker)
- [10. JOINs and Aggregations](#10-joins-and-aggregations)
- [11. Versioning](#11-versioning)
- [12. Optimistic Locking](#12-optimistic-locking)
- [13. DocumentDB vs MongoDB](#13-documentdb-vs-mongodb)
- [14. Schema Validation](#14-schema-validation)
- [15. Aggregations](#15-aggregations)
- [16. Tools](#16-tools)
- [17. VSCode](#17-vscode)
- [18. Sample Data](#18-sample-data)
- [19. Books](#19-books)
- [20. Courses](#20-courses)
- [21. Docker](#21-docker)
- [22. Schema Validation](#22-schema-validation)
  - [22.1. Validation using Java](#221-validation-using-java)
  - [22.2. Schema validation for polymorphic schemas](#222-schema-validation-for-polymorphic-schemas)
  - [22.3. Curate](#223-curate)
- [23. Staging](#23-staging)
- [24. MongoDB Shows](#24-mongodb-shows)
  - [24.1. [Migrating Stored Procedures to MongoDB](https://www.youtube.com/watch?v=Bog0aIGHG0A) By Vittal Pai @B'lore](#241-migrating-stored-procedures-to-mongodbhttpswwwyoutubecomwatchvbog0aighg0a-by-vittal-pai-blore)
- [25. Curate](#25-curate)
- [26. Unique Ids](#26-unique-ids)
- [27. Sequence Generation](#27-sequence-generation)

<!-- /TOC -->

# 1. AWS

1. [AWS Marketplace: Best Practices of Running MongoDB Atlas on AWS By cloudAdvisor](https://www.youtube.com/watch?v=Gi7yX2xh_e0)
1. [Relational to NoSQL at Enterprise Scale: Lessons from Amazon](https://www.mongodb.com/blog/post/relational-nosql-enterprise-scale-lessons-amazon)

# 2. Patterns

1. [Dive into 6 Common MongoDB Patterns With Example](https://medium.com/geekculture/dive-into-6-common-mongodb-patterns-with-examples-3fa7ece34b55)
2. [Data Model Examples and Patterns](https://www.mongodb.com/docs/manual/applications/data-models/)
3. [MongoDB Data Modeling Patterns](https://medium.com/@italoservio/mongodb-data-modeling-patterns-ae2e7a4ff155)

## 2.1. Bucket Pattern

1. [Building with Patterns: The Bucket Pattern](https://www.mongodb.com/blog/post/building-with-patterns-the-bucket-pattern)

## 2.2. Polymorphism

1. [Building with Patterns: The Polymorphic Pattern Ken W. Alger, Daniel Coupal](https://www.mongodb.com/developer/products/mongodb/polymorphic-pattern/)
> 1. [[**START-HERE**] MongoDB Java Driver for Polymorphism](https://zx77.medium.com/mongodb-java-driver-for-polymorphism-8d8a9e28ec24)
1. [M320: MongoDB Data Modeling](https://learn.mongodb.com/courses/m320-mongodb-data-modeling)
- Polymorphism
1. [All Schema Tutorials from MongoDB](https://www.mongodb.com/developer/products/mongodb/schema/tutorials/)
1. [Building with Patterns: A Summary](https://www.mongodb.com/blog/post/building-with-patterns-a-summary)
> 1. [[**START-HERE**]Document Validation for Polymorphic Collections By Andrew Morgan](https://www.mongodb.com/developer/products/mongodb/polymorphic-document-validation/)

# 3. Anti-Patterns

1. [Schema Design Anti-Patterns Series MongoDB](https://www.youtube.com/watch?v=8CZs-0it9r4)
2. [Schema Design Anti-Patterns - Part 1](https://www.youtube.com/watch?v=8CZs-0it9r4)
3. [Schema Design Anti-Patterns - Part 2](https://www.youtube.com/watch?v=mHeP5IbozDU)

# 4. Data Modeling

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

# 5. Change Data Streams

1. [Change Streams & Triggers with Node.js Tutorial](https://www.mongodb.com/developer/languages/javascript/nodejs-change-streams-triggers/)

# 6. Connection Pooling

1. [How to Use MongoDB Connection Pooling on AWS Lambda](https://scalegrid.io/blog/how-to-use-mongodb-connection-pooling-on-aws-lambda/)
2. [Manage Connections with AWS Lambda](https://www.mongodb.com/docs/atlas/manage-connections-aws-lambda/)
3. [Use MongoDB Connection Pool on AWS Lambda By Mohaiminul Islam](https://mithulix.medium.com/use-mongodb-connection-pooling-on-aws-lambda-be91038dda92)
4. [How to Use MongoDB Connection Pooling on AWS Lambda](https://devpress.csdn.net/mongodb/62f2030ac6770329307f5cb7.html)

# 7. Lambda

1. [How to Use MongoDB Connection Pooling on AWS Lambda](https://scalegrid.io/blog/how-to-use-mongodb-connection-pooling-on-aws-lambda/)
2. [Manage Connections with AWS Lambda](https://www.mongodb.com/docs/atlas/manage-connections-aws-lambda/)
3. [AWS Lambda Import JSON messages to MongoDB](https://github.com/udoheld/aws-lambda-import-json-to-mongodb/tree/master)

# 8. Spring Data

1. [Chap 17 - Working with Spring Data MongoDB @ Java Persistence with Spring Data and Hibernate By Catalin Tudose](https://learning.oreilly.com/library/view/java-persistence-with/9781617299186/OEBPS/Text/17.htm#heading_id_3)

# 9. Spring boot and docker

1. [Spring Boot Application with MongoDB in Docker Containe](https://www.appsdeveloperblog.com/deploying-spring-boot-mongodb-application-with-docker/)

# 10. JOINs and Aggregations

1. [JOINs and Aggregations Using Real-Time Indexing on MongoDB Atlas](https://www.youtube.com/watch?v=6be6aEOHk3w)
2. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 1 of 3) – Introduction](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-1-of-3-introduction)
3. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 2 of 3) – Worked Examples](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-2-of-3-worked-examples)
4. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 3 of 3) – Adding Some Code Glue and Geolocation](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-3-of-3-adding-some-code-glue-and-geolocation)
5. [JOINs and Aggregations Using Real-Time Indexing on MongoDB Atlas By Bhat](https://www.youtube.com/watch?v=6be6aEOHk3w)

# 11. Versioning

1. [Versioning using mongoose-vermongo](https://medium.com/versioning-in-database-mongodb-versioning-vermongo/database-versioning-7cf59a729bb3)

# 12. Optimistic Locking

1. [Optimistic locking in Mongo](https://medium.com/@andris.briedis/optimistic-locking-in-mongo-69fa693864ec)

# 13. DocumentDB vs MongoDB

1. [Amazon DocumentDB Architectural Comparisons](https://www.mongodb.com/compare/documentdb-vs-mongodb/architecture)

# 14. Schema Validation

1. [MongoDB Schema Validation Rules By Panos Zafeiropoulos](https://betterprogramming.pub/mongodb-schema-validation-rules-8a1afc6ea67b)
2. [JSON Schema Validation - Locking down your model the smart way](https://www.mongodb.com/blog/post/json-schema-validation--locking-down-your-model-the-smart-way)
3. [JSON Schema Validation: A Vocabulary for Structural Validation of JSON](http://json-schema.org/draft/2020-12/json-schema-validation.html#name-json-pointers)
4. [Generate JSON Schema from JSON](https://www.jsonschema.net/)
5. [Schema validation from MongoDB docs](https://www.mongodb.com/docs/manual/core/schema-validation/)
6. [Add a choice, conditional, or pattern field from hackolade docs](https://hackolade.com/help/Addachoiceconditionalorpatternfi.html)

# 15. Aggregations

1. [Use a View to Join Two Collections](https://www.mongodb.com/docs/manual/core/views/join-collections-with-view/)

# 16. Tools

1. [Generate JSON Schema from JSON](https://www.jsonschema.net/)

# 17. VSCode

1. [MongoDB for VS Code - MongoDB Developer Tools](https://www.youtube.com/watch?v=MLWlWrRAb4w)

# 18. Sample Data

1. [Sample Data from MongoDB](https://www.mongodb.com/docs/atlas/sample-data/)

# 19. Books

1. [Practical MongoDB Aggregations Book](https://www.practical-mongodb-aggregations.com/front-cover.html)

# 20. Courses

1. [The MongoDB Aggregation Framework (M121)](https://university.mongodb.com/courses/M121/about)
2. [M100: MongoDB for SQL Pros](https://learn.mongodb.com/courses/m100-mongodb-for-sql-pros)
3. [MongoDB CRUD Operations in Java](https://learn.mongodb.com/learn/course/mongodb-crud-operations-in-java/lesson-1-working-with-mongodb-documents-in-java/learn?client=customer)
4. [Using MongoDB with Java](https://learn.mongodb.com/learn/learning-path/using-mongodb-with-java)

# 21. Docker

1. [MongoDB in Docker](https://medium.com/@zzpzaf.se/mongodb-in-docker-bfa77346b389)

# 22. Schema Validation

## 22.1. Validation using Java

1. [Introduction to JSON Schema in Java](https://www.baeldung.com/introduction-to-json-schema-in-java)
1. [JSON schema validation in Postman using external JSON files](https://medium.com/skillhive/json-schema-validation-in-postman-using-external-json-files-2f3f0741800f)

## 22.2. Schema validation for polymorphic schemas

2. [Document Validation for Polymorphic Collections By Andrew Morgan](https://www.mongodb.com/developer/products/mongodb/polymorphic-document-validation/)

## 22.3. Curate

1. https://www.percona.com/blog/mongodb-how-to-use-json-schema-validator/
1. https://hackolade.com/help/Addachoiceconditionalorpatternfi.html
1. /Volumes/Lexar/git-repos/aws-repo/my-aws-samples/mongodb/mongodb-staging.md
1. https://json-schema.org/understanding-json-schema/reference/conditionals.html
1. https://www.mongodb.com/docs/manual/core/schema-validation/
1. https://github.com/everit-org/json-schema
1. https://json-schema.org/implementations.html
1. https://github.com/victools/jsonschema-generator
1. https://github.com/PacktPublishing/MongoDB---The-Complete-Developer-s-Guide/tree/master

# 23. Staging

1. [Relational to NoSQL at Enterprise Scale: Lessons from Amazon By Rick Houlihan](https://www.mongodb.com/blog/post/relational-nosql-enterprise-scale-lessons-amazon)

# 24. MongoDB Shows

## 24.1. [Migrating Stored Procedures to MongoDB](https://www.youtube.com/watch?v=Bog0aIGHG0A) By Vittal Pai @B'lore

1. [Technical Guide: Migrating Stored Procedures to MongoDB](https://www.mongodb.com/collateral/technical-guide-migrating-stored-procedures-to-mongodb)
1. https://github.com/vittalpai/Stored-Procedure-Migration
1. https://landing.mdb.link/migrating-stored-procedures-mongodb

# 25. Curate

1. https://www.mongodb.com/blog/post/generating-globally-unique-identifiers-for-use-with-mongodb
1. https://www.mongodb.com/developer/products/mongodb/polymorphic-document-validation/

# 26. Unique Ids

1. [Generating Globally Unique Identifiers for Use with MongoDB](https://www.mongodb.com/blog/post/generating-globally-unique-identifiers-for-use-with-mongodb)

# 27. Sequence Generation

1.[Spring Boot and MongoDB Sequence ID Generator](https://examples.javacodegeeks.com/spring-boot-and-mongodb-sequence-id-generator/)
1. https://www.baeldung.com/spring-boot-mongodb-auto-generated-field