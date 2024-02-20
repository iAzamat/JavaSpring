FROM openjdk:21
COPY ./build/libs/eureka-server-0.0.1-SNAPSHOT.jar /usr/src/eureka-server/eureka-server-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/eureka-server
EXPOSE 8761
CMD ["java", "-jar", "eureka-server-0.0.1-SNAPSHOT.jar"]