FROM eclipse-temurin:18.0.1_10-jre-alpine
ARG JAR_FILE=build/docker/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]