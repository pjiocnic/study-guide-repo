# 📊 Spring Boot Observability Project

This project demonstrates full observability and monitoring for a Spring Boot 3.x application using:

- ✅ Prometheus + Grafana (metrics and dashboards)
- ✅ Oracle XE (simulated DB workload)
- ✅ ActiveMQ (event processing via JMS)
- ✅ Micrometer (metrics instrumentation)
- ✅ TestContainers (integration testing for Oracle + MQ)
- ✅ Docker Compose for local orchestration

---

## 🎯 Objectives

This project teaches:

- How to expose application, DB, and API metrics
- How to deploy an observability stack with Prometheus and Grafana
- How to simulate event-driven MQ processing
- How to integrate with Oracle and monitor DB interactions
- Best practices in Spring Boot project structure and configurations

---

## 📁 Project Structure

```
.
├── docker-compose.yml
├── pom.xml
├── prometheus.yml
├── grafana/
│   ├── provisioning/
│   │   └── dashboards.yml
│   └── dashboards/
│       └── observability.json
├── oracle/
│   └── init/
│       └── schema.sql
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/observability/
│   │   │       ├── controller/
│   │   │       ├── dao/
│   │   │       ├── mq/
│   │   │       ├── service/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/example/observability/integration/
```

---

## 🚀 How to Run

### 1. 📦 Build Spring Boot Application

```bash
mvn clean package
```

### 2. 🐳 Start Docker Stack

```bash
docker-compose up --build
```

This launches:
- Oracle XE
- ActiveMQ
- Prometheus
- Grafana
- Your Spring Boot app

---

## 🔗 Application URLs

| Component      | URL                                |
|----------------|-------------------------------------|
| Spring Boot    | http://localhost:8080               |
| Actuator Metrics | http://localhost:8080/actuator/prometheus |
| Prometheus     | http://localhost:9090               |
| Grafana        | http://localhost:3000 (admin/admin) |
| ActiveMQ Admin | http://localhost:8161              |

---

## 📌 Key Concepts & Best Practices

### 🧩 Externalizing Configurations

- All JDBC and MQ settings are in `application.properties`
- Docker container schema loaded from `oracle/init/schema.sql`
- SQL code belongs in `.sql` files, not inline strings
- Use `@Value` or `@ConfigurationProperties` for sensitive values

### 💾 Persistence

`MetricsDao` uses `JdbcTemplate` for writing metrics:

```java
jdbcTemplate.update("INSERT INTO metrics_demo (name, value) VALUES (?, ?)", name, value);
```

---

## 📈 Metrics Tracked

| Metric                          | Description                              |
|----------------------------------|------------------------------------------|
| `db.query.duration`              | Oracle query latency                     |
| `api.remote.response.duration`   | External API latency                     |
| `app.total.processing.time`      | End-to-end processing duration (REST/MQ) |

---

## 📊 Grafana Dashboard

- Auto-provisioned via `/grafana/dashboards/observability.json`
- Queries like: `rate(app_total_processing_time_seconds_sum[1m])`

---

## 🧪 Integration Testing (TestContainers)

Run:
```bash
mvn test
```

Tests will:
- Launch Oracle + ActiveMQ containers
- Validate they're reachable and running

```java
assertThat(oracle.isRunning()).isTrue();
assertThat(activemq.isRunning()).isTrue();
```

---

## 🔒 Logging and Observability

- Uses SLF4J with Spring Boot starter
- Easily pluggable with JSON formatters for Loki integration

---

## 📤 Deployment

To deploy this:
- Replace Oracle with your production database
- Set secrets via environment variables or external `.properties` files
- Push metrics to Prometheus (cloud-hosted or managed Grafana Cloud)
- Use Grafana Cloud dashboards for alerting, SLA/SLOs

---

## 🧠 Next Steps

- Add resilience patterns: retries, circuit breakers
- Integrate structured logging (JSON for Loki)
- Add HTTP tracing with OpenTelemetry + Tempo

---

Happy Observing! 📡