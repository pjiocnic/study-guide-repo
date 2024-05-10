1. [EKS provisioning — VPC, OIDC, MNG, IAM by Yassine Essadraoui](https://medium.com/@yassine.essadraoui_78000/eks-provisioning-vpc-oidc-mng-iam-8d9cef7fcdcd)
1. [OpenID Connect (OIDC): — AWS EKS use case by Yassine Essadraoui](https://medium.com/@yassine.essadraoui_78000/openid-connect-oidc-aws-eks-use-case-a94af59be42b)
1. [Implementing and Understanding IAM Roles for Service Accounts in AWS EKS By Anil Goyal](https://medium.com/@anil.goyal0057/implementing-and-understanding-iam-roles-for-service-accounts-in-aws-eks-00e8fd2a0262)
1. [Introducing OIDC identity provider authentication for Amazon EKS by Rashmi Dwaraka, Mike Stefaniak, and Paavan Mistry ](https://aws.amazon.com/blogs/containers/introducing-oidc-identity-provider-authentication-amazon-eks/)

# ChatGPT

In AWS EKS (Amazon Elastic Kubernetes Service) OIDC (OpenID Connect), both "issuer" and "provider" play crucial roles in enabling Kubernetes clusters running on AWS to authenticate users via OIDC tokens.

**Issuer**: In the context of AWS EKS OIDC, the issuer refers to the identity provider (IDP) that issues the OIDC tokens used for authentication. In other words, the issuer is responsible for generating tokens that Kubernetes clusters can use to verify the identity of users or services attempting to access the cluster. The issuer typically identifies itself using a URL, often the URL of the OIDC provider.

**Provider**: The provider, on the other hand, refers to the service that verifies the tokens issued by the issuer. In AWS EKS OIDC, Amazon Cognito Identity Pools or any other OIDC-compatible identity provider can act as the provider. The provider validates the tokens presented by users or services to ensure that they are genuine and have not been tampered with.

In summary, the issuer generates the OIDC tokens, while the provider verifies these tokens. Together, they establish a secure authentication mechanism for users and services to access Kubernetes clusters in AWS EKS.

#  Anil Goyal

the identity provider in IAM and the **OIDC provider** in EKS are essentially the same entity. They both represent the same **OIDC issuer**, and they use the same public and private keys to sign and verify JWTs. The role of the OIDC provider in IAM is to allow AWS STS to trust JWTs that are issued by the OIDC provider in EKS.