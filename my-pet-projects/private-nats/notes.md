
```bash
aws cloudformation create-stack \
  --stack-name privatenatgatewaydemo \
  --template-body file://./tgw_natgw_overlapping_cidrs.yaml  \
  --capabilities CAPABILITY_NAMED_IAM
```

```bash
aws cloudformation delete-stack \
  --stack-name privatenatgatewaydemo
```

https://repost.aws/knowledge-center/elb-find-load-balancer-ip