<h1> EKS Networking</h1>

# 1. Default CNI behavior without enabling CNI **custom** networking

<img src="./images/eks-networking-without-cni.jpg" title="eks-networking-without-cni.jpg" width="900"/>

1. When an Amazon EC2 instance is provisioned, one ENI attached to an underlying subnet with the Worker Node Security Group attached.
2. **Primary IP is used to talk to host:** A primary IP that is attached to the ENI is used to access the host.
3. **Pods only get secondary IPs**: As pods are deployed on the Amazon EC2 instance, the VPC CNI attaches secondary IPs from the VPC to the same ENI until the limit on that ENI is reached.
4. **Additional secondary ENIs**: The VPC CNI provisions a second ENI on the same subnet and fills it with the secondary IPs.
5. **Limits reached on secondary ENIs**: This process continues until the ENI and secondary IP limits of an instance are reached.
6. **Same Security group is shared across all ENIs**: The same security group(s) are attached to all worker node ENIs and apply to all the pods.

# 2. Enabling custom networking without security groups for pods

<img src="./images/eks-networking-without-cni.jpg" title="eks-networking-without-cni.jpg" width="900"/>

Write diagrams from following:

https://catalog.us-east-1.prod.workshops.aws/workshops/afee4679-89af-408b-8108-44f5b1065cc7/en-US/500-eks-terraform-workshop/510-scenario/intro

https://aws.amazon.com/blogs/containers/leveraging-cni-custom-networking-alongside-security-groups-for-pods-in-amazon-eks/
https://jicowan.medium.com/custom-networking-with-the-aws-vpc-cni-plug-in-c6eebb105220
https://jicowan.medium.com/the-impacts-of-using-custom-networking-with-the-aws-vpc-cni-65f109d245be
https://blog.sivamuthukumar.com/eks-high-pod-density

# 3. What is Trunking
1. https://docs.aws.amazon.com/AmazonECS/latest/developerguide/container-instance-eni.html#eni-trunking-considerations
1. https://aws.amazon.com/blogs/containers/leveraging-cni-custom-networking-alongside-security-groups-for-pods-in-amazon-eks/
1. [Increasing task density with ENI Trunking](https://docs.aws.amazon.com/AmazonECS/latest/bestpracticesguide/networking-networkmode-awsvpc.html)

# 4. Using custom CNI

1. To use a new CIDR range for the pods update `aws-node daemonset` by setting following varaible:
`AWS_VPC_K8S_CNI_CUSTOM_NETWORK_CFG=TRUE`

When this variable is set to true, the CNI will allocate IP addresses from the subnet specified in the `ENIConfig` assigned to the worker node.

**ENIConfig (aka custom resource definition CRD)**: This lets you specify the subnet and security groups you want to use for the pods running on a particular worker node

# How does Prefix Delegation allow more pods (density) per node instance

[See here](../../amazon-eks-networking.md#5-prefix-delegation)

## How to enable prefix delegation
