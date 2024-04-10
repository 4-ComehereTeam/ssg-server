package com.comehere.ssgserver.member.application;

import static com.comehere.ssgserver.common.response.BaseResponseStatus.*;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.comehere.ssgserver.common.exception.BaseException;
import com.comehere.ssgserver.common.security.jwt.JWTUtil;
import com.comehere.ssgserver.common.security.social.SocialAuthenticationToken;
import com.comehere.ssgserver.member.domain.Agree;
import com.comehere.ssgserver.member.domain.Member;
import com.comehere.ssgserver.member.domain.Role;
import com.comehere.ssgserver.member.dto.req.OAuthSigninReqDTO;
import com.comehere.ssgserver.member.dto.req.OAuthSignupReqDTO;
import com.comehere.ssgserver.member.dto.resp.OAuthSigninRespDTO;
import com.comehere.ssgserver.member.infrastructure.AgreeRepository;
import com.comehere.ssgserver.member.infrastructure.MemberRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class OAuthServiceImpl implements OAuthService {

	private final MemberRepository memberRepository;
	private final AgreeRepository agreeRepository;
	private final AuthenticationManager authenticationManager;
	private final JWTUtil jwtUtil;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	// 회원가입
	@Override
	public void signup(OAuthSignupReqDTO oAuthSignupReqDto) {

		if (memberRepository.existsByEmail(oAuthSignupReqDto.getEmail())) {
			throw new BaseException(DUPLICATED_MEMBERS);
		}
		this.createMember(oAuthSignupReqDto);
		this.createAgree(oAuthSignupReqDto);
	}

	// 로그인
	@Override
	public OAuthSigninRespDTO signin(OAuthSigninReqDTO oAuthSigninReqDTO) {

		Member member = memberRepository.findBySigninId(oAuthSigninReqDTO.getSigninId())
				.filter(m -> m.getStatus() == 1)
				.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));

		authenticationManager.authenticate(new SocialAuthenticationToken(member.getUuid(), null));

		// 생성된 JWT 토큰을 포함하여 응답 객체 반환
		return OAuthSigninRespDTO.builder()
				.accessToken(jwtUtil.createJwt(member.getUuid(), member.getRole().toString()))
				.build();
	}

	// 멤버생성
	private Member createMember(OAuthSignupReqDTO oAuthSignupReqDto) {

		String password = bCryptPasswordEncoder.encode(RandomStringUtils.randomAlphanumeric(20));
		return memberRepository.save(Member
				.builder()
				.signinId(oAuthSignupReqDto.getId())
				.password(password)
				.email(oAuthSignupReqDto.getEmail())
				.name(oAuthSignupReqDto.getName())
				.status((short)1)
				.role(Role.valueOf("SOCIAL"))
				.gender(oAuthSignupReqDto.getGender())
				.phone(oAuthSignupReqDto.getPhone())
				.uuid(UUID.randomUUID())
				.resignCount(0)
				.build());
	}

	private void createAgree(OAuthSignupReqDTO oAuthSignupReqDto) {

		agreeRepository.save(Agree
				.builder()
				.email(oAuthSignupReqDto.getEmail())
				.ssgPointMktAgr1(false)
				.ssgPointMktAgr2(false)
				.ssgPointEmail(false)
				.ssgPointSms(false)
				.ssgPointMail(false)
				.ssgPointCall(false)
				.ssgcomMktAgr1(false)
				.ssgcomEmail(false)
				.ssgcomSms(false)
				.build());
	}
}
