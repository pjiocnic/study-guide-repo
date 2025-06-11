
# Sentiment Analysis API Starter (Flask + DistilBERT + Production Upgrades)

## Architecture

Client -> POST /predict (with API Key) -> Flask Server -> DistilBERT Model -> Prediction -> JSON Response

## Features
- Single or batch text prediction
- Simple API Key Authentication
- Hugging Face DistilBERT sentiment model
- Optimized for GPU/CPU auto selection
- Dockerized app ready for deployment
- Example client request script

## How to Run Locally

1. Install dependencies:
```bash
pip install -r requirements.txt
```

2. Set your API Key in `app.py`:
```python
API_KEY = "your-secure-api-key"
```

3. Start the app:
```bash
python app.py
```

Visit: http://localhost:5000/predict

## How to Test

Single text:
```bash
curl -X POST http://localhost:5000/predict -H "Content-Type: application/json" -H "x-api-key: your-secure-api-key" -d '{"text":"I love this product!"}'
```

Batch texts:
```bash
curl -X POST http://localhost:5000/predict -H "Content-Type: application/json" -H "x-api-key: your-secure-api-key" -d '{"text":["I love it", "I hate it", "It's okay"]}'
```

## Docker Instructions

1. Build the image:
```bash
docker build -t sentiment-flask .
```

2. Run the container:
```bash
docker run -p 5000:5000 sentiment-flask
```

## Test with Python

```python
import requests

url = "http://localhost:5000/predict"
headers = {
    "Content-Type": "application/json",
    "x-api-key": "your-secure-api-key"
}
payload = {
    "text": [
        "I am happy.",
        "This is terrible.",
        "Not bad, could be better."
    ]
}
response = requests.post(url, json=payload, headers=headers)
print(response.json())
```

## Best Practices
- Load model once
- Use API keys
- Support both single and batch predictions
- Use Docker for deployment

## Future Enhancements
- Swagger/OpenAPI documentation
- Caching with Redis
- Accelerate inference using Hugging Face optimum
- Deploy to cloud (AWS, Azure, GCP)
