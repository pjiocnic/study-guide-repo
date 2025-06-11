# 🧭 Spring Boot Observability on Kubernetes

This project demonstrates how to deploy and observe a Spring Boot application on Kubernetes with:

- ✅ Loki for log streaming
- ✅ Prometheus for metrics
- ✅ Grafana for dashboards and alerting
- ✅ Kubernetes YAML configs (Helm-ready or `kubectl` deployable)

---

## 📦 What's Included

| Directory              | Purpose                                      |
|------------------------|----------------------------------------------|
| `k8s/app/`             | Deploys the Spring Boot app as a Deployment and Service |
| `k8s/loki/`            | Configures Loki for log aggregation          |
| `k8s/grafana/`         | Adds alert rules for Prometheus + Grafana    |
| `helm/` (optional)     | Helm-compatible directory layout              |

---

## 🚀 Deployment Instructions

### 1. ⬇️ Pre-requisites

- Kubernetes cluster (e.g., Minikube, Kind, EKS, GKE)
- `kubectl` CLI
- [Loki](https://grafana.com/oss/loki/), [Prometheus](https://prometheus.io/), [Grafana](https://grafana.com/) — can be installed via Helm

### 2. 📥 Apply Configs

```bash
kubectl apply -f k8s/loki/loki-config.yaml
kubectl apply -f k8s/app/deployment.yaml
kubectl apply -f k8s/grafana/alerting-rules.yaml
```

You can also use Helm for Grafana + Prometheus stack:

```bash
helm repo add grafana https://grafana.github.io/helm-charts
helm install loki grafana/loki-stack
helm install grafana grafana/grafana
helm install prometheus prometheus-community/kube-prometheus-stack
```

---

## 🌐 Access Services

> You may need `kubectl port-forward` or an Ingress controller to access services from your browser.

```bash
kubectl port-forward svc/observability-app 8080:8080
kubectl port-forward svc/prometheus 9090:9090
kubectl port-forward svc/grafana 3000:3000
```

---

## 📈 Observability Features

| Feature                | How it's done                              |
|------------------------|--------------------------------------------|
| Application Metrics    | Micrometer exposes `/actuator/prometheus` |
| Log Streaming          | Logs written to pod volume streamed to Loki |
| Alerting               | Triggered via `alerting-rules.yaml`        |

---

## 🔔 Grafana Alerting Rule Example

Alerts when app processing time exceeds threshold:

```yaml
- alert: HighProcessingTime
  expr: rate(app_total_processing_time_seconds_sum[1m]) > 1
  for: 1m
```

---

## 🛠️ Best Practices

- Externalize configs with `ConfigMap` or `Secrets`
- Use `emptyDir` or `PVC` for pod logs (scraped by Loki)
- Use `values.yaml` with Helm for environment-specific overrides
- Always define resource limits for containers

---

## ✅ Next Steps

- Add Promtail DaemonSet to scrape logs from all pods
- Setup TLS Ingress (e.g., with cert-manager)
- Use Grafana Cloud or Alertmanager to email alerts

---

Happy Kubernetes Observability! 📡