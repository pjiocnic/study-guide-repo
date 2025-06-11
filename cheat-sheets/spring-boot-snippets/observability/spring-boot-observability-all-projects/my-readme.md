
### 📦 Overview of Spring Boot Observability Projects

| **Project ZIP File** | **Focus Area** | **Features Demonstrated** | **Visualization Tools** | **Docker Compose** | **Beginner Tutorial** |
|----------------------|----------------|----------------------------|--------------------------|---------------------|------------------------|
| [`spring-boot-monitoring-prometheus-grafana`]) | Metrics Monitoring | ✅ Prometheus metrics via Actuator<br>✅ PostgreSQL integration<br>✅ HikariCP pool + thread metrics | 📊 Grafana dashboards | ✅ Yes | ✅ Yes |
| [`spring-boot-opentelemetry-tracing`]() | Distributed Tracing | ✅ OpenTelemetry Tracing<br>✅ OTLP Exporter<br>✅ Sample REST endpoint | 🔍 Jaeger<br>🔍 Grafana Tempo | ✅ Yes (2 versions: Jaeger + Tempo) | ✅ Yes |
| [`spring-boot-observability-tracing-metrics`]() | Combined Observability | ✅ Prometheus metrics<br>✅ OpenTelemetry tracing<br>✅ Tempo backend<br>✅ All-in-one dashboard stack | 📊 Grafana (for both metrics + traces)<br>📈 Prometheus<br>🔍 Tempo | ✅ Yes (Unified stack) | ✅ Yes |

---

### 🧭 How to Choose

| **Use Case** | **Recommended Project** |
|--------------|--------------------------|
| Monitor only metrics like DB calls, thread usage | `spring-boot-monitoring-prometheus-grafana` |
| Visualize distributed traces across microservices | `spring-boot-opentelemetry-tracing` |
| Combine both tracing and metrics in a single dashboard | `spring-boot-observability-tracing-metrics` |

---

Would you like a unified ZIP that includes all three examples for offline reference? Or perhaps a **Kubernetes Helm chart version** of the combined stack?