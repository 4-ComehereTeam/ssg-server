package com.comehere.ssgserver.purchase.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyAddressReqDTO {

	private Long addressId;

	private String nickName;

	private String name;

	private String phone;

	private String tel;

	private String zipcode;

	private String address;

	private String detailAddress;
}
