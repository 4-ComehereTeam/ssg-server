package com.comehere.ssgserver.purchase.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddressDetailVO {

	private Long addressId;
	
	private String nickname;

	private String address;

	private String dtailAddress;

	private String zipcode;

	private String phone;
}
