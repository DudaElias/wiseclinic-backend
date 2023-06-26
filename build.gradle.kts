plugins {
	java
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.unicamp"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_19

val ENABLE_PREVIEW = "--enable-preview"

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.core:jackson-core:2.15.2")
	implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.2")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8")
	implementation("com.fasterxml.jackson.module:jackson-module-parameter-names")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<JavaCompile> {
	options.compilerArgs.add(ENABLE_PREVIEW)
}

tasks.withType<Test> {
	useJUnitPlatform()
	jvmArgs(ENABLE_PREVIEW)
}
