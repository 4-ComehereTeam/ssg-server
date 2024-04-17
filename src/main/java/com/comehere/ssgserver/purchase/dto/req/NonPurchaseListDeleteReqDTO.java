package com.comehere.ssgserver.purchase.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NonPurchaseListDeleteReqDTO {
	private String senderName;

	private String senderPhone;

	private String purchaseCode;

	private Long purchaseListId;

	private String cancelReason;

	private String detailReason;
}
