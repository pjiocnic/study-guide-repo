Upload local DistilBERT (or any HF model) to S3

Outputs:
- upload_distilbert_to_s3.ipynb  (Notebook: configure and run cells)
- upload_distilbert_to_s3.py     (Script: run from terminal)
- This README

Notebook steps:
1) Set CONFIG values (LOCAL_MODEL_DIR, S3_BUCKET, MODEL_S3_PREFIX).
2) Run cells to upload all files under LOCAL_MODEL_DIR to s3://<bucket>/<MODEL_S3_PREFIX>.

Script usage:
python upload_distilbert_to_s3.py --local_dir /path/to/distilbert-base-uncased --bucket your-bucket --prefix models/distilbert-base-uncased/ --region us-east-1

After uploading, in your SageMaker DistilBERT notebook:
- Set MODEL_MODE='offline'
- Set MODEL_S3_PREFIX to the same prefix you used here.
