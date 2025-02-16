# Use an official Maven image to build the springboot app
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and install the dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the Source code and build the Application
COPY src ./src
RUN mvn clean package -DskipTests


# Use an official OpenJdk image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file from the build stage into the container
COPY --from=build /app/target/Employee-Management-System-0.0.1-SNAPSHOT.jar .

# Expose the port that Spring Boot app will run on (default is 8080)
EXPOSE 8080

# Set the entry point to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "Employee-Management-System-0.0.1-SNAPSHOT.jar"]




