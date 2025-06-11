The maximum number of network interfaces that you can use per EC2 instance varies by instance type.

https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-eni.html#AvailableIpPerENI

https://aws.amazon.com/blogs/containers/leveraging-cni-custom-networking-alongside-security-groups-for-pods-in-amazon-eks/

[Elastic network interface trunking](https://docs.aws.amazon.com/AmazonECS/latest/developerguide/container-instance-eni.html)

# Difference between ENI, Trunk ENI and Brach ENI

**Elastic Network Interface (ENI):**

An ENI is a virtual network interface that you can attach to an instance in your VPC (Virtual Private Cloud) in AWS. It essentially represents a network interface in the cloud. ENIs can have various attributes, such as private IP addresses, security groups, and MAC addresses. They facilitate communication between instances in the same VPC, between instances in different VPCs (if peering is configured), and with the internet.

**Trunk ENI:**

A trunk ENI is the primary network interface attached to an EC2 instance. It's associated with the instance itself and handles regular network traffic, including communication with other instances, internet traffic, and AWS services. In the context of AWS EKS or Kubernetes on EC2 instances, the trunk ENI is typically used for general networking purposes, including communication with the Kubernetes control plane and other cluster components.

**Branch ENI:**

Branch ENIs are additional network interfaces that you can attach to an EC2 instance as secondary interfaces. They are often used for specialized networking purposes within your Kubernetes cluster or AWS infrastructure. In the context of AWS EKS or Kubernetes, branch ENIs might be utilized for tasks such as pod-to-pod communication, implementing custom networking solutions (e.g., Calico, CNI plugins), or enforcing specific network policies. Branch ENIs provide a way to extend the networking capabilities of your EC2 instances beyond what the trunk ENI alone can provide. In summary, ENIs are the fundamental building blocks for networking in AWS, while trunk ENIs and branch ENIs represent different roles or functions that these network interfaces can fulfill within the context of EC2 instances, VPCs, and services like AWS EKS. Trunk ENIs serve as the primary network interface, while branch ENIs offer additional flexibility for specialized networking requirements.