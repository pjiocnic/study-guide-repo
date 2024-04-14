

# Cold start measurements in cloudwatch insights

https://github.com/aws-samples/serverless-snippets/tree/main/cloudwatch-insight-lambda-cold-start-invocations-byfunction

```bash
filter @type = "REPORT" and @message like /(?i)(Init Duration)/
|parse @message /^REPORT.*Init Duration: (?<initDuration>.*) ms.*/
| parse @log /^.*/aws/lambda/(?<functionName>.*)/
| fields @memorySize / 1000000 as memorySize
| stats count() as coldStarts, avg(initDuration) as avgInitDuration, max(initDuration) as maxInitDuration
by functionName, memorySize
```

Switch to ARM architecture provided your lambda doesn't have any x86 dependencies


# References

1. [Lambda performance tuning | Serverless Office Hours](https://www.twitch.tv/videos/2068582604

