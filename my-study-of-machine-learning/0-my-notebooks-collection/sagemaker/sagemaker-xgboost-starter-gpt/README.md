# SageMaker XGBoost Starter

## Features
- Upload CSV to S3
- Train using built-in XGBoost container
- Hyperparameter tuning
- Model deployment and invocation
- Cleanup script

## Usage
1. Edit bucket name in scripts
2. Run `s3_upload.py`
3. Train with `xgboost_estimator.py` or `hyperparameter_tuning.py`
4. Deploy with `deploy_endpoint.py`
5. Predict with `invoke_endpoint.py`
6. Clean up with `cleanup.py`
