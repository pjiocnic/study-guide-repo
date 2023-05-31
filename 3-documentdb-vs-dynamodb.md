
<H1> DocumentDB vs DynamoDB</h1>

https://www.twitch.tv/aws/video/484071022

Can have 100 level deep
DocumentDB can query on nested fields but DynamoDB can do it with workarounds (How?).  Both services support Document API
To get 4 9s avaialbility you have to deploy documentDB into 3 AZs; you get that by default in dynamoDB
DocumentDB has 1Write + 15 Read replicas = Toital 16
Recommended instance type for DocumentDB is R5 family
If I have > 64 TB then dynamodb maybe a better choice
DocumentDB - smaller instances 1100 connections and larger instances support 4500 connections



https://docs.aws.amazon.com/documentdb/latest/developerguide/limits.html
https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Limits.html

# From chatGPT
Sure, I can give you a detailed comparison between Amazon DynamoDB and Amazon DocumentDB (with MongoDB compatibility).

Amazon DynamoDB is a fully-managed NoSQL database service that supports key-value and document data models. It is designed for high scalability and availability, with automatic scaling and multi-region replication built-in. DynamoDB is a great choice for applications that require low latency, high throughput, and predictable performance.

On the other hand, Amazon DocumentDB is a fully-managed MongoDB-compatible database service that is built to be compatible with existing MongoDB applications and tools. It is designed for high performance, scalability, and availability, with automatic scaling and multi-region replication built-in. DocumentDB is a great choice for applications that require a flexible data model and support for complex queries.

Here are some key differences between DynamoDB and DocumentDB:

Data Model: DynamoDB supports both key-value and document data models, whereas DocumentDB supports only the document data model.

Querying: DynamoDB uses a query language called DynamoDB Query, which supports basic filtering and sorting of data. DocumentDB, on the other hand, supports the full power of the MongoDB query language, which includes support for complex queries, aggregations, and indexes.

Schema: DynamoDB is schemaless, which means that you can store data in any format without having to define a schema beforehand. DocumentDB requires a schema to be defined before data can be stored.

Scaling: Both DynamoDB and DocumentDB are designed for high scalability and availability, with automatic scaling and multi-region replication built-in. However, DynamoDB uses a partitioning scheme that is optimized for key-value data, whereas DocumentDB uses a sharding scheme that is optimized for document data.

Pricing: DynamoDB offers a pay-per-request pricing model, where you only pay for the read and write requests that you make to the database. DocumentDB offers a more traditional pricing model, where you pay for the amount of storage and processing power that you use.

In summary, DynamoDB is a great choice for applications that require low latency, high throughput, and predictable performance, and support for both key-value and document data models. DocumentDB, on the other hand, is a great choice for applications that require a flexible data model and support for complex queries, and are already using MongoDB.

# Can Dynamodb handle embedded objects?

[ChatGPT] Yes, Amazon DynamoDB can handle embedded objects or nested objects, which are also known as maps or dictionaries. DynamoDB supports a data type called "Map" that represents a collection of attributes that can be nested within another attribute.

To store embedded objects in DynamoDB, you can use the Map data type. A Map data type can store complex, nested data structures as key-value pairs. The keys in a Map can be strings, numbers, or binary values, and the values can be scalar types like strings, numbers, or binary values, or other complex types like lists or other maps.

When using the Map data type, you can store nested data structures with multiple levels of nesting, and you can perform CRUD operations on the individual elements of the nested structure using DynamoDB APIs and query expressions.

Overall, DynamoDB is a flexible and scalable NoSQL database that can handle a variety of data types, including embedded objects or nested structures, making it suitable for a wide range of use cases.

# How deep can the objects be nested in Dynamodb?

[ChatGPT] The maximum depth of nesting in Amazon DynamoDB is 32 levels. This means that you can have up to 32 nested maps or lists within a single top-level item in DynamoDB.

When using nested structures in DynamoDB, it's important to consider the potential impact on performance and query complexity. As the depth of nesting increases, so does the complexity of accessing and querying the data, which can have an impact on the performance of your application.

In general, it's recommended to keep the depth of nesting to a minimum and to flatten your data model where possible to improve performance and simplify query logic. If you find that your data model requires a high degree of nesting, it may be worth considering alternative database solutions that are better suited to handling complex, nested data structures.

# can i create indexes on the nested objects in dynamodb?

Yes, you can create indexes on nested objects in Amazon DynamoDB.

DynamoDB supports two types of indexes: Local Secondary Indexes (LSIs) and Global Secondary Indexes (GSIs). LSIs can only be created on `top-level attributes of a table`, while GSIs can be created on `any top-level attribute or nested attribute within a table`.

To create an index on a nested attribute, you need to `specify the full path of the attribute` in the attribute definitions and index key schema. For example, suppose you have a nested attribute "customer.address.city". To create an index on this attribute, you would specify "customer.address.city" as the attribute name in the attribute definitions and index key schema.

Note that when querying an index on a nested attribute, you need to specify the full path of the attribute in the query expression. For example, if you have a GSI on "customer.address.city", you would need to use the following query expression to retrieve items based on the city:

```json
"IndexName": "customer-address-city-index",
"KeyConditionExpression": "customer.address.city = :city",
"ExpressionAttributeValues": {
    ":city": { "S": "Seattle" }
}
```

Overall, creating indexes on nested attributes can help improve query performance and make it easier to retrieve data based on specific attributes within a nested structure.

# can documentdb validate schemas?

Yes, Amazon DocumentDB (with MongoDB compatibility) supports schema validation, which allows you to enforce the structure of your documents and ensure that they conform to a specific schema.

Schema validation is a feature that is available in MongoDB and is supported by Amazon DocumentDB, which is a fully-managed, MongoDB-compatible document database service. With schema validation, you can define rules that specify the allowed types and values of fields in your documents, as well as any constraints on the structure of your documents.

To enable schema validation in Amazon DocumentDB, you can create a collection with a validator. A validator is a JSON schema that defines the rules for document validation. You can specify a validator when creating a new collection or update an existing collection to add validation.

For example, to create a collection with a validator that enforces a specific schema, you could use a command like the following:

```php
db.createCollection("myCollection", {
   validator: {
      $jsonSchema: {
         bsonType: "object",
         required: [ "firstName", "lastName", "email" ],
         properties: {
            firstName: {
               bsonType: "string",
               description: "must be a string and is required"
            },
            lastName: {
               bsonType: "string",
               description: "must be a string and is required"
            },
            email: {
               bsonType : "string",
               pattern: "^\\S+@\\S+\\.\\S+$",
               description: "must be a valid email address and is required"
            }
         }
      }
   }
})
```

In this example, the validator specifies that documents in the "myCollection" collection must have a "firstName", "lastName", and "email" field, and that the "email" field must be a valid email address.

Overall, schema validation in Amazon DocumentDB can help you ensure the consistency and integrity of your data by enforcing a specific structure and set of rules on your documents.

# Conditional schema validation

Yes, Amazon DocumentDB (with MongoDB compatibility) supports schema validation rules that can be conditional on the values of other fields in the document. This means that you can define schema validation rules that require certain fields to be present or have certain values based on the values of other fields in the document.

To define a schema validation rule that depends on the value of a field in the document, you can use the $cond operator in your validation expression. The $cond operator evaluates a boolean expression and returns one of two specified values depending on whether the expression evaluates to true or false.

For example, suppose you have a collection that contains documents representing orders, and each order can have one of two types: "online" or "in-store". If the order type is "online", you want to require a "shippingAddress" field, but if the order type is "in-store", you want to require a "storeLocation" field. You can define a schema validation rule to enforce this requirement as follows:

```php
db.createCollection("orders", {
   validator: {
      $jsonSchema: {
         bsonType: "object",
         required: [ "orderType" ],
         properties: {
            orderType: {
               enum: [ "online", "in-store" ],
               description: "must be either 'online' or 'in-store'"
            },
            shippingAddress: {
               $cond: {
                  if: { $eq: [ "$orderType", "online" ] },
                  then: { bsonType: "object", required: [ "street", "city", "state", "zip" ] },
                  else: { bsonType: "object" }
               }
            },
            storeLocation: {
               $cond: {
                  if: { $eq: [ "$orderType", "in-store" ] },
                  then: { bsonType: "object", required: [ "storeId", "storeName" ] },
                  else: { bsonType: "object" }
               }
            }
         }
      }
   }
})
```

In this example, the validator specifies that the "orderType" field is required and must be either "online" or "in-store". It also defines two other fields, "shippingAddress" and "storeLocation", that are conditional based on the value of "orderType". If "orderType" is "online", then "shippingAddress" is required and must have fields for "street", "city", "state", and "zip". If "orderType" is "in-store", then "storeLocation" is required and must have fields for "storeId" and "storeName".

Overall, schema validation in Amazon DocumentDB can be used to enforce complex data validation rules, including rules that depend on the values of other fields in the document.

https://stackoverflow.com/questions/36999377/how-to-add-conditional-schema-based-on-other-field