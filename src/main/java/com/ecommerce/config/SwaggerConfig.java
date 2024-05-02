package com.ecommerce.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;

@OpenAPIDefinition(
	info = @Info(title = "E-COMMERCE API",
		description = "이커머스 관련 api 명세서입니다.",
		version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi sampleOpenApi() {
		String[] paths = {"/**"};

		return GroupedOpenApi.builder()
			.group("Sample v1")
			.pathsToMatch(paths)
			.build();
	}
}