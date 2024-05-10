FROM eclipse-temurin:21.0.3_9-jre-ubi9-minimal
WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
