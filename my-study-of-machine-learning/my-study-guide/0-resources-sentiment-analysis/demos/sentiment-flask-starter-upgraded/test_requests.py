
import requests

url = "http://localhost:5000/predict"
headers = {
    "Content-Type": "application/json",
    "x-api-key": "your-secure-api-key"
}
payload = {
    "text": [
        "Awesome product!",
        "Terrible experience.",
        "It was just okay."
    ]
}

response = requests.post(url, json=payload, headers=headers)
print(response.json())
