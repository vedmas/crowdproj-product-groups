plugins {
    kotlin("jvm")
}

val coroutinesVersion: String by project
val konformVersion: String by project
val datetimeVersion: String by project

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))

    testImplementation("io.konform:konform:$konformVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-datetime:$datetimeVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
}