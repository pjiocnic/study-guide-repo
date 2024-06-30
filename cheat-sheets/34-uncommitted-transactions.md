Uncommitted transactions in your WebLogic application consuming messages from WebSphere MQ and committing to Oracle Exadata can occur due to several reasons:

**Transaction Timeouts**: If a transaction takes longer than the configured timeout period, it can be left uncommitted. Ensure the transaction timeout settings in WebLogic and MQ are adequately configured.

**Application Logic Issues**: If the application has logic that does not always complete transactions properly (e.g., exceptions not being caught correctly), this can leave transactions in an uncommitted state. Review your application code to ensure all transactions are correctly handled.

**Connection Issues**: Network or connection problems between WebLogic, WebSphere MQ, and Oracle Exadata can disrupt transactions. Ensure all network connections are stable and reliable.

**Resource Availability**: If the Oracle Exadata database or the WebSphere MQ queue manager is under heavy load or experiencing resource constraints, this could prevent transactions from committing. Monitor and optimize the performance of these systems.

**Configuration Mismatches**: Mismatched or incorrect configurations between WebLogic, WebSphere MQ, and Oracle Exadata can lead to transactional inconsistencies. Double-check all relevant configurations.

**XA Transaction Issues**: Since you are using a two-phase commit (XA transactions), ensure that the XA configurations are correctly set up in WebLogic, WebSphere MQ, and Oracle Exadata.

**Steps to Troubleshoot and Fix the Issue**:
Check and Increase Transaction Timeout: Ensure that the transaction timeout is set to a reasonable value in WebLogic and MQ.

**For WebLogic**: TransactionTimeoutSeconds and TimeoutSeconds parameters.

**For MQ**: MAXACT (maximum number of concurrent transactions) and MAXTPH (maximum transactions per hour).

**Review and Enhance Application Code**: Ensure that your application logic is robust, handling all possible exceptions, and committing or rolling back transactions appropriately.

**Monitor and Improve System Performance**: Use monitoring tools to check the load on Oracle Exadata and WebSphere MQ. Ensure there are no performance bottlenecks.

**Verify and Align Configuration Settings**: Check all relevant configuration settings across WebLogic, WebSphere MQ, and Oracle Exadata to ensure they are correct and compatible.

**Enable and Review Logs**: Enable detailed logging in WebLogic, WebSphere MQ, and Oracle Exadata. Review the logs to identify any specific issues or patterns causing the uncommitted transactions.

**Test XA Transaction Flow**: Ensure that the XA transaction flow between WebLogic, WebSphere MQ, and Oracle Exadata is working correctly. Use test scripts or a test environment to simulate and diagnose issues.

By addressing these areas, you should be able to identify the root cause of the uncommitted transactions and implement appropriate fixes.