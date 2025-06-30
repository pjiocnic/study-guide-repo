Great question — yes, you can use the **AWS CLI** to **automate checking and setting SQS Dead Letter Queue (DLQ) configurations**. Below is a **fully-commented Bash script** that:

✅ Loops through all SQS queues in your account.
✅ Retrieves Dead Letter Queue (DLQ) config (`MaximumReceives`).
✅ Retrieves `VisibilityTimeout`.
✅ Retrieves `MessageRetentionPeriod`.
✅ Sets them if they’re missing or insufficient.

You can adjust the thresholds in the variables at the top of the script.

---

### ⚠️ **Prerequisites**

* AWS CLI installed and configured (`aws configure`)
* Bash shell

---

### 📜 Example AWS CLI Bash script:

```bash
#!/bin/bash

# ==================== Configuration ====================
# Adjust as per your policy
DESIRED_MAX_RECEIVES=10
MIN_VISIBILITY_TIMEOUT=60         # in seconds
MIN_MESSAGE_RETENTION_PERIOD=345600 # 4 days = 345600s

# ========================================================

echo "Fetching list of SQS queues..."
for queue_url in $(aws sqs list-queues --query "QueueUrls[]" --output text); do
  echo "--------------------------------------------"
  echo "Checking queue: $queue_url"

  # Fetch the DeadLetterTargetArn
  redrive_policy=$(aws sqs get-queue-attributes --queue-url "$queue_url" --attribute-name RedrivePolicy --output json --query "Attributes.RedrivePolicy" --output text 2>/dev/null)

  if [[ "$redrive_policy" == "None" || -z "$redrive_policy" ]]; then
    echo "  ❌ No Dead Letter Queue configured."
    continue
  else
    echo "  ✅ Dead Letter Policy exists."
  fi

  # Parse the RedrivePolicy JSON
  dead_letter_arn=$(echo "$redrive_policy" | jq -r '.deadLetterTargetArn')
  max_receive_count=$(echo "$redrive_policy" | jq -r '.maxReceiveCount')
  echo "  📜 Dead Letter Target ARN: $dead_letter_arn"
  echo "  🔄 Current Max Receive Count: $max_receive_count"

  # Check Max Receive Count
  if [[ $max_receive_count -lt $DESIRED_MAX_RECEIVES ]]; then
    echo "  ⚠️ MaxReceiveCount ($max_receive_count) is too low. Updating to $DESIRED_MAX_RECEIVES."
    new_policy=$(echo "$redrive_policy" | jq ".maxReceiveCount=$DESIRED_MAX_RECEIVES")
    aws sqs set-queue-attributes --queue-url "$queue_url" --attributes RedrivePolicy="$new_policy"
  else
    echo "  ✅ MaxReceiveCount OK."
  fi

  # Check Visibility Timeout
  visibility_timeout=$(aws sqs get-queue-attributes --queue-url "$queue_url" --attribute-name VisibilityTimeout --query "Attributes.VisibilityTimeout" --output text)
  if [[ $visibility_timeout -lt $MIN_VISIBILITY_TIMEOUT ]]; then
    echo "  ⚠️ VisibilityTimeout ($visibility_timeout) too low. Updating to $MIN_VISIBILITY_TIMEOUT."
    aws sqs set-queue-attributes --queue-url "$queue_url" --attributes VisibilityTimeout=$MIN_VISIBILITY_TIMEOUT
  else
    echo "  ✅ VisibilityTimeout OK."
  fi

  # Check MessageRetentionPeriod
  retention_period=$(aws sqs get-queue-attributes --queue-url "$queue_url" --attribute-name MessageRetentionPeriod --query "Attributes.MessageRetentionPeriod" --output text)
  if [[ $retention_period -lt $MIN_MESSAGE_RETENTION_PERIOD ]]; then
    echo "  ⚠️ MessageRetentionPeriod ($retention_period) too low. Updating to $MIN_MESSAGE_RETENTION_PERIOD."
    aws sqs set-queue-attributes --queue-url "$queue_url" --attributes MessageRetentionPeriod=$MIN_MESSAGE_RETENTION_PERIOD
  else
    echo "  ✅ MessageRetentionPeriod OK."
  fi

done

echo "--------------------------------------------"
echo "🎉 Done checking and applying settings!"
```

---

### 🎯 What this script does:

1. Retrieves all SQS queues.
2. Checks for Dead Letter Policy (`RedrivePolicy`).

   * Parses `deadLetterTargetArn` and `maxReceiveCount`.
   * If `maxReceiveCount < DESIRED_MAX_RECEIVES`, updates it.
3. Checks `VisibilityTimeout` and updates if too low.
4. Checks `MessageRetentionPeriod` and updates if too low.

---

💡 **Before running this script**:

* Make sure you have `jq` installed (`brew install jq` on Mac or `apt-get install jq` on Linux).
* Adjust the thresholds as per your policies.

---

✅ Would you also like a **PowerShell version** or a **Python version** of this script? Let me know — I can provide that too!

---

**Python version**

```py
# check_and_update_sqs.py
import json
import boto3

# ==================== Configuration ====================
DESIRED_MAX_RECEIVES = 10
MIN_VISIBILITY_TIMEOUT = 60         # in seconds
MIN_MESSAGE_RETENTION_PERIOD = 345600  # 4 days = 345600s
# ========================================================

sqs = boto3.client('sqs')

def check_and_update_queue(queue_url):
    print(f"\n--------------------------------------------")
    print(f"Checking queue: {queue_url}")

    # Get existing attributes
    attrs = sqs.get_queue_attributes(
        QueueUrl=queue_url,
        AttributeNames=['All']
    )['Attributes']

    redrive_policy_str = attrs.get('RedrivePolicy')
    if not redrive_policy_str:
        print("❌ No Dead Letter Queue configured.")
    else:
        redrive_policy = json.loads(redrive_policy_str)
        max_receive_count = int(redrive_policy.get('maxReceiveCount', 0))
        print(f"🔄 Current MaxReceiveCount: {max_receive_count}")

        # Update MaxReceiveCount if too low
        if max_receive_count < DESIRED_MAX_RECEIVES:
            print(f"⚠️ Updating MaxReceiveCount to {DESIRED_MAX_RECEIVES}.")
            redrive_policy['maxReceiveCount'] = DESIRED_MAX_RECEIVES
            sqs.set_queue_attributes(
                QueueUrl=queue_url,
                Attributes={'RedrivePolicy': json.dumps(redrive_policy)}
            )
        else:
            print("✅ MaxReceiveCount OK.")

    # Check VisibilityTimeout
    visibility_timeout = int(attrs.get('VisibilityTimeout', 30))
    if visibility_timeout < MIN_VISIBILITY_TIMEOUT:
        print(f"⚠️ Updating VisibilityTimeout to {MIN_VISIBILITY_TIMEOUT}.")
        sqs.set_queue_attributes(
            QueueUrl=queue_url,
            Attributes={'VisibilityTimeout': str(MIN_VISIBILITY_TIMEOUT)}
        )
    else:
        print("✅ VisibilityTimeout OK.")

    # Check MessageRetentionPeriod
    retention_period = int(attrs.get('MessageRetentionPeriod', 345600))
    if retention_period < MIN_MESSAGE_RETENTION_PERIOD:
        print(f"⚠️ Updating MessageRetentionPeriod to {MIN_MESSAGE_RETENTION_PERIOD}.")
        sqs.set_queue_attributes(
            QueueUrl=queue_url,
            Attributes={'MessageRetentionPeriod': str(MIN_MESSAGE_RETENTION_PERIOD)}
        )
    else:
        print("✅ MessageRetentionPeriod OK.")


def main():
    sqs = boto3.client('sqs')
    # Get all queue URLs
    response = sqs.list_queues()
    queue_urls = response.get('QueueUrls', [])
    if not queue_urls:
        print("No SQS queues found!")
        return

    for queue_url in queue_urls:
        check_and_update_queue(queue_url)

    print("\n🎉 Done checking and applying settings!")

if __name__ == "__main__":
    main()
```
