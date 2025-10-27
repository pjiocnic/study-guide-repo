
```py
from transformers import TrainingArguments, Trainer, AutoTokenizer, AutoModelForSequenceClassification
from transformers.trainer_utils import get_last_checkpoint

# 1) Locate your last checkpoint
ckpt = get_last_checkpoint("bert_base_train_dir")
print("Resuming from:", ckpt)

# 2) Rebuild model/tokenizer exactly as before
model_ckpt = "distilbert-base-uncased"   # <- whatever you used originally
num_labels = 5                           # <- your task
tokenizer = AutoTokenizer.from_pretrained(model_ckpt, use_fast=True)
model = AutoModelForSequenceClassification.from_pretrained(model_ckpt, num_labels=num_labels)

# 3) Rebuild TrainingArguments (you can fix & improve them here)
training_args = TrainingArguments(
    output_dir="bert_base_train_dir",
    overwrite_output_dir=False,          # <- IMPORTANT: don't wipe the checkpoints
    num_train_epochs=3,                  # <- e.g., extend training beyond where you left off
    learning_rate=2e-5,                  # <- will be ignored if optimizer state is loaded (see note below)
    per_device_train_batch_size=64,
    per_device_eval_batch_size=64,
    weight_decay=0.01,
    evaluation_strategy="epoch",         # <- fix the arg name
    save_strategy="epoch",               # <- ensure future checkpoints
    save_total_limit=2,
    disable_tqdm=False,
    report_to=[],
)

# 4) Rebuild datasets/collator/metrics just like before
# train_dataset = ...
# eval_dataset  = ...
# data_collator = ...
# def compute_metrics(p): ...

trainer = Trainer(
    model=model,
    args=training_args,
    train_dataset=train_dataset,
    eval_dataset=eval_dataset,
    tokenizer=tokenizer,
    data_collator=data_collator,
    compute_metrics=compute_metrics,
)

# 5) Resume
trainer.train(resume_from_checkpoint=ckpt)
```