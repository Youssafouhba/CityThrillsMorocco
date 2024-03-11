FROM eclipse-temurin:20-jdk-alpine
VOLUME /tmp
# Compilez et empaquetez l'application
RUN mvn package
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
