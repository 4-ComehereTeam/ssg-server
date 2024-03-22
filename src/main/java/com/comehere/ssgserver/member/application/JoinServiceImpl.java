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

		Boolean isExist = memberRepository.existsBySigninId(joinDTO.getSigninId());
		if (isExist) {
			return;
		}

		Member member = Member
				.builder()
				.signinId(joinDTO.getSigninId())
				.password(bCryptPasswordEncoder.encode(joinDTO.getPassword()))
				.build();
		
		memberRepository.save(member);
	}
}
