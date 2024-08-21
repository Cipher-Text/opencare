# Stage 1: Build the application using Maven
FROM maven:3.9.2-eclipse-temurin-17 AS build

# Set the working directory inside the build container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Package the application (skip tests if necessary)
RUN mvn clean install -DskipTests

# Stage 2: Create the final image
FROM openjdk:17-jdk-slim

# Set the working directory inside the final container
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/open-care-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's port
EXPOSE 6500

# Run the JAR file with the 'dev' profile
CMD ["java", "-jar", "app.jar", "--spring.profiles.active=dev"]
