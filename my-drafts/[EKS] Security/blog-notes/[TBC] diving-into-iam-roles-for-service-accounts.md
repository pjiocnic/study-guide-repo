https://aws.amazon.com/blogs/containers/diving-into-iam-roles-for-service-accounts/

# TBC
1. Need to try this out

# Detailed steps

1. start eks cluster

```bash
eksctl create cluster \
--name eks-oidc-demo \
--region us-east-2
```

2. deploy `aws-cli` pod with `restart = false`

```yaml
cat <<EOF | kubectl apply -f -
apiVersion: v1
kind: Pod
metadata:
  name: eks-iam-test1
spec:
  containers:
    - name: my-aws-cli
      image: amazon/aws-cli:latest
      args: ['s3', 'ls']
  restartPolicy: Never
EOF
```

3. When you check on the pods you will see it error

```bash
$ kubectl get pod
NAME              READY   STATUS   RESTARTS   AGE
eks-iam-test1     0/1     Error    0          37s

$ kubectl logs eks-iam-test1
An error occurred (AccessDenied) when calling the ListBuckets operation: Access Denied
```

cloud Trail tells you the real error

<img src="./images/CloudTrail-Log.png" title="CloudTrail-Log.png" width="900"/>

```json
{
...
  "userIdentity": {
    "type": "AssumedRole",
    "principalId": "xxxx",
    "arn": "arn:aws:sts::111122223333:assumed-role/eksctl-eks-oidc-demo-nodegroup-ng-NodeInstanceRole-xxxx/xxxx",
    "accountId": "111122223333",
    "accessKeyId": "AKIAIOSFODNN7EXAMPLE",
    "sessionContext": {
      "sessionIssuer": {
        "type": "Role",
        "principalId": "xxxx",
        "arn": "arn:aws:iam::xxxx:role/eksctl-eks-oidc-demo-nodegroup-ng-NodeInstanceRole-xxxx",
        "accountId": "111122223333",
        "userName": "eksctl-eks-oidc-demo-nodegroup-ng-NodeInstanceRole-xxxx"
      },
      "webIdFederationData": {},
      "attributes": {
        "creationDate": "2021-12-04T14:54:49Z",
        "mfaAuthenticated": "false"
      },
      "ec2RoleDelivery": "2.0"
    }
  },
  "eventTime": "2021-12-04T15:09:20Z",
  "eventSource": "s3.amazonaws.com",
  "eventName": "ListBuckets",
  "awsRegion": "us-east-2",
  "sourceIPAddress": "192.0.2.1",
  "userAgent": "[aws-cli/2.4.5 Python/3.8.8 Linux/5.4.156-83.273.amzn2.x86_64 docker/x86_64.amzn.2 prompt/off command/s3.ls]",
  "errorCode": "AccessDenied",
  "errorMessage": "Access Denied",
  "requestParameters": {
    "Host": "s3.us-east-2.amazonaws.com"
  },
...
}
```

# What does a POD need to access S3 bucket

It needs credentials (secret key etc).  This can be temporary or permanent

# Different ways a Pod can get credentials

1. The SDk used by the pod would get from the container, if not found it will try assuming (fallback) an IAM Role attached to the Amazon EC2 instance and leveraging this role to try and list the S3 buckets.  However, we see an error since the role does not have permissions to access S3 bucket.  We can fix this by adding the permission to the role but the problem all pods running in the EC2 instance will get this access also which breaks the principal of least privilege ie giving more permissions than needed
2. We can also inject AWS credentials via Kubernetes Secrets or environment variables  into a container so the container does not default to the EC2 instance profile

# Kubernetes Service Accounts

Kubernetes Pods are given an identity through a Kubernetes concept called a Kubernetes Service Account (SA).

When a ServiceAccount is created a JWT Token is created in the form of Kubernetes secret.

This secret is mounted into the pod and used to authenticate with API server

```bash
kubectl get sa
```

```bash
kubectl describe sa default
```

2. Problems using this token for IAM authentication
- First, it is **only** the Kubernetes **API server** that can **validate** this token.
- Second, these Service Account tokens **do not expire**, and rotating the signing key is a difficult process

- Viewing the token (ie secret) using `jwtcli`

```bash
kubectl get secret default-token-m4tdn -o json | jq -r '.data.token' | base64 -d | jwt decode --json -
```