Example for https://aws.amazon.com/blogs/containers/leveraging-cni-custom-networking-alongside-security-groups-for-pods-in-amazon-eks/

# Demo

```bash
terraform init
terraform apply
```

2. set up CNI custom networking

```bash
# enable CNI custom networking
kubectl set env daemonset aws-node -n kube-system AWS_VPC_K8S_CNI_CUSTOM_NETWORK_CFG=true

# ENI_CONFIG_LABEL_DEF defines the worker node label that selects which ENIConfig to use for each node
# topology.kubernetes.io/zone is the label set on the managed nodes
kubectl set env daemonset aws-node -n kube-system ENI_CONFIG_LABEL_DEF=topology.kubernetes.io/zone
```

3. set up the ENIConfig custom resource configuration that points to the intra subnets

```yaml
apiVersion: crd.k8s.amazonaws.com/v1alpha1
kind: ENIConfig
metadata:
 name: "ap-southeast-2a" # name of zone
spec:
 subnet: "${subnet_a}"
 securityGroups:
 - ${NODE_SG}
```

terraform destroy