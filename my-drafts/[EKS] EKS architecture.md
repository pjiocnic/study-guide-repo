1. An EKS cluster consists of two VPCs

one VPC managed by AWS that hosts the Kubernetes control plane and a second VPC managed by customers that hosts the Kubernetes worker nodes (EC2 instances) where containers run, as well as other AWS infrastructure (like load balancers) used by the cluster

2. How do worker nodes talk to API server endpint

- either thru' public endpoint
- or through the EKS-managed elastic network interfaces (ENIs) that are placed in the subnets that you provide when you create the cluster.

3. How does the worker node which route to take - public or private ENI?

The route that worker nodes take to connect is determined by whether you have enabled or disabled the private endpoint for your cluster.

4. Does EKS provision the ENIs even when private endpoint is disabled?

Yes, since the ENI is used by the API server to run `kubectl exec` and logs

# EKS managed ENI provisioned in customer VPC

<img src="./images/eks_architecture-1.png" title="eks_architecture-1.png" width="900"/>

sequence of events -

1. EC2 instance starts. Kubelet and the Kubernetes node agent are started as part of the boot process on each instance.
2. Kubelet reaches out to the Kubernetes cluster endpoint to register the node. It connects to the public endpoint outside of the VPC or to the private endpoint within the VPC.
3. Kubelet receives API commands and sends regular status and heartbeats to the endpoint. When you query the API server (`kubectl get nodes`), you see the latest status that each node’s Kubelet has reported back to the API server.

NOTE: If the node is unable to reach the cluster endpoint, it’s unable to register with the control plane and thus unable to receive commands to start or stop pods

# Different ways to control access to API server endpoint

1. Thru' CIDR restrictions: This allow you to limit the client IP addresses that can connect to the public endpoint.
2. Access can be controlled via the console or API.

