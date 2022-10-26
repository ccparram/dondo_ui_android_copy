import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("maven-publish")
}

android {
    namespace = "com.dondo.ui"
    compileSdk = 33

    defaultConfig {
        minSdk = 23
        targetSdk = 33
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Android support
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.core:core-ktx:1.9.0")

    // UI
    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("com.google.android.exoplayer:exoplayer:2.18.1")
    implementation("io.coil-kt:coil:2.2.2")

    //Compose
    implementation("androidx.activity:activity-compose:1.6.0")
    implementation("androidx.compose.material:material:1.3.0")
    implementation("androidx.compose.animation:animation:1.2.1")
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("androidx.compose.ui:ui-tooling:1.2.1")

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.1")
    implementation("com.google.accompanist:accompanist-appcompat-theme:0.27.0")

    // Development
    api("com.orhanobut:logger:2.2.0")
    api("com.github.ccparram:timber:4.8")
}

val githubProperties = Properties()
githubProperties.load(FileInputStream(rootProject.file("github.properties")))

publishing {
    publications {
        create<MavenPublication>("gpr") {
            run {
                groupId = "com.crowdswap"
                artifactId = "dondo_ui_android"
                version = "1.1.11"
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/dondoapp/dondo_ui_android")
            credentials {
                username = githubProperties["gpr.usr"] as String
                password = githubProperties["gpr.key"] as String
            }
        }
    }
}
