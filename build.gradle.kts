plugins {
    id("org.jetbrains.intellij") version "0.7.3"
    kotlin("jvm") version "1.5.0"
    kotlin("plugin.serialization") version "1.5.0"
}

group = "org.thuddle"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.0")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    type = "IU"
    localPath = "/Applications/IntelliJ IDEA.app"
}

tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
    changeNotes("""
      Add change notes here.<br>
      <em>most HTML tags may be used</em>""")
}