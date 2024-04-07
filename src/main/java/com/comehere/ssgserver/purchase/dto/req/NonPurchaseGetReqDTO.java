package com.comehere.ssgserver.purchase.dto.req;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NonPurchaseGetReqDTO {
	private String name;

	private String phone;

	private String purchaseCode;

	@Builder
	public NonPurchaseGetReqDTO(String name, String phone, String purchaseCode) {
		this.name = name;
		this.phone = phone;
		this.purchaseCode = purchaseCode;
	}
}
