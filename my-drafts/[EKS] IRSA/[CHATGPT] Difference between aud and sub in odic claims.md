In the trust policy for OIDC (OpenID Connect) used with AWS IAM roles, the terms "audience" and "subject" have specific meanings and roles:

**1. Audience (aud):**

* The `aud` claim in a JSON Web Token (JWT) represents the intended recipient(s) of the token. It is essentially a way to declare who the token is meant for. When AWS IAM roles are used with OIDC, the `aud` claim typically specifies the identifier of the OIDC client or audience that is trusted to receive the token.

* In the context of an AWS IAM trust policy, the `aud` claim is used to ensure that the token was issued for the correct audience, which is usually the AWS service itself. For example, when using OIDC with Amazon EKS, the audience is often set to `sts.amazonaws.com`, indicating that the token is intended to be used with AWS Security Token Service (STS).

**2. Subject (sub):**

* The `sub` claim in a JWT represents the subject of the token, usually a unique identifier for the entity (such as a user or a service account) that the token represents.

* In the context of an AWS IAM trust policy, the `sub` claim is used to match the entity (e.g., a Kubernetes service account) that is allowed to assume the role. It ensures that only the specified service account can use the token to assume the role.

# Example Trust Policy

Here is an example trust policy that illustrates the use of both `aud` and `sub` claims:

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "Federated": "arn:aws:iam::YOUR_ACCOUNT_ID:oidc-provider/YOUR_EKS_OIDC_PROVIDER"
      },
      "Action": "sts:AssumeRoleWithWebIdentity",
      "Condition": {
        "StringEquals": {
          "YOUR_EKS_OIDC_PROVIDER:sub": "system:serviceaccount:YOUR_NAMESPACE:YOUR_SERVICE_ACCOUNT",
          "YOUR_EKS_OIDC_PROVIDER:aud": "sts.amazonaws.com"
        }
      }
    }
  ]
}
```

# Breakdown

* **Principal**: Specifies the OIDC provider that is trusted to authenticate the token.
* **Action**: Specifies the action that is allowed, in this case, `sts:AssumeRoleWithWebIdentity`.
* **Condition**: Specifies the conditions under which the role can be assumed.
    * **sub claim**: Ensures that only the specified service account (`system:serviceaccount:YOUR_NAMESPACE:YOUR_SERVICE_ACCOUNT`) can assume the role.
    * **aud claim**: Ensures that the token's audience is `sts.amazonaws.com`, indicating that the token is intended for use with AWS STS.

**Summary**

**Audience (aud):** Ensures the token is intended for the AWS STS service.
**Subject (sub):** Ensures the token is associated with the specific Kubernetes service account allowed to assume the role.

Understanding these claims and their usage is crucial for securely configuring and using OIDC tokens to assume AWS IAM roles within EKS clusters.

