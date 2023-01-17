FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
WORKDIR .
CMD ./mvnw clean package -DskipTests
CMD cp ./target/godzilla.jar src/main/docker
COPY src/main/docker/${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]