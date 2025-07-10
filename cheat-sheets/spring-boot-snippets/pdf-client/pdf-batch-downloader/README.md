
# PDF Batch Downloader

This Spring Boot project reads batches of IDs from an Oracle table, starts a PDF generation process for each,
retrieves the PDF, and saves it to a local folder. It supports both manual triggering (via REST) and scheduled
triggering.

## How to Run

- Configure your Oracle database connection in `application.properties`
- Adjust `batch.size` and `schedule.interval.seconds` as needed
- Run:

```
mvn spring-boot:run
```

- Access manually via:
```
http://localhost:8080/download/batch
```

or let the scheduler run automatically.

