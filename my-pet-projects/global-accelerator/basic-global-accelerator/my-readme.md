https://aws.amazon.com/blogs/networking-and-content-delivery/starting-small-with-aws-global-accelerator/

# Demo


```bash
aws cloudformation create-stack \
  --stack-name vpc-for-ga \
  --template-body file://templates/vpc-ec2-httpd.yaml \
  --parameters \
    ParameterKey="KeyName",ParameterValue="2023iamadmin" \
    ParameterKey="EnvironmentName",ParameterValue="DEV" \
  --capabilities CAPABILITY_NAMED_IAM
```