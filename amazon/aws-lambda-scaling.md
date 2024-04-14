<h1>How Lambda scales with Synchronous and Asynchronous Requests - Account/Burst Quotas</h1>

<!-- TOC -->

- [1. Scaling concepts](#1-scaling-concepts)
- [2. Low Priority Blogs](#2-low-priority-blogs)
- [3. SQS](#3-sqs)
- [4. Handling Partial Batch Failures](#4-handling-partial-batch-failures)
- [5. Repost](#5-repost)
- [6. Videos](#6-videos)
- [7. Curate](#7-curate)

<!-- /TOC -->

# 1. Scaling concepts

1. [[MUST_SEE] Scaling and processing](https://docs.aws.amazon.com/lambda/latest/dg/with-sqs.html#events-sqs-scaling)
1. [[MUST_SEE] Understanding AWS Lambda scaling and throughput by Julian Wood](https://aws.amazon.com/blogs/compute/understanding-aws-lambda-scaling-and-throughput/)
1. [[MUST_SEE] Understanding AWS Lambda’s invoke throttling limits by Archana Srikanta](https://aws.amazon.com/blogs/compute/understanding-aws-lambdas-invoke-throttle-limits/)
1. [AWS Lambda functions now scale 12 times faster when handling high-volume requests by Marcia Villalba](https://aws.amazon.com/blogs/aws/aws-lambda-functions-now-scale-12-times-faster-when-handling-high-volume-requests/)
1. [[MUST_SEE] Operating Lambda: Application design – Scaling and concurrency: Part 2 by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-application-design-scaling-and-concurrency-part-2/)

# 2. Low Priority Blogs

1. [Rate Limiting Strategies for Serverless Applications by Sharon Li, Akhil Aendapally, and Ashish Lagwankar](https://aws.amazon.com/blogs/architecture/rate-limiting-strategies-for-serverless-applications/)
1. [Building well-architected serverless applications: Regulating inbound request rates – part 2 by Julian Wood](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-regulating-inbound-request-rates-part-2/)

# 3. SQS

1. [[DOCS] Scaling and processing](https://docs.aws.amazon.com/lambda/latest/dg/with-sqs.html#events-sqs-scaling)
1. [Introducing faster polling scale-up for AWS Lambda functions configured with Amazon SQS by Anton Aleksandrov](https://aws.amazon.com/blogs/compute/introducing-faster-polling-scale-up-for-aws-lambda-functions-configured-with-amazon-sqs/)
1. [Understanding how AWS Lambda scales with Amazon SQS standard queues by James Beswick](https://aws.amazon.com/blogs/compute/understanding-how-aws-lambda-scales-when-subscribed-to-amazon-sqs-queues/)

# 4. Handling Partial Batch Failures

1. [AWS Lambda now supports partial batch response for SQS as an event source](https://aws.amazon.com/about-aws/whats-new/2021/11/aws-lambda-partial-batch-response-sqs-event-source/)
1. https://rawatpankaj.com/posts/handling-partial-batch-failure-when-processing-sqs-messages-with-a-lambda-function

# 5. Repost

1. [[MUST_SEE] Why isn't my Lambda function with an Amazon SQS event source optimally scaling?](https://repost.aws/knowledge-center/lambda-sqs-scaling)
1. [Does SQS reach "unlimited capacity" instantaneously, or do I need to spread messages (ramp up) over some time?](https://repost.aws/questions/QUsh9gCND0SIuULmWD7mY_bg/does-sqs-reach-unlimited-capacity-instantaneously-or-do-i-need-to-spread-messages-ramp-up-over-some-time)
1. [How is the Burst Capacity related to the Baseline?](https://repost.aws/questions/QUtk6b7KN_QkabwGGn6PLs4w/how-is-the-burst-capacity-related-to-the-baseline)
1. [Burst Concurrency Quota increase?](https://repost.aws/questions/QUmJCiCtd_Slm9XUqcom0NfQ/burst-concurrency-quota-increase)

# 6. Videos

1. [Understanding AWS Lambda scaling and throughput](https://www.youtube.com/watch?v=wenqZqNOVZw)
- See this after `https://aws.amazon.com/blogs/compute/understanding-aws-lambda-scaling-and-throughput/`

# 7. Curate

1. [Use AWS Lambda to adjust scaling steps and thresholds for Amazon AppStream 2.0 by Kellie Cottingame and Marques Oliveira](https://aws.amazon.com/blogs/desktop-and-application-streaming/use-aws-lambda-to-adjust-scaling-steps-and-thresholds-for-amazon-appstream-2-0/)