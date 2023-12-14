plugins {
    id("java")
}

group = "de.zonlykroks"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(fileTree(mapOf("dir" to "lib", "include" to listOf("*.jar"))))
    implementation("org.jetbrains:annotations:24.0.0")
    implementation("org.jetbrains:annotations:24.0.0")
}