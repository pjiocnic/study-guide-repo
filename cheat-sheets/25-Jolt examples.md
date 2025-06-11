https://jolt-demo.appspot.com/#inception

# Tutorials

https://docs.digibee.com/documentation/components/tools/transformer-jolt/transformer-getting-to-know-jolt

# Videos
https://www.youtube.com/watch?v=Ef4DZNGdkP4

# examples

## Example 1

https://stackoverflow.com/questions/77272848/jolt-transformation-to-remove-json-object-based-on-condition

1. Input

```json
{
  "body": {
    "Activity": [
      {
        "attributeCode": "ABC",
        "attributeValue": "1200"
      },
      {
        "attributeCode": "DEF",
        "attributeValue": "1400"
      },
      {
        "attributeCode": "GHI",
        "attributeValue": "1600"
      }
    ],
    "NonActivity": {
      "bill": "23Oct11",
      "activityNumber": "6100"
    }
  },
  "header": {
    "id": "010400",
    "referenceNumber": "6100"
  },
  "action": "UPDATE"
}
```

2. Jolt Spec

```json
[
  {
    "operation": "shift",
    "spec": {
      "body": {
        "Activity": "&1.&.@(2,action)", // assign a label by action's value nested within "Activity" object
        "*": "&1.&"
      },
      "*": "&"
    }
  },
  {
    "operation": "shift",
    "spec": {
      "body": {
        "Activity": { "CREATE": "&2.&1" },
        "*": "&1.&"
      },
      "*": "&"
    }
  }
]
```

3. Output

```json
{
  "body" : {
    "NonActivity" : {
      "bill" : "23Oct11",
      "activityNumber" : "6100"
    }
  },
  "header" : {
    "id" : "010400",
    "referenceNumber" : "6100"
  },
  "action" : "UPDATE"
}
```
