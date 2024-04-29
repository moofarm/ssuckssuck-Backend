FROM openjdk:17-jdk-slim
ARG JAR_PATH=build/libs/ssuckssuck-0.0.1-SNAPSHOT.jar

COPY ${JAR_PATH} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
