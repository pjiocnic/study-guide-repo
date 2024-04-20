
aws ec2 replace-iam-instance-profile-association \
  --iam-instance-profile "Name=$profile_name" \
  --association-id $iip


An IAM instance profile is a container for an IAM role that you can use to pass role information to an EC2 instance when the instance starts.

aws configure
AWS Access Key ID [None]: AKIAIOSFODNN7EXAMPLE
AWS Secret Access Key [None]: wJalrXUtnFEMI/K7MDENG/EXAMPLEKEY
Default region name [None]: us-east-1
Default output format [None]: text

aws_access_key_id = AKIARU3ZKEC4PHELCYMO
aws_secret_access_key = va+XhPKZMrmKWHkehGY3iR2/cegI1jsseoITWuA/

# Subnet IP calculator

https://www.calculator.net/ip-subnet-calculator.html