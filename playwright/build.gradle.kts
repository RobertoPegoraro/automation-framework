plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":config"))
    implementation("io.smallrye.config:smallrye-config:3.13.4")
    implementation("com.microsoft.playwright:playwright:1.54.0")
    testImplementation("org.testng:testng:7.11.0")
    testImplementation("org.assertj:assertj-core:4.0.0-M1")
}

tasks.test {
    useTestNG()
}