

Rather than intercepting the requests to the EC2 metadata API to perform a call to the STS API to retrieve temporary credentials, we made changes in the AWS identity APIs to recognize Kubernetes pods.

By combining an OpenID Connect (OIDC) identity provider and Kubernetes service account annotations, you can now use IAM roles at the pod level

OIDC federation access allows you to assume IAM roles via the Secure Token Service (STS), enabling authentication with an OIDC provider, receiving a JSON Web Token (JWT), which in turn can be used to assume an IAM role

Kubernetes, on the other hand, can issue so-called projected service account tokens, which happen to be valid OIDC JWTs for pods.

Our setup equips each pod with a cryptographically-signed token that can be verified by STS against the OIDC provider of your choice to establish the pod’s identity.

We’ve updated AWS SDKs with a new credential provider that calls `sts:AssumeRoleWithWebIdentity`, exchanging the Kubernetes-issued OIDC token for AWS role credentials.

The control plane runs the webhook responsible for injecting the necessary environment variables and projected volume

# How IRSA works at high Level

1. Create a cluster with eksctl and OIDC provider setup enabled. This feature works with EKS clusters 1.13 and above.
2. Create an IAM role defining access to the target AWS services, for example S3, and annotate a service account with said IAM role.
3. Finally, configure your pods by using the service account created in the previous step and assume the IAM role.