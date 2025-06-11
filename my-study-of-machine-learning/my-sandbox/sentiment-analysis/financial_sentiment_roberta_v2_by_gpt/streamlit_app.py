import streamlit as st
from inference import predict_sentiment

st.title("Financial Sentiment Classifier (RoBERTa)")

text = st.text_area("Enter financial news headline:")

if st.button("Predict"):
    label, probs = predict_sentiment(text)
    st.write(f"**Prediction:** {label}")
    st.write(f"**Probabilities:** {probs}")