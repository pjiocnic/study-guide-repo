# Specifies how we connect to Terraform , where the credentials are - and optionally where
# to store the state file (we are using the default - the local directory).
terraform {
  # specify minimum version of Terraform
  required_version = "> 1.4.0"
  required_providers {
    aws = {
      source = "hashicorp/aws"
      #  Lock version to prevent unexpected problems
      version = "4.65.0"
    }
    null = {
      source  = "hashicorp/null"
      version = "~> 3.1.0"
    }
    external = {
      source  = "hashicorp/external"
      version = "~> 2.1.0"
    }
    kubernetes = {
      source  = "hashicorp/kubernetes"
      version = "2.17.0"
    }
    helm = {
      source  = "hashicorp/helm"
      version = "~> 2.4.1"
    }
    local = {
      source  = "hashicorp/local"
      version = "~> 2.1.0"
    }

  }
}

# In Terraform, the provider blocks are used to define and configure providers. Providers are
# responsible for interacting with APIs of specific infrastructure platforms or services.

# specify local directory for AWS credentials
provider "aws" {
  region                   = var.region
  shared_credentials_files = ["~/.aws/credentials"]
  profile                  = var.profile
}

# "null" provider: where you want to perform operations that don't interact with any real infrastructure
# for example: you might use the null provider to generate random values, manipulate data, or perform
# other operations without affecting real resources
provider "null" {}

# external provider is used to interact with external scripts or external programs as datasources
# This can be useful when you need to integrate with external tools or systems that are not natively supported by Terraform
provider "external" {}



