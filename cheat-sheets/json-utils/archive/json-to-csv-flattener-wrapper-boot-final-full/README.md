# JSON to CSV Flattener with Wrapper & Spring Boot

## ✅ Features
- Deeply nested JSON to flat CSV
- Wrapper architecture (custom logic on top of model)
- Spring Boot CLI & REST API modes
- JUnit 5 test scaffold
- Docker support

---

## 📦 Run Modes

### ▶️ CLI Mode (Default)

```bash
mvn clean package
java -jar target/json-to-csv-flattener-wrapper-boot-1.0-SNAPSHOT.jar \
  --mode=cli \
  --input.dir=src/main/resources/jsons \
  --output.csv=output.csv \
  --fields.prop=flat-fields.properties
```

### 🌐 REST API Mode

```bash
java -jar target/json-to-csv-flattener-wrapper-boot-1.0-SNAPSHOT.jar --mode=web
```

Then use `POST /flatten`:
```bash
curl -X POST http://localhost:8080/flatten -H "Content-Type: application/json" -d @sample.json
```

---

## 🧪 Tests

```bash
mvn test
```

---

## 🐳 Docker

```bash
docker build -t json-csv-flattener .
docker run --rm json-csv-flattener
```

---

## ⚙️ Configuration: `flat-fields.properties`

Control which fields to extract and rename headers.

Syntax:
```properties
field.path=Y:Custom Header
```

Arrays: use `[*]` for flattening dynamic elements.

```properties
id=Y:ID
name=Y:Name
address.city=Y:City
address.zip=Y:ZIP
address.locations[*].type=Y:Address Type
address.locations[*].coords[*]=Y:Coordinates
```
