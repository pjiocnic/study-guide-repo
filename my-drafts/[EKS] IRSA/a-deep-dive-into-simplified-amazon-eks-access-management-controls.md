[A deep dive into simplified Amazon EKS access management controls by Sheetal Joshi, Rodrigo Bersa, and Mike Stefaniak](https://aws.amazon.com/blogs/containers/a-deep-dive-into-simplified-amazon-eks-access-management-controls/)

- authentication (AuthN) and authorization (AuthZ)
- access entries and access policies

- An **access entry** is a cluster identity—directly linked to an AWS IAM principal user or role—that is used to authenticate to an Amazon EKS cluster.
- An Amazon EKS **access policy** authorizes an access entry to perform specific cluster actions.  These are Amazon EKS-specific policies and not be confused with IAM policies.  These are defined and managed by AWS EKS.

# Benefits

- With this approach administrators can completely remove or refine the permissions automatically granted to the AWS IAM principal used to create the cluster
- If a misconfiguration occurs, then cluster access can be restored simply by calling an Amazon EKS API, as long as the caller has the necessary permissions.

# Recommendations

1. Always use AWS IAM roles as principals to create Amazon EKS clusters.
**Reason**: Roles provide a layer-of-indirection that decouples users from permissions. Users can be removed from roles, without having to adjust AWS IAM policies that provide permissions to the cluster creator roles.

# Walk-Thru

1. cluster administrators create Amazon EKS access entries with the desired AWS IAM principals
1. cluster administrators can then grant access to those entries by assigning access policies.
1. Sample list of access-policies

```bash
# List of all access policies for managing cluster access
$ aws eks list-access-policies
{
    "accessPolicies": [
        {
            "name": "AmazonEKSAdminPolicy",
            "arn": "arn:aws:eks::aws:cluster-access-policy/AmazonEKSAdminPolicy"
        },
        {
            "name": "AmazonEKSClusterAdminPolicy",
            "arn": "arn:aws:eks::aws:cluster-access-policy/AmazonEKSClusterAdminPolicy"
        },
        {
            "name": "AmazonEKSEditPolicy",
            "arn": "arn:aws:eks::aws:cluster-access-policy/AmazonEKSEditPolicy"
        },
        {
            "name": "AmazonEKSViewPolicy",
            "arn": "arn:aws:eks::aws:cluster-access-policy/AmazonEKSViewPolicy"
        }
    ]
}
```

EKS access policies include permission sets that support common use cases of administration, editing, or read-only access to **Kubernetes resources**

Ref: https://kubernetes.io/docs/reference/access-authn-authz/rbac/#user-facing-roles