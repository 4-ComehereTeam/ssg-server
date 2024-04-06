package com.comehere.ssgserver.purchase.vo.resp;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PurchaseListGetRespVO {
	private Long itemId;

	private String itemName;

	private String createdDate;

	private String status;
}
