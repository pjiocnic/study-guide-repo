
# Point to docker desktop

```javascript {.line-numbers}
# switch to docker desktop
kubectl config get-contexts
kubectl config use-context docker-desktop
```

# Helm

```javascript {.line-numbers}
[EKS] Helm git:(staging) ✗ helm list
NAME    NAMESPACE       REVISION        UPDATED STATUS  CHART   APP VERSION
```

```javascript {.line-numbers}
# Create project
helm create dummy-chart

# review files created
dummy-chart git:(staging) ✗ tree
├── Chart.yaml  # This is the main file that contains the description of our chart
├── charts # This is an optional directory that may contain sub-charts
├── templates # This is the directory where Kubernetes resources are defined as templates
│   ├── NOTES.txt
│   ├── _helpers.tpl
│   ├── deployment.yaml
│   ├── hpa.yaml
│   ├── ingress.yaml
│   ├── service.yaml
│   ├── serviceaccount.yaml
│   └── tests
│       └── test-connection.yaml
└── values.yaml # this is the file that contains the default values for our chart
```

# Docs

```javascript {.line-numbers}
# docs
https://helm.sh/docs/
# Artifact Hub
https://artifacthub.io/
```

# Search for a chart

```javascript {.line-numbers}
helm search hub mongodb
```

# Install a sample chart (mongodb from bitnami)

```javascript {.line-numbers}
# add repo ...like pom.xml's repositories
helm repo add bitnami https://charts.bitnami.com/bitnami

# install
helm install mongo-release1 bitnami/mongodb

# verify installation
helm list
NAME            NAMESPACE       REVISION        UPDATED                                 STATUS          CHART                 APP VERSION
mongo-release1  default         1               2024-04-27 09:13:33.988687 -0400 EDT    deployed        mongodb-15.1.5        7.0.8

```

# Installing different versions of charts

You can install multiple versions of MongoDB and each install is called a **release**

```javascript {.line-numbers}
helm install mongo-release2 bitnami/mongodb

 [EKS] Helm git:(staging) ✗ helm list
NAME            NAMESPACE       REVISION        UPDATED                                 STATUS          CHART                 APP VERSION
mongo-release1  default         1               2024-04-27 09:13:33.988687 -0400 EDT    deployed        mongodb-15.1.5        7.0.8
mongo-release2  default         1               2024-04-27 09:24:14.798575 -0400 EDT    deployed        mongodb-15.1.5        7.0.8

```

# Installing a chart also deploys the pods

```javascript {.line-numbers}
[EKS] Helm git:(staging) ✗ kubectl get po
NAME                                      READY   STATUS    RESTARTS   AGE
mongo-release1-mongodb-5477458955-w5xvv   1/1     Running   0          77s
➜  [EKS] Helm git:(staging) ✗ kubectl get deploy
NAME                     READY   UP-TO-DATE   AVAILABLE   AGE
mongo-release1-mongodb   1/1     1            1           90s

```

# Clean up

```javascript {.line-numbers}
helm uninstall mongo-release1
helm uninstall mongo-release2
```

# End-End demo using React and Java

src: https://medium.com/bb-tutorials-and-thoughts/deploying-react-with-java-backend-on-aws-eks-using-helm-3937023151a6

```javascript {.line-numbers}
// clone the project
git clone https://github.com/bbachi/react-java-gke.git


```

# References

1. https://medium.com/bb-tutorials-and-thoughts/how-to-get-started-with-helm-b3babb30611f
1. https://medium.com/bb-tutorials-and-thoughts/deploying-react-with-java-backend-on-aws-eks-using-helm-3937023151a6
1. https://medium.com/bb-tutorials-and-thoughts/how-to-deploy-java-apis-on-aws-eks-using-helm-1c7bec29c4c7
1. [Crafting Seamless Deployments: Docker, Kubernetes, and Helm Unite for Java Spring Boot Applications byShreelakshmi Javagal](https://medium.com/@akshatajavagal99/deploying-a-java-application-onto-a-kubernetes-cluster-using-docker-f5653c0161e4)
1. [How To Deploy Java APIs on AWS EKS using HELM by Bhargav Bachina](https://medium.com/bb-tutorials-and-thoughts/how-to-deploy-java-apis-on-aws-eks-using-helm-1c7bec29c4c7)