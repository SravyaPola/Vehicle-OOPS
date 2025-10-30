# 1) base image
FROM openjdk:17-jdk-slim

# 2) working dir inside container
WORKDIR /app

# 3) copy the jar into container
COPY target/Vehicle-OOPS-0.0.1-SNAPSHOT.jar app.jar

# 4) app listens on 8080
EXPOSE 8080

# 5) run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
