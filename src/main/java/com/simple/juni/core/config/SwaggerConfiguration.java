package com.simple.juni.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
	public Docket apiUser() {
		String title = "User API";
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(swaggerInfo(title))
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.simple.juni.security.controller"))
			.paths(PathSelectors.ant("/user/**"))
			.build()
			.useDefaultResponseMessages(false); // 기본으로 세팅되는 200,401,403,404 메시지를 표시 하지 않음
	}

	private ApiInfo swaggerInfo(String title) {
		return new ApiInfoBuilder().title("Spring API Documentation")
			.description("Elastic Search 1.6 리모트를 통한 TEST 진행시 사용되는 API에 대한 연동 문서입니다")
			.title(title)
			.license("by.juni")
			.version("v1.0.0")
			.build();
	}

}
