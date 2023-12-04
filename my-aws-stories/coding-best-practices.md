# TODO

Move following sections into respective aws services under `my-aws-cookbook`

# 1. Event bridge pipes

If you had a code as follows

```ts
async function sendEvent(event) {
    const ebClient = new EventBridgeClient();
    const params = { Entries: [ {
          Detail: JSON.stringify(event),
          DetailType: "newOrder",  // Not Good: hard coded
          Source: "orders",        // Not Good: hard coded
          EventBusName: eventBusName
        } ] };
    return await ebClient.send(new PutEventsCommand(params));
}

...
...

const order = { "orderId": "1234", "Items": ["One", "Two", "Three"] }
// Problem: Following should be in a transaction
await writeToDb(order);
await sendEvent(order);
```

Instead we save make the lambda save to dynamodb which in turn using streams will send an event (Transaction Outbox pattern)

<img src="./images/pipes-1.png" title="pipes-1.png" width="900"/>

# 2. After refactoring

## 2.1. Externalize the source and target and make it part of IAC

```ts
const pipe = new CfnPipe(this, 'pipe', {
  roleArn: pipeRole.roleArn,
  source: ordersTable.tableStreamArn!,
  sourceParameters: {
    dynamoDbStreamParameters: {
      startingPosition: 'LATEST'
    },
  },
  target: ordersEventBus.eventBusArn,
  targetParameters: {
    eventBridgeEventBusParameters: {
      detailType: 'order-details',
      source: 'my-source',
    },
    inputTemplate: '{"orderId": <$.dynamodb.NewImage.orderId.S>,' +
                 '"items": <$.dynamodb.NewImage.items.L[*].S>}'
  },
});
```

where source dynamodbstreams message is

```json
{
  "detail": {
    "eventID": "be1234f372dd12552323a2a3362f6bd2",
    "eventName": "INSERT",
    "eventSource": "aws:dynamodb",
    "dynamodb": {
      "Keys": { "orderId": { "S": "ABCDE" } },
      "NewImage": {
        "orderId": { "S": "ABCDE" },
        "items": {
            "L": [ { "S": "One" }, { "S": "Two" }, { "S": "Three" } ]
        }
      }
    }
  }
}
```

and the output of pipes would be

```json
{
  "time": "2023-06-01T06:18:10Z",
  "detail": {
    "orderId": "ABCDE",
    "items": ["One", "Two", "Three" ]
  }
}
```

https://aws.amazon.com/blogs/compute/decoupling-event-publishing-with-amazon-eventbridge-pipes/

# 3. SQS

1. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 1 by Pascal Vogel ](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-1/)
2. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 2 by Pascal Vogel ](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-2/)
3. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 3 by Pascal Vogel](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-3/)
4. [Implementing Well-Architected Best Practices for Amazon SQS with CDK](https://github.com/aws-samples/amazon-sqs-best-practices-cdk)