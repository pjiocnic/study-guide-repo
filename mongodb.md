https://stackoverflow.com/questions/40105958/mongodb-conditional-validation-on-arrays-and-embedded-documents

<!-- TOC -->

- [1. Patterns](#1-patterns)
- [2. Anti-Patterns](#2-anti-patterns)
- [3. Data Modeling](#3-data-modeling)
- [4. Change Data Streams](#4-change-data-streams)
- [5. JOINs and Aggregations](#5-joins-and-aggregations)
- [6. Versioning](#6-versioning)
- [7. Optimistic Locking](#7-optimistic-locking)
- [8. DocumentDB vs MongoDB](#8-documentdb-vs-mongodb)
- [9. Schema Validation](#9-schema-validation)
- [10. Aggregations](#10-aggregations)
- [11. Tools](#11-tools)
- [12. VSCode](#12-vscode)
- [13. Sample Data](#13-sample-data)
- [14. Books](#14-books)
- [15. Courses](#15-courses)
- [16. Docker](#16-docker)
- [17. Schema Validation](#17-schema-validation)

<!-- /TOC -->

# 1. Patterns

1. [Building with Patterns: The Polymorphic Pattern Ken W. Alger, Daniel Coupal](https://www.mongodb.com/developer/products/mongodb/polymorphic-pattern/)
2. [Dive into 6 Common MongoDB Patterns With Example](https://medium.com/geekculture/dive-into-6-common-mongodb-patterns-with-examples-3fa7ece34b55)
3. [Data Model Examples and Patterns](https://www.mongodb.com/docs/manual/applications/data-models/)
4. [MongoDB Data Modeling Patterns](https://medium.com/@italoservio/mongodb-data-modeling-patterns-ae2e7a4ff155)
5. [M320: MongoDB Data Modeling](https://learn.mongodb.com/courses/m320-mongodb-data-modeling)
- Polymorphism
6. [Building with Patterns: The Bucket Pattern](https://www.mongodb.com/blog/post/building-with-patterns-the-bucket-pattern)
7. [All Schema Tutorials from MongoDB](https://www.mongodb.com/developer/products/mongodb/schema/tutorials/)
8. [Building with Patterns: A Summary](https://www.mongodb.com/blog/post/building-with-patterns-a-summary)

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

# 5. JOINs and Aggregations

1. [JOINs and Aggregations Using Real-Time Indexing on MongoDB Atlas](https://www.youtube.com/watch?v=6be6aEOHk3w)
2. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 1 of 3) – Introduction](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-1-of-3-introduction)
3. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 2 of 3) – Worked Examples](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-2-of-3-worked-examples)
4. [Joins and Other Aggregation Enhancements Coming in MongoDB 3.2 (Part 3 of 3) – Adding Some Code Glue and Geolocation](https://www.mongodb.com/blog/post/joins-and-other-aggregation-enhancements-coming-in-mongodb-3-2-part-3-of-3-adding-some-code-glue-and-geolocation)
5. [JOINs and Aggregations Using Real-Time Indexing on MongoDB Atlas By Bhat](https://www.youtube.com/watch?v=6be6aEOHk3w)

# 6. Versioning

1. [Versioning using mongoose-vermongo](https://medium.com/versioning-in-database-mongodb-versioning-vermongo/database-versioning-7cf59a729bb3)

# 7. Optimistic Locking

1. [Optimistic locking in Mongo](https://medium.com/@andris.briedis/optimistic-locking-in-mongo-69fa693864ec)

# 8. DocumentDB vs MongoDB

1. [Amazon DocumentDB Architectural Comparisons](https://www.mongodb.com/compare/documentdb-vs-mongodb/architecture)

# 9. Schema Validation

1. [MongoDB Schema Validation Rules By Panos Zafeiropoulos](https://betterprogramming.pub/mongodb-schema-validation-rules-8a1afc6ea67b)
2. [JSON Schema Validation - Locking down your model the smart way](https://www.mongodb.com/blog/post/json-schema-validation--locking-down-your-model-the-smart-way)
3. [JSON Schema Validation: A Vocabulary for Structural Validation of JSON](http://json-schema.org/draft/2020-12/json-schema-validation.html#name-json-pointers)
4. [Generate JSON Schema from JSON](https://www.jsonschema.net/)
5. [Schema validation from MongoDB docs](https://www.mongodb.com/docs/manual/core/schema-validation/)
6. [Add a choice, conditional, or pattern field from hackolade docs](https://hackolade.com/help/Addachoiceconditionalorpatternfi.html)

# 10. Aggregations

1. [Use a View to Join Two Collections](https://www.mongodb.com/docs/manual/core/views/join-collections-with-view/)

# 11. Tools
1. [Generate JSON Schema from JSON](https://www.jsonschema.net/)

# 12. VSCode

1. [MongoDB for VS Code - MongoDB Developer Tools](https://www.youtube.com/watch?v=MLWlWrRAb4w)

# 13. Sample Data

1. [Sample Data from MongoDB](https://www.mongodb.com/docs/atlas/sample-data/)

# 14. Books

1. [Practical MongoDB Aggregations Book](https://www.practical-mongodb-aggregations.com/front-cover.html)

# 15. Courses

1. [The MongoDB Aggregation Framework (M121)](https://university.mongodb.com/courses/M121/about)
2. [M100: MongoDB for SQL Pros](https://learn.mongodb.com/courses/m100-mongodb-for-sql-pros)
3. [MongoDB CRUD Operations in Java](https://learn.mongodb.com/learn/course/mongodb-crud-operations-in-java/lesson-1-working-with-mongodb-documents-in-java/learn?client=customer)
4. [Using MongoDB with Java](https://learn.mongodb.com/learn/learning-path/using-mongodb-with-java)

# 16. Docker

1. [MongoDB in Docker](https://medium.com/@zzpzaf.se/mongodb-in-docker-bfa77346b389)

# 17. Schema Validation

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