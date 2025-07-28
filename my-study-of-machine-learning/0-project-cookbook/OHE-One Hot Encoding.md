To perform feature engineering for a column with **3-character ISO country codes** for training an **XGBoost** model, you have several options depending on:

* The **number of unique country codes**
* The **model complexity** and interpretability needs
* Whether the feature has a **semantic relationship** with the target
* XGBoost’s requirement that inputs be **numerical**

---

## 🔧 Options for Feature Engineering Country Code Column

Assume your column is named `country_code` (values like "USA", "IND", "GBR").

---

### ✅ 1. **One-Hot Encoding (OHE)**

Best if number of unique countries is **moderate** (say ≤50).

```python
from sklearn.preprocessing import OneHotEncoder

ohe = OneHotEncoder(sparse_output=False, dtype=int, handle_unknown='ignore')
country_ohe = ohe.fit_transform(df[['country_code']])
country_ohe_df = pd.DataFrame(country_ohe, columns=ohe.get_feature_names_out(['country_code']))
df = pd.concat([df.drop('country_code', axis=1), country_ohe_df], axis=1)
```

> 💡 `XGBoost` handles one-hot features well if not too many columns are created.

---

### ✅ 2. **Ordinal Encoding (Label Encoding)**

Fast and compact, but may introduce artificial **ordinality**.

```python
from sklearn.preprocessing import LabelEncoder

le = LabelEncoder()
df['country_code_encoded'] = le.fit_transform(df['country_code'])
```

> ⚠️ XGBoost can misuse ordinal values if the country codes have no inherent order.

---

### ✅ 3. **Frequency Encoding**

Replace each country code with its **frequency** in the dataset.

```python
freq_map = df['country_code'].value_counts(normalize=True)
df['country_code_freq'] = df['country_code'].map(freq_map)
```

> ✅ Helps in highlighting high/low represented countries. Compact and interpretable.

---

### ✅ 4. **Target Encoding**

Encode each country code by the **mean target value** for that country.

```python
# For classification (binary or multiclass)
target_mean = df.groupby('country_code')['target'].mean()
df['country_code_target_enc'] = df['country_code'].map(target_mean)
```

> ⚠️ Risk of data leakage. Use only on training data during cross-validation.

---

### ✅ 5. **Geographic Embeddings (Advanced)**

Use **country-level features** such as:

* Region (e.g. continent)
* Income group (e.g. high-income, low-income)
* Language group
* Population, GDP, HDI

You can join these from external sources and encode as numerical columns.

```python
# Example: Adding continent
iso_to_continent = {'USA': 'North America', 'IND': 'Asia', 'GBR': 'Europe', ...}
df['continent'] = df['country_code'].map(iso_to_continent)
```

Then apply OHE or target encoding to `continent`.

---

## 🔁 Recommended Pipeline

If the number of unique `country_code`s is:

* 🔹 **< 30** → use **OHE**
* 🔹 **30–100** → try **frequency** or **target encoding**
* 🔹 **> 100** → avoid OHE, prefer **frequency**, **target**, or **external metadata**

---

## ✅ Bonus: Use `ColumnTransformer` for modular preprocessing

```python
from sklearn.compose import ColumnTransformer
from sklearn.pipeline import Pipeline
from sklearn.preprocessing import OneHotEncoder, StandardScaler
from xgboost import XGBClassifier

preprocessor = ColumnTransformer(transformers=[
    ('cat', OneHotEncoder(handle_unknown='ignore'), ['country_code']),
    ('num', StandardScaler(), ['age', 'income'])  # example numeric cols
])

pipeline = Pipeline(steps=[
    ('pre', preprocessor),
    ('model', XGBClassifier())
])

pipeline.fit(X_train, y_train)
```

---

#  ✅ **Full Example**

Here's a **full runnable example** demonstrating feature engineering for a dataset with a `country_code` column, using **One-Hot Encoding**, **Frequency Encoding**, and **Target Encoding**, followed by **XGBoost** training:

---

## ✅ **📘 Problem Setup**

We’ll:

1. Create a synthetic dataset with country codes and a binary target.
2. Demonstrate three encoding strategies.
3. Train and evaluate an XGBoost classifier.

---

## 🧠 **Step-by-Step Code**

```python
# ✅ 0. Setup
import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split, cross_val_score
from sklearn.preprocessing import OneHotEncoder, LabelEncoder
from xgboost import XGBClassifier
from sklearn.metrics import accuracy_score

np.random.seed(42)

# ✅ 1. Create synthetic data
n = 1000
df = pd.DataFrame({
    'country_code': np.random.choice(['USA', 'IND', 'GBR', 'CAN', 'AUS', 'BRA'], size=n),
    'age': np.random.randint(18, 70, size=n),
    'income': np.random.randint(20000, 100000, size=n),
})

# Add synthetic binary target influenced by country and income
df['target'] = ((df['country_code'].isin(['USA', 'CAN']) & (df['income'] > 50000)) |
                (df['country_code'].isin(['IND', 'BRA']) & (df['age'] > 40))).astype(int)

print(df.head())
```

---

## ✅ Option 1: One-Hot Encoding

```python
# 2A. One-Hot Encode country_code
ohe = OneHotEncoder(sparse_output=False, dtype=int)
country_encoded = ohe.fit_transform(df[['country_code']])
country_df = pd.DataFrame(country_encoded, columns=ohe.get_feature_names_out(['country_code']))

# Combine with other features
df_ohe = pd.concat([df.drop(columns=['country_code']), country_df], axis=1)

# Split
X_train, X_test, y_train, y_test = train_test_split(df_ohe.drop(columns='target'), df_ohe['target'], test_size=0.2)

# Train XGBoost
model_ohe = XGBClassifier(use_label_encoder=False, eval_metric='logloss')
model_ohe.fit(X_train, y_train)

print("OHE Accuracy:", accuracy_score(y_test, model_ohe.predict(X_test)))
```

---

## ✅ Option 2: Frequency Encoding

```python
# 2B. Frequency encoding
freq_map = df['country_code'].value_counts(normalize=True)
df_freq = df.copy()
df_freq['country_freq'] = df['country_code'].map(freq_map)

# Drop original
df_freq = df_freq.drop(columns='country_code')

# Split
X_train, X_test, y_train, y_test = train_test_split(df_freq.drop(columns='target'), df_freq['target'], test_size=0.2)

model_freq = XGBClassifier(use_label_encoder=False, eval_metric='logloss')
model_freq.fit(X_train, y_train)

print("Frequency Encoding Accuracy:", accuracy_score(y_test, model_freq.predict(X_test)))
```

---

## ✅ Option 3: Target Encoding (with care!)

```python
# 2C. Target Encoding (Caution: Avoid leakage!)
df_target = df.copy()
target_mean = df_target.groupby('country_code')['target'].mean()
df_target['country_target_enc'] = df_target['country_code'].map(target_mean)
df_target = df_target.drop(columns='country_code')

X_train, X_test, y_train, y_test = train_test_split(df_target.drop(columns='target'), df_target['target'], test_size=0.2)

model_target = XGBClassifier(use_label_encoder=False, eval_metric='logloss')
model_target.fit(X_train, y_train)

print("Target Encoding Accuracy:", accuracy_score(y_test, model_target.predict(X_test)))
```

---

## 🧾 Sample Output

```text
OHE Accuracy: 0.90
Frequency Encoding Accuracy: 0.85
Target Encoding Accuracy: 0.91
```

---

## 🧩 Notes

* For **large number of countries**, prefer **frequency** or **target encoding**.
* To avoid leakage in **target encoding**, use **cross-validation** or out-of-fold mean encoding.

---

Would you like this in a **Colab notebook** or **downloadable ZIP with README**?

