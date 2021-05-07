FROM maven:3-openjdk-11 as build
COPY pom.xml .
COPY src ./src
RUN ["mvn", "clean", "package"]

FROM openjdk:11-jdk-slim
COPY --from=build /app/target/proposta-*.jar /app/proposta.jar
EXPOSE 8080
CMD ["/usr/bin/java", "-jar", "/app/proposta.jar"]