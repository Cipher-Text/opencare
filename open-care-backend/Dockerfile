FROM openjdk:latest

WORKDIR /app


COPY /target/open-care-backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 6500

CMD ["java", "-jar", "app.jar"]
