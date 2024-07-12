# [Amazon DocumentDB (with MongoDB compatibility) read autoscaling by Randy DeFauw](https://aws.amazon.com/blogs/database/amazon-documentdb-with-mongodb-compatibility-read-autoscaling/)

code: https://github.com/aws-samples/amazon-documentdb-integration-with-application-autoscaler

# My Summary

1. Code demonstrates how to use Application Auto Scaling to automatically add or remove read replicas based on cluster load

# Architecture

<img src="./images/1.png" title="1.png" width="900"/>

# Demo

1. Create bucket

```javascript {.line-numbers}
# aws s3 mb s3://<bucket>
aws s3 mb s3://asg-docdb-05092023
```

2. enable versioning for the bucket in the console

3. Run a script to create deployment packages for our Lambda functions:

```javascript {.line-numbers}
# ./scripts/zip-lambda.sh <bucket>
./scripts/zip-lambda.sh asg-docdb-05092023
```

4. Create infra

```javascript {.line-numbers}
# ./scripts/create.sh <bucket> <template prefix> <database password> <stack name> <region>
./scripts/create.sh asg-docdb-05092023 cfn PrimaryPassword docdbautoscale us-east-1
```

5. Note down following by running `aws cloudformation --region us-east-1 describe-stacks --stack-name docdbautoscale --output table`:

**DBClusterIdentifier**: dbcluster-hvloe3jp7vqk
**DashboardName**: DocumentDB-Metrics
**DbEndpoint**: dbcluster-hvloe3jp7vqk.cluster-cxifhekmt7b5.us-east-1.docdb.amazonaws.com
**DbUser**: PrimaryUser
**JumpHost**: i-0e43431f877e7b0a0
**VpcId**: vpc-03d2a0388ddfccf35
**ApiEndpoint**: 7jsrxwc5i1

**Complete output:**

```javascript {.line-numbers}
PRASHANTHs-MBP:amazon-documentdb-integration-with-application-autoscaler pjoisha$ aws cloudformation --region us-east-1 describe-stacks --stack-name docdbautoscale --output table
----------------------------------------------------------------------------------------------------------------------------------------------
|                                                               DescribeStacks                                                               |
+--------------------------------------------------------------------------------------------------------------------------------------------+
||                                                                  Stacks                                                                  ||
|+-----------------------------+------------------------------------------------------------------------------------------------------------+|
||  CreationTime               |  2023-05-11T10:53:23.839000+00:00                                                                          ||
||  Description                |  This template builds a DocumentDB cluster in a VPC.
                                                      ||
||  DisableRollback            |  False                                                                                                     ||
||  EnableTerminationProtection|  False                                                                                                     ||
||  StackId                    |  arn:aws:cloudformation:us-east-1:113534181560:stack/docdbautoscale/0ce25a60-efea-11ed-9afa-12c2c0e8153f   ||
||  StackName                  |  docdbautoscale                                                                                            ||
||  StackStatus                |  CREATE_COMPLETE                                                                                           ||
|+-----------------------------+------------------------------------------------------------------------------------------------------------+|
|||                                                              Capabilities                                                              |||
||+----------------------------------------------------------------------------------------------------------------------------------------+||
|||  CAPABILITY_IAM                                                                                                                        |||
|||  CAPABILITY_NAMED_IAM                                                                                                                  |||
||+----------------------------------------------------------------------------------------------------------------------------------------+||
|||                                                            DriftInformation                                                            |||
||+-----------------------------------------------------------------------------+----------------------------------------------------------+||
|||  StackDriftStatus                                                           |  NOT_CHECKED                                             |||
||+-----------------------------------------------------------------------------+----------------------------------------------------------+||
|||                                                                 Outputs                                                                |||
||+-------------------------------+-----------------------+--------------------------------------------------------------------------------+||
|||          Description          |       OutputKey       |                                  OutputValue                                   |||
||+-------------------------------+-----------------------+--------------------------------------------------------------------------------+||
|||  DocumentDB metrics dashboard |  DashboardName        |  DocumentDB-Metrics                                                            |||
|||  VPC ID                       |  VpcId                |  vpc-03d2a0388ddfccf35                                                         |||
|||  DocumentDB endpoint          |  DbEndpoint           |  dbcluster-hvloe3jp7vqk.cluster-cxifhekmt7b5.us-east-1.docdb.amazonaws.com     |||
|||  DocumentDB cluster ID        |  DBClusterIdentifier  |  dbcluster-hvloe3jp7vqk                                                        |||
|||  Instance ID of jump host     |  JumpHost             |  i-0e43431f877e7b0a0                                                           |||
|||                               |  ApiEndpoint          |  7jsrxwc5i1                                                                    |||
|||  DocumentDB user name         |  DbUser               |  PrimaryUser                                                                   |||
||+-------------------------------+-----------------------+--------------------------------------------------------------------------------+||
|||                                                               Parameters                                                               |||
||+-----------------------------------------------------+----------------------------------------------------------------------------------+||
|||                    ParameterKey                     |                                 ParameterValue                                   |||
||+-----------------------------------------------------+----------------------------------------------------------------------------------+||
|||  AppPublicCIDRB                                     |  10.20.2.0/24                                                                    |||
|||  vpccidr                                            |  10.20.0.0/16                                                                    |||
|||  PrimaryPassword                                    |  PrimaryPassword                                                                 |||
|||  TemplateBucketPrefix                               |  cfn                                                                             |||
|||  AppPublicCIDRA                                     |  10.20.1.0/24                                                                    |||
|||  ProjectTag                                         |  DocDbAutoScale                                                                  |||
|||  ZipGet                                             |  aICkQl5354WAg_Pf8ycL_v9xhg1IUbvO                                                |||
|||  ZipPatch                                           |  pXN47gT0ut5EmybyCXKFnvF9DoAR.cbJ                                                |||
|||  AppPrivateCIDRB                                    |  10.20.4.0/24                                                                    |||
|||  AppPrivateCIDRA                                    |  10.20.3.0/24                                                                    |||
|||  TemplateBucketName                                 |  asg-docdb-05092023                                                              |||
||+-----------------------------------------------------+----------------------------------------------------------------------------------+||
|||                                                                  Tags                                                                  |||
||+--------------------------------------------------+-------------------------------------------------------------------------------------+||
|||  Key                                             |  Project                                                                            |||
|||  Value                                           |  DocDbLambda                                                                        |||
||+--------------------------------------------------+-------------------------------------------------------------------------------------+||
```

6. Register the autoscaler:

```javascript {.line-numbers}
cd scripts
# python register.py <ApiEndpoint> <Region> <DbClusterIdentifier> <Account>
python register.py 7jsrxwc5i1 us-east-1 dbcluster-hvloe3jp7vqk 113534181560
```

The autoscaler `implements` the `custom resource scaling pattern` from the Application Auto Scaling service. In this pattern, we have a REST API that offers a GET method to obtain the status of the resource we want to scale, and a PATCH method that updates the resource.


aws ssm start-session --target <JumpHost>

aws ssm start-session --target i-0e43431f877e7b0a0

sudo su - ec2-user
sudo yum -y install java
curl -O --location https://github.com/brianfrankcooper/YCSB/releases/download/0.17.0/ycsb-0.17.0.tar.gz
tar xfvz ycsb-0.17.0.tar.gz
cd ycsb-0.17.0

# Misc commands

1. Finding a Cluster's Endpoints

aws docdb describe-db-clusters ^
   --region us-east-1 ^
   --db-cluster-identifier sample-cluster ^
   --query 'DBClusters[*].[DBClusterIdentifier,Port,Endpoint,ReaderEndpoint]'

2. Loading data

mongoimport --db samples --collection cases -h <Amazon DocumentDB cluster endpoint>:27017 -u <Amazon DocumentDB cluster master user name> -p <Amazon DocumentDB cluster master password> --ssl --sslCAFile rds-combined-ca-bundle.pem --file cases.json

- if has an array
mongoimport --db samples --collection movies -h <Amazon DocumentDB cluster endpoint>:27017 -u <Amazon DocumentDB cluster master user name> -p <Amazon DocumentDB cluster master password> --ssl --sslCAFile rds-combined-ca-bundle.pem --jsonArray --file moviedata.json

src: https://aws.amazon.com/blogs/database/get-started-with-the-amazon-documentdb-jdbc-driver/