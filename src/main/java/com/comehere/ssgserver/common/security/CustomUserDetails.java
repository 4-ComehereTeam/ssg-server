package com.comehere.ssgserver.common.security;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.comehere.ssgserver.member.domain.Member;

public class CustomUserDetails implements UserDetails {

	// 커스텀할 경우 필요한 정보만 가져와서 사용
	// uuid pwd 만 가져오는 것이 더 효율
	private UUID uuid;
	private String password;

	public CustomUserDetails(Member member) {
		this.uuid = member.getUuid();
		this.password = member.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.uuid.toString();
	}

	public UUID getUuid() {
		return this.uuid;
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
