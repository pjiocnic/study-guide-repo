
# When does Amazon Managed PHZ get created and associated with VPC?

When you create a VPC endpoint to an AWS service or AWS PrivateLink SaaS, you can **enable Private DNS**. When enabled, the setting creates an AWS managed Route 53 private hosted zone (PHZ) for you.

## Problems Associating Amazon Managed PHZ with VPC

The managed PHZ works great for resolving the DNS name **within a VPC** however, it **does not work outside of the VPC**.

This is where PHZ sharing and Route 53 Resolver come into play to help us get unified name resolution for shared VPC endpoints - like in a hub and spoke architecture

source: https://aws.amazon.com/blogs/networking-and-content-delivery/integrating-aws-transit-gateway-with-aws-privatelink-and-amazon-route-53-resolver/

# Steps to associate custom PHZ with VPC within the same account

1. enableDnsHostnames and enableDnsSupport to true: for each VPC that you associate with a private hosted zone, you must set the Amazon VPC settings enableDnsHostnames and enableDnsSupport to true
1. At least one VPC association must exist

source:
- https://aws.amazon.com/blogs/networking-and-content-delivery/integrating-aws-transit-gateway-with-aws-privatelink-and-amazon-route-53-resolver/
- https://www.javierinthecloud.com/associating-a-route-53-private-hosted-zone-across-accounts/
- https://aws.amazon.com/blogs/networking-and-content-delivery/centralize-access-using-vpc-interface-endpoints/

# How to associate custom PHZ and VPC cross-accounts

https://www.javierinthecloud.com/associating-a-route-53-private-hosted-zone-across-accounts/
https://repost.aws/knowledge-center/route53-private-hosted-zone

# Hub and Spoke architecture

- Between VPCs but within an Account

<img src="./images/phz-sharing-within-account.png" title="phz-sharing-within-account.png" width="900"/>

[Centralize access using VPC interface endpoints to access AWS services across multiple VPCs
by Chetan Agrawal](https://aws.amazon.com/blogs/networking-and-content-delivery/centralize-access-using-vpc-interface-endpoints/)

# Route 53 Resolvers