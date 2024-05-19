
# Terraform

[Terraform: Building EKS, part 1 – VPC, Subnets and Endpoints](https://rtfm.co.ua/en/terraform-building-eks-part-1-vpc-subnets-and-endpoints/)
[Terraform: building EKS, part 2 – an EKS cluster, WorkerNodes, and IAM](https://rtfm.co.ua/en/terraform-building-eks-part-2-an-eks-cluster-workernodes-and-iam/)
[Terraform: building EKS, part 3 – Karpenter installation](https://rtfm.co.ua/en/terraform-building-eks-part-3-karpenter-installation/)
[Terraform: building EKS, part 4 – installing controllers](https://rtfm.co.ua/en/terraform-building-eks-part-4-installing-controllers/)

## Planning

* AWS:
  * VPC:
    * 6 subnets – 2 private, 2 public, 2 for EKS Control Plane
    * VPC Endpoints – S3, STS, DynamoDB, ECR
* EKS cluster:
  * create a default NodeGroup with a CrticalAddonsOnly=true tag and add Taints (see Kubernetes: Pods and WorkerNodes – control the placement of the Pods on the Nodes)
  * create a new StorageClass with the ReclaimPolicy=Retain
  * add a “masters_access_role” and my IAM User as admins to the aws-auth ConfigMap – “everything is just beginning” (c), so let’s skip RBAC to avoid complications
* IAM:
  * create “masters_access_role” with the eks:DescribeCluster policy for aws eks update-kubeconfig to add users later
  * add OIDC Provider for the cluster
* in the EKS cluster:
  * install EKS EBS CSI Add-on
  * install ExternalDNS controller
  * install AWS Load Balancer Controller
  * add SecretStore CSI Driver and ASCP
  * install Metrics Server
  * install Karpenter
  * add Vertical Pod Autoscaler and Horizontal Pod Autoscaler