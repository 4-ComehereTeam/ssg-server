package com.comehere.ssgserver.member.vo.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressInfoVO {

	private String zipcode;

	private String address;

	private String detailAddress;
}
