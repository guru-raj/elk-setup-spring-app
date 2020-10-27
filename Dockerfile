FROM java:11
COPY target/spring-app-0.0.1-SNAPSHOT.jar /app/spring-app.jar
EXPOSE 9001
CMD ["java","-jar","app/spring-app.jar"]
