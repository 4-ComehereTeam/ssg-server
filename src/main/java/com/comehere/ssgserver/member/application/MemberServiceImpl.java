package com.comehere.ssgserver.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.member.infrastructure.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	@Transactional(readOnly = true)
	@Override
	public boolean checkUserSignInIdDuplication(String signInId) {
		return memberRepository.existsBySignInId(signInId);
	}

	@Transactional(readOnly = true)
	@Override
	public boolean checkUserEmailDuplication(String email) {

		return memberRepository.existsByEmail(email);
	}
}
