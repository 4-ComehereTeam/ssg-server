package com.comehere.ssgserver.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		//csrf 토큰 비활성화
		// http
		// 		.csrf((auth) -> auth.disable());
		//
		// // form 로그인 비활성화
		// http
		// 		.formLogin((auth) -> auth.disable());
		//
		// // httpBasic 로그인 비활성화
		// http
		// 		.httpBasic((auth) -> auth.disable());

		http
				.csrf((csrf) -> csrf.disable())
				.authorizeHttpRequests((auth) -> auth
						.requestMatchers("/", "/join", "/api/v1/**", "/swagger-ui/**").permitAll()
						.anyRequest().authenticated()
				)
				.sessionManagement(
						sessionManagement -> sessionManagement
								.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				);

		return http.build();
	}
}
