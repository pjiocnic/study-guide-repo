
<H1>Amazon Kinesis</h1>

<!-- TOC -->

- [1. In-Progress](#1-in-progress)
- [2. Architectures](#2-architectures)
- [3. Core concepts](#3-core-concepts)
- [4. Enhanced Fan-Out](#4-enhanced-fan-out)
- [5. Using With RDS](#5-using-with-rds)
- [6. Curate](#6-curate)
- [7. Sharding](#7-sharding)
- [8. Scaling](#8-scaling)
- [9. Tumbling Windows](#9-tumbling-windows)
- [10. Theory](#10-theory)
- [11. Vidoes](#11-vidoes)
- [12. Workshops](#12-workshops)

<!-- /TOC -->

# 1. In-Progress

1. [Put and consume events with AWS Lambda, Amazon Kinesis Data Stream and Event Source Mapping](https://www.youtube.com/watch?v=UEenPG3YwQ0)

# 2. Architectures

1. [[MY NEXT] Build highly available streams with Amazon Kinesis Data Streams by Jeremy Ber and Pratik Patel](https://aws.amazon.com/blogs/big-data/build-highly-available-streams-with-amazon-kinesis-data-streams/)
- Warm standby pattern
- Cold standby pattern
- CFN
2. [Building a real-time notification system with Amazon Kinesis Data Streams for Amazon DynamoDB and Amazon Kinesis Data Analytics for Apache Flink by Saurabh Shrivastava, Sameer Goel, and Pratik Patel ](https://aws.amazon.com/blogs/big-data/building-a-real-time-notification-system-with-amazon-kinesis-data-streams-for-amazon-dynamodb-and-amazon-kinesis-data-analytics-for-apache-flink/)
- CFN
- Java
3. [Streaming Amazon DynamoDB data into a centralized data lake by Praveen Krishnamoorthy Ravikumar, Abhishek Gupta, and Ashok Yoganand Sridharan](https://aws.amazon.com/blogs/big-data/streaming-amazon-dynamodb-data-into-a-centralized-data-lake/)
- CFN

# 3. Core concepts
1. [Exploring the Depths of Kinesis Data Streams - Part 1: Partitioning](https://www.trek10.com/blog/exploring-the-depths-of-kinesis-data-streams---part-1-partitioning)
2. [Exploring the Depths of Kinesis Data Streams - Part 2: Scaling](https://www.trek10.com/blog/exploring-the-depths-of-kinesis-data-streams---part-2-scaling)
3. [Exploring the Depths of Kinesis Data Streams - Part 3: Advanced Features](https://www.trek10.com/blog/exploring-the-depths-of-kinesis-data-streams-part-3-advanced-features)
4. [Mastering AWS Kinesis Data Streams, Part 1](https://dev.solita.fi/2020/05/28/kinesis-streams-part-1.html)
5. [Mastering AWS Kinesis Data Streams, Part 2](https://dev.solita.fi/2020/12/21/kinesis-streams-part-2.html)

# 4. Enhanced Fan-Out

1. [Amazon Kinesis Enhanced Fan-Out](https://medium.com/avmconsulting-blog/amazon-kinesis-enhanced-fan-out-4e500411a414)

# 5. Using With RDS

1. [[MAKE BITE-NOTES] Send Relational Database CDC Information to Kinesis Data Streams](https://www.youtube.com/watch?v=xfAJfKDwdIA)

# 6. Curate

1. [Simplify Amazon DynamoDB data extraction and analysis by using AWS Glue and Amazon Athena by Laith Al-Saadoon and Ghiyath Alazzah](https://aws.amazon.com/blogs/database/simplify-amazon-dynamodb-data-extraction-and-analysis-by-using-aws-glue-and-amazon-athena/)
2. [Accelerate Amazon DynamoDB data access in AWS Glue jobs using the new AWS Glue DynamoDB Export connector by Noritaka Sekiyama, Andrew Kim, Neil Gupta, and Savio Dsouza](https://aws.amazon.com/blogs/big-data/accelerate-amazon-dynamodb-data-access-in-aws-glue-jobs-using-the-new-aws-glue-dynamodb-elt-connector/)

# 7. Sharding
1. [Under the hood: Scaling your Kinesis data streams by Ahmed Gaafar](https://aws.amazon.com/blogs/big-data/under-the-hood-scaling-your-kinesis-data-streams/)
    - [amazon-kinesis-scaling-utils](https://github.com/awslabs/amazon-kinesis-scaling-utils)
2. [Uniform Data Distribution Among Kinesis Data Stream Shards](https://medium.com/onebyte-llc/uniform-data-distribution-among-kinesis-data-stream-shards-7d350bca4a99)

# 8. Scaling

1. [Scale Kinesis Data Streams for high throughput and low latency | Amazon Web Services](https://www.youtube.com/watch?v=oAliBHw_08M)

# 9. Tumbling Windows

1. [[MY NEXT] Using AWS Lambda for streaming analytics by James Beswick ](https://aws.amazon.com/blogs/compute/using-aws-lambda-for-streaming-analytics/)

# 10. Theory

1. [[MY NEXT] Serverless Stream-Based Processing for Real-Time Insights by Justin Pirtle](https://aws.amazon.com/blogs/architecture/serverless-stream-based-processing-for-real-time-insights/)

# 11. Vidoes

1. [Amazon Kinesis Introduction and Overview (with SNS, SQS, Eventbridge Comparisons! By BeBetterdev)](https://www.youtube.com/watch?v=_bRTlb9b59Y)

# 12. Workshops

1. [Real Time Streaming with Amazon Kinesis](https://catalog.us-east-1.prod.workshops.aws/workshops/2300137e-f2ac-4eb9-a4ac-3d25026b235f/en-US)
    - Produce data to Kinesis Data Streams
    - Write Data to a Kinesis Data Stream using Kinesis Data Analytics Studio Notebook
    - Lambda with Kinesis Data Firehose
    - Clean, Aggregate, and Enrich Events with Kinesis Data Analytics
    - Lambda Consumer for Kinesis Data Stream
    - Consuming with Amazon KCL
2. [aws-lambda-kinesis-aggregated-event-filtering](https://github.com/aws-samples/aws-lambda-kinesis-aggregated-event-filtering)

