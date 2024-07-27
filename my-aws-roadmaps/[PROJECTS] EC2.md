
# 1. [TODO] Standup an EC2 instance
1. Create an EC2 instance and install Apache
1. While creating the instance, create a Security Group (my-sg-1) and open port 80(HTTP) for future testing
1. Allocate EIP and Associate the created Elastic IP to the primary/default ENI (eth0)
1. Create an additional ENI (secondary ENI)
1. Create another Elastic IP and associate to secondary ENI (eth1)
1. Attach the additional ENI to the EC2 instance.

ref: https://crishantha.medium.com/handling-elastic-network-interface-s-enis-in-aws-part-01-9696fe6f6df0