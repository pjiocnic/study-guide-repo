# This is the main file to add resources

locals {
  vpc = {
    azs        = slice(data.aws_availability_zones.available.names, 0, var.az_num)
    cidr_block = var.vpc_cidr_block
  }

  rds = {
    engine         = "mysql"
    engine_version = "8.0.28"
    instance_class = "db.t3.micro"
    db_name        = "mydb"
    username       = "dbuser123"
  }

  vm = {
    instance_type = "m5.large"

    instance_requirements = {
      memory_mib = {
        min = 8192
      }
      vcpu_count = {
        min = 2
      }
      instance_generations = ["current"]
    }
  }

  demo = {
    admin = {
      username = "wpadmin"
      password = "wppassword"
      email    = "admin@demo.com"
    }
  }
}

# Basic Lookups
data "aws_region" "current" {}

data "aws_availability_zones" "available" {
  state = "available"
}

data "aws_ami" "linux" {
  owners      = ["amazon"] # Specifies that the AMI should be owned by Amazon.
  most_recent = true
  name_regex  = "^al2023-ami-2023\\..*" # Specifies a regular expression for the name of the AMI

  filter {   #  additional filtering criteria - looking for AMIs with architecture "x86_64."
    name   = "architecture"
    values = ["x86_64"]
  }
}

# IAM
data "aws_iam_policy" "administrator" {
  name = "AdministratorAccess"
}

data "aws_iam_policy" "ssm_managed" {
  name = "AmazonSSMManagedInstanceCore"
}

data "aws_iam_policy" "database" {
  name = "AmazonRDSDataFullAccess"
}

data "aws_iam_policy" "s3_ReadOnly" {
  name = "AmazonS3ReadOnlyAccess"
}

data "aws_iam_policy_document" "assume_role" {
  statement {
    effect = "Allow"

    principals {
      type        = "Service"
      identifiers = ["ec2.amazonaws.com"]
    }

    actions = ["sts:AssumeRole"]
  }
}

# ---------------------------------------------- Creating IAM resources
# aws_iam_role, aws_iam_instance_profile (IAM EC2 Instance profile of a IAM role to attach to an EC2 instance)

# Create an IAM Role
resource "aws_iam_role" "app" {
  name               = "app"
  path               = "/"
  assume_role_policy = data.aws_iam_policy_document.assume_role.json
  managed_policy_arns = [
    data.aws_iam_policy.ssm_managed.arn,
    data.aws_iam_policy.database.arn
  ]
}

# Create an IAM Role
resource "aws_iam_role" "web_hosting" {
  name               = "web_hosting"
  path               = "/"
  assume_role_policy = data.aws_iam_policy_document.assume_role.json
  managed_policy_arns = [
    data.aws_iam_policy.ssm_managed.arn,
    data.aws_iam_policy.s3_ReadOnly.arn
  ]
}

# Create an IAM EC2 Instance profile of a IAM role to attach to an EC2 instance
resource "aws_iam_instance_profile" "app" {
  name = "app-profile"
  role = aws_iam_role.app.name
}

resource "aws_iam_instance_profile" "web_hosting" {
  name = "web-hosting-profile"
  role = aws_iam_role.web_hosting.name
}

# ---------------------------------------- Creating Networking resources
# aws_vpc, aws_subnet, aws_internet_gateway, aws_route_table, aws_main_route_table_association, aws_route_table_association, aws_nat_gateway, aws_eip

# VPC
resource "aws_vpc" "default" {
  cidr_block           = local.vpc.cidr_block
  enable_dns_hostnames = true
  enable_dns_support   = true

  tags = {
    Name = "${var.namespace}-vpc"
  }
}

resource "aws_subnet" "public" {
  for_each = { for index, az_name in local.vpc.azs : index => az_name } # creates a map of index:az_name

  vpc_id                  = aws_vpc.default.id
  # The cidrsubnet function is used to calculate a subnet CIDR block based on the CIDR block of the parent VPC (aws_vpc.default.cidr_block).
  # aws_vpc.default.cidr_block: CIDR block of the parent VPC
  # 8: number of bits to allocate for the subnet - Subnet size 2^(32-8) = 256 IP addresses.
  # (each.key + (length(local.vpc.azs) * 0): calculates the new subnet address ..0,1,2
  #   each.key: index of the availability zone in the loop
  #   local.vpc.azs: total number of availability zones.
  cidr_block              = cidrsubnet(aws_vpc.default.cidr_block, 8, (each.key + (length(local.vpc.azs) * 0)))
  availability_zone       = each.value # az_name
  map_public_ip_on_launch = true

  tags = {
    Name = "${var.namespace}-subnet-public-${each.key}"
  }
}

resource "aws_subnet" "private" {
  for_each = { for index, az_name in local.vpc.azs : index => az_name }

  vpc_id            = aws_vpc.default.id
  # (each.key + (length(local.vpc.azs) * 0): calculates the new subnet address .. 3,4,5
  cidr_block        = cidrsubnet(aws_vpc.default.cidr_block, 8, (each.key + (length(local.vpc.azs) * 1)))
  availability_zone = each.value

  tags = {
    Name = "${var.namespace}-subnet-private-${each.key}"
  }
}

resource "aws_subnet" "private_ingress" {
  for_each = { for index, az_name in local.vpc.azs : index => az_name }

  vpc_id            = aws_vpc.default.id
  # (each.key + (length(local.vpc.azs) * 0): calculates the new subnet address .. 6,7,8
  cidr_block        = cidrsubnet(aws_vpc.default.cidr_block, 8, (each.key + (length(local.vpc.azs) * 2)))
  availability_zone = each.value

  tags = {
    Name = "${var.namespace}-subnet-private_ingress-${each.key}"
  }
}

resource "aws_internet_gateway" "default" {
  vpc_id = aws_vpc.default.id

  tags = {
    Name = "${var.namespace}-internet-gateway"
  }
}

resource "aws_route_table" "public" {
  vpc_id = aws_vpc.default.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.default.id
  }

  tags = {
    Name = "${var.namespace}-route-table-public"
  }
}

resource "aws_route_table" "private_ingress" {
  count = length(aws_subnet.private_ingress)

  vpc_id = aws_vpc.default.id

  route {
    cidr_block     = "0.0.0.0/0"
    nat_gateway_id = aws_nat_gateway.default[count.index].id
  }

  tags = {
    Name = "${var.namespace}-route-table-private-ingress-${count.index}"
  }
}

resource "aws_main_route_table_association" "default" {
  vpc_id         = aws_vpc.default.id
  route_table_id = aws_route_table.public.id
}

resource "aws_route_table_association" "public" {
  count = length(aws_subnet.public)

  subnet_id      = aws_subnet.public[count.index].id
  route_table_id = aws_route_table.public.id
}

resource "aws_route_table_association" "private_ingress" {
  count = length(aws_subnet.private_ingress)

  subnet_id      = aws_subnet.private_ingress[count.index].id
  route_table_id = aws_route_table.private_ingress[count.index].id
}

resource "aws_eip" "nat_gateway" {
  count = length(aws_subnet.public)

  tags = {
    Name = "${var.namespace}-private_ingress-nat-gateway-eip-${count.index}"
  }
}

resource "aws_nat_gateway" "default" {
  count = length(aws_subnet.public)

  connectivity_type = "public"
  subnet_id         = aws_subnet.public[count.index].id
  allocation_id     = aws_eip.nat_gateway[count.index].id
  depends_on        = [aws_internet_gateway.default]

  tags = {
    Name = "${var.namespace}-private_ingress-nat-gateway-${count.index}"
  }
}

# ------------------------------------------ create Security Groups and VPC endpoints in your VPC
# aws_security_group, aws_vpc_endpoint

# Security Groups
resource "aws_security_group" "nfs" {
  name_prefix = "${var.namespace}-nfs-"
  vpc_id      = aws_vpc.default.id

  ingress {
    description = "Allow any NFS traffic from private subnets"
    cidr_blocks = concat(values(aws_subnet.private)[*].cidr_block, values(aws_subnet.private_ingress)[*].cidr_block)
    from_port   = 2049 # NFS traffic
    to_port     = 2049
    protocol    = "tcp"
  }

  egress {
    description      = "Allow all outbound traffic"
    cidr_blocks      = ["0.0.0.0/0"] # to any destination
    ipv6_cidr_blocks = ["::/0"]
    from_port        = 0
    to_port          = 0
    protocol         = "-1" # All protocols
  }
}

resource "aws_security_group" "app" {
  name_prefix = "${var.namespace}-app-"
  vpc_id      = aws_vpc.default.id

  ingress {
    description = "Allow HTTPS from any IP"
    cidr_blocks = ["0.0.0.0/0"]
    from_port   = 443  # https traffic
    to_port     = 443
    protocol    = "tcp"
  }

  ingress {
    description = "Allow HTTP from any IP"
    cidr_blocks = ["0.0.0.0/0"]
    from_port   = 80 # http traffic
    to_port     = 80
    protocol    = "tcp"
  }

  egress {
    description      = "Allow all outbound traffic"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
  }
}

resource "aws_security_group" "db" {
  name_prefix = "${var.namespace}-db-"
  vpc_id      = aws_vpc.default.id

  ingress {
    description = "Allow incoming traffic for MySQL"
    from_port   = 3306 # MySQL traffic
    to_port     = 3306
    protocol    = "tcp"
    cidr_blocks = concat(values(aws_subnet.private)[*].cidr_block, values(aws_subnet.private_ingress)[*].cidr_block)
  }

  egress {
    description      = "Allow all outbound traffic"
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }
}

resource "aws_security_group" "any" {
  name_prefix = "${var.namespace}-any-"
  vpc_id      = aws_vpc.default.id

  ingress {
    description      = "Allow any incoming traffic "
    from_port        = 0 # all ports
    to_port          = 0
    protocol         = -1 #  all protocols.
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  egress {
    description      = "Allow all outbound traffic"
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }
}

# ------------------------------------------------ Create VPC endpoints
resource "aws_vpc_endpoint" "interface" {
  # The for_each block is used to create multiple VPC endpoints for each of the specified services.
  for_each = toset(["ssm", "ssmmessages", "ec2messages", "secretsmanager"])

  vpc_id              = aws_vpc.default.id # The VPC endpoint is associated with the VPC specified by aws_vpc.default.id.
  service_name        = "com.amazonaws.${data.aws_region.current.name}.${each.key}"
  vpc_endpoint_type   = "Interface" # indicates interface VPC endpoint.
  private_dns_enabled = true # indicates that instances in your VPC use the Amazon provided DNS server for DNS resolution

  subnet_ids         = values(aws_subnet.private_ingress)[*].id # subnets where the VPC endpoint network interfaces will be created.
  security_group_ids = [aws_security_group.any.id] #very permissive (allowing all traffic)

  tags = {
    Name = "${var.namespace}-endpoint-${each.key}"
  }
}

# Gateway endpoint
resource "aws_vpc_endpoint" "gateway" {
  for_each = toset(["s3"])

  vpc_id       = aws_vpc.default.id
  service_name = "com.amazonaws.${data.aws_region.current.name}.${each.key}"

  tags = {
    Name = "${var.namespace}-endpoint-${each.key}"
  }
}

# ------------------------------------------ create Security Groups and VPC endpoints in your VPC
# Create an RDS MySQL database, a load balancer, an autoscaling group, an EFS file system, an S3 bucket

# RDS
# A DB subnet group in AWS is a collection of subnets
resource "aws_db_subnet_group" "mission_db_group" {
  name       = "${var.namespace}-db-group"
  subnet_ids = values(aws_subnet.private)[*].id # can be group of private subnets

  tags = {
    Name = "${var.namespace}-db-group"
  }
}

// --------------------------------------------------------- Secrets manager setup

resource "random_password" "default" {
  length           = 25
  special          = false  # special chars not included
  override_special = "!#$%&*()-_=+[]{}<>:?" # indicates custom set of special characters to be included in the password, even if special is set to false
}

// -------------------------------------------------------- Create a Secrets Manager entry
resource "aws_secretsmanager_secret" "db" {
  name_prefix             = "${var.namespace}-secret-db-"
  description             = "Password to the RDS"
  recovery_window_in_days = 7
}

// -------------------------------------------------------- Create a value for the Secrets Manager entry
resource "aws_secretsmanager_secret_version" "db" {
  secret_id     = aws_secretsmanager_secret.db.id
  secret_string = random_password.default.result # accessing random password
}

resource "aws_db_instance" "wp_mysql" {
  identifier = "${var.namespace}-db"

  allocated_storage      = 20
  engine                 = local.rds.engine
  engine_version         = local.rds.engine_version
  instance_class         = local.rds.instance_class
  db_name                = local.rds.db_name
  username               = local.rds.username
  password               = aws_secretsmanager_secret_version.db.secret_string
  db_subnet_group_name   = aws_db_subnet_group.mission_db_group.name
  vpc_security_group_ids = [aws_security_group.db.id]
  multi_az               = true
  skip_final_snapshot    = true

  tags = {
    Name = "${var.namespace}-db"
  }
}

// ------------------------------------------------------------------  EFS setup

# In summary, creates an EFS file system with encryption enabled and associated
# mount targets in different subnets, one for each availability zone in the VPC.
# The mount targets are configured to use the NFS security group. This setup allows
# instances in the specified subnets to mount the EFS file system and share data across those instances.

# EFS: Create an EFS volume
resource "aws_efs_file_system" "mission_app" {
  creation_token = "${var.namespace}-efs"
  encrypted      = true # Indicates whether the file system is encrypted.

  tags = {
    Name = "${var.namespace}-efs"
  }
}

# Create a configuration for efs mount targets in private_ingress subnets
resource "aws_efs_mount_target" "mission_app_targets" {
  count = length(local.vpc.azs) # Utilizes the count parameter to create multiple EFS mount targets, one for each availability zone (az) in the VPC

  file_system_id  = aws_efs_file_system.mission_app.id # Associates the EFS mount target with the EFS file system
  subnet_id       = aws_subnet.private_ingress[count.index].id # Specifies the subnet in which the EFS mount target is created
  security_groups = [aws_security_group.nfs.id]
}

// ----------------------------------------------------------  EC2s for Wordpress application

# Create an EC2 that bootstraps the Wordpress application
resource "aws_instance" "staging_app" {

  # define certain lifecycle settings for the resource.
  lifecycle {
    prevent_destroy = false # allows the instance to be destroyed by Terraform
    ignore_changes  = [iam_instance_profile, tags, tags_all] # specifies the attributes for which changes should be ignored during updates.
  }

  ami                         = data.aws_ami.linux.image_id
  instance_type               = local.vm.instance_type
  subnet_id                   = aws_subnet.private_ingress[0].id
  user_data_replace_on_change = true # Specifies whether to replace the instance when user_data changes.

  user_data = templatefile("${path.module}/userdata/staging-efs.sh", {
    region        = data.aws_region.current.name,
    efs_id        = aws_efs_file_system.mission_app.id
    db_name       = aws_db_instance.wp_mysql.db_name
    db_username   = aws_db_instance.wp_mysql.username
    db_password   = aws_db_instance.wp_mysql.password
    db_host       = aws_db_instance.wp_mysql.address
    DOMAIN_NAME   = aws_cloudfront_distribution.mission_app.domain_name
    demo_username = local.demo.admin.username
    demo_password = local.demo.admin.password
    demo_email    = local.demo.admin.email
  })

  iam_instance_profile   = aws_iam_instance_profile.app.name # The IAM instance profile associated with the instance.
  availability_zone      = data.aws_availability_zones.available.names[0] # The availability zone for the instance
  vpc_security_group_ids = [aws_security_group.app.id] # The security group(s) associated with the instance

  # Configures the metadata options for the instance, including HTTP endpoint settings
  metadata_options {
    http_endpoint               = "enabled"
    http_put_response_hop_limit = 1
    http_tokens                 = "required"
    instance_metadata_tags      = "enabled"
  }

  # Configures the root block device of the instance, specifying deletion on termination and encryption
  root_block_device {
    delete_on_termination = true
    encrypted             = true
  }

  tags = {
    Name = format("${var.namespace}-staging_app-%s", element(data.aws_availability_zones.available.names, 0))
  }

  # Specifies dependencies on other resources including S3 objects
  depends_on = [aws_s3_object.mission_app-private_key, aws_s3_object.mission_app-public_key]
}

# aws_ami_copy: Create a copy of the Amazon Machine Image (AMI) being used
# is used to copy an Amazon Machine Image (AMI) from one AWS region to another
# in our example we copy ami within the same region but you can specify different region
resource "aws_ami_copy" "mission_app_ami" {
  name              = "Amazon Linux 2 Image"
  description       = "A copy of ${data.aws_ami.linux.image_id} - ${data.aws_ami.linux.description}"
  source_ami_id     = data.aws_ami.linux.image_id
  source_ami_region = data.aws_region.current.name # if need to copy from a different region specify it here - source_ami_region = "us-west-2"

  tags = {
    Name               = "${var.namespace}-ami"
    Description        = data.aws_ami.linux.description
    "Creation Date"    = data.aws_ami.linux.creation_date
    "Deprecation Time" = data.aws_ami.linux.deprecation_time
  }
}


// -------------------------------------------------------------- Launch configuration
# Launch configuration - Create a launch template that hosts the Wordpress web application
resource "aws_launch_template" "mission_app_lc" {
  name_prefix = "${var.namespace}-mission_app_iac_lc-"
  image_id    = aws_ami_copy.mission_app_ami.id

  # Specifies requirements for the instances launched from the template, such as minimum memory and vCPU count
  instance_requirements {
    memory_mib {
      min = local.vm.instance_requirements.memory_mib.min
    }
    vcpu_count {
      min = local.vm.instance_requirements.vcpu_count.min
    }

    allowed_instance_types = ["m*"] # Limits the allowed instance types to those starting with "m*".
    instance_generations   = local.vm.instance_requirements.instance_generations # Specifies the instance generations allowed based on local configuration.
  }

  ebs_optimized          = true
  vpc_security_group_ids = [aws_security_group.app.id]

  iam_instance_profile {
    name = aws_iam_instance_profile.web_hosting.name
  }

  # specifies an EBS volume with encryption and deletion on termination.
  block_device_mappings {
    device_name = "/dev/xvda"

    ebs {
      delete_on_termination = true
      encrypted             = true
    }
  }

  monitoring {
    enabled = true # enables detailed monitoring
  }

  # Configures the metadata options for the instances launched from the template, including HTTP endpoint settings.
  metadata_options {
    http_endpoint               = "enabled"
    http_put_response_hop_limit = 1
    http_tokens                 = "required"
    instance_metadata_tags      = "enabled"
  }

  user_data = base64encode(templatefile("${path.module}/userdata/staging-wordpress.sh", {
    region    = data.aws_region.current.name,
    efs_id    = aws_efs_file_system.mission_app.id,
    s3_bucket = aws_s3_bucket.mission_app.bucket
  }))

  update_default_version = true # specifies that this launch template should become the default version for the specified launch template name
}

# --------------- ASG that is triggerred by Cloudwatch alarms to scale out or scale in
# define an Auto Scaling Group that automatically adjusts the number of instances based on demand,
resource "aws_autoscaling_group" "mission_app_asg" {
  name     = "${var.namespace}-asg-mission_app"
  min_size = 2
  max_size = 8

  # Identifies the list of private subnets in which the instances launched by the Auto Scaling Group will be placed.
  vpc_zone_identifier = values(aws_subnet.private_ingress)[*].id
  target_group_arns   = [aws_lb_target_group.mission_app.arn] # instances launched by the Auto Scaling Group will register with this target group for load balancing.

  # Defines a mixed instances policy, allowing the Auto Scaling Group to use a combination of On-Demand Instances and Spot Instances
  mixed_instances_policy {
    launch_template {
      launch_template_specification {
        launch_template_id = aws_launch_template.mission_app_lc.id # references launch template
        version            = "$Latest"
      }
    }
  }

  # indicates a new ASG will be created before the old one is destroyed when there are changes.
  lifecycle {
    create_before_destroy = true
  }

  tag {
    key                 = "Name"
    value               = "${var.namespace}-asg-mission_app"
    propagate_at_launch = true
  }

  depends_on = [aws_instance.staging_app, aws_db_instance.wp_mysql] # these resources are created before the Auto Scaling Group.
}

# define a policy that triggers scaling actions based on certain conditions.
# The policy defined here is for scaling out (adding instances).
resource "aws_autoscaling_policy" "mission_app_scale_out_policy" {
  name                   = "${var.namespace}-out-policy"
  scaling_adjustment     = 4 # Indicates the number of instances by which to adjust the desired capacity of the Auto Scaling Group
  adjustment_type        = "ChangeInCapacity" # adjusts the capacity by the specified number.
  cooldown               = 300 # Specifies the amount of time, in seconds, after a scaling activity completes before another can start
  autoscaling_group_name = aws_autoscaling_group.mission_app_asg.name # Specifies the name of the Auto Scaling Group to which the policy is applied.
}

# ASG Cloudwatch policy
resource "aws_cloudwatch_metric_alarm" "mission_app_scale_out_alarm" {
  alarm_name          = "${var.namespace}-out-alarm"
  comparison_operator = "GreaterThanOrEqualToThreshold"
  evaluation_periods  = "2" # Specifies the number of periods over which data is compared to the specified threshold.
  metric_name         = "CPUUtilization" # metric is "CPUUtilization" in the "AWS/EC2" namespace
  namespace           = "AWS/EC2"
  period              = "60"
  statistic           = "Average"
  threshold           = "30" # The alarm is triggered if the average CPU utilization over a 60-second period is greater than or equal to 30%.

  # dimensions used to filter the metric
  dimensions = {
    AutoScalingGroupName = aws_autoscaling_group.mission_app_asg.name
  }

  alarm_description = "This metric monitors ec2 cpu utilization for Mission App ASG"
  alarm_actions     = [aws_autoscaling_policy.mission_app_scale_out_policy.arn]
}

resource "aws_autoscaling_policy" "mission_app_scale_in_policy" {
  name                   = "${var.namespace}-in-policy"
  scaling_adjustment     = -1 # scale in by reducing the desired capacity by one instance.
  adjustment_type        = "ChangeInCapacity" # adjusts the capacity by the specified number.
  cooldown               = 180 # Specifies the amount of time, in seconds, after a scaling activity completes before another can start.
  autoscaling_group_name = aws_autoscaling_group.mission_app_asg.name # Specifies the name of the Auto Scaling Group to which the policy is applied
}

# ASG Cloudwatch policy
resource "aws_cloudwatch_metric_alarm" "mission_app_scale_in_alarm" {
  alarm_name          = "${var.namespace}-in-alarm"
  comparison_operator = "LessThanOrEqualToThreshold"
  evaluation_periods  = "2" # Specifies the number of periods over which data is compared to the specified threshold
  metric_name         = "CPUUtilization" # metric is "CPUUtilization" in the "AWS/EC2" namespace
  namespace           = "AWS/EC2"
  period              = "60"
  statistic           = "Average"
  threshold           = "20"

  # dimensions for the alarm - Auto Scaling Group name as a dimension
  dimensions = {
    AutoScalingGroupName = aws_autoscaling_group.mission_app_asg.name
  }

  alarm_description = "This metric monitors ec2 cpu utilization for Mission App ASG"
  # alarm_actions: Specifies the actions to perform when the alarm changes state
  alarm_actions     = [aws_autoscaling_policy.mission_app_scale_in_policy.arn] # triggers the specified Auto Scaling Policy
}

// -------------------------------------------  ALB for wordpress application
# This section sets up an AWS Application Load Balancer with a target group, a listener
# that forwards traffic to the target group, and two listener rules.
#
# The rules handle redirection from CloudFront to HTTP and HTTP to HTTPS
# based on specified conditions

# Application Load Balancer: Create a application load balancer (ALB) for the Wordpress web application
resource "aws_lb" "mission_app" {
  name               = "${var.namespace}-alb"
  internal           = false
  load_balancer_type = "application"
  subnets            = values(aws_subnet.private)[*].id # Specifies the list of subnets in which the ALB will be deployed

  tags = {
    Name = "${var.namespace}-lb"
  }

  security_groups = [aws_security_group.app.id]
}

# Create a target group to attach the load balancer for the Wordpress web application
resource "aws_lb_target_group" "mission_app" {
  name     = "${var.namespace}-lb-tg"
  port     = 443
  protocol = "HTTPS"
  vpc_id   = aws_vpc.default.id

  # Configures session stickiness using the "lb_cookie" type with a cookie duration of 1800 seconds (30 minutes).
  stickiness {
    type            = "lb_cookie"
    cookie_duration = 1800
    enabled         = true
  }

  # Configures health checks for instances in the target group
  health_check {
    healthy_threshold   = 3
    unhealthy_threshold = 10
    timeout             = 5
    interval            = 10
    path                = "/"
    port                = 443
    protocol            = "HTTPS"
  }
}

# Create a load balancer listener for the ALB
resource "aws_lb_listener" "mission_app_http" {
  load_balancer_arn = aws_lb.mission_app.arn
  port              = "80"
  protocol          = "HTTP"

  # Configures the default action for the listener, forwarding traffic to the target group
  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.mission_app.arn
  }
}

# Redirect CloudFront to HTTP Rule
# This rule is designed to handle redirection when the incoming request is coming from CloudFront.
# The CloudFront distribution is expected to add a custom HTTP header named "X-Request," and the rule
# checks for the presence of this header to determine if the request originated from CloudFront.
# If the condition is met, the rule forwards the request to the target group.
resource "aws_lb_listener_rule" "redirect_cloudfront_to_http" {
  listener_arn = aws_lb_listener.mission_app_http.arn
  priority     = 100

  # Configures the action to forward traffic to the target group.
  action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.mission_app.arn
  }

  # Specifies a condition based on the value of the "X-Request" HTTP header.
  # This rule is checking for a specific header value associated with CloudFront
  condition {
    http_header {
      http_header_name = "X-Request"
      values           = [aws_lb.mission_app.dns_name]
    }
  }
}

# Redirect HTTP to HTTPS Rule
# This rule is designed to enforce HTTPS. If an incoming request is received over
# HTTP and matches the specified domain, it redirects the request to the same path
# but using the HTTPS protocol. This ensures that users are always accessing the
# application over a secure (encrypted) connection.
resource "aws_lb_listener_rule" "redirect_http_to_https" {
  listener_arn = aws_lb_listener.mission_app_http.arn
  priority     = 200

  # Configures the action to redirect traffic to HTTPS with a 301 status code.
  action {
    type = "redirect"

    # Specifies the details of the redirection, including the target port and protocol.
    redirect {
      port        = "443"
      protocol    = "HTTPS"
      status_code = "HTTP_301"
    }
  }

  # Specifies a condition based on the host header value, redirecting only requests to the specified domain
  condition {
    host_header {
      values = [aws_lb.mission_app.dns_name]
    }
  }
}

# Certificates
resource "tls_private_key" "mission_app" {
  algorithm = "RSA"
}

resource "tls_self_signed_cert" "mission_app" {
  private_key_pem = tls_private_key.mission_app.private_key_pem

  validity_period_hours = 8760

  # Generate a new certificate if Terraform is run within three
  # hours of the certificate's expiration time.
  early_renewal_hours = 3

  # Reasonable set of uses for a server SSL certificate.
  allowed_uses = [
    "key_encipherment",
    "digital_signature",
    "server_auth",
  ]

  dns_names = [
    aws_lb.mission_app.dns_name
  ]

  subject {
    common_name         = aws_lb.mission_app.dns_name
    organization        = "Amazon Web Services"
    organizational_unit = "WWPS ProServe"
    country             = "USA"
    locality            = "San Diego"
  }
}

resource "aws_acm_certificate" "mission_app_private" {
  private_key      = tls_private_key.mission_app.private_key_pem
  certificate_body = tls_self_signed_cert.mission_app.cert_pem
}

# Create a private S3 Bucket
resource "aws_s3_bucket" "mission_app" {
  bucket_prefix = "${var.namespace}-mission-app-"

  tags = {
    Description = "Used to store items"
  }
}

# Create a public access block which disable public access for the private S3 Bucket
resource "aws_s3_bucket_public_access_block" "mission_app" {
  bucket = aws_s3_bucket.mission_app.bucket

  block_public_acls       = true
  block_public_policy     = true
  ignore_public_acls      = true
  restrict_public_buckets = true
}

# An object of S3, typically represent data similar file
resource "aws_s3_object" "mission_app-private_key" {
  bucket = aws_s3_bucket.mission_app.bucket

  key     = "server.key"
  content = tls_private_key.mission_app.private_key_pem
}

resource "aws_s3_object" "mission_app-public_key" {
  bucket = aws_s3_bucket.mission_app.bucket

  key     = "server.crt"
  content = tls_self_signed_cert.mission_app.cert_pem
}

# Application Load Balancer (finishing touch)
resource "aws_lb_listener" "mission_app_https" {
  load_balancer_arn = aws_lb.mission_app.arn
  port              = "443"
  protocol          = "HTTPS"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.mission_app.arn
  }

  certificate_arn = aws_acm_certificate.mission_app_private.arn
}

#  Create a CloudFront distribution for the Wordpress web application
resource "aws_cloudfront_distribution" "mission_app" {
  enabled = true
  comment = "${var.namespace} - frontend site for mission app"

  origin {
    domain_name = aws_lb.mission_app.dns_name
    origin_id   = aws_lb.mission_app.dns_name
    custom_origin_config {
      http_port              = 80
      https_port             = 443
      origin_protocol_policy = "http-only"
      origin_ssl_protocols   = ["TLSv1.2"]
    }

    custom_header {
      name  = "X-Request"
      value = aws_lb.mission_app.dns_name
    }
  }

  default_cache_behavior {
    allowed_methods        = ["GET", "HEAD", "OPTIONS", "PUT", "POST", "PATCH", "DELETE"]
    cached_methods         = ["GET", "HEAD", "OPTIONS"]
    target_origin_id       = aws_lb.mission_app.dns_name
    viewer_protocol_policy = "redirect-to-https"
    cache_policy_id        = aws_cloudfront_cache_policy.default.id
  }

  # https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes
  restrictions {
    geo_restriction {
      restriction_type = "none"
      locations        = []
    }
  }

  viewer_certificate {
    cloudfront_default_certificate = true
  }
}

# Create a CloudFront cache policy for the Wordpress web application
resource "aws_cloudfront_cache_policy" "default" {
  name        = "${var.namespace}-default-policy"
  comment     = "Default Policy"
  default_ttl = 50
  max_ttl     = 100
  min_ttl     = 1

  parameters_in_cache_key_and_forwarded_to_origin {
    cookies_config {
      cookie_behavior = "all"
    }
    headers_config {
      header_behavior = "whitelist"
      headers {
        items = ["X-Request"]
      }
    }
    query_strings_config {
      query_string_behavior = "all"
    }
  }
}
