package com.comehere.ssgserver.member.application;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.comehere.ssgserver.common.response.BaseResponse;
import com.comehere.ssgserver.member.domain.Member;
import com.comehere.ssgserver.member.dto.FindSigninIdDTO;
import com.comehere.ssgserver.member.dto.ModifyPwdDTO;
import com.comehere.ssgserver.member.infrastructure.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public BaseResponse<?> modifyPassword(UUID userUuid, ModifyPwdDTO modifyPwdDTO) {

		Member member = memberRepository.findByUuid(userUuid)
				.orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

		memberRepository.save(Member.builder()
				.id(member.getId())
				.phone(member.getPhone())
				.email(member.getEmail())
				.password(passwordEncoder.encode(modifyPwdDTO.getNewPassword()))
				.build());

		return new BaseResponse<>(true);
	}

	@Override
	public FindSigninIdDTO findSigninId(UUID userUuid) {

		Member member = memberRepository.findByUuid(userUuid)
				.orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

		return FindSigninIdDTO.builder()
				.signinId(member.getSigninId())
				.build();
	}
}

