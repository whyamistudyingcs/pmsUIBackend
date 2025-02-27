# Build stage
FROM maven:3.9-amazoncorretto-17 AS build

# Set working directory
WORKDIR /usr/app

# Copy the parent pom.xml first
COPY pom.xml .

# Copy all module directories
COPY ./src .

# Build the application
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-jdk-slim

WORKDIR /usr/app

# Copy the built artifact from build stage
COPY --from=build /usr/target/*.jar /usr/app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"] 
