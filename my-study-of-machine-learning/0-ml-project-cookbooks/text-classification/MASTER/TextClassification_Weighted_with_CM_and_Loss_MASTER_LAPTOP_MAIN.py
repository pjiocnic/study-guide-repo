# -*- coding: utf-8 -*-

# === DistilBERT text classification (scaled for 1.5M rows, RTX A2000 8 GB) ===
# This is an update of your original file with these key changes for scale:
# 1) No global pandas dedup (which doesn't scale to 1.5M easily). We keep
#    cleaning in-stream via HF Datasets map/filter. (You can dedup upstream.)
# 2) Tokenization with max_length=128 (speed/memory), dynamic padding with
#    pad_to_multiple_of=8 (tensor cores), multiprocessing map.
# 3) Training defaults for A2000 8GB: batch=16, grad_accum=4, epochs=2, bf16/fp16,
#    gradient_checkpointing, fused AdamW, group_by_length and step-based eval/save.
# 4) Class weights computed from TRAIN only (imbalance-aware).
# 5) "Hardest / Easiest" examples are computed WITHOUT converting entire val set
#    to pandas; we only fetch the top-10 indices from the raw validation split.
# 6) Optional quick sampling knob (QUICK_RUN_N) to sanity-check hyperparams.

import os
import random
import numpy as np
import torch
import matplotlib.pyplot as plt

from datasets import load_dataset, DatasetDict
from transformers import (
    AutoTokenizer, AutoModelForSequenceClassification,
    DataCollatorWithPadding, TrainingArguments, Trainer, pipeline, EarlyStoppingCallback
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

try:
    torch.set_float32_matmul_precision("high")
except Exception:
    pass

# ----------------------------
# 1) Input config (edit these two)
# ----------------------------
# Use a glob or list; both work with load_dataset
CSV_GLOB_OR_LIST = [
    "file1.csv", "file2.csv", "file3.csv", "file4.csv", "file5.csv",
    "file6.csv", "file7.csv", "file8.csv", "file9.csv", "file10.csv",
]
HAS_HEADER = True      # set to False if your CSVs have no header
TEXT_COL = "text"
LABEL_COL = "label"

# Optional quick run for smoke tests before full 1.5M (0 = disabled)
QUICK_RUN_N = 0

# ----------------------------
# 2) Load CSVs via HF Datasets (stream-friendly ops)
# ----------------------------
if HAS_HEADER:
    ds_raw = load_dataset("csv", data_files=CSV_GLOB_OR_LIST, split="train")
else:
    ds_raw = load_dataset("csv", data_files=CSV_GLOB_OR_LIST, split="train",
                          column_names=[LABEL_COL, TEXT_COL])

# Heuristic rename if needed
maybe_cols = ds_raw.column_names
rename_map = {}
if LABEL_COL not in maybe_cols:
    for c in maybe_cols:
        lc = c.lower()
        if lc.startswith("cat") or lc == "label":
            rename_map[c] = LABEL_COL
            break
if TEXT_COL not in maybe_cols:
    for c in maybe_cols:
        lc = c.lower()
        if lc.startswith("text") or lc in ("remarks", "message"):
            rename_map[c] = TEXT_COL
            break
if rename_map:
    ds_raw = ds_raw.rename_columns(rename_map)

# Keep only the two columns we need
to_drop = [c for c in ds_raw.column_names if c not in (TEXT_COL, LABEL_COL)]
if to_drop:
    ds_raw = ds_raw.remove_columns(to_drop)

# Clean & filter with HF (avoid pandas for scale)
def _stringify_strip(ex):
    t = str(ex[TEXT_COL]) if ex[TEXT_COL] is not None else ""
    ex[TEXT_COL] = t.strip()
    return ex

num_proc = min(8, os.cpu_count() or 2)
ds_clean = ds_raw.map(_stringify_strip, num_proc=num_proc)
ds_clean = ds_clean.filter(lambda ex: (ex[TEXT_COL] is not None) and (ex[LABEL_COL] is not None) and (len(ex[TEXT_COL]) >= 1))

# Downsample for a quick test if desired
if QUICK_RUN_N and QUICK_RUN_N > 0:
    print(f"Quick-run enabled: sampling {QUICK_RUN_N} rows for a smoke test.")
    ds_clean = ds_clean.shuffle(seed=SEED).select(range(min(QUICK_RUN_N, len(ds_clean))))

# Encode labels AFTER cleaning
ds_clean = ds_clean.class_encode_column(LABEL_COL)

# Stratified 80/10/10 split
tmp = ds_clean.train_test_split(test_size=0.2, stratify_by_column=LABEL_COL, seed=SEED)
val_test = tmp["test"].train_test_split(test_size=0.5, stratify_by_column=LABEL_COL, seed=SEED)
data = DatasetDict(train=tmp["train"], validation=val_test["train"], test=val_test["test"])
print(data)

# ----------------------------
# 3) Tokenizer + tokenization
# ----------------------------
MODEL_CKPT = "distilbert-base-uncased"
MAX_LEN = 128  # <= You've requested 128
tokenizer = AutoTokenizer.from_pretrained(MODEL_CKPT, clean_up_tokenization_spaces=False, model_max_length=MAX_LEN)

def tok(batch):
    return tokenizer(batch[TEXT_COL], truncation=True, max_length=MAX_LEN)

# data_tok = data.map(tok, batched=True, remove_columns=[TEXT_COL], num_proc=num_proc)
data_tok = data.map(tok, batched=True, remove_columns=[
                    TEXT_COL], num_proc=1)  # issues with multi-threading. so num_proc=1

# Pad to multiple of 8 for Tensor Core efficiency
collator = DataCollatorWithPadding(tokenizer=tokenizer, pad_to_multiple_of=8)

# ----------------------------
# 4) Class weights (TRAIN only)
# ----------------------------
id2label = {i: name for i, name in enumerate(data["train"].features[LABEL_COL].names)}
label2id = {v: k for k, v in id2label.items()}
num_labels = len(id2label)

train_counts = np.bincount(data_tok["train"][LABEL_COL], minlength=num_labels)
train_counts = np.maximum(train_counts, 1)
weights = train_counts.sum() / (num_labels * train_counts)
class_weights = torch.tensor(weights, dtype=torch.float)

# ----------------------------
# 5) Metrics
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
# 6) Weighted Trainer
# ----------------------------
class WeightedTrainer(Trainer):
    def compute_loss(self, model, inputs, return_outputs=False):
        labels = inputs.get("labels")
        outputs = model(**inputs)
        logits = outputs.get("logits")
        loss_fct = torch.nn.CrossEntropyLoss(weight=class_weights.to(logits.device))
        loss = loss_fct(logits.view(-1, logits.size(-1)), labels.view(-1))
        return (loss, outputs) if return_outputs else loss

model = AutoModelForSequenceClassification.from_pretrained(
    MODEL_CKPT, num_labels=num_labels, id2label=id2label, label2id=label2id
).to(device)

# ----------------------------
# 7) TrainingArguments tuned for A2000 8 GB & 1.5M rows
# ----------------------------
# Requested: batch=16, grad_accum=4 (effective 64), MAX_LEN=128
USE_BF16 = torch.cuda.is_available() and torch.cuda.get_device_capability(0)[0] >= 8
USE_FP16 = (not USE_BF16) and torch.cuda.is_available()

EPOCHS = 2                 # start with 2 for 1.5M
TRAIN_BATCH = 16
EVAL_BATCH = 32
GRAD_ACCUM = 4
EVAL_STEPS = 1000          # reduce eval overhead; tune as needed
SAVE_STEPS = 1000

training_args = TrainingArguments(
    output_dir="distilbert-textclf",
    learning_rate=2e-5,
    per_device_train_batch_size=TRAIN_BATCH,
    per_device_eval_batch_size=EVAL_BATCH,
    gradient_accumulation_steps=GRAD_ACCUM,
    num_train_epochs=EPOCHS,
    weight_decay=0.01,
    lr_scheduler_type="cosine",
    warmup_ratio=0.03,

    evaluation_strategy="steps",
    save_strategy="steps",
    eval_steps=EVAL_STEPS,
    save_steps=SAVE_STEPS,
    save_total_limit=2,
    load_best_model_at_end=True,
    metric_for_best_model="f1_weighted",
    greater_is_better=True,

    logging_steps=100,
    report_to="none",
    seed=SEED,

    bf16=USE_BF16,
    fp16=USE_FP16,
    optim="adamw_torch_fused",
    dataloader_num_workers=min(8, os.cpu_count() or 2),
    dataloader_pin_memory=True,
    group_by_length=True,
    gradient_checkpointing=True,
)

callbacks = [EarlyStoppingCallback(early_stopping_patience=2)]  # optional; adjust/remove if not wanted

trainer = WeightedTrainer(
    model=model,
    args=training_args,
    train_dataset=data_tok["train"],
    eval_dataset=data_tok["validation"],
    tokenizer=tokenizer,
    data_collator=collator,
    compute_metrics=compute_metrics,
    callbacks=callbacks,
)

# ----------------------------
# 8) Train + evaluate
# ----------------------------
trainer.train()
test_metrics = trainer.evaluate(data_tok["test"])
print("Test metrics:", test_metrics)
print("Trainer device:", trainer.args.device)

# ----------------------------
# 9) Predict on validation (per-example loss & CM)
# ----------------------------
pred = trainer.predict(data_tok["validation"])
logits = torch.from_numpy(pred.predictions)
labels_t = torch.from_numpy(pred.label_ids).long()

per_ex_loss = torch.nn.functional.cross_entropy(logits, labels_t, reduction="none").numpy()
y_preds = logits.argmax(dim=-1).numpy()

labels_list = [id2label[i] for i in range(num_labels)]
cm = confusion_matrix(pred.label_ids, y_preds, normalize="true")
fig, ax = plt.subplots(figsize=(6, 6))
disp = ConfusionMatrixDisplay(confusion_matrix=cm, display_labels=labels_list)
disp.plot(cmap="Blues", values_format=".2f", ax=ax, colorbar=False)
plt.title("Normalized confusion matrix (validation)")
plt.tight_layout()
plt.show()

# ----------------------------
# 10) Hardest/Easiest without full pandas conversion
# ----------------------------
import numpy as _np

order_hard = _np.argsort(-per_ex_loss)[:10]   # top 10 hardest
order_easy = _np.argsort(per_ex_loss)[:10]    # top 10 easiest

# Fetch the corresponding raw texts from *untokenized* validation split
texts_val_hard = [data["validation"][int(i)][TEXT_COL] for i in order_hard]
texts_val_easy = [data["validation"][int(i)][TEXT_COL] for i in order_easy]


def _show_examples(ixs, texts, title):
    print(f"\n{title}")
    for rank, (i, t) in enumerate(zip(ixs, texts), 1):
        print(
            f"#{rank} (idx={int(i)}): "
            f"loss={float(per_ex_loss[int(i)]):.4f} | "
            f"true={id2label[int(pred.label_ids[int(i)])]} | "
            f"pred={id2label[int(y_preds[int(i)])]}"
        )
        print(f"TEXT: {t[:300]}")
        print("-" * 80)

_show_examples(order_hard, texts_val_hard, "Top 10 hardest validation examples")
_show_examples(order_easy, texts_val_easy, "Top 10 easiest validation examples")

# ----------------------------
# 11) Save model + tokenizer + tiny pipeline demo
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

clf = pipeline("text-classification", model=mdl2, tokenizer=tok2, device=0 if torch.cuda.is_available() else -1)
print("\nPipeline inference demo:")
print(clf(["I love sunny mornings!", "I'm feeling really down today."]))