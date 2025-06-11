
# Spring Boot Observability with Prometheus & Grafana

## ✅ Objectives
This project demonstrates how to:
- Monitor a Spring Boot application using Prometheus and Grafana
- Expose Actuator metrics for Prometheus scraping
- Visualize data in Grafana dashboards
- Package all components using Docker Compose
- Validate functionality with unit tests

---

## 🛠️ Build and Run Instructions

### 1. Pre-requisites
- Docker + Docker Compose
- Java 8
- Maven

### 2. Build Spring Boot App
```bash
mvn clean package
```

### 3. Start Monitoring Stack
```bash
docker-compose up --build
```

- Spring Boot: [http://localhost:8080/actuator](http://localhost:8080/actuator)
- Prometheus: [http://localhost:9090](http://localhost:9090)
- Grafana: [http://localhost:3000](http://localhost:3000) (Login: `admin` / `admin`)

### 4. Test Endpoints
```bash
curl http://localhost:8080/api/hello
```

### 5. Run Unit Tests
```bash
mvn test
```

---

## 📂 Project Structure
```
.
├── docker-compose.yml
├── prometheus/
│   └── prometheus.yml
├── src/
│   ├── main/
│   │   ├── java/com/example/observability/
│   │   │   ├── ObservabilityApp.java
│   │   │   └── controller/HelloController.java
│   └── test/
│       └── java/com/example/observability/
│           └── controller/HelloControllerTest.java
```

---

## 📈 Grafana Dashboards
You can add a new Prometheus data source with:
```
URL: http://prometheus:9090
```

Then create dashboards for:
- JVM memory, GC
- HTTP requests
- Custom application metrics

Enjoy your observability journey! 🎯
