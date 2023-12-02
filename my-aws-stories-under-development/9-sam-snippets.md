
<!-- TOC -->

- [1. Template validation](#1-template-validation)
- [2. How to read from SSM Paramater store](#2-how-to-read-from-ssm-paramater-store)
- [3. How to setup API gateway](#3-how-to-setup-api-gateway)
- [4. How to attach API Gateway to Function](#4-how-to-attach-api-gateway-to-function)

<!-- /TOC -->

# 1. Template validation

For complete discussion see `/Volumes/Lexar/git-repos/aws-repo/my-aws-workshops/serverless/serverless-developer-experience/2-notes/2-Developing Unicorn Contracts.md`

```bash
pip install cfn-lint cfn-lint-serverless
cfn-lint template.yaml -a cfn_lint_serverless.rules
```

# 2. How to read from SSM Paramater store

For complete discussion see `/Volumes/Lexar/git-repos/aws-repo/my-aws-workshops/serverless/serverless-developer-experience/3-sandbox/unicorn/unicorn_contracts/template.yaml`

```yaml
Globals:
  Api:
    OpenApiVersion: 3.0.1
  Function:
    Runtime: java17
    MemorySize: 512
    Timeout: 900
    Architectures:
      - x86_64
    Environment:
      Variables:
        DYNAMODB_TABLE: !Ref ContractsTable
        EVENT_BUS: !Sub "{{resolve:ssm:/UniProp/${Stage}/EventBusName}}"
        SERVICE_NAMESPACE: !Sub "{{resolve:ssm:/UniProp/${Stage}/UnicornContractsNamespace}}"
        LOG_LEVEL: INFO
    Tags:
      stage: !Ref Stage
      project: AWS Serverless Developer Experience
      service: Unicorn Contracts Service
```

# 3. How to setup API gateway

For complete discussion see `/Volumes/Lexar/git-repos/aws-repo/my-aws-workshops/serverless/serverless-developer-experience/3-sandbox/unicorn/unicorn_contracts/template.yaml`

```yaml

Parameters:
  Stage:
    Type: String
    Default: Local
    AllowedValues:
      - Local
      - Dev
      - Prod

ContractsApiAccessLogsRole:
    Type: AWS::IAM::Role
    Properties:
      AssumeRolePolicyDocument:
        Statement:
          Action: sts:AssumeRole
          Effect: Allow
          Principal:
            Service: apigateway.amazonaws.com
      ManagedPolicyArns:
        - !Sub "arn:${AWS::Partition}:iam::aws:policy/service-role/AmazonAPIGatewayPushToCloudWatchLogs"

ContractsApi:
    Type: AWS::Serverless::Api
    DependsOn: ContractsApiGwAccountConfig
    Properties:
      StageName: !Ref Stage
      # Added from cfn_lint_serverless.rules suggestion
      TracingEnabled: true
      # https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-apigateway-restapi-endpointconfiguration.html
      EndpointConfiguration:
        Type: REGIONAL # can be EDGE/REGIONAL/PRIVATE
      MethodSettings:
        - MetricsEnabled: true
          ResourcePath: /*
          HttpMethod: "*"
          LoggingLevel: !If
            - IsProd
            - ERROR
            - INFO
          ThrottlingBurstLimit: 10
          ThrottlingRateLimit: 100
      AccessLogSetting:
        DestinationArn: !GetAtt ContractsApiLogGroup.Arn
        Format: >
          {"requestId":"$context.requestId",
          "integration-error":"$context.integration.error",
          "integration-status":"$context.integration.status",
          "integration-latency":"$context.integration.latency",
          "integration-requestId":"$context.integration.requestId",
          "integration-integrationStatus":"$context.integration.integrationStatus",
          "response-latency":"$context.responseLatency",
          "status":"$context.status"}
      Tags:
        stage: !Ref Stage
        project: AWS Serverless Developer Experience
        service: Unicorn Contracts Service

  ContractsApiGwAccountConfig:
    Type: AWS::ApiGateway::Account
    Properties:
      CloudWatchRoleArn: !GetAtt ContractsApiAccessLogsRole.Arn
```

# 4. How to attach API Gateway to Function

For complete discussion see `/Volumes/Lexar/git-repos/aws-repo/my-aws-workshops/serverless/serverless-developer-experience/3-sandbox/unicorn/unicorn_contracts/template.yaml`

```yaml
CreateContractFunction:
    Type: AWS::Serverless::Function
    Properties:
      CodeUri: ContractsFunction # Java example
      Handler: contracts.CreateContractFunction::handleRequest
      Tracing: Active
      Policies:
        - DynamoDBWritePolicy:
            TableName: !Ref ContractsTable
        - DynamoDBReadPolicy:
            TableName: !Ref ContractsTable
        - EventBridgePutEventsPolicy:
            EventBusName: !Sub "{{resolve:ssm:/UniProp/${Stage}/EventBusName}}"
      Events:
        CreateContract:
          Type: Api
          Properties:
            Path: /contracts
            Method: post
            RestApiId: !Ref ContractsApi
```