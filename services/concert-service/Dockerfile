FROM eclipse-temurin:17-jre

WORKDIR /app

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} concert-service.jar
COPY build/otel/opentelemetry-javaagent-1.33.3.jar opentelemetry-javaagent-1.33.3.jar

ENV OTEL_EXPORTER_OTLP_ENDPOINT=http://host.docker.internal:4317
ENV OTEL_METRICS_EXPORTER=none
ENV OTEL_EXPORTER_OTLP_PROTOCOL=grpc

EXPOSE 8080

ENTRYPOINT ["java", "-Dotel.service.name=concert-service", "-javaagent:/app/opentelemetry-javaagent-1.33.3.jar", "-jar", "/app/concert-service.jar"]

