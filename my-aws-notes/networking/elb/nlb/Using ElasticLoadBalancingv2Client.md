

Yes, using the ElasticLoadBalancingV2Client from the AWS SDK for Java is a good approach, especially if you need to interact with the Network Load Balancer itself or if you want to leverage AWS-specific features. This client provides more integration with AWS services and can be particularly useful if you need to perform operations related to the load balancer configuration or if you're working within the AWS ecosystem. [1]

Here's how you can use ElasticLoadBalancingV2Client in your Java Lambda function:

First, add the following dependency to your pom.xml:

```xml
<dependency>
    <groupId>software.amazon.awssdk</groupId>
    <artifactId>elasticloadbalancingv2</artifactId>
    <version>2.20.26</version>
</dependency>
```

Now, you can use the ElasticLoadBalancingV2Client in your Lambda function like this:

```java
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.elasticloadbalancingv2.ElasticLoadBalancingV2Client;
import software.amazon.awssdk.services.elasticloadbalancingv2.model.DescribeTargetHealthRequest;
import software.amazon.awssdk.services.elasticloadbalancingv2.model.DescribeTargetHealthResponse;
import software.amazon.awssdk.services.elasticloadbalancingv2.model.TargetHealthDescription;

public class MyLambdaFunction implements RequestHandler<Object, String> {

    private final ElasticLoadBalancingV2Client elbv2Client;
    private static final String TARGET_GROUP_ARN = System.getenv("TARGET_GROUP_ARN");

    public MyLambdaFunction() {
        this.elbv2Client = ElasticLoadBalancingV2Client.builder()
                .region(Region.of(System.getenv("AWS_REGION")))
                .build();
    }

    @Override
    public String handleRequest(Object input, Context context) {
        try {
            DescribeTargetHealthRequest request = DescribeTargetHealthRequest.builder()
                    .targetGroupArn(TARGET_GROUP_ARN)
                    .build();

            DescribeTargetHealthResponse response = elbv2Client.describeTargetHealth(request);

            StringBuilder result = new StringBuilder();
            for (TargetHealthDescription healthDescription : response.targetHealthDescriptions()) {
                result.append("Target: ").append(healthDescription.target().id())
                      .append(", Health: ").append(healthDescription.targetHealth().state())
                      .append("\n");
            }

            return result.toString();
        } catch (Exception e) {
            context.getLogger().log("Error: " + e.getMessage());
            return "Error occurred: " + e.getMessage();
        }
    }
}
```

In this example:

We're creating an ElasticLoadBalancingV2Client in the constructor. The AWS region is fetched from an environment variable.

The handleRequest method describes the health of targets in a specified target group.

The TARGET_GROUP_ARN is stored as an environment variable. You should set this in your Lambda function's configuration.

This approach is particularly useful for operations related to the load balancer itself, such as:

Describing load balancers

Modifying load balancer attributes

Managing target groups

Checking target health

However, if you simply need to make HTTP requests to your application behind the NLB, using a standard HTTP client (as in the previous answer) might be more straightforward. The ElasticLoadBalancingV2Client is more about managing and interacting with the load balancer configuration, not for making application-level requests through the load balancer. [2]

If you do need to make HTTP requests to your application, you would still need to use an HTTP client like Apache HttpClient or Java's built-in HttpURLConnection, using the NLB's DNS name as the host.

Remember to ensure your Lambda function has the necessary IAM permissions to interact with the Elastic Load Balancing service if you're using the ElasticLoadBalancingV2Client.

# -----------------------------------------------------------

Q: now NLBs should be deployed in 2 AZs. correct? How does ElasticLoadBalancingV2Client round robin?

Ans:

You're correct that it's a best practice to deploy Network Load Balancers (NLBs) across multiple Availability Zones (AZs) for high availability and fault tolerance. AWS recommends using at least two AZs for your NLB to ensure continued operation if one AZ becomes unavailable. [1]

Regarding how ElasticLoadBalancingV2Client handles load balancing: [2]

The ElasticLoadBalancingV2Client itself doesn't actually perform the load balancing or round-robin distribution. It's an API client for managing and interacting with Elastic Load Balancing resources, not for routing traffic.

The actual traffic distribution is handled by the NLB itself, not the client. The NLB uses a flow hash algorithm to distribute connections across targets.

By default, each NLB node distributes traffic only to targets within its Availability Zone. This is to minimize cross-AZ data transfer costs and reduce latency. [3]

If you want to distribute traffic across all AZs, you need to enable cross-zone load balancing. When enabled, each load balancer node distributes traffic across the registered targets in all enabled Availability Zones.

The NLB doesn't use a simple round-robin algorithm. Instead, it uses a flow hash algorithm that takes into account the protocol, source IP address, source port, destination IP address, destination port, and TCP sequence number (for TCP flows).

This flow hash algorithm aims to distribute new TCP connections or UDP flows evenly across the healthy targets, while ensuring that all packets for a specific connection or flow are directed to the same target.

To enable cross-zone load balancing using the ElasticLoadBalancingV2Client, you can use the modifyLoadBalancerAttributes method:

```java
import software.amazon.awssdk.services.elasticloadbalancingv2.ElasticLoadBalancingV2Client;
import software.amazon.awssdk.services.elasticloadbalancingv2.model.LoadBalancerAttribute;
import software.amazon.awssdk.services.elasticloadbalancingv2.model.ModifyLoadBalancerAttributesRequest;


public class EnableCrossZoneLoadBalancing {
    public static void enableCrossZoneLoadBalancing(String loadBalancerArn) {
        ElasticLoadBalancingV2Client elbv2Client = ElasticLoadBalancingV2Client.create();

        LoadBalancerAttribute attribute = LoadBalancerAttribute.builder()
            .key("load_balancing.cross_zone.enabled")
            .value("true")
            .build();

        ModifyLoadBalancerAttributesRequest request = ModifyLoadBalancerAttributesRequest.builder()
            .loadBalancerArn(loadBalancerArn)
            .attributes(attribute)
            .build();

        elbv2Client.modifyLoadBalancerAttributes(request);
    }
}
```

It's important to note:

Cross-zone load balancing for NLB is disabled by default and incurs additional charges for inter-AZ data transfer when enabled.

Even with cross-zone load balancing enabled, the NLB still uses its flow hash algorithm, not a simple round-robin.

For TCP and TLS listeners, the NLB routes each individual TCP connection to a single target for the life of the connection.

For UDP listeners, the NLB attempts to route packets for a single flow to the same target, but this is not guaranteed due to the connectionless nature of UDP.

When designing your application, consider these behaviors to ensure your system can handle the distribution of traffic effectively across your targets in multiple AZs.

**sources**

1. [Elastic Load Balancer: Maximizing Benefits and Keeping Costs Low by Philippe Lantin, Abel Cruz, and Avinash Kolluri](https://aws.amazon.com/blogs/networking-and-content-delivery/elb-maximizing-benefits-and-keeping-costs-low/)
1. [Elastic Load Balancer - Part1 by Utkarsh Rastogi](https://awslearner.hashnode.dev/elastic-load-balancer-part1)
1. [Elastic Load Balancer - Part2 by Utkarsh Rastogi](https://awslearner.hashnode.dev/elastic-load-balancer-part2)
1. [nlb-cross-zone-load-balancing-enabled - Checks if cross-zone load balancing is enabled on Network Load Balancers (NLBs). The rule is NON_COMPLIANT if cross-zone load balancing is not enabled for an NLB.](https://docs.aws.amazon.com/config/latest/developerguide/nlb-cross-zone-load-balancing-enabled.html)
- https://docs.aws.amazon.com/config/latest/developerguide/aws-config-managed-rules-cloudformation-templates.html