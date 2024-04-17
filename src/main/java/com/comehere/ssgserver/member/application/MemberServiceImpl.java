package com.comehere.ssgserver.member.application;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.member.domain.Member;
import com.comehere.ssgserver.member.dto.req.ModifyEmailReqDTO;
import com.comehere.ssgserver.member.dto.req.ModifyPhoneReqDTO;
import com.comehere.ssgserver.member.dto.req.ModifyPwdReqDTO;
import com.comehere.ssgserver.member.infrastructure.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	// 비밀번호 변경
	@Override
	@Transactional
	public Boolean modifyPassword(UUID userUuid, ModifyPwdReqDTO modifyPwdReqDTO) {

		Member member = getMemberByUuid(userUuid);
		return memberRepository.memberUpdatePassword(member.getUuid(),
				bCryptPasswordEncoder.encode(modifyPwdReqDTO.getNewPassword())) > 0;
	}

	// 이메일 변경
	@Override
	@Transactional
	public Boolean modifyEmail(UUID userUuid, ModifyEmailReqDTO modifyEmailReqDTO) {

		Member member = getMemberByUuid(userUuid);
		return memberRepository.updateEmail(member.getUuid(), modifyEmailReqDTO.getNewEmail()) > 0;
	}

	// 전화번호 변경
	@Override
	@Transactional
	public Boolean modifyPhone(UUID userUuid, ModifyPhoneReqDTO modifyPhoneReqDTO) {

		Member member = getMemberByUuid(userUuid);
		return memberRepository.updatePhone(member.getUuid(), modifyPhoneReqDTO.getNewPhone()) > 0;
	}

	// 회원 탈퇴
	@Override
	@Transactional
	public Boolean resignMember(UUID userUuid) {

		Member member = getMemberByUuid(userUuid);
		memberRepository.ResignMember(member.getSigninId());
		memberRepository.deleteAddress(member.getUuid());
		memberRepository.deleteAgree(member.getEmail());
		return true;
	}

	// 회원 정보 조회
	private Member getMemberByUuid(UUID userUuid) {
		return memberRepository.findByUuid(userUuid)
				.orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
	}
}

