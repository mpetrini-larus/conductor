# FROM openjdk:17-jdk
FROM eclipse-temurin:17-jdk

WORKDIR /workspace/conductor
COPY . /workspace/conductor

RUN ./gradlew clean build
