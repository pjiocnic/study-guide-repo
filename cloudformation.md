<h1>Cloudformation<h1>

<!-- TOC -->

- [1. WaitConditions](#1-waitconditions)
- [2. Custom Resources](#2-custom-resources)
- [3. Lambda](#3-lambda)
- [4. CFN Lint](#4-cfn-lint)
- [5. Workshop](#5-workshop)
- [6. Templates](#6-templates)
- [7. WhitePaper](#7-whitepaper)
- [8. Testing](#8-testing)
- [9. Blog series](#9-blog-series)
- [Intrinsic Functions](#intrinsic-functions)

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

# 6. Templates

1. [Cloud Foundations on AWS - Templates](https://github.com/cloud-foundations-on-aws/cloud-foundations-templates/tree/main)

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

# Intrinsic Functions

1. [AWS CloudFormation Intrinsic Functions By: Jeff Loughridge](https://konekti.us/post/aws-cloudformation-intrinsic-functions/)