FROM adoptopenjdk:11-jre-hotspot
WORKDIR .
ARG JAR_FILE=*.jar
COPY src/main/docker/${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]