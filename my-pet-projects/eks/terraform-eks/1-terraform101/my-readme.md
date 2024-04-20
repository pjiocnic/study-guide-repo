terraform init
terraform validate
terraform plan
terraform apply
terraform destroy


https://developer.hashicorp.com/terraform/docs
https://registry.terraform.io/
https://registry.terraform.io/providers/hashicorp/aws/latest/docs

# Resources

- syntax

```tf
resource <resource_type> <resource_name> {
  # arguments
}
```
- example

```tf
resource "random_password" "default" {
  length           = 25
  special          = false
  override_special = "!#$%&*()-_=+[]{}<>:?"
}
```

# count argument

The count argument is used to create multiple instances of a resource based on a specified count value
Terraform will create as many instances of the resource as specified by that count

- following creates 3 instances of EC2

```tf
resource "aws_instance" "example" {
  count = 3

  ami           = "ami-12345678"
  instance_type = "t2.micro"
  key_name      = "my-key"
}
```

- can use `count.index` to find current index of the iteration

```tf
resource "aws_instance" "example" {
  count = 3

  ami           = "ami-12345678"
  instance_type = "t2.micro"
  key_name      = "my-key"

  tags = {
    Name = "instance-${count.index + 1}"
  }
}
```

# locals

```tf
locals {
  vpc = {
    azs        = slice(data.aws_availability_zones.available.names, 0, var.az_num)
    cidr_block = var.vpc_cidr_block
  }

  rds = {
    engine         = "mysql"
    engine_version = "8.0.28"
    instance_class = "db.t3.micro"
    db_name        = "mydb"
    username       = "dbuser123"
  }

  vm = {
    instance_type = "m5.large"

    instance_requirements = {
      memory_mib = {
        min = 8192
      }
      vcpu_count = {
        min = 2
      }
      instance_generations = ["current"]
    }
  }
}
```

# for-each

The for_each argument is used to dynamically create **multiple** instances of a resource based on the contents of a map, set, or list

```tf
resource "aws_subnet" "private_ingress" {
  # local.vpc.azs is a map in the form of {index => az_name}
  # :  index => az_name ... here a new map is created
  for_each = { for index, az_name in local.vpc.azs : index => az_name }

  vpc_id            = aws_vpc.default.id
  # (each.key + (length(local.vpc.azs) * 0): calculates the new subnet address .. 6,7,8
  cidr_block        = cidrsubnet(aws_vpc.default.cidr_block, 8, (each.key + (length(local.vpc.azs) * 2)))
  availability_zone = each.value

  tags = {
    Name = "${var.namespace}-subnet-private_ingress-${each.key}"
  }
}

resource "aws_instance" "example" {
  for_each = { for index, az_name in local.vpc.azs : index => az_name }

  ami           = "ami-12345678"
  instance_type = "t2.micro"
  key_name      = "my-key"

  subnet_id = each.value  # Using each.value to access the current value in the loop

  tags = {
    Name = "instance-${each.key}"
  }
}
```

# Format

```terraform
  tags = {
    Name = format("${var.namespace}-staging_app-%s", element(data.aws_availability_zones.available.names, 0))
  }
```

# Notes to prepare

1. How does ASG connect to the instances to be launched?

ALB sends traffic to target group (aws_lb_target_group). ASG scales in/out instances registered in targetgroup (aws_lb_target_group)

- https://spacelift.io/blog/terraform-autoscaling-group