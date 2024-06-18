# Setting up Cloud9 for EKS development

1. Create an IAM role for your Cloud9 workspace environment

- Select `Trusted entity type -> AWS Service`
- Use case `EC2`
- Add Permissions - choose `AdministratorAccess`
- Name the role as `eks-workshop-admin`

2. Attach the IAM role `eks-workshop-admin` to the cloud9 workspace

- Thru' EC2 console select the Cloud9 instance and do `Actions / Security / Modify IAM Role`
- Choose eks-workshop-admin from the IAM Role drop down, and select Save

4. Disable Cloud9 AWS temporary credentials

* Open the "Preferences" tab in Cloud9 console
* Open the "AWS Settings" and see "AWS Managed Temporary Credentials" is "Off", if not turn it "Off"

5. Remove existing credentials file

```bash
aws cloud9 update-environment  --environment-id $C9_PID --managed-credentials-action DISABLE
rm -vf ${HOME}/.aws/credentials
```

6. Verify you are seeing the IAM role that you have attached to Cloud9 IDE

```bash
aws sts get-caller-identity --query Arn | grep eks-workshop-admin -q && echo "IAM role valid" || echo "IAM role NOT valid"
```

# Installing Tools

1. Install eksctl

```bash
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp

sudo mv -v /tmp/eksctl /usr/local/bin
```

2. Install kubectl

```bash
curl -O https://s3.us-west-2.amazonaws.com/amazon-eks/1.28.5/2024-01-04/bin/linux/amd64/kubectl

chmod +x ./kubectl

mkdir -p $HOME/bin && cp ./kubectl $HOME/bin/kubectl && export PATH=$HOME/bin:$PATH

echo 'export PATH=$HOME/bin:$PATH' >> ~/.bashrc
```

3. Install jq, envsubst (from GNU gettext utilities) and bash-completion

```bash
sudo yum -y install jq gettext bash-completion moreutils
```

4. Install yq for yaml processing

```bash
echo 'yq() {
  docker run --rm -i -v "${PWD}":/workdir mikefarah/yq "$@"
}' | tee -a ~/.bashrc && source ~/.bashrc
```

5. Install C9 to open files in cloud9

```bash
npm install -g c9
```

6. Install k9s, a Kubernetes CLI, To Manage Your Clusters

```bash
curl -sS https://webinstall.dev/k9s | bash
```

7. Verify the binaries are in the path and executable

```bash
kubectl completion bash >>  ~/.bash_completion
. /etc/profile.d/bash_completion.sh
. ~/.bash_completion
```

8. Enable kubectl bash_completion

```bash
kubectl completion bash >>  ~/.bash_completion
. /etc/profile.d/bash_completion.sh
. ~/.bash_completion
```

9. Enable some kubernetes aliases

```bash
git clone --depth 1 https://github.com/junegunn/fzf.git ~/.fzf
~/.fzf/install --all
sudo curl https://raw.githubusercontent.com/blendle/kns/master/bin/kns -o /usr/local/bin/kns && sudo chmod +x $_
sudo curl https://raw.githubusercontent.com/blendle/kns/master/bin/ktx -o /usr/local/bin/ktx && sudo chmod +x $_
echo "alias kgn='kubectl get nodes -L beta.kubernetes.io/arch -L eks.amazonaws.com/capacityType -L beta.kubernetes.io/instance-type -L eks.amazonaws.com/nodegroup -L topology.kubernetes.io/zone -L karpenter.sh/provisioner-name -L karpenter.sh/capacity-type'" | tee -a ~/.bashrc
source ~/.bashrc
```

10. Configure aws cli with your current region as default

```bash
export ACCOUNT_ID=$(aws sts get-caller-identity --output text --query Account)

TOKEN=$(curl -s -X PUT "http://169.254.169.254/latest/api/token" -H "X-aws-ec2-metadata-token-ttl-seconds: 60")

AWS_REGION=$(curl -s -H "X-aws-ec2-metadata-token: $TOKEN" -v http://169.254.169.254/latest/meta-data/placement/region 2> /dev/null)

export LAB_CLUSTER_ID=eksworkshop-eksctl
```

11. Check if AWS_REGION is set to desired region

```bash
test -n "$AWS_REGION" && echo AWS_REGION is "$AWS_REGION" || echo AWS_REGION is not set
```

12. Save these into bash_profile

```bash
echo "export ACCOUNT_ID=${ACCOUNT_ID}" | tee -a ~/.bash_profile
echo "export AWS_REGION=${AWS_REGION}" | tee -a ~/.bash_profile
echo "export LAB_CLUSTER_ID=eksworkshop-eksctl" | tee -a ~/.bash_profile
aws configure set default.region ${AWS_REGION}
aws configure get default.region
```

13. Create EKS cluster

```bash
cat << EOF > eksworkshop.yaml
apiVersion: eksctl.io/v1alpha5
kind: ClusterConfig

metadata:
  name: eksworkshop-eksctl
  region: ${AWS_REGION}
  version: "1.28"

managedNodeGroups:
- name: nodegroup
  minSize: 2
  maxSize: 3
  desiredCapacity: 3
  instanceType: m5.large
  #volumeSize: 20
  privateNetworking: true
  ssh:
    enableSsm: true
  labels: {role: workshop}
EOF

eksctl create cluster -f eksworkshop.yaml
```

14. add EKS console credentials to EKS Cluster by using below.

This will give the EKS console role to access the EKS resources

```bash
## ROLE_ARN will be "arn:aws:iam::<YOUR_ACCOUNT_ID>:role/<YOUR_CONSOLE_ROLE>"

eksctl create iamidentitymapping --cluster eksworkshop-eksctl --arn <ROLE_ARN> --group system:masters --username admin
```

15. Confirm EKS setup

```bash
kubectl get nodes
```

# Install AWS Load Balancer Controller

1. Creates NLB if Kubernetes Service resources of Type LoadBalancer - https://kubernetes.io/docs/concepts/services-networking/service/

2. Creates ALB if you create an Ingress resource,
https://kubernetes.io/docs/concepts/services-networking/ingress/