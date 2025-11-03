FROM eclipse-temurin:20-jdk-jammy
WORKDIR /app
COPY target/booking-service.jar booking-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/booking-service.jar"]
