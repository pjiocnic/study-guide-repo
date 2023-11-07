

# Call Backs

2 scenarios

1. If the target is an AWS service like SQS or SNS, then use SQS and Amazon SNS integrations to forward a token to that system for processing.
1. If the target is in another AWS account or on another platform, then use a Lambda function to to broker the call to that service.

If the target **external system** is also running on AWS, the SQS and Amazon SNS integrations enable forwarding of the token to that system for processing. However, these services may be in other AWS accounts or hosted on another platform. In this case, you can use a Lambda function to broker the call to that service.

# References

1. https://aws.amazon.com/blogs/compute/integrating-aws-step-functions-callbacks-and-external-systems/?ref=serverlessland