1. What are the advantages of a multi-Region active-active architecture?

Multi-Region active-active architectures are deployments of applications that run in two or more AWS Regions that are geographically separate. Both (or all) Regions have all the required components and data for your application, and actively serve requests, based on end-user proximity. When your application is impaired in one Region, the other Region can automatically take over user traffic, without any downtime for your global users.

src: https://aws.amazon.com/blogs/networking-and-content-delivery/latency-based-routing-leveraging-amazon-cloudfront-for-a-multi-region-active-active-architecture/

2. When should you consider adopting a multi-Region active-active architecture?

You should consider a multi-Region active-active architecture only if your application’s unit of failure is at an AWS Regional level. With an active-active architecture, carefully weigh the potential for higher costs and increased complexity. For stateful applications that require cross-Region data replication, you should also factor in some data inconsistency, higher latency, and slower performance.

src: https://aws.amazon.com/blogs/networking-and-content-delivery/latency-based-routing-leveraging-amazon-cloudfront-for-a-multi-region-active-active-architecture/

3. When should you consider Active-Active architecture?

**Stringent** recovery time objectives (RTO)/ recovery point objectives (RPO) requirements: One reason that you might choose to use an active-active architecture is to meet stringent SLA requirements that can’t be addressed by using other disaster recovery (DR) strategies, such as active-passive, warm-standby, or pilot-light configurations.

4. What're the other types of DR architectures?

active-passive, warm-standby, or pilot-light configurations


