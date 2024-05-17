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

1. EKS has 3 modes of authentication: CONFIG_MAP, API_AND_CONFIG_MAP, and API

- CONFIG_MAP mode uses `aws-auth configMap`
- API_AND_CONFIG_MAP uses AWS IAM principals from both Amazon EKS access entry APIs and the aws-auth configMap.
- API uses AWS IAM principals from both Amazon EKS access entry APIs

```bash
aws eks create-cluster \
   --name <CLUSTER_NAME> \
   --role-arn <CLUSTER_ROLE_ARN> \
  --resources-vpc-config subnetIds=<value>,endpointPublicAccess=true,endpointPrivateAccess=true \
  --logging '{"clusterLogging":[{"types":["api","audit","authenticator","controllerManager","scheduler"],"enabled":true}]}' \
  --access-config authenticationMode=API
```

2. To update the cluster

```bash
aws eks update-cluster-config \
   --name <CLUSTER_NAME> \
   --access-config authenticationMode=API
```

3. Creating a cluster by an IAM principal who does not have administrative privileges

```bash
# Create Amazon EKS cluster with no cluster administrator
$ aws eks create-cluster --name <CLUSTER_NAME> \
  --role-arn <CLUSTER_ROLE_ARN> \
  --resources-vpc-config subnetIds=<value>,securityGroupIds=<value>,endpointPublicAccess=true,endpointPrivateAccess=true \
  --logging '{"clusterLogging":[{"types":["api","audit","authenticator","controllerManager","scheduler"],"enabled":true}]}' \
  --access-config authenticationMode=API_AND_CONFIG_MAP,bootstrapClusterCreatorAdminPermissions=false
```

and then verify no access entries exist for the cluster

```bash
# List access entries for cluster
$ aws eks list-access-entries --cluster-name <CLUSTER_NAME>

{
    "accessEntries": []
}
```

also you can verify the IAM principal used to create the cluster does not have access to list the pods

```bash
# Verify cluster creator cannot access cluster
$ kubectl auth can-i --list
error: You must be logged in to the server (Unauthorized)
```

To remove the cluster creator administrator role from an existing cluster that has been upgraded to use `authenticationMode=API` ie supports `access entry`

```bash
# Delete access entry
$ aws eks delete-access-entry --cluster-name <CLUSTER_NAME> \
  --principal-arn <IAM_PRINCIPAL_ARN>
```

# Adding cluster administrators to EXISTING clusters

1. Create a cluster access entry  (that will be granted cluster administrator access) and associate with IAM Principal (can be an IAM Role)

```bash
# Create cluster access entry
$ aws eks create-access-entry --cluster-name <CLUSTER_NAME> \
  --principal-arn <IAM_PRINCIPAL_ARN>

{
    "accessEntry": {
        "clusterName": "<value>",
        "principalArn": "<value>",
        "kubernetesGroups": [],
        "accessEntryArn": "<ACCESS_ENTRY_ARN>",
        "createdAt": "2023-03-30T21:38:24.185000-04:00",
        "modifiedAt": "2023-03-30T21:38:24.185000-04:00",
        "tags": {},
        "username": "arn:aws:sts::<AWS_ACCOUNT_ID>:assumed-role/<ROLE_NAME>/{{SessionName}}"
    }
}
```

2. Assign `AmazonEKSClusterAdminPolicy` policy to access entry

```bash
# Associate access policy to access entry
$ aws eks associate-access-policy --cluster-name <CLUSTER_NAME> \
  --principal-arn <IAM_PRINCIPAL_ARN> \
  --policy-arn arn:aws:eks::aws:cluster-access-policy/AmazonEKSClusterAdminPolicy
  --access-scope type=cluster # since cluster administrator entry we set type=cluster

  {
    "clusterName": "<CLUSTER_NAME>",
    "principalArn": "<AWS_IAM_PRINCIPAL_ARN>",
    "associatedAccessPolicy": {
        "policyArn": "arn:aws:eks::aws:cluster-access-policy/AmazonEKSClusterAdminPolicy",
        "accessScope": {
            "type": "cluster",
            "namespaces": []
        },
        "associatedAt": "2023-04-03T13:44:09.788000-04:00",
        "modifiedAt": "2023-04-03T13:44:09.788000-04:00"
    }
}
```

# Adding namespace administrators