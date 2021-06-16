package com.tudor.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.tudor.controller", "com.tudor.service"})
@EntityScan("com.tudor.entity")
@EnableJpaRepositories("com.tudor.repository")
public class UniversityMonolithicApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityMonolithicApplication.class, args);
	}

}
