
1. Check if a profile exists

```bash
aws iam list-instance-profiles | grep AWSCloud9SSMInstanceProfile
```
2. Deleting a profile

```bash
aws iam detach-role-policy --role-name AWSCloud9SSMAccessRole --policy-arn $(aws iam list-attached-role-policies --role-name AWSCloud9SSMAccessRole --output text | awk '{ print $2 }')

aws iam remove-role-from-instance-profile --instance-profile-name AWSCloud9SSMInstanceProfile --role-name AWSCloud9SSMAccessRole

aws iam delete-role --role-name AWSCloud9SSMAccessRole

aws iam delete-instance-profile --instance-profile-name AWSCloud9SSMInstanceProfile
```
