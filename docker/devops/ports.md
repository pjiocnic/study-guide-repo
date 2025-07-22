
### show_compose_ports.sh

```sh
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
