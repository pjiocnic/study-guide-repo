
# 1. [Safari] Scalable Data Architecture with Java By Sinchan Banerjee

**Section 1 – Foundation of Data Systems**
* Basics of Modern Data Architecture
* Exploring the landscape of data engineering
* What is data engineering?
* Dimensions of data
* Types of data engineering problems
* Responsibilities and challenges of a Java data architect
* Data architect versus data engineer
* Challenges of a data architect
* Techniques to mitigate those challenges
* Summary
**2. Data Storage and Databases**
* Understanding data types, formats, and encodings
* Data types
* Data formats
* Understanding file, block, and object storage
* File storage
* Block storage
* Object storage
* The data lake, data warehouse, and data mart
* Data lake
* Data warehouse
* Data marts
* Databases and their types
* Relational database
* NoSQL database
* Data model design considerations
* Summary
**3. Identifying the Right Data Platform**
* Technical requirements
* Virtualization and containerization platforms
* Benefits of virtualization
* Containerization
* Benefits of containerization
* Kubernetes
* Hadoop platforms
* Hadoop architecture
* Cloud platforms
* Benefits of cloud computing
* Choosing the correct platform
* When to choose virtualization versus containerization
* When to use big data
* Choosing between on-premise versus cloud-based solutions
* Choosing between various cloud vendors
* Summary
**Section 2 – Building Data Processing Pipelines**
**4. ETL Data Load – A Batch-Based Solution to Ingesting Data in a Data Warehouse**
* Technical requirements
* Understanding the problem and source data
* Problem statement
* Understanding the source data
* Building an effective data model
* Relational data warehouse schemas
* Evaluation of the schema design
* Designing the solution
* Implementing and unit testing the solution
* Summary
**5. Architecting a Batch Processing Pipeline**
* Technical requirements
* Developing the architecture and choosing the right tools
* Problem statement
* Analyzing the problem
* Architecting the solution
* Factors that affect your choice of storage
* Determining storage based on cost
* The cost factor in the processing layer
* Implementing the solution
* Profiling the source data
* Writing the Spark application
* Deploying and running the Spark application
* Developing and testing a Lambda trigger
* Performance tuning a Spark job
* Querying the ODL using AWS Athena
* Summary
6. Architecting a Real-Time Processing Pipeline
* Technical requirements
* Understanding and analyzing the streaming problem
* Problem statement
* Analyzing the problem
* Architecting the solution
* Implementing and verifying the design
* Setting up Apache Kafka on your local machine
* Developing the Kafka streaming application
* Unit testing a Kafka Streams application
* Configuring and running the application
* Creating a MongoDB Atlas cloud instance and database
* Configuring Kafka Connect to store the results in MongoDB
* Verifying the solution
* Summary
**7. Core Architectural Design Patterns**
* Core batch processing patterns
* The staged Collect-Process-Store pattern
* Common file format processing pattern
* The Extract-Load-Transform pattern
* The compaction pattern
* The staged report generation pattern
* Core stream processing patterns
* The outbox pattern
* The saga pattern
* The choreography pattern
* The Command Query Responsibility Segregation (CQRS) pattern
* The strangler fig pattern
* The log stream analytics pattern
* Hybrid data processing patterns
* The Lambda architecture
* The Kappa architecture
* Serverless patterns for data ingestion
* Summary
**8 Enabling Data Security and Governance**
* Technical requirements
* Introducing data governance – what and why
* When to consider data governance
* The DGI data governance framework
* Practical data governance using DataHub and NiFi
* Creating the NiFi pipeline
* Setting up DataHub
* Governance activities
* Understanding the need for data security
* Solution and tools available for data security
* Summary
**Section 3 – Enabling Data as a Service**
**9 Exposing MongoDB Data as a Service**
* Technical requirements
* Introducing DaaS – what and why
* Benefits of using DaaS
* Creating a DaaS to expose data using Spring Boot
* Problem statement
* Analyzing and designing a solution
* Implementing the Spring Boot REST application
* Deploying the application in an ECS cluster
* API management
* Enabling API management over the DaaS API using AWS API Gateway
* Summary
**10 Federated and Scalable DaaS with GraphQL**
* Technical requirements
* Introducing GraphQL – what, when, and why
* Operation types
* Why use GraphQL?
* When to use GraphQL
* Core architectural patterns of GraphQL
* A practical use case – exposing federated data models using GraphQL
* Summary
**Section 4 – Choosing Suitable Data Architecture**
**11 Measuring Performance and Benchmarking Your Applications**
* Performance engineering and planning
* Performance engineering versus performance testing
* Tools for performance engineering
* Publishing performance benchmarks
* Optimizing performance
* Java Virtual Machine and garbage collection optimizations
* Big data performance tuning
* Optimizing streaming applications
* Database tuning
* Summary
**12 Evaluating, Recommending, and Presenting Your Solutions**
* Creating cost and resource estimations
* Storage and compute capacity planning
* Effort and timeline estimation
* Creating an architectural decision matrix
* Data-driven architectural decisions to mitigate risk
* Presenting the solution and recommendations
* Summary

# 2. [Safari] AWS for System Administrators Prashant Lakhera



**Chapter 1: Setting Up the AWS Environment**
* Technical requirements
* Setting up the environment
* Installing the AWS CLI
* Configuring command-line completion
* Configuring the AWS command line
* Understanding the AWS CLI command structure
* Introducing Python Boto3
* Installing Python Boto3
* Verifying the Boto3 setup
* Introducing CloudFormation
* Writing your first CloudFormation template
* Creating a CloudFormation stack using the AWS console
* Creating a CloudFormation stack using the AWS CLI
* Introducing Terraform
* Installing Terraform
* Creating resources using Terraform
* Installing tools in an automated way
* Summary
**Chapter 2: Protecting Your AWS Account Using IAM**
* Technical requirements
* Creating IAM users and groups
* Introducing IAM users
* Introducing IAM groups
* Understanding IAM policies
* IAM policy structure
* Introducing ARN
* IAM policy evaluation
* Creating the IAM policy using the AWS CLI
* Creating IAM roles
* Advantages of using an IAM role
* Creating an IAM role using Terraform
* Introducing AWS Security Token Service (AWS STS)
* Advantages of AWS STS
* Use cases
* IAM cross-account access
* Real-time use case of launching a specific instance using CloudFormation
* Rotating IAM credentials using Boto3
* Prerequisites
* Creating a Boto3 script to rotate credentials
* Summary

**Chapter 3: Creating a Data Center in the Cloud Using VPC**
* Technical requirements
* Setting up two VPCs
* Creating your first VPC using the AWS console
* Creating a second VPC using CloudFormation
* Introducing AWS Transit Gateway
* Creating your first transit gateway using the AWS console
* Creating a second transit gateway using Terraform
* Real-time use case to enable a VPC flow log
* Summary

**Chapter 4: Scalable Compute Capacity in the Cloud via EC2**
* Technical requirements
* Setting up EC2 instances
* Creating an EC2 instance using AWS CloudFormation
* Creating an AWS billing alarms
* Real-time use case to clean up an unused AMI
* Real-time use case to detach unused EBS volumes
* Real-time use case to shutdown instances on a daily basis
* Summary

**Chapter 5: Increasing an Application's Fault Tolerance with Elastic Load Balancing**
* Technical requirements
* Different load balancers offered by AWS
* Setting up the application load balancer
* Setting up the application load balancer
* Automating the application load balancer using Terraform
* Summary

**Chapter 6: Increasing Application Performance Using AWS Auto Scaling**
* Technical requirements
* Setting up Auto Scaling
* Creating a launch template
* Creating an AWS Auto Scaling group
* Verifying an Auto Scaling group
* Understanding Auto Scaling policies
* Scaling an application based on demand
* Testing the Auto Scaling group
* Creating an Auto Scaling group using Terraform
* Summary

**Chapter 7: Creating a Relational Database in the Cloud using AWS Relational Database Service (RDS)**
* Technical requirements
* The different database offerings in AWS RDS
* Setting up AWS RDS in high availability mode
* Setting up a MySQL read replica
* Automating AWS RDS MySQL creation using Terraform
* Summary

**Chapter 8: Monitoring AWS Services Using CloudWatch and SNS**
* Technical requirements
* CloudWatch monitoring
* Monitoring custom metrics using CloudWatch
* Downloading and installing the CloudWatch agent
* Creating an IAM role used by CloudWatch agent
* Running the CloudWatch agent on your server
* Introduction to SNS
* Introduction to CloudWatch Events
* Automating alarm notification using email and a Slack channel
* Configuring Slack
* Configuring CloudWatch
* Creating a Lambda function
* Testing the integration
* Summary

**Chapter 9: Centralizing Logs for Analysis**
* Technical requirements
* Why do we need log management?
* Setting up the CloudWatch agent
* Setting up AWS Elasticsearch and Kibana
* Summary

**Chapter 10: Centralizing Cloud Backup Solution**
* Technical requirements
* The v backup options offered by AWS
* Why do we back up data?
* Setting up the AWS DLM
* Backing up your data to S3 using the AWS CLI
* Transitioning S3 data to Glacier using a lifecycle policy
* Automating transitioning S3 data to Glacier using Terraform
* Summary

**Chapter 11: AWS Disaster Recovery Solutions**
* Technical requirements
* Discussing the various DR solutions offered by AWS
* Backup and restore
* Pilot light
* Warm standby in AWS
* Hot standby (with multi-site)
* Configuring a website to fail over to an S3 bucket
* Summary

**Chapter 12: AWS Tips and Tricks**
* Technical requirements
* Some common pitfalls – VPC limitations
* Which VPC subnets to choose while building a VPC
* Dedicated instance versus dedicated host – which should you choose?
* The power of the IAM permission boundary
* Custom CloudWatch metrics
* Tagging, tagging, and tagging – why is tagging important?
* Protecting your EC2 instances and EBS volumes using termination protection
* How to reduce your AWS bill
* Choosing an AWS bucket name and how to create a random bucket name
* Automating AMI creation
* Creating an AMI using the AWS console
* Creating an AMI using the AWS CLI
* Automating AMI creation using Packer
* Summary

# 3. [Early Access] Learning System Design on AWS Jayanth Kumar, Mandeep Singh

Chapter 2. AWS Network Services

# 4. [Early Access] AWS System Administration, 2nd Edition Federico Lucifredi, Jacob Zimmerman, Mike Ryan

1. Setting Up AWS Tools
2. EC2 and CloudFormation Crash Course
3. Amazon Machine Images

# 5. AWS System Administration Mike Ryan, Federico Lucifredi

# Serverless Architectures on AWS, Second Edition Peter Sbarski, Yan Cui, Ajay Nair