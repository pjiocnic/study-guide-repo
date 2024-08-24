Hyperplane ENIs differ from standard ENIs in several key ways:

1. Scalability and Connection Limits

* Standard ENIs support up to 65,535 connections/ports.

* Hyperplane ENIs can support up to 65,000 connections/ports per ENI, providing higher scalability.

2. Automatic Provisioning and Management

Standard ENIs need to be manually provisioned and managed by customers.

Hyperplane ENIs are automatically provisioned and managed by AWS based on traffic demands.

3. Multi-Tenancy

* Standard ENIs are dedicated resources for a single service or customer.

* Hyperplane ENIs enable multi-tenant sharing, allowing multiple AWS services or customers to share the same ENI efficiently.

4. Performance

* Hyperplane ENIs are designed to provide higher throughput and lower latency compared to standard ENIs.

5. Visibility and Control

* Customers have full visibility and control over standard ENIs.

* Hyperplane ENIs are not directly visible or configurable by customers; AWS manages them transparently.

6. Use Cases

* Standard ENIs are used for EC2 instances, NAT Gateways, and other customer-managed resources.

* Hyperplane ENIs are used by AWS services like Lambda, App Runner, Network Load Balancer, etc., to connect to resources within a VPC securely.

In essence, Hyperplane ENIs are optimized for AWS services that need to access resources within a customer's VPC. They provide higher scalability, multi-tenancy, and performance, while offloading the provisioning and management overhead from customers. Standard ENIs, on the other hand, offer more direct control and visibility for customer-managed resources like EC2 instances.

# Key differences

The key differences are:

1. **Architecture** : Hyperplane ENIs are built on AWS's proprietary Hyperplane networking technology, which is different from the architecture of standard ENIs.

2. **Multi-Tenancy:** Hyperplane ENIs enable efficient multi-tenant sharing, allowing multiple AWS services or customers to share the same ENI resource. Standard ENIs are dedicated resources for a single service or customer. [1]

3. **Scalability:** AWS automatically scales the number of Hyperplane ENIs based on traffic demands, without any manual intervention. Standard ENIs need to be manually provisioned and scaled.

4. **Management:** Hyperplane ENIs are fully managed by AWS, while standard ENIs are managed by customers.

So while a single Hyperplane ENI may provide higher scalability and multi-tenant sharing capabilities, it does not actually consist of multiple standard ENIs under the hood. It is a distinct type of network interface built on AWS's proprietary Hyperplane networking technology, designed specifically for AWS services to securely access resources within a customer's VPC.

The scalability and multi-tenancy of Hyperplane ENIs are achieved through AWS's advanced networking capabilities and resource sharing mechanisms, rather than by combining multiple standard ENIs.