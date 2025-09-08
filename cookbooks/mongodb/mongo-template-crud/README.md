# mongo-template-crud

Spring Boot 3.x + **MongoTemplate** CRUD example (Java 17). No Spring Data repositories—just `MongoTemplate`.

## Quick start

### 1) Start Mongo (Docker)
```bash
docker compose up -d
```

### 2) Run the app
```bash
./mvnw spring-boot:run
# or
mvn spring-boot:run
```

App listens on `http://localhost:8080`.

### 3) API
- `POST /api/items` – create
- `GET /api/items?page=0&size=10&q=pen` – list with optional search
- `GET /api/items/{id}` – read
- `PUT /api/items/{id}` – replace
- `PATCH /api/items/{id}` – partial update
- `DELETE /api/items/{id}` – delete
- `DELETE /api/items` – delete all

### Example
```bash
curl -X POST http://localhost:8080/api/items   -H "Content-Type: application/json"   -d '{"name":"Pencil","description":"HB 0.5mm","quantity":20}'
```

## Notes
- Configure Mongo URI in `src/main/resources/application.yml`.
- Add custom converters in `config/MongoConfig` if needed.
