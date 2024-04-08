package com.comehere.ssgserver.purchase.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NonPurchaseDeleteReqDTO {
	private String senderName;

	private String senderPhone;

	private String purchaseCode;
}
