FROM eclipse-temurin:20-jdk-jammy
WORKDIR /app
COPY target/analytics-service.jar analytics-service.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app/analytics-service.jar"]
