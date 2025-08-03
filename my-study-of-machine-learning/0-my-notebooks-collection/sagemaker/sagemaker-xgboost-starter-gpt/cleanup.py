import boto3
sm = boto3.client("sagemaker")
endpoint_name = "your-endpoint-name"
sm.delete_endpoint(EndpointName=endpoint_name)
print("Deleted endpoint:", endpoint_name)