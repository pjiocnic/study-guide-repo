<h1>888-Message-sequencing without FIFO</h1>

Yes, you can implement message ordering and exactly-once processing using standard SQS and DynamoDB.

Here's how to achieve FIFO-like behavior:

### Implementation Pattern

```py
import boto3
import time
from boto3.dynamodb.conditions import Attr
from datetime import datetime

class MessageOrchestrator:
    def __init__(self):
        self.sqs = boto3.client('sqs')
        self.dynamodb = boto3.resource('dynamodb')
        self.message_table = self.dynamodb.Table('message_tracking')

    def send_message(self, group_id, message_body, sequence_number):
        """
        Send message with ordering metadata
        """
        message = {
            'group_id': group_id,
            'sequence_number': sequence_number,
            'timestamp': int(time.time() * 1000),
            'body': message_body
        }

        response = self.sqs.send_message(
            QueueUrl='YOUR_QUEUE_URL',
            MessageBody=str(message)
        )

        return response

    def process_message(self, message_group_id):
        """
        Process messages in order for a specific group
        """
        while True:
            # Get the last processed sequence number
            last_processed = self._get_last_processed_sequence(message_group_id)

            # Receive messages from SQS
            response = self.sqs.receive_message(
                QueueUrl='YOUR_QUEUE_URL',
                MaxNumberOfMessages=10,
                WaitTimeSeconds=20
            )

            if 'Messages' not in response:
                break

            messages = response['Messages']
            for message in sorted(messages,
                               key=lambda x: eval(x['Body'])['sequence_number']):
                message_data = eval(message['Body'])

                # Process only if it's the next in sequence
                if (message_data['group_id'] == message_group_id and
                    message_data['sequence_number'] == last_processed + 1):

                    try:
                        # Process message
                        self._process_single_message(message_data)

                        # Update sequence tracking
                        self._update_sequence_number(
                            message_data['group_id'],
                            message_data['sequence_number']
                        )

                        # Delete from queue
                        self.sqs.delete_message(
                            QueueUrl='YOUR_QUEUE_URL',
                            ReceiptHandle=message['ReceiptHandle']
                        )

                    except Exception as e:
                        print(f"Error processing message: {e}")
                        # Message will return to queue after visibility timeout
                        continue

    def _get_last_processed_sequence(self, group_id):
        """
        Get last processed sequence number from DynamoDB
        """
        response = self.message_table.get_item(
            Key={'group_id': group_id}
        )
        return response.get('Item', {}).get('last_sequence', 0)

    def _update_sequence_number(self, group_id, sequence_number):
        """
        Update the last processed sequence number
        """
        self.message_table.put_item(
            Item={
                'group_id': group_id,
                'last_sequence': sequence_number,
                'updated_at': datetime.utcnow().isoformat()
            }
        )

    def _process_single_message(self, message_data):
        """
        Process individual message (implement your logic here)
        """
        print(f"Processing message: {message_data}")
        # Add your processing logic here
```

### DynamoDB Table Structure

```yaml
TableName: message_tracking
AttributeDefinitions:
  - AttributeName: group_id
    AttributeType: S
KeySchema:
  - AttributeName: group_id
    KeyType: HASH
Attributes:
  - last_sequence: number
  - updated_at: string
```

### Usage Example

```py
# Initialize orchestrator
orchestrator = MessageOrchestrator()

# Send messages
orchestrator.send_message('order-123', {'action': 'create'}, 1)
orchestrator.send_message('order-123', {'action': 'update'}, 2)
orchestrator.send_message('order-123', {'action': 'complete'}, 3)

# Process messages for group
orchestrator.process_message('order-123')
```

### Key Features of This Implementation

1. Message Grouping :

* Messages are grouped using group_id
* Each group maintains its own sequence

2. Ordered Processing :

* Uses sequence numbers to ensure order
* Tracks last processed sequence in DynamoDB
* Only processes next message in sequence

3. Deduplication :

* DynamoDB tracking prevents duplicate processing
* Sequence numbers help identify duplicates

4. Error Handling :

* Failed messages return to queue
* Visibility timeout controls retry timing

5. Scalability :

* Different groups can process in parallel
* Standard SQS provides high throughput

### Limitations

1. Head-of-line blocking within groups
2. No built-in exactly-once guarantee
3. Requires additional DynamoDB operations
4. Higher latency compared to SQS FIFO

### Best Practices

1. Error Handling :

```py
def safe_process_message(self, group_id):
    try:
        self.process_message(group_id)
    except Exception as e:
        # Log error and implement retry logic
        print(f"Error processing group {group_id}: {e}")
```

2. Monitoring :

```py
def _update_sequence_number(self, group_id, sequence_number):
    self.message_table.put_item(
        Item={
            'group_id': group_id,
            'last_sequence': sequence_number,
            'updated_at': datetime.utcnow().isoformat(),
            'processing_stats': {
                'total_processed': 0,
                'last_processed_timestamp': datetime.utcnow().isoformat()
            }
        }
    )
```

3. Dead Letter Queue Integration :

```py
def _move_to_dlq(self, message):
    self.sqs.send_message(
        QueueUrl='YOUR_DLQ_URL',
        MessageBody=str({
            'original_message': message,
            'error_timestamp': datetime.utcnow().isoformat(),
            'reason': 'Processing failed after max retries'
        })
    )
```

This implementation provides a robust alternative to SQS FIFO when you need message ordering but want to use standard SQS for higher throughput or other requirements.