# Spring Boot Performance Observability

This example demonstrates how to monitor:

- Oracle query duration
- Remote API call duration
- End-to-end app processing duration

### Endpoints

- `GET /api/process`: Simulates DB + API processing and records metrics

### Metrics Tracked

| Metric Name                      | Description                                |
|----------------------------------|--------------------------------------------|
| db.query.duration                | Time spent executing simulated Oracle query|
| api.remote.response.duration     | Time for simulated API response            |
| app.total.processing.time        | Total time from receiving to responding    |

### How to Run

```bash
mvn spring-boot:run
```

Access metrics at:
```
http://localhost:8080/actuator/prometheus
```

Use in Grafana with Prometheus for dashboards.