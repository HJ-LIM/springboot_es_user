package com.simple.juni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
	scanBasePackages = {
		"com.simple.juni.restful",
		"com.simple.juni.elasticsearch",
		"com.simple.juni.controller",
		"com.simple.juni.security",
		"com.simple.juni.core.config"
		// "com.simple.juni.test.constroctor"
	})
public class SpringBootRunApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRunApplication.class, args);
	}

}
