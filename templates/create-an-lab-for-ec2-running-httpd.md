<h1>create an application load balancer with an EC2 instance running httpd as target</h1>

src: chatgpt

1. CFN

```yaml
AWSTemplateFormatVersion: '2010-09-09'
Description: Create an Application Load Balancer with EC2 instance running httpd

Parameters:
  VpcId:
    Type: AWS::EC2::VPC::Id
    Description: ID of the VPC where the resources will be created

Resources:
  WebServerSecurityGroup:
    Type: AWS::EC2::SecurityGroup
    Properties:
      GroupDescription: Enable HTTP and SSH access
      VpcId: !Ref VpcId
      SecurityGroupIngress:
        - IpProtocol: tcp
          FromPort: 22
          ToPort: 22
          CidrIp: 0.0.0.0/0
        - IpProtocol: tcp
          FromPort: 80
          ToPort: 80
          CidrIp: 0.0.0.0/0

  WebServerInstance:
    Type: AWS::EC2::Instance
    Properties:
      InstanceType: t2.micro
      SecurityGroupIds:
        - !Ref WebServerSecurityGroup
      KeyName: your-key-pair-name
      ImageId: ami-xxxxxxxxxxxxxx  # Replace with your desired AMI ID (Amazon Linux 2 with httpd pre-installed, for example)
      UserData:
        Fn::Base64: !Sub |
          #!/bin/bash
          yum update -y
          yum install -y httpd
          systemctl start httpd
          systemctl enable httpd

  LoadBalancer:
    Type: AWS::ElasticLoadBalancingV2::LoadBalancer
    Properties:
      Name: MyALB
      Subnets:
        - subnet-xxxxxxxx  # Replace with your subnet IDs where you want to deploy the ALB
        - subnet-xxxxxxxx
      SecurityGroups:
        - !GetAtt WebServerSecurityGroup.GroupId
      Scheme: internet-facing
      Type: application

  TargetGroup:
    Type: AWS::ElasticLoadBalancingV2::TargetGroup
    Properties:
      Name: MyTargetGroup
      Port: 80
      Protocol: HTTP
      VpcId: !Ref VpcId
      TargetType: instance
      HealthCheckPath: "/"
      HealthCheckProtocol: HTTP
      Matcher:
        HttpCode: 200

  TargetGroupAttachment:
    Type: AWS::ElasticLoadBalancingV2::TargetGroupAttachment
    Properties:
      TargetGroupArn: !Ref TargetGroup
      TargetId: !Ref WebServerInstance

Outputs:
  ALBDNSName:
    Description: DNS Name of the ALB
    Value: !GetAtt LoadBalancer.DNSName
  WebServerInstanceId:
    Description: ID of the EC2 instance running httpd
    Value: !Ref WebServerInstance

```

2.  create an index.html

```bash
ssh ec2-user@your-ec2-instance-ip

# Navigate to the directory where index.html is located
cd /var/www/html/

# Edit the index.html file
sudo nano index.html

# Replace the content with your desired message or HTML code
```

3. sample index.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Custom Message</title>
</head>
<body>
    <h1>Welcome to My Custom Web Server!</h1>
    <p>This is a customized message served by the Apache HTTP Server.</p>
</body>
</html>
```