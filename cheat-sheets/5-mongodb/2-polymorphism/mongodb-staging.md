// This is draft 7 version as suggested by chatgpt. MongoDB is at draft 4

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
              "allOf": [
                {
                  "if": {
                    "properties": {"orderType" : {"const": "in-store" }}
                  },
                  "then": {
                    "properties": {"street" : {"type":"string"}, "city": {"type":"string"}},
                    "required": [ "street", "city" ]
                  }
                }
              ]
            }
        }
      }
   }
})

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
              "$cond":
                {
                  "if": { $eq [$orderType,"in-store"] },
                  "then": {
                    "properties": {"street" : {"type":"string"}, "city": {"type":"string"}},
                    "required": [ "street", "city" ]
                  },
                  "else": {
                    "properties": {"state" : {"type":"string"}},
                    "required": [ "state"]
                  }

                }

            }
        }
      }
   }
})


db.createCollection("orders", {
   validator: {
      $jsonSchema: {
         bsonType: "object",
         required: [ "orderType" ],
         properties: {
            orderType: {
               enum: [ "online", "in-store" ],
               description: "must be either 'online' or 'in-store'"
            }
        }
      }
   }
})