
FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY target/file-generator-app-1.0.jar app.jar

VOLUME ["/data/output"]
ENV FILEGEN_OUTPUT_DIR=/data/output

ENTRYPOINT ["java", "-Dfilegen.output-dir=/data/output", "-jar", "app.jar"]
