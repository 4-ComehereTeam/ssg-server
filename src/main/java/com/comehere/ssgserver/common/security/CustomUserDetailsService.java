package com.comehere.ssgserver.common.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.comehere.ssgserver.member.domain.Member;
import com.comehere.ssgserver.member.infrastructure.MemberRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;

	public CustomUserDetailsService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String signInId) throws UsernameNotFoundException {

		Member member = memberRepository.findBySignInId(signInId)
				.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

		return new CustomUserDetails(member);
	}
}
