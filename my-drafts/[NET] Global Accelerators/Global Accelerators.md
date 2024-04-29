<h1>Global Accelerators</h1>

<!-- TOC -->

- [1. What is Anycast?](#1-what-is-anycast)
- [2. BGP and GA](#2-bgp-and-ga)
- [3. Traffic routing](#3-traffic-routing)
  - [3.1. Multi-Region](#31-multi-region)
  - [3.2. Handling Endpoint Failover](#32-handling-endpoint-failover)
  - [3.3. Edge Locations](#33-edge-locations)
- [4. Why would you front a Global Accelerator with R53?](#4-why-would-you-front-a-global-accelerator-with-r53)
- [5. What are Network Zones](#5-what-are-network-zones)
- [6. References](#6-references)

<!-- /TOC -->

# 1. What is Anycast?

https://www.cloudflare.com/learning/cdn/glossary/anycast-network/

# 2. BGP and GA

Global Accelerator utilizes Border Gateway Protocol (BGP) Anycast to efficiently route traffic across multiple paths (edge locations) towards its destination. To simplify, a BGP announcement serves as a means for routers to signal their capability and readiness to receive traffic for specific IP addresses. As traffic traverses routers, known as hops, minimizing these hops reduces latency

Also see

# 3. Traffic routing

When you call the anycast IP + port + protocol, a listener located at the points of presence processes inbound connections from your users to Global Accelerator, based on the port (or port range) and protocol (or protocols). Global Accelerator then directs user connections to your endpoint located in an AWS Region thru' AWS backbone. Your endpoint can be an EC2 instance, Application Load Balancer, Network Load Balancer, or Elastic IPs.

## 3.1. Multi-Region

If your application is multi-region, Global Accelerator can direct traffic based on weights or dialing up and down the traffic coming to an AWS Region, giving you granular control over your global traffic.

## 3.2. Handling Endpoint Failover

Global Accelerator provides you with **two IP addresses** that are **anycast from the AWS edge network**. You can set up failover to redirect traffic in seconds without impacting the customer-facing IP address or requiring a change in your customers’ DNS cache.  The customer's DNS cache will have the 2 static anycast IP address which will not change during endpoint failover

## 3.3. Edge Locations

Networking services **Amazon CloudFront, AWS Global Accelerator**, and **Amazon Route 53** sit at AWS’ global edge locations connected by dedicated 100Gbps redundant fiber to deliver data with single digit millisecond AWS network latency.

# 4. Why would you front a Global Accelerator with R53?

Global Accelerator assigns a DNS name for accelerators, but you may want to use your own domain. In that case, you can use Amazon Route 53 to use a custom domain name with your accelerator.

src: https://aws.amazon.com/blogs/containers/operating-a-multi-regional-stateless-application-using-amazon-eks/

<img src="./images/global-accelerator-plus-r53.jpg" title="global-accelerator-plus-r53.jpg" width="900"/>

- https://xebia.com/blog/latency-based-routing-in-aws/

# 5. What are Network Zones

They are simialr to AZ but means 2 independent network stacks

# 6. References

1. https://www.bschaatsbergen.com/latency-based-routing-in-aws#fnref1
1. https://xebia.com/blog/latency-based-routing-in-aws/