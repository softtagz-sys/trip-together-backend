# Use JDK 23 for building the application
FROM eclipse-temurin:23-jdk-alpine AS builder

# Set working directory inside container
WORKDIR /app

# Copy Gradle wrapper and build files first (to leverage caching)
COPY gradlew gradlew.bat settings.gradle.kts build.gradle.kts ./
COPY gradle/ gradle/

# Copy the entire source code
COPY src/ src/

# Make the Gradle wrapper executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build -x test

# Use a smaller JRE image for running the application
FROM eclipse-temurin:23-jre-alpine

# Set working directory inside container
WORKDIR /app

# Copy only the built JAR from the builder stage
COPY --from=builder /app/build/libs/trip-together-backend-0.0.1.jar /app/app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
