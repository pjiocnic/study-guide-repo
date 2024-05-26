<h1>[CHATGPT] Service Account Issuer Discovery</h1>

**Also see**: Service Account Issuer Discovery

<img src="./images/Service account issuer discovery.png" title="Service account issuer discovery.png" width="900"/>

**Service Account Issuer Discovery** is a feature in Kubernetes that allows the Kubernetes API server to act as an OpenID Connect (OIDC) identity provider. This feature enhances Kubernetes service accounts by enabling the issuance and verification of OIDC tokens, which can be used for authentication and authorization in external systems, such as AWS IAM roles. Here’s a deep dive into how Service Account Issuer Discovery works in Kubernetes:

1. **Enabling Service Account Issuer Discovery**

To enable Service Account Issuer Discovery, you need to configure the Kubernetes API server with the following flags:

**--service-account-issuer**: Specifies the URL of the OIDC issuer.
**--service-account-key-file**: Specifies the file containing the private key(s) for signing service account tokens.
**--service-account-signing-key-file**: (Optional) Specifies the file containing the signing key if it differs from the key file.
**--service-account-api-audiences**: Specifies the audiences for the tokens issued by the API server.

**Example API server flags:**

```yaml
apiVersion: kubeadm.k8s.io/v1beta2
kind: ClusterConfiguration
apiServer:
  extraArgs:
    service-account-issuer: "https://kubernetes.default.svc"
    service-account-key-file: "/etc/kubernetes/pki/sa.key"
    service-account-signing-key-file: "/etc/kubernetes/pki/sa.key"
    service-account-api-audiences: "https://kubernetes.default.svc"
```

2. **Issuing Tokens**

When a Kubernetes Pod uses a service account, the API server issues a JWT token to that service account. This token contains various claims, including the issuer (iss), subject (sub), and audience (aud).

**Example JWT token payload:**

```json
{
  "iss": "https://kubernetes.default.svc",
  "sub": "system:serviceaccount:default:my-service-account",
  "aud": [
    "https://kubernetes.default.svc"
  ],
  "exp": 1629398474,
  "iat": 1629394874,
  "nbf": 1629394874,
  "jti": "unique-jwt-id"
}
```

3. **Discovering OIDC Configuration**

The OIDC discovery endpoint is typically located at /.well-known/openid-configuration relative to the issuer URL. When Service Account Issuer Discovery is enabled, the API server provides this endpoint.

**Example discovery document:**

```json
{
  "issuer": "https://kubernetes.default.svc",
  "jwks_uri": "https://kubernetes.default.svc/openid/v1/jwks",
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

4. **JWKS Endpoint**

The JSON Web Key Set (JWKS) endpoint provides the public keys needed to verify the signatures of the issued tokens. This endpoint is specified in the jwks_uri field of the discovery document.

**Example JWKS document:**

```json
{
  "keys": [
    {
      "kty": "RSA",
      "use": "sig",
      "kid": "key-id",
      "alg": "RS256",
      "n": "modulus",
      "e": "exponent"
    }
  ]
}
```

5. **Using the Token**

**5.1. Kubernetes Internal Usage**

Within Kubernetes, the token is used to authenticate service accounts to the API server and other components. For example, when a Pod communicates with the API server, it presents the token in the Authorization header.

```http
Authorization: Bearer <service-account-token>
```

The API server validates the token using the public key provided in the JWKS endpoint.

**5.2. External Usage (e.g., AWS IAM Role Assumption)**

Outside of Kubernetes, the token can be used to authenticate to external systems. For example, when using AWS IAM roles, the service account token can be passed to the AWS Security Token Service (STS) to assume a role.

**Example process:**

1. Service Account Token Issuance: A Pod receives a token from the API server when it starts.
2. AssumeRoleWithWebIdentity Request: The Pod sends the token to AWS STS with the AssumeRoleWithWebIdentity API call.

```java

AssumeRoleWithWebIdentityRequest request = AssumeRoleWithWebIdentityRequest.builder()
    .roleArn("arn:aws:iam::YOUR_ACCOUNT_ID:role/YOUR_IAM_ROLE_NAME")
    .roleSessionName("eks-session")
    .webIdentityToken(token)  // The OIDC token
    .build();

AssumeRoleWithWebIdentityResponse response = stsClient.assumeRoleWithWebIdentity
(request);
```

**Token Validation:** AWS STS retrieves the OIDC discovery document and the JWKS from the Kubernetes API server. It verifies the token’s signature and claims.
**Temporary Credentials Issuance**: If the token is valid, AWS STS issues temporary security credentials for the IAM role.

**Benefits**
* **Improved Security**: By issuing tokens that can be validated against a trusted OIDC provider, Kubernetes ensures secure and verifiable identity management.
* **Integration with External Systems**: The issued tokens can be used to authenticate service accounts to external systems, such as cloud services and APIs.
* **Dynamic Credential Management**: Tokens have a limited lifetime and can be automatically rotated, reducing the risk of long-lived credentials.

**Summary**
Service Account Issuer Discovery in Kubernetes allows the API server to act as an OIDC provider, issuing JWT tokens for service accounts. These tokens can be validated and used both within Kubernetes and by external systems, providing secure and dynamic identity management for workloads running in Kubernetes clusters. This mechanism leverages OIDC standards to ensure interoperability and security.