# JSON to CSV Flattener with Wrappers (Spring Boot Edition)

This project reads multiple JSON files from the `jsons/` directory, flattens them using wrapper objects, and outputs a single CSV file using customizable field selection.

## ✅ Features

- Spring Boot compatible
- CLI argument support for batch jobs
- Optional REST API for interactive use
- Dockerized
- Wrapper classes for extensibility
- Supports wildcards and nested JSON arrays

---

## 🚀 Run Instructions

### 🔨 Build
```bash
mvn clean package
```

### ▶️ Run as CLI Batch Job
```bash
# Arguments:
# --input.dir     Directory containing JSON files
# --output.csv    Output CSV file path
# --fields.prop   Path to flat-fields.properties

java -jar target/json-to-csv-flattener-wrapper-boot-1.0-SNAPSHOT.jar \
  --input.dir=./jsons \
  --output.csv=./output.csv \
  --fields.prop=./flat-fields.properties
```

Defaults:
- Input Dir: `./jsons`
- Output File: `./output.csv`
- Properties File: `./flat-fields.properties`

---

### 🌐 Run as REST API
```bash
curl -X POST http://localhost:8080/flatten   -H "Content-Type: application/json"   --data-binary @jsons/input1.json
```

- Endpoint: `POST /flatten`
- Response: CSV row (flattened) as JSON

---

### 🐳 Run with Docker
```bash
docker build -t json-flattener .
docker run --rm -v $PWD/jsons:/app/jsons json-flattener \
  --input.dir=/app/jsons --output.csv=/app/output.csv
```
