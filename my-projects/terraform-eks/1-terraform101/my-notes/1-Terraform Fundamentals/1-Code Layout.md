
```bash
➜  terraform tree
.
├── main.tf    # contains the core resources of the module
├── outputs.tf # (Optional) contains the outputs of the module
├── providers.tf # (Optional) contains the provider configuration
├── userdata
│   ├── staging-efs.sh
│   └── staging-wordpress.sh
└── variables.tf # (Optional) contains the input variables of the module
```