plugins {
    kotlin("jvm")
}

dependencies {
    val coroutinesVersion: String by project
    val kotlincor: String by project

    implementation(kotlin("stdlib-common"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    implementation(project(":common"))
    implementation(project(":stubs"))
    implementation("com.crowdproj:kotlin-cor:$kotlincor")

    implementation(kotlin("test-common"))
    implementation(kotlin("test-annotations-common"))
    implementation(kotlin("test-junit"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
}
