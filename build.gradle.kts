plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.apache.tomee:openejb-core:9.1.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
