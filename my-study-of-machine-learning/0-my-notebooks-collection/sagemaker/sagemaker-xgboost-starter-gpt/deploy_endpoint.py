from sagemaker import Predictor

predictor = xgb_estimator.deploy(
    initial_instance_count=1,
    instance_type="ml.m5.large"
)
print("Endpoint deployed at:", predictor.endpoint_name)