plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.lyscraft.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 23

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
    buildFeatures {
        buildConfig = true
        flavorDimensions += "version"
    }
    productFlavors {
        create("prod") {
            dimension = "version"
            resValue("string", "app_name", "LysCraft")
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org\"")
            buildConfigField("String", "API_KEY", "\"cacb54ec0b15dd973ab5ac4ae29203e1\"")
        }
        create("dev") {
            dimension = "version"
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org\"")
            buildConfigField("String", "API_KEY", "\"cacb54ec0b15dd973ab5ac4ae29203e1\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":base"))
    implementation(project(":domain"))
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    //hilt
    implementation(libs.hiltAndroid)
    ksp(libs.hiltCompiler)

    //retrofit
    implementation(libs.retrofit)

    // okhttp
    implementation(libs.okhttp)
    implementation(libs.okhttpInterceptor)

    //gson
    implementation(libs.gson)
    implementation(libs.gsonConverter)
    implementation(libs.coroutineAdapter)

    debugImplementation(libs.chucker)
}