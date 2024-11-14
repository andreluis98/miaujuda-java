FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/br.com.miaujuda-0.0.1-SNAPSHOT.jar backend.jar


ENTRYPOINT ["java", "-jar", "backend.jar"]
