# SGs in "Service Provider VPC"

1. BastionHost-SG

NameTag: BastionHost-SG
GroupName: BastionHost-SG
Description: Accepts traffic on port 22 from on-premises network into the Service Provider VPC

Add inbound rule:

Type: SSH
Protocol: TCP(6)
Port Range:22
Source: 0.0.0.0/0 or your on-premises or machine-specific IP address in the form x.x.x.x/32
Description: Accept Only SSH traffic
VPC: Service Provider

2. SquidProxySG

NameTag: SquidProxySG
GroupName: SquidProxySG
Description: Accepts traffic on incoming port 3128 from Consumer VPCs- 172.16.0.0/16
VPC: Service Provider

Add inbound rule:

Type: Custom TCP Rule
Protocol :TCP(6)
Port Range: 3128
Source: 172.16.0.0/16
Description: Accept Only Squid proxy traffic from Service Consumer VPC - 172.16.0.0/16

Type: Custom TCP Rule
Protocol :TCP(6)
Port Range:3128
Source: 10.0.0.0/16
Description: Rule is required for Network Load Balancer health check to pass

## Question: Why Network Load Balancer needs to health check on source?

# Subnets in "Service Consumer VPC"

Name Tag: Consumer **Public** Subnet1
VPC: Select Service Consumer
Availability Zone: Select the first Availability Zone <region_name>
IPv4 CIDR block: 172.16.0.0/24

Name Tag: Consumer **Private** Subnet1
VPC: Select Service Consumer VPC
Availability Zone: Select the second AZ <region_name>
IPv4 CIDR block: 172.16.1.0/24

# IGW for "Service Consumer VPC"

NameTag: ServiceConsumerIGW

# RT for "Service Consumer VPC"

NameTag: Consumer Public RT
VPC: Service Consumer
Route: 0.0.0.0/0 to IGW

# SG in "Service Consumer VPC"

1.  BastionHost-SG-ConsumerVPC
NameTag: BastionHost-SG-ConsumerVPC
GroupName: BastionHost-SG-ConsumerVPC
Description: Accepts traffic on port 22 from on-premises network
VPC: Service Consumer VPC
Add inbound rule:
Type: SSH
Protocol :TCP(6)
Port Range: 22
Source: 0.0.0.0/0 or your on-premises or machine-specific IP address in the form of x.x.x.x/32.
Description: Accept only SSH traffic

2. ProxyTraffic-SG-ConsumerVPC

NameTag: ProxyTraffic-SG-ConsumerVPC
GroupName: ProxyTraffic-SG-ConsumerVPC

Description: Accepts traffic on incoming port 3128 from Service Provider VPC - 10.0.0.0/16
VPC: Service ConsumerAdd inbound rule:

Type: Custom TCP Rule
Protocol :TCP(6)
Port Range: 3128
Source: 172.16.0.0/16 # Service Consumer VPC
Description: Accept Only Squid proxy traffic from Service Consumer VPC – 172.16.0.0/16

Type: SSH
Protocol :TCP(6)
Port Range: 22
Source: 172.16.0.0/24 # bastion host subnet
Description: Accepts only SSH traffic from bastion host subnet

# EC2 instances

1. Launch one Amazon Linux t2.micro instance in `Consumer Public Subnet1` and 1 Ec2 instance in `Consumer Private Subnet1`
2. Launch one Amazon Linux t2.micro instance in each public subnet created in the `Service Provider VPC`


BastionHost-SG:
  SSH: 0.0.0.0/0 -> ServiceProviderVPC
  TCP:3128: 172.16.0.0/16 -> SquidProxySG

What is this rule?
Type: Custom TCP Rule
Protocol :TCP(6)
Port Range:3128
Source: 10.0.0.0/16
Description: Rule is required for Network Load Balancer health check to pass.


NameTag: ProxyTraffic-SG-ConsumerVPC
GroupName: ProxyTraffic-SG-ConsumerVPC
Description: Accepts traffic on incoming port 3128 from Service Provider VPC - 10.0.0.0/16

VPC: Service Consumer

Add inbound rule:

Type: Custom TCP Rule
Protocol :TCP(6)
Port Range: 3128 (squid port)
Source: 172.16.0.0/16
Description: Accept Only Squid proxy traffic from Service Consumer VPC – 172.16.0.0/16

Type: SSH
Protocol :TCP(6)
Port Range: 22
Source: 172.16.0.0/24
Description: Accepts only SSH traffic from bastion host subnet