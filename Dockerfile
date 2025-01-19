FROM openjdk:17-jdk-slim

# Set the working directory inside container
WORKDIR /app

# Copy gradle wrapper and build files
COPY gradlew settings.gradle.kts build.gradle.kts /app/
COPY gradle /app/gradle

# Copy the src code
COPY src /app/src

# Grant execute permissions to Gradle wrapper
RUN chmod +x ./gradlew

# Build
RUN ./gradlew clean build -x test

# Define the entry point
CMD ["java", "-jar", "build/libs/LeagueRanksApp-1.0-SNAPSHOT.jar"]
