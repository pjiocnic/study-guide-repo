# Spring Boot OpenTelemetry Tracing Project

This project demonstrates how to enable distributed tracing in Spring Boot using:
- ✅ OpenTelemetry
- ✅ Jaeger (Classic tracing UI)
- ✅ Grafana Tempo (for modern Grafana-native traces)

## 🧰 What’s Inside

- Spring Boot 3.x with OpenTelemetry instrumentation
- OTLP exporter for both Tempo and Jaeger
- REST endpoint: `GET /trace`

## 🚀 How to Run

```bash
docker-compose -f docker/docker-compose-tempo.yml up
# or
docker-compose -f docker/docker-compose-jaeger.yml up
```

Then run the Spring Boot app:
```bash
mvn clean install
java -javaagent:opentelemetry-javaagent.jar -Dotel.exporter.otlp.endpoint=http://localhost:4317 -jar target/spring-boot-opentelemetry-tracing-0.0.1-SNAPSHOT.jar
```

## 🌐 UI Access

- Tempo + Grafana: [http://localhost:3000](http://localhost:3000)
- Jaeger: [http://localhost:16686](http://localhost:16686)

Search for traces by service name (e.g., `spring-boot-service`).

