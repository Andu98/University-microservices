package com.tudor.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
@ComponentScan({"com.tudor.controller", "com.tudor.service"})
@EntityScan("com.tudor.entity")
@EnableJpaRepositories("com.tudor.repository")
@EnableFeignClients("com.tudor.feignclients")
//deoarece student-service este un microserviciu, trebuie sa aiba adnotarea 
@EnableEurekaClient
public class StudentServiceApplication {

	@Value("${address.service.url}")
	private String addressServiceUrl;
	
	
	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}

	//webclient va fi autowired in StudentService.java
	//unde va fi folosit pentru a face apelurile rest la addressService
	@Bean
	public WebClient webClient() {
		WebClient webClient = WebClient.builder()
				.baseUrl(addressServiceUrl).build();
		
		return webClient;
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
