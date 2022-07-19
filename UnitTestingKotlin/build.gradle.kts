import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    application
    jacoco
}

jacoco {
    toolVersion = "0.8.5"
}

group = "com.github.jhvictor4"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
//    testImplementation("com.navercorp.fixturemonkey:fixture-monkey-starter:0.3.1")

    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("org.mockito:mockito-junit-jupiter:4.6.1")
    testImplementation("org.mockito:mockito-core:4.6.1")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
//    finalizedBy("jacocoTestReport")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

//tasks.jacocoTestReport {
//    reports {
//        html.required.set(true)
//    }
//    finalizedBy("jacocoTestCoverageVerification")
//}