```bash
const dups = db.orders.aggregate([
  {
    $group: {
      _id: "$order_id",
      count: { $sum: 1 },
      ids: { $push: "$_id" }
    }
  },
  {
    $match: {
      count: { $gt: 1 }
    }
  }
]).toArray();


dups.forEach(doc => {

  if (!doc.ids || doc.ids.length <= 1) {
    print(`Nothing to delete for order_id: ${doc._id}`);
    return;
  }

  if (doc.ids && doc.ids.length > 1) {
    const idsToDelete = doc.ids.slice(1); // keep first
    db.orders.deleteMany({ _id: { $in: idsToDelete } });
  }
});
```