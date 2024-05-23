FROM maven:3.8.4-openjdk-17 AS builder

# Définissez le répertoire de travail dans le conteneur
WORKDIR /app
COPY . /app/

# Exécutez les tests et construisez le package
RUN mvn clean package -DskipTests

FROM openjdk:17-alpine

# Définissez le répertoire de travail dans le conteneur
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
