plugins {
    kotlin("jvm") version "2.3.0"
    // Make Java CLI applications
    id("application")

    // Make a fat JAR (jar with all dependencies included)
    id("com.gradleup.shadow") version "9.3.0"
}

// package repository for dependencies
repositories {
    mavenCentral()
}

// main entrypoint
application {
    mainClass.set("img2ascii.MainKt")
}

tasks.build {
    dependsOn("shadowJar")
}
