import org.gradle.internal.os.OperatingSystem

plugins {
    kotlin("multiplatform") version "1.3.30"
}


repositories {
    mavenCentral()
}

val os: OperatingSystem
    get() = OperatingSystem.current()

kotlin {
    sourceSets {
        @Suppress("UNUSED_VARIABLE")
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        @Suppress("UNUSED_VARIABLE")
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }

    jvm {
        compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        compilations["test"].defaultSourceSet {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
    }

    js {
        compilations["main"].apply {
            kotlinOptions {
                sourceMap = true
                moduleKind = "commonjs"
            }

            defaultSourceSet {
                dependencies {
                    implementation(kotlin("stdlib-js"))
                }
            }
        }

        compilations["test"].apply {
            kotlinOptions {
                sourceMap = true
                moduleKind = "commonjs"
            }

            defaultSourceSet {
                dependencies {
                    implementation(kotlin("test-js"))
                }
            }
        }
    }

    if (os.isLinux) {
        linuxX64 {
            compilations["main"].defaultSourceSet {
                kotlin.srcDir("src/nativeMain/kotlin")
            }
        }
    }

    if (os.isMacOsX) {
        macosX64 {
            compilations["main"].defaultSourceSet {
                kotlin.srcDir("src/nativeMain/kotlin")
            }
        }
    }
}

val collectJs = tasks.register<Sync>("jsDist") {
    dependsOn(tasks["jsTestClasses"])

    configurations["jsTestRuntimeClasspath"].forEach {
        from(zipTree(it.absolutePath)) {
            includeEmptyDirs = false
            include {
                val path = it.path
                path.endsWith(".js") && (path.startsWith("META-INF/resources/") ||
                        !path.startsWith("META-INF/"))
            }
        }
    }

    kotlin {
        js {
            from(this.compilations["main"].output.allOutputs)
            from(this.compilations["test"].output.allOutputs)
        }
    }

    into(File(buildDir, "dist"))
}

tasks["assemble"].dependsOn(collectJs)
