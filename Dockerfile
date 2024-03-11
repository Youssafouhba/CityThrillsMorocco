FROM eclipse-temurin:20-jdk-alpine
VOLUME /tmp
# Utilisez une image de base avec Java et Maven
FROM maven:3.8.4-openjdk-11 AS builder

# Définissez le répertoire de travail dans le conteneur
WORKDIR /app

# Copiez le fichier pom.xml dans le conteneur
COPY pom.xml .
# Copiez le reste des fichiers du projet dans le conteneur
COPY src ./src

COPY *.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
