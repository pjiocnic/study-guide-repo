# mongo-cli-crud-json

Spring Boot **command-line** demo that uses **MongoTemplate** for CRUD **and** shows how to
- serialize POJOs to JSON (Jackson),
- deserialize JSON back to POJOs,
- convert raw Mongo `Document` JSON to POJOs.

## What it demonstrates
1. Insert two `Book` docs
2. Fetch as POJOs
3. Serialize one to JSON and deserialize back
4. Serialize a `List<Book>` to JSON array and deserialize back
5. Fetch raw `Document` from Mongo, print JSON, map to `Book`
6. Update and delete

## Prereqs
- Java 17
- Maven 3.9+
- Docker (optional) for local MongoDB

## Run MongoDB (Docker)
```bash
docker compose up -d
# or:
# docker run -d --name mongo -p 27017:27017 mongo:7
```

## Build & Run
```bash
mvn clean package
java -jar target/mongo-cli-crud-json-0.0.1-SNAPSHOT.jar
```

## Code entry points
- `src/main/java/com/example/mongocli/MongoCliCrudJsonApplication.java` – CLI runner with CRUD + JSON
- `src/main/java/com/example/mongocli/model/Book.java` – simple document

## Config
Change Mongo URI in `src/main/resources/application.yml`.
