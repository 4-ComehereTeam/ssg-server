package com.comehere.ssgserver.common.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
@OpenAPIDefinition(
		info = @Info(
				title = "드루와 API 명세서",
				description = "스파로스 ssg.com 클론 코딩 프로젝트",
				version = "v1.0"
		)
)
public class SwaggerConfig {
	@Bean
	public GroupedOpenApi ItemAPI() {
		return GroupedOpenApi.builder()
				.group("상품")
				.pathsToMatch("/items/**")
				.build();
	}

	@Bean
	public GroupedOpenApi MemberAPI() {
		return GroupedOpenApi.builder()
				.group("회원")
				.pathsToMatch("/members/**")
				.build();
	}

	@Bean
	public GroupedOpenApi PurchaseAPI() {
		return GroupedOpenApi.builder()
				.group("주문")
				.pathsToMatch("/purchases/**")
				.build();
	}
}
