FROM openjdk:21
COPY ./build/libs/taskservice-0.0.1-SNAPSHOT.jar /usr/src/taskservice/taskservice-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/taskservice
EXPOSE 8081
CMD ["java", "-jar", "taskservice-0.0.1-SNAPSHOT.jar"]