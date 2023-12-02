<h1>Amazon SQS</h1>

<!-- TOC -->

- [1. All about SQS](#1-all-about-sqs)
- [2. Best Practices](#2-best-practices)
- [3. Costs](#3-costs)
- [4. DLQs](#4-dlqs)
- [5. Delay Queue](#5-delay-queue)
- [6. Lambda](#6-lambda)
- [7. Scaling](#7-scaling)
- [8. Temporary Queues](#8-temporary-queues)
- [9. Throttling](#9-throttling)
- [10. ABAC](#10-abac)
- [11. Redrive](#11-redrive)

<!-- /TOC -->

# 1. All about SQS

1. [The Essential Guide to Amazon SQS](https://baselime.io/blog/sqs-guide)
1. [Explaining sqs using sequence diagrams](https://medium.com/circuitpeople/aws-sqs-in-pictures-448c897534e1)

# 2. Best Practices

1. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 1 by Pascal Vogel ](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-1/)
2. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 2 by Pascal Vogel ](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-2/)
3. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 3 by Pascal Vogel](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-3/)
4. [Mastering Retries: Best Practices for Amazon SQS By Ran Isenberg](https://www.ranthebuilder.cloud/post/mastering-retries-best-practices-for-amazon-sqs)
5. [Effective Amazon SQS Batch Handling with AWS Lambda Powertools By Ran Isenberg](https://www.ranthebuilder.cloud/post/effective-amazon-sqs-batch-handling-with-aws-lambda-powertools)

# 3. Costs

1. [8 Lessons Learned with SQS: Improve Performance and Cutting Costs By Tarek](https://aws.plainenglish.io/sqs-lessons-learned-improve-performance-and-cutting-costs-86a9d8fb7b39)

# 4. DLQs

1. [See aws-lambda.md#29-dlqs](./aws-lambda.md#29-dlqs)
1. [[DLQs for SQS] Designing durable serverless apps with DLQs for Amazon SNS, Amazon SQS, AWS Lambda by Rachel Richardson and Otavio Ferreira](https://aws.amazon.com/blogs/compute/designing-durable-serverless-apps-with-dlqs-for-amazon-sns-amazon-sqs-aws-lambda/)

# 5. Delay Queue

1. [AWS SQS Delay Queue and Delay Message Example in Java](https://examples.javacodegeeks.com/aws-sqs-delay-queue-and-delay-message-example-in-java/)

# 6. Lambda

> 1. [[**MUST-SEE**] Lessons learned from combining SQS and Lambda in a data project by solita.fi](https://data.solita.fi/lessons-learned-from-combining-sqs-and-lambda-in-a-data-project/))
1. [Using Lambda with Amazon SQS](https://docs.aws.amazon.com/lambda/latest/dg/with-sqs.html#events-sqs-scaling)
1. [Why isn't my Lambda function with an Amazon SQS event source scaling optimally?](https://repost.aws/knowledge-center/lambda-sqs-scaling)
1. [Handle SQS message failure in batch with partial batch response feature](https://medium.com/srcecde/handle-sqs-message-failure-in-batch-with-partial-batch-response-b858ad212573)
1. [Lambda + SQS Users Should Know About This](https://www.youtube.com/watch?v=0707Py8Jyf0)
1. [Why isn't my Lambda function with an Amazon SQS event source scaling optimally?](https://repost.aws/knowledge-center/lambda-sqs-scaling)
> 1. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 1 by Pascal Vogel ](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-1/)
> 1. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 2 by Pascal Vogel ](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-2/)
> 1. [Implementing AWS Well-Architected best practices for Amazon SQS – Part 3 by Pascal Vogel](https://aws.amazon.com/blogs/compute/implementing-aws-well-architected-best-practices-for-amazon-sqs-part-3/)

# 7. Scaling

1. [Understanding how AWS Lambda scales with Amazon SQS standard queues by James Beswick](https://aws.amazon.com/blogs/compute/understanding-how-aws-lambda-scales-when-subscribed-to-amazon-sqs-queues/)
2. [Why isn't my Lambda function with an Amazon SQS event source scaling optimally?](https://repost.aws/knowledge-center/lambda-sqs-scaling)
3. [Why isn't my Lambda function with an Amazon SQS event source scaling optimally?](https://www.youtube.com/watch?v=mIYWo0LS2-Q)

# 8. Temporary Queues

1. [Simple Two-way Messaging using the Amazon SQS Temporary Queue Client by Rachel Richardson](https://aws.amazon.com/blogs/compute/simple-two-way-messaging-using-the-amazon-sqs-temporary-queue-client/)

# 9. Throttling

1. [How To: Use SNS and SQS to Distribute and Throttle Events](https://www.jeremydaly.com/how-to-use-sns-and-sqs-to-distribute-and-throttle-events/)

# 10. ABAC

1. [Introducing attribute-based access controls (ABAC) for Amazon SQS by Vikas Panghal and Hardik Vasa ](https://aws.amazon.com/blogs/compute/introducing-attribute-based-access-controls-abac-for-amazon-sqs/)
- Update /Volumes/Lexar/git-repos/aws-repo/my-github/study-guide-repo/prepare-notes/best practices - sqs.md

# 11. Redrive

1. [Introducing Amazon Simple Queue Service dead-letter queue redrive to source queues by Mark Richman,](https://aws.amazon.com/blogs/compute/introducing-amazon-simple-queue-service-dead-letter-queue-redrive-to-source-queues/)
2. [SQS Redrive Policy](https://awslabs.github.io/serverless-rules/rules/sqs/redrive_policy/)
3. [A New Set of APIs for Amazon SQS Dead-Letter Queue Redrive by Sébastien Stormacq](https://aws.amazon.com/blogs/aws/a-new-set-of-apis-for-amazon-sqs-dead-letter-queue-redrive/)

