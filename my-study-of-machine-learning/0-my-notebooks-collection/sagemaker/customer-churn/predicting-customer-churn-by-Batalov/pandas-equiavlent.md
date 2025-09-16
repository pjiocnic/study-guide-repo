## Transforming Amazon ML recipe used to transform data to Pandas way

```json
{
  "groups": {
    "NUMERIC_VARS_NORM": "group('Intl_Charge','Night_Calls','Day_Calls','Eve_Calls','Eve_Mins','Intl_Mins','VMail_Message','Intl_Calls','Day_Mins','Night_Mins','Day_Charge','Night_Charge','Eve_Charge','Account_Length')"
  },
  "assignments": {},
  "outputs": [
    "ALL_BINARY",
    "State",
    "Area_Code",
    "normalize(NUMERIC_VARS_NORM)",
    "CustServ_Calls"
  ]
}

```

### What the recipe does

* **Groups**: `NUMERIC_VARS_NORM` = list of numeric columns to **z-score normalize**.
* **Outputs**: keep **all binary columns** (`ALL_BINARY`), keep `State` and `Area_Code` as-is, add **normalized** numeric group, and keep `CustServ_Calls` raw.

### Pandas / scikit-learn version

```python
import pandas as pd
import numpy as np
from sklearn.preprocessing import StandardScaler
import joblib

# 1) Normalize the same numeric group as in the recipe
NUMERIC_VARS_NORM = [
    "Intl_Charge","Night_Calls","Day_Calls","Eve_Calls","Eve_Mins","Intl_Mins",
    "VMail_Message","Intl_Calls","Day_Mins","Night_Mins","Day_Charge",
    "Night_Charge","Eve_Charge","Account_Length"
]

# 2) Helper: tidy column names to snake_case so they match the recipe keys
def clean_cols(df: pd.DataFrame) -> pd.DataFrame:
    out = df.copy()
    out.columns = (out.columns.str.strip()
                   .str.replace(r"[^0-9a-zA-Z]+", "_", regex=True)
                   .str.replace(r"_+", "_", regex=True)
                   .str.strip("_"))
    # "Churn?" -> "Churn"
    if "Churn_" in out.columns: out = out.rename(columns={"Churn_": "Churn"})
    return out

# 3) Helper: detect & map binary columns (yes/no/true/false/0/1) EXCEPT the label "Churn"
def detect_binary_cols(df: pd.DataFrame) -> list[str]:
    bins = []
    for c in df.columns:
        if c == "Churn":                      # label stays out of features
            continue
        if df[c].dtype == bool:
            bins.append(c)
            continue
        vals = set(df[c].dropna().astype(str).str.lower().unique())
        if vals <= {"yes","no","true","false","t","f","0","1"}:
            bins.append(c)
    return bins

def map_yes_no_to01(s: pd.Series) -> pd.Series:
    return (s.astype(str).str.lower()
              .map({"yes":1,"true":1,"t":1,"1":1,"no":0,"false":0,"f":0,"0":0})
              .astype(int))

# 4) Core: replicate the recipe
def make_features(df: pd.DataFrame, scaler: StandardScaler|None=None):
    df = clean_cols(df)

    # Binary features (ALL_BINARY)
    binary_cols = detect_binary_cols(df)
    for c in binary_cols:
        if df[c].dtype != bool and not np.issubdtype(df[c].dtype, np.number):
            df[c] = map_yes_no_to01(df[c])

    # Label (optional, if present)
    y = None
    if "Churn" in df.columns:
        y = map_yes_no_to01(df["Churn"])

    # Fit scaler on TRAIN ONLY; reuse for val/test
    if scaler is None:
        scaler = StandardScaler().fit(df[NUMERIC_VARS_NORM])
    df_norm = df.copy()
    df_norm[NUMERIC_VARS_NORM] = scaler.transform(df[NUMERIC_VARS_NORM])

    # Outputs per recipe:
    #   ALL_BINARY + State + Area_Code + normalize(NUMERIC_VARS_NORM) + CustServ_Calls
    keep_cols = binary_cols + ["State","Area_Code"] + NUMERIC_VARS_NORM + ["CustServ_Calls"]
    # Only keep columns that actually exist (defensive)
    keep_cols = [c for c in keep_cols if c in df_norm.columns]
    X = df_norm[keep_cols].copy()

    return X, y, scaler

# ---- Example usage in a SageMaker notebook/script ---------------------------
# train_df = pd.read_csv("/opt/ml/input/data/train/churn.csv")
# test_df  = pd.read_csv("/opt/ml/input/data/test/churn.csv")

# X_train, y_train, scaler = make_features(train_df, scaler=None)
# X_test,  y_test,  _      = make_features(test_df,  scaler=scaler)

# Persist the scaler for inference
# joblib.dump(scaler, "/opt/ml/model/num_scaler.joblib")

# If your downstream model needs numeric-only features, you can one-hot encode:
# X_train = pd.get_dummies(X_train, columns=["State","Area_Code"], drop_first=False)
# X_test  = pd.get_dummies(X_test,  columns=["State","Area_Code"], drop_first=False)
# X_test  = X_test.reindex(columns=X_train.columns, fill_value=0)
```

**Notes**

* The `StandardScaler` step reproduces `normalize(NUMERIC_VARS_NORM)` (z-score).
* `ALL_BINARY` is implemented by auto-detecting yes/no style columns and mapping them to 0/1.
* `State`, `Area_Code`, and `CustServ_Calls` are passed through as in the recipe.
* Most models (e.g., XGBoost) need numeric inputs—use the commented **`get_dummies`** block to one-hot `State`/`Area_Code` if you’re training such a model.
