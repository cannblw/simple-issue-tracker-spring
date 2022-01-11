# Build JAR
FROM maven:3.8.4-openjdk-17 AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app


COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

# Run
FROM openjdk:17-alpine
WORKDIR /opt/app

COPY --from=maven /usr/src/app/target/*.jar /opt/app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]