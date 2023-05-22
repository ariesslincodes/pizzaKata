plugins {
    id("java")
}

group = "org.pizzaKata"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:3.12.4")
    implementation("com.google.guava:guava:30.1-jre")
}

tasks.test {
    useJUnitPlatform()
}