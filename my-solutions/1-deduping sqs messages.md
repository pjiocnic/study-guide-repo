1. Following code is used to bake


```java
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class SQSDeduplicationExample {

    private static final String QUEUE_URL = "YOUR_SQS_QUEUE_URL";
    private static final String TABLE_NAME = "UniqueMessages";

    private static final AmazonSQS sqs = AmazonSQSClientBuilder.standard().build();
    private static final AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.standard().build();
    private static final DynamoDB dynamoDB = new DynamoDB(dynamoDBClient);
    private static final Table dynamoTable = dynamoDB.getTable(TABLE_NAME);

    public static void main(String[] args) {
        while (true) {
            processMessages();
        }
    }

    private static void processMessages() {
        ReceiveMessageRequest receiveRequest = new ReceiveMessageRequest()
                .withQueueUrl(QUEUE_URL)
                .withMaxNumberOfMessages(10); // Adjust as needed

        List<Message> messages = sqs.receiveMessage(receiveRequest).getMessages();
        for (Message message : messages) {
            String messageBody = message.getBody();
            String messageId = generateMessageId(messageBody);

            // Check if the message ID exists in DynamoDB
            Item item = dynamoTable.getItem("MessageId", messageId);
            if (item == null) {
                // Process the message
                System.out.println("Processing message: " + messageBody);

                // Add message ID to DynamoDB to mark as processed
                dynamoTable.putItem(new Item().withString("MessageId", messageId));

                // Delete the message from SQS queue
                sqs.deleteMessage(new DeleteMessageRequest()
                        .withQueueUrl(QUEUE_URL)
                        .withReceiptHandle(message.getReceiptHandle()));
            } else {
                System.out.println("Skipping duplicate message: " + messageBody);
            }
        }
    }

    private static String generateMessageId(String messageBody) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(messageBody.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
```

- The cooler ..using Lambda

```java
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;

import java.util.List;

public class DynamoDBStreamProcessor implements RequestHandler<DynamodbEvent, Void> {

    private static final long DEDUPLICATION_WINDOW = 600000; // 10 minutes in milliseconds

    @Override
    public Void handleRequest(DynamodbEvent event, Context context) {
        for (DynamodbEvent.DynamodbStreamRecord record : event.getRecords()) {
            long currentTimestamp = System.currentTimeMillis();
            long recordTimestamp = record.getDynamodb().getApproximateCreationDateTime().getTime();
            long timeElapsed = currentTimestamp - recordTimestamp;

            // Check if the message has not been deduplicated for the specified duration
            if (timeElapsed >= DEDUPLICATION_WINDOW) {
                // Forward the message to the SQS queue
                forwardMessageToSQS(record);
            }
        }
        return null;
    }

    private void forwardMessageToSQS(DynamodbEvent.DynamodbStreamRecord record) {
        // Implement logic to forward the message to the SQS queue
    }
}
```

- The cooler using pipes

```bash
aws events put-rule \
  --name DeduplicationRule \
  --event-pattern '{
      "source": ["aws.dynamodb"],
      "detail-type": ["Dynamo DB Stream Event"],
      "detail": {
        "eventSourceARN": ["YOUR_DYNAMODB_STREAM_ARN"]
      }
  }' \
  --schedule-expression 'cron(0/5 * * * ? *)'  # Trigger every 5 minutes

aws events put-targets \
  --rule DeduplicationRule \
  --targets '[{
      "arn": "YOUR_SQS_QUEUE_ARN",
      "id": "1",
      "batchParameters": {
        "jobDefinition": {
          "maxRetryAttempts": 1,
          "retryStrategy": "EXPONENTIAL"
        },
        "jobAttempts": 1
      }
  }]'
```