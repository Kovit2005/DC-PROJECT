FROM eclipse-temurin:20-jdk-jammy
WORKDIR /app
COPY target/gateway-service.jar gateway-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/gateway-service.jar"]
