FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
CMD sleep 5 && java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=container -jar app.jar