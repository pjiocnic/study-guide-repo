The following IAM resources will be created in your account
**AWSServiceRoleForAWSCloud9** - AWS Cloud9 creates a service-linked role for you. This allows AWS Cloud9 to call other AWS services on your behalf. You can delete the role from the AWS IAM console once you no longer have any AWS Cloud9 environments. Learn more
**AWSCloud9SSMAccessRole and AWSCloud9SSMInstanceProfile** - A service role and an instance profile are automatically created if Cloud9 accesses its EC2 instance through AWS Systems Manager. If your environments no longer require EC2 instances that block incoming traffic, you can delete these roles using the AWS IAM console. Learn more

https://archive.eksworkshop.com/020_prerequisites/workspaceiam/

```javascript {.line-numbers}
Cloud9 Name: eks-terraform
# see: https://archive.eksworkshop.com/020_prerequisites/ec2instance/
aws cloud9 update-environment  --environment-id $C9_PID --managed-credentials-action DISABLE
rm -vf ${HOME}/.aws/credentials
```

# Terraform

I manually installed terraform as described here - https://aws-quickstart.github.io/workshop-terraform-modules/40_setup_cloud9_ide/42_install_terraform_c9.html

- Get the latest version

```javascript {.line-numbers}
wget https://releases.hashicorp.com/terraform/1.7.2/terraform_1.7.2_linux_amd64.zip
unzip terraform_1.7.2_linux_amd64.zip
sudo mv terraform /usr/local/bin
```
