#!/usr/bin/env python
# -*- coding: utf-8 -*-
# DistilBERT text classification (scalable, multi-CSV) with class weights
# - Cleans (stringify/strip), drops NaN, optional dedup (skipped by default for scale)
# - Stratified 80/10/10 split
# - GPU auto-detect/use + throughput tweaks (bf16/fp16, grad checkpointing, fused AdamW)
# - Per-example loss from trainer.predict
# - Confusion matrix + hardest/easiest rows (saved to files)
# - Save/reload + quick pipeline demo
#
# USAGE (examples):
#   python DistilBERT_TextClassification_Scalable_A2000_8G.py \
#     --csv_glob "data/*.csv" \
#     --max_length 128 --train_batch 24 --grad_accum 4 --epochs 2 --bf16 \
#     --gradient_checkpointing --eval_steps 1000 --save_steps 1000
#
#   If your CSVs do NOT have a header row:
#   ... --no_header --label_col Category --text_col Text
#
#   For very large datasets (>= 1M rows), dedup is optional (default off).
#   If needed, enable pandas-based dedup (can be RAM heavy):
#   ... --dedup_mode pandas
#
# Notes:
# - Defaults fit well on an NVIDIA RTX A2000 Ada 8 GB for DistilBERT.
# - If you scale to about 1.5M rows, keep --max_length 128, consider --epochs 1 or 2,
#   and tune eval/save steps to reduce overhead.

import os
import re
import glob
import argparse
import random
import numpy as np
import pandas as pd
import torch
import matplotlib.pyplot as plt

from datasets import load_dataset, Dataset, DatasetDict, concatenate_datasets
from transformers import (
    AutoTokenizer, AutoModelForSequenceClassification,
    DataCollatorWithPadding, TrainingArguments, Trainer, pipeline, EarlyStoppingCallback
)
import evaluate
from sklearn.metrics import ConfusionMatrixDisplay, confusion_matrix

# ----------------------------
# CLI
# ----------------------------
def build_arg_parser():
    p = argparse.ArgumentParser(description="Scalable DistilBERT text classification with class weights")
    p.add_argument("--csv_glob", type=str, required=True,
                   help="Glob for CSV files (e.g., 'data/*.csv') or comma-separated paths")
    p.add_argument("--no_header", action="store_true", help="Set if CSVs have no header row")
    p.add_argument("--label_col", type=str, default="label", help="Name of label column when header exists or for renaming")
    p.add_argument("--text_col", type=str, default="text", help="Name of text column when header exists or for renaming")

    # Model / tokenization
    p.add_argument("--model_ckpt", type=str, default="distilbert-base-uncased")
    p.add_argument("--max_length", type=int, default=128)
    p.add_argument("--use_fast_tokenizer", action="store_true", help="Force fast tokenizer (default True for DistilBERT)")

    # Training scale knobs
    p.add_argument("--train_batch", type=int, default=24)
    p.add_argument("--eval_batch", type=int, default=32)
    p.add_argument("--grad_accum", type=int, default=4)
    p.add_argument("--epochs", type=int, default=2)
    p.add_argument("--lr", type=float, default=2e-5)
    p.add_argument("--weight_decay", type=float, default=0.01)
    p.add_argument("--warmup_ratio", type=float, default=0.03)
    p.add_argument("--scheduler", type=str, default="cosine",
                   choices=["linear", "cosine", "polynomial", "cosine_with_restarts"])

    # Mixed precision & memory
    p.add_argument("--bf16", action="store_true", help="Use bfloat16 if supported (preferred on Ada)")
    p.add_argument("--fp16", action="store_true", help="Use float16 if bf16 not used/available")
    p.add_argument("--gradient_checkpointing", action="store_true")
    p.add_argument("--group_by_length", action="store_true", help="Group batches by length to reduce padding")
    p.add_argument("--pad_mult_8", action="store_true", help="Pad to multiple of 8 for Tensor Cores (recommended)")

    # Eval/save/early stop
    p.add_argument("--eval_steps", type=int, default=1000)
    p.add_argument("--save_steps", type=int, default=1000)
    p.add_argument("--save_total_limit", type=int, default=2)
    p.add_argument("--metric_for_best", type=str, default="f1_weighted")
    p.add_argument("--early_stop", action="store_true")
    p.add_argument("--early_patience", type=int, default=2)

    # Data handling
    p.add_argument("--min_text_len", type=int, default=1, help="Drop rows with len(text) < this")
    p.add_argument("--dedup_mode", type=str, default="none", choices=["none", "pandas"],
                   help="Deduplicate by (text,label). 'pandas' may be RAM heavy on huge corpora")
    p.add_argument("--sample_size", type=int, default=0,
                   help="Optional downsample N examples (after cleaning, before split) for quick tests")
    p.add_argument("--seed", type=int, default=42)

    # Output
    p.add_argument("--outdir", type=str, default="distilbert-textclf-out")
    p.add_argument("--save_dir_best", type=str, default="distilbert-textclf-best")
    p.add_argument("--report_to", type=str, default="none")
    p.add_argument("--log_steps", type=int, default=100)
    p.add_argument("--resume_from", type=str, default=None, help="Path to checkpoint to resume from")
    return p

# ----------------------------
# Utils
# ----------------------------
def set_all_seeds(seed: int):
    random.seed(seed)
    np.random.seed(seed)
    torch.manual_seed(seed)

def detect_device():
    if torch.cuda.is_available():
        dev = torch.device("cuda")
        print("✅ Using GPU:", torch.cuda.get_device_name(0))
    else:
        dev = torch.device("cpu")
        print("⚠️ GPU not detected, running on CPU")
    return dev

def collect_csv_paths(pattern: str):
    if "," in pattern:
        paths = [p.strip() for p in pattern.split(",") if p.strip()]
    else:
        paths = sorted(glob.glob(pattern))
    if not paths:
        raise FileNotFoundError(f"No CSV files matched: {pattern}")
    print(f"Found {len(paths)} CSV files.")
    return paths

def build_dataset_from_csvs(paths, no_header=False, label_col="label", text_col="text"):
    # If no header: we provide column names explicitly.
    if no_header:
        ds_list = []
        for p in paths:
            ds = load_dataset("csv", data_files=p, split="train",
                              column_names=[label_col, text_col])
            ds_list.append(ds)
        ds_raw = concatenate_datasets(ds_list) if len(ds_list) > 1 else ds_list[0]
    else:
        ds_raw = load_dataset("csv", data_files=paths, split="train")

        # If needed, try to rename columns heuristically
        cols = [c.lower() for c in ds_raw.column_names]
        rename_map = {}
        if label_col not in cols:
            # try to find likely label column
            for c in ds_raw.column_names:
                lc = c.lower()
                if lc.startswith("cat") or lc == "label":
                    rename_map[c] = label_col
                    break
        if text_col not in cols:
            for c in ds_raw.column_names:
                lc = c.lower()
                if lc.startswith("text") or lc in ("remarks", "message"):
                    rename_map[c] = text_col
                    break
        if rename_map:
            ds_raw = ds_raw.rename_columns(rename_map)

    # Keep only needed columns
    to_drop = [c for c in ds_raw.column_names if c not in (text_col, label_col)]
    if to_drop:
        ds_raw = ds_raw.remove_columns(to_drop)

    return ds_raw, label_col, text_col

def clean_and_filter(ds, label_col, text_col, min_len=1, num_proc=None):
    # ensure string + strip
    def _stringify_strip(ex):
        t = str(ex[text_col]) if ex[text_col] is not None else ""
        ex[text_col] = t.strip()
        return ex

    ds = ds.map(_stringify_strip, num_proc=num_proc)

    # drop NaNs/empties
    ds = ds.filter(lambda ex: (ex[text_col] is not None) and (ex[label_col] is not None) and (len(ex[text_col]) >= min_len))

    return ds

def maybe_dedup_pandas(ds, label_col, text_col):
    print("Performing pandas-based dedup on (text,label). This may use significant RAM...")
    df = ds.to_pandas()
    df = df.replace({"": np.nan})
    df = df.dropna(subset=[text_col, label_col]).drop_duplicates(subset=[text_col, label_col]).reset_index(drop=True)
    ds = Dataset.from_pandas(df, preserve_index=False)
    return ds

# ----------------------------
# Main
# ----------------------------
def main():
    args = build_arg_parser().parse_args()

    os.makedirs(args.outdir, exist_ok=True)
    os.makedirs(args.save_dir_best, exist_ok=True)

    set_all_seeds(args.seed)

    device = detect_device()

    try:
        torch.set_float32_matmul_precision("high")
    except Exception:
        pass

    # 1) Load CSVs
    csv_paths = collect_csv_paths(args.csv_glob)
    ds_raw, label_col, text_col = build_dataset_from_csvs(csv_paths, args.no_header, args.label_col, args.text_col)

    # 2) Clean/filter (no full pandas)
    num_proc = min(8, os.cpu_count() or 2)
    ds_clean = clean_and_filter(ds_raw, label_col, text_col, args.min_text_len, num_proc=num_proc)

    # Optional: sample for quick test
    if args.sample_size and args.sample_size > 0:
        print(f"Sampling {args.sample_size} rows for a quick run...")
        ds_clean = ds_clean.shuffle(seed=args.seed).select(range(min(args.sample_size, len(ds_clean))))

    # Optional: dedup
    if args.dedup_mode == "pandas":
        ds_clean = maybe_dedup_pandas(ds_clean, label_col, text_col)
    else:
        print("Skipping global dedup (recommended for 500k–1.5M scale).")

    # 3) Encode labels AFTER cleaning/dedup
    ds_clean = ds_clean.class_encode_column(label_col)

    # 4) Stratified split 80/10/10
    tmp = ds_clean.train_test_split(test_size=0.2, stratify_by_column=label_col, seed=args.seed)
    val_test = tmp["test"].train_test_split(test_size=0.5, stratify_by_column=label_col, seed=args.seed)
    data = DatasetDict(train=tmp["train"], validation=val_test["train"], test=val_test["test"])
    print(data)

    # names/maps
    id2label = {i: name for i, name in enumerate(data["train"].features[label_col].names)}
    label2id = {v: k for k, v in id2label.items()}

    # 5) Tokenizer
    tokenizer = AutoTokenizer.from_pretrained(
        args.model_ckpt,
        clean_up_tokenization_spaces=False,
        use_fast=args.use_fast_tokenizer or True,
        model_max_length=args.max_length
    )

    def tok(batch):
        return tokenizer(batch[text_col], truncation=True, max_length=args.max_length)

    data_tok = data.map(tok, batched=True, remove_columns=[text_col], num_proc=num_proc)

    # 6) Collator (pad to multiple of 8 for Tensor Cores)
    collator = DataCollatorWithPadding(tokenizer=tokenizer, pad_to_multiple_of=(8 if args.pad_mult_8 else None))

    # 7) Class weights from TRAIN only
    num_labels = len(id2label)
    counts = np.bincount(data_tok["train"][label_col], minlength=num_labels)
    counts = np.maximum(counts, 1)
    weights = counts.sum() / (num_labels * counts)
    class_weights = torch.tensor(weights, dtype=torch.float)

    # 8) Metrics
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

    # 9) Trainer with weighted loss
    class WeightedTrainer(Trainer):
        def compute_loss(self, model, inputs, return_outputs=False):
            labels = inputs.get("labels")
            outputs = model(**inputs)
            logits = outputs.get("logits")
            loss_fct = torch.nn.CrossEntropyLoss(weight=class_weights.to(logits.device))
            loss = loss_fct(logits.view(-1, logits.size(-1)), labels.view(-1))
            return (loss, outputs) if return_outputs else loss

    model = AutoModelForSequenceClassification.from_pretrained(
        args.model_ckpt, num_labels=num_labels, id2label=id2label, label2id=label2id
    ).to(device)

    # Mixed precision selection
    use_bf16 = False
    if args.bf16 and torch.cuda.is_available():
        cc_major, cc_minor = torch.cuda.get_device_capability(0)
        use_bf16 = (cc_major >= 8)

    use_fp16 = args.fp16 and not use_bf16 and torch.cuda.is_available()

    training_args = TrainingArguments(
        output_dir=args.outdir,
        learning_rate=args.lr,
        per_device_train_batch_size=args.train_batch,
        per_device_eval_batch_size=args.eval_batch,
        gradient_accumulation_steps=args.grad_accum,
        num_train_epochs=args.epochs,
        weight_decay=args.weight_decay,
        lr_scheduler_type=args.scheduler,
        warmup_ratio=args.warmup_ratio,

        evaluation_strategy="steps",
        save_strategy="steps",
        eval_steps=args.eval_steps,
        save_steps=args.save_steps,
        save_total_limit=args.save_total_limit,
        load_best_model_at_end=True,
        metric_for_best_model=args.metric_for_best,
        greater_is_better=True,

        logging_steps=args.log_steps,
        report_to=args.report_to,
        seed=args.seed,

        bf16=use_bf16,
        fp16=use_fp16,
        optim="adamw_torch_fused",
        dataloader_num_workers=min(8, os.cpu_count() or 2),
        dataloader_pin_memory=True,
        group_by_length=args.group_by_length,
        gradient_checkpointing=args.gradient_checkpointing,
    )

    callbacks = []
    if args.early_stop:
        callbacks.append(EarlyStoppingCallback(early_stopping_patience=args.early_patience))

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

    # 10) Train + evaluate
    trainer.train(resume_from_checkpoint=args.resume_from)
    test_metrics = trainer.evaluate(data_tok["test"])
    print("Test metrics:", test_metrics)
    print("Trainer device:", trainer.args.device)

    # 11) Predict on validation (per-example loss & CM)
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
    cm_path = os.path.join(args.outdir, "confusion_matrix_validation.png")
    plt.savefig(cm_path, dpi=150)
    print(f"Saved confusion matrix to: {cm_path}")
    plt.close(fig)

    # 12) Hardest/Easiest examples — save CSVs
    # Recover raw validation texts
    data.set_format("pandas")
    texts_val = data["validation"][:][text_col]
    df_val = pd.DataFrame({
        "text": texts_val,
        "true_label_id": pred.label_ids,
        "pred_label_id": y_preds,
        "loss": per_ex_loss
    })
    df_val["true_label"] = df_val["true_label_id"].map(id2label)
    df_val["pred_label"] = df_val["pred_label_id"].map(id2label)

    hardest_csv = os.path.join(args.outdir, "validation_top10_hardest.csv")
    easiest_csv = os.path.join(args.outdir, "validation_top10_easiest.csv")
    df_val.sort_values("loss", ascending=False).head(10).to_csv(hardest_csv, index=False)
    df_val.sort_values("loss", ascending=True).head(10).to_csv(easiest_csv, index=False)
    print(f"Saved hardest examples: {hardest_csv}")
    print(f"Saved easiest examples: {easiest_csv}")

    # 13) Save model + tokenizer + quick pipeline demo
    SAVE_DIR = args.save_dir_best
    os.makedirs(SAVE_DIR, exist_ok=True)
    trainer.save_model(SAVE_DIR)
    tokenizer.save_pretrained(SAVE_DIR)
    print(f"Saved model + tokenizer to: {SAVE_DIR}")

    tok2 = AutoTokenizer.from_pretrained(SAVE_DIR)
    mdl2 = AutoModelForSequenceClassification.from_pretrained(SAVE_DIR)
    mdl2.config.id2label = id2label
    mdl2.config.label2id = label2id

    clf = pipeline("text-classification", model=mdl2, tokenizer=tok2, device=0 if torch.cuda.is_available() else -1)
    demo_out = clf(["I love sunny mornings!", "I'm feeling really down today."])
    print("\nPipeline inference demo:", demo_out)

if __name__ == "__main__":
    main()
