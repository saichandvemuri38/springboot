FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} libraryService.jar

ENTRYPOINT ["java","-jar","/libraryService.jar"]

EXPOSE 8080