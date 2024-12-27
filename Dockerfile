
FROM maven:3.9.5-eclipse-temurin-21 AS builder


WORKDIR /app


COPY pom.xml .


RUN mvn dependency:go-offline


COPY src ./src


RUN mvn clean package -DskipTests


FROM eclipse-temurin:21-jre


WORKDIR /app


EXPOSE 8080


COPY --from=builder /app/target/*.jar app.jar


#ENV SPRING_PROFILES_ACTIVE=prod


ENTRYPOINT ["java", "-jar", "app.jar"]
