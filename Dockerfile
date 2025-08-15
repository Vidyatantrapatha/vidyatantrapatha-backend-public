# ---- STAGE 1: Build the application ----
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies (caching)
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# ---- STAGE 2: Run the application ----
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port
EXPOSE 80