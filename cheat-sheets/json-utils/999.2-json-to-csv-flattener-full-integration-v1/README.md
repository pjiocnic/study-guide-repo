# JSON to CSV Flattener

## ✅ Features

- Reads multiple `input.json` files from a directory
- Handles nested array fields (e.g., `address.locations[*]`)
- Pads missing array elements
- Uses `flat-fields.properties` to:
  - Include/exclude fields
  - Rename column headers
- Outputs one unified `output.csv` file

---

## 📁 flat-fields.properties Example

```properties
id=Y:ID
name=Y:Name
address.city=Y:City
address.locations[*].type=Y:Address Type
address.locations[*].zip=N
```

---

## 🚀 Run CLI

```bash
java -cp target/json-to-csv-flattener-full-1.0-SNAPSHOT.jar \
     com.example.FlattenBatchJob \
     --input.dir=data/input \
     --output.csv=data/output.csv \
     --fields.prop=flat-fields.properties
```

---

## 🛠️ Build

```bash
mvn clean package
```

Output: `target/json-to-csv-flattener-full-1.0-SNAPSHOT.jar`
