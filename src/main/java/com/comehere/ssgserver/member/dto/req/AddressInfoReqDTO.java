package com.comehere.ssgserver.member.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressInfoReqDTO {

	private String zipcode;

	private String address;

	private String detailAddress;
}
