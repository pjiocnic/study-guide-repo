Thanks — that's a key clarification.

If **each CSV already contains a `target` column**:

* You **don’t need to add `target` manually**.
* But you **still need to ensure consistent schema**, especially if one file has extra features.

---

### ✅ Updated Streaming Merge Plan

We will:

1. Use the existing `target` column from both CSVs.
2. Ensure the **union of all columns**, including `target`.
3. Fill missing columns as `NaN`.
4. Interleave chunks to avoid class grouping.
5. Shuffle each chunk locally before writing to a combined CSV.

---

### ✅ Updated Python Script

```python
import pandas as pd
import numpy as np

file_class_1 = "class1.csv"
file_class_0 = "class0.csv"
output_file = "combined_shuffled.csv"
chunksize = 100_000

# Step 1: Infer union of all columns including 'target'
sample1 = pd.read_csv(file_class_1, nrows=100)
sample0 = pd.read_csv(file_class_0, nrows=100)

all_columns = sorted(set(sample1.columns).union(sample0.columns))

print(f"Unified schema: {len(all_columns)} columns")

# Step 2: Generator to stream each file with padded columns
def stream_padded_chunks(filepath, all_columns, chunksize):
    for chunk in pd.read_csv(filepath, chunksize=chunksize):
        for col in all_columns:
            if col not in chunk.columns:
                chunk[col] = np.nan
        yield chunk[all_columns]

# Step 3: Write header
with open(output_file, "w", encoding="utf-8") as f:
    pd.DataFrame(columns=all_columns).to_csv(f, index=False)

# Step 4: Interleave chunks from both CSVs
stream1 = stream_padded_chunks(file_class_1, all_columns, chunksize)
stream0 = stream_padded_chunks(file_class_0, all_columns, chunksize)

done1 = done0 = False

with open(output_file, "a", encoding="utf-8") as f:
    while not (done1 and done0):
        try:
            chunk = next(stream1)
            chunk.sample(frac=1).to_csv(f, index=False, header=False)
        except StopIteration:
            done1 = True
        try:
            chunk = next(stream0)
            chunk.sample(frac=1).to_csv(f, index=False, header=False)
        except StopIteration:
            done0 = True
```

---

### ✅ Output

* Final file: `combined_shuffled.csv`
* All rows retain original `target` labels
* Union of all columns from both files
* Pseudo-random order (due to interleaved + local shuffle)

---

### 📌 Optional Improvements

| Goal                          | Action                                                      |
| ----------------------------- | ----------------------------------------------------------- |
| Fully shuffle after combining | Use pandas `sample(frac=1)` on full file (if memory allows) |
| Memory-efficient full shuffle | Use `dask.dataframe` or `pandas + SQLite`                   |
| Reproducibility               | Add `random_state=42` to `.sample()`                        |

---

Let me know if you'd like:

* A ZIP with the full script + sample CSVs
* A version using **Dask**, **DuckDB**, or **SQLite** for shuffling large datasets on disk
