FROM maven:3.8.4-openjdk-17 AS builder
# Étape 1 : Construire le backend avec Maven
FROM maven:3.8.4-openjdk-17 AS backend-builder

WORKDIR /app
COPY . /app/

# Étape 3 : Exécuter l'application
FROM openjdk:17-alpine

WORKDIR /app
COPY --from=backend-builder /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

