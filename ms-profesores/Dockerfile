FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-profesores-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/ms-profesores-0.0.1-SNAPSHOT.jar"]