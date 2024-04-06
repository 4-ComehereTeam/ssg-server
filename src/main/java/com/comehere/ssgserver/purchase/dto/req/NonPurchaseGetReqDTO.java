package com.comehere.ssgserver.purchase.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NonPurchaseGetReqDTO {
	private String name;

	private String phone;

	private String purchaseCode;
}
