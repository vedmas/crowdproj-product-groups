plugins {
    kotlin("jvm")
}

dependencies {
    val coroutinesVersion: String by project

    implementation(kotlin("stdlib-common"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    implementation(kotlin("test-common"))
    implementation(kotlin("test-annotations-common"))
    implementation(kotlin("test-junit"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
}