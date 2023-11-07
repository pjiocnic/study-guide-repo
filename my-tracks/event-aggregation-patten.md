https://aws.amazon.com/blogs/compute/orchestrating-dependent-file-uploads-with-aws-step-functions/

<img src="./images/aggregation-1.png" title="aggregation-1.png" width="900"/>

1. the `Warehouse file` must be processed before the `Sales file`.
2. However, its quite possible `Sales file` gets uploaded before `Warehouse file` ie no way to enforce the order
3. To solve this problem use `Aggregator pattern` - the pattern collects the both events (ie processed Warehouse file and the uploaded Sales file) and then triggers the next stage.

# References

1. [Integrating AWS Step Functions callbacks and external systems](https://aws.amazon.com/blogs/compute/integrating-aws-step-functions-callbacks-and-external-systems/)
1. [Orchestrating dependent file uploads with AWS Step Functions by Nelson Assis](https://aws.amazon.com/blogs/compute/orchestrating-dependent-file-uploads-with-aws-step-functions/)
1. [Moving to event-driven architectures with serverless event aggregators](https://aws.amazon.com/blogs/mt/moving-to-event-driven-architectures-with-serverless-event-aggregators/)