FROM openjdk:21

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} authsecurity.jar

ENTRYPOINT ["java","-jar","/authsecurity.jar"]

EXPOSE 8082