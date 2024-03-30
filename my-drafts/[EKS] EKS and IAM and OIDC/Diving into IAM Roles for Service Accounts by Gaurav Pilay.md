https://aws.amazon.com/blogs/containers/diving-into-iam-roles-for-service-accounts/

# Useful tools

1. jwt-cli: for decoding JSON Web Tokens
2. jq: parsing json

# Demo

1. create cluster

Create in us-east-2 since us-east-1 fails

```javascript {.line-numbers}
eksctl create cluster \
--name eks-oidc-demo \
--region us-east-2

2024-03-29 20:08:35 [ℹ]  eksctl version 0.167.0
2024-03-29 20:08:35 [ℹ]  using region us-east-2
2024-03-29 20:08:36 [ℹ]  setting availability zones to [us-east-2a us-east-2b us-east-2c]
2024-03-29 20:08:36 [ℹ]  subnets for us-east-2a - public:192.168.0.0/19 private:192.168.96.0/19
2024-03-29 20:08:36 [ℹ]  subnets for us-east-2b - public:192.168.32.0/19 private:192.168.128.0/19
2024-03-29 20:08:36 [ℹ]  subnets for us-east-2c - public:192.168.64.0/19 private:192.168.160.0/19
2024-03-29 20:08:36 [ℹ]  nodegroup "ng-36d0f950" will use "" [AmazonLinux2/1.27]
2024-03-29 20:08:36 [ℹ]  using Kubernetes version 1.27
2024-03-29 20:08:36 [ℹ]  creating EKS cluster "eks-oidc-demo" in "us-east-2" region with managed nodes
2024-03-29 20:08:36 [ℹ]  will create 2 separate CloudFormation stacks for cluster itself and the initial managed nodegroup
2024-03-29 20:08:36 [ℹ]  if you encounter any issues, check CloudFormation console or try 'eksctl utils describe-stacks --region=us-east-2 --cluster=eks-oidc-demo'
2024-03-29 20:08:36 [ℹ]  Kubernetes API endpoint access will use default of {publicAccess=true, privateAccess=false} for cluster "eks-oidc-demo" in "us-east-2"
2024-03-29 20:08:36 [ℹ]  CloudWatch logging will not be enabled for cluster "eks-oidc-demo" in "us-east-2"
2024-03-29 20:08:36 [ℹ]  you can enable it with 'eksctl utils update-cluster-logging --enable-types={SPECIFY-YOUR-LOG-TYPES-HERE (e.g. all)} --region=us-east-2 --cluster=eks-oidc-demo'
2024-03-29 20:08:36 [ℹ]
2 sequential tasks: { create cluster control plane "eks-oidc-demo",
    2 sequential sub-tasks: {
        wait for control plane to become ready,
        create managed nodegroup "ng-36d0f950",
    }
}
2024-03-29 20:08:36 [ℹ]  building cluster stack "eksctl-eks-oidc-demo-cluster"
2024-03-29 20:08:36 [ℹ]  deploying stack "eksctl-eks-oidc-demo-cluster"
2024-03-29 20:09:06 [ℹ]  waiting for CloudFormation stack "eksctl-eks-oidc-demo-cluster"
2024-03-29 20:19:40 [ℹ]  building managed nodegroup stack "eksctl-eks-oidc-demo-nodegroup-ng-36d0f950"
2024-03-29 20:19:40 [ℹ]  deploying stack "eksctl-eks-oidc-demo-nodegroup-ng-36d0f950"
2024-03-29 20:19:41 [ℹ]  waiting for CloudFormation stack "eksctl-eks-oidc-demo-nodegroup-ng-36d0f950"
2024-03-29 20:22:55 [ℹ]  waiting for the control plane to become ready
2024-03-29 20:22:58 [✔]  saved kubeconfig as "/Users/pjoisha/.kube/config"
2024-03-29 20:22:58 [ℹ]  no tasks
2024-03-29 20:22:58 [✔]  all EKS cluster resources for "eks-oidc-demo" have been created
2024-03-29 20:22:58 [ℹ]  nodegroup "ng-36d0f950" has 2 node(s)
2024-03-29 20:22:58 [ℹ]  node "ip-192-168-1-169.us-east-2.compute.internal" is ready
2024-03-29 20:22:58 [ℹ]  node "ip-192-168-53-243.us-east-2.compute.internal" is ready
2024-03-29 20:22:58 [ℹ]  waiting for at least 2 node(s) to become ready in "ng-36d0f950"
2024-03-29 20:22:58 [ℹ]  nodegroup "ng-36d0f950" has 2 node(s)
2024-03-29 20:22:58 [ℹ]  node "ip-192-168-1-169.us-east-2.compute.internal" is ready
2024-03-29 20:22:58 [ℹ]  node "ip-192-168-53-243.us-east-2.compute.internal" is ready
2024-03-29 20:23:02 [ℹ]  kubectl command should work with "/Users/pjoisha/.kube/config", try 'kubectl get nodes'
2024-03-29 20:23:02 [✔]  EKS cluster "eks-oidc-demo" in "us-east-2" region is ready
```

```javascript {.line-numbers}
kubectl config current-context
2024iamadmin@eks-oidc-demo.us-east-2.eksctl.io
```

2. create pod with restart policy never

```javascript {.line-numbers}
cat <<EOF | kubectl apply -f -
apiVersion: v1
kind: Pod
metadata:
  name: eks-iam-test1
spec:
  containers:
    - name: my-aws-cli
      image: amazon/aws-cli:latest
      args: ['s3', 'ls']         # execute aws s3 list
  restartPolicy: Never
EOF
```

3. See if the pod is running

```javascript {.line-numbers}
[EKS] EKS and IAM and OIDC git:(staging) ✗ kubectl get pod
NAME            READY   STATUS    RESTARTS   AGE
eks-iam-test1   1/1     Running   0          9s
➜  [EKS] EKS and IAM and OIDC git:(staging) ✗ kubectl get pod
NAME            READY   STATUS   RESTARTS   AGE
eks-iam-test1   0/1     Error    0          19s

```

4. Inspect logs to see why it failed

```javascript {.line-numbers}
kubectl logs eks-iam-test1

An error occurred (AccessDenied) when calling the ListBuckets operation: Access Denied
```

5. Inspecting cloudtrail after filtering by "List Buckets"

```json
{
    "eventVersion": "1.09",
    "userIdentity": {
        "type": "AssumedRole",
        "principalId": "AROARU3ZKEC4HPDV4HGOU:i-09330a7fbc141a62c", # pod workload in ec2 instance
        "arn": "arn:aws:sts::113534181560:assumed-role/eksctl-eks-oidc-demo-nodegroup-ng--NodeInstanceRole-rL7RY47katu4/i-09330a7fbc141a62c", # The fallback Ec2 Role ARN that pod workload assumes
        "accountId": "113534181560",
        "accessKeyId": "ASIARU3ZKEC4OH3YH77L", # The access key ID used to make the API call
        "sessionContext": {
            "sessionIssuer": { # Provides information about the entity that granted the role to the principal
                "type": "Role",
                "principalId": "AROARU3ZKEC4HPDV4HGOU",
                "arn": "arn:aws:iam::113534181560:role/eksctl-eks-oidc-demo-nodegroup-ng--NodeInstanceRole-rL7RY47katu4", # The ARN of the IAM role that issued the session.
                "accountId": "113534181560",
                "userName": "eksctl-eks-oidc-demo-nodegroup-ng--NodeInstanceRole-rL7RY47katu4" # The name of the IAM role
            },
            "attributes": {
                "creationDate": "2024-03-30T00:21:04Z",
                "mfaAuthenticated": "false"
            },
            "ec2RoleDelivery": "2.0"
        }
    },
    "eventTime": "2024-03-30T00:29:59Z",
    "eventSource": "s3.amazonaws.com",
    "eventName": "ListBuckets",
    "awsRegion": "us-east-2",
    "sourceIPAddress": "3.144.201.55",
    "userAgent": "[aws-cli/2.15.34 Python/3.11.8 Linux/5.10.210-201.852.amzn2.x86_64 docker/x86_64.amzn.2 prompt/off command/s3.ls]",
    "errorCode": "AccessDenied",
    "errorMessage": "Access Denied",
    "requestParameters": {
        "Host": "s3.us-east-2.amazonaws.com"
    },
    "responseElements": null,
    "additionalEventData": {
        "SignatureVersion": "SigV4",
        "CipherSuite": "ECDHE-RSA-AES128-GCM-SHA256",
        "bytesTransferredIn": 0,
        "AuthenticationMethod": "AuthHeader",
        "x-amz-id-2": "0eBxQN/fHZHmuj6T1tzOm+BR1O1OZxfY5kjJy/OEoWN5Jik9s+v+FRIML3vFB6LQK+yRRpc9Bak=",
        "bytesTransferredOut": 243
    },
    "requestID": "YJQA03C4P34TSBN6",
    "eventID": "c258b2d7-b07d-4017-bf98-107b45288cff",
    "readOnly": true,
    "eventType": "AwsApiCall",
    "managementEvent": true,
    "recipientAccountId": "113534181560",
    "eventCategory": "Management",
    "tlsDetails": {
        "tlsVersion": "TLSv1.2",
        "cipherSuite": "ECDHE-RSA-AES128-GCM-SHA256",
        "clientProvidedHostHeader": "s3.us-east-2.amazonaws.com"
    }
}
```

**Reason for failure:** Under the `userIdentity` section of the output, you can see that our workload running in the Kubernetes Pod is assuming an IAM Role attached to the Amazon EC2 instance and leveraging this role to try and list the S3 buckets. This is because no other AWS credentials were found in the container, so the SDK fell back to the IAM metadata server

As the IAM role within the EC2 Instance Profile does not have necessary permissions to list the buckets, the command received an “Access Denied” error

**Possible fix:** attach additional permissions to the EC2 instance profile.

**Why fix is bad:** this violates a key security principle, the principle of least privilege. This additional permission would be at the EC2 Node level, not at the Kubernetes Pod level. Therefore, all Pods running on that node would gain access to our S3 buckets. We want to restrict this permission to the Pod level.

**alternative solution:** Injecting AWS credentials via Kubernetes Secrets or environment variables <u>would not be secure</u>, and the user would have to <u>manage the lifecycle of these credentials</u>

# Kubernetes Service Accounts

In kubernetes there are 2 types of users (ie identities) - normal user and service accounts

Pods will use service accounts as an identity when talking to API server

When a Service Account is created, a **JWT token** is automatically created as a Kubernetes Secret. This Secret can then be mounted into Pods and used by that Service Account to authenticate to the Kubernetes API Server.

```bash
kubectl get secret default-token-m4tdn -o json | jq -r '.data.token' | base64 -d | jwt decode --json -
```

```bash
kubectl get pods --field-selector=spec.serviceAccountName=default -o yaml
apiVersion: v1
items:
- apiVersion: v1
  kind: Pod
  metadata:
    annotations:
      kubectl.kubernetes.io/last-applied-configuration: |
        {"apiVersion":"v1","kind":"Pod","metadata":{"annotations":{},"name":"eks-iam-test1","namespace":"default"},"spec":{"containers":[{"args":["s3","ls"],"image":"amazon/aws-cli:latest","name":"my-aws-cli"}],"restartPolicy":"Never"}}
    creationTimestamp: "2024-03-30T00:29:51Z"
    name: eks-iam-test1
    namespace: default
    resourceVersion: "2787"
    uid: 04e44bea-d10e-44da-b2a4-31f6902cf51f
  spec:
    containers:
    - args:
      - s3
      - ls
      image: amazon/aws-cli:latest
      imagePullPolicy: Always
      name: my-aws-cli
      resources: {}
      terminationMessagePath: /dev/termination-log
      terminationMessagePolicy: File
      volumeMounts:
      - mountPath: /var/run/secrets/kubernetes.io/serviceaccount
        name: kube-api-access-fmzxd
        readOnly: true
    dnsPolicy: ClusterFirst
    enableServiceLinks: true
    nodeName: ip-192-168-53-243.us-east-2.compute.internal
    preemptionPolicy: PreemptLowerPriority
    priority: 0
    restartPolicy: Never
    schedulerName: default-scheduler
    securityContext: {}
    serviceAccount: default
    serviceAccountName: default
    terminationGracePeriodSeconds: 30
    tolerations:
    - effect: NoExecute
      key: node.kubernetes.io/not-ready
      operator: Exists
      tolerationSeconds: 300
    - effect: NoExecute
      key: node.kubernetes.io/unreachable
      operator: Exists
      tolerationSeconds: 300
    volumes:
    - name: kube-api-access-fmzxd
      projected:
        defaultMode: 420
        sources:
        - serviceAccountToken:
            expirationSeconds: 3607
            path: token
        - configMap:
            items:
            - key: ca.crt
              path: ca.crt
            name: kube-root-ca.crt
        - downwardAPI:
            items:
            - fieldRef:
                apiVersion: v1
                fieldPath: metadata.namespace
              path: namespace
  status:
    conditions:
    - lastProbeTime: null
      lastTransitionTime: "2024-03-30T00:29:51Z"
      status: "True"
      type: Initialized
    - lastProbeTime: null
      lastTransitionTime: "2024-03-30T00:30:02Z"
      reason: PodFailed
      status: "False"
      type: Ready
    - lastProbeTime: null
      lastTransitionTime: "2024-03-30T00:30:02Z"
      reason: PodFailed
      status: "False"
      type: ContainersReady
    - lastProbeTime: null
      lastTransitionTime: "2024-03-30T00:29:51Z"
      status: "True"
      type: PodScheduled
    containerStatuses:
    - containerID: containerd://8ebe2be20f24e0aa955d92d8f1ba6ef4f6131de2cbd64aa9d51607b3ad3327db
      image: docker.io/amazon/aws-cli:latest
      imageID: docker.io/amazon/aws-cli@sha256:015921f475b78f20f9e65eb301c3c5155b6b463620feb6d9137cbddb1f4a07bc
      lastState: {}
      name: my-aws-cli
      ready: false
      restartCount: 0
      started: false
      state:
        terminated:
          containerID: containerd://8ebe2be20f24e0aa955d92d8f1ba6ef4f6131de2cbd64aa9d51607b3ad3327db
          exitCode: 254
          finishedAt: "2024-03-30T00:29:59Z"
          reason: Error
          startedAt: "2024-03-30T00:29:58Z"
    hostIP: 192.168.53.243
    phase: Failed
    podIP: 192.168.44.155
    podIPs:
    - ip: 192.168.44.155
    qosClass: BestEffort
    startTime: "2024-03-30T00:29:51Z"
kind: List
metadata:
  resourceVersion: ""
```

```bash
cat <<EOF | kubectl apply -f -
apiVersion: v1
kind: Pod
metadata:
  name: eks-iam-test2
spec:
  containers:
    - name: my-aws-cli
      image: amazon/aws-cli:latest
      command: ['sleep', '36000']
  restartPolicy: Never
EOF
```

```bash
brew tap mike-engel/jwt-cli
brew install jwt-cli
brew install jq
```

```bash
$ SA_TOKEN=$(kubectl exec -it eks-iam-test2 -- cat /var/run/secrets/kubernetes.io/serviceaccount/token)
# $ jwt decode $SA_TOKEN --json --iso8601
[EKS] EKS and IAM and OIDC git:(staging) ✗ jwt decode $SA_TOKEN --json
{
  "header": {
    "alg": "RS256",
    "kid": "37e17b7dd642a1d5f1dfcef2cf3de8712f48a0ee"
  },
  "payload": {
    "aud": [
      "https://kubernetes.default.svc"
    ],
    "exp": 1743298067,
    "iat": 1711762067,
    "iss": "https://oidc.eks.us-east-2.amazonaws.com/id/8AF2D61DF335B97C72A2ADEA1614706B",
    "kubernetes.io": {
      "namespace": "default",
      "pod": {
        "name": "eks-iam-test2",
        "uid": "3a5613ae-8106-429e-9f5c-a1e0680ccf44"
      },
      "serviceaccount": {
        "name": "default",
        "uid": "91878549-9996-48a4-a83a-3d6693773331"
      },
      "warnafter": 1711765674
    },
    "nbf": 1711762067,
    "sub": "system:serviceaccount:default:default"
  }
}
```

```bash
eksctl utils associate-iam-oidc-provider --region=us-east-2 --cluster=eks-oidc-demo --approve
2024-03-29 21:35:02 [ℹ]  will create IAM Open ID Connect provider for cluster "eks-oidc-demo" in "us-east-2"
2024-03-29 21:35:02 [✔]  created IAM Open ID Connect provider for cluster "eks-oidc-demo" in "us-east-2"
```

# Create new service account

```bash
eksctl create iamserviceaccount \
  --name my-sa \
  --region=us-east-2 \
  --namespace default \
  --cluster eks-oidc-demo \
  --approve \
  --attach-policy-arn $(aws iam list-policies --query 'Policies[?PolicyName==`AmazonS3ReadOnlyAccess`].Arn' --output text)

2024-03-29 21:46:36 [ℹ]  1 iamserviceaccount (default/my-sa) was included (based on the include/exclude rules)
2024-03-29 21:46:36 [!]  serviceaccounts that exist in Kubernetes will be excluded, use --override-existing-serviceaccounts to override
2024-03-29 21:46:36 [ℹ]  1 task: {
    2 sequential sub-tasks: {
        create IAM role for serviceaccount "default/my-sa",
        create serviceaccount "default/my-sa",
    } }2024-03-29 21:46:36 [ℹ]  building iamserviceaccount stack "eksctl-eks-oidc-demo-addon-iamserviceaccount-default-my-sa"
2024-03-29 21:46:37 [ℹ]  deploying stack "eksctl-eks-oidc-demo-addon-iamserviceaccount-default-my-sa"
2024-03-29 21:46:37 [ℹ]  waiting for CloudFormation stack "eksctl-eks-oidc-demo-addon-iamserviceaccount-default-my-sa"
2024-03-29 21:47:07 [ℹ]  waiting for CloudFormation stack "eksctl-eks-oidc-demo-addon-iamserviceaccount-default-my-sa"
2024-03-29 21:47:07 [ℹ]  created serviceaccount "default/my-sa"

```

`eksctl create iamserviceaccount` command creates:
- A Kubernetes Service Account
- An IAM role with the specified IAM policy
- A trust policy on that IAM role

kubectl describe sa my-sa
Name:                my-sa
Namespace:           default
Labels:              app.kubernetes.io/managed-by=eksctl
Annotations:         eks.amazonaws.com/role-arn: arn:aws:iam::113534181560:role/eksctl-eks-oidc-demo-addon-iamserviceaccount--Role1-4JIbFaEdQaXO
Image pull secrets:  <none>
Mountable secrets:   <none>
Tokens:              <none>
Events:              <none>

# Appendix

1. aws-cli image/pod usage