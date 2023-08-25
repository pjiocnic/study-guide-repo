# 08252023

# 8. Questions

1. How do you use connection pooling inside a Lambda?
2. Any cloudformation templates to create MongoDB in AWS

Links to CDk and Cloudformation templates please

https://www.mongodb.com/developer/code-examples/bash/get-started-atlas-aws-cloudformation/
https://www.mongodb.com/docs/atlas/manage-connections-aws-lambda/

https://www.mongodb.com/blog/post/atlas-integrations-aws-cloud-formation-cdk-now-generally-available
https://github.com/mongodb/awscdk-resources-mongodbatlas/tree/main/examples/mern-cdk-ci-cd
https://github.com/mongodb/awscdk-resources-mongodbatlas
https://github.com/mongodb/awscdk-resources-mongodbatlas/tree/main/examples/mern-cdk-ci-cd

https://www.mongodb.com/cloud/atlas/register

i want to use schema validation with polymorphic documents. Any examples how to go about?
A: Yes, setting up schema validators for schema governance is definitely recommended even for polymorphic documents. Here is some information about schema validators: https://www.mongodb.com/docs/manual/core/schema-validation/

Soheyl Rafi and Bobby

Q. jumping ahead - i'm coming from oracle and was wondering if its possible do vpd at the document and fields. I want to secure Personal Information (PII)
A: Atlas clusters are deployed in VPCs in the cloud. You can then use VPC peering to connect your application to Atlas clusters. There are many ways to secure PII, including BYOK, client side field level encryption, RBAC, and more.

Q: trying to secure at rest. So, User B should not be able to SEE User A's data
A: For this, we have granular, collection-level role based access controls within Atlas.
A: RBAC: https://www.mongodb.com/docs/manual/core/authorization/#:~:text=MongoDB%20employs%20Role%2DBased%20Access,no%20access%20to%20the%20system.

Q: is the vpc peering automatically setup when integrating from aws. Any docs that walk thru?
A: https://www.mongodb.com/docs/atlas/security-vpc-peering/