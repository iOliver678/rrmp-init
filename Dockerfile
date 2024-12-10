# Stage 1: Build React + Vite frontend
FROM node:18 AS frontend-builder

# Set working directory for frontend
WORKDIR /app/frontend

# Copy frontend dependencies and source files
COPY frontend/package*.json ./
COPY frontend/ ./

# Install dependencies and build the React app
RUN npm install
RUN npm run build

# Stage 2: Build Java Spring backend
FROM maven:3.8.8-eclipse-temurin-17 AS backend-builder

# Set working directory for backend
WORKDIR /app/backend

# Copy backend dependencies and source files
COPY pom.xml ./
COPY src ./src/

# Build the Spring Boot JAR file
RUN mvn clean package -DskipTests

# Stage 3: Final image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the backend JAR from the builder
COPY --from=backend-builder /app/backend/target/*.jar app.jar

# Copy the React frontend build from the frontend builder
COPY --from=frontend-builder /app/frontend/dist ./static

# Expose the application port
EXPOSE 8080

# Set the default command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
