
1. arn:aws:iam::111122223333:root: any IAM principal (user or role) in account 1111222233332
2. S3 bucket policy includes a Principal element; Principal element is unnecessary in an IAM policy because the principal is by default the entity to which the IAM policy attaches

•	Your IAM policies bump up against the size limit (up to 2 KB for users, 5 KB for groups, and 10 KB for roles). S3 supports bucket policies of up to 20 KB.
•	You want a simple way to grant cross-account access to your S3 environment, without using IAM roles.