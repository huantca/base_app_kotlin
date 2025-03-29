pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://maven.aliyun.com/repository/jcenter") }

        maven { url = uri("https://jitpack.io") }

        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }

        maven {
            url = uri("https://repository.liferay.com/nexus/content/repositories/public/")
        }
    }
}

rootProject.name = "Base"
include(":app")
 