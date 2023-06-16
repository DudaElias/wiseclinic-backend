plugins {
	java
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.unicamp"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	implementation("com.fasterxml.jackson.core:jackson-core:2.6.3")
	implementation("com.fasterxml.jackson.core:jackson-annotations:2.6.3")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.6.3")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
