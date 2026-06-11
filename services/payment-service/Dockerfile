FROM eclipse-temurin:17-jre

WORKDIR /app

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} payment-service.jar
COPY build/otel/opentelemetry-javaagent-1.33.3.jar opentelemetry-javaagent-1.33.3.jar

ENV OTEL_EXPORTER_OTLP_ENDPOINT=http://host.docker.internal:4317
ENV OTEL_METRICS_EXPORTER=none
ENV OTEL_EXPORTER_OTLP_PROTOCOL=grpc

EXPOSE 8085

ENTRYPOINT ["java", "-Dotel.service.name=payment-service", "-javaagent:/app/opentelemetry-javaagent-1.33.3.jar", "-jar", "/app/payment-service.jar"]
