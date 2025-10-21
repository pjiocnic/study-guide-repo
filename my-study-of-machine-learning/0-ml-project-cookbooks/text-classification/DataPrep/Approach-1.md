Here’s a clean, minimal way to prep your file and save it for the two notebooks:

## Steps (what you asked for)

1. Create `TEXT` from `REMARKS_1` else `REMARKS_2`
   `df["TEXT"] = df["REMARKS_1"].where(df["REMARKS_1"].notna(), df["REMARKS_2"])`

2. Drop `REMARKS_1`, `REMARKS_2` (optional to keep until the end, but not needed later)

3. Deduplicate on the combination `(CATEGORY, TEXT)`.

4. Build a final 2-column table with headers exactly: `text`, `label`.
   Rename `TEXT → text` and `CATEGORY → label`.

5. Save as **CSV (UTF-8)** with quoting so commas/newlines in text are preserved.

### Pandas one-pager

```python
import pandas as pd, csv

# read your spreadsheet (Excel or CSV)
# df = pd.read_excel("input.xlsx", sheet_name="Sheet1")
df = pd.read_csv("input.csv")  # if already CSV

# 1) TEXT from REMARKS_1 else REMARKS_2
df["TEXT"] = df["REMARKS_1"].where(df["REMARKS_1"].notna(), df["REMARKS_2"])

# basic hygiene (optional but recommended)
df["TEXT"] = df["TEXT"].astype(str).str.strip()
df = df[df["TEXT"].str.len() >= 3]  # drop very short/empty

# 2–3) dedup on (CATEGORY, TEXT)
df = df.drop_duplicates(subset=["CATEGORY","TEXT"])

# 4) final two columns
out_df = df.rename(columns={"TEXT":"text","CATEGORY":"label"})[["text","label"]]

# 5) SAVE — quotes handled for commas/newlines
out_df.to_csv("text_label.csv",
              index=False,
              encoding="utf-8",
              quoting=csv.QUOTE_MINIMAL,   # auto-quotes when needed
              lineterminator="\n")
print("Wrote text_label.csv", out_df.shape)
```

### Why CSV + quoting?

* Your `TEXT` contains commas—CSV handles this by quoting fields automatically (with `QUOTE_MINIMAL`).
* The notebooks you have expect headers `text,label`. This matches perfectly.
* If you’ll open in Excel, it’s fine; if Excel misreads encoding on Windows, use `encoding="utf-8-sig"`.

---

## Optional: keep a traceability map (to SEQ_NBR)

If you may need to relate back to originals after dedup, save a companion mapping:

```python
m = (df.groupby(["CATEGORY","TEXT"])["SEQ_NBR"]
       .apply(list)
       .reset_index()
       .rename(columns={"CATEGORY":"label","TEXT":"text"}))
m.to_csv("text_label_mapping.csv", index=False, encoding="utf-8", quoting=csv.QUOTE_MINIMAL)
```

This gives you `(text, label) -> [list of SEQ_NBRs collapsed]`.

---

## Ready-made helper script (download)

I also generated a tiny script you can run locally or in Jupyter terminals. It accepts Excel or CSV input and writes `text_label.csv` (plus an optional mapping file):

* [Download: `prep_text_dataset.py`](sandbox:/mnt/data/prep_text_dataset.py)

Usage:

```bash
python prep_text_dataset.py \
  --input input.xlsx \
  --sheet Sheet1 \
  --output text_label.csv \
  --mapping_out text_label_mapping.csv
```

(If your source is already CSV, omit `--sheet`.)

---

## For SageMaker notebooks

* Upload `text_label.csv` to your S3 input prefix the notebooks use, e.g.:

  ```bash
  aws s3 cp text_label.csv s3://YOUR-BUCKET/datasets/text/
  ```
* Both SageMaker notebooks you downloaded look for multiple CSVs under that prefix and concatenate them, as long as each has **`text,label`** headers.

If you want me to tweak the prep script to also **upload to S3 automatically** after writing the CSV, say the word and I’ll add it.
