https://aws.amazon.com/blogs/networking-and-content-delivery/starting-small-with-aws-global-accelerator/

# Demo

# Step – 1: Hosting a Simple Single Region Application on EC2

```bash
aws cloudformation create-stack \
  --stack-name vpc-for-ga \
  --template-body file://templates/1-vpc-alb-auto-scale-httpd.yaml \
  --parameters \
    ParameterKey="KeyName",ParameterValue="2023iamadmin" \
    ParameterKey="EnvironmentName",ParameterValue="mytestapplication-primary" \
  --capabilities CAPABILITY_NAMED_IAM
```

# Step – 2: Adding our test Global Accelerator

```bash
aws cloudformation create-stack \
  --stack-name vpc-for-ga \
  --template-body file://templates/2-vpc-alb-scale-httpd-ga.yaml \
  --parameters \
    ParameterKey="KeyName",ParameterValue="2023iamadmin" \
    ParameterKey="EnvironmentName",ParameterValue="mytestapplication-primary" \
  --capabilities CAPABILITY_NAMED_IAM
```

# Step – 3: Utilizing Amazon Route53 to gradually deploy AWS Global Accelerator

aws globalaccelerator describe-accelerator \
    --accelerator-arn arn:aws:globalaccelerator::111122223333:accelerator/1234abcd-abcd-1234-abcd-1234abcdefgh \
    --region us-west-2