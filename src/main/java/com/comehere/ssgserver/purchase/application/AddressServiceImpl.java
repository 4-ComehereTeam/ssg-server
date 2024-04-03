package com.comehere.ssgserver.purchase.application;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.comehere.ssgserver.member.domain.Member;
import com.comehere.ssgserver.member.infrastructure.MemberRepository;
import com.comehere.ssgserver.purchase.domain.Address;
import com.comehere.ssgserver.purchase.dto.req.AddressAddReqDTO;
import com.comehere.ssgserver.purchase.dto.resp.AddressListRespDTO;
import com.comehere.ssgserver.purchase.dto.resp.DefaultCheckRespDTO;
import com.comehere.ssgserver.purchase.infrastructure.AddressRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {

	private final MemberRepository memberRepository;
	private final AddressRepository addressRepository;

	// 해당 uuid의 회원이 가지고 있는 기본 배송지의 id를 반환
	public DefaultCheckRespDTO getDefaultAddress(UUID uuid) {

		Member member = findMember(uuid);

		return DefaultCheckRespDTO.builder()
				.addressId(addressRepository.getDefaultAddress(member.getUuid()))
				.build();
	}

	public void addAddress(UUID uuid, AddressAddReqDTO addressAddReqDTO) {

		Member member = findMember(uuid);
		Address address = createAddress(member, addressAddReqDTO);
		log.info("address: {}", address);
	}

	public AddressListRespDTO getAddressList(UUID uuid) {

		Member member = findMember(uuid);
		
		return AddressListRespDTO.builder()
				.addressIds(addressRepository.findAllByUuid(member.getUuid()).stream()
						.map(Address::getId) // Address 엔티티에서 ID를 가져오는 메소드를 호출
						.collect(Collectors.toList()))
				.build();
	}

	private Address createAddress(Member member, AddressAddReqDTO addressAddReqDTO) {

		return addressRepository.save(Address.builder()
				.name(addressAddReqDTO.getName())
				.nickname(addressAddReqDTO.getNickname())
				.phone(addressAddReqDTO.getPhone())
				.tel(addressAddReqDTO.getTel())
				.zipcode(addressAddReqDTO.getZipcode())
				.address(addressAddReqDTO.getAddress())
				.defaultAddress(false)
				.uuid(member.getUuid())
				.build());
	}

	private Member findMember(UUID uuid) {
		return memberRepository.findByUuid(uuid)
				.orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
	}
}
