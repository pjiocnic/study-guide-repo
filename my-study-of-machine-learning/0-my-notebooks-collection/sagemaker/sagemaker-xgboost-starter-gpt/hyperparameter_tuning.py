from sagemaker.tuner import HyperparameterTuner, IntegerParameter, ContinuousParameter
from sagemaker.inputs import TrainingInput
import sagemaker
from sagemaker.estimator import Estimator

session = sagemaker.Session()
role = sagemaker.get_execution_role()
bucket = "your-bucket-name"
prefix = "xgboost-demo"

xgb = Estimator(
    image_uri=sagemaker.image_uris.retrieve("xgboost", session.boto_region_name, version="1.3-1"),
    role=role,
    instance_count=1,
    instance_type="ml.m5.large",
    output_path=f"s3://{bucket}/{prefix}/output",
    sagemaker_session=session,
)

xgb.set_hyperparameters(objective="binary:logistic", num_round=100)

hyperparameter_ranges = {
    "max_depth": IntegerParameter(3, 10),
    "eta": ContinuousParameter(0.1, 0.5),
    "gamma": ContinuousParameter(0, 5),
}

tuner = HyperparameterTuner(
    estimator=xgb,
    objective_metric_name="validation:auc",
    hyperparameter_ranges=hyperparameter_ranges,
    max_jobs=5,
    max_parallel_jobs=2,
    objective_type="Maximize"
)

train_input = TrainingInput(f"s3://{bucket}/{prefix}/train.csv", content_type="csv")
tuner.fit({"train": train_input})