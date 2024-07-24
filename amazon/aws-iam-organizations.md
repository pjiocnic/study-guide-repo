<h1>AWS IAM Organizations</h1>

<!-- TOC -->

- [1. Curate](#1-curate)
- [2. Getting Started](#2-getting-started)
- [3. AWS Solutions Library](#3-aws-solutions-library)
- [4. Best Practices](#4-best-practices)
- [5. Setting up Environment](#5-setting-up-environment)
  - [5.1. Using Cloudformation](#51-using-cloudformation)
  - [5.2. Using CLI](#52-using-cli)
- [6. Examples](#6-examples)
- [7. QuickStarts](#7-quickstarts)
- [8. Multiple accounts](#8-multiple-accounts)
- [9. Route 53](#9-route-53)

<!-- /TOC -->

# 1. Curate

1. [[MUST SEE] Getting started with AWS Multi-account approach by Alejandro Lazaro ](https://dev.to/aws-builders/getting-started-with-aws-multi-account-approach-4j5c)
1. [AWS Account Structure: Think twice before using AWS Organizations](https://cloudonaut.io/aws-account-structure-think-twice-before-using-aws-organizations/)
1. [How SMBs can deploy a multi-account environment quickly using AWS Organizations and AWS CloudFormation StackSets by Nivedita Tripathi, Alex Torres, Caroline Johnston, Justin Plock, and Siddhesh Jog](https://aws.amazon.com/blogs/mt/deploy-a-multi-account-environment-in-under-30-minutes-using-aws-cloudformation-stacksets/)
1. [[Hierarchy Diagram] Organizing Your AWS Environment Using Multiple Accounts AWS Whitepaper](https://docs.aws.amazon.com/whitepapers/latest/organizing-your-aws-environment/core-concepts.html)
1. [Automated AWS Organization Creation Using CLI By Teri Radichel](https://medium.com/cloud-security/automated-aws-organization-creation-4d31519c4a32)
1. [Creating and configuring an organization from console](https://docs.aws.amazon.com/organizations/latest/userguide/orgs_tutorials_basic.html)

# 2. Getting Started

1. [AWS Multi-Account Strategy I: AWS Organizations, OUs, and Accounts by Sarah Chen](https://towardsaws.com/aws-multi-account-strategy-i-aws-organizations-ous-and-accounts-a4860f475161)
1. [AWS Multi-account Strategy II: IAM Identities and SCPs by Sarah Chen](https://medium.com/towards-aws/aws-multi-account-strategy-ii-iam-identities-and-scps-a84e371d72b7)
1. [AWS Multi-account Strategy III: Federated Login and SSO by Sarah Chen](https://medium.com/towards-aws/aws-multi-account-strategy-iii-federated-login-and-sso-cc49b8be164f)

# 3. AWS Solutions Library

1. [Account Assessment for AWS Organizations](https://aws.amazon.com/solutions/implementations/account-assessment-for-aws-organizations/)
2. [Multi-account strategy for small and medium businesses by Alex Torres and Siddhesh Jog](https://aws.amazon.com/blogs/mt/multi-account-strategy-for-small-and-medium-businesses/)

# 4. Best Practices

2. [[MY NEXT] Best Practices for Organizational Units with AWS Organizations by Andrew Blackham and Sam Elmalak ](https://aws.amazon.com/blogs/mt/best-practices-for-organizational-units-with-aws-organizations/)

# 5. Setting up Environment

## 5.1. Using Cloudformation

1. [[BEST] Deploy AWS Organizations resources by using CloudFormation by Matt Luttrell and Swara Gandhi](https://aws.amazon.com/blogs/security/deploy-aws-organizations-resources-by-using-cloudformation/)
- [[CFN] CloudFormationForAWSOrganizations.yaml](./templates/organizations/CloudFormationForAWSOrganizations.yaml)
2. [Foundational Organizational Unit Structure and Accounts](https://github.com/cloud-foundations-on-aws/cloud-foundations-templates/tree/main/organizations/foundational-organizational-unit-structure)

## 5.2. Using CLI

1. [[BEST] Workshop AWS Account Setup > Master Account > AWS organization setup](https://workshop-aws-account-setup.fstehle.com/master-account/aws-organization/)
1. [Multi-Account Security Governance Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/d3f60827-89f2-46a8-9be7-6e7185bd7665/en-US/2-service-guardrails/cloudtrail)

# 6. Examples

1. [AWS Organizations and Service Control Policies](https://github.com/hamidnazari/workshop-aws-org-scp)

# 7. QuickStarts

1. [Set up AWS Organization @ Multi-Account Security Governance Workshop](https://catalog.us-east-1.prod.workshops.aws/workshops/d3f60827-89f2-46a8-9be7-6e7185bd7665/en-US/1-env-setup/setup-org)
2. [Deploy AWS Organizations resources by using CloudFormation by Matt Luttrell and Swara Gandhi](https://aws.amazon.com/blogs/security/deploy-aws-organizations-resources-by-using-cloudformation/)
3. [AWS organization setup @ Workshop AWS Account Setup](https://workshop-aws-account-setup.fstehle.com/master-account/aws-organization/)

# 8. Multiple accounts

1. [How to use multiple AWS accounts for managing quotas by James Beswick](https://aws.amazon.com/blogs/compute/operating-lambda-application-design-and-service-quotas-part-1/)
2. [[Whitepaper] Organizing Your AWS Environment Using Multiple Accounts](https://docs.aws.amazon.com/whitepapers/latest/organizing-your-aws-environment/organizing-your-aws-environment.html)
3. [Multi-account strategy for small and medium businesses by Alex Torres and Siddhesh Jog](https://aws.amazon.com/blogs/mt/multi-account-strategy-for-small-and-medium-businesses/)

# 9. Route 53
1. [How to manage Route53 hosted zones in a multi-account environment](https://theburningmonk.com/2021/05/how-to-manage-route53-hosted-zones-in-a-multi-account-environment/)

