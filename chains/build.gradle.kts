plugins {
    alias(libs.plugins.jvm)

    `java-library`

    `maven-publish`
}

group = "com.nomemmurrakh"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    testImplementation(libs.junit.jupiter.engine)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.nomemmurrakh"
            artifactId = "chains"
            version = "1.0.0"

            from(components["java"])
        }
    }
}
