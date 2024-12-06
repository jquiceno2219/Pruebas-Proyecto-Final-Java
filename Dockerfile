FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/target/Parcial_2-0.0.1-SNAPSHOT.war proyectofinal-app.war

ENTRYPOINT ["java", "-jar", "proyectofinal-app.war"]