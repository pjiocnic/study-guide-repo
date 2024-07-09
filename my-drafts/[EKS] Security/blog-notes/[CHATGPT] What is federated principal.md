A federated principal in AWS IAM refers to an entity that can assume an IAM role through a federated identity provider (IdP) rather than directly using AWS credentials. This allows users, applications, or services from external identity systems (like an OIDC provider, SAML IdP, or other identity federations) to access AWS resources securely without needing AWS-specific credentials.

**Key Concepts**
1. **Federation:** Federation allows users to authenticate with an external identity provider and then assume an IAM role in AWS. The identity provider can be a service like Google, Microsoft Azure AD, or any other service that supports federation standards like OIDC or SAML.

2. **Principal:** In AWS IAM, a principal is an entity that can perform actions and access resources. Principals can be AWS users, roles, or federated users.

3. **Federated Principal:** A federated principal is an external identity (authenticated by an external identity provider) that AWS trusts. This principal can assume roles in AWS based on the policies and conditions specified.

**Example:** OpenID Connect (OIDC) Federation
When using OIDC for federation, the federated principal typically involves:

**OIDC Provider:** An OIDC-compatible identity provider that authenticates the user and issues tokens.
**Kubernetes Service Account:** In the context of EKS, a service account in Kubernetes that is linked to an IAM role via OIDC.

**Trust Relationship Example**

Here’s an example of a trust relationship policy document for an IAM role that trusts a federated principal (OIDC provider):

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "Federated": "arn:aws:iam::123456789012:oidc-provider/oidc.eks.us-west-2.amazonaws.com/id/EXAMPLED539D4633E53DE1B716D3041E"
      },
      "Action": "sts:AssumeRoleWithWebIdentity",
      "Condition": {
        "StringEquals": {
          "oidc.eks.us-west-2.amazonaws.com/id/EXAMPLED539D4633E53DE1B716D3041E:aud": "sts.amazonaws.com",
          "oidc.eks.us-west-2.amazonaws.com/id/EXAMPLED539D4633E53DE1B716D3041E:sub": "system:serviceaccount:default:my-service-account"
        }
      }
    }
  ]
}
```

**Explanation**

**Effect:** Allow – Allows the specified action.
**Principal:** Specifies the federated principal.
"Federated": "arn:aws:iam::123456789012:oidc-provider/oidc.eks.us-west-2.amazonaws.com/id/EXAMPLED539D4633E53DE1B716D3041E" – The ARN of the OIDC provider.
**Action:** sts:AssumeRoleWithWebIdentity – Allows assuming the role using web identity tokens.
Condition:
**StringEquals:**
"oidc.eks.us-west-2.amazonaws.com/id/EXAMPLED539D4633E53DE1B716D3041E:aud": Ensures the audience in the token is sts.amazonaws.com.
"oidc.eks.us-west-2.amazonaws.com/id/EXAMPLED539D4633E53DE1B716D3041E:sub": Ensures the subject in the token matches the specified service account.

**Use Case**

Federated principals are particularly useful for:

**Kubernetes Clusters (EKS):** Where service accounts in Kubernetes need to interact with AWS resources securely.
**Web Applications:** Where users authenticate using an external IdP and then need temporary access to AWS resources.
**Corporate Identity Systems:** Where employees authenticate using a corporate IdP (like Active Directory) to access AWS resources without maintaining separate AWS credentials.
By leveraging federated principals, organizations can integrate their existing identity systems with AWS, enhancing security and simplifying user management.