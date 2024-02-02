plugins {
    alias(libs.plugins.jvm) // <1>

    `java-library` // <2>
}

group = "com.nomemmurrakh"
version = "1.0"

repositories {
    mavenCentral() // <3>
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5") // <4>

    testImplementation(libs.junit.jupiter.engine) // <5>

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    api(libs.commons.math3) // <6>

    implementation(libs.guava) // <7>
}

tasks.named<Test>("test") {
    useJUnitPlatform() // <8>
}
