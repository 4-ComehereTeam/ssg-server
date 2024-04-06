package com.comehere.ssgserver.common.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.comehere.ssgserver.member.infrastructure.MemberRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SocialAuthenticationProvider implements AuthenticationProvider {

	private final MemberRepository memberRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String socialUserId = authentication.getPrincipal().toString();

		return new UsernamePasswordAuthenticationToken(member, null, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
