# 1. Clone repo without checking out any files
git clone --no-checkout https://github.com/aws/amazon-sagemaker-examples.git
cd amazon-sagemaker-examples

# 2. Enable sparse-checkout in "cone" mode
git sparse-checkout init --cone

# 3. Specify the directory you want
git sparse-checkout set use-cases/customer_churn

# 4. Check out the files
git checkout main
