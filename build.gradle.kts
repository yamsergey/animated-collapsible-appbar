import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library") version "4.1.0-alpha05"
    kotlin("android") version "1.3.70"

    `maven-publish`
}

android {
    compileSdkVersion(29)

    defaultConfig {
        compileOptions {
            setSourceCompatibility(JavaVersion.VERSION_1_8)
            setTargetCompatibility(JavaVersion.VERSION_1_8)
        }

        minSdkVersion(19)
        targetSdkVersion(29)
    }

    sourceSets {
        get("main").apply {
            java.srcDirs("src/main/kotlin")
        }

        get("test").apply {
            java.srcDirs("src/test/kotlin")
        }
    }

    lintOptions {
        resourcePrefix("animated_collapsible_appbar")
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.2.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.72")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("com.google.android.material:material:1.1.0")

    testImplementation("com.google.truth:truth:1.0.1")
    testImplementation("junit:junit:4.13")
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}

tasks.configureEach<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.configureEach<Test> {
    testLogging.events("PASSED", "FAILED", "SKIPPED")
}

apply(
    from = "https://raw.githubusercontent.com/sky-uk/gradle-maven-plugin/master/gradle-mavenizer.gradle"
)
