
one cluster per team, per environment, per account

some level of logical or physical isolation between them

Security, for example, so that each team can operate only on its intended workloads and cannot operate on other teams’ workloads, or to have network isolation between application (by default, all pods, in all namespaces, can communicate to each other). Another reason is to provide fair shares of resources (CPU, memory, network…) across different workloads sharing the same infrastructure

Software as a service (SAAS) companies that deploy a **solution per customer**, can increase utilization of infrastructure by running **multiple tenants on the same cluster**, but will need to provide an even higher degree of isolation between each of the tenant

**foundational for multi-tenancy**: Namespaces is a way to divide cluster resources between multiple users

Examples of kubernetes objects/resources that are not namespace specific (but cluster-wide resources) ie used by pods across namsepaces:

- Nodes
- PersistentVolumes (PVs) and PersistentVolumeClaims (PVCs)
- StorageClasses
- CustomResourceDefinitions (CRDs)
- ClusterRoles and ClusterRoleBindings
- ServiceAccounts
- Namespace itself: The Namespace object itself is cluster-wide and is used to provide a scope for names. While namespaces organize resources, the namespace object itself is not contained within any namespace

# Compute isolation

1.  What is **Namespace** used for?
Namespaces is “a way to divide cluster resources between multiple users”

1. What does **RBAC** do?
It provides a way to define who can do what (aka authorization) on the **Kubernetes API**

1. Different ways to apply authorization
- Use **ClusterRole** to apply authorizations at cluster level
- Use **Role** to apply authorizations at namespace level

1. by default, all pods, in all namespaces, can communicate to each other

1. Namespaces can be used to provide fair shares of resources (CPU, memory, network…) across different workloads sharing the same infrastructure

1. Namespace DOES NOT provide workload or user isolation

5. Example:

Define a role called **namespace1-admin** to be used to provide administrator access to one namespace called **namespace1**, and associate it with a group named **admin-ns1** using RoleBinding **admins-ns1-rb**

```yaml
kind: Role
apiVersion: rbac.authorization.k8s.io/v1
metadata:
  name: namespace1-admin
  namespace: namespace1
rules:
- apiGroups: ["", "extensions", "apps"] # Allows all API groups ("", "extensions", "apps").
  resources: ["*"] # Grants full access ("*" verbs) to all resources within these API groups
  verbs: ["*"]
- apiGroups: ["batch"] # Specifically allows access to resources in the batch API group ("batch").
  resources: # Grants full access ("*" verbs) to jobs and cronjobs resources within the batch API group.
  - jobs
  - cronjobs
  verbs: ["*"]
---
kind: RoleBinding
apiVersion: rbac.authorization.k8s.io/v1
metadata:
  name: admins-ns1-rb
  namespace: namespace1
subjects:
- kind: Group  # The permissions are granted to the group admins-ns1.
  name: admins-ns1
  apiGroup: rbac.authorization.k8s.io
roleRef:
  kind: Role
  name: namespace1-admin
  apiGroup: rbac.authorization.k8s.io
```

6. **AWS IAM Authenticator** for Kubernetes

This maps IAM users and roles to RBAC groups

7. Use **ResourceQuotas** to define requests and limits for CPU and Memory for Pods

```yaml
apiVersion: v1
kind: ResourceQuota
metadata:
  name: mem-cpu-demo
  namespace: namespace1
spec:
  hard:
    requests.cpu: "1"
    requests.memory: 1Gi
    limits.cpu: "2"
    limits.memory: 2Gi
```

8. Use **Pod Security Policy (PSP)** to limit access to node instance from pods

9. Specifying where to schedule a pod using **Pod Anti-Affinity**?

Example: pods in **namespace1** with labels **team: team-1** will not be scheduled on the same nodes, where other pods with **label type: monitoring** in namespace **shared-services** are already scheduled, to prevent interfering between business applications and monitoring application

```yaml
apiVersion: v1
kind: Pod
metadata:
  namespace: namespace1
  name: workload-1
  labels:
    team: "team-1"
spec:
  affinity:
    podAntiAffinity:
      requiredDuringSchedulingIgnoredDuringExecution:
      - topologyKey: "kubernetes.io/hostname"
        namespaces:
          - shared-services
        labelSelector:
          matchExpressions:
          - key: "type"
            operator: In
            values: ["monitoring"]
```

10. Allocate Pod to specific nodes with the **nodeSelectors** and **nodeAffinity**.

11. How to indicate **node instances** that are normally excluded for scheduling pods, unless specified differently?

Use **Taints and Tolerations**

Example: the cluster administrator might prefer to create a dedicate Worker Group on EKS that should be used for monitoring and administrative workloads, using a Node Group Config file such as the following:

```yaml
apiVersion: eksctl.io/v1alpha5
kind: ClusterConfig
metadata:
  name: dev-cluster
  region: eu-west-1
nodeGroups:
  - name: ng-monitoring
    instanceType: c5.8xlarge
    desiredCapacity: 2
    taints:
      monitoring: "true:NoSchedule"
```

With this taint, nodes that do not have a toleration for this taint, will not let pods to be scheduled on this Node Group.

To get a smilar kind of "**Taints and Toleration**" behavior use **AWS Fargate on EKS**. Fargate runs each pod in a VM-isolated environment without sharing resources with other pods, and eliminates the need to create or manage EC2 instances as nodes.

# References Used

1. [Multi-tenant design considerations for Amazon EKS clusters by Roberto Migli](https://aws.amazon.com/blogs/containers/multi-tenant-design-considerations-for-amazon-eks-clusters/)
