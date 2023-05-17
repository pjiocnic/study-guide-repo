
<!-- TOC -->

- [1. Netflix's autoscaler Titus](#1-netflixs-autoscaler-titus)
- [2. Scaling Policy based on Memory](#2-scaling-policy-based-on-memory)
- [3. Scaling Policy based on CPU](#3-scaling-policy-based-on-cpu)
- [4. Custom Target Target Tracking Policy](#4-custom-target-target-tracking-policy)
- [5. Scaling based on SQS](#5-scaling-based-on-sqs)
- [6. EC2 Auto Scaling](#6-ec2-auto-scaling)
  - [6.1. Introduction](#61-introduction)
  - [6.2. Using Prometheus metrics](#62-using-prometheus-metrics)
- [7. Lambda](#7-lambda)
- [8. Scaling DynamoDB](#8-scaling-dynamodb)
- [ECS](#ecs)

<!-- /TOC -->


# 1. Netflix's autoscaler Titus

1. [Auto Scaling Production Services on Titus](https://netflixtechblog.com/auto-scaling-production-services-on-titus-1f3cd49f5cd7)
- https://github.com/aws/aws-auto-scaling-custom-resource

# 2. Scaling Policy based on Memory

1. [How to create an Amazon EC2 Auto Scaling policy based on a memory utilization metric (Linux) by Ahmed Magdy Wahdan](https://aws.amazon.com/blogs/mt/create-amazon-ec2-auto-scaling-policy-memory-utilization-metric-linux/)
- Includes a CFN

# 3. Scaling Policy based on CPU

# 4. Custom Target Target Tracking Policy

1. [Amazon DocumentDB (with MongoDB compatibility) read autoscaling by Randy DeFauw](https://aws.amazon.com/blogs/database/amazon-documentdb-with-mongodb-compatibility-read-autoscaling/)

# 5. Scaling based on SQS

1. [Running Cost-effective queue workers with Amazon SQS and Amazon EC2 Spot Instances by Ben Peven ](https://aws.amazon.com/blogs/compute/running-cost-effective-queue-workers-with-amazon-sqs-and-amazon-ec2-spot-instances/)
2. [Scaling an ASG using target tracking with a dynamic SQS target](https://aws.amazon.com/blogs/compute/scaling-an-asg-using-target-tracking-with-a-dynamic-sqs-target/)
3. [Scaling based on Amazon SQS](https://docs.aws.amazon.com/autoscaling/ec2/userguide/as-using-sqs-queue.html)

# 6. EC2 Auto Scaling

## 6.1. Introduction

1. [Get started with Amazon EC2 Auto Scaling](https://docs.aws.amazon.com/autoscaling/ec2/userguide/get-started-with-ec2-auto-scaling.html)
2. [EC2 Auto Scaling from AWS General Immersion Day wokshop](https://catalog.us-east-1.prod.workshops.aws/workshops/f3a3e2bd-e1d5-49de-b8e6-dac361842e76/en-US/basic-modules/10-ec2/ec2-auto-scaling/ec2-auto-scaling)
3. [Amazon EC2 Auto Scaling with EC2 Spot Instances](https://aws.amazon.com/getting-started/hands-on/ec2-auto-scaling-spot-instances/)
4. [Scaling your applications faster with EC2 Auto Scaling Warm Pools by Chad Schmutzer](https://aws.amazon.com/blogs/compute/scaling-your-applications-faster-with-ec2-auto-scaling-warm-pools/)

## 6.2. Using Prometheus metrics

1. [Auto-scaling Amazon EC2 using Amazon Managed Service for Prometheus and alert manager](https://aws.amazon.com/blogs/mt/auto-scaling-amazon-ec2-using-amazon-managed-service-for-prometheus-and-alert-manager/)

# 7. Lambda

1. [Scheduling AWS Lambda Provisioned Concurrency for recurring peak usage By Jerome Van Der Linden](https://aws.amazon.com/blogs/compute/scheduling-aws-lambda-provisioned-concurrency-for-recurring-peak-usage/)

# 8. Scaling DynamoDB

1. [4.6 Autoscaling DynamoDB Table Provisioned Capacity @ AWS Cookbook](https://learning.oreilly.com/library/view/aws-cookbook/9781492092599/ch04.html#auto_scaling_dynamodb_table_provisioned)
2. [Amazon DynamoDB auto scaling: Performance and cost optimization at any scale by Daniel Yoder and Sean Shriver](https://aws.amazon.com/blogs/database/amazon-dynamodb-auto-scaling-performance-and-cost-optimization-at-any-scale/)
3. [New – Auto Scaling for Amazon DynamoDB by Jeff Barr](https://aws.amazon.com/blogs/aws/new-auto-scaling-for-amazon-dynamodb/)
4. [The problems with DynamoDB Auto Scaling and how it might be improved By Yan Cui](https://medium.com/hackernoon/the-problems-with-dynamodb-auto-scaling-and-how-it-might-be-improved-a92029c8c10b)


# ECS

11. [Deep Dive on Amazon ECS Cluster Auto Scaling by Nick Coult](https://aws.amazon.com/blogs/containers/deep-dive-on-amazon-ecs-cluster-auto-scaling/)

