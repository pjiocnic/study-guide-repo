# 0) Install (if needed)
# pip install datasets transformers accelerate -q

from datasets import load_dataset, DatasetDict
from transformers import (AutoTokenizer, AutoModelForSequenceClassification,
                          DataCollatorWithPadding, TrainingArguments, Trainer)
import numpy as np
import evaluate
import torch
from collections import Counter
import os, random

SEED = 42
random.seed(SEED); np.random.seed(SEED); torch.manual_seed(SEED)

CSV_PATH = "your_file.csv"  # <-- change this

# 1) Load your CSV
# If your file has NO header row, provide column_names=['label','text'].
# If your file HAS headers, use column names below instead of column_names.
ds = load_dataset(
    "csv",
    data_files=CSV_PATH,
    split="train",
    # column_names=['label','text']  # <-- uncomment if no header in CSV
)

# If your header names aren't already 'label' and 'text', rename here:
# e.g. if headers are ["Category","Text"]:
maybe_cols = ds.column_names
if "label" not in maybe_cols or "text" not in maybe_cols:
    # Try a best-guess rename; edit if your headers differ
    rename_map = {}
    # find the likely label and text columns
    for c in maybe_cols:
        lc = c.lower()
        if lc.startswith("cat"): rename_map[c] = "label"
        if lc.startswith("text"): rename_map[c] = "text"
    ds = ds.rename_columns(rename_map)

# Keep only the two columns we need
ds = ds.remove_columns([c for c in ds.column_names if c not in ("text","label")])

# 2) Convert string labels -> integer IDs (ClassLabel)
# This adds .features['label'].names (id2label) and lets you keep names neatly.
ds = ds.class_encode_column("label")

# 3) Stratified train/val/test split (80/10/10)
tmp = ds.train_test_split(test_size=0.2, stratify_by_column="label", seed=SEED)
val_test = tmp["test"].train_test_split(test_size=0.5, stratify_by_column="label", seed=SEED)
data = DatasetDict(train=tmp["train"], validation=val_test["train"], test=val_test["test"])

print(data)
# DatasetDict({
#   train: Dataset({'text','label'}) ...
#   validation: ...
#   test: ...
# })

# (Optional) Save for reuse
# data.save_to_disk("my_text_dataset_emotion_style")

# 4) Prepare DistilBERT
model_ckpt = "distilbert-base-uncased"
id2label = {i: name for i, name in enumerate(data["train"].features["label"].names)}
label2id = {v: k for k, v in id2label.items()}

tokenizer = AutoTokenizer.from_pretrained(model_ckpt)

def tok(batch):
    return tokenizer(batch["text"], truncation=True)  # let Trainer pad dynamically

data_tok = data.map(tok, batched=True, remove_columns=["text"])
collator = DataCollatorWithPadding(tokenizer=tokenizer)

# 5) (Imbalance-aware) class weights for cross-entropy
#   weight_c = N / (K * count_c)  — common, simple choice
num_labels = len(id2label)
counts = np.bincount(data_tok["train"]["label"], minlength=num_labels)
weights = counts.sum() / (num_labels * counts)
class_weights = torch.tensor(weights, dtype=torch.float)

# 6) Metrics
accuracy = evaluate.load("accuracy")
f1 = evaluate.load("f1")  # macro F1 is useful for imbalance

def compute_metrics(eval_pred):
    logits, labels = eval_pred
    preds = logits.argmax(-1)
    return {
        "accuracy": accuracy.compute(predictions=preds, references=labels)["accuracy"],
        "f1_macro": f1.compute(predictions=preds, references=labels, average="macro")["f1"],
        "f1_weighted": f1.compute(predictions=preds, references=labels, average="weighted")["f1"],
    }

# 7) Model with custom weighted loss (handles imbalance during training)
class WeightedTrainer(Trainer):
    def compute_loss(self, model, inputs, return_outputs=False):
        labels = inputs.get("labels")
        outputs = model(**inputs)
        logits = outputs.get("logits")
        loss_fct = torch.nn.CrossEntropyLoss(weight=class_weights.to(logits.device))
        loss = loss_fct(logits.view(-1, logits.size(-1)), labels.view(-1))
        return (loss, outputs) if return_outputs else loss

model = AutoModelForSequenceClassification.from_pretrained(
    model_ckpt,
    num_labels=num_labels,
    id2label=id2label,
    label2id=label2id
)

# 8) Training args
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

trainer.train()

# 9) Evaluate on test
test_metrics = trainer.evaluate(data_tok["test"])
print(test_metrics)

# 10) Inference examples
from transformers import pipeline
clf = pipeline("text-classification", model=trainer.model, tokenizer=tokenizer, top_k=None)
examples = [
    "Company posts record profits but announces layoffs amid restructuring.",
    "Local team clinches the championship in a stunning final."
]
print(clf(examples))  # returns [{'label': 'Cat_X', 'score': ...}, ...]
