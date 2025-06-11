# Batch Runner Spring Boot

A template Spring Boot app to launch multiple batch instances with custom range and Spring profile support.

## How It Works

- Use command line to pass batch range: `--batch.start`, `--batch.end`
- Configure different environments via Spring profiles (`dev`, `prod`, `test-batch`)
- Batch logic runs on startup via `@PostConstruct`

## Run Example

```bash
java -jar target/batch-runner-springboot.jar --batch.start=100 --batch.end=200 --spring.profiles.active=prod
```

## Profiles Config

| Profile       | Default Start | Default End |
|---------------|----------------|-------------|
| `dev`         | 0              | 99          |
| `prod`        | 100            | 199         |
| `test-batch`  | 200            | 299         |
