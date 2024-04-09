package com.comehere.ssgserver.purchase.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressAddReqDTO {

	private String nickname;

	private String name;

	private String phone;

	private String tel;

	private String zipcode;

	private String address;

	private String detailAddress;
}
