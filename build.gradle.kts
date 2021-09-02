import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.32"
}

group = "com.clearme"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
// Set our project variables
ext {
    var dropwizard_version = "2.0.25"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.dropwizard:dropwizard-core:2.0.25")

    testImplementation(kotlin("test-junit"))
    testImplementation("io.dropwizard:dropwizard-testing:2.0.25")
    testImplementation("io.dropwizard:dropwizard-client:2.0.25")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.5")
    testImplementation("org.mockito:mockito-core:3.12.4")
    testImplementation("junit:junit:4.12")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}