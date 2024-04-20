<h1>Providers, Resources, and Data Sources</h1>

# Providers

Providers  provide interactions with cloud providers

```tf
provider "aws" {
  region = "us-east-1"
}
```

# Resources

Declaring a resource can **define** one or more infrastructure resource objects such as compute, networking, etc ...

1. Creating s3 resource

```tf
# declares a resource block named "example" that will manage an AWS S3 bucket
resource "aws_s3_bucket" "example" {
  # configuration block specifying the parameters for creating the S3 bucket
  bucket = "my-tf-test-bucket" # S3 bucket with the specified name (my-tf-test-bucket) will be created
}
```

2. Usage example - create a resource to upload a file to this bucket

```tf
resource "aws_s3_bucket_object" "example_object" {
  bucket = aws_s3_bucket.example.bucket  # references the bucket created
  key    = "example-file.txt"
  source = "/path/to/local/example-file.txt"
}
```

# Datasources

Data Sources allow **lookup** of resources **defined outside of Terraform** and provide the attributes found within that resource

1. Fetching VPC information using data block

```tf
# data block named "selected"
data "aws_vpc" "selected" {
  id = "vpc-00f0b02721857a89d" # Terraform will fetch information about the VPC with this ID.
}
```

2. Using data block

```tf
# Using the CIDR block of the selected VPC in another resource
resource "aws_subnet" "example_subnet" {
  vpc_id                  = data.aws_vpc.selected.id
  cidr_block              = data.aws_vpc.selected.cidr_block
  availability_zone       = "us-east-1a"
  map_public_ip_on_launch = true
}
```