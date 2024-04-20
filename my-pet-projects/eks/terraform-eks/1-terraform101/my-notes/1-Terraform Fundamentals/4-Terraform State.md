<h1>Terraform State</h1>

Terraform stores information about your infrastructure within a state file, typically in a file named `terraform.tfstate`. Terraform uses state to determine which changes to make to your infrastructure like to modify, add, or remove. Before any operation is applied, Terraform performs a refresh to sync the current infrastructure for any resources' configuration drifts.

Example state file

```json
{
  "version": 4,
  "terraform_version": "1.4.6",
  "serial": 133,
  "lineage": "cb58f73d-003f-5734-7872-6019650d0d41",
  "outputs": {},
  "resources": [
    {
      "mode": "data",
      "type": "aws_availability_zones",
      "name": "available",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "all_availability_zones": null,
            "exclude_names": null,
            "exclude_zone_ids": null,
            "filter": null,
            "group_names": [
              "us-west-2"
            ],
            "id": "us-west-2",
            "names": [
              "us-west-2a",
              "us-west-2b",
              "us-west-2c",
              "us-west-2d"
            ],
            "state": "available",
            "timeouts": null,
            "zone_ids": [
              "usw2-az2",
              "usw2-az1",
              "usw2-az3",
              "usw2-az4"
            ]
          },
          "sensitive_attributes": []
        }
      ]
    },
    {
      "mode": "data",
      "type": "aws_region",
      "name": "current",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 0,
          "attributes": {
            "description": "US West (Oregon)",
            "endpoint": "ec2.us-west-2.amazonaws.com",
            "id": "us-west-2",
            "name": "us-west-2"
          },
          "sensitive_attributes": []
        }
      ]
    },
    {
      "mode": "managed",
      "type": "aws_vpc",
      "name": "default",
      "provider": "provider[\"registry.terraform.io/hashicorp/aws\"]",
      "instances": [
        {
          "schema_version": 1,
          "attributes": {
            "arn": "arn:aws:ec2:us-west-2:000000000000:vpc/vpc-00f0b02721857a89d",
            "assign_generated_ipv6_cidr_block": false,
            "cidr_block": "10.0.0.0/16",
            "default_network_acl_id": "acl-09dcdd87a8f96ba42",
            "default_route_table_id": "rtb-059654c5165723557",
            "default_security_group_id": "sg-0e43a7c421cc6f39d",
            "dhcp_options_id": "dopt-0869880b486ae393b",
            "enable_dns_hostnames": true,
            "enable_dns_support": true,
            "enable_network_address_usage_metrics": false,
            "id": "vpc-00f0b02721857a89d",
            "instance_tenancy": "default",
            "ipv4_ipam_pool_id": null,
            "ipv4_netmask_length": null,
            "ipv6_association_id": "",
            "ipv6_cidr_block": "",
            "ipv6_cidr_block_network_border_group": "",
            "ipv6_ipam_pool_id": "",
            "ipv6_netmask_length": 0,
            "main_route_table_id": "rtb-059654c5165723557",
            "owner_id": "000000000000",
            "tags": {
              "Name": "terraform-workshop-vpc"
            },
            "tags_all": {
              "Management": "Terraform",
              "Name": "terraform-workshop-vpc"
            }
          },
          "sensitive_attributes": [],
          "private": "eyJzY2hlbWFfdmVyc2lvbiI6IjEifQ==",
          "dependencies": [
            "data.aws_availability_zones.available"
          ]
        }
      ]
    }
  ],
  "check_results": null
}

```