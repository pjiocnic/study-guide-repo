<h1>Amazon EKS Networking</h1>

<!-- TOC -->

- [1. VPC Lattice](#1-vpc-lattice)
- [2. Custom Networking](#2-custom-networking)
- [3. How Data Plane connects to Control Plane](#3-how-data-plane-connects-to-control-plane)
- [4. Pod density](#4-pod-density)
- [5. Videos](#5-videos)

<!-- /TOC -->

# 1. VPC Lattice

1. [EKS Application Networking with Amazon VPC Lattice](https://www.youtube.com/watch?v=AdO0bx3N3fs)
2. [Application Networking with Amazon VPC Lattice and Amazon EKS by Viji Sarathy and Sheetal Joshi ](https://aws.amazon.com/blogs/containers/application-networking-with-amazon-vpc-lattice-and-amazon-eks/)

# 2. Custom Networking

1. [Hands on with EKS Networking (2023) | Amazon EKS Workshop BY Sheetal Joshi](https://www.youtube.com/watch?v=EAZnXII9NTY)
1. [Deep dive on the AWS CNI Plug-in for Kubernetes - Mitch Beaumont (AWS)](https://www.youtube.com/watch?v=ezcnPcRcJdc)
1. [Leveraging CNI custom networking alongside security groups for pods in Amazon EKS by Bin Liu and Haofei Feng](https://aws.amazon.com/blogs/containers/leveraging-cni-custom-networking-alongside-security-groups-for-pods-in-amazon-eks/)

  - Ingress

  <img src="./images/eks-ingress-traffic-pattern.jpg" title="eks-cni-networking-1.png" width="900"/>

  - Egress

  <img src="./images/eks-egress-traffic-pattern.jpg" title="eks-cni-networking-1.png" width="900"/>

# 3. How Data Plane connects to Control Plane

1. [De-mystifying cluster networking for Amazon EKS worker nodes by Nathan Taber](https://aws.amazon.com/blogs/containers/de-mystifying-cluster-networking-for-amazon-eks-worker-nodes/)
1. [Upcoming Changes to IP Assignment for EKS Managed Node Groups by Nathan Taber](https://aws.amazon.com/blogs/containers/upcoming-changes-to-ip-assignment-for-eks-managed-node-groups/)
1. [A Deep Dive Into AWS EKS Networking And Its Best Practices](https://www.codincity.com/blogs/a-deep-dive-into-aws-eks-networking-and-its-best-practices)

# 4. Pod density

1. [Increasing pod density for Windows nodes on Amazon EKS by Harsh Rawat, Purvi Goyal, and Jie Chen](https://aws.amazon.com/blogs/containers/increasing-pod-density-for-windows-nodes-on-amazon-eks/)
1. [Amazon VPC CNI plugin increases pods per node limits by Sheetal Joshi, Mike Stefaniak, and Jayanth Varavani ](https://aws.amazon.com/blogs/containers/amazon-vpc-cni-increases-pods-per-node-limits/)

# 5. Videos

1. [The Routing Loop - Chalk Talk - Networking strategies for Kubernetes on AWS](https://www.twitch.tv/videos/2057125802?collection=HBANRY7lIxagXQ)
1. [The Routing Loop - Networking Strategies for Kubernetes on AWS - Part 2](https://www.twitch.tv/videos/2063623113)