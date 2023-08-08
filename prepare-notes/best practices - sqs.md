
# 1. Operational Excellence Pillar

## 1.1. Create a Queue using CDK

```py
from aws_cdk import (
    Duration,
    Stack,
    aws_sqs as sqs,
)
from constructs import Construct


class SqsCdBlogStack(Stack):
    def __init__(self, scope: Construct, construct_id: str, **kwargs) -> None:
        super().__init__(scope, construct_id, **kwargs)

        # The code that defines your stack goes here

        # example resource
        queue = sqs.Queue(
            self,
            "InventoryUpdatesQueue",
            visibility_timeout=Duration.seconds(300),
        )
```

## 1.2. Best practice: Configure CloudWatch alarms for ApproximateAgeofOldestMessage

```py
# Create a CloudWatch alarm for ApproximateAgeOfOldestMessage metric
alarm = cloudwatch.Alarm(
	self,
	"OldInventoryUpdatesAlarm",
	alarm_name="OldInventoryUpdatesAlarm",
	metric=queue.metric_approximate_age_of_oldest_message(),
	threshold=600,  # Specify your desired threshold value in seconds
	evaluation_periods=1,
	comparison_operator=cloudwatch.ComparisonOperator.GREATER_THAN_OR_EQUAL_TO_THRESHOLD,
)
```

## 1.3. Best practice: Add a tracing header while sending a message to the queue to provide distributed tracing capabilities for faster troubleshooting

```py
# Create pre-processing Lambda function
csv_processing_to_sqs_function = _lambda.Function(
    self,
    "CSVProcessingToSQSFunction",
    runtime=_lambda.Runtime.PYTHON_3_8,
    code=_lambda.Code.from_asset("sqs_blog/lambda"),
    handler="CSVProcessingToSQSFunction.lambda_handler",
    role=role,
    tracing=Tracing.ACTIVE,  # Enable active tracing with X-Ray
)

# Create a post-processing Lambda function with the specified role
sqs_to_dynamodb_function = _lambda.Function(
    self,
    "SQSToDynamoDBFunction",
    runtime=_lambda.Runtime.PYTHON_3_8,
    code=_lambda.Code.from_asset("sqs_blog/lambda"),
    handler="SQSToDynamoDBFunction.lambda_handler",
    role=role,
    tracing=Tracing.ACTIVE,  # Enable active tracing with X-Ray
)
```

# 2. Security Pillar

## 2.1. Best practice: Configure server-side encryption

<img src="./images/2.1.png" title="2.1.png" width="900"/>

1. In transist (ie when you make a https call (internally) to send message) encryption is provided by default.  But if you need encryption at rest you need to enable
2. Thru' AWS console you can enable encryption in 2 ways - SSE-SQS and SSE-KMS
3. How to enable server-side encryption in CDK?
    &nbsp;
    The AWS CDK code sets `SSE-SQS` as the default encryption key type. However, the following AWS CDK code shows how to encrypt the queue with `SSE-KMS`.
    &nbsp;

        ```py
        # Create the SQS queue with DLQ setting
        queue = sqs.Queue(
            self,
            "InventoryUpdatesQueue",
            visibility_timeout=Duration.seconds(300),
            encryption=sqs.QueueEncryption.KMS_MANAGED,
        )
        ```

## 2.2. Best practice: Implement least-privilege access using access policy

Give enough permissions to allow Lambda to interact with SQS and only allow Lambda to access the queue

```py

# Create the SQS queue with DLQ setting
queue = sqs.Queue(
    self, "InventoryUpdatesQueue",
    visibility_timeout=Duration.seconds(300),
    #encryption=sqs.QueueEncryption.KMS_MANAGED,
    dead_letter_queue=sqs.DeadLetterQueue(
        max_receive_count=5,  # Number of retries before sending the message to the DLQ
        queue=dlq
    )
)

# Create a role for the Lambda function
role = iam.Role(
    self, "InventoryFunctionRole",
    assumed_by=iam.ServicePrincipal("lambda.amazonaws.com"),
    role_name="InventoryFunctionRole",
    description="Role for Lambda functions"
)

# Allow the Lambda function to receive messages from the SQS queue
role.add_to_policy(iam.PolicyStatement(
    actions=["sqs:ReceiveMessage", "sqs:DeleteMessage", "sqs:GetQueueAttributes"],
    resources=[queue.queue_arn]
))

# Create pre-processing Lambda function
csv_processing_to_sqs_function = _lambda.Function(
    self,
    "CSVProcessingToSQSFunction",
    runtime=_lambda.Runtime.PYTHON_3_8,
    code=_lambda.Code.from_asset("sqs_blog/lambda"),
    handler="CSVProcessingToSQSFunction.lambda_handler",
    role=role,
    tracing=Tracing.ACTIVE,
)

# Define the queue policy to allow messages from the Lambda function's role only
policy = iam.PolicyStatement(
    actions=["sqs:SendMessage"],
    effect=iam.Effect.ALLOW,
    principals=[iam.ArnPrincipal(role.role_arn)],
    resources=[queue.queue_arn],
)

queue.add_to_resource_policy(policy)
```

## 2.3. Best practice: Allow only encrypted connections over HTTPS using aws:SecureTransport

Enforce HTTPS connections to the queue

```py
# Create an IAM policy statement allowing only HTTPS access to the queue
secure_transport_policy = iam.PolicyStatement(
    effect=iam.Effect.DENY,
    actions=["sqs:*"],
    resources=[queue.queue_arn],
    conditions={
        "Bool": {
            "aws:SecureTransport": "false",
        },
    },
)
```

Now, any requests made over non-secure HTTP receive a 400 InvalidSecurity error from SQS.

## 2.4. Best practice: Use attribute-based access controls (ABAC)

Attribute-based access controls (ABAC) is an authorization strategy that defines permissions based on tags attached to users and AWS resources.

**Further Reading**: https://aws.amazon.com/blogs/compute/introducing-attribute-based-access-controls-abac-for-amazon-sqs/

# 3. Cost Optimization Pillar

## 3.1. Use Temporary Queues

These are also called as vitual queues and have no cost associated with it
It used for short synchronoud 2 way communication

1. [When to use them?](https://aws.amazon.com/blogs/compute/simple-two-way-messaging-using-the-amazon-sqs-temporary-queue-client/)
2. [Best practice: Use temporary queues](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-3/)

# 4. Sustainability Pillar

## 4.1. Use long polling

# 5. References

1. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 1 By Chetan Makvana and Hardik Vasa](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-1/)
2. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 2 By Chetan Makvana and Hardik Vasa](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-2/)