<h1>AWS IAM Organizations</h1>

<!-- TOC -->

- [1. Quick Start](#1-quick-start)
- [2. Curate](#2-curate)
- [Cost Reporting](#cost-reporting)
- [3. Getting Started](#3-getting-started)
- [4. AWS Solutions Library](#4-aws-solutions-library)
- [5. Best Practices](#5-best-practices)
- [6. Setting up Centralized Logging](#6-setting-up-centralized-logging)
- [7. Using StackSets in an Organization](#7-using-stacksets-in-an-organization)
- [8. Setting up Environment](#8-setting-up-environment)
  - [8.1. Using Cloudformation](#81-using-cloudformation)
  - [8.2. Using CLI](#82-using-cli)
- [9. Centralizing policy-management in an organization](#9-centralizing-policy-management-in-an-organization)
- [12. Multiple accounts](#12-multiple-accounts)
- [13. Centralizing Route 53 in an organization](#13-centralizing-route-53-in-an-organization)
- [14. Closing Member Accounts (aka legacy accounts)](#14-closing-member-accounts-aka-legacy-accounts)
- [15. Tagging accounts in an Organization](#15-tagging-accounts-in-an-organization)
- [16. SCP Examples](#16-scp-examples)

<!-- /TOC -->

# 1. Quick Start

1. [[ASSOCIATESHARED] AWS Organizations by Cantrill](https://learn.cantrill.io/courses/1820301/lectures/41301370)
1. [Set up AWS Organization @ Multi-Account Security Governance Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/d3f60827-89f2-46a8-9be7-6e7185bd7665/en-US/1-env-setup/setup-org)
2. [Deploy AWS Organizations resources by using CloudFormation by Matt Luttrell and Swara Gandhi](https://aws.amazon.com/blogs/security/deploy-aws-organizations-resources-by-using-cloudformation/)
3. [AWS organization setup @ Workshop AWS Account Setup](https://workshop-aws-account-setup.fstehle.com/master-account/aws-organization/)

# 2. Curate

1. [AWS Organizations: The Key to Managing Your Cloud Infrastructure Effectively by Tobias Schmidt](https://blog.awsfundamentals.com/aws-organizations-the-key-to-managing-your-cloud-infrastructure-effectively)
1. [[MUST SEE] Getting started with AWS Multi-account approach by Alejandro Lazaro ](https://dev.to/aws-builders/getting-started-with-aws-multi-account-approach-4j5c)
1. [AWS Account Structure: Think twice before using AWS Organizations](https://cloudonaut.io/aws-account-structure-think-twice-before-using-aws-organizations/)
1. [How SMBs can deploy a multi-account environment quickly using AWS Organizations and AWS CloudFormation StackSets by Nivedita Tripathi, Alex Torres, Caroline Johnston, Justin Plock, and Siddhesh Jog](https://aws.amazon.com/blogs/mt/deploy-a-multi-account-environment-in-under-30-minutes-using-aws-cloudformation-stacksets/)
1. [[Hierarchy Diagram] Organizing Your AWS Environment Using Multiple Accounts AWS Whitepaper](https://docs.aws.amazon.com/whitepapers/latest/organizing-your-aws-environment/core-concepts.html)
1. [Automated AWS Organization Creation Using CLI By Teri Radichel](https://medium.com/cloud-security/automated-aws-organization-creation-4d31519c4a32)
1. [Creating and configuring an organization from console](https://docs.aws.amazon.com/organizations/latest/userguide/orgs_tutorials_basic.html)

# Cost Reporting

1. [Cost Tagging and Reporting with AWS Organizations by Stephanie Gooch and Bowen Wang ](https://aws.amazon.com/blogs/aws-cloud-financial-management/cost-tagging-and-reporting-with-aws-organizations/)
1. [Cost Reporting Based on AWS Organizations Account ID Tags by Bowen Wang](https://aws.amazon.com/blogs/aws-cloud-financial-management/cost-reporting-based-on-aws-organizations-account-id-tags/)

# 3. Getting Started

1. [AWS Multi-Account Strategy I: AWS Organizations, OUs, and Accounts by Sarah Chen](https://towardsaws.com/aws-multi-account-strategy-i-aws-organizations-ous-and-accounts-a4860f475161)
1. [AWS Multi-account Strategy II: IAM Identities and SCPs by Sarah Chen](https://medium.com/towards-aws/aws-multi-account-strategy-ii-iam-identities-and-scps-a84e371d72b7)
1. [AWS Multi-account Strategy III: Federated Login and SSO by Sarah Chen](https://medium.com/towards-aws/aws-multi-account-strategy-iii-federated-login-and-sso-cc49b8be164f)

# 4. AWS Solutions Library

1. [Account Assessment for AWS Organizations](https://aws.amazon.com/solutions/implementations/account-assessment-for-aws-organizations/)
2. [Multi-account strategy for small and medium businesses by Alex Torres and Siddhesh Jog](https://aws.amazon.com/blogs/mt/multi-account-strategy-for-small-and-medium-businesses/)

# 5. Best Practices

2. [[MY NEXT] Best Practices for Organizational Units with AWS Organizations by Andrew Blackham and Sam Elmalak ](https://aws.amazon.com/blogs/mt/best-practices-for-organizational-units-with-aws-organizations/)

# 6. Setting up Centralized Logging

1. [[MUST SEE] Stream Amazon CloudWatch Logs to a Centralized Account for Audit and Analysis by David Bailey](https://aws.amazon.com/blogs/architecture/stream-amazon-cloudwatch-logs-to-a-centralized-account-for-audit-and-analysis/)
    - Uses Firehouse
    - cross account logging without OU

# 7. Using StackSets in an Organization

See my-github/study-guide-repo/amazon/aws-iac-cloudformation.md#StackSets

# 8. Setting up Environment

## 8.1. Using Cloudformation

1. [[BEST] Deploy AWS Organizations resources by using CloudFormation by Matt Luttrell and Swara Gandhi](https://aws.amazon.com/blogs/security/deploy-aws-organizations-resources-by-using-cloudformation/)
- [[CFN] CloudFormationForAWSOrganizations.yaml](./templates/organizations/CloudFormationForAWSOrganizations.yaml)
2. [Foundational Organizational Unit Structure and Accounts](https://github.com/cloud-foundations-on-aws/cloud-foundations-templates/tree/main/organizations/foundational-organizational-unit-structure)
3. [[DEMO] How to Use AWS Organizations to Automate End-to-End Account Creation by David Schonbrun](https://aws.amazon.com/blogs/security/how-to-use-aws-organizations-to-automate-end-to-end-account-creation/)

## 8.2. Using CLI

1. [[BEST] Workshop AWS Account Setup > Master Account > AWS organization setup](https://workshop-aws-account-setup.fstehle.com/master-account/aws-organization/)
1. [Multi-Account Security Governance Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/d3f60827-89f2-46a8-9be7-6e7185bd7665/en-US/2-service-guardrails/cloudtrail)
1. [Announcing AWS Organizations: Centrally Manage Multiple AWS Accounts by Caitlyn Shim ](https://aws.amazon.com/blogs/security/announcing-aws-organizations-centrally-manage-multiple-aws-accounts/)

# 9. Centralizing policy-management in an organization

1. [AWS Organizations – Policy-Based Management for Multiple AWS Accounts by Jeff Barr](https://aws.amazon.com/blogs/aws/aws-organizations-policy-based-management-for-multiple-aws-accounts/)

# 12. Multiple accounts

1. [How to use multiple AWS accounts for managing quotas by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-application-design-and-service-quotas-part-1/)
2. [[Whitepaper] Organizing Your AWS Environment Using Multiple Accounts](https://docs.aws.amazon.com/whitepapers/latest/organizing-your-aws-environment/organizing-your-aws-environment.html)
3. [Multi-account strategy for small and medium businesses by Alex Torres and Siddhesh Jog](https://aws.amazon.com/blogs/mt/multi-account-strategy-for-small-and-medium-businesses/)

# 13. Centralizing Route 53 in an organization

1. [How to manage Route53 hosted zones in a multi-account environment](https://theburningmonk.com/2021/05/how-to-manage-route53-hosted-zones-in-a-multi-account-environment/)
1. [ Workshop AWS Account Setup > Shared Services Account > Setting up a DNS zone in Route53](https://workshop-aws-account-setup.fstehle.com/shared-services-account/route53/)

# 14. Closing Member Accounts (aka legacy accounts)

1. [AWS Organizations now provides a simple, scalable and more secure way to close your member accounts by Eric Peña](https://aws.amazon.com/blogs/mt/aws-organizations-now-provides-a-simple-scalable-and-more-secure-way-to-close-your-member-accounts/)

# 15. Tagging accounts in an Organization

1. [Simplifying permissions management at scale using tags in AWS Organizations by Eric Peña ](https://aws.amazon.com/blogs/mt/simplifying-permissions-management-at-scale-using-tags-in-aws-organizations/)

# 16. SCP Examples

1. [Service Control Policy examples](https://github.com/aws-samples/service-control-policy-examples/tree/main)
1. [AWS Organizations and Service Control Policies](https://github.com/hamidnazari/workshop-aws-org-scp)