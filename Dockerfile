# Stage 1: Build React + Vite frontend
FROM node:18 AS frontend-builder

WORKDIR /app/frontend

# Copy only necessary files
COPY frontend/package*.json ./
COPY frontend/ ./

RUN npm install
RUN npm run build

# Stage 2: Build Java Spring backend
FROM maven:3.8.8-eclipse-temurin-17 AS backend-builder

WORKDIR /app/backend

COPY pom.xml ./
COPY src ./src/

RUN mvn clean package -DskipTests

# Stage 3: Final image
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=backend-builder /app/backend/target/*.jar app.jar
COPY --from=frontend-builder /app/frontend/dist ./frontend

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
