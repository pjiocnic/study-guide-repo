# Spring Boot Multi-Instance Monitoring

This project demonstrates monitoring of multiple Spring Boot 3.x instances using:
- Prometheus (metrics scraping)
- Grafana (dashboarding)
- Micrometer + Spring Boot Actuator
- PostgreSQL database (monitored via HikariCP)

## 📦 Included Services
- 2 Spring Boot instances
- Prometheus
- Grafana
- PostgreSQL

## 📊 Metrics Tracked
- HTTP request count & latency
- DB connection pool stats
- Active threads
- Custom metrics

## 🚀 How to Run

```bash
docker-compose up --build
```

Access Grafana: http://localhost:3000  
Default credentials: `admin` / `admin`  
Prometheus: http://localhost:9090

Dashboards will be preloaded.

## 🔧 Endpoints

- `GET /hello` - test endpoint
- `/actuator/prometheus` - metrics endpoint
