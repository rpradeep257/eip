# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

ENV SERVER_PORT=7000

# The application's jar file
ARG JAR_FILE=target/*.jar

# COPY the application's jar to the container
COPY ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"] 

EXPOSE ${SERVER_PORT}