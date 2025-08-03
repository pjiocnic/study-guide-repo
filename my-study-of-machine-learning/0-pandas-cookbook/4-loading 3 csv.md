To merge two large CSVs (class 0 and class 1) into one unified pandas DataFrame while handling:

* Different columns across files (union of columns),
* Automatic alignment (missing columns filled with `NaN`),
* Efficient memory usage,
* Shuffling the final result to randomize target label distribution,

here’s a **robust and memory-efficient approach**:

---

### ✅ Step-by-step Code

```python
import pandas as pd
from pathlib import Path

# Paths to large CSV files
csv_class_1 = "class1.csv"  # positive class
csv_class_0 = "class0.csv"  # negative class

# Add class label during read
df1 = pd.read_csv(csv_class_1)
df1['target'] = 1

df0 = pd.read_csv(csv_class_0)
df0['target'] = 0

# Combine both — pandas will align on column names
df_combined = pd.concat([df1, df0], ignore_index=True, sort=True)

# Shuffle the rows to avoid class grouping
df_combined = df_combined.sample(frac=1, random_state=42).reset_index(drop=True)

# Done
print(df_combined.shape)
print(df_combined['target'].value_counts())
```

---

### ✅ Notes:

* **`sort=True`** in `pd.concat()` ensures columns are aligned correctly even if order differs.
* Missing columns are automatically filled with `NaN`.
* **Shuffling with `frac=1`** ensures a full random permutation of the DataFrame.
* Set `random_state` for reproducibility.

---

### ⚠️ Large CSVs? Use Chunked Approach

If the files are **too large to fit in memory**, load and label in chunks:

```python
def read_labeled_chunks(filepath, label, chunksize=100_000):
    for chunk in pd.read_csv(filepath, chunksize=chunksize):
        chunk['target'] = label
        yield chunk

# Build combined chunks
chunks = []

for chunk in read_labeled_chunks("class1.csv", 1):
    chunks.append(chunk)

for chunk in read_labeled_chunks("class0.csv", 0):
    chunks.append(chunk)

# Combine and shuffle
df_combined = pd.concat(chunks, ignore_index=True, sort=True)
df_combined = df_combined.sample(frac=1, random_state=42).reset_index(drop=True)
```

---

### ✅ Optional: Write to Disk

```python
df_combined.to_csv("combined_shuffled.csv", index=False)
```

Let me know if you want it done **on disk (streaming)** without loading full data in RAM.
