Great question — XGBoost wants a **fixed-width, numeric table** (one row per training example). Deeply nested JSON with arrays-of-objects is the opposite: **variable-length, hierarchical**. The trick is to **denormalize** into a flat table by (a) flattening objects and (b) turning arrays into **aggregated features**.

Here’s a practical, battle-tested approach you can drop into your workflow.

# 1) Strategy at a glance

* **Row grain:** pick your primary key (e.g., `order_id`, `user_id`). Each row in the final table must represent exactly one entity/label.
* **Objects (dicts):** flatten keys into columns, e.g., `shipping.address.city`.
* **Arrays of scalars:** summarize (count, nunique, min/max/mean if numeric; top-K category counts if categorical).
* **Arrays of objects:** explode → aggregate back to one row per ID (numeric stats + categorical counts/encodings).
* **Categoricals:**

  * Low cardinality → One-Hot (k-1).
  * High cardinality → Frequency encoding or **cross-validated target/mean encoding** (to avoid leakage).
* **Dates:** extract features (year, month, day, dow, age, “days since …”).
* **Missing values:** keep as NaN (XGBoost handles them) + optional “was\_missing” indicator columns.
* **Text fields:** either drop, or featurize (lengths, keyword counts, TF-IDF/embeddings → numeric).

---

# 2) End-to-end Pandas template

This template:

* Flattens non-list dicts
* Detects list columns and handles:

  * scalar lists
  * arrays of objects (explode → aggregate)
* Produces a single denormalized DataFrame ready for XGBoost.

```python
import json
from collections import Counter, defaultdict
from typing import List, Dict, Any, Tuple, Optional
import numpy as np
import pandas as pd

# -------------------------
# Helpers
# -------------------------
def load_json(path: str) -> List[Dict[str, Any]]:
    with open(path, "r", encoding="utf-8") as f:
        data = json.load(f)
    # ensure list-of-records shape
    return data if isinstance(data, list) else [data]

def is_list_col(series: pd.Series) -> bool:
    return series.apply(lambda x: isinstance(x, list)).any()

def is_dict_col(series: pd.Series) -> bool:
    return series.apply(lambda x: isinstance(x, dict)).any()

def summarize_scalar_list(col_values: List[Any], topk_cats: int = 5, prefix: str = "") -> Dict[str, Any]:
    """
    Summarize a list of scalars (mixture allowed). Returns fixed-size dict.
    Numeric stats for numeric-only values; category counts for strings/objects.
    """
    res = {}
    lst = [x for x in col_values if x is not None]
    res[f"{prefix}__len"] = len(lst)
    res[f"{prefix}__nunique"] = len(set(lst))
    if not lst:
        return res

    # Try numeric
    numeric_vals = []
    cat_vals = []
    for v in lst:
        if isinstance(v, (int, float)) and not isinstance(v, bool) and np.isfinite(v):
            numeric_vals.append(float(v))
        else:
            cat_vals.append(v)

    if numeric_vals:
        arr = np.array(numeric_vals, dtype=float)
        res[f"{prefix}__num_sum"] = np.sum(arr)
        res[f"{prefix}__num_mean"] = np.mean(arr)
        res[f"{prefix}__num_std"] = float(np.std(arr, ddof=1)) if len(arr) > 1 else 0.0
        res[f"{prefix}__num_min"] = np.min(arr)
        res[f"{prefix}__num_p50"] = np.median(arr)
        res[f"{prefix}__num_max"] = np.max(arr)

    if cat_vals:
        counts = Counter(cat_vals).most_common(topk_cats)
        for i, (val, cnt) in enumerate(counts, 1):
            res[f"{prefix}__top{i}_val"] = str(val)
            res[f"{prefix}__top{i}_cnt"] = int(cnt)

    return res

def agg_numeric(df: pd.DataFrame, group_key: str, cols: List[str], prefix: str) -> pd.DataFrame:
    aggs = {c: ["count", "nunique", "mean", "std", "min", "median", "max", "sum"] for c in cols}
    out = df.groupby(group_key).agg(aggs)
    # flatten columns
    out.columns = [f"{prefix}__{c}__{stat}" for c, stat in out.columns]
    return out.reset_index()

def topk_categorical_counts(df: pd.DataFrame, group_key: str, col: str, topk: int, prefix: str) -> pd.DataFrame:
    # compute global top-k categories
    top_vals = df[col].dropna().astype(str).value_counts().head(topk).index.tolist()
    # pivot counts per ID for only top-k to keep width bounded
    mask = df[col].isin(top_vals)
    sub = df.loc[mask, [group_key, col]].copy()
    sub[col] = sub[col].astype(str)
    crosstab = pd.crosstab(sub[group_key], sub[col])
    # rename
    crosstab = crosstab.add_prefix(f"{prefix}__{col}__cnt_").reset_index()
    return crosstab

def aggregate_array_of_objects(
    df: pd.DataFrame,
    id_col: str,
    prefix: str,
    topk_cats: int = 5
) -> pd.DataFrame:
    """
    df is exploded sub-docs with columns from json_normalize.
    Mixed numeric/categorical: summarize numerics with stats, categoricals with top-k counts + nunique.
    """
    # identify types (rough heuristic)
    numeric_cols = [c for c in df.columns if c != id_col and pd.api.types.is_numeric_dtype(df[c])]
    object_cols = [c for c in df.columns if c != id_col and c not in numeric_cols]

    parts = []

    if numeric_cols:
        parts.append(agg_numeric(df[[id_col] + numeric_cols], id_col, numeric_cols, prefix=prefix))

    # nunique for categoricals (compact)
    if object_cols:
        nunique = df.groupby(id_col)[object_cols].nunique()
        nunique = nunique.add_prefix(f"{prefix}__").add_suffix("__nunique").reset_index()
        parts.append(nunique)
        # top-k per categorical
        for c in object_cols:
            parts.append(topk_categorical_counts(df[[id_col, c]].copy(), id_col, c, topk=topk_cats, prefix=prefix))

    # row counts (how many subdocs)
    counts = df.groupby(id_col).size().rename(f"{prefix}__rows").reset_index()
    parts.append(counts)

    # merge all
    out = parts[0]
    for p in parts[1:]:
        out = out.merge(p, on=id_col, how="outer")
    return out

# -------------------------
# Main denormalizer
# -------------------------
def denormalize_json_for_xgb(
    data: List[Dict[str, Any]],
    id_col: str,
    topk_cats: int = 5,
    array_object_prefix_map: Optional[Dict[str, str]] = None,
    sep: str = "."
) -> pd.DataFrame:
    """
    data: list of JSON docs
    id_col: primary key to keep one-row-per-entity
    array_object_prefix_map: optional mapping from array column name to a friendly prefix
    """
    array_object_prefix_map = array_object_prefix_map or {}

    # 1) Flatten non-list dictionaries
    base = pd.json_normalize(data, sep=sep)

    if id_col not in base.columns:
        raise ValueError(f"'{id_col}' not found after flattening. Ensure it exists at the root or is flattened in.")

    # 2) Identify list columns
    list_cols = [c for c in base.columns if is_list_col(base[c])]
    out = base.drop(columns=list_cols).copy()

    # 3) For each list column: handle scalars vs objects
    features_by_listcol = []

    for c in list_cols:
        prefix = array_object_prefix_map.get(c, c.replace(sep, "__"))

        # separate scalars vs objects, by peeking
        def classify(val):
            if isinstance(val, list) and val and isinstance(val[0], dict):
                return "array_of_objects"
            elif isinstance(val, list):
                return "array_of_scalars"
            else:
                return "other"

        kind = base[c].apply(classify)
        # If any row is array_of_objects, treat as such. Otherwise scalars.
        if (kind == "array_of_objects").any():
            # explode -> normalize -> aggregate
            exploded = base[[id_col, c]].explode(c, ignore_index=False)
            exploded = exploded.dropna(subset=[c])
            if exploded.empty:
                continue
            # normalize subdocs
            sub = pd.json_normalize(exploded[c].tolist(), sep=sep)
            sub.insert(0, id_col, exploded[id_col].values)
            agg = aggregate_array_of_objects(sub, id_col=id_col, prefix=prefix, topk_cats=topk_cats)
            features_by_listcol.append(agg)
        else:
            # array of scalars
            summaries = []
            for _id, vals in base[[id_col, c]].itertuples(index=False, name=None):
                vals = vals if isinstance(vals, list) else []
                summaries.append({"__id": _id, **summarize_scalar_list(vals, topk_cats=topk_cats, prefix=prefix)})
            feat = pd.DataFrame(summaries).rename(columns={"__id": id_col})
            features_by_listcol.append(feat)

    # 4) Merge all list-driven features back
    for feat in features_by_listcol:
        out = out.merge(feat, on=id_col, how="left")

    # 5) Optional: simple date parsing & basic features
    date_cols = [c for c in out.columns if any(tok in c.lower() for tok in ["date", "time", "timestamp"])]
    for c in date_cols:
        with np.errstate(all="ignore"):
            dt = pd.to_datetime(out[c], errors="coerce", utc=True)
        out[f"{c}__year"] = dt.dt.year
        out[f"{c}__month"] = dt.dt.month
        out[f"{c}__day"] = dt.dt.day
        out[f"{c}__dow"] = dt.dt.dayofweek
        out[f"{c}__is_null"] = out[c].isna().astype(int)

    # 6) Keep NaNs; XGBoost will handle them. (You can add missingness flags above)
    return out
```

### How to use it

```python
data = load_json("your_nested.json")
df = denormalize_json_for_xgb(
    data,
    id_col="order_id",                # <- your primary key here
    topk_cats=5,
    array_object_prefix_map={"items": "items", "events": "events"}  # optional nicer prefixes
)
# Optional: choose encodings for top-level categoricals
low_card = [c for c in df.columns if df[c].dtype == "object" and df[c].nunique() <= 12]
df = pd.get_dummies(df, columns=low_card, drop_first=True)

# High-cardinality categoricals -> frequency encoding example:
high_card = [c for c in df.columns if df[c].dtype == "object" and df[c].nunique() > 12]
for c in high_card:
    freqs = df[c].value_counts(normalize=True)
    df[c] = df[c].map(freqs)  # NaN stays NaN

# Save for XGBoost
df.to_parquet("denormalized.parquet", index=False)   # preferred for speed/size
# or df.to_csv("denormalized.csv", index=False)
```

---

# 3) Notes on arrays-of-objects (the hard part)

* **Explode → Aggregate** is the safest general pattern:

  1. `explode` the array so each subdocument becomes a row.
  2. `json_normalize` the subdocuments.
  3. `groupby(id)` and compute **numeric stats** (`count, nunique, mean, std, min, median, max, sum`) and **categorical features**:

     * `nunique` per subfield
     * **Top-K category counts** per subfield to keep width bounded
* If you know arrays have a **small, fixed max size**, you can also “index” them (e.g., `address[0].city`, `address[1].city`), but this can encode incidental order and blow up columns.

---

# 4) Leakage-safe encodings (important for XGBoost)

* **Target/mean encoding** for high-cardinality categoricals should be done **within cross-validation folds** (fit encodings on train-fold, apply to val-fold), not on the whole dataset at once. Otherwise you’ll leak label info into features.
* Frequency encoding (global proportions) is leakage-safe and often strong for tree models.

---

# 5) Dates & times

* Convert to UTC datetimes; derive **calendar features** and **recencies** (e.g., `days_since_last_event`, `events_in_last_7d`, etc.). Those require a reference date per entity; you can compute them in the exploded table before aggregating.

---

# 6) Text

* If you have text blobs, convert to numeric: lengths, word counts, presence of keywords, TF-IDF (then reduce with SVD) or embeddings (averaged) — XGBoost will then happily ingest those numeric vectors.

---

# 7) Class imbalance & missingness

* If your target is imbalanced, consider `scale_pos_weight` or focal loss variants (custom objective).
* Keep NaNs; optionally add `__is_missing` indicators for important columns.

---

# 8) (Optional) PySpark sketch for very large data

If your JSON is huge, do the same pattern in Spark:

```python
from pyspark.sql import functions as F, types as T

df = spark.read.json("s3://bucket/path/*.json", multiLine=True)

# Flatten known structs (Spark doesn't have an automatic deep flattener; you often select with dotted paths)
# Example: df = df.select("order_id", "user.id", "user.country", "items", "events")

# Arrays of objects (e.g., items)
items = df.select("order_id", F.explode_outer("items").alias("item"))
items_flat = items.select(
    "order_id",
    "item.sku".alias("sku"),
    "item.qty".alias("qty"),
    "item.price".alias("price"),
    "item.category".alias("category")
)

# Numeric aggregations
num_agg = items_flat.groupBy("order_id").agg(
    F.count("*").alias("items__rows"),
    F.countDistinct("sku").alias("items__sku_nunique"),
    F.sum("qty").alias("items__qty_sum"),
    F.avg("qty").alias("items__qty_mean"),
    F.expr("percentile_approx(qty, 0.5)").alias("items__qty_p50"),
    F.min("price").alias("items__price_min"),
    F.max("price").alias("items__price_max")
)

# Top-K category counts (example K=5)
topk = (items_flat.groupBy("category").count()
        .orderBy(F.desc("count")).limit(5)
        .select(F.collect_list("category")).first()[0])

cat_piv = (items_flat
    .filter(F.col("category").isin(topk))
    .groupBy("order_id", "category").count()
    .groupBy("order_id")
    .pivot("category", topk).sum("count")
    .fillna(0))

final = df.join(num_agg, "order_id", "left").join(cat_piv, "order_id", "left")
```

---

## Wrap-up

* **Goal:** fixed-width, numeric feature table per entity.
* **Method:** flatten dicts, **explode → aggregate** arrays, encode categoricals (OHE/frequency/target-CV), derive date features, preserve NaNs.
* The included Pandas template will get you from “deep JSON” to “XGBoost-ready DataFrame” with predictable, bounded width.

If you want, I can plug your actual schema (or a small sample) into this template and hand you a ready-to-run notebook with the denormalization tailored to your fields.
