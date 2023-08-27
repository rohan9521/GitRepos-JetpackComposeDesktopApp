import com.apollographql.apollo3.gradle.internal.ApolloDownloadSchemaTask
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("com.apollographql.apollo3").version("3.8.2")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}
apollo {
    service("service") {
        packageName.set("src.main")
    }
}
//tasks.named<com.apollographql.apollo3.gradle.internal.ApolloDownloadSchemaTask>("downloadApolloSchema") {
//    schema.set(file("path/to/schema.json"))
//}
dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation("com.apollographql.apollo3:apollo-runtime:3.8.2")

    implementation("io.insert-koin:koin-core:2.0.1")

    // Koin for dependency injection
    implementation("io.insert-koin:koin-core:2.0.1")
//    implementation("io.insert-koin:koin-extensions:2.0.1")



}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "GitHubRepoDesktop"
            packageVersion = "1.0.0"
        }
    }
}
