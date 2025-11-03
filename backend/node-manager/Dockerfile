FROM eclipse-temurin:20-jdk-jammy
WORKDIR /app
COPY target/node-manager.jar node-manager.jar
ENTRYPOINT ["java","-jar","/app/node-manager.jar"]
