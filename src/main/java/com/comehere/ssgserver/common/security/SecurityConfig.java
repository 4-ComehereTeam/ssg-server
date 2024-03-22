package com.comehere.ssgserver.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.comehere.ssgserver.common.security.jwt.LoginFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	//AuthenticationManager가 인자로 받을 AuthenticationConfiguration 객체 생성자 주입
	private final AuthenticationConfiguration authenticationConfiguration;

	public SecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
		this.authenticationConfiguration = authenticationConfiguration;
	}

	//AuthenticationManager 빈 등록
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
				.csrf((csrf) -> csrf.disable())
				.formLogin((auth) -> auth.disable())
				.httpBasic((auth) -> auth.disable())
				.authorizeHttpRequests((auth) -> auth
						.requestMatchers("/", "/join",
								"/api/v1/members/**", "/api-docs/**",
								"/swagger-ui/**", "/swagger-resources/**")
						.permitAll()
						.anyRequest()
						.authenticated()
				)
				//로그인 필터 추가
				.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration)),
						UsernamePasswordAuthenticationFilter.class)

				// 세션 생성 정책 설정
				.sessionManagement(
						sessionManagement -> sessionManagement
								.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				);

		return http.build();
	}
}
