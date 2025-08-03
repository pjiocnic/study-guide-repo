[https://machinelearningmastery.com/data-preparation-gradient-boosting-xgboost-python/](https://machinelearningmastery.com/data-preparation-gradient-boosting-xgboost-python/)

# 1. Encode target labels using Label Encoders

Use Label Encoder on iris dataset see 0-demo-notebooks/xgboost-dataprep/label_encoding_colab.ipynb. The three class values (`Iris-setosa, Iris-versicolor, Iris-virginica`) are mapped to the integer values (0, 1, 2).

# 2. categorical data

Use OneHotEncoder or Ordinal Encoder. See 0-demo-notebooks/xgboost-dataprep/One_Hot_Encode_Categorical_Data_Colab.ipynb

# 3. Missing Data

- How XGBoost Handles misisng data - https://arxiv.org/abs/1603.02754
- Technique 1: Impuet with 0s
- Technique 2: Impute with Mean