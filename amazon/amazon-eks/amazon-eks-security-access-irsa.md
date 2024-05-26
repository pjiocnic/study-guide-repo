<h1>IAM roles for service accounts (IRSA)</h1>

<!-- TOC -->

- [1. Concepts](#1-concepts)
- [Cross Acccount Access Using SA](#cross-acccount-access-using-sa)
- [2. Trust policy for OIDC provider](#2-trust-policy-for-oidc-provider)
- [3. Token volume projection](#3-token-volume-projection)
- [4. Samples](#4-samples)
- [5. Repost](#5-repost)
- [6. Videos](#6-videos)
- [7. Workshops](#7-workshops)

<!-- /TOC -->

# 1. Concepts

1. [[_**DOC-MAKE_NOTES**_] IAM roles for service accounts](https://docs.aws.amazon.com/eks/latest/userguide/iam-roles-for-service-accounts.html)
1. [AWS IAM: EKS Pod Identity vs OpenID Connect Comparison](https://www.perfectscale.io/blog/eks-iam-oidc-vs-pod-identity)
1. [[_**BEST**_] Diving into IAM Roles for Service Accounts by Gaurav Pilay](https://aws.amazon.com/blogs/containers/diving-into-iam-roles-for-service-accounts/)
1. [[_**MY_NEXT**_] Introducing fine-grained IAM roles for service accounts by Micah Hausler and Michael Hausenblas](https://aws.amazon.com/blogs/opensource/introducing-fine-grained-iam-roles-service-accounts/)
1. [[**START_HERE**] Understanding IAM roles for service accounts, IRSA, on AWS EKS by Ankit Wal](https://medium.com/@ankit.wal/the-how-of-iam-roles-for-service-accounts-irsa-on-aws-eks-3d76badb8942)
1. [IAM Roles for Service Accounts (IRSA) in AWS EKS within and cross AWS Accounts](https://platformwale.blog/2023/08/02/iam-roles-for-service-accounts-irsa-in-aws-eks-within-and-cross-aws-accounts/)
1. [IAM Roles for Service Accounts](https://eksctl.io/usage/iamserviceaccounts/)
1. [Implementing Cross-Account Access with Amazon EKS using IAM Roles for Service Accounts by Praveen Dandu](https://www.linkedin.com/pulse/implementing-cross-account-access-amazon-eks-using-iam-praveen-dandu-zj8te/)
1. [How a Pod assumes an AWS identity? By James Mak](https://medium.com/airwalk/how-a-pod-assumes-an-aws-identity-284fc6fda873)
1. [[**START_HERE/MAKE_NOTES**] Implementing and Understanding IAM Roles for Service Accounts in AWS EKS by Anil Goyal](https://medium.com/@anil.goyal0057/implementing-and-understanding-iam-roles-for-service-accounts-in-aws-eks-00e8fd2a0262)

# Cross Acccount Access Using SA

1. [Cross account IAM roles for Kubernetes service accounts by Amit Borulkar](https://aws.amazon.com/blogs/containers/cross-account-iam-roles-for-kubernetes-service-accounts/)

# 2. Trust policy for OIDC provider

1. [How to use trust policies with IAM roles by Jonathan Jenkyn and Liam Wadman](https://aws.amazon.com/blogs/security/how-to-use-trust-policies-with-iam-roles/)
- the meaning of `sub` and `aud` is used as claims in Access Tokens
1. [AWS: EKS, OpenID Connect, and ServiceAccounts](https://rtfm.co.ua/en/aws-eks-openid-connect-and-serviceaccounts/)
- How to find and see the contents of access token

```bash
$ token=`kubectl -n kube-system exec external-dns-85587d4b76-2flhg -- cat /var/run/secrets/kubernetes.io/serviceaccount/token`
```

```bash
$ jwt decode $token

Token header
------------
{
  "alg": "RS256",
  "kid": "64aacc8aa986bf6161312dfdfeba00e63ed64f9d"
}

Token claims
------------
{
  "aud": [
    "https://kubernetes.default.svc"
  ],
  "exp": 1720254790,
  "iat": 1688718790,
  "iss": "https://oidc.eks.us-east-1.amazonaws.com/id/FDF***F2F",
  "kubernetes.io": {
    "namespace": "kube-system",
    "pod": {
      "name": "external-dns-85587d4b76-2flhg",
      "uid": "d59b56f1-fa01-4a0f-8897-1933926e4d42"
    },
    "serviceaccount": {
      "name": "external-dns",
      "uid": "38c8f023-60bf-416e-b6c2-d37939ac3c06"
    },
    "warnafter": 1688722397
  },
  "nbf": 1688718790,
  "sub": "system:serviceaccount:kube-system:external-dns"
}
```

and this usage can be seen in a trust policy(example from https://aws.amazon.com/blogs/security/how-to-use-trust-policies-with-iam-roles/)

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Principal": {
                "Federated": "arn:aws:iam::11112222333:oidc-provider/auth.example.com"
            },
            "Action": "sts:AssumeRoleWithWebIdentity",
            "Condition": {
                "StringEquals": {
                    "auth.example.com:sub": "Administrator",
                    "auth.example.com:aud": "MyappWebIdentity"
                }
            }
        }
    ]
}
```

# 3. Token volume projection

1. [Understanding Service Accounts in Kubernetes by Mohammad Asim Ayub](https://medium.com/codex/understanding-service-accounts-in-kubernetes-236e22282eeb)
1. [Understanding service account token volume projection in Kubernetes by Mohammad Asim Ayub](https://mohammad-ayub.medium.com/understanding-service-account-token-volume-projection-in-kubernetes-15d5623e7cc7)

# 4. Samples

1. [Amazon EKS IAM Role for Service Accounts CDK/CloudFormation Library](https://github.com/awslabs/amazon-eks-irsa-cfn)

# 5. Repost

1. [[NOTES] How do I use the IAM roles for service accounts (IRSA) feature with Amazon EKS to restrict access to an Amazon S3 bucket?](https://repost.aws/knowledge-center/eks-restrict-s3-bucket)
1. [Why can't I use an IAM role for the service account in my Amazon EKS pod?](https://repost.aws/knowledge-center/eks-pods-iam-role-service-accounts)

# 6. Videos

1. [IAM Roles for Service Accounts | Learn about IRSA with demo in 20 mins by Visa2Learn](https://www.youtube.com/watch?v=otmLHWW3Tos)

# 7. Workshops

1. [IAM Roles for Service Accounts @ EKS Workshop](https://www.eksworkshop.com/docs/security/iam-roles-for-service-accounts/)


