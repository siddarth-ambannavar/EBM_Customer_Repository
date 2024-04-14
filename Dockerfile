FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} ebmcustomerservice.jar

ENTRYPOINT ["java", "-jar", "/ebmcustomerservice.jar"]

EXPOSE 8082