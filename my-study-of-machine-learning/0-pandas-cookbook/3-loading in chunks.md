

## How to load csv in chunks

```py
# Load only first 1000 rows to inspect
df_sample = pd.read_csv("your_file.csv", nrows=1000)
print(df_sample.head())

# Or load in chunks
chunk_size = 100000  # tune this value
chunks = []

for chunk in pd.read_csv("your_file.csv", chunksize=chunk_size):
    chunks.append(chunk)
    # Optionally: break early for test
    # break

# Combine chunks
df = pd.concat(chunks, ignore_index=True)
```