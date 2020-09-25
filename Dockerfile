FROM adoptopenjdk:14-jre-hotspot
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=production"]