resource "aws_vpc" "VPC" {
  count                            = var.mycount # The Terraform variable count is an iterator - it says "create this resource count times"
  assign_generated_ipv6_cidr_block = false
  # ex: if count.index =0 then var.aws_vpc[count.index] = vpc-10-1; lookup returns "10.1.0.0/16" from aws_cidr map
  cidr_block                       = lookup(var.aws_cidr, var.aws_vpc[count.index])
  enable_dns_hostnames             = false
  enable_dns_support               = true
  instance_tenancy                 = "default"
  tags = {
    "Name" = var.aws_vpc[count.index]
  }
}