FROM eclipse-temurin:21-jdk

WORKDIR /workspace/conductor
COPY . /workspace/conductor

RUN ./gradlew clean build -x test
