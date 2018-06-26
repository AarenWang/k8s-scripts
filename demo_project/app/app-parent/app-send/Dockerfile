FROM openjdk:8-jdk-alpine3.7
VOLUME /tmp
ADD target/app-send-1.0-SNAPSHOT.jar app-send.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app-send.jar" ]