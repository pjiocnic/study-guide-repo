
<img src="./images/tgw-1.png" title="tgw-1.png" width="900"/>

1. When a Transit Gateway Attachment is made to a VPC, it will deploy an Elastic Network Interface (ENI) **to each Availability Zone in the VPC**.
2. Eventhough the attachment can be deployed to an existing subnet, AWS recommends creating it in separate subnets (aka connectivity subnets)
3. Cross Account TGW Attachment only support VPC attachments



# References

1. https://medium.com/@heycasey/creating-a-transit-gateway-6e3df814a07a