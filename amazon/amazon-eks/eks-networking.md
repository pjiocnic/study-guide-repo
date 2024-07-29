<h1>Amazon EKS Networking</h1>

<!-- TOC -->

- [1. Exposing applications for external access](#1-exposing-applications-for-external-access)
- [2. VPC Lattice](#2-vpc-lattice)
- [3. Custom Networking](#3-custom-networking)
- [4. How Data Plane connects to Control Plane](#4-how-data-plane-connects-to-control-plane)
- [5. Pod density](#5-pod-density)
- [6. VPC Prefix delegation](#6-vpc-prefix-delegation)
- [7. How to check # Pods that a node can run?](#7-how-to-check--pods-that-a-node-can-run)
- [8. Videos](#8-videos)

<!-- /TOC -->

# 1. Exposing applications for external access

explore Service, Ingress resource  and Load Balancer types

1. [Exposing Kubernetes Applications, Part 1: Service and Ingress Resources by Dmitry Nutels and Tsahi Duek](https://aws.amazon.com/blogs/containers/exposing-kubernetes-applications-part-1-service-and-ingress-resources/)
1. [Exposing Kubernetes Applications, Part 2: AWS Load Balancer Controller by Dmitry Nutels and Tsahi Duek](https://aws.amazon.com/blogs/containers/exposing-kubernetes-applications-part-2-aws-load-balancer-controller/)

# 2. VPC Lattice

1. [EKS Application Networking with Amazon VPC Lattice](https://www.youtube.com/watch?v=AdO0bx3N3fs)
2. [Application Networking with Amazon VPC Lattice and Amazon EKS by Viji Sarathy and Sheetal Joshi ](https://aws.amazon.com/blogs/containers/application-networking-with-amazon-vpc-lattice-and-amazon-eks/)

# 3. Custom Networking

1. [Hands on with EKS Networking (2023) | Amazon EKS Workshop BY Sheetal Joshi](https://www.youtube.com/watch?v=EAZnXII9NTY)
1. [Deep dive on the AWS CNI Plug-in for Kubernetes - Mitch Beaumont (AWS)](https://www.youtube.com/watch?v=ezcnPcRcJdc)
1. [Leveraging CNI custom networking alongside security groups for pods in Amazon EKS by Bin Liu and Haofei Feng](https://aws.amazon.com/blogs/containers/leveraging-cni-custom-networking-alongside-security-groups-for-pods-in-amazon-eks/)

  - Ingress

  <img src="./images/eks-ingress-traffic-pattern.jpg" title="eks-cni-networking-1.png" width="900"/>

  - Egress

  <img src="./images/eks-egress-traffic-pattern.jpg" title="eks-cni-networking-1.png" width="900"/>

# 4. How Data Plane connects to Control Plane

1. [De-mystifying cluster networking for Amazon EKS worker nodes by Nathan Taber](https://aws.amazon.com/blogs/containers/de-mystifying-cluster-networking-for-amazon-eks-worker-nodes/)
1. [Upcoming Changes to IP Assignment for EKS Managed Node Groups by Nathan Taber](https://aws.amazon.com/blogs/containers/upcoming-changes-to-ip-assignment-for-eks-managed-node-groups/)
1. [A Deep Dive Into AWS EKS Networking And Its Best Practices](https://www.codincity.com/blogs/a-deep-dive-into-aws-eks-networking-and-its-best-practices)
1. [EKS Control Plane](https://aws.github.io/aws-eks-best-practices/reliability/docs/controlplane/)

# 5. Pod density

1. [Increasing pod density for Windows nodes on Amazon EKS by Harsh Rawat, Purvi Goyal, and Jie Chen](https://aws.amazon.com/blogs/containers/increasing-pod-density-for-windows-nodes-on-amazon-eks/)
1. [Amazon VPC CNI plugin increases pods per node limits by Sheetal Joshi, Mike Stefaniak, and Jayanth Varavani ](https://aws.amazon.com/blogs/containers/amazon-vpc-cni-increases-pods-per-node-limits/)

# 6. VPC Prefix delegation

1. [AWS: VPC Prefix and the maximum of Pods on Kubernetes WorkerNodes](https://rtfm.co.ua/en/aws-vpc-prefix-and-maximum-of-pods-on-kubernetes-workernodes/)

# 7. How to check # Pods that a node can run?

1. [AWS: VPC Prefix and the maximum of Pods on Kubernetes WorkerNodes](https://rtfm.co.ua/en/aws-vpc-prefix-and-maximum-of-pods-on-kubernetes-workernodes/)

# 8. Videos

[See Networking section of Re-invent videos](./amazon-eks-reinvent-REG.md#4-networking)