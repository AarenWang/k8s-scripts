FROM openjdk:8-jdk-alpine3.7
VOLUME /tmp
ADD target/app-receive-1.0-SNAPSHOT.jar app-receive.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app-receive.jar" ]