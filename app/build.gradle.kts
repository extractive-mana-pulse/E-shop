plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("kotlin-kapt")
    id ("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.e_shop"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.e_shop"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
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


    //  Dagger hilt
    kapt ("androidx.hilt:hilt-compiler:1.1.0")
    kapt ("com.google.dagger:hilt-android-compiler:2.50")
    implementation ("com.google.dagger:hilt-android:2.50")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.2")

    // Coroutines
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // liveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // Navigation
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.7")

    // Scroll to Refresh Layout
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Glide
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")

    // rounding image corners
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    // preference
    implementation ("androidx.preference:preference-ktx:1.2.1")

    // Splash Screen
    implementation (libs.androidx.core.splashscreen)

    // Image Slider
    implementation ("com.github.denzcoskun:ImageSlideshow:0.1.2")

    //  Room
    kapt (libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)
    implementation (libs.androidx.room.runtime)

}