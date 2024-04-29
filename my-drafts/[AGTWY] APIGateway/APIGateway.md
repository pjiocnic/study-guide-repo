
# How does Throttling work?

## Region-wide Throttling

1. Account level settings per region
1. 10,000 requests/sec per account per region; 5,000 bursts - This means in the 1st millisec it can service upto 5,000 burst requests. However if 5,001 requests come in the 1st millisec, then 1 will be throttled. Also, if I get another 5,000 in the rest of the second, it will be handled as well since the cap is 10,000 requests/sec per account per region
1. 10,000 requests/sec per account per region; 5,000 bursts setting is shared across ALL APIs in this region.

## Method-Level Throttling

1. You can distribute 10,000 requests/sec per account per region; 5,000 bursts across different methods
1. The settings have to be whats set at account level ie cannot method level settings cannot override account level settings

## Client Level Throttling

1. This is regulated using the usage plan and API key ie if you have this API key you are regulated to this many calls

Also see - https://www.alexdebrie.com/posts/api-gateway-elements/#api-keys-and-usage-plans

# How to configure Throttling in API Gateway

https://www.youtube.com/watch?v=sCXejDwPGY4


# References
[AWS re:Invent 2019: [REPEAT 2] I didn’t know Amazon API Gateway did that (SVS212-R2) By Eric Johnson](https://youtu.be/yfJZc3sJZ8E?t=2010)
https://xebia.com/blog/aws-api-gateway-throttling-explained/
https://www.alexdebrie.com/posts/api-gateway-elements/#step-0-protecting-your-api-with-authorization-and-usage-plans