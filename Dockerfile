# Stage 1: Build React + Vite frontend
FROM node:18 AS frontend-builder

WORKDIR /app/frontend
COPY frontend/package*.json ./
COPY frontend/ ./

# Install dependencies and build the frontend
RUN npm install
RUN npm run build

# Stage 2: Build Java Spring backend
FROM maven:3.8.8-eclipse-temurin-17 AS backend-builder

WORKDIR /app/backend
COPY pom.xml ./
COPY src ./src/

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# Stage 3: Final image
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the Spring Boot JAR
COPY --from=backend-builder /app/backend/target/*.jar app.jar

# Copy the React frontend build to the static folder
COPY --from=frontend-builder /app/frontend/dist ./static

# Expose the application's port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
