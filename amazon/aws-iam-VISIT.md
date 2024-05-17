
<!-- TOC -->

- [1. Dashboard](#1-dashboard)
- [ABAC](#abac)
- [2. Organizations](#2-organizations)
- [3. courses](#3-courses)
- [4. Different Policy Types](#4-different-policy-types)
- [5. Examples](#5-examples)
- [6. Exam Prep](#6-exam-prep)
- [7. PassRole](#7-passrole)
- [8. Policy Types](#8-policy-types)
  - [8.1. Identity Based Policies](#81-identity-based-policies)
  - [8.2. Resource Based Policies](#82-resource-based-policies)
- [9. Permission Boundaries](#9-permission-boundaries)
  - [9.1. Demo - Permission Boundaries](#91-demo---permission-boundaries)
- [10. Roles](#10-roles)
  - [10.1. AssumeRoles](#101-assumeroles)
  - [10.2. Role Chaining](#102-role-chaining)
- [11. Session Tags](#11-session-tags)
- [12. Service linked Roles](#12-service-linked-roles)
- [13. Service Control Policies](#13-service-control-policies)
- [14. SkillBuilder Courses](#14-skillbuilder-courses)
- [15. Trust Policies](#15-trust-policies)
- [16. Tutorials](#16-tutorials)
- [17. Videos](#17-videos)

<!-- /TOC -->

# 1. Dashboard

1. [Get to Grips with AWS IAM Roles: Terms, Concepts, and Examples](https://blog.awsfundamentals.com/aws-iam-roles-terms-concepts-and-examples)
    - Service Roles and Service Linked Roles
    - IAM Pass Role
    - Permission Boundaries
    - Cross-Account Access
1. [[**MY_NEXT**] When and where to use IAM permissions boundaries by Umair Rehmat](https://aws.amazon.com/blogs/security/when-and-where-to-use-iam-permissions-boundaries/)
1. [[**MY_NEXT**] IAM policy types: How and when to use them by Matt Luttrell and Josh Joy ](https://aws.amazon.com/blogs/security/iam-policy-types-how-and-when-to-use-them/)

# ABAC

1. [Introducing attribute-based access controls (ABAC) for Amazon SQS by Vikas Panghal](https://aws.amazon.com/blogs/compute/introducing-attribute-based-access-controls-abac-for-amazon-sqs/)
1. [Scaling AWS Lambda permissions with Attribute-Based Access Control (ABAC) by Julian Wood and Chris McPeek](https://aws.amazon.com/blogs/compute/scaling-aws-lambda-permissions-with-attribute-based-access-control-abac/)

# 2. Organizations

1. [How to Use Service Control Policies in AWS Organizations by Frank Phillis](https://aws.amazon.com/blogs/security/how-to-use-service-control-policies-in-aws-organizations/)
2. [Best Practices for Organizational Units with AWS Organizations by Andrew Blackham and Sam Elmalak ](https://aws.amazon.com/blogs/mt/best-practices-for-organizational-units-with-aws-organizations/)
3. [How to manage Route53 hosted zones in a multi-account environment](https://theburningmonk.com/2021/05/how-to-manage-route53-hosted-zones-in-a-multi-account-environment/)

# 3. courses

1. [Effective IAM for Amazon Web Services](https://www.effectiveiam.com/)

# 4. Different Policy Types

1. [IAM policy types: How and when to use them by Matt Luttrell and Josh Joy ](https://aws.amazon.com/blogs/security/iam-policy-types-how-and-when-to-use-them/)

# 5. Examples

1. [AWS Organization Formation is an Infrastructure as Code (IaC) tool for AWS Organizations.](https://github.com/org-formation/org-formation-cli)

# 6. Exam Prep

1. [How I passed the AWS Security Speciality exam with mostly free content](https://dev.to/aws-heroes/how-i-passed-the-aws-security-speciality-exam-with-mostly-free-content-3id3)
- Has lots of links

# 7. PassRole

1. [How to use the PassRole permission with IAM roles by Liam Wadman and Laura Reith](https://aws.amazon.com/blogs/security/how-to-use-the-passrole-permission-with-iam-roles/)
1. [Get to Grips with AWS IAM Roles: Terms, Concepts, and Examples](https://blog.awsfundamentals.com/aws-iam-roles-terms-concepts-and-examples)

# 8. Policy Types

## 8.1. Identity Based Policies

1. [Example IAM identity-based policies](https://docs.aws.amazon.com/IAM/latest/UserGuide/access_policies_examples.html)
1. [Introducing cross-account access capabilities for AWS Step Functions by Siarhei Kazhura](https://aws.amazon.com/blogs/compute/introducing-cross-account-access-capabilities-for-aws-step-functions/)

## 8.2. Resource Based Policies

1. [How to access secrets across AWS accounts by attaching resource-based policies by Apurv Awasthi](https://aws.amazon.com/blogs/security/how-to-access-secrets-across-aws-accounts-by-attaching-resource-based-policies/)
1. [Simplifying cross-account access with Amazon EventBridge resource policies by Stephen Liedig](https://aws.amazon.com/blogs/compute/simplifying-cross-account-access-with-amazon-eventbridge-resource-policies/)
1. [Simplify cross-account access control with Amazon DynamoDB using resource-based policies by Esteban Serna Parra and Ashwin Venkatesh](https://aws.amazon.com/blogs/database/simplify-cross-account-access-control-with-amazon-dynamodb-using-resource-based-policies/)
1. [Approach 1: Resource-based policy access](https://aws.amazon.com/blogs/containers/amazon-eks-pod-identity-a-new-way-for-applications-on-eks-to-obtain-iam-credentials/)

# 9. Permission Boundaries

1. [AWS IAM Permissions Boundary By Digital Cloud Training](https://www.youtube.com/watch?v=t8P8ffqWrsY)
> 2. [[**MY_NEXT**] When and where to use IAM permissions boundaries by Umair Rehmat](https://aws.amazon.com/blogs/security/when-and-where-to-use-iam-permissions-boundaries/)
3. [Secure CDK deployments with IAM permission boundaries by Brian Farnhill and David Turnbull](https://aws.amazon.com/blogs/devops/secure-cdk-deployments-with-iam-permission-boundaries/)

## 9.1. Demo - Permission Boundaries

1. [Apply AWS IAM Permissions Boundary | Hands-on By Digital Cloud Training](https://www.youtube.com/watch?v=D-1u0dBM-q8&)

# 10. Roles

1. [Get to Grips with AWS IAM Roles: Terms, Concepts, and Examples](https://blog.awsfundamentals.com/aws-iam-roles-terms-concepts-and-examples)
- Service Roles and Service Linked Roles
- IAM Pass Role
- Permission Boundaries
- Cross-Account Access

## 10.1. AssumeRoles

1. [[**MY_NEXT**] How do I assume an IAM role using the AWS CLI?](https://repost.aws/knowledge-center/iam-assume-role-cli)

## 10.2. Role Chaining

1. [What is IAM Role Chaining and can I increase the IAM role chaining session duration limit?](https://www.youtube.com/watch?v=2TcKghUbikw)
2. The AWS Service Catalog makes use of Role Chaining
3. [See section "Approach 2: Role chaining in the application"](https://aws.amazon.com/blogs/containers/amazon-eks-pod-identity-a-new-way-for-applications-on-eks-to-obtain-iam-credentials/)
4. [AWS IAM Role Chaining: Walkthrough](https://aws.plainenglish.io/aws-iam-role-chaining-walkthrough-248e48582b92)
5. [AWS IAM Role Chaining by Aashish Gaba](https://aws.plainenglish.io/aws-iam-role-chaining-df41b1101068)

# 11. Session Tags

1. [New for Identity Federation – Use Employee Attributes for Access Control in AWS by Sébastien Stormacq](https://aws.amazon.com/blogs/aws/new-for-identity-federation-use-employee-attributes-for-access-control-in-aws/)
1. [See section "Support for session tags"](https://aws.amazon.com/blogs/containers/amazon-eks-pod-identity-a-new-way-for-applications-on-eks-to-obtain-iam-credentials)

# 12. Service linked Roles

1. [Viewing permission issues with service-linked roles by Rich McDonough ](https://aws.amazon.com/blogs/mt/viewing-permission-issues-with-service-linked-roles/)

# 13. Service Control Policies

1. [How to Use Service Control Policies in AWS Organizations by Frank Phillis](https://aws.amazon.com/blogs/security/how-to-use-service-control-policies-in-aws-organizations/)

# 14. SkillBuilder Courses

1. [Troubleshooting: AWS Identity and Access Management](https://explore.skillbuilder.aws/learn/course/15564/play/76564/troubleshooting-aws-identity-and-access-management)

# 15. Trust Policies

1. [[**MY_NEXT**] How to use trust policies with IAM roles by Jonathan Jenkyn and Liam Wadman](https://aws.amazon.com/blogs/security/how-to-use-trust-policies-with-iam-roles/)

# 16. Tutorials

1. [IAM tutorials](https://docs.aws.amazon.com/IAM/latest/UserGuide/tutorials.html)

# 17. Videos

1. [AWS re:Invent 2020: AWS identity: Next-generation permission management By Brigid Johnson](https://www.youtube.com/watch?v=8vsD_aTtuTo)
2. [AWS re:Invent 2021 - A least privilege journey: AWS IAM policies and Access Analyzer By Brigid Johnson](https://www.youtube.com/watch?v=pKPiPplJNak)
3. [AWS re:Invent 2022 - Harness power of IAM policies & rein in permissions w/Access Analyzer (SEC313) By Brigid Johnson](https://www.youtube.com/watch?v=x-Kh8hKVX74)
4. [[MY NEXT] AWS re:Invent 2018: [REPEAT 1] Become an IAM Policy Master in 60 Minutes or Less (SEC316-R1) By Brigid Johnson](https://www.youtube.com/watch?v=YQsK4MtsELU)
5. [AWS re:Invent 2019: [REPEAT 1] Getting started with AWS identity (SEC209-R1) By Becky Weiss](https://www.youtube.com/watch?v=Zvz-qYYhvMk)
6. [AWS re:Invent 2019: Access control confidence: Right access to the right things (SEC316-R1) By Brigid Johnson](https://www.youtube.com/watch?v=XO4CALyzbVM)