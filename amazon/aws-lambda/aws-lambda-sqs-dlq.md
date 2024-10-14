[How to reprocess Lambda dead-letter queue messages on-demand By Yan Cui](https://theburningmonk.com/2024/01/how-would-you-reprocess-lambda-dead-letter-queue-messages-on-demand)
- Using StartMessageMoveTask

# Additional Material
1. https://www.ranthebuilder.cloud/post/amazon-sqs-dead-letter-queues-and-failures-handling-best-practices
1. [A New Set of APIs for Amazon SQS Dead-Letter Queue Redrive by Sébastien Stormacq](https://aws.amazon.com/blogs/aws/a-new-set-of-apis-for-amazon-sqs-dead-letter-queue-redrive/)

# SAM Template for Reprocessing DLQ Messages with Java

SAM Template (template.yaml)

```yaml
AWSTemplateFormatVersion: '2010-09-09'
Transform: AWS::Serverless-2016-10-31
Description: SAM template for reprocessing DLQ messages with Java

Resources:
  MainProcessingFunction:
    Type: AWS::Serverless::Function
    Properties:
      Handler: com.example.MainProcessor::handleRequest
      Runtime: java11
      CodeUri: ./target/lambda.jar
      Events:
        SQSEvent:
          Type: SQS
          Properties:
            Queue: !GetAtt MainQueue.Arn

  DeadLetterQueue:
    Type: AWS::SQS::Queue

  MainQueue:
    Type: AWS::SQS::Queue
    Properties:
      RedrivePolicy:
        deadLetterTargetArn: !GetAtt DeadLetterQueue.Arn
        maxReceiveCount: 3

  ReprocessingFunction:
    Type: AWS::Serverless::Function
    Properties:
      Handler: com.example.Reprocessor::handleRequest
      Runtime: java11
      CodeUri: ./target/lambda.jar
      Policies:
        - SQSPollerPolicy:
            QueueName: !GetAtt DeadLetterQueue.QueueName
        - SQSSendMessagePolicy:
            QueueName: !GetAtt MainQueue.QueueName
      Environment:
        Variables:
          MAIN_QUEUE_URL: !Ref MainQueue
          DLQ_URL: !Ref DeadLetterQueue

  ReprocessingFunctionRole:
    Type: AWS::IAM::Role
    Properties:
      AssumeRolePolicyDocument:
        Version: '2012-10-17'
        Statement:
          - Effect: Allow
            Principal:
              Service: lambda.amazonaws.com
            Action: sts:AssumeRole
      Policies:
        - PolicyName: SQSAccess
          PolicyDocument:
            Version: '2012-10-17'
            Statement:
              - Effect: Allow
                Action:
                  - sqs:ReceiveMessage
                  - sqs:DeleteMessage
                  - sqs:GetQueueAttributes
                  - sqs:ChangeMessageVisibility
                  - sqs:StartMessageMoveTask
                Resource:
                  - !GetAtt DeadLetterQueue.Arn
                  - !GetAtt MainQueue.Arn

Outputs:
  MainQueueURL:
    Description: "URL of the main SQS queue"
    Value: !Ref MainQueue
  DeadLetterQueueURL:
    Description: "URL of the Dead Letter Queue"
    Value: !Ref DeadLetterQueue
  ReprocessingFunctionName:
    Description: "Name of the reprocessing Lambda function"
    Value: !Ref ReprocessingFunction
```

Java Implementation for Reprocessing Function

```java
package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.StartMessageMoveTaskRequest;
import com.amazonaws.services.sqs.model.StartMessageMoveTaskResult;

import java.util.Map;

public class Reprocessor implements RequestHandler<Map<String, String>, String> {

    private final AmazonSQS sqsClient;
    private final String mainQueueUrl;
    private final String dlqUrl;

    public Reprocessor() {
        this.sqsClient = AmazonSQSClientBuilder.defaultClient();
        this.mainQueueUrl = System.getenv("MAIN_QUEUE_URL");
        this.dlqUrl = System.getenv("DLQ_URL");
    }

    @Override
    public String handleRequest(Map<String, String> input, Context context) {
        try {
            StartMessageMoveTaskRequest request = new StartMessageMoveTaskRequest()
                .withSourceArn(dlqUrl)
                .withDestinationArn(mainQueueUrl)
                .withMaxNumberOfMessagesPerSecond(50);

            StartMessageMoveTaskResult result = sqsClient.startMessageMoveTask(request);

            return "Message move task started. Task ID: " + result.getTaskHandle();
        } catch (Exception e) {
            context.getLogger().log("Error starting message move task: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }
}
```

This Java implementation does the following:

1. It uses the AWS SDK for Java to interact with SQS.

2. The Reprocessor class implements RequestHandler, which is the interface for Lambda functions in Java.

3. In the constructor, it initializes the SQS client and retrieves the queue URLs from environment variables.

4. The handleRequest method is called when the Lambda function is invoked.

5. It creates a StartMessageMoveTaskRequest to move messages from the DLQ back to the main queue.

6. The maxNumberOfMessagesPerSecond is set to 50, but you can adjust this based on your requirements.

7. It calls startMessageMoveTask and returns the task ID.

To use this:

1. Compile your Java code and create a JAR file.

2. Update the CodeUri in the SAM template to point to your JAR file.

3. Deploy the SAM template.

You would also need to implement the `MainProcessor` class for the main processing function, which would handle the actual processing of messages from the main queue.

Remember to include the necessary dependencies in your pom.xml or build.gradle file, particularly the AWS Java SDK for SQS.

This setup allows you to reprocess messages from the Dead Letter Queue on-demand by invoking the `ReprocessingFunction`.

# Batch Reprocessing

```java
package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.StartMessageMoveTaskRequest;
import com.amazonaws.services.sqs.model.StartMessageMoveTaskResult;
import com.amazonaws.services.sqs.model.ListMessageMoveTasksRequest;
import com.amazonaws.services.sqs.model.ListMessageMoveTasksResult;
import com.amazonaws.services.sqs.model.MessageMoveTaskState;

import java.util.Map;

public class Reprocessor implements RequestHandler<Map<String, String>, String> {

    private final AmazonSQS sqsClient;
    private final String mainQueueUrl;
    private final String dlqUrl;

    public Reprocessor() {
        this.sqsClient = AmazonSQSClientBuilder.defaultClient();
        this.mainQueueUrl = System.getenv("MAIN_QUEUE_URL");
        this.dlqUrl = System.getenv("DLQ_URL");
    }

    @Override
    public String handleRequest(Map<String, String> input, Context context) {
        try {
            // Start the message move task
            StartMessageMoveTaskResult startResult = startMessageMoveTask();
            String taskId = startResult.getTaskHandle();

            // Monitor the task progress
            boolean isCompleted = false;
            while (!isCompleted) {
                Thread.sleep(5000); // Wait for 5 seconds before checking again
                isCompleted = checkTaskStatus(taskId);
            }

            return "Message move task completed. Task ID: " + taskId;
        } catch (Exception e) {
            context.getLogger().log("Error processing DLQ messages: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }

    private StartMessageMoveTaskResult startMessageMoveTask() {
        StartMessageMoveTaskRequest request = new StartMessageMoveTaskRequest()
            .withSourceArn(dlqUrl)
            .withDestinationArn(mainQueueUrl)
            .withMaxNumberOfMessagesPerSecond(50);

        return sqsClient.startMessageMoveTask(request);
    }

    private boolean checkTaskStatus(String taskId) {
        ListMessageMoveTasksRequest listRequest = new ListMessageMoveTasksRequest()
            .withQueueUrl(dlqUrl);

        ListMessageMoveTasksResult listResult = sqsClient.listMessageMoveTasks(listRequest);

        for (MessageMoveTaskState task : listResult.getMessageMoveTasks()) {
            if (task.getTaskHandle().equals(taskId)) {
                if ("Completed".equals(task.getStatus())) {
                    System.out.println("Task completed. Messages moved: " + task.getApproximateNumberOfMessagesMoved());
                    return true;
                } else if ("Failed".equals(task.getStatus())) {
                    System.out.println("Task failed. Reason: " + task.getFailureReason());
                    return true;
                } else {
                    System.out.println("Task in progress. Status: " + task.getStatus());
                    return false;
                }
            }
        }

        System.out.println("Task not found. It may have completed and been removed from the list.");
        return true;
    }
}
```

This enhanced implementation does the following:

It starts a message move task using StartMessageMoveTask, which initiates the batch processing of messages from the DLQ to the main queue. [2]

After starting the task, it periodically checks the status of the task using ListMessageMoveTasks.

It continues to monitor the task until it's completed or failed.

It provides feedback on the number of messages moved or the reason for failure.

This approach allows for efficient batch processing of messages from the DLQ. The MaxNumberOfMessagesPerSecond parameter in the StartMessageMoveTaskRequest controls the rate at which messages are moved, allowing you to manage the load on your system.

To use this in your SAM template, you would need to ensure that the IAM role for the Lambda function includes permissions for the sqs:ListMessageMoveTasks action in addition to the permissions we had before.

Here's how you might modify the ReprocessingFunctionRole in your SAM template:

```yaml
ReprocessingFunctionRole:
  Type: AWS::IAM::Role
  Properties:
    AssumeRolePolicyDocument:
      Version: '2012-10-17'
      Statement:
        - Effect: Allow
          Principal:
            Service: lambda.amazonaws.com
          Action: sts:AssumeRole
    Policies:
      - PolicyName: SQSAccess
        PolicyDocument:
          Version: '2012-10-17'
          Statement:
            - Effect: Allow
              Action:
                - sqs:ReceiveMessage
                - sqs:DeleteMessage
                - sqs:GetQueueAttributes
                - sqs:ChangeMessageVisibility
                - sqs:StartMessageMoveTask
                - sqs:ListMessageMoveTasks
              Resource:
                - !GetAtt DeadLetterQueue.Arn
                - !GetAtt MainQueue.Arn
```

This implementation provides a more robust way to handle batch processing of DLQ messages, with better monitoring and error handling capabilities.

**Sources**
1. [Using dead-letter queues in Amazon SQS](https://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/sqs-dead-letter-queues.html)