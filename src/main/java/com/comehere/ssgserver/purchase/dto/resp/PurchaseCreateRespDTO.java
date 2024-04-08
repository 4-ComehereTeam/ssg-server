package com.comehere.ssgserver.purchase.dto.resp;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PurchaseCreateRespDTO {
	private String purchaseCode;

	@Builder
	public PurchaseCreateRespDTO(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
}
