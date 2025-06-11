
[Amazon EKS Pod Identity: a new way for applications on EKS to obtain IAM credentials by George John, Ashok Srirama, and Hemanth AVS](https://aws.amazon.com/blogs/containers/amazon-eks-pod-identity-a-new-way-for-applications-on-eks-to-obtain-iam-credentials/)

https://www.ecsworkshop.com/ecs_networking/host/
https://docs.aws.amazon.com/IAM/latest/UserGuide/reference_aws-signing.html
https://docs.aws.amazon.com/sdkref/latest/guide/overview.html

# Notes

1. Amazon EKS Pod Identity agent runs in **host network mode** and gets its permissions from the **arn:aws:iam::aws:policy/AmazonEKSWorkerNodePolicy** managed policy that is **attached to the worker nodes**.