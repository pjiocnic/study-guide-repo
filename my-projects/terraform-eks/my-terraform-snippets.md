

<!-- TOC -->

- [1. common files](#1-common-files)
- [2. Providers](#2-providers)
- [3. How to run a shell script from tf](#3-how-to-run-a-shell-script-from-tf)
- [4. Common functions](#4-common-functions)
- [5. Functions](#5-functions)
- [6. resources](#6-resources)
  - [6.1. count variable](#61-count-variable)
  - [6.2. S3](#62-s3)
  - [6.3. VPCE (vpc endpoint)](#63-vpce-vpc-endpoint)
- [7. SSM  - Parameter store](#7-ssm----parameter-store)
- [8. VPC](#8-vpc)
- [9. Associating secondary IPs to the VPC (for EKS Pods)](#9-associating-secondary-ips-to-the-vpc-for-eks-pods)
- [10. Lock tables](#10-lock-tables)
- [11. [STATE] Accessing values from remote state](#11-state-accessing-values-from-remote-state)

<!-- /TOC -->

# 1. common files

* **aws.tf**: Specifies how we connect to Terraform, where the credentials are - and optionally where to store the state file (we are using the default - the local directory).
* **variables.tf**: Defines the variables our terraform code will use.
* **data*.tf**: Any existing resources we will use.
* **vpc.tf**: an object most other things depend on in AWS.
* **subnets.tf** and security groups **mysg*.tf** - depends on the vpc.
* **nat_gateway.tf**: depends on subnets and elastic ip.
* **instance.tf**: has the most dependencies and thus is one of the last resources to be created.

# 2. Providers

These are normally defined in `aws.tf`

1. security credentials for aws provider

```javascript {.line-numbers}

# specify local directory for AWS credentials
provider "aws" {
  region                   = var.region
  shared_credentials_files = ["~/.aws/credentials"]
  profile                  = var.profile
}
```

2. "null" provider

```javascript {.line-numbers}

# "null" provider
# where you want to perform operations that don't interact with any real infrastructure
# for example: you might use the null provider to generate random values, manipulate data, or perform
# other operations without affecting real resources
provider "null" {}

# null provider usage examples
resource "null_resource" "example" {
  triggers = {
    random_value = "${random_integer.example.result}"
  }

  provisioner "local-exec" {
    command = "echo ${random_integer.example.result} > random_value.txt"
  }
}

resource "random_integer" "example" {
  min = 1
  max = 100
}
```

4. external provider

This is used to interact with external scripts or external programs as datasources. This can be useful when you need to integrate with external tools or systems that are not natively supported by Terraform

```javascript {.line-numbers}

provider "external" {}

data "external" "example" {
  program = ["python3", "${path.module}/external_script.py"]
}

output "external_output" {
  value = data.external.example.result
}
```

# 3. How to run a shell script from tf

```bash
resource "null_resource" "gen_backend" {
  triggers = {
    always_run = timestamp()
  }
  depends_on = [aws_dynamodb_table.terraform_locks,aws_s3_bucket_server_side_encryption_configuration.terraform_state]
  provisioner "local-exec" {
    when    = create
    command = <<EOT
        sleep 6
        ./gen-backend.sh
    EOT
  }
}
```

# 4. Common functions

1. `count` variable of resource

The Terraform variable count is an iterator - it says "create this resource count times"

```tf
resource "aws_vpc" "VPC" {
  count                            = var.mycount
  assign_generated_ipv6_cidr_block = false
  cidr_block                       = lookup(var.aws_cidr, var.aws_vpc[count.index])
  enable_dns_hostnames             = false
  enable_dns_support               = true
  instance_tenancy                 = "default"
  tags = {
    "Name" = var.aws_vpc[count.index]
  }
}
```

# 5. Functions

1. lookup function

if I had following variable definition

```javascript {.line-numbers}
variable "mycount" {
  default = 1
}

variable "aws_vpc" {
  type    = list
  default = ["vpc-10-1", "vpc-10-2"]
}

variable "aws_cidr" {
  default = {
    "vpc-10-1"       = "10.1.0.0/16"
    "vpc-devt-proja" = "10.3.0.0/16"
    "vpc-10-2"       = "10.2.0.0/16"
    "vpc-devt-projx" = "10.4.0.0/16"
  }
}
```

2. format function

```bash
resource "aws_subnet" "myprivsubnet" {
  count = var.mycount
  ...
  cidr_block = format("10.%s.4.0/24", count.index + 1) # substitute a variable here as a string
  ...
  tags = {
    "Name" = format("Priv subnet 10-%s-4-0", count.index + 1)
  }
  vpc_id = aws_vpc.VPC[count.index].id # VPC created in vpc.tf file is referenced
  ...
}
```

# 6. resources

## 6.1. count variable

```javascript {.line-numbers}
resource "aws_vpc" "VPC" {
  count = var.mycount # The Terraform variable count is an iterator - it says "create this resource count times"
  ...
  # ex: if count.index =0 then var.aws_vpc[count.index] = vpc-10-1; lookup returns "10.1.0.0/16" from aws_cidr map
  cidr_block = lookup(var.aws_cidr, var.aws_vpc[count.index])
  ...
  tags = {
    "Name" = var.aws_vpc[count.index]
  }
}
```

then cidr_block will be assigned 10.1.0.0/16 and 10.2.0.0/16

## 6.2. S3

1. Creating a bucket

```javascript {.line-numbers}
# S3 Bucket
resource "aws_s3_bucket" "terraform_state" {

  bucket = format("tf-state-workshop-%s", random_id.id1.hex)

  // This is only here so we can destroy the bucket as part of automated tests.
  // You should not copy this for production usage
  force_destroy = true

  lifecycle {
    ignore_changes = [bucket]
  }

}
```

2. server-side encryption rule for the S3 bucket

```javascript {.line-numbers}

# S3 Server-Side Encryption Configuration
resource "aws_s3_bucket_server_side_encryption_configuration" "terraform_state" {
  bucket = aws_s3_bucket.terraform_state.id

  // Defines a server-side encryption rule for the S3 bucket
  rule {
    bucket_key_enabled = false #  it means that the S3 bucket does not require objects to have server-side encryption provided by the AWS Key Management Service (KMS).

    apply_server_side_encryption_by_default {
      sse_algorithm     = "aws:kms"
      kms_master_key_id = aws_kms_key.ekskey.key_id
    }
  }
}
```

3. S3 Bucket Versioning

```javascript {.line-numbers}
# S3 Bucket Versioning
resource "aws_s3_bucket_versioning" "terraform_state" {
  # Enable versioning so we can see the full revision history
  # of our state files
  bucket = aws_s3_bucket.terraform_state.id
  versioning_configuration {
    status = "Enabled"
  }
}
```

4. Blocking public access

```javascript {.line-numbers}
# S3 Bucket Public Access Block
resource "aws_s3_bucket_public_access_block" "pub_block_state" {
  bucket = aws_s3_bucket.terraform_state.id

  restrict_public_buckets = true
  block_public_acls       = true  # blocks new public Access Control Lists (ACLs) for objects in the bucket.
  block_public_policy     = true  # blocks public bucket policies
  ignore_public_acls      = true  # ignores public ACLs
}
```

5. How to run a external shell script

```javascript {.line-numbers}
resource "null_resource" "gen_backend" {
  triggers = {
    always_run = timestamp() # resource is re-run every time the timestamp changes ie make the resource always run
  }
  # run after following dependencies have been created or updated
  depends_on = [aws_dynamodb_table.terraform_locks,aws_s3_bucket_server_side_encryption_configuration.terraform_state]
  provisioner "local-exec" {
    when    = create # Specifies that the provisioner should only run during the resource creation phase, not during updates
    # sleeps for 6 seconds and then runs the gen-backend.sh script
    command = <<EOT
        sleep 6
        ./gen-backend.sh
    EOT
  }
}
```

## 6.3. VPCE (vpc endpoint)

1. VPC endpoint

```javascript {.line-numbers}
resource "aws_vpc_endpoint" "interface" {
  # The for_each block is used to create multiple VPC endpoints for each of the specified services.
  for_each = toset(["ssm", "ssmmessages", "ec2messages", "secretsmanager"])

  vpc_id              = aws_vpc.default.id # The VPC endpoint is associated with the VPC specified by aws_vpc.default.id.
  service_name        = "com.amazonaws.${data.aws_region.current.name}.${each.key}"
  vpc_endpoint_type   = "Interface" # indicates interface VPC endpoint.
  private_dns_enabled = true # indicates that instances in your VPC use the Amazon provided DNS server for DNS resolution

  subnet_ids         = values(aws_subnet.private_ingress)[*].id # subnets where the VPC endpoint network interfaces will be created.
  security_group_ids = [aws_security_group.any.id] #very permissive (allowing all traffic)

  tags = {
    Name = "${var.namespace}-endpoint-${each.key}"
  }
}
```

2. VPS Endpoint with policy

```javascript {.line-numbers}
resource "aws_vpc_endpoint" "vpce-autoscaling" {
  policy = jsonencode(
    {
      Statement = [
        {
          Action    = "*"
          Effect    = "Allow"
          Principal = "*"
          Resource  = "*"
        },
      ]
    }
  )
  private_dns_enabled = true
  route_table_ids     = []
  security_group_ids = [
    aws_security_group.allnodes-sg.id,
    aws_security_group.cluster-sg.id
  ]
  service_name = format("com.amazonaws.%s.autoscaling", data.aws_region.current.name)
  subnet_ids = [
    aws_subnet.subnet-i3.id,
    aws_subnet.subnet-i1.id,
    aws_subnet.subnet-i2.id,
  ]
  tags              = {}
  vpc_endpoint_type = "Interface"
  vpc_id            = aws_vpc.cluster.id

  timeouts {}
}
```

2. Gateway endpoint

```javascript {.line-numbers}
# Gateway endpoint
resource "aws_vpc_endpoint" "gateway" {
  for_each = toset(["s3"])

  vpc_id       = aws_vpc.default.id
  service_name = "com.amazonaws.${data.aws_region.current.name}.${each.key}"

  tags = {
    Name = "${var.namespace}-endpoint-${each.key}"
  }
}
```

# 7. SSM  - Parameter store

```javascript {.line-numbers}
resource "aws_ssm_parameter" "tf-eks-id" {
  name        = "/workshop/tf-eks/id"
  description = "The unique id for the workshop"
  type        = "String"
  value       = random_id.id1.hex

  tags = {
    workshop = "tf-eks-workshop"
  }
}
```

# 8. VPC

```javascript {.line-numbers}
resource "aws_vpc" "cluster" {
  assign_generated_ipv6_cidr_block = false
  cidr_block                       = "10.0.0.0/22"
  # true: receive public DNS hostnames (<ec2-instance-name>.<public-dns-domain>)
  enable_dns_hostnames             = true
  # true: enables instances to resolve bothe private and public DNS hostnames
  enable_dns_support               = true
  # default: instances can share physical hardware
  # dedicated: use dedicated hardware due to compliance or licensing reasons
  instance_tenancy                 = "default" # default or dedicated
  tags = {
    "Name" = format("eks-%s-cluster", data.aws_ssm_parameter.tf-eks-cluster-name.value)
  }
}
```

# 9. Associating secondary IPs to the VPC (for EKS Pods)

```javascript {.line-numbers}
resource "aws_vpc_ipv4_cidr_block_association" "vpc-cidr-assoc" {
  cidr_block = "100.64.0.0/16"
  vpc_id     = aws_vpc.cluster.id

  timeouts {}
}
```

# 10. Lock tables

TODO


# 11. [STATE] Accessing values from remote state

see: `/tfekscode/tf-setup/remotes/remote-net.tf.sav`

- definition
```javascript {.line-numbers}
data "terraform_remote_state" "net" {
  backend = "s3"
  config = {
    bucket = format("tf-state-workshop-%s", var.tfid)
    region = data.aws_region.current.name
    key    = "terraform/terraform_locks_net.tfstate"
  }
}
```

- usage

```javascript {.line-numbers}
resource "aws_route" "rt-eks1" {
  route_table_id            = data.terraform_remote_state.net.outputs.rtb-priv1
  destination_cidr_block    = data.aws_vpc.vpc-default.cidr_block
  vpc_peering_connection_id = aws_vpc_peering_connection.def-peer.id
}
```

**Note**: I don't see the workshop using this approach instead gets from ssm

```javascript {.line-numbers}
resource "aws_route" "rt-eks1" {
  route_table_id            = data.aws_ssm_parameter.rtb-priv1.value
  destination_cidr_block    = data.aws_vpc.vpc-default.cidr_block
  vpc_peering_connection_id = aws_vpc_peering_connection.def-peer.id
}
```