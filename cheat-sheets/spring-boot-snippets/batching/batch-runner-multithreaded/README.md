# Batch Runner (Multi-threaded)

This Spring Boot app runs multiple threads to simulate batch processing using a fixed number of worker threads.

## Features

- Launches 4 threads at startup
- Each thread processes a segment of 1000 records
- Output is printed by thread

## Run

```bash
mvn spring-boot:run
```

Each thread logs its own work to the console.
