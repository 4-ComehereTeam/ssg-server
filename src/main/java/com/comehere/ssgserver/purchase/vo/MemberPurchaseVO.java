package com.comehere.ssgserver.purchase.vo;

import lombok.Getter;

@Getter
public class MemberPurchaseVO {
	private Long memberId;

	private Long memberAddressId;

	private String requestMessage;

	private Boolean requestMessageSave;
}
