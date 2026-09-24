// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "org.dpdns.mfsky.miuix"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "org.dpdns.mfsky.miuix"
        minSdk = 33
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)

    implementation(libs.miuix.ui)
    implementation(libs.miuix.preference)
    implementation(libs.miuix.icons)

    // Only the inlined miuix-glass sources reach for these two, so they look unused from the app.
    // They are not: dropping them breaks `top/yukonga/miuix/kmp/glass`.
    implementation(libs.miuix.blur)
    implementation(libs.miuix.shader)
    implementation(libs.androidx.navigationevent.compose)

    debugImplementation(libs.androidx.compose.ui.tooling)
}
