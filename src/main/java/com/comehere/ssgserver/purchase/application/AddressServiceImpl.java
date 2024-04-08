package com.comehere.ssgserver.purchase.application;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comehere.ssgserver.member.domain.Member;
import com.comehere.ssgserver.member.infrastructure.MemberRepository;
import com.comehere.ssgserver.purchase.domain.Address;
import com.comehere.ssgserver.purchase.dto.AddressDetailDTO;
import com.comehere.ssgserver.purchase.dto.req.AddressAddReqDTO;
import com.comehere.ssgserver.purchase.dto.req.AddressReqDTO;
import com.comehere.ssgserver.purchase.dto.req.ModifyAddressReqDTO;
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

	// 해당 uuid의 회원에게 배송지를 추가
	@Override
	public void addAddress(UUID uuid, AddressAddReqDTO addressAddReqDTO) {

		Member member = findMember(uuid);
		Address address = createAddress(member, addressAddReqDTO);
		log.info("address: {}", address);
	}

	// 해당 uuid의 회원이 가지고 있는 기본 배송지의 id를 반환
	@Override
	public DefaultCheckRespDTO getDefaultAddress(UUID uuid) {

		Member member = findMember(uuid);

		return DefaultCheckRespDTO.builder()
				.addressId(addressRepository.getDefaultAddress(member.getUuid()))
				.build();
	}

	// 해당 uuid의 회원이 가지고 있는 배송지 목록을 반환
	@Override
	public AddressListRespDTO getAddressList(UUID uuid) {

		return AddressListRespDTO.builder()
				.addressIds(addressRepository.findAllByUuid(uuid).stream()
						.map(AddressDetailDTO::new) // Address 엔티티에서 ID를 가져오는 메소드를 호출
						.collect(Collectors.toList()))
				.build();
	}

	// 해당 uuid의 회원이 가지고 있는 기본 배송지 주소 -> false로 변경 후 새로운 기본 배송지 주소 -> true로 변경
	@Override
	@Transactional
	public Boolean changeDefaultAddress(UUID uuid, AddressReqDTO addressReqDTO) {

		addressRepository.cancelDefaultAddress(uuid);
		addressRepository.updateDefaultAddress(uuid, addressReqDTO.getAddressId());
		return true;
	}

	// 배송지 수정
	@Override
	public void updateAddressInfo(UUID uuid, ModifyAddressReqDTO modifyAddressReqDTO) {

		Address address = addressRepository.findById(modifyAddressReqDTO.getAddressId())
				.orElseThrow(() -> new IllegalArgumentException("해당 배송지가 존재하지 않습니다."));

		addressRepository.save(address.builder()
				.id(address.getId())
				.uuid(address.getUuid())
				.requestMessage(address.getRequestMessage())
				.defaultAddress(address.getDefaultAddress())
				.name(modifyAddressReqDTO.getName())
				.nickname(modifyAddressReqDTO.getNickName())
				.phone(modifyAddressReqDTO.getPhone())
				.tel(modifyAddressReqDTO.getTel())
				.zipcode(modifyAddressReqDTO.getZipcode())
				.address(modifyAddressReqDTO.getAddress())
				.detailAddress(modifyAddressReqDTO.getDetailAddress())
				.build());
	}

	// 배송지 삭제
	@Override
	public Boolean deleteAddress(UUID uuid, AddressReqDTO addressReqDTO) {
		addressRepository.deleteAddressById(addressReqDTO.getAddressId(), uuid);
		return true;
	}

	// 배송지 추가
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

	// 해당 uuid의 회원을 찾아 반환
	private Member findMember(UUID uuid) {
		return memberRepository.findByUuid(uuid)
				.orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
	}
}
