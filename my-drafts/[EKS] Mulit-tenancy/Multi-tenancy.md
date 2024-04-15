
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

Namespace **DOES NOT** provide workload or user isolation

