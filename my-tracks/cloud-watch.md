
1. How to track following using cloudwatch ?

src: https://aws.amazon.com/blogs/aws/automatically-detect-operational-issues-in-lambda-functions-with-amazon-devops-guru-for-serverless/

1. **Lambda concurrent executions reaching account limit** – Triggered when concurrent executions reach an account limit for a continuous period.
2. **Lambda Provisioned Concurrency function limit breached** – Triggered when the reserved amount of provisioned concurrency is not enough over a period.
3. **Lambda timeout high compared to SQS’s visibility timeout** – Triggered when the duration of the lambda function exceeds the visibility timeout for the event source Amazon Simple Queue Service (Amazon SQS).
4. **­Lambda­ Provisioned Concurrency usage is lower than expected** – Triggered when the utilization of the provisioned concurrency is too low.
5. **Account read/write capacity for DynamoDB consumption reaching account limit** – Triggered when the account consumed capacity is approaching account-level limits during a period of time.
6. **DynamoDB table read/write consumed capacity reaching table limit** – Triggered when the writes or reads in a table are reaching the ProvisionedWriteCapacityUnits or ProvisionedReadCapacityUnits limits for the table over a period.
7. **DynamoDB table consumed capacity reaching AutoScaling Max parameter limit** – Triggered when table consumed capacity is reaching AutoScaling Max parameters limit over a period.
8. **DynamoDB read/write consumption lower than expected** – Triggered when the value for ProvisionedWriteCapacityUnits or ProvisionedReadCapacityUnits is far from what is being consumed during a period of time.