# Spring Boot Performance Observability with Metrics

This project shows how to measure and export:
- 🔹 Oracle query duration (simulated)
- 🔹 Remote API response duration (simulated)
- 🔹 Total app processing duration (MQ or REST)
- 🔹 Prometheus-compatible metrics

---

## 🧩 Components

| Metric Name                      | Source     | Description                                |
|----------------------------------|------------|--------------------------------------------|
| `db.query.duration`              | DAO/Service| Time spent on Oracle queries               |
| `api.remote.response.duration`   | RESTClient | Time for remote API calls                  |
| `app.total.processing.time`      | App Layer  | Time from receiving request to responding  |

---

## 🚀 Usage Instructions

### 🧰 Requirements
- Java 8
- Maven
- JMS provider (optional, for MQ)
- Prometheus and Grafana (external)

---

### 🛠️ Build and Run
```bash
mvn clean spring-boot:run
```

---

### 🔗 Endpoints
- `GET /api/process` → Simulates Oracle + Remote API + App timing
- `POST /event` (via JMS) → Triggers same processing through MQ

---

### 📡 Prometheus Metrics
Available at:
```
http://localhost:8080/actuator/prometheus
```

---

### 📊 Grafana Setup
1. Add Prometheus as data source (`http://localhost:9090`)
2. Import dashboard with metrics:
   - `rate(db_query_duration_seconds_sum[1m])`
   - `rate(api_remote_response_duration_seconds_sum[1m])`
   - `rate(app_total_processing_time_seconds_sum[1m])`

---

### 🔁 Real Integration Ideas

To use real integrations:
- Replace `Thread.sleep(...)` in `PerformanceService` with:
  - Oracle DB access using `JdbcTemplate` or JPA
  - External API call using `RestTemplate` or `WebClient`

---

### 🧪 Testing MQ
To test the MQ listener:
```bash
# Connect to MQ (e.g., ActiveMQ or IBM MQ) and send a message
```

---

Let me know if you need:
- Docker Compose with Prometheus + Grafana + MQ
- A sample Oracle DB schema
- Loki integration for logs

Enjoy full-stack observability! 🔍