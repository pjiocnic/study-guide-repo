.
├── api-gtwy-private-integrations
│   ├── apigateway-vpcendpoints
│   │   ├── Building private cross-account APIs using Amazon API Gateway and AWS PrivateLink _ AWS Compute Blog.pdf
│   │   ├── CODE_OF_CONDUCT.md
│   │   ├── CONTRIBUTING.md
│   │   ├── LICENSE
│   │   ├── README.md
│   │   ├── VPC-blog.png
│   │   ├── apigw-vpce-helpers
│   │   │   ├── README.md
│   │   │   ├── apigw_vpce_helpers
│   │   │   │   ├── __init__.py
│   │   │   │   ├── custom_resource
│   │   │   │   │   ├── __init__.py
│   │   │   │   │   ├── cfnresponse.py
│   │   │   │   │   └── handler.py
│   │   │   │   ├── helpers.py
│   │   │   │   └── vpce_helpers.py
│   │   │   ├── setup.cfg
│   │   │   └── setup.py
│   │   ├── architecture.png
│   │   ├── ec2-provider
│   │   │   ├── Makefile
│   │   │   ├── README.md
│   │   │   ├── app.py
│   │   │   ├── cdk.json
│   │   │   ├── ec2_provider
│   │   │   │   ├── __init__.py
│   │   │   │   ├── todo_app_stack.py
│   │   │   │   └── vpc_stack.py
│   │   │   ├── requirements.txt
│   │   │   ├── setup.py
│   │   │   ├── source.bat
│   │   │   └── user-data.sh
│   │   ├── ecs-provider
│   │   │   ├── Makefile
│   │   │   ├── README.md
│   │   │   ├── app.py
│   │   │   ├── bin
│   │   │   │   └── create-ecr-repo.sh
│   │   │   ├── cdk.json
│   │   │   ├── ecs_provider
│   │   │   │   ├── __init__.py
│   │   │   │   ├── ecs_provider_stack.py
│   │   │   │   └── vpc_stack.py
│   │   │   ├── image
│   │   │   │   ├── Dockerfile
│   │   │   │   └── server.go
│   │   │   ├── requirements.txt
│   │   │   ├── setup.py
│   │   │   └── source.bat
│   │   ├── global-apigw
│   │   │   ├── Makefile
│   │   │   ├── README.md
│   │   │   ├── app.py
│   │   │   ├── cdk.json
│   │   │   ├── global_apigw
│   │   │   │   ├── __init__.py
│   │   │   │   └── global_apigw_stack.py
│   │   │   ├── requirements.txt
│   │   │   ├── setup.py
│   │   │   └── source.bat
│   │   ├── my-readme.md
│   │   └── requirements.txt
│   ├── aws-apigw-http-api-private--integrations
│   │   ├── Architecting for scale with Amazon API Gateway private integrations _ AWS Compute Blog.pdf
│   │   ├── CODE_OF_CONDUCT.md
│   │   ├── CONTRIBUTING.md
│   │   ├── LICENSE
│   │   ├── README.md
│   │   ├── my-readme.md
│   │   └── templates
│   │       ├── APIGW-HTTP-private-integration-ALB-ecs.yml
│   │       ├── APIGW-HTTP-private-integration-AWS-Cloudmap-ecs.yml
│   │       └── APIGW-HTTP-private-integration-NLB-ecs.yml
│   └── http-api-aws-fargate-cdk
│       ├── Building HTTP API-based services using Amazon API Gateway, AWS PrivateLink and AWS Fargate _ Containers.pdf
│       ├── CODE_OF_CONDUCT.md
│       ├── CONTRIBUTING.md
│       ├── LICENSE
│       ├── README.md
│       ├── cdk
│       │   └── singleAccount
│       │       ├── README.md
│       │       ├── bin
│       │       │   └── app.ts
│       │       ├── cdk.json
│       │       ├── jest.config.js
│       │       ├── lib
│       │       │   ├── fargate-vpclink-stack.ts
│       │       │   └── httpApi-stack.ts
│       │       ├── package.json
│       │       └── tsconfig.json
│       ├── my-readme.md
│       └── src
│           ├── author-service
│           │   ├── Dockerfile
│           │   ├── healthcheck.html
│           │   ├── index.js
│           │   └── package.json
│           └── book-service
│               ├── Dockerfile
│               ├── healthcheck.html
│               ├── index.js
│               └── package.json
├── aws-cdk-quickstart-lambda-insights
│   ├── CODE_OF_CONDUCT.md
│   ├── CONTRIBUTING.md
│   ├── Introducing CloudWatch Lambda Insights _ AWS Cloud Operations & Migrations Blog.pdf
│   ├── LICENSE
│   ├── README.md
│   ├── bin
│   │   └── service_test.ts
│   ├── cdk.json
│   ├── jest.config.js
│   ├── lib
│   │   ├── lambda_cpu_service.ts
│   │   ├── lambda_filesystem_service.ts
│   │   ├── lambda_main_service.ts
│   │   ├── lambda_memory_service.ts
│   │   └── service_test-stack.ts
│   ├── my-readme.md
│   ├── package-lock.json
│   ├── package.json
│   ├── resources
│   │   ├── lambda_cpu.js
│   │   ├── lambda_filesystem.js
│   │   ├── lambda_main.js
│   │   ├── lambda_memory.js
│   │   ├── package-lock.json
│   │   └── package.json
│   ├── test
│   │   └── service_test.test.ts
│   └── tsconfig.json
├── documentdb
│   └── amazon-documentdb-integration-with-application-autoscaler
│       ├── Amazon DocumentDB (with MongoDB compatibility) read autoscaling _ AWS Database Blog.pdf
│       ├── code
│       │   ├── CODE_OF_CONDUCT.md
│       │   ├── CONTRIBUTING.md
│       │   ├── LICENSE
│       │   ├── README.md
│       │   ├── cfn
│       │   │   ├── autoscaler.yaml
│       │   │   ├── docdb.yaml
│       │   │   ├── jumphost.yaml
│       │   │   ├── main.yaml
│       │   │   └── network.yaml
│       │   ├── lambda
│       │   │   ├── index_get.py
│       │   │   ├── index_patch.py
│       │   │   ├── lambda-get.zip
│       │   │   └── lambda-patch.zip
│       │   └── scripts
│       │       ├── create.sh
│       │       ├── register.py
│       │       └── zip-lambda.sh
│       ├── my-readme.md
│       └── summary.md
├── dynamo-lambda-blog
│   ├── Integrating Amazon ElastiCache with other AWS services_ The serverless way _ AWS Database Blog.pdf
│   ├── Makefile
│   ├── README.md
│   ├── dynamo-go
│   │   ├── go.mod
│   │   └── main.go
│   ├── my-readme.md
│   └── template.yaml
├── ec2-scaling
│   ├── sqs-ec2-spot-asg
│   │   ├── Running Cost-effective queue workers with Amazon SQS and Amazon EC2 Spot Instances _ AWS Compute Blog.pdf
│   │   ├── awslogs.conf
│   │   ├── convert-worker.conf
│   │   ├── convert-worker.sh
│   │   ├── my-readme.md
│   │   ├── spot-instance-interruption-notice-handler.conf
│   │   ├── spot-instance-interruption-notice-handler.sh
│   │   ├── sqs-ec2-spot-asg.yaml
│   │   └── user-data.sh
│   └── sqs-ec2-spot-fleet-autoscaling
│       ├── awslogs.conf
│       ├── convert-worker.conf
│       ├── convert-worker.sh
│       ├── my-readme.md
│       ├── spot-instance-interruption-notice-handler.conf
│       ├── spot-instance-interruption-notice-handler.sh
│       ├── sqs-ec2-spot-fleet-autoscaling.yaml
│       └── user-data.sh
├── eks
│   ├── agtwy-vpclink-nlb-eks-using-helm
│   │   ├── Integrate Amazon API Gateway with Amazon EKS _ Containers.pdf
│   │   └── my-readme.md
│   ├── amazon-ecr-repository-compliance-webhook
│   │   ├── Building serverless admission webhooks for Kubernetes with AWS SAM _ Containers.pdf
│   │   ├── CODE_OF_CONDUCT.md
│   │   ├── CONTRIBUTING.md
│   │   ├── LICENSE
│   │   ├── Makefile
│   │   ├── NOTICE
│   │   ├── README.md
│   │   ├── THIRD-PARTY-LICENSES
│   │   ├── buildspec.yml
│   │   ├── deploy
│   │   │   ├── mydeployment.yaml
│   │   │   └── validatingwebhook.yaml
│   │   ├── go.mod
│   │   ├── go.sum
│   │   ├── main.go
│   │   ├── main_test.go
│   │   ├── my-readme.md
│   │   ├── pkg
│   │   │   ├── function
│   │   │   │   ├── container.go
│   │   │   │   ├── ecr.go
│   │   │   │   └── middleware.go
│   │   │   └── webhook
│   │   │       ├── parse_test.go
│   │   │       ├── plugin.go
│   │   │       ├── request.go
│   │   │       └── response.go
│   │   ├── screenshots
│   │   │   └── architecture.png
│   │   ├── scripts
│   │   │   └── publish.sh
│   │   ├── template.yaml
│   │   └── testdata
│   │       └── testdata.go
│   ├── amazon-ecs-fluent-bit-kinesis-firehose
│   │   ├── CODE_OF_CONDUCT.md
│   │   ├── CONTRIBUTING.md
│   │   ├── Centralized Container Logging with Fluent Bit.docx
│   │   ├── LICENSE
│   │   ├── NOTICE
│   │   ├── README.md
│   │   ├── ecs
│   │   │   ├── Dockerfile
│   │   │   ├── ecs-fluent-bit-daemonset.yml
│   │   │   ├── enable-fluent-log-driver.sh
│   │   │   ├── fluent-bit.conf
│   │   │   ├── load-gen-ecs.sh
│   │   │   ├── nginx-task-definition.json
│   │   │   └── parsers.conf
│   │   ├── eks
│   │   │   ├── eks-fluent-bit-configmap.yaml
│   │   │   ├── eks-fluent-bit-daemonset-policy.json
│   │   │   ├── eks-fluent-bit-daemonset-rbac.yaml
│   │   │   ├── eks-fluent-bit-daemonset.yaml
│   │   │   ├── eks-nginx-app.yaml
│   │   │   └── load-gen-eks.sh
│   │   ├── log-analysis
│   │   │   ├── create-consolidated-view.sql
│   │   │   ├── create-ecs-table.sql
│   │   │   ├── create-eks-table.sql
│   │   │   ├── firehose-delivery-policy.json
│   │   │   ├── firehose-policy.json
│   │   │   ├── select-all.sql
│   │   │   └── select-top-10.sql
│   │   └── my-readme.md
│   ├── amazon-eks-example-for-stateful-java-service
│   │   ├── CODE_OF_CONDUCT.md
│   │   ├── CONTRIBUTING.md
│   │   ├── Dockerfile
│   │   ├── Field Notes_ Running a Stateful Java Service on Amazon EKS _ AWS Architecture Blog.pdf
│   │   ├── LICENSE
│   │   ├── README.md
│   │   ├── cloudformation
│   │   │   ├── buildbucket.template.yaml
│   │   │   ├── eks.template.yaml
│   │   │   ├── elasticache.template.yaml
│   │   │   ├── main.template.yaml
│   │   │   ├── network.template.yaml
│   │   │   └── securitygroups.template.yaml
│   │   ├── deploy.sh
│   │   ├── destroy.sh
│   │   ├── img
│   │   │   ├── EKS_java_blog_post.drawio
│   │   │   ├── EKS_java_blog_post.jpg
│   │   │   └── EKS_java_blog_post.svg
│   │   ├── init.sh
│   │   ├── k8s-resources
│   │   │   ├── config-map.yaml
│   │   │   ├── deployment.yaml
│   │   │   └── service.yaml
│   │   ├── my-readme.md
│   │   ├── pom.xml
│   │   └── src
│   │       └── main
│   │           ├── java
│   │           │   └── com
│   │           │       └── amazon
│   │           │           └── aws
│   │           │               ├── ApplicationConfiguration.java
│   │           │               ├── Config.java
│   │           │               ├── SpringBootSessionApplication.java
│   │           │               └── controller
│   │           │                   └── SessionController.java
│   │           └── resources
│   │               ├── application.yml
│   │               └── templates
│   │                   └── index.html
│   ├── centralized-logging-using-firehose-VISIT
│   ├── containers-blog-maelstrom-CURATE
│   │   ├── A deeper look at Ingress Sharing and Target Group Binding in AWS Load Balancer Controller _ Containers.pdf
│   │   ├── CODE_OF_CONDUCT.md
│   │   ├── CONTRIBUTING.md
│   │   ├── CloudFormation
│   │   │   └── wordpress-ecs-fargate.yaml
│   │   ├── EFS-Jenkins
│   │   │   ├── create-efs-fs.sh
│   │   │   └── create-env.sh
│   │   ├── LICENSE
│   │   ├── README.md
│   │   ├── aws-cdk-eks-app-alarms-to-slack
│   │   │   ├── README.md
│   │   │   ├── bin
│   │   │   │   └── cluster-blueprint.ts
│   │   │   ├── bootstrap-env.sh
│   │   │   ├── cdk.json
│   │   │   ├── cleanup.sh
│   │   │   ├── deploy-sam-app.sh
│   │   │   ├── format_display.sh
│   │   │   ├── jest.config.js
│   │   │   ├── package.json
│   │   │   ├── templates
│   │   │   │   ├── cloudwatch-to-slack-function.zip
│   │   │   │   ├── cluster-403-alarm.json
│   │   │   │   ├── cluster-403-metric-filter.json
│   │   │   │   ├── sam-template.yaml
│   │   │   │   ├── sample-app-400-alarm.json
│   │   │   │   ├── sample-app-metric-filter.json
│   │   │   │   ├── sample-app.yaml
│   │   │   │   └── test-event.json
│   │   │   └── tsconfig.json
│   │   ├── aws-cdk-eks-multi-region-skeleton
│   │   │   ├── README.md
│   │   │   ├── bin
│   │   │   │   └── multi-cluster-ts.ts
│   │   │   ├── cdk.json
│   │   │   ├── jest.config.js
│   │   │   ├── lib
│   │   │   │   ├── cicd-stack.ts
│   │   │   │   ├── cluster-stack.ts
│   │   │   │   └── container-stack.ts
│   │   │   ├── package.json
│   │   │   ├── test
│   │   │   │   └── multi-cluster-ts.test.ts
│   │   │   ├── tsconfig.json
│   │   │   ├── utils
│   │   │   │   ├── buildimage
│   │   │   │   │   ├── Dockerfile
│   │   │   │   │   └── entrypoint.sh
│   │   │   │   ├── buildspecs.ts
│   │   │   │   └── read-file.ts
│   │   │   ├── yaml-common
│   │   │   │   └── 00_namespaces.yaml
│   │   │   ├── yaml-eu-west-2
│   │   │   │   └── 00_ap_nginx.yaml
│   │   │   ├── yaml-us-east-2
│   │   │   │   └── 00_ap_nginx.yaml
│   │   │   └── yaml-us-west-2
│   │   │       └── 00_us_nginx.yaml
│   │   ├── aws-cdk-multi-region-sample-app
│   │   │   ├── Dockerfile
│   │   │   ├── README.md
│   │   │   ├── app-deployment.yaml
│   │   │   └── app.py
│   │   ├── aws-lb-controller-blog
│   │   │   ├── ingress-grouping
│   │   │   │   ├── blue-green-app.yaml
│   │   │   │   ├── blue-green-ingress.yaml
│   │   │   │   ├── orange-purple-app.yaml
│   │   │   │   └── orange-purple-ingress.yaml
│   │   │   └── target-grp-binding
│   │   │       ├── black-app-tgb.yaml
│   │   │       ├── black-app.yaml
│   │   │       ├── red-app-tgb.yaml
│   │   │       └── red-app.yaml
│   │   ├── batch-processing-with-k8s
│   │   │   ├── LICENSE
│   │   │   ├── README.md
│   │   │   ├── bin
│   │   │   │   └── index.ts
│   │   │   ├── cdk.json
│   │   │   ├── lib
│   │   │   │   └── index.ts
│   │   │   ├── package.json
│   │   │   └── payload
│   │   └── my-readme.md
│   ├── crossplane
│   │   ├── crossplane-on-eks
│   │   │   ├── ADOPTERS.md
│   │   │   ├── CODEOWNERS
│   │   │   ├── CODE_OF_CONDUCT.md
│   │   │   ├── CONTRIBUTING.md
│   │   │   ├── LICENSE
│   │   │   ├── NOTICE
│   │   │   ├── README.md
│   │   │   ├── bootstrap
│   │   │   │   ├── eksctl
│   │   │   │   │   ├── README.md
│   │   │   │   │   ├── crossplane
│   │   │   │   │   │   ├── argocd
│   │   │   │   │   │   │   ├── argocd-values.yaml
│   │   │   │   │   │   │   └── argocd.yaml
│   │   │   │   │   │   ├── aws-provider-config.yaml
│   │   │   │   │   │   ├── aws-provider-vault-secret.yaml
│   │   │   │   │   │   ├── aws-provider.yaml
│   │   │   │   │   │   ├── environmentconfig.yaml
│   │   │   │   │   │   ├── helm
│   │   │   │   │   │   │   ├── clusterrolebinding.yaml
│   │   │   │   │   │   │   ├── controller-config.yaml
│   │   │   │   │   │   │   ├── provider-config.yaml
│   │   │   │   │   │   │   └── provider.yaml
│   │   │   │   │   │   ├── kubernetes-provider-config.yaml
│   │   │   │   │   │   ├── kubernetes-provider.yaml
│   │   │   │   │   │   ├── store-config-vault.yaml
│   │   │   │   │   │   ├── upbound-aws-provider-config.yaml
│   │   │   │   │   │   └── upbound-aws-provider.yaml
│   │   │   │   │   ├── eksctl-fargate.yaml
│   │   │   │   │   ├── eksctl.yaml
│   │   │   │   │   └── permission-boundary.json
│   │   │   │   ├── terraform
│   │   │   │   │   ├── README.md
│   │   │   │   │   ├── config
│   │   │   │   │   │   └── environmentconfig.yaml
│   │   │   │   │   ├── main.tf
│   │   │   │   │   ├── outputs.tf
│   │   │   │   │   ├── providers
│   │   │   │   │   │   ├── aws
│   │   │   │   │   │   │   ├── provider-config.yaml
│   │   │   │   │   │   │   ├── provider.yaml
│   │   │   │   │   │   │   └── runtime-config.yaml
│   │   │   │   │   │   ├── helm
│   │   │   │   │   │   │   ├── clusterrolebinding.yaml
│   │   │   │   │   │   │   ├── provider-config.yaml
│   │   │   │   │   │   │   ├── provider.yaml
│   │   │   │   │   │   │   └── runtime-config.yaml
│   │   │   │   │   │   ├── kubernetes
│   │   │   │   │   │   │   ├── clusterrolebinding.yaml
│   │   │   │   │   │   │   ├── provider-config.yaml
│   │   │   │   │   │   │   ├── provider.yaml
│   │   │   │   │   │   │   └── runtime-config.yaml
│   │   │   │   │   │   └── upjet-aws
│   │   │   │   │   │       ├── provider-config.yaml
│   │   │   │   │   │       ├── provider.yaml
│   │   │   │   │   │       └── runtime-config.yaml
│   │   │   │   │   ├── values
│   │   │   │   │   │   ├── argocd.yaml
│   │   │   │   │   │   ├── crossplane.yaml
│   │   │   │   │   │   └── prometheus.yaml
│   │   │   │   │   ├── variables.tf
│   │   │   │   │   └── versions.tf
│   │   │   │   └── terraform-fully-private
│   │   │   │       ├── README.md
│   │   │   │       ├── config
│   │   │   │       │   └── environmentconfig.yaml
│   │   │   │       ├── ecr.tf
│   │   │   │       ├── main.tf
│   │   │   │       ├── outputs.tf
│   │   │   │       ├── providers
│   │   │   │       │   ├── aws
│   │   │   │       │   │   ├── provider-config.yaml
│   │   │   │       │   │   ├── provider.yaml
│   │   │   │       │   │   └── runtime-config.yaml
│   │   │   │       │   ├── helm
│   │   │   │       │   │   ├── clusterrolebinding.yaml
│   │   │   │       │   │   ├── provider-config.yaml
│   │   │   │       │   │   ├── provider.yaml
│   │   │   │       │   │   └── runtime-config.yaml
│   │   │   │       │   ├── kubernetes
│   │   │   │       │   │   ├── clusterrolebinding.yaml
│   │   │   │       │   │   ├── provider-config.yaml
│   │   │   │       │   │   ├── provider.yaml
│   │   │   │       │   │   └── runtime-config.yaml
│   │   │   │       │   └── upjet-aws
│   │   │   │       │       ├── provider-config.yaml
│   │   │   │       │       ├── provider-family-aws.yaml
│   │   │   │       │       ├── provider.yaml
│   │   │   │       │       └── runtime-config.yaml
│   │   │   │       ├── scripts
│   │   │   │       │   ├── cleanup.sh
│   │   │   │       │   ├── copy-images-to-ecr.sh
│   │   │   │       │   └── create-crossplane-ecr-repos.sh
│   │   │   │       ├── values
│   │   │   │       │   ├── argocd.yaml
│   │   │   │       │   ├── aws-load-balancer-controller.yaml
│   │   │   │       │   ├── crossplane.yaml
│   │   │   │       │   ├── gatekeeper.yaml
│   │   │   │       │   ├── metrics-server.yaml
│   │   │   │       │   └── prometheus.yaml
│   │   │   │       ├── variables.tf
│   │   │   │       └── versions.tf
│   │   │   ├── compositions
│   │   │   │   ├── README.md
│   │   │   │   ├── aws-provider
│   │   │   │   │   ├── cloudfront
│   │   │   │   │   │   ├── definition.yaml
│   │   │   │   │   │   └── s3-origin-oai.yaml
│   │   │   │   │   ├── crossplane.yaml
│   │   │   │   │   ├── daskhub
│   │   │   │   │   │   ├── definition.yaml
│   │   │   │   │   │   └── test-env.yaml
│   │   │   │   │   ├── dynamodb
│   │   │   │   │   │   ├── definition.yaml
│   │   │   │   │   │   ├── on-demand-composite-key.yaml
│   │   │   │   │   │   ├── on-demand-partition-key.yaml
│   │   │   │   │   │   ├── provisioned-composite-gsi.yaml
│   │   │   │   │   │   ├── provisioned-composite-key.yaml
│   │   │   │   │   │   └── provisioned-composite-lsi.yaml
│   │   │   │   │   ├── eks
│   │   │   │   │   │   ├── autoscaler.yaml
│   │   │   │   │   │   ├── definition.yaml
│   │   │   │   │   │   ├── eks-managed-node-group-subnet-labels.yaml
│   │   │   │   │   │   └── eks-managed-node-group.yaml
│   │   │   │   │   ├── elasticache-redis
│   │   │   │   │   │   ├── definition.yaml
│   │   │   │   │   │   └── rediscluster.yaml
│   │   │   │   │   ├── emr-on-eks
│   │   │   │   │   │   ├── definition.yaml
│   │   │   │   │   │   ├── job-run.yaml
│   │   │   │   │   │   └── virtual-cluster.yaml
│   │   │   │   │   ├── example-application
│   │   │   │   │   │   ├── definition.yaml
│   │   │   │   │   │   └── example-application.yaml
│   │   │   │   │   ├── iam-policy
│   │   │   │   │   │   ├── definition.yaml
│   │   │   │   │   │   ├── dynamodb-manage-table.yaml
│   │   │   │   │   │   ├── dynamodb-read.yaml
│   │   │   │   │   │   ├── dynamodb-write.yaml
│   │   │   │   │   │   ├── s3-read.yaml
│   │   │   │   │   │   └── s3-write.yaml
│   │   │   │   │   ├── irsa
│   │   │   │   │   │   ├── definition.yaml
│   │   │   │   │   │   ├── irsa-exact.yaml
│   │   │   │   │   │   └── irsa-role-only.yaml
│   │   │   │   │   ├── rds
│   │   │   │   │   │   ├── definition.yaml
│   │   │   │   │   │   ├── postgres-aurora.yaml
│   │   │   │   │   │   └── rds-postgres.yaml
│   │   │   │   │   ├── s3
│   │   │   │   │   │   ├── definition.yaml
│   │   │   │   │   │   ├── general-purpose.yaml
│   │   │   │   │   │   └── multi-tenant.yaml
│   │   │   │   │   ├── vpc
│   │   │   │   │   │   ├── definition.yaml
│   │   │   │   │   │   └── vpc-composition.yaml
│   │   │   │   │   └── vpc-subnets
│   │   │   │   │       ├── definition.yaml
│   │   │   │   │       ├── vpc-composition-networkid.yaml
│   │   │   │   │       └── vpc-composition.yaml
│   │   │   │   ├── kustomization.yaml
│   │   │   │   └── upbound-aws-provider
│   │   │   │       ├── apigw
│   │   │   │       │   ├── definition.yaml
│   │   │   │       │   ├── kustomization.yaml
│   │   │   │       │   └── rest.yaml
│   │   │   │       ├── aurora
│   │   │   │       │   ├── aurora.yaml
│   │   │   │       │   ├── definition.yaml
│   │   │   │       │   └── kustomization.yaml
│   │   │   │       ├── cloudwatch-logs
│   │   │   │       │   ├── definition.yaml
│   │   │   │       │   ├── kustomization.yaml
│   │   │   │       │   └── subscriptionfilter.yaml
│   │   │   │       ├── dynamo-irsa
│   │   │   │       │   ├── definition.yaml
│   │   │   │       │   ├── dynamo-irsa.yaml
│   │   │   │       │   └── kustomization.yaml
│   │   │   │       ├── dynamodb
│   │   │   │       │   ├── definition.yaml
│   │   │   │       │   ├── kustomization.yaml
│   │   │   │       │   └── table.yaml
│   │   │   │       ├── event-source-mapping
│   │   │   │       │   ├── definition.yaml
│   │   │   │       │   ├── kustomization.yaml
│   │   │   │       │   └── sqs.yaml
│   │   │   │       ├── iam-policy
│   │   │   │       │   ├── cloudwatch-metrics-write.yaml
│   │   │   │       │   ├── definition.yaml
│   │   │   │       │   ├── dynamodb-write.yaml
│   │   │   │       │   ├── firehose-write.yaml
│   │   │   │       │   ├── kms-read.yaml
│   │   │   │       │   ├── kustomization.yaml
│   │   │   │       │   ├── lambda-invoke.yaml
│   │   │   │       │   ├── s3-read.yaml
│   │   │   │       │   ├── s3-write-firehose.yaml
│   │   │   │       │   ├── s3-write.yaml
│   │   │   │       │   ├── sqs-read.yaml
│   │   │   │       │   └── sqs-write.yaml
│   │   │   │       ├── irsa
│   │   │   │       │   ├── definition.yaml
│   │   │   │       │   ├── irsa.yaml
│   │   │   │       │   └── kustomization.yaml
│   │   │   │       ├── kinesis-data-firehose
│   │   │   │       │   ├── definition.yaml
│   │   │   │       │   ├── kinesis-data-firehose.yaml
│   │   │   │       │   └── kustomization.yaml
│   │   │   │       ├── kinesis-data-firehose-app
│   │   │   │       │   ├── definition.yaml
│   │   │   │       │   ├── kustomization.yaml
│   │   │   │       │   └── log-forwarder.yaml
│   │   │   │       ├── kms
│   │   │   │       │   ├── definition.yaml
│   │   │   │       │   ├── kms.yaml
│   │   │   │       │   └── kustomization.yaml
│   │   │   │       ├── kustomization.yaml
│   │   │   │       ├── lambda
│   │   │   │       │   ├── container.yaml
│   │   │   │       │   ├── definition.yaml
│   │   │   │       │   ├── kustomization.yaml
│   │   │   │       │   └── zip.yaml
│   │   │   │       ├── s3
│   │   │   │       │   ├── definition.yaml
│   │   │   │       │   ├── general-purpose.yaml
│   │   │   │       │   └── kustomization.yaml
│   │   │   │       ├── s3-irsa
│   │   │   │       │   ├── definition.yaml
│   │   │   │       │   ├── kustomization.yaml
│   │   │   │       │   └── s3-irsa.yaml
│   │   │   │       ├── serverless
│   │   │   │       │   ├── definition.yaml
│   │   │   │       │   ├── kustomization.yaml
│   │   │   │       │   ├── sns-sqs-lambda-s3.yaml
│   │   │   │       │   └── sqs-lambda-s3.yaml
│   │   │   │       ├── serverless-microservice
│   │   │   │       │   ├── definition.yaml
│   │   │   │       │   ├── functions.yaml
│   │   │   │       │   ├── kustomization.yaml
│   │   │   │       │   └── rest-lambda-ddb.yaml
│   │   │   │       ├── sns
│   │   │   │       │   ├── definition.yaml
│   │   │   │       │   ├── kustomization.yaml
│   │   │   │       │   └── sns.yaml
│   │   │   │       ├── sns-sqs
│   │   │   │       │   ├── definition.yaml
│   │   │   │       │   ├── kustomization.yaml
│   │   │   │       │   └── sns-sqs.yaml
│   │   │   │       └── sqs
│   │   │   │           ├── definition.yaml
│   │   │   │           ├── kustomization.yaml
│   │   │   │           └── sqs.yaml
│   │   │   ├── docs
│   │   │   │   ├── _partials
│   │   │   │   │   └── destroy.md
│   │   │   │   ├── faq.md
│   │   │   │   ├── getting-started.md
│   │   │   │   ├── index.md
│   │   │   │   └── patterns
│   │   │   │       ├── debugging.md
│   │   │   │       ├── nested-compositions.md
│   │   │   │       ├── patching-101.md
│   │   │   │       ├── rds-day-2.md
│   │   │   │       └── vault-integration.md
│   │   │   ├── examples
│   │   │   │   ├── aws-provider
│   │   │   │   │   ├── README.md
│   │   │   │   │   ├── composite-resources
│   │   │   │   │   │   ├── cloudfront
│   │   │   │   │   │   │   └── oai-s3.yaml
│   │   │   │   │   │   ├── daskhub
│   │   │   │   │   │   │   ├── README.md
│   │   │   │   │   │   │   ├── daskhub-sensitive.yaml
│   │   │   │   │   │   │   └── example-dask-hub-password.yaml
│   │   │   │   │   │   ├── dynamodb
│   │   │   │   │   │   │   ├── dynamodb-on-demand-composite.yaml
│   │   │   │   │   │   │   ├── dynamodb-on-demand-partition.yaml
│   │   │   │   │   │   │   ├── dynamodb-provisioned-composite-gsi.yaml
│   │   │   │   │   │   │   ├── dynamodb-provisioned-composite-lsi.yaml
│   │   │   │   │   │   │   └── dynamodb-provisioned-composite.yaml
│   │   │   │   │   │   ├── eks
│   │   │   │   │   │   │   ├── README.md
│   │   │   │   │   │   │   ├── eks-cas-claim.yaml
│   │   │   │   │   │   │   └── eks-claim.yaml
│   │   │   │   │   │   ├── elasticache-redis
│   │   │   │   │   │   │   └── rediscluster.yaml
│   │   │   │   │   │   ├── emr-on-eks
│   │   │   │   │   │   │   ├── job-run.yaml
│   │   │   │   │   │   │   └── virtual-cluster.yaml
│   │   │   │   │   │   ├── example-application
│   │   │   │   │   │   │   └── example-application.yaml
│   │   │   │   │   │   ├── iam
│   │   │   │   │   │   │   └── iam-dynamodb-read.yaml
│   │   │   │   │   │   ├── rds
│   │   │   │   │   │   │   ├── aurora-postgres.yaml
│   │   │   │   │   │   │   └── postgres.yaml
│   │   │   │   │   │   ├── s3
│   │   │   │   │   │   │   ├── general-purpose-copy.yaml
│   │   │   │   │   │   │   ├── general-purpose.yaml
│   │   │   │   │   │   │   └── multi-tenant.yaml
│   │   │   │   │   │   ├── vpc
│   │   │   │   │   │   │   └── vpc.yaml
│   │   │   │   │   │   ├── vpc-subnets
│   │   │   │   │   │   │   └── vpc-subnets-claim.yaml
│   │   │   │   │   │   └── vpc-subnets-eks
│   │   │   │   │   │       └── vpc-subnets-eks-claims.yaml
│   │   │   │   │   └── managed-resources
│   │   │   │   │       ├── eks-clusterauth.yaml
│   │   │   │   │       ├── s3.yaml
│   │   │   │   │       └── vpc.yaml
│   │   │   │   ├── gatekeeper
│   │   │   │   │   ├── duplicate-s3
│   │   │   │   │   │   ├── README.md
│   │   │   │   │   │   ├── samples
│   │   │   │   │   │   │   ├── allowed-bucket.yaml
│   │   │   │   │   │   │   ├── allowed-claim.yaml
│   │   │   │   │   │   │   ├── constraint.yaml
│   │   │   │   │   │   │   ├── existing-buckets.yaml
│   │   │   │   │   │   │   ├── not-allowed-bucket.yaml
│   │   │   │   │   │   │   └── not-allowed-claim.yaml
│   │   │   │   │   │   ├── suite.yaml
│   │   │   │   │   │   ├── syncset.yaml
│   │   │   │   │   │   └── template.yaml
│   │   │   │   │   ├── duplicate-vpc
│   │   │   │   │   │   ├── README.md
│   │   │   │   │   │   ├── samples
│   │   │   │   │   │   │   ├── allowed-data.yaml
│   │   │   │   │   │   │   ├── allowed-unique.yaml
│   │   │   │   │   │   │   ├── constraint.yaml
│   │   │   │   │   │   │   ├── duplicate-name-data.yaml
│   │   │   │   │   │   │   └── duplicate-name.yaml
│   │   │   │   │   │   ├── suite.yaml
│   │   │   │   │   │   └── template.yaml
│   │   │   │   │   ├── managed-resource
│   │   │   │   │   │   ├── README.md
│   │   │   │   │   │   ├── samples
│   │   │   │   │   │   │   ├── allowed.yaml
│   │   │   │   │   │   │   ├── constraint.yaml
│   │   │   │   │   │   │   ├── invalid-ownere-ref-disallowed.yaml
│   │   │   │   │   │   │   └── missing-owner-ref-disallowed.yaml
│   │   │   │   │   │   ├── suite.yaml
│   │   │   │   │   │   └── template.yaml
│   │   │   │   │   ├── provider-config-ns
│   │   │   │   │   │   ├── README.md
│   │   │   │   │   │   ├── kustomization.yaml
│   │   │   │   │   │   ├── samples
│   │   │   │   │   │   │   ├── allowed.yaml
│   │   │   │   │   │   │   ├── constraint.yaml
│   │   │   │   │   │   │   ├── missing-claim-disallowed.yaml
│   │   │   │   │   │   │   ├── namespace-mismatch-disallowed.yaml
│   │   │   │   │   │   │   └── not-applicable-allowed.yaml
│   │   │   │   │   │   ├── suite.yaml
│   │   │   │   │   │   └── template.yaml
│   │   │   │   │   ├── region-restrict
│   │   │   │   │   │   ├── README.md
│   │   │   │   │   │   ├── samples
│   │   │   │   │   │   │   ├── constraint.yaml
│   │   │   │   │   │   │   ├── sample-table-eu-west-2-pass.yaml
│   │   │   │   │   │   │   └── sample-table-us-east-1-fail.yaml
│   │   │   │   │   │   ├── suite.yaml
│   │   │   │   │   │   └── template.yaml
│   │   │   │   │   ├── required-tags
│   │   │   │   │   │   ├── README.md
│   │   │   │   │   │   ├── samples
│   │   │   │   │   │   │   ├── constraint.yaml
│   │   │   │   │   │   │   ├── dummy-table-missing-tag-fail.yaml
│   │   │   │   │   │   │   ├── dummy-table-no-tags-fail.yaml
│   │   │   │   │   │   │   └── finance-table-pass.yaml
│   │   │   │   │   │   ├── suite.yaml
│   │   │   │   │   │   └── template.yaml
│   │   │   │   │   └── required-tags-s3
│   │   │   │   │       ├── README.md
│   │   │   │   │       ├── constraint.yaml
│   │   │   │   │       └── template.yaml
│   │   │   │   ├── helm-provider
│   │   │   │   │   └── test-helm.yaml
│   │   │   │   ├── kubernetes-provider
│   │   │   │   │   └── test-namespace.yaml
│   │   │   │   └── upbound-aws-provider
│   │   │   │       ├── README.md
│   │   │   │       ├── composite-resources
│   │   │   │       │   ├── databases
│   │   │   │       │   │   ├── aurora
│   │   │   │       │   │   │   ├── README.md
│   │   │   │       │   │   │   ├── aurora-monitoring.json
│   │   │   │       │   │   │   ├── aurora-postgresql.yaml
│   │   │   │       │   │   │   ├── psql-client-pod.yaml
│   │   │   │       │   │   │   ├── rds-proxy-policy.json
│   │   │   │       │   │   │   ├── rds-proxy.json
│   │   │   │       │   │   │   └── rdsproxy-access.json
│   │   │   │       │   │   ├── dynamo-irsa
│   │   │   │       │   │   │   ├── README.md
│   │   │   │       │   │   │   ├── awscli-pod.yaml
│   │   │   │       │   │   │   ├── claim
│   │   │   │       │   │   │   │   └── dynamo-irsa.yaml
│   │   │   │       │   │   │   └── kustomization.yaml
│   │   │   │       │   │   └── dynamodb
│   │   │   │       │   │       ├── dynamodbtable-ondemand.yaml
│   │   │   │       │   │       ├── dynamodbtable-provisioned-composite-gsi.yaml
│   │   │   │       │   │       ├── dynamodbtable-provisioned-composite-lsi.yaml
│   │   │   │       │   │       ├── dynamodbtable-provisioned-composite.yaml
│   │   │   │       │   │       └── dynamodbtable-provisioned.yaml
│   │   │   │       │   ├── dynamodbtable.yaml
│   │   │   │       │   ├── irsa.yaml
│   │   │   │       │   ├── kms.yaml
│   │   │   │       │   ├── lambda.yaml
│   │   │   │       │   ├── s3-irsa
│   │   │   │       │   │   ├── README.md
│   │   │   │       │   │   ├── Values.yaml
│   │   │   │       │   │   ├── argocd-s3-irsa-app.yaml
│   │   │   │       │   │   ├── helm-chart
│   │   │   │       │   │   │   ├── Chart.yaml
│   │   │   │       │   │   │   ├── templates
│   │   │   │       │   │   │   │   ├── _helper.tpl
│   │   │   │       │   │   │   │   ├── claim.yaml
│   │   │   │       │   │   │   │   └── deployment.yaml
│   │   │   │       │   │   │   └── values.yaml
│   │   │   │       │   │   ├── kustomization.yaml
│   │   │   │       │   │   └── s3-irsa.yaml
│   │   │   │       │   ├── s3.yaml
│   │   │   │       │   ├── serverless-examples
│   │   │   │       │   │   ├── kinesis-lambda-s3-logs
│   │   │   │       │   │   │   ├── README.md
│   │   │   │       │   │   │   ├── claim
│   │   │   │       │   │   │   │   ├── kinesis-lambda-s3-claim-tmpl.yaml
│   │   │   │       │   │   │   │   ├── kustomization.yaml
│   │   │   │       │   │   │   │   └── subscription-claim-tmpl.yaml
│   │   │   │       │   │   │   ├── environment
│   │   │   │       │   │   │   │   ├── environmentconfig-tmpl.yaml
│   │   │   │       │   │   │   │   └── kustomization.yaml
│   │   │   │       │   │   │   ├── kustomization.yaml
│   │   │   │       │   │   │   ├── managed
│   │   │   │       │   │   │   │   ├── managed-subscription.yaml
│   │   │   │       │   │   │   │   └── managed.yaml
│   │   │   │       │   │   │   └── upload_dynatrace_zip.sh
│   │   │   │       │   │   ├── microservice
│   │   │   │       │   │   │   ├── README.md
│   │   │   │       │   │   │   ├── assets
│   │   │   │       │   │   │   │   ├── Architecture.graffle
│   │   │   │       │   │   │   │   └── architecture.png
│   │   │   │       │   │   │   ├── build-and-upload-zip.sh
│   │   │   │       │   │   │   ├── claim
│   │   │   │       │   │   │   │   ├── kustomization.yaml
│   │   │   │       │   │   │   │   └── microservice-claim-tmpl.yaml
│   │   │   │       │   │   │   ├── kustomization.yaml
│   │   │   │       │   │   │   └── src
│   │   │   │       │   │   │       ├── authorizer
│   │   │   │       │   │   │       │   └── lambda_function.py
│   │   │   │       │   │   │       ├── logic
│   │   │   │       │   │   │       │   └── lambda_function.py
│   │   │   │       │   │   │       └── requirements.txt
│   │   │   │       │   │   ├── object-processor-app
│   │   │   │       │   │   │   ├── Dockerfile
│   │   │   │       │   │   │   ├── README.md
│   │   │   │       │   │   │   ├── build-and-upload-zip.sh
│   │   │   │       │   │   │   ├── go.mod
│   │   │   │       │   │   │   ├── go.sum
│   │   │   │       │   │   │   └── main.go
│   │   │   │       │   │   ├── sns-sqs-lambda-s3
│   │   │   │       │   │   │   ├── README.md
│   │   │   │       │   │   │   ├── claim
│   │   │   │       │   │   │   │   ├── kustomization.yaml
│   │   │   │       │   │   │   │   ├── sns-sqs-lambda-s3-claim-tmpl.yaml
│   │   │   │       │   │   │   │   └── sns-sqs-lambda-s3-claim.yaml
│   │   │   │       │   │   │   └── kustomization.yaml
│   │   │   │       │   │   └── sqs-lambda-s3
│   │   │   │       │   │       ├── README.md
│   │   │   │       │   │       ├── claim
│   │   │   │       │   │       │   ├── kustomization.yaml
│   │   │   │       │   │       │   └── sqs-lambda-s3-claim-tmpl.yaml
│   │   │   │       │   │       └── kustomization.yaml
│   │   │   │       │   ├── sns-sqs.yaml
│   │   │   │       │   ├── sns.yaml
│   │   │   │       │   ├── sqs-read.yaml
│   │   │   │       │   └── sqs.yaml
│   │   │   │       ├── diagrams
│   │   │   │       │   ├── argo-cd-s3-irsa-sync.gif
│   │   │   │       │   ├── dynamo-irsa-app.png
│   │   │   │       │   ├── s3-irsa-app-check-pod-logs.gif
│   │   │   │       │   ├── s3-irsa-app.png
│   │   │   │       │   ├── serverless.png
│   │   │   │       │   └── sqs-lambda-s3.png
│   │   │   │       └── managed-resources
│   │   │   │           ├── dynamodb
│   │   │   │           │   └── table.yaml
│   │   │   │           ├── ec2
│   │   │   │           │   └── vpc.yaml
│   │   │   │           └── s3
│   │   │   │               └── bucket.yaml
│   │   │   ├── mkdocs.yml
│   │   │   ├── my-readme.md
│   │   │   ├── notes
│   │   │   └── tests
│   │   │       ├── e2e
│   │   │       │   └── base-tests
│   │   │       │       └── 00-errors.yaml
│   │   │       └── kuttl
│   │   │           ├── test-suite.yaml
│   │   │           └── values.yaml
│   │   └── eks-gitops-crossplane-argocd
│   │       ├── CODE_OF_CONDUCT.md
│   │       ├── CONTRIBUTING.md
│   │       ├── GitOps model for provisioning and bootstrapping Amazon EKS clusters using Crossplane and Argo CD _ Containers.pdf
│   │       ├── GitOps model for provisioning and bootstrapping Amazon EKS clusters using Crossplane and Flux _ Containers.pdf
│   │       ├── LICENSE
│   │       ├── README.md
│   │       ├── application-apps.yaml
│   │       ├── application-crossplane.yaml
│   │       ├── argocd-setup-configmap.yaml
│   │       ├── argocd-setup-helm-repositories.yaml
│   │       ├── argocd-setup-ssh-known-hosts-configmap.yaml
│   │       ├── argocd.sh
│   │       ├── crossplane-complete
│   │       │   ├── Chart.yaml
│   │       │   └── templates
│   │       │       ├── 2-aws-provider.yaml
│   │       │       ├── 3-k8s-provider.yaml
│   │       │       ├── 4-aws-providerconfig.yaml
│   │       │       ├── 5-k8s-providerconfig.yaml
│   │       │       ├── 6-crossplane-eks-composition.yaml
│   │       │       └── 7-eks-cluster-xr.yaml
│   │       ├── crossplane-imperative
│   │       │   ├── aws-provider.yaml
│   │       │   ├── aws-providerconfig.yaml
│   │       │   ├── eks-cluster-xr.yaml
│   │       │   └── eks-configuration
│   │       │       ├── compositeresourcedefinition.yaml
│   │       │       ├── composition.yaml
│   │       │       └── crossplane.yaml
│   │       ├── crossplane.sh
│   │       ├── deprecated
│   │       │   ├── 2-controller.yaml
│   │       │   └── 3-aws-credentials-sealed.yaml
│   │       ├── install.yaml
│   │       ├── my-readme.md
│   │       ├── project-applications.yaml
│   │       ├── project-workloads.yaml
│   │       ├── remote
│   │       │   ├── cluster-secret.yaml
│   │       │   ├── remote-cluster-setup.sh
│   │       │   └── service-account-remote.yaml
│   │       ├── workload-apps
│   │       │   ├── Chart.yaml
│   │       │   └── templates
│   │       │       ├── application-monitoring.yaml
│   │       │       └── application-s3reader.yaml
│   │       └── workloads
│   │           ├── monitoring
│   │           │   ├── Chart.yaml
│   │           │   ├── grafana-values.yaml
│   │           │   └── prometheus-values.yaml
│   │           └── s3reader
│   │               └── deployment-s3reader.yaml
│   ├── eks-app-mesh-polyglot-demo
│   │   ├── IAM role-based authentication to Aurora RDS.docx
│   │   ├── LICENSE
│   │   ├── README.md
│   │   ├── Using IAM database authentication with workloads running on Amazon EKS.docx
│   │   ├── apps
│   │   │   ├── catalog_detail
│   │   │   │   ├── Dockerfile
│   │   │   │   ├── app.js
│   │   │   │   ├── app2.js
│   │   │   │   ├── deployment.yaml
│   │   │   │   ├── package-lock.json
│   │   │   │   ├── package.json
│   │   │   │   ├── proddetail-0.1.0.tgz
│   │   │   │   └── version2
│   │   │   │       └── Dockerfile
│   │   │   ├── frontend_node
│   │   │   │   ├── Dockerfile
│   │   │   │   ├── index.html
│   │   │   │   ├── package-lock.json
│   │   │   │   ├── package.json
│   │   │   │   ├── public
│   │   │   │   │   ├── architecture.png
│   │   │   │   │   └── css
│   │   │   │   │       └── styles.css
│   │   │   │   ├── server.js
│   │   │   │   └── views
│   │   │   │       └── index.ejs
│   │   │   └── product_catalog
│   │   │       ├── Dockerfile
│   │   │       ├── app.py
│   │   │       ├── bootstrap.sh
│   │   │       └── requirements.txt
│   │   ├── deployment
│   │   │   ├── base_app.yaml
│   │   │   ├── canary.yaml
│   │   │   ├── clusterconfig.yaml
│   │   │   ├── envoy-iam-policy.json
│   │   │   ├── fluentbit-config.yaml
│   │   │   ├── mesh.yaml
│   │   │   ├── meshed_app.yaml
│   │   │   └── virtual_gateway.yaml
│   │   ├── my-readme.md
│   │   └── workshop
│   │       ├── apps
│   │       │   ├── catalog_detail
│   │       │   │   ├── Dockerfile
│   │       │   │   ├── app.js
│   │       │   │   ├── package-lock.json
│   │       │   │   ├── package.json
│   │       │   │   └── readiness.txt
│   │       │   ├── frontend_node
│   │       │   │   ├── Dockerfile
│   │       │   │   ├── index.html
│   │       │   │   ├── package-lock.json
│   │       │   │   ├── package.json
│   │       │   │   ├── public
│   │       │   │   │   ├── arch.png
│   │       │   │   │   ├── architecture.png
│   │       │   │   │   └── css
│   │       │   │   │       └── styles.css
│   │       │   │   ├── server.js
│   │       │   │   └── views
│   │       │   │       └── index.ejs
│   │       │   ├── product_catalog
│   │       │   │   ├── Dockerfile
│   │       │   │   ├── app.py
│   │       │   │   ├── app_aurora.py
│   │       │   │   ├── app_ebs.py
│   │       │   │   ├── app_efs.py
│   │       │   │   ├── app_secrets.py
│   │       │   │   ├── bootstrap.sh
│   │       │   │   ├── rds-combined-ca-bundle.pem
│   │       │   │   └── requirements.txt
│   │       │   └── sku
│   │       │       ├── app
│   │       │       │   ├── Dockerfile
│   │       │       │   ├── app.js
│   │       │       │   ├── index.html
│   │       │       │   ├── package-lock.json
│   │       │       │   ├── package.json
│   │       │       │   ├── public
│   │       │       │   │   └── css
│   │       │       │   │       └── styles.css
│   │       │       │   └── views
│   │       │       │       └── index.ejs
│   │       │       └── helm-chart
│   │       │           ├── Chart.yaml
│   │       │           ├── templates
│   │       │           │   ├── NOTES.txt
│   │       │           │   ├── _helpers.tpl
│   │       │           │   ├── deployment.yaml
│   │       │           │   ├── ingress.yaml
│   │       │           │   ├── namespace.yaml
│   │       │           │   ├── service.yaml
│   │       │           │   └── tests
│   │       │           │       └── test-connection.yaml
│   │       │           └── values.yaml
│   │       ├── aws_lbc_iam_policy.json
│   │       ├── cloudformation
│   │       │   └── alb_deployment.yaml
│   │       ├── cluster-autoscaler.yaml
│   │       ├── collector-config-opentelemetry.yaml
│   │       ├── efs-pvc.yaml
│   │       ├── helm-chart
│   │       │   ├── Chart.yaml
│   │       │   ├── productcatalog_workshop-1.0.0.tgz
│   │       │   ├── security
│   │       │   │   ├── values-psa-pss-baseline-ns.yaml
│   │       │   │   ├── values-psa-pss-baseline.yaml
│   │       │   │   ├── values-psa-pss-priv.yaml
│   │       │   │   ├── values-psa-pss-restricted-ns.yaml
│   │       │   │   ├── values-psa-pss-restricted.yaml
│   │       │   │   └── values-psa-pss.yaml
│   │       │   ├── templates
│   │       │   │   ├── NOTES.txt
│   │       │   │   ├── _helpers.tpl
│   │       │   │   ├── catalog_deployment.yaml
│   │       │   │   ├── catalog_service.yaml
│   │       │   │   ├── detail_deployment.yaml
│   │       │   │   ├── detail_service.yaml
│   │       │   │   ├── frontend_deployment.yaml
│   │       │   │   ├── frontend_service.yaml
│   │       │   │   ├── ingress.yaml
│   │       │   │   ├── namespace.yaml
│   │       │   │   └── tests
│   │       │   │       └── test-connection.yaml
│   │       │   ├── values-aurora.yaml
│   │       │   ├── values-ebs.yaml
│   │       │   ├── values-efs.yaml
│   │       │   ├── values-k8s-secret.yaml
│   │       │   ├── values-secrets-manager.yaml
│   │       │   └── values.yaml
│   │       ├── mysql-statefulset-with-secret.yaml
│   │       ├── mysql-statefulset.yaml
│   │       ├── otel-collector-config.yaml
│   │       ├── productcatalog_workshop-1.0.0.tgz
│   │       ├── prometheus-eks.yaml
│   │       ├── script
│   │       │   └── build.sh
│   │       ├── spinnaker
│   │       │   ├── installspinnaker.sh
│   │       │   └── spinnakerservice.yml
│   │       └── xray-eks.yaml
│   ├── eks-network-policy-examples
│   │   ├── Amazon VPC CNI now supports Kubernetes Network Policies _ Containers.pdf
│   │   ├── CODE_OF_CONDUCT.md
│   │   ├── CONTRIBUTING.md
│   │   ├── LICENSE
│   │   ├── README.md
│   │   ├── advanced
│   │   │   ├── README.md
│   │   │   ├── cleanup.sh
│   │   │   ├── manifests
│   │   │   │   ├── 00-namespace.yaml
│   │   │   │   ├── 01-demo-app.yaml
│   │   │   │   ├── 02-clients-same-ns.yaml
│   │   │   │   └── 03-clients-another-ns.yaml
│   │   │   └── policies
│   │   │       ├── 01-deny-all-ingress.yaml
│   │   │       ├── 02-allow-ingress-from-samens.yaml
│   │   │       ├── 03-allow-ingress-from-samens-client-one.yaml
│   │   │       ├── 04-allow-ingress-from-xns.yaml
│   │   │       ├── 05-allow-ingress-from-xns-client-one.yaml
│   │   │       ├── 06-deny-egress-from-client-one.yaml
│   │   │       ├── 07-allow-egress-to-coredns.yaml
│   │   │       ├── 08-allow-egress-to-demo-app.yaml
│   │   │       └── 09-allow-egress-to-ip.yaml
│   │   ├── my-readme.md
│   │   ├── security-groups-for-pods
│   │   │   ├── README.md
│   │   │   ├── manifests
│   │   │   │   ├── 00-namespace.yaml
│   │   │   │   ├── 01-sg-policy.yaml
│   │   │   │   ├── 02-sgp-demo-app.yaml
│   │   │   │   └── 03-clients-same-ns.yaml
│   │   │   └── policies
│   │   │       └── 01-allow-ingress-client-one.yaml
│   │   └── stars
│   │       ├── README.md
│   │       ├── manifests
│   │       │   ├── 00-namespace.yaml
│   │       │   ├── 01-management-ui.yaml
│   │       │   ├── 02-backend.yaml
│   │       │   ├── 03-frontend.yaml
│   │       │   └── 04-client.yaml
│   │       ├── policies
│   │       │   ├── allow-ui-client.yaml
│   │       │   ├── allow-ui.yaml
│   │       │   ├── backend-policy.yaml
│   │       │   ├── default-deny.yaml
│   │       │   └── frontend-policy.yaml
│   │       └── reset.sh
│   ├── istio-on-eks
│   │   ├── CODE_OF_CONDUCT.md
│   │   ├── CONTRIBUTING.md
│   │   ├── Enhancing Network Resilience with Istio on Amazon EKS.docx
│   │   ├── Getting Started with Istio on Amazon EKS.docx
│   │   ├── LICENSE
│   │   ├── NOTICE
│   │   ├── README.md
│   │   ├── Using Istio Traffic Management on Amazon EKS to Enhance User Experience.docx
│   │   ├── apps
│   │   │   ├── catalog_detail
│   │   │   │   ├── Dockerfile
│   │   │   │   ├── app.js
│   │   │   │   ├── package-lock.json
│   │   │   │   ├── package.json
│   │   │   │   └── readiness.txt
│   │   │   ├── frontend_node
│   │   │   │   ├── Dockerfile
│   │   │   │   ├── index.html
│   │   │   │   ├── package-lock.json
│   │   │   │   ├── package.json
│   │   │   │   ├── public
│   │   │   │   │   ├── arch.png
│   │   │   │   │   ├── architecture.png
│   │   │   │   │   └── css
│   │   │   │   │       └── styles.css
│   │   │   │   ├── server.js
│   │   │   │   └── views
│   │   │   │       └── index.ejs
│   │   │   └── product_catalog
│   │   │       ├── Dockerfile
│   │   │       ├── app.py
│   │   │       ├── app_aurora.py
│   │   │       ├── app_ebs.py
│   │   │       ├── app_efs.py
│   │   │       ├── app_secrets.py
│   │   │       ├── bootstrap.sh
│   │   │       ├── rds-combined-ca-bundle.pem
│   │   │       └── requirements.txt
│   │   ├── modules
│   │   │   ├── 00-setup-mesh-resources
│   │   │   │   ├── catalogdetail-destinationrule.yaml
│   │   │   │   ├── catalogdetail-virtualservice.yaml
│   │   │   │   ├── frontend-virtualservice.yaml
│   │   │   │   └── productcatalog-virtualservice.yaml
│   │   │   ├── 01-getting-started
│   │   │   │   ├── Chart.yaml
│   │   │   │   ├── README.md
│   │   │   │   ├── templates
│   │   │   │   │   ├── NOTES.txt
│   │   │   │   │   ├── _helpers.tpl
│   │   │   │   │   ├── catalogdetail-deployment.yaml
│   │   │   │   │   ├── catalogdetail-sa.yaml
│   │   │   │   │   ├── catalogdetail-service.yaml
│   │   │   │   │   ├── catalogdetail2-deployment.yaml
│   │   │   │   │   ├── frontend-deployment.yaml
│   │   │   │   │   ├── frontend-sa.yaml
│   │   │   │   │   ├── frontend-service.yaml
│   │   │   │   │   ├── productapp-gateway.yaml
│   │   │   │   │   ├── productapp-virtualservice.yaml
│   │   │   │   │   ├── productcatalog-deployment.yaml
│   │   │   │   │   ├── productcatalog-sa.yaml
│   │   │   │   │   └── productcatalog-service.yaml
│   │   │   │   └── values.yaml
│   │   │   ├── 02-traffic-management
│   │   │   │   ├── README.md
│   │   │   │   ├── header-based-routing
│   │   │   │   │   ├── catalogdetail-virtualservice.yaml
│   │   │   │   │   └── productcatalog-envoyfilter.yaml
│   │   │   │   ├── path-based-routing
│   │   │   │   │   └── catalogdetail-virtualservice.yaml
│   │   │   │   ├── route-traffic-to-version-v1
│   │   │   │   │   └── catalogdetail-virtualservice.yaml
│   │   │   │   ├── setup-mesh-resources
│   │   │   │   │   ├── catalogdetail-destinationrule.yaml
│   │   │   │   │   ├── catalogdetail-virtualservice.yaml
│   │   │   │   │   ├── frontend-virtualservice.yaml
│   │   │   │   │   └── productcatalog-virtualservice.yaml
│   │   │   │   ├── traffic-mirroring
│   │   │   │   │   └── catalogdetail-virtualservice.yaml
│   │   │   │   └── weight-based-routing
│   │   │   │       └── catalogdetail-virtualservice.yaml
│   │   │   ├── 03-network-resiliency
│   │   │   │   ├── README.md
│   │   │   │   ├── fault-injection
│   │   │   │   │   ├── README.md
│   │   │   │   │   ├── abort
│   │   │   │   │   │   └── catalogdetail-virtualservice.yaml
│   │   │   │   │   └── delay
│   │   │   │   │       └── catalogdetail-virtualservice.yaml
│   │   │   │   ├── rate-limiting
│   │   │   │   │   ├── README.md
│   │   │   │   │   ├── global-ratelimit
│   │   │   │   │   │   ├── filter-ratelimit-svc.yaml
│   │   │   │   │   │   ├── filter-ratelimit.yaml
│   │   │   │   │   │   ├── global-ratelimit-config.yaml
│   │   │   │   │   │   └── global-ratelimit-service.yaml
│   │   │   │   │   └── local-ratelimit
│   │   │   │   │       └── local-ratelimit.yaml
│   │   │   │   └── timeouts-retries-circuitbreaking
│   │   │   │       ├── README.md
│   │   │   │       ├── circuitbreaking
│   │   │   │       │   └── catalogdetail-destinationrule.yaml
│   │   │   │       ├── retries
│   │   │   │       │   └── productcatalog-virtualservice.yaml
│   │   │   │       └── timeouts
│   │   │   │           ├── catalogdetail-virtualservice.yaml
│   │   │   │           └── productcatalog-virtualservice.yaml
│   │   │   └── 04-security
│   │   │       ├── README.md
│   │   │       ├── ingress-security
│   │   │       │   └── README.md
│   │   │       ├── opa-external-authorization
│   │   │       │   ├── README.md
│   │   │       │   ├── kustomization.yaml
│   │   │       │   ├── opa-ext-authz-serviceentry.yaml
│   │   │       │   ├── opa-ext-authz-sidecar-assign.yaml
│   │   │       │   ├── policy.rego
│   │   │       │   ├── policy_test.rego
│   │   │       │   └── productapp-authorizationpolicy.yaml
│   │   │       ├── peer-authentication
│   │   │       │   └── README.md
│   │   │       ├── request-authentication
│   │   │       │   ├── README.md
│   │   │       │   ├── ingress-authorizationpolicy.yaml
│   │   │       │   └── ingress-requestauthentication-template.yaml
│   │   │       ├── scripts
│   │   │       │   ├── cleanup-crds.sh
│   │   │       │   └── helpers.sh
│   │   │       └── terraform
│   │   │           ├── eks.tf
│   │   │           ├── istio.tf
│   │   │           ├── keycloak.tf
│   │   │           ├── lb.tf
│   │   │           ├── main.tf
│   │   │           ├── outputs.tf
│   │   │           ├── variables.tf
│   │   │           ├── versions.tf
│   │   │           ├── vpc.tf
│   │   │           └── workshop.tf
│   │   └── patterns
│   │       └── multi-cluster-multinetwork-multiprimary
│   │           ├── README.md
│   │           ├── charts
│   │           │   └── multicluster-gateway-n-apps
│   │           │       ├── Chart.yaml
│   │           │       ├── templates
│   │           │       │   ├── gateway.yaml
│   │           │       │   ├── helloworld-deployment.yaml
│   │           │       │   ├── helloworld-service.yaml
│   │           │       │   ├── remote-secret.yaml
│   │           │       │   ├── sleep-deployment.yaml
│   │           │       │   ├── sleep-service.yaml
│   │           │       │   └── sleep-serviceaccount.yaml
│   │           │       └── values.yaml
│   │           ├── data.tf
│   │           ├── eks_1.tf
│   │           ├── eks_2.tf
│   │           ├── istio-multi-cluster-architecture.png
│   │           ├── locals.tf
│   │           ├── providers.tf
│   │           ├── root_cert.tf
│   │           ├── scripts
│   │           │   ├── check-cross-cluster-sync.sh
│   │           │   ├── check-lb-readiness.sh
│   │           │   ├── deploy.sh
│   │           │   ├── destroy.sh
│   │           │   └── set-cluster-contexts.sh
│   │           └── versions.tf
│   ├── k8s-psa-pss-testing
│   │   ├── CODE_OF_CONDUCT.md
│   │   ├── CONTRIBUTING.md
│   │   ├── Implementing Pod Security Standards in Amazon EKS _ Containers.pdf
│   │   ├── LICENSE
│   │   ├── README.md
│   │   ├── my-readme.md
│   │   └── tests
│   │       ├── 0-ns.yaml
│   │       ├── 1-ok.yaml
│   │       ├── 2-dep-sec-cont.yaml
│   │       ├── 3-pod.yaml
│   │       ├── 4-pod.yaml
│   │       ├── 5-pod.yaml
│   │       ├── 6-pod.yaml
│   │       ├── clean.sh
│   │       └── tests.sh
│   ├── keda
│   │   ├── amazon-eks-scaling-with-keda-and-karpenter
│   │   │   ├── Scalable and Cost-Effective Event-Driven Workloads with KEDA and Karpenter on Amazon EKS _ Containers.pdf
│   │   │   ├── code
│   │   │   │   ├── CODE_OF_CONDUCT.md
│   │   │   │   ├── CONTRIBUTING.md
│   │   │   │   ├── LICENSE
│   │   │   │   ├── README.md
│   │   │   │   ├── app
│   │   │   │   │   ├── docker-command.sh
│   │   │   │   │   ├── karpenter
│   │   │   │   │   │   ├── Dockerfile
│   │   │   │   │   │   ├── karpenter-mock-sqs-post.py
│   │   │   │   │   │   └── karpenter-sqs-reader.py
│   │   │   │   │   └── keda
│   │   │   │   │       ├── Dockerfile
│   │   │   │   │       ├── keda-mock-sqs-post.py
│   │   │   │   │       └── sqs-reader.py
│   │   │   │   ├── cleanup.sh
│   │   │   │   ├── deployment
│   │   │   │   │   ├── _main.sh
│   │   │   │   │   ├── app
│   │   │   │   │   │   └── keda-python-app.yaml
│   │   │   │   │   ├── cluster
│   │   │   │   │   │   └── createCluster.sh
│   │   │   │   │   ├── environmentVariables.sh
│   │   │   │   │   ├── karpenter
│   │   │   │   │   │   ├── README.md
│   │   │   │   │   │   ├── cloudformation.yaml
│   │   │   │   │   │   ├── createkarpenter.sh
│   │   │   │   │   │   └── createkarpenter_before_v.32.sh
│   │   │   │   │   ├── keda
│   │   │   │   │   │   ├── ReadME.md
│   │   │   │   │   │   ├── createkeda.sh
│   │   │   │   │   │   ├── dynamoPolicy.json
│   │   │   │   │   │   ├── keda-scaleobject.sh
│   │   │   │   │   │   ├── sqsPolicy.json
│   │   │   │   │   │   └── values.sh
│   │   │   │   │   └── services
│   │   │   │   │       └── awsService.sh
│   │   │   │   └── img
│   │   │   │       ├── Karpenterversion.jpg
│   │   │   │       ├── Keda.gif
│   │   │   │       ├── YouTube-Logo.jpg
│   │   │   │       ├── accountverify.jpg
│   │   │   │       ├── aws_kedakarpenter_arch_small.gif
│   │   │   │       ├── deploymentcompleted.jpg
│   │   │   │       ├── deploymentverify.jpg
│   │   │   │       ├── karpenterhelm.jpg
│   │   │   │       ├── mockarchitecture.jpg
│   │   │   │       ├── node.jpg
│   │   │   │       ├── nodescaling.jpg
│   │   │   │       ├── pod.jpg
│   │   │   │       ├── podscaling.jpg
│   │   │   │       ├── runloadscript.jpg
│   │   │   │       └── setenv.jpg
│   │   │   └── my-readme.md
│   │   └── keda-on-amazon-eks
│   │       ├── CODE_OF_CONDUCT.md
│   │       ├── CONTRIBUTING.md
│   │       ├── LICENSE
│   │       ├── README.md
│   │       ├── assets
│   │       │   └── keda-architecture.png
│   │       ├── event-driven-application-autoscaling-with-keda-on-amazon-eks.pdf
│   │       ├── keda
│   │       │   ├── keda-values.yaml.template
│   │       │   └── trust-policy-keda-operator.json
│   │       ├── my-readme.md
│   │       ├── scaledobject-samples
│   │       │   ├── amazonsqs
│   │       │   │   ├── sample-app
│   │       │   │   │   ├── Dockerfile
│   │       │   │   │   └── sqs-consumer.py
│   │       │   │   ├── scaledobject-sqs.yaml
│   │       │   │   └── setup
│   │       │   │       ├── keda-sqs-policy.json
│   │       │   │       ├── sqs-consumer-deployment.yaml
│   │       │   │       └── sqs-consumer-policy.json
│   │       │   └── prometheus
│   │       │       ├── adot
│   │       │       │   ├── collector.yaml
│   │       │       │   └── configmap.yaml
│   │       │       ├── cloudformation
│   │       │       │   └── requirements.yaml
│   │       │       ├── sample-app
│   │       │       │   ├── Dockerfile
│   │       │       │   ├── app.py
│   │       │       │   ├── app.yaml
│   │       │       │   └── requirements.txt
│   │       │       ├── setup
│   │       │       │   ├── create_requirements.sh
│   │       │       │   └── keda-prometheus-policy.json
│   │       │       └── sigv4
│   │       │           └── keda-sigv4.yaml
│   │       └── setup
│   │           ├── cloud9-instance-enviroment.yaml
│   │           ├── cluster.yaml
│   │           ├── env.sh
│   │           └── tools.sh
│   ├── multi-region-eks
│   │   ├── Operating a multi-regional stateless application using Amazon EKS _ Containers.pdf
│   │   ├── awslb-policy.json
│   │   └── my-readme.md
│   ├── node-scaling
│   │   ├── Eliminate Kubernetes node scaling lag with pod priority and over-provisioning _ Containers.pdf
│   │   └── my-readme.md
│   ├── security
│   │   ├── IRSA-s3-echoer
│   │   │   ├── Introducing fine-grained IAM roles for service accounts _ AWS Open Source Blog.pdf
│   │   │   ├── LICENSE
│   │   │   ├── Makefile
│   │   │   ├── README.md
│   │   │   ├── demo-instrunctions.md
│   │   │   ├── main.go
│   │   │   ├── my-readme.md
│   │   │   ├── s3-echoer-job.yaml
│   │   │   └── s3-echoer-job.yaml.template
│   │   └── amazon-eks-pod-identity-demo
│   │       ├── Amazon EKS Pod Identity.pdf
│   │       ├── code
│   │       │   ├── CODE_OF_CONDUCT.md
│   │       │   ├── CONTRIBUTING.md
│   │       │   ├── LICENSE
│   │       │   ├── README.md
│   │       │   ├── assets
│   │       │   │   ├── eks-pod-identity-arch.jpg
│   │       │   │   └── eks-pod-identity-console.png
│   │       │   ├── aws-secretmgr-policy.json
│   │       │   ├── cluster.yaml
│   │       │   ├── eks-pod-identity-trust-policy.json
│   │       │   ├── s3-app.yaml
│   │       │   └── secretmgr-app-pod.yaml
│   │       ├── my-readme.md
│   │       └── notes
│   │           ├── 1. [CHATGPT] Cross Account Access Using Resource-Based Policy.md
│   │           ├── 2. [CHATGPT] Role chaining.md
│   │           └── amazon-eks-pod-identity.md
│   ├── terraform-aws-eks-blueprints
│   │   ├── ADOPTERS.md
│   │   ├── CODE_OF_CONDUCT.md
│   │   ├── CONTRIBUTING.md
│   │   ├── Enable Private Access to the Amazon EKS Kubernetes API with AWS PrivateLink _ Containers.pdf
│   │   ├── LICENSE
│   │   ├── NOTICE.txt
│   │   ├── README.md
│   │   ├── cspell.config.yaml
│   │   ├── docs
│   │   │   ├── _partials
│   │   │   │   └── destroy.md
│   │   │   ├── cSpell_dict.txt
│   │   │   ├── faq.md
│   │   │   ├── getting-started.md
│   │   │   ├── index.md
│   │   │   ├── internal
│   │   │   │   └── ci.md
│   │   │   ├── patterns
│   │   │   │   ├── agones-game-controller.md
│   │   │   │   ├── aws-vpc-cni-network-policy.md
│   │   │   │   ├── blue-green-upgrade.md
│   │   │   │   ├── external-secrets.md
│   │   │   │   ├── fargate-serverless.md
│   │   │   │   ├── fully-private-cluster.md
│   │   │   │   ├── gitops-getting-started-argocd.md
│   │   │   │   ├── gitops-multi-cluster-hub-spoke-argocd.md
│   │   │   │   ├── ipv6-eks-cluster.md
│   │   │   │   ├── istio.md
│   │   │   │   ├── karpenter-mng.md
│   │   │   │   ├── karpenter.md
│   │   │   │   ├── kubecost.md
│   │   │   │   ├── ml-capacity-block.md
│   │   │   │   ├── multi-tenancy-with-teams.md
│   │   │   │   ├── nvidia-gpu-efa.md
│   │   │   │   ├── private-public-ingress.md
│   │   │   │   ├── privatelink-access.md
│   │   │   │   ├── sso-iam-identity-center.md
│   │   │   │   ├── sso-okta.md
│   │   │   │   ├── stateful.md
│   │   │   │   ├── targeted-odcr.md
│   │   │   │   ├── tls-with-aws-pca-issuer.md
│   │   │   │   ├── vpc-lattice
│   │   │   │   │   └── client-server-communication.md
│   │   │   │   ├── vpc-lattice.md
│   │   │   │   └── wireguard-with-cilium.md
│   │   │   ├── snippets
│   │   │   │   ├── ipv4-prefix-delegation.md
│   │   │   │   └── vpc-cni-custom-networking.md
│   │   │   └── v4-to-v5
│   │   │       ├── addons.md
│   │   │       ├── cluster.md
│   │   │       ├── example
│   │   │       │   ├── README.md
│   │   │       │   ├── main.tf
│   │   │       │   ├── outputs.tf
│   │   │       │   ├── v4.tf
│   │   │       │   ├── v5.tf
│   │   │       │   ├── variables.tf
│   │   │       │   └── versions.tf
│   │   │       ├── motivation.md
│   │   │       └── teams.md
│   │   ├── mkdocs.yml
│   │   ├── my-readme.md
│   │   └── patterns
│   │       ├── agones-game-controller
│   │       │   ├── README.md
│   │       │   ├── helm_values
│   │       │   │   └── agones-values.yaml
│   │       │   ├── main.tf
│   │       │   ├── outputs.tf
│   │       │   ├── test
│   │       │   │   ├── sample-game-server
│   │       │   │   │   ├── fleet.yaml
│   │       │   │   │   └── gameserver.yaml
│   │       │   │   └── xonotic
│   │       │   │       ├── fleet.yaml
│   │       │   │       ├── fleetautoscaler.yaml
│   │       │   │       ├── gameserver.yaml
│   │       │   │       └── gameserverallocator.yaml
│   │       │   ├── variables.tf
│   │       │   └── versions.tf
│   │       ├── aws-vpc-cni-network-policy
│   │       │   ├── README.md
│   │       │   ├── charts
│   │       │   │   └── demo-application
│   │       │   │       ├── Chart.yaml
│   │       │   │       └── templates
│   │       │   │           ├── backend-deploy.yaml
│   │       │   │           ├── backend-svc.yaml
│   │       │   │           ├── client-deploy.yaml
│   │       │   │           ├── client-ns.yaml
│   │       │   │           ├── client-svc.yaml
│   │       │   │           ├── frontend-deploy.yaml
│   │       │   │           ├── frontend-svc.yaml
│   │       │   │           ├── management-ui-deploy.yaml
│   │       │   │           ├── management-ui-ns.yaml
│   │       │   │           ├── management-ui-svc.yaml
│   │       │   │           └── stars-ns.yaml
│   │       │   ├── main.tf
│   │       │   ├── outputs.tf
│   │       │   ├── variables.tf
│   │       │   └── versions.tf
│   │       ├── blue-green-upgrade
│   │       │   ├── README.md
│   │       │   ├── bootstrap
│   │       │   │   ├── addons.yaml
│   │       │   │   └── workloads.yaml
│   │       │   ├── eks-blue
│   │       │   │   ├── README.md
│   │       │   │   ├── main.tf
│   │       │   │   ├── outputs.tf
│   │       │   │   ├── providers.tf
│   │       │   │   └── variables.tf
│   │       │   ├── eks-green
│   │       │   │   ├── README.md
│   │       │   │   ├── main.tf
│   │       │   │   ├── outputs.tf
│   │       │   │   ├── providers.tf
│   │       │   │   └── variables.tf
│   │       │   ├── environment
│   │       │   │   ├── README.md
│   │       │   │   ├── main.tf
│   │       │   │   ├── outputs.tf
│   │       │   │   ├── variables.tf
│   │       │   │   └── versions.tf
│   │       │   ├── modules
│   │       │   │   └── eks_cluster
│   │       │   │       ├── README.md
│   │       │   │       ├── main.tf
│   │       │   │       ├── outputs.tf
│   │       │   │       ├── variables.tf
│   │       │   │       └── versions.tf
│   │       │   ├── static
│   │       │   │   ├── archi-blue-green.png
│   │       │   │   ├── archi-blue.png
│   │       │   │   ├── archi-green.png
│   │       │   │   ├── burnham-records.png
│   │       │   │   ├── burnham-records2.png
│   │       │   │   ├── burnham-records3.png
│   │       │   │   ├── eks-argo.png
│   │       │   │   ├── github-ssh-secret.png
│   │       │   │   └── gitops-bridge.excalidraw.png
│   │       │   ├── tear-down-applications.sh
│   │       │   ├── tear-down.sh
│   │       │   └── terraform.tfvars.example
│   │       ├── external-secrets
│   │       │   ├── README.md
│   │       │   ├── main.tf
│   │       │   ├── outputs.tf
│   │       │   ├── variables.tf
│   │       │   └── versions.tf
│   │       ├── fargate-serverless
│   │       │   ├── README.md
│   │       │   ├── main.tf
│   │       │   ├── outputs.tf
│   │       │   ├── variables.tf
│   │       │   └── versions.tf
│   │       ├── fully-private-cluster
│   │       │   ├── README.md
│   │       │   ├── main.tf
│   │       │   ├── outputs.tf
│   │       │   ├── variables.tf
│   │       │   └── versions.tf
│   │       ├── gitops
│   │       │   ├── getting-started-argocd
│   │       │   │   ├── README.md
│   │       │   │   ├── bootstrap
│   │       │   │   │   ├── addons.yaml
│   │       │   │   │   └── workloads.yaml
│   │       │   │   ├── destroy.sh
│   │       │   │   ├── k8s
│   │       │   │   │   └── game-2048.yaml
│   │       │   │   ├── main.tf
│   │       │   │   ├── outputs.tf
│   │       │   │   ├── static
│   │       │   │   │   ├── gitops-bridge.drawio
│   │       │   │   │   └── gitops-bridge.drawio.png
│   │       │   │   ├── variables.tf
│   │       │   │   └── versions.tf
│   │       │   └── multi-cluster-hub-spoke-argocd
│   │       │       ├── README.md
│   │       │       ├── hub
│   │       │       │   ├── bootstrap
│   │       │       │   │   ├── addons.yaml
│   │       │       │   │   └── workloads.yaml
│   │       │       │   ├── destroy.sh
│   │       │       │   ├── main.tf
│   │       │       │   ├── outputs.tf
│   │       │       │   ├── variables.tf
│   │       │       │   └── versions.tf
│   │       │       ├── spokes
│   │       │       │   ├── deploy.sh
│   │       │       │   ├── destroy.sh
│   │       │       │   ├── main.tf
│   │       │       │   ├── outputs.tf
│   │       │       │   ├── variables.tf
│   │       │       │   ├── versions.tf
│   │       │       │   └── workspaces
│   │       │       │       ├── dev.tfvars
│   │       │       │       ├── prod.tfvars
│   │       │       │       └── staging.tfvars
│   │       │       └── static
│   │       │           ├── gitops-bridge-multi-cluster-hup-spoke.drawio
│   │       │           └── gitops-bridge-multi-cluster-hup-spoke.drawio.png
│   │       ├── ipv6-eks-cluster
│   │       │   ├── README.md
│   │       │   ├── main.tf
│   │       │   ├── outputs.tf
│   │       │   ├── variables.tf
│   │       │   └── versions.tf
│   │       ├── istio
│   │       │   ├── README.md
│   │       │   ├── main.tf
│   │       │   ├── outputs.tf
│   │       │   ├── variables.tf
│   │       │   └── versions.tf
│   │       ├── karpenter
│   │       │   ├── README.md
│   │       │   ├── example.yaml
│   │       │   ├── karpenter.yaml
│   │       │   ├── main.tf
│   │       │   ├── outputs.tf
│   │       │   ├── variables.tf
│   │       │   └── versions.tf
│   │       ├── karpenter-mng
│   │       │   ├── README.md
│   │       │   ├── eks.tf
│   │       │   ├── example.yaml
│   │       │   ├── karpenter.yaml
│   │       │   ├── main.tf
│   │       │   └── vpc.tf
│   │       ├── kubecost
│   │       │   ├── README.md
│   │       │   ├── assets
│   │       │   │   └── screenshot.png
│   │       │   ├── kubecost-values.yaml
│   │       │   ├── main.tf
│   │       │   ├── outputs.tf
│   │       │   ├── run-me-in-24h
│   │       │   │   └── main.tf
│   │       │   ├── variables.tf
│   │       │   └── versions.tf
│   │       ├── ml-capacity-block
│   │       │   ├── README.md
│   │       │   ├── eks.tf
│   │       │   └── main.tf
│   │       ├── multi-tenancy-with-teams
│   │       │   ├── README.md
│   │       │   ├── main.tf
│   │       │   ├── outputs.tf
│   │       │   ├── variables.tf
│   │       │   └── versions.tf
│   │       ├── nvidia-gpu-efa
│   │       │   ├── README.md
│   │       │   ├── eks.tf
│   │       │   └── main.tf
│   │       ├── private-public-ingress
│   │       │   ├── README.md
│   │       │   ├── main.tf
│   │       │   ├── outputs.tf
│   │       │   ├── variables.tf
│   │       │   └── versions.tf
│   │       ├── privatelink-access
│   │       │   ├── README.md
│   │       │   ├── client.tf
│   │       │   ├── eks.tf
│   │       │   ├── lambdas
│   │       │   │   ├── create_eni.py
│   │       │   │   └── delete_eni.py
│   │       │   ├── main.tf
│   │       │   ├── outputs.tf
│   │       │   ├── privatelink.tf
│   │       │   ├── variables.tf
│   │       │   └── versions.tf
│   │       ├── sso-iam-identity-center
│   │       │   ├── README.md
│   │       │   ├── main.tf
│   │       │   ├── outputs.tf
│   │       │   ├── sso.tf
│   │       │   ├── teams.tf
│   │       │   ├── variables.tf
│   │       │   └── versions.tf
│   │       ├── sso-okta
│   │       │   ├── README.md
│   │       │   ├── main.tf
│   │       │   ├── okta.tf
│   │       │   ├── outputs.tf
│   │       │   ├── variables.tf
│   │       │   └── versions.tf
│   │       ├── stateful
│   │       │   ├── README.md
│   │       │   ├── main.tf
│   │       │   ├── outputs.tf
│   │       │   ├── variables.tf
│   │       │   └── versions.tf
│   │       ├── targeted-odcr
│   │       │   ├── README.md
│   │       │   ├── assets
│   │       │   │   ├── odcr-screenshot1.png
│   │       │   │   └── odcr-screenshot2.png
│   │       │   ├── eks.tf
│   │       │   └── main.tf
│   │       ├── tls-with-aws-pca-issuer
│   │       │   ├── README.md
│   │       │   ├── main.tf
│   │       │   ├── outputs.tf
│   │       │   ├── variables.tf
│   │       │   └── versions.tf
│   │       ├── vpc-lattice
│   │       │   ├── README.md
│   │       │   └── client-server-communication
│   │       │       ├── README.md
│   │       │       ├── assets
│   │       │       │   └── diagram.png
│   │       │       ├── charts
│   │       │       │   └── demo-application
│   │       │       │       ├── Chart.yaml
│   │       │       │       └── templates
│   │       │       │           ├── deployment.yaml
│   │       │       │           ├── gateway-class.yaml
│   │       │       │           ├── gateway.yaml
│   │       │       │           ├── httproute.yaml
│   │       │       │           └── service.yaml
│   │       │       ├── client.tf
│   │       │       ├── eks.tf
│   │       │       ├── lattice.tf
│   │       │       ├── main.tf
│   │       │       ├── outputs.tf
│   │       │       ├── variables.tf
│   │       │       └── versions.tf
│   │       └── wireguard-with-cilium
│   │           ├── README.md
│   │           ├── eks.tf
│   │           ├── example.yaml
│   │           └── main.tf
│   ├── terraform-cni-custom-network-sample
│   │   ├── CODE_OF_CONDUCT.md
│   │   ├── CONTRIBUTING.md
│   │   ├── LICENSE
│   │   ├── Leveraging CNI custom networking alongside security groups for pods in Amazon EKS _ Containers.pdf
│   │   ├── README.md
│   │   ├── architecture.jpg
│   │   ├── iam.tf
│   │   ├── locals.tf
│   │   ├── main.tf
│   │   ├── my-readme.md
│   │   ├── providers.tf
│   │   ├── sample-sg.tf
│   │   ├── scripts
│   │   │   ├── network.sh
│   │   │   └── sg-policy.sh
│   │   └── test.yaml
│   └── terraform-eks
│       ├── 1-terraform101
│       │   ├── cloud9-workshop.yaml
│       │   ├── my-notes
│       │   │   ├── 1-Terraform Fundamentals
│       │   │   │   ├── 1-Code Layout.md
│       │   │   │   ├── 2-Locals,Input and Output.md
│       │   │   │   ├── 3-Providers, Resources, and Data Sources.md
│       │   │   │   ├── 4-Terraform State.md
│       │   │   │   ├── 5-Terraform Documentation.md
│       │   │   │   └── 6-Terraform CLI.md
│       │   │   └── Your first terraform project
│       │   │       └── Terraform Project.md
│       │   ├── my-readme.md
│       │   ├── terraform
│       │   │   ├── main.tf
│       │   │   ├── outputs.tf
│       │   │   ├── providers.tf
│       │   │   ├── terraform.tfstate
│       │   │   ├── userdata
│       │   │   │   ├── staging-efs.sh
│       │   │   │   └── staging-wordpress.sh
│       │   │   └── variables.tf
│       │   └── workshop-commands.md
│       ├── 1.1-aws-terraform
│       │   ├── code
│       │   │   └── terraform-aws-vpc
│       │   │       ├── LICENSE
│       │   │       ├── NOTICE.txt
│       │   │       ├── README.md
│       │   │       ├── deploy
│       │   │       │   ├── main.tf
│       │   │       │   ├── outputs.tf
│       │   │       │   └── variables.tf
│       │   │       ├── main.tf
│       │   │       ├── modules
│       │   │       │   └── vpc
│       │   │       │       ├── locals.tf
│       │   │       │       ├── main.tf
│       │   │       │       ├── outputs.tf
│       │   │       │       └── variables.tf
│       │   │       ├── outputs.tf
│       │   │       ├── setup_workspace
│       │   │       │   ├── variables.tf
│       │   │       │   └── workspace.tf
│       │   │       └── variables.tf
│       │   └── my-readme.md
│       ├── 1.2-manage-cloud-resources-with-terraform
│       │   └── my-readme.md
│       ├── 1.3-amplify-with-terraform
│       │   └── my-readme.md
│       ├── 2-eks-terraform-workshop
│       │   ├── TODO.md
│       │   ├── course-code
│       │   │   ├── CODE_OF_CONDUCT.md
│       │   │   ├── CONTRIBUTING.md
│       │   │   ├── Dockerfile
│       │   │   ├── LICENSE
│       │   │   ├── README.md
│       │   │   ├── c9net
│       │   │   │   ├── aws-data.tf -> ../common-files/aws-data.tf
│       │   │   │   ├── backend-c9net.tf -> ../tf-setup/generated/backend-c9net.tf
│       │   │   │   ├── cicd-peering.tf
│       │   │   │   ├── cicd-route-add.tf
│       │   │   │   ├── data-cicdvpc.tf
│       │   │   │   ├── data-defvpc.tf
│       │   │   │   ├── data-params-net.tf -> ../common-files/data-params-net.tf
│       │   │   │   ├── data-rtb-cicd.tf
│       │   │   │   ├── data-sg-c9-instance.tf
│       │   │   │   ├── data-sg-cicd.tf
│       │   │   │   ├── def-peering.tf
│       │   │   │   ├── def-route-add.tf
│       │   │   │   ├── eks-route-add.tf
│       │   │   │   ├── sg-rule-cicd.tf
│       │   │   │   ├── sg-rule-def.tf
│       │   │   │   ├── sg-rule-eks.tf
│       │   │   │   └── vars-main.tf -> ../common-files/vars-main.tf
│       │   │   ├── check
│       │   │   ├── cicd
│       │   │   │   ├── aws_codebuild_project__eks-cicd-build-app.tf
│       │   │   │   ├── aws_codecommit_repository__eksworkshop-app.tf
│       │   │   │   ├── aws_codepipeline__pipe-eksworkshop-app.tf
│       │   │   │   ├── aws_iam_policy__AWSCodePipelineServiceRole-pipe-eksworkshop-app.tf
│       │   │   │   ├── aws_iam_policy__CodeBuildBasePolicy-eks-cicd-build-app-eu-west-1.tf
│       │   │   │   ├── aws_iam_policy__CodeBuildVpcPolicy-eks-cicd-build-app.tf
│       │   │   │   ├── aws_iam_role__AWSCodePipelineServiceRole-pipe-eksworkshop-app.tf
│       │   │   │   ├── aws_iam_role__codebuild-eks-cicd-build-app-service-role.tf
│       │   │   │   ├── aws_iam_role_policy_attachment__AWSCodePipelineServiceRole-eu-west-1-pipe-eksworkshop-app__AWSCodePipelineServiceRole-pipe-eksworkshop-app.tf
│       │   │   │   ├── aws_iam_role_policy_attachment__codebuild-eks-cicd-build-app-service-role__AdministratorAccess.tf
│       │   │   │   ├── aws_iam_role_policy_attachment__codebuild-eks-cicd-build-app-service-role__CodeBuildBasePolicy-eks-cicd-build-app-eu-west-1.tf
│       │   │   │   ├── aws_iam_role_policy_attachment__codebuild-eks-cicd-build-app-service-role__CodeBuildVpcPolicy-eks-cicd-build-app-eu-west-1.tf
│       │   │   │   ├── aws_iam_user.git-user.tf
│       │   │   │   ├── aws_s3_bucket__codepipeline-bucket.tf
│       │   │   │   ├── backend-cicd.tf -> ../tf-setup/generated/backend-cicd.tf
│       │   │   │   ├── codepipeline.json
│       │   │   │   ├── data-aws.tf
│       │   │   │   ├── data-cicdvpc.tf
│       │   │   │   ├── data-eks-vpc.tf
│       │   │   │   ├── data-params-net.tf -> ../common-files/data-params-net.tf
│       │   │   │   ├── data-params-setup.tf -> ../common-files/data-params-setup.tf
│       │   │   │   ├── data-sg-cicd.tf
│       │   │   │   ├── data_kms_alias_s3.tf
│       │   │   │   ├── data_subnet_cicd.tf
│       │   │   │   ├── ecr-pull-through.tf
│       │   │   │   ├── load_ecr.sh
│       │   │   │   ├── null-load_ecr.tf
│       │   │   │   ├── saved
│       │   │   │   │   ├── auth-cicd.sh
│       │   │   │   │   ├── aws_ecr_repository__aws-cli.tf
│       │   │   │   │   ├── aws_ecr_repository__busybox.tf
│       │   │   │   │   ├── aws_ecr_repository__nginx.tf
│       │   │   │   │   ├── aws_ecr_repository__sample-app.tf
│       │   │   │   │   ├── get-bucket-name.sh
│       │   │   │   │   └── load_ecr.sh
│       │   │   │   ├── var-karpenter-version.tf -> ../common-files/var-karpenter-version.tf
│       │   │   │   └── vars-main.tf -> ../common-files/vars-main.tf
│       │   │   ├── cluster
│       │   │   │   ├── auth.sh
│       │   │   │   ├── aws_eks-addons.tf
│       │   │   │   ├── aws_eks_cluster__cluster.tf
│       │   │   │   ├── aws_eks_idp.tf
│       │   │   │   ├── aws_iam_openid_connect_provider.tf.sav
│       │   │   │   ├── backend-cluster.tf -> ../tf-setup/generated/backend-cluster.tf
│       │   │   │   ├── cluster-sg-rule.tf
│       │   │   │   ├── cni.json
│       │   │   │   ├── data-aws.tf
│       │   │   │   ├── data-kms.tf
│       │   │   │   ├── data-params-iam.tf -> ../common-files/data-params-iam.tf
│       │   │   │   ├── data-params-net.tf -> ../common-files/data-params-net.tf
│       │   │   │   ├── data-params-setup.tf -> ../common-files/data-params-setup.tf
│       │   │   │   ├── null_resource.tf
│       │   │   │   ├── ssm-params-cluster.tf
│       │   │   │   ├── test.sh
│       │   │   │   └── vars-main.tf -> ../common-files/vars-main.tf
│       │   │   ├── common-files
│       │   │   │   ├── aws-data.tf
│       │   │   │   ├── aws.tf
│       │   │   │   ├── data-params-cluster.tf
│       │   │   │   ├── data-params-iam.tf
│       │   │   │   ├── data-params-net.tf
│       │   │   │   ├── data-params-setup.tf
│       │   │   │   ├── var-karpenter-version.tf
│       │   │   │   └── vars-main.tf
│       │   │   ├── extra
│       │   │   │   ├── eks-cidr2
│       │   │   │   │   ├── annotate-nodes2.sh
│       │   │   │   │   ├── aws-data.tf -> ../../common-files/aws-data.tf
│       │   │   │   │   ├── aws.tf -> ../../common-files/aws.tf
│       │   │   │   │   ├── data-eks-cluster.tf
│       │   │   │   │   ├── data-params-cluster.tf -> ../../common-files/data-params-cluster.tf
│       │   │   │   │   ├── data-params-net.tf -> ../../common-files/data-params-net.tf
│       │   │   │   │   ├── data-subnet-p.tf
│       │   │   │   │   ├── null-cidr.tf
│       │   │   │   │   ├── reannotate-nodes.sh
│       │   │   │   │   └── vars-main.tf -> ../../common-files/vars-main.tf
│       │   │   │   ├── fargate
│       │   │   │   │   ├── aws-data.tf -> ../../common-files/aws-data.tf
│       │   │   │   │   ├── aws.tf -> ../../common-files/aws.tf
│       │   │   │   │   ├── data-eks-cluster.tf
│       │   │   │   │   ├── data-params-cluster.tf -> ../../common-files/data-params-cluster.tf
│       │   │   │   │   ├── data-params-net.tf -> ../../common-files/data-params-net.tf
│       │   │   │   │   ├── data-params-setup.tf -> ../../common-files/data-params-setup.tf
│       │   │   │   │   ├── data-subnet-i.tf
│       │   │   │   │   ├── fargate-execution-role.tf
│       │   │   │   │   ├── fargate_profile.tf
│       │   │   │   │   ├── logging-permissions.json
│       │   │   │   │   ├── logging-policy.tf
│       │   │   │   │   └── vars-main.tf -> ../../common-files/vars-main.tf
│       │   │   │   ├── fargateapp
│       │   │   │   │   ├── DEBUG.md
│       │   │   │   │   ├── README.md
│       │   │   │   │   ├── aws-data.tf -> ../../common-files/aws-data.tf
│       │   │   │   │   ├── aws.tf -> ../../common-files/aws.tf
│       │   │   │   │   ├── cli-test.sh
│       │   │   │   │   ├── configmap-logging.tf
│       │   │   │   │   ├── k8s.tf
│       │   │   │   │   ├── sampleapp-deployment.tf
│       │   │   │   │   ├── sampleapp-namespace.tf
│       │   │   │   │   ├── sampleapp-service.tf
│       │   │   │   │   └── vars-main.tf -> ../../common-files/vars-main.tf
│       │   │   │   ├── nodeg2
│       │   │   │   │   ├── aws-data.tf -> ../../common-files/aws-data.tf
│       │   │   │   │   ├── aws.tf -> ../../common-files/aws.tf
│       │   │   │   │   ├── data-eks-cluster.tf
│       │   │   │   │   ├── data-params-cluster.tf -> ../../common-files/data-params-cluster.tf
│       │   │   │   │   ├── data-params-iam.tf -> ../../common-files/data-params-iam.tf
│       │   │   │   │   ├── data-params-net.tf -> ../../common-files/data-params-net.tf
│       │   │   │   │   ├── launch_template.tf
│       │   │   │   │   ├── nodeg2.tf
│       │   │   │   │   ├── outputs.tf
│       │   │   │   │   ├── ssm-param-ami.tf
│       │   │   │   │   ├── user_data.tf
│       │   │   │   │   └── vars-main.tf -> ../../common-files/vars-main.tf
│       │   │   │   └── sampleapp2
│       │   │   │       ├── 2048_deployment-ng1.yml
│       │   │   │       ├── 2048_deployment-ng2.yml
│       │   │   │       ├── 2048_ingress.yml.orig
│       │   │   │       ├── 2048_ingress1.yml
│       │   │   │       ├── 2048_ingress2.yml
│       │   │   │       ├── 2048_ingresses.yaml
│       │   │   │       ├── 2048_namespace1.yml
│       │   │   │       ├── 2048_namespace2.yml
│       │   │   │       ├── 2048_service1.yml
│       │   │   │       ├── 2048_service2.yml
│       │   │   │       ├── README.md
│       │   │   │       ├── app-setup.sh
│       │   │   │       ├── aws-data.tf -> ../../common-files/aws-data.tf
│       │   │   │       ├── aws.tf -> ../../common-files/aws.tf
│       │   │   │       ├── buildspec.yml
│       │   │   │       ├── delete-app.sh
│       │   │   │       ├── deploy-app.sh
│       │   │   │       ├── do-app-push.sh
│       │   │   │       ├── k8s.tf
│       │   │   │       ├── sampleapp-deployment.tf
│       │   │   │       ├── sampleapp-namespace.tf
│       │   │   │       ├── sampleapp-service.tf
│       │   │   │       ├── sampleapp_ingresses.tf
│       │   │   │       ├── subvar.sh
│       │   │   │       └── vars-main.tf -> ../../common-files/vars-main.tf
│       │   │   ├── git
│       │   │   │   ├── cleanup.sh
│       │   │   │   └── test.sh
│       │   │   ├── iam
│       │   │   │   ├── aws_iam_role__cluster-ServiceRole.tf
│       │   │   │   ├── aws_iam_role__nodegroup-NodeInstanceRole.tf
│       │   │   │   ├── aws_iam_role_policy__cluster-ServiceRole-PolicyELBPermissions.tf
│       │   │   │   ├── aws_iam_role_policy__cluster-ServiceRole__PolicyCloudWatchMetrics.tf
│       │   │   │   ├── aws_iam_role_policy__nodegroup-NodeInstanceRole-PolicyALBIngress.tf
│       │   │   │   ├── aws_iam_role_policy__nodegroup-NodeInstanceRole-PolicyAutoScaling.tf
│       │   │   │   ├── aws_iam_role_policy__nodegroup-NodeInstanceRole-PolicyCertManagerChangeSet.tf
│       │   │   │   ├── aws_iam_role_policy__nodegroup-NodeInstanceRole-PolicyCertManagerGetChange.tf
│       │   │   │   ├── aws_iam_role_policy__nodegroup-NodeInstanceRole-PolicyCertManagerHostedZones.tf
│       │   │   │   ├── aws_iam_role_policy__nodegroup-NodeInstanceRole-PolicyEBS.tf
│       │   │   │   ├── aws_iam_role_policy__nodegroup-NodeInstanceRole-PolicyEFS.tf
│       │   │   │   ├── aws_iam_role_policy__nodegroup-NodeInstanceRole-PolicyEFSEC2.tf
│       │   │   │   ├── aws_iam_role_policy_attachment__cluster-ServiceRole-AmazonEKSClusterPolicy.tf
│       │   │   │   ├── aws_iam_role_policy_attachment__cluster-ServiceRole-AmazonEKSVPCResourceController.tf
│       │   │   │   ├── aws_iam_role_policy_attachment__nodegroup-NodeInstanceRole-AmazonEC2ContainerRegistryReadOnly.tf
│       │   │   │   ├── aws_iam_role_policy_attachment__nodegroup-NodeInstanceRole-AmazonEKSWorkerNodePolicy.tf
│       │   │   │   ├── aws_iam_role_policy_attachment__nodegroup-NodeInstanceRole-AmazonEKS_CNI_Policy.tf
│       │   │   │   ├── aws_iam_role_policy_attachment__nodegroup-NodeInstanceRole-AmazonSSMManagedInstanceCore.tf
│       │   │   │   ├── aws_iam_role_policy_attachment__nodegroup-NodeInstanceRole-CloudWatchAgentServerPolicy.tf
│       │   │   │   ├── aws_key_pair__eksworkshop.tf
│       │   │   │   ├── backend-iam.tf -> ../tf-setup/generated/backend-iam.tf
│       │   │   │   ├── data-params-setup.tf -> ../common-files/data-params-setup.tf
│       │   │   │   ├── ssm-params-iam.tf
│       │   │   │   └── vars-main.tf -> ../common-files/vars-main.tf
│       │   │   ├── lb2
│       │   │   │   ├── README.md
│       │   │   │   ├── aws-data.tf -> ../common-files/aws-data.tf
│       │   │   │   ├── aws.tf -> ../common-files/aws.tf
│       │   │   │   ├── aws_iam_policy-lb2.tf
│       │   │   │   ├── data-eks-cluster.tf
│       │   │   │   ├── data-params-cluster.tf -> ../common-files/data-params-cluster.tf
│       │   │   │   ├── helm_loadbalancer.tf
│       │   │   │   ├── iam_policy.json
│       │   │   │   ├── notused
│       │   │   │   │   ├── cleanup-man.sh
│       │   │   │   │   ├── cleanup.sh
│       │   │   │   │   ├── crds.yaml
│       │   │   │   │   ├── install.sh
│       │   │   │   │   ├── post-policy-man.sh
│       │   │   │   │   └── post-policy-man2.sh
│       │   │   │   ├── null_destoy.tf
│       │   │   │   ├── null_policy.tf
│       │   │   │   ├── null_post_policy.tf
│       │   │   │   ├── post-policy.sh
│       │   │   │   └── vars-main.tf -> ../common-files/vars-main.tf
│       │   │   ├── net
│       │   │   │   ├── aws-data.tf -> ../common-files/aws-data.tf
│       │   │   │   ├── aws_eip__eipalloc-cicd-natgw.tf
│       │   │   │   ├── aws_internet_gateway__eks-cicd.tf
│       │   │   │   ├── aws_nat_gateway__eks-cicd.tf
│       │   │   │   ├── aws_route_table__private1.tf
│       │   │   │   ├── aws_route_table__public1.tf
│       │   │   │   ├── aws_route_table__rtb-isol.tf
│       │   │   │   ├── aws_route_table__rtb-p1.tf
│       │   │   │   ├── aws_route_table__rtb-p2.tf
│       │   │   │   ├── aws_route_table__rtb-p3.tf
│       │   │   │   ├── aws_route_table_association__private1.tf
│       │   │   │   ├── aws_route_table_association__public1.tf
│       │   │   │   ├── aws_route_table_association__rtbassoc-i1.tf
│       │   │   │   ├── aws_route_table_association__rtbassoc-i2.tf
│       │   │   │   ├── aws_route_table_association__rtbassoc-i3.tf
│       │   │   │   ├── aws_route_table_association__rtbassoc-p1.tf
│       │   │   │   ├── aws_route_table_association__rtbassoc-p2.tf
│       │   │   │   ├── aws_route_table_association__rtbassoc-p3.tf
│       │   │   │   ├── aws_security_group__allnodes-sg.tf
│       │   │   │   ├── aws_security_group__cluster-sg.tf
│       │   │   │   ├── aws_security_group__sg-eks-cicd.tf
│       │   │   │   ├── aws_subnet__eks-cicd-private1.tf
│       │   │   │   ├── aws_subnet__eks-cicd-public1.tf
│       │   │   │   ├── aws_vpc__eks-cicd.tf
│       │   │   │   ├── aws_vpc_ipv4_cidr_block_association__vpc-cidr-assoc.tf
│       │   │   │   ├── backend-net.tf -> ../tf-setup/generated/backend-net.tf
│       │   │   │   ├── data-params-setup.tf -> ../common-files/data-params-setup.tf
│       │   │   │   ├── sg-rules.tf
│       │   │   │   ├── ssm-params-net.tf
│       │   │   │   ├── subnets-eks.tf
│       │   │   │   ├── vars-main.tf -> ../common-files/vars-main.tf
│       │   │   │   ├── vpc-cluster.tf
│       │   │   │   └── vpce.tf
│       │   │   ├── nodeg
│       │   │   │   ├── annotate-nodes.sh
│       │   │   │   ├── auth-cicd.sh
│       │   │   │   ├── aws_eks-addons.tf.sav
│       │   │   │   ├── aws_eks_node_group_ng1.tf
│       │   │   │   ├── backend-nodeg.tf -> ../tf-setup/generated/backend-nodeg.tf
│       │   │   │   ├── c9-auth.sh
│       │   │   │   ├── data-aws.tf
│       │   │   │   ├── data-eks-cluster.tf
│       │   │   │   ├── data-params-cluster.tf -> ../common-files/data-params-cluster.tf
│       │   │   │   ├── data-params-iam.tf -> ../common-files/data-params-iam.tf
│       │   │   │   ├── data-params-net.tf -> ../common-files/data-params-net.tf
│       │   │   │   ├── data-params-setup.tf -> ../common-files/data-params-setup.tf
│       │   │   │   ├── data-subnet-i.tf
│       │   │   │   ├── launch_template.tf
│       │   │   │   ├── null_annotate.tf
│       │   │   │   ├── null_resource.tf
│       │   │   │   ├── outputs.tf
│       │   │   │   ├── reannotate-nodes.sh
│       │   │   │   ├── ssm-param-ami.tf
│       │   │   │   ├── user_data.tf
│       │   │   │   ├── vars-dynamodb.tf.sav -> ../tf-setup/vars-dynamodb.tf
│       │   │   │   └── vars-main.tf -> ../common-files/vars-main.tf
│       │   │   ├── primer
│       │   │   │   ├── autogen-tf.sh
│       │   │   │   ├── challenge-1
│       │   │   │   │   ├── aws.tf
│       │   │   │   │   ├── data-instance-10-1.tf
│       │   │   │   │   ├── data-instance.tf
│       │   │   │   │   ├── data-sg-10-1.tf
│       │   │   │   │   ├── data-sg-def.tf
│       │   │   │   │   ├── data-subnet-priv-10-1.tf
│       │   │   │   │   ├── data-subnet.tf
│       │   │   │   │   ├── data-vpc-10-1.tf
│       │   │   │   │   ├── data-vpc.tf
│       │   │   │   │   ├── data_mytgw.tf
│       │   │   │   │   ├── def-rtb.tf
│       │   │   │   │   ├── deftgw-attach.tf
│       │   │   │   │   ├── labsol.sh
│       │   │   │   │   ├── mysg10ingress-icmp.tf
│       │   │   │   │   ├── mysgingress-icmp.tf
│       │   │   │   │   ├── outputs.tf
│       │   │   │   │   ├── rt-entries-10-1.tf
│       │   │   │   │   ├── rt-entries.tf
│       │   │   │   │   ├── rtb-10-1.tf
│       │   │   │   │   └── vars.tf
│       │   │   │   ├── tflab1
│       │   │   │   │   ├── aws.tf
│       │   │   │   │   ├── my-eip.tf
│       │   │   │   │   ├── subnets.tf
│       │   │   │   │   ├── variables.tf
│       │   │   │   │   ├── vpc-10-1.tf
│       │   │   │   │   └── vpc.tf
│       │   │   │   ├── tflab1-complete
│       │   │   │   │   ├── aws.tf
│       │   │   │   │   ├── az.tf
│       │   │   │   │   ├── my-eip.tf
│       │   │   │   │   ├── subnets.tf
│       │   │   │   │   └── vpc-192.168.tf
│       │   │   │   └── tflab2
│       │   │   │       ├── ami.tf
│       │   │   │       ├── aws.tf
│       │   │   │       ├── az.tf
│       │   │   │       ├── igw.tf
│       │   │   │       ├── my-eip.tf
│       │   │   │       ├── my-privrtassociation.tf
│       │   │   │       ├── my-pubrtassociation.tf
│       │   │   │       ├── my_instance.tf
│       │   │   │       ├── mysg.tf
│       │   │   │       ├── mysgingress-80.tf
│       │   │   │       ├── mysgingress-icmp.tf
│       │   │   │       ├── mysgrule-egress-all.tf
│       │   │   │       ├── nat_gateway.tf
│       │   │   │       ├── rtb1-natgw-tgw.tf
│       │   │   │       ├── rtb2-igw.tf
│       │   │   │       ├── subnets.tf
│       │   │   │       ├── user_data.sh
│       │   │   │       ├── variables.tf
│       │   │   │       └── vpc.tf
│       │   │   ├── sampleapp
│       │   │   │   ├── README.md
│       │   │   │   ├── app-setup.sh
│       │   │   │   ├── backend-sampleapp.tf -> ../tf-setup/generated/backend-sampleapp.tf
│       │   │   │   ├── buildspec.yml
│       │   │   │   ├── cleanup.sh
│       │   │   │   ├── do-app-push.sh
│       │   │   │   ├── k8s.tf
│       │   │   │   ├── notused
│       │   │   │   │   ├── 2048_deployment.yml
│       │   │   │   │   ├── 2048_ingress.yml
│       │   │   │   │   ├── 2048_namespace.yml
│       │   │   │   │   ├── 2048_service.yml
│       │   │   │   │   ├── auth-cicd.sh
│       │   │   │   │   ├── buildspec-debug.yml
│       │   │   │   │   ├── delete-app.sh
│       │   │   │   │   ├── delete-ingress.sh
│       │   │   │   │   ├── deploy-app.sh
│       │   │   │   │   ├── deploy-ingress.sh
│       │   │   │   │   ├── null-auth-cicd.tf
│       │   │   │   │   ├── sampleapp.tf
│       │   │   │   │   └── subvar.sh
│       │   │   │   ├── null-cleanup.tf
│       │   │   │   ├── sampleapp-deployment.tf
│       │   │   │   ├── sampleapp-ingress.tf
│       │   │   │   ├── sampleapp-namespace.tf
│       │   │   │   ├── sampleapp-service.tf
│       │   │   │   ├── vars-dynamodb.tf.sav -> ../tf-setup/vars-dynamodb.tf
│       │   │   │   └── vars-main.tf -> ../common-files/vars-main.tf
│       │   │   ├── setup
│       │   │   └── tf-setup
│       │   │       ├── aws-data.tf -> ../common-files/aws-data.tf
│       │   │       ├── aws.tf -> ../common-files/aws.tf
│       │   │       ├── dot-terraform.rc
│       │   │       ├── dynamodb-tables.tf
│       │   │       ├── gen-backend.sh
│       │   │       ├── generated
│       │   │       │   ├── backend-c9net.tf
│       │   │       │   ├── backend-cicd.tf
│       │   │       │   ├── backend-cluster.tf
│       │   │       │   ├── backend-eks-cidr.tf
│       │   │       │   ├── backend-iam.tf
│       │   │       │   ├── backend-k8scicd.tf
│       │   │       │   ├── backend-net.tf
│       │   │       │   ├── backend-nodeg.tf
│       │   │       │   ├── backend-sampleapp.tf
│       │   │       │   ├── backend-tf-setup.tf
│       │   │       │   ├── base.tf
│       │   │       │   └── data-params.tf
│       │   │       ├── kms.tf
│       │   │       ├── null_resource.tf
│       │   │       ├── output.tf
│       │   │       ├── rand.tf
│       │   │       ├── remotes
│       │   │       │   ├── remote-cluster.tf.sav
│       │   │       │   ├── remote-iam.tf.sav
│       │   │       │   ├── remote-net.tf.sav
│       │   │       │   ├── remote-nodeg.tf.sav
│       │   │       │   └── remote-tf-setup.tf.sav
│       │   │       ├── s3-bucket.tf
│       │   │       ├── ssm-params-setup.tf
│       │   │       ├── vars-dynamodb.tf
│       │   │       └── vars-main.tf -> ../common-files/vars-main.tf
│       │   ├── course-notes
│       │   │   ├── 1-cloud9 env.md
│       │   │   ├── 4-Terraform primer.md
│       │   │   ├── 5. Creating a private EKS Cluster with Terraform.md
│       │   │   ├── 5.7 EKS control Plane creation.md
│       │   │   ├── 5.8 Create a customized managed Node Group.md
│       │   │   ├── Lab-1.md
│       │   │   ├── issues-faced.md
│       │   │   ├── lab-2.md
│       │   │   └── workshop-quickstart.md
│       │   └── my-readme.md
│       ├── eks-installation-patterns
│       │   ├── eks-demo-option1
│       │   │   ├── Optimize IP addresses usage by pods in your Amazon EKS cluster _ Containers.pdf
│       │   │   ├── deployment.yaml
│       │   │   ├── eks-control-plane.yaml
│       │   │   ├── eks-data-plane.yaml
│       │   │   ├── eks-vpc-secondary.yaml
│       │   │   ├── eks-vpc.yaml
│       │   │   ├── my-readme.md
│       │   │   └── quick-start.md
│       │   ├── eks-demo-option2a-cni
│       │   │   └── my-readme.md
│       │   ├── eks-demo-option2b-cni
│       │   │   ├── CODE_OF_CONDUCT.md
│       │   │   ├── CONTRIBUTING.md
│       │   │   ├── LICENSE
│       │   │   ├── Leveraging CNI custom networking alongside security groups for pods in Amazon EKS _ Containers.pdf
│       │   │   ├── README.md
│       │   │   ├── architecture.jpg
│       │   │   ├── iam.tf
│       │   │   ├── locals.tf
│       │   │   ├── main.tf
│       │   │   ├── my-readme.md
│       │   │   ├── providers.tf
│       │   │   ├── sample-sg.tf
│       │   │   ├── scripts
│       │   │   │   ├── network.sh
│       │   │   │   └── sg-policy.sh
│       │   │   └── test.yaml
│       │   └── eks-installation-patterns.md
│       ├── eks-topics-2-explore.md
│       ├── my-aws-notes.md
│       ├── my-terraform-snippets.md
│       ├── terraform-commands.md
│       ├── terraform-docs.md
│       └── trouble-shooting-guide.md
├── eventbridge-events-to-vpc
│   ├── CODE_OF_CONDUCT.md
│   ├── CONTRIBUTING.md
│   ├── LICENSE
│   ├── README.md
│   ├── Sending Amazon EventBridge events to private endpoints in a VPC _ AWS Compute Blog.pdf
│   ├── diagrams
│   │   ├── load-balancer.jpg
│   │   ├── sam-deploy.jpg
│   │   ├── secrets-manager-endpoint.jpg
│   │   ├── security-groups.jpg
│   │   ├── solution-architecture.jpg
│   │   └── subnets.jpg
│   ├── event-relay
│   │   ├── example-event.json
│   │   ├── src
│   │   │   ├── __init__.py
│   │   │   ├── event_relay_function
│   │   │   │   ├── __init__.py
│   │   │   │   ├── app.py
│   │   │   │   └── requirements.txt
│   │   │   └── tests
│   │   │       ├── __init__.py
│   │   │       └── unit
│   │   │           ├── __init__.py
│   │   │           └── test_handler.py
│   │   └── template.yaml
│   ├── example-vpc-application
│   │   ├── Dockerfile
│   │   ├── app.py
│   │   ├── eksctl_config.yaml
│   │   ├── iam_policy.json
│   │   ├── manifests
│   │   │   ├── deployment.yaml
│   │   │   ├── kustomization.yaml
│   │   │   └── vendor
│   │   │       ├── aws-alb-controller.yaml
│   │   │       └── cert-manager.yaml
│   │   ├── requirements.txt
│   │   └── tests
│   │       ├── __init__.py
│   │       └── unit
│   │           ├── __init__.py
│   │           └── test_app.py
│   └── my-readme.md
├── fargate
│   ├── amazon-msk-java-app-cdk
│   │   ├── Building an Apache Kafka data processing Java application using the AWS CDK _ AWS Developer Tools Blog.pdf
│   │   ├── CODE_OF_CONDUCT.md
│   │   ├── CONTRIBUTING.md
│   │   ├── LICENSE
│   │   ├── README.md
│   │   ├── amazon-msk-java-app-cdk
│   │   │   ├── bin
│   │   │   │   └── amazon-msk-java-app-cdk.ts
│   │   │   ├── cdk.json
│   │   │   ├── lambda
│   │   │   │   ├── kafka-topic-handler.ts
│   │   │   │   ├── package.json
│   │   │   │   └── transaction-handler.ts
│   │   │   ├── lib
│   │   │   │   ├── dynamodb-stack.ts
│   │   │   │   ├── fargate-stack.ts
│   │   │   │   ├── kafka-stack.ts
│   │   │   │   ├── kafka-topic-stack.ts
│   │   │   │   ├── lambda-stack.ts
│   │   │   │   └── vpc-stack.ts
│   │   │   ├── package.json
│   │   │   └── tsconfig.json
│   │   ├── consumer
│   │   │   ├── docker
│   │   │   │   └── Dockerfile
│   │   │   ├── pom.xml
│   │   │   └── src
│   │   │       ├── main
│   │   │       │   ├── java
│   │   │       │   │   └── amazon
│   │   │       │   │       └── aws
│   │   │       │   │           └── samples
│   │   │       │   │               └── kafka
│   │   │       │   │                   ├── ApplicationConfig.java
│   │   │       │   │                   ├── BalanceException.java
│   │   │       │   │                   ├── ConsumerApplication.java
│   │   │       │   │                   ├── DynamoDBService.java
│   │   │       │   │                   ├── KafkaConsumer.java
│   │   │       │   │                   ├── KafkaConsumerProperties.java
│   │   │       │   │                   └── Message.java
│   │   │       │   └── resources
│   │   │       │       └── application.yaml
│   │   │       └── test
│   │   │           └── java
│   │   │               └── amazon
│   │   │                   └── aws
│   │   │                       └── samples
│   │   │                           └── kafka
│   │   │                               └── KafkaConsumerTest.java
│   │   ├── doc
│   │   │   ├── architecture.drawio
│   │   │   └── architecture.png
│   │   ├── my-readme.md
│   │   └── scripts
│   │       ├── deploy.sh
│   │       └── destroy.sh
│   └── aws-batch-processing-job-repo
│       ├── AWS Batch Application Orchestration using AWS Fargate _ AWS Developer Tools Blog.pdf
│       ├── CODE_OF_CONDUCT.md
│       ├── CONTRIBUTING.md
│       ├── LICENSE
│       ├── Orchestrating an application process with AWS Batch.png
│       ├── README.md
│       ├── aws-fargate-batch-application.png
│       ├── cleanup.sh
│       ├── config
│       │   └── buildspec.yml
│       ├── exec.sh
│       ├── exec_ec2.sh
│       ├── my-readme.md
│       ├── sample
│       │   └── sample.csv
│       ├── src
│       │   ├── Dockerfile
│       │   └── batch_processor.py
│       └── template
│           ├── template.yaml
│           └── template_ec2.yaml
├── global-accelerator
│   └── basic-global-accelerator
│       ├── my-readme.md
│       └── templates
│           ├── 1-vpc-alb-scale-httpd.yaml
│           └── 2-vpc-alb-scale-httpd-ga.yaml
├── http-api-aws-fargate-cdk
│   ├── Building HTTP API-based services using Amazon API Gateway, AWS PrivateLink and AWS Fargate _ Containers.pdf
│   ├── CODE_OF_CONDUCT.md
│   ├── CONTRIBUTING.md
│   ├── LICENSE
│   ├── README.md
│   ├── cdk
│   │   └── singleAccount
│   │       ├── README.md
│   │       ├── bin
│   │       │   └── app.ts
│   │       ├── cdk.json
│   │       ├── jest.config.js
│   │       ├── lib
│   │       │   ├── fargate-vpclink-stack.ts
│   │       │   └── httpApi-stack.ts
│   │       ├── package.json
│   │       └── tsconfig.json
│   ├── my-readme.md
│   └── src
│       ├── author-service
│       │   ├── Dockerfile
│       │   ├── healthcheck.html
│       │   ├── index.js
│       │   └── package.json
│       └── book-service
│           ├── Dockerfile
│           ├── healthcheck.html
│           ├── index.js
│           └── package.json
├── hub-and-spoke-using-endpoints
│   ├── centralised-egress-proxy
│   │   ├── CODE_OF_CONDUCT.md
│   │   ├── CONTRIBUTING.md
│   │   ├── Dockerfile
│   │   ├── LICENSE
│   │   ├── README.md
│   │   ├── allowedlist.txt
│   │   ├── centralised_egress_proxy.yaml
│   │   ├── entrypoint.sh
│   │   └── squid.conf
│   └── my-readme.md
├── kms
│   └── enable-bucket-key-example-main
│       ├── Reduce encryption costs by using Amazon S3 Bucket Keys on existing objects _ AWS Storage Blog.pdf
│       ├── enable-bucket-key-example-template.cloudformation.yaml
│       ├── lambda_function.py
│       ├── my-readme.md
│       └── package_lambda.sh
├── kubernetes
│   ├── kubernetes-by-elton
│   │   ├── CNAME
│   │   ├── LICENSE
│   │   ├── README.md
│   │   ├── _config.yml
│   │   ├── hackathon
│   │   │   ├── README.md
│   │   │   ├── files
│   │   │   │   ├── grafana-dashboard.json
│   │   │   │   ├── helm
│   │   │   │   │   ├── smoke-test.yaml
│   │   │   │   │   └── uat.yaml
│   │   │   │   └── kibana-dashboard.ndjson
│   │   │   ├── project
│   │   │   │   ├── Jenkinsfile
│   │   │   │   └── src
│   │   │   │       ├── db
│   │   │   │       │   ├── postgres
│   │   │   │       │   │   ├── Dockerfile
│   │   │   │       │   │   └── init-products-db.sh
│   │   │   │       │   └── postgres-replicated
│   │   │   │       │       ├── Dockerfile
│   │   │   │       │       ├── conf
│   │   │   │       │       │   ├── pg_hba.conf
│   │   │   │       │       │   ├── primary.conf
│   │   │   │       │       │   └── standby.conf
│   │   │   │       │       ├── initdb
│   │   │   │       │       │   ├── create-replica-user.sh
│   │   │   │       │       │   └── init-products-db.sh
│   │   │   │       │       └── scripts
│   │   │   │       │           ├── initialize-replication.sh
│   │   │   │       │           ├── startup.sh
│   │   │   │       │           └── wait-service.sh
│   │   │   │       ├── products-api
│   │   │   │       │   └── java
│   │   │   │       │       ├── Dockerfile
│   │   │   │       │       ├── pom.xml
│   │   │   │       │       └── src
│   │   │   │       │           └── main
│   │   │   │       │               ├── java
│   │   │   │       │               │   └── com
│   │   │   │       │               │       └── widgetario
│   │   │   │       │               │           ├── Application.java
│   │   │   │       │               │           ├── configuration
│   │   │   │       │               │           │   └── RegistryConfiguration.java
│   │   │   │       │               │           ├── controllers
│   │   │   │       │               │           │   └── ProductsController.java
│   │   │   │       │               │           ├── models
│   │   │   │       │               │           │   └── Product.java
│   │   │   │       │               │           ├── repositories
│   │   │   │       │               │           │   └── ProductRepository.java
│   │   │   │       │               │           └── startup
│   │   │   │       │               │               └── ApplicationStartup.java
│   │   │   │       │               └── resources
│   │   │   │       │                   └── application.properties
│   │   │   │       ├── stock-api
│   │   │   │       │   └── golang
│   │   │   │       │       ├── Dockerfile
│   │   │   │       │       └── src
│   │   │   │       │           ├── go.mod
│   │   │   │       │           ├── handlers
│   │   │   │       │           │   └── handlers.go
│   │   │   │       │           ├── main.go
│   │   │   │       │           ├── middleware
│   │   │   │       │           │   └── prometheus.go
│   │   │   │       │           ├── models
│   │   │   │       │           │   └── models.go
│   │   │   │       │           └── router
│   │   │   │       │               └── router.go
│   │   │   │       └── web
│   │   │   │           └── dotnet
│   │   │   │               ├── Dockerfile
│   │   │   │               ├── Widgetario.Web
│   │   │   │               │   ├── Controllers
│   │   │   │               │   │   ├── HomeController.cs
│   │   │   │               │   │   └── UpController.cs
│   │   │   │               │   ├── Models
│   │   │   │               │   │   ├── ErrorViewModel.cs
│   │   │   │               │   │   ├── Product.cs
│   │   │   │               │   │   ├── ProductStock.cs
│   │   │   │               │   │   └── ProductViewModel.cs
│   │   │   │               │   ├── Program.cs
│   │   │   │               │   ├── Properties
│   │   │   │               │   │   └── launchSettings.json
│   │   │   │               │   ├── Services
│   │   │   │               │   │   ├── ProductService.cs
│   │   │   │               │   │   └── StockService.cs
│   │   │   │               │   ├── Startup.cs
│   │   │   │               │   ├── Views
│   │   │   │               │   │   ├── Home
│   │   │   │               │   │   │   └── Index.cshtml
│   │   │   │               │   │   ├── Shared
│   │   │   │               │   │   │   ├── Error.cshtml
│   │   │   │               │   │   │   ├── _Layout.cshtml
│   │   │   │               │   │   │   └── _ValidationScriptsPartial.cshtml
│   │   │   │               │   │   ├── _ViewImports.cshtml
│   │   │   │               │   │   └── _ViewStart.cshtml
│   │   │   │               │   ├── Widgetario.Web.csproj
│   │   │   │               │   ├── appsettings.json
│   │   │   │               │   ├── config
│   │   │   │               │   │   └── serilog.json
│   │   │   │               │   └── wwwroot
│   │   │   │               │       ├── css
│   │   │   │               │       │   ├── site.css
│   │   │   │               │       │   └── themes
│   │   │   │               │       │       ├── dark.css
│   │   │   │               │       │       └── light.css
│   │   │   │               │       ├── favicon.ico
│   │   │   │               │       ├── img
│   │   │   │               │       │   ├── logo.png
│   │   │   │               │       │   ├── logo2-small.png
│   │   │   │               │       │   └── logo2.png
│   │   │   │               │       ├── js
│   │   │   │               │       │   └── site.js
│   │   │   │               │       └── lib
│   │   │   │               │           ├── bootstrap
│   │   │   │               │           │   ├── LICENSE
│   │   │   │               │           │   └── dist
│   │   │   │               │           │       ├── css
│   │   │   │               │           │       │   ├── bootstrap-grid.css
│   │   │   │               │           │       │   ├── bootstrap-grid.css.map
│   │   │   │               │           │       │   ├── bootstrap-grid.min.css
│   │   │   │               │           │       │   ├── bootstrap-grid.min.css.map
│   │   │   │               │           │       │   ├── bootstrap-reboot.css
│   │   │   │               │           │       │   ├── bootstrap-reboot.css.map
│   │   │   │               │           │       │   ├── bootstrap-reboot.min.css
│   │   │   │               │           │       │   ├── bootstrap-reboot.min.css.map
│   │   │   │               │           │       │   ├── bootstrap.css
│   │   │   │               │           │       │   ├── bootstrap.css.map
│   │   │   │               │           │       │   ├── bootstrap.min.css
│   │   │   │               │           │       │   └── bootstrap.min.css.map
│   │   │   │               │           │       └── js
│   │   │   │               │           │           ├── bootstrap.bundle.js
│   │   │   │               │           │           ├── bootstrap.bundle.js.map
│   │   │   │               │           │           ├── bootstrap.bundle.min.js
│   │   │   │               │           │           ├── bootstrap.bundle.min.js.map
│   │   │   │               │           │           ├── bootstrap.js
│   │   │   │               │           │           ├── bootstrap.js.map
│   │   │   │               │           │           ├── bootstrap.min.js
│   │   │   │               │           │           └── bootstrap.min.js.map
│   │   │   │               │           ├── jquery
│   │   │   │               │           │   ├── LICENSE.txt
│   │   │   │               │           │   └── dist
│   │   │   │               │           │       ├── jquery.js
│   │   │   │               │           │       ├── jquery.min.js
│   │   │   │               │           │       └── jquery.min.map
│   │   │   │               │           ├── jquery-validation
│   │   │   │               │           │   ├── LICENSE.md
│   │   │   │               │           │   └── dist
│   │   │   │               │           │       ├── additional-methods.js
│   │   │   │               │           │       ├── additional-methods.min.js
│   │   │   │               │           │       ├── jquery.validate.js
│   │   │   │               │           │       └── jquery.validate.min.js
│   │   │   │               │           └── jquery-validation-unobtrusive
│   │   │   │               │               ├── LICENSE.txt
│   │   │   │               │               ├── jquery.validate.unobtrusive.js
│   │   │   │               │               └── jquery.validate.unobtrusive.min.js
│   │   │   │               └── v2
│   │   │   │                   └── Index.cshtml
│   │   │   ├── solution-part-1
│   │   │   │   ├── README.md
│   │   │   │   ├── products-api
│   │   │   │   │   ├── deployment.yaml
│   │   │   │   │   └── products-api-service.yaml
│   │   │   │   ├── products-db
│   │   │   │   │   ├── deployment.yaml
│   │   │   │   │   └── products-db-service.yaml
│   │   │   │   ├── stock-api
│   │   │   │   │   ├── deployment.yaml
│   │   │   │   │   └── stock-api-service.yaml
│   │   │   │   └── web
│   │   │   │       ├── deployment.yaml
│   │   │   │       └── web-service.yaml
│   │   │   ├── solution-part-2
│   │   │   │   ├── README.md
│   │   │   │   ├── products-api
│   │   │   │   │   ├── deployment.yaml
│   │   │   │   │   ├── products-api-service.yaml
│   │   │   │   │   └── secret-db-properties.yaml
│   │   │   │   ├── products-db
│   │   │   │   │   ├── products-db-service.yaml
│   │   │   │   │   ├── products-db.yaml
│   │   │   │   │   └── secret-db-password.yaml
│   │   │   │   ├── stock-api
│   │   │   │   │   ├── deployment.yaml
│   │   │   │   │   ├── secret-db-connection.yaml
│   │   │   │   │   └── stock-api-service.yaml
│   │   │   │   └── web
│   │   │   │       ├── configmap-web-features.yaml
│   │   │   │       ├── deployments.yaml
│   │   │   │       ├── secret-web-api.yaml
│   │   │   │       └── web-service.yaml
│   │   │   ├── solution-part-3
│   │   │   │   ├── README.md
│   │   │   │   ├── products-api
│   │   │   │   │   ├── deployment.yaml
│   │   │   │   │   ├── products-api-service.yaml
│   │   │   │   │   └── secret-db-properties.yaml
│   │   │   │   ├── products-db
│   │   │   │   │   ├── products-db-service.yaml
│   │   │   │   │   ├── secret-db-password.yaml
│   │   │   │   │   └── statefulset.yaml
│   │   │   │   ├── stock-api
│   │   │   │   │   ├── deployment.yaml
│   │   │   │   │   ├── secret-db-connection.yaml
│   │   │   │   │   └── stock-api-service.yaml
│   │   │   │   └── web
│   │   │   │       ├── configmap-features.yaml
│   │   │   │       ├── deployment.yaml
│   │   │   │       ├── secret-api.yaml
│   │   │   │       └── web-service.yaml
│   │   │   ├── solution-part-4
│   │   │   │   ├── README.md
│   │   │   │   ├── ingress-controller
│   │   │   │   │   ├── 01_namespace.yaml
│   │   │   │   │   ├── 02_rbac.yaml
│   │   │   │   │   ├── configmap.yaml
│   │   │   │   │   ├── daemonset.yaml
│   │   │   │   │   ├── ingressClass.yaml
│   │   │   │   │   └── services.yaml
│   │   │   │   ├── products-api
│   │   │   │   │   ├── deployment.yaml
│   │   │   │   │   ├── ingress.yaml
│   │   │   │   │   ├── products-api-service.yaml
│   │   │   │   │   └── secret-db-properties.yaml
│   │   │   │   ├── products-db
│   │   │   │   │   ├── products-db-service.yaml
│   │   │   │   │   ├── secret-db-password.yaml
│   │   │   │   │   └── statefulset.yaml
│   │   │   │   ├── stock-api
│   │   │   │   │   ├── deployment.yaml
│   │   │   │   │   ├── secret-db-connection.yaml
│   │   │   │   │   └── stock-api-service.yaml
│   │   │   │   └── web
│   │   │   │       ├── deployment.yaml
│   │   │   │       ├── ingress.yaml
│   │   │   │       ├── secret-api.yaml
│   │   │   │       ├── web-api.yaml
│   │   │   │       └── web-service.yaml
│   │   │   ├── solution-part-5
│   │   │   │   ├── README.md
│   │   │   │   ├── ingress-controller
│   │   │   │   │   ├── 01_namespace.yaml
│   │   │   │   │   ├── 02_rbac.yaml
│   │   │   │   │   ├── configmap.yaml
│   │   │   │   │   ├── daemonset.yaml
│   │   │   │   │   ├── ingressClass.yaml
│   │   │   │   │   └── services.yaml
│   │   │   │   ├── products-api
│   │   │   │   │   ├── deployment.yaml
│   │   │   │   │   ├── ingress.yaml
│   │   │   │   │   ├── products-api-service.yaml
│   │   │   │   │   └── secret-db-properties.yaml
│   │   │   │   ├── products-db
│   │   │   │   │   ├── products-db-service.yaml
│   │   │   │   │   ├── secret-db-password.yaml
│   │   │   │   │   └── statefulset.yaml
│   │   │   │   ├── stock-api
│   │   │   │   │   ├── deployment.yaml
│   │   │   │   │   ├── secret-db-connection.yaml
│   │   │   │   │   └── stock-api-service.yaml
│   │   │   │   └── web
│   │   │   │       ├── config-features.yaml
│   │   │   │       ├── deployment.yaml
│   │   │   │       ├── ingress.yaml
│   │   │   │       ├── secrets-api.yaml
│   │   │   │       └── web-service.yaml
│   │   │   ├── solution-part-6
│   │   │   │   ├── README.md
│   │   │   │   ├── ingress-controller
│   │   │   │   │   ├── 01_namespace.yaml
│   │   │   │   │   ├── 02_rbac.yaml
│   │   │   │   │   ├── configmap.yaml
│   │   │   │   │   ├── daemonset.yaml
│   │   │   │   │   ├── ingressClass.yaml
│   │   │   │   │   └── services.yaml
│   │   │   │   ├── logging
│   │   │   │   │   ├── 01-namespace.yaml
│   │   │   │   │   ├── elasticsearch.yaml
│   │   │   │   │   ├── fluentbit-config.yaml
│   │   │   │   │   ├── fluentbit.yaml
│   │   │   │   │   └── kibana.yml
│   │   │   │   ├── monitoring
│   │   │   │   │   ├── 01-namespace.yaml
│   │   │   │   │   ├── grafana-config.yaml
│   │   │   │   │   ├── grafana.yml
│   │   │   │   │   ├── prometheus-config.yaml
│   │   │   │   │   └── prometheus.yaml
│   │   │   │   └── widgetario
│   │   │   │       ├── products-api.yaml
│   │   │   │       ├── products-db.yaml
│   │   │   │       ├── stock-api.yaml
│   │   │   │       └── web.yaml
│   │   │   └── solution-part-7
│   │   │       ├── Jenkinsfile
│   │   │       ├── README.md
│   │   │       ├── helm
│   │   │       │   └── widgetario
│   │   │       │       ├── Chart.yaml
│   │   │       │       ├── templates
│   │   │       │       │   ├── products-api.yaml
│   │   │       │       │   ├── products-db.yaml
│   │   │       │       │   ├── stock-api.yaml
│   │   │       │       │   └── web.yaml
│   │   │       │       └── values.yaml
│   │   │       └── infrastructure
│   │   │           ├── 01_namespace.yaml
│   │   │           ├── buildkitd.yaml
│   │   │           ├── gogs.yaml
│   │   │           └── jenkins.yaml
│   │   ├── images.txt
│   │   ├── img
│   │   │   ├── adminer-login.png
│   │   │   ├── adminer-updated.png
│   │   │   ├── docker-desktop-kubernetes.png
│   │   │   ├── docker-desktop-settings.png
│   │   │   ├── grafana-fulfilment.png
│   │   │   ├── hackathon-grafana.png
│   │   │   ├── hackathon-kibana.png
│   │   │   ├── ingress-controller-cert.png
│   │   │   ├── monitoring-lab-dashboard.png
│   │   │   ├── monitoring-lab-targets.png
│   │   │   ├── ohmyzsh.png
│   │   │   ├── tools-dashboard-edit.png
│   │   │   ├── tools-dashboard-pods.png
│   │   │   ├── tools-k9s-pods.png
│   │   │   ├── tools-k9s-secret.png
│   │   │   ├── tools-kubewatch-slack.png
│   │   │   ├── widgetario-architecture.png
│   │   │   ├── widgetario-solution-1.png
│   │   │   └── widgetario-solution-2.png
│   │   ├── index.md
│   │   ├── labs
│   │   │   ├── admission
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── solution
│   │   │   │   │   ├── 01-namespace.yaml
│   │   │   │   │   ├── api.yaml
│   │   │   │   │   ├── log.yaml
│   │   │   │   │   └── web.yaml
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       ├── apod
│   │   │   │       │   ├── 01-namespace.yaml
│   │   │   │       │   ├── api.yaml
│   │   │   │       │   ├── log.yaml
│   │   │   │       │   └── web.yaml
│   │   │   │       ├── cert-manager
│   │   │   │       │   ├── 1.5.3.yaml
│   │   │   │       │   └── issuers
│   │   │   │       │       └── self-signed.yaml
│   │   │   │       ├── mutating-webhook
│   │   │   │       │   └── mutatingWebhookConfiguration.yaml
│   │   │   │       ├── opa-gatekeeper
│   │   │   │       │   ├── 3.5.yaml
│   │   │   │       │   ├── constraints
│   │   │   │       │   │   ├── requiredLabels.yaml
│   │   │   │       │   │   └── resourceLimits.yaml
│   │   │   │       │   └── templates
│   │   │   │       │       ├── requiredLabels-template.yaml
│   │   │   │       │       └── resourceLimits-template.yaml
│   │   │   │       ├── pi
│   │   │   │       │   ├── fix
│   │   │   │       │   │   └── pi-nonroot.yaml
│   │   │   │       │   └── pi.yaml
│   │   │   │       ├── sleep
│   │   │   │       │   └── pod.yaml
│   │   │   │       ├── validating-webhook
│   │   │   │       │   └── validatingWebhookConfiguration.yaml
│   │   │   │       ├── webhook-server
│   │   │   │       │   ├── admission-webhook.yaml
│   │   │   │       │   └── certificate.yaml
│   │   │   │       └── whoami
│   │   │   │           ├── deployment.yaml
│   │   │   │           ├── fix
│   │   │   │           │   └── deployment.yaml
│   │   │   │           └── service.yaml
│   │   │   ├── affinity
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── solution
│   │   │   │   │   └── whoami-compliance-preferred.yaml
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       ├── multi-arch
│   │   │   │       │   ├── linux-or-windows.yaml
│   │   │   │       │   └── services.yaml
│   │   │   │       └── whoami
│   │   │   │           ├── colocate-region
│   │   │   │           │   └── deployment.yaml
│   │   │   │           ├── compliance-required
│   │   │   │           │   └── deployment.yaml
│   │   │   │           ├── deployment.yaml
│   │   │   │           ├── prefer-spread-zone
│   │   │   │           │   └── deployment.yaml
│   │   │   │           ├── services.yaml
│   │   │   │           └── spread-zone
│   │   │   │               └── deployment.yaml
│   │   │   ├── argo
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── project
│   │   │   │   │   ├── apod
│   │   │   │   │   │   └── base
│   │   │   │   │   │       ├── api.yaml
│   │   │   │   │   │       ├── kustomization.yaml
│   │   │   │   │   │       ├── log.yaml
│   │   │   │   │   │       └── web.yaml
│   │   │   │   │   └── helm
│   │   │   │   │       └── whoami
│   │   │   │   │           ├── Chart.yaml
│   │   │   │   │           ├── templates
│   │   │   │   │           │   ├── deployment.yaml
│   │   │   │   │           │   └── service.yaml
│   │   │   │   │           └── values.yaml
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       ├── argocd
│   │   │   │       │   ├── 01_namespace.yaml
│   │   │   │       │   ├── 2.1.2.yaml
│   │   │   │       │   └── nodeport.yaml
│   │   │   │       ├── gogs
│   │   │   │       │   ├── 01_namespace.yaml
│   │   │   │       │   └── gogs.yaml
│   │   │   │       └── whoami
│   │   │   │           └── app.yaml
│   │   │   ├── buildkit
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       ├── buildkit-cli
│   │   │   │       │   └── buildkit-cli.yaml
│   │   │   │       ├── buildkitd
│   │   │   │       │   └── buildkitd.yaml
│   │   │   │       └── sleep
│   │   │   │           └── pod.yaml
│   │   │   ├── clusters
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       ├── ingress
│   │   │   │       │   ├── v1
│   │   │   │       │   │   └── ingress.yaml
│   │   │   │       │   └── v1beta1
│   │   │   │       │       └── whoami.yaml
│   │   │   │       ├── ingress-controller
│   │   │   │       │   ├── 01_namespace.yaml
│   │   │   │       │   ├── 02_rbac.yaml
│   │   │   │       │   ├── configmap.yaml
│   │   │   │       │   ├── daemonset.yaml
│   │   │   │       │   ├── services.yaml
│   │   │   │       │   └── update
│   │   │   │       │       └── daemonset.yaml
│   │   │   │       └── whoami
│   │   │   │           ├── deployment.yaml
│   │   │   │           ├── services.yaml
│   │   │   │           └── update
│   │   │   │               └── deployment.yaml
│   │   │   ├── configmaps
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── solution
│   │   │   │   │   ├── configurable.env
│   │   │   │   │   └── override.json
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       └── configurable
│   │   │   │           ├── config-broken
│   │   │   │           │   └── deployment-broken.yaml
│   │   │   │           ├── config-env
│   │   │   │           │   ├── configmap-env.yaml
│   │   │   │           │   └── deployment-env.yaml
│   │   │   │           ├── config-json
│   │   │   │           │   ├── configmap-json.yaml
│   │   │   │           │   └── deployment-json.yaml
│   │   │   │           ├── deployment.yaml
│   │   │   │           ├── lab
│   │   │   │           │   └── deployment-lab.yaml
│   │   │   │           └── services.yaml
│   │   │   ├── daemonsets
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── solution
│   │   │   │   │   └── daemonset-update-on-delete.yaml
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       ├── nginx
│   │   │   │       │   ├── daemonset.yaml
│   │   │   │       │   ├── services.yaml
│   │   │   │       │   ├── update-bad
│   │   │   │       │   │   └── daemonset-bad-command.yaml
│   │   │   │       │   ├── update-good
│   │   │   │       │   │   └── daemonset-init-container.yaml
│   │   │   │       │   └── update-subset
│   │   │   │       │       └── daemonset-node-selector.yaml
│   │   │   │       └── sleep-with-hostPath.yaml
│   │   │   ├── deployments
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── solution
│   │   │   │   │   ├── whoami-deployments.yaml
│   │   │   │   │   ├── whoami-service-v1.yaml
│   │   │   │   │   └── whoami-service-v2.yaml
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       ├── deployments
│   │   │   │       │   ├── whoami-v1-scale.yaml
│   │   │   │       │   ├── whoami-v1.yaml
│   │   │   │       │   └── whoami-v2.yaml
│   │   │   │       └── services
│   │   │   │           ├── whoami-loadbalancer.yaml
│   │   │   │           └── whoami-nodeport.yaml
│   │   │   ├── docker
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── simple
│   │   │   │   │   └── Dockerfile
│   │   │   │   ├── solution.md
│   │   │   │   └── whoami
│   │   │   │       ├── Dockerfile
│   │   │   │       ├── app.go
│   │   │   │       └── go.mod
│   │   │   ├── helm
│   │   │   │   ├── README.md
│   │   │   │   ├── charts
│   │   │   │   │   └── whoami
│   │   │   │   │       ├── Chart.yaml
│   │   │   │   │       ├── templates
│   │   │   │   │       │   ├── deployment.yaml
│   │   │   │   │       │   └── service.yaml
│   │   │   │   │       └── values.yaml
│   │   │   │   ├── hints.md
│   │   │   │   ├── ingress-nginx
│   │   │   │   │   └── dev.yaml
│   │   │   │   └── solution.md
│   │   │   ├── ingress
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── ingress-https.md
│   │   │   │   ├── solution
│   │   │   │   │   ├── controller
│   │   │   │   │   │   └── service-lb.yaml
│   │   │   │   │   └── ingress
│   │   │   │   │       └── configurable-http.yaml
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       ├── configurable
│   │   │   │       │   ├── 01-namespace.yaml
│   │   │   │       │   ├── configmap.yaml
│   │   │   │       │   ├── deployment.yaml
│   │   │   │       │   └── services.yaml
│   │   │   │       ├── default
│   │   │   │       │   ├── configmap.yaml
│   │   │   │       │   ├── deployment.yaml
│   │   │   │       │   ├── ingress
│   │   │   │       │   │   └── default.yaml
│   │   │   │       │   └── service.yaml
│   │   │   │       ├── ingress-controller
│   │   │   │       │   ├── 01_namespace.yaml
│   │   │   │       │   ├── 02_rbac.yaml
│   │   │   │       │   ├── configmap.yaml
│   │   │   │       │   ├── daemonset.yaml
│   │   │   │       │   ├── ingressClass.yaml
│   │   │   │       │   └── services.yaml
│   │   │   │       ├── pi
│   │   │   │       │   ├── ingress.yaml
│   │   │   │       │   ├── pi.yaml
│   │   │   │       │   └── update
│   │   │   │       │       └── ingress-with-cache.yaml
│   │   │   │       ├── tls
│   │   │   │       │   ├── cert-generator.yaml
│   │   │   │       │   └── ingress
│   │   │   │       │       ├── default-https.yaml
│   │   │   │       │       ├── pi-https.yaml
│   │   │   │       │       └── whoami-https.yaml
│   │   │   │       └── whoami
│   │   │   │           ├── ingress.yaml
│   │   │   │           └── whoami.yaml
│   │   │   ├── jenkins
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── project
│   │   │   │   │   ├── Jenkinsfile
│   │   │   │   │   ├── Jenkinsfile.original
│   │   │   │   │   ├── helm
│   │   │   │   │   │   ├── integration-test.yaml
│   │   │   │   │   │   └── whoami
│   │   │   │   │   │       ├── Chart.yaml
│   │   │   │   │   │       ├── templates
│   │   │   │   │   │       │   ├── deployment.yaml
│   │   │   │   │   │       │   └── service.yaml
│   │   │   │   │   │       └── values.yaml
│   │   │   │   │   └── src
│   │   │   │   │       ├── CREDITS.md
│   │   │   │   │       ├── Dockerfile
│   │   │   │   │       ├── Dockerfile.original
│   │   │   │   │       ├── LICENSE
│   │   │   │   │       ├── app.go
│   │   │   │   │       └── go.mod
│   │   │   │   ├── solution
│   │   │   │   │   └── Dockerfile
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       └── infrastructure
│   │   │   │           ├── 01_namespace.yaml
│   │   │   │           ├── buildkitd.yaml
│   │   │   │           ├── gogs.yaml
│   │   │   │           └── jenkins.yaml
│   │   │   ├── jobs
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       ├── backup
│   │   │   │       │   └── cronjob.yaml
│   │   │   │       ├── cleanup
│   │   │   │       │   ├── configmap.yaml
│   │   │   │       │   ├── cronjob.yaml
│   │   │   │       │   ├── rbac.yaml
│   │   │   │       │   └── update
│   │   │   │       │       └── cronjob.yaml
│   │   │   │       └── pi
│   │   │   │           ├── many
│   │   │   │           │   └── pi-job-random.yaml
│   │   │   │           ├── one
│   │   │   │           │   ├── pi-job-50dp.yaml
│   │   │   │           │   └── update
│   │   │   │           │       └── pi-job-500dp.yaml
│   │   │   │           └── one-failing
│   │   │   │               ├── pi-job.yaml
│   │   │   │               └── update
│   │   │   │                   └── pi-job-restart.yaml
│   │   │   ├── logging
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── solution
│   │   │   │   │   └── deployment.yaml
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       ├── fulfilment-api
│   │   │   │       │   ├── deployment.yaml
│   │   │   │       │   └── services.yml
│   │   │   │       ├── fulfilment-processor
│   │   │   │       │   └── deployment.yaml
│   │   │   │       ├── jumpbox
│   │   │   │       │   └── pod.yaml
│   │   │   │       └── logging
│   │   │   │           ├── 01-namespace.yaml
│   │   │   │           ├── elasticsearch.yaml
│   │   │   │           ├── fluentbit-config.yaml
│   │   │   │           ├── fluentbit.yaml
│   │   │   │           └── kibana.yml
│   │   │   ├── monitoring
│   │   │   │   ├── README.md
│   │   │   │   ├── dashboards
│   │   │   │   │   ├── cluster.json
│   │   │   │   │   └── fulfilment-processor.json
│   │   │   │   ├── hints.md
│   │   │   │   ├── solution
│   │   │   │   │   └── prometheus-config.yaml
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       ├── cluster-metrics
│   │   │   │       │   ├── cadvisor.yaml
│   │   │   │       │   └── kube-state-metrics.yaml
│   │   │   │       ├── fulfilment-processor
│   │   │   │       │   ├── deployment.yaml
│   │   │   │       │   └── services.yaml
│   │   │   │       └── monitoring
│   │   │   │           ├── 01-namespace.yaml
│   │   │   │           ├── grafana-config.yaml
│   │   │   │           ├── grafana.yml
│   │   │   │           ├── prometheus-config.yaml
│   │   │   │           └── prometheus.yaml
│   │   │   ├── namespaces
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── solution
│   │   │   │   │   ├── 01-namespace.yaml
│   │   │   │   │   └── nginx-configMap.yaml
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       ├── configurable
│   │   │   │       │   ├── 01-namespace.yaml
│   │   │   │       │   ├── configmap.yaml
│   │   │   │       │   ├── deployment.yaml
│   │   │   │       │   └── services.yaml
│   │   │   │       ├── pi
│   │   │   │       │   ├── 01-namespace.yaml
│   │   │   │       │   ├── 02-cpu-limit-quota.yaml
│   │   │   │       │   ├── max-cpu
│   │   │   │       │   │   └── web-deployment.yaml
│   │   │   │       │   ├── mid-cpu
│   │   │   │       │   │   └── web-deployment.yaml
│   │   │   │       │   ├── web-deployment.yaml
│   │   │   │       │   └── web-services.yaml
│   │   │   │       ├── reverse-proxy
│   │   │   │       │   └── nginx.yaml
│   │   │   │       ├── sleep-pod.yaml
│   │   │   │       └── whoami
│   │   │   │           ├── 01-namespace.yaml
│   │   │   │           ├── deployment.yaml
│   │   │   │           └── services.yaml
│   │   │   ├── networkpolicy
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── solution
│   │   │   │   │   └── apod
│   │   │   │   │       ├── 01-namespace.yaml
│   │   │   │   │       ├── api.yaml
│   │   │   │   │       ├── log.yaml
│   │   │   │   │       ├── network-policies.yaml
│   │   │   │   │       └── web.yaml
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       ├── apod
│   │   │   │       │   ├── api.yaml
│   │   │   │       │   ├── log.yaml
│   │   │   │       │   ├── network-policies
│   │   │   │       │   │   ├── apod-api.yaml
│   │   │   │       │   │   ├── apod-log.yaml
│   │   │   │       │   │   └── apod-web.yaml
│   │   │   │       │   └── web.yaml
│   │   │   │       ├── apod-hack
│   │   │   │       │   └── sleep.yaml
│   │   │   │       ├── deny-all
│   │   │   │       │   └── default-deny.yaml
│   │   │   │       └── k3d
│   │   │   │           └── calico.yaml
│   │   │   ├── nodes
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   └── solution.md
│   │   │   ├── operators
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── solution
│   │   │   │   │   ├── todo-list-db.yaml
│   │   │   │   │   └── todo-list-queue.yaml
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       ├── crd
│   │   │   │       │   └── student-crd.yaml
│   │   │   │       ├── mysql
│   │   │   │       │   ├── database
│   │   │   │       │   │   ├── 01-secret.yaml
│   │   │   │       │   │   └── db.yaml
│   │   │   │       │   └── operator
│   │   │   │       │       ├── Chart.yaml
│   │   │   │       │       ├── README.md
│   │   │   │       │       ├── crds
│   │   │   │       │       │   ├── mysql.presslabs.org_mysqlbackups.yaml
│   │   │   │       │       │   ├── mysql.presslabs.org_mysqlclusters.yaml
│   │   │   │       │       │   ├── mysql.presslabs.org_mysqldatabases.yaml
│   │   │   │       │       │   └── mysql.presslabs.org_mysqlusers.yaml
│   │   │   │       │       ├── templates
│   │   │   │       │       │   ├── NOTES.txt
│   │   │   │       │       │   ├── _helpers.tpl
│   │   │   │       │       │   ├── _orchestrator-helpers.tpl
│   │   │   │       │       │   ├── clusterrole.yaml
│   │   │   │       │       │   ├── clusterrolebinding.yaml
│   │   │   │       │       │   ├── clustersservicemonitor.yaml
│   │   │   │       │       │   ├── orchestrator-config.yaml
│   │   │   │       │       │   ├── orchestrator-ingress.yaml
│   │   │   │       │       │   ├── orchestrator-raft-service.yaml
│   │   │   │       │       │   ├── orchestrator-secret.yaml
│   │   │   │       │       │   ├── pdb.yaml
│   │   │   │       │       │   ├── podsecuritypolicy-role.yaml
│   │   │   │       │       │   ├── podsecuritypolicy-rolebinding.yaml
│   │   │   │       │       │   ├── podsecuritypolicy.yaml
│   │   │   │       │       │   ├── service.yaml
│   │   │   │       │       │   ├── serviceaccount.yaml
│   │   │   │       │       │   ├── servicemonitor.yaml
│   │   │   │       │       │   └── statefulset.yaml
│   │   │   │       │       └── values.yaml
│   │   │   │       ├── nats
│   │   │   │       │   ├── cluster
│   │   │   │       │   │   └── msgq.yaml
│   │   │   │       │   └── operator
│   │   │   │       │       ├── 00-prereqs.yaml
│   │   │   │       │       └── 10-deployment.yaml
│   │   │   │       ├── students
│   │   │   │       │   ├── edwin.yaml
│   │   │   │       │   └── priti.yaml
│   │   │   │       └── todo-list
│   │   │   │           ├── todo-list-configMap.yaml
│   │   │   │           ├── todo-list-secret.yaml
│   │   │   │           ├── todo-save-handler.yaml
│   │   │   │           └── todo-web.yaml
│   │   │   ├── persistentvolumes
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── solution
│   │   │   │   │   └── sleep-with-hostpath.yaml
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       ├── caching-proxy-emptydir
│   │   │   │       │   └── nginx.yaml
│   │   │   │       ├── caching-proxy-pv
│   │   │   │       │   ├── nginx.yaml
│   │   │   │       │   ├── persistentVolume.yaml
│   │   │   │       │   └── pvc.yaml
│   │   │   │       ├── caching-proxy-pvc
│   │   │   │       │   ├── nginx.yaml
│   │   │   │       │   └── pvc.yaml
│   │   │   │       └── pi
│   │   │   │           ├── nginx-configMap.yaml
│   │   │   │           ├── nginx.yaml
│   │   │   │           └── web.yaml
│   │   │   ├── pods
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── solution
│   │   │   │   │   └── lab.yaml
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       ├── sleep-pod.yaml
│   │   │   │       └── whoami-pod.yaml
│   │   │   ├── productionizing
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── solution
│   │   │   │   │   ├── deployment-productionized.yaml
│   │   │   │   │   └── hpa-cpu.yaml
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       ├── configurable
│   │   │   │       │   ├── deployment.yaml
│   │   │   │       │   └── services.yaml
│   │   │   │       ├── metrics-server
│   │   │   │       │   └── components-v0.6.1.yaml
│   │   │   │       ├── pi
│   │   │   │       │   ├── deployment.yaml
│   │   │   │       │   ├── hpa-cpu.yaml
│   │   │   │       │   └── service.yaml
│   │   │   │       ├── pi-secure
│   │   │   │       │   ├── deployment.yaml
│   │   │   │       │   ├── service.yaml
│   │   │   │       │   └── update
│   │   │   │       │       └── deployment-custom-port.yaml
│   │   │   │       ├── products-db
│   │   │   │       │   └── products-db.yaml
│   │   │   │       └── whoami
│   │   │   │           ├── deployment.yaml
│   │   │   │           ├── services.yaml
│   │   │   │           ├── update
│   │   │   │           │   └── deployment-with-readiness.yaml
│   │   │   │           └── update2
│   │   │   │               └── deployment-with-liveness.yaml
│   │   │   ├── rbac
│   │   │   │   ├── README.md
│   │   │   │   ├── docker-desktop-fix.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── rbac-for-users.md
│   │   │   │   ├── solution
│   │   │   │   │   ├── service-account-viewer.yaml
│   │   │   │   │   ├── sleep-with-no-token-sa.yaml
│   │   │   │   │   └── sleep-without-sa-token.yaml
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       ├── group
│   │   │   │       │   ├── clusterrole-podviewer.yaml
│   │   │   │       │   └── clusterrolebinding-podviewer-courselabs.yaml
│   │   │   │       ├── kube-explorer
│   │   │   │       │   ├── deployment.yaml
│   │   │   │       │   ├── rbac-cluster
│   │   │   │       │   │   ├── clusterrole-pod-reader.yaml
│   │   │   │       │   │   └── clusterrolebinding-pod-reader-sa.yaml
│   │   │   │       │   ├── rbac-namespace
│   │   │   │       │   │   ├── role-pod-manager.yaml
│   │   │   │       │   │   └── rolebinding-pod-manager-sa.yaml
│   │   │   │       │   └── services.yaml
│   │   │   │       ├── sleep.yaml
│   │   │   │       ├── user
│   │   │   │       │   └── rolebinding-edit-default.yaml
│   │   │   │       └── user-cert-generator
│   │   │   │           ├── 01_service-account.yaml
│   │   │   │           ├── 02_rbac.yaml
│   │   │   │           └── 03_pod.yaml
│   │   │   ├── rollouts
│   │   │   │   ├── README.md
│   │   │   │   ├── helm
│   │   │   │   │   └── vweb
│   │   │   │   │       ├── Chart.yaml
│   │   │   │   │       ├── templates
│   │   │   │   │       │   ├── deployment.yaml
│   │   │   │   │       │   └── service.yaml
│   │   │   │   │       └── values.yaml
│   │   │   │   ├── hints.md
│   │   │   │   ├── solution
│   │   │   │   │   └── helm
│   │   │   │   │       └── vweb
│   │   │   │   │           ├── Chart.yaml
│   │   │   │   │           ├── templates
│   │   │   │   │           │   ├── deployment.yaml
│   │   │   │   │           │   └── service.yaml
│   │   │   │   │           └── values.yaml
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       ├── nginx-daemonset
│   │   │   │       │   ├── 1.18.yaml
│   │   │   │       │   ├── services.yaml
│   │   │   │       │   └── update-ondelete
│   │   │   │       │       └── 1.20.yaml
│   │   │   │       ├── nginx-statefulset
│   │   │   │       │   ├── 1.18.yaml
│   │   │   │       │   ├── services.yaml
│   │   │   │       │   └── update-partition
│   │   │   │       │       └── 1.20.yaml
│   │   │   │       └── vweb
│   │   │   │           ├── deployment.yaml
│   │   │   │           ├── services.yaml
│   │   │   │           ├── update-broken
│   │   │   │           │   └── deployment.yaml
│   │   │   │           ├── update-fast
│   │   │   │           │   └── deployment.yaml
│   │   │   │           └── update-slow
│   │   │   │               └── deployment.yaml
│   │   │   ├── secrets
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── secrets
│   │   │   │   │   ├── configurable.env
│   │   │   │   │   └── secret.json
│   │   │   │   ├── solution
│   │   │   │   │   ├── deployment.yaml
│   │   │   │   │   ├── secret.yaml
│   │   │   │   │   ├── services.yaml
│   │   │   │   │   ├── v2-annotation
│   │   │   │   │   │   ├── deployment.yaml
│   │   │   │   │   │   └── secret-v2.yaml
│   │   │   │   │   └── v2-name
│   │   │   │   │       ├── deployment.yaml
│   │   │   │   │       └── secret-v2.yaml
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       └── configurable
│   │   │   │           ├── configmaps.yaml
│   │   │   │           ├── deployment.yaml
│   │   │   │           ├── secrets-encoded
│   │   │   │           │   ├── deployment-env.yaml
│   │   │   │           │   └── secret-encoded.yaml
│   │   │   │           ├── secrets-file
│   │   │   │           │   └── deployment.yaml
│   │   │   │           ├── secrets-overlapping
│   │   │   │           │   ├── configmap-env.yaml
│   │   │   │           │   ├── deployment-env.yaml
│   │   │   │           │   └── secret-plain.yaml
│   │   │   │           ├── secrets-plain
│   │   │   │           │   ├── deployment-env.yaml
│   │   │   │           │   └── secret-plain.yaml
│   │   │   │           ├── secrets-update
│   │   │   │           │   ├── deployment-file.yaml
│   │   │   │           │   ├── secret-plain.yaml
│   │   │   │           │   └── v1-update
│   │   │   │           │       └── secret-plain.yaml
│   │   │   │           └── services.yaml
│   │   │   ├── services
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── solution
│   │   │   │   │   ├── whoami-pod-2.yaml
│   │   │   │   │   └── whoami-svc-zeromatches.yaml
│   │   │   │   ├── solution.md
│   │   │   │   └── specs
│   │   │   │       ├── pods
│   │   │   │       │   ├── sleep.yaml
│   │   │   │       │   └── whoami.yaml
│   │   │   │       └── services
│   │   │   │           ├── whoami-clusterip.yaml
│   │   │   │           ├── whoami-loadbalancer.yaml
│   │   │   │           └── whoami-nodeport.yaml
│   │   │   ├── statefulsets
│   │   │   │   ├── README.md
│   │   │   │   ├── hints.md
│   │   │   │   ├── solution
│   │   │   │   │   ├── service.yaml
│   │   │   │   │   └── statefulset.yaml
│   │   │   │   ├── solution.md
│   │   │   │   ├── specs
│   │   │   │   │   ├── adminer
│   │   │   │   │   │   ├── deployment.yaml
│   │   │   │   │   │   └── services.yaml
│   │   │   │   │   ├── products-db
│   │   │   │   │   │   ├── secret.yaml
│   │   │   │   │   │   ├── service.yaml
│   │   │   │   │   │   ├── statefulset-with-pvc.yaml
│   │   │   │   │   │   └── update
│   │   │   │   │   │       └── statefulset-with-pvc-annotation.yaml
│   │   │   │   │   ├── simple
│   │   │   │   │   │   ├── configmap-scripts.yaml
│   │   │   │   │   │   ├── services.yaml
│   │   │   │   │   │   ├── statefulset.yaml
│   │   │   │   │   │   └── update
│   │   │   │   │   │       └── services.yaml
│   │   │   │   │   ├── simple-proxy
│   │   │   │   │   │   ├── configmap.yaml
│   │   │   │   │   │   ├── deployment.yaml
│   │   │   │   │   │   └── external-services.yaml
│   │   │   │   │   └── sleep-pod.yaml
│   │   │   │   └── statefulsets-sql-client.md
│   │   │   ├── tools
│   │   │   │   ├── README.md
│   │   │   │   ├── kubewatch
│   │   │   │   │   └── values.yaml
│   │   │   │   └── specs
│   │   │   │       ├── dashboard
│   │   │   │       │   ├── 01-namespace.yaml
│   │   │   │       │   ├── rng-admin-user.yaml
│   │   │   │       │   ├── service.yaml
│   │   │   │       │   └── v2.3.1.yaml
│   │   │   │       ├── metrics-server
│   │   │   │       │   └── components-v0.3.6.yaml
│   │   │   │       └── rng
│   │   │   │           ├── 01-namespace.yaml
│   │   │   │           ├── api.yaml
│   │   │   │           └── web.yaml
│   │   │   └── troubleshooting
│   │   │       ├── README.md
│   │   │       ├── hints.md
│   │   │       ├── solution
│   │   │       │   ├── deployment.yaml
│   │   │       │   └── service.yaml
│   │   │       ├── solution.md
│   │   │       └── specs
│   │   │           └── pi-failing
│   │   │               ├── deployment.yaml
│   │   │               └── service.yaml
│   │   ├── my-readme.md
│   │   ├── scripts
│   │   │   ├── add-to-hosts.ps1
│   │   │   ├── add-to-hosts.sh
│   │   │   ├── fix-rbac-docker-desktop.ps1
│   │   │   ├── fix-rbac-docker-desktop.sh
│   │   │   ├── imageSync.ps1
│   │   │   ├── pull-images.ps1
│   │   │   ├── pull-images.sh
│   │   │   └── windows-tools.ps1
│   │   ├── setup
│   │   │   ├── README.md
│   │   │   └── kind.yaml
│   │   └── src
│   │       ├── bad-sleep
│   │       │   └── Dockerfile
│   │       └── compose.yml
│   └── kustomize
│       ├── Kubernetes Kustomize Tutorial (Comprehensive Guide).pdf
│       ├── README.md
│       ├── base
│       │   ├── deployment.yaml
│       │   ├── kustomization.yaml
│       │   └── service.yaml
│       ├── my-readme.md
│       └── overlays
│           ├── dev
│           │   ├── deployment-dev.yaml
│           │   ├── index-dev.html
│           │   ├── kustomization.yaml
│           │   └── service-dev.yaml
│           ├── generators
│           │   ├── deployment.yaml
│           │   ├── files
│           │   │   └── index.html
│           │   ├── kustomization.yaml
│           │   └── service.yaml
│           └── prod
│               ├── deployment-prod.yaml
│               ├── kustomization.yaml
│               └── service-prod.yaml
├── lambda
│   ├── aws-refactoring-to-serverless
│   │   ├── CONTRIBUTING.md
│   │   ├── LICENSE
│   │   ├── NOTICE
│   │   ├── README.md
│   │   ├── implementation
│   │   │   ├── choreography-to-orchestration
│   │   │   │   ├── README.md
│   │   │   │   ├── bin
│   │   │   │   │   └── choreography-to-orchestration.ts
│   │   │   │   ├── cdk.json
│   │   │   │   ├── jest.config.js
│   │   │   │   ├── lambda
│   │   │   │   │   ├── choreography
│   │   │   │   │   │   ├── ProcessPayment.py
│   │   │   │   │   │   ├── ShipOrder.py
│   │   │   │   │   │   └── UpdateReward.py
│   │   │   │   │   └── orchestration
│   │   │   │   │       ├── ProcessPayment.py
│   │   │   │   │       ├── ShipOrder.py
│   │   │   │   │       └── UpdateReward.py
│   │   │   │   ├── lib
│   │   │   │   │   ├── cdk_test-stack.ts
│   │   │   │   │   ├── choreography-stack.ts
│   │   │   │   │   └── orchestration-stack.ts
│   │   │   │   ├── package-lock.json
│   │   │   │   ├── package.json
│   │   │   │   ├── test
│   │   │   │   │   └── choreography-to-orchestration.test.ts
│   │   │   │   └── tsconfig.json
│   │   │   ├── direct-database-access
│   │   │   │   ├── README.md
│   │   │   │   ├── bin
│   │   │   │   │   └── direct-database-access.ts
│   │   │   │   ├── cdk.json
│   │   │   │   ├── jest.config.js
│   │   │   │   ├── lambda
│   │   │   │   │   └── read-dynamodb.js
│   │   │   │   ├── lib
│   │   │   │   │   ├── base-stack.ts
│   │   │   │   │   ├── read-dynamodb-stack-original.ts
│   │   │   │   │   └── read-dynamodb-stack-refactored.ts
│   │   │   │   ├── package-lock.json
│   │   │   │   ├── package.json
│   │   │   │   ├── test
│   │   │   │   │   └── direct-database-access.test.ts
│   │   │   │   └── tsconfig.json
│   │   │   ├── extract-function-invocation
│   │   │   │   ├── README.md
│   │   │   │   ├── bin
│   │   │   │   │   └── extract-function-invocation.ts
│   │   │   │   ├── cdk.json
│   │   │   │   ├── jest.config.js
│   │   │   │   ├── lambda
│   │   │   │   │   ├── destination
│   │   │   │   │   │   └── index.js
│   │   │   │   │   ├── invocation-before
│   │   │   │   │   │   └── index.js
│   │   │   │   │   └── invocation-refactored
│   │   │   │   │       └── index.js
│   │   │   │   ├── lib
│   │   │   │   │   ├── extract-function-invocation-original.ts
│   │   │   │   │   └── extract-function-invocation-refactored.ts
│   │   │   │   ├── package-lock.json
│   │   │   │   ├── package.json
│   │   │   │   ├── test
│   │   │   │   │   └── extract-function-invocation.test.ts
│   │   │   │   └── tsconfig.json
│   │   │   ├── extract-message-router
│   │   │   │   ├── README.md
│   │   │   │   ├── bin
│   │   │   │   │   └── extract-message-router.ts
│   │   │   │   ├── cdk.json
│   │   │   │   ├── jest.config.js
│   │   │   │   ├── lib
│   │   │   │   │   ├── extract-message-router-original.ts
│   │   │   │   │   └── extract-message-router-refactored.ts
│   │   │   │   ├── package-lock.json
│   │   │   │   ├── package.json
│   │   │   │   ├── test
│   │   │   │   │   └── extract-message-filter.test.ts
│   │   │   │   └── tsconfig.json
│   │   │   ├── extract-send-message
│   │   │   │   ├── README.md
│   │   │   │   ├── bin
│   │   │   │   │   └── sqs-destination.ts
│   │   │   │   ├── cdk.json
│   │   │   │   ├── jest.config.js
│   │   │   │   ├── lambda-fns
│   │   │   │   │   ├── send-message-from-code
│   │   │   │   │   │   └── bakePizza.js
│   │   │   │   │   └── send-message-using-destination
│   │   │   │   │       └── bakePizza.js
│   │   │   │   ├── lib
│   │   │   │   │   ├── send-message-original.ts
│   │   │   │   │   └── send-message-refactored.ts
│   │   │   │   ├── message-send.png
│   │   │   │   ├── package-lock.json
│   │   │   │   ├── package.json
│   │   │   │   ├── test
│   │   │   │   │   ├── sqs-destination.test.js
│   │   │   │   │   └── sqs-destination.test.ts
│   │   │   │   ├── test.js
│   │   │   │   └── tsconfig.json
│   │   │   ├── orchestration-to-choreography
│   │   │   │   ├── README.md
│   │   │   │   ├── bin
│   │   │   │   │   └── orchestration-to-choreography.ts
│   │   │   │   ├── cdk.json
│   │   │   │   ├── lambda
│   │   │   │   │   ├── choreography
│   │   │   │   │   │   ├── BankSns.js
│   │   │   │   │   │   ├── index.js
│   │   │   │   │   │   └── quoteAggregator.py
│   │   │   │   │   └── orchestration
│   │   │   │   │       ├── RecipientBank.js
│   │   │   │   │       └── index.js
│   │   │   │   ├── lib
│   │   │   │   │   ├── choreography.ts
│   │   │   │   │   └── orchestration.ts
│   │   │   │   ├── package-lock.json
│   │   │   │   ├── package.json
│   │   │   │   └── tsconfig.json
│   │   │   ├── parallel-to-sns-scatter-gather
│   │   │   │   ├── README.md
│   │   │   │   ├── app.py
│   │   │   │   ├── arch_designs.drawio
│   │   │   │   ├── cdk.json
│   │   │   │   ├── requirements.txt
│   │   │   │   ├── scatter_gather
│   │   │   │   │   ├── __init__.py
│   │   │   │   │   ├── input.json
│   │   │   │   │   ├── lambda_
│   │   │   │   │   │   ├── aggregator
│   │   │   │   │   │   │   └── app.py
│   │   │   │   │   │   ├── lambda_functions.py
│   │   │   │   │   │   ├── requester
│   │   │   │   │   │   │   └── app.py
│   │   │   │   │   │   └── responder
│   │   │   │   │   │       └── app.py
│   │   │   │   │   ├── original_component.py
│   │   │   │   │   ├── refactored_component.py
│   │   │   │   │   └── stepfunction
│   │   │   │   │       ├── __init__.py
│   │   │   │   │       └── stepfunctions_workflow.py
│   │   │   │   └── source.bat
│   │   │   ├── replace-polling-with-callback
│   │   │   │   ├── README.md
│   │   │   │   ├── bin
│   │   │   │   │   └── replace-polling-with-callback.ts
│   │   │   │   ├── cdk.json
│   │   │   │   ├── jest.config.js
│   │   │   │   ├── lambda-fns
│   │   │   │   │   ├── polling
│   │   │   │   │   │   └── checkStatus.js
│   │   │   │   │   └── processing
│   │   │   │   │       ├── before.js
│   │   │   │   │       └── refactored.js
│   │   │   │   ├── lib
│   │   │   │   │   ├── replace-polling-with-callback-before.ts
│   │   │   │   │   └── replace-polling-with-callback-refactored.ts
│   │   │   │   ├── package-lock.json
│   │   │   │   ├── package.json
│   │   │   │   ├── test
│   │   │   │   │   └── replace-polling-with-wait.test.ts
│   │   │   │   └── tsconfig.json
│   │   │   ├── send-message-via-pipes
│   │   │   │   ├── README.md
│   │   │   │   ├── bin
│   │   │   │   │   └── send-message-via-pipes.ts
│   │   │   │   ├── cdk.json
│   │   │   │   ├── jest.config.js
│   │   │   │   ├── lambda
│   │   │   │   │   ├── process-order-original
│   │   │   │   │   │   └── index.js
│   │   │   │   │   └── process-order-refactored
│   │   │   │   │       └── index.js
│   │   │   │   ├── lib
│   │   │   │   │   ├── send-message-via-pipes-original.ts
│   │   │   │   │   └── send-message-via-pipes-refactored.ts
│   │   │   │   ├── package-lock.json
│   │   │   │   ├── package.json
│   │   │   │   ├── response.json
│   │   │   │   ├── test
│   │   │   │   │   └── refactoring-to-serverless.test.ts
│   │   │   │   └── tsconfig.json
│   │   │   └── service_integration
│   │   │       ├── README.md
│   │   │       ├── bin
│   │   │       │   └── service-integration.ts
│   │   │       ├── cdk.context.json
│   │   │       ├── cdk.json
│   │   │       ├── jest.config.js
│   │   │       ├── lambda-fns
│   │   │       │   └── detectObjectInImage
│   │   │       ├── lib
│   │   │       │   ├── service-integration-stack-original.ts
│   │   │       │   └── service-integration-stack-refactored.ts
│   │   │       ├── package-lock.json
│   │   │       ├── package.json
│   │   │       ├── test
│   │   │       │   └── service_integration.test.ts
│   │   │       └── tsconfig.json
│   │   ├── my-readme.md
│   │   └── patterns
│   │       ├── choreography-to-orchestration.md
│   │       ├── direct-database-access.png
│   │       ├── direct_database_access.md
│   │       ├── extract-message-router.md
│   │       ├── extract_function_invocation.md
│   │       ├── extract_send_message.md
│   │       ├── orchestration_ to_choreography.md
│   │       ├── parallel_to_sns_scatter_gather.md
│   │       ├── replace_polling_with_callback.md
│   │       ├── send-message-via-pipes.md
│   │       └── service_integration.md
│   ├── lambda-performance-optimization-workshop
│   │   ├── Community _ We Improved Our Lambda Warm Start Speed by 95%! Here’s How.pdf
│   │   ├── my-readme.md
│   │   ├── optimizations-for-lambda-functions
│   │   │   ├── CODE_OF_CONDUCT.md
│   │   │   ├── CONTRIBUTING.md
│   │   │   ├── LICENSE
│   │   │   ├── README.md
│   │   │   ├── THIRD-PARTY-LICENSES.txt
│   │   │   ├── diagrams
│   │   │   │   ├── lambda-optimizations.drawio
│   │   │   │   ├── no-proxy.png
│   │   │   │   ├── optimized-aws.png
│   │   │   │   ├── optimized.png
│   │   │   │   ├── proxy.png
│   │   │   │   ├── unoptimized-aws.png
│   │   │   │   └── unoptimized.png
│   │   │   ├── optimized
│   │   │   │   ├── README.md
│   │   │   │   ├── bin
│   │   │   │   │   └── rds-proxy.ts
│   │   │   │   ├── cdk.json
│   │   │   │   ├── lambda
│   │   │   │   │   ├── database
│   │   │   │   │   │   ├── Cache.ts
│   │   │   │   │   │   ├── DBValueObject.ts
│   │   │   │   │   │   ├── DBparameters.ts
│   │   │   │   │   │   ├── PostgresDB.ts
│   │   │   │   │   │   └── StadiumValueObject.ts
│   │   │   │   │   ├── getData.ts
│   │   │   │   │   ├── populate.ts
│   │   │   │   │   ├── powertools
│   │   │   │   │   │   └── utilities.ts
│   │   │   │   │   └── stadium-data.json
│   │   │   │   ├── lib
│   │   │   │   │   ├── lambda-powertuning.ts
│   │   │   │   │   ├── parameters-stack.ts
│   │   │   │   │   └── stadiums-stack.ts
│   │   │   │   ├── package-lock.json
│   │   │   │   ├── package.json
│   │   │   │   ├── scripts
│   │   │   │   │   ├── execute.sh
│   │   │   │   │   ├── loadTestStrategy.yml
│   │   │   │   │   └── params.json
│   │   │   │   └── tsconfig.json
│   │   │   └── unoptimized
│   │   │       ├── README.md
│   │   │       ├── bin
│   │   │       │   └── rds-proxy.ts
│   │   │       ├── cdk.json
│   │   │       ├── lambda
│   │   │       │   ├── database
│   │   │       │   │   ├── DBValueObject.ts
│   │   │       │   │   ├── DBparametersSDK.ts
│   │   │       │   │   └── sequelize.ts
│   │   │       │   ├── getData.ts
│   │   │       │   ├── populate.ts
│   │   │       │   ├── powertools
│   │   │       │   │   └── utilities.ts
│   │   │       │   └── stadium-data.json
│   │   │       ├── lib
│   │   │       │   ├── parameters-stack.ts
│   │   │       │   └── stadiums-stack.ts
│   │   │       ├── package-lock.json
│   │   │       ├── package.json
│   │   │       └── tsconfig.json
│   │   └── show-notes
│   │       ├── Community _ We Improved Our Lambda Warm Start Speed by 95%! Here’s How.pdf
│   │       └── Lambda Performance tuning.md
│   └── lambda-sqs-event-source-mapping-scaling-improvements
│       ├── CODE_OF_CONDUCT.md
│       ├── Introducing faster polling scale-up for AWS Lambda functions configured with Amazon SQS _ AWS Compute Blog.pdf
│       ├── LICENSE
│       ├── README.md
│       ├── bin
│       │   └── app.js
│       ├── cdk.json
│       ├── diagram1.png
│       ├── diagram2.png
│       ├── diagram3.png
│       ├── jest.config.js
│       ├── lib
│       │   └── stack.js
│       ├── my-readme.md
│       ├── package-lock.json
│       ├── package.json
│       ├── sqs-cannon
│       │   ├── index.mjs
│       │   ├── package-lock.json
│       │   └── package.json
│       └── src
│           └── process-document
│               └── index.mjs
├── private-nats
│   ├── docs
│   │   ├── How to solve Private IP exhaustion with Private NAT Solution _ Networking & Content Delivery.pdf
│   │   ├── Leveraging CNI custom networking alongside security groups for pods in Amazon EKS _ Containers.pdf
│   │   └── building-scalable-secure-multi-vpc-network-infrastructure.pdf
│   ├── my-readme.md
│   ├── notes.md
│   └── tgw_natgw_overlapping_cidrs.yaml
├── s3
│   └── s3-to-lambda-diff-checker
│       ├── Building a difference checker with Amazon S3 and AWS Lambda _ AWS Compute Blog.pdf
│       ├── CODE_OF_CONDUCT.md
│       ├── CONTRIBUTING.md
│       ├── LICENSE
│       ├── README.md
│       ├── my-readme.md
│       ├── src
│       │   ├── app.js
│       │   ├── compareS3.js
│       │   ├── deleteS3.js
│       │   ├── package.json
│       │   ├── processS3.js
│       │   ├── test.js
│       │   └── testEvent.json
│       ├── template.yaml
│       └── test.txt
├── security
│   ├── amazonmacie-chatops-remediation
│   │   ├── CODE_OF_CONDUCT.md
│   │   ├── CONTRIBUTING.md
│   │   ├── LICENSE
│   │   ├── README.md
│   │   ├── bin
│   │   │   └── macie-auto-remediation.ts
│   │   ├── cdk.json
│   │   ├── jest.config.js
│   │   ├── lib
│   │   │   └── macie-auto-remediation-stack.ts
│   │   ├── my-readme.md
│   │   ├── package.json
│   │   ├── sensitive-data-samples
│   │   │   ├── dummy-financial-data.txt
│   │   │   └── dummy-personal-data.txt
│   │   ├── src
│   │   │   ├── common
│   │   │   │   └── macie-rem-common
│   │   │   │       ├── index.js
│   │   │   │       ├── package-lock.json
│   │   │   │       └── package.json
│   │   │   ├── macie-finding-handler
│   │   │   │   ├── macie-finding-handler.js
│   │   │   │   ├── package-lock.json
│   │   │   │   └── package.json
│   │   │   ├── macie-remediation-handler
│   │   │   │   ├── macie-remediation-handler.js
│   │   │   │   ├── package-lock.json
│   │   │   │   └── package.json
│   │   │   └── macie-remediator
│   │   │       ├── macie-remediator.js
│   │   │       ├── package-lock.json
│   │   │       └── package.json
│   │   ├── test
│   │   │   └── macie-auto-remediation.test.ts
│   │   └── tsconfig.json
│   ├── automated-security-response-on-aws
│   │   ├── AWSSD-DevNotes.md
│   │   ├── AWSSD-README.md
│   │   ├── CHANGELOG.md
│   │   ├── CODE_OF_CONDUCT.md
│   │   ├── CONTRIBUTING.md
│   │   ├── LICENSE.txt
│   │   ├── NOTICE.txt
│   │   ├── README.md
│   │   ├── automated-security-response-on-aws.pdf
│   │   ├── aws-security-hub-automated-response-and-remediation-implementation-guide.pdf
│   │   ├── buildspec.yml
│   │   ├── deployment
│   │   │   ├── build-open-source-dist.sh
│   │   │   ├── build-s3-dist.sh
│   │   │   ├── requirements_dev.txt
│   │   │   ├── run-unit-tests.sh
│   │   │   ├── solution_env.sh
│   │   │   └── upload-s3-dist.sh
│   │   ├── docs
│   │   │   └── architecture_diagram.png
│   │   ├── my-readme.md
│   │   ├── mypy.ini
│   │   ├── pyproject.toml
│   │   ├── simtest
│   │   │   ├── simdata
│   │   │   │   ├── afsbp-autoscaling.1.json
│   │   │   │   ├── afsbp-cloudformation.1.json
│   │   │   │   ├── afsbp-cloudfront.1.json
│   │   │   │   ├── afsbp-cloudtrail.1.json
│   │   │   │   ├── afsbp-cloudtrail.2.json
│   │   │   │   ├── afsbp-cloudtrail.4.json
│   │   │   │   ├── afsbp-cloudtrail.5.json
│   │   │   │   ├── afsbp-codebuild.2.json
│   │   │   │   ├── afsbp-codebuild.5.json
│   │   │   │   ├── afsbp-config.1.json
│   │   │   │   ├── afsbp-ec2.1.json
│   │   │   │   ├── afsbp-ec2.15.json
│   │   │   │   ├── afsbp-ec2.2.json
│   │   │   │   ├── afsbp-ec2.6.json
│   │   │   │   ├── afsbp-ec2.7.json
│   │   │   │   ├── afsbp-ec2.8.json
│   │   │   │   ├── afsbp-iam.3.json
│   │   │   │   ├── afsbp-iam.7.json
│   │   │   │   ├── afsbp-iam.8-nodetails.json
│   │   │   │   ├── afsbp-iam.8.json
│   │   │   │   ├── afsbp-lambda.1.json
│   │   │   │   ├── afsbp-rds.1-nodetails.json
│   │   │   │   ├── afsbp-rds.1.json
│   │   │   │   ├── afsbp-rds.13.json
│   │   │   │   ├── afsbp-rds.2.json
│   │   │   │   ├── afsbp-rds.4.json
│   │   │   │   ├── afsbp-rds.5.json
│   │   │   │   ├── afsbp-rds.6.json
│   │   │   │   ├── afsbp-rds.7-nodetails.json
│   │   │   │   ├── afsbp-rds.7.json
│   │   │   │   ├── afsbp-rds.8.json
│   │   │   │   ├── afsbp-redshift.1.json
│   │   │   │   ├── afsbp-redshift.3.json
│   │   │   │   ├── afsbp-redshift.4.json
│   │   │   │   ├── afsbp-redshift.6.json
│   │   │   │   ├── afsbp-s3.1.json
│   │   │   │   ├── afsbp-s3.2.json
│   │   │   │   ├── afsbp-s3.3.json
│   │   │   │   ├── afsbp-s3.4.json
│   │   │   │   ├── afsbp-s3.5.json
│   │   │   │   ├── afsbp-s3.6.json
│   │   │   │   ├── afsbp-s3.8.json
│   │   │   │   ├── afsbp-sms.1.json
│   │   │   │   ├── afsbp-sns.2.json
│   │   │   │   ├── afsbp-sqs.1.json
│   │   │   │   ├── cis-1.4-nodetails.json
│   │   │   │   ├── cis-110.json
│   │   │   │   ├── cis-111.json
│   │   │   │   ├── cis-15.json
│   │   │   │   ├── cis-16.json
│   │   │   │   ├── cis-17.json
│   │   │   │   ├── cis-18.json
│   │   │   │   ├── cis-19.json
│   │   │   │   ├── cis-31.json
│   │   │   │   ├── cis-310.json
│   │   │   │   ├── cis-311.json
│   │   │   │   ├── cis-312.json
│   │   │   │   ├── cis-313.json
│   │   │   │   ├── cis-314.json
│   │   │   │   ├── cis-33.json
│   │   │   │   ├── cis-34.json
│   │   │   │   ├── cis-35.json
│   │   │   │   ├── cis-36.json
│   │   │   │   ├── cis-37.json
│   │   │   │   ├── cis-38.json
│   │   │   │   ├── cis-39.json
│   │   │   │   ├── cis-41.json
│   │   │   │   ├── cis-42.json
│   │   │   │   ├── cis13.json
│   │   │   │   ├── cis1314-save.json
│   │   │   │   ├── cis14.json
│   │   │   │   ├── cis15111.json
│   │   │   │   ├── cis21.json
│   │   │   │   ├── cis22.json
│   │   │   │   ├── cis23.json
│   │   │   │   ├── cis24.json
│   │   │   │   ├── cis25.json
│   │   │   │   ├── cis26.json
│   │   │   │   ├── cis27.json
│   │   │   │   ├── cis28.json
│   │   │   │   ├── cis29.json
│   │   │   │   ├── cis32.json
│   │   │   │   ├── cis4142.json
│   │   │   │   ├── cis43.json
│   │   │   │   ├── pci-autoscaling.1.json
│   │   │   │   ├── pci-cloudtrail.1.json
│   │   │   │   ├── pci-cloudtrail.2.json
│   │   │   │   ├── pci-cloudtrail.3.json
│   │   │   │   ├── pci-cloudtrail.4.json
│   │   │   │   ├── pci-codebuild.2.json
│   │   │   │   ├── pci-config.1.json
│   │   │   │   ├── pci-cw.1.json
│   │   │   │   ├── pci-ec2.1.json
│   │   │   │   ├── pci-ec2.2.json
│   │   │   │   ├── pci-ec2.5.json
│   │   │   │   ├── pci-ec2.6.json
│   │   │   │   ├── pci-iam.7.json
│   │   │   │   ├── pci-iam.8.json
│   │   │   │   ├── pci-kms.1.json
│   │   │   │   ├── pci-lambda.1.json
│   │   │   │   ├── pci-pci.iam.6.json
│   │   │   │   ├── pci-rds.1.json
│   │   │   │   ├── pci-rds.2.json
│   │   │   │   ├── pci-redshift.1.json
│   │   │   │   ├── pci-s3.1.json
│   │   │   │   ├── pci-s3.2.json
│   │   │   │   ├── pci-s3.4.json
│   │   │   │   ├── pci-s3.5.json
│   │   │   │   └── pci-s3.6.json
│   │   │   ├── simtest
│   │   │   │   ├── __init__.py
│   │   │   │   ├── boto_session.py
│   │   │   │   ├── controls.py
│   │   │   │   ├── orchestrator.py
│   │   │   │   ├── remediation
│   │   │   │   │   ├── autoscaling.py
│   │   │   │   │   ├── aws_lambda.py
│   │   │   │   │   ├── cloudtrail.py
│   │   │   │   │   ├── cloudwatch.py
│   │   │   │   │   ├── config.py
│   │   │   │   │   ├── ec2.py
│   │   │   │   │   ├── guardduty.py
│   │   │   │   │   ├── iam.py
│   │   │   │   │   ├── kms.py
│   │   │   │   │   ├── rds.py
│   │   │   │   │   ├── s3.py
│   │   │   │   │   └── vpc.py
│   │   │   │   └── remediation_test.py
│   │   │   └── simulate.py
│   │   ├── solution-manifest.yaml
│   │   ├── sonar-project.properties
│   │   ├── source
│   │   │   ├── Orchestrator
│   │   │   │   ├── check_ssm_doc_state.py
│   │   │   │   ├── check_ssm_execution.py
│   │   │   │   ├── exec_ssm_doc.py
│   │   │   │   ├── get_approval_requirement.py
│   │   │   │   ├── schedule_remediation.py
│   │   │   │   ├── send_notifications.py
│   │   │   │   └── test
│   │   │   │       ├── __init__.py
│   │   │   │       ├── conftest.py
│   │   │   │       ├── test_check_ssm_doc_state.py
│   │   │   │       ├── test_check_ssm_execution.py
│   │   │   │       ├── test_data
│   │   │   │       │   └── notifier.json
│   │   │   │       ├── test_exec_ssm_doc.py
│   │   │   │       ├── test_get_approval_requirement.py
│   │   │   │       ├── test_schedule_remediation.py
│   │   │   │       └── test_send_notifications.py
│   │   │   ├── jest.config.ts
│   │   │   ├── layer
│   │   │   │   ├── __init__.py
│   │   │   │   ├── applogger.py
│   │   │   │   ├── awsapi_cached_client.py
│   │   │   │   ├── cloudwatch_metrics.py
│   │   │   │   ├── logger.py
│   │   │   │   ├── metrics.py
│   │   │   │   ├── py.typed
│   │   │   │   ├── sechub_findings.py
│   │   │   │   ├── test
│   │   │   │   │   ├── __init__.py
│   │   │   │   │   ├── conftest.py
│   │   │   │   │   ├── file_utilities.py
│   │   │   │   │   ├── test_api_cached_client.py
│   │   │   │   │   ├── test_applogger.py
│   │   │   │   │   ├── test_cloudwatch_metrics.py
│   │   │   │   │   ├── test_json_data
│   │   │   │   │   │   ├── CIS-1.3.json
│   │   │   │   │   │   ├── CIS-1.4.json
│   │   │   │   │   │   ├── CIS-bad.json
│   │   │   │   │   │   ├── CIS_1-6-multi-select.json
│   │   │   │   │   │   ├── CIS_1-6.json
│   │   │   │   │   │   ├── CIS_unsupversion.json
│   │   │   │   │   │   ├── afsbp-ec2.7.json
│   │   │   │   │   │   └── custom-action-mismatch.json
│   │   │   │   │   ├── test_logger.py
│   │   │   │   │   ├── test_metrics.py
│   │   │   │   │   ├── test_sechub_findings.py
│   │   │   │   │   └── test_utils.py
│   │   │   │   └── utils.py
│   │   │   ├── lib
│   │   │   │   ├── __snapshots__
│   │   │   │   │   ├── admin-account-param.test.ts.snap
│   │   │   │   │   ├── member-stack.test.ts.snap
│   │   │   │   │   └── ssm-doc-rate-limit.test.ts.snap
│   │   │   │   ├── admin-account-param.test.ts
│   │   │   │   ├── admin-account-param.ts
│   │   │   │   ├── admin-playbook.ts
│   │   │   │   ├── appregistry
│   │   │   │   │   └── applyAppRegistry.ts
│   │   │   │   ├── cdk-helper
│   │   │   │   │   ├── add-cfn-nag-suppression.test.ts
│   │   │   │   │   ├── add-cfn-nag-suppression.ts
│   │   │   │   │   ├── choice-param.test.ts
│   │   │   │   │   ├── choice-param.ts
│   │   │   │   │   ├── condition-aspect.ts
│   │   │   │   │   ├── nested-stack.ts
│   │   │   │   │   ├── override-logical-id.test.ts
│   │   │   │   │   ├── override-logical-id.ts
│   │   │   │   │   ├── set-condition.test.ts
│   │   │   │   │   └── set-condition.ts
│   │   │   │   ├── cloudwatch_metrics.ts
│   │   │   │   ├── common-orchestrator-construct.ts
│   │   │   │   ├── member
│   │   │   │   │   ├── bucket-encryption.ts
│   │   │   │   │   ├── log-group.ts
│   │   │   │   │   ├── redshift-audit-logging.ts
│   │   │   │   │   ├── remediation-key.ts
│   │   │   │   │   └── version.ts
│   │   │   │   ├── member-playbook.ts
│   │   │   │   ├── member-stack.test.ts
│   │   │   │   ├── member-stack.ts
│   │   │   │   ├── orchestrator-log-stack.ts
│   │   │   │   ├── orchestrator_roles-construct.ts
│   │   │   │   ├── rds6-remediation-resources.ts
│   │   │   │   ├── remediation_runbook-stack.ts
│   │   │   │   ├── runbook_factory.ts
│   │   │   │   ├── sharrplaybook-construct.ts
│   │   │   │   ├── sns2-remediation-resources.ts
│   │   │   │   ├── solution_deploy-stack.ts
│   │   │   │   ├── ssm-doc-rate-limit.test.ts
│   │   │   │   ├── ssm-doc-rate-limit.ts
│   │   │   │   ├── ssmplaybook.ts
│   │   │   │   ├── tags
│   │   │   │   │   └── applyTag.ts
│   │   │   │   └── wait-provider.ts
│   │   │   ├── package-lock.json
│   │   │   ├── package.json
│   │   │   ├── playbooks
│   │   │   │   ├── AFSBP
│   │   │   │   │   ├── README.md
│   │   │   │   │   ├── bin
│   │   │   │   │   │   └── afsbp.ts
│   │   │   │   │   ├── cdk.json
│   │   │   │   │   ├── description.txt
│   │   │   │   │   ├── ssmdocs
│   │   │   │   │   │   ├── AFSBP_AutoScaling.1.yaml
│   │   │   │   │   │   ├── AFSBP_CloudFormation.1.yaml
│   │   │   │   │   │   ├── AFSBP_CloudFront.1.yaml
│   │   │   │   │   │   ├── AFSBP_CloudFront.12.yaml
│   │   │   │   │   │   ├── AFSBP_CloudTrail.1.yaml
│   │   │   │   │   │   ├── AFSBP_CloudTrail.2.yaml
│   │   │   │   │   │   ├── AFSBP_CloudTrail.4.yaml
│   │   │   │   │   │   ├── AFSBP_CloudTrail.5.yaml
│   │   │   │   │   │   ├── AFSBP_CodeBuild.2.yaml
│   │   │   │   │   │   ├── AFSBP_CodeBuild.5.yaml
│   │   │   │   │   │   ├── AFSBP_Config.1.yaml
│   │   │   │   │   │   ├── AFSBP_EC2.1.yaml
│   │   │   │   │   │   ├── AFSBP_EC2.15.yaml
│   │   │   │   │   │   ├── AFSBP_EC2.18.yaml
│   │   │   │   │   │   ├── AFSBP_EC2.19.yaml
│   │   │   │   │   │   ├── AFSBP_EC2.2.yaml
│   │   │   │   │   │   ├── AFSBP_EC2.23.yaml
│   │   │   │   │   │   ├── AFSBP_EC2.4.yaml
│   │   │   │   │   │   ├── AFSBP_EC2.6.yaml
│   │   │   │   │   │   ├── AFSBP_EC2.7.yaml
│   │   │   │   │   │   ├── AFSBP_EC2.8.yaml
│   │   │   │   │   │   ├── AFSBP_ECR.1.yaml
│   │   │   │   │   │   ├── AFSBP_GuardDuty.1.yaml
│   │   │   │   │   │   ├── AFSBP_IAM.3.yaml
│   │   │   │   │   │   ├── AFSBP_IAM.7.yaml
│   │   │   │   │   │   ├── AFSBP_IAM.8.yaml
│   │   │   │   │   │   ├── AFSBP_Lambda.1.yaml
│   │   │   │   │   │   ├── AFSBP_RDS.1.yaml
│   │   │   │   │   │   ├── AFSBP_RDS.13.yaml
│   │   │   │   │   │   ├── AFSBP_RDS.16.yaml
│   │   │   │   │   │   ├── AFSBP_RDS.2.yaml
│   │   │   │   │   │   ├── AFSBP_RDS.4.yaml
│   │   │   │   │   │   ├── AFSBP_RDS.5.yaml
│   │   │   │   │   │   ├── AFSBP_RDS.6.yaml
│   │   │   │   │   │   ├── AFSBP_RDS.7.yaml
│   │   │   │   │   │   ├── AFSBP_RDS.8.yaml
│   │   │   │   │   │   ├── AFSBP_Redshift.1.yaml
│   │   │   │   │   │   ├── AFSBP_Redshift.3.yaml
│   │   │   │   │   │   ├── AFSBP_Redshift.4.yaml
│   │   │   │   │   │   ├── AFSBP_Redshift.6.yaml
│   │   │   │   │   │   ├── AFSBP_S3.1.yaml
│   │   │   │   │   │   ├── AFSBP_S3.11.yaml
│   │   │   │   │   │   ├── AFSBP_S3.13.yaml
│   │   │   │   │   │   ├── AFSBP_S3.2.yaml
│   │   │   │   │   │   ├── AFSBP_S3.4.yaml
│   │   │   │   │   │   ├── AFSBP_S3.5.yaml
│   │   │   │   │   │   ├── AFSBP_S3.6.yaml
│   │   │   │   │   │   ├── AFSBP_S3.9.yaml
│   │   │   │   │   │   ├── AFSBP_SNS.1.yaml
│   │   │   │   │   │   ├── AFSBP_SNS.2.yaml
│   │   │   │   │   │   ├── AFSBP_SQS.1.yaml
│   │   │   │   │   │   ├── AFSBP_SSM.4.yaml
│   │   │   │   │   │   ├── AFSBP_SecretsManager.1.yaml
│   │   │   │   │   │   ├── AFSBP_SecretsManager.3.yaml
│   │   │   │   │   │   ├── AFSBP_SecretsManager.4.yaml
│   │   │   │   │   │   └── scripts
│   │   │   │   │   │       ├── deserializeApiList.py
│   │   │   │   │   │       └── test
│   │   │   │   │   │           └── test_s3-6_deserialize_api_list.py
│   │   │   │   │   ├── support.txt
│   │   │   │   │   ├── test
│   │   │   │   │   │   ├── __snapshots__
│   │   │   │   │   │   │   └── afsbp_stack.test.ts.snap
│   │   │   │   │   │   ├── afsbp_stack.test.ts
│   │   │   │   │   │   └── test_data
│   │   │   │   │   │       ├── AFSBP-iam.6.json
│   │   │   │   │   │       ├── afsbp_autoscaling1.json
│   │   │   │   │   │       ├── afsbp_ec22.json
│   │   │   │   │   │       ├── afsbp_ec27.json
│   │   │   │   │   │       ├── afsbp_rds1.json
│   │   │   │   │   │       ├── afsbp_rds6.json
│   │   │   │   │   │       └── afsbp_rds7.json
│   │   │   │   │   └── tsconfig.json
│   │   │   │   ├── CIS120
│   │   │   │   │   ├── README.md
│   │   │   │   │   ├── bin
│   │   │   │   │   │   └── cis120.ts
│   │   │   │   │   ├── cdk.json
│   │   │   │   │   ├── description.txt
│   │   │   │   │   ├── ssmdocs
│   │   │   │   │   │   ├── CIS_1.20.yaml
│   │   │   │   │   │   ├── CIS_1.3.yaml
│   │   │   │   │   │   ├── CIS_1.4.yaml
│   │   │   │   │   │   ├── CIS_1.5.yaml
│   │   │   │   │   │   ├── CIS_2.1.yaml
│   │   │   │   │   │   ├── CIS_2.2.yaml
│   │   │   │   │   │   ├── CIS_2.3.yaml
│   │   │   │   │   │   ├── CIS_2.4.yaml
│   │   │   │   │   │   ├── CIS_2.5.yaml
│   │   │   │   │   │   ├── CIS_2.6.yaml
│   │   │   │   │   │   ├── CIS_2.7.yaml
│   │   │   │   │   │   ├── CIS_2.8.yaml
│   │   │   │   │   │   ├── CIS_2.9.yaml
│   │   │   │   │   │   ├── CIS_3.1.yaml
│   │   │   │   │   │   ├── CIS_4.1.yaml
│   │   │   │   │   │   ├── CIS_4.3.yaml
│   │   │   │   │   │   └── scripts
│   │   │   │   │   ├── support.txt
│   │   │   │   │   ├── test
│   │   │   │   │   │   ├── __snapshots__
│   │   │   │   │   │   │   └── cis_stack.test.ts.snap
│   │   │   │   │   │   └── cis_stack.test.ts
│   │   │   │   │   └── tsconfig.json
│   │   │   │   ├── CIS140
│   │   │   │   │   ├── README.md
│   │   │   │   │   ├── bin
│   │   │   │   │   │   └── cis140.ts
│   │   │   │   │   ├── cdk.json
│   │   │   │   │   ├── description.txt
│   │   │   │   │   ├── lib
│   │   │   │   │   │   ├── cis140_playbook-construct.ts
│   │   │   │   │   │   └── control_runbooks-construct.ts
│   │   │   │   │   ├── ssmdocs
│   │   │   │   │   │   ├── CIS140_1.12.ts
│   │   │   │   │   │   ├── CIS140_1.14.ts
│   │   │   │   │   │   ├── CIS140_1.17.ts
│   │   │   │   │   │   ├── CIS140_1.8.ts
│   │   │   │   │   │   ├── CIS140_2.1.1.ts
│   │   │   │   │   │   ├── CIS140_2.1.2.ts
│   │   │   │   │   │   ├── CIS140_2.1.5.1.ts
│   │   │   │   │   │   ├── CIS140_2.1.5.2.ts
│   │   │   │   │   │   ├── CIS140_2.2.1.ts
│   │   │   │   │   │   ├── CIS140_3.1.ts
│   │   │   │   │   │   ├── CIS140_3.2.ts
│   │   │   │   │   │   ├── CIS140_3.3.ts
│   │   │   │   │   │   ├── CIS140_3.4.ts
│   │   │   │   │   │   ├── CIS140_3.5.ts
│   │   │   │   │   │   ├── CIS140_3.6.ts
│   │   │   │   │   │   ├── CIS140_3.7.ts
│   │   │   │   │   │   ├── CIS140_3.8.ts
│   │   │   │   │   │   ├── CIS140_3.9.ts
│   │   │   │   │   │   ├── CIS140_4.1.ts
│   │   │   │   │   │   └── CIS140_5.3.ts
│   │   │   │   │   ├── support.txt
│   │   │   │   │   ├── test
│   │   │   │   │   │   ├── __snapshots__
│   │   │   │   │   │   │   └── cis_stack.test.ts.snap
│   │   │   │   │   │   └── cis_stack.test.ts
│   │   │   │   │   └── tsconfig.json
│   │   │   │   ├── NEWPLAYBOOK
│   │   │   │   │   ├── README.md
│   │   │   │   │   ├── bin
│   │   │   │   │   │   └── newplaybook.ts
│   │   │   │   │   ├── cdk.json
│   │   │   │   │   ├── description.txt
│   │   │   │   │   ├── ssmdocs
│   │   │   │   │   │   ├── NPB_RDS.6.yaml
│   │   │   │   │   │   └── scripts
│   │   │   │   │   ├── support.txt
│   │   │   │   │   ├── test
│   │   │   │   │   │   ├── __snapshots__
│   │   │   │   │   │   │   └── newplaybook_stack.test.ts.snap
│   │   │   │   │   │   └── newplaybook_stack.test.ts
│   │   │   │   │   └── tsconfig.json
│   │   │   │   ├── NIST80053
│   │   │   │   │   ├── bin
│   │   │   │   │   │   └── nist80053.ts
│   │   │   │   │   ├── cdk.json
│   │   │   │   │   ├── description.txt
│   │   │   │   │   ├── lib
│   │   │   │   │   │   ├── NIST80053_playbook-construct.ts
│   │   │   │   │   │   └── control_runbooks-construct.ts
│   │   │   │   │   ├── ssmdocs
│   │   │   │   │   │   ├── NIST80053_AutoScaling.1.ts
│   │   │   │   │   │   ├── NIST80053_CloudFormation.1.ts
│   │   │   │   │   │   ├── NIST80053_CloudFront.1.ts
│   │   │   │   │   │   ├── NIST80053_CloudFront.12.ts
│   │   │   │   │   │   ├── NIST80053_CloudTrail.1.ts
│   │   │   │   │   │   ├── NIST80053_CloudTrail.2.ts
│   │   │   │   │   │   ├── NIST80053_CloudTrail.4.ts
│   │   │   │   │   │   ├── NIST80053_CloudTrail.5.ts
│   │   │   │   │   │   ├── NIST80053_CodeBuild.2.ts
│   │   │   │   │   │   ├── NIST80053_CodeBuild.5.ts
│   │   │   │   │   │   ├── NIST80053_Config.1.ts
│   │   │   │   │   │   ├── NIST80053_EC2.1.ts
│   │   │   │   │   │   ├── NIST80053_EC2.13.ts
│   │   │   │   │   │   ├── NIST80053_EC2.15.ts
│   │   │   │   │   │   ├── NIST80053_EC2.18.ts
│   │   │   │   │   │   ├── NIST80053_EC2.19.ts
│   │   │   │   │   │   ├── NIST80053_EC2.2.ts
│   │   │   │   │   │   ├── NIST80053_EC2.23.ts
│   │   │   │   │   │   ├── NIST80053_EC2.4.ts
│   │   │   │   │   │   ├── NIST80053_EC2.6.ts
│   │   │   │   │   │   ├── NIST80053_EC2.7.ts
│   │   │   │   │   │   ├── NIST80053_EC2.8.ts
│   │   │   │   │   │   ├── NIST80053_ECR.1.ts
│   │   │   │   │   │   ├── NIST80053_GuardDuty.1.ts
│   │   │   │   │   │   ├── NIST80053_IAM.3.ts
│   │   │   │   │   │   ├── NIST80053_IAM.7.ts
│   │   │   │   │   │   ├── NIST80053_IAM.8.ts
│   │   │   │   │   │   ├── NIST80053_KMS.4.ts
│   │   │   │   │   │   ├── NIST80053_Lambda.1.ts
│   │   │   │   │   │   ├── NIST80053_RDS.1.ts
│   │   │   │   │   │   ├── NIST80053_RDS.13.ts
│   │   │   │   │   │   ├── NIST80053_RDS.16.ts
│   │   │   │   │   │   ├── NIST80053_RDS.2.ts
│   │   │   │   │   │   ├── NIST80053_RDS.4.ts
│   │   │   │   │   │   ├── NIST80053_RDS.5.ts
│   │   │   │   │   │   ├── NIST80053_RDS.6.ts
│   │   │   │   │   │   ├── NIST80053_RDS.7.ts
│   │   │   │   │   │   ├── NIST80053_RDS.8.ts
│   │   │   │   │   │   ├── NIST80053_Redshift.1.ts
│   │   │   │   │   │   ├── NIST80053_Redshift.3.ts
│   │   │   │   │   │   ├── NIST80053_Redshift.4.ts
│   │   │   │   │   │   ├── NIST80053_Redshift.6.ts
│   │   │   │   │   │   ├── NIST80053_S3.1.ts
│   │   │   │   │   │   ├── NIST80053_S3.11.ts
│   │   │   │   │   │   ├── NIST80053_S3.13.ts
│   │   │   │   │   │   ├── NIST80053_S3.2.ts
│   │   │   │   │   │   ├── NIST80053_S3.4.ts
│   │   │   │   │   │   ├── NIST80053_S3.5.ts
│   │   │   │   │   │   ├── NIST80053_S3.6.ts
│   │   │   │   │   │   ├── NIST80053_S3.9.ts
│   │   │   │   │   │   ├── NIST80053_SNS.1.ts
│   │   │   │   │   │   ├── NIST80053_SNS.2.ts
│   │   │   │   │   │   ├── NIST80053_SQS.1.ts
│   │   │   │   │   │   ├── NIST80053_SSM.4.ts
│   │   │   │   │   │   ├── NIST80053_SecretsManager.1.ts
│   │   │   │   │   │   ├── NIST80053_SecretsManager.3.ts
│   │   │   │   │   │   └── NIST80053_SecretsManager.4.ts
│   │   │   │   │   ├── support.txt
│   │   │   │   │   ├── test
│   │   │   │   │   │   ├── __snapshots__
│   │   │   │   │   │   │   └── nist_stack.test.ts.snap
│   │   │   │   │   │   └── nist_stack.test.ts
│   │   │   │   │   └── tsconfig.json
│   │   │   │   ├── PCI321
│   │   │   │   │   ├── README.md
│   │   │   │   │   ├── bin
│   │   │   │   │   │   └── pci321.ts
│   │   │   │   │   ├── cdk.json
│   │   │   │   │   ├── description.txt
│   │   │   │   │   ├── ssmdocs
│   │   │   │   │   │   ├── PCI_PCI.AutoScaling.1.yaml
│   │   │   │   │   │   ├── PCI_PCI.CW.1.yaml
│   │   │   │   │   │   ├── PCI_PCI.CloudTrail.1.yaml
│   │   │   │   │   │   ├── PCI_PCI.CloudTrail.2.yaml
│   │   │   │   │   │   ├── PCI_PCI.CloudTrail.3.yaml
│   │   │   │   │   │   ├── PCI_PCI.CloudTrail.4.yaml
│   │   │   │   │   │   ├── PCI_PCI.CodeBuild.2.yaml
│   │   │   │   │   │   ├── PCI_PCI.Config.1.yaml
│   │   │   │   │   │   ├── PCI_PCI.EC2.1.yaml
│   │   │   │   │   │   ├── PCI_PCI.EC2.2.yaml
│   │   │   │   │   │   ├── PCI_PCI.EC2.5.yaml
│   │   │   │   │   │   ├── PCI_PCI.EC2.6.yaml
│   │   │   │   │   │   ├── PCI_PCI.GuardDuty.1.yaml
│   │   │   │   │   │   ├── PCI_PCI.IAM.7.yaml
│   │   │   │   │   │   ├── PCI_PCI.IAM.8.yaml
│   │   │   │   │   │   ├── PCI_PCI.KMS.1.yaml
│   │   │   │   │   │   ├── PCI_PCI.Lambda.1.yaml
│   │   │   │   │   │   ├── PCI_PCI.RDS.1.yaml
│   │   │   │   │   │   ├── PCI_PCI.RDS.2.yaml
│   │   │   │   │   │   ├── PCI_PCI.Redshift.1.yaml
│   │   │   │   │   │   ├── PCI_PCI.S3.1.yaml
│   │   │   │   │   │   ├── PCI_PCI.S3.4.yaml
│   │   │   │   │   │   ├── PCI_PCI.S3.5.yaml
│   │   │   │   │   │   ├── PCI_PCI.S3.6.yaml
│   │   │   │   │   │   └── scripts
│   │   │   │   │   │       ├── pci_get_input_values.py
│   │   │   │   │   │       └── test
│   │   │   │   │   │           └── test_pci_get_input_values.py
│   │   │   │   │   ├── support.txt
│   │   │   │   │   ├── test
│   │   │   │   │   │   ├── __snapshots__
│   │   │   │   │   │   │   └── pci321_stack.test.ts.snap
│   │   │   │   │   │   └── pci321_stack.test.ts
│   │   │   │   │   └── tsconfig.json
│   │   │   │   ├── SC
│   │   │   │   │   ├── README.md
│   │   │   │   │   ├── bin
│   │   │   │   │   │   └── security_controls.ts
│   │   │   │   │   ├── cdk.json
│   │   │   │   │   ├── description.txt
│   │   │   │   │   ├── lib
│   │   │   │   │   │   ├── control_runbooks-construct.ts
│   │   │   │   │   │   └── security_controls_playbook-construct.ts
│   │   │   │   │   ├── ssmdocs
│   │   │   │   │   │   ├── SC_AutoScaling.1.ts
│   │   │   │   │   │   ├── SC_CloudFormation.1.ts
│   │   │   │   │   │   ├── SC_CloudFront.1.ts
│   │   │   │   │   │   ├── SC_CloudFront.12.ts
│   │   │   │   │   │   ├── SC_CloudTrail.1.ts
│   │   │   │   │   │   ├── SC_CloudTrail.2.ts
│   │   │   │   │   │   ├── SC_CloudTrail.4.ts
│   │   │   │   │   │   ├── SC_CloudTrail.5.ts
│   │   │   │   │   │   ├── SC_CloudTrail.6.ts
│   │   │   │   │   │   ├── SC_CloudTrail.7.ts
│   │   │   │   │   │   ├── SC_CloudWatch.1.ts
│   │   │   │   │   │   ├── SC_CodeBuild.2.ts
│   │   │   │   │   │   ├── SC_CodeBuild.5.ts
│   │   │   │   │   │   ├── SC_Config.1.ts
│   │   │   │   │   │   ├── SC_EC2.1.ts
│   │   │   │   │   │   ├── SC_EC2.13.ts
│   │   │   │   │   │   ├── SC_EC2.15.ts
│   │   │   │   │   │   ├── SC_EC2.18.ts
│   │   │   │   │   │   ├── SC_EC2.19.ts
│   │   │   │   │   │   ├── SC_EC2.2.ts
│   │   │   │   │   │   ├── SC_EC2.23.ts
│   │   │   │   │   │   ├── SC_EC2.4.ts
│   │   │   │   │   │   ├── SC_EC2.6.ts
│   │   │   │   │   │   ├── SC_EC2.7.ts
│   │   │   │   │   │   ├── SC_EC2.8.ts
│   │   │   │   │   │   ├── SC_ECR.1.ts
│   │   │   │   │   │   ├── SC_GuardDuty.1.ts
│   │   │   │   │   │   ├── SC_IAM.18.ts
│   │   │   │   │   │   ├── SC_IAM.22.ts
│   │   │   │   │   │   ├── SC_IAM.3.ts
│   │   │   │   │   │   ├── SC_IAM.7.ts
│   │   │   │   │   │   ├── SC_IAM.8.ts
│   │   │   │   │   │   ├── SC_KMS.4.ts
│   │   │   │   │   │   ├── SC_Lambda.1.ts
│   │   │   │   │   │   ├── SC_RDS.1.ts
│   │   │   │   │   │   ├── SC_RDS.13.ts
│   │   │   │   │   │   ├── SC_RDS.16.ts
│   │   │   │   │   │   ├── SC_RDS.2.ts
│   │   │   │   │   │   ├── SC_RDS.4.ts
│   │   │   │   │   │   ├── SC_RDS.5.ts
│   │   │   │   │   │   ├── SC_RDS.6.ts
│   │   │   │   │   │   ├── SC_RDS.7.ts
│   │   │   │   │   │   ├── SC_RDS.8.ts
│   │   │   │   │   │   ├── SC_Redshift.1.ts
│   │   │   │   │   │   ├── SC_Redshift.3.ts
│   │   │   │   │   │   ├── SC_Redshift.4.ts
│   │   │   │   │   │   ├── SC_Redshift.6.ts
│   │   │   │   │   │   ├── SC_S3.1.ts
│   │   │   │   │   │   ├── SC_S3.11.ts
│   │   │   │   │   │   ├── SC_S3.13.ts
│   │   │   │   │   │   ├── SC_S3.2.ts
│   │   │   │   │   │   ├── SC_S3.4.ts
│   │   │   │   │   │   ├── SC_S3.5.ts
│   │   │   │   │   │   ├── SC_S3.6.ts
│   │   │   │   │   │   ├── SC_SNS.1.ts
│   │   │   │   │   │   ├── SC_SNS.2.ts
│   │   │   │   │   │   ├── SC_SQS.1.ts
│   │   │   │   │   │   ├── SC_SSM.4.ts
│   │   │   │   │   │   ├── SC_SecretsManager.1.ts
│   │   │   │   │   │   ├── SC_SecretsManager.3.ts
│   │   │   │   │   │   ├── SC_SecretsManager.4.ts
│   │   │   │   │   │   ├── control_runbook.ts
│   │   │   │   │   │   ├── descriptions
│   │   │   │   │   │   │   ├── AutoScaling.1.md
│   │   │   │   │   │   │   ├── CloudFormation.1.md
│   │   │   │   │   │   │   ├── CloudFront.1.md
│   │   │   │   │   │   │   ├── CloudFront.12.md
│   │   │   │   │   │   │   ├── CloudTrail.1.md
│   │   │   │   │   │   │   ├── CloudTrail.2.md
│   │   │   │   │   │   │   ├── CloudTrail.4.md
│   │   │   │   │   │   │   ├── CloudTrail.5.md
│   │   │   │   │   │   │   ├── CloudTrail.6.md
│   │   │   │   │   │   │   ├── CloudTrail.7.md
│   │   │   │   │   │   │   ├── CloudWatch.1.md
│   │   │   │   │   │   │   ├── CodeBuild.2.md
│   │   │   │   │   │   │   ├── CodeBuild.5.md
│   │   │   │   │   │   │   ├── Config.1.md
│   │   │   │   │   │   │   ├── EC2.1.md
│   │   │   │   │   │   │   ├── EC2.13.md
│   │   │   │   │   │   │   ├── EC2.15.md
│   │   │   │   │   │   │   ├── EC2.18.md
│   │   │   │   │   │   │   ├── EC2.19.md
│   │   │   │   │   │   │   ├── EC2.2.md
│   │   │   │   │   │   │   ├── EC2.23.md
│   │   │   │   │   │   │   ├── EC2.4.md
│   │   │   │   │   │   │   ├── EC2.6.md
│   │   │   │   │   │   │   ├── EC2.7.md
│   │   │   │   │   │   │   ├── EC2.8.md
│   │   │   │   │   │   │   ├── ECR.1.md
│   │   │   │   │   │   │   ├── GuardDuty.1.md
│   │   │   │   │   │   │   ├── IAM.18.md
│   │   │   │   │   │   │   ├── IAM.3.md
│   │   │   │   │   │   │   ├── IAM.7.md
│   │   │   │   │   │   │   ├── IAM.8.md
│   │   │   │   │   │   │   ├── KMS.4.md
│   │   │   │   │   │   │   ├── Lambda.1.md
│   │   │   │   │   │   │   ├── RDS.1.md
│   │   │   │   │   │   │   ├── RDS.13.md
│   │   │   │   │   │   │   ├── RDS.16.md
│   │   │   │   │   │   │   ├── RDS.2.md
│   │   │   │   │   │   │   ├── RDS.4.md
│   │   │   │   │   │   │   ├── RDS.5.md
│   │   │   │   │   │   │   ├── RDS.6.md
│   │   │   │   │   │   │   ├── RDS.7.md
│   │   │   │   │   │   │   ├── RDS.8.md
│   │   │   │   │   │   │   ├── Redshift.1.md
│   │   │   │   │   │   │   ├── Redshift.3.md
│   │   │   │   │   │   │   ├── Redshift.4.md
│   │   │   │   │   │   │   ├── Redshift.6.md
│   │   │   │   │   │   │   ├── S3.1.md
│   │   │   │   │   │   │   ├── S3.11.md
│   │   │   │   │   │   │   ├── S3.13.md
│   │   │   │   │   │   │   ├── S3.2.md
│   │   │   │   │   │   │   ├── S3.4.md
│   │   │   │   │   │   │   ├── S3.5.md
│   │   │   │   │   │   │   ├── S3.6.md
│   │   │   │   │   │   │   ├── SNS.1.md
│   │   │   │   │   │   │   ├── SNS.2.md
│   │   │   │   │   │   │   ├── SQS.1.md
│   │   │   │   │   │   │   ├── SSM.4.md
│   │   │   │   │   │   │   ├── SecretsManager.1.md
│   │   │   │   │   │   │   ├── SecretsManager.3.md
│   │   │   │   │   │   │   └── SecretsManager.4.md
│   │   │   │   │   │   └── scripts
│   │   │   │   │   │       └── check_for_s3_bucket_name.py
│   │   │   │   │   ├── support.txt
│   │   │   │   │   ├── test
│   │   │   │   │   │   ├── __snapshots__
│   │   │   │   │   │   │   └── security_controls_stack.test.ts.snap
│   │   │   │   │   │   └── security_controls_stack.test.ts
│   │   │   │   │   └── tsconfig.json
│   │   │   │   ├── common
│   │   │   │   │   ├── cloudwatch_get_input_values.py
│   │   │   │   │   ├── deserialize_json.py
│   │   │   │   │   ├── get_input_params.py
│   │   │   │   │   ├── parse_input.py
│   │   │   │   │   └── test
│   │   │   │   │       ├── __init__.py
│   │   │   │   │       ├── conftest.py
│   │   │   │   │       ├── test_afsbp_parse.py
│   │   │   │   │       ├── test_cis120_parse.py
│   │   │   │   │       ├── test_cloudwatch_get_input_values.py
│   │   │   │   │       ├── test_deserialize_json.py
│   │   │   │   │       ├── test_pci321_parse.py
│   │   │   │   │       └── test_sc_parse.py
│   │   │   │   └── playbook-index.ts
│   │   │   ├── remediation_runbooks
│   │   │   │   ├── BlockSSMDocumentPublicAccess.yaml
│   │   │   │   ├── ConfigureS3BucketPublicAccessBlock.yaml
│   │   │   │   ├── ConfigureS3PublicAccessBlock.yaml
│   │   │   │   ├── ConfigureSNSTopicForStack.yaml
│   │   │   │   ├── CreateAccessLoggingBucket.yaml
│   │   │   │   ├── CreateCloudTrailMultiRegionTrail.yaml
│   │   │   │   ├── CreateIAMSupportRole.yaml
│   │   │   │   ├── CreateLogMetricFilterAndAlarm.yaml
│   │   │   │   ├── DisablePublicAccessToRDSInstance.yaml
│   │   │   │   ├── DisablePublicAccessToRedshiftCluster.yaml
│   │   │   │   ├── DisablePublicIPAutoAssign.yaml
│   │   │   │   ├── DisableTGWAutoAcceptSharedAttachments.yaml
│   │   │   │   ├── DisableUnrestrictedAccessToHighRiskPorts.yaml
│   │   │   │   ├── EnableAWSConfig.yaml
│   │   │   │   ├── EnableAutoScalingGroupELBHealthCheck.yaml
│   │   │   │   ├── EnableAutoSecretRotation.yaml
│   │   │   │   ├── EnableAutomaticSnapshotsOnRedshiftCluster.yaml
│   │   │   │   ├── EnableAutomaticVersionUpgradeOnRedshiftCluster.yaml
│   │   │   │   ├── EnableBucketEventNotifications.yaml
│   │   │   │   ├── EnableCloudFrontDefaultRootObject.yaml
│   │   │   │   ├── EnableCloudTrailEncryption.yaml
│   │   │   │   ├── EnableCloudTrailLogFileValidation.yaml
│   │   │   │   ├── EnableCloudTrailToCloudWatchLogging.yaml
│   │   │   │   ├── EnableCopyTagsToSnapshotOnRDSCluster.yaml
│   │   │   │   ├── EnableDefaultEncryptionS3.yaml
│   │   │   │   ├── EnableDeliveryStatusLoggingForSNSTopic.yaml
│   │   │   │   ├── EnableEbsEncryptionByDefault.yaml
│   │   │   │   ├── EnableEncryptionForSNSTopic.yaml
│   │   │   │   ├── EnableEncryptionForSQSQueue.yaml
│   │   │   │   ├── EnableEnhancedMonitoringOnRDSInstance.yaml
│   │   │   │   ├── EnableGuardDuty.yaml
│   │   │   │   ├── EnableIMDSV2OnInstance.yaml
│   │   │   │   ├── EnableKeyRotation.yaml
│   │   │   │   ├── EnableMinorVersionUpgradeOnRDSDBInstance.yaml
│   │   │   │   ├── EnableMultiAZOnRDSInstance.yaml
│   │   │   │   ├── EnablePrivateRepositoryScanning.yaml
│   │   │   │   ├── EnableRDSClusterDeletionProtection.yaml
│   │   │   │   ├── EnableRDSInstanceDeletionProtection.yaml
│   │   │   │   ├── EnableRedshiftClusterAuditLogging.yaml
│   │   │   │   ├── EnableVPCFlowLogs.yaml
│   │   │   │   ├── EncryptRDSSnapshot.yaml
│   │   │   │   ├── MakeEBSSnapshotsPrivate.yaml
│   │   │   │   ├── MakeRDSSnapshotPrivate.yaml
│   │   │   │   ├── RemoveCodeBuildPrivilegedMode.yaml
│   │   │   │   ├── RemoveLambdaPublicAccess.yaml
│   │   │   │   ├── RemoveUnusedSecret.yaml
│   │   │   │   ├── RemoveVPCDefaultSecurityGroupRules.yaml
│   │   │   │   ├── ReplaceCodeBuildClearTextCredentials.yaml
│   │   │   │   ├── RevokeUnauthorizedInboundRules.yaml
│   │   │   │   ├── RevokeUnrotatedKeys.yaml
│   │   │   │   ├── RevokeUnusedIAMUserCredentials.yaml
│   │   │   │   ├── S3BlockDenylist.yaml
│   │   │   │   ├── SetCloudFrontOriginDomain.yaml
│   │   │   │   ├── SetIAMPasswordPolicy.yaml
│   │   │   │   ├── SetS3LifecyclePolicy.yaml
│   │   │   │   ├── SetSSLBucketPolicy.yaml
│   │   │   │   ├── UpdateSecretRotationPeriod.yaml
│   │   │   │   └── scripts
│   │   │   │       ├── CreateAccessLoggingBucket_createloggingbucket.py
│   │   │   │       ├── CreateCloudTrailMultiRegionTrail_createcloudtrailbucket.py
│   │   │   │       ├── CreateCloudTrailMultiRegionTrail_createcloudtrailbucketpolicy.py
│   │   │   │       ├── CreateCloudTrailMultiRegionTrail_createloggingbucket.py
│   │   │   │       ├── CreateCloudTrailMultiRegionTrail_enablecloudtrail.py
│   │   │   │       ├── CreateCloudTrailMultiRegionTrail_process_results.py
│   │   │   │       ├── CreateIAMSupportRole.py
│   │   │   │       ├── CreateLogMetricFilterAndAlarm.py
│   │   │   │       ├── CreateLogMetricFilterAndAlarm_createtopic.py
│   │   │   │       ├── DisableTGWAutoAcceptSharedAttachments.py
│   │   │   │       ├── DisableUnrestrictedAccessToHighRiskPorts.py
│   │   │   │       ├── EnableAWSConfig_createconfigbucket.py
│   │   │   │       ├── EnableAWSConfig_createtopic.py
│   │   │   │       ├── EnableAWSConfig_enableconfig.py
│   │   │   │       ├── EnableAWSConfig_summary.py
│   │   │   │       ├── EnableAutoScalingGroupELBHealthCheck_validate.py
│   │   │   │       ├── EnableAutoSecretRotation.py
│   │   │   │       ├── EnableCloudTrailEncryption.py
│   │   │   │       ├── EnableCloudTrailToCloudWatchLogging_waitforloggroup.py
│   │   │   │       ├── EnableGuardDuty.py
│   │   │   │       ├── EnablePrivateRepositoryScanning.py
│   │   │   │       ├── EnableVPCFlowLogs.py
│   │   │   │       ├── GetPublicEBSSnapshots.py
│   │   │   │       ├── MakeEBSSnapshotsPrivate.py
│   │   │   │       ├── MakeRDSSnapshotPrivate.py
│   │   │   │       ├── PutS3BucketPolicyDeny.py
│   │   │   │       ├── RemoveLambdaPublicAccess.py
│   │   │   │       ├── RemoveUnusedSecret.py
│   │   │   │       ├── ReplaceCodeBuildClearTextCredentials.py
│   │   │   │       ├── RevokeUnauthorizedInboundRules.py
│   │   │   │       ├── RevokeUnrotatedKeys.py
│   │   │   │       ├── SetCloudFrontOriginDomain.py
│   │   │   │       ├── SetS3LifecyclePolicy.py
│   │   │   │       ├── SetSSLBucketPolicy.py
│   │   │   │       ├── UpdateSecretRotationPeriod.py
│   │   │   │       ├── block_ssm_doc_public_access.py
│   │   │   │       ├── configure_stack_notifications.py
│   │   │   │       ├── disable_publicip_auto_assign.py
│   │   │   │       ├── enable_bucket_event_notifications.py
│   │   │   │       ├── enable_cloudfront_default_root_object.py
│   │   │   │       ├── enable_delivery_status_logging.py
│   │   │   │       ├── enable_imds_v2_on_instance.py
│   │   │   │       ├── enable_minor_version_upgrade_rds.py
│   │   │   │       ├── remove_codebuild_privileged_mode.py
│   │   │   │       └── test
│   │   │   │           ├── __init__.py
│   │   │   │           ├── conftest.py
│   │   │   │           ├── test_DisableTGWAutoAcceptSharedAttachments.py
│   │   │   │           ├── test_EnableAutoSecretRotation.py
│   │   │   │           ├── test_EnableGuardDuty.py
│   │   │   │           ├── test_EnablePrivateRepositoryScanning.py
│   │   │   │           ├── test_RemoveUnusedSecret.py
│   │   │   │           ├── test_RevokeUnauthorizedInboundRules.py
│   │   │   │           ├── test_SetCloudFrontOriginDomain.py
│   │   │   │           ├── test_SetS3LifecyclePolicy.py
│   │   │   │           ├── test_UpdateSecretRotationPeriod.py
│   │   │   │           ├── test_block_ssm_doc_public_access.py
│   │   │   │           ├── test_configure_stack_notifications.py
│   │   │   │           ├── test_createaccessloggingbucket.py
│   │   │   │           ├── test_createcloudtrailmultiregiontrail.py
│   │   │   │           ├── test_createlogmetricfilterandalarm.py
│   │   │   │           ├── test_disable_publicip_auto_assign.py
│   │   │   │           ├── test_disableunrestrictedaccesstohighriskports.py
│   │   │   │           ├── test_enable_bucket_event_notifications.py
│   │   │   │           ├── test_enable_cloudfront_default_root_object.py
│   │   │   │           ├── test_enable_delivery_status_logging.py
│   │   │   │           ├── test_enable_imds_V2_on_instance.py
│   │   │   │           ├── test_enable_minor_version_upgrade_rds.py
│   │   │   │           ├── test_enableautoscalinggroupelbhealthcheck.py
│   │   │   │           ├── test_enableawsconfig.py
│   │   │   │           ├── test_enablecloudtrailencryption.py
│   │   │   │           ├── test_enablecloudtrailtocloudwatchlogging.py
│   │   │   │           ├── test_enablevpcflowlogs.py
│   │   │   │           ├── test_makeebssnapshotsprivate.py
│   │   │   │           ├── test_makerdssnapshotprivate.py
│   │   │   │           ├── test_puts3bucketpolicydeny.py
│   │   │   │           ├── test_remove_codebuild_privileged_mode.py
│   │   │   │           ├── test_removelambdapublicaccess.py
│   │   │   │           ├── test_replacecodebuildcleartextcredentials.py
│   │   │   │           ├── test_revokeunrotatedkeys.py
│   │   │   │           └── test_s3SslOnlyBucketPolicy.py
│   │   │   ├── solution_deploy
│   │   │   │   ├── bin
│   │   │   │   │   └── solution_deploy.ts
│   │   │   │   ├── cdk.json
│   │   │   │   └── source
│   │   │   │       ├── action_target_provider.py
│   │   │   │       ├── cfnresponse.py
│   │   │   │       ├── deployment_metrics_custom_resource.py
│   │   │   │       ├── test
│   │   │   │       │   ├── __init__.py
│   │   │   │       │   ├── conftest.py
│   │   │   │       │   ├── test_action_target_provider.py
│   │   │   │       │   ├── test_cfnresponse.py
│   │   │   │       │   ├── test_deployment_custom_resource.py
│   │   │   │       │   └── test_wait_provider.py
│   │   │   │       └── wait_provider.py
│   │   │   ├── test
│   │   │   │   ├── __snapshots__
│   │   │   │   │   ├── orchestrator.test.ts.snap
│   │   │   │   │   ├── orchestrator_logs.test.ts.snap
│   │   │   │   │   ├── runbook_stack.test.ts.snap
│   │   │   │   │   └── solution_deploy.test.ts.snap
│   │   │   │   ├── orchestrator.test.ts
│   │   │   │   ├── orchestrator_logs.test.ts
│   │   │   │   ├── regex_registry.test.ts
│   │   │   │   ├── regex_registry.ts
│   │   │   │   ├── runbook_stack.test.ts
│   │   │   │   ├── runbook_validator.test.ts
│   │   │   │   ├── solution_deploy.test.ts
│   │   │   │   ├── ssmplaybook.test.ts
│   │   │   │   └── test_data
│   │   │   │       ├── tstest-cis29.yaml
│   │   │   │       ├── tstest-rds1.yaml
│   │   │   │       └── tstest-runbook.yaml
│   │   │   └── tsconfig.json
│   │   └── tox.ini
│   ├── aws-iam-temporary-elevated-access-broker
│   │   ├── CODE_OF_CONDUCT.md
│   │   ├── CONTRIBUTING.md
│   │   ├── LICENSE
│   │   ├── Managing temporary elevated access to your AWS environment _ AWS Security Blog.pdf
│   │   ├── README.md
│   │   ├── dynamodb-stream
│   │   │   └── dbstream.py
│   │   ├── lambda-layer
│   │   │   └── python
│   │   │       └── requirements.txt
│   │   ├── my-readme.md
│   │   ├── okta-authorizer
│   │   │   ├── index.js
│   │   │   └── package.json
│   │   ├── template.yaml
│   │   ├── ui-api
│   │   │   └── api.py
│   │   └── ui-frontend
│   │       ├── package.json
│   │       ├── public
│   │       │   ├── Logo.png
│   │       │   ├── favicon.ico
│   │       │   ├── icon-full.png
│   │       │   ├── icon.png
│   │       │   ├── index.html
│   │       │   ├── logo-light-full.png
│   │       │   ├── manifest.json
│   │       │   ├── robots.txt
│   │       │   └── style.css
│   │       ├── src
│   │       │   ├── App.tsx
│   │       │   ├── common
│   │       │   │   ├── CustomSelect.tsx
│   │       │   │   └── api.tsx
│   │       │   ├── components
│   │       │   │   ├── AppLayout
│   │       │   │   │   └── index.tsx
│   │       │   │   ├── Audit
│   │       │   │   │   ├── AuditDashboard.tsx
│   │       │   │   │   └── AuditTable.tsx
│   │       │   │   ├── Logoff
│   │       │   │   │   └── Logoff.tsx
│   │       │   │   ├── Request
│   │       │   │   │   ├── RequestDashboard.tsx
│   │       │   │   │   ├── RequestForm.tsx
│   │       │   │   │   └── RequestTable.tsx
│   │       │   │   ├── Review
│   │       │   │   │   ├── PendingTable.tsx
│   │       │   │   │   ├── ReviewDashboard.tsx
│   │       │   │   │   ├── ReviewView.tsx
│   │       │   │   │   └── ReviewedTable.tsx
│   │       │   │   └── home
│   │       │   │       ├── HomePageContent.tsx
│   │       │   │       ├── TEA.png
│   │       │   │       └── styles.css
│   │       │   ├── config
│   │       │   │   └── index.ts
│   │       │   ├── data
│   │       │   │   └── index.ts
│   │       │   ├── index.tsx
│   │       │   ├── interfaces
│   │       │   │   └── index.ts
│   │       │   ├── react-app-env.d.ts
│   │       │   └── redux
│   │       │       ├── Store.ts
│   │       │       ├── actions
│   │       │       │   └── index.ts
│   │       │       └── reducers
│   │       │           └── index.ts
│   │       └── tsconfig.json
│   ├── aws-security-hub-findings-account-data-enrichment
│   │   ├── CODE_OF_CONDUCT.md
│   │   ├── CONTRIBUTING.md
│   │   ├── How to enrich AWS Security Hub findings with account metadata _ AWS Security Blog.pdf
│   │   ├── LICENSE
│   │   ├── NOTICE
│   │   ├── README.md
│   │   ├── __init__.py
│   │   ├── aws-securityhub-findings-resource-enrichment.yaml
│   │   ├── cleanup.sh
│   │   ├── create-bucket.sh
│   │   ├── deploy.sh
│   │   ├── enrichment_function
│   │   │   ├── README.md
│   │   │   ├── __init__.py
│   │   │   ├── import_findings
│   │   │   │   ├── __init__.py
│   │   │   │   ├── app.py
│   │   │   │   └── helper.py
│   │   │   ├── requirements.txt
│   │   │   └── schema
│   │   │       └── aws
│   │   │           └── securityhub
│   │   │               └── securityhubfindingsimported
│   │   │                   ├── AWSEvent.py
│   │   │                   ├── SecurityHubFindingsImported.py
│   │   │                   ├── __init__.py
│   │   │                   └── marshaller.py
│   │   ├── management-account-contact-readonly-role-template.yml
│   │   ├── my-readme.md
│   │   ├── profile.txt
│   │   └── solution.png
│   ├── aws-security-reference-architecture-examples
│   │   ├── AWS Security Reference Architecture_ A guide to designing with AWS security services _ AWS Security Blog.pdf
│   │   ├── AWS-SRA-KEY-INFO.md
│   │   ├── CHANGELOG.md
│   │   ├── CONTRIBUTING.md
│   │   ├── LICENSE
│   │   ├── LICENSE-SAMPLECODE
│   │   ├── LICENSE-SUMMARY
│   │   ├── README.md
│   │   ├── aws_sra_examples
│   │   │   ├── docs
│   │   │   │   ├── CFCT-DEPLOYMENT-INSTRUCTIONS.md
│   │   │   │   ├── DOWNLOAD-AND-STAGE-SOLUTIONS.md
│   │   │   │   └── artifacts
│   │   │   │       ├── easy-setup-process.png
│   │   │   │       ├── organizations-setup-process.png
│   │   │   │       ├── terraform-control-tower-process.png
│   │   │   │       ├── terraform-process.png
│   │   │   │       ├── where-to-start-process.png
│   │   │   │       └── where-to-start-process.pptx
│   │   │   ├── easy_setup
│   │   │   │   ├── README.md
│   │   │   │   ├── assets
│   │   │   │   │   ├── SRA-Easy-Setup.png
│   │   │   │   │   └── sra-easy-setup-architecture.vsdx
│   │   │   │   ├── customizations_for_aws_control_tower
│   │   │   │   │   ├── README.md
│   │   │   │   │   └── manifest.yaml
│   │   │   │   └── templates
│   │   │   │       └── sra-easy-setup.yaml
│   │   │   ├── modules
│   │   │   │   ├── cloudtrail-org-module
│   │   │   │   │   └── templates
│   │   │   │   │       ├── sra-cloudtrail-org-module-main.yaml
│   │   │   │   │       └── sra-cloudtrail-org-solution.yaml
│   │   │   │   ├── config-org-module
│   │   │   │   │   └── templates
│   │   │   │   │       ├── sra-config-org-module-main.yaml
│   │   │   │   │       └── sra-config-org-solution.yaml
│   │   │   │   ├── guardduty-org-module
│   │   │   │   │   └── templates
│   │   │   │   │       ├── sra-guardduty-org-module-main.yaml
│   │   │   │   │       └── sra-guardduty-org-solution.yaml
│   │   │   │   └── securityhub-org-module
│   │   │   │       └── templates
│   │   │   │           ├── sra-securityhub-org-module-main.yaml
│   │   │   │           └── sra-securityhub-org-solution.yaml
│   │   │   ├── solutions
│   │   │   │   ├── README.md
│   │   │   │   ├── account
│   │   │   │   │   └── account_alternate_contacts
│   │   │   │   │       ├── README.md
│   │   │   │   │       ├── customizations_for_aws_control_tower
│   │   │   │   │       │   ├── README.md
│   │   │   │   │       │   ├── manifest.yaml
│   │   │   │   │       │   └── parameters
│   │   │   │   │       │       └── sra-account-alternate-contacts-main-ssm.json
│   │   │   │   │       ├── documentation
│   │   │   │   │       │   ├── account-alternate-contacts.png
│   │   │   │   │       │   └── account-alternate-contacts.pptx
│   │   │   │   │       ├── lambda
│   │   │   │   │       │   └── src
│   │   │   │   │       │       ├── app.py
│   │   │   │   │       │       └── requirements.txt
│   │   │   │   │       └── templates
│   │   │   │   │           ├── sra-account-alternate-contacts-configuration-role.yaml
│   │   │   │   │           ├── sra-account-alternate-contacts-global-events.yaml
│   │   │   │   │           ├── sra-account-alternate-contacts-main-ssm.yaml
│   │   │   │   │           └── sra-account-alternate-contacts.yaml
│   │   │   │   ├── ami_bakery
│   │   │   │   │   └── ami_bakery_org
│   │   │   │   │       ├── README.md
│   │   │   │   │       ├── customizations_for_aws_control_tower
│   │   │   │   │       │   ├── README.md
│   │   │   │   │       │   ├── manifest.yaml
│   │   │   │   │       │   └── parameters
│   │   │   │   │       │       └── sra-ami-bakery-org-main-ssm.json
│   │   │   │   │       ├── documentation
│   │   │   │   │       │   ├── sra-ami-bakery-org.png
│   │   │   │   │       │   └── sra-ami-bakery-org.pptx
│   │   │   │   │       ├── lambda
│   │   │   │   │       │   └── src
│   │   │   │   │       │       ├── app.py
│   │   │   │   │       │       ├── cfn_trust_relationship.json
│   │   │   │   │       │       ├── cloudformation_policy.json
│   │   │   │   │       │       ├── codepipeline.py
│   │   │   │   │       │       ├── codepipeline_policy.json
│   │   │   │   │       │       ├── common.py
│   │   │   │   │       │       ├── cp_trust_relationship.json
│   │   │   │   │       │       ├── iam.py
│   │   │   │   │       │       ├── requirements.txt
│   │   │   │   │       │       ├── s3.py
│   │   │   │   │       │       ├── s3_bucket_policy.json
│   │   │   │   │       │       ├── sra-ami-bakery-org-amazon-linux-stig-hardened.yaml
│   │   │   │   │       │       ├── sra-ami-bakery-org-ubuntu-pro-20-04-cis-level-1-hardened.yaml
│   │   │   │   │       │       └── sra-ami-bakery-org-windows-server-2022-stig-hardened.yaml
│   │   │   │   │       ├── layer
│   │   │   │   │       │   └── boto3
│   │   │   │   │       │       └── package.txt
│   │   │   │   │       └── templates
│   │   │   │   │           ├── sra-ami-bakery-org-configuration-role.yaml
│   │   │   │   │           ├── sra-ami-bakery-org-configuration.yaml
│   │   │   │   │           └── sra-ami-bakery-org-main-ssm.yaml
│   │   │   │   ├── cloudtrail
│   │   │   │   │   └── cloudtrail_org
│   │   │   │   │       ├── README.md
│   │   │   │   │       ├── customizations_for_aws_control_tower
│   │   │   │   │       │   ├── README.md
│   │   │   │   │       │   ├── manifest.yaml
│   │   │   │   │       │   └── parameters
│   │   │   │   │       │       └── sra-cloudtrail-org-main-ssm.json
│   │   │   │   │       ├── documentation
│   │   │   │   │       │   ├── sra-cloudtrail-org-terraform.png
│   │   │   │   │       │   ├── sra-cloudtrail-org.png
│   │   │   │   │       │   └── sra-cloudtrail-org.pptx
│   │   │   │   │       ├── lambda
│   │   │   │   │       │   └── src
│   │   │   │   │       │       ├── app.py
│   │   │   │   │       │       └── requirements.txt
│   │   │   │   │       ├── layer
│   │   │   │   │       │   └── boto3
│   │   │   │   │       │       └── package.txt
│   │   │   │   │       └── templates
│   │   │   │   │           ├── sra-cloudtrail-org-bucket.yaml
│   │   │   │   │           ├── sra-cloudtrail-org-kms.yaml
│   │   │   │   │           ├── sra-cloudtrail-org-main-ssm.yaml
│   │   │   │   │           └── sra-cloudtrail-org.yaml
│   │   │   │   ├── common
│   │   │   │   │   ├── common_cfct_setup
│   │   │   │   │   │   ├── README.md
│   │   │   │   │   │   ├── documentation
│   │   │   │   │   │   │   ├── common-cfct-setup.png
│   │   │   │   │   │   │   └── common-cfct-setup.pptx
│   │   │   │   │   │   └── templates
│   │   │   │   │   │       └── sra-common-cfct-setup-main.yaml
│   │   │   │   │   ├── common_prerequisites
│   │   │   │   │   │   ├── README.md
│   │   │   │   │   │   ├── documentation
│   │   │   │   │   │   │   ├── common-prerequisites.png
│   │   │   │   │   │   │   ├── common-prerequisites.pptx
│   │   │   │   │   │   │   └── terraform-common-prerequisites.png
│   │   │   │   │   │   ├── lambda
│   │   │   │   │   │   │   └── src
│   │   │   │   │   │   │       ├── app.py
│   │   │   │   │   │   │       ├── inline_lambda.py
│   │   │   │   │   │   │       └── requirements.txt
│   │   │   │   │   │   └── templates
│   │   │   │   │   │       ├── sra-common-prerequisites-control-tower-execution-role.yaml
│   │   │   │   │   │       ├── sra-common-prerequisites-main-ssm.yaml
│   │   │   │   │   │       ├── sra-common-prerequisites-management-account-parameters.yaml
│   │   │   │   │   │       ├── sra-common-prerequisites-member-account-parameters.yaml
│   │   │   │   │   │       ├── sra-common-prerequisites-secrets-kms.yaml
│   │   │   │   │   │       ├── sra-common-prerequisites-stackset-admin-role.yaml
│   │   │   │   │   │       ├── sra-common-prerequisites-stackset-execution-role.yaml
│   │   │   │   │   │       └── sra-common-prerequisites-staging-s3-bucket.yaml
│   │   │   │   │   └── common_register_delegated_administrator
│   │   │   │   │       ├── README.md
│   │   │   │   │       ├── customizations_for_aws_control_tower
│   │   │   │   │       │   ├── README.md
│   │   │   │   │       │   ├── manifest.yaml
│   │   │   │   │       │   └── parameters
│   │   │   │   │       │       └── sra-common-register-delegated-administrator-ssm.json
│   │   │   │   │       ├── documentation
│   │   │   │   │       │   ├── sra-common-register-delegated-administrator-terraform.png
│   │   │   │   │       │   ├── sra-common-register-delegated-administrator.png
│   │   │   │   │       │   └── sra-common-register-delegated-administrator.pptx
│   │   │   │   │       ├── lambda
│   │   │   │   │       │   └── src
│   │   │   │   │       │       ├── app.py
│   │   │   │   │       │       └── requirements.txt
│   │   │   │   │       └── templates
│   │   │   │   │           └── sra-common-register-delegated-administrator-ssm.yaml
│   │   │   │   ├── config
│   │   │   │   │   ├── config_aggregator_org
│   │   │   │   │   │   ├── README.md
│   │   │   │   │   │   ├── customizations_for_aws_control_tower
│   │   │   │   │   │   │   ├── README.md
│   │   │   │   │   │   │   ├── manifest.yaml
│   │   │   │   │   │   │   └── parameters
│   │   │   │   │   │   │       └── sra-config-aggregator-org-main-ssm.json
│   │   │   │   │   │   ├── documentation
│   │   │   │   │   │   │   ├── config-aggregator-org.png
│   │   │   │   │   │   │   └── config-aggregator-org.pptx
│   │   │   │   │   │   └── templates
│   │   │   │   │   │       ├── sra-config-aggregator-org-configuration.yaml
│   │   │   │   │   │       └── sra-config-aggregator-org-main-ssm.yaml
│   │   │   │   │   ├── config_conformance_pack_org
│   │   │   │   │   │   ├── README.md
│   │   │   │   │   │   ├── customizations_for_aws_control_tower
│   │   │   │   │   │   │   ├── README.md
│   │   │   │   │   │   │   ├── manifest.yaml
│   │   │   │   │   │   │   └── parameters
│   │   │   │   │   │   │       └── sra-config-conformance-pack-org-main-ssm.json
│   │   │   │   │   │   ├── documentation
│   │   │   │   │   │   │   ├── config-conformance-pack-org.png
│   │   │   │   │   │   │   └── config-conformance-pack-org.pptx
│   │   │   │   │   │   ├── scripts
│   │   │   │   │   │   │   └── list_config_recorder_status.py
│   │   │   │   │   │   └── templates
│   │   │   │   │   │       ├── aws_config_conformance_packs
│   │   │   │   │   │       │   └── Operational-Best-Practices-for-Encryption-and-Keys.yaml
│   │   │   │   │   │       ├── sra-config-conformance-pack-org-delivery-bucket.yaml
│   │   │   │   │   │       ├── sra-config-conformance-pack-org-deployment.yaml
│   │   │   │   │   │       └── sra-config-conformance-pack-org-main-ssm.yaml
│   │   │   │   │   ├── config_management_account
│   │   │   │   │   │   ├── README.md
│   │   │   │   │   │   ├── customizations_for_aws_control_tower
│   │   │   │   │   │   │   ├── README.md
│   │   │   │   │   │   │   ├── manifest.yaml
│   │   │   │   │   │   │   └── parameters
│   │   │   │   │   │   │       └── sra-config-management-account-main-ssm.json
│   │   │   │   │   │   ├── documentation
│   │   │   │   │   │   │   ├── config-management-account.png
│   │   │   │   │   │   │   └── config-management-account.pptx
│   │   │   │   │   │   ├── lambda
│   │   │   │   │   │   │   └── src
│   │   │   │   │   │   │       ├── app.py
│   │   │   │   │   │   │       └── requirements.txt
│   │   │   │   │   │   └── templates
│   │   │   │   │   │       ├── sra-config-management-account-main-ssm.yaml
│   │   │   │   │   │       ├── sra-config-management-account-role.yaml
│   │   │   │   │   │       ├── sra-config-management-account-update-aggregator.yaml
│   │   │   │   │   │       └── sra-config-management-account.yaml
│   │   │   │   │   └── config_org
│   │   │   │   │       ├── README.md
│   │   │   │   │       ├── documentation
│   │   │   │   │       │   ├── sra-config-org.png
│   │   │   │   │       │   ├── sra-config-org.pptx
│   │   │   │   │       │   └── ~$sra-config-org.pptx
│   │   │   │   │       ├── lambda
│   │   │   │   │       │   └── src
│   │   │   │   │       │       ├── app.py
│   │   │   │   │       │       ├── common.py
│   │   │   │   │       │       ├── config.py
│   │   │   │   │       │       └── requirements.txt
│   │   │   │   │       └── templates
│   │   │   │   │           ├── sra-config-aggregator-org-configuration.yaml
│   │   │   │   │           ├── sra-config-org-configuration-role.yaml
│   │   │   │   │           ├── sra-config-org-configuration.yaml
│   │   │   │   │           ├── sra-config-org-delivery-kms-key.yaml
│   │   │   │   │           ├── sra-config-org-delivery-s3-bucket.yaml
│   │   │   │   │           ├── sra-config-org-global-events.yaml
│   │   │   │   │           ├── sra-config-org-main-ssm.yaml
│   │   │   │   │           ├── sra-config-org-sns-kms-key.yaml
│   │   │   │   │           └── sra-config-sns.yaml
│   │   │   │   ├── detective
│   │   │   │   │   └── detective_org
│   │   │   │   │       ├── README.md
│   │   │   │   │       ├── customizations_for_aws_control_tower
│   │   │   │   │       │   ├── README.md
│   │   │   │   │       │   ├── manifest.yaml
│   │   │   │   │       │   └── parameters
│   │   │   │   │       │       └── sra-detective-org-main-ssm.json
│   │   │   │   │       ├── documentation
│   │   │   │   │       │   ├── sra-detective-org.png
│   │   │   │   │       │   └── sra-detective-org.pptx
│   │   │   │   │       ├── lambda
│   │   │   │   │       │   └── src
│   │   │   │   │       │       ├── app.py
│   │   │   │   │       │       ├── common.py
│   │   │   │   │       │       ├── detective.py
│   │   │   │   │       │       └── requirements.txt
│   │   │   │   │       ├── layer
│   │   │   │   │       │   └── boto3
│   │   │   │   │       │       └── package.txt
│   │   │   │   │       └── templates
│   │   │   │   │           ├── sra-detective-org-configuration-role.yaml
│   │   │   │   │           ├── sra-detective-org-configuration.yaml
│   │   │   │   │           └── sra-detective-org-main-ssm.yaml
│   │   │   │   ├── ec2
│   │   │   │   │   └── ec2_default_ebs_encryption
│   │   │   │   │       ├── README.md
│   │   │   │   │       ├── customizations_for_aws_control_tower
│   │   │   │   │       │   ├── README.md
│   │   │   │   │       │   ├── manifest.yaml
│   │   │   │   │       │   └── parameters
│   │   │   │   │       │       └── sra-ec2-default-ebs-encryption-main-ssm.json
│   │   │   │   │       ├── documentation
│   │   │   │   │       │   ├── ec2-default-ebs-encryption.png
│   │   │   │   │       │   └── ec2-default-ebs-encryption.pptx
│   │   │   │   │       ├── lambda
│   │   │   │   │       │   └── src
│   │   │   │   │       │       ├── app.py
│   │   │   │   │       │       └── requirements.txt
│   │   │   │   │       └── templates
│   │   │   │   │           ├── sra-ec2-default-ebs-encryption-global-events.yaml
│   │   │   │   │           ├── sra-ec2-default-ebs-encryption-main-ssm.yaml
│   │   │   │   │           ├── sra-ec2-default-ebs-encryption-role.yaml
│   │   │   │   │           └── sra-ec2-default-ebs-encryption.yaml
│   │   │   │   ├── firewall_manager
│   │   │   │   │   └── firewall_manager_org
│   │   │   │   │       ├── README.md
│   │   │   │   │       ├── customizations_for_aws_control_tower
│   │   │   │   │       │   ├── README.md
│   │   │   │   │       │   ├── manifest.yaml
│   │   │   │   │       │   └── parameters
│   │   │   │   │       │       └── sra-firewall-manager-org-main-ssm.json
│   │   │   │   │       ├── documentation
│   │   │   │   │       │   ├── firewall-manager-org.png
│   │   │   │   │       │   └── firewall-manager-org.pptx
│   │   │   │   │       ├── lambda
│   │   │   │   │       │   └── src
│   │   │   │   │       │       ├── app.py
│   │   │   │   │       │       └── requirements.txt
│   │   │   │   │       └── templates
│   │   │   │   │           ├── sra-firewall-manager-org-delegate-admin.yaml
│   │   │   │   │           ├── sra-firewall-manager-org-disassociate-iam-role.yaml
│   │   │   │   │           ├── sra-firewall-manager-org-main-ssm.yaml
│   │   │   │   │           ├── sra-firewall-manager-org-sg-policy.yaml
│   │   │   │   │           └── sra-firewall-manager-org-waf-policy.yaml
│   │   │   │   ├── guardduty
│   │   │   │   │   └── guardduty_org
│   │   │   │   │       ├── README.md
│   │   │   │   │       ├── customizations_for_aws_control_tower
│   │   │   │   │       │   ├── README.md
│   │   │   │   │       │   ├── manifest.yaml
│   │   │   │   │       │   └── parameters
│   │   │   │   │       │       └── sra-guardduty-org-main-ssm.json
│   │   │   │   │       ├── documentation
│   │   │   │   │       │   ├── guardduty-org-terraform.png
│   │   │   │   │       │   ├── guardduty-org.png
│   │   │   │   │       │   └── guardduty-org.pptx
│   │   │   │   │       ├── lambda
│   │   │   │   │       │   └── src
│   │   │   │   │       │       ├── app.py
│   │   │   │   │       │       ├── common.py
│   │   │   │   │       │       ├── guardduty.py
│   │   │   │   │       │       └── requirements.txt
│   │   │   │   │       ├── layer
│   │   │   │   │       │   └── boto3
│   │   │   │   │       │       └── package.txt
│   │   │   │   │       └── templates
│   │   │   │   │           ├── sra-guardduty-org-configuration-role.yaml
│   │   │   │   │           ├── sra-guardduty-org-configuration.yaml
│   │   │   │   │           ├── sra-guardduty-org-delete-detector-role.yaml
│   │   │   │   │           ├── sra-guardduty-org-delivery-kms-key.yaml
│   │   │   │   │           ├── sra-guardduty-org-delivery-s3-bucket.yaml
│   │   │   │   │           └── sra-guardduty-org-main-ssm.yaml
│   │   │   │   ├── iam
│   │   │   │   │   ├── iam_access_analyzer
│   │   │   │   │   │   ├── README.md
│   │   │   │   │   │   ├── customizations_for_aws_control_tower
│   │   │   │   │   │   │   ├── README.md
│   │   │   │   │   │   │   ├── manifest.yaml
│   │   │   │   │   │   │   └── parameters
│   │   │   │   │   │   │       └── sra-iam-access-analyzer-main-ssm.json
│   │   │   │   │   │   ├── documentation
│   │   │   │   │   │   │   ├── iam-access-analyzer-terraform.png
│   │   │   │   │   │   │   ├── iam-access-analyzer.png
│   │   │   │   │   │   │   └── iam-access-analyzer.pptx
│   │   │   │   │   │   └── templates
│   │   │   │   │   │       ├── sra-iam-access-analyzer-account.yaml
│   │   │   │   │   │       ├── sra-iam-access-analyzer-main-ssm.yaml
│   │   │   │   │   │       └── sra-iam-access-analyzer-org.yaml
│   │   │   │   │   └── iam_password_policy
│   │   │   │   │       ├── README.md
│   │   │   │   │       ├── customizations_for_aws_control_tower
│   │   │   │   │       │   ├── README.md
│   │   │   │   │       │   ├── manifest.yaml
│   │   │   │   │       │   └── parameters
│   │   │   │   │       │       └── sra-iam-password-policy-main-ssm.json
│   │   │   │   │       ├── documentation
│   │   │   │   │       │   ├── iam-password-policy-terraform.png
│   │   │   │   │       │   ├── iam-password-policy.png
│   │   │   │   │       │   └── iam-password-policy.pptx
│   │   │   │   │       ├── lambda
│   │   │   │   │       │   └── src
│   │   │   │   │       │       ├── app.py
│   │   │   │   │       │       └── requirements.txt
│   │   │   │   │       └── templates
│   │   │   │   │           ├── sra-iam-password-policy-main-ssm.yaml
│   │   │   │   │           └── sra-iam-password-policy.yaml
│   │   │   │   ├── inspector
│   │   │   │   │   └── inspector_org
│   │   │   │   │       ├── README.md
│   │   │   │   │       ├── customizations_for_aws_control_tower
│   │   │   │   │       │   ├── README.md
│   │   │   │   │       │   ├── manifest.yaml
│   │   │   │   │       │   └── parameters
│   │   │   │   │       │       └── sra-inspector-org-main-ssm.json
│   │   │   │   │       ├── documentation
│   │   │   │   │       │   ├── Inspector_Code_Workflows.jpg
│   │   │   │   │       │   ├── inspector-org-terraform.png
│   │   │   │   │       │   ├── inspector-org.png
│   │   │   │   │       │   └── inspector-org.pptx
│   │   │   │   │       ├── lambda
│   │   │   │   │       │   └── src
│   │   │   │   │       │       ├── app.py
│   │   │   │   │       │       ├── common.py
│   │   │   │   │       │       ├── inspector.py
│   │   │   │   │       │       └── requirements.txt
│   │   │   │   │       ├── layer
│   │   │   │   │       │   └── boto3
│   │   │   │   │       │       └── package.txt
│   │   │   │   │       └── templates
│   │   │   │   │           ├── sra-inspector-org-configuration-role.yaml
│   │   │   │   │           ├── sra-inspector-org-configuration.yaml
│   │   │   │   │           ├── sra-inspector-org-global-events.yaml
│   │   │   │   │           └── sra-inspector-org-main-ssm.yaml
│   │   │   │   ├── macie
│   │   │   │   │   └── macie_org
│   │   │   │   │       ├── README.md
│   │   │   │   │       ├── customizations_for_aws_control_tower
│   │   │   │   │       │   ├── README.md
│   │   │   │   │       │   ├── manifest.yaml
│   │   │   │   │       │   └── parameters
│   │   │   │   │       │       └── sra-macie-org-main-ssm.json
│   │   │   │   │       ├── documentation
│   │   │   │   │       │   ├── macie-org-terraform.png
│   │   │   │   │       │   ├── macie-org.png
│   │   │   │   │       │   └── macie-org.pptx
│   │   │   │   │       ├── lambda
│   │   │   │   │       │   └── src
│   │   │   │   │       │       ├── app.py
│   │   │   │   │       │       ├── common.py
│   │   │   │   │       │       ├── macie.py
│   │   │   │   │       │       └── requirements.txt
│   │   │   │   │       └── templates
│   │   │   │   │           ├── sra-macie-org-configuration-role.yaml
│   │   │   │   │           ├── sra-macie-org-configuration.yaml
│   │   │   │   │           ├── sra-macie-org-delivery-kms-key.yaml
│   │   │   │   │           ├── sra-macie-org-delivery-s3-bucket.yaml
│   │   │   │   │           ├── sra-macie-org-disable-role.yaml
│   │   │   │   │           └── sra-macie-org-main-ssm.yaml
│   │   │   │   ├── patch_mgmt
│   │   │   │   │   └── patch_mgmt_org
│   │   │   │   │       ├── README.md
│   │   │   │   │       ├── documentation
│   │   │   │   │       │   ├── missing-patch-summary.png
│   │   │   │   │       │   ├── node-compliance.png
│   │   │   │   │       │   ├── patch-mgr-deployment.png
│   │   │   │   │       │   └── patch-mgr-solution.png
│   │   │   │   │       ├── lambda
│   │   │   │   │       │   └── src
│   │   │   │   │       │       ├── app.py
│   │   │   │   │       │       ├── common.py
│   │   │   │   │       │       ├── patchmgmt.py
│   │   │   │   │       │       └── requirements.txt
│   │   │   │   │       ├── layer
│   │   │   │   │       │   └── boto3
│   │   │   │   │       │       └── package.txt
│   │   │   │   │       └── templates
│   │   │   │   │           ├── sra-patch_mgmt-configuration-role.yaml
│   │   │   │   │           ├── sra-patch_mgmt-configuration.yaml
│   │   │   │   │           ├── sra-patch_mgmt-default-host-config-role.yaml
│   │   │   │   │           ├── sra-patch_mgmt-org-global-events.yaml
│   │   │   │   │           └── sra-patch_mgmt-org-main-ssm.yaml
│   │   │   │   ├── s3
│   │   │   │   │   └── s3_block_account_public_access
│   │   │   │   │       ├── README.md
│   │   │   │   │       ├── customizations_for_aws_control_tower
│   │   │   │   │       │   ├── README.md
│   │   │   │   │       │   ├── manifest.yaml
│   │   │   │   │       │   └── parameters
│   │   │   │   │       │       └── sra-s3-block-account-public-access-main-ssm.json
│   │   │   │   │       ├── documentation
│   │   │   │   │       │   ├── s3-block-account-public-access.png
│   │   │   │   │       │   └── s3-block-account-public-access.pptx
│   │   │   │   │       ├── lambda
│   │   │   │   │       │   └── src
│   │   │   │   │       │       ├── app.py
│   │   │   │   │       │       └── requirements.txt
│   │   │   │   │       └── templates
│   │   │   │   │           ├── sra-s3-block-account-public-access-global-events.yaml
│   │   │   │   │           ├── sra-s3-block-account-public-access-main-ssm.yaml
│   │   │   │   │           ├── sra-s3-block-account-public-access-role.yaml
│   │   │   │   │           └── sra-s3-block-account-public-access.yaml
│   │   │   │   ├── securityhub
│   │   │   │   │   └── securityhub_org
│   │   │   │   │       ├── README.md
│   │   │   │   │       ├── customizations_for_aws_control_tower
│   │   │   │   │       │   ├── README.md
│   │   │   │   │       │   ├── manifest.yaml
│   │   │   │   │       │   └── parameters
│   │   │   │   │       │       └── sra-securityhub-org-main-ssm.json
│   │   │   │   │       ├── documentation
│   │   │   │   │       │   ├── securityhub-org-terraform.png
│   │   │   │   │       │   ├── securityhub-org.png
│   │   │   │   │       │   └── securityhub-org.pptx
│   │   │   │   │       ├── lambda
│   │   │   │   │       │   └── src
│   │   │   │   │       │       ├── app.py
│   │   │   │   │       │       ├── common.py
│   │   │   │   │       │       ├── requirements.txt
│   │   │   │   │       │       └── securityhub.py
│   │   │   │   │       └── templates
│   │   │   │   │           ├── sra-securityhub-org-configuration-role.yaml
│   │   │   │   │           ├── sra-securityhub-org-configuration.yaml
│   │   │   │   │           ├── sra-securityhub-org-global-events.yaml
│   │   │   │   │           ├── sra-securityhub-org-main-ssm.yaml
│   │   │   │   │           └── sra-securityhub-org-recorder-start-event.yaml
│   │   │   │   └── shield_advanced
│   │   │   │       └── shield_advanced
│   │   │   │           ├── README.md
│   │   │   │           ├── customizations_for_aws_control_tower
│   │   │   │           │   ├── README.md
│   │   │   │           │   ├── manifest.yaml
│   │   │   │           │   └── parameters
│   │   │   │           │       └── sra-shield-org-main-ssm.json
│   │   │   │           ├── documentation
│   │   │   │           │   ├── shield.png
│   │   │   │           │   └── shield.pptx
│   │   │   │           ├── lambda
│   │   │   │           │   └── src
│   │   │   │           │       ├── app.py
│   │   │   │           │       ├── common.py
│   │   │   │           │       ├── requirements.txt
│   │   │   │           │       └── shield.py
│   │   │   │           ├── layer
│   │   │   │           │   └── boto3
│   │   │   │           │       └── package.txt
│   │   │   │           └── templates
│   │   │   │               ├── sra-shield-advanced-configuration-role.yaml
│   │   │   │               ├── sra-shield-advanced-configuration.yaml
│   │   │   │               ├── sra-shield-advanced-global-events.yaml
│   │   │   │               └── sra-shield-advanced-main-ssm.yaml
│   │   │   ├── terraform
│   │   │   │   ├── README.md
│   │   │   │   ├── common
│   │   │   │   │   ├── README.md
│   │   │   │   │   ├── data.tf
│   │   │   │   │   ├── dynamodb
│   │   │   │   │   │   ├── README-TF.MD
│   │   │   │   │   │   ├── main.tf
│   │   │   │   │   │   ├── output.tf
│   │   │   │   │   │   └── variables.tf
│   │   │   │   │   ├── main.tf
│   │   │   │   │   ├── outputs.tf
│   │   │   │   │   ├── providers.tf
│   │   │   │   │   ├── s3
│   │   │   │   │   │   ├── README-TF.MD
│   │   │   │   │   │   ├── data.tf
│   │   │   │   │   │   ├── main.tf
│   │   │   │   │   │   ├── output.tf
│   │   │   │   │   │   └── variables.tf
│   │   │   │   │   ├── secrets_kms
│   │   │   │   │   │   ├── README-TF.MD
│   │   │   │   │   │   ├── data.tf
│   │   │   │   │   │   ├── main.tf
│   │   │   │   │   │   ├── output.tf
│   │   │   │   │   │   └── variables.tf
│   │   │   │   │   ├── sra_execution_role
│   │   │   │   │   │   ├── README-TF.MD
│   │   │   │   │   │   ├── data.tf
│   │   │   │   │   │   ├── execution-role.yaml
│   │   │   │   │   │   ├── main.tf
│   │   │   │   │   │   ├── providers.tf
│   │   │   │   │   │   └── variables.tf
│   │   │   │   │   ├── ssm_parameters
│   │   │   │   │   │   ├── README-TF.MD
│   │   │   │   │   │   ├── data.tf
│   │   │   │   │   │   ├── invoke.tf
│   │   │   │   │   │   ├── main.tf
│   │   │   │   │   │   ├── outputs.tf
│   │   │   │   │   │   └── variables.tf
│   │   │   │   │   └── variables.tf
│   │   │   │   └── solutions
│   │   │   │       ├── README.md
│   │   │   │       ├── cloudtrail_org
│   │   │   │       │   ├── README.md
│   │   │   │       │   ├── data.tf
│   │   │   │       │   ├── kms
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   ├── output.tf
│   │   │   │       │   │   ├── providers.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   ├── main.tf
│   │   │   │       │   ├── org
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── invoke.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   ├── providers.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   ├── providers.tf
│   │   │   │       │   ├── s3
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   ├── output.tf
│   │   │   │       │   │   ├── providers.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   └── variables.tf
│   │   │   │       ├── data.tf
│   │   │   │       ├── guard_duty
│   │   │   │       │   ├── README.md
│   │   │   │       │   ├── configuration_role
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   ├── output.tf
│   │   │   │       │   │   ├── providers.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   ├── data.tf
│   │   │   │       │   ├── delete_detector
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   ├── output.tf
│   │   │   │       │   │   ├── providers.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   ├── gd_configuration
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── invoke.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   ├── outputs.tf
│   │   │   │       │   │   ├── providers.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   ├── kms_key
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   ├── output.tf
│   │   │   │       │   │   ├── providers.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   ├── main.tf
│   │   │   │       │   ├── output.tf
│   │   │   │       │   ├── providers.tf
│   │   │   │       │   ├── s3
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   ├── outputs.tf
│   │   │   │       │   │   ├── providers.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   └── variables.tf
│   │   │   │       ├── iam_access_analyzer
│   │   │   │       │   ├── README.md
│   │   │   │       │   ├── account
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   ├── data.tf
│   │   │   │       │   ├── main.tf
│   │   │   │       │   ├── org
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   ├── providers.tf
│   │   │   │       │   └── variables.tf
│   │   │   │       ├── iam_password_policy
│   │   │   │       │   ├── README.md
│   │   │   │       │   ├── configuration
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── invoke.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   ├── providers.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   ├── data.tf
│   │   │   │       │   ├── main.tf
│   │   │   │       │   ├── providers.tf
│   │   │   │       │   └── variables.tf
│   │   │   │       ├── inspector
│   │   │   │       │   ├── README.md
│   │   │   │       │   ├── configuration
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── invoke.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   ├── providers.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   ├── configuration_role
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   ├── providers.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   ├── data.tf
│   │   │   │       │   ├── main.tf
│   │   │   │       │   ├── providers.tf
│   │   │   │       │   └── variables.tf
│   │   │   │       ├── macie
│   │   │   │       │   ├── README.md
│   │   │   │       │   ├── configuration
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── invoke.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   ├── providers.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   ├── configuration_role
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   ├── providers.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   ├── data.tf
│   │   │   │       │   ├── delivery_kms_key
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   ├── output.tf
│   │   │   │       │   │   ├── providers.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   ├── delivery_s3_bucket
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   ├── output.tf
│   │   │   │       │   │   ├── providers.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   ├── disable_role
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   ├── providers.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   ├── main.tf
│   │   │   │       │   ├── providers.tf
│   │   │   │       │   └── variables.tf
│   │   │   │       ├── main.tf
│   │   │   │       ├── output.tf
│   │   │   │       ├── providers.tf
│   │   │   │       ├── register_delegated_administrator
│   │   │   │       │   ├── README.md
│   │   │   │       │   ├── data.tf
│   │   │   │       │   ├── main.tf
│   │   │   │       │   ├── providers.tf
│   │   │   │       │   ├── register_admin
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── invoke.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   ├── output.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   └── variables.tf
│   │   │   │       ├── security_hub
│   │   │   │       │   ├── README.md
│   │   │   │       │   ├── configuration
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── invoke.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   ├── providers.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   ├── configuration_role
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   ├── output.tf
│   │   │   │       │   │   ├── providers.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   ├── data.tf
│   │   │   │       │   ├── main.tf
│   │   │   │       │   ├── output.tf
│   │   │   │       │   ├── providers.tf
│   │   │   │       │   ├── recorder_start_event
│   │   │   │       │   │   ├── data.tf
│   │   │   │       │   │   ├── main.tf
│   │   │   │       │   │   ├── providers.tf
│   │   │   │       │   │   └── variables.tf
│   │   │   │       │   └── variables.tf
│   │   │   │       ├── terraform_stack.py
│   │   │   │       └── variables.tf
│   │   │   └── utils
│   │   │       ├── __init__.py
│   │   │       └── packaging_scripts
│   │   │           ├── README.md
│   │   │           ├── sra-prepare-layer-code.py
│   │   │           └── stage_solution.sh
│   │   ├── my-readme.md
│   │   └── pyproject.toml
│   └── iam
│       ├── [TBC] how-and-when-to-use-aws-iam-policy-blog-samples
│       │   ├── CODE_OF_CONDUCT.md
│       │   ├── CONTRIBUTING.md
│       │   ├── Future projects.md
│       │   ├── IAM policy types_ How and when to use them _ AWS Security Blog.pdf
│       │   ├── LICENSE
│       │   ├── README.md
│       │   ├── architecture.png
│       │   ├── cloudformation-templates
│       │   │   ├── deploy-templates.sh
│       │   │   ├── pipeline.yml
│       │   │   └── sample-application.yml
│       │   ├── my-readme.md
│       │   ├── notes
│       │   │   └── IAM policy types: How and when to use them.md
│       │   └── policies
│       │       ├── application-role-identity-policy.json
│       │       ├── cicd-pipeline-role-identity-policy.json
│       │       ├── developer-role-identity-policy.json
│       │       ├── permissions-boundary.json
│       │       ├── resource-policy.json
│       │       └── service-control-policy.json
│       ├── amazon-redshift-assume-role-sample
│       │   ├── CODE_OF_CONDUCT.md
│       │   ├── CONTRIBUTING.md
│       │   ├── LICENSE
│       │   ├── README.md
│       │   ├── Secure data movement across Amazon S3 and Amazon Redshift using role chaining and ASSUMEROLE _ AWS Big Data Blog.pdf
│       │   ├── my-readme.md
│       │   ├── redshift-onboarding-role.cf.yaml
│       │   └── redshift-tenant-resources.cf.yaml
│       ├── aws-iam-access-analyzer-policy-validation-config-rule
│       │   ├── CODE_OF_CONDUCT.md
│       │   ├── CONTRIBUTING.md
│       │   ├── LICENSE
│       │   ├── README.md
│       │   ├── Validate IAM policies with Access Analyzer using AWS Config rules _ AWS Security Blog.pdf
│       │   ├── function
│       │   │   ├── __init__.py
│       │   │   ├── base_config_handler.py
│       │   │   ├── filters
│       │   │   │   ├── exceptions-file-schema.json
│       │   │   │   ├── ignore_finding_types.py
│       │   │   │   ├── ignore_findings_with_filter.py
│       │   │   │   └── validation_filters.py
│       │   │   ├── handler.py
│       │   │   ├── iam_group.py
│       │   │   ├── iam_policy.py
│       │   │   ├── iam_role.py
│       │   │   ├── iam_user.py
│       │   │   ├── kms_key.py
│       │   │   ├── logger.py
│       │   │   ├── logs_destination.py
│       │   │   ├── requirements.txt
│       │   │   ├── s3_access_point.py
│       │   │   ├── s3_bucket.py
│       │   │   ├── sample-events
│       │   │   │   ├── create-iam-group.json
│       │   │   │   ├── create-iam-policy.json
│       │   │   │   ├── create-iam-role.json
│       │   │   │   ├── create-iam-user.json
│       │   │   │   ├── create-kms-key.json
│       │   │   │   ├── create-logs-destination.json
│       │   │   │   ├── create-s3-ap.json
│       │   │   │   ├── create-s3-bucket.json
│       │   │   │   ├── create-secretsmanager-secret.json
│       │   │   │   ├── create-sns-topic.json
│       │   │   │   ├── create-sqs-queue.json
│       │   │   │   ├── delete-iam-group.json
│       │   │   │   ├── delete-iam-policy.json
│       │   │   │   ├── delete-iam-role.json
│       │   │   │   ├── delete-iam-user.json
│       │   │   │   ├── delete-kms-key.json
│       │   │   │   ├── delete-logs-destination.json
│       │   │   │   ├── delete-s3-ap.json
│       │   │   │   ├── delete-s3-bucket.json
│       │   │   │   ├── delete-secretsmanager-secret.json
│       │   │   │   ├── delete-sns-topic.json
│       │   │   │   ├── delete-sqs-queue.json
│       │   │   │   ├── history-iam-group.json
│       │   │   │   ├── history-iam-policy.json
│       │   │   │   ├── history-iam-role.json
│       │   │   │   ├── history-iam-user.json
│       │   │   │   ├── history-kms-key.json
│       │   │   │   ├── history-logs-destination.json
│       │   │   │   ├── history-s3-ap.json
│       │   │   │   ├── history-s3-bucket.json
│       │   │   │   ├── history-secretsmanager-secret.json
│       │   │   │   ├── history-sns-topic.json
│       │   │   │   ├── history-sqs-queue.json
│       │   │   │   ├── normalize-json.sh
│       │   │   │   ├── oversized.json
│       │   │   │   ├── periodic.json
│       │   │   │   ├── some-fake-type.json
│       │   │   │   ├── update-iam-group.json
│       │   │   │   ├── update-iam-policy.json
│       │   │   │   ├── update-iam-role.json
│       │   │   │   ├── update-iam-user.json
│       │   │   │   ├── update-kms-key.json
│       │   │   │   ├── update-logs-destination.json
│       │   │   │   ├── update-s3-ap.json
│       │   │   │   ├── update-s3-bucket.json
│       │   │   │   ├── update-secretsmanager-secret.json
│       │   │   │   ├── update-sns-topic.json
│       │   │   │   └── update-sqs-queue.json
│       │   │   ├── secretsmanager_secret.py
│       │   │   ├── sns_topic.py
│       │   │   ├── sqs_queue.py
│       │   │   └── tests
│       │   │       ├── __init__.py
│       │   │       ├── test_filters.py
│       │   │       ├── test_handler.py
│       │   │       ├── test_iam_group.py
│       │   │       ├── test_iam_policy.py
│       │   │       ├── test_iam_role.py
│       │   │       ├── test_iam_user.py
│       │   │       ├── test_kms_key.py
│       │   │       ├── test_logs_destination.py
│       │   │       ├── test_non_compliant_reason.py
│       │   │       ├── test_s3_access_point.py
│       │   │       ├── test_s3_bucket.py
│       │   │       ├── test_secretsmanager_secret.py
│       │   │       ├── test_sns_topic.py
│       │   │       ├── test_sqs_queue.py
│       │   │       ├── test_validation_exception_schema.py
│       │   │       └── utils.py
│       │   ├── my-readme.md
│       │   └── templates
│       │       ├── exceptions-example.json
│       │       └── template.yaml
│       └── organizations
│           ├── project-1
│           │   ├── AWSOrganizations_AutomateEndtoEndAccountCreation_ScriptandTemplates.zip
│           │   ├── How to Use AWS Organizations to Automate End-to-End Account Creation _ AWS Security Blog.pdf
│           │   └── my-readme.md
│           └── workshop-aws-org-scp
│               ├── Makefile
│               ├── README.md
│               ├── diagrams
│               │   ├── architecture.png
│               │   ├── step-1.png
│               │   ├── step-2-1.png
│               │   ├── step-2-2.png
│               │   ├── step-3-1.png
│               │   ├── step-3-2.png
│               │   └── step-4.png
│               ├── instructions
│               │   ├── 01-Organizations.md
│               │   ├── 02.1-StackSets-Admin.md
│               │   ├── 02.2-StackSets-Execution.md
│               │   ├── 03.1-IAM-Roles.md
│               │   ├── 03.2-Permissions-Boundaries.md
│               │   └── 04-SCP.md
│               ├── my-readme.md
│               └── templates
│                   ├── restricted-admin-role.cf.yaml
│                   ├── stackset-admin-roles.cf.yaml
│                   └── stackset-execution-role.cf.yaml
├── serverless
│   └── elastic-serverless-forwarder
│       ├── CHANGELOG.md
│       ├── CONTRIBUTING.md
│       ├── LICENSE.txt
│       ├── Makefile
│       ├── README.md
│       ├── dev-corner
│       │   ├── README.md
│       │   └── how-to-test-locally
│       │       ├── README.md
│       │       └── Taskfile.yaml
│       ├── docs
│       │   ├── README-AWS.md
│       │   ├── en
│       │   │   ├── aws-deploy-elastic-serverless-forwarder.asciidoc
│       │   │   ├── aws-elastic-serverless-forwarder-configuration.asciidoc
│       │   │   ├── aws-elastic-serverless-forwarder.asciidoc
│       │   │   ├── aws-serverless-troubleshooting.asciidoc
│       │   │   └── index.asciidoc
│       │   ├── false-after-multi.png
│       │   ├── false-before-multi.png
│       │   ├── lambda-create-function.png
│       │   ├── lambda-flow.png
│       │   ├── multiline-regexp-test-repl-main.png
│       │   ├── multiline-regexp-test-repl-run.png
│       │   ├── true-after-multi.png
│       │   └── true-before-multi.png
│       ├── handlers
│       │   ├── __init__.py
│       │   └── aws
│       │       ├── __init__.py
│       │       ├── cloudwatch_logs_trigger.py
│       │       ├── exceptions.py
│       │       ├── handler.py
│       │       ├── kinesis_trigger.py
│       │       ├── replay_trigger.py
│       │       ├── s3_sqs_trigger.py
│       │       ├── sqs_trigger.py
│       │       └── utils.py
│       ├── main_aws.py
│       ├── my-readme.md
│       ├── mypy.ini
│       ├── publish_lambda.sh
│       ├── pyproject.toml
│       ├── pytest.ini
│       ├── requirements-lint.txt
│       ├── requirements-tests.txt
│       ├── requirements.txt
│       ├── share
│       │   ├── __init__.py
│       │   ├── config.py
│       │   ├── environment.py
│       │   ├── events.py
│       │   ├── expand_event_list_from_field.py
│       │   ├── factory.py
│       │   ├── include_exlude.py
│       │   ├── json.py
│       │   ├── logger.py
│       │   ├── multiline.py
│       │   ├── secretsmanager.py
│       │   ├── utils.py
│       │   └── version.py
│       ├── shippers
│       │   ├── __init__.py
│       │   ├── composite.py
│       │   ├── es.py
│       │   ├── factory.py
│       │   ├── logstash.py
│       │   └── shipper.py
│       ├── storage
│       │   ├── __init__.py
│       │   ├── decorator.py
│       │   ├── factory.py
│       │   ├── payload.py
│       │   ├── s3.py
│       │   └── storage.py
│       └── tests
│           ├── Dockerfile
│           ├── __init__.py
│           ├── entrypoint.sh
│           ├── handlers
│           │   ├── __init__.py
│           │   └── aws
│           │       ├── __init__.py
│           │       ├── test_handler.py
│           │       ├── test_integrations.py
│           │       ├── test_replay_trigger.py
│           │       ├── test_utils.py
│           │       ├── testdata
│           │       │   ├── cloudwatch-log-1.json
│           │       │   ├── cloudwatch-log-2.json
│           │       │   └── cloudwatch-log-3.json
│           │       └── utils.py
│           ├── scripts
│           │   ├── black.sh
│           │   ├── docker
│           │   │   ├── black.sh
│           │   │   ├── flake8.sh
│           │   │   ├── isort.sh
│           │   │   ├── mypy.sh
│           │   │   └── run_tests.sh
│           │   ├── flake8.sh
│           │   ├── isort.sh
│           │   ├── license_headers_check.sh
│           │   ├── mypy.sh
│           │   └── run_tests.sh
│           ├── share
│           │   ├── __init__.py
│           │   ├── test_config.py
│           │   ├── test_environment.py
│           │   ├── test_factory.py
│           │   ├── test_include_exclude.py
│           │   ├── test_json.py
│           │   ├── test_multiline.py
│           │   └── test_secretsmanager.py
│           ├── shippers
│           │   ├── __init__.py
│           │   ├── ssl
│           │   │   ├── localhost.crt
│           │   │   └── localhost.pkcs8.key
│           │   ├── test_composite.py
│           │   ├── test_es.py
│           │   ├── test_factory.py
│           │   └── test_logstash.py
│           ├── storage
│           │   ├── __init__.py
│           │   ├── test_benchmark.py
│           │   ├── test_decorator.py
│           │   ├── test_factory.py
│           │   ├── test_payload.py
│           │   └── test_s3.py
│           └── testcontainers
│               ├── es.py
│               └── logstash.py
├── sns
│   └── sns-dynamic-subscribers
│       ├── Building dynamic Amazon SNS subscriptions for auto scaling container workloads  _ AWS Compute Blog.pdf
│       ├── CODE_OF_CONDUCT.md
│       ├── CONTRIBUTING.md
│       ├── Dockerfile
│       ├── LICENSE
│       ├── README.md
│       ├── my-readme.md
│       ├── pom.xml
│       ├── samconfig.toml
│       ├── sns-sqs-cleanup-service
│       │   ├── app.py
│       │   └── requirements.txt
│       ├── sns-sqs-subscription-service
│       │   ├── app.py
│       │   └── requirements.txt
│       ├── sqs-dynamic-subscribers.iml
│       ├── src
│       │   └── main
│       │       └── java
│       │           └── com
│       │               └── amazonaws
│       │                   └── samples
│       │                       ├── Bootstrap.java
│       │                       ├── JSONUtil.java
│       │                       └── SQSContainerMapping.java
│       └── template.yaml
├── tools
│   └── aws-support-tools
│       ├── APIGateway
│       │   ├── README.md
│       │   └── Tools
│       │       ├── README.md
│       │       ├── curl_for_latency
│       │       │   ├── README.md
│       │       │   └── curl_for_latency.sh
│       │       └── vpc_link_lister
│       │           ├── README.md
│       │           └── vpc_link_lister.py
│       ├── CloudFormation
│       │   ├── CloudFormer
│       │   │   ├── CloudFormer-Existing-VPC.json
│       │   │   └── README.md
│       │   └── README.md
│       ├── CloudFront
│       │   └── README.md
│       ├── CloudSearch
│       │   └── README.md
│       ├── Cognito
│       │   ├── README.md
│       │   ├── decode-verify-jwt
│       │   │   ├── README.md
│       │   │   ├── decode-verify-jwt.py
│       │   │   └── decode-verify-jwt.ts
│       │   └── identity-pool-integrator-onelogin
│       │       ├── LICENSE
│       │       ├── README.md
│       │       └── identitypool_integrator_onelogin.py
│       ├── Connect
│       │   └── DynamicOutboundCallerID
│       │       ├── README.md
│       │       └── lambda_function.py
│       ├── DRS
│       │   └── DRS-Settings-Tool
│       │       ├── README.md
│       │       ├── classes
│       │       │   ├── basic_launch_settings.py
│       │       │   ├── launch_template_settings.py
│       │       │   ├── replication_settings.py
│       │       │   └── source_server_info.py
│       │       ├── get_settings.py
│       │       ├── object_builders
│       │       │   ├── drs_launch_settings_obj_builder.py
│       │       │   ├── launch_template_obj_builder.py
│       │       │   ├── replication_settings_obj_builder.py
│       │       │   └── source_server_info_obj_builder.py
│       │       ├── update_settings.py
│       │       └── utils
│       │           ├── clients.py
│       │           ├── delete_none.py
│       │           ├── logger.py
│       │           ├── obj_to_dict.py
│       │           ├── settings_validator.py
│       │           └── str2bool.py
│       ├── DataPipeline
│       │   ├── MySqlRdsToPostgreSqlRds
│       │   │   ├── Readme.md
│       │   │   ├── dbconv-mysqlRDS-to-postgresqlRDS.sh
│       │   │   └── mysqlRDS-psqlRDS-copy-using-shell-definition.json
│       │   ├── PostgresqlRdsToRedshift
│       │   │   ├── Readme.md
│       │   │   ├── dbconv_psqlRDStoRedshift.sh
│       │   │   └── postgresqlRDS-to-Redshift-definition.json
│       │   ├── Readme.md
│       │   └── WriteToAnySchemaInRedshiftFromRds
│       │       ├── RDStoRedshift-AnySchema.json
│       │       ├── Readme.md
│       │       ├── dbconv_tablename_noquotes.sh
│       │       └── mysql_to_redshift_tablename_noquotes.py
│       ├── DeviceFarm
│       │   ├── README.md
│       │   └── TestOutputDownloader
│       │       ├── LICENSE.txt
│       │       ├── NOTICE.txt
│       │       ├── README.md
│       │       └── test-output-downloader.sh
│       ├── EBS
│       │   └── VolumeLimitCalculator
│       │       ├── Readme.md
│       │       └── volume_Limit_calculator.sh
│       ├── EC2
│       │   ├── AutomateDnsmasq
│       │   │   ├── AutomateDnsmasq.cloudinit
│       │   │   ├── AutomateDnsmasq.sh
│       │   │   └── README.md
│       │   ├── EC2EBSThroughput
│       │   │   ├── Readme.md
│       │   │   └── ebs-stats.sh
│       │   ├── EnableIPv6
│       │   │   ├── README.md
│       │   │   └── enableIPv6.sh
│       │   ├── NitroInstanceChecks
│       │   │   ├── Readme.md
│       │   │   └── nitro_check_script.sh
│       │   ├── QuickInstanceEC2
│       │   │   ├── DeployScripts
│       │   │   │   ├── README.md
│       │   │   │   └── tomcat7_ubuntu14.04_install.sh
│       │   │   ├── README.md
│       │   │   └── qi.py
│       │   └── README.md
│       ├── EC2 Auto Scaling
│       │   ├── Multiple ENI Auto Scaling group
│       │   │   ├── MultipleENIforASG.py
│       │   │   └── README.md
│       │   └── README.md
│       ├── EMR
│       │   ├── Assign_Private_IP
│       │   │   ├── README.md
│       │   │   ├── assign_private_ip.py
│       │   │   └── assign_private_ip_v2.py
│       │   ├── Get_EMR_CLI_Export
│       │   │   ├── README.md
│       │   │   ├── aws-cli-export.png
│       │   │   └── get_emr_cli_export.py
│       │   └── README.md
│       ├── ElasticTranscoder
│       │   └── README.md
│       ├── Elasticsearch Service
│       │   ├── DeleteIndices
│       │   │   ├── LICENSE
│       │   │   ├── README.md
│       │   │   └── processOldESIndicesForDeletion.py
│       │   └── README.md
│       ├── Find and delete elastic network interfaces created by Lambda _ AWS re_Post.pdf
│       ├── Glacier
│       │   └── README.md
│       ├── IoT
│       │   └── README.md
│       ├── LICENSE
│       ├── Lambda
│       │   ├── BuildLambdaLayers
│       │   │   ├── LICENSE.txt
│       │   │   ├── NOTICE
│       │   │   ├── README.md
│       │   │   ├── nodejs
│       │   │   │   ├── LICENSE.txt
│       │   │   │   ├── README.md
│       │   │   │   ├── buildlayer.sh
│       │   │   │   └── requirements.txt
│       │   │   └── python
│       │   │       ├── LICENSE.txt
│       │   │       ├── README.md
│       │   │       ├── buildlayer.sh
│       │   │       └── requirements.txt
│       │   ├── CheckFunctionConcurrency
│       │   │   ├── CheckFunctionConcurrency.py
│       │   │   └── README.md
│       │   ├── DeploymentPackages
│       │   │   ├── README.md
│       │   │   ├── global-lambda-amis.sh
│       │   │   └── lambda-user-data.txt
│       │   ├── FindEniMappings
│       │   │   ├── LICENCE
│       │   │   ├── README.md
│       │   │   └── findEniAssociations
│       │   ├── Functions
│       │   │   ├── AutoShutEC2
│       │   │   │   ├── README.md
│       │   │   │   └── auto_shut_ec2.py
│       │   │   ├── README.md
│       │   │   ├── TagEC2Dependencies
│       │   │   │   ├── README.md
│       │   │   │   └── tag_ec2_dependencies.py
│       │   │   ├── echo
│       │   │   │   ├── README.md
│       │   │   │   └── lambda_function.py
│       │   │   └── echo-api-lambda-proxy
│       │   │       ├── README.md
│       │   │       └── lambda_function.py
│       │   └── README.md
│       ├── MGN
│       │   └── Windows
│       │       ├── MGN-Toolkit.psd1
│       │       ├── MGN-Toolkit.psm1
│       │       ├── Private
│       │       │   ├── Get-IEProxySetting.ps1
│       │       │   ├── Get-LocalSystemAccountEnvironmentVariablesProxy.ps1
│       │       │   ├── Get-SystemWideEnvironmentVariablesProxy.ps1
│       │       │   ├── Get-SystemWideProxy.ps1
│       │       │   ├── Set-PSObjectResponse.ps1
│       │       │   ├── Set-ProxyOutput.ps1
│       │       │   ├── Test-RegistryValue.ps1
│       │       │   ├── Test-TcpConnection.ps1
│       │       │   └── Write-Log.ps1
│       │       ├── Public
│       │       │   ├── Get-AntivirusEnabled.ps1
│       │       │   ├── Get-Authenticationmethod.ps1
│       │       │   ├── Get-Bandwidth.ps1
│       │       │   ├── Get-BitLockerStatus.ps1
│       │       │   ├── Get-BootMode.ps1
│       │       │   ├── Get-DiskActivity.ps1
│       │       │   ├── Get-DomainControllerStatus.ps1
│       │       │   ├── Get-DotNETFramework.ps1
│       │       │   ├── Get-FreeRAM.ps1
│       │       │   ├── Get-ProxySetting.ps1
│       │       │   ├── Get-RootDiskSpace.ps1
│       │       │   ├── Get-SCandNET.ps1
│       │       │   ├── Get-TrustedRootCertificate.ps1
│       │       │   ├── Get-WMIServiceStatus.ps1
│       │       │   ├── Invoke-MGNToolkit.ps1
│       │       │   └── Test-EndpointsNetworkAccess.ps1
│       │       ├── README.md
│       │       └── Tests
│       │           ├── Get-IEProxySettings.tests.ps1
│       │           ├── Get-LocalSystemAccountEnvironmentVariablesProxyKey.tests.ps1
│       │           ├── Get-SystemWideEnvironmentVariablesProxy.tests.ps1
│       │           ├── Get-SystemWideProxy.tests.ps1
│       │           └── Test-EndpointsNetworkAccess.tests.ps1
│       ├── MWAA
│       │   ├── LICENSE
│       │   ├── readme.md
│       │   ├── tests
│       │   │   ├── __init__.py
│       │   │   └── test_verify_env.py
│       │   └── verify_env
│       │       ├── __init__.py
│       │       └── verify_env.py
│       ├── MobileAnalytics
│       │   └── README.md
│       ├── MobileHub
│       │   └── README.md
│       ├── OpsWorks
│       │   └── sample-cookbooks
│       │       └── cpulimit-ruby
│       │           ├── README.md
│       │           ├── attributes
│       │           │   └── default.rb
│       │           ├── chefignore
│       │           ├── files
│       │           │   └── default
│       │           │       └── cpulimit-master
│       │           │           ├── Makefile
│       │           │           ├── src
│       │           │           │   ├── Makefile
│       │           │           │   ├── cpulimit.c
│       │           │           │   ├── list.c
│       │           │           │   ├── list.h
│       │           │           │   ├── memrchr.c
│       │           │           │   ├── process_group.c
│       │           │           │   ├── process_group.h
│       │           │           │   ├── process_iterator.c
│       │           │           │   ├── process_iterator.h
│       │           │           │   ├── process_iterator_apple.c
│       │           │           │   ├── process_iterator_freebsd.c
│       │           │           │   └── process_iterator_linux.c
│       │           │           └── tests
│       │           │               ├── Makefile
│       │           │               ├── busy.c
│       │           │               ├── process_iterator_test.c
│       │           │               └── run_tests.sh
│       │           ├── metadata.rb
│       │           ├── recipes
│       │           │   └── default.rb
│       │           └── templates
│       │               └── default
│       │                   ├── cpulimit.erb
│       │                   └── cpulimit.sh.erb
│       ├── README.md
│       ├── S3
│       │   ├── README.md
│       │   └── S3_Transfer_Acceleration
│       │       ├── Bash-script
│       │       │   ├── README.md
│       │       │   └── test-upload.sh
│       │       └── README.md
│       ├── SES
│       │   ├── CheckSESQuota
│       │   │   ├── README.md
│       │   │   └── check_ses_quota.py
│       │   ├── README.md
│       │   ├── SESMailer
│       │   │   ├── README.md
│       │   │   └── ses_mailer.py
│       │   └── SESReports
│       │       ├── LICENSE
│       │       ├── README.md
│       │       ├── example
│       │       │   └── Jul-04-2018-15-42-10.html
│       │       ├── lambda-dep-package
│       │       │   ├── index.js
│       │       │   ├── node_modules
│       │       │   │   └── https
│       │       │   │       └── package.json
│       │       │   └── template
│       │       │       ├── begin_new.html
│       │       │       └── end_new.html
│       │       ├── ses-reports.yaml
│       │       └── sesreport.zip
│       ├── SNS
│       │   ├── APNSSampleApp
│       │   │   └── README.md
│       │   ├── FCMSampleApp
│       │   │   └── README.md
│       │   ├── GCMSampleApp
│       │   │   └── README.md
│       │   ├── README.md
│       │   ├── SNSFastPublish
│       │   │   ├── README.md
│       │   │   └── sns_fast_publish.py
│       │   └── SNSReflect
│       │       ├── README.md
│       │       ├── sns_event.json
│       │       └── sns_reflect.py
│       ├── SQS
│       │   └── README.md
│       ├── Snowball
│       │   └── README.md
│       ├── StorageGateway
│       │   └── README.md
│       ├── Systems Manager
│       │   ├── SSMAGENT-TOOLKIT-LINUX
│       │   │   ├── LICENSE
│       │   │   ├── NOTICE
│       │   │   ├── Readme.md
│       │   │   └── ssmagent-toolkit-Linux.sh
│       │   └── SSMAgent-Toolkit-Windows
│       │       ├── LICENSE
│       │       ├── NOTICE
│       │       ├── README.md
│       │       ├── SSMAgent-Toolkit
│       │       │   ├── Private
│       │       │   │   ├── Get-AppVersionNumber.ps1
│       │       │   │   ├── Invoke-CustomHTTPRequest.ps1
│       │       │   │   ├── New-PSObjectResponse.ps1
│       │       │   │   ├── New-ProxyOutput.ps1
│       │       │   │   ├── Test-RegistryValue.ps1
│       │       │   │   └── Write-Log.ps1
│       │       │   ├── Public
│       │       │   │   ├── Get-AgentProxySettings.ps1
│       │       │   │   ├── Get-IEProxySettings.ps1
│       │       │   │   ├── Get-InstanceID.ps1
│       │       │   │   ├── Get-LocalSystemAccountEnvironmentVariablesProxy.ps1
│       │       │   │   ├── Get-LocalSystemSTSCallerIdentity.ps1
│       │       │   │   ├── Get-MetadataAccess.ps1
│       │       │   │   ├── Get-Region.ps1
│       │       │   │   ├── Get-SSMAgentVersion.ps1
│       │       │   │   ├── Get-ServiceAccount.ps1
│       │       │   │   ├── Get-ServiceAvailability.ps1
│       │       │   │   ├── Get-ServiceStartupMode.ps1
│       │       │   │   ├── Get-ServiceStatus.ps1
│       │       │   │   ├── Get-SessionManagerPluginVersion.ps1
│       │       │   │   ├── Get-SystemWideEnvironmentVariablesProxy.ps1
│       │       │   │   ├── Get-SystemWideProxy.ps1
│       │       │   │   ├── Get-WindowsImageState.ps1
│       │       │   │   ├── Invoke-SSMChecks.ps1
│       │       │   │   ├── New-MetadataToken.ps1
│       │       │   │   ├── Test-EndpointsNetworkAccess.ps1
│       │       │   │   ├── Test-HybridRegistration.ps1
│       │       │   │   ├── Test-IAMInstanceProfile.ps1
│       │       │   │   └── Test-IAMInstanceProfileCredentialLastUpdate.ps1
│       │       │   ├── README.md
│       │       │   ├── SSMAgent-Toolkit.psd1
│       │       │   ├── SSMAgent-Toolkit.psm1
│       │       │   └── Tests
│       │       │       ├── Get-AgentProxySettings.tests.ps1
│       │       │       ├── Get-IEProxySettings.tests.ps1
│       │       │       ├── Get-InstanceID.tests.ps1
│       │       │       ├── Get-LocalSystemAccountEnvironmentVariablesProxyKey.tests.ps1
│       │       │       ├── Get-MetadataAccess.tests.ps1
│       │       │       ├── Get-Region.tests.ps1
│       │       │       ├── Get-SSMAgentVersion.tests.ps1
│       │       │       ├── Get-ServiceAccount.tests.ps1
│       │       │       ├── Get-ServiceAvailability.tests.ps1
│       │       │       ├── Get-ServiceStartupMode.tests.ps1
│       │       │       ├── Get-ServiceStatus.tests.ps1
│       │       │       ├── Get-SessionManagerPluginVersion.tests.ps1
│       │       │       ├── Get-SystemWideEnvironmentVariablesProxy.tests.ps1
│       │       │       ├── Get-SystemWideProxy.tests.ps1
│       │       │       ├── Get-WindowsImageState.tests.ps1
│       │       │       ├── New-MetadataToken.tests.ps1
│       │       │       ├── Output.txt
│       │       │       ├── README.md
│       │       │       ├── Test-EndpointsNetworkAccess.tests.ps1
│       │       │       ├── Test-HybridRegistration.tests.ps1
│       │       │       ├── Test-IAMInstanceProfile.tests.ps1
│       │       │       └── Test-IAMInstanceProfileCredentialLastUpdate.tests.ps1
│       │       ├── SSMAgent-Toolkit.zip
│       │       ├── SSMAgent-Toolkit_EC2Output.png
│       │       ├── SSMAgent-Toolkit_Flowchart.png
│       │       └── SSMAgent-Toolkit_HybridOutput.png
│       ├── WAF
│       │   └── WAF-Enhanced-Replicator
│       │       ├── README.md
│       │       ├── requirements.txt
│       │       ├── wafer
│       │       ├── waffun.py
│       │       └── wafget.py
│       └── my-readme.md
├── vpc-endpoints
│   └── accessing-aws-services-using-private-links-VISIT
│       ├── Streamline access to most used AWS services using VPC Endpoints _ Networking & Content Delivery.pdf
│       ├── aws-vpc-endpoints-existing-vpc.yaml
│       ├── aws-vpc-endpoints-new-vpc.yaml
│       ├── my-readme.md
│       └── vpc-flow-logs.yaml
└── vpc-vpc-with-interface-endpoints
    ├── my-readme.md
    ├── private-link-for-vpc-vpc.yaml
    └── privatelink-cfn.json

1496 directories, 4871 files
