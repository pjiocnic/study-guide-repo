# DLQs - What are the different ways of setting up DLQs

1. [[**START_HERE**] Designing durable serverless apps with DLQs for Amazon SNS, Amazon SQS, AWS Lambda By Otavio Ferreira](https://aws.amazon.com/blogs/compute/designing-durable-serverless-apps-with-dlqs-for-amazon-sns-amazon-sqs-aws-lambda/)
- Use Project from SNS Message Filters to setup DLQs for SNS Topic when filter fails, `https://github.com/aws-samples/aws-sns-samples/tree/master` and `https://aws.amazon.com/blogs/compute/introducing-payload-based-message-filtering-for-amazon-sns/`
1. [[**MY_NEXT**] Implementing AWS Lambda error handling patterns by Julian Wood, Jeff Chen, and Jeff Li ](https://aws.amazon.com/blogs/compute/implementing-aws-lambda-error-handling-patterns/)
1. [[**MY_NEXT**] Using Amazon SQS dead-letter queues to replay messages By Alexandre Pinhel](https://aws.amazon.com/blogs/compute/using-amazon-sqs-dead-letter-queues-to-replay-messages/)
1. [How do dead-letter queues work](https://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/sqs-dead-letter-queues.html)
1. [DLQ with AWS Lambda By Thiwanka Wickramage](https://thiwankawickramage.medium.com/dlq-with-aws-lambda-ab7c080b6f5a)
1. [AWS Lambda: Ultimate Guide to Error Handling By Yan Cui](https://www.youtube.com/watch?v=GK-z1n2E3BQ)
1. [Implementing AWS Lambda error handling patterns](https://aws.amazon.com/blogs/compute/implementing-aws-lambda-error-handling-patterns/)
1. [Durable Serverless Architectures: Working with Dead-Letter Queues API309 By Otavio Ferreira](https://d1.awsstatic.com/events/reinvent/2019/Durable_serverless_architecture_Working_with_dead-letter_queues_API309.pdf)
1. [Asynchronous invocation](https://docs.aws.amazon.com/lambda/latest/dg/invocation-async.html)
1. [[SNS SUBSCRIPTION] Configuring an Amazon SNS dead-letter queue for a subscription](https://docs.aws.amazon.com/sns/latest/dg/sns-configure-dead-letter-queue.html)
1. [[MAKE NOTES] Amazon SNS dead-letter queues (DLQs)](https://docs.aws.amazon.com/sns/latest/dg/sns-dead-letter-queues.html)
1. [[SQS] DLQ for SQS](https://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/sqs-dead-letter-queues.html)

# Other Topics

1. [How to setup Jitters](../aws-lambda.md#1221-exponential-backoff-and-jitter-algorithm)

1. [**[MY_NEXT]** Implementing AWS Lambda error handling patterns by Julian Wood, Jeff Chen, and Jeff Li ](https://aws.amazon.com/blogs/compute/implementing-aws-lambda-error-handling-patterns/)
1. [**[MY_NEXT]** Using Amazon SQS dead-letter queues to replay messages By Alexandre Pinhel](https://aws.amazon.com/blogs/compute/using-amazon-sqs-dead-letter-queues-to-replay-messages/)

# Sample code

1. https://gist.github.com/austoonz/f6d45d5f22b4944df42ca80ed4e2d819

2. The SAM Template equivalent for `https://github.com/aws-samples/aws-lambda-error-handling-pattern/blob/main/dlq/core_lambda.py` as given by ChatGPT

```yaml
AWSTemplateFormatVersion: '2010-09-09'
Transform: AWS::Serverless-2016-10-31

Resources:
  MyLambdaFunction:
    Type: AWS::Serverless::Function
    Properties:
      Handler: app.lambda_handler  # Adjust the handler as per your function
      Runtime: python3.8  # Adjust the runtime as per your function
      CodeUri: ./
      Events:
        SNSEvent:
          Type: SNS
          Properties:
            Topic:
              Fn::ImportValue: YourSNSTopicExportName  # Import the SNS topic export name
            DeadLetterQueue:
              Type: SQS
              Queue:
                Fn::GetAtt:
                  - SNSDeadLetterQueue
                  - QueueName

  SNSDeadLetterQueue:
    Type: AWS::SQS::Queue

Outputs:
  LambdaFunctionArn:
    Value:
      Fn::GetAtt:
        - MyLambdaFunction
        - Arn
    Export:
      Name: YourLambdaFunctionExportName

  SNSDeadLetterQueueUrl:
    Value:
      Fn::GetAtt:
        - SNSDeadLetterQueue
        - QueueUrl
    Export:
      Name: YourSNSDeadLetterQueueExportName

```

Another template

```yaml
AWSTemplateFormatVersion: '2010-09-09'
Description: Java Application on EC2 Reading from SQS Queue with DLQ

Parameters:
  KeyName:
    Type: AWS::EC2::KeyPair::KeyName
    Description: Name of an existing EC2 Key Pair for SSH access

Resources:
  EC2Instance:
    Type: AWS::EC2::Instance
    Properties:
      ImageId: ami-xxxxxxxxxxxxxxxxx  # Specify an appropriate AMI ID
      InstanceType: t2.micro          # Specify an appropriate instance type
      KeyName: !Ref KeyName
      UserData:
        Fn::Base64: !Sub |
          #!/bin/bash
          yum update -y
          yum install -y java-11-openjdk

          # Download and run your Java application JAR file
          wget -O /home/ec2-user/your-application.jar s3://your-s3-bucket/your-application.jar
          java -jar /home/ec2-user/your-application.jar

  YourSQSQueue:
    Type: AWS::SQS::Queue
    Properties:
      QueueName: YourQueueName
      RedrivePolicy:
        deadLetterTargetArn:
          Fn::GetAtt:
            - "YourDLQ"
            - "Arn"
        maxReceiveCount: 5

  YourDLQ:
    Type: AWS::SQS::Queue
    Properties:
      QueueName: YourDLQName

  SQSQueuePolicy:
    Type: AWS::SQS::QueuePolicy
    Properties:
      Queues:
        - !Ref YourSQSQueue
      PolicyDocument:
        Version: '2012-10-17'
        Statement:
          - Effect: Allow
            Principal:
              Service: ec2.amazonaws.com
            Action: sqs:SendMessage
            Resource: !GetAtt YourSQSQueue.Arn

Outputs:
  InstanceId:
    Description: Instance ID of the created EC2 instance
    Value: !Ref EC2Instance
  SQSQueueUrl:
    Description: URL of the created SQS queue
    Value: !GetAtt YourSQSQueue.QueueUrl
  DLQUrl:
    Description: URL of the created DLQ
    Value: !GetAtt YourDLQ.QueueUrl
```

Another script

```yaml
AWSTemplateFormatVersion: '2010-09-09'
Description: SNS Topic with SQS Subscriptions and DLQs

Parameters:
  SNSDisplayName:
    Type: String
    Description: Display name for the SNS topic

Resources:
  SNSTopic:
    Type: AWS::SNS::Topic
    Properties:
      DisplayName: !Ref SNSDisplayName
      TopicName: YourSNSTopicName
      RedrivePolicy:
        deadLetterTargetArn:
          Fn::GetAtt:
            - "DLQForSNSTopic"
            - "Arn"
        maxReceiveCount: 5

  DLQForSNSTopic:
    Type: AWS::SQS::Queue
    Properties:
      QueueName: YourDLQForSNSTopicName

  SubscriptionOneQueue:
    Type: AWS::SQS::Queue
    Properties:
      QueueName: YourSubscriptionOneQueueName
      RedrivePolicy:
        deadLetterTargetArn:
          Fn::GetAtt:
            - "DLQForSubscriptionOne"
            - "Arn"
        maxReceiveCount: 5

  SubscriptionTwoQueue:
    Type: AWS::SQS::Queue
    Properties:
      QueueName: YourSubscriptionTwoQueueName
      RedrivePolicy:
        deadLetterTargetArn:
          Fn::GetAtt:
            - "DLQForSubscriptionTwo"
            - "Arn"
        maxReceiveCount: 5

  DLQForSubscriptionOne:
    Type: AWS::SQS::Queue
    Properties:
      QueueName: YourDLQForSubscriptionOneName

  DLQForSubscriptionTwo:
    Type: AWS::SQS::Queue
    Properties:
      QueueName: YourDLQForSubscriptionTwoName

  FilterPolicyForSubscriptionOne:
    Type: AWS::SNS::Subscription
    Properties:
      Protocol: sqs
      TopicArn: !Ref SNSTopic
      Endpoint: !GetAtt SubscriptionOneQueue.Arn
      RedrivePolicy:
        deadLetterTargetArn:
          Fn::GetAtt:
            - "DLQForSubscriptionOne"
            - "Arn"
        maxReceiveCount: 5
      FilterPolicy:
        MyFilterPolicyAttribute: ["filter-value-one"]

  FilterPolicyForSubscriptionTwo:
    Type: AWS::SNS::Subscription
    Properties:
      Protocol: sqs
      TopicArn: !Ref SNSTopic
      Endpoint: !GetAtt SubscriptionTwoQueue.Arn
      RedrivePolicy:
        deadLetterTargetArn:
          Fn::GetAtt:
            - "DLQForSubscriptionTwo"
            - "Arn"
        maxReceiveCount: 5
      FilterPolicy:
        MyFilterPolicyAttribute: ["filter-value-two"]

Outputs:
  SNSTopicArn:
    Description: ARN of the created SNS Topic
    Value: !Ref SNSTopic
  SubscriptionOneQueueUrl:
    Description: URL of the Subscription One SQS Queue
    Value: !GetAtt SubscriptionOneQueue.QueueUrl
  SubscriptionTwoQueueUrl:
    Description: URL of the Subscription Two SQS Queue
    Value: !GetAtt SubscriptionTwoQueue.QueueUrl
  DLQForSNSTopicUrl:
    Description: URL of the DLQ for the SNS Topic
    Value: !GetAtt DLQForSNSTopic.QueueUrl




```