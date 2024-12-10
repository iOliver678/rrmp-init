# Stage 1: Build React frontend
FROM node:18 AS frontend-builder

WORKDIR /app/frontend

# Copy package.json and lock file first (to leverage Docker caching)
COPY frontend/package*.json ./

# Install dependencies
RUN npm ci

# Copy the rest of the frontend code
COPY frontend/ ./

# Build the React app
RUN npm run build

# Stage 2: Build Java Spring backend
FROM maven:3.8.8-eclipse-temurin-17 AS backend-builder

WORKDIR /app/backend

# Copy Maven project files
COPY pom.xml ./
COPY src ./src/

# Package the Spring Boot app
RUN mvn clean package -DskipTests

# Stage 3: Final production image
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy backend JAR
COPY --from=backend-builder /app/backend/target/*.jar app.jar

# Copy React build files to static folder
COPY --from=frontend-builder /app/frontend/dist ./static

EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
