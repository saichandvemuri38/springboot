FROM openjdk:21

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} billingservice.jar

ENTRYPOINT ["java","-jar","/billingservice.jar"]

EXPOSE 8084