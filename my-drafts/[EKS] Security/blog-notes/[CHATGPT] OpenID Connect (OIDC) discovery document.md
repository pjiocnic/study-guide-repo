<h1>OpenID Connect (OIDC) discovery document</h1>

The following JSON object is an example of an OpenID Connect (OIDC) discovery document.

```bash
$ IDP=$(aws eks describe-cluster --name eks-oidc-demo --query cluster.identity.oidc.issuer --output text)

# Reach the Discovery Endpoint
$ curl -s $IDP/.well-known/openid-configuration | jq -r '.'
{
  "issuer": "https://oidc.eks.us-east-2.amazonaws.com/id/xxxx",
  "jwks_uri": "https://oidc.eks.us-east-2.amazonaws.com/id/xxxx/keys",
  "authorization_endpoint": "urn:kubernetes:programmatic_authorization",
  "response_types_supported": [
    "id_token"
  ],
  "subject_types_supported": [
    "public"
  ],
  "claims_supported": [
    "sub",
    "iss"
  ],
  "id_token_signing_alg_values_supported": [
    "RS256"
  ]
}
```

This document provides metadata about the OIDC provider, which in this case is associated with an Amazon EKS (Elastic Kubernetes Service) cluster. Here is a breakdown of each field in the document:

**Fields Explanation**

1. issuer:

* **Definition**: This is the URL of the OIDC provider. It is a unique identifier for the issuer of the token.
* **Example**: "https://oidc.eks.us-east-2.amazonaws.com/id/xxxx"

2. jwks_uri:

* **Definition**: This URL points to the JSON Web Key Set (JWKS) endpoint. This endpoint provides the public keys used to verify the signature of the JWTs issued by the OIDC provider.
* **Example**: "https://oidc.eks.us-east-2.amazonaws.com/id/xxxx/keys"

3. authorization_endpoint:

* **Definition**: This specifies the endpoint used for obtaining authorization from the OIDC provider. In the context of Kubernetes, this is set to a special value indicating programmatic authorization.
* **Example**: "urn:kubernetes:programmatic_authorization"

4. response_types_supported:

* **Definition**: This lists the types of responses supported by the OIDC provider. In this case, it indicates that the provider supports issuing ID tokens.
* **Example**: ["id_token"]

5. subject_types_supported:

* **Definition**: This specifies the types of subjects supported by the OIDC provider. In this context, public means that the subjects are public and can be used across different contexts.
* **Example**: ["public"]

6. claims_supported:

* **Definition**: This lists the claims that the OIDC provider can include in the ID token. Claims are pieces of information asserted about the subject.
* **Example**: ["sub", "iss"]
    * **sub**: The subject of the token, typically a unique identifier for the user or service account.
    * **iss**: The issuer of the token.

7. id_token_signing_alg_values_supported:

* **Definition**: This specifies the algorithms supported by the OIDC provider for signing the ID tokens. RS256 is a commonly used algorithm which uses RSA with SHA-256.
* **Example**: ["RS256"]

**Putting It All Together**

This discovery document is used by clients (such as your EKS cluster and the applications running within it) to understand how to interact with the OIDC provider.

Here's how each part is used in practice:

* **issuer**: Helps clients validate the tokens by checking if they are issued by the expected OIDC provider.
* **jwks_uri**: Allows clients to retrieve the public keys needed to verify the signature of the ID tokens.
* **authorization_endpoint**: Though in Kubernetes this is often a special value, it indicates where to request authorization.
* **response_types_supported**: Indicates that the provider issues ID tokens, which are used for authentication.
* **subject_types_supported**: Indicates the type of subject in the token; public typically means the subject can be widely used.
* **claims_supported**: Helps clients understand what information (claims) will be present in the ID token.
* **id_token_signing_alg_values_supported**: Indicates the cryptographic algorithm used to sign the tokens, ensuring the client knows how to validate the token's signature.

**Example Use Case in EKS**
When a Kubernetes service account in an EKS cluster is used to assume an IAM role, the following steps generally occur:

1. **Token Request**: The service account requests an ID token from the OIDC provider (the issuer).
2. **Token Issuance**: The OIDC provider issues an ID token, signed using the algorithm specified (RS256).
3. **Token Validation**: AWS STS uses the jwks_uri to obtain the public keys and verify the token's signature.
4. **Assume Role**: If the token is valid, AWS STS allows the role assumption based on the claims (sub and iss) in the token.

This mechanism ensures secure and verifiable identity management within EKS, leveraging standard OIDC protocols.