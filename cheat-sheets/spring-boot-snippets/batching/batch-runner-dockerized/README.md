# Batch Runner Dockerized

Dockerized version of the batch runner Spring Boot app.

## Build

```bash
docker build -t batch-runner .
```

## Run Multiple Instances

```bash
./run-batches.sh
```

## View Logs

```bash
docker logs batch1
docker logs batch2
docker logs batch3
```

## Clean Up

```bash
docker stop batch1 batch2 batch3
docker rm batch1 batch2 batch3
```
