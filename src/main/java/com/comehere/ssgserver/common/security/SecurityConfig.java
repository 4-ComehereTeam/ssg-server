package com.comehere.ssgserver.common.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import com.comehere.ssgserver.common.security.jwt.JWTFilter;
import com.comehere.ssgserver.common.security.jwt.JWTUtil;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final AuthenticationConfiguration authenticationConfiguration;
	private final JWTUtil jwtUtil;
	private final AuthenticationProvider authenticationProvider;

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		return request -> {
			var cors = new org.springframework.web.cors.CorsConfiguration();
			cors.setAllowedOriginPatterns(List.of("*"));
			cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
			cors.setAllowedHeaders(List.of("*"));
			return cors;
		};
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
				.csrf((csrf) -> csrf.disable())
				.authorizeHttpRequests((auth) -> auth

						.requestMatchers("/", "/api/v1/auth/**",
								"/swagger-ui/**", "/swagger-resources/**", "/api-docs/**",
								"/api/v1/items/**", "/api/v1/categories/**", "/api/v1/bundle/**",
								"/api/v1/brand/**", "/api/v1/images/**", "/api/v1/review/**",
								"/api/v1/option/**",
								"/error")
						.permitAll()
						.anyRequest()
						.authenticated())
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
				.sessionManagement(
						sessionManagement -> sessionManagement
								.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				);

		return http.build();
	}
}
