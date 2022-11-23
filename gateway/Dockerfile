FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} gateway-v1.jar
ENTRYPOINT ["java","-jar","/gateway-v1.jar"]
EXPOSE 8080