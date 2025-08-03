import boto3
runtime = boto3.client("runtime.sagemaker")
endpoint_name = "your-endpoint-name"
payload = "1.0,2.0"
response = runtime.invoke_endpoint(
    EndpointName=endpoint_name,
    ContentType="text/csv",
    Body=payload
)
print("Prediction result:", response["Body"].read().decode())