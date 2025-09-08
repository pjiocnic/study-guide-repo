# mongo-cli-crud

Aptly named Spring Boot **command-line** demo that uses **MongoTemplate** (not repositories) to perform basic CRUD.

## What it does
On startup, the app runs a `CommandLineRunner` that:
1. Inserts a `Book`
2. Reads all books and by author
3. Updates the `year`
4. Deletes the book
5. Prints final state

## Prereqs
- Java 17
- Maven 3.9+
- Docker (optional, for local MongoDB)

## Run MongoDB (Docker)
```bash
docker compose up -d
# or, directly:
# docker run -d --name mongo -p 27017:27017 mongo:7
```

## Configure (if needed)
Edit `src/main/resources/application.yml` to change the MongoDB URI.

## Build & Run
```bash
mvn clean package
java -jar target/mongo-cli-crud-0.0.1-SNAPSHOT.jar
```

You should see console output showing insert → find → update → delete using `MongoTemplate`.

## Project layout
```
mongo-cli-crud/
├─ pom.xml
├─ src/main/java/com/example/mongocli
│  ├─ MongoCliCrudApplication.java   # CommandLineRunner with MongoTemplate CRUD
│  └─ model/Book.java                # Example document
└─ src/main/resources/application.yml
```
