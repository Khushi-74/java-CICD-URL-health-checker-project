FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/url-health-checker-0.0.1-SNAPSHOT.jar /app/url-health-checker.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "url-health-checker.jar"]   
