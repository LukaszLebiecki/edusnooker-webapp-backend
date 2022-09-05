FROM gradle:jdk17-alpine AS GRADLE_BUILD
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM eclipse-temurin:17.0.3_7-jdk-alpine
EXPOSE 8080
RUN mkdir /app
COPY --from=GRADLE_BUILD /home/gradle/src/build/libs/edusnooker-webapp-backend-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java","-jar","/app/edusnooker-webapp-backend-0.0.1-SNAPSHOT.jar"]