so I have a organization with a hub and spoke accounts. I also created another account outside my organization to simulate on-prem account. GIven this how can I use RAM to share the inbound and outbount endpoints deployed in my shared account (the hub) with my spoke account using AWS RAM and PHZ. Can you give me a step by step demo along with CLI commands and how to verify in console?

Sure, I can provide you with a step-by-step demo on how to share the inbound and outbound Route 53 Resolver endpoints deployed in your hub account with your spoke account using AWS Resource Access Manager (RAM) and private hosted zones (PHZs). I'll include the necessary AWS CLI commands and instructions on how to verify the configuration in the AWS Management Console. [1]

Before we begin, please note that Route 53 Resolver endpoints (inbound and outbound) can only be shared with accounts within the same AWS Organization. Since you mentioned that you have an account outside your organization to simulate an on-premises environment, we'll focus on sharing the endpoints with the spoke account within your organization. [2]

Here are the steps:

**1. Set up the environment**

Ensure that you have the following resources in place:

* An AWS Organization with a hub account and at least one spoke account

* Route 53 Resolver inbound and outbound endpoints created in the hub account

* A private hosted zone (PHZ) created in the hub account

**2. Create a resource share in the hub account**

In the hub account, create a resource share using the AWS CLI: [3]

```bash
aws ram create-resource-share --name MyResolverEndpointsShare \
--allowed-principals '{"AWS":"arn:aws:organizations::<org-id>:account/ou/<ou-id>/<spoke-account-id>"}' \
--resource-arns "arn:aws:route53-resolver::<hub-account-id>:resolver-endpoint/<inbound-endpoint-id>" "arn:aws:route53-resolver::<hub-account-id>:resolver-endpoint/<outbound-endpoint-id>"
```

Replace the placeholders with your actual values:

* <org-id>: Your AWS Organization ID

* <ou-id>: The ID of the Organizational Unit (OU) where your spoke account resides (optional if the account is not part of an OU)

* <spoke-account-id>: The AWS account ID of your spoke account

* <hub-account-id>: The AWS account ID of your hub account

* <inbound-endpoint-id>: The ID of the inbound Route 53 Resolver endpoint in the hub account

* <outbound-endpoint-id>: The ID of the outbound Route 53 Resolver endpoint in the hub account

**3. Accept the resource share in the spoke account**

In the spoke account, accept the resource share created in the previous step:

```bash
aws ram get-resource-share-invitations --resource-share-arn "arn:aws:ram::<hub-account-id>:resource-share/<resource-share-id>"
```

Replace <hub-account-id> with the hub account ID and <resource-share-id> with the ID of the resource share you created.

Note the resourceShareInvitationArn from the output.

```bash
aws ram accept-resource-share-invitation --resource-share-invitation-arn "<resourceShareInvitationArn>"
```

**4. Associate the shared endpoints with the PHZ in the spoke account**

In the spoke account, associate the shared inbound and outbound endpoints with the private hosted zone (PHZ) that you want to use for DNS resolution:

```bash
aws route53 associate-resolver-endpoint-ip-address --resolver-endpoint-id "<inbound-endpoint-id>" --ip-address "<ip-address>" --direction INBOUND

aws route53 associate-resolver-endpoint-ip-address --resolver-endpoint-id "<outbound-endpoint-id>" --ip-address "<ip-address>" --direction OUTBOUND
```

Replace <inbound-endpoint-id> and <outbound-endpoint-id> with the respective endpoint IDs shared from the hub account. Replace <ip-address> with the IP address of the PHZ in the spoke account.

**5. Verify the configuration in the AWS Management Console**

In the AWS Management Console, navigate to the Route 53 service in the spoke account. You should see the shared inbound and outbound endpoints listed under the "Resolver endpoints" section.

Additionally, you can navigate to the "Hosted zones" section and select the PHZ you associated with the endpoints. Under the "Resolver endpoints" tab, you should see the shared inbound and outbound endpoints listed and associated with the PHZ.

By following these steps, you have successfully shared the Route 53 Resolver inbound and outbound endpoints from your hub account with your spoke account using AWS RAM. The spoke account can now use these shared endpoints for DNS resolution, forwarding queries to and from the on-premises environment (simulated by the account outside your organization) through the hub account's endpoints.

Please note that you'll need to configure the appropriate resolver rules and forwarding behavior in the hub account to ensure that DNS queries are routed correctly between the spoke account, the hub account, and the on-premises environment.

**Sources**
[1] Using Amazon Route 53 Profiles for scalable multi-account AWS environments | Networking & Content Delivery
aws.amazon.comblogsnetworking-and-content-deliveryusing-amazon-route-53-profiles-for-scalable-multi-account-aws-environments
[2] Shareable AWS resources - AWS Resource Access Manager
docs.aws.amazon.comramlatestshareable.html
[3] Share Amazon Route 53 Application Recovery Controller clusters across multiple AWS accounts
aws.amazon.comabout-awswhats-newshare-amazon-route-53-application-recovery-controller-clusters-multiple-aws-accounts


# Testing this setup

To test the setup where you have shared Route 53 Resolver inbound and outbound endpoints from the hub account to the spoke account, you can follow these steps:

**1. Test DNS resolution within the spoke account's VPC**

Launch an EC2 instance in the spoke account's VPC where you associated the shared endpoints with the private hosted zone (PHZ). From this EC2 instance, try to resolve domain names that should be handled by the different components of the setup:

* Domains within the PHZ: These should resolve correctly using the shared endpoints and the PHZ.

* Domains that should be forwarded to the on-premises DNS server: These should resolve correctly if the resolver rules in the hub account are configured properly to forward the queries to the on-premises DNS server via the outbound endpoint.

* Public domains: These should resolve correctly using the shared endpoints and the internet DNS servers.

You can use tools like nslookup or dig to test the DNS resolution from the EC2 instance.

**2. Test DNS resolution from the on-premises environment**

If you have access to resources in the simulated on-premises environment (the account outside your organization), you can test DNS resolution from there as well. Try to resolve domain names that should be handled by the Route 53 Resolver in the hub account's VPC.

**3. Verify resolver query logs**

In the hub account, enable query logging for the inbound and outbound endpoints. This will allow you to view the DNS queries being received and forwarded by the endpoints. You can then cross-reference these logs with the DNS queries you made from the spoke account's VPC and the on-premises environment to ensure that the queries are being handled correctly.

**4. Check resolver endpoint monitoring**

Route 53 Resolver provides monitoring capabilities for inbound and outbound endpoints. In the hub account, check the monitoring status of the endpoints to ensure that they are healthy and functioning correctly.

**5. Test failover scenarios**

If you have multiple on-premises DNS servers or multiple outbound endpoints configured, you can simulate failover scenarios by disabling or stopping one of the components and testing if DNS resolution continues to work as expected.

**6. Review VPC flow logs**

Enable VPC flow logs in the spoke account's VPC and review the logs to verify that DNS traffic is being routed correctly between the VPC and the shared endpoints in the hub account.

**7. Check routing tables and security groups**

Ensure that the routing tables and security groups in the spoke account's VPC are configured correctly to allow DNS traffic to flow between the VPC and the shared endpoints in the hub account.

By performing these tests, you can validate the end-to-end DNS resolution process and ensure that the shared Route 53 Resolver endpoints are functioning as expected, allowing seamless DNS resolution between the spoke account's VPC, the hub account, and the simulated on-premises environment.