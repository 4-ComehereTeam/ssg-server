package com.comehere.ssgserver.purchase.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NonMemberPurchaseGetDTO {
	private Long itemOptionId;

	private String itemName;

	private Long itemPrice;

	private Integer count;

	private Long itemId;

	@Builder

	public NonMemberPurchaseGetDTO(Long itemOptionId, String itemName, Long itemPrice, Integer count, Long itemId) {
		this.itemOptionId = itemOptionId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.count = count;
		this.itemId = itemId;
	}
}
