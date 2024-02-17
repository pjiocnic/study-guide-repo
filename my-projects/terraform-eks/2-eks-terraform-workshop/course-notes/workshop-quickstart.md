
1. Initialize the Terraform state bucket and DynamoDB lock tables

```bash
cd ~/environment/tfekscode/tf-setup
terraform init
terraform validate
terraform plan -out tfplan
terraform apply tfplan
```

2. Setup the VPC, Subnets, Security Groups and VPC Endpoints

```bash
cd ~/environment/tfekscode/net
terraform init
terraform validate
terraform plan -out tfplan
terraform show -no-color tfplan > tfplan.txt
terraform apply tfplan
```

3. create the IAM Roles and Policies for EKS

```bash
cd ~/environment/tfekscode/iam
terraform init -upgrade
terraform validate
terraform plan -out tfplan
terraform show -no-color tfplan > tfplan.txt
```

4. Linking the Cloud9 IDE & CI/CD VPC to the EKS Network

```bash
cd ~/environment/tfekscode/c9net
terraform init -upgrade
terraform validate
terraform plan -out tfplan
terraform show -no-color tfplan > tfplan.txt
```

5. create EKS control Plane

```bash
cd ~/environment/tfekscode/cluster
terraform init
terraform validate
terraform plan -out tfplan
terraform show -no-color tfplan > tfplan.txt
terraform apply tfplan
```

6. Create a customized managed Node Group

```bash

cd ~/environment/tfekscode/nodeg
terraform init
terraform validate
terraform plan -out tfplan
terraform apply tfplan
```

7.