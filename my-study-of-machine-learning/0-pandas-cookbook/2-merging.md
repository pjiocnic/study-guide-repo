Absolutely! Here are clean, consolidated **code snippets** for both scenarios:

---

## ✅ **Scenario 1: Add a new column to a DataFrame**

```python
import pandas as pd

# Original DataFrame
df = pd.DataFrame({
    'name': ['Alice', 'Bob', 'Charlie'],
    'score': [85, 70, 95]
})

# 1️⃣ Add a new column with a constant value
df['category'] = 'student'

# 2️⃣ Add a new column based on a condition
df['grade'] = df['score'].apply(lambda x: 'Pass' if x >= 75 else 'Fail')

print(df)
```

---

## ✅ **Scenario 2: Merge (union) two DataFrames with different columns**

```python
import pandas as pd

# First DataFrame
df1 = pd.DataFrame({
    'id': [1, 2],
    'name': ['Alice', 'Bob']
})

# Second DataFrame with different columns
df2 = pd.DataFrame({
    'id': [3, 4],
    'score': [85, 90]
})

# Merge (union) the two DataFrames with all columns; fill missing with NaN
merged_df = pd.concat([df1, df2], ignore_index=True)

print(merged_df)
```

---

Let me know if you want:

* 🧼 NaNs replaced with default values (e.g., `'unknown'`, `0`)
* 🏷 A column indicating source (`'from_df1'` or `'from_df2'`)
