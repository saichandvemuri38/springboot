FROM openjdk:21

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} patienceservice.jar

ENTRYPOINT ["java","-jar","/patienceservice.jar"]

EXPOSE 8083