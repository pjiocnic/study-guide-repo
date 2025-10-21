#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
SageMaker-ready Text Classification (DistilBERT) with WeightedTrainer
- Reads multiple CSVs directly from S3
- Stratified 80/10/10 split
- Confusion matrix, per-example loss (validation)
- Save + reload model, optional S3 upload of artifacts
- Optional offline model loading from S3 (if egress is blocked)

Prereqs in your SageMaker kernel/env:
  pip install -q datasets transformers evaluate s3fs boto3 scikit-learn matplotlib

Notes:
- CSVs must have columns: 'text' and 'label'
  (or we attempt best-guess rename, e.g., 'Category'->'label', 'Text'->'text')
- If your CSVs have headers, ensure all files share identical headers.
"""

import os, sys, glob, random, io, json, time
import numpy as np
import boto3
import s3fs
import torch
import matplotlib.pyplot as plt

from datasets import load_dataset, DatasetDict
from transformers import (
    AutoTokenizer, AutoModelForSequenceClassification,
    DataCollatorWithPadding, TrainingArguments, Trainer, pipeline
)
from sklearn.metrics import ConfusionMatrixDisplay, confusion_matrix
from torch.nn.functional import cross_entropy
import evaluate

# ------------------------------
# CONFIG — EDIT THESE
# ------------------------------
S3_BUCKET               = os.environ.get("S3_BUCKET", "your-bucket-name")
S3_INPUT_PREFIX         = os.environ.get("S3_INPUT_PREFIX", "path/to/csvs")  # e.g., "datasets/textclf/train"
S3_OUTPUT_PREFIX        = os.environ.get("S3_OUTPUT_PREFIX", "outputs/textclf")  # where to upload artifacts (optional)
USE_OFFLINE_MODEL       = os.environ.get("USE_OFFLINE_MODEL", "false").lower() == "true"
OFFLINE_MODEL_S3_URI    = os.environ.get("OFFLINE_MODEL_S3_URI", "")  # e.g., "s3://my-bucket/models/distilbert-base-uncased"
MODEL_CKPT              = os.environ.get("MODEL_CKPT", "distilbert-base-uncased")
OUTPUT_DIR              = os.environ.get("OUTPUT_DIR", "distilbert-textclf-sagemaker")
NUM_EPOCHS              = int(os.environ.get("NUM_EPOCHS", "3"))
TRAIN_BS                = int(os.environ.get("TRAIN_BS", "16"))
EVAL_BS                 = int(os.environ.get("EVAL_BS", "32"))
LEARNING_RATE           = float(os.environ.get("LEARNING_RATE", "2e-5"))
SEED                    = int(os.environ.get("SEED", "42"))
UPLOAD_ARTIFACTS_TO_S3  = os.environ.get("UPLOAD_ARTIFACTS_TO_S3", "true").lower() == "true"

# ------------------------------
# Helper: S3 paths
# ------------------------------
def s3_uri(bucket, prefix_glob):
    if prefix_glob.startswith("s3://"):
        return prefix_glob
    return f"s3://{bucket}/{prefix_glob}".rstrip("/")

S3_DATA_GLOB = s3_uri(S3_BUCKET, f"{S3_INPUT_PREFIX}/*.csv")

print(f"[INFO] Will load CSV(s) from: {S3_DATA_GLOB}")
if UPLOAD_ARTIFACTS_TO_S3:
    print(f"[INFO] Will upload artifacts to: s3://{S3_BUCKET}/{S3_OUTPUT_PREFIX}")

# ------------------------------
# Reproducibility
# ------------------------------
random.seed(SEED); np.random.seed(SEED); torch.manual_seed(SEED)

# ------------------------------
# 1) Load dataset from S3
# ------------------------------
# Requires s3fs and valid AWS creds (automatically present in SageMaker).
ds = load_dataset(
    "csv",
    data_files=S3_DATA_GLOB,
    split="train",
    # column_names=["label", "text"],  # uncomment if NO header
)

# Try best-guess rename if needed
maybe_cols = ds.column_names
if "label" not in maybe_cols or "text" not in maybe_cols:
    rename_map = {}
    for c in maybe_cols:
        lc = c.lower()
        if lc.startswith("cat") and "label" not in rename_map:
            rename_map[c] = "label"
        if lc.startswith("text") and "text" not in rename_map:
            rename_map[c] = "text"
    if rename_map:
        ds = ds.rename_columns(rename_map)

# Keep only needed columns
ds = ds.remove_columns([c for c in ds.column_names if c not in ("text","label")])

# Encode labels to ClassLabel (int ids + names)
ds = ds.class_encode_column("label")

# Stratified split 80/10/10
tmp = ds.train_test_split(test_size=0.2, stratify_by_column="label", seed=SEED)
val_test = tmp["test"].train_test_split(test_size=0.5, stratify_by_column="label", seed=SEED)
data = DatasetDict(train=tmp["train"], validation=val_test["train"], test=val_test["test"])
print(data)

# ------------------------------
# 2) Tokenizer + (optional) offline model
# ------------------------------
def ensure_local_from_s3_folder(s3_uri, local_dir):
    """Download an entire S3 prefix (model snapshot) to local_dir."""
    if not s3_uri or not s3_uri.startswith("s3://"):
        return
    os.makedirs(local_dir, exist_ok=True)
    s3 = boto3.resource("s3")
    bucket, key_prefix = s3_uri.replace("s3://", "").split("/", 1)
    b = s3.Bucket(bucket)
    for obj in b.objects.filter(Delimiter="/", Prefix=key_prefix):
        # skip "directories"
        if obj.key.endswith("/"):
            continue
        rel = obj.key[len(key_prefix):].lstrip("/")
        dest_path = os.path.join(local_dir, rel)
        os.makedirs(os.path.dirname(dest_path), exist_ok=True)
        b.download_file(obj.key, dest_path)

if USE_OFFLINE_MODEL and OFFLINE_MODEL_S3_URI:
    LOCAL_MODEL_DIR = "./offline_model"
    print(f"[INFO] Downloading offline model from {OFFLINE_MODEL_S3_URI} -> {LOCAL_MODEL_DIR}")
    ensure_local_from_s3_folder(OFFLINE_MODEL_S3_URI, LOCAL_MODEL_DIR)
    model_name_or_path = LOCAL_MODEL_DIR
else:
    model_name_or_path = MODEL_CKPT

id2label = {i: name for i, name in enumerate(data["train"].features["label"].names)}
label2id = {v: k for k, v in id2label.items()}

tokenizer = AutoTokenizer.from_pretrained(model_name_or_path)

def tok(batch):
    return tokenizer(batch["text"], truncation=True)

data_tok = data.map(tok, batched=True, remove_columns=["text"])
collator = DataCollatorWithPadding(tokenizer=tokenizer)

# ------------------------------
# 3) Class weights for imbalance
# ------------------------------
num_labels = len(id2label)
counts = np.bincount(data_tok["train"]["label"], minlength=num_labels)
weights = counts.sum() / (num_labels * counts)
class_weights = torch.tensor(weights, dtype=torch.float)

# ------------------------------
# 4) Metrics
# ------------------------------
accuracy = evaluate.load("accuracy")
f1 = evaluate.load("f1")
def compute_metrics(eval_pred):
    logits, labels = eval_pred
    preds = logits.argmax(-1)
    return {
        "accuracy": accuracy.compute(predictions=preds, references=labels)["accuracy"],
        "f1_macro": f1.compute(predictions=preds, references=labels, average="macro")["f1"],
        "f1_weighted": f1.compute(predictions=preds, references=labels, average="weighted")["f1"],
    }

# ------------------------------
# 5) Weighted Trainer
# ------------------------------
class WeightedTrainer(Trainer):
    def compute_loss(self, model, inputs, return_outputs=False):
        labels = inputs.get("labels")
        outputs = model(**inputs)
        logits = outputs.get("logits")
        loss_fct = torch.nn.CrossEntropyLoss(weight=class_weights.to(logits.device))
        loss = loss_fct(logits.view(-1, logits.size(-1)), labels.view(-1))
        return (loss, outputs) if return_outputs else loss

model = AutoModelForSequenceClassification.from_pretrained(
    model_name_or_path,
    num_labels=num_labels,
    id2label=id2label,
    label2id=label2id
)

# ------------------------------
# 6) TrainingArguments tuned for m5.xlarge / m5.4xlarge
#    (adjust batch sizes via env vars if needed)
# ------------------------------
training_args = TrainingArguments(
    output_dir=OUTPUT_DIR,
    learning_rate=LEARNING_RATE,
    per_device_train_batch_size=TRAIN_BS,
    per_device_eval_batch_size=EVAL_BS,
    num_train_epochs=NUM_EPOCHS,
    weight_decay=0.01,
    evaluation_strategy="epoch",
    save_strategy="epoch",
    load_best_model_at_end=True,
    metric_for_best_model="f1_macro",
    logging_steps=50,
    report_to="none",
    seed=SEED
)

trainer = WeightedTrainer(
    model=model,
    args=training_args,
    train_dataset=data_tok["train"],
    eval_dataset=data_tok["validation"],
    tokenizer=tokenizer,
    data_collator=collator,
    compute_metrics=compute_metrics,
)

# ------------------------------
# 7) Train + evaluate
# ------------------------------
train_out = trainer.train()
print(train_out)

test_metrics = trainer.evaluate(data_tok["test"])
print("[RESULT] Test metrics:", test_metrics)

# ------------------------------
# 8) Confusion matrix (validation)
# ------------------------------
preds_output = trainer.predict(data_tok["validation"])
y_preds = np.argmax(preds_output.predictions, axis=1)
y_true  = preds_output.label_ids
labels_list = [id2label[i] for i in range(num_labels)]

cm = confusion_matrix(y_true, y_preds, normalize="true")
fig, ax = plt.subplots(figsize=(6, 6))
disp = ConfusionMatrixDisplay(confusion_matrix=cm, display_labels=labels_list)
disp.plot(cmap="Blues", values_format=".2f", ax=ax, colorbar=False)
plt.title("Normalized confusion matrix (validation)")
plt.tight_layout()

os.makedirs(OUTPUT_DIR, exist_ok=True)
cm_path = os.path.join(OUTPUT_DIR, "confusion_matrix_val.png")
plt.savefig(cm_path, dpi=180)
plt.close(fig)
print(f"[INFO] Saved confusion matrix to: {cm_path}")

# ------------------------------
# 9) Per-example loss (validation hardest/easiest)
# ------------------------------
device = trainer.model.device
def forward_pass_with_label(batch):
    inputs = {k: v.to(device) for k, v in batch.items() if k in tokenizer.model_input_names}
    with torch.no_grad():
        output = trainer.model(**inputs)
        pred_label = torch.argmax(output.logits, axis=-1)
        loss = cross_entropy(output.logits, batch["labels"].to(device), reduction="none")
    return {"loss": loss.cpu().numpy(), "predicted_label": pred_label.cpu().numpy()}

needed_cols = ["labels"] + [n for n in tokenizer.model_input_names if n in data_tok["validation"].column_names]
data_tok.set_format("torch", columns=needed_cols)
data_tok["validation"] = data_tok["validation"].map(forward_pass_with_label, batched=True, batch_size=16)

# Switch to pandas to inspect
data_tok.set_format("pandas")
df_val = data_tok["validation"][:]
data.set_format("pandas")
texts_val = data["validation"][:]["text"]
df_val["text"] = texts_val

def int2str(i): return id2label[int(i)]
df_val["label_name"] = df_val["labels"].apply(int2str)
df_val["predicted_label_name"] = df_val["predicted_label"].apply(int2str)

hardest_csv = os.path.join(OUTPUT_DIR, "validation_hardest_examples.csv")
easiest_csv = os.path.join(OUTPUT_DIR, "validation_easiest_examples.csv")
df_val.sort_values("loss", ascending=False)[["text","label_name","predicted_label_name","loss"]].head(50).to_csv(hardest_csv, index=False)
df_val.sort_values("loss", ascending=True)[["text","label_name","predicted_label_name","loss"]].head(50).to_csv(easiest_csv, index=False)
print(f"[INFO] Saved hardest/easiest CSVs to: {hardest_csv} and {easiest_csv}")

# ------------------------------
# 10) Save model locally and (optional) upload to S3
# ------------------------------
BEST_DIR = os.path.join(OUTPUT_DIR, "best")
os.makedirs(BEST_DIR, exist_ok=True)
trainer.save_model(BEST_DIR)
tokenizer.save_pretrained(BEST_DIR)
print(f"[INFO] Saved best model + tokenizer to: {BEST_DIR}")

# Small inference demo
tok2 = AutoTokenizer.from_pretrained(BEST_DIR)
mdl2 = AutoModelForSequenceClassification.from_pretrained(BEST_DIR)
mdl2.config.id2label = id2label
mdl2.config.label2id = label2id
clf = pipeline("text-classification", model=mdl2, tokenizer=tok2)

examples = ["I love sunny mornings!", "I'm feeling really down today."]
print("[DEMO] Pipeline outputs:", clf(examples))

# Upload artifacts to S3 (optional)
if UPLOAD_ARTIFACTS_TO_S3:
    s3 = boto3.client("s3")
    def _upload(local_path, s3_bucket, s3_prefix):
        key = f"{s3_prefix.rstrip('/')}/{os.path.basename(local_path)}"
        s3.upload_file(local_path, s3_bucket, key)
        print(f"[UPLOAD] s3://{s3_bucket}/{key}")

    _upload(cm_path, S3_BUCKET, S3_OUTPUT_PREFIX)
    _upload(hardest_csv, S3_BUCKET, S3_OUTPUT_PREFIX)
    _upload(easiest_csv, S3_BUCKET, S3_OUTPUT_PREFIX)
    # upload a small marker with metrics
    metrics_path = os.path.join(OUTPUT_DIR, "test_metrics.json")
    with open(metrics_path, "w") as f:
        json.dump(test_metrics, f, indent=2)
    _upload(metrics_path, S3_BUCKET, S3_OUTPUT_PREFIX)

print("[DONE] Training + evaluation complete.")
