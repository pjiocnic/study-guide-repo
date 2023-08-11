
# 1. Difference between client errors vs server errors

Ref: https://aws.amazon.com/blogs/compute/designing-durable-serverless-apps-with-dlqs-for-amazon-sns-amazon-sqs-aws-lambda/

## 1.1. Client Error

- you might have deleted the SQS queue that is subscribed to your SNS topic, without also deleting the SNS subscription corresponding to the queue
- you changed the resource policy attached to your endpoint in a way that prevents SNS from delivering messages to that endpoint

### 1.1.1. How does SNS Handle client errors?

SNS **does not retry** the delivery of messages that failed as the result of client errors.

## 1.2. Server error

- the system that powers the subscribed endpoint is unavailable
- when it returns an exception response indicating that it failed to process a valid request from SNS

### 1.2.1. How does SNS Handle Server error?

When a server error occurs for an AWS managed endpoint, backed by either SQS or Lambda, then SNS retries the delivery for up to 100,015 times, **over 23 days**
