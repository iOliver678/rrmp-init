# Stage 1: Build React + Vite frontend
FROM node:18 AS frontend-builder

# Set working directory for frontend
WORKDIR /app/frontend

# Copy frontend code
COPY frontend/package*.json ./
COPY frontend/ ./

# Install dependencies and build the app
RUN npm install
RUN npm run dev

# Stage 2: Build Java Spring backend
FROM maven:3.8.8-eclipse-temurin-17 AS backend-builder

# Set working directory for backend
WORKDIR /app/backend

# Copy backend code and Maven config
COPY pom.xml ./
COPY src ./src/

# Build the backend JAR
RUN mvn clean package -DskipTests

# Stage 3: Final image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy backend JAR
COPY --from=backend-builder /app/backend/target/*.jar app.jar

# Copy built frontend files to serve with Spring (if applicable)
COPY --from=frontend-builder /app/frontend/dist ./frontend

# Expose the application's port
EXPOSE 8080

# Set the default command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
