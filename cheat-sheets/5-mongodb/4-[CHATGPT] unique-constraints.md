
To avoid duplicate rows in enterprise MongoDB without relying on built-in constraints (as MongoDB doesn’t support features like primary key constraints natively), you can implement a custom solution to handle uniqueness and prevent duplicates. Here's a structured approach:

### 1. **Use Unique Indexes**
MongoDB allows you to create unique indexes on specific fields. This will prevent the insertion of documents with duplicate values in the indexed fields.

**Steps**:
- Create a unique index on the field (or combination of fields) where you want to enforce uniqueness.

**Example**:
```javascript
db.collection.createIndex(
   { fieldName: 1 },
   { unique: true }
)
```

If you have a combination of fields that need to be unique (like a composite key in relational databases), you can specify multiple fields:
```javascript
db.collection.createIndex(
   { field1: 1, field2: 1 },
   { unique: true }
)
```

This ensures that no two documents can have the same value in the specified field(s).

### 2. **Leverage Application-Level Locking or Pre-Check**
If you want to control duplicates beyond what unique indexes allow (e.g., handling bulk operations or specific logic for insertion):

- **Pre-insert Query**: Before inserting a document, query the database to check if a document with the same key or criteria already exists.

**Example**:
```javascript
const existingDocument = db.collection.findOne({ field: value });
if (!existingDocument) {
    db.collection.insertOne({ field: value });
} else {
    // Handle duplicate case
}
```
This ensures that no duplicate document is inserted.

### 3. **Implement a Middleware for Custom Uniqueness Checks**
If you're working with an application framework (like Node.js with Mongoose), you can implement middleware that checks for uniqueness before inserting or updating documents. This is useful if you have complex business logic for determining uniqueness.

```javascript
// Mongoose example
schema.pre('save', async function(next) {
    const doc = await this.constructor.findOne({ field: this.field });
    if (doc) {
        throw new Error('Duplicate entry');
    }
    next();
});
```

### 4. **Atomic Transactions (For Duplicate Check and Insert)**
Starting from MongoDB 4.0, you can use **multi-document transactions** to perform a read and insert in an atomic fashion. This is particularly useful if your uniqueness condition involves multiple fields or documents across collections.

**Example**:
```javascript
const session = db.getMongo().startSession();
session.startTransaction();

try {
    const existingDocument = db.collection.findOne({ field: value }, { session });

    if (!existingDocument) {
        db.collection.insertOne({ field: value }, { session });
    }

    session.commitTransaction();
} catch (error) {
    session.abortTransaction();
    // Handle duplicate or other error
} finally {
    session.endSession();
}
```
This approach ensures that even in high concurrency environments, you can safely check for duplicates and insert without race conditions.

### 5. **Secondary Database (Shadow Database)**
If strict uniqueness is required across a distributed system where the default MongoDB unique index is insufficient (e.g., sharded clusters), you can maintain a secondary, lightweight relational database (like PostgreSQL or MySQL) to enforce uniqueness.

- Insert/update a unique key into the secondary database before inserting into MongoDB.
- Use the secondary database for any complex uniqueness validation (atomic operations are easier in relational databases).

**Flow**:
1. Check or insert unique record in secondary database.
2. If successful, proceed with MongoDB insertion.

### 6. **Handling Bulk Operations**
If you’re performing batch inserts and want to handle duplicates during the operation:
- You can use the `ordered: false` option to allow MongoDB to continue processing the remaining documents in a bulk insert even if some are duplicates (caught by unique index).

**Example**:
```javascript
db.collection.insertMany(
  [{ field: value1 }, { field: value2 }, { field: value1 }],
  { ordered: false }
);
```

- Then catch duplicate key errors and handle them as needed:
```javascript
try {
    db.collection.insertMany([...], { ordered: false });
} catch (e) {
    if (e.code === 11000) {
        // Handle duplicates
    }
}
```

### Summary:
- **Unique Indexes**: Enforce uniqueness on specific fields.
- **Pre-Check Queries**: Query for duplicates before inserting.
- **Custom Middleware**: Handle uniqueness logic in your application.
- **Transactions**: Ensure atomic checks and inserts.
- **Secondary Database**: Offload uniqueness checks to a relational system.
- **Handling Bulk Operations**: Use `ordered: false` and process duplicates gracefully.

By using a combination of unique indexes, application-level checks, and atomic operations, you can effectively prevent duplicates in enterprise MongoDB without built-in constraints.