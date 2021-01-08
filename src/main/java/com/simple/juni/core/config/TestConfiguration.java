package com.simple.juni.core.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableCaching
@Profile("test")
@PropertySource("/application-test.yml")
@Slf4j
public class TestConfiguration {

	final Environment environment;

	public TestConfiguration(Environment environment) {
		this.environment = environment;
	}

	@Bean
	public void init(){
		log.info(">> spring.profiles : " + environment.getProperty("spring.profiles"));
		log.info(">> notification.message : " + environment.getProperty("notification.run"));
		log.info(">> elasticsearch.index : " + environment.getProperty("elasticsearch.index"));
	}
}
