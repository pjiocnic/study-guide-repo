# 1. FIFO Queues (both SNS and SQS)

1. [[Deduplication Id] Deduplicating SNS and SQS messages using AWS’s new FIFO SNS Topics and a FIFO SQS Queue](https://medium.com/build-succeeded/deduplicating-sns-and-sqs-messages-using-awss-new-fifo-sns-topics-and-a-fifo-sqs-queue-df0e11fc2f0d)
- [Justin Pertle's example on how Deduplication Id works](https://youtu.be/8zysQqxgj0I?t=1053)

# 2. Exactly once processing

1. [Justin Pirtle's explanation of what's exactly once processing](https://youtu.be/8zysQqxgj0I?t=1004)

# 3. Need for message groups in FIFO queue

1. [Justin Pirtle's explanation on why message groups are needed](https://youtu.be/8zysQqxgj0I?t=914)

# 4. For FIFO queue - How does Poller pull messages in order

1. [Justin Pirtle's explanation on how pollers work with FIFO queue](https://youtu.be/8zysQqxgj0I?t=864)

# 5. Rate Limiting

2. [Why Rate Limiting is needed](https://youtu.be/8zysQqxgj0I?t=645)
1. [Justin Pirtle's explanation on how Rate Limiting is done with SQS](https://youtu.be/8zysQqxgj0I?t=677)

# 6. Concurrency with SQS and SNS

1. [Lambda function concurrency across SQS/SNS](https://youtu.be/8zysQqxgj0I?t=602)

# 7. How Lambda works with SNS

1. [](https://youtu.be/8zysQqxgj0I?t=366)

# Error Handling

1. [How does SQS error handling take place in case of Function errors](https://youtu.be/8zysQqxgj0I?t=1263)
2. [Implementing AWS Lambda error handling patterns by Julian Wood, Jeff Chen, and Jeff Li](https://aws.amazon.com/blogs/compute/implementing-aws-lambda-error-handling-patterns/)

# Redrive

1. [Where do you setup redrive Policy](https://aws.amazon.com/blogs/compute/introducing-amazon-simple-queue-service-dead-letter-queue-redrive-to-source-queues/)

# Message expiration

1. Whats the message retention policy for SQS vs SNS?
2. https://blog.serverlessq.com/aws-sns-vs-sqs-what-are-the-main-differences

# Best practices for Lamba

1. [Amazon SQS to Lambda: Best practices and tips Queue configuration](https://youtu.be/8zysQqxgj0I?t=1717)