In MongoDB, you can **rename a field** with `$rename` and **update its value** with `$set` (or `$inc`, `$mul`, etc. depending on your needs).

---

## **1️⃣ Rename a Field**

If your documents look like:

```json
{ "_id": 1, "oldField": "value1" }
```

and you want to rename `oldField` → `newField`:

```javascript
db.myCollection.updateMany(
  {},
  { $rename: { "oldField": "newField" } }
);
```

✅ This keeps the same value but under a new key.

---

## **2️⃣ Update the New Field**

Once renamed, you can update it with `$set`:

```javascript
db.myCollection.updateMany(
  {},
  { $set: { "newField": "newValue" } }
);
```

---

## **3️⃣ Combine Rename + Update in One Step**

You can chain `$rename` and `$set` in a **single `updateMany`** call:

```javascript
db.myCollection.updateMany(
  {},
  {
    $rename: { "oldField": "newField" },
    $set: { "newField": "updatedValue" }
  }
);
```

⚠ **Order matters**: `$rename` happens before `$set` in the same update operation.

---

## **4️⃣ Example with Condition**

Only rename & update where `oldField` exists:

```javascript
db.myCollection.updateMany(
  { oldField: { $exists: true } },
  {
    $rename: { "oldField": "newField" },
    $set: { "newField": "updatedValue" }
  }
);
```

---

## **5️⃣ Updating Based on Current Value**

If you want to modify the existing value instead of overwriting it:

```javascript
db.myCollection.updateMany(
  {},
  [
    { $set: { newField: { $concat: ["$oldField", "_suffix"] } } },
    { $unset: "oldField" } // remove the old field
  ]
);
```

Here we use **aggregation-style updates** (`[ ... ]`) to transform the value before renaming.

---

If you want, I can show you **a version that renames a field and updates only duplicates of `order_id`** in one go — that would fit perfectly with your earlier cleanup scripts.
Do you want me to do that?
