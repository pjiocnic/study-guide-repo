// How to validate polymorphic schemas

// src: https://www.mongodb.com/developer/products/mongodb/polymorphic-document-validation/

// In this eample, we add 2 different types of documents - customer and account into a single collection

// sample data
const cust1 = {
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

const cust2 = {
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

const acc1 = {
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

const acc2 = {
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

const acc3 = {
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

// set up indexes
const indexKeys1 = { accountNumber: 1 }
const indexKeys2 = {  accountNumber: 1, docType: 1 }
const indexOptions1 = {partialFilterExpression: { docType: 'account' }}

// schema definitions
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

// ====================  semantic validations =============================================

// For customer documents, the customerSince value can't be any earlier than the current time.
// For account documents, the dateOpened value can't be any earlier than the current time.
// For savings accounts, the balance can't fall below zero.

const semanticChecks = {
    "$expr": {
        "$and": [
            {
                "$not": [
                    { "$and": [
                        { "$eq": ["$docType", "customer"] },
                        { "$gt": ["$customerSince", ISODate()] }
                    ]
                    }
                ]
            },
            {
                "$not": [
                    { "$and": [
                        { "$eq": ["$docType", "account"] },
                        { "$gt": ["$dateOpened", ISODate()] }
                    ]
                    }
                ]
            },
            {
                "$not": [
                    { "$and": [
                        { "$eq": ["$accountType", "savings"] },
                        { "$lt": ["$balance", 0] }
                    ]}
                ]
            }
        ]
    }
}

const schemaValidation = {
    "$and": [
        { $jsonSchema: { oneOf: [ customerSchema, accountSchema ] }},
        semanticChecks
    ]
}
const badDoc1 = {
    dummy: "some stuff"
}

const database = 'MongoBank';
const collection = 'Accounts';

use(database);

db.getCollection(collection).drop();

db.createCollection(collection, { validator: schemaValidation })

db.getCollection(collection).replaceOne({"_id": cust1._id}, cust1, {upsert: true});
db.getCollection(collection).replaceOne({"_id": cust2._id}, cust2, {upsert: true});
db.getCollection(collection).replaceOne({"_id": acc1._id}, acc1, {upsert: true});
db.getCollection(collection).replaceOne({"_id": acc2._id}, acc2, {upsert: true});
db.getCollection(collection).replaceOne({"_id": acc3._id}, acc3, {upsert: true});
db.getCollection(collection).dropIndexes();
db.getCollection(collection).createIndex(indexKeys1, indexOptions1);
db.getCollection(collection).createIndex(indexKeys2);

// My additions

// Example 1. This will fail due to baddoc
db.getCollection(collection).replaceOne({ "_id": cust1._id }, badDoc1, { upsert: true });

// Example 2: Updating an existing doc
db.getCollection(collection).findOne({ "_id": cust1._id })

const cust1b = {
    "_id": "kjfgjebgjfbkjb",
    "customerId": "CUST-123456789",
    "docType": "customer",
    "name": {
        "title": "Mr",
        "first": "James",
        "middle": "",
        "last": "Doe"
    },
    "address": {
        "street1": "18 Hatfields",
        "city": "London",
        "postCode": "SE1 8DJ",
        "country": "UK"
    },
    "customerSince": ISODate("2005-05-20")
}
db.getCollection(collection).replaceOne({ "_id": cust1._id }, cust1b, { upsert: true });
// We will find the updated doc
db.getCollection(collection).findOne({ "_id": cust1._id })

// Example 3: This json is missing required field customerSince
const cust1c = {
    "_id": "kjfgjebgjfbkjb",
    "customerId": "CUST-123456789",
    "docType": "customer",
    "name": {
        "title": "Mr",
        "first": "James",
        "middle": "",
        "last": "Doe"
    },
    "address": {
        "street1": "18 Hatfields",
        "city": "London",
        "postCode": "SE1 8DJ",
        "country": "UK"
    }
}

// Try updating ... you should get Document validation failed
db.getCollection(collection).replaceOne({ "_id": cust1._id }, cust1c, { upsert: true });

// The message
```bash
failingDocumentId: 'kjfgjebgjfbkjb',
    details: {
    operatorName: '$and',
        clausesNotSatisfied: [
            {
                index: 0,
                details: {
                    operatorName: '$jsonSchema',
                    schemaRulesNotSatisfied: [
                        {
                            operatorName: 'oneOf',
                            schemasNotSatisfied: [
                                {
                                    index: 0,
                                    details: [
                                        {
                                            operatorName: 'required',
                                            specifiedAs: {
                                                required: [
                                                    'docType',
                                                    'customerId',
                                                    'name',
                                                    'customerSince'
                                                ]
                                            },
                                            missingProperties: [
                                                'customerSince'
                                            ]
                                        }
                                    ]
                                },
                                {
                                    index: 1,
                                    details: [
                                        {
                                            operatorName: 'properties',
                                            propertiesNotSatisfied: [
                                                {
                                                    propertyName: 'docType',
                                                    details: [
                                                        {
                                                            operatorName: 'enum',
                                                            specifiedAs: {
                                                                enum: [
                                                                    'account'
                                                                ]
                                                            },
                                                            reason: 'value was not found in enum',
                                                            consideredValue: 'customer'
                                                        }
                                                    ]
                                                },
                                                {
                                                    propertyName: 'customerId',
                                                    details: [
                                                        {
                                                            operatorName: 'bsonType',
                                                            specifiedAs: {
                                                                bsonType: 'array'
                                                            },
                                                            reason: 'type did not match',
                                                            consideredValue: 'CUST-123456789',
                                                            consideredType: 'string'
                                                        }
                                                    ]
                                                }
                                            ]
                                        },
                                        {
                                            operatorName: 'required',
                                            specifiedAs: {
                                                required: [
                                                    'docType',
                                                    'accountNumber',
                                                    'accountType',
                                                    'customerId',
                                                    'dateOpened',
                                                    'balance'
                                                ]
                                            },
                                            missingProperties: [
                                                'accountNumber',
                                                'accountType',
                                                'balance',
                                                'dateOpened'
                                            ]
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                }
            }
        ]
}
}
```