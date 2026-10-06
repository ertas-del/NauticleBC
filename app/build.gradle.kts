plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "ca.nauticlebc.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "ca.nauticlebc.app"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.kotlinx.serialization.json)
    debugImplementation(libs.androidx.compose.ui.tooling)

    testImplementation(libs.junit)
}

// The app reads only the generated content bundle. Build it from /content before every build.
val buildContentBundle by tasks.registering(Exec::class) {
    group = "content"
    description = "Validate /content and write app/src/main/assets/content/bundle.json"
    workingDir = rootProject.projectDir
    commandLine("python3", "tools/build_bundle.py")
    inputs.dir(rootProject.file("content"))
    inputs.dir(rootProject.file("schemas"))
    inputs.dir(rootProject.file("tools"))
    outputs.file(layout.projectDirectory.file("src/main/assets/content/bundle.json"))
}

tasks.named("preBuild") {
    dependsOn(buildContentBundle)
}
