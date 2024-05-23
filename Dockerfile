FROM maven:3.8.4-openjdk-17 AS builder

# Étape 1 : Construire le frontend avec Node.js
FROM node:20.11.0 AS frontend-builder

WORKDIR /app
COPY AtlasGuide_angular/AtlasGuide_angular-master /app/
RUN npm install
RUN npm run build

# Étape 2 : Construire le backend avec Maven
FROM maven:3.8.4-openjdk-17 AS backend-builder

WORKDIR /app
COPY . /app/
# Copier le build du frontend dans le backend
COPY --from=frontend-builder /app/dist /app/src/main/resources/static

RUN mvn clean package -DskipTests

# Étape 3 : Exécuter l'application
FROM openjdk:17-alpine

WORKDIR /app
COPY --from=backend-builder /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

