
import json, joblib, numpy as np

def model_fn(model_dir):
    return joblib.load(f"{model_dir}/vectorizer.pkl")

def input_fn(request_body, content_type):
    if "application/json" in content_type or "json" in content_type:
        payload = json.loads(request_body)
        if isinstance(payload, dict) and "instances" in payload:
            return [str(t) for t in payload["instances"]]
        if isinstance(payload, list):
            return [str(t) for t in payload]
    raise ValueError("Unsupported content_type or payload format.")

def predict_fn(texts, vectorizer):
    X = vectorizer.transform([str(t) for t in texts])
    return X.astype(np.float32).toarray()

def output_fn(preds, accept):
    return json.dumps({"instances": preds.tolist()}), "application/json"
