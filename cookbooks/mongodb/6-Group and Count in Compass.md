In **MongoDB Compass**, you can get counts grouped by some property using the **Aggregation Pipeline** view.

---

## **Step-by-Step: Group and Count in Compass**

1. **Open your collection** in MongoDB Compass.
2. Click the **"Aggregations"** tab.
3. Click **"Create New Pipeline"**.

---

### **Stage 1 – `$group`**

In the stage editor, select **`$group`** and enter:

```json
{
  "_id": "$<fieldName>",
  "count": { "$sum": 1 }
}
```

* Replace `<fieldName>` with your property name, for example `"order_id"`.
* `_id` is the field you’re grouping by.
* `"count"` will hold the number of documents per group.

---

### **Stage 2 – (Optional) `$sort`**

Add a new stage `$sort`:

```json
{ "count": -1 }
```

This sorts the results by count, highest first.

---

### **Example: Count Orders by `status`**

```json
[
  { "$group": { "_id": "$status", "count": { "$sum": 1 } } },
  { "$sort": { "count": -1 } }
]
```

**Result example:**

```json
{ "_id": "shipped", "count": 145 }
{ "_id": "pending", "count": 67 }
```

---

### **Exporting Results**

After running the aggregation, you can click **Export Aggregation Results** to save the grouped counts as JSON or CSV.

---

If you’d like, I can also show you how to **filter before grouping** (e.g., count only documents where `status = "pending"`).
Do you want me to add that?
