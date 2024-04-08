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
import com.comehere.ssgserver.member.dto.req.JoinReqDTO;
import com.comehere.ssgserver.member.dto.req.SigninReqDTO;
import com.comehere.ssgserver.member.dto.resp.CheckResignCountRespDTO;
import com.comehere.ssgserver.member.dto.resp.SigninRespDTO;
import com.comehere.ssgserver.member.infrastructure.AgreeRepository;
import com.comehere.ssgserver.member.infrastructure.MemberRepository;
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

	//회원 가입 처리
	@Override
	@Transactional
	public void signUp(JoinReqDTO joinReqDTO) {

		Member newMember = new Member();
		if (memberRepository.existsByEmail(joinReqDTO.getEmail())) {
			Member member = memberRepository.findByEmail(joinReqDTO.getEmail());
			if (member.getStatus() == -1) {
				newMember = this.recreateMember(member, joinReqDTO);
			} else {
				throw new BaseException(DUPLICATED_MEMBERS);
			}
		} else {
			newMember = this.createMember(joinReqDTO);
		}

		createAddress(newMember, joinReqDTO);
		createAgree(newMember, joinReqDTO);
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

		return memberRepository.existsByEmail(email);
	}

	// 회원의 탈퇴횟수 조회
	public CheckResignCountRespDTO checkResignCount(CheckStateReqDTO checkStateReqDTO) {

		Member member = memberRepository.findBySigninId(checkStateReqDTO.getSigninId())
				.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));

		CheckResignCountRespDTO checkResignCountRespDTO = new CheckResignCountRespDTO();
		checkResignCountRespDTO.setResignCount(member.getResignCount());

		return checkResignCountRespDTO;
	}

	// 휴면계정 여부 확인
	public boolean checkDormancy(CheckStateReqDTO checkStateReqDTO) {
		Member member = memberRepository.findBySigninId(checkStateReqDTO.getSigninId())
				.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));

		if (member.getStatus() == 0) {
			return true;
		} else {
			return false;
		}
	}

	// 탈퇴회원 여부 확인
	public boolean checkResign(CheckStateReqDTO checkStateReqDTO) {
		Member member = memberRepository.findBySigninId(checkStateReqDTO.getSigninId())
				.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));

		if (member.getStatus() == -1) {
			return true;
		} else {
			return false;
		}
	}

	// 탈퇴 회원 재가입
	private Member recreateMember(Member member, JoinReqDTO joinReqDTO) {

		return memberRepository.save(Member
				.builder()
				.id(member.getId())
				.signinId(joinReqDTO.getSigninId())
				.password(bCryptPasswordEncoder.encode(joinReqDTO.getPassword()))
				.name(joinReqDTO.getName())
				.phone(joinReqDTO.getPhone())
				.email(member.getEmail())
				.gender(joinReqDTO.getGender())
				.status((short)1)
				.resignCount(member.getResignCount())
				.uuid(UUID.randomUUID())
				.role(Role.valueOf("USER"))
				.zipcode(joinReqDTO.getAddressInfoReqDTO().getZipcode())
				.address(joinReqDTO.getAddressInfoReqDTO().getAddress())
				.detailAddress(joinReqDTO.getAddressInfoReqDTO().getAddressDetail())
				.build());
	}

	// 신규 회원 가입
	private Member createMember(JoinReqDTO joinReqDTO) {

		return memberRepository.save(Member
				.builder()
				.signinId(joinReqDTO.getSigninId())
				.password(bCryptPasswordEncoder.encode(joinReqDTO.getPassword()))
				.name(joinReqDTO.getName())
				.phone(joinReqDTO.getPhone())
				.email(joinReqDTO.getEmail())
				.gender(joinReqDTO.getGender())
				.status((short)1)
				.resignCount(0)
				.uuid(UUID.randomUUID())
				.role(Role.valueOf("USER"))
				.zipcode(joinReqDTO.getAddressInfoReqDTO().getZipcode())
				.address(joinReqDTO.getAddressInfoReqDTO().getAddress())
				.detailAddress(joinReqDTO.getAddressInfoReqDTO().getAddressDetail())
				.build());
	}

	// 회원 주소 등록
	private void createAddress(Member member, JoinReqDTO joinReqDTO) {
		addressRepository.save(Address
				.builder()
				.name(joinReqDTO.getName())
				.phone(joinReqDTO.getPhone())
				.zipcode(joinReqDTO.getAddressInfoReqDTO().getZipcode())
				.address(joinReqDTO.getAddressInfoReqDTO().getAddress())
				.detailAddress(joinReqDTO.getAddressInfoReqDTO().getAddressDetail())
				.defaultAddress((boolean)true)
				.uuid(member.getUuid())
				.build());
	}

	// 회원 동의사항 등록
	private void createAgree(Member member, JoinReqDTO joinReqDTO) {

		agreeRepository.save(Agree
				.builder()
				.email(joinReqDTO.getEmail())
				.ssgPointMktAgr1(joinReqDTO.getSsgPointAgreesDTO().getSsgPointMktAgr1())
				.ssgPointMktAgr2(joinReqDTO.getSsgPointAgreesDTO().getSsgPointMktAgr2())
				.ssgPointEmail(joinReqDTO.getSsgPointAgreesDTO().getSsgPointEmail())
				.ssgPointSms(joinReqDTO.getSsgPointAgreesDTO().getSsgPointSms())
				.ssgPointMail(joinReqDTO.getSsgPointAgreesDTO().getSsgPointMail())
				.ssgPointCall(joinReqDTO.getSsgPointAgreesDTO().getSsgPointCall())
				.ssgcomMktAgr1(joinReqDTO.getSsgcomAgreesDTO().getSsgcomMktAgr1())
				.ssgcomEmail(joinReqDTO.getSsgcomAgreesDTO().getSsgcomEmail())
				.ssgcomSms(joinReqDTO.getSsgcomAgreesDTO().getSsgcomSms())
				.build());
	}
}
