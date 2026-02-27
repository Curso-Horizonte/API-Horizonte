FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

ENV MAVEN_OPTS="-Dfile.encoding=UTF-8"

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests -Dproject.build.sourceEncoding=UTF-8

FROM openjdk:21-jdk-slim
WORKDIR /app

COPY --from=build /app/target/API-Fluxar-0.0.1-SNAPSHOT.jar app.jar

ENV JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF-8"

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
