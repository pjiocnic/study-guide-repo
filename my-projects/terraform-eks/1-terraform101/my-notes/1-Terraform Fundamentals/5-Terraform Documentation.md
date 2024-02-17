Sample: Creating a new aws_vpc resource using terraform registry

```tf
resource "aws_vpc" "main" {
  # Provide arguments that the resource require or optionally needs in order to create the target resource.
  cidr_block = "10.0.0.0/16"
}
```

# Creating an AWS VPC with specified attributes
resource "aws_vpc" "example_vpc" {
  cidr_block = "10.0.0.0/16"
  enable_dns_support = true
  enable_dns_hostnames = true

  tags = {
    Name = "my-tf-vpc"
    Environment = "production"
  }
}

# Reference the VPC attributes in another resource (e.g., Subnet)
resource "aws_subnet" "example_subnet" {
  vpc_id                  = aws_vpc.example_vpc.id
  cidr_block              = "10.0.1.0/24"
  availability_zone       = "us-east-1a"
  map_public_ip_on_launch = true
}
