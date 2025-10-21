Minimal Text Classification Notebooks

Files:
- tfidf_xgboost_minimal.ipynb
- distilbert_minimal.ipynb

Input:
- Provide a CSV file with columns: text,label
- Update CSV_PATH in each notebook to point to your file.

TF-IDF + XGBoost:
- Uses a minimal gensim cleaner (simple_preprocess + STOPWORDS)
- Stratified split, TF-IDF (fit on train), class-weighted training
- Saves: xgb_tfidf_model.joblib, tfidf_vectorizer.joblib, label_encoder.joblib

DistilBERT:
- Keeps text raw (only trims/filters empties)
- Stratified split, tokenization via AutoTokenizer
- Evaluates accuracy + macro-F1
- Saves: distilbert_clf/best (model+tokenizer), label_map.csv
- Offline? Set MODEL to a local path with the weights/tokenizer

Tip:
- For imbalanced data, monitor macro-F1.
- For XGB + GPU: set tree_method='gpu_hist' if your build supports CUDA.
- For very long texts, consider truncation at 256-512 tokens for BERT.
