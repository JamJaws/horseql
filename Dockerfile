FROM eclipse-temurin:17-jdk as build

WORKDIR /workspace/build/

COPY gradle gradle
COPY gradlew ./
COPY settings.gradle.kts ./
COPY build.gradle.kts ./
COPY src src

RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:17-jre

RUN mkdir /app

COPY --from=build /workspace/build/build/libs/*.jar /app/horseql-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/horseql-0.0.1-SNAPSHOT.jar"]
