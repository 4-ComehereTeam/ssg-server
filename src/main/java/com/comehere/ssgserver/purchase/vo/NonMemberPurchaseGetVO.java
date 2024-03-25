package com.comehere.ssgserver.purchase.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NonMemberPurchaseGetVO {
	private String nonMemberName;

	private String phoneNumber;

	private String code;
}
