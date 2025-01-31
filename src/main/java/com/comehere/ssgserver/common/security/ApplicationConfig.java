package com.comehere.ssgserver.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.comehere.ssgserver.common.security.social.SocialAuthenticationProvider;
import com.comehere.ssgserver.member.infrastructure.MemberRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

	private final MemberRepository memberRepository;
	private final CustomUserDetailsService customUserDetailsService;

	// 사용자 정보를 가져오는 역할을 하는 클래스
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(new CustomUserDetailsService(memberRepository));
		authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return authenticationProvider;
	}

	@Bean
	public SocialAuthenticationProvider socialAuthenticationProvider() {
		return new SocialAuthenticationProvider(customUserDetailsService);
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
				AuthenticationManagerBuilder.class);

		// 기본 인증 제공자 등록
		authenticationManagerBuilder.authenticationProvider(authenticationProvider());

		// 커스텀 인증 제공자 등록
		authenticationManagerBuilder.authenticationProvider(socialAuthenticationProvider());

		return authenticationManagerBuilder.build();
	}

	// 비밀번호 암호화
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {

		return new BCryptPasswordEncoder();
	}
}
