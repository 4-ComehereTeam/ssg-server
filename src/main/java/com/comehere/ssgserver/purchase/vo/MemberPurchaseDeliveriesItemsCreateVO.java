package com.comehere.ssgserver.purchase.vo;

import java.util.List;

import lombok.Getter;

@Getter
public class MemberPurchaseDeliveriesItemsCreateVO {
	private List<MemberPurchaseVO> memberPurchaseVOS;

	private List<PurchaseItemOptionVO> itemOptions;
}
