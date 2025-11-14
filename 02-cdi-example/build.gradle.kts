plugins {
    id("java")
}

group = "com.programacion_avanzada"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jboss.weld.se:weld-se-core:6.0.3.Final")
    implementation("io.smallrye:jandex:3.5.1")

    implementation("org.postgresql:postgresql:42.7.8")
    // https://mvnrepository.com/artifact/com.zaxxer/HikariCP
    implementation("com.zaxxer:HikariCP:7.0.2")
    // https://mvnrepository.com/artifact/org.projectlombok/lombok
//    implementation("org.projectlombok:lombok:1.18.42")
    // LOMBOK: Necesitas ambas líneas obligatoriamente
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
}

tasks.test {
    useJUnitPlatform()
}

sourceSets {
    main {
        output.setResourcesDir(file("${buildDir}/classes/java/main"))
    }
}