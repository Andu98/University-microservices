package com.tudor.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
@ComponentScan({"com.tudor.controller", "com.tudor.service"})
@EntityScan("com.tudor.entity")
@EnableJpaRepositories("com.tudor.repository")
//deoarece address-service este un microserviciu, trebuie sa aiba adnotarea 
@EnableEurekaClient
public class AddressServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressServiceApplication.class, args);
	}
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(
				new Info()
				.title("Spring Cloud application API")
				.version("1")
				.description("demo Spring Cloud")
				.termsOfService("http://swagger.io/terms/")
				.license(
			new License()
			.name("Apache 2.0")
			.url("http://springdoc.org")));}
	
	

}
