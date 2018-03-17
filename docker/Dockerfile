FROM openjdk:8u121-jdk-alpine


COPY ./tradium-0.0.1-SNAPSHOT.jar /home/tradium-0.0.1-SNAPSHOT.jar

COPY ./img home/img


WORKDIR /home



CMD ["java", "-jar", "tradium-0.0.1-SNAPSHOT.jar"]