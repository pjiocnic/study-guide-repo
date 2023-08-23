<h1>Cloudformation<h1>

<!-- TOC -->

- [1. BestPractices](#1-bestpractices)
- [2. Blog series](#2-blog-series)
- [3. Courses](#3-courses)
- [4. Custom Resources](#4-custom-resources)
- [5. Lambda](#5-lambda)
- [6. CFN Lint](#6-cfn-lint)
- [7. Intrinsic Functions](#7-intrinsic-functions)
- [8. Sample Templates](#8-sample-templates)
- [9. Skillbuilder courses](#9-skillbuilder-courses)
- [10. Stack Sets](#10-stack-sets)
- [11. Testing](#11-testing)
- [12. WaitConditions](#12-waitconditions)
- [13. Workshop](#13-workshop)
- [14. WhitePapers](#14-whitepapers)

<!-- /TOC -->

# 1. BestPractices

1. [8 best practices when automating your deployments with AWS CloudFormation by Dave May](https://aws.amazon.com/blogs/infrastructure-and-automation/best-practices-automating-deployments-with-aws-cloudformation/)

# 2. Blog series

1. [Hands-on AWS CloudFormation - Part 1. It All Starts Here By Samira Yusifova](https://dev.to/tiamatt/hands-on-aws-cloudformation-part-1-it-all-starts-here-5153)
2. [Hands-on AWS CloudFormation - Part 2. Into to Intrinsic functions By Samira Yusifova](https://dev.to/tiamatt/hands-on-aws-cloudformation-part-2-into-to-intrinsic-functions-4kj2)
3. [Hands-on AWS CloudFormation - Part 3. Intrinsic functions in Action By Samira Yusifova](https://dev.to/tiamatt/hands-on-aws-cloudformation-part-3-intrinsic-functions-in-action-5hj2)
4. [Hands-on AWS CloudFormation - Part 4. Create VPC with private and public subnets By Samira Yusifova](https://dev.to/tiamatt/hands-on-aws-cloudformation-part-4-create-vpc-with-private-and-public-subnets-85d)
5. [Hands-on AWS CloudFormation - Part 5. IAM users, groups and roles By Samira Yusifova](https://dev.to/tiamatt/hands-on-aws-cloudformation-part-5-iam-users-groups-and-roles-5d9f)

# 3. Courses

1. [Automation in AWS with CloudFormation, CLI, and SDKs By Richard A. Jones](https://learning.oreilly.com/videos/automation-in-aws/9780134818313/)
- Good place to brush up

# 4. Custom Resources

1. [Implementing long running deployments with AWS CloudFormation Custom Resources using AWS Step Functions by DAMODAR SHENVI WAGLE](https://aws.amazon.com/blogs/devops/implementing-long-running-deployments-with-aws-cloudformation-custom-resources-using-aws-step-functions/)
2. [Coordinating complex resource dependencies across CloudFormation stacks by Rafael Liu and Tony Suarez](https://aws.amazon.com/blogs/mt/coordinating-complex-resource-dependencies-across-cloudformation-stacks/)
- How to coordinate across multiple stacks by using Custom Resources along with WaitCondition resources, based on custom events within the same account and region.

# 5. Lambda

1. [Faster Auto Scaling in AWS CloudFormation Stacks with Lambda-backed Custom Resources by Tom Maddox ](https://aws.amazon.com/blogs/devops/faster-auto-scaling-in-aws-cloudformation-stacks-with-lambda-backed-custom-resources/)

# 6. CFN Lint

1. [AWS CloudFormation Linter](https://github.com/aws-cloudformation/cfn-lint)

# 7. Intrinsic Functions

1. [AWS CloudFormation Intrinsic Functions By: Jeff Loughridge](https://konekti.us/post/aws-cloudformation-intrinsic-functions/)

# 8. Sample Templates

1. [Cloud Foundations on AWS - Templates](https://github.com/cloud-foundations-on-aws/cloud-foundations-templates/tree/main)
2. [AWS CloudFormation Sample Templates](https://github.com/awslabs/aws-cloudformation-templates)

# 9. Skillbuilder courses

1. [Advanced CloudFormation: Macros](https://explore.skillbuilder.aws/learn/course/113/advanced-cloudformation-macros)

# 10. Stack Sets

1. [AWS Management and Governance Tools Workshop > AWS CloudFormation > MAPPING AND STACKSETS](https://mng.workshop.aws/cloudformation/mappingstacksets.html)

# 11. Testing

1. [Automated CloudFormation Testing Pipeline with TaskCat and CodePipeline by Raleigh Hansen and Dan Le](https://aws.amazon.com/blogs/devops/automated-cloudformation-testing-pipeline-with-taskcat-and-codepipeline/)
- Includes code for running `TaskCat` and scripts to test solution functionality

# 12. WaitConditions

1. https://tutorialsdojo.com/how-to-create-wait-conditions-in-a-cloudformation-template/
2. [Signaling AWS CloudFormation WaitConditions using AWS PrivateLink by Chuck Meyer ](https://aws.amazon.com/blogs/mt/signaling-aws-cloudformation-waitconditions-using-aws-privatelink/)
3. [[ASSOCIATESHARED] CloudFormation Wait Conditions & cfn-signal By Cantrill](https://learn.cantrill.io/courses/1820301/lectures/41301545)
4. [Coordinating complex resource dependencies across CloudFormation stacks by Rafael Liu and Tony Suarez](https://aws.amazon.com/blogs/mt/coordinating-complex-resource-dependencies-across-cloudformation-stacks/)
- How to coordinate across multiple stacks by using Custom Resources along with WaitCondition resources, based on custom events within the same account and region.

# 13. Workshop

1. [AWS CloudFormation Workshop](https://catalog.workshops.aws/cfn101/en-US)
2. [[My NEXT] Highly Available Web Application Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/3de93ad5-ebbe-4258-b977-b45cdfe661f1/en-US)
    - Lab 1: Configure the network
    - Lab 2: Set up your RDS database using cfn
    - Lab 3: Set up Elasticache for Memcached using cfn
    - Lab 4: Create the shared filesystem using cfn
    - Lab 5: Create the load balancer using cfn
    - Lab 6: Create a launch configuration using cfn
    - Lab 7: Create the app server using cfn
    - Lab 8: Configure caching using cfn
    - Lab 9:: Add a Content Delivery Network using cfn
    - Lab 10: Chaos testing with AWS Fault Injection Simulator
3. [AWS Management and Governance Tools Workshop > AWS CloudFormation > MAPPING AND STACKSETS](https://mng.workshop.aws/cloudformation/mappingstacksets.html)

# 14. WhitePapers

1. [Establishing Your Cloud Foundation on AWS](https://docs.aws.amazon.com/whitepapers/latest/establishing-your-cloud-foundation-on-aws/welcome.html)


