package com.comehere.ssgserver.member.application;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.comehere.ssgserver.member.domain.Agree;
import com.comehere.ssgserver.member.domain.Member;
import com.comehere.ssgserver.member.infrastructure.AgreeRepository;
import com.comehere.ssgserver.member.infrastructure.MemberRepository;
import com.comehere.ssgserver.member.vo.JoinRequestVo;
import com.comehere.ssgserver.purchase.domain.Address;
import com.comehere.ssgserver.purchase.infrastructure.AddressRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
	private final MemberRepository memberRepository;
	private final AddressRepository addressRepository;
	private final AgreeRepository agreeRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public AuthServiceImpl(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
			AgreeRepository agreeRepository, AddressRepository addressRepository) {
		this.memberRepository = memberRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.addressRepository = addressRepository;
		this.agreeRepository = agreeRepository;
	}

	@Override
	public void signUp(JoinRequestVo joinRequestVo) {

		Member member = this.createMember(joinRequestVo);
		log.info("member: {}", member);
	}

	private Member createMember(JoinRequestVo joinRequestVo) {

		UUID uuid = UUID.randomUUID();

		Member member = Member
				.builder()
				.signinId(joinRequestVo.getSigninId())
				.password(bCryptPasswordEncoder.encode(joinRequestVo.getPassword()))
				.name(joinRequestVo.getName())
				.phone(joinRequestVo.getPhone())
				.email(joinRequestVo.getEmail())
				.birthday(joinRequestVo.getBirthday())
				.gender(joinRequestVo.getGender())
				.uuid(uuid)
				.build();

		Member savedMember = memberRepository.save(member);

		Address address = Address
				.builder()
				.member(member)
				.name(joinRequestVo.getName())
				.phone(joinRequestVo.getPhone())
				.zipcode(joinRequestVo.getAddressInfoVo().getZipcode())
				.address(joinRequestVo.getAddressInfoVo().getAddress())
				.detailAddress(joinRequestVo.getAddressInfoVo().getDetailAddress())
				.defaultAddress((short)1)
				.build();

		addressRepository.save(address); // 주소 저장

		Agree agree = Agree
				.builder()
				.email(joinRequestVo.getEmail())
				.marketingAgree(joinRequestVo.getAgreeVo().getMarketingAgree())
				.emailAgree(joinRequestVo.getAgreeVo().getEmailAgree())
				.smsAgree(joinRequestVo.getAgreeVo().getSmsAgree())
				.callAgree(joinRequestVo.getAgreeVo().getCallAgree())
				.build();

		agreeRepository.save(agree); // 동의 저장

		log.info("member: {}", member);
		log.info("memberUUid : {}", uuid);

		return savedMember;
	}
}
