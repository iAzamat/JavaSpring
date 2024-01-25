FROM openjdk:17
COPY ./build/libs/LibraryEditor-0.0.1-SNAPSHOT.jar /usr/src/my-app/LibraryEditor-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/my-app
EXPOSE 8080
CMD ["java", "-jar", "LibraryEditor-0.0.1-SNAPSHOT.jar"]