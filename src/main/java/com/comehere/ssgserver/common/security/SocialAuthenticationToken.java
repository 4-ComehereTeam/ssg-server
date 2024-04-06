package com.comehere.ssgserver.common.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class SocialAuthenticationToken extends AbstractAuthenticationToken {

	private final Object principal;
	private Object credentials;

	// 주로 인증되지 않은 상태의 객체를 생성할 때 사용됩니다.
	public SocialAuthenticationToken(Object principal, Object credentials) {
		super(null); // 여기서 null은 권한 목록을 의미하며, 인증되지 않은 상태이므로 권한이 없습니다.
		this.principal = principal;
		this.credentials = credentials; // credentials를 인자로 받고 있으나 사용하지 않으므로, 실제 사용 여부를 결정해야 합니다.
		setAuthenticated(false);
	}

	// 인증된 사용자의 객체를 생성할 때 사용됩니다.
	public SocialAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities); // 인증된 사용자의 권한 목록을 전달합니다.
		this.principal = principal;
		this.credentials = credentials;
		super.setAuthenticated(true); // 생성자 내에서 직접 호출하는 것은 권장되지 않으나, 권한 목록이 제공되면 인증된 것으로 간주할 수 있습니다.
	}

	@Override
	public Object getCredentials() {
		return this.credentials;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}
}
