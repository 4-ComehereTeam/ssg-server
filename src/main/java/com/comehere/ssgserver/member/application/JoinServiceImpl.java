package com.comehere.ssgserver.member.application;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.comehere.ssgserver.member.domain.Member;
import com.comehere.ssgserver.member.dto.JoinDTO;
import com.comehere.ssgserver.member.infrastructure.MemberRepository;

@Service
public class JoinServiceImpl implements JoinService {

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public JoinServiceImpl(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.memberRepository = memberRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public void joinProcess(JoinDTO joinDTO) {

		Boolean isExist = memberRepository.existsByLoginId(joinDTO.getLoginId());
		if (isExist) {
			return;
		}

		Member member = Member
				.builder()
				.loginId(joinDTO.getLoginId())
				.password(bCryptPasswordEncoder.encode(joinDTO.getPassword()))
				.build();

		member.setRole("ROLE_USER");

		// Member member = Member
		// 		.builder()
		// 		.name(joinDTO.getName())
		// 		.birthday(joinDTO.getBirthday())
		// 		.gender(joinDTO.getGender())
		// 		.phoneNumber(joinDTO.getPhoneNumber())
		// 		.email(joinDTO.getEmail())
		// 		.signupTime(LocalDateTime.now())
		// 		.loginId(joinDTO.getLoginId())
		// 		.password(bCryptPasswordEncoder.encode(joinDTO.getPassword()))
		// 		.build();

		memberRepository.save(member);
	}
}
