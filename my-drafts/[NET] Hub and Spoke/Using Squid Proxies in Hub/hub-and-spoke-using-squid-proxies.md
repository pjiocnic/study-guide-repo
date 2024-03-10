<h1>[Providing controlled internet access through centralised proxy servers using AWS Fargate and PrivateLink by Sanjay Dandeker and Saurabh Kothari](https://aws.amazon.com/blogs/networking-and-content-delivery/providing-controlled-internet-access-through-centralised-proxy-servers-using-aws-fargate-and-privatelink/)</h1>

Customers choosing to adopt a centralised internet model to overcome this problem can use AWS Network Firewall, which can be deployed using a centralised or decentralised model to control internet traffic through an inspection VPC.

AWS Network Firewall acts as a proxy to incoming traffic but when routing traffic to the internet its transparent

In this demo we use "Squid proxies" running on ECS instances to act as proxy servers

# Technologies used

1. VPC-VPC connectivity using VPC private links
2. ECS proxies using SQSDeveloperGuide

# How is this done?

1. The service consumer (the application ‘spoke’ accounts) for this solution will have a ‘proxy endpoint’ deployed inside the VPC which **permits internet access to the list of domains** permitted in the central account.

# References

1. https://aws.amazon.com/blogs/networking-and-content-delivery/providing-controlled-internet-access-through-centralised-proxy-servers-using-aws-fargate-and-privatelink/


