package com.comehere.ssgserver.purchase.dto.resp;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressDetailRespDTO {

	private String nickname;

	private String address;

	private String dtailAddress;

	private String zipcode;

	private String phone;
}
