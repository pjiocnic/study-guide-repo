# Spring Batch Starter - Oracle Edition

This project is a production-ready Spring Boot application designed for batch job processing with the following features:

## ✅ Features

- **Oracle Database** support with HikariCP connection pooling
- **Redis-based distributed locking** to avoid overlapping job execution
- **Scheduled batch processing** using `@Scheduled`
- **Logging** with SLF4J and Logback (rotates 10MB x 5 files)
- **Monitoring** via Spring Boot Actuator (`/actuator`)
- **Docker** and **Docker Compose** support
- **Kubernetes Helm Chart**
- **Ready for Prometheus + Grafana monitoring**

---

### 🔧 What’s NEW:

| Feature | Description |
|--------|-------------|
| **Prometheus + Grafana** | Full setup in `docker-compose.yml` |
| **Prometheus Scrape Config** | In `monitoring/prometheus.yml` |
| **Spring Boot ➜ Prometheus** | Metrics exposed at `/actuator/prometheus` |
| **Micrometer + Prometheus** | Integrated in `pom.xml` |
| **Phased Feature Deployment** | Clear instructions in `README.md` to toggle Redis, Prometheus, Oracle, Scheduling |

---

## 🐳 Docker Compose

To start Oracle XE and Redis locally:

```bash
docker-compose up -d
```

Then visit:
- Spring Boot health: [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)
- Prometheus UI: [http://localhost:9090](http://localhost:9090)
- Grafana: [http://localhost:3000](http://localhost:3000) (login: `admin`/`admin`)

---

## 🔁 Scheduled Job

Jobs are picked up every 60 seconds and processed in parallel (5 threads). Only one instance runs the job logic using Redis lock.

---

## 📊 Monitoring

Enable endpoints at:

- `http://localhost:8080/actuator/health`
- `http://localhost:8080/actuator/metrics`

Integrate with Prometheus + Grafana for full monitoring (via Helm chart).

---

## 🛠 How to Build & Run

```bash
mvn clean package
docker build -t spring-batch-starter .
docker run --network host spring-batch-starter
```

---

## 📦 Helm Chart

Located in `helm/spring-batch-starter/`. Install with:

```bash
helm install spring-batch ./helm/spring-batch-starter
```

---

## 🧩 Optional Feature Toggle for Phased Deployment

To enable/disable certain features without deleting them:

### 1. 🔒 Redis Locking
Comment out `RedisLockManager.java` and remove usage from `BatchJobService.java`.

### 2. 📅 Scheduled Processing
Comment out `@Scheduled` and use controller instead.

### 3. 📊 Prometheus + Grafana Monitoring
- Comment out `micrometer-registry-prometheus` in `pom.xml`
- Remove `prometheus` and `grafana` services in `docker-compose.yml`
- Remove `/actuator/prometheus` from `application.properties`

### 4. 🐘 Oracle DB
Switch to PostgreSQL by replacing datasource config and driver dependency.

This way, you can deploy features progressively in different environments.
