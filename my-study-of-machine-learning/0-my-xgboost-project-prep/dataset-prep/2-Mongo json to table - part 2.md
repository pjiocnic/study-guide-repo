
```json

{
  "order_id": "A12345",
  "customer": {
    "customer_id": "C98765",
    "name": {
      "first": "John",
      "last": "Doe"
    },
    "contact": {
      "email": "john.doe@example.com",
      "phone": "+1-202-555-0147"
    },
    "addresses": [
      {
        "type": "billing",
        "street": "123 Main St",
        "city": "Springfield",
        "state": "IL",
        "postal_code": "62701",
        "country": "USA"
      },
      {
        "type": "shipping",
        "street": "789 Market St",
        "city": "Chicago",
        "state": "IL",
        "postal_code": "60616",
        "country": "USA"
      }
    ]
  },
  "items": [
    {
      "product_id": "P1001",
      "name": "Laptop",
      "price": 999.99,
      "quantity": 1,
      "attributes": {
        "brand": "Dell",
        "specs": {
          "cpu": "Intel i7",
          "ram": "16GB",
          "storage": "512GB SSD"
        }
      }
    },
    {
      "product_id": "P2002",
      "name": "Wireless Mouse",
      "price": 29.99,
      "quantity": 2,
      "attributes": {
        "brand": "Logitech",
        "color": "Black"
      }
    }
  ],
  "payment": {
    "method": "Credit Card",
    "transaction_id": "TX123456789",
    "amount": 1059.97,
    "currency": "USD",
    "billing_address_ref": "billing"
  },
  "shipping": {
    "carrier": "UPS",
    "tracking_number": "1Z999AA10123456784",
    "estimated_delivery": "2025-08-25"
  },
  "status_history": [
    {
      "status": "Order Placed",
      "timestamp": "2025-08-15T10:15:30Z"
    },
    {
      "status": "Payment Confirmed",
      "timestamp": "2025-08-15T10:17:45Z"
    },
    {
      "status": "Shipped",
      "timestamp": "2025-08-16T08:22:10Z"
    }
  ]
}
```

---

Great question. With XGBoost you want a **single, row-per-example table** (a DataFrame / CSV) with **numeric and one-hot/frequency-encoded features**—no nested structures. For your e-commerce JSON, the cleanest grain is **one row per order\_id**. Everything nested (items, addresses, status\_history) should be **aggregated** up to that grain.

Below is a practical recipe you can use right away.

---

# 1) Pick the prediction target (label)

Decide what you want to predict; the label must already be known in historical data.

Typical examples (add one of these fields to your training set):

* `is_returned` (0/1)
* `is_fraud` (0/1)
* `delivered_late` (0/1) — e.g., arrived after `estimated_delivery`
* `order_value` (regression)

*(I’ll assume a binary label `is_returned` for code placeholders.)*

---

# 2) Choose the row grain

**One row per `order_id`**.

---

# 3) Engineer order-level features by aggregating nested parts

### From `items` (array → per-order aggregations)

* `n_items` = number of line items
* `qty_total` = Σ `quantity`
* `item_subtotal` = Σ `price * quantity`
* `avg_item_price` = `item_subtotal / qty_total`
* `n_unique_products` = nunique of `product_id`
* `n_unique_brands` = nunique of `attributes.brand`
* `has_premium_cpu` = any(specs.cpu contains “i7”/“M2”/“Ryzen 7”, etc.) *(domain logic)*

### From `payment`

* `payment_method` (one-hot or frequency encoding if many)
* `amount`, `currency` (currency one-hot if multiple)
* `amount_vs_items` = `payment.amount - item_subtotal` (detect discounts/taxes/mismatch)

### From `customer.addresses`

* `has_separate_shipping` = shipping != billing
* `shipping_state`, `billing_state` (one-hot low-cardinality)
* `same_state` = 1 if state matches else 0

### From `shipping`

* `carrier` (one-hot: UPS/USPS/FedEx/…)
* `est_days_to_delivery` = days(estimated\_delivery − order\_placed\_ts)

### From `status_history` (timeline → dates & durations)

* `placed_ts` = timestamp of “Order Placed”
* `paid_ts` = timestamp of “Payment Confirmed”
* `shipped_ts` = timestamp of “Shipped”
* `pay_delay_min` = minutes(paid\_ts − placed\_ts)
* `ship_delay_hours` = hours(shipped\_ts − paid\_ts)
* `last_status` (categorical: one-hot)

> ⚠️ **Leakage guard:** Only use statuses/timestamps available **at prediction time**.
> If you predict “late delivery at checkout”, don’t use `shipped_ts` or later statuses.

---

# 4) Encoding & missing values

* **Low-cardinality categoricals** (e.g., `payment_method`, `carrier`, `last_status`, states): **one-hot encode**.
* **High-cardinality categoricals** (e.g., `brand` at line-item level): don’t one-hot; instead use **aggregations** you created (`n_unique_brands`) and optionally **frequency encoding** at the order level.
* **Missing numerics**: fill with constants like `-1` or median; **XGBoost can handle NaN**, but consistent fills are fine.
* **Missing categoricals**: fill `"unknown"` before one-hot/frequency enc.

---

# 5) Train/test split

* For time-dependent problems, **split by time** (e.g., train on older orders, test on newer) rather than random split.

---

# 6) End-to-end pandas pipeline (drop-in starter)

```python
import pandas as pd
import numpy as np
from pandas import json_normalize
from datetime import datetime

# if you have many orders, make data = list of order dicts
data = [YOUR_JSON_OBJECT]  # or load from NDJSON: [json.loads(line) for line in open('orders.ndjson')]

orders = pd.DataFrame([{"order_id": d["order_id"]} for d in data])

# --- Base flat columns (customer, payment, shipping) ---
base = json_normalize(
    data,
    sep=".",
    max_level=1  # keep it shallow; deep arrays handled separately
)[[
    "order_id",
    "customer.customer_id",
    "payment.method", "payment.amount", "payment.currency",
    "shipping.carrier", "shipping.tracking_number", "shipping.estimated_delivery"
]].rename(columns={
    "customer.customer_id":"customer_id",
    "payment.method":"payment_method",
    "payment.amount":"payment_amount",
    "payment.currency":"payment_currency",
    "shipping.carrier":"carrier",
    "shipping.tracking_number":"tracking",
    "shipping.estimated_delivery":"estimated_delivery"
})

# --- Addresses: pull billing/shipping, then features ---
def extract_addr(d, atype):
    for a in d.get("customer",{}).get("addresses",[]):
        if a.get("type")==atype:
            return {"street":a.get("street"),
                    "city":a.get("city"),
                    "state":a.get("state"),
                    "postal_code":a.get("postal_code"),
                    "country":a.get("country")}
    return {}

addr_rows = []
for d in data:
    billing = extract_addr(d, "billing")
    shipping = extract_addr(d, "shipping")
    addr_rows.append({
        "order_id": d["order_id"],
        "billing_state": billing.get("state"),
        "shipping_state": shipping.get("state"),
        "has_separate_shipping": int(bool(billing) and bool(shipping) and (billing != shipping)),
        "same_state": int(billing.get("state")==shipping.get("state")) if billing and shipping else 0
    })
addr = pd.DataFrame(addr_rows)

# --- Items aggregation ---
item_rows = []
for d in data:
    for it in d.get("items", []):
        item_rows.append({
            "order_id": d["order_id"],
            "product_id": it.get("product_id"),
            "brand": (it.get("attributes") or {}).get("brand"),
            "price": it.get("price", 0.0),
            "quantity": it.get("quantity", 0),
            "cpu": (((it.get("attributes") or {}).get("specs") or {}).get("cpu") or "")
        })
items = pd.DataFrame(item_rows)

if len(items):
    items["line_total"] = items["price"] * items["quantity"]
    item_agg = items.groupby("order_id").agg(
        n_items=("product_id","count"),
        qty_total=("quantity","sum"),
        item_subtotal=("line_total","sum"),
        n_unique_products=("product_id","nunique"),
        n_unique_brands=("brand","nunique"),
        has_premium_cpu=("cpu", lambda s: int(any(x.lower().find("i7")>=0 or x.lower().find("ryzen 7")>=0 or x.lower().find("m2")>=0 for x in s if isinstance(x,str))))
    ).reset_index()
else:
    # if no items present
    item_agg = orders[["order_id"]].assign(
        n_items=0, qty_total=0, item_subtotal=0.0,
        n_unique_products=0, n_unique_brands=0, has_premium_cpu=0
    )

# avg price (guard div by zero)
item_agg["avg_item_price"] = np.where(item_agg["qty_total"]>0, item_agg["item_subtotal"]/item_agg["qty_total"], 0.0)

# --- Status history: timestamps & durations ---
def ts_of(statuses, name):
    for s in statuses:
        if s.get("status")==name:
            return s.get("timestamp")
    return None

status_rows = []
for d in data:
    sh = d.get("status_history", [])
    placed = ts_of(sh, "Order Placed")
    paid   = ts_of(sh, "Payment Confirmed")
    shipped= ts_of(sh, "Shipped")
    last   = sh[-1]["status"] if sh else None
    status_rows.append({
        "order_id": d["order_id"],
        "placed_ts": placed,
        "paid_ts": paid,
        "shipped_ts": shipped,
        "last_status": last
    })
status = pd.DataFrame(status_rows)

# parse timestamps
def to_dt(x):
    try:
        return pd.to_datetime(x, utc=True)
    except Exception:
        return pd.NaT

for c in ["placed_ts","paid_ts","shipped_ts"]:
    status[c] = status[c].map(to_dt)

# durations
status["pay_delay_min"]   = (status["paid_ts"] - status["placed_ts"]).dt.total_seconds() / 60.0
status["ship_delay_hours"]= (status["shipped_ts"] - status["paid_ts"]).dt.total_seconds() / 3600.0

# --- Merge all order-level frames ---
df = (orders[["order_id"]]
      .merge(base,   on="order_id", how="left")
      .merge(addr,   on="order_id", how="left")
      .merge(item_agg, on="order_id", how="left")
      .merge(status, on="order_id", how="left")
)

# --- Delivery estimate derived feature ---
df["estimated_delivery"] = pd.to_datetime(df["estimated_delivery"], errors="coerce", utc=True)
df["est_days_to_delivery"] = (df["estimated_delivery"] - df["placed_ts"]).dt.total_seconds() / (3600*24)

# Consistency check
df["amount_vs_items"] = df["payment_amount"] - df["item_subtotal"]

# --- Example label (placeholder): is_returned ---
# In real data, join historical returns and set 0/1 here.
df["is_returned"] = np.nan  # fill with real labels later

# --- Categorical handling (simple one-hot for low-cardinality) ---
cats_low = ["payment_method", "payment_currency", "carrier", "billing_state", "shipping_state", "last_status"]
for c in cats_low:
    df[c] = df[c].fillna("unknown")

df_ohe = pd.get_dummies(df, columns=cats_low, drop_first=False)

# --- Missing values for numerics ---
numeric_cols = df_ohe.select_dtypes(include=[np.number]).columns.tolist()
# (Option A) keep NaN (XGBoost can handle)
# (Option B) simple fill:
df_ohe[numeric_cols] = df_ohe[numeric_cols].fillna(-1)

# --- Final train matrix ---
feature_cols = [c for c in df_ohe.columns if c not in ["order_id", "customer_id", "tracking", "estimated_delivery", "placed_ts", "paid_ts", "shipped_ts", "is_returned"]]
X = df_ohe[feature_cols].astype(float)
y = df_ohe["is_returned"]  # replace with real labels

print("Shape:", X.shape)
print("Sample columns:", feature_cols[:12])
```

**Notes**

* If your source is **line-delimited JSON (NDJSON)**, read it line-by-line and build `data = [json.loads(line) for line in open('orders.ndjson')]`.
* For **big data**, replicate the same logic with **PySpark**: explode `items`, aggregate by `order_id`, join back.

---

# 7) Common pitfalls to avoid

* **Data leakage:** don’t use post-checkout info when predicting at checkout time.
* **Exploding without regrouping:** always re-aggregate item rows back to **one row per order**.
* **Too many one-hot columns:** don’t one-hot high-cardinality fields like `brand`; prefer counts, nunique, frequency encoding.
* **Currency mixing:** normalize to a base currency before training if you have multiple.

---

If you tell me your **exact label** (e.g., fraud/return/late-delivery) and **when** you’ll predict (checkout vs. post-payment), I’ll tailor the feature list and tweak the pipeline to avoid leakage specific to your workflow.

---

Perfect — since you’re predicting **fraud at post-payment**, your row grain stays **one row per `order_id`**, and your “knowledge cutoff” for features is **the moment payment is confirmed** (`paid_ts`). Everything that is created **after** `paid_ts` (e.g., shipping scans, delivery outcomes, chargebacks recorded later) must be excluded to avoid leakage.

Below is a tight blueprint you can drop into your pipeline.

---

# What to include (allowed before/at `paid_ts`)

## A) Transaction features

* `payment_amount`, `payment_currency`
* `payment_method` (one-hot / freq-enc)
* `pay_delay_min` = `paid_ts - placed_ts` (cart→pay speed)
* `item_subtotal`, `qty_total`, `avg_item_price`, `n_items`, `n_unique_products`, `n_unique_brands`
* `amount_vs_items` = `payment_amount - item_subtotal` (discount/tax/mismatch)
* `est_days_to_delivery` if it’s computed at checkout (OK).

## B) Instrument / identity features (if present)

* Card BIN, last4 (hash), **card\_age\_days** (since first seen), **card\_txn\_count\_7d/30d**, **card\_total\_amt\_7d/30d**
* Email domain (free vs. corporate), **email\_age\_days**, **email\_txn\_count\_7d/30d**
* Phone (E.164 validity, country match)
* IP (ASN, proxy/VPN flags if available), **ip\_txn\_count\_24h/7d**, **n\_distinct\_emails\_on\_ip\_24h**
* Device fingerprint hash, **device\_txn\_count\_30d**, **n\_distinct\_cards\_on\_device\_7d**

## C) Consistency/geography

* `billing_state`, `shipping_state`, `same_state`, `has_separate_shipping`
* Country consistency between IP ↔ billing ↔ shipping (if IP geo available)
* Distance between billing/shipping ZIP centroids (if you can map ZIP→lat/lon)

## D) Time signals

* TOD/DOW one-hot from `paid_ts` (night/weekend spikes)
* Promo/holiday flags

> Don’t one-hot very high-cardinality features directly (e.g., email, card hash). Use **counts/uniques/ratios** and optionally **frequency encoding** at the order level.

---

# What to exclude (leakage at post-payment)

* `shipped_ts`, `delivered_ts`, delivery scan events
* `status_history` entries **after** `paid_ts` (e.g., “Shipped”, “Out for delivery”)
* Any chargeback/return flags recorded later (those become your **label** in historical data, not a feature)

---

# “Cut at paid\_ts” guardrail (code)

```python
# Assume you already produced: orders df like before (from your JSON)
# We'll refine status and velocity features to be valid at paid_ts.

# --- Status fields, clipped to paid_ts ---
status["paid_ts"] = pd.to_datetime(status["paid_ts"], utc=True, errors="coerce")
status["placed_ts"] = pd.to_datetime(status["placed_ts"], utc=True, errors="coerce")

# Keep only statuses up to paid_ts to derive 'last_status_at_pay'
def last_status_before_paid(row):
    sh = next(d for d in data if d["order_id"]==row["order_id"])["status_history"]
    # keep only events <= paid_ts
    pts = row["paid_ts"]
    allowed = [s for s in sh if pts is not None and pd.to_datetime(s["timestamp"], utc=True) <= pts]
    return allowed[-1]["status"] if allowed else None

status["last_status_at_pay"] = status.apply(last_status_before_paid, axis=1)
status["pay_delay_min"] = (status["paid_ts"] - status["placed_ts"]).dt.total_seconds()/60.0

# Drop any post-payment fields from earlier build:
safe_df = df.drop(columns=[
    "shipped_ts", "last_status",  # post-payment
], errors="ignore")

# Replace with the safe status
safe_df = safe_df.merge(status[["order_id","last_status_at_pay","pay_delay_min"]], on="order_id", how="left")
```

---

# Velocity & “first-seen” features (no leakage)

These are the biggest lift for fraud. They must be computed **only using events with `paid_ts` strictly earlier** than the current order’s `paid_ts`.

```python
# Build a small fact table with the fields needed for velocities
hist = safe_df[["order_id","customer_id","payment_amount","paid_ts",
                "billing_state","shipping_state","payment_method"]].copy()

# Choose identities you have (examples shown)
# If you also have device_hash, ip, email, card_hash, add them here.
# For demo, we'll use customer_id only; pattern is the same for each identity.

hist = hist.sort_values("paid_ts")
# Prior counts & amounts for the same customer at various windows
for win_name, win_days in [("24h",1),("7d",7),("30d",30)]:
    # rolling window on time requires setting paid_ts as index per group
    g = hist.set_index("paid_ts").groupby("customer_id")["payment_amount"]
    # Use rolling-count/rolling-sum with a time window, then shift(1) to exclude current row
    hist[f"cust_txn_count_{win_name}"] = (
        g.rolling(f"{win_days}D").count().groupby(level=1).shift(1).reset_index(level=0, drop=True)
    )
    hist[f"cust_amt_sum_{win_name}"] = (
        g.rolling(f"{win_days}D").sum().groupby(level=1).shift(1).reset_index(level=0, drop=True)
    )

# First-seen age (days since customer's first paid_ts), excluding current row
first_seen = hist.groupby("customer_id")["paid_ts"].transform("min")
hist["customer_age_days"] = (hist["paid_ts"] - first_seen).dt.total_seconds() / (3600*24)
# Exclude current: age before this order
# (Because first_seen includes this row if it is the first one, that's ok -> age==0)

# Merge back to feature table
safe_df = safe_df.merge(hist.drop(columns=["billing_state","shipping_state","payment_method","payment_amount"]),
                        on=["order_id","customer_id","paid_ts"], how="left")

# Fill NaNs for early customers (no history yet)
fill0 = [c for c in safe_df.columns if c.startswith("cust_txn_count_") or c.startswith("cust_amt_sum_")]
safe_df[fill0] = safe_df[fill0].fillna(0)
safe_df["customer_age_days"] = safe_df["customer_age_days"].fillna(0)
```

> Repeat the same pattern for `email_hash`, `card_hash`, `ip`, `device_hash` if available:
> counts, sums, number of distinct counterpart identities in window (e.g., `n_distinct_cards_on_email_7d`), and ratio features (e.g., `cust_amt_sum_7d / (1 + cust_txn_count_7d)`).

---

# Final feature prep for XGBoost

```python
# Categorical one-hot (low-cardinality only)
cats = ["payment_method","payment_currency","carrier","billing_state","shipping_state","last_status_at_pay"]
for c in cats:
    if c in safe_df: safe_df[c] = safe_df[c].fillna("unknown")

X = pd.get_dummies(
    safe_df.drop(columns=[
        "order_id","customer_id","tracking",
        "estimated_delivery","placed_ts","paid_ts",
        # label column kept separate:
        "is_fraud"
    ], errors="ignore"),
    columns=[c for c in cats if c in safe_df],
    drop_first=False
)

# Label: historical chargeback/confirmed fraud → 1 else 0 (you bring this from your system)
y = safe_df["is_fraud"].astype(int)

# XGBoost (sklearn API)
from xgboost import XGBClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import average_precision_score, roc_auc_score

# Time-aware split is better; for brevity we’ll do simple holdout
X_train, X_test, y_train, y_test = train_test_split(X, y, stratify=y, test_size=0.2, random_state=42)

# Handle imbalance
neg, pos = (y_train==0).sum(), (y_train==1).sum()
scale_pos_weight = max(1.0, neg / max(1, pos))

clf = XGBClassifier(
    n_estimators=600,
    learning_rate=0.05,
    max_depth=6,
    min_child_weight=5,
    subsample=0.8,
    colsample_bytree=0.8,
    reg_lambda=1.0,
    gamma=0.0,
    objective="binary:logistic",
    eval_metric=["aucpr","auc"],
    tree_method="hist",
    scale_pos_weight=scale_pos_weight,
    n_jobs=-1
)
clf.fit(X_train, y_train)

proba = clf.predict_proba(X_test)[:,1]
print("ROC-AUC:", roc_auc_score(y_test, proba))
print("PR-AUC:", average_precision_score(y_test, proba))
```

**Thresholding:** For operations, tune the decision threshold on **PR-curve** or cost curves (chargeback cost vs. false-positive cost), not accuracy.

---

## Quick checklist

* [x] Row grain = **order**
* [x] Feature time = **≤ `paid_ts`**
* [x] No post-payment events used
* [x] Velocity/first-seen features use **only earlier transactions** (shifted/rolling)
* [x] High-cardinality fields → **counts/uniques/frequency**
* [x] Class imbalance → `scale_pos_weight`, **PR-AUC** metric
* [x] Prefer **time-based validation** (e.g., train: older months, test: newer)

---

If you can share which **identities** you actually capture (email hash, card hash, device, IP), I’ll plug those straight into the velocity block and give you the exact pandas you can paste in.
