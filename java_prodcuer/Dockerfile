FROM gradle:9.1-jdk21 AS builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN ./gradlew build --no-daemon -Dorg.gradle.jvmargs="-Xmx512m -Xms256m"

FROM eclipse-temurin:20-jre AS runtime
WORKDIR /app
COPY --from=builder /home/gradle/src/build/libs/*.jar ./spring-boot-application.jar
EXPOSE 8000
CMD ["java", "-jar", "spring-boot-application.jar"]