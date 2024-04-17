package com.comehere.ssgserver.purchase.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
