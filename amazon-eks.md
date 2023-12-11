<H1>EKS Blog Posts</H1>

<!-- TOC -->

- [1. ALBs](#1-albs)
- [2. Blueprints](#2-blueprints)
- [3. CDK](#3-cdk)
- [4. CI/CD](#4-cicd)
  - [4.1. ArgoCD](#41-argocd)
  - [4.2. Spinnaker](#42-spinnaker)
  - [4.3. Kustomize with Spinnaker](#43-kustomize-with-spinnaker)
- [5. Crossplane](#5-crossplane)
- [6. Integrations](#6-integrations)
- [7. Istio](#7-istio)
- [8. Lambda](#8-lambda)
- [9. Networking](#9-networking)
- [10. Observability](#10-observability)
- [11. Kubernetes on AWS](#11-kubernetes-on-aws)
- [12. Samples](#12-samples)
- [13. Terraform](#13-terraform)
- [14. Videos](#14-videos)
- [15. Workshop](#15-workshop)
  - [15.1. Crossplane workshop](#151-crossplane-workshop)

<!-- /TOC -->

# 1. ALBs

1. [Deploying AWS Load Balancer Controller on Amazon EKS by Karthik Chemudupati, Scott Chang, and James Wenzel](https://aws.amazon.com/blogs/networking-and-content-delivery/deploying-aws-load-balancer-controller-on-amazon-eks/)
- [CODE / Cloudformation](https://github.com/aws-samples/eks-single-button-aws-alb-addon-installation/tree/main)

# 2. Blueprints

1. [Amazon EKS Blueprints Quick Start](https://aws-quickstart.github.io/cdk-eks-blueprints/pipelines/)
2. [Amazon EKS Blueprints for Terraform](https://aws-ia.github.io/terraform-aws-eks-blueprints/v4.32.1/)

# 3. CDK

1. [Simplify integration of your Amazon EKS cluster with Amazon EKS Blueprints for CDK by Mikhail Shapirov and Elamaran Shanmugam](https://aws.amazon.com/blogs/infrastructure-and-automation/simplify-integration-of-your-amazon-eks-cluster-with-amazon-eks-blueprints-for-cdk/)

# 4. CI/CD

1. [Configure Continuous Deployment Using Kustomize Components and Spinnaker Operator in Amazon EKS by Praseeda Sathaye, Fernando Freire, and Puneet Singh](https://aws.amazon.com/blogs/opensource/configure-continuous-deployment-using-kustomize-components-and-spinnaker-operator-in-amazon-eks/)

## 4.1. ArgoCD

1. [Amazon EKS Workshop > Intermediate > Continuous Deployment with ArgoCD](https://archive.eksworkshop.com/intermediate/290_argocd/)
2. [EKSWorkshop (NEW) > GitOps > Argo CD](https://www.eksworkshop.com/docs/automation/gitops/argocd/)
3. [**[MY NEXT]** Continuous Deployment and GitOps delivery with Amazon EKS Blueprints and ArgoCD by Tsahi Duek and Dima Breydo](https://aws.amazon.com/blogs/containers/continuous-deployment-and-gitops-delivery-with-amazon-eks-blueprints-and-argocd/)
4. [Amazon EKS Blueprints Quick Start > Argo CD Add-on](https://aws-quickstart.github.io/cdk-eks-blueprints/addons/argo-cd/)
5. [How to Deploy ArgoCD in EKS cluster For Continuous Deployment](https://blog.devgenius.io/how-to-deploy-argocd-in-eks-cluster-for-continuous-deployment-6ebbb3009024)

## 4.2. Spinnaker

1. [Configure Continuous Deployment Using Kustomize Components and Spinnaker Operator in Amazon EKS by Praseeda Sathaye, Fernando Freire, and Puneet Singh](https://aws.amazon.com/blogs/opensource/configure-continuous-deployment-using-kustomize-components-and-spinnaker-operator-in-amazon-eks/)

## 4.3. Kustomize with Spinnaker

1. [Configure Continuous Deployment Using Kustomize Components and Spinnaker Operator in Amazon EKS by Praseeda Sathaye, Fernando Freire, and Puneet Singh](https://aws.amazon.com/blogs/opensource/configure-continuous-deployment-using-kustomize-components-and-spinnaker-operator-in-amazon-eks/)

# 5. Crossplane

[see amazon-eks-crossplane.md](./amazon-eks-crossplane.md)

# 6. Integrations

1. [Integrating an EKS Cluster with Other AWS Services by Rushabh Doshi](https://app.pluralsight.com/library/courses/eks-cluster-aws-services-integrating/table-of-contents)

# 7. Istio

1. [Getting Started with Istio on Amazon EKS by Praseeda Sathaye and Vijay Chintalapati](https://aws.amazon.com/blogs/opensource/getting-started-with-istio-on-amazon-eks/)
- [Code](https://github.com/aws-ia/terraform-aws-eks-blueprints.git)

# 8. Lambda

1. [Deploying AWS Lambda functions using AWS Controllers for Kubernetes (ACK) By rajdeep saha](https://aws.amazon.com/blogs/compute/deploying-aws-lambda-functions-using-aws-controllers-for-kubernetes-ack/)

# 9. Networking

1. [EKS Application Networking with Amazon VPC Lattice](https://www.youtube.com/watch?v=AdO0bx3N3fs)
2. [Application Networking with Amazon VPC Lattice and Amazon EKS by Viji Sarathy and Sheetal Joshi ](https://aws.amazon.com/blogs/containers/application-networking-with-amazon-vpc-lattice-and-amazon-eks/)

# 10. Observability

1. [Hands on with EKS Observability (2023) | Amazon EKS Workshop](https://www.youtube.com/watch?v=ajPe7HVypxg)

# 11. Kubernetes on AWS

1. [12 steps to setup Kubernetes Cluster on AWS EC2](https://www.golinuxcloud.com/setup-kubernetes-cluster-on-aws-ec2/)

# 12. Samples

1. [Blueprints for Crossplane on Amazon EKS](https://github.com/awslabs/crossplane-on-eks)
1. [[_**CDK,EKS,Fargate,Typescript**_] Building and deploying Fargate with EKS in an enterprise context using the AWS Cloud Development Kit and cdk8s+ by Jasper Wang and Van Vo Thanh](https://aws.amazon.com/blogs/containers/building-and-deploying-fargate-with-eks-in-an-enterprise-context-using-the-aws-cloud-development-kit-and-cdk8s/)
<img src="./images/CDK8s-1.png" title="CDK8s-1.png" width="900"/>


# 13. Terraform

1. [Amazon EKS Blueprints for Terraform](https://aws-ia.github.io/terraform-aws-eks-blueprints/v4.1.0/)

# 14. Videos

1. [Deploying Lambda with EKS and Crossplane | Serverless Office Hours](https://www.youtube.com/watch?v=8CdyxX7eGkA)

# 15. Workshop

1. [EKS Blueprints for CDK Workshop](https://catalog.workshops.aws/eks-blueprints-for-cdk/en-US)
2. [Running batch workloads on Amazon EKS with AWS Batch](https://catalog.workshops.aws/running-batch-on-eks/en-US)
3. [EKS Immersion Workshop](https://catalog.workshops.aws/eks-immersionday/en-US)
4. [EKS Workshop - Practical exercises to learn about Amazon Elastic Kubernetes Service](https://www.eksworkshop.com/)
5. [Manage your EKS cluster in Full-stack with CDK](https://catalog.us-east-1.prod.workshops.aws/workshops/c15012ac-d05d-46b1-8a4a-205e7c9d93c9/en-US/10-intro)
6. [Kubernetes hands on introduction (2023) | Amazon EKS Workshop](https://www.youtube.com/watch?v=_TFk5jQr2lk&list=PLehXSATXjcQGIctop3ok3xIMB1cUCPYEK)

## 15.1. Crossplane workshop

1. [Crossplane workshop](https://www.eksworkshop.com/docs/automation/controlplanes/crossplane/)



