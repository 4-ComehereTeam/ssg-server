package com.comehere.ssgserver.common.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * 각 담당 파트마다 페이지 분리 : Item(상품), Member(회원), Purchase(주문)
 * - 각 컨트롤러에 매핑된 URI(@RequestMapping)를 각자 해당하는 API 메서드의 `pathsToMatch()`에 넣어주시면 됩니다..
 */

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
				.pathsToMatch("/api/v1/items/**", "/api/v1/categories/**",
						"/api/v1/bundle/**", "/api/v1/brand/**", "/api/v1/option/**")
				.build();
	}

	@Bean
	public GroupedOpenApi MemberAPI() {
		return GroupedOpenApi.builder()
				.group("회원")
				.pathsToMatch("/api/v1/members/**", "/api/v1/auth/**")
				.build();
	}

	@Bean
	public GroupedOpenApi CartAPI() {
		return GroupedOpenApi.builder()
				.group("장바구니")
				.pathsToMatch("/api/v1/cart/**")
				.build();
	}

	@Bean
	public GroupedOpenApi AddressAPI() {
		return GroupedOpenApi.builder()
				.group("배송지")
				.pathsToMatch("/api/v1/address/**")
				.build();
	}

	@Bean
	public GroupedOpenApi PurchaseAPI() {
		return GroupedOpenApi.builder()
				.group("주문")
				.pathsToMatch("/api/v1/purchases/**", "/api/v1/clip/**", "/api/v1/review/**", "/api/v1/purchase/**",
						"/api/v1/non/purchase/**")
				.build();
	}
}
