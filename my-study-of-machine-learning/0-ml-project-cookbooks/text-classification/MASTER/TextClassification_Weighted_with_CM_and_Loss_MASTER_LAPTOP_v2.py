#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
DistilBERT text classification (multi-CSV) with class weights
- Cleans, drops NaN, deduplicates (text,label)
- Stratified 80/10/10 split
- GPU auto-detect/use + throughput tweaks
- Per-example loss from trainer.predict
- Confusion matrix
- Save/reload + quick pipeline demo
"""

"""
Tuning Ideas:
* If VRAM allows, increase `per_device_train_batch_size` (e.g., 24/32).
* If texts are short, set `max_length=128` in `tokenizer(..., truncation=True, max_length=128)` for a noticeable speedup.
* Consider `warmup_ratio=0.1` and/or `gradient_accumulation_steps` if you want larger effective batch sizes.
* For very large corpora, you can cache tokenization (`cache_dir`) and use `--use_fast_tokenizer` (fast is default for DistilBERT).
"""

import os
import random
import numpy as np
import pandas as pd
import torch
import matplotlib.pyplot as plt
from datasets import load_dataset, Dataset, DatasetDict
from transformers import (
    AutoTokenizer, AutoModelForSequenceClassification,
    DataCollatorWithPadding, TrainingArguments, Trainer, pipeline
)
import evaluate
from sklearn.metrics import ConfusionMatrixDisplay, confusion_matrix

# ----------------------------
# 0) Reproducibility + GPU check
# ----------------------------
SEED = 42
random.seed(SEED)
np.random.seed(SEED)
torch.manual_seed(SEED)

if torch.cuda.is_available():
    device = torch.device("cuda")
    print("✅ Using GPU:", torch.cuda.get_device_name(0))
else:
    device = torch.device("cpu")
    print("⚠️ GPU not detected, running on CPU")

# Optional: small perf nudge on Ampere/Ada (PyTorch 2.x)
try:
    torch.set_float32_matmul_precision("high")
except Exception:
    pass

# ----------------------------
# 1) Load your CSVs (multi-file)
# ----------------------------
# Replace with your actual 10 files (absolute/relative paths both fine)
CSV_PATHS = [
    "file1.csv", "file2.csv", "file3.csv", "file4.csv", "file5.csv",
    "file6.csv", "file7.csv", "file8.csv", "file9.csv", "file10.csv",
]

# If your CSVs have NO header, add: column_names=['label','text'] below
ds_raw = load_dataset(
    "csv",
    data_files=CSV_PATHS,
    split="train",
    # column_names=["label", "text"],  # <-- uncomment if no header in CSVs
)

# Rename columns heuristically if needed
maybe_cols = ds_raw.column_names
if "label" not in maybe_cols or "text" not in maybe_cols:
    rename_map = {}
    for c in maybe_cols:
        lc = c.lower()
        if lc.startswith("cat") and "label" not in rename_map:
            rename_map[c] = "label"
        if lc.startswith("text") and "text" not in rename_map:
            rename_map[c] = "text"
    if rename_map:
        ds_raw = ds_raw.rename_columns(rename_map)

# Keep only needed columns
ds_raw = ds_raw.remove_columns(
    [c for c in ds_raw.column_names if c not in ("text", "label")])

# ---- Clean + Dedup on pandas ----
df = ds_raw.to_pandas()

# Ensure text is string; strip whitespace
df["text"] = df["text"].astype(str).str.strip()

# Drop NaN/empty in both columns, then DEDUP by (text, label)
df = df.replace({"": np.nan})
df = df.dropna(subset=["text", "label"]).drop_duplicates(
    subset=["text", "label"]).reset_index(drop=True)

# (Optional) drop ultra-short strings that hurt training
# df = df[df["text"].str.len() >= 3].reset_index(drop=True)

# Back to HF Dataset (no _index column)
ds = Dataset.from_pandas(df, preserve_index=False)

# Encode labels AFTER cleaning/dedup
ds = ds.class_encode_column("label")

# Stratified 80/10/10 split
tmp = ds.train_test_split(test_size=0.2, stratify_by_column="label", seed=SEED)
val_test = tmp["test"].train_test_split(
    test_size=0.5, stratify_by_column="label", seed=SEED)
data = DatasetDict(
    train=tmp["train"], validation=val_test["train"], test=val_test["test"])
print(data)

# ----------------------------
# 2) Tokenizer + model labels
# ----------------------------
model_ckpt = "distilbert-base-uncased"
id2label = {i: name for i, name in enumerate(
    data["train"].features["label"].names)}
label2id = {v: k for k, v in id2label.items()}

tokenizer = AutoTokenizer.from_pretrained(
    model_ckpt,
    clean_up_tokenization_spaces=False
)


def tok(batch):
    # Add max_length=128/192 if your texts are short for speed
    return tokenizer(batch["text"], truncation=True)  # padding via collator


# Speed up tokenization with multiprocessing (uses your CPU cores)
num_proc = min(8, os.cpu_count() or 2)
data_tok = data.map(tok, batched=True, remove_columns=[
                    "text"], num_proc=num_proc)

collator = DataCollatorWithPadding(tokenizer=tokenizer)

# ----------------------------
# 3) Class weights (imbalance-aware)
#    weight_c = N / (K * count_c)
# ----------------------------
num_labels = len(id2label)
counts = np.bincount(data_tok["train"]["label"], minlength=num_labels)
counts = np.maximum(counts, 1)  # safety against any rare zero counts
weights = counts.sum() / (num_labels * counts)
class_weights = torch.tensor(weights, dtype=torch.float)

# ----------------------------
# 4) Metrics
# ----------------------------
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

# ----------------------------
# 5) Weighted Trainer
# ----------------------------


class WeightedTrainer(Trainer):
    def compute_loss(self, model, inputs, return_outputs=False):
        # Inside Trainer batches use 'labels' (plural)
        labels = inputs.get("labels")
        outputs = model(**inputs)
        logits = outputs.get("logits")
        loss_fct = torch.nn.CrossEntropyLoss(
            weight=class_weights.to(logits.device))
        loss = loss_fct(logits.view(-1, logits.size(-1)), labels.view(-1))
        return (loss, outputs) if return_outputs else loss


model = AutoModelForSequenceClassification.from_pretrained(
    model_ckpt, num_labels=num_labels, id2label=id2label, label2id=label2id
).to(device)

# Throughput optimizations
use_bf16 = torch.cuda.is_available() and torch.cuda.get_device_capability(0)[
    0] >= 8  # Ada/Lovelace
training_args = TrainingArguments(
    output_dir="distilbert-textclf",
    learning_rate=2e-5,
    per_device_train_batch_size=16,
    per_device_eval_batch_size=32,
    num_train_epochs=3,
    weight_decay=0.01,
    evaluation_strategy="epoch",
    save_strategy="epoch",
    load_best_model_at_end=True,
    metric_for_best_model="f1_macro",
    logging_steps=50,
    report_to="none",
    seed=SEED,

    # Speed/throughput
    bf16=use_bf16,
    fp16=(not use_bf16) and torch.cuda.is_available(),
    optim="adamw_torch_fused",
    dataloader_num_workers=4,      # try 2–8
    dataloader_pin_memory=True,
    group_by_length=True,          # shorter batches, less padding
    save_total_limit=2,
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

# ----------------------------
# 6) Train + evaluate
# ----------------------------
trainer.train()
test_metrics = trainer.evaluate(data_tok["test"])
print("Test metrics:", test_metrics)
print("Trainer device:", trainer.args.device)

# ----------------------------
# 7) Predict on validation (per-example loss & CM)
# ----------------------------
pred = trainer.predict(data_tok["validation"])
# on CPU is fine for analysis
logits = torch.from_numpy(pred.predictions)
labels_t = torch.from_numpy(pred.label_ids).long()

# Per-example loss and preds
per_ex_loss = torch.nn.functional.cross_entropy(
    logits, labels_t, reduction="none").numpy()
y_preds = logits.argmax(dim=-1).numpy()

# Confusion matrix (normalized)
labels_list = [id2label[i] for i in range(num_labels)]
cm = confusion_matrix(pred.label_ids, y_preds, normalize="true")
fig, ax = plt.subplots(figsize=(6, 6))
disp = ConfusionMatrixDisplay(confusion_matrix=cm, display_labels=labels_list)
disp.plot(cmap="Blues", values_format=".2f", ax=ax, colorbar=False)
plt.title("Normalized confusion matrix (validation)")
plt.tight_layout()
plt.show()

# ----------------------------
# 8) Hardest/Easiest examples (with text)
# ----------------------------
# Get raw validation text from the *untokenized* 'data'
data.set_format("pandas")
texts_val = data["validation"][:]["text"]

df_val = pd.DataFrame({
    "text": texts_val,
    "true_label_id": pred.label_ids,
    "pred_label_id": y_preds,
    "loss": per_ex_loss
})
df_val["true_label"] = df_val["true_label_id"].map(id2label)
df_val["pred_label"] = df_val["pred_label_id"].map(id2label)

print("\nTop 10 hardest (highest loss) validation examples:")
print(df_val.sort_values("loss", ascending=False)[
      ["text", "true_label", "pred_label", "loss"]].head(10))

print("\nTop 10 easiest (lowest loss) validation examples:")
print(df_val.sort_values("loss", ascending=True)[
      ["text", "true_label", "pred_label", "loss"]].head(10))

# ----------------------------
# 9) Save model + tokenizer and a small pipeline demo
# ----------------------------
SAVE_DIR = "./distilbert-textclf-best"
os.makedirs(SAVE_DIR, exist_ok=True)
trainer.save_model(SAVE_DIR)
tokenizer.save_pretrained(SAVE_DIR)
print(f"Saved model + tokenizer to: {SAVE_DIR}")

tok2 = AutoTokenizer.from_pretrained(SAVE_DIR)
mdl2 = AutoModelForSequenceClassification.from_pretrained(SAVE_DIR)
mdl2.config.id2label = id2label
mdl2.config.label2id = label2id

clf = pipeline("text-classification", model=mdl2, tokenizer=tok2)
print("\nPipeline inference demo:")
print(clf(["I love sunny mornings!", "I'm feeling really down today."]))
