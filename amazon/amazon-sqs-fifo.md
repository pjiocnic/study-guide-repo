<h1>Amazon SQS FIFO</h1>

<!-- TOC -->

- [1. Dashboard (Material to start with)](#1-dashboard-material-to-start-with)
- [2. Deduplication](#2-deduplication)
- [3. Lambda](#3-lambda)
  - [3.1. Concurrency Handling](#31-concurrency-handling)
- [4. SQS FIFO](#4-sqs-fifo)
- [5. Concepts](#5-concepts)
- [6. DLQs for FIFO](#6-dlqs-for-fifo)
- [7. Troubleshooting](#7-troubleshooting)
- [8. Videos](#8-videos)
- [9. Workshop](#9-workshop)

<!-- /TOC -->

# 1. Dashboard (Material to start with)

1. [[**MUST-SEE**] Lessons learned from combining SQS and Lambda in a data project by solita.fi](https://data.solita.fi/lessons-learned-from-combining-sqs-and-lambda-in-a-data-project/)
1. [How Lambdas are spun upto number of unique MessageGroupId's that You set](https://www.foxy.io/blog/we-love-aws-lambda-but-its-concurrency-handling-with-sqs-is-silly/)
1. [how you can trigger a lambda using a FIFO Queue By FooBar](https://www.youtube.com/watch?v=wD65sR5ENxA)
1. [sam-fifo-queue-test](https://github.com/mavi888/sam-fifo-queue-test)
1. [AWS Lambda Concurrency when SQS FIFO Queue as Trigger by Pubudu Jayawardana for AWS Community Builders](https://dev.to/aws-builders/aws-lambda-concurrency-when-sqs-fifo-queue-as-trigger-1nn6)
1. [Using Python and Amazon SQS FIFO Queues to Preserve Message Sequencing by Tara Van Unen ](https://aws.amazon.com/blogs/developer/using-python-and-amazon-sqs-fifo-queues-to-preserve-message-sequencing/)
1. [Solving Complex Ordering Challenges with Amazon SQS FIFO Queues by Christie Gifrin and Shea Lutton |](https://aws.amazon.com/blogs/compute/solving-complex-ordering-challenges-with-amazon-sqs-fifo-queues/)
1. [How the Amazon SQS FIFO API Works by Leah Rivers, Jakub Wojciak, and Tim Bray](https://aws.amazon.com/blogs/developer/how-the-amazon-sqs-fifo-api-works/)
1. [[**MUST-SEE**] Troubleshooting Slow SQS FIFO Queues By Marcelo Andrade](https://www.serverlessguru.com/blog/troubleshooting-slow-sqs-fifo-queues)

# 2. Deduplication

1. [De-duplication in SQS](https://andrewtarry.com/posts/de-duplicate-sqs/)
2. [Deduplicating Amazon SQS Messages](https://medium.com/avmconsulting-blog/deduplicating-amazon-sqs-messages-dc114d1e6545)
- https://gitlab.com/rrhodes/sqs-message-deduplication/tree/master/src

# 3. Lambda

> 1. [New for AWS Lambda – SQS FIFO as an event source by James Beswick](https://aws.amazon.com/blogs/compute/new-for-aws-lambda-sqs-fifo-as-an-event-source/)
1. [how you can trigger a lambda using a FIFO Queue By FooBar](https://www.youtube.com/watch?v=wD65sR5ENxA)
1. [sam-fifo-queue-test](https://github.com/mavi888/sam-fifo-queue-test)

## 3.1. Concurrency Handling
> 1. [AWS Lambda Concurrency when SQS FIFO Queue as Trigger by Pubudu Jayawardana for AWS Community Builders](https://dev.to/aws-builders/aws-lambda-concurrency-when-sqs-fifo-queue-as-trigger-1nn6)
1. [How Lambdas are spun upto number of unique MessageGroupId's that You set](https://www.foxy.io/blog/we-love-aws-lambda-but-its-concurrency-handling-with-sqs-is-silly/)
1. [[**MUST-SEE**] Lessons learned from combining SQS and Lambda in a data project by solita.fi](https://data.solita.fi/lessons-learned-from-combining-sqs-and-lambda-in-a-data-project/)

# 4. SQS FIFO

> 1. [Using Python and Amazon SQS FIFO Queues to Preserve Message Sequencing by Tara Van Unen ](https://aws.amazon.com/blogs/developer/using-python-and-amazon-sqs-fifo-queues-to-preserve-message-sequencing/)
> 2. [Solving Complex Ordering Challenges with Amazon SQS FIFO Queues by Christie Gifrin and Shea Lutton](https://aws.amazon.com/blogs/compute/solving-complex-ordering-challenges-with-amazon-sqs-fifo-queues/)


# 5. Concepts

1. [3 surprising facts about AWS SQS FIFO queues BY Tom Gregory](https://tomgregory.com/3-surprising-facts-about-aws-sqs-fifo-queues/)
1. [Solving Complex Ordering Challenges with Amazon SQS FIFO Queues by Christie Gifrin and Shea Lutton](https://aws.amazon.com/blogs/compute/solving-complex-ordering-challenges-with-amazon-sqs-fifo-queues/)
1. [Using Python and Amazon SQS FIFO Queues to Preserve Message Sequencing by Tara Van Unen](https://aws.amazon.com/blogs/developer/using-python-and-amazon-sqs-fifo-queues-to-preserve-message-sequencing/)
> 1. [How the Amazon SQS FIFO API Works by Leah Rivers, Jakub Wojciak, and Tim Bray](https://aws.amazon.com/blogs/developer/how-the-amazon-sqs-fifo-api-works/)
1. [AWS SQS FIFO Queues Simplified: The Ultimate Guide 101](https://hevodata.com/learn/sqs-fifo-queues/)

# 6. DLQs for FIFO

1. [Automating Redrive from DLQ for FIFO SQS Queue](https://dev.to/aws-builders/automating-redrive-from-dlq-for-fifo-sqs-queue-od8)

# 7. Troubleshooting

1. [Troubleshooting Slow SQS FIFO Queues By Marcelo Andrade](https://www.serverlessguru.com/blog/troubleshooting-slow-sqs-fifo-queues)

# 8. Videos

> 1. [AWS SQS FIFO Queues Overview and Demonstration By Be A Better Dev](https://www.youtube.com/watch?v=cl_5dGGeTmY)
1. [how you can trigger a lambda using a FIFO Queue By FooBar](https://www.youtube.com/watch?v=wD65sR5ENxA)

# 9. Workshop

1. [Sqs Publisher To Sqs Subscriber With Fifo Message Ordering](https://workshops.devax.academy/monoliths-to-microservices/module5/explore_messaging_options/sqs_publisher_sqs_subscriber_with_fifo.html)
