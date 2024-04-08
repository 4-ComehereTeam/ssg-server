package com.comehere.ssgserver.purchase.vo.req;

import lombok.Getter;

@Getter
public class ModifyAddressReqVO {

	private Long addressId;

	private String nickName;

	private String name;

	private String phone;

	private String tel;

	private String zipcode;

	private String address;
	
	private String detailAddress;
}
