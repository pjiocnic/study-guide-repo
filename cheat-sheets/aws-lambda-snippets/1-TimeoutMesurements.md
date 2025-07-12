[How do I use CloudWatch Logs to determine if my Lambda function timed out?](https://repost.aws/knowledge-center/lambda-verify-invocation-timeouts)

### Query for Log Insights
```
fields @timestamp, @requestId, @message, @logStream| filter @message like "Task timed out"
| sort @timestamp desc
| limit 100
```

## Additional References

1. [CloudWatch Logs Insights language query syntax](https://docs.aws.amazon.com/AmazonCloudWatch/latest/logs/CWL_QuerySyntax.html)
1. [How do I determine if my AWS Lambda function is timing out?](https://www.youtube.com/watch?v=ULDmHcqijA0)