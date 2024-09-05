<h1>DNS management in a multi-account environment with Route 53 Resolver</h1>

When you share Route 53 Resolver rules from a hub account to spoke accounts using AWS Resource Access Manager (RAM), the DNS resolution process for resources in an on-premises environment from EC2 instances in the spoke accounts works as follows: [1]

1. An EC2 instance in the spoke account's VPC initiates a DNS query for a resource in the on-premises environment (e.g., app.corp.internal). [2]

2. The DNS query is intercepted by the Route 53 Resolver in the spoke account's VPC, which evaluates the associated resolver rules.

3. One of the shared resolver rules from the hub account matches the query (e.g., a rule to forward queries for corp.internal to the on-premises DNS server).

4. The Route 53 Resolver in the spoke account's VPC forwards the DNS query to the Route 53 Resolver outbound endpoint in the hub account's VPC, as specified in the shared resolver rule.

5. The outbound endpoint in the hub account's VPC forwards the query to the Route 53 Resolver inbound endpoint, which is configured to resolve queries for the on-premises domain. [3]

6. The inbound endpoint in the hub account's VPC resolves the query by forwarding it to the on-premises DNS server over a Direct Connect or VPN connection.

7. The on-premises DNS server responds with the IP address of the requested resource (e.g., app.corp.internal).

8. The response is sent back through the inbound endpoint, outbound endpoint, and the spoke account's Resolver, eventually reaching the EC2 instance that initiated the query.

In summary, the shared resolver rules from the hub account instruct the spoke account's Resolver to forward queries for on-premises resources to the outbound endpoint in the hub account. The hub account's inbound and outbound endpoints act as a bridge, forwarding queries to and responses from the on-premises DNS server, enabling seamless DNS resolution for resources in the on-premises environment from EC2 instances in the spoke accounts.

**Sources**

[1] Introducing dual-stack and IPv6-only support for Amazon Route 53 Resolver Endpoints | Networking & Content Delivery
aws.amazon.comblogsnetworking-and-content-deliveryintroducing-dual-stack-and-ipv6-only-support-for-amazon-route-53-resolver-endpoints
[2] Privately access a central AWS service endpoint from multiple VPCs - AWS Prescriptive Guidance
docs.aws.amazon.comprescriptive-guidancelatestprivately-access-a-central-aws-service-endpoint-from-multiple-vpcs.html
[3] Route 53 Resolver endpoints and forwarding rules - Hybrid Cloud DNS Options for Amazon VPC
docs.aws.amazon.comwhitepaperslatestroute-53-resolver-endpoints-and-forwarding-rules.html

# Additional sources
https://aws.amazon.com/blogs/security/simplify-dns-management-in-a-multiaccount-environment-with-route-53-resolver/