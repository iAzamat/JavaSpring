FROM openjdk:21
COPY ./build/libs/api-gateway-0.0.1-SNAPSHOT.jar /usr/src/api-gateway/api-gateway-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/api-gateway
EXPOSE 8765
CMD ["java", "-jar", "api-gateway-0.0.1-SNAPSHOT.jar"]