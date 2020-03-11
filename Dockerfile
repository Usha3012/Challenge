FROM adoptopenjdk/openjdk11:latest
COPY target/soniq-project-1.0-SNAPSHOT.jar .
CMD ["java","-jar","soniq-project-1.0-SNAPSHOT.jar"]

