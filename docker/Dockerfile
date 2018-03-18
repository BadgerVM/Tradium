FROM openjdk:8u121-jdk-alpine

COPY ./Docker.jar /home/Docker.jar
WORKDIR /home


ENTRYPOINT ["java", "-jar", "Docker.jar"]