package com.comehere.ssgserver.purchase.dto.req;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NonPurchaseGetReqDTO {
	private String senderName;

	private String senderPhone;

	private String purchaseCode;

	@Builder
	public NonPurchaseGetReqDTO(String senderName, String senderPhone, String purchaseCode) {
		this.senderName = senderName;
		this.senderPhone = senderPhone;
		this.purchaseCode = purchaseCode;
	}
}
