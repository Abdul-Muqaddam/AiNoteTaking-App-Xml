plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")

    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.21"
}

android {
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    namespace = "com.example.firstxmlprojectainotepad"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.firstxmlprojectainotepad"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "OPENAI_API_KEY",
            "\"${project.properties["OPENAI_API_KEY"]}\""
        )

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.ktorCore)
    implementation(libs.ktorOkhttp)
    implementation(libs.ktorSerialization)
    implementation(libs.ktorNegotiation)
    implementation(libs.ktorAndroid)
    implementation("com.intuit.sdp:sdp-android:1.1.1")
    implementation("com.intuit.ssp:ssp-android:1.1.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.2")
    implementation("com.kizitonwose.calendar:view:2.7.0")
//    implementation("io.ktor:ktor-client-android:3.2.2")
//    implementation("io.ktor:ktor-client-core:3.2.2")
//    implementation("io.ktor:ktor-client-serialization-jvm:3.2.2")
    implementation(libs.ktor.ktor.client.logging)
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
//    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
//    implementation("io.insert-koin:koin-android:4.1.0")
//    implementation("io.ktor:ktor-client-content-negotiation:3.2.2")
//    implementation("io.ktor:ktor-client-auth:3.2.2")
    implementation("io.insert-koin:koin-android:4.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.0")
    implementation("androidx.room:room-runtime:2.7.2")
    kapt("androidx.room:room-compiler:2.7.2")
    implementation("androidx.room:room-ktx:2.7.2")
    implementation("io.insert-koin:koin-android:4.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
    implementation("com.airbnb.android:lottie:6.6.7")
    implementation("com.google.android.gms:play-services-ads:24.4.0")
//    implementation("io.ktor:ktor-serialization-kotlinx-json:3.2.2")
}