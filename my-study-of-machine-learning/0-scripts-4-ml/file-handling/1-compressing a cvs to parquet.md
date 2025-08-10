You can convert a CSV file to **Parquet** easily in Python using **pandas**.
Here’s the simplest approach:

---

### **1. Install pandas (if not already installed)**

```bash
pip install pandas pyarrow
```

> `pyarrow` or `fastparquet` is required as a Parquet engine. Pandas will use one automatically if installed.

---

### **2. Basic CSV → Parquet conversion**

```python
import pandas as pd

# Read CSV
df = pd.read_csv("input.csv")

# Save as Parquet
df.to_parquet("output.parquet", engine="pyarrow", index=False)

print("Conversion complete!")
```

**Notes:**

* `engine` can be `"pyarrow"` or `"fastparquet"`.
* `index=False` ensures the DataFrame index is not written unless needed.

---

### **3. With compression**

Parquet supports efficient compression.

```python
df.to_parquet(
    "output.parquet",
    engine="pyarrow",
    index=False,
    compression="snappy"   # Options: snappy, gzip, brotli, zstd
)
```

---

### **4. Convert in chunks for very large CSV**

If the CSV is huge:

```python
chunksize = 100_000
chunks = pd.read_csv("big.csv", chunksize=chunksize)

for i, chunk in enumerate(chunks):
    mode = "append" if i > 0 else "overwrite"
    chunk.to_parquet(
        "big.parquet",
        engine="pyarrow",
        index=False,
        compression="snappy",
        append=(i > 0)
    )
```

*(Note: Append works best with `fastparquet`.)*

---

✅ **Why use Parquet instead of CSV?**

* Much smaller file size (especially with compression)
* Columnar format → faster queries on subsets of columns
* Preserves data types better than CSV

---

### **Using `csv2parquet` (Fastest & Easiest)**

```bash
pip install csv2parquet
csv2parquet input.csv output.parquet
```

---

### **Using `pyarrow` from the command line**

```bash
python -c "import pandas as pd; pd.read_csv('input.csv').to_parquet('output.parquet', engine='pyarrow', index=False)"
```

---

### **Using DuckDB CLI (Great for huge files)**

```bash
duckdb -c "COPY (SELECT * FROM read_csv_auto('input.csv')) TO 'output.parquet' (FORMAT PARQUET);"
```

> DuckDB is super fast, memory-efficient, and works well with large datasets.

---

If you want, I can give you a **benchmark comparison of Python vs DuckDB vs csv2parquet** so you know which is fastest for your file size. Would you like me to prepare that?
