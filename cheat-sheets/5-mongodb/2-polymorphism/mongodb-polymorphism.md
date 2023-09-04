<h1>How to validate polymorphic schema<h1>

src: https://www.mongodb.com/developer/products/mongodb/polymorphic-document-validation/

1. Sample JSON docs

```json
{
    "_id": "kjfgjebgjfbkjb",
    "customerId": "CUST-123456789",
    "docType": "customer",
    "name": {
        "title": "Mr",
        "first": "Andrew",
        "middle": "James",
        "last": "Morgan"
    },
    "address": {
        "street1": "18 Hatfields",
        "city": "London",
        "postCode": "SE1 8DJ",
        "country": "UK"
    },
    "customerSince": ISODate("2005-05-20")
}

{
    "_id": "jnafjkkbEFejfleLJ",
    "customerId": "CUST-987654321",
    "docType": "customer",
    "name": {
        "title": "Mrs",
        "first": "Anne",
        "last": "Morgan"
    },
    "address": {
        "street1": "18 Hatfields",
        "city": "London",
        "postCode": "SE1 8DJ",
        "country": "UK"
    },
    "customerSince": ISODate("2003-12-01")
}

{
    "_id": "dksfmkpGJPowefjdfhs",
    "accountNumber": "ACC1000000654",
    "docType": "account",
    "accountType": "checking",
    "customerId": [
        "CUST-123456789",
        "CUST-987654321"
    ],
    "dateOpened": ISODate("2003-12-01"),
    "balance": NumberDecimal("5067.65")
}

{
    "_id": "kliwiiejeqydioepwj",
    "accountNumber": "ACC1000000432",
    "docType": "account",
    "accountType": "savings",
    "customerId": [
        "CUST-123456789"
    ],
    "dateOpened": ISODate("2005-10-28"),
    "balance": NumberDecimal("10341.21")
}

{
    "_id": "djahspihhfheiphfipewe",
    "accountNumber": "ACC1000000890",
    "docType": "account",
    "accountType": "savings",
    "customerId": [
        "CUST-987654321"
    ],
    "dateOpened": ISODate("2003-12-15"),
    "balance": NumberDecimal("10341.89")
}

```

I have two different types of documents that I want to store in my **Accounts collection** — **customer** and **account**.

```bash
const indexKeys1 = { accountNumber: 1 }
const indexKeys2 = {  accountNumber: 1, docType: 1 }
const indexOptions1 = {partialFilterExpression: { docType: 'account' }}

db.getCollection(collection).createIndex(indexKeys1, indexOptions1);
db.getCollection(collection).createIndex(indexKeys2);
```

# Schemas


```bash
const customerSchema = {
    required: ["docType", "customerId", "name", "customerSince"],
    properties: {
        docType: { enum: ["customer"] },
        customerId: { bsonType: "string"},
        name: {
            bsonType: "object",
            required: ["first", "last"],
            properties: {
                title: { enum: ["Mr", "Mrs", "Ms", "Dr"]},
                first: { bsonType: "string" },
                middle: { bsonType: "string" },
                last: { bsonType: "string" }
            }
        },
        address: {
            bsonType: "object",
            required: ["street1", "city", "postCode", "country"],
            properties: {
                street1: { bsonType: "string" },
                street2: { bsonType: "string" },
                postCode: { bsonType: "string" },
                country: { bsonType: "string" }
            }
        },
        customerSince: {
            bsonType: "date"
        }
    }
}

```

## How to indicate optional fields?**

  `name.title` and `name.middle` are optional
  `address.street2` is optional


```bash
const accountSchema = {
    required: ["docType", "accountNumber", "accountType", "customerId", "dateOpened", "balance"],
    properties: {
        docType: { enum: ["account"] },
        accountNumber: { bsonType: "string" },
        accountType: { enum: ["checking", "savings", "mortgage", "loan"] },
        customerId: { bsonType: "array" },
        dateOpened: { bsonType: "date" },
        balance: { bsonType: "decimal" }
    }
}
```

```bash
const schemaValidation = {
    $jsonSchema: { oneOf: [ customerSchema, accountSchema ] }
}

db.createCollection(collection, {validator: schemaValidation})
```