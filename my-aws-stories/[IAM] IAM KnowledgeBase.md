
# AssumingRole

1. How to assume a role from command line?
- https://learning.oreilly.com/library/view/aws-cookbook/9781492092599/ch01.html#introduction-id0000012

# Permission Boundaries

1. How does permission bourndy work with and example
- [1.5 Delegating IAM Administrative Capabilities Using Permissions Boundaries](https://learning.oreilly.com/library/view/aws-cookbook/9781492092599/ch01.html#introduction-id0000012)

# SSM

1. How to connect to ec2 using ssm
- [1.6 Connecting to EC2 Instances Using AWS SSM Session Manager](https://learning.oreilly.com/library/view/aws-cookbook/9781492092599/ch01.html#introduction-id0000012)

# Topics not completed

1. Policy Types:
Deploy the pipeline template, investigate and Write notes -
`my-github/study-guide-repo/my-pet-projects/iam/[TBC] how-and-when-to-use-aws-iam-policy-blog-samples`
- Talks about Temporary elevated access.  See how to do it in real world.
- Findout who creates CloudCentralTeam, Application Team and the data lake team.

2. Trust policies:
Read this article and execute samples - `/Volumes/Lexar/git-repos/aws-repo/my-github/study-guide-repo/my-drafts/[IAM] IAM/blog-notes/how-to-use-trust-policies-with-iam-roles/how-to-use-trust-policies-with-iam-roles.md`

3. IAM Paths

```json
  "Condition": {
      "ArnLike": {
        "aws:PrincipalArn": "arn:aws:iam::111122223333:role/OpsRoles/*"
      }
  }
```