FROM openjdk:21
COPY ./build/libs/userservice-0.0.1-SNAPSHOT.jar /usr/src/userservice/userservice-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/userservice
EXPOSE 8082
CMD ["java", "-jar", "userservice-0.0.1-SNAPSHOT.jar"]