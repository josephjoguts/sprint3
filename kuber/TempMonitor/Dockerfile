FROM openjdk:21-jdk-slim
ENV APP_HOME=/app
RUN mkdir -p $APP_HOME
COPY TemperatureMonitor-0.0.1-SNAPSHOT.jar $APP_HOME/app.jar
WORKDIR $APP_HOME
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
