FROM maven:3.8.2-jdk-11-slim AS build

COPY pom.xml /build/

COPY src /build/src

WORKDIR /build/

RUN ["mvn", "package", "-Dmaven.test.skip=true"]

FROM openjdk:11.0.6-jre-slim

WORKDIR /app

COPY --from=build /build/target/todolister-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "todolister-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080