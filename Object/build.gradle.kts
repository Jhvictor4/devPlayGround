plugins {
    kotlin("jvm") version "1.6.21"
    application
}


group = "com.github.jhvictor4"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

dependencies {
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("org.mockito:mockito-junit-jupiter:4.6.1")
    testImplementation("org.mockito:mockito-core:4.6.1")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
