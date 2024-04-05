package com.comehere.ssgserver.member.application;

import static com.comehere.ssgserver.common.response.BaseResponseStatus.*;

import org.springframework.stereotype.Service;

import com.comehere.ssgserver.common.exception.BaseException;
import com.comehere.ssgserver.member.domain.Member;
import com.comehere.ssgserver.member.domain.Role;
import com.comehere.ssgserver.member.dto.req.OAuthSignupReqDTO;
import com.comehere.ssgserver.member.infrastructure.MemberRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OAuthServiceImpl implements OAuthService {

	private final MemberRepository memberRepository;

	@Override
	public Boolean signup(OAuthSignupReqDTO oAuthSignupReqDto) {

		if (memberRepository.existsByEmail(oAuthSignupReqDto.getEmail())) {
			throw new BaseException(DUPLICATED_MEMBERS);
		}

		Member member = createMember(oAuthSignupReqDto);
		return true;
	}

	private Member createMember(OAuthSignupReqDTO oAuthSignupReqDto) {
		return memberRepository.save(Member
				.builder()
				.signinId(oAuthSignupReqDto.getId())
				.email(oAuthSignupReqDto.getEmail())
				.name(oAuthSignupReqDto.getName())
				.status((short)1)
				.role(Role.valueOf("USER"))
				.gender(oAuthSignupReqDto.getGender())
				.phone(oAuthSignupReqDto.getPhone())
				.resignCount(0)
				.build());
	}
}
