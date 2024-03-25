package com.comehere.ssgserver.purchase.vo;

import lombok.Getter;

@Getter
public class NonMemberPurchaseCreateVO {
	private Long itemOptionId;

	private String itemName;

	private Long itemPrice;

	private Integer count;

	private String nonMemberName;

	private String phoneNumber;

	private String address;

	private String addressDetail;

	private String zipcode;

	private String email;

	private String requestMessage;

}
