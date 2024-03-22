package com.comehere.ssgserver.common.security.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	public LoginFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
			AuthenticationException {

		//클라이언트 요청에서 loginId와 password를 추출
		String LoginId = obtainUsername(request);
		String password = obtainPassword(request);

		//스프링 시큐리티에서 username과 password를 검증하기 위해서는 toke에 담아야 함.
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(LoginId, password);

		//token에 담은 검증을 위한 AuthenticationManager로 전달
		return authenticationManager.authenticate(authToken);
	}

	// 로그인 성공 시 실행 되는 메소드 (JWT 토큰 발급)
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			jakarta.servlet.FilterChain chain, Authentication authResult) {
	}

	// 로그인 실패 시 실행 되는 메소드
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) {
	}
}
