
<!-- TOC -->

- [1. Pod security](#1-pod-security)
- [2. Access management](#2-access-management)
  - [2.1. How does aws-auth configMap work?](#21-how-does-aws-auth-configmap-work)
- [3. EKS Pod Identity](#3-eks-pod-identity)
- [4. OIDC](#4-oidc)
- [5. ServiceAccounts](#5-serviceaccounts)
- [6. RBAC](#6-rbac)
- [7. IRSA](#7-irsa)
- [ProjectedServiceAccountToken](#projectedserviceaccounttoken)

<!-- /TOC -->

# 1. Pod security
1. [Introducing security groups for pods by Mike Stefaniak and Sri Saran Balaji ](https://aws.amazon.com/blogs/containers/introducing-security-groups-for-pods/)
2. [Leveraging CNI custom networking alongside security groups for pods in Amazon EKS by Bin Liu and Haofei Feng](https://aws.amazon.com/blogs/containers/leveraging-cni-custom-networking-alongside-security-groups-for-pods-in-amazon-eks/)
3. [AWS EKS Security Groups Per Pod: Improve the Security of Your Kubernetes Clusters by Seifeddine](https://medium.com/@seifeddinerajhi/aws-eks-security-groups-per-pod-improve-the-security-of-your-kubernetes-clusters-a23a961793dc)
1. [EKS Best Practices Guides > Security Groups Per Pod](https://aws.github.io/aws-eks-best-practices/networking/sgpp/)
1. [Implementing Pod Security Standards in Amazon EKS by Jayaprakash Alawala and Jimmy Ray](https://aws.amazon.com/blogs/containers/implementing-pod-security-standards-in-amazon-eks/)

# 2. Access management

1. [A deep dive into simplified Amazon EKS access management controls by Sheetal Joshi and Mike Stefaniak](https://aws.amazon.com/blogs/containers/a-deep-dive-into-simplified-amazon-eks-access-management-controls/)

## 2.1. How does aws-auth configMap work?

1. [Enabling IAM principal access to your cluster](https://docs.aws.amazon.com/eks/latest/userguide/add-user-role.html)
1. [Identity and Access Management](https://aws.github.io/aws-eks-best-practices/security/docs/iam/)

# 3. EKS Pod Identity

1. [Amazon EKS Pod Identity: a new way for applications on EKS to obtain IAM credentials by George John, Ashok Srirama, and Hemanth AVS](https://aws.amazon.com/blogs/containers/amazon-eks-pod-identity-a-new-way-for-applications-on-eks-to-obtain-iam-credentials/)
1. [Amazon EKS Pod Identity simplifies IAM permissions for applications on Amazon EKS clusters by Donnie Prakoso](https://aws.amazon.com/blogs/aws/amazon-eks-pod-identity-simplifies-iam-permissions-for-applications-on-amazon-eks-clusters/)

# 4. OIDC

1. [[BEST] An Illustrated Guide to OAuth and OpenID Connect](https://developer.okta.com/blog/2019/10/21/illustrated-guide-to-oauth-and-oidc)
1. [[BEST] Understanding how EKS and IAM work together](https://www.padok.fr/en/blog/aws-eks-iam)
1. [Create an oidc identity provider](https://archive.eksworkshop.com/beginner/110_irsa/oidc-provider/)
1. [Creating OpenID Connect (OIDC) identity providers](https://docs.aws.amazon.com/IAM/latest/UserGuide/id_roles_providers_create_oidc.html)
1. [Seamlessly Secure Your EKS Cluster: AWS EKS OIDC Identity Integration By Yash Thube](https://medium.com/@thube09/seamlessly-secure-your-eks-cluster-aws-eks-oidc-identity-integration-7045abdc7d4c)
1. [Building an App Using Amazon Cognito and an OpenID Connect Identity Providerby Shon Shah and Abrom Douglas](https://aws.amazon.com/blogs/security/building-an-app-using-amazon-cognito-and-an-openid-connect-identity-provider/)
1. [Consistent OIDC authentication across multiple EKS clusters using Kube-OIDC-Proxy by Jeremy Cowan](https://aws.amazon.com/blogs/opensource/consistent-oidc-authentication-across-multiple-eks-clusters-using-kube-oidc-proxy/)
1. [Use IAM roles to connect GitHub Actions to actions in AWS by David Rowe](https://aws.amazon.com/blogs/security/use-iam-roles-to-connect-github-actions-to-actions-in-aws/)

# 5. ServiceAccounts

1. [Introducing fine-grained IAM roles for service accounts by Micah Hausler and Michael Hausenblas](https://aws.amazon.com/blogs/opensource/introducing-fine-grained-iam-roles-service-accounts/)
1. [Diving into IAM Roles for Service Accounts by Gaurav Pilay](https://aws.amazon.com/blogs/containers/diving-into-iam-roles-for-service-accounts/)

# 6. RBAC

1. [How to manage access and EKS permissions?](https://www.padok.fr/en/blog/aws-eks-cluster)

# 7. IRSA

1. [[START HERE] Understanding IAM roles for service accounts, IRSA, on AWS EKS by Ankit Wal](https://medium.com/@ankit.wal/the-how-of-iam-roles-for-service-accounts-irsa-on-aws-eks-3d76badb8942)
1. [IAM Roles for Service Accounts (IRSA) in AWS EKS within and cross AWS Accounts](https://platformwale.blog/2023/08/02/iam-roles-for-service-accounts-irsa-in-aws-eks-within-and-cross-aws-accounts/)
1. [Diving into IAM Roles for Service Accounts by Gaurav Pilay](https://aws.amazon.com/blogs/containers/diving-into-iam-roles-for-service-accounts/)
1. [IAM Roles for Service Accounts](https://eksctl.io/usage/iamserviceaccounts/)
1. [IAM Roles for Service Accounts @ EKS Workshop](https://www.eksworkshop.com/docs/security/iam-roles-for-service-accounts/)
1. [[KNOWLEDGE-CENTER] Why can't I use an IAM role for the service account in my Amazon EKS pod?](https://repost.aws/knowledge-center/eks-pods-iam-role-service-accounts)
1. [Introducing fine-grained IAM roles for service accounts by Micah Hausler and Michael Hausenblas](https://aws.amazon.com/blogs/opensource/introducing-fine-grained-iam-roles-service-accounts/)
1. [IAM Roles for Service Accounts | Learn about IRSA with demo in 20 mins by Visa2Learn](https://www.youtube.com/watch?v=otmLHWW3Tos)
1. [Implementing Cross-Account Access with Amazon EKS using IAM Roles for Service Accounts by Praveen Dandu](https://www.linkedin.com/pulse/implementing-cross-account-access-amazon-eks-using-iam-praveen-dandu-zj8te/)

# ProjectedServiceAccountToken

1. [Kubernetes Bound Projected Service Account Token Volumes Might Surprise You by Nick Gibbon](https://medium.com/pareture/kubernetes-bound-projected-service-account-token-volumes-might-surprise-you-434ff2cd1483)
1. [Diving into IAM Roles for Service Accounts by Gaurav Pilay](https://aws.amazon.com/blogs/containers/diving-into-iam-roles-for-service-accounts/)