Great — since you confirmed you want to **combine two large CSVs on disk (streaming)** without loading everything into RAM, here’s a **memory-efficient solution**:

---

## ✅ Goal

* Handle very large files (many GBs)
* Concatenate two CSVs (class=1 and class=0)
* Align on all columns (union of schema)
* Fill missing columns with `NaN`
* Add a `target` column
* Shuffle output (best effort with limited memory)
* Write to a new **combined CSV** file line-by-line

---

## ✅ Streaming Merge Script (No Full RAM Usage)

```python
import pandas as pd
import numpy as np
from pathlib import Path

# Inputs
file_class_1 = "class1.csv"
file_class_0 = "class0.csv"
output_file = "combined_shuffled.csv"
chunksize = 100_000

# Step 1: Read first chunk from both to get full column union
sample1 = pd.read_csv(file_class_1, nrows=100)
sample0 = pd.read_csv(file_class_0, nrows=100)

# Union of all columns
all_columns = sorted(set(sample1.columns).union(sample0.columns))
all_columns.append('target')

print(f"Unified schema with {len(all_columns)} columns")

# Step 2: Function to stream with padding and label
def stream_labeled_chunks(filepath, label, all_columns, chunksize):
    for chunk in pd.read_csv(filepath, chunksize=chunksize):
        chunk['target'] = label
        for col in all_columns:
            if col not in chunk.columns:
                chunk[col] = np.nan
        yield chunk[all_columns]

# Step 3: Write header
with open(output_file, "w", encoding="utf-8") as f:
    pd.DataFrame(columns=all_columns).to_csv(f, index=False)

# Step 4: Interleave writing chunks from both files
stream1 = stream_labeled_chunks(file_class_1, 1, all_columns, chunksize)
stream0 = stream_labeled_chunks(file_class_0, 0, all_columns, chunksize)

# Alternate chunks for pseudo-random distribution
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

### 🧠 How It Works

| Step | What It Does                                                            |
| ---- | ----------------------------------------------------------------------- |
| 1.   | Loads only 100 rows from each CSV to infer all column names             |
| 2.   | Reads in `chunksize` batches (e.g., 100,000 rows)                       |
| 3.   | Pads missing columns with NaN                                           |
| 4.   | Adds a target column (`0` or `1`)                                       |
| 5.   | Alternates between class0 and class1 chunks to **avoid class grouping** |
| 6.   | Shuffles each chunk individually (random enough for large datasets)     |
| 7.   | Appends all chunks to a single output CSV                               |

---

### 🔁 Optional: Fully Shuffle the Final File?

If the above isn’t random enough, and you can afford 1 extra disk pass:

```python
# After initial output
df_final = pd.read_csv("combined_shuffled.csv")  # in chunks if needed
df_final = df_final.sample(frac=1).reset_index(drop=True)
df_final.to_csv("fully_shuffled.csv", index=False)
```

Or use **Unix sort** if randomization isn't essential but sorting by a specific column helps.

---

Would you like a ZIP version of this as a standalone script with README?
