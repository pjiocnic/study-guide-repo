FROM eclipse-temurin:8-jdk-alpine

ARG JAR_FILE=target/email-batch-springboot-oracle-freemarker-validated.jar
COPY ${JAR_FILE} app.jar

VOLUME /config
VOLUME /templates
VOLUME /logs

ENTRYPOINT ["java", "-Dconfig.path=/config/config.properties", "-Dtemplate.path=/templates/email.ftl", "-jar", "/app.jar"]
