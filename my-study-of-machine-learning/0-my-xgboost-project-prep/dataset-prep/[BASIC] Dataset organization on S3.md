Totally fair—let’s keep the POC **simple** and avoid a pile of services. Here’s a minimal, clean setup that works great for XGBoost without Glue, Athena, Step Functions, etc.

# Minimal stack

* **S3**: the only data store
* **One compute**: SageMaker Notebook Instance *or* Studio *or* plain EC2
* **Built-in XGBoost** (optional) for training job
* **CloudWatch** comes for free for logs (no extra setup needed)

# S3 layout (POC-simple)

```
s3://<bucket>/<proj>/<env>/data/
  raw/                         # exactly-as-ingested files
  preprocessed/                # one clean CSV or small set of CSVs
  train/                       # frozen split used for training
  valid/
  test/
  meta/                        # tiny JSONs with schema/split info
```

* No medallion layers, no partitions—just **clear folders**.

# One-time environment

```bash
export bucket=my-ml-bucket
export proj=churn-poc
export env=dev
export base="s3://$bucket/$proj/$env"
```

# Upload raw & keep it simple

```bash
# raw data up
aws s3 cp ./data/raw "$base/data/raw/" --recursive
```

# A tiny preprocessing + split script (local or notebook)

```python
import os, json, gzip, io
import pandas as pd
from sklearn.model_selection import train_test_split

# 1) read raw (local or s3 path if s3fs configured)
df = pd.read_csv("data/raw/input.csv")  # keep it to 1–2 files for POC

# 2) minimal cleaning / feature build (POC-level)
# df = df.dropna()  # example
# df["feature_x"] = df["a"]/df["b"]  # example
target = "label"

# 3) split (stratified if classification)
X = df.drop(columns=[target])
y = df[target]
X_tr, X_tmp, y_tr, y_tmp = train_test_split(X, y, test_size=0.2, random_state=42, stratify=y)
X_va, X_te, y_va, y_te = train_test_split(X_tmp, y_tmp, test_size=0.5, random_state=42, stratify=y_tmp)

# 4) write small, gzipped CSVs (good I/O, still simple)
def to_gz_csv(df_, path):  # local then sync
    with gzip.open(path, "wt") as f:
        df_.to_csv(f, index=False)

os.makedirs("out/train", exist_ok=True)
os.makedirs("out/valid", exist_ok=True)
os.makedirs("out/test",  exist_ok=True)

to_gz_csv(pd.concat([y_tr, X_tr], axis=1), "out/train/part-000.csv.gz")
to_gz_csv(pd.concat([y_va, X_va], axis=1), "out/valid/part-000.csv.gz")
to_gz_csv(pd.concat([y_te, X_te], axis=1), "out/test/part-000.csv.gz")

# 5) tiny metadata (nice to have, still POC-simple)
meta = {
  "target": target,
  "split": {"train": len(y_tr), "valid": len(y_va), "test": len(y_te)},
  "random_state": 42
}
os.makedirs("out/meta", exist_ok=True)
with open("out/meta/dataset.json","w") as f: json.dump(meta, f, indent=2)
```

# Sync to S3

```bash
aws s3 sync ./out/train "$base/data/train/"
aws s3 sync ./out/valid "$base/data/valid/"
aws s3 sync ./out/test  "$base/data/test/"
aws s3 cp   ./out/meta/dataset.json "$base/data/meta/dataset.json"
```

# Train with built-in XGBoost (optional but tidy)

```python
from sagemaker import Session
from sagemaker.inputs import TrainingInput
from sagemaker.xgboost.estimator import XGBoost

sess = Session()

est = XGBoost(
    role="<your-exec-role>",
    framework_version="1.7-1",      # keep it fixed for POC
    instance_type="ml.c5.large",    # non-burstable, smooth POC
    instance_count=1,
    output_path=f"{os.environ['base'] if 'base' in os.environ else 's3://my-ml-bucket/churn-poc/dev'}/models/"
)

est.set_hyperparameters(
    objective="binary:logistic",
    eval_metric="auc",
    max_depth=6,
    eta=0.1,
    subsample=0.8,
    colsample_bytree=0.8,
    num_round=200
)

train_input = TrainingInput(f"{base}/data/train/", content_type="text/csv")
valid_input = TrainingInput(f"{base}/data/valid/", content_type="text/csv")
est.fit({"train": train_input, "validation": valid_input})
```

# Practical tips (POC-level)

* Keep **one gzipped CSV per split** to start; add more shards only if needed.
* Stick to **CSV** for training (XGBoost-friendly). Use **Parquet** later if your POC grows.
* Use a **non-burstable** instance (e.g., `ml.c5.large`) to avoid T-class throttling.
* Put **feature names** and **target** in the `meta/dataset.json` so your training/inference code stays sane.

# What we avoided on purpose

* No Glue/Athena catalogs
* No Step Functions / complex orchestration
* No event-driven pipelines
* No medallion/iceberg layers

If you want, I can turn the above into a **single Makefile** (targets: `make raw`, `make split`, `make sync`, `make train`) so you can rerun the POC end-to-end with one-liners.

---

Awesome — here’s a **single `Makefile`** that runs your whole POC end-to-end with one-liners:

* `make raw` → uploads raw files to S3
* `make split` → preprocess + split locally (gzipped CSVs + metadata)
* `make sync` → pushes train/valid/test + metadata to S3
* `make train` → kicks off SageMaker XGBoost training
* `make all` → raw → split → sync → train

Copy-paste this into a file named `Makefile` in your project root.

```makefile
# ===============================
# Minimal XGBoost POC Makefile
# ===============================

# ---- CONFIG: set these via env or edit defaults here ----
BUCKET ?= my-ml-bucket
PROJ   ?= churn-poc
ENV    ?= dev
BASE   := s3://$(BUCKET)/$(PROJ)/$(ENV)

RAW_DIR     ?= data/raw                  # local raw input folder
RAW_FILE    ?= data/raw/input.csv        # local raw CSV (POC: 1 file is fine)
TARGET      ?= label                     # target column name

# Python env (optional). If you already have env, you can skip `make setup`
PYTHON ?= python3

# -------- INTERNALS --------
OUT_DIR     := out
TRAIN_DIR   := $(OUT_DIR)/train
VALID_DIR   := $(OUT_DIR)/valid
TEST_DIR    := $(OUT_DIR)/test
META_DIR    := $(OUT_DIR)/meta

S3_RAW      := $(BASE)/data/raw/
S3_TRAIN    := $(BASE)/data/train/
S3_VALID    := $(BASE)/data/valid/
S3_TEST     := $(BASE)/data/test/
S3_META     := $(BASE)/data/meta/

.PHONY: help all setup raw split sync train clean show

help:
	@echo "Make targets:"
	@echo "  make all     - raw -> split -> sync -> train"
	@echo "  make raw     - upload ./data/raw to $(S3_RAW)"
	@echo "  make split   - local preprocess + stratified split -> ./out/{train,valid,test,meta}"
	@echo "  make sync    - push ./out splits + metadata to S3"
	@echo "  make train   - run SageMaker built-in XGBoost training"
	@echo "  make show    - print key variables"
	@echo "  make clean   - remove ./out"

show:
	@echo "BUCKET=$(BUCKET)"
	@echo "PROJ=$(PROJ)"
	@echo "ENV=$(ENV)"
	@echo "BASE=$(BASE)"
	@echo "RAW_FILE=$(RAW_FILE)"
	@echo "TARGET=$(TARGET)"

# Optional: create a lightweight env with needed libs
setup:
	$(PYTHON) -m pip install --upgrade pip
	$(PYTHON) -m pip install pandas scikit-learn pyarrow sagemaker boto3

all: raw split sync train

# 1) Upload RAW to S3
raw:
	aws s3 cp $(RAW_DIR) $(S3_RAW) --recursive

# 2) Local preprocess + split into gz CSVs + metadata
split:
	@mkdir -p $(TRAIN_DIR) $(VALID_DIR) $(TEST_DIR) $(META_DIR)
	$(PYTHON) - <<'PY'
import os, json, gzip
import pandas as pd
from sklearn.model_selection import train_test_split

raw_file   = "$(RAW_FILE)"
target_col = "$(TARGET)"
out_train  = "$(TRAIN_DIR)"
out_valid  = "$(VALID_DIR)"
out_test   = "$(TEST_DIR)"
meta_dir   = "$(META_DIR)"

# ---- 2.1 Read raw ----
df = pd.read_csv(raw_file)

# ---- 2.2 Minimal cleaning/features (keep POC-simple) ----
# (Add lightweight steps here if needed)
# Example: df = df.dropna()

# ---- 2.3 Split ----
if target_col not in df.columns:
    raise SystemExit(f"Target column '{target_col}' not found in {raw_file}")

X = df.drop(columns=[target_col])
y = df[target_col]

# Stratify if classification-like (binary/multi-class)
stratify = y if (y.nunique() <= 50 and y.dtype != 'float') else None

X_tr, X_tmp, y_tr, y_tmp = train_test_split(
    X, y, test_size=0.2, random_state=42, stratify=stratify
)
X_va, X_te, y_va, y_te = train_test_split(
    X_tmp, y_tmp, test_size=0.5, random_state=42, stratify=(y_tmp if stratify is not None else None)
)

def write_gz(df_, path):
    with gzip.open(path, "wt") as f:
        df_.to_csv(f, index=False)

# XGBoost expects label typically in first column for CSV; we’ll place target first
def combine(y_, X_):
    return pd.concat([y_.rename(target_col), X_], axis=1)

write_gz(combine(y_tr, X_tr), os.path.join(out_train, "part-000.csv.gz"))
write_gz(combine(y_va, X_va), os.path.join(out_valid, "part-000.csv.gz"))
write_gz(combine(y_te, X_te), os.path.join(out_test,  "part-000.csv.gz"))

# ---- 2.4 Metadata ----
meta = {
    "target": target_col,
    "shape": {"total": int(df.shape[0])},
    "split_counts": {
        "train": int(len(y_tr)),
        "valid": int(len(y_va)),
        "test":  int(len(y_te)),
    },
    "random_state": 42,
}
with open(os.path.join(meta_dir, "dataset.json"), "w") as f:
    json.dump(meta, f, indent=2)
print("Wrote splits to ./out and metadata to ./out/meta/dataset.json")
PY

# 3) Push splits + metadata to S3
sync:
	aws s3 sync $(TRAIN_DIR) $(S3_TRAIN)
	aws s3 sync $(VALID_DIR) $(S3_VALID)
	aws s3 sync $(TEST_DIR)  $(S3_TEST)
	aws s3 cp   $(META_DIR)/dataset.json $(S3_META)

# 4) Launch SageMaker XGBoost training (built-in algo)
train:
	$(PYTHON) - <<'PY'
import os
from sagemaker import Session
from sagemaker.inputs import TrainingInput
from sagemaker.xgboost.estimator import XGBoost

bucket = os.environ.get("BUCKET", "my-ml-bucket")
proj   = os.environ.get("PROJ",   "churn-poc")
env    = os.environ.get("ENV",    "dev")
base   = f"s3://{bucket}/{proj}/{env}"

train_s3 = f"{base}/data/train/"
valid_s3 = f"{base}/data/valid/"
output_s3= f"{base}/models/"

sess = Session()

est = XGBoost(
    role           = os.environ.get("SAGEMAKER_EXEC_ROLE", "<your-sagemaker-exec-role-arn>"),
    framework_version = "1.7-1",   # keep fixed for POC
    instance_type  = os.environ.get("INSTANCE_TYPE", "ml.c5.large"),
    instance_count = int(os.environ.get("INSTANCE_COUNT", "1")),
    output_path    = output_s3,
    sagemaker_session = sess,
)

est.set_hyperparameters(
    objective="binary:logistic",   # change if regression/multi-class
    eval_metric="auc",
    max_depth=6,
    eta=0.1,
    subsample=0.8,
    colsample_bytree=0.8,
    num_round=200
)

train_input = TrainingInput(train_s3, content_type="text/csv")
valid_input = TrainingInput(valid_s3, content_type="text/csv")

print("Launching training job...")
est.fit({"train": train_input, "validation": valid_input})
print("Training complete. Model artifacts at:", output_s3)
PY

clean:
	rm -rf $(OUT_DIR)
```

## How to use

```bash
# 0) Set your values (recommended)
export BUCKET=my-ml-bucket
export PROJ=churn-poc
export ENV=dev
export SAGEMAKER_EXEC_ROLE=arn:aws:iam::<acct>:role/service-role/AmazonSageMaker-ExecutionRole-XYZ

# 1) (Optional) install libs locally
make setup

# 2) Upload your raw files (put a CSV at data/raw/input.csv)
make raw

# 3) Preprocess + split locally (creates ./out/*)
make split

# 4) Sync splits + metadata to S3
make sync

# 5) Train with built-in XGBoost on SageMaker
make train

# Or do all steps in order
make all
```

### Notes & tweaks

* **Exact target name**: set `TARGET` (default `label`).
  `make split TARGET=is_churn`
* **Instance type**: override when training:
  `INSTANCE_TYPE=ml.m5.xlarge make train`
* **Multi-file raw**: For POC simplicity it reads `data/raw/input.csv`. If you have many files, change `RAW_FILE` logic and concatenate in the Python block.

If you want, I can also add a `predict` target that deploys the trained model to a SageMaker endpoint and does a quick sample inference.
