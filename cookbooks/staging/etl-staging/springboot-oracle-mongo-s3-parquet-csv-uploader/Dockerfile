# Build stage
FROM maven:3.9-eclipse-temurin-17 as build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

# Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/oracle-mongo-s3-uploader-0.0.2-SNAPSHOT.jar app.jar
ENV SPRING_CONFIG_LOCATION=/config/application.properties
ENTRYPOINT ["java","-jar","/app/app.jar"]