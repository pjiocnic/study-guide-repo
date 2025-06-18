## 📘 Goal

> Browse (read) all messages in an SQS queue **without deleting** them.

---

## 1️⃣ Shell Script Version (Using AWS CLI)

### 🔧 Prerequisites:

* Install and configure AWS CLI:
  `aws configure`

### 📝 Script: `browse_sqs.sh`

```bash
#!/bin/bash

QUEUE_URL="https://sqs.<region>.amazonaws.com/<account-id>/<queue-name>"
VISIBILITY_TIMEOUT=10
WAIT_SECONDS=5
MAX_MESSAGES=10

echo "Browsing messages from $QUEUE_URL"
while true; do
  RESPONSE=$(aws sqs receive-message \
    --queue-url "$QUEUE_URL" \
    --max-number-of-messages $MAX_MESSAGES \
    --wait-time-seconds $WAIT_SECONDS \
    --visibility-timeout $VISIBILITY_TIMEOUT)

  MSG_COUNT=$(echo "$RESPONSE" | jq '.Messages | length')

  if [ "$MSG_COUNT" -eq 0 ]; then
    echo "No more messages retrieved."
    break
  fi

  echo "$RESPONSE" | jq -r '.Messages[] | "\(.MessageId): \(.Body | @json)"'
done
```

> 🧠 Tip: Requires [`jq`](https://stedolan.github.io/jq/) for JSON parsing.

---

## 2️⃣ Java Version (AWS SDK v2)

### 🔧 Maven Dependency

```xml
<dependency>
  <groupId>software.amazon.awssdk</groupId>
  <artifactId>sqs</artifactId>
  <version>2.25.1</version> <!-- or latest -->
</dependency>
```

### 🧾 Java Code: `SqsBrowser.java`

```java
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.List;

public class SqsBrowser {
    public static void main(String[] args) {
        String queueUrl = "https://sqs.<region>.amazonaws.com/<account-id>/<queue-name>";
        int visibilityTimeout = 10;

        try (SqsClient sqsClient = SqsClient.create()) {
            while (true) {
                ReceiveMessageRequest request = ReceiveMessageRequest.builder()
                        .queueUrl(queueUrl)
                        .maxNumberOfMessages(10)
                        .waitTimeSeconds(5)
                        .visibilityTimeout(visibilityTimeout)
                        .build();

                List<Message> messages = sqsClient.receiveMessage(request).messages();

                if (messages.isEmpty()) {
                    System.out.println("No more messages.");
                    break;
                }

                for (Message msg : messages) {
                    System.out.printf("MessageId: %s\nBody: %s\n\n", msg.messageId(), msg.body());
                }
            }
        }
    }
}
```

---

## 3️⃣ DLQ-Based Inspection Setup

### 🎯 Why Use a DLQ?

You want to force all messages to a separate queue where you can inspect them at leisure.

### 📋 Step-by-Step Guide:

#### ✅ A. Create a DLQ (Dead Letter Queue)

```bash
aws sqs create-queue --queue-name my-inspection-dlq
```

#### ✅ B. Attach Redrive Policy to Main Queue

```bash
aws sqs set-queue-attributes \
  --queue-url https://sqs.<region>.amazonaws.com/<acct-id>/<main-queue> \
  --attributes '{"RedrivePolicy":"{\"deadLetterTargetArn\":\"arn:aws:sqs:<region>:<acct-id>:my-inspection-dlq\",\"maxReceiveCount\":\"1\"}"}'
```

#### ✅ C. Run Your Consumer with No Deletion

Now run your Java or shell script **without calling deleteMessage**. Each message is delivered once, not deleted, and will go to the DLQ after `maxReceiveCount` is exceeded.

#### ✅ D. Browse the DLQ Like Any Queue

Use the same shell or Java code, but point it to the **DLQ URL**:

```bash
QUEUE_URL="https://sqs.<region>.amazonaws.com/<acct-id>/my-inspection-dlq"
```

#### 🔁 Optional: Move Messages Back

```bash
# For each DLQ message
aws sqs send-message --queue-url <original-queue-url> --message-body "<msg-body>"
```

---

## ✅ Summary

| Task                        | Tool           | Notes                         |
| --------------------------- | -------------- | ----------------------------- |
| Browse messages (no delete) | Shell / Java   | Uses short visibility timeout |
| Safe inspection             | DLQ            | Redirect unprocessed messages |
| Restore messages            | `send-message` | Manual replay possible        |

---

Would you like:

* A Dockerized version of the Java consumer?
* The DLQ setup converted into Terraform or CloudFormation?
