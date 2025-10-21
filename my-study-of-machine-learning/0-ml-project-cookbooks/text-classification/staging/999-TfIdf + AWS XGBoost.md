Awesome—using **AWS SageMaker’s built-in XGBoost container** works great with TF-IDF. You have **two solid patterns**; pick the one that fits your deployment needs.

---

# Option A — Precompute TF-IDF, train with the XGBoost container

**When to use:** simple training + batch inference; you’re OK storing TF-IDF features as files.

**Flow**

1. Clean text → build `TfidfVectorizer` in Python
2. Transform to sparse matrix (CSR)
3. Save **LIBSVM** (preferred for sparse) or CSV
4. Upload to S3
5. Train with SageMaker XGBoost (built-in)
6. For inference: run the same TF-IDF transformation offline or in a small service, then call a **batch transform** or endpoint that expects already-vectorized features.

### Minimal notebook snippet

```python
# 0) Setup
import numpy as np, pandas as pd, joblib, os
from sklearn.model_selection import train_test_split
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.datasets import dump_svmlight_file
from scipy import sparse
import boto3, sagemaker
from sagemaker import image_uris

sess = sagemaker.Session()
bucket = sess.default_bucket()
prefix = "tfidf-xgb-demo"
role = sagemaker.get_execution_role()

# 1) TF-IDF
X_text = df["text"].astype(str).tolist()
y = df["label"].values

tfidf = TfidfVectorizer(
    lowercase=True,
    strip_accents="unicode",
    ngram_range=(1,2),
    min_df=2,          # or 0.005 on very large corpora
    max_df=0.9,
    sublinear_tf=True,
    norm="l2",
    dtype=np.float32
)
X = tfidf.fit_transform(X_text)

# 2) Split + save as LIBSVM
Xtr, Xte, ytr, yte = train_test_split(X, y, test_size=0.2, stratify=y, random_state=42)
os.makedirs("data", exist_ok=True)
dump_svmlight_file(Xtr, ytr, "data/train.libsvm", zero_based=True)
dump_svmlight_file(Xte, yte, "data/validation.libsvm", zero_based=True)

# 3) Persist vectorizer for later inference (important!)
joblib.dump(tfidf, "data/vectorizer.pkl")

# 4) Upload to S3
s3_train = sess.upload_data("data/train.libsvm", bucket=bucket, key_prefix=prefix)
s3_val   = sess.upload_data("data/validation.libsvm", bucket=bucket, key_prefix=prefix)
```

### Train with the built-in container

```python
# 5) Estimator
region = sess.boto_region_name
xgb_image = image_uris.retrieve(framework="xgboost", region=region, version="1.7-1")

from sagemaker.estimator import Estimator

est = Estimator(
    image_uri=xgb_image,
    role=role,
    instance_count=1,
    instance_type="ml.m5.xlarge",
    output_path=f"s3://{bucket}/{prefix}/output",
    sagemaker_session=sess,
)

# Hyperparameters (binary) change to multi:softprob + num_class for multi-class
est.set_hyperparameters(
    objective="binary:logistic",
    eval_metric="auc",
    num_round=800,
    max_depth=6,
    eta=0.05,
    subsample=0.8,
    colsample_bytree=0.8,
    reg_lambda=1.0,
    reg_alpha=0.0,
    tree_method="hist"
)

# 6) Fit (XGBoost expects 'train' and optional 'validation')
est.fit({"train": s3_train, "validation": s3_val})
```

### Inference options here

* **Batch transform**: convert new text → TF-IDF with the saved `vectorizer.pkl`, dump LIBSVM, send to `Transformer`.
* **Realtime endpoint**: same idea, but your client must TF-IDF the text before calling the endpoint (the endpoint only runs XGBoost).

---

# Option B — Inference Pipeline (TF-IDF + XGBoost in one endpoint)

**When to use:** you want the endpoint to accept raw text and do TF-IDF internally before scoring.

**Flow**

1. Package a small **scikit-learn inference script** that loads `vectorizer.pkl` and converts raw JSON/CSV text to TF-IDF (CSR → dense/sparse to numpy).
2. Create an **SKLearnModel** (first container) + the **XGBoostModel** (second container).
3. Chain them with a **PipelineModel** → single endpoint.

### 1) Preprocessing inference script (entry_point)

`inference_preprocess.py` (kept short for clarity):

```python
# expects JSON: {"instances": ["raw text 1", "raw text 2", ...]}
import json, joblib, numpy as np
from io import BytesIO
from scipy import sparse

def model_fn(model_dir):
    # model_dir contains vectorizer.pkl you uploaded when creating the model
    vec = joblib.load(f"{model_dir}/vectorizer.pkl")
    return vec

def input_fn(request_body, content_type):
    if "application/json" in content_type:
        payload = json.loads(request_body)
        texts = payload.get("instances") or payload
        return texts
    raise ValueError("Unsupported content_type")

def predict_fn(texts, vectorizer):
    X = vectorizer.transform([str(t) for t in texts])
    # Return dense float32 for downstream container (XGBoost expects array-like)
    return X.astype(np.float32).toarray()

def output_fn(preds, accept):
    # Forward as JSON array of arrays
    return json.dumps({"instances": preds.tolist()}), "application/json"
```

You’ll also place your `vectorizer.pkl` next to that script when creating the SKLearn model.

### 2) Build models & pipeline

```python
from sagemaker.sklearn.model import SKLearnModel
from sagemaker.xgboost.model import XGBoostModel
from sagemaker.pipeline import PipelineModel

# Upload vectorizer.pkl for the SKLearn container
vec_s3 = sess.upload_data("data/vectorizer.pkl", bucket=bucket, key_prefix=f"{prefix}/model-artifacts")

sklearn_model = SKLearnModel(
    framework_version="1.3-1",         # pick a current SKLearn container version
    role=role,
    entry_point="inference_preprocess.py",
    model_data=vec_s3,                  # contains vectorizer.pkl
    sagemaker_session=sess
)

xgb_model = XGBoostModel(
    model_data=est.model_data,          # from the training step
    role=role,
    framework_version="1.7-1",
    sagemaker_session=sess
)

pipe_model = PipelineModel(
    name="tfidf-xgb-pipeline",
    role=role,
    models=[sklearn_model, xgb_model],
    sagemaker_session=sess
)

predictor = pipe_model.deploy(
    initial_instance_count=1,
    instance_type="ml.m5.large"
)
```

**Now the endpoint accepts raw text**:

```python
payload = {"instances": ["maven loves lemonade market", "lemon tea"]}
pred = predictor.predict(payload)   # -> probabilities (from XGBoost)
```

> For multi-class, train with `objective="multi:softprob"` and set `num_class=<K>`. For binary, use `binary:logistic`.

---

## Practical tips & “gotchas”

* **Format** for XGBoost container:

  * LIBSVM (best for sparse TF-IDF) or CSV (dense).
  * Make sure labels are the **first column** in CSV; LIBSVM handles labels naturally.
* **Imbalance:** set `scale_pos_weight = (neg/pos)` for binary.
* **Feature count:** keep `max_features` at 50k–200k. Monitor memory.
* **Vectorizer freeze:** once you train, **freeze** the vocabulary (`TfidfVectorizer(vocabulary=...)`) for inference consistency.
* **Metrics:** `eval_metric`—`auc`, `logloss`, `merror` (multi-class), or `mlogloss`.
* **Batch vs realtime:** if you don’t need realtime raw-text scoring, Option A is simpler and cheaper.

---

## Recommended TF-IDF defaults (carry into SageMaker)

```python
TfidfVectorizer(
  lowercase=True, strip_accents="unicode",
  ngram_range=(1,2),
  min_df=2,            # or 0.005 on huge corpora
  max_df=0.9,
  sublinear_tf=True,
  norm="l2",
  dtype=np.float32
)
```

If you want, I can wrap this into a **full, runnable notebook** that:

1. downloads a public text dataset,
2. runs TF-IDF,
3. trains with the **SageMaker XGBoost** container, and
4. deploys an **inference pipeline** that accepts **raw text**.
