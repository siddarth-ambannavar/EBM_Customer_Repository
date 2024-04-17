FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} customer-0.0.1.jar

ENTRYPOINT ["java", "-jar", "/customer-0.0.1.jar"]

EXPOSE 8082