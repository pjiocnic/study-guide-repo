# Setting tags on role sessions

You can use Deny statements with the sts:TagSession operation to restrict certain tags from being set.

1. Create the IAM Role with the Trust Policy

- create trust-policy.json

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Deny",
            "Action": "sts:TagSession",
            "Principal": {"AWS": "*"},
            "Condition": {
                "StringEquals": {
                    "aws:RequestTag/Team": "Admin"
                }
            }
        },
        {
            "Effect": "Allow",
            "Action": "sts:TagSession",
            "Principal": {"AWS": "*"},
            "Condition": {
                "StringLike": {
                    "aws:RequestTag/Team": "*"
                }
            }
        }
    ]
}
```

- attach policy

```bash
aws iam create-role \
    --role-name TagSessionRole \
    --assume-role-policy-document file://trust-policy.json
```

- attach permissions to role

```bash
aws iam attach-role-policy \
    --role-name TagSessionRole \
    --policy-arn arn:aws:iam::aws:policy/ReadOnlyAccess
```

2. Test

- Assume the Role with a Non-Admin Team Tag:

```bash
aws sts assume-role \
    --role-arn arn:aws:iam::account-id:role/TagSessionRole \
    --role-session-name TestSession \
    --tags Key=Team,Value=Development
```

This should succeed because the Team tag value Development is allowed by the trust policy.

- Assume the Role with the Admin Team Tag

```bash
aws sts assume-role \
    --role-arn arn:aws:iam::account-id:role/TagSessionRole \
    --role-session-name TestSession \
    --tags Key=Team,Value=Admin
```

# deny any attempt to use the sts:TagSession action if the request includes a tag with the key Admin

```json
{
    "Effect": "Deny",
    "Action": "sts:TagSession",
    "Principal": {"AWS": "*"},
    "Condition": { //  this condition matches if the Admin tag key is included in the request, causing the action to be denied.
        "Null": {
            "aws:RequestTag/Admin": false // the key exists and its value is not null
        }
    }
}
```

https://docs.aws.amazon.com/IAM/latest/UserGuide/reference_policies_elements_condition_operators.html#Conditions_Null


# Demo

- Trust policy

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Principal": {"AWS": "*"},
            "Action": "sts:AssumeRole"
        }
    ]
}
```

- Deny policy

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Deny",
            "Action": "sts:TagSession",
            "Principal": {"AWS": "*"},
            "Condition": {
                "Null": {
                    "aws:RequestTag/Admin": false
                }
            }
        }
    ]
}
```

- create role with trust policy

```bash
aws iam create-role \
    --role-name TagSessionRole \
    --assume-role-policy-document file://trust-policy.json
```

- attach deny policy to role

```bash
aws iam put-role-policy \
    --role-name TagSessionRole \
    --policy-name DenyAdminTagPolicy \
    --policy-document file://deny-policy.json
```

- attach permissions to the role

Attach a policy that allows the role to perform some actions.

```bash
aws iam attach-role-policy \
    --role-name TagSessionRole \
    --policy-arn arn:aws:iam::aws:policy/ReadOnlyAccess
```

- assume the role with non-admin tab

```bash
aws sts assume-role \
    --role-arn arn:aws:iam::123456789012:role/TagSessionRole \
    --role-session-name TestSession \
    --tags Key=Team,Value=Development
```

This should succeed because the Team tag value Development is not restricted by the deny policy.

expected output

```json
{
    "Credentials": {
        "AccessKeyId": "ASIAXXXXXXXXXXXXXXXX",
        "SecretAccessKey": "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY",
        "SessionToken": "FQoGZXIvYXdzEJr//////////wEaDKvR1...",
        "Expiration": "2024-05-19T19:33:48Z"
    },
    "AssumedRoleUser": {
        "AssumedRoleId": "AROA3SAMPLESAMPLE:TestSession",
        "Arn": "arn:aws:sts::123456789012:assumed-role/TagSessionRole/TestSession"
    }
}
```

- assume the role with admin tag

```bash
aws sts assume-role \
    --role-arn arn:aws:iam::123456789012:role/TagSessionRole \
    --role-session-name TestSession \
    --tags Key=Admin,Value=True
```

expected output

```bash
An error occurred (AccessDenied) when calling the AssumeRole operation: User: arn:aws:sts::123456789012:assumed-role/TagSessionRole/TestSession is not authorized to perform: sts:TagSession on resource: arn:aws:sts::123456789012:assumed-role/TagSessionRole/TestSession
```