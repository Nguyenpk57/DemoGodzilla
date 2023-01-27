FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
WORKDIR .
COPY ./target/${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]