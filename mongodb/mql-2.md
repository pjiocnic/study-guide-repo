Alright — here’s a **hands-on MongoDB Practice Guide** you can use to go from beginner to advanced querying skills.

I’ve broken it into:

1. **Sample Dataset** (to load into Mongo)
2. **Beginner Challenges**
3. **Intermediate Challenges**
4. **Advanced Challenges**
5. **Solutions & Explanations**

---

## **1️⃣ Sample Dataset**

Save this as `orders.json` and import into Mongo:

```json
[
  { "order_id": "ORD001", "customer": "Alice",   "status": "shipped", "amount": 250, "items": ["pen", "book"], "country": "US" },
  { "order_id": "ORD002", "customer": "Bob",     "status": "pending", "amount": 80,  "items": ["pencil"],      "country": "CA" },
  { "order_id": "ORD003", "customer": "Charlie", "status": "shipped", "amount": 120, "items": ["pen"],         "country": "US" },
  { "order_id": "ORD004", "customer": "Alice",   "status": "pending", "amount": 150, "items": ["book"],        "country": "US" },
  { "order_id": "ORD005", "customer": "Eve",     "status": "cancelled", "amount": 300, "items": ["pen","book"],"country": "FR" }
]
```

Import via:

```bash
mongoimport --db practice --collection orders --file orders.json --jsonArray
```

---

## **2️⃣ Beginner Challenges**

1. Find all orders.
2. Find orders where `status` is `"pending"`.
3. Find orders with `amount` greater than 100.
4. Find orders from `"US"` customers.
5. Find distinct countries in the collection.

---

## **3️⃣ Intermediate Challenges**

6. Count how many orders are `"shipped"`.
7. Show only `order_id` and `customer` for all orders.
8. Find orders where `"pen"` is in the `items` array.
9. Sort orders by `amount` descending.
10. Find orders with `amount` between 100 and 200.

---

## **4️⃣ Advanced Challenges**

11. Group by `status` and count orders in each.
12. Find the total amount of orders per country.
13. Find the top 2 highest `amount` orders.
14. Find customers who have placed more than 1 order.
15. Group by `customer` and list all `order_id`s for each.

---

## **5️⃣ Solutions & Explanations**

### Beginner

```javascript
// 1
db.orders.find()

// 2
db.orders.find({ status: "pending" })

// 3
db.orders.find({ amount: { $gt: 100 } })

// 4
db.orders.find({ country: "US" })

// 5
db.orders.distinct("country")
```

### Intermediate

```javascript
// 6
db.orders.countDocuments({ status: "shipped" })

// 7
db.orders.find({}, { order_id: 1, customer: 1, _id: 0 })

// 8
db.orders.find({ items: "pen" })

// 9
db.orders.find().sort({ amount: -1 })

// 10
db.orders.find({ amount: { $gte: 100, $lte: 200 } })
```

### Advanced

```javascript
// 11
db.orders.aggregate([
  { $group: { _id: "$status", count: { $sum: 1 } } }
])

// 12
db.orders.aggregate([
  { $group: { _id: "$country", totalAmount: { $sum: "$amount" } } }
])

// 13
db.orders.find().sort({ amount: -1 }).limit(2)

// 14
db.orders.aggregate([
  { $group: { _id: "$customer", orderCount: { $sum: 1 } } },
  { $match: { orderCount: { $gt: 1 } } }
])

// 15
db.orders.aggregate([
  { $group: { _id: "$customer", orders: { $push: "$order_id" } } }
])
```

---

I can also make this into a **MongoDB Compass-ready version** so you can copy/paste each query into the Compass **Filter** or **Aggregations** tab without modification.

Do you want me to prepare a **Compass-ready** workbook for these exercises? That way, you can practice visually while seeing the pipeline stages.
