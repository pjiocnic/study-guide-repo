<h1>How Lambda scales with Synchronous and Asynchronous Requests - Account/Burst Quotas</h1>

<!-- TOC -->

- [1. Scaling concepts](#1-scaling-concepts)
- [Controlling max number of comcurrent Lambdas](#controlling-max-number-of-comcurrent-lambdas)
- [Scaling with SQS](#scaling-with-sqs)
- [Scaling with Kafka](#scaling-with-kafka)
- [2. Low Priority Blogs](#2-low-priority-blogs)
- [3. Handling Partial Batch Failures](#3-handling-partial-batch-failures)
- [6. Curate](#6-curate)

<!-- /TOC -->

# 1. Scaling concepts

1. [[MUST_SEE] Scaling and processing](https://docs.aws.amazon.com/lambda/latest/dg/with-sqs.html#events-sqs-scaling)
1. [[MUST_SEE] Understanding AWS Lambda scaling and throughput by Julian Wood](https://aws.amazon.com/blogs/compute/understanding-aws-lambda-scaling-and-throughput/)
1. [[MUST_SEE] Understanding AWS Lambda’s invoke throttling limits by Archana Srikanta](https://aws.amazon.com/blogs/compute/understanding-aws-lambdas-invoke-throttle-limits/)
1. [[START_HERE] AWS Lambda functions now scale 12 times faster when handling high-volume requests by Marcia Villalba](https://aws.amazon.com/blogs/aws/aws-lambda-functions-now-scale-12-times-faster-when-handling-high-volume-requests/)
1. [[MUST_SEE] Operating Lambda: Application design – Scaling and concurrency: Part 2 by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-application-design-scaling-and-concurrency-part-2/)

# Controlling max number of comcurrent Lambdas

1. [Introducing maximum concurrency of AWS Lambda functions when using Amazon SQS as an event source by Julian Wood](https://aws.amazon.com/blogs/compute/introducing-maximum-concurrency-of-aws-lambda-functions-when-using-amazon-sqs-as-an-event-source)

# Scaling with SQS

1. [Introducing faster polling scale-up for AWS Lambda functions configured with Amazon SQS by by Anton Aleksandrov](https://aws.amazon.com/blogs/compute/introducing-faster-polling-scale-up-for-aws-lambda-functions-configured-with-amazon-sqs/)
1. [Scaling an ASG using target tracking with a dynamic SQS target by Wassim Benhallam](https://aws.amazon.com/blogs/compute/scaling-an-asg-using-target-tracking-with-a-dynamic-sqs-target/)

# Scaling with Kafka

1. [Scaling improvements when processing Apache Kafka with AWS Lambda by Julian Wood](https://aws.amazon.com/blogs/compute/scaling-improvements-when-processing-apache-kafka-with-aws-lambda/)

# 2. Low Priority Blogs

1. [Rate Limiting Strategies for Serverless Applications by Sharon Li, Akhil Aendapally, and Ashish Lagwankar](https://aws.amazon.com/blogs/architecture/rate-limiting-strategies-for-serverless-applications/)
1. [Building well-architected serverless applications: Regulating inbound request rates – part 2 by Julian Wood](https://aws.amazon.com/blogs/compute/building-well-architected-serverless-applications-regulating-inbound-request-rates-part-2/)

# 3. Handling Partial Batch Failures

1. [AWS Lambda now supports partial batch response for SQS as an event source](https://aws.amazon.com/about-aws/whats-new/2021/11/aws-lambda-partial-batch-response-sqs-event-source/)
1. https://rawatpankaj.com/posts/handling-partial-batch-failure-when-processing-sqs-messages-with-a-lambda-function

# 6. Curate

1. [Use AWS Lambda to adjust scaling steps and thresholds for Amazon AppStream 2.0 by Kellie Cottingame and Marques Oliveira](https://aws.amazon.com/blogs/desktop-and-application-streaming/use-aws-lambda-to-adjust-scaling-steps-and-thresholds-for-amazon-appstream-2-0/)