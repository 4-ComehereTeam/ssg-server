package com.comehere.ssgserver.member.application;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.common.security.jwt.JWTUtil;
import com.comehere.ssgserver.member.domain.Agree;
import com.comehere.ssgserver.member.domain.Member;
import com.comehere.ssgserver.member.domain.Role;
import com.comehere.ssgserver.member.dto.request.SigninRequestDTO;
import com.comehere.ssgserver.member.dto.response.SigninResponseDTO;
import com.comehere.ssgserver.member.infrastructure.AgreeRepository;
import com.comehere.ssgserver.member.infrastructure.MemberRepository;
import com.comehere.ssgserver.member.vo.request.JoinRequestVO;
import com.comehere.ssgserver.purchase.domain.Address;
import com.comehere.ssgserver.purchase.infrastructure.AddressRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

	private final MemberRepository memberRepository;

	private final AddressRepository addressRepository;

	private final AgreeRepository agreeRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final AuthenticationManager authenticationManager;

	private final JWTUtil jwtUtil;

	@Override
	@Transactional
	public void signUp(JoinRequestVO joinRequestVo) {

		validateDuplicateMember(joinRequestVo);
		Member member = this.createMember(joinRequestVo);
		Address address = this.createAddress(member, joinRequestVo);
		Agree agree = this.create(joinRequestVo);
		log.info("member: {}", member);
	}

	// 로그인 처리
	@Override
	public SigninResponseDTO signIn(SigninRequestDTO signinRequestDto) {

		// 사용자 정보 조회
		Member member = memberRepository.findBySigninId(signinRequestDto.getSigninId())
				.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));
		// 인증 과정 수행
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				member.getUuid(), signinRequestDto.getPassword()));

		// 인증 성공 응답 생성
		return SigninResponseDTO.builder()
				.uuid(member.getUuid())
				.name(member.getName())
				.email(member.getEmail())
				.signinId(member.getSigninId())
				.accessToken(jwtUtil.createJwt(member.getUuid(), member.getRole().toString()))
				.build();
	}

	private Member createMember(JoinRequestVO joinRequestVo) {

		return memberRepository.save(Member
				.builder()
				.signinId(joinRequestVo.getSigninId())
				.password(bCryptPasswordEncoder.encode(joinRequestVo.getPassword()))
				.name(joinRequestVo.getName())
				.phone(joinRequestVo.getPhone())
				.email(joinRequestVo.getEmail())
				.gender(joinRequestVo.getGender())
				.uuid(UUID.randomUUID())
				.role(Role.valueOf("USER"))
				.build());
	}

	private Address createAddress(Member member, JoinRequestVO joinRequestVo) {
		return addressRepository.save(Address
				.builder()
				.name(joinRequestVo.getName())
				.phone(joinRequestVo.getPhone())
				.zipcode(joinRequestVo.getAddressInfoVo().getZipcode())
				.address(joinRequestVo.getAddressInfoVo().getAddress())
				.detailAddress(joinRequestVo.getAddressInfoVo().getDetailAddress())
				.defaultAddress((boolean)true)
				.build());
	}

	private Agree create(JoinRequestVO joinRequestVo) {

		return agreeRepository.save(Agree
				.builder()
				.email(joinRequestVo.getEmail())
				.ssgPointMktAgr1(joinRequestVo.getSsgPointAgreesVo().getSsgPointMktAgr1())
				.ssgPointMktAgr2(joinRequestVo.getSsgPointAgreesVo().getSsgPointMktAgr2())
				.ssgPointEmail(joinRequestVo.getSsgPointAgreesVo().getSsgPointEmail())
				.ssgPointSms(joinRequestVo.getSsgPointAgreesVo().getSsgPointSms())
				.ssgPointMail(joinRequestVo.getSsgPointAgreesVo().getSsgPointMail())
				.ssgPointCall(joinRequestVo.getSsgPointAgreesVo().getSsgPointCall())
				.ssgcomMktAgr1(joinRequestVo.getSsgcomAgreesVo().getSsgcomMktAgr1())
				.ssgcomEmail(joinRequestVo.getSsgcomAgreesVo().getSsgcomEmail())
				.ssgcomSms(joinRequestVo.getSsgcomAgreesVo().getSsgcomSms())
				.build());
	}

	// 회원가입 전 중복 회원 검증
	private void validateDuplicateMember(JoinRequestVO joinRequestVo) {
		memberRepository.findBySigninId(joinRequestVo.getSigninId())
				.ifPresent(m -> {
					throw new IllegalStateException("이미 존재하는 회원입니다.");
				});
	}

	// 회원가입 전 중복 로그인 아이디 검증
	@Transactional(readOnly = true)
	@Override
	public boolean checkUserSignInIdDuplication(String signInId) {
		return memberRepository.existsBySigninId(signInId);
	}

	// 회원가입 전 중복 이메일 검증
	@Transactional(readOnly = true)
	@Override
	public boolean checkUserEmailDuplication(String email) {

		boolean test = memberRepository.existsByEmail(email);
		log.info(test + "");
		return memberRepository.existsByEmail(email);
	}
}
