# This is where to configure providers

terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = ">= 5.0"
    }
  }
}

# Configure the AWS Provider
provider "aws" {
  default_tags {
    tags = {
      Management = "Terraform"
    }
  }
}
