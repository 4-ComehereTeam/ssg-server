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

import com.comehere.ssgserver.common.security.jwt.JWTFilter;
import com.comehere.ssgserver.common.security.jwt.JWTUtil;
import com.comehere.ssgserver.common.security.jwt.LoginFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	//AuthenticationManager가 인자로 받을 AuthenticationConfiguration 객체 생성자 주입
	private final AuthenticationConfiguration authenticationConfiguration;

	private final JWTUtil jwtUtil;

	public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {
		this.authenticationConfiguration = authenticationConfiguration;
		this.jwtUtil = jwtUtil;
	}

	//AuthenticationManager 빈 등록
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public BCryptPasswordEncoder bCrytPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
				.csrf((csrf) -> csrf.disable())
				.formLogin((auth) -> auth.disable())
				.httpBasic((auth) -> auth.disable())
				.authorizeHttpRequests((auth) -> auth

						.requestMatchers("/", "/api/v1/members/join", "/api/v1/members/**",
								"/swagger-ui/**", "/swagger-resources/**", "/api-docs/**",
								"/api/v1/items/**", "/api/v1/categories/**", "/api/v1/bundle/**",
								"/api/v1/brand/**", "/api/v1/images/**","/api/v1/review/**")

						.permitAll()
						.anyRequest()
						.authenticated())
				.addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class)
				//로그인 필터 추가 LoginFilter()는 인자를 받음(AuthenticationManager() 메소드에
				//authenticationConfiguration 객체 주입) 따라서 등록 필요
				.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil),
						UsernamePasswordAuthenticationFilter.class)

				.sessionManagement(
						sessionManagement -> sessionManagement
								.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				);

		return http.build();
	}
}
