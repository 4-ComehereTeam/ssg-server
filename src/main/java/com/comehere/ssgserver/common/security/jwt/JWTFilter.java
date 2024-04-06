package com.comehere.ssgserver.common.security.jwt;

import java.io.IOException;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.comehere.ssgserver.common.security.CustomUserDetails;
import com.comehere.ssgserver.common.security.social.SocialAuthenticationToken;
import com.comehere.ssgserver.member.domain.Member;
import com.comehere.ssgserver.member.domain.Role;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

	private final JWTUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {

		String authorization = request.getHeader("Authorization");

		//Authorization 헤더 검증
		if (authorization == null || !authorization.startsWith("Bearer")) {

			System.out.println("token null");
			filterChain.doFilter(request, response);

			return;
		}
		System.out.println("authorization now");

		String token = authorization.substring(7);

		if (jwtUtil.isExpired(token)) {
			System.out.println("token expired");
			filterChain.doFilter(request, response);
			return;
		}

		//token 에서 uuid와 password 추출
		UUID userUuid = jwtUtil.getUserUuid(token);
		String roleString = jwtUtil.getRole(token);
		Role role = Role.valueOf(roleString.toUpperCase());

		System.out.println("userUuid : " + userUuid + ",  role : " + roleString);

		Member member = Member
				.builder()
				.uuid(userUuid)
				.role(role)
				.build();

		// UserDetails에 회원 정보 객체 담기
		CustomUserDetails customUserDetails = new CustomUserDetails(member);

		// // 스프링 시큐리티 인증 토큰 생성
		// Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null,
		// 		customUserDetails.getAuthorities());

		Authentication authToken = null;

		if ("SOCIAL".equals(roleString)) {
			authToken = new SocialAuthenticationToken(customUserDetails, null,
					customUserDetails.getAuthorities());
		} else {
			// 일반 회원을 위한 인증 로직
			authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null,
					customUserDetails.getAuthorities());
		}

		// SecurityContext에 인증 토큰 저장
		SecurityContextHolder.getContext().setAuthentication(authToken);

		// 다음 필터로 이동
		filterChain.doFilter(request, response);
	}
}
