<h1>Amazon EKS Networking</h1>

<!-- TOC -->

- [1. VPC Lattice](#1-vpc-lattice)
- [2. CNI PlugIns](#2-cni-plugins)
- [3. EKS Networking Basics](#3-eks-networking-basics)
- [4. Private Links](#4-private-links)

<!-- /TOC -->

# 1. VPC Lattice

1. [EKS Application Networking with Amazon VPC Lattice](https://www.youtube.com/watch?v=AdO0bx3N3fs)
2. [Application Networking with Amazon VPC Lattice and Amazon EKS by Viji Sarathy and Sheetal Joshi ](https://aws.amazon.com/blogs/containers/application-networking-with-amazon-vpc-lattice-and-amazon-eks/)

# 2. CNI PlugIns

1. [Hands on with EKS Networking (2023) | Amazon EKS Workshop BY Sheetal Joshi](https://www.youtube.com/watch?v=EAZnXII9NTY)
1. [Deep dive on the AWS CNI Plug-in for Kubernetes - Mitch Beaumont (AWS)](https://www.youtube.com/watch?v=ezcnPcRcJdc)
1. [Leveraging CNI custom networking alongside security groups for pods in Amazon EKS by Bin Liu and Haofei Feng](https://aws.amazon.com/blogs/containers/leveraging-cni-custom-networking-alongside-security-groups-for-pods-in-amazon-eks/)

  - Ingress

  <img src="./images/eks-ingress-traffic-pattern.jpg" title="eks-cni-networking-1.png" width="900"/>

  - Egress

  <img src="./images/eks-egress-traffic-pattern.jpg" title="eks-cni-networking-1.png" width="900"/>

# 3. EKS Networking Basics

1. [De-mystifying cluster networking for Amazon EKS worker nodes by Nathan Taber](https://aws.amazon.com/blogs/containers/de-mystifying-cluster-networking-for-amazon-eks-worker-nodes/)
1. [Upcoming Changes to IP Assignment for EKS Managed Node Groups by Nathan Taber](https://aws.amazon.com/blogs/containers/upcoming-changes-to-ip-assignment-for-eks-managed-node-groups/)

# 4. Private Links

1. [Enable Private Access to the Amazon EKS Kubernetes API with AWS PrivateLink by Aaron Miller, Praseeda Sathaye, and Vijay Chintalapati](https://aws.amazon.com/blogs/containers/enable-private-access-to-the-amazon-eks-kubernetes-api-with-aws-privatelink/)