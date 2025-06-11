
from transformers import pipeline

def load_model():
    sentiment_pipeline = pipeline(
        "sentiment-analysis",
        model="distilbert-base-uncased-finetuned-sst-2-english",
        device_map="auto"  # automatically use GPU if available
    )
    return sentiment_pipeline
