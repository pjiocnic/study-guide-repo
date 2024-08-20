<h1>IAM Paths</h1>

# Important considerations for IAM paths

1.  IAM paths aren’t added to the uniqueness of the role name. Role names must be unique
    within your account without the path taken into consideration.
2.  You can only create IAM paths using AWS API or command line tools. You cannot create
    IAM paths with the AWS console.
3.  AWS reserves several paths including /aws-service-role/ and you cannot create roles in this path.


1. Give some Examples of IAM paths

-  `arn:aws:iam::444455556666:role/teamA/<role-name>`
-  `arn:aws:iam::444455556666:policy/teamA/<policy-name>`

2. what is the benefit of using IAM Paths?

Helps organize

```bash
arn:aws:iam::444455556666:policy/security/universal-security-readonly-policy
arn:aws:iam::444455556666:policy/teamA/DBA-policy-A
arn:aws:iam::444455556666:policy/teamB/DBA-policy-B
```

1. How do you allow Team A to create Policies and Roles under a certain path?

-  `arn:aws:iam::444455556666:role/teamA/<role-name>`
- ` arn:aws:iam::444455556666:policy/teamA/<policy-name>`

2. [BUCKeT-POLICY] how do you allow ONLY teamA to access a bucket?

OR

How to grant access to groups of roles to a resource?

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": "s3:*",
      "Effect": "Deny",
      "Resource": [
          "arn:aws:s3:::EXAMPLE-BUCKET",
          "arn:aws:s3:::EXAMPLE-BUCKET/*"
      ],
      "Principal": "*",
      "Condition": {
        "ArnNotLike": {
          "aws:PrincipalArn": "arn:aws:iam::*:role/teamA/*"
        }
      }
    }
  ]
}
```

3. How to make Teams use only Roles that belong to them and not try using roles meant for others

For example, following four path-based policiestcan be attached to each team’s **CI/CD pipeline role**, which has permissions to create roles.

* Policy 1 - Create a role in the path and modify inline policies on the role:  Allow creating roles and maintain them **ONLY** under a **certain path**

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
        "Effect": "Allow",
        "Action": [
            "iam:CreateRole",
            "iam:PutRolePolicy",
            "iam:DeleteRolePolicy"
        ],
        "Resource": "arn:aws:iam::444455556666:role/teamA/*"  // This restricts the actions to only affect IAM roles within the teamA path in the specified AWS account (444455556666).
    }
  ]
}
```

* Policy 2 - Allow To Add and remove managed policies - This policy allows attaching and detaching only those managed policies that are within the teamA path to/from roles within the same teamA path, all within the AWS account 444455556666.

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
        "Effect": "Allow",
        "Action": [
            "iam:AttachRolePolicy",
            "iam:DetachRolePolicy"
        ],
        "Resource": "arn:aws:iam::444455556666:role/teamA/*",
        "Condition": {
            "ArnLike": {
                "iam:PolicyARN": "arn:aws:iam::444455556666:policy/teamA/*"
            }
        }
    }
  ]
}
```

* Policy 3: Allow principal to Delete roles, tag and untag roles, read roles

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
        "Effect": "Allow",
        "Action": [
            "iam:DeleteRole",
            "iam:TagRole",
            "iam:UntagRole",
            "iam:GetRole",
            "iam:GetRolePolicy"
        ],
        "Resource": "arn:aws:iam::444455556666:role/teamA/*"
    }
  ]
}
```

* Policy 4: Allow allows access to create, modify, get, and delete policies that are created in the /teamA/ path

```json
{
  "Version": "2012-10-17",
  "Statement": [
  {
    "Effect": "Allow",
    "Action": [
        "iam:CreatePolicy",
        "iam:DeletePolicy",
        "iam:CreatePolicyVersion",
        "iam:DeletePolicyVersion",
        "iam:GetPolicy",
        "iam:TagPolicy",
        "iam:UntagPolicy",
        "iam:SetDefaultPolicyVersion",
        "iam:ListPolicyVersions"
      ],
    "Resource": "arn:aws:iam::444455556666:policy/teamA/*"
  }
  ]
}
```

4. CLI commmands to create a path

```bash
aws iam create-role \
  --role-name <ROLE-NAME> \
  --assume-role-policy-document \
  file://assume-role-doc.json \
  --path <PATH>
```

```bash
aws iam create-policy \
  --policy-name <POLICY-NAME> \
  --path <PATH> \
  --policy-document file://policy.json
```

5. How can I grant access to specific paths

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": "s3:*",
      "Effect": "Deny",
      "Resource": [
    "arn:aws:s3:::EXAMPLE-BUCKET",
    "arn:aws:s3:::EXAMPLE-BUCKET/*"
    ],
      "Principal": "*",
"Condition": {
        "ArnNotLike": {
          "aws:PrincipalArn": "arn:aws:iam::*:role/teamA/*"
        }
      }
   }
  ]
}
```

6. How can the security team allow Team A to add IAM roles to an instance profile
   and attach it to Amazon EC2 instances, only if the roles are in the `/teamA/ path`?

   OR

   How do you restrict Team A to only be able to pass only roles created by them to the AWS service?

```json
   {
    "Version": "2012-10-17",
    "Statement": [{
        "Effect": "Allow",
        "Action": "iam:PassRole",
        "Resource": "arn:aws:iam::444455556666:role/teamA/*"
    }]
   }
```

By using above policy Team A cannot pass Adminsitrative access type of policies

7. How do you prevent anyone but the `IAMAdministrator Role` (ie preventative guardrails) from modifying specific (like security) IAM paths?

You can create following as an SCP.

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
      "Sid": "RestrictIAMWithPathManagement",
            "Effect": "Deny",
            "Action": [
                "iam:AttachRolePolicy",
                "iam:CreateRole",
                "iam:DeleteRole",
                "iam:DeleteRolePermissionsBoundary",
                "iam:DeleteRolePolicy",
                "iam:DetachRolePolicy",
                "iam:PutRolePermissionsBoundary",
                "iam:PutRolePolicy",
                "iam:UpdateRole",
                "iam:UpdateAssumeRolePolicy",
                "iam:UpdateRoleDescription",
                "sts:AssumeRole",
                "iam:TagRole",
                "iam:UntagRole"
            ],
            "Resource": [
                "arn:aws:iam::*:role/security/* "
            ],
            "Condition": {
                "ArnNotLike": {
                    "aws:PrincipalARN": "arn:aws:iam::444455556666:role/security/IAMAdministrator"
                }
            }
        }
    ]
}
```

8. How can you allow certain actions (like closing accounts) only by certain team?

The following will only allow members of security team to close accounts

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "PreventCloseAccount",
      "Effect": "Deny",
      "Action": "organizations:CloseAccount",
      "Resource": "*",
      "Condition": {
        "ArnNotLikeIfExists": {
          "aws:PrincipalArn": [
            "arn:aws:iam::*:role/security/*"
          ]
        }
      }
    }
  ]
}
```

# References

1. [Optimize AWS administration with IAM paths by David Rowe and Valentine Reid ](https://aws.amazon.com/blogs/security/optimize-aws-administration-with-iam-paths/)