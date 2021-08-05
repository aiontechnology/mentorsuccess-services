FROM adoptopenjdk:16-jre-hotspot
ARG JAR_FILE=build/docker/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]