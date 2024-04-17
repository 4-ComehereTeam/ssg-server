FROM openjdk:17
COPY build/libs/ssgserver-0.0.1-SNAPSHOT.jar ssgserver.jar
ENTRYPOINT ["java", "-jar", "ssgserver.jar"]

