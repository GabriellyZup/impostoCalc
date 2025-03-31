FROM eclipse-temurin:17-jdk AS build
WORKDIR /workspace/app
COPY . .
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre
VOLUME /tmp
COPY --from=build /workspace/app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]