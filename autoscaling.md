
<!-- TOC -->

- [1. Concepts](#1-concepts)
- [2. AWS Elastic Beanstalk](#2-aws-elastic-beanstalk)
- [3. Netflix's autoscaler Titus](#3-netflixs-autoscaler-titus)
- [4. Scaling Policy based on Memory](#4-scaling-policy-based-on-memory)
- [5. Scaling Policy based on CPU](#5-scaling-policy-based-on-cpu)
- [6. Custom Target Target Tracking Policy](#6-custom-target-target-tracking-policy)
- [7. Scaling based on SQS](#7-scaling-based-on-sqs)
- [8. EC2 Auto Scaling](#8-ec2-auto-scaling)
  - [8.1. Introduction](#81-introduction)
  - [8.2. Using Prometheus metrics](#82-using-prometheus-metrics)
- [9. Lambda](#9-lambda)
- [10. Scaling DynamoDB](#10-scaling-dynamodb)
- [11. Scaling DocumentDB](#11-scaling-documentdb)
- [12. ECS](#12-ecs)
- [13. SNS](#13-sns)
- [14. Curate](#14-curate)

<!-- /TOC -->

# 1. Concepts

1. [AWS Auto-Scaling Deep Dive — Advance Concepts By Gaurav Gupta](https://gauravguptacloud.medium.com/aws-auto-scaling-deep-dive-advance-concepts-7f4fa4d46d58)
2. [How is "Target Groups" different from "Auto-Scaling Groups" in AWS?](https://stackoverflow.com/questions/48529074/how-is-target-groups-different-from-auto-scaling-groups-in-aws)
3. [[White Paper] Blue/Green Deployments on AWS](https://docs.aws.amazon.com/whitepapers/latest/blue-green-deployments/introduction.html)

# 2. AWS Elastic Beanstalk
1. [Autoscaling AWS Elastic Beanstalk worker tier based on SQS queue length](https://blog.cbeer.info/2016/autoscaling-elasticbeanstalk-workers-sqs-length/)

# 3. Netflix's autoscaler Titus

1. [Auto Scaling Production Services on Titus](https://netflixtechblog.com/auto-scaling-production-services-on-titus-1f3cd49f5cd7)
- https://github.com/aws/aws-auto-scaling-custom-resource

# 4. Scaling Policy based on Memory

1. [How to create an Amazon EC2 Auto Scaling policy based on a memory utilization metric (Linux) by Ahmed Magdy Wahdan](https://aws.amazon.com/blogs/mt/create-amazon-ec2-auto-scaling-policy-memory-utilization-metric-linux/)
- Includes a CFN

# 5. Scaling Policy based on CPU

# 6. Custom Target Target Tracking Policy

1. [[MY NEXT]Amazon DocumentDB (with MongoDB compatibility) read autoscaling by Randy DeFauw](https://aws.amazon.com/blogs/database/amazon-documentdb-with-mongodb-compatibility-read-autoscaling/)

# 7. Scaling based on SQS

1. [Running Cost-effective queue workers with Amazon SQS and Amazon EC2 Spot Instances by Ben Peven ](https://aws.amazon.com/blogs/compute/running-cost-effective-queue-workers-with-amazon-sqs-and-amazon-ec2-spot-instances/)
2. [Scaling an ASG using target tracking with a dynamic SQS target](https://aws.amazon.com/blogs/compute/scaling-an-asg-using-target-tracking-with-a-dynamic-sqs-target/)
3. [Scaling based on Amazon SQS](https://docs.aws.amazon.com/autoscaling/ec2/userguide/as-using-sqs-queue.html)

# 8. EC2 Auto Scaling

## 8.1. Introduction

1. [Get started with Amazon EC2 Auto Scaling](https://docs.aws.amazon.com/autoscaling/ec2/userguide/get-started-with-ec2-auto-scaling.html)
2. [EC2 Auto Scaling from AWS General Immersion Day wokshop](https://catalog.us-east-1.prod.workshops.aws/workshops/f3a3e2bd-e1d5-49de-b8e6-dac361842e76/en-US/basic-modules/10-ec2/ec2-auto-scaling/ec2-auto-scaling)
3. [Amazon EC2 Auto Scaling with EC2 Spot Instances](https://aws.amazon.com/getting-started/hands-on/ec2-auto-scaling-spot-instances/)
4. [Scaling your applications faster with EC2 Auto Scaling Warm Pools by Chad Schmutzer](https://aws.amazon.com/blogs/compute/scaling-your-applications-faster-with-ec2-auto-scaling-warm-pools/)
5. [AWS EC2 Auto Scaling By devops-training](https://tkssharma-devops.gitbook.io/devops-training/syllabus/untitled/aws-compute/aws-ec2-auto-scaling)
6. [[TUTORIAL] Amazon EC2 Auto Scaling with EC2 Spot Instances](https://aws.amazon.com/getting-started/hands-on/ec2-auto-scaling-spot-instances/)

## 8.2. Using Prometheus metrics

1. [Auto-scaling Amazon EC2 using Amazon Managed Service for Prometheus and alert manager](https://aws.amazon.com/blogs/mt/auto-scaling-amazon-ec2-using-amazon-managed-service-for-prometheus-and-alert-manager/)

# 9. Lambda

1. [Scheduling AWS Lambda Provisioned Concurrency for recurring peak usage By Jerome Van Der Linden](https://aws.amazon.com/blogs/compute/scheduling-aws-lambda-provisioned-concurrency-for-recurring-peak-usage/)
2. [Use AWS Lambda to adjust scaling steps and thresholds for Amazon AppStream 2.0 by Kellie Cottingame and Marques Oliveira](https://aws.amazon.com/blogs/desktop-and-application-streaming/use-aws-lambda-to-adjust-scaling-steps-and-thresholds-for-amazon-appstream-2-0/)
- Fleet Auto Scaling

# 10. Scaling DynamoDB

1. [4.6 Autoscaling DynamoDB Table Provisioned Capacity @ AWS Cookbook](https://learning.oreilly.com/library/view/aws-cookbook/9781492092599/ch04.html#auto_scaling_dynamodb_table_provisioned)
2. [Amazon DynamoDB auto scaling: Performance and cost optimization at any scale by Daniel Yoder and Sean Shriver](https://aws.amazon.com/blogs/database/amazon-dynamodb-auto-scaling-performance-and-cost-optimization-at-any-scale/)
3. [New – Auto Scaling for Amazon DynamoDB by Jeff Barr](https://aws.amazon.com/blogs/aws/new-auto-scaling-for-amazon-dynamodb/)
4. [The problems with DynamoDB Auto Scaling and how it might be improved By Yan Cui](https://medium.com/hackernoon/the-problems-with-dynamodb-auto-scaling-and-how-it-might-be-improved-a92029c8c10b)

# 11. Scaling DocumentDB

1. [[MY NEXT]Amazon DocumentDB (with MongoDB compatibility) read autoscaling by Randy DeFauw](https://aws.amazon.com/blogs/database/amazon-documentdb-with-mongodb-compatibility-read-autoscaling/)

# 12. ECS

1. [Deep Dive on Amazon ECS Cluster Auto Scaling by Nick Coult](https://aws.amazon.com/blogs/containers/deep-dive-on-amazon-ecs-cluster-auto-scaling/)
2. [Building dynamic Amazon SNS subscriptions for auto scaling container workloads By Mithun Mallick](https://aws.amazon.com/blogs/compute/building-dynamic-amazon-sns-subscriptions-for-auto-scaling-container-workloads/)

# 13. SNS

1. [Building dynamic Amazon SNS subscriptions for auto scaling container workloads By Mithun Mallick](https://aws.amazon.com/blogs/compute/building-dynamic-amazon-sns-subscriptions-for-auto-scaling-container-workloads/)

# 14. Curate

1. [Deploy auto scaling web service](https://catalog.workshops.aws/general-immersionday/en-US/advanced-modules/compute/auto-scaling)
1. https://github.com/tryolabs/aws-workshop/blob/master/workshop/elb-auto-scaling-group/02-auto-scaling-group.md
2. https://awslabs.github.io/scale-out-computing-on-aws/workshops/reinvent19-MFG405/#lab-environment-at-a-glance
3. https://ecsworkshop.com/capacity_providers/ec2/
4. https://tech.smartling.com/aws-terraform-workshop-2-ec2-networking-autoscaling-groups-cloudwatch-12ee08c17





