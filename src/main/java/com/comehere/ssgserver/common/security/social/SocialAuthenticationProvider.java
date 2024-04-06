package com.comehere.ssgserver.common.security.social;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.comehere.ssgserver.common.security.CustomUserDetails;
import com.comehere.ssgserver.common.security.CustomUserDetailsService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class SocialAuthenticationProvider implements AuthenticationProvider {

	private final CustomUserDetailsService customUserDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		SocialAuthenticationToken authToken = (SocialAuthenticationToken)authentication;
		String userUuid = authToken.getPrincipal().toString();

		CustomUserDetails user = customUserDetailsService.loadUserByUsername(userUuid);
		return new SocialAuthenticationToken(user.getUuid(), null, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return SocialAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
