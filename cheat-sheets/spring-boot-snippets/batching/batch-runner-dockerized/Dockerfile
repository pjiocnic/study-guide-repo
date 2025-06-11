FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/batch-runner-springboot.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
