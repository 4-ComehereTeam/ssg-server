package com.comehere.ssgserver.member.application;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.member.domain.Member;
import com.comehere.ssgserver.member.dto.FindSigninIdDTO;
import com.comehere.ssgserver.member.dto.ModifyEmailDTO;
import com.comehere.ssgserver.member.dto.ModifyPhoneDTO;
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
	public Boolean modifyPassword(UUID userUuid, ModifyPwdDTO modifyPwdDTO) {

		Member member = getMemberByUuid(userUuid);

		memberRepository.save(Member.builder()
				.id(member.getId())
				.signinId(member.getSigninId())
				.phone(member.getPhone())
				.email(member.getEmail())
				.password(passwordEncoder.encode(modifyPwdDTO.getNewPassword()))
				.resignCount(member.getResignCount())
				.status(member.getStatus())
				.resignTime(member.getResignTime())
				.build());

		return true;
	}

	@Override
	public FindSigninIdDTO findSigninId(UUID userUuid) {

		Member member = getMemberByUuid(userUuid);

		return FindSigninIdDTO.builder()
				.signinId(member.getSigninId())
				.build();
	}

	@Override
	public Boolean modifyEmail(UUID userUuid, ModifyEmailDTO modifyEmailDTO) {

		Member member = getMemberByUuid(userUuid);

		memberRepository.save(Member.builder()
				.id(member.getId())
				.signinId(member.getSigninId())
				.phone(member.getPhone())
				.email(modifyEmailDTO.getNewEmail())
				.password(member.getPassword())
				.resignCount(member.getResignCount())
				.status(member.getStatus())
				.resignTime(member.getResignTime())
				.build());

		return true;
	}

	@Override
	public Boolean modifyPhone(UUID userUuid, ModifyPhoneDTO modifyPhoneDTO) {

		Member member = getMemberByUuid(userUuid);

		memberRepository.save(Member.builder()
				.id(member.getId())
				.signinId(member.getSigninId())
				.phone(modifyPhoneDTO.getNewPhone())
				.email(member.getEmail())
				.password(member.getPassword())
				.resignCount(member.getResignCount())
				.status(member.getStatus())
				.resignTime(member.getResignTime())
				.build());

		return true;
	}

	@Override
	@Transactional
	public Boolean resignMember(UUID userUuid) {

		Member member = getMemberByUuid(userUuid);
		memberRepository.resignMember(member.getSigninId());
		memberRepository.deleteAddress(member.getUuid());
		memberRepository.deldteAgree(member.getEmail());

		return true;
	}

	private Member getMemberByUuid(UUID userUuid) {
		return memberRepository.findByUuid(userUuid)
				.orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
	}
}

