package com.comehere.ssgserver.purchase.vo;

import lombok.Getter;

@Getter
public class NonMemberPurchaseDeleteVO {
	private Long nonMemberPurchaseId;

	private String cancellationReasons;

	private String detailReasons;
}
