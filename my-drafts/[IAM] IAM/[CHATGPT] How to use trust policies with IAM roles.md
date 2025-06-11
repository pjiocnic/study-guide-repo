1. What does this statement mean? can you give examples? "If an IAM role has a principal from the same account in its trust policy directly, that principal doesn’t need an explicit entitlement in its identity-attached policy to assume the role"

Quote from src: https://aws.amazon.com/blogs/security/how-to-use-trust-policies-with-iam-roles/

The statement refers to a specific behavior in AWS Identity and Access Management (IAM) concerning how roles and policies work within the same AWS account.

Here's a breakdown of what it means:

# Explanation
In AWS IAM, there are two types of policies involved when one entity (like a user or a role) assumes another role:

**Trust Policy (Role Trust Relationship)**: This policy specifies who (which principals) is allowed to assume the role.
**Identity-Attached Policy (Permission Policy)**: This policy specifies what actions the principal (like a user or a role) is allowed to perform.

When the statement says "If an IAM role has a principal from the same account in its trust policy directly," it means that the role's trust policy explicitly names a principal (like an IAM user or another role) from the same AWS account. In such cases, the named principal does not need additional permissions in its own identity-attached policy to assume the role.

# Example 1: Trust Policy with a Principal from the Same Account

Consider the following scenario:

**Role Name:** ExampleRole
**IAM User Name:** UserA

Trust Policy of ExampleRole:
```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "AWS": "arn:aws:iam::123456789012:user/UserA"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}

```

In this trust policy, the principal `arn:aws:iam::123456789012:user/UserA` (which is an IAM user in the same account) is allowed to assume ExampleRole.

# Identity-Attached Policy of UserA

Because UserA is explicitly listed in the trust policy of ExampleRole, UserA does not need a specific statement in its own identity-attached policy to allow the **sts:AssumeRole** action. UserA can assume ExampleRole based on the trust policy alone.

# Example 2: Trust Policy with a Role from the Same Account

**Role Name:** ExampleRole
**Other Role Name:** RoleB

Trust Policy of ExampleRole
```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "AWS": "arn:aws:iam::123456789012:role/RoleB"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
```

In this trust policy, the principal `arn:aws:iam::123456789012:role/RoleB` (another role in the same account) is allowed to assume ExampleRole.

# Identity-Attached Policy of RoleB
Similarly, RoleB does not need a specific policy attached to it to perform sts:AssumeRole on ExampleRole. The explicit mention in the trust policy of ExampleRole suffices.

# Summary
The key point is that if the trust policy of a role explicitly names a principal from the same AWS account, that principal can assume the role without needing an explicit `sts:AssumeRole` permission in its own identity-attached policy. This simplifies role assumption within the same account by reducing the need for redundant permissions.

# Cross Account Behavior

**Trust Policy**

For a role in `Account A` to be assumable by a principal (user or role) in `Account B`, the role in `Account A` must have a trust policy that explicitly allows the principal from `Account B` to assume the role.

**Identity-Attached Policy**

The principal in `Account B` must have an identity-attached policy that allows it to perform the `sts:AssumeRole` action on the role in `Account A`.

**Example**

**Scenario:**
    * **Account A (Role Owner):** 123456789012
    * **Role in Account A:** ExampleRole
    * **Account B (Principal Account):** 210987654321
    * **IAM User in Account B:** UserB

**Trust Policy of ExampleRole in Account A (123456789012)**

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "AWS": "arn:aws:iam::210987654321:user/UserB" // UserB in Account B
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
```

This trust policy allows the IAM user `UserB` from `Account B` to assume `ExampleRole` in `Account A`.

**Identity-Attached Policy of UserB in Account B (210987654321)**

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": "sts:AssumeRole",
      "Resource": "arn:aws:iam::123456789012:role/ExampleRole" // ExampleRole in Account A
    }
  ]
}
```

This identity-attached policy defined in `Account B` allows `UserB` in `Account B` to assume the role `ExampleRole` in `Account A`.

**Detailed Breakdown**

**1. Trust Policy (Role in Account A):**

    * Specifies who can assume the role.
    * Needs to explicitly list the ARN of the principal from Account B.

**2. Identity Policy (Principal in Account B):**

    * Specifies what actions the principal can perform.
    * Needs to include a policy that allows the sts:AssumeRole action on the specified role in Account A.

**Summary**

When the principal is from a different account, two things are required:

1. The role in the trusting account (Account A) must have a trust policy that explicitly allows the principal (user or role) from the other account (Account B) to assume the role.
2. The principal in the trusted account (Account B) must have an identity-attached policy that grants permission to perform the sts:AssumeRole action on the role in Account A.

This ensures secure and explicit cross-account access, preventing unauthorized role assumptions across different AWS accounts.