<h1>Cloudformation<h1>

<!-- TOC -->

- [1. BestPractices](#1-bestpractices)
- [2. Blog series](#2-blog-series)
- [3. Series by Brett Gillet](#3-series-by-brett-gillet)
- [4. Courses](#4-courses)
- [5. CFN Lint](#5-cfn-lint)
- [6. Custom Resources](#6-custom-resources)
- [7. Lambda](#7-lambda)
- [8. Intrinsic Functions](#8-intrinsic-functions)
- [9. AWS CloudFormation modules](#9-aws-cloudformation-modules)
- [10. StackSets](#10-stacksets)
- [11. Samples](#11-samples)
- [12. Skillbuilder courses](#12-skillbuilder-courses)
- [13. Stack Sets](#13-stack-sets)
- [14. Testing](#14-testing)
- [15. WaitConditions](#15-waitconditions)
- [16. Workshop](#16-workshop)
- [17. WhitePapers](#17-whitepapers)

<!-- /TOC -->

# 1. BestPractices

1. [8 best practices when automating your deployments with AWS CloudFormation by Dave May](https://aws.amazon.com/blogs/infrastructure-and-automation/best-practices-automating-deployments-with-aws-cloudformation/)

# 2. Blog series

1. [Hands-on AWS CloudFormation - Part 1. It All Starts Here By Samira Yusifova](https://dev.to/tiamatt/hands-on-aws-cloudformation-part-1-it-all-starts-here-5153)
2. [Hands-on AWS CloudFormation - Part 2. Into to Intrinsic functions By Samira Yusifova](https://dev.to/tiamatt/hands-on-aws-cloudformation-part-2-into-to-intrinsic-functions-4kj2)
3. [Hands-on AWS CloudFormation - Part 3. Intrinsic functions in Action By Samira Yusifova](https://dev.to/tiamatt/hands-on-aws-cloudformation-part-3-intrinsic-functions-in-action-5hj2)
4. [Hands-on AWS CloudFormation - Part 4. Create VPC with private and public subnets By Samira Yusifova](https://dev.to/tiamatt/hands-on-aws-cloudformation-part-4-create-vpc-with-private-and-public-subnets-85d)
5. [Hands-on AWS CloudFormation - Part 5. IAM users, groups and roles By Samira Yusifova](https://dev.to/tiamatt/hands-on-aws-cloudformation-part-5-iam-users-groups-and-roles-5d9f)

# 3. Series by Brett Gillet

1. [Using a Deletion Policy to protect resources deployed using AWS CloudFormation By Brett Gillett](https://curiousorbit.com/blog/cloudformation-deletion-policy/)
1. [CloudFormation changesets - review changes before running a stack update By Brett Gillett](https://curiousorbit.com/blog/cloudformation-changeset/)
1. [Using Conditions in your CloudFormation templates By Brett Gillett](https://curiousorbit.com/blog/cloudformation-using-conditions/)
1. [Using Parameters in your CloudFormation templates By Brett Gillett ](https://curiousorbit.com/blog/cloudformation-using-parameters/)

# 4. Courses

1. [Automation in AWS with CloudFormation, CLI, and SDKs By Richard A. Jones](https://learning.oreilly.com/videos/automation-in-aws/9780134818313/)
- Good place to brush up

# 5. CFN Lint

1. [AWS CloudFormation Linter](https://github.com/aws-cloudformation/cfn-lint)
1. [Serverless Rules](https://awslabs.github.io/serverless-rules/rules/)

# 6. Custom Resources

1. [Implementing long running deployments with AWS CloudFormation Custom Resources using AWS Step Functions by DAMODAR SHENVI WAGLE](https://aws.amazon.com/blogs/devops/implementing-long-running-deployments-with-aws-cloudformation-custom-resources-using-aws-step-functions/)
2. [Coordinating complex resource dependencies across CloudFormation stacks by Rafael Liu and Tony Suarez](https://aws.amazon.com/blogs/mt/coordinating-complex-resource-dependencies-across-cloudformation-stacks/)
- How to coordinate across multiple stacks by using Custom Resources along with WaitCondition resources, based on custom events within the same account and region.
3. [Introducing TypeScript support for building AWS CloudFormation resource types by Craig Lefkowitz](https://aws.amazon.com/blogs/mt/introducing-typescript-support-for-building-aws-cloudformation-resource-types/)
4. [Managing resources using AWS CloudFormation Resource Types by Craig Lefkowitz](https://aws.amazon.com/blogs/mt/managing-resources-using-aws-cloudformation-resource-types/)
5. [Lambda to S3 via a custom resource](https://serverlessland.com/patterns/lambda-s3-cfn)

# 7. Lambda

1. [Faster Auto Scaling in AWS CloudFormation Stacks with Lambda-backed Custom Resources by Tom Maddox ](https://aws.amazon.com/blogs/devops/faster-auto-scaling-in-aws-cloudformation-stacks-with-lambda-backed-custom-resources/)

# 8. Intrinsic Functions

1. [AWS CloudFormation Intrinsic Functions By: Jeff Loughridge](https://konekti.us/post/aws-cloudformation-intrinsic-functions/)

# 9. AWS CloudFormation modules

1. [[DEMO] Share reusable infrastructure as code by using AWS CloudFormation modules and StackSets by Josh Hart ](https://aws.amazon.com/blogs/mt/share-reusable-infrastructure-code-aws-cloudformation-modules-and-stacksets/)

# 10. StackSets

1. [[DEMO] Share reusable infrastructure as code by using AWS CloudFormation modules and StackSets by Josh Hart ](https://aws.amazon.com/blogs/mt/share-reusable-infrastructure-code-aws-cloudformation-modules-and-stacksets/)
1. [[HOW_IT_WORKS] Use CloudFormation StackSets to Provision Resources Across Multiple AWS Accounts and Regions by Jeff Barr ](https://aws.amazon.com/blogs/aws/use-cloudformation-stacksets-to-provision-resources-across-multiple-aws-accounts-and-regions/)
1. [Working with AWS CloudFormation StackSets](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/what-is-cfnstacksets.html)

# 11. Samples

1. [Cloud Foundations on AWS - Templates](https://github.com/cloud-foundations-on-aws/cloud-foundations-templates/tree/main)
2. [AWS CloudFormation Sample Templates](https://github.com/awslabs/aws-cloudformation-templates)
3. [Template snippets](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/CHAP_TemplateQuickRef.html)
4. [AWS CloudFormation Templates](https://aws.amazon.com/cloudformation/resources/templates/govcloud-us/)

# 12. Skillbuilder courses

1. [Advanced CloudFormation: Macros](https://explore.skillbuilder.aws/learn/course/113/advanced-cloudformation-macros)

# 13. Stack Sets

1. [AWS Management and Governance Tools Workshop > AWS CloudFormation > MAPPING AND STACKSETS](https://mng.workshop.aws/cloudformation/mappingstacksets.html)

# 14. Testing

1. [Automated CloudFormation Testing Pipeline with TaskCat and CodePipeline by Raleigh Hansen and Dan Le](https://aws.amazon.com/blogs/devops/automated-cloudformation-testing-pipeline-with-taskcat-and-codepipeline/)
- Includes code for running `TaskCat` and scripts to test solution functionality

# 15. WaitConditions

1. https://tutorialsdojo.com/how-to-create-wait-conditions-in-a-cloudformation-template/
2. [Signaling AWS CloudFormation WaitConditions using AWS PrivateLink by Chuck Meyer ](https://aws.amazon.com/blogs/mt/signaling-aws-cloudformation-waitconditions-using-aws-privatelink/)
3. [[ASSOCIATESHARED] CloudFormation Wait Conditions & cfn-signal By Cantrill](https://learn.cantrill.io/courses/1820301/lectures/41301545)
4. [Coordinating complex resource dependencies across CloudFormation stacks by Rafael Liu and Tony Suarez](https://aws.amazon.com/blogs/mt/coordinating-complex-resource-dependencies-across-cloudformation-stacks/)
- How to coordinate across multiple stacks by using Custom Resources along with WaitCondition resources, based on custom events within the same account and region.

# 16. Workshop

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

# 17. WhitePapers

1. [Establishing Your Cloud Foundation on AWS](https://docs.aws.amazon.com/whitepapers/latest/establishing-your-cloud-foundation-on-aws/welcome.html)


