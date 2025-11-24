plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.itmo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("jakarta.jms:jakarta.jms-api:3.1.0")
    implementation("org.apache.activemq:activemq-broker:6.1.1")
    implementation("com.rabbitmq:amqp-client:5.27.1")
    implementation("org.slf4j:slf4j-simple:2.0.16")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("Main")
}

tasks.shadowJar {
    archiveFileName.set("app.jar")
    manifest {
        attributes["Main-Class"] = "Main"
    }
}