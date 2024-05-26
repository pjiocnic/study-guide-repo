# how will the related access token look like?

An OIDC (OpenID Connect) access token is a JSON Web Token (JWT) that contains various claims about the user or service account, the audience, and other metadata. Below is an example of how an OIDC access token might look, focusing on the relevant aud (audience) and sub (subject) claims:

## Example OIDC Access Token

```json
{
  "iss": "https://oidc.eks.YOUR_REGION.amazonaws.com/id/YOUR_EKS_OIDC_PROVIDER",
  "aud": "sts.amazonaws.com",
  "sub": "system:serviceaccount:YOUR_NAMESPACE:YOUR_SERVICE_ACCOUNT",
  "exp": 1629398474,
  "iat": 1629394874,
  "nbf": 1629394874,
  "jti": "unique-jwt-id",
  "kid": "key-id",
  "alg": "RS256"
}
```

## Breakdown of the Token

* **Header**:

```json
{
  "alg": "RS256",
  "kid": "key-id"
}
```

* **Payload**:

```json
{
  "iss": "https://oidc.eks.YOUR_REGION.amazonaws.com/id/YOUR_EKS_OIDC_PROVIDER",  // Issuer URL
  "aud": "sts.amazonaws.com",  // Audience claim, indicating the token is intended for AWS STS
  "sub": "system:serviceaccount:YOUR_NAMESPACE:YOUR_SERVICE_ACCOUNT",  // Subject claim, indicating the Kubernetes service account
  "exp": 1629398474,  // Expiration time
  "iat": 1629394874,  // Issued at time
  "nbf": 1629394874,  // Not before time
  "jti": "unique-jwt-id",  // Unique identifier for the token
  "other_claims": "value"  // Other custom claims
}
```

* **Header and Payload in Encoded Form**
The JWT consists of three parts: Header, Payload, and Signature, each encoded in Base64Url and separated by dots (.):

```bash
eyJhbGciOiJSUzI1NiIsImtpZCI6ImtleS1pZCJ9.eyJpc3MiOiJodHRwczovL29pZGMuZWtzLllPVVJfUkVHSU9OLmFtYXpvbmF3cy5jb20vaWQvWU9VUl9FS1NfT0lEQ19QUk9WSURFUiIsImF1ZCI6InN0cy5hbWF6b25hd3MuY29tIiwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50OllPVVJfTkFNRVNQQUNFOllPVVJfU0VSVklDRV9BQ0NPVU5UIiwiZXhwIjoxNjI5Mzk4NDc0LCJpYXQiOjE2MjkzOTQ4NzQsIm5iZiI6MTYyOTM5NDg3NCwianRpIjoidW5pcXVlLWp3dC1pZCIsIm90aGVyX2NsYWltcyI6InZhbHVlIn0.SIGNATURE
```

**Signature**
The signature is created using the header and payload, signed with the private key corresponding to the public key specified by the kid in the header.

**Using the Token**
When the user application inside the EKS pod uses this token to assume an IAM role, it sends the token to AWS STS in an AssumeRoleWithWebIdentity request. Here's a simplified request flow:

**1. User Application:**

Sends the token to AWS STS with the AssumeRoleWithWebIdentity API call.

```java
AssumeRoleWithWebIdentityRequest request = AssumeRoleWithWebIdentityRequest.builder()
    .roleArn("arn:aws:iam::YOUR_ACCOUNT_ID:role/YOUR_IAM_ROLE_NAME")
    .roleSessionName("eks-session")
    .webIdentityToken(token)  // The OIDC token
    .build();

AssumeRoleWithWebIdentityResponse response = stsClient.assumeRoleWithWebIdentity(request);
```

**2. AWS STS:**

* Validates the token against the OIDC provider.
* Checks the aud claim to ensure the token is intended for AWS STS.
* Checks the sub claim to ensure the token is associated with the correct service account.
* Issues temporary security credentials (AccessKeyId, SecretAccessKey, SessionToken) if the token is valid.

**3. User Application:**

Uses the temporary credentials to access AWS resources.

**Summary**

The key elements in the access token that are crucial for assuming the role are:

**aud (audience):** Indicates the token is intended for AWS STS (sts.amazonaws.com).
**sub (subject):** Uniquely identifies the Kubernetes service account allowed to assume the role.

These claims help ensure that only the intended service account can use the token to obtain temporary AWS credentials, enhancing the security of your EKS environment.