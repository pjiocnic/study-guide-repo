<H1>EKS Blog Posts</H1>

<!-- TOC -->

- [1. EKS Series](#1-eks-series)
- [2. Curate](#2-curate)
- [3. AWS Controllers for Kubernetes (ACK)](#3-aws-controllers-for-kubernetes-ack)
- [4. Managed Node Groups](#4-managed-node-groups)
- [5. node group vs auto scaling group](#5-node-group-vs-auto-scaling-group)
- [6. ALBs](#6-albs)
- [7. Best Practices](#7-best-practices)
- [8. Blueprints](#8-blueprints)
- [9. CDK](#9-cdk)
  - [9.1. Cluster Creation](#91-cluster-creation)
- [10. CI/CD](#10-cicd)
  - [10.1. ArgoCD](#101-argocd)
  - [10.2. Spinnaker](#102-spinnaker)
  - [10.3. Kustomize with Spinnaker](#103-kustomize-with-spinnaker)
- [11. Crossplane](#11-crossplane)
- [12. Integrations](#12-integrations)
- [13. Istio](#13-istio)
- [14. Observability](#14-observability)
- [15. Kubernetes on AWS](#15-kubernetes-on-aws)
- [16. Samples](#16-samples)
- [17. Terraform](#17-terraform)
- [18. Videos](#18-videos)
- [19. Newsletter](#19-newsletter)

<!-- /TOC -->

# 1. EKS Series

1. [EKS Cluster Creation: The First Step in Your AWS EKS Journey(Part 1) by Ekant Mate](https://aws.plainenglish.io/eks-cluster-creation-the-first-step-in-your-aws-eks-journey-90d1640a3604)
1. [AWS Workshops DIY — EKS Workshop — 1. Introduction and Scope Review By Luther](https://medium.com/the-aws-way/aws-workshops-diy-eks-workshop-1-introduction-and-scope-review-7599f38f06b3)

# 2. Curate

1. [Use CloudFormation to automate management of the Fargate profile in Amazon EKS by Gaurav Acharya](https://aws.amazon.com/blogs/containers/use-cloudformation-to-automate-management-of-the-fargate-profile-in-amazon-eks/)
1. [8 best practices when automating your deployments with AWS CloudFormation by Dave May](https://aws.amazon.com/blogs/infrastructure-and-automation/best-practices-automating-deployments-with-aws-cloudformation/)
1. https://eksctl.io/usage/vpc-configuration/
1. https://aws.github.io/aws-eks-best-practices/networking/vpc-cni/

# 3. AWS Controllers for Kubernetes (ACK)

A tool that lets you directly manage AWS services from Kubernetes

1. [Introducing the AWS Controllers for Kubernetes (ACK) by Jay Pipes, Michael Hausenblas, and Nathan Taber](https://aws.amazon.com/blogs/containers/aws-controllers-for-kubernetes-ack/)

# 4. Managed Node Groups

1. [What are Managed Node Groups By Raghav Tripathi, Michael Hausenblas, and Nathan Taber](https://aws.amazon.com/blogs/containers/eks-managed-node-groups/)
1. [Amazon EKS > User Guide> Managed node groups](https://docs.aws.amazon.com/eks/latest/userguide/managed-node-groups.html)

# 5. node group vs auto scaling group

[EKS Node groups and auto scaling groups](https://repost.aws/questions/QU0mMQm_nhSCur_zM6UrB5EQ/eks-node-groups-and-auto-scaling-groups)
[ECS + Cluster Auto Scaling versus EKS + Managed Node Group](https://cloudonaut.io/versus/docker-containers/ecs-cluster-auto-scaling-vs-eks-managed-node-group/)

# 6. ALBs

1. [Deploying AWS Load Balancer Controller on Amazon EKS by Karthik Chemudupati, Scott Chang, and James Wenzel](https://aws.amazon.com/blogs/networking-and-content-delivery/deploying-aws-load-balancer-controller-on-amazon-eks/)
- [CODE / Cloudformation](https://github.com/aws-samples/eks-single-button-aws-alb-addon-installation/tree/main)

# 7. Best Practices

1. [EKS Best Practices Guides](https://aws.github.io/aws-eks-best-practices/networking/vpc-cni/)

# 8. Blueprints

1. [Amazon EKS Blueprints Quick Start](https://aws-quickstart.github.io/cdk-eks-blueprints/pipelines/)
2. [Amazon EKS Blueprints for Terraform](https://aws-ia.github.io/terraform-aws-eks-blueprints/v4.32.1/)
3. [[WORKSHOP] EKS Blueprints for Terraform](https://catalog.us-east-1.prod.workshops.aws/workshops/d2b662ae-e9d7-4b31-b68b-64ade19d5dcc/en-US)

# 9. CDK

## 9.1. Cluster Creation

1. [Simplify integration of your Amazon EKS cluster with Amazon EKS Blueprints for CDK by Mikhail Shapirov and Elamaran Shanmugam](https://aws.amazon.com/blogs/infrastructure-and-automation/simplify-integration-of-your-amazon-eks-cluster-with-amazon-eks-blueprints-for-cdk/)

# 10. CI/CD

1. [Configure Continuous Deployment Using Kustomize Components and Spinnaker Operator in Amazon EKS by Praseeda Sathaye, Fernando Freire, and Puneet Singh](https://aws.amazon.com/blogs/opensource/configure-continuous-deployment-using-kustomize-components-and-spinnaker-operator-in-amazon-eks/)

## 10.1. ArgoCD

1. [Amazon EKS Workshop > Intermediate > Continuous Deployment with ArgoCD](https://archive.eksworkshop.com/intermediate/290_argocd/)
2. [EKSWorkshop (NEW) > GitOps > Argo CD](https://www.eksworkshop.com/docs/automation/gitops/argocd/)
3. [**[MY NEXT]** Continuous Deployment and GitOps delivery with Amazon EKS Blueprints and ArgoCD by Tsahi Duek and Dima Breydo](https://aws.amazon.com/blogs/containers/continuous-deployment-and-gitops-delivery-with-amazon-eks-blueprints-and-argocd/)
4. [Amazon EKS Blueprints Quick Start > Argo CD Add-on](https://aws-quickstart.github.io/cdk-eks-blueprints/addons/argo-cd/)
5. [How to Deploy ArgoCD in EKS cluster For Continuous Deployment](https://blog.devgenius.io/how-to-deploy-argocd-in-eks-cluster-for-continuous-deployment-6ebbb3009024)

## 10.2. Spinnaker

1. [Configure Continuous Deployment Using Kustomize Components and Spinnaker Operator in Amazon EKS by Praseeda Sathaye, Fernando Freire, and Puneet Singh](https://aws.amazon.com/blogs/opensource/configure-continuous-deployment-using-kustomize-components-and-spinnaker-operator-in-amazon-eks/)

## 10.3. Kustomize with Spinnaker

1. [Configure Continuous Deployment Using Kustomize Components and Spinnaker Operator in Amazon EKS by Praseeda Sathaye, Fernando Freire, and Puneet Singh](https://aws.amazon.com/blogs/opensource/configure-continuous-deployment-using-kustomize-components-and-spinnaker-operator-in-amazon-eks/)

# 11. Crossplane

[see amazon-eks-crossplane.md](./amazon-eks-crossplane.md)

# 12. Integrations

1. [Integrating an EKS Cluster with Other AWS Services by Rushabh Doshi](https://app.pluralsight.com/library/courses/eks-cluster-aws-services-integrating/table-of-contents)

# 13. Istio

1. [Getting Started with Istio on Amazon EKS by Praseeda Sathaye and Vijay Chintalapati](https://aws.amazon.com/blogs/opensource/getting-started-with-istio-on-amazon-eks/)
- [Code](https://github.com/aws-ia/terraform-aws-eks-blueprints.git)

# 14. Observability

1. [Hands on with EKS Observability (2023) | Amazon EKS Workshop](https://www.youtube.com/watch?v=ajPe7HVypxg)

# 15. Kubernetes on AWS

1. [12 steps to setup Kubernetes Cluster on AWS EC2](https://www.golinuxcloud.com/setup-kubernetes-cluster-on-aws-ec2/)

# 16. Samples

1. [Blueprints for Crossplane on Amazon EKS](https://github.com/awslabs/crossplane-on-eks)
1. [[_**CDK,EKS,Fargate,Typescript**_] Building and deploying Fargate with EKS in an enterprise context using the AWS Cloud Development Kit and cdk8s+ by Jasper Wang and Van Vo Thanh](https://aws.amazon.com/blogs/containers/building-and-deploying-fargate-with-eks-in-an-enterprise-context-using-the-aws-cloud-development-kit-and-cdk8s/)
<img src="./images/CDK8s-1.png" title="CDK8s-1.png" width="900"/>

# 17. Terraform

1. [Amazon EKS Blueprints for Terraform](https://aws-ia.github.io/terraform-aws-eks-blueprints/v4.1.0/)



# 19. Newsletter

1. [Amazon EKS now supports Kubernetes 1.22 by Chris Short](https://aws.amazon.com/blogs/containers/amazon-eks-now-supports-kubernetes-1-22/)

