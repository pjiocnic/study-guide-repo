# lambda-config-sample (Java 17, AWS SDK v2) — Jackson edition

AWS Lambda example that reads from **SSM Parameter Store** and **AWS Secrets Manager** using **Jackson** for robust JSON parsing.

## What's new
- Added Jackson (`jackson-databind`) for parsing Secrets Manager JSON.
- `SecretsManagerService` now exposes:
  - `getSecretJsonMap(String)` — flat map of fields
  - `getSecretAs(String, Class<T>)` — bind to a POJO
  - `getSecretNode(String)` — raw `JsonNode`

## Build
```bash
mvn -q -DskipTests package
```
Produces: `target/lambda-config-sample-0.0.2-shaded.jar`

## Handler
`com.example.lambda.Handler::handleRequest` — shows fetching an SSM SecureString and a JSON secret mapped to a `Map<String,String>`.

## Permissions
- `ssm:GetParameter`, `ssm:GetParameters*`
- `secretsmanager:GetSecretValue`
- Add `kms:Decrypt` if SSM SecureString uses a CMK.
