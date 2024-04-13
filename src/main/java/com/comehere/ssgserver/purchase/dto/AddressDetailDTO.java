package com.comehere.ssgserver.purchase.dto;

import com.comehere.ssgserver.purchase.domain.Address;

import lombok.Getter;

@Getter
public class AddressDetailDTO {

	private Long addressId;

	private String nickname;

	private String address;

	private String dtailAddress;

	private String zipcode;

	private String phone;

	public AddressDetailDTO(Address address) {

		this.addressId = address.getId();
		this.address = address.getAddress();
		this.dtailAddress = address.getDetailAddress();
		this.nickname = address.getNickname();
		this.phone = address.getPhone();
		this.zipcode = address.getZipcode();
	}
}
