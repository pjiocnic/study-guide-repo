# Spring Boot Monitoring with Prometheus + Grafana

This project demonstrates monitoring multiple Spring Boot instances with:
- Prometheus for scraping metrics
- Grafana for dashboards
- PostgreSQL DB for sample query metrics

## 🚀 How to Run

```bash
docker-compose up --build
```

Then open Grafana at `http://localhost:3000` (admin/admin) and explore the dashboards.

Spring Boot apps expose metrics at `/actuator/prometheus`.

## 🧪 Simulate Load

Use tools like `wrk`, Postman, or `ab` to generate requests to `http://localhost:8080`.

