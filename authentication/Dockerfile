FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} authentication-v1.jar
ENTRYPOINT ["java","-jar","/authentication-v1.jar"]
EXPOSE 8081