FROM eclipse-temurin:17-jre-jammy
WORKDIR /usr/app

# Uncomment the following line if use multi-stage build
# COPY --from=builder /usr/app/target/*.jar app.jar

COPY target/*.jar app.jar 

EXPOSE 8083
# Define the entry point for the application
ENTRYPOINT ["java", "-jar", "app.jar"]
