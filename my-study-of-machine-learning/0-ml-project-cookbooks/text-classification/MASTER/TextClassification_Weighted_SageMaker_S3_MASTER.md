Absolutely—here’s a SageMaker-ready version that reads **one or many CSVs directly from S3**, trains with your **weighted trainer**, and includes the **confusion matrix**, **per-example loss**, and **save/reload + pipeline inference** just like your laptop script.

**Download the script:**
[TextClassification_Weighted_SageMaker_S3.py](sandbox:/mnt/data/TextClassification_Weighted_SageMaker_S3.py)

### How to use (SageMaker Studio or Notebook Instance)

1. Make sure the kernel has these (most Studio images do, but you can `pip install` if needed):

   ```
   pip install -q datasets transformers evaluate s3fs boto3 scikit-learn matplotlib
   ```
2. Set env vars (or edit the CONFIG block at the top of the script):

   ```bash
   export S3_BUCKET=your-bucket
   export S3_INPUT_PREFIX=datasets/textclf/train   # folder containing CSVs
   export S3_OUTPUT_PREFIX=outputs/textclf         # where to upload artifacts
   # Optional offline model:
   export USE_OFFLINE_MODEL=true
   export OFFLINE_MODEL_S3_URI=s3://your-bucket/models/distilbert-base-uncased
   # Optional tuning:
   export TRAIN_BS=16 EVAL_BS=32 NUM_EPOCHS=3 LEARNING_RATE=2e-5
   ```
3. Run it:

   ```
   python TextClassification_Weighted_SageMaker_S3.py
   ```

### What it does

* Loads **all** `s3://$S3_BUCKET/$S3_INPUT_PREFIX/*.csv` (requires matching headers across files).
* Auto-renames common columns (`Category`→`label`, `Text`→`text`) when needed.
* Builds a stratified **80/10/10** split and encodes labels.
* Uses **class-weighted loss** for imbalance.
* Computes metrics (**accuracy, macro-F1, weighted-F1**).
* Plots and saves a **normalized confusion matrix** (validation).
* Computes **per-example validation loss**, writes top **hardest/easiest** CSVs.
* Saves model + tokenizer to `OUTPUT_DIR/best/`, runs a mini pipeline inference demo.
* Optionally uploads artifacts (PNG, CSVs, metrics JSON) to `s3://$S3_BUCKET/$S3_OUTPUT_PREFIX`.

### Instance tips (your options: m3, m5.xlarge, m5.4xlarge)

* **m3**: legacy gen; stick to smaller batch sizes (e.g., `TRAIN_BS=8`, `EVAL_BS=16`).
* **m5.xlarge (4 vCPU, 16 GB RAM)**: `TRAIN_BS=16`, `EVAL_BS=32` is a good start.
* **m5.4xlarge (16 vCPU, 64 GB RAM)**: you can bump to `TRAIN_BS=24–32`, `EVAL_BS=48–64` if memory permits.

If your environment blocks outbound access to Hugging Face, set `USE_OFFLINE_MODEL=true` and point `OFFLINE_MODEL_S3_URI` to a model snapshot you uploaded earlier (e.g., your local DistilBERT copy). Otherwise it will fetch `distilbert-base-uncased` as usual.

Want this integrated as a **SageMaker Training Job** with the SDK (Estimator/entry point) next? I can generate the training script + launcher as well, but this file mirrors your laptop flow closely.
