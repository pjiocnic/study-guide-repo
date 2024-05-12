
# 1. [Introducing OIDC identity provider authentication for Amazon EKS by Rashmi Dwaraka, Mike Stefaniak, and Paavan Mistry ](https://aws.amazon.com/blogs/containers/introducing-oidc-identity-provider-authentication-amazon-eks/)

1. OpenID Connect (OIDC) Identity Provider (IDP)
1. OpenID Connect is an interoperable authentication protocol based on the OAuth 2.0 family of specifications
1. OIDC adds a thin layer that sits on top of OAuth 2.0 which adds login and profile information about the identity who is logged in.


# 2. Summary

1. We create Amazon Cognito OIDC IDP and populate it with a test user and group
2. understand the ID token to retrieve group key
3. associate OIDC IDP with Amazon EKS cluster
4. authorize secrets object resource access for users in a group through Kubernetes RBAC
5. and configure a user to authenticate.

# 3. Step 1: Create a Cognito OIDC IDP using AWS CDK

## 3.1. create and configure a Cognito User Pool in your AWS account

```bash
mkdir -p cognitouserpool && cd cognitouserpool && cdk init -l typescript

# Install the Amazon Cognito package
npm install @aws-cdk/aws-cognito
```

## 3.2. Update `./lib/cognitouserpool-stack.ts`

```ts
import * as cdk from '@aws-cdk/core';
import * as cognito from '@aws-cdk/aws-cognito';

export class CognitouserpoolStack extends cdk.Stack {
  constructor(scope: cdk.Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    const pool = new cognito.UserPool(this, 'myuserpool', {
      userPoolName: 'oidc-userpool',
      passwordPolicy: {
        minLength: 8,
        requireLowercase: false,
        requireUppercase: false,
        requireDigits: false,
        requireSymbols: false,
      },
      selfSignUpEnabled: true,
      signInAliases: {
        email: true,
      },
      autoVerify: {
        email: false,
      },
      accountRecovery: cognito.AccountRecovery.NONE,
      signInCaseSensitive: false,

    });

    const client = pool.addClient('oidc-client', {
      generateSecret: false,
      authFlows: {
        adminUserPassword: true,
        userPassword: true,
      },
      oAuth: {
        flows: {
          implicitCodeGrant: true,
        }
      },
    });

    pool.addDomain("CognitoDomain", {
      cognitoDomain: {
        domainPrefix: "oidc-userpool",
      },
    });

    const region = cdk.Stack.of(this).region
    const urlsuffix = cdk.Stack.of(this).urlSuffix
    const issuerUrl = `https://cognito-idp.${region}.${urlsuffix}/${pool.userPoolId}`;
    new cdk.CfnOutput(this, 'IssuerUrl', { value: issuerUrl })
    new cdk.CfnOutput(this, 'PoolId', { value: pool.userPoolId })
    new cdk.CfnOutput(this, 'ClientId', { value: client.userPoolClientId })
  }
}

const devEnv = {
  account: process.env.CDK_DEFAULT_ACCOUNT,
  region: process.env.CDK_DEFAULT_REGION,
}

const app = new cdk.App()

new CognitouserpoolStack(app, 'oidc-demo-dev', { env: devEnv})

app.synth();
```

## 3.3. Build

```bash
npm run build && cdk deploy
```

## 3.4. Save the Output

```bash
Outputs:
CognitouserpoolStack.ClientId = 702vqsrjicklgb7c5b7b50i1gc
CognitouserpoolStack.IssuerUrl = https://cognito-idp.us-west-2.amazonaws.com/us-west-2_re1u6bpRA
CognitouserpoolStack.PoolId = us-west-2_re1u6bpRA

# save in variables
CLIENT_ID=702vqsrjicklgb7c5b7b50i1gc && \
ISSUER_URL=https://cognito-idp.us-west-2.amazonaws.com/us-west-2_re1u6bpRA && \
POOL_ID=us-west-2_re1u6bpRA
```

## 3.5. Create a new user and group

create a group named **secret-reader** and add a new user with email **test@example.com** to the group within the User Pool.

```bash
aws cognito-idp admin-create-user \
  --user-pool-id $POOL_ID \
  --username test@example.com \
  --temporary-password password

aws cognito-idp admin-set-user-password \
  --user-pool-id $POOL_ID \
  --username test@example.com \
  --password Blah123$ \
  --permanent

aws cognito-idp create-group \
  --group-name secret-reader \
  --user-pool-id $POOL_ID

aws cognito-idp admin-add-user-to-group \
  --user-pool-id $POOL_ID \
  --username test@example.com \
  --group-name secret-reader

```

# 4. Step 2: Understanding the ID token to retrieve the `group claim`

## What is an ID Token?

ID token contents that are returned by the IDP **on successful authentication**. [PJ] This token is generated post authentication.

The ID token is a security token that contains claims (ie info) about the authentication of an end-user by an Authorization Server when using a client, and potentially other requested claims. The ID token is represented as a JWT.

Ref: [ID token contents](https://openid.net/specs/openid-connect-core-1_0.html#IDToken)

## [Google-AI] What's the meaning of claim?

In JSON Web Tokens (JWTs), claims are the core information that JWTs transmit. They are represented as name/value pairs, where the name is always a string. For example, an ID token can contain a claim called name that asserts that the name of the user authenticating is "John Doe"

More Ref: https://auth0.com/docs/secure/tokens/json-web-tokens/json-web-token-claims

## Autheticate against Cognito UserPool's IDP and get ID Token

```bash
# Get the ID Token post authentication
aws cognito-idp admin-initiate-auth --auth-flow ADMIN_USER_PASSWORD_AUTH \
--client-id $CLIENT_ID \
--auth-parameters USERNAME=test@example.com,PASSWORD=Blah123$ \
--user-pool-id $POOL_ID --query 'AuthenticationResult.IdToken' \
--output text | cut -f 2 -d. | base64 --decode | awk '{print $1"}"}' | jq

```

## Typical payload (ie claims in OIDC parlance) of ID Token

```json
{
  "sub": "86f7130a-5605-4c05-b402-c970b27633ce",
  "aud": "702vqsrjicklgb7c5b7b50i1gc",
  "cognito:groups": [
    "secret-reader"
  ],
  "event_id": "aa0723aa-12f3-49f1-9a21-7a7d542129bd",
  "token_use": "id",
  "auth_time": 1612760751,
  "iss": "https://cognito-idp.us-west-2.amazonaws.com/us-west-2_re1u6bpRA",
  "cognito:username": "86f7130a-5605-4c05-b402-c970b27633ce",
  "exp": 1612764351,
  "iat": 1612760751,
  "email": "test@example.com"
}
```