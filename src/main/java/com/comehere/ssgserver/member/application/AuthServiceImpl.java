package com.comehere.ssgserver.member.application;

import static com.comehere.ssgserver.common.response.BaseResponseStatus.*;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.common.exception.BaseException;
import com.comehere.ssgserver.common.security.jwt.JWTUtil;
import com.comehere.ssgserver.member.domain.Agree;
import com.comehere.ssgserver.member.domain.Member;
import com.comehere.ssgserver.member.domain.Role;
import com.comehere.ssgserver.member.dto.req.CheckStateReqDTO;
import com.comehere.ssgserver.member.dto.req.SigninReqDTO;
import com.comehere.ssgserver.member.dto.resp.CheckResignCountRespDTO;
import com.comehere.ssgserver.member.dto.resp.SigninRespDTO;
import com.comehere.ssgserver.member.infrastructure.AgreeRepository;
import com.comehere.ssgserver.member.infrastructure.MemberRepository;
import com.comehere.ssgserver.member.vo.req.JoinReqVO;
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
	public void signUp(JoinReqVO joinReqVo) {

		validateDuplicateMember(joinReqVo);
		Member member = this.createMember(joinReqVo);
		Address address = this.createAddress(member, joinReqVo);
		Agree agree = this.create(joinReqVo);
		log.info("member: {}", member);
	}

	// 로그인 처리
	@Override
	public SigninRespDTO signIn(SigninReqDTO signinReqDto) {

		// 사용자 정보 조회
		Member member = memberRepository.findBySigninId(signinReqDto.getSigninId())
				.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));

		if (member.getStatus() == -1) {
			throw new BaseException(WITHDRAWAL_MEMBERS);
		}
		// 인증 과정 수행
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				member.getUuid(), signinReqDto.getPassword()));

		// 인증 성공 응답 생성
		return SigninRespDTO.builder()
				.uuid(member.getUuid())
				.name(member.getName())
				.email(member.getEmail())
				.signinId(member.getSigninId())
				.accessToken(jwtUtil.createJwt(member.getUuid(), member.getRole().toString()))
				.build();
	}

	private Member createMember(JoinReqVO joinReqVo) {

		return memberRepository.save(Member
				.builder()
				.signinId(joinReqVo.getSigninId())
				.password(bCryptPasswordEncoder.encode(joinReqVo.getPassword()))
				.name(joinReqVo.getName())
				.phone(joinReqVo.getPhone())
				.email(joinReqVo.getEmail())
				.gender(joinReqVo.getGender())
				.uuid(UUID.randomUUID())
				.role(Role.valueOf("USER"))
				.build());
	}

	private Address createAddress(Member member, JoinReqVO joinReqVo) {
		return addressRepository.save(Address
				.builder()
				.name(joinReqVo.getName())
				.phone(joinReqVo.getPhone())
				.zipcode(joinReqVo.getAddressInfoVo().getZipcode())
				.address(joinReqVo.getAddressInfoVo().getAddress())
				.detailAddress(joinReqVo.getAddressInfoVo().getDetailAddress())
				.defaultAddress((boolean)true)
				.uuid(member.getUuid())
				.build());
	}

	private Agree create(JoinReqVO joinReqVo) {

		return agreeRepository.save(Agree
				.builder()
				.email(joinReqVo.getEmail())
				.ssgPointMktAgr1(joinReqVo.getSsgPointAgreesVo().getSsgPointMktAgr1())
				.ssgPointMktAgr2(joinReqVo.getSsgPointAgreesVo().getSsgPointMktAgr2())
				.ssgPointEmail(joinReqVo.getSsgPointAgreesVo().getSsgPointEmail())
				.ssgPointSms(joinReqVo.getSsgPointAgreesVo().getSsgPointSms())
				.ssgPointMail(joinReqVo.getSsgPointAgreesVo().getSsgPointMail())
				.ssgPointCall(joinReqVo.getSsgPointAgreesVo().getSsgPointCall())
				.ssgcomMktAgr1(joinReqVo.getSsgcomAgreesVo().getSsgcomMktAgr1())
				.ssgcomEmail(joinReqVo.getSsgcomAgreesVo().getSsgcomEmail())
				.ssgcomSms(joinReqVo.getSsgcomAgreesVo().getSsgcomSms())
				.build());
	}

	// 회원가입 전 중복 회원 검증
	private void validateDuplicateMember(JoinReqVO joinReqVo) {
		memberRepository.findBySigninId(joinReqVo.getSigninId())
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

	public CheckResignCountRespDTO checkResignCount(CheckStateReqDTO checkStateReqDTO) {

		Member member = memberRepository.findBySigninId(checkStateReqDTO.getSigninId())
				.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));

		CheckResignCountRespDTO checkResignCountRespDTO = new CheckResignCountRespDTO();
		checkResignCountRespDTO.setResignCount(member.getResignCount());

		return checkResignCountRespDTO;
	}

	public boolean checkDormancy(CheckStateReqDTO checkStateReqDTO) {
		Member member = memberRepository.findBySigninId(checkStateReqDTO.getSigninId())
				.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));

		if (member.getStatus() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkResign(CheckStateReqDTO checkStateReqDTO) {
		Member member = memberRepository.findBySigninId(checkStateReqDTO.getSigninId())
				.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));

		if (member.getStatus() == -1) {
			return true;
		} else {
			return false;
		}
	}
}
