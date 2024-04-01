package com.comehere.ssgserver.purchase.vo.req;

import lombok.Getter;

@Getter
public class PurchaseListCreateReqVO {
	private Long itemOptionId;

	private String itemName;

	private Long itemPrice;

	private Integer itemDiscountRate;

	private Integer count;
}
