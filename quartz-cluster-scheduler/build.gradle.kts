plugins {
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
    id("java")
}

group = "com.example.quartz-cluster-scheduler"
version = "0.0.1-snapshot"

springBoot {
    mainClass.set("com.example.quartzclusterscheduler.Application")
}

repositories {
    mavenCentral()
}


java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-quartz")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.postgresql:postgresql:42.5.3")
    implementation("org.liquibase:liquibase-core:4.20.0")
}
