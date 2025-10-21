#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
DistilBERT text classification with class weights
+ GPU auto-detect/use
+ clean per-example loss via trainer.predict
+ confusion matrix
+ save/reload + small pipeline demo
"""

from datasets import Dataset
import os
import random
import numpy as np
import pandas as pd
import torch
import matplotlib.pyplot as plt
from datasets import load_dataset, DatasetDict
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

# Optional: can help perf on Ampere/Ada w/ PyTorch 2.x
try:
    torch.set_float32_matmul_precision("high")
except Exception:
    pass

# ----------------------------
# 1) Load your CSV
# ----------------------------
CSV_PATH = ["file1.csv", "file2.csv", "file23.csv"]  # <-- change this

ds = load_dataset(
    "csv",
    data_files=CSV_PATH,
    split="train",
    # column_names=['label','text']  # <-- uncomment if your CSV has NO header
)

# If headers aren't already 'label' and 'text', try a best-guess rename:
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

# Keep only needed columns then encode string labels -> ClassLabel
ds = ds.remove_columns(
    [c for c in ds.column_names if c not in ("text", "label")])


df = ds.to_pandas()
df.isnull().sum()
df_cleaned = df.dropna(subset=["text"])
df_cleaned.isnull().sum()
ds = Dataset.from_pandas(df_cleaned)

# Remove duplicate rows based on 'text' and 'label'
ds = ds.drop_duplicates(subset=["text", "label"])

ds = ds.class_encode_column("label")

# Stratified split: 80/10/10
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
    model_ckpt, clean_up_tokenization_spaces=False)


def tok(batch):
    # no padding here; use collator
    return tokenizer(batch["text"], truncation=True)


data_tok = data.map(tok, batched=True, remove_columns=["text"])
collator = DataCollatorWithPadding(tokenizer=tokenizer)

# ----------------------------
# 3) Class weights (imbalance-aware)
#    weight_c = N / (K * count_c)
# ----------------------------
num_labels = len(id2label)
counts = np.bincount(data_tok["train"]["label"], minlength=num_labels)
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
        # NOTE: inside Trainer it's "labels" (plural)
        labels = inputs.get("labels")
        outputs = model(**inputs)
        logits = outputs.get("logits")
        loss_fct = torch.nn.CrossEntropyLoss(
            weight=class_weights.to(logits.device))
        loss = loss_fct(logits.view(-1, logits.size(-1)), labels.view(-1))
        return (loss, outputs) if return_outputs else loss


model = AutoModelForSequenceClassification.from_pretrained(
    model_ckpt, num_labels=num_labels, id2label=id2label, label2id=label2id
).to(device)  # <-- ensure the model is on your GPU/CPU choice

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
# 7) Predict on validation (simple + robust)
# ----------------------------
pred = trainer.predict(data_tok["validation"])
# stays on CPU; fine for analysis
logits = torch.from_numpy(pred.predictions)
labels_t = torch.from_numpy(pred.label_ids).long()

# Per-example loss and preds
per_ex_loss = torch.nn.functional.cross_entropy(
    logits, labels_t, reduction="none").numpy()
y_preds = logits.argmax(dim=-1).numpy()

# Confusion matrix
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
