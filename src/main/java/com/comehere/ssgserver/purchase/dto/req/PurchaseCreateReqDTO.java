package com.comehere.ssgserver.purchase.dto.req;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PurchaseCreateReqDTO {
	private String name;

	private String phone;

	private String email;

	private String addressNickname;

	private String address;

	private String detailAddress;

	private String zipcode;

	private String requestMessage;

	private List<PurchaseListCreateReqDTO> purchases;
}
