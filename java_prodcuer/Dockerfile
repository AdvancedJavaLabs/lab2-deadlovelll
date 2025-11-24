FROM gradle:8.4-jdk21 AS builder

WORKDIR /home/gradle/src
COPY --chown=gradle:gradle . .

RUN gradle shadowJar --no-daemon

FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app
COPY --from=builder /home/gradle/src/build/libs/app.jar .
COPY --from=builder /home/gradle/src/src/main/java/data ./data