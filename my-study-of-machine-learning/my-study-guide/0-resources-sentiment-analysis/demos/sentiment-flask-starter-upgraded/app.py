
from flask import Flask, request, jsonify
from model_loader import load_model

app = Flask(__name__)
sentiment_pipeline = load_model()

# Define your API key
API_KEY = "your-secure-api-key"

@app.route("/predict", methods=["POST"])
def predict():
    api_key = request.headers.get("x-api-key")
    if api_key != API_KEY:
        return jsonify({"error": "Invalid or missing API key"}), 401

    data = request.get_json()
    texts = data.get("text", "")

    if not texts:
        return jsonify({"error": "Text field is required"}), 400

    if isinstance(texts, str):
        predictions = sentiment_pipeline(texts)
    elif isinstance(texts, list):
        predictions = sentiment_pipeline(texts)
    else:
        return jsonify({"error": "Invalid format for 'text'. Should be a string or list."}), 400

    return jsonify(predictions)

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
