plugins {
    kotlin("jvm") version "2.3.0"
    id("application")
    id("com.gradleup.shadow") version "9.3.0"
}

version = project.findProperty("version") as String
group = "dev.cactuz"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(25)
}

application {
    mainClass.set("dev.cactuz.img2ascii.MainKt")
}

/*
dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
*/

tasks.build {
    dependsOn(tasks.shadowJar)
}
