import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
}

dependencies {
    implementation(projects.example)
    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "de.fabiexe.sweet.example.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "de.fabiexe.sweet"
            packageVersion = "1.0.0"
        }
    }
}