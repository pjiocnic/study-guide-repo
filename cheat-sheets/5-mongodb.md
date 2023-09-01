
```json
{
     "_id": ObjectId("5f69320ecf113d40f4d7d0a3"),
    "type": "comment",
    "content": "This is a comment."
}
{
    "_id": ObjectId("5f69322ecf113d40f4d7d0a4"),
    "type": "post",
    "title": "A new post",
    "comments": [
        {
            "type": "comment",
            "content": "This is a comment."
        },
        {
            "type": "comment",
            "content": "Another comment."
        }
    ]
}

```

- Finders

```json
// Find all posts and comments
db.collection.find({
    "type": { $in: [
            "post",
            "comment"
        ]
    }
})

// Find only posts
db.collection.find({
    "type": "post"
})

// Find only comments
db.collection.find({
    "type": "comment"
})
```

- Schema validations

```json
{
    "$jsonSchema": {
        "bsonType": "object",
        "required": [
            "type",
            "content"
        ],
        "properties": {
            "type": {
                "enum": [
                    "post",
                    "comment"
                ]
            },
            "content": {
                "type": "string"
            },
            // Add other properties specific to each type
        }
    }
}
```

- Apply schema validation to a collection

```json
db.createCollection("documents", {
  validator: {
    $jsonSchema: {
      // Your JSON Schema here
    }
  }
})
```

- Insert documents

```json
db.documents.insert({
  "type": "post",
  "content": "This is a post."
})

db.documents.insert({
  "type": "comment",
  "content": "This is a comment."
})

// This would fail validation because "type" is not one of the allowed values
db.documents.insert({
  "type": "invalid",
  "content": "Invalid document."
})
```

# More Material

1. [Document Validation for Polymorphic Collections By Andrew Morgan](https://www.mongodb.com/developer/products/mongodb/polymorphic-document-validation/)