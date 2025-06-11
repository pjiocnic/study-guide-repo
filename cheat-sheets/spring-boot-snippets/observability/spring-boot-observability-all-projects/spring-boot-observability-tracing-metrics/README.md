# Spring Boot Observability: Metrics + Tracing

This project demonstrates combining:
- Prometheus (metrics)
- OpenTelemetry (tracing)
- Grafana (visualization)
- Tempo (trace backend)

## 🔧 Run

```bash
docker-compose up --build
```

Then:
- Access app: [http://localhost:8080/observe](http://localhost:8080/observe)
- Prometheus: [http://localhost:9090](http://localhost:9090)
- Grafana: [http://localhost:3000](http://localhost:3000) (admin/admin)

## 📈 Metrics
Access `/actuator/prometheus` on the app to view metrics.

## 🔍 Tracing
Traces from `/observe` can be visualized via Tempo in Grafana.
