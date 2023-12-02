1. Based Network.yaml that creates a sanbox VPC
1. Create and Associate Instance Profile. How do yu attach additional permissions to an existing profile?
1. SSM sessionmanager
1. EC2 Instance connect
1. Event Bridge, Rules and EventBusPolicy  - /Volumes/Lexar/git-repos/aws-repo/my-aws-workshops/serverless/serverless-developer-experience/3-sandbox/templates/uni-prop-shared-dev.yaml
1. LogGroup with Retention Policy - /Volumes/Lexar/git-repos/aws-repo/my-aws-workshops/serverless/serverless-developer-experience/3-sandbox/templates/uni-prop-shared-dev.yaml

# Running template validation

```yaml
######################################
# METADATA
######################################
Metadata:
  cfn-lint:
    config:
      ignore_checks:
        - ES6000
        - WS1004
```

```bash
pip install cfn-lint cfn-lint-serverless
cfn-lint template.yaml -a cfn_lint_serverless.rules
```