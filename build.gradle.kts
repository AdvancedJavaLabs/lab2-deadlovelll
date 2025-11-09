plugins {
    kotlin("jvm") version "1.9.20"
    application
}

group = "org.itmo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("jakarta.jms:jakarta.jms-api:3.1.0")
    implementation("org.apache.activemq:activemq-broker:6.1.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(20)
}

application {
    mainClass.set("MainKt")
}