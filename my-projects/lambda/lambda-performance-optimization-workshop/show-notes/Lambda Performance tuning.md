<h1>[Lambda performance tuning | Serverless Office Hours](https://www.twitch.tv/videos/2068582604)</h1>

https://community.aws/content/2e2OlZen47W4OziSbu1on004vFI/improved-lambda-warm-start-speed-95
https://github.com/aws-samples/optimizations-for-lambda-functions


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

