![alt text](../../amazon/amazon-eks/images/global-accelerator-plus-r53.png)

1. create cluster in 2 regions

```bash
eksctl create cluster --name multi-region-blog
```

2. Set env variables

```bash
export AWS_REGION_1=<<Primary AWS Region>>
export AWS_REGION_2=<<Secondary AWS Region>>
export EKS_CLUSTER_1=<<EKS cluster name in the primary AWS Region>>
export EKS_CLUSTER_2=<<EKS cluster name in the secondary AWS Region>>
export my_domain=<<example.com Your domain>>
export ACCOUNT_ID=$(aws sts get-caller-identity --query 'Account' --output text)
```

3. Install the AWS Load Balancer Controller