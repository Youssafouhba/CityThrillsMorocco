FROM eclipse-temurin:20-jdk-alpine
VOLUME /tmp
COPY src
COPY *.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
