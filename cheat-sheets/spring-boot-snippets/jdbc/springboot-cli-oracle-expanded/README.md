
# Spring Boot CLI Oracle Batch

## Objectives

✅ Command-line Spring Boot application  
✅ Runs as cron or one-off  
✅ Connects to Oracle using JDBC  
✅ Uses externalized properties (dev/prod profiles)  
✅ Docker Compose Oracle XE included  
✅ Sample schema and data init  
✅ Logs with rotation  
✅ Sample unit and integration tests  

## How to run

1. Start Oracle XE using Docker:
```bash
docker-compose up -d
```

2. Build
```bash
mvn clean package
```

3. Run with dev profile
```bash
java -jar target/springboot-cli-oracle-expanded.jar --spring.profiles.active=dev
```

4. Schedule with cron
```
0 2 * * * java -jar /opt/myapp/springboot-cli-oracle-expanded.jar --spring.config.location=/opt/myapp/config.properties --spring.profiles.active=prod >> /opt/myapp/batch.log 2>&1
```

## Notes

- Default table: USERS  
- Updates name based on param1  
- Profile-based config  
- See `schema.sql` for quick test data  
