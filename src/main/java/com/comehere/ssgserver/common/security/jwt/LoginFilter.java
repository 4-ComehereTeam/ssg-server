package com.comehere.ssgserver.common.security.jwt;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.comehere.ssgserver.common.security.CustomUserDetails;
import com.comehere.ssgserver.member.vo.SignInRequestVo;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	private final JWTUtil jwtUtil;

	public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			SignInRequestVo signInRequestVo = new ObjectMapper().readValue(request.getInputStream(),
					SignInRequestVo.class);
			// 클라이언트 요청에서 로그인 ID와 비밀번호 추출
			String signInId = signInRequestVo.getSignInId();
			String password = signInRequestVo.getPassword();

			// 스프링 시큐리티에서 signInId와 password를 검증하기 위해서 token에 담아야 함
			// 로그인 ID와 비밀번호를 사용하여 인증 토큰 생성
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(signInId, password,
					null);

			// 생성된 토큰을 AuthenticationManager에게 전달
			return authenticationManager.authenticate(authToken);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain, Authentication authentication) {
		// 인증 성공 시 처리할 내용
		CustomUserDetails customUserDetails = (CustomUserDetails)authentication.getPrincipal();

		UUID userUuid = customUserDetails.getUserUuid();
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
		GrantedAuthority auth = iterator.next();

		String role = auth.getAuthority();
		String token = jwtUtil.createJwt(userUuid, role, 60 * 60 * 10L);

		response.addHeader("accessToken", "Bearer " + token);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException {
		// 인증 실패 시 처리할 내용
		response.setStatus(401);
	}

}
