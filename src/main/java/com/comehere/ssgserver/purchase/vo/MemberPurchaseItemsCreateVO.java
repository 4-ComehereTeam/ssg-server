package com.comehere.ssgserver.purchase.vo;

import java.util.List;

import lombok.Getter;

@Getter
public class MemberPurchaseItemsCreateVO {
	private MemberPurchaseVO memberPurchaseVO;

	private List<PurchaseItemOptionVO> itemOptions;
}
