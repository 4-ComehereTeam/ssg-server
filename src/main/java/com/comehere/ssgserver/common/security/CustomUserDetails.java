package com.comehere.ssgserver.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.comehere.ssgserver.member.domain.Member;

public class CustomUserDetails implements UserDetails {

	private final Member member;

	public CustomUserDetails(Member member) {
		this.member = member;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collection = new ArrayList<>();

		collection.add(new GrantedAuthority() {

			@Override
			public String getAuthority() {
				return member.getRole().name();
			}
		});
		return collection;
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getSignInId();
	}

	public UUID getUserUuid() {
		return member.getUserUuid();
	}

	// 사용자 계정의 만료 여부
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 사용자 계정의 잠김 여부
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//사용자 자격증명의 만료 여부
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 사용자 계정의 활성화 여부
	@Override
	public boolean isEnabled() {
		return true;
	}
}
