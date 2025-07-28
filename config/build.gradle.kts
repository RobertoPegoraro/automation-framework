plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.eclipse.microprofile.config:microprofile-config-api:3.1")
    implementation("io.smallrye.config:smallrye-config:3.13.4")
}

tasks.test {
    useJUnitPlatform()
}