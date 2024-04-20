1. create `aws.tf`

```tf
terraform {
  required_version = "> 1.5.0"
  required_providers {
    aws = {
      source = "hashicorp/aws"
      #  Fix version version of the AWS provider
      version = "= 5.3.0"
    }
  }
}

provider "aws" {
  region                   = var.region
  shared_credentials_files = ["~/.aws/credentials"]
  profile                  = "default"
}

variable "region" {
  description = "The name of the AWS Region"
  type        = string
  default     = "eu-west-1"
}
```

2. Init env

```bash
terraform init
```

3. create new file `vpc-10-1.tf`

```tf
resource "aws_vpc" "vpc-10-1" {
  assign_generated_ipv6_cidr_block = false
  cidr_block                       = "10.1.0.0/16"
  enable_dns_hostnames             = true
  enable_dns_support               = true
  instance_tenancy                 = "default"
  tags = {
    "Name" = "vpc-10-1"
  }
}

```

4. format and validate

```bash
terraform fmt
terraform validate
```

5. Create plan and apply
```bash
terraform plan -out tfplan
terraform apply tfplan
```

# Terraform State

1. state file

```bash
ls terraform.tfstate
```

2. contents of state file

```bash
terraform state list
```

3. show state

```bash
terraform state show aws_vpc.vpc-10-1
```

4. Make changes to *.tf and resync

```bash
terraform plan -out tfplan
terraform apply tfplan
```

# cleanup

```bash
terraform destroy
```