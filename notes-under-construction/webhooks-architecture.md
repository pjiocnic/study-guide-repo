https://github.com/aws-samples/webhooks/tree/main

<img src="./images/webhooks-1.png" title="webhooks-1.png" width="900"/>

## As a Webhook Producer

1. Event generation: Use EventBridge Scheduler if you can pool for events and send in batches. Does AWS DocumentDB workshop have an example?
1. Filtering: EventBridge Pipes support filtering by matching event patterns.
1. Delivery: Use EventBridge if you want to deliver events outside of AWS using REST API
1. Delivery: EventBridge uses Invocation rate Limit to protect extenranl endpoints from surge in traffic. Example? [AWS re:Invent 2019: [REPEAT 2] I didn’t know Amazon API Gateway did that (SVS212-R2)](https://www.youtube.com/watch?v=yfJZc3sJZ8E)
1. Provides scalable and resilient delivery using SQS: Possible to setup Eventbridge to handle retries with exponential backoff and then send them off to SQS DLQs.

# Architecting a Webhook consumer

<img src="./images/webhooks-2.png" title="webhooks-2.png" width="900"/>


1. API Gateway can be an endpoint to receive messages

2. API Gateway can be configured for rate and burst limits.  This way you can protect your downstream systems from being overwhelmed.

<img src="./images/webhooks-3.png" title="webhooks-3.png" width="900"/>

3. **Authorization and Verification:**
- **Approach 1** - Using Hash-based Message Authentication Code (**HMAC**): A shared secret is established and stored in Secrets Manager. A Lambda function then verifies integrity of the message, processing a signature in the request header. Typically, the signature contains a timestamped **nonce** with an expiry to mitigate replay attacks, where events are sent multiple times by an attacker
- **Approach 2**: if the provider supports OAuth, consider securing the API with Amazon Cognito.
4. **Handling different Webhook Provider Payload Sizes**:

<img src="./images/webhooks-4.png" title="webhooks-4.png" width="900"/>

- Above limits can present a bottleneck.  So use [**claim check-pattern**](https://serverlessland.com/event-driven-architecture/visuals/claim-check-pattern)
- If **claim-check-pattern** is used the you can go upto 6MB since Lambda's payload restriction will only be the limiting factor

4. **Idempotency**: There's possibility that webhook provider submits the same request multiple times (ie at-least-once instead of exactly-once).  To handle this use **Lambda + Dynamodb** to implement idempotency.

5. **Message Ordering**: Since the provider can submit messages out of order (for example when they submit the same message again and again) then you need to use **DynamoDBStreams and eventbridge pipes** to do best effort ordering. To indicate order, events may include a timestamp or a sequence identifier in the payload. If the provider doesn't provide any field to indicate order then use best effort basis.  If a field is provided use  DynamoDB Streams and EventBridge Pipes to create an order.
- See how pipes ensure ordering https://www.clouddatainsights.com/the-value-proposition-of-eventbridge-pipes/

# NextSteps

1. [Managing large Amazon SQS messages using Amazon S3](https://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/sqs-s3-messages.html)
1. What's the size of SQS payload without the extention?
1. https://serverlessland.com/event-driven-architecture/visuals/claim-check-pattern
1. https://www.clouddatainsights.com/the-value-proposition-of-eventbridge-pipes/
1. Make notes - When should I use SNS FO over eventbridge? https://cbannes.medium.com/decoupling-microservices-with-aws-eventbridge-pipes-3cef3a1dfce7#What about ordering of events?
1. Make Notes https://www.clouddatainsights.com/the-value-proposition-of-eventbridge-pipes/#Ordered Event Delivery
1. Find an example to see how **DynamoDB Streams and EventBridge Pipes** ensure event ordering

# Standards

1. https://webhooks.fyi/
1. https://cloudevents.io/