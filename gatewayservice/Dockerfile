FROM openjdk:21
COPY ./build/libs/gatewayservice-0.0.1-SNAPSHOT.jar /usr/src/gatewayservice/gatewayservice-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/gatewayservice
EXPOSE 8080
CMD ["java", "-jar", "gatewayservice-0.0.1-SNAPSHOT.jar"]