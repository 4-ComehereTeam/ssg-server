package com.comehere.ssgserver.common.security;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.comehere.ssgserver.member.infrastructure.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;

	//이 부분을 어디로..?
	// UUid 가져오기
	@Override
	public CustomUserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {

		UUID userUuid = UUID.fromString(uuid);

		return new CustomUserDetails(memberRepository.findByUuid(userUuid)
				.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다.")));
	}
}
