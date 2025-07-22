Great — now that you're using **Docker Swarm** with `docker stack deploy`, the behavior is **different from `docker-compose`**, especially regarding **scaling and port publishing**.

---

## ✅ Scenario Summary

* You're running a Docker **stack** via:

```bash
docker stack deploy --compose-file docker-stack-compose.yml myapp
```

* Inside `docker-stack-compose.yml`, you have something like:

```yaml
services:
  web:
    image: myapp:latest
    ports:
      - "8384:8080"
    deploy:
      replicas: 2
```

---

## ❗ Problem: Port Conflicts When Scaling

Even though you specify `replicas: 2`, the port mapping `8384:8080` means:

> Each **replica** exposes container port `8080`, but only **one replica** can bind to **host port 8384**.

---

## ✅ How Docker Swarm Handles This

In **Docker Swarm**, **only one replica** is assigned the published port. The others do not bind to host ports directly.

Instead, Swarm routes traffic to the correct backend container through the **ingress routing mesh**.

You do **not** get a port conflict.

---

### ✅ So yes — you *can* safely scale `replicas: 2` with `ports: "8384:8080"` in Swarm!

Swarm will expose `8384` on all nodes, and load-balance to available replicas via the **ingress mesh**.

---

## 🔄 How to Scale Up Replicas (Safely)

You can scale the service (say, to 5 replicas) using:

```bash
docker service scale myapp_web=5
```

✔️ No need to change ports
✔️ No port conflict
✔️ Load-balanced by Swarm

---

## 🔍 How to Check Published Ports

You can inspect the **service**:

```bash
docker service ls
```

To get the service name, then:

```bash
docker service ps myapp_web
```

To inspect details (including published ports):

```bash
docker service inspect myapp_web --format '{{json .Endpoint.Ports}}' | jq
```

You’ll see something like:

```json
[
  {
    "Protocol": "tcp",
    "TargetPort": 8080,
    "PublishedPort": 8384,
    "PublishMode": "ingress"
  }
]
```

---

## 🔍 Check Port Binding on Host (Optional)

```bash
ss -tuln | grep 8384
```

Or:

```bash
docker container ls
docker container inspect <container_id> | jq '.[0].NetworkSettings.Ports'
```

---

## 🧠 Summary

| Task                       | How to do it                            |             |
| -------------------------- | --------------------------------------- | ----------- |
| Scale replicas             | `docker service scale myapp_web=5`      |             |
| Avoid port conflict        | ✅ Swarm ingress handles routing safely  |             |
| Check active services      | `docker service ls`                     |             |
| Inspect service ports      | `docker service inspect myapp_web`      |             |
| Check per-node container   | `docker ps`, then `docker inspect <id>` |             |
| Check port binding on host | \`ss -tuln                              | grep 8384\` |

---

Would you like a visual example or a downloadable demo stack (YAML + sample app + test script)?
