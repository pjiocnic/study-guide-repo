Great questions—here’s a clear, AWS-aligned way to set up a 100k-row dataset for XGBoost and the best docs/workshops to follow.

---

# 1) Package the data & upload to S3

**Recommended file format (for flexibility):**

* **CSV** (easy to inspect; required shape for built-in XGBoost is `label,feat1,feat2,…` and **no header**). ([AWS Documentation][1])
* **OR Parquet** (smaller, typed, faster IO; also supported by built-in XGBoost). ([AWS Documentation][2])

**Local prep (Python):**

```python
import pandas as pd
df = ...  # your 100k rows
# For built-in XGBoost with CSV: put the target in the first column and drop header
cols = ['label'] + [c for c in df.columns if c != 'label']
df[cols].to_csv('train.csv', index=False, header=False)

# Alternatively, Parquet (Snappy by default)
df.to_parquet('train.parquet', index=False)
```

**Upload to S3 (either CLI or Python):**

```bash
aws s3 cp train.csv s3://your-bucket/your-prefix/train/train.csv
# or
aws s3 cp train.parquet s3://your-bucket/your-prefix/train/train.parquet
```

SageMaker training can read **all objects under an S3 prefix** (i.e., you can put multiple files under `s3://bucket/prefix/train/`). ([SageMaker Documentation][3])

---

# 2) One big file or multiple (and gzip)?

* **Multiple files** are fine and often preferable (e.g., several 10–200 MB shards) for scalability and parallel reads. Point your training channel to the **prefix**. ([SageMaker Documentation][3])
* **Compression:** For training with built-in XGBoost, prefer **plain CSV or Parquet**. While gzip is common in other workflows, support varies by algorithm/mode—CSV/Parquet keep things simple and fully supported for XGBoost training. (Batch Transform can read gzip; training support depends on algo & mode.) ([mikulskibartosz.name][4], [AWS Documentation][2])

---

# 3) Read back with pandas

You can round-trip from S3 easily:

```python
import pandas as pd
df = pd.read_csv('s3://your-bucket/your-prefix/train/train.csv', header=None,
                 storage_options={'anon': False})
# or Parquet
df = pd.read_parquet('s3://your-bucket/your-prefix/train/train.parquet',
                     storage_options={'anon': False})
```

(Use AWS creds in your environment; pandas uses `s3fs` under the hood.)

---

# 4) Do feature engineering

Options from fastest to more “platform”:

* **Pure pandas/Sklearn in a Notebook/Studio:** quick iterations; then write CSV/Parquet back to S3 for training (built-in XGBoost supports CSV/Parquet). ([AWS Documentation][2])
* **SageMaker Data Wrangler:** GUI transformations, data quality checks, export to Processing/Pipelines/Feature Store for repeatable FE. ([Amazon Web Services, Inc.][5])
* **SageMaker Processing / Pipelines:** productionize your FE code as jobs in a pipeline that outputs train/val/test under S3 prefixes. The churn Pipelines post shows the full pattern with XGBoost. ([Amazon Web Services, Inc.][6])
* **Feature Store:** centralize engineered features and reuse across training/inference. (There’s an end-to-end workshop combining Data Wrangler + Feature Store + Pipelines.) ([Amazon Web Services, Inc.][7], [GitHub][8])

---

## Exactly the AWS resources you asked for (blogs/workshops)

* **SageMaker XGBoost data formats + usage (official docs)** — CSV/Parquet support, label-first/no-header CSV rule, and how to point to S3 prefixes. ([AWS Documentation][2], [SageMaker Documentation][3])
* **Customer Churn example (SageMaker official notebook)** — End-to-end: upload to S3, prep with pandas, train XGBoost, evaluate/deploy. (Great template to copy.) ([sagemaker-examples.readthedocs.io][9])
* **Build, tune, and deploy churn model with Pipelines (AWS Blog)** — Shows automated preprocessing/training/ eval/deploy with built-in XGBoost. ([Amazon Web Services, Inc.][6])
* **Automate feature engineering with Data Wrangler + Feature Store + Pipelines (AWS Blog)** — Pattern for repeatable FE at scale. ([Amazon Web Services, Inc.][7])
* **Data Wrangler for data prep & FE (AWS Blog)** — How to use Wrangler for cleaning/FE and integrate with Studio/Studio Lab. ([Amazon Web Services, Inc.][10])
* **Feature engineering at scale (AWS Blog)** — Glue Interactive Sessions + Studio + built-in XGBoost; good for larger workflows and governance needs. ([Amazon Web Services, Inc.][11])
* **SageMaker Workshops catalog** — Hands-on labs for SageMaker; look for Data Wrangler/Feature Store/XGBoost labs. ([workshops.aws][12], [workshops.aws][13])

---

## Quick “good defaults” for your 100k rows

* **File format:** CSV (label first, no header) **or** Parquet. ([AWS Documentation][1])
* **Layout in S3:**

  ```
  s3://your-bucket/your-project/
    ├── raw/
    ├── processed/
    └── train/
        ├── part-000.csv
        ├── part-001.csv
        └── ...
  ```

  Train channel points to `s3://your-bucket/your-project/train/`. ([SageMaker Documentation][3])
* **Next step:** Start from the **SageMaker XGBoost churn notebook** and swap in your S3 prefix—it already shows pandas → S3 → XGBoost → evaluation. ([sagemaker-examples.readthedocs.io][9])

If you want, I can paste a minimal SageMaker SDK snippet that (a) uploads your files, (b) configures the XGBoost estimator with the right `ContentType`, and (c) kicks off training using your S3 prefix.

[1]: https://docs.aws.amazon.com/sagemaker/latest/dg/cdf-training.html?utm_source=chatgpt.com "Common Data Formats for Training - Amazon SageMaker AI"
[2]: https://docs.aws.amazon.com/sagemaker/latest/dg/xgboost-how-to-use.html?utm_source=chatgpt.com "How to use SageMaker AI XGBoost"
[3]: https://sagemaker.readthedocs.io/en/v2.72.2/inputs.html?utm_source=chatgpt.com "Inputs — sagemaker 2.72.2 documentation"
[4]: https://mikulskibartosz.name/sagemaker-batch-transform-jobs?utm_source=chatgpt.com "How to run batch inference using Sagemaker ..."
[5]: https://aws.amazon.com/blogs/aws/introducing-amazon-sagemaker-data-wrangler-a-visual-interface-to-prepare-data-for-machine-learning/?utm_source=chatgpt.com "Introducing Amazon SageMaker Data Wrangler, a Visual ..."
[6]: https://aws.amazon.com/blogs/machine-learning/build-tune-and-deploy-an-end-to-end-churn-prediction-model-using-amazon-sagemaker-pipelines/?utm_source=chatgpt.com "Build, tune, and deploy an end-to-end churn prediction ..."
[7]: https://aws.amazon.com/blogs/machine-learning/automate-feature-engineering-pipelines-with-amazon-sagemaker/?utm_source=chatgpt.com "Automate feature engineering pipelines with ..."
[8]: https://github.com/aws-samples/amazon-sagemaker-feature-store-end-to-end-workshop?utm_source=chatgpt.com "aws-samples/amazon-sagemaker-feature-store-end-to- ..."
[9]: https://sagemaker-examples.readthedocs.io/en/latest/introduction_to_applying_machine_learning/xgboost_customer_churn/xgboost_customer_churn.html?utm_source=chatgpt.com "Customer Churn Prediction with XGBoost"
[10]: https://aws.amazon.com/blogs/machine-learning/use-amazon-sagemaker-data-wrangler-for-data-preparation-and-studio-labs-to-learn-and-experiment-with-ml/?utm_source=chatgpt.com "Use Amazon SageMaker Data Wrangler for ..."
[11]: https://aws.amazon.com/blogs/machine-learning/large-scale-feature-engineering-with-sensitive-data-protection-using-aws-glue-interactive-sessions-and-amazon-sagemaker-studio/?utm_source=chatgpt.com "Large-scale feature engineering with sensitive data ..."
[12]: https://www.workshops.aws/categories/SageMaker?utm_source=chatgpt.com "AWS SageMaker Workshop"
[13]: https://workshops.aws/categories/Machine%20Learning?utm_source=chatgpt.com "Machine Learning"
