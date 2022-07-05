FROM gradle:jdk17-alpine AS GRADLE_BUILD
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle -q buildJar

FROM eclipse-temurin:17.0.3_7-jdk-alpine
EXPOSE 8080
COPY --from=GRADLE_BUILD /home/gradle/src/build/libs/*.jar /app/edusnooker.jar
ENTRYPOINT ["java","-jar","/app/edusnooker.jar","--spring.profiles.active=dev"]