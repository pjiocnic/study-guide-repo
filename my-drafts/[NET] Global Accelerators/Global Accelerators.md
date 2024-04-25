
# What is Anycast?

https://www.cloudflare.com/learning/cdn/glossary/anycast-network/

# BGP and GA

Global Accelerator utilizes Border Gateway Protocol (BGP) Anycast to efficiently route traffic across multiple paths (edge locations) towards its destination. To simplify, a BGP announcement serves as a means for routers to signal their capability and readiness to receive traffic for specific IP addresses. As traffic traverses routers, known as hops, minimizing these hops reduces latency

Also see

# Traffic routing

when you call the Global Accelerator endpoint, your ISP routes the traffic to the nearest AWS edge location. The edge location then routes the traffic to the nearest AWS region. This is a more efficient way to route traffic compared to the traditional method of routing traffic through the internet to the AWS region.

# Why would you front a Global Accelerator with R53?

Global Accelerator assigns a DNS name for accelerators, but you may want to use your own domain. In that case, you can use Amazon Route 53 to use a custom domain name with your accelerator.

src: https://aws.amazon.com/blogs/containers/operating-a-multi-regional-stateless-application-using-amazon-eks/

<img src="./images/global-accelerator-plus-r53.jpg" title="global-accelerator-plus-r53.jpg" width="900"/>

- https://xebia.com/blog/latency-based-routing-in-aws/

# What are Network Zones

They are simialr to AZ but means 2 independent network stacks



# References

1. https://www.bschaatsbergen.com/latency-based-routing-in-aws#fnref1
1. https://xebia.com/blog/latency-based-routing-in-aws/