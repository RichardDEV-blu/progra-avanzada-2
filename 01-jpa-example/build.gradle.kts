plugins {
    id("java")
    id("application")
    id("io.freefair.lombok") version "9.1.0"
}

group = "com.programacion.avanzada"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.hibernate:hibernate-core:7.1.10.Final")
    implementation("org.postgresql:postgresql:42.7.8")

    implementation("org.jboss.weld.se:weld-se-core:6.0.3.Final")
    implementation("io.smallrye:jandex:3.5.1")
}

application {
    // Clase Main con el método public static void main(String[] args)
    mainClass.set("com.programacion.avanzada.MainJpa01")
}

tasks.test {
    useJUnitPlatform()
}

sourceSets {
    main {
        output.setResourcesDir(output.classesDirs.files.first())
    }
}