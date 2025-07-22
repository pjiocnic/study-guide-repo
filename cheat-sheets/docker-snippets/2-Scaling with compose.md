Here’s a **helper shell script** to monitor the current ports for all containers in a Docker Compose project, grouped by service name and showing external → internal port mappings:

---

### 📜 `show_compose_ports.sh`

```bash
#!/bin/bash

# Optional: specify compose project name
PROJECT_NAME=${1:-$(basename "$PWD")}

echo "📦 Docker Compose Project: $PROJECT_NAME"
echo "🔍 Scanning services and container ports..."
echo

# List all running containers in the project
docker ps --filter "label=com.docker.compose.project=$PROJECT_NAME" --format '{{.Names}}' | while read CONTAINER_NAME; do
  echo "🧱 Container: $CONTAINER_NAME"

  # Show port mappings (host → container)
  docker port "$CONTAINER_NAME" | sed 's/^/   ↪ /'

  echo
done
```

---

### ✅ How to Use:

1. Save it as `show_compose_ports.sh`
2. Make it executable:

```bash
chmod +x show_compose_ports.sh
```

3. Run it from your project directory:

```bash
./show_compose_ports.sh
```

Or specify a different project name:

```bash
./show_compose_ports.sh myproject
```

---

### 🧪 Sample Output

```
📦 Docker Compose Project: myproject
🔍 Scanning services and container ports...

🧱 Container: myproject_web_1
   ↪ 8000/tcp -> 0.0.0.0:8384

🧱 Container: myproject_web_2
   ↪ 8000/tcp -> 0.0.0.0:49153

🧱 Container: myproject_nginx_1
   ↪ 80/tcp -> 0.0.0.0:8080
```

---

Let me know if you'd like an enhanced version that:

* groups by service (`web`, `db`, etc.),
* shows container IPs,
* outputs in CSV or Markdown.
