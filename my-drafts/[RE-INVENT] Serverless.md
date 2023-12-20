
1. Having higher memory allocated for HTTP calls from LAMBDA makes it run faster. This is because TLS encryption rate rfequires higher CPU

https://youtu.be/sdCA0Y7QDrM?t=1313

2. ARM based processors on Graviton2 gives upto 34% bettter price performance over x86 based Lambda

3. AWS Power tuning tools
- If I have **synchronous** calls then "high performance" is needed.  If I have a **asynchronous** call then **Lower costs** is what is sought.
- Run pwoer tunning both on X86 and Graviton and compare the costs (https://youtu.be/sdCA0Y7QDrM?t=1449)

4. AWS Compute Optimizer (https://youtu.be/sdCA0Y7QDrM?t=1479)
