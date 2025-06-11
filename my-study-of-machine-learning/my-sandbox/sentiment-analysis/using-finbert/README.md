Here's your updated FinBERT version:

📦 [Download financial\_sentiment\_finbert.zip](sandbox:/mnt/data/financial_sentiment_finbert.zip)

### ✅ What's Included

| File                                 | Description                                                       |
| ------------------------------------ | ----------------------------------------------------------------- |
| `financial_classifier_finbert.ipynb` | Colab-ready notebook to run FinBERT predictions on financial text |
| `inference.py`                       | Inference script using pretrained `yiyanghkust/finbert-tone`      |
| `streamlit_app.py`                   | Streamlit app to input headlines and view sentiment prediction    |
| `README.md`                          | Overview of labels and instructions                               |

---

### 🚀 To Run the Streamlit App

```bash
# Step 1: Unzip
unzip financial_sentiment_finbert.zip
cd financial_sentiment_roberta  # directory name remains same internally

# Step 2: Install dependencies
pip install transformers streamlit

# Step 3: Run the app
streamlit run streamlit_app.py
```

Let me know if you’d like:

* Docker container setup
* Integration with MongoDB or logging system
* A combined model for sentiment + relevance using FinBERT + another classifier

Ready when you are!
