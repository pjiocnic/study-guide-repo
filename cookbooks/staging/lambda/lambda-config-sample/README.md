# lambda-config-sample (Java 17, AWS SDK v2)

Sample AWS Lambda that reads configuration from **SSM Parameter Store** and **AWS Secrets Manager** with simple TTL caching.

## Build
```bash
mvn -q -DskipTests package
```
Produces: `target/lambda-config-sample-0.0.1-shaded.jar`

## Deploy (Console quick start)
1. Create a Lambda (Java 17 / Amazon Corretto), upload the shaded jar.
2. Handler: `com.example.lambda.Handler::handleRequest`
3. Set environment variables (optional):
   - `PARAM_DB_URL` (default `/myapp/prod/db/url`)
   - `SECRET_DB` (default `myapp/prod/dbCredentials`)
4. IAM policy: allow `ssm:GetParameter`, `ssm:GetParameters*`, and `secretsmanager:GetSecretValue`. Add `kms:Decrypt` if SecureString uses a CMK.
5. Test with a simple `{}` event.

## Deploy with SAM
```bash
sam build --use-container -t sam/template.yaml
sam deploy --guided
```
This creates the role, permissions, and function with env vars.

## Deploy with Terraform
In `terraform/`, edit placeholders then:
```bash
terraform init
terraform apply
```

## Notes
- SDK v2 clients are reused across warm invocations.
- TTL cache defaults to 5 minutes; tune in code as desired.
- For nested/complex JSON secrets, replace `JsonMini` with Jackson.
