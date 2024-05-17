
[How to use trust policies with IAM roles by Jonathan Jenkyn and Liam Wadman](https://aws.amazon.com/blogs/security/how-to-use-trust-policies-with-iam-roles/)
[AssumeRole with External-Id - 1-Minute AWS IAM Lesson](https://www.youtube.com/watch?v=DLVlW3dOJww)
1. [[**MY_NEXT**] How do I assume an IAM role using the AWS CLI?](https://repost.aws/knowledge-center/iam-assume-role-cli)

A trust policy is a specific type of **resource-based policy** for IAM roles

# AssumeRole

A common use case is when you need to provide access to a **role in account A** to assume a **role in Account B**.

To facilitate this, you add an entry in the role in account B’s trust policy that allows authenticated principals from account A to assume the role through the sts:AssumeRole API call

<img src="./images/trust-policy-assumeRole-1.png" title="trust-policy-assumeRole-1.png" width="900"/>

<img src="./images/trust-policy-assumeRole-2.png" title="trust-policy-assumeRole-2.png" width="900"/>