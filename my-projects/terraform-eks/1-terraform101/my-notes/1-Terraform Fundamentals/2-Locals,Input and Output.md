
# 1. Local values

1. Defining

```tf
locals {
  # constant values: Assign the value of 'dev-instance' to environment
  instance_name = "dev-instance"

  # referenced values: Obtain the instance ID of the 'app_server' EC2 instance
  instance_id   = aws_instance.app_server.id
}
```

2. Using

```tf
resource "aws_instance" "this_server" {
  # ...

  tags = {
    # Using local variable
    Name = local.instance_name,  # local.<variable_name>
    "Environment" = "dev"
  }
}
```

# Input variables

Input variables are used to provide parameters for you to customize your Terraform module without altering the module's source code and prevent hard-coding values and enables you to re-use code

```tf
variable "app_name" {
  type        = string
  description = "Name  of the application"
  default     = ""
}
```

Using Input variables

```tf
resource "aws_instance" "app_server" {
  # ...

  tags = {
    # Using input variable
    Name = var.app_name,
    "Environment" = "prod"
  }
}
```

You can assign input variables as follows:

```tf
terraform apply -var="app_name=wordpress-app"
```

# FAQ

1. difference between input variables and local variables?

# Output variables

Output variables allows you to expose information on the resources so that others Terraform configurations can use it.

```tf
output "instance_tags" {
  value       = aws_instance.this_server.tags
  description = "A mapping of EC2 instance tags"
}
```