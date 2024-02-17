
```bash
terraform init
terraform validate
terraform plan
# terraform plan -out tfplan
# terraform show -no-color tfplan > tfplan.txt
terraform apply # terraform apply tfplan
terraform destroy

terraform fmt
```

2. after applying

```bash
terraform state list | grep vpc

terraform state show aws_vpc.vpc-10-1
terraform state show data.aws_instance.c9inst
```
