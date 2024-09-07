<h1>amazon-route53-hybriddns-and-r53-resolvers</h1>

1. [[**_NEW_VERSION_**] ini Project - Hybrid DNS By Cantrill](https://www.youtube.com/watch?v=NHhtXpAcAc0)
1. [[**_OLD_VERSION_**] Mini Project - Hybrid DNS - Stage 1 - Provision and Verify By Cantrill](https://www.youtube.com/watch?v=UmPTavtAB9s)
1. [[**_OLD_VERSION_**] Mini Project - Hybrid DNS - Stage 2 - Environment Connectivity  By Cantrill](https://www.youtube.com/watch?v=n1vSVxUhsQ4)
1. [[**_OLD_VERSION_**] Mini Project - Hybrid DNS - Stage 3 - Inbound R53 Endpoint (Onprem to AWS) BY Cantrill](https://www.youtube.com/watch?v=uN49-noXk2I)
1. [[**_OLD_VERSION_**] Mini Project - Hybrid DNS - Stage 4 - Outbound R53 Endpoint (AWS to ONPREM)](https://www.youtube.com/watch?v=0nub1kV1Hg8)
1. [[**_START_HERE_**][**_MAKE_NOTES_**] Route53 Resolver Endpoints | Part-1 | Hybrid DNS | Route 53 Resolver | Forwarding Rules | DEMO By CloudDeepDive](https://www.youtube.com/watch?v=P159VMSR694)
- [DIAGRAM] How Route 53 Resolver works?
- Inbound / Outbound endpoints
- How .2 Resolver talks to R53 Resolver
- What were DNS forwarders?
1. [Route53 Resolver Endpoints | Part-2 | Hybrid DNS | Route 53 Resolver | Forwarding Rules | DEMO By CloudDeepDive](https://www.youtube.com/watch?v=oddzx0JiukQ)
1. [[**_ADVANCED_**][**_MUST_SEE_**] AWS re:Invent 2019: Deep dive on DNS in the hybrid cloud (NET410) By Gavin](https://www.youtube.com/watch?v=_Z5jAs2gvPA)
- What is .2 Resolver (or now called R53 Resolver)
- How does .2 Resolver send queries to PHZ, VPC DNS and Internet (see https://youtu.be/_Z5jAs2gvPA?t=360)
- DNS Forwarders
- lot more
1. [[**_MY_NEXT_**] New – Amazon Route 53 Resolver for Hybrid Clouds by Shaun Ray](https://aws.amazon.com/blogs/aws/new-amazon-route-53-resolver-for-hybrid-clouds/)

# 1. Route53 Resolver Endpoints

1. [[**START_HERE**] Route53 Resolver Endpoints | Part-1 | Hybrid DNS | Route 53 Resolver | Forwarding Rules | DEMO By CloudDeepDive](https://www.youtube.com/watch?v=P159VMSR694)
1. [Route53 Resolver Endpoints | Part-2 | Hybrid DNS | Route 53 Resolver | Forwarding Rules | DEMO By CloudDeepDive](https://www.youtube.com/watch?v=oddzx0JiukQ)
1. [Mini Project - Hybrid DNS By Cantrill](https://www.youtube.com/watch?v=NHhtXpAcAc0)
1. [[**ADVANCED**][**MUST_SEE**] AWS re:Invent 2019: Deep dive on DNS in the hybrid cloud (NET410) By Gavin](https://www.youtube.com/watch?v=_Z5jAs2gvPA)
- What is .2 Resolver (or now called R53 Resolver)
- How does .2 Resolver send queries to PHZ, VPC DNS and Internet (see https://youtu.be/_Z5jAs2gvPA?t=360)
- DNS Forwarders
- lot more
1. [[**ADVANCED**] Simplify DNS management in a multi-account environment with Route 53 Resolver by Mahmoud Matouk ](https://aws.amazon.com/blogs/security/simplify-dns-management-in-a-multiaccount-environment-with-route-53-resolver/)

## 1.1. Sharing Resolver Rules with accounts using RAM

1. [Automating DNS infrastructure using Route 53 Resolver endpoints by Shiva Vaidyanathan and Akhil Nayabu](https://aws.amazon.com/blogs/networking-and-content-delivery/automating-dns-infrastructure-using-route-53-resolver-endpoints/)
- This uses Amazon DNS servers for the demo which may not be the newer approach
1. [Using Route 53 Private Hosted Zones for Cross-account Multi-region Architectures by Anandprasanna Gaitonde and John Bickle](https://aws.amazon.com/blogs/architecture/using-route-53-private-hosted-zones-for-cross-account-multi-region-architectures/)