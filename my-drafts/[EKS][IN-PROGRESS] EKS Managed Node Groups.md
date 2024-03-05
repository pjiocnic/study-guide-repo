Amazon EKS managed node groups automate the provisioning and lifecycle management of nodes for Amazon EKS clusters

# Limits

You can have 10 managed node groups per EKS cluster
Each Nodegroup can have a maximum of 100 nodes per node group

# Creating node group from CLI

```bash
aws eks create-nodegroup

aws eks list-nodegroups

aws eks describe-nodegroup

# To edit configuration (like Labels, Tags and # of nodes) of nodegroup
aws eks update-nodegroup-config

# removing node group
aws eks delete-nodegroup
```

# Creating EKS Cluster and Node Group in console

# Create a Role (https://docs.aws.amazon.com/eks/latest/userguide/service_IAM_role.html#create-service-role)

- cluster policy - `cluster-trust-policy.json`

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "Service": "eks.amazonaws.com"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
```

- create a role `eksClusterRole`

```bash
aws iam create-role \
  --role-name eksClusterRole \
  --assume-role-policy-document file://"cluster-trust-policy.json"
```

- Attach the required IAM policy to the role

```bash
aws iam attach-role-policy \
  --policy-arn arn:aws:iam::aws:policy/AmazonEKSClusterPolicy \
  --role-name eksClusterRole
```

# References

1. [Extending the EKS API: Managed Node Groups By Raghav Tripathi, Michael Hausenblas, and Nathan Taber](https://aws.amazon.com/blogs/containers/eks-managed-node-groups/)
1. https://www.eksworkshop.com/docs/fundamentals/managed-node-groups/