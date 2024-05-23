
<!-- TOC -->

- [1. Dashboard](#1-dashboard)
- [2. ABAC](#2-abac)
- [Cross Account Role Pattern](#cross-account-role-pattern)
- [3. Organizations](#3-organizations)
- [4. courses](#4-courses)
- [5. Different Policy Types](#5-different-policy-types)
- [6. Examples](#6-examples)
- [7. Exam Prep](#7-exam-prep)
- [8. PassRole](#8-passrole)
- [9. Policy Types](#9-policy-types)
  - [9.1. Different Policy Types](#91-different-policy-types)
  - [9.2. Identity Based Policies](#92-identity-based-policies)
  - [9.3. Resource Based Policies](#93-resource-based-policies)
- [10. Permission Boundaries](#10-permission-boundaries)
  - [10.1. Demo - Permission Boundaries](#101-demo---permission-boundaries)
- [11. Roles](#11-roles)
  - [11.1. AssumeRoles](#111-assumeroles)
  - [11.2. Role Chaining](#112-role-chaining)
- [IAM Paths](#iam-paths)
- [Temporary Elevated Access](#temporary-elevated-access)
- [12. Session Tags](#12-session-tags)
- [13. Service linked Roles](#13-service-linked-roles)
- [14. Service Control Policies](#14-service-control-policies)
- [15. SkillBuilder Courses](#15-skillbuilder-courses)
- [16. Trust Policies](#16-trust-policies)
- [17. Tutorials](#17-tutorials)
- [18. Videos](#18-videos)

<!-- /TOC -->

# 1. Dashboard

1. [Get to Grips with AWS IAM Roles: Terms, Concepts, and Examples](https://blog.awsfundamentals.com/aws-iam-roles-terms-concepts-and-examples)
    - Service Roles and Service Linked Roles
    - IAM Pass Role
    - Permission Boundaries
    - Cross-Account Access
1. [[**MY_NEXT**] When and where to use IAM permissions boundaries by Umair Rehmat](https://aws.amazon.com/blogs/security/when-and-where-to-use-iam-permissions-boundaries/)
1. [[**MY_NEXT**] IAM policy types: How and when to use them by Matt Luttrell and Josh Joy ](https://aws.amazon.com/blogs/security/iam-policy-types-how-and-when-to-use-them/)

# 2. ABAC

1. [Introducing attribute-based access controls (ABAC) for Amazon SQS by Vikas Panghal](https://aws.amazon.com/blogs/compute/introducing-attribute-based-access-controls-abac-for-amazon-sqs/)
1. [Scaling AWS Lambda permissions with Attribute-Based Access Control (ABAC) by Julian Wood and Chris McPeek](https://aws.amazon.com/blogs/compute/scaling-aws-lambda-permissions-with-attribute-based-access-control-abac/)

# Cross Account Role Pattern

1. [How to Enable Cross-Account Access to the AWS Management Console by Mike Pope](https://aws.amazon.com/blogs/security/how-to-enable-cross-account-access-to-the-aws-management-console/)
1. [How to use trust policies with IAM roles by Jonathan Jenkyn and Liam Wadman ](https://aws.amazon.com/blogs/security/how-to-use-trust-policies-with-iam-roles/)
- This pattern mentioned in this blog
1. [Design patterns to access cross-account secrets stored in AWS Secrets Manager by Gowri Balasubramanian and Harsha Sharma](https://aws.amazon.com/blogs/database/design-patterns-to-access-cross-account-secrets-stored-in-aws-secrets-manager/)
- Accessing secrets across accounts

# 3. Organizations

1. [How to Use Service Control Policies in AWS Organizations by Frank Phillis](https://aws.amazon.com/blogs/security/how-to-use-service-control-policies-in-aws-organizations/)
2. [Best Practices for Organizational Units with AWS Organizations by Andrew Blackham and Sam Elmalak ](https://aws.amazon.com/blogs/mt/best-practices-for-organizational-units-with-aws-organizations/)
3. [How to manage Route53 hosted zones in a multi-account environment](https://theburningmonk.com/2021/05/how-to-manage-route53-hosted-zones-in-a-multi-account-environment/)

# 4. courses

1. [Effective IAM for Amazon Web Services](https://www.effectiveiam.com/)

# 5. Different Policy Types

1. [IAM policy types: How and when to use them by Matt Luttrell and Josh Joy ](https://aws.amazon.com/blogs/security/iam-policy-types-how-and-when-to-use-them/)

# 6. Examples

1. [AWS Organization Formation is an Infrastructure as Code (IaC) tool for AWS Organizations.](https://github.com/org-formation/org-formation-cli)

# 7. Exam Prep

1. [How I passed the AWS Security Speciality exam with mostly free content](https://dev.to/aws-heroes/how-i-passed-the-aws-security-speciality-exam-with-mostly-free-content-3id3)
- Has lots of links

# 8. PassRole

1. [How to use the PassRole permission with IAM roles by Liam Wadman and Laura Reith](https://aws.amazon.com/blogs/security/how-to-use-the-passrole-permission-with-iam-roles/)
1. [Get to Grips with AWS IAM Roles: Terms, Concepts, and Examples](https://blog.awsfundamentals.com/aws-iam-roles-terms-concepts-and-examples)

# 9. Policy Types

## 9.1. Different Policy Types

1. [IAM policy types: How and when to use them by Matt Luttrell and Josh Joy](https://aws.amazon.com/blogs/security/iam-policy-types-how-and-when-to-use-them/)

## 9.2. Identity Based Policies

1. [Example IAM identity-based policies](https://docs.aws.amazon.com/IAM/latest/UserGuide/access_policies_examples.html)
1. [Introducing cross-account access capabilities for AWS Step Functions by Siarhei Kazhura](https://aws.amazon.com/blogs/compute/introducing-cross-account-access-capabilities-for-aws-step-functions/)

## 9.3. Resource Based Policies

1. [How to access secrets across AWS accounts by attaching resource-based policies by Apurv Awasthi](https://aws.amazon.com/blogs/security/how-to-access-secrets-across-aws-accounts-by-attaching-resource-based-policies/)
1. [Simplifying cross-account access with Amazon EventBridge resource policies by Stephen Liedig](https://aws.amazon.com/blogs/compute/simplifying-cross-account-access-with-amazon-eventbridge-resource-policies/)
1. [Simplify cross-account access control with Amazon DynamoDB using resource-based policies by Esteban Serna Parra and Ashwin Venkatesh](https://aws.amazon.com/blogs/database/simplify-cross-account-access-control-with-amazon-dynamodb-using-resource-based-policies/)
1. [Approach 1: Resource-based policy access](https://aws.amazon.com/blogs/containers/amazon-eks-pod-identity-a-new-way-for-applications-on-eks-to-obtain-iam-credentials/)

# 10. Permission Boundaries

1. [AWS IAM Permissions Boundary By Digital Cloud Training](https://www.youtube.com/watch?v=t8P8ffqWrsY)
2. [[**MY_NEXT**] When and where to use IAM permissions boundaries by Umair Rehmat](https://aws.amazon.com/blogs/security/when-and-where-to-use-iam-permissions-boundaries/)
3. [Secure CDK deployments with IAM permission boundaries by Brian Farnhill and David Turnbull](https://aws.amazon.com/blogs/devops/secure-cdk-deployments-with-iam-permission-boundaries/)

## 10.1. Demo - Permission Boundaries

1. [Apply AWS IAM Permissions Boundary | Hands-on By Digital Cloud Training](https://www.youtube.com/watch?v=D-1u0dBM-q8&)

# 11. Roles

1. [Get to Grips with AWS IAM Roles: Terms, Concepts, and Examples](https://blog.awsfundamentals.com/aws-iam-roles-terms-concepts-and-examples)
- Service Roles and Service Linked Roles
- IAM Pass Role
- Permission Boundaries
- Cross-Account Access

## 11.1. AssumeRoles

1. [[**MY_NEXT**] How do I assume an IAM role using the AWS CLI?](https://repost.aws/knowledge-center/iam-assume-role-cli)

## 11.2. Role Chaining

1. [[**START_HERE**] AWS IAM Role Chaining by Aashish Gaba](https://aws.plainenglish.io/aws-iam-role-chaining-df41b1101068)

1. [What is IAM Role Chaining and can I increase the IAM role chaining session duration limit?](https://www.youtube.com/watch?v=2TcKghUbikw)
1. The AWS Service Catalog makes use of Role Chaining
1. [See section "Approach 2: Role chaining in the application"](https://aws.amazon.com/blogs/containers/amazon-eks-pod-identity-a-new-way-for-applications-on-eks-to-obtain-iam-credentials/)
1. [AWS IAM Role Chaining: Walkthrough](https://aws.plainenglish.io/aws-iam-role-chaining-walkthrough-248e48582b92)

# IAM Paths

1. [How to enforce creation of roles in a specific path: Use IAM role naming in hierarchy models by Varun Sharma and Nishant Mainro ](https://aws.amazon.com/blogs/security/how-to-enforce-creation-of-roles-in-a-specific-path-use-iam-role-naming-in-hierarchy-models/)
1. [Optimize AWS administration with IAM paths by David Rowe and Valentine Reid ](https://aws.amazon.com/blogs/security/optimize-aws-administration-with-iam-paths/)

# Temporary Elevated Access

1. [Managing temporary elevated access to your AWS environment by James Greenwood, Bikash Behera, and Kevin Higgins](https://aws.amazon.com/blogs/security/managing-temporary-elevated-access-to-your-aws-environment/)
1. [Temporary elevated access management with IAM Identity Center by Taiwo Awoyinfa, James Greenwood, and Varvara Semenova](https://aws.amazon.com/blogs/security/temporary-elevated-access-management-with-iam-identity-center/)

# 12. Session Tags

1. [New for Identity Federation – Use Employee Attributes for Access Control in AWS by Sébastien Stormacq](https://aws.amazon.com/blogs/aws/new-for-identity-federation-use-employee-attributes-for-access-control-in-aws/)
1. [See section "Support for session tags"](https://aws.amazon.com/blogs/containers/amazon-eks-pod-identity-a-new-way-for-applications-on-eks-to-obtain-iam-credentials)

# 13. Service linked Roles

1. [Viewing permission issues with service-linked roles by Rich McDonough ](https://aws.amazon.com/blogs/mt/viewing-permission-issues-with-service-linked-roles/)

# 14. Service Control Policies

1. [How to Use Service Control Policies in AWS Organizations by Frank Phillis](https://aws.amazon.com/blogs/security/how-to-use-service-control-policies-in-aws-organizations/)

# 15. SkillBuilder Courses

1. [Troubleshooting: AWS Identity and Access Management](https://explore.skillbuilder.aws/learn/course/15564/play/76564/troubleshooting-aws-identity-and-access-management)

# 16. Trust Policies

1. [[**MY_NEXT**] How to use trust policies with IAM roles by Jonathan Jenkyn and Liam Wadman](https://aws.amazon.com/blogs/security/how-to-use-trust-policies-with-iam-roles/)

# 17. Tutorials

1. [IAM tutorials](https://docs.aws.amazon.com/IAM/latest/UserGuide/tutorials.html)

# 18. Videos

1. [AWS re:Invent 2020: AWS identity: Next-generation permission management By Brigid Johnson](https://www.youtube.com/watch?v=8vsD_aTtuTo)
2. [AWS re:Invent 2021 - A least privilege journey: AWS IAM policies and Access Analyzer By Brigid Johnson](https://www.youtube.com/watch?v=pKPiPplJNak)
3. [AWS re:Invent 2022 - Harness power of IAM policies & rein in permissions w/Access Analyzer (SEC313) By Brigid Johnson](https://www.youtube.com/watch?v=x-Kh8hKVX74)
4. [[MY NEXT] AWS re:Invent 2018: [REPEAT 1] Become an IAM Policy Master in 60 Minutes or Less (SEC316-R1) By Brigid Johnson](https://www.youtube.com/watch?v=YQsK4MtsELU)
5. [AWS re:Invent 2019: [REPEAT 1] Getting started with AWS identity (SEC209-R1) By Becky Weiss](https://www.youtube.com/watch?v=Zvz-qYYhvMk)
6. [AWS re:Invent 2019: Access control confidence: Right access to the right things (SEC316-R1) By Brigid Johnson](https://www.youtube.com/watch?v=XO4CALyzbVM)