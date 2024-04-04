package com.comehere.ssgserver.purchase.vo.req;

import lombok.Getter;

@Getter
public class NonPurchaseListDeleteReqVO {
	private String name;

	private String phone;

	private String purchaseCode;

	private Long purchaseListId;

	private String cancelReason;

	private String detailReason;
}
