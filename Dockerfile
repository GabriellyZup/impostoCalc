FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/impostoCalc-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
CMD ["java", "-jar", "app.jar"]

