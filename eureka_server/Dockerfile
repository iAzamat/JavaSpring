FROM openjdk:21
COPY ./build/libs/eureka_server-0.0.1-SNAPSHOT.jar /usr/src/eureka_server/eureka_server-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/eureka_server
EXPOSE 8761
CMD ["java", "-jar", "eureka_server-0.0.1-SNAPSHOT.jar"]