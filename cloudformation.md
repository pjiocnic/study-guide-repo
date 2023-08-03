<h1>Cloudformation<h1>

<!-- TOC -->

- [1. WaitConditions](#1-waitconditions)
- [2. Custom Resources](#2-custom-resources)
- [3. Lambda](#3-lambda)
- [4. CFN Lint](#4-cfn-lint)
- [5. Workshop](#5-workshop)
- [6. Sample Templates](#6-sample-templates)
- [7. WhitePaper](#7-whitepaper)
- [8. Testing](#8-testing)
- [9. Blog series](#9-blog-series)
- [10. Intrinsic Functions](#10-intrinsic-functions)
- [11. Courses](#11-courses)
- [12. Stack Sets](#12-stack-sets)

<!-- /TOC -->

# 1. WaitConditions

1. https://tutorialsdojo.com/how-to-create-wait-conditions-in-a-cloudformation-template/
2. [Signaling AWS CloudFormation WaitConditions using AWS PrivateLink by Chuck Meyer ](https://aws.amazon.com/blogs/mt/signaling-aws-cloudformation-waitconditions-using-aws-privatelink/)
3. [[ASSOCIATESHARED] CloudFormation Wait Conditions & cfn-signal By Cantrill](https://learn.cantrill.io/courses/1820301/lectures/41301545)
4. [Coordinating complex resource dependencies across CloudFormation stacks by Rafael Liu and Tony Suarez](https://aws.amazon.com/blogs/mt/coordinating-complex-resource-dependencies-across-cloudformation-stacks/)
- How to coordinate across multiple stacks by using Custom Resources along with WaitCondition resources, based on custom events within the same account and region.

# 2. Custom Resources

1. [Implementing long running deployments with AWS CloudFormation Custom Resources using AWS Step Functions by DAMODAR SHENVI WAGLE](https://aws.amazon.com/blogs/devops/implementing-long-running-deployments-with-aws-cloudformation-custom-resources-using-aws-step-functions/)
2. [Coordinating complex resource dependencies across CloudFormation stacks by Rafael Liu and Tony Suarez](https://aws.amazon.com/blogs/mt/coordinating-complex-resource-dependencies-across-cloudformation-stacks/)
- How to coordinate across multiple stacks by using Custom Resources along with WaitCondition resources, based on custom events within the same account and region.


# 3. Lambda

1. [Faster Auto Scaling in AWS CloudFormation Stacks with Lambda-backed Custom Resources by Tom Maddox ](https://aws.amazon.com/blogs/devops/faster-auto-scaling-in-aws-cloudformation-stacks-with-lambda-backed-custom-resources/)

# 4. CFN Lint

1. [AWS CloudFormation Linter](https://github.com/aws-cloudformation/cfn-lint)

# 5. Workshop

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

# 6. Sample Templates

1. [Cloud Foundations on AWS - Templates](https://github.com/cloud-foundations-on-aws/cloud-foundations-templates/tree/main)
2. [AWS CloudFormation Sample Templates](https://github.com/awslabs/aws-cloudformation-templates)

# 7. WhitePaper

1. [Establishing Your Cloud Foundation on AWS](https://docs.aws.amazon.com/whitepapers/latest/establishing-your-cloud-foundation-on-aws/welcome.html)

# 8. Testing

1. [Automated CloudFormation Testing Pipeline with TaskCat and CodePipeline by Raleigh Hansen and Dan Le](https://aws.amazon.com/blogs/devops/automated-cloudformation-testing-pipeline-with-taskcat-and-codepipeline/)
- Includes code for running `TaskCat` and scripts to test solution functionality

# 9. Blog series

1. [Hands-on AWS CloudFormation - Part 1. It All Starts Here By Samira Yusifova](https://dev.to/tiamatt/hands-on-aws-cloudformation-part-1-it-all-starts-here-5153)
2. [Hands-on AWS CloudFormation - Part 2. Into to Intrinsic functions By Samira Yusifova](https://dev.to/tiamatt/hands-on-aws-cloudformation-part-2-into-to-intrinsic-functions-4kj2)
3. [Hands-on AWS CloudFormation - Part 3. Intrinsic functions in Action By Samira Yusifova](https://dev.to/tiamatt/hands-on-aws-cloudformation-part-3-intrinsic-functions-in-action-5hj2)
4. [Hands-on AWS CloudFormation - Part 4. Create VPC with private and public subnets By Samira Yusifova](https://dev.to/tiamatt/hands-on-aws-cloudformation-part-4-create-vpc-with-private-and-public-subnets-85d)
5. [Hands-on AWS CloudFormation - Part 5. IAM users, groups and roles By Samira Yusifova](https://dev.to/tiamatt/hands-on-aws-cloudformation-part-5-iam-users-groups-and-roles-5d9f)

# 10. Intrinsic Functions

1. [AWS CloudFormation Intrinsic Functions By: Jeff Loughridge](https://konekti.us/post/aws-cloudformation-intrinsic-functions/)

# 11. Courses

1. [Automation in AWS with CloudFormation, CLI, and SDKs By Richard A. Jones](https://learning.oreilly.com/videos/automation-in-aws/9780134818313/)
- Good place to brush up

# 12. Stack Sets

1. [AWS Management and Governance Tools Workshop > AWS CloudFormation > MAPPING AND STACKSETS](https://mng.workshop.aws/cloudformation/mappingstacksets.html)