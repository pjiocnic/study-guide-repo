Awesome — here’s a **MongoDB Compass–ready practice workbook** you can copy/paste directly into the **Filter** or **Aggregations** tabs. It includes a tiny dataset, beginner→advanced tasks, and ready-made queries/pipelines.

---

# 0) Load the sample data (one-time)

Save as `orders.json` and import:

```json
[
  { "order_id": "ORD001", "customer": "Alice",   "status": "shipped",   "amount": 250, "items": ["pen","book"], "country": "US" },
  { "order_id": "ORD002", "customer": "Bob",     "status": "pending",   "amount": 80,  "items": ["pencil"],     "country": "CA" },
  { "order_id": "ORD003", "customer": "Charlie", "status": "shipped",   "amount": 120, "items": ["pen"],        "country": "US" },
  { "order_id": "ORD004", "customer": "Alice",   "status": "pending",   "amount": 150, "items": ["book"],       "country": "US" },
  { "order_id": "ORD005", "customer": "Eve",     "status": "cancelled", "amount": 300, "items": ["pen","book"], "country": "FR" }
]
```

CLI (optional):

```bash
mongoimport --db practice --collection orders --file orders.json --jsonArray
```

Open **Compass → practice → orders**.

---

# 1) Beginner — paste into **Documents → Filter**

1. All docs

```json
{}
```

2. `status = "pending"`

```json
{ "status": "pending" }
```

3. `amount > 100`

```json
{ "amount": { "$gt": 100 } }
```

4. Country = US

```json
{ "country": "US" }
```

5. Distinct countries (Compass UI)

* Click **“Distinct”** (top-right), Field: `country`

6. Only certain fields (projection)

* Click **PROJECT** in the right panel → add:

```json
{ "order_id": 1, "customer": 1, "_id": 0 }
```

7. Sort by amount desc

* Click **SORT** → add:

```json
{ "amount": -1 }
```

8. Amount between 100 and 200

```json
{ "amount": { "$gte": 100, "$lte": 200 } }
```

9. Array contains "pen"

```json
{ "items": "pen" }
```

---

# 2) Intermediate — use **Aggregations** tab

Click **Create New Pipeline** and add stages exactly as below.

### A) Count shipped orders

```json
[
  { "$match": { "status": "shipped" } },
  { "$count": "shippedCount" }
]
```

### B) Group by status (counts)

```json
[
  { "$group": { "_id": "$status", "count": { "$sum": 1 } } },
  { "$sort": { "count": -1 } }
]
```

### C) Total amount by country

```json
[
  { "$group": { "_id": "$country", "totalAmount": { "$sum": "$amount" } } },
  { "$sort": { "totalAmount": -1 } }
]
```

### D) Top 2 orders by amount

```json
[
  { "$sort": { "amount": -1 } },
  { "$limit": 2 }
]
```

### E) Customers with >1 order

```json
[
  { "$group": { "_id": "$customer", "orderCount": { "$sum": 1 } } },
  { "$match": { "orderCount": { "$gt": 1 } } },
  { "$sort": { "orderCount": -1, "_id": 1 } }
]
```

### F) Group customer → list order\_ids

```json
[
  { "$group": { "_id": "$customer", "orders": { "$push": "$order_id" } } },
  { "$sort": { "_id": 1 } }
]
```

---

# 3) Data quality / duplicates — **Aggregations**

### A) Find duplicates of `order_id` with counts

```json
[
  { "$group": { "_id": "$order_id", "count": { "$sum": 1 } } },
  { "$match": { "count": { "$gt": 1 } } },
  { "$sort": { "count": -1 } }
]
```

### B) Show docs whose `order_id` is missing or null

* In **Documents → Filter**:

Only null:

```json
{ "order_id": { "$type": 10 } }
```

Only missing:

```json
{ "order_id": { "$exists": false } }
```

Null or missing:

```json
{ "order_id": null }
```

---

# 4) Bonus — “group then list full docs” pattern

Compass can’t join pipeline results back to full docs automatically, so do it in two steps:

1. **Aggregations**: get duplicate keys

```json
[
  { "$group": { "_id": "$order_id", "count": { "$sum": 1 } } },
  { "$match": { "count": { "$gt": 1 } } }
]
```

Copy the `_id` values (the duplicate `order_id`s).

2. **Documents → Filter**: list full docs

```json
{ "order_id": { "$in": ["ORD001", "ORD005"] } }
```

---

# 5) Compass Export

Any Aggregation result → click **Export Aggregation Results** (JSON/CSV).
Documents view → **Export Query Results** after applying a filter.

---

If you’d like, I can also spin this into a tiny **mongosh playground script** that:

* reports duplicates,
* optionally deletes all but one per key,
* logs stats to console.
