<h1>[Q] ALB vs NLB</h1>

Certainly! I'd be happy to provide a comprehensive comparison of Network Load Balancer (NLB) and Application Load Balancer (ALB). Both are part of the Elastic Load Balancing (ELB) service offered by AWS, but they serve different purposes and have distinct features. Let's break down the comparison: [1]

1. OSI Layer Operation:

    * NLB: Operates at Layer 4 (Transport Layer) of the OSI model.
    * ALB: Operates at Layer 7 (Application Layer) of the OSI model.

2. Protocol Support:

    * NLB: Supports TCP, UDP, and TLS protocols. [2]
    * ALB: Supports HTTP, HTTPS, HTTP/2, WebSocket, and gRPC protocols. [3]

3. Performance and Scalability:

    * NLB: Designed for extreme performance, can handle millions of requests per second with ultra-low latencies.
    * ALB: Offers good performance but may introduce slightly higher latency due to additional processing at the application layer.

4. IP Address Handling:

    * NLB: Provides static IP addresses per Availability Zone, which can be used as the front-end IP.
    * ALB: Does not provide static IP addresses; uses DNS name for routing.

5. Connection Handling:

    * NLB: Maintains the client's source IP address.
    * ALB: Replaces the client's source IP with its own IP; original client IP is available in the X-Forwarded-For header.

6. Routing Capabilities:

    * NLB: Routes traffic based on IP protocol data.
    * ALB: Offers advanced routing based on content, including host-based and path-based routing, HTTP headers, methods, query parameters, and source IP address.

7. Health Checks:

    * NLB: Supports TCP, HTTP, and HTTPS health checks.
    * ALB: Supports more sophisticated application-level health checks, including HTTP and HTTPS checks with customizable response codes.

8. SSL/TLS Handling:

    * NLB: Supports TLS termination but doesn't inspect the content of the encrypted traffic.
    * ALB: Supports SSL/TLS termination and can inspect the decrypted content for routing decisions.

9. Target Types:

    * NLB: Supports IP addresses, EC2 instances, and Application Load Balancers as targets.
    * ALB: Supports EC2 instances, IP addresses, Lambda functions, and containers.

10. Cross-Zone Load Balancing:

    * NLB: Disabled by default, can be enabled for an additional cost.
    * ALB: Enabled by default at no additional cost.

11. Sticky Sessions:

    * NLB: Supports source IP-based sticky sessions.
    * ALB: Supports more advanced sticky session options, including duration-based and application-based stickiness.

12. AWS PrivateLink Support:

    * NLB: Supports AWS PrivateLink, allowing you to expose services to other VPCs and on-premises networks.
    * ALB: Does not natively support AWS PrivateLink.

13. Integration with AWS Services:

    * NLB: Integrates well with services requiring static IP addresses or PrivateLink.
    * ALB: Integrates well with various AWS services, including AWS WAF, AWS Cognito, and AWS Certificate Manager.

14. Cost:

    * NLB: Generally less expensive per hour, but charges for each new connection.
    * ALB: Slightly more expensive per hour, but doesn't charge for new connections.

15. Use Cases:

    * NLB: Ideal for TCP/UDP traffic, extreme performance requirements, static IP needs, and PrivateLink scenarios.
    * ALB: Best for HTTP/HTTPS traffic, microservices, container-based applications, and when advanced routing and content-based decisions are needed.

16. Zonal Isolation:

    * NLB: Provides better zonal isolation, as it can be configured to maintain traffic within a specific Availability Zone.
    * ALB: Cross-zone load balancing is always on, which may not be ideal for certain compliance or data residency requirements.

17. Security Groups:

    * NLB: Does not use security groups.
    * ALB: Can be associated with security groups for additional network-level security.

In summary, choose NLB when you need extreme performance, static IP addresses, or are working with non-HTTP protocols. Opt for ALB when you're dealing with HTTP/HTTPS traffic and need advanced routing capabilities, especially in microservices architectures. Both load balancers have their strengths, and the choice depends on your specific application requirements and architecture.

Sources
[1] Application Load Balancer-type Target Group for Network Load Balancer | Networking & Content Delivery
aws.amazon.comblogsnetworking-and-content-deliveryapplication-load-balancer-type-target-group-for-network-load-balancer
[2] Application Load Balancer now enables AWS PrivateLink and static IP addresses by direct integration with Network Load Balancer
aws.amazon.comabout-awswhats-newapplication-load-balancer-aws-privatelink-static-ip-addresses-network-load-balancer
[3] Is it possible to do mTLS using a NLB? | AWS re:Post
repost.awsquestionsQUbnYiaPg7RZGTbacncebL3gis-it-possible-to-do-mtls-using-a-nlb