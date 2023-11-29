TD;LR

1. Difference between Resource Policy and Identity-based policy


To grant permissions, you use **resource-based policies** (such as S3 bucket policies) or **identity-based policies** (such as **managed** or **in-line** permissions policies).

## Why is this needed

## How is it done by central administrator

# Process

**Basic Premise:** To limit access, the central administrator can attach a condition to the `developer’s identity policy` that helps ensure that the developer `can only create a role` if the `role has a permissions boundary policy attached to it`.

<img src="./images/15.1.png" title="15.1.png" width="900"/>

1. The central IAM team adds a condition to the developer’s IAM policy that allows the developer to create a role only if a permissions boundary is attached to the role.
2. The developer creates a role with accompanying permissions to allow access to an application’s Amazon S3 bucket and DynamoDB table. As part of this step, the developer also attaches a permissions boundary that defines the maximum permissions for the role.
3. Resource access is granted to the application’s resources.
4. Resource access is denied to the sensitive S3 bucket.

# Sample Permission Policy to attach to developer

```json
{
  "Version": "2012-10-17",
  "Statement": [
    { // The central administrator can use the following policy sample for your developers to allow the creation of
      // roles only if a permissions boundary is attached to them
      "Sid": "AllowRoleCreationWithAttachedPermissionsBoundary",
      "Effect": "Allow",
      "Action": "iam:CreateRole",
      "Resource": "*",
      "Condition": {
        "StringEquals": {
          "iam:PermissionsBoundary": "arn:aws:iam::<YourAccount_ID>:policy/<DevelopersPermissionsBoundary>"
        }
      }
    },
    {   // The central administrator can deny deletion of a permissions boundary
      "Sid": "DenyPermissionsBoundaryDeletion",
      "Effect": "Deny",
      "Action": "iam:DeleteRolePermissionsBoundary",
      "Resource": "*",
      "Condition": {
        "StringEquals": {
          "iam:PermissionsBoundary": "arn:aws:iam::<YourAccount_ID>:policy/<DevelopersPermissionsBoundary>"
        }
      }
    },
    { // The central administrator can further prevent detaching, modifying, or deleting the policy that is your permissions boundary
      "Sid": "DenyPolicyChange",
      "Effect": "Deny",
      "Action": [
        "iam:CreatePolicyVersion",
        "iam:DeletePolicyVersion",
        "iam:DetachRolePolicy",
        "iam:SetDefaultPolicyVersion"
      ],
      "Resource": "arn:aws:iam::<YourAccount_ID>:policy/<DevelopersPermissionsBoundary>"
    }
  ]
}
```

# References
1. https://aws.amazon.com/blogs/security/when-and-where-to-use-iam-permissions-boundaries/