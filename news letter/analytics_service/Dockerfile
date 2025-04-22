FROM openjdk:21

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} analyticsservice.jar

ENTRYPOINT ["java","-jar","/analyticsservice.jar"]

EXPOSE 8085