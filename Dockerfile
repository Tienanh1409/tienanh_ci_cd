FROM maven:3.9.6-ibm-semeru-21-jammy

WORKDIR /app

COPY pom.xml /app/pom.xml
COPY ./src /app/src

RUN mvn clean package

EXPOSE 8002

CMD ["java", "-jar", "/app/target/Reactive-0.0.1-SNAPSHOT.jar"]