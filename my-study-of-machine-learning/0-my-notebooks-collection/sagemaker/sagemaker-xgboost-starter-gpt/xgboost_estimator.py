from sagemaker.estimator import Estimator
import sagemaker
from sagemaker.inputs import TrainingInput

role = sagemaker.get_execution_role()
session = sagemaker.Session()
bucket = "your-bucket-name"
prefix = "xgboost-demo"

xgb_estimator = Estimator(
    image_uri=sagemaker.image_uris.retrieve("xgboost", session.boto_region_name, version="1.3-1"),
    role=role,
    instance_count=1,
    instance_type="ml.m5.large",
    output_path=f"s3://{bucket}/{prefix}/output",
    sagemaker_session=session,
)

xgb_estimator.set_hyperparameters(objective="binary:logistic", num_round=50)
train_input = TrainingInput(f"s3://{bucket}/{prefix}/train.csv", content_type="csv")
xgb_estimator.fit({"train": train_input})