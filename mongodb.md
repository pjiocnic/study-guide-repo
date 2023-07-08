https://stackoverflow.com/questions/40105958/mongodb-conditional-validation-on-arrays-and-embedded-documents

<!-- TOC -->

- [1. Patterns](#1-patterns)
  - [1.1. Bucket Pattern](#11-bucket-pattern)
  - [1.2. Polymorphism](#12-polymorphism)
- [2. Anti-Patterns](#2-anti-patterns)
- [3. Data Modeling](#3-data-modeling)
- [4. Change Data Streams](#4-change-data-streams)
- [5. Connection Pooling](#5-connection-pooling)
- [6. Spring Data](#6-spring-data)
- [7. JOINs and Aggregations](#7-joins-and-aggregations)
- [8. Versioning](#8-versioning)
- [9. Optimistic Locking](#9-optimistic-locking)
- [10. DocumentDB vs MongoDB](#10-documentdb-vs-mongodb)
- [11. Schema Validation](#11-schema-validation)
- [12. Aggregations](#12-aggregations)
- [13. Tools](#13-tools)
- [14. VSCode](#14-vscode)
- [15. Sample Data](#15-sample-data)
- [16. Books](#16-books)
- [17. Courses](#17-courses)
- [18. Docker](#18-docker)
- [19. Schema Validation](#19-schema-validation)
- [20. Patterns](#20-patterns)
  - [20.1. Polymorphism](#201-polymorphism)

<!-- /TOC -->

# 1. Patterns

1. [Dive into 6 Common MongoDB Patterns With Example](https://medium.com/geekculture/dive-into-6-common-mongodb-patterns-with-examples-3fa7ece34b55)
2. [Data Model Examples and Patterns](https://www.mongodb.com/docs/manual/applications/data-models/)
3. [MongoDB Data Modeling Patterns](https://medium.com/@italoservio/mongodb-data-modeling-patterns-ae2e7a4ff155)

## 1.1. Bucket Pattern

1. [Building with Patterns: The Bucket Pattern](https://www.mongodb.com/blog/post/building-with-patterns-the-bucket-pattern)

## 1.2. Polymorphism

1. [Building with Patterns: The Polymorphic Pattern Ken W. Alger, Daniel Coupal](https://www.mongodb.com/developer/products/mongodb/polymorphic-pattern/)
2. [[START HERE] MongoDB Java Driver for Polymorphism](https://zx77.medium.com/mongodb-java-driver-for-polymorphism-8d8a9e28ec24)
3. [M320: MongoDB Data Modeling](https://learn.mongodb.com/courses/m320-mongodb-data-modeling)
- Polymorphism
4. [All Schema Tutorials from MongoDB](https://www.mongodb.com/developer/products/mongodb/schema/tutorials/)
5. [Building with Patterns: A Summary](https://www.mongodb.com/blog/post/building-with-patterns-a-summary)

# 2. Anti-Patterns

1. [Schema Design Anti-Patterns Series MongoDB](https://www.youtube.com/watch?v=8CZs-0it9r4)
2. [Schema Design Anti-Patterns - Part 1](https://www.youtube.com/watch?v=8CZs-0it9r4)
3. [Schema Design Anti-Patterns - Part 2](https://www.youtube.com/watch?v=mHeP5IbozDU)

# 3. Data Modeling

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

# 4. Change Data Streams

1. [Change Streams & Triggers with Node.js Tutorial](https://www.mongodb.com/developer/languages/javascript/nodejs-change-streams-triggers/)

# 5. Connection Pooling

1. [How to Use MongoDB Connection Pooling on AWS Lambda](https://scalegrid.io/blog/how-to-use-mongodb-connection-pooling-on-aws-lambda/)

# 6. Spring Data

1. [Chap 17 - Working with Spring Data MongoDB @ Java Persistence with Spring Data and Hibernate By Catalin Tudose](https://learning.oreilly.com/library/view/java-persistence-with/9781617299186/OEBPS/Text/17.htm#heading_id_3)

# 7. JOINs and Aggregations

1. [JOINs and Aggregations Using Real-Time Indexing on MongoDB Atlas](https://www.youtube.com/watch?v=6be6aEOHk3w)
2. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 1 of 3) – Introduction](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-1-of-3-introduction)
3. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 2 of 3) – Worked Examples](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-2-of-3-worked-examples)
4. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 3 of 3) – Adding Some Code Glue and Geolocation](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-3-of-3-adding-some-code-glue-and-geolocation)
5. [JOINs and Aggregations Using Real-Time Indexing on MongoDB Atlas By Bhat](https://www.youtube.com/watch?v=6be6aEOHk3w)

# 8. Versioning

1. [Versioning using mongoose-vermongo](https://medium.com/versioning-in-database-mongodb-versioning-vermongo/database-versioning-7cf59a729bb3)

# 9. Optimistic Locking

1. [Optimistic locking in Mongo](https://medium.com/@andris.briedis/optimistic-locking-in-mongo-69fa693864ec)

# 10. DocumentDB vs MongoDB

1. [Amazon DocumentDB Architectural Comparisons](https://www.mongodb.com/compare/documentdb-vs-mongodb/architecture)

# 11. Schema Validation

1. [MongoDB Schema Validation Rules By Panos Zafeiropoulos](https://betterprogramming.pub/mongodb-schema-validation-rules-8a1afc6ea67b)
2. [JSON Schema Validation - Locking down your model the smart way](https://www.mongodb.com/blog/post/json-schema-validation--locking-down-your-model-the-smart-way)
3. [JSON Schema Validation: A Vocabulary for Structural Validation of JSON](http://json-schema.org/draft/2020-12/json-schema-validation.html#name-json-pointers)
4. [Generate JSON Schema from JSON](https://www.jsonschema.net/)
5. [Schema validation from MongoDB docs](https://www.mongodb.com/docs/manual/core/schema-validation/)
6. [Add a choice, conditional, or pattern field from hackolade docs](https://hackolade.com/help/Addachoiceconditionalorpatternfi.html)

# 12. Aggregations

1. [Use a View to Join Two Collections](https://www.mongodb.com/docs/manual/core/views/join-collections-with-view/)

# 13. Tools

1. [Generate JSON Schema from JSON](https://www.jsonschema.net/)

# 14. VSCode

1. [MongoDB for VS Code - MongoDB Developer Tools](https://www.youtube.com/watch?v=MLWlWrRAb4w)

# 15. Sample Data

1. [Sample Data from MongoDB](https://www.mongodb.com/docs/atlas/sample-data/)

# 16. Books

1. [Practical MongoDB Aggregations Book](https://www.practical-mongodb-aggregations.com/front-cover.html)

# 17. Courses

1. [The MongoDB Aggregation Framework (M121)](https://university.mongodb.com/courses/M121/about)
2. [M100: MongoDB for SQL Pros](https://learn.mongodb.com/courses/m100-mongodb-for-sql-pros)
3. [MongoDB CRUD Operations in Java](https://learn.mongodb.com/learn/course/mongodb-crud-operations-in-java/lesson-1-working-with-mongodb-documents-in-java/learn?client=customer)
4. [Using MongoDB with Java](https://learn.mongodb.com/learn/learning-path/using-mongodb-with-java)

# 18. Docker

1. [MongoDB in Docker](https://medium.com/@zzpzaf.se/mongodb-in-docker-bfa77346b389)

# 19. Schema Validation

https://www.percona.com/blog/mongodb-how-to-use-json-schema-validator/
https://hackolade.com/help/Addachoiceconditionalorpatternfi.html
/Volumes/Lexar/git-repos/aws-repo/my-aws-samples/mongodb/mongodb-staging.md
https://json-schema.org/understanding-json-schema/reference/conditionals.html
https://www.mongodb.com/docs/manual/core/schema-validation/

https://www.baeldung.com/introduction-to-json-schema-in-java

https://github.com/everit-org/json-schema
https://json-schema.org/implementations.html
https://github.com/victools/jsonschema-generator

1. [JSON schema validation in Postman using external JSON files](https://medium.com/skillhive/json-schema-validation-in-postman-using-external-json-files-2f3f0741800f)

https://github.com/PacktPublishing/MongoDB---The-Complete-Developer-s-Guide/tree/master

# 20. Patterns

## 20.1. Polymorphism

