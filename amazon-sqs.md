<h1>Amazon SQS</h1>

<!-- TOC -->

- [1. All about SQS](#1-all-about-sqs)
- [2. Deduplication](#2-deduplication)
- [3. SQS FIFO](#3-sqs-fifo)
- [4. Well Architected](#4-well-architected)
- [5. Scaling](#5-scaling)
- [6. DLQs](#6-dlqs)
- [7. Temporary Queues](#7-temporary-queues)
- [8. ABAC](#8-abac)
- [9. Redrive](#9-redrive)

<!-- /TOC -->

# 1. All about SQS

1. [The Essential Guide to Amazon SQS](https://baselime.io/blog/sqs-guide)

# 2. Deduplication

1. [De-duplication in SQS](https://andrewtarry.com/posts/de-duplicate-sqs/)
2. [Deduplicating Amazon SQS Messages](https://medium.com/avmconsulting-blog/deduplicating-amazon-sqs-messages-dc114d1e6545)
- https://gitlab.com/rrhodes/sqs-message-deduplication/tree/master/src

# 3. SQS FIFO

1. [Using Python and Amazon SQS FIFO Queues to Preserve Message Sequencing by Tara Van Unen ](https://aws.amazon.com/blogs/developer/using-python-and-amazon-sqs-fifo-queues-to-preserve-message-sequencing/)
2. [Solving Complex Ordering Challenges with Amazon SQS FIFO Queues by Christie Gifrin and Shea Lutton |](https://aws.amazon.com/blogs/compute/solving-complex-ordering-challenges-with-amazon-sqs-fifo-queues/)
3. [Building event-driven architectures with Amazon SNS FIFO By Christian Mueller](https://aws.amazon.com/blogs/compute/building-event-driven-architectures-with-amazon-sns-fifo/)

# 4. Well Architected

1. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 1 by Pascal Vogel ](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-1/)
2. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 2 by Pascal Vogel ](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-2/)
3. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 3 by Pascal Vogel](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-3/)

# 5. Scaling

1. [Understanding how AWS Lambda scales with Amazon SQS standard queues by James Beswick](https://aws.amazon.com/blogs/compute/understanding-how-aws-lambda-scales-when-subscribed-to-amazon-sqs-queues/)
2. [Why isn't my Lambda function with an Amazon SQS event source scaling optimally?](https://repost.aws/knowledge-center/lambda-sqs-scaling)
3. [Why isn't my Lambda function with an Amazon SQS event source scaling optimally?](https://www.youtube.com/watch?v=mIYWo0LS2-Q)

# 6. DLQs

1. [Designing durable serverless apps with DLQs for Amazon SNS, Amazon SQS, AWS Lambda](https://aws.amazon.com/blogs/compute/designing-durable-serverless-apps-with-dlqs-for-amazon-sns-amazon-sqs-aws-lambda/)
2. [Implementing AWS Lambda error handling patterns by Julian Wood, Jeff Chen, and Jeff Li ](https://aws.amazon.com/blogs/compute/implementing-aws-lambda-error-handling-patterns/)

# 7. Temporary Queues

1. [Simple Two-way Messaging using the Amazon SQS Temporary Queue Client by Rachel Richardson](https://aws.amazon.com/blogs/compute/simple-two-way-messaging-using-the-amazon-sqs-temporary-queue-client/)

# 8. ABAC

1. [Introducing attribute-based access controls (ABAC) for Amazon SQS by Vikas Panghal and Hardik Vasa ](https://aws.amazon.com/blogs/compute/introducing-attribute-based-access-controls-abac-for-amazon-sqs/)
- Update /Volumes/Lexar/git-repos/aws-repo/my-github/study-guide-repo/prepare-notes/best practices - sqs.md

# 9. Redrive

1. [Introducing Amazon Simple Queue Service dead-letter queue redrive to source queues by Mark Richman,](https://aws.amazon.com/blogs/compute/introducing-amazon-simple-queue-service-dead-letter-queue-redrive-to-source-queues/)
2. [SQS Redrive Policy](https://awslabs.github.io/serverless-rules/rules/sqs/redrive_policy/)
3. [A New Set of APIs for Amazon SQS Dead-Letter Queue Redrive by Sébastien Stormacq](https://aws.amazon.com/blogs/aws/a-new-set-of-apis-for-amazon-sqs-dead-letter-queue-redrive/)